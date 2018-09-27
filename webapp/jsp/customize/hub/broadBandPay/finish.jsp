<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
            
    //BOSS ������ˮ
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
    //BOSS ����ʱ��
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	//�̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// �Ƿ���Ҫ�������
	String needRandomPwd = (String)request.getAttribute("needRandomPwd");
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
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
					document.forms[0].action = "${sessionScope.basePath}broadBandPay/broadBandPay.action";
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
						document.forms[0].action = "${sessionScope.basePath }broadBandPay/printInvoiceWithoutPwd.action";
						document.forms[0].submit();
					}
				}				
			}
			
			function sendRandomPwd()
			{
				document.getElementById("invoiceType").value = invoiceType;
						
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action="${sessionScope.basePath }broadBandPay/validateByRandomPwd.action";
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
				//modify by xkf57421 for R00043798 begin
				//if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				//{
					// �˿�
					//var ret = TakeOutUserCard();

					// �¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
					//if (ret == "0" && "1" == "<%=needCaptureCard %>")
					//{
						//startClock();
					//}
					//else if (ret != "0")
					//{
						//�¿��쳣
					//}
				//}
				//modify by xkf57421 for R00043798 end
				
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				var pDealNum = "<%=pDealNum%>";     //������ˮ��
				var pDealTime = "<%=pDealTime%>";   //����ʱ��
				var tMoney = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pDealStatus = "�ɷѳɹ�"; //����״̬
				var pTerminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
				
				//��ӡƾ֤
				if ("<%=canPrintReceipt %>" == "1")
				{	
					// �ɷ�СƱ
					doPrintPayProof_wband(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
							pDealStatus, pTerminalSeq, "", "0");
					// ��ӡ����СƱ
					if (payType == "<%=Constants.PAY_BYCARD %>")
					{
						var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
						if (printcontext != "")
						{
							//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
						}
					}
				}
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<!-- �����ƷNCODE -->
			<input type="hidden" id="ncode" name="ncode" value="<s:property value='ncode' />"/>
			
			<!-- �����Ʒ���� -->
			<input type="hidden" id="prodName" name="prodName" value="<s:property value='prodName' />"/>
			
			<!-- �����Ʒ���� -->
			<input type="hidden" id="detailDesc" name="detailDesc" value="<s:property value='detailDesc' />"/>
			
			<!-- ������ -->
			<input type="hidden" id="fee" name="fee" value="<s:property value='fee' />"/>
			
			<!-- �ֻ�������region -->
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			
			<!-- ����ʺ� -->
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
		
			<!-- Ͷ�ҽ�� -->
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			
			<!-- ������ˮ -->
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>">
			
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>����ɷ����̣�</h2>
      					<div class="blank10"></div>
					<a href="#">1. �������˺�</a> 
					<a href="#">2. ѡ������Ʒ</a>
					<a href="#">3. ѡ��֧����ʽ</a> 
					<a href="#">4. Ͷ��ֽ��</a>
    				<a href="#" class="on">5. ���</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>����˺ţ�<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">�ɷѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">���Ŀ���ɷ��ѳɹ���</p>
          						<p class="desc pt20">�뱣������ĵĽ���ƾ����</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>   
          							<!--        						
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ����</a>
          							<%
									if ("1".equals(canPrintInvoice))
									{						
									%>
									<a href="javascript:void(0);" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">��ӡ��ǰ��Ʊ</a>
									<%
									}
									%>          	
									 -->						
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
