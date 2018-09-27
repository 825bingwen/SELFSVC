<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	String invoiceData = (String) request.getAttribute("invoice");
	System.out.println(invoiceData);	
	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>		
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript">
		
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
		}
		
		//确认
		function doFinish() 
		{ 
			var invoiceXml = getDocument();
			
			if (invoiceXml == null)
			{
				return;
			}
			var allInvoices = invoiceXml.getElementsByTagName("entry");
			// alert(allInvoices.length);
			// alert(invoiceXml.getElementsByTagName("entry")[0].innerHTML);
			
			
			for (i = 0; i < allInvoices.length; i++)
			{
			
				printInvoice(allInvoices[i]);
			}
			
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
				//打印湖北发票
				printInvoiceHB(invoice,'<%=termInfo.getOperid() %>');
				// var feeTime = getXmlInvoiceData(invoice, "formnum");
				var url = "${sessionScope.basePath }invoice/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&formnum=<s:property value='formnum' />";
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
					document.forms[0].action = "${sessionScope.basePath }invoice/valiPrint.action";
					document.forms[0].submit();
				}
			}
		
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="formnum" name="formnum" value="<s:property value="formnum" />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main" align="center">
				<%@ include file="/customer.jsp"%>
				<div class="div_blank_170w538h">
  						
  				</div>
  				<div class="pl30" > 					
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">发票正在打印</p>
          						<p id="resultMsg2" class="desc pt20">请稍侯。。。。。。</p>
          						<div class="btn_box4 clearfix">
          							<a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continuePrint();return false;">继续打印</a>     							
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
		
		
		//继续打印
		function continuePrint()
		{	
			document.payMoneyForm.target = "_self";
			document.payMoneyForm.action = "${sessionScope.basePath }invoice/invoiceList.action";
			document.payMoneyForm.submit();
		}
	</script>
</html>
