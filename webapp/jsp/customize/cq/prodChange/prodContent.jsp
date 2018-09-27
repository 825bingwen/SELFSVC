<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Vector dataVector = (Vector)request.getAttribute("prodList");

%>
<html>
	<head>
		<title>主体产品变更确认</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script>
var submitFlag = 0;
	
function doSub()
{	
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }prodChange/proChgCommit.action";
		document.actform.submit();
	}
}



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
		document.forms[0].action = "${sessionScope.basePath }prodChange/getProdTmpList.action";
		document.forms[0].submit();
	}
}
</script>
	</head>
	<BODY scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="servnumber" name="servnumber"
				value="<s:property value="servnumber"/>" />
			<input type="hidden" id="toProdId" name="toProdId"
				value='<s:property value="toProdId"/>'>
			<input type="hidden" id="toProdName" name="toProdName"
				value="<s:property value="toProdName"/>" />
			<input type="hidden" id="toProdBrand" name="toProdBrand"
				value="<s:property value="toProdBrand"/>" />
			<input type="hidden" id="templetID" name="templetID"
				value="<s:property value="templetID"/>" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<h1>
					<span></span><%=menuName %></h1>

				<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div
								style="height: 444px; padding: 0px 0px 5px 0px; width: 747px; overflow: hidden;">
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn"
									style="height: 411px; width: 747px; overflow: hidden;">
								<table width="100%" class="tb_blue">
									<tr>
										<th scope="col" colspan="4">
											产品转换确认
										</th>
									</tr>
									<tr>
										<td>
											客户姓名
										</td>
										<td>
											<s:property value="#session.CustomerSimpInfo.servNumber" />
										</td>
										<td>
											手机号码
										</td>
										<td>
											<s:property value="#session.CustomerSimpInfo.customerName" />
										</td>
									</tr>
									<tr>
										<td>
											品牌名称
										</td>
										<td>
											<s:property value="toProdBrand" />
										</td>
										<td>
											新主体产品
										</td>
										<td>
											<s:property value="toProdName" />
										</td>
									</tr>
								</table>
								<br />
								<table width="100%" class="tb_blue">
									<tr>
										<th scope="col" colspan="2">
											产品明细
										</th>
									</tr>
									<tr>
										<td>
											名称
										</td>
										<td>
											资费描述
										</td>
									</tr>
									<s:iterator value="#request.templetItemList" id="data"
										status="st">

										<tr>
											<td>
												<input type="hidden" name="entityId"
													value="<s:property value="#data.entityID" />">
												<s:property value="#data.entityName" />
											</td>
											<td>
												<s:property value="#data.notes" />
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="2">
											<input type="button" class="bt2_liebiao"
												style="color: #FFFFFF;" value="确认转换"
												onmousedown="this.className='bt2on'"
												onmouseup="this.className='bt2';doSub();" />
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width: 75px; height: 350px;">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom"
									style="width: 66px; height: 36px; position: absolute; cursor: move; left: 765px; top: 52px; line-height: 36px">
									0%
								</div>
							</div>
							<input type="button" class="btnDown"
								onclick="myScroll.moveDown(30)" />
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
