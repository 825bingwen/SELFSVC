<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
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
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		
	}			
}

document.onkeyup = pwdKeyboardUp;
		
function pwdKeyboardUp(e)
{
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
     //确认
	if (key == 13 || key == 89 || key == 221)
	{
		doSub();	
		return ;
	}
	
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		if (key == 48)
		{
			topGo('qryService', 'serviceinfo/serviceInfoFunc.action'); 
		}

	<%
	}
	%>
}

function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
						
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }baseService/sendRandomPwd.action";
		document.actform.submit();
	}
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">	
		<input type="hidden" id="initPwdLoginFlag" name="initPwdLoginFlag" value="<s:property value='initPwdLoginFlag'/>"/>
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
			<div class="b966">
				<div class="blank15" style=""></div>
				<div class="p40">
					<p class=" tit_info1">
						<%=additionalInfo == null ? "&nbsp;" : additionalInfo %>
					</p>
					<div class="tc" style="position: absolute; top: 480px; left: 400px;">
	<%--
	modify by sWX219697 2015-1-29 16:55:32 OR_HUB_201408_620_自助终端界面改版优化
	--%>
	<%
	if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(pageProvince))
	{
	%>
		<input type="button" class=" bt4" style="font-size:20px;font-weight:bold;" value="确定" onmousedown="this.className='bt4on';doSub();" onmouseup="this.className='bt4';"/>	  
	<%
	}
	else
	{
	%>
		<input type="button" class=" bt4" style="font-size:20px;font-weight:bold;" value="确定(按确认键)" onmousedown="this.className='bt4on';doSub();" onmouseup="this.className='bt4';"/>	  
	<%
	}
	%>	                </div>
				</div>
			</div>
		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
