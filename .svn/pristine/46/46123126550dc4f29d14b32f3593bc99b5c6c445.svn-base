<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	// 清除缓存，防止页面后退安全隐患 
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
	
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
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
		//防止页面重复提交
		var submitFlag = 0;
		
		<%--add begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
		var enablePrintReceipt = 0;
		<%--add end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
		
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//更正
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//这句话最关键
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{
    		//只允许输入0-9
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
			
			//返回
			if (key == 220)
			{
				goback("<s:property value='curMenuId' />");
			}
			//退出
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}	
			//继续充值交费
			if (key == 49)
			{
				continueCharge();
			}
			
			<%--add begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%> 
			//打印小票(2)
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
			<%--add end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
			
			
		}
		//modify end cKF76106 2012/09/25 OR_NX_201209_258
			
		//确认
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
		
		<%--modify begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
		var printReceiptFlag = 0;
		function printReceipt()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "<s:property value='servnumber' />选择打印小票");
				
			if (printReceiptFlag == 0)
			{
				printReceiptFlag = 1;
					
				// 按钮按下，置不可用状态
				document.getElementById('dayinButton').className = 'hover';
				document.getElementById('dayinButton').onmousedown = "";
				document.getElementById('dayinButton').onmouseup = "";
				document.getElementById('dayinButton').onclick = "";
					
				var pServNumber = "<s:property value='servnumber' />";
				var pOperID = "<%=termInfo.getOperid() %>";  //打印地点
				var pDealNum = "<s:property value='dealNum' />";     //交易流水号
				var pDealTime = "<s:property value='dealTime' />";   //交易时间
				var tMoney = "<s:property value='tMoney' />元";     //交易金额
				var pSubsName = "<%=subsName %>";
				var pLatestBalance = "<s:property value='balance' />元";
					
				//打印凭证
				if ("<%=canPrintReceipt %>" == "1")
				{	
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"自动打印凭条");
						
					doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					
					document.getElementById("resultMsg1").innerHTML = "您的小票已打印成功！";
					document.getElementById("resultMsg2").innerHTML = "请保存好您的小票。";
				}
			}
		}
		<%--modify end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
		
			/**
			  * 取得缓存相关发票xml数据的documentElement对象
			  * id xml对象的ID属性
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
        			pubErrShow("浏览器不能识别，无法进行发票打印!");
        			
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
				
				//打印最后一张发票
				var allInvoices = invoiceXml.getElementsByTagName("entry");
				printInvoice(allInvoices[allInvoices.length - 1]);
			}
			
			function printInvoice(invoice) 
			{
				if (invoice == undefined)
				{
					document.getElementById("resultMsg1").innerHTML = "您的发票打印失败！";
					document.getElementById("resultMsg2").innerHTML = "请打印小票。";
					document.getElementById("failedDIV").style.display = "block";
					enablePrintReceipt = 1;
					alertError("获取发票打印信息失败！");
					return;
				}
				
				<%--modify begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
				//打印宁夏发票				
				var result = printInvoiceNX(invoice);
				
				if (result == "")
				{
					document.getElementById("resultMsg1").innerHTML = "您的发票已打印成功！";
					document.getElementById("resultMsg2").innerHTML = "请保存好您的发票。";
					
					document.getElementById("successDIV").style.display = "block";

					var feeTime = getXmlInvoiceData(invoice, "feeTime");
				
					var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&cycle=" + feeTime;
					var loader = new net.ContentLoader(url, null, null, "POST", "", null);
				}
				else
				{
					enablePrintReceipt = 1;
					
					document.getElementById("resultMsg1").innerHTML = result;
					document.getElementById("resultMsg2").innerHTML = "请选择打印小票。";
					
					document.getElementById("failedDIV").style.display = "block";
				}
				<%--modify end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
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
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择金额</a> 
      					<a href="#">3. 选择支付方式</a> 
      					<a href="#">4. 投入纸币</a>
          				<a href="#" class="on">5. 完成</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">正在打印</p>
          						<p id="resultMsg2" class="desc pt20">请稍侯</p>
          						<%--modify begin g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
          						<div id="successDIV" class="btn_box2_echo clearfix" style="display:none;">
          							<a href="javascript:void(0)" class="ml190" onmousedown="this.className='hover ml190'" onmouseup="this.className='ml190'" onclick="continueCharge();return false;">继续充值交费(请按1键)</a>     							
          						</div>
          						<div id="failedDIV" class="btn_box2_echo clearfix" style="display:none;">
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费 (请按1键)</a>
          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;">打印小票 (请按2键)</a>   							
          						</div>
          						<%--modify end g00140516 2013/02/02 R003C13L01n01 发票打印失败时，提供小票打印功能 --%>
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
		
		//继续充值缴费
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
