<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	 // 错误提示方式
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
	
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NG2.3自助终端系统随机密码认证</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	}
	//返回
	else if (key == 82 || key == 220)
	{
		goback("<%=currMenuID %>");
		return;
	}
	//更正
	else if (key == 8 || key == 32 || key == 66 || key ==77)
	{
		var etarget = getEventTarget(e);
		etarget.value = backString(etarget.value);
		
		var code = document.actform.password.value;
		if (pangu_getStrLen(trim(code)) == 6)
		{
			document.actform.password.focus();
			return true;
		}
	}
}
		
document.onkeyup = pwdKeyboardUp;

function MoveLast(textbox, start) 
{
	try 
	{
		var r = textbox.createTextRange();
		r.moveStart("character", start);
		r.select();
	}
	catch (e) 
	{
	}
}
		
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
	// 已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/backForward.action";
		document.actform.submit();
	}		
}

</script>
</head>
<body scroll="no" onload="document.getElementById('password').focus();">
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
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';chkRamdomPwdPage(); return false;" style="display:inline">短信验证码验证</a>
			<div class="b966 hidden">
				<div class="blank30" id="errorMsg"></div>
   				<div class=" pl62">
   					<div class="blank30"></div>
     				<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
     					
   					<%--键盘+输入框--%>
   					<div class="keyboard_wrap authentication authentication_2n clearfix">
   						<ul class="phone_num_list fl">
       						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1"></li>
       						<li class="clearfix pt10 hidden" style="height:auto;">
       							<p class=" fs18 fl lh30 yellow" >请输入服务密码</p>
       						</li>
       						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
       							<div class="blank30"></div>
       							<span style="font-size:18px;">服务密码：</span>
         						<input type="password"  maxlength="6" id="password" name="password" class="text1 fl relative" onfocus="MoveLast(event);" value="" />
       						</li>        
    					 </ul>
    					 	
    					<%--小键盘--%>
      					<div class="numboard numboard_big fl numboard_bg_short" id="numBoard">
      						<div class=" numbox">
          						<div class="blank10"></div>
          						<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
          						<div class="clear"></div>
          						<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
          						<div class="clear"></div>
          						<div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> </div>
          						<div class="nright"> <a href="javascript:void(0);" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> </div>
          						<div class="blank10"></div>
        					</div>
      					</div>
      					<script type="text/javascript">
              				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
							var numBoardText = document.getElementById('password');
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
   					</div>
     				<div class="blank10"></div>
   				</div>    				
			</div>
			<%--弹出框 正在验证是否满足参与条件,请稍候--%>
			<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
				<div class="bg"></div>
                <p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" /></p>
                <p class="tips_txt">正在验证是否满足参与条件，请稍候...</p>
               	<div class="line"></div>
                <div class="popup_banner"></div>                
            </div>
            <div class="popup_confirm" id="openWin_tipsMsg">
                <div class="bg"></div>
                <div class="tips_title">提示：</div>
                <div class="tips_body">
				    <p id="winText_tipsMsg"> <i ></i></p>
				    <div class="blank10"></div>
				    <p class="mt30"></p>
				</div>
	            <div class="btn_box tc mt20">
	             	<span class=" mr10 inline_block "><a title="继续" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();continueHandle();">继续</a></span>
	             	<span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();" id="closeBtn">关闭</a></span>
	            </div>
          	</div>
		</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">
		
// 转向到随机密码认证页面
function chkRamdomPwdPage()
{   
	if (submitFlag == 0)
	{
		submitFlag = 1;	
		openRecWaitLoading();
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}/recommendActivity/chkRamdomPwdPage.action";
		document.actform.submit();
	}
}
		
// 确认提交
function doSub() 
{
	var password = document.actform.password.value;
	
	if (trim(password) == "" || isNaN(password) || pangu_getStrLen(trim(password)) != 6) 
	{
		document.actform.password.focus();
		document.actform.password.select();
		MoveLast(document.actform.password, 0);
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入正确的服务密码");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入正确的服务密码";
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
	// 校验服务密码并进行活动预受理
	var ret = chkServPwd();
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


// 校验服务密码和活动预受理
function chkServPwd()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}activitiesRec/chkServPwd.action";
	
	// 参数
	var params = "activityId=" + encodeURI(encodeURI(document.getElementById('activityId').value));
	    params = params + "&dangciId=" + encodeURI(encodeURI(document.getElementById("dangciId").value)) ;
	    params = params + "&password=" + document.getElementById("password").value ;
	    params = params + "&curMenuId=" + document.getElementById("curMenuId").value ;
	    params = params + "&servnumber=" + document.getElementById("servnumber").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
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
	this.className = 'btn_bg_146';
	wiWindow.close();
}

// 继续办理
function continueHandle()
{
	openRecWaitLoading('recWaitLoading');
	document.actform.target="_self";
	document.actform.action = "${sessionScope.basePath}/recommendActivity/continueHandle.action";
	document.actform.submit();
}

// 打开弹出窗口	
function openWindow(id,tipMsg)
{
	document.getElementById("winText_tipsMsg").innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);
}

// 关闭弹出div时
function windowClose()
{
	wiWindow.close();
}
</script>
</html>
