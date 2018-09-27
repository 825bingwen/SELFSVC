<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	String invoiceData = (String) request.getAttribute("invoice");
	// 1:银联卡 4:现金
	String payType = (String)request.getAttribute("payType");
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
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
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
				var feeTime = getXmlInvoiceData(invoice, "chargeDate");
				
				var url = "${sessionScope.basePath }activitiesRec/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&recoid=<s:property value='recoid' />";
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
					document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
					document.forms[0].submit();
				}
			}
		//-->
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />" />
						
			<!-- 营销推荐标识 -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  					<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<%
      					if ("4".equals(payType))
      					{
      					%>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#" class="on">5. 完成</a>
          				<%
          				}
          				else
          				{
          				%>
						<a title="选择活动档次" href="#">1. 选择活动档次</a>
    					<a title="受理协议" href="#">2. 受理协议</a> 
    					<a title="选择支付方式" href="#">3. 选择支付方式</a>
    					<a title="插入储蓄卡" href="#">4. 插入储蓄卡</a>
    					<a title="输入密码" href="#">5. 输入密码</a>
    					<a title="核对信息" href="#">6. 核对信息</a>
    					<a title="完成" href="#" class="on">7. 完成</a>
          				<%
          				}
          				%>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value="#request.totalfee_yuan" />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">发票正在打印</p>
          						<p id="resultMsg2" class="desc pt20">请稍侯</p>
          						<div class="btn_box4 clearfix">
	          						<s:if test='recommendActivityFlag == "1"'>
	          							<a href="javascript:void(0);" onclick="continueHandle();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">继续办理</a>
	          						</s:if>
	          						<s:else>
	          							<a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续促销活动</a>     							
	          						</s:else>
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
			document.payMoneyForm.action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
			document.payMoneyForm.submit();
		}
		
		// 暂不办理，继续原有业务
		function continueHandle()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading('recWaitLoading');
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action = "${sessionScope.basePath}recommendActivity/continueHandle.action";
				document.payMoneyForm.submit();
			}
		}
	</script>
</html>
