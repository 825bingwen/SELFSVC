<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.customize.cq.selfsvc.common.CommonUtilCQ"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>梦网业务选择</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script>
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
		goback("<s:property value='#request.curMenuId' />");
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
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
		        <!--滚动条-->
				<div class="boxCQ fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
								<p style="height:20px;"></p>
		                        <p align="left" style="height:60px;padding-left:20px;font-size:12pt;"><%=menuName %></p>
		                        <p class="ptop180 tc" id="inn" style="height:411px; width:720px; overflow:hidden;" >
		                        <table>
		                        	<tr><td>
		                        	<p style="height:600px;width:670px;line-height:24pt;padding-left:40px;font-size:12pt;"><%=CommonUtilCQ.getMenuBusiDetail(curMenuId) %></p>
		                        	</td></tr>
		                        </table>
							</div>
						</div>
						<div class="box70W fr">
							<a href="${sessionScope.basePath }login/goHomePage.action" class="bt10 fr mr43" style="width:70px;height:55px;display:inline" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';">返回</a>
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:290px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:112px; line-height:36px" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
		          
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
