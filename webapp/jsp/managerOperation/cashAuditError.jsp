<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
String message = (String) request.getAttribute("errorMessage");
String errorMessage = CommonUtil.errorMessage(message);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>现金稽核错误页面</title>
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

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) 
{
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback();
   		return ;
	}
}

function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target = "_self";
		<%
		if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData("ProvinceID"))){
		%>
			document.actform.action = "${sessionScope.basePath }managerOperation/doCashAudit.action?from=cashAuditError";			
		<%
		}
		else
		{
		%>
			document.actform.action = "${sessionScope.basePath }managerOperation/selectOperationType.action";	
		<%
		}
		%>	  
		
		document.actform.submit();
	}
}
	
function doLoad()
{	
	// 顶部菜单不可用
	closeStatus = 1;
}
</script>

</head>
<body scroll="no" onload="doLoad();">
	<form name="actform" method="post">			
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
					<div class="service_brand w966 m0auto">
						<div class="service_index_list">
						<p class="hot_service"></p>
								<div class="p40">
					      			<div class="blank10"></div>
									<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">自助终端提醒您：</span></p>
									<div class="blank25"></div>
									<div class="blank25"></div>
									<div class="btn_box tc">
									<p class="fs22w  pl20"><span class="yellow fs30">操作失败！</span></p>
          							<div class=" blank60"></div>
          							<p class="fs22w  pl20">
          							<span class="yellow fs30">

									<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>	
									<%=errorMessage %>      							
									<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>
									
									</span></p>
								</div>
							</div>
				  	 	</div>
			 		</div>
			</div>		    
		<!-- 宁夏屏蔽“首页、“退出”按钮操作 -->
   		<%
   		
   		// 现金稽核开关
		String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
   		
		if (Constants.PROOPERORGID_NX.equals(proID) || "1".equals(cashAuditSwitch)){
		%>
			<div class="footer" id="footer">
			<a id="backBtn" href="javascript:void(0);" class="pre1" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';" onclick="goback('<%=curMenuId %>');return false;"></a>
			</div>
		<%
		}else{
		%>
			<%@ include file="/backinc.jsp"%>	
		<%
		}
		%>	    		
	</form>
</body>
</html>
