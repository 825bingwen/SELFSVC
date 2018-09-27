<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>初始密码登录成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function menuGo(url) 
{
	window.location.href = "${sessionScope.basePath}" + url +"?servnumber=" + "<s:property value='servnumber'/>";
	return;
}

function goback(curmenu) 
{
	window.history.back();
}

document.onkeyup = pwdKeyboardUp;
      
function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);

	// 退出
	if (key == 82)
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		return;			
	}
	// 密码修改(按1键)
	if (key == 49)
	{
		window.location.href = "${sessionScope.basePath}login/sendRandomCode.action?servnumber=" + "<s:property value='servnumber'/>";
		return;
	}
	// 密码重置(按2键)
	else if(key == 50)
	{
		window.location.href = "${sessionScope.basePath}login/goIdCardPage.action";
		return;
	}
}
</script>
</head>

<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">			
			<%@ include file="/customer.jsp"%>
			  <div class="service_brand w966 m0auto">
			    <div class="service_index_list">
			    <p class="hot_service"></p>
			    	<div class="p40">
						<div class="blank10"></div>
						<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">您输入的服务密码是初始密码，请先进行密码修改或者密码重置。</span></p>
						<div class="blank25"></div>
						<div class="blank25"></div>
      						<div class="btn_box tc">
     						<%
							if ("1".equals(keyFlag))
							{
							%>
								 <span class=" mr10 inline_block ">
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';menuGo('login/sendRandomCode.action'); return false;">
										密码修改(按1键)
									</a>
								</span>
								<span class=" mr10 inline_block ">
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';menuGo('login/goIdCardPage.action'); return false;">
										密码重置(按2键)
									</a>
								</span>
							<%
							}
							else
							{
							%>
								 <span class=" mr10 inline_block ">
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';menuGo('login/sendRandomCode.action'); return false;">
										密码修改
									</a>
								</span>
								<span class=" mr10 inline_block ">
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';menuGo('login/goIdCardPage.action'); return false;">
										密码重置
									</a>
								</span>
							<%
							}
							%>
	    				</div>
      				</div>
			    </div>
			  </div>
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
