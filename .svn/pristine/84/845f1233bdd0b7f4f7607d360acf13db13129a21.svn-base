<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
String message = (String) request.getAttribute("errormessage");
String errorMessage = CommonUtil.errorMessage(message);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
%>
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<div class="pl30">
			<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
			
			 <div class="b712">
			 	<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40 pr0">
    						<div class="blank10"></div>     
      					<div class="blank20"></div>
      					<div class="blank40"></div>
    						<div class="casherror">
    						
    							<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>	
								<%=errorMessage %>      							
								<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>
    							
      					</div>
    					</div>
			 </div>
		</div>
	</div>
	
	<%@ include file="/backinc.jsp"%>

	<script type="text/javascript">
	// 键盘松开
	document.onkeyup = pwdKeyboardUp;
	
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//返回
		if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
			return;
		}			
	}
	</script>
</html>
