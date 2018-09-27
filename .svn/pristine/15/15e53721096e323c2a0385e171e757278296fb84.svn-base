<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
errorMessage = CommonUtil.errorMessage(errorMessage);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求

String exception_message = (String) request.getAttribute("exception.message");
// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
exception_message = CommonUtil.errorMessage(exception_message);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
String backStep = (String) request.getAttribute("backStep");

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

//用户信息session
NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

String servnumber = null;

if (null != customer )
{
	servnumber = customer.getServNumber();
}

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
//防止页面重复提交
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	//更正
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//这句话最关键
	}
}

function KeyIsNumber(KeyCode) 
{
	//只允许输入0-9
	if (KeyCode >= 48 && KeyCode <= 57)
	{
		return true;
	}
	
	return false;
}

function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	<%	
	} 
	%>
}


</script>
</head>
	<body scroll="no" onload="feedbackByStatus()">
		<form name="actform" method="post">
			<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok">

						    <%
						    if (errorMessage == null || "".equals(errorMessage))
						    {
						    	if (exception_message != null && !"".equals(exception_message))
						    	{
						    		out.print(exception_message);
						    	}
						    	else
						    	{
						    		out.print("操作失败，请稍后再试。");
						    	}
						    }
						    else
						    {
						    	out.print(errorMessage);
						    }  
						    %>
							   
							</span>
						</dd>
					</dl>
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
<script type="text/javascript">
function goback(menuid)
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
		
		// modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		<%
		if (null != backStep && !"".equals(backStep.trim()))
		{
		%>
			history.go(parseInt("<%=backStep %>"));
		<%	
		}
		else
		{
		%>
			document.getElementById("curMenuId").value = menuid;
				
			//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597
			
			// 产品快速发布标志  
			var quickPubFlag = "<s:property value='quickPubFlag' />";
			
			// 产品开通（通过产品开通菜单进入，或者从热门业务进入）
			if('1' == quickPubFlag)
			{
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
				document.actform.submit();
			}
			else
			{						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
			//modify end CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
		<%
		}
		%>
		// modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
	}
}

//add begin sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_自助终端营销功能优化
//山东，向智能营销平台反馈办理结果	
function feedbackByStatus()
{
	<%
	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(pageProvince))
	{
	%>	
		//菜单id
		var offerMenu = "<s:property value='curMenuId' />";
		
		//手机号码
		var servnumber = <%=servnumber%>;
		
		//参数status=1，表示业务订购成功
		var postStr ="offerMenu="+offerMenu+"&servnumber="+servnumber+"&status=0";  
		 
		var receptionUrl = "${sessionScope.basePath}ISSS/feedbackByStatus.action";
		
		//向智能营销平台反馈办理结果
		var loader11 = new net.ContentLoader(receptionUrl, netload = function () 
		{
		}, null, "POST", postStr, null);
	
	<%
	}
	%>
}
//add end sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_自助终端营销功能优化
</script>


</html>
