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
	var key=GetKeyCode(e);
	if(key == 82 || key == 220) 
	{
	  goback("<s:property value='curMenuId' />");
	  return;
	}
}

function billSendCommit()
{
	var billSendType=document.getElementById("zdSend").value;
	document.actform.target="_self";
	document.actform.action="${sessionScope.basePath}billSend/billSendCommit.action?billSendType=" + billSendType;
	document.actform.submit();
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
//确认按钮			
function mainProductChangeSubmit(prodName,zdsend)
	{		
		document.getElementById("zdSend").value=zdsend;
		if ("mltpNo" == zdsend)
		{
			document.getElementById('winText').innerHTML = '<span class="yellow">尊敬的客户<s:property value="servnumber"/></span><br />您确认取消账单寄送业务吗？';
		}
		else
		{
			document.getElementById('winText').innerHTML = '<span class="yellow">尊敬的客户<s:property value="servnumber"/></span><br />您确认开通'+prodName+'账单寄送业务吗？';
		}
		
		openWindow('openWin1');
	}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">

				<%@ include file="/customer.jsp"%>
				<!--弹出窗 BEGIN-->
				<div class="openwin_tip" id="openWin1">
					<div class="bg"></div>
					<div class=" blank60"></div>
					<p class="fs28 lh2 pl50 pr50" id="winText"
						name="winText">

					</p>
					<div class="tc">
						<div class=" clear "></div>
						<div class=" blank30 "></div>
						<a title="确认" href="javascript:void(0)" class=" bt4"
							onmousedown="this.className='bt4on';billSendCommit();"
							onmouseup="this.className='bt4';wiWindow.close();">确认</a>
						<a title="" href="javascript:void(0)" class=" bt4 ml20"
							onmousedown="this.className='bt4on ml20'"
							onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
					</div>
				</div>
				<!-- 弹出窗 END -->
				<div class="service_brand w966 m0auto">
					<div class="service_index_list">
						<p class="hot_service"></p>
						<div class="p40">
							<div class="blank10"></div>
							<p class="tit_arrow fs22">
								<span class="bg"></span><span class="fs22">请选择账单寄送方式：</span>
							</p>
							<div class="blank25"></div>
							<div class="blank25"></div>
							<div class="btn_box tc">
								<input type="hidden" id="zdSend" name="zdSend" />
								<span class=" inline_block mr70 "><a title="短信账单"
									href="#" class="btn_bg_146"
									onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';mainProductChangeSubmit('短信','mltpSms');">短信账单</a>
								</span>
								<span class=" inline_block mr70 "><a title="彩信账单"
									href="#" class="btn_bg_146"
									onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';mainProductChangeSubmit('彩信','mltpMms');">彩信账单</a>
								</span>
<%--								<s:if test="emailService==1">--%>
									<span class=" inline_block mr70 "><a title="email账单"
										href="#" class="btn_bg_146"
										onmousedown="this.className='key_down'"
										onmouseup="this.className='btn_bg_146';mainProductChangeSubmit('email','mltpEml');">email账单</a>
									</span>
<%--								</s:if>--%>
								<!-- add by xkf57421 for OR_HUB_201112_1044 20120309 begin -->
								<span class=" inline_block mr70 "><a title="不寄账单"
									href="#" class="btn_bg_146"
									onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';mainProductChangeSubmit('不寄帐单','mltpNo');">不寄账单</a>
								</span>
								<!-- add by xkf57421 for OR_HUB_201112_1044 20120309 end -->
							</div>
						</div>
					</div>
				</div>
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
