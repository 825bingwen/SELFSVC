<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main" >
				<%@ include file="/customer.jsp"%>
				<!-- modify begin m00227318 -->
				<div class="tc mb120" >
				<!-- modify end m00227318 -->
					<ul class="favorable_item clearfix" >
						<p class="tit_info" align="left">
							<span class="bg" ></span><%=menuName%></p>

						<%
						    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
						    {
						        for (int i = 0; i < titleTotalMenus.size(); i++)
						        {
						            MenuInfoPO menu = (MenuInfoPO)titleTotalMenus.get(i);
						            if ("scoreSpecial".equalsIgnoreCase(menu.getParentid())
						                    && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion()) || terminalInfo.getRegion()
						                            .equalsIgnoreCase(menu.getRegion())))
						            {
						%>
						                <!-- modify begin m00227318 -->
										<li class="mr20 ml10 mt20 tc hrefmenu">
										<!-- modify end m00227318 -->
											<a href="#" onclick="topGo('<%=menu.getMenuid()%>', '<%=menu.getGuiobj()%>');" onmousedown="this.className='hover'" onmouseup="this.className='';">
												<%=menu.getMenuname() + ""%>
											</a>
										</li>
						<%
						    		}
						        }
						    }
						%>
					</ul>			
				</div>			
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>	
</html>
