<%@page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 终端特性
String termSpecial = terminalInfo.getTermspecial();

//省份
String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

//add begin cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
// 页面渐进效果时间
String lateTime = "0.5";

if(Constants.PROOPERORGID_NX.equals(province))
{
	lateTime = "0.0";
}
//add end cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">		
		<meta http-equiv="Page-Exit"; content="blendTrans(Duration=<%=lateTime %>)"> 
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/net.js"></script>
		<script type="text/javascript">
		function goHomePage()
		{
			<%
			// modify begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
			// 现金稽核开关
			String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
			
			if ((Constants.PROOPERORGID_NX.equals(province) || "1".equals(cashAuditSwitch)) && termSpecial.charAt(3) == '1')
			{
			%>
				if (window.parent.first_frame == 0)
				{
					window.parent.first_frame = 1;
				
					var loader = new net.ContentLoaderSynchro("${sessionScope.basePath }managerOperation/getUnPrintRecord.action", 
					netload = function () {
						var returnStr = this.req.responseText;
					
						// 有未打印的结账单记录，跳转至现金稽核页面
						if (returnStr == 1)
						{
							window.location.href = "${sessionScope.basePath}managerOperation/doCashAudit.action?unprintFlag=1&from=index";
						}
						else
						{
							// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
							window.location.href = "${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
							// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
						}
					}, null, "POST", "", null);
				}
				else
				{
					// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
					window.location.href = "${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
					// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
				}
			<%
			}
			else
			{
			%>
				// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
				window.location.href ="${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
				// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
			<%
			}
			// modify end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
			%>
		}
		
		setTimeout("goHomePage()", 500)
		</script>
	</head>
	<body>
		<div class="header">
		<%
		if (!Constants.PROOPERORGID_SD.equals(province))
		{
		%>
	    	<div class="nx_new_logo"></div>
	    <%
		}
		else
		{
		%>
			<div class="logo"></div>
		<%
		}
		%>
		</div>
	  	<div class="index_main">
	    	<div class="blank60"></div>
	    	<div class="b966">
	    		<div class="blank60"></div>
	    		<div class="blank60"></div>
		      	<div class="relative tc">
		        	<img src="${sessionScope.basePath}images/loading.gif" alt="加载中..." />
		        	<div class="blank15"></div>
		        	<p class="tc fs22"></p>
		      	</div>
	    	</div>
	  	</div>
	</body>
</html>
