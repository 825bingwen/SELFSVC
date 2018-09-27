<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String month = (String)request.getAttribute("month0");
	String month1 = (String)request.getAttribute("month1");
	String month2 = (String)request.getAttribute("month2");
	String month3 = (String)request.getAttribute("month3");
	String month4 = (String)request.getAttribute("month4");
	String month5 = (String)request.getAttribute("month5");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
</head>
  
<body scroll="no">

	<form action="" name="actform" method="post">
		<s:hidden name="invoicePrint.billCycle" id="month" />
		<%@ include file="/titleinc.jsp" %>
		<div class="main" id="main">
			<%@ include file="/customer.jsp" %>
			
			<div class="blank30"></div>
          	<p class="fs18 p20 ml50">请选择发票月份：</p>
         	<div class="blank15"></div>
          	<div class="tc p120 ">
	            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month %>')"><%=month %></a>
	            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month1 %>')"><%=month1 %></a>
	            <div class="blank25"></div>
	            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month2 %>')"><%=month2 %></a>
	            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month3 %>')"><%=month3 %></a>
	            <div class="blank25"></div>
	            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month4 %>')"><%=month4 %></a>
	            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month5 %>')"><%=month5 %></a>
          </div>
		</div>
        <%@ include file="/backinc.jsp" %> 
	</form>
</body>
<script type="text/javascript">

	// 提交的标志位
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
	
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = menuid;
					
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath}login/backForward.action";
			document.forms[0].submit();
		}
	}
	
	// 点击月份信息，触发此函数
	function btnClick(btn,btClass,month)
	{
		 // 获取点击的月份信息
		document.getElementById("month").value = month;
		
		document.actform.target = "_self";
		
		<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
		openRecWaitLoading();
		<%-- add end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
		
		<s:if test="%{#request.curMenuId == 'rePrintInvoice' || #parameters.curMenuId == 'rePrintInvoice'}">
			// 提交表单的action信息
			document.actform.action = "${sessionScope.basePath}invoice/invoiceList.action";
		</s:if>
		<s:else>
			// 提交表单的action信息
			document.actform.action = "${sessionScope.basePath}monthInvoicePrint/qryBillCycle.action";
        </s:else>
				
		document.actform.submit();
	}
	
</script>
</html>
