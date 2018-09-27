<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%
// 系统升级页面提示信息
String sysUpdateTip =  (String) PublicCache.getInstance().getCachedData("SH_SYS_UPDATE_PROMPT");

if(StringUtils.isEmpty(sysUpdateTip))
{
	sysUpdateTip = "尊敬的用户，系统正在升级，请稍后再试！";
}

// 系统升级页面返回首页校验周期（秒）
String chkGoHomeCycleTime =  (String) PublicCache.getInstance().getCachedData("SH_GOHOME_CHKCYCLE_TIME");

if(StringUtils.isEmpty(chkGoHomeCycleTime))
{
	chkGoHomeCycleTime = "30";
}
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
<script type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
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

var chkGoHomeCycleTime = '<%=chkGoHomeCycleTime%>';

function qrySysUpdateSwitch()
{
	var url = "${sessionScope.basePath }login/qrySysUpdateSwitch.action";
	
	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
				
		if (resXml != "1" || resXml != 1)
		{
			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=0");
		}								
	}, null, "POST", null, null);
	
}

window.setInterval("qrySysUpdateSwitch()",chkGoHomeCycleTime * 1000);
</script>
</head>
	<body scroll="no" >
		<form name="actform" method="post">
			<br/><br/><br/><br/><br/>
			<div class="main" id="main">
				<h1><span></span></h1>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix" style="width:750px;">
						<dd class="tc" style="">
							<span class="transcact_ok">
							<%=sysUpdateTip%>
							</span>
						</dd>
					</dl>
				</div>				
			</div>		
		</form>
	</body>
</html>