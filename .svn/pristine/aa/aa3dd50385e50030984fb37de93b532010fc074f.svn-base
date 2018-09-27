<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.common.base.CEntityString"%>
<%@page import="com.gmcc.boss.selfsvc.util.CurrencyUtil"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Vector dataVector = (Vector) request.getAttribute("spservice");
	request.setAttribute("spservice", dataVector);
	CEntityString sTitle = (CEntityString) dataVector.get(0);
	String[] titles = sTitle.EntityString.split(",");
	CRSet data = (CRSet) dataVector.get(1);
	String curmenuid = (String) request
			.getAttribute(Constants.CUR_MENUID);
%>
<html>
	<head>
		<title>梦网业务选择</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />			
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
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
		
		openRecWaitLoading_NX('recWaitLoading');
		
		document.actform.action="${sessionScope.basePath}baseService/cancelSP.action?operType=Cancel";
		document.actform.submit();
	}
}

//确认按钮			
function mainProductChangeSubmit(spBizName,dealType,domain,spId,spBizCode,bizType)
	{		
		document.getElementById("spBizName").value = spBizName;
		document.getElementById("dealType").value = dealType;
		document.getElementById("domain").value = domain;
		document.getElementById("spId").value = spId;
		document.getElementById("spBizCode").value = spBizCode;
		document.getElementById("bizType").value = bizType;
		
		document.getElementById('winText').innerHTML = '<br/>您选择退订：<span class="yellow">' + spBizName + '</span>业务 <br/>确认退订请点击"确认"提交。';
		openWindow('openWin1');
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
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}
</script>
	</head>
	<BODY scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="spBizName" name="spBizName" value="" />
			<input type="hidden" id="dealType" name="dealType" value="" />
			<input type="hidden" id="domain" name="domain" value="" />
			<input type="hidden" id="spId" name="spId" value="" />
			<input type="hidden" id="spBizCode" name="spBizCode" value="" />
			<input type="hidden" id="bizType" name="bizType" value="" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!--弹出窗 BEGIN-->
				<div class="openwin_tip" id="openWin1"
					style="width: 708px; height: 392px;">
					<div class="bg"></div>
					<div class=" blank60"></div>
					<p class="fs28 lh2"
						style="padding-left: 50px; padding-right: 50px;" id="winText"
						name="winText">

					</p>
					<div class="tc">
						<div class=" clear "></div>
						<div class=" blank30 "></div>
						<a title="确认" href="javascript:void(0);" class=" bt4"
							onmousedown="this.className='bt4on';doSub();"
							onmouseup="this.className='bt4';wiWindow.close();">确认</a>
						<a title="" href="javascript:void(0);" class=" bt4 ml20"
							onmousedown="this.className='bt4on ml20'"
							onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
					</div>
				</div>
				<!-- 弹出窗 END -->

				<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName%></p>
								<p class="ptop180 tc p747w411h" id="inn" >

									<%--modify begin cKF48754 2011/11/16 R003C11L11n01 OR_HUB_201111_82 --%>
								<table width="100%" class="tb_blue">
									<tr>
										<%
											if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province)) {
										%>
										<th scope="col">
											提供商
										</th>
										<th scope="col">
											业务名称
										</th>
										<%
											} else {
										%>
										<th scope="col">
											业务名称
										</th>
										<th scope="col">
											提供商
										</th>
										<th scope="col">
											计费方式
										</th>
										<%
											}
										%>
										<th scope="col">
											费用(元)
										</th>
										<th scope="col">
											开通时间
										</th>
										<th scope="col">
											操作
										</th>
									</tr>
									<%
										if (dataVector != null && data.GetRowCount() != 0) {
											for (int i = 0; i < data.GetRowCount(); i++) {
									%>
									<tr>

										<%
											if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province)) {
										%>
										<td><%=data.GetValue(i, 2)%></td>
										<td><%=data.GetValue(i, 4)%></td>
										<%
											} else {
										%>
										<td><%=data.GetValue(i, 4)%></td>
										<td><%=data.GetValue(i, 2)%></td>
										<td><%=data.GetValue(i, 6)%></td>
										<%
											}
										%>
										<td><%=CurrencyUtil.divide(data.GetValue(i, 7), "1000")%></td>
										<td><%=data.GetValue(i, 9)%></td>
										<td>
											<input type="button" class="bt2_liebiao white"
												value="退订"
												onmousedown="this.className='bt2on white'"
												onmouseup="this.className='bt2 white';mainProductChangeSubmit('<%=data.GetValue(i, 4)%>','<%=data.GetValue(i, 0)%>','<%=data.GetValue(i, 8)%>','<%=data.GetValue(i, 1)%>','<%=data.GetValue(i, 3)%>','<%=data.GetValue(i, 5)%>');" />
									</tr>
									<%
										}
										}
									%>
								</table>
								<%--modify end cKF48754 2011/11/16 R003C11L11n01 OR_HUB_201111_82 --%>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage div75w350h">
								<div class="blank10px"></div>
								
								<%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
                                	<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
								<%
								}
								else
								{
								%>
                                	<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
								<%
								}
								%>
								
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
				<script type="text/javascript">
				openWindow = function(id){
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
				}
				</script>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
