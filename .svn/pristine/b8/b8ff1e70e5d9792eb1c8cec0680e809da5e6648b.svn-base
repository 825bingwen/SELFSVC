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
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix" style="padding-top:60px;">
						<!--    <dt class="fl" id="face" ></dt>-->   
						<dd class="tc" style="">
							<span class="transcact_ok">
							    <s:if test="successMessage == null">
								    业务办理成功!
								</s:if>
								<s:else>
								    <s:property value="successMessage"/>
								</s:else>
							</span>
							<p class="btn_box clearfix"> 
								<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';topGo('serv', 'service/serviceFunc.action'); return false;">更多业务</a> 
							</p>
						</dd>
					</dl>
					
					<!-- 添加推荐业务开始 -->
					  <div class="line"></div>
					  <div class="recommend_box">
					    <div class="recommend_title clearfix"><span>相关业务推荐：</span></div>
					    <ul class="recommend_list clearfix">
						    <%
						    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
						    {
						    	MenuInfoPO menu = null;
						    	int j = 0; int k = 0;
						    	for (int i = 0; i < titleTotalMenus.size(); i++)
						    	{
						    		menu = (MenuInfoPO) titleTotalMenus.get(i);
						    		
						    		 if ("rec".equalsIgnoreCase(menu.getParentid()) 
						                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
						                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))&& k<4) 
						            {
						            	k++;
										%>
											<li>
												<a class="relative" style="width:110px; height:120px;margin-right:40px;margin-left:40px;" id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><img src='${sessionScope.basePath}<%=menu.getImgpath2() %>' /></a>
												<p><a href="javascript:void(0);" style="font-size:20px;width:120px; height:20px;margin-right:40px;margin-left:40px;" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><%=menu.getMenuname() %></a></p>
											</li>
										<%
						            }
						    	}
						    }
				      		%>
					    </ul>
					  </div>
					<!-- 添加推荐业务结束 -->
					
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
