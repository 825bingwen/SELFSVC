<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintInvoice = termSpecial.substring(1, 2);
          
    //BOSS ������ˮ
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
    //BOSS ����ʱ��
    // modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	String pDealTime = (String) request.getAttribute("pDealTime");
	// modify end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	// �Ƿ���Ҫ�������
	String needRandomPwd = (String)request.getAttribute("needRandomPwd");
	
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
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
			
			// ������ֵ����
			if (key == 49)
			{
				continueCharge();
			}
			
			// ��ӡ��Ʊ
			if (key == 50)
			{
				printInvoice('Last');
			}			
		}
		
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
					document.forms[0].submit();
				}
			}		
			
			//��ӡ��ǰ��Ʊ���ж��ŷ�Ʊ��ӡ���һ��
			function printInvoice(invoicetype)
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />ѡ���ӡ��Ʊ");
				
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
					
					//�̿�
					CaptureBankCard();
					
					return;
				}
				
				try 
				{
					//������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
					var ret = getCardPosition();
					
					//������
					if (ret == "1" || ret == 1) 
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
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />���ѳɹ�");
				
				if (payType == "<%=Constants.PAY_BYCARD %>")
				{
					//������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
					var ret = getCardPosition();
					
					//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
					if (ret == "2")
					{
						startClock();
					}
					else if (ret != "0")
					{
						//�¿��쳣
					}
				}
				
				<%-- modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371--%>
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				var pDealNum = "<%=pDealNum%>";     //������ˮ��
				var pDealTime = "<%=pDealTime%>";   //����ʱ��
				var tMoney = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pDealStatus = "���ѳɹ�"; //����״̬
				var pTerminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"�Զ���ӡƾ��");
				
				//modify begin g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
				doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
							pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />");
				//modify end g00140516 2012/04/24 R003C12L03n01 ƾ����ӡʱ��ӡ�ͻ�����
				
				<%-- modify end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371--%>	
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
      					<div class="blank10"></div>
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ��֧����ʽ</a> 
      					<a href="#">3. ѡ����</a> 
      					<a href="#">4. Ͷ��ֽ��</a>
          				<a href="#" class="on">5. ���</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
          						<p class="desc pt20">�뱣������ĵĽ���ƾ����</p>
          						<div class="btn_box2_echo clearfix">         						
          							<a href="javascript:void(0);" class="ml5" onmousedown="this.className='hover ml5'" onmouseup="this.className='ml5'" onclick="continueCharge();return false;" >������ֵ���� (�밴1��)</a>
          							
          							<%
									if ("1".equals(canPrintInvoice))
									{						
									%>
									<%--modify begin g00140516 2012/05/03 R003C12L04n01 ��ӡȫ����ֵ���ѷ�Ʊ --%>
									<a href="javascript:void(0);" class="ml20" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover ml20'" onmouseup="this.className='ml20'" >��ӡ��Ʊ (�밴2��)</a>
									<%--modify end g00140516 2012/05/03 R003C12L04n01 ��ӡȫ����ֵ���ѷ�Ʊ --%>
									<%
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
