<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if (errorMessage == null)
{
	errorMessage = request.getParameter("errormessage");
}

// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
errorMessage = CommonUtil.errorMessage(errorMessage);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求


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
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<object id="mgrpluginid" classid="CLSID:90B097CF-4A8F-47B8-B7C2-166153FB4155"></object>
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

function getTermStatus()
{
	window.setTimeout(getTermStratus,5 * 60 * 1000);
}

function getTermStratus()
{
	document.forms[0].target = "_self";
	//document.forms[0].action = "${sessionScope.basePath}login/index.action?lockTerm=lockTerm";
	document.forms[0].action = "http://127.0.0.1:8000/NGSELFSVC/initip.jsp";
	document.forms[0].submit();
}
</script>
</head>
	<body scroll="no" onload="getTermStatus();">
		<form name="actform" method="post">
			<br/><br/><br/>
			<div class="main" id="main">
				<h1><span></span></h1>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix" style="width:750px;">
						<dd class="tc" style="">
							<span class="transcact_ok">
							<% 
							   out.println(errorMessage);
							 %>
							</span>
						</dd>
					</dl>
				</div>				
			</div>		
		</form>
	</body>
</html>