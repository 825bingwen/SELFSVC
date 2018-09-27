<%--
 @User: 吕旭光/l00190940
 @De: 2011/12/12
 @comment: 亲情号码显示页面
 @remark: create l00190940 2011/12/12 OR_HUB_201112_16
--%>
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

//修改或设置亲情号码
function setFamilyNumber()
{
    var sn = document.getElementById("sn").value;
    
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}familyNumber/setFamilyNumberPage.action?sn=" + sn;
	document.actform.submit();
}

function passParam(sn)
{
    document.getElementById("sn").value = sn;
}

//取消亲情号码
function cancelFamilyNumber()
{
    var sn = document.getElementById("sn").value;
    var sregion = document.getElementById("sregion").value;
    
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}familyNumber/setFamilyNumber.action?sn=" + sn + "&sregion=" + sregion;
	document.actform.submit();
}

//修改还是设置
function changeStatus(status, position)
{
    if (status == "0")
    {
        document.getElementById("noticeMsg").innerHTML="尊敬的客户，您确定设置亲情号码" + position + "吗？";
        document.getElementById("noticeConfirm").innerHTML="确认设置请点击“确认”提交";
    }
    else if (status == "1")
    {
        document.getElementById("noticeMsg").innerHTML="尊敬的客户，您确定修改亲情号码" + position + "吗？";
        document.getElementById("noticeConfirm").innerHTML="确认修改请点击“确认”提交";
    }
}

//取消的位置
function cancelPosition(position)
{
    document.getElementById("cancelMsg").innerHTML="尊敬的客户，您确定取消亲情号码" + position + "吗？";
}

function openWindow(id)
{
    wiWindow = new OpenWindow(id,708,392);//打开弹出窗口
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>

			<input id="sn" value="" type="hidden"/>
			<input id="sregion" value="" type="hidden"/>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
					<div class="blank30"></div>
					<div class="relative p40">
						<p class="tit_info">
						    <span class="bg"></span>
							尊敬的客户，您的亲情号码如下：
						</p>
						<div class="blank15"></div>
						<div class="p20">
							<table width="95%" class="tb_blue" align="center">
								<tr class="tr_color">
									<th class='list_title'>
										编号
									</th>
									<th class='list_title'>
										亲情号
									</th>
									<th class='list_title'>
										办理
									</th>
								</tr>


								<tr>
									<td>
										亲情号码1
									</td>
									<td>
									    <s:if test="#request.friendnum1 != null && #request.friendnum1 != \"\"">
										    <s:property value="#request.friendnum1" />
										</s:if>
										<s:else>
										    暂未设置
										</s:else>
									</td>
									<s:if
										test="#request.friendnum1 != null && #request.friendnum1 != \"\"">
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('1');this.className='bt2';openWindow('open_change_confirm');changeStatus('1', '1');">修改</a><a
												class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('1');this.className='bt2';openWindow('open_cancel_confirm');cancelPosition('1');">取消</a>
										</td>
									</s:if>
									<s:else>
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('1');this.className='bt2';openWindow('open_change_confirm');changeStatus('0', '1');">设置</a>
										</td>
									</s:else>
								</tr>

								<tr>
									<td>
										亲情号码2
									</td>
									<td>
										<s:if test="#request.friendnum2 != null && #request.friendnum2 != \"\"">
										    <s:property value="#request.friendnum2" />
										</s:if>
										<s:else>
										    暂未设置
										</s:else>
									</td>
									<s:if
										test="#request.friendnum2 != null && #request.friendnum2 != \"\"">
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('2');this.className='bt2';openWindow('open_change_confirm');changeStatus('1', '2');">修改</a>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('2');this.className='bt2';openWindow('open_cancel_confirm');cancelPosition('2');">取消</a>
										</td>
									</s:if>
									<s:else>
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('2');this.className='bt2';openWindow('open_change_confirm');changeStatus('0', '2');">设置</a>
										</td>
									</s:else>
								</tr>

								<tr>
									<td>
										亲情号码3
									</td>
									<td>
										<s:if test="#request.friendnum3 != null && #request.friendnum3 != \"\"">
										    <s:property value="#request.friendnum3" />
										</s:if>
										<s:else>
										    暂未设置
										</s:else>
									</td>
									<s:if
										test="#request.friendnum3 != null && #request.friendnum3 != \"\"">
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('3');this.className='bt2';openWindow('open_change_confirm');changeStatus('1', '3');">修改</a><a
												class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('3');this.className='bt2';openWindow('open_cancel_confirm');cancelPosition('3');">取消</a>
										</td>
									</s:if>
									<s:else>
										<td>
											<a class="bt2" href="javascript:void(0)"
												onmousedown="this.className='bt2on'"
												onmouseup="passParam('3');this.className='bt2';openWindow('open_change_confirm');changeStatus('0', '3');">设置</a>
										</td>
									</s:else>
								</tr>


							</table>
						</div>
						<!--滚动条结束-->





						<div class="popup_confirm" id="open_change_confirm">
							<div class="bg"></div>
							<div class="tips_title">
								提示：
							</div>
							<div class="tips_body">
								<p id="noticeMsg">
									
								</p>
								<div class="blank10"></div>
								<p class="mt30" id="noticeConfirm">
								
								</p>
							</div>
							<div class="btn_box tc mt20">
								<span class=" mr10 inline_block "> <a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';setFamilyNumber();">确认</a>
								</span>
								<span class=" inline_block "> <a class="btn_bg_146"
									href="javascript:void(0)"
									onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
								</span>
							</div>
						</div>

						<div class="popup_confirm" id="open_cancel_confirm">
							<div class="bg"></div>
							<div class="tips_title">
								提示：
							</div>
							<div class="tips_body">
								<p id="cancelMsg">
									
								</p>
								<div class="blank10"></div>
								<p class="mt30">
                                            确认取消请点击“确认”提交
								</p>
							</div>
							<div class="btn_box tc mt20">
								<span class=" mr10 inline_block "> <a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';cancelFamilyNumber();">确认</a>
								</span>
								<span class=" inline_block "> <a class="btn_bg_146"
									href="javascript:void(0)"
									onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
								</span>
							</div>
						</div>

					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>