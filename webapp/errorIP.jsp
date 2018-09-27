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


String errorType = (String) request.getAttribute("errorType");
if (errorType == null)
{
	errorType = request.getParameter("errorType");
}
if(null != errorType)
{
	if("001".equals(errorType))
	{
		errorMessage = "终端管理控件初始化失败,请联系终端厂商解决.";
	}
	else if("002".equals(errorType))
	{
		errorMessage = "通过终端管理控件获取终端IP失败,请联系终端厂商解决.";
	}
	else if("003".equals(errorType))
	{
		errorMessage = "终端控制器初始化异常,请检查终端管理控件安装是否正确.";
	}
	else if("004".equals(errorType))
	{
		errorMessage = "通过终端管理控件获取终端IP失败,请重启下终端机应用再试.";
	}
	
	//add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
	else if("005".equals(errorType))
	{
		errorMessage = "通过终端管理控件获取终端MAC失败,请联系终端厂商解决.";
	}
	//add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
}


//重新获取session时间
String refreshTime = "10";
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

var timeRefresh = <%=refreshTime %>;

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
	<% if(null == errorType){ %>
	//重新获取SESSION
	window.setTimeout("refreshSession()",timeRefresh*1000);
	<% } %>
}

function refreshSession()
{
	var ip ;
	try
	{
		ip = document.getElementById("mgrpluginid").GetLocalIpaddress();
	}
	catch (e)
  	{
		//控件获取IP失败
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }errorIP.jsp?errorType=004";
		document.forms[0].submit();
		return;
  	}
	if(ip == "" || ip == undefined)
	{
		//控件获取IP失败
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }errorIP.jsp?errorType=004";
		document.forms[0].submit();
		return;
	}
	<%--modify begin g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149 --%>
	var mac;
	try
	{
		mac = document.getElementById("mgrpluginid").GetLocalMac();
	}
	catch (e)
  	{
		//控件获取MAC失败
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }errorIP.jsp?errorType=005";
		document.forms[0].submit();
		return;
  	}
	if (mac == "" || mac == undefined)
	{
		//控件获取MAC失败
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }errorIP.jsp?errorType=005";
		document.forms[0].submit();
		return;
	}
	
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath }login/index.action?termIP=" + ip + "&termMac=" + mac;
	document.forms[0].submit();
	<%--modify end g00140516 2011/11/08 R003C11L11n01 OR_huawei_201111_149 --%>
}

</script>
</head>
	<body scroll="no" onload="getTermStatus();">
		<form name="actform" method="post">
			<br/><br/><br/>
			<div class="main" id="main">
				<h1><span></span></h1>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok">
							<% if(null == errorMessage || "".equals(errorMessage)){ %>
							   系统发生异常,当前会话已丢失, <%=refreshTime %>秒后获取新的会话。
							   <%}else{ 
							   out.println(errorMessage);
							   }%>
							   
							</span>
						</dd>
					</dl>
				</div>				
			</div>		
		</form>
	</body>
</html>