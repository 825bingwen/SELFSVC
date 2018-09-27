<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	String invoiceData = (String) request.getAttribute("invoiceXML");
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	String subsName = (String) request.getAttribute("subsName");
	if (subsName == null)
	{
		subsName = "";
	}
	
	String invoiceType = (String) request.getAttribute("invoiceType");
	
	// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript">
		<!--
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		<%--add begin g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
		var enablePrintReceipt = 0;
		<%--add end g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
		
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
		
		//modify begin cKF76106 2012/09/25 OR_NX_201209_258	
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 220)
			{
				goback("<s:property value='curMenuId' />");
			}
			//�˳�
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}	
			//������ֵ����
			if (key == 49)
			{
				continueCharge();
			}
			
			<%--add begin g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%> 
			//��ӡСƱ(2)
			if (key == 50 && enablePrintReceipt == 1)
			{
				printReceipt();
			}
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			<%--add end g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
			
			
		}
		//modify end cKF76106 2012/09/25 OR_NX_201209_258
			
		//ȷ��
		function doFinish() 
		{		
			if ("1" == "<%=invoiceType %>")
			{
				printSingleInvoice();
			}
			else
			{
				printReceipt();
			}			
		}
		
		<%--modify begin g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
		var printReceiptFlag = 0;
		function printReceipt()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "<s:property value='servnumber' />ѡ���ӡСƱ");
				
			if (printReceiptFlag == 0)
			{
				printReceiptFlag = 1;
					
				// ��ť���£��ò�����״̬
				document.getElementById('dayinButton').className = 'hover';
				document.getElementById('dayinButton').onmousedown = "";
				document.getElementById('dayinButton').onmouseup = "";
				document.getElementById('dayinButton').onclick = "";
					
				var pServNumber = "<s:property value='servnumber' />";
				var pOperID = "<%=termInfo.getOperid() %>";  //��ӡ�ص�
				var pDealNum = "<s:property value='dealNum' />";     //������ˮ��
				var pDealTime = "<s:property value='dealTime' />";   //����ʱ��
				var tMoney = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pSubsName = "<%=subsName %>";
				var pLatestBalance = "<s:property value='balance' />Ԫ";
					
				//��ӡƾ֤
				if ("<%=canPrintReceipt %>" == "1")
				{	
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"�Զ���ӡƾ��");
						
					doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					
					document.getElementById("resultMsg1").innerHTML = "����СƱ�Ѵ�ӡ�ɹ���";
					document.getElementById("resultMsg2").innerHTML = "�뱣�������СƱ��";
				}
			}
		}
		<%--modify end g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
		
			/**
			  * ȡ�û�����ط�Ʊxml���ݵ�documentElement����
			  * id xml�����ID����
			  */
			function getDocument() 
			{
   				try
   				{
	    			var xmlDoc; 
	    			if (IsIE() == 1)
	    			{
	        			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		    			xmlDoc.async = "false";
		    			xmlDoc.loadXML('<%=invoiceData%>');
		    			return xmlDoc;
	    			}
	    			else
	    			{
	        			var parser = new DOMParser();
	        			xmlDoc = parser.parseFromString('<%=invoiceData%>', "text/xml");
	        			return xmlDoc;
	    			}
    			} 
    			catch(e) 
    			{
        			pubErrShow("���������ʶ���޷����з�Ʊ��ӡ!");
        			
        			return null;
    			}
			}
			
			function printSingleInvoice()
			{
				var invoiceXml = getDocument();
				if (invoiceXml == null)
				{
					return;
				}
				
				//��ӡ���һ�ŷ�Ʊ
				var allInvoices = invoiceXml.getElementsByTagName("entry");
				printInvoice(allInvoices[allInvoices.length - 1]);
			}
			
			function printInvoice(invoice) 
			{
				if (invoice == undefined)
				{
					document.getElementById("resultMsg1").innerHTML = "���ķ�Ʊ��ӡʧ�ܣ�";
					document.getElementById("resultMsg2").innerHTML = "���ӡСƱ��";
					document.getElementById("failedDIV").style.display = "block";
					enablePrintReceipt = 1;
					alertError("��ȡ��Ʊ��ӡ��Ϣʧ�ܣ�");
					return;
				}
				
				<%--modify begin g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
				//��ӡ���ķ�Ʊ				
				var result = printInvoiceNX(invoice);
				
				if (result == "")
				{
					document.getElementById("resultMsg1").innerHTML = "���ķ�Ʊ�Ѵ�ӡ�ɹ���";
					document.getElementById("resultMsg2").innerHTML = "�뱣������ķ�Ʊ��";
					
					document.getElementById("successDIV").style.display = "block";

					var feeTime = getXmlInvoiceData(invoice, "feeTime");
				
					var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&cycle=" + feeTime;
					var loader = new net.ContentLoader(url, null, null, "POST", "", null);
				}
				else
				{
					enablePrintReceipt = 1;
					
					document.getElementById("resultMsg1").innerHTML = result;
					document.getElementById("resultMsg2").innerHTML = "��ѡ���ӡСƱ��";
					
					document.getElementById("failedDIV").style.display = "block";
				}
				<%--modify end g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
			}
			
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					if (document.getElementById("backWaitingFlag").value == "1")
					{
						openRecWaitLoading_NX("recWaitLoading");
					}
					// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
					document.forms[0].submit();
				}
			}
		//-->
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData %>'>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
      					<div class="blank10"></div>
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ����</a> 
      					<a href="#">3. ѡ��֧����ʽ</a> 
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
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">���ڴ�ӡ</p>
          						<p id="resultMsg2" class="desc pt20">���Ժ�</p>
          						<%--modify begin g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
          						<div id="successDIV" class="btn_box2_echo clearfix" style="display:none;">
          							<a href="javascript:void(0)" class="ml190" onmousedown="this.className='hover ml190'" onmouseup="this.className='ml190'" onclick="continueCharge();return false;">������ֵ����(�밴1��)</a>     							
          						</div>
          						<div id="failedDIV" class="btn_box2_echo clearfix" style="display:none;">
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ���� (�밴1��)</a>
          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;">��ӡСƱ (�밴2��)</a>   							
          						</div>
          						<%--modify end g00140516 2013/02/02 R003C13L01n01 ��Ʊ��ӡʧ��ʱ���ṩСƱ��ӡ���� --%>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
		doFinish();
		
		//������ֵ�ɷ�
		function continueCharge()
		{	
			if (submitFlag == 0)
			{
				submitFlag = 1;
					
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			
				document.payMoneyForm.target = "_self";
				document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
				document.payMoneyForm.submit();
			}
		}
	</script>
</html>
