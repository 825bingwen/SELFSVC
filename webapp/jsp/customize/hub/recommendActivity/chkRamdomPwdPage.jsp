<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
		
    String errormessage = (String)request.getAttribute("errormessage");
    if (errormessage == null)
    {
    	errormessage = "";
    }
    
    // add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errormessage = CommonUtil.errorMessage(errormessage);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
    
    // 错误提示方式
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String strLeftTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_RANDOMPWD_INTERVAL);
    if (strLeftTime == null || "".equals(strLeftTime.trim()))
    {
    	strLeftTime = "60";
    }
    
    int leftTime = Integer.parseInt(strLeftTime);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NG2.3自助终端系统随机密码认证</title>
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
var leftTime = <%=leftTime %>;
var leftTimeToken;
		
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
		return false;
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
		
document.onkeydown = pwdKeyboardDown;		

//计算字符串的长度
function pangu_getStrLen(s) 
{
	var count = 0;
	var lenByte = s.length;
	for (i = 0; i < lenByte; i++) 
	{
		if (s.charCodeAt(i) > 256) 
		{
			count = count + 2;
		} 
		else 
		{
			count = count + 1;
		}
	}
	return count;
}

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	//确认
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}
	
	// 返回
	if (key == 82 || key == 220)
	{
		goback("<%=currMenuID %>");
		return;
	}		
	//更正
	else if (key == 8 || key == 32 || key == 66 || key ==77)
	{
		var etarget = getEventTarget(e);
		etarget.value = backString(etarget.value);
	
		var code = document.actform.randomPwd.value;
		if (pangu_getStrLen(trim(code)) == 6)
		{
			document.actform.randomPwd.focus();
			return true;
		}
	}
}
		
document.onkeyup = pwdKeyboardUp;
		
function trim(str) 
{
	while (str.charAt(str.length - 1) == " ") 
	{
		str = str.substring(0, str.length - 1);
	}

	while (str.charAt(0) == " ") 
	{
		str = str.substring(1, str.length);
	}
	
	return str;
}

function goback(curmenu) 
{
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading()
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/backForward.action";
		document.actform.submit();
	}		
}

// 点击输入框，光标定位在文本内容后
function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
	r.select();
}		
</script>
</head>
<body scroll="no" onload="document.getElementById('randomPwd').focus();">
	<form id="actform" name="actform" method="post">
		
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
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'"
				onmouseup="this.className='bt10 fr mr43';chkRamdomPwdPage(); return false;" style="display: inline">服务密码验证</a>
			<div class="b966 hidden">
				<div class="blank30" id="errorMsg"></div>
				<div class=" pl62">
					<p class="fs18 mt10">
						<span style="color: #c71d02; font-size: 18px;" id="needRandomPwd"></span>
					</p>

					<!--键盘+输入框+温馨提示-->
					<div class="keyboard_wrap authentication authentication_2n ">
						<ul class="phone_num_list fl">
							<li class="clearfix pt10 hidden" style="height: auto;">
								<p class=" fs18 fl lh10 yellow" id="fdTipsMsg"></p>
							</li>
							<li class="clearfix pt10 hidden" style="height: auto;">
								<p class=" fs18 fl lh10 yellow" id="content">
									如需获取短信验证码，请点击按钮"点击获取"。
								</p>
							</li>
							<li class="clearfix pt10 hidden" style="height: auto;">
								<p class=" fs18 fl lh30 yellow">
									请输入短信验证码
								</p>
							</li>
							<li class="fs20 mt10 clearfix hidden" style="height: auto;"
								id="phone_input_1">
								<a id="randomPwdBtn" href="javascript:void(0)" class="bt2" onmousedown="this.className='bt2on'"
									onmouseup="this.className='bt2';sendRandomPwd();">点击获取</a>
							</li>
							<li class="fs20 mt10 clearfix hidden" style="height: auto;" id="phone_input_1">
								<div class="blank10"></div>
								<span id="redstar1" style="font-size: 18px;">短信验证码：</span>
								<input type="password" id="randomPwd" name="randomPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(event);" value="" />
							</li>
						</ul>

						<!--小键盘-->
						<div class="numboard numboard_big fl numboard_bg_short"
							id="numBoard">
							<div class=" numbox">
								<div class="blank10"></div>
								<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a>
								<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
								<div class="clear"></div>
								<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a>
								<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'"
									onmouseup="this.className='func2';changValue(-2);"></a>
								<div class="clear"></div>
								<div class="nleft">
									<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a>
									<a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a>
								</div>
								<div class="nright">
									<a href="javascript:void(0);" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a>
								</div>
								<div class="blank10"></div>
							</div>
						</div>
						<script type="text/javascript">
              				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
						var numBoardText = document.getElementById('randomPwd');
						for ( i = 0; i < numBoardBtns.length; i++)
						{
			    			if (!numBoardBtns[i].className) 
			    			{
			    				numBoardBtns[i].className = '';
			    			}
			    			
		     				if (numBoardBtns[i].name=='functionkey')
		     				{
		     					continue ;
		     				}  
			 
							numBoardBtns[i].onmousedown = function() {
				  				this.className = 'on';
							}
							
							numBoardBtns[i].onmouseup = function() {
										changValue(0, this.innerHTML);
										
				  				this.className = '';						  									   
							}					
						}
						
						function changValue(t, v)
						{
							if (t == -1)
							{
								numBoardText.value = numBoardText.value.slice(0, -1);
							}
							else if(t == -2)
							{
								numBoardText.value = ""
							}
							else if (numBoardText.value.length < 6)
							{								
								numBoardText.value += v;																													
							}
							
							var r = numBoardText.createTextRange(); 
							r.collapse(false); 
							r.select();
						}				
            				</script>
						<!--小键盘end-->
						<!--弹出框 正在处理 请稍候-->
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
								<span class=" mr10 inline_block "><a title="继续" href="#" class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';windowClose();continueHandle();">继续</a>
								</span>
								<span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" id="closeBtn"
									onmouseup="this.className='btn_bg_146';windowClose();">关闭</a>
								</span>
							</div>
						</div>
					</div>
					<div class="blank10"></div>
				</div>
			</div>
		</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">
if ("" != "<%=errormessage %>")
{			
	if ("1" == "<%=popupFlag %>")
	{
		alertRedErrorMsg("<%=errormessage %>");
	}
	else
	{
		document.getElementById("errorMsg").innerHTML = "<%=errormessage %>";
	}
}

// 转向到服务密码认证页面
function chkRamdomPwdPage()
{   
	if (submitFlag == 0)
	{
		submitFlag = 1;		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}recommendActivity/chkServPwdPage.action";
		document.actform.submit();
	}
}
		
// 发送短信随机码
function sendRandomPwd()
{
	if (document.getElementById("randomPwdBtn").disabled)
	{
		return;
	}
	
	var url = "${sessionScope.basePath}login/sendRandomPwd_hub.action";
	var params = "servnumber=<s:property  value='servnumber'/>&number=" +  + Math.random();
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
					var resXml = this.req.responseText;
						if ('' != resXml)
						{									
							if ("1" == "<%=popupFlag %>")
						{
							alertRedErrorMsg(resXml);
						}
						else
						{
							document.getElementById("errorMsg").innerHTML = resXml;
						}
					}
					else
					{		
						document.getElementById("errorMsg").innerHTML = "";
						
						if ("1" == "<%=popupFlag %>")
						{
							alertSuccessMsg(" 短信验证码已发送到手机，请注意查收！");
						}
						else
						{
							document.getElementById("fdTipsMsg").innerHTML = " 短信验证码已发送到手机，请注意查收！";
						}
						
						document.getElementById("randomPwdBtn").disabled = true;
						document.getElementById("randomPwdBtn").innerHTML = "再次获取";
						leftTime = <%=leftTime %>;					
						document.getElementById("content").innerHTML = '如需获取新短信密码，请' + leftTime + '秒后点击"再次获取"。';
						leftTimeToken = setInterval("calLeftTime()", 1000);									
					}
				}, null, "POST", params, "application/x-www-form-urlencoded"); 				
}

function calLeftTime()
{
	if (leftTime <= 0)
	{
		return;
	}
	
	leftTime = leftTime - 1;
	
	if (leftTime > 0)
	{
		document.getElementById("content").innerHTML = '如需获取新短信验证码，请' + leftTime + '秒后点击"再次获取"。';
	}
	else
	{
		clearInterval(leftTimeToken);
		document.getElementById("content").innerHTML = '如需获取新短信密码，请点击"再次获取"。';
		document.getElementById("randomPwdBtn").disabled = false;
	}
}

// 确认提交
function doSub() 
{
	var randomPwd = document.actform.randomPwd.value;
	
	if (trim(randomPwd) == "" || isNaN(randomPwd) || pangu_getStrLen(trim(randomPwd)) != 6) 
	{
		document.actform.randomPwd.focus();
		document.actform.randomPwd.select();
		
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入正确的短信验证码！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入正确的短信验证码！";
		}
		
		return;
	}
	
	// 已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0) 
	{
		submitFlag = 1;

		wiWindow = new OpenWindow("pls_wait", 804, 515);
		
		setTimeout("toSub()",500);
    }
}

function toSub()
{
	// 校验短信验证码并活动预受理
	var ret = chkRandomPwd();
	if (ret != 0)
	{
		submitFlag = 0;
		wiWindow.close();
		
		// 错误标识
		var resonMark = ret.split('~~')[0]
		
		// 错误原因
		var reasonTip = ret.split('~~')[1];
		
		if("11" == resonMark)
		{
			document.getElementById("closeBtn").onmouseup = closeAndStay;
		}
		else
		{
			document.getElementById("closeBtn").onmouseup = closeAndBack;
		}
		
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
		document.actform.action = "<%=basePath %>activitiesRec/queryDangCiDesc.action";
		document.actform.submit();
	}
}

// 关闭并返回
function closeAndBack()
{
	document.actform.target = "_self";
	document.actform.action = "<%=basePath %>/recommendActivity/qryRecommendActList.action";
	document.actform.submit();
}

// 关闭并留在当前页面
function closeAndStay()
{
	this.className='btn_bg_146';
	wiWindow.close();
}

// 校验服务密码和活动预受理
function chkRandomPwd()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}activitiesRec/chkRandomPwd.action";
	
	// 参数
	var params = "activityId=" + encodeURI(encodeURI(document.getElementById('activityId').value));
	    params = params + "&dangciId=" + encodeURI(encodeURI(document.getElementById("dangciId").value)) ;
	    params = params + "&randomPwd=" + document.getElementById("randomPwd").value ;
	    params = params + "&curMenuId=" + document.getElementById("curMenuId").value ;
	    params = params + "&servnumber=" + document.getElementById("servnumber").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

// 继续办理
function continueHandle()
{
	openRecWaitLoading('recWaitLoading');
	document.actform.target="_self";
	document.actform.action = "${sessionScope.basePath}/recommendActivity/continueHandle.action";
	document.actform.submit();
}

function openWindow(id,tipMsg)
{
	document.getElementById("winText_tipsMsg").innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子	
}

// 关闭弹出div
function windowClose()
{
	wiWindow.close();
}
</script>
</html>
