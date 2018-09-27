<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
//用户信息session
NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
String servnumber = null;

if (null != customer )
{
	servnumber = customer.getServNumber();
}

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
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
							    <s:if test="successMessage == null">
								    业务办理成功!
								</s:if>
								<s:else>
								    <s:property value="successMessage"/>
								</s:else>
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
