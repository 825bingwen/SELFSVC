<%@page contentType="text/html; charset=GBK"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	String url = (String) request.getAttribute("redirectURL");
	String menuID = (String) request.getAttribute("curMenuId");

	if (url.contains("?"))
	{
		response.sendRedirect(basePath + url + "&curMenuId=" + menuID);
	}
	else
	{
		response.sendRedirect(basePath + url + "?curMenuId=" + menuID);
	}
	
%>