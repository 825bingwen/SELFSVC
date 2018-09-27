<%@page contentType="text/html; charset=GBK"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String feeChargeFlag = (String) request.getAttribute("feeChargeFlag");
	String servnumber = (String) request.getAttribute("servnumber");
	String userSeq = (String)request.getAttribute("userSeq");
	String commitProductFlag = (String)request.getAttribute("commitProductFlag");
	String commendOID = (String)request.getAttribute("commendOID");
	// ��ǰ�˵�ID
	String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	
	// �ɷ�ҳ���¼�����Ӫ���
	if("1".equals(commitProductFlag))
	{
		// ���feedBackDefFlag������ֵΪ1ʱ����ʾ�ǰ������
		if ("1".equals(request.getParameter("feedBackDefFlag")))
		{
			response.sendRedirect(basePath + "recommendProduct/doFeedBackDef.action?servnumber=" 
				+ servnumber + "&curMenuId=" + currMenuID + "&feeChargeFlag=" + feeChargeFlag 
				+ "&commendOID=" + commendOID + "&actId=" + request.getParameter("actId") 
				+ "&moContent=" + request.getParameter("moContent")
				+ "&recmdType=" + request.getParameter("recmdType"));
		}
		else
		{
			response.sendRedirect(basePath + "recommendProduct/recommendProduct.action?servnumber=" 
				+ servnumber + "&curMenuId=" + currMenuID + "&userSeq=" + userSeq + "&feeChargeFlag=" 
				+ feeChargeFlag + "&commendOID=" + commendOID + "&actId=" + request.getParameter("actId")
				+ "&recmdType=" + request.getParameter("recmdType"));
		}
		
		return;
	}

	// �����ֽ�ɷ���ת
	if(feeChargeFlag.equals("1"))
	{
		response.sendRedirect(basePath + "charge/qryfeeChargeAccount.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID);
	}
	// add begin hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
	else if("broadBandPay".equals(currMenuID))
	{
		response.sendRedirect(basePath + "broadBandPay/qryWBankList.action?servnumber=" + servnumber + "&curMenuId=" + currMenuID);
	}
	// add end hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
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
		// �ն���Ϣ
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