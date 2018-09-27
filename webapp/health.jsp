<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@page import="java.util.List"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
</head>


<%
	List<DictItemPO> dictItemList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("OperationType");
	
	if (!dictItemList.isEmpty())
	{
	    out.println("success");
	}
	else
	{
	    out.println("error");
	}
%>