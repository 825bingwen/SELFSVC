<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
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
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/qryfeeChargeAccount.action";
				document.forms[0].submit();
			}
		}
		
		function inpPayCenter()
		{
			var payCenterUrl = document.getElementById("redirectUrl").value;	
			//payCenterFrame.location.href = payCenterUrl;
			window.open(payCenterUrl,'payCenterFrame');
			//chkPayCenterTimeOut();
		}

		function chkPayCenterTimeOut()
		{			
			setTimeout(function(){ window.location.reload();},5000);
		}
		
		function payCenterCallback()
		{
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }charge/intPayCetChargeCommt.action";
			document.forms[0].submit();
		}
		
	</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="inpPayCenter()">
		<form name="actform" method="post"></form>
			<input type="hidden" id="taskoid" name="taskoid" value="<s:property value='#request.taskoid'/>" />
			<input type="hidden" id="redirectUrl" name="redirectUrl"  value="<s:property value='redirectUrl' />" />
			<input type="hidden" id="chargeMoney" name="chargeMoney" value="<s:property value='chargeMoney' />" />
			<%@ include file="/titleinc.jsp"%>		
			<div class="main" id="main">
				<iframe name="payCenterFrame" id="payCenterFrame" frameborder="0" scrolling="auto" style="" height="100%" width="100%"></iframe>
				
			</div>		
			<%@ include file="/backinc.jsp"%>		
	</body>
</html>
