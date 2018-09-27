<%@page contentType="text/html; charset=GBK"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	String servnumber = (String) request.getAttribute("servnumber");

	// 当前菜单ID
	String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	
	// 处理现金缴费跳转
	if("feeCharge".equals(currMenuID))
	{
		response.sendRedirect(basePath + "charge/qryfeeChargeAccount.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID);
	}
	else if("broadBandPay".equals(currMenuID))
	{
		response.sendRedirect(basePath + "broadBandPay/qryWBankList.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID);
	}
	else if("recReissueCard".equals(currMenuID))
	{
		response.sendRedirect(basePath + "reissueCard/readCert.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID + "&recommendActivityFlag=1");
	}
	else if("recPrepareCard".equals(currMenuID))
	{
		response.sendRedirect(basePath + "prepareCard/validPassword.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID + "&recommendActivityFlag=1");
	}
	else
	{
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
	
%>