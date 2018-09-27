<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

// 当前菜单ID
String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
if (currMenuID == null || "".equals(currMenuID.trim()))
{
	currMenuID = request.getParameter(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = "root";
	}
}

String url = "";
// 终端信息
TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
List totalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
if (totalMenus != null && totalMenus.size() > 0)
{
	MenuInfoPO menu = null;
	for (int i = 0; i < totalMenus.size(); i++)
	{
		menu = (MenuInfoPO) totalMenus.get(i);
		if (currMenuID.equals(menu.getMenuid()))
		{
			url = menu.getGuiobj();
			break;
		}		
	}
}
List spMenus = (List) PublicCache.getInstance().getCachedData("10000000000000");
if (spMenus != null && spMenus.size() > 0)
{
	MenuInfoPO menu = null;
	for (int i = 0; i < spMenus.size(); i++)
	{
		menu = (MenuInfoPO) spMenus.get(i);
		if (currMenuID.equals(menu.getMenuid()))
		{
			url = menu.getGuiobj();
			break;
		}		
	}
}

//add begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597

// 产品大类
String typeID = "";

if(null != request.getAttribute("typeID"))
{
	typeID = (String)request.getAttribute("typeID");
}

// 热门产品受理标志
String hotRecFlag = "";

if(null != request.getAttribute("hotRecFlag"))
{
	hotRecFlag = (String)request.getAttribute("hotRecFlag");
}

// 产品快速发布标识
String quickPubFlag = "";

if(null != request.getAttribute("quickPubFlag"))
{
	quickPubFlag = (String)request.getAttribute("quickPubFlag");
}

String searchType = (String) request.getParameter("searchType");
if (searchType == null)
{
    searchType = "";
}
//add end CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597

// add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
String initPwdLoginFlag = (String) request.getParameter("initPwdLoginFlag");
           
if(null == initPwdLoginFlag)
{
    initPwdLoginFlag = ""; 
}
// add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>NG2.3自助终端系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <%
  	
  	//modify begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597
  	
  	// 产品快速发布，未登录点击办理按钮，登录后继续办理
  	if("1".equals(quickPubFlag))
  	{
  		response.sendRedirect(basePath + "quickpublish/authPassword.action?curMenuId=" + currMenuID + "&typeID=" + typeID + "&hotRecFlag=" + hotRecFlag + "&searchType=" + searchType);
  	}
  	// add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
  	else if("1".equals(initPwdLoginFlag))
  	{
  		response.sendRedirect(basePath + "baseService/sendRandomPwd.action?initPwdLoginFlag=1");
  	}
  	// add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
  	else
  	{
  		if (url.indexOf("?") != -1)
	  	{
	  		url = url + "&curMenuId=" + currMenuID;
	  	}
	  	else
	  	{
	  		url = url + "?curMenuId=" + currMenuID;
	  	}

	  	response.sendRedirect(basePath + url);
  	}
  	//modify end CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597
  %>   
  </body>
</html>
