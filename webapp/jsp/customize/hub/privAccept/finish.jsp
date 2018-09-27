<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
            
    String successmessage = (String) request.getAttribute("successmessage");

    String payType = (String) request.getAttribute("payType");
    
    String tMoney = (String) request.getAttribute("tMoney");
    if(null != tMoney)
    {
        if (Constants.PAY_BYCARD.equals(payType))
        {
    	    tMoney = CommonUtil.fenToYuan(tMoney);
        }
    }
    
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	String recStatus = (String) request.getAttribute("errormessage");
	if (recStatus == null)
	{
		recStatus = "";
	}
	
	String errFlag = (String)request.getAttribute("errFlag");
	if(errFlag == null){
		errFlag =  "";
	}
	
	//�̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	//String needRandomPwd = (String) PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD");
	String needRandomPwd = "0";
	//add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽�� begin
    String dealAddedTax = (String) PublicCache.getInstance().getCachedData("DealAddedTax");
	//add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽��  end

	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_hb.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/SelfPrivPrinter_Hub.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var payType = "<s:property value='payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//ȡ��ʱ��30��
	
		var invoiceType = "";
		
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//����
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//��仰��ؼ�
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{
    		//ֻ��������0-9
    		if (KeyCode >= 48 && KeyCode <= 57)
    		{
    			return true;
    		}
    		
    		return false;
		}	
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}			
		}
		
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }privAccept/qryPriv.action";
					document.forms[0].submit();
				}
			}		
			
			//��ӡ��ǰ��Ʊ���ж��ŷ�Ʊ��ӡ���һ��
			function printInvoice(invoicetype)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					invoiceType = invoicetype;
					
					//��ӡ��Ʊʱ��Ҫ�������
					if ("1" == "<%=needRandomPwd %>")
					{
						openWindow('popup_confirm');
					}
					else
					{
						document.getElementById("invoiceType").value = invoiceType;
						document.forms[0].target = "_self";
						document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
						document.forms[0].submit();
					}
				}				
			}
			
			function sendRandomPwd()
			{
				document.getElementById("invoiceType").value = invoiceType;
						
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action="${sessionScope.basePath }charge/validateByRandomPwd.action";
				document.payMoneyForm.submit();
			}
			
			// ��ȡ������ȡ��״̬
			function takeOutBankCardStatus() 
			{
				limitTime = limitTime - 1;
				
				if (limitTime <= 0)
				{
					//��ʱ�������ʱ����ͬʱ�̿�
					clearInterval(waitingToken);
					
					captureUserCard();
					
					return;
				}
				
				try 
				{
					//������
					var ret = TakeOutUserCardStatus();//��ȡ������ȡ��״̬
					
					//������
					//var ret = 0;
					if (ret == "0" || ret == 0) 
					{	
						//�Ѿ�ȡ�߿��������ʱ����
						clearInterval(waitingToken);								
					}			
				}
				catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
			}
			
			function startClock()
			{
				try 
				{
					waitingToken = setInterval("takeOutBankCardStatus()", 1000);
				}
				catch (e) {}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
			}
			
			function doFinish()
			{
				if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				{
					// �˿�
					var ret = TakeOutUserCard();

					//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
					if (ret == "0" && "1" == "<%=needCaptureCard %>")
					{
						startClock();
					}
					else if (ret != "0")
					{
						//�¿��쳣
					}
				}
				
				var prtData = new function () 
			    {
					//�û�����
			        this.servnumber = "<s:property value='servnumber' />";
			        //�ն���Ϣ
			        this.termID = '<%=termInfo.getTermid()%>';
			        this.username = '';
			        this.groupname = '<s:property value="privName" escape="false" />';
			        //�Ӧ��
			        this.prepayfee = "<s:property value='privMoney' />";
			        //�Ż�����ʱ��
			        this.recDate = "<s:property value='priRecDate' />";
			        //Ͷ�ҽ��
			        this.tMoney = '<%=tMoney == null ? "" : tMoney%>';
			        //�ɷѽ��
			        this.tmpMoney = '<s:property value='tmpMoney' />';
			        //����״̬
			        this.payStatus = '<s:property value='payStatus' />';
			        //�ɷ���ˮ
			        this.dealNum = '<%=pDealNum == null ? "" : pDealNum%>';
			        this.transResult = "<%=successmessage == null ? "����ɹ�" : successmessage%>";
			        //��ӡ�ص�
			        this.pOrgName = '<%=pOrgName%>';
			        //�ɷ�ʱ��
			        this.dealTime = '<%=pDealTime == null ? "" : pDealTime%>';
			        //��ӡʱ��
			        this.pPrintDate = getDateTime();
			    }
				
				//var pServNumber = "<s:property value='servnumber' />";
				//var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				//var pPrintDate = getDateTime();  //��ӡ����
				//var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				//var priAmount = "<s:property value='privMoney' />Ԫ";   //����
				//var privToMoney = "<s:property value='priBackMoney' />Ԫ";    //Ͷ�ҽ��
				//var pRecDate = "<s:property value='priRecDate' />";       //�����ʱ��
				//var pBackAmount =  "";	//�˻����
				//var pDealNum = "<%=pDealNum%>";     //������ˮ��
				//var pDealTime = "<%=pDealTime%>";   //����ʱ��
				//var tMoney = "<s:property value='priBackMoney' />Ԫ";     //���׽��
				//var pDealStatus = "<%=recStatus%>"; //����״̬
				//var pTerminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
				//var printFlag = "<%=errFlag%>";  //errFlag Ϊ0ʱû�нɷѽ��
				//��ӡƾ֤
				if ("<%=canPrintReceipt %>" == "1")
				{	
								
					//alert(pServNumber + "	" + pOrgName + "	" + pPrintDate + "	" + pTerminalInfo + "	"  + pDealNum + "	" + pDealTime + "	" + tMoney + "	" + pDealStatus + "	" + pTerminalSeq);

					//doPrintPrivPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, priAmount, privToMoney,pRecDate, pBackAmount, pDealNum, pDealTime, tMoney, pDealStatus, pTerminalSeq,printFlag);
					recNotePrint(prtData);
					
					if (payType == "<%=Constants.PAY_BYCARD %>")
					{
						var printcontext = '<%=(String)request.getAttribute("printcontext") %>';
						//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
					}
				}
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>">
			<!-- Chagne by LiFeng �����Ż����� 20111121 begin -->
			<input type="hidden" id="privId" name="privId" value='<s:property value="privId" />'/>
			<input type="hidden" id="privName" name="privName" value='<s:property value="privName" />'/>
			<!-- Chagne by LiFeng �����Ż����� 20111121 End -->	
					<!-- Chagne by LiFeng �����Ż����� 20111121 begin -->
		<input type="hidden" id="favorabletype" name="favorabletype" value="<s:property value="favorabletype" />"/>
		<!-- Chagne by LiFeng �����Ż����� 20111121 end -->
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>�Żݽ������̣�</h2>
      					<div class="blank10"></div>
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ���Ż�</a> 
      					<a href="#">3. ѡ��֧����ʽ</a> 
      					<a href="#">4. Ͷ��ֽ��</a>
          				<a href="#" class="on">5. ���</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><%=tMoney %>Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">ҵ�����ɹ���</p>
          						<p class="desc pt20">
          						
									<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©�������� --%>	
									<%=errorMessage %>      							
									<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©�������� --%>
								
								</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>          						
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">���������Ż�</a>
          							<%
									if ("0".equals(canPrintInvoice)&&(!"0".equals(errFlag)))
									{	
									  //add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽�� begin
									    if(!"1".equals(dealAddedTax)){
									   //add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽�� end
									%>
									<a href="javascript:void(0);" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">��ӡ��ǰ��Ʊ</a>
									<%
									//add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽�� begin
										}
										  //add by yKF73963 20140221 for OR_HUB_201401_662  [Ӫ����]Ӫ���������ն˲��ָ��췽�� end
									}
									%>          							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
  				
  				<div class="popup_confirm" id="popup_confirm">
                  	<div class="bg"></div>
                  	<div class="tips_title">��ʾ��</div>
                  	<div class="tips_body">
	    				<p>������ӡ��Ʊ��Ϊ��ȷ������Ϣ�İ�ȫ��ϵͳ���·�����У���뵽�����ֻ����뽫�յ��Ķ���У���������¸�ҳ���У������ڡ�</p>
	    				<div class="blank10"></div>
	    				<p class="mt30">��ȷ���Ƿ������</p>
  					</div>
                  	<div class="btn_box tc mt20">
                  		<span class=" mr10 inline_block ">
                  			<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="sendRandomPwd();return false;">ȷ��</a>
                  		</span>
                  		<span class=" inline_block ">
                  			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();submitFlag=0;">ȡ��</a>
                  		</span>
                  	</div>
                </div>
				<script type="text/javascript">
				openWindow = function(id)
				{
					wiWindow = new OpenWindow("popup_confirm", 708, 392);//�򿪵�����������
				}				
				</script>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
	//������ֵ�ɷ�
	function continueCharge()
	{	
		document.payMoneyForm.target = "_self";
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		document.payMoneyForm.submit();
	}
	</script>
</html>
