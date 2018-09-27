<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
    String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新业务体验</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
setTimeout("back()",300000);

function back(){
    top.mainfrm.location.replace("login/login.action");
}
</script>
	</head>

	<body scroll="no" onload="" style="margin: 0 0 0 0">
		<iframe height="100%" width="100%" align="top" frameborder="0" name="mainfrm" src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_REC_TASTE_URL) %>"></iframe>
		<div
			style="position: absolute; top: 718px; right: 20px; width: 120px; height: 40px; z-index: 10000">
			<div class="return" onclick="back()" id="back">
				<img src="<%=path%>/images/home1.png" border="0" alt='首页' />
			</div>
		</div>
	</body>
</html>
