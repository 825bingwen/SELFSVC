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
function searchFunc(searchType){
	document.getElementById("searchType").value=searchType;
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
	document.forms[0].submit();
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
						            if ("rec".equalsIgnoreCase(menu.getParentid())
						                    && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion()) || terminalInfo.getRegion()
						                            .equalsIgnoreCase(menu.getRegion())))
						            {
						%>
						                <!-- modify begin m00227318 -->
										<li style="margin-left:15px;_margin-left:10px;" class="mr20 mt20 tc hrefmenu">
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
				
				<!-- add 业务菜单显示方式 begin -->
				<%
				// 业务办理显示按品牌查询、首字母搜索(开关 0:关 1:开)
				String recSwitch = PublicCache.getInstance().getCachedData("SH_RECSEARCH_SWITCH") == null ? "0" : (String)PublicCache.getInstance().getCachedData("SH_RECSEARCH_SWITCH");
				if ("1".equals(recSwitch))
				{
				%>	
					<div class="service_quick_search bg952 mt_12 m0auto clearfix" id="showbutton" style = "margin-top:-50px">
						<input type="hidden" id="searchType" name="searchType"/>
						<p style="margin-left:163px;_margin-left:82px" class="brand pbleft01">
							<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('2'); return false;">
								<span class="cfff200">按品牌查看</span>
								<span>选择您的品牌，快速查<br />找您适用的业务</span>
							</a>
						</p>
						
						<p class="brand pbleft02">
							<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('3'); return false;">
								<span class="cfff200">首字母搜索</span>
								<span>输入业务首字的拼音首<br />字母，快速查找业务</span>
							</a>
						</p>
					</div>
				<% 
				}
				%>
				<!-- add 业务菜单显示方式 end -->
				
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>	
</html>
