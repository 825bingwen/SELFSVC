<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
NserCustomerSimp custInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

// 当前菜单id
String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);

// 菜单支持营销推荐活动标志
String needAuthFlag = "";

// 营销推荐列表
List<DictItemPO> actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENUNEW);

// 支持营销推荐活动的菜单
if (actMenuList != null)
{
    for (int i = 0; i < actMenuList.size(); i++)
    {
        DictItemPO dictItemPO = actMenuList.get(i);
        if (currMenuID.equals(dictItemPO.getDictid()))
        {
            needAuthFlag = dictItemPO.getDescription();
            break;
        }
    }
}
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
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
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
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
		goback("<s:property value='curMenuId' />");
		return;
	}
}
</script>
</head>
<body scroll="no">
	<form id="actform" name="actform" method="post" >
		
		<%-- 新营销推荐活动标识 --%>
		<s:hidden id="recommendActivityFlag" name="recommendActivityFlag"></s:hidden>
		
		<%-- 手机号码 --%>
		<s:hidden id="servnumber" name="servnumber"></s:hidden>
		
		<%-- 活动组ID --%>
		<s:hidden id="groupId" name="groupId"></s:hidden>
		
		<%-- 活动组名称 --%>
		<s:hidden id="groupName" name="groupName"></s:hidden>
		
		<%-- 活动编码 --%>
		<s:hidden id="activityId" name="activityId"></s:hidden>
		
		<%-- 活动名称 --%>
		<s:hidden id="activityName" name="activityName"></s:hidden>
		
		<%-- 档次编码 --%>
		<s:hidden id="dangciId" name="dangciId"></s:hidden>
		
		<%-- 档次名称 --%>
		<s:hidden id="dangciName" name="dangciName"></s:hidden>
		
		<%-- 受理金额 --%>
		<s:hidden id="prepayFee" name="prepayFee"></s:hidden>
		
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<a href="#" class="bt10 fr mr92" style="margin-left: 10px;" onmousedown="this.className='bt10on fr mr92'"
				onmouseup="this.className='bt10 fr mr92';continueHandle(); return false;" style="display:inline">暂不办理</a>
			<%--滚动条--%>
			<div class="box842W fl ml45 relative" style="margin-left: 90px; display: inline;">
				<div class="bg"></div>
				<div class="top"></div>
				<div class="con relative">
					<div class="box747W fl">
						<div class="div747w444h">
							<!-- 列表内容 -->
							<p class="ptop180 tc p747w411h" id="inn">
							<table width="100%" class="tb_blue" id="actTable">
								<tr>
									<th class="list_title" align="center" colspan="20">
										营销推荐活动
									</th>
								</tr>
								<tr>
									<th scope="col">
										活动名称
									</th>
									<th scope="col">
										活动介绍
									</th>
									<th style="width: 10%" scope="col">
										是否参与
									</th>
								</tr>
								<s:iterator value="activityAllList" id="list" status="st">
									<tr>
										<td>
											<s:property value="#list.groupName" />
										</td>
										<td>
											<s:property value="#list.dangciName" />
										</td>
										<td>
											<input type="button" class="bt2_liebiao" style="color: #FFFFFF;" value="参与"
											onmouseup="queryDangCiDesc('<s:property value="#list.groupId" />','<s:property value="#list.groupName" />','<s:property value="#list.dangciId" />','<s:property value="#list.dangciName" />', '<s:property value="#list.activityId" />','<s:property value="#list.activityName" />','<s:property value="#list.prepayFee" />')" />
										</td>
									</tr>
								</s:iterator>
							</table>
							<br />
						</div>
					</div>
					<div class="box70W fr">
						<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
						<div class="div75w350h">
							<div class="blank10px"></div>
							<div class="box66W tc f16 div66w36h" id="gunDom" style="left: 766px; top: 39px;">0%</div>
						</div>
						<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
					</div>
					<div class="clear"></div>
				</div>
				<div class="btm"></div>
			</div>
			<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
			<%-- 滚动条结束 --%>
			<%-- 等待提示框 --%>
			<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
				<div class="bg"></div>
				<p class="mt40">
					<img src="${sessionScope.basePath }images/loading.gif" />
				</p>
				<p class="tips_txt">
					正在验证是否满足参与条件，请稍候...
				</p>
				<div class="line"></div>
				<div class="popup_banner"></div>
			</div>
			<div class="popup_confirm" id="openWin_tipsMsg">
				<div class="bg"></div>
				<div class="tips_title">
					提示：
				</div>
				<div class="tips_body">
					<p id="winText_tipsMsg">
						<i></i>
					</p>
					<div class="blank10"></div>
					<p class="mt30"></p>
				</div>
				<div class="btn_box tc mt20">
					<span class=" mr10 inline_block "><a title="继续" href="#"
						class="btn_bg_146" onmousedown="this.className='key_down'"
						onmouseup="this.className='btn_bg_146';windowClose();submitFlag = 0;continueHandle();">继续</a>
					</span>
					<span class=" inline_block "><a title="" class="btn_bg_146"
						href="#" onmousedown="this.className='key_down'"
						onmouseup="this.className='btn_bg_146';windowClose();">关闭</a>
					</span>
				</div>
			</div>
		</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">

// 暂不办理，继续原有业务
function continueHandle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		document.actform.target="_self";
		document.actform.action = "${sessionScope.basePath}recommendActivity/continueHandle.action";
		document.actform.submit();
	}
}

// 查询活动档次
function queryDangCiDesc(groupId,groupName,dangciId,dangciName,activityId,activityName,prepayFee)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 活动组编码
		setValue("groupId", groupId);
		
		// 活动级名称
		setValue("groupName",groupName);
		
		// 活动编码
		setValue("activityId",activityId);
		
		// 活动名称
		setValue("activityName",activityName);
		
		// 档次编码
		setValue("dangciId",dangciId);
		
		// 档次名称
		setValue("dangciName",dangciName);
		
		// 受理金额
		setValue("prepayFee",prepayFee);

		<%
			// 有用户信息并且不需要验证身份的进行活动预受理并查询活动档次
			if(null != custInfor && "NONEEDAUTH".equals(needAuthFlag))
			{
		%>
				wiWindow = new OpenWindow("pls_wait", 804, 515);
				
				setTimeout("toSub()",500);
		<%
			}
			else
			{
		%>
				openRecWaitLoading('recWaitLoading');
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}/recommendActivity/chkServPwdPage.action";
				document.actform.submit();
		<%
			}
		%>
	}
}

function toSub()
{
	// 活动预受理
	var ret = preAccept();
	if (ret != 0)
	{
		submitFlag = 0;
		wiWindow.close();
		var reasonTip = ret.split('~~')[1];
		if(reasonTip.length > 66)
		{
			document.getElementById("winText_tipsMsg").title = reasonTip;
			reasonTip = reasonTip.substring(0,66)+"...";
		}
		else
		{
			document.getElementById("winText_tipsMsg").title = "";
		}
		var tipText = "<i>"+reasonTip+"</i><br\><br\>"+"您因为以上原因无法办理该活动,点击'继续'按钮可以继续办理原业务！";
		openWindow("openWin_tipsMsg",tipText);
		return;
	}
	else
	{
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }activitiesRec/queryDangCiDesc.action";
		document.actform.submit();
	}
}

// 活动预受理
function preAccept()
{
	// 成功:0 失败:1~~失败原因
	var ret = 1;
	
	// URL
	var url = "${sessionScope.basePath}activitiesRec/preAccept.action";
	
	// 参数
	var params = "activityId=" + encodeURI(encodeURI(document.getElementById('activityId').value));
	    params = params + "&dangciId=" + encodeURI(encodeURI(document.getElementById("dangciId").value)) ;
	    params = params + "&curMenuId=" + document.getElementById("curMenuId").value ;
	    params = params + "&servnumber=" + document.getElementById("servnumber").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

function openWindow(id,tipMsg)
{
	document.getElementById("winText_tipsMsg").innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子	
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	wiWindow.close();
}
</script>
</html>
