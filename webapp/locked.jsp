<%--
 @User: ��Ⱥ/g00140516
 @De: 2013/02/19
 @comment: �ն˻�����������
 @remark create g00140516 2013/02/19 R003C13L01n01 OR_NX_201302_600
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if (errorMessage == null)
{
	errorMessage = request.getParameter("errormessage");
}

// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
errorMessage = CommonUtil.errorMessage(errorMessage);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������


 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	//����
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//��仰��ؼ�
	}
}

function KeyIsNumber(KeyCode) 
{
	//ֻ��������0-9
	if (KeyCode >= 48 && KeyCode <= 57)
	{
		return true;
	}
	
	return false;
}

function getTermStatus()
{
	//ÿ���Ӳ�ѯһ���ն˻�����״̬
	window.setTimeout(getTermStratus, 60 * 1000);
}

function getTermStratus()
{
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm";
	document.forms[0].submit();
}

</script>
</head>
	<body scroll="no" onload="getTermStatus();">
		<form name="actform" method="post">
			<div class="header">
			    <%
		    	String province = (String) PublicCache.getInstance().getCachedData("ProvinceID");
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
			    <div class="tit"></div>
			    <div class="time">		   	 	
					<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" alt="��ǰʱ��">					
			    	<span id="_ShowTime"></span>
			    	<script type="text/javascript">startTime();</script>
			    </div>			    
			</div>
			
			<div class="index_main">
				<div class="box horizontalCenter">
					<dl class="clearfix pt240">
						<dd class="tc">					
							<span class='lockedMsg'>
							<%=errorMessage %>
							</span>	
						</dd>
					</dl>
				</div>			
			</div>			
		</form>
	</body>
</html>