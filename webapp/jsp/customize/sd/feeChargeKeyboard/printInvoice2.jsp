<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	String invoiceData = (String) request.getAttribute("invoiceXML");
	String invoiceType = (String) request.getAttribute("invoiceType");
	String printType = (String) request.getAttribute("printType");
	
	//add begin by cwx456134 2017-04-19  2017-04-19 OR_SD_201703_234_山东_电子发票优化需求
	String isElectronInvoice = (String) request.getAttribute("isElectronInvoice");
	String servnumber = (String) request.getAttribute("servnumber");
	//add end by cwx456134 2017-04-19  2017-04-19 OR_SD_201703_234_山东_电子发票优化需求
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		<!--
		//防止页面重复提交
		var submitFlag = 0;
		
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
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />");
			}	
			
			if (key == 49)
			{
				continueCharge();
			}	
		}
		
		//确认
		function doFinish() 
		{
			if ("Last" == "<%=invoiceType %>")
			{
				printLastInvoice();
			}		
		}
		
		function printLastInvoice()
		{			
			<%-- add begin by cwx456134 2017-04-19 OR_SD_201703_234_山东_电子发票优化需求 --%>
			<%-- 获取开关，是否启动电子发票，如启动，走此处逻辑 ；电子发票接口不返回发票信息--%>
			var isElectronInvoice = "<%=isElectronInvoice %>";
			if (isElectronInvoice == "true")
			{
				var servnumber = "<%=servnumber %>";
				var innerHtml = "<span style='font-size:23px;line-height:35px;'>当前开具发票为电子发票，电子发票开具后将推送到您的邮箱："+servnumber+"@139.com，请注意查收。</span>";
				document.getElementById("resultMsg1").innerHTML = innerHtml;
				document.getElementById("resultMsg2").innerHTML = "";
				return;
			}
			<%-- add end by cwx456134 2017-04-19 OR_SD_201703_234_山东_电子发票优化需求 --%>
			
			var invoiceXml = getDocument();
			if (invoiceXml == null)
			{
				return;
			}
			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"发票打印开始");
			
			<%--modify begin g00140516 2012/05/03 R003C12L04n01 打印全部充值交费发票 --%>	
			//打印全部发票
			var allInvoices = invoiceXml.getElementsByTagName("entry");
			for (i = 0; i < allInvoices.length; i++)
			{
				printInvoice(allInvoices[i]);
			}
			<%--modify end g00140516 2012/05/03 R003C12L04n01 打印全部充值交费发票 --%>
			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"发票打印结束");
			
			document.getElementById("resultMsg1").innerHTML = "您的发票已打印成功！";
			document.getElementById("resultMsg2").innerHTML = "请保存好您的发票。";
		}
		
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
			
			function printInvoice(invoice) 
			{
				// 打印山东发票
				if ("1" == "<%=printType %>")
				{
					printInvoiceSD1(invoice);
				}
				else
				{
					printInvoiceSD(invoice);
				}
				
				var feeTime = getXmlInvoiceData(invoice, "feeTime");
				
				var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&cycle=" + feeTime;
				var loader = new net.ContentLoader(url, null, null, "POST", "", null);
			}
			
			function getXmlInvoiceData(invoice, itemname) 
			{
				var items = invoice.getElementsByTagName(itemname);
				var itemdata = "";
				if (items.length > 0) {
					itemdata = items[0].childNodes[0].nodeValue;
				}
				return itemdata;
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
		//-->
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择支付方式</a> 
      					<a href="#">3. 选择金额</a> 
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
       							<p id="resultMsg1" class="title yellow pt30">发票正在打印</p>
          						<p id="resultMsg2" class="desc pt20">请稍侯</p>
          						<div class="btn_box2_echo clearfix">
          							<a href="javascript:void(0)" class="ml190" onmousedown="this.className='hover ml190'" onmouseup="this.className='ml190'" onclick="continueCharge();return false;">继续充值交费(请按1键)</a>     							
          						</div>
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
			document.payMoneyForm.target = "_self";
			document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
			document.payMoneyForm.submit();
		}
	</script>
</html>
