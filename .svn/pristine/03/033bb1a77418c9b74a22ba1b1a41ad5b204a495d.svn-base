<%--
 @User: 高群/g00140516
 @De: 2013/02/21
 @comment: 可选认证方式
 @remark create g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@ page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@ page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@ page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
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
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
    // 山东可选方式登录样式切换标志   1:新样式  0：老样式
    String availableFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_AVAILABLECODE_FLAG);
    
    // 服务密码、身份证登陆页面切换手机号码获取
    String currServnumber = (String)request.getAttribute("servnumber");
    
    if (null == currServnumber)
    {
        currServnumber = "";
    }
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
    
    String selectReason = (String)request.getAttribute("selectReason");
    if (selectReason == null)
    {
    	selectReason = "";
    }
    
    String authCodeType = (String) request.getParameter("authCodeType");
    
    String resultAvaiCode = (String)request.getParameter("resultAvaiCode");
    
    NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    
    String strLeftTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_RANDOMPWD_INTERVAL);
    if (strLeftTime == null || "".equals(strLeftTime.trim()))
    {
    	strLeftTime = "10";
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
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		var leftTime = <%=leftTime %>;
		var leftTimeToken;
		
		document.onkeydown = pwdKeyboardDown;
		
		// modify begin by qWX279398 DTS2015111900633
		getCurrLeftTime();
		
		// 当前号码短信验证码剩余时间提醒语句
		function getCurrLeftTime()
		{
			// 省份判断
			if ('<%=pageProvince %>' == '<%=Constants.PROOPERORGID_SD %>')
			{
				// 不是从服务密码页面或身份证页面跳转，清空父页面短信码时间和手机号
				if (window.parent.currFlag != 'servicePWD' && window.parent.currFlag != 'idCard')
				{
					window.parent.currLeftTime = "";
					window.parent.currNumber = "";
				}
			
				// 两次输入手机号码不同,清空父页面短信码时间和手机号
				if ("" != window.parent.currNumber && window.parent.currNumber != '<%=currServnumber %>')
				{
					window.parent.currLeftTime = "";
					window.parent.currNumber = "";
				}
				
				// 父页面短信码时间不为空,调用短信码时间 递减方法
				if ("" != window.parent.currLeftTime)
				{
					leftTime = window.parent.currLeftTime;
					leftTimeToken = setInterval("calLeftTime()", 1000);
				}
				
				window.parent.currFlag = "";
			}
		}
		
		// 手机号码赋值给父页面currNumber
		function saveNumToPare()
		{
			window.parent.currNumber = document.getElementById('servnumber').value;
		}
		// modify end by qWX279398 DTS2015111900633
		
		function pwdKeyboardDown(e) 
		{
			var key = GetKeyCode(e);
			
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
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);

			//确认
			if (key == 13 || key == 89 || key == 221) 
			{
				doSub();
				return;
			}
			
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
			{
			%>		
				// 退出
				if (key == 82 || key == 220)
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;			
				}
				// 清除
				else if (key == 77)
				{
					changValue(-2);
					return;
				}	
				//更正
				else if (key == 8 || key == 32 || key == 66)
				{
					var etarget = getEventTarget(e);
					if (etarget.type == "text" || etarget.type == "password") 
					{
						etarget.value = backString(etarget.value);
					}
					if (etarget.name == 'password' && etarget.value == '' )
					{
						<%
						if (null == custInformation)
						{
						%>
							MoveLast(document.getElementById('servnumber'));
						<%
						}
						%>
					}
				}
			
				<%
				if (null == custInformation)
				{
				%>
				var tel = document.forms[0].servnumber.value;
				var password = document.forms[0].password.value;		
		
				if ((key == 8 || key == 32 || key == 66)
				 		&& pangu_getStrLen(trim(password)) == 0 && pangu_getStrLen(trim(tel)) == 11) 
				{
					document.forms[0].servnumber.focus();
					
					changObj(document.forms[0].servnumber, 1);
					
					return true;
				}
				
	 			if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(password)) == 0) 
	 			{
	 				document.forms[0].password.focus();
	 				
	 				changObj(document.forms[0].password, 2);
	 				
	 				return true;
	 			}
	 			<%
				}
				%>
	 			
				return true;
			<%
			}
			else
			{
			%>	
				//返回
				if (key == 82 || key == 220) 
				{
					goback("");
					return;
				}
				//更正
				else if (key == 8 || key == 32 || key == 66 || key ==77)
				{
					var etarget = getEventTarget(e);
					if (etarget.type == "text" || etarget.type == "password") 
					{
						etarget.value = backString(etarget.value);
					}
					if (etarget.name == 'password' && etarget.value == '' )
					{
						<%
						if (null == custInformation)
						{
						%>
							MoveLast(document.getElementById('servnumber'));
						<%
						}
						%>
					}
				}
			
				<%
				if (null == custInformation)
				{
				%>
				var tel = document.forms[0].servnumber.value;
				var password = document.forms[0].password.value;		
		
				if ((key == 8 || key == 32 || key == 66 || key ==77)
				 		&& pangu_getStrLen(trim(password)) == 0 && pangu_getStrLen(trim(tel)) == 11)
				{
					document.forms[0].servnumber.focus();
					
					changObj(document.forms[0].servnumber, 1);
					
					return true;
				}
				
	 			if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(password)) == 0) 
	 			{
	 				document.forms[0].password.focus();
	 				
	 				changObj(document.forms[0].password, 2);
	 				
	 				return true;
	 			}
	 			<%
				}
				%>
	 			
				return true;
			<%
			}
			%>
		}		
		
		function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
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
		
		function doSub()
		{
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("servnumber").value;
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(document.getElementById('servnumber'), 1);
			
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入正确的手机号码");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入正确的手机号码";
				}
				
				return;
			}
		
			//检验用户输入的手机号是否在黑名单中(宁夏)
			<%
				if(Constants.PROOPERORGID_NX.equals(pageProvince))
				{
			%>
					if(checkBlackList(telNumber))
					{
						changObj(document.getElementById('servnumber'), 1);
						
						if ("1" == "<%=popupFlag %>")
						{
							alertRedErrorMsg("对不起，您不能在自助终端办理业务");
						}
						else
						{
							document.getElementById("errorMsg").innerHTML = "对不起，您不能在自助终端办理业务";
						}
						return;
					}
			<%
				}
			%>
			var password = document.getElementById("password").value;
			if (password.value == "" || pangu_getStrLen(trim(password)) != 6)
			{
				changObj(document.getElementById('password'), 2);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请正确输入短信随机码");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请正确输入短信随机码";
				}
				
				return;
			}
					
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openWindow_wait('pls_wait');
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/loginWithRandomPwdNew.action";
				document.actform.submit();
			}
		}
		
		function goback(curmenu) 
		{
			//已经点击了确认或返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openWindow_wait('pls_wait');
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				window.history.back();
			}	
		}

	    // 转向到服务密码认证页面
		function goServicePwdPage()
		{   
			if (submitFlag == 0)
			{
				submitFlag = 1;	
					
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goServicePwdPage.action";
				document.actform.submit();
			}
			
		}
		
		// 转向身份证认证页面
		function goIdPage()
		{
		    if (submitFlag == 0)
		    {
		        submitFlag = 1;		
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goIDPage.action";
				document.actform.submit();
		    }
		}
		
		// 发送短信随机码
		var sendingFlag = false;
		function sendRandomPwd()
		{
			if (document.getElementById("nextRandomPwdBtn").disabled)
			{
				return;
			}
			
			if (sendingFlag)
			{
				return;
			}
			
			sendingFlag = true;
		
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			var servnumber = document.getElementById("servnumber").value;
			if (servnumber == "" || !pattern.test(servnumber))
			{
				changObj(document.getElementById('servnumber'), 1);
			
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入正确的手机号码");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入正确的手机号码";
				}
				
				sendingFlag = false;
				
				return;
			}
			
			var url = "${sessionScope.basePath}login/sendRandomPwd_hub.action";
			var params = "servnumber=" + servnumber+ "&number=" +  + Math.random();
			var loader = new net.ContentLoaderSynchro(url, netload = function () {
							sendingFlag = false;
							
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
									leftTime = <%=leftTime %>;
									
									document.getElementById("errorMsg").innerHTML = ' 短信验证码已发送到手机，请注意查收！如需获取新短信验证码，请' + leftTime + '秒后点击"点击获取"。';
									
									document.getElementById("nextRandomPwdBtn").disabled = true;
									leftTimeToken = setInterval("calLeftTime()", 1000);	
								}
							}, null, "POST", params, "application/x-www-form-urlencoded"); 				
		}
		
		// 光标聚焦
		function focusText()
		{
			<%
			if (null == custInformation)
			{
			%>
				document.getElementById('servnumber').focus();
			<%
			}
			else
			{
			%>
				document.getElementById('password').focus();
			<%
			}
			%>
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
				// modify begin by qWX279398 DTS2015111900633
				window.parent.currLeftTime = leftTime;
				document.getElementById("nextRandomPwdBtn").disabled = true;
				// modify end by qWX279398 DTS2015111900633
				
				document.getElementById("errorMsg").innerHTML = ' 短信验证码已发送到手机，请注意查收！如需获取新短信验证码，请' + leftTime + '秒后点击"点击获取"。';
			}
			else
			{
				clearInterval(leftTimeToken);
				
				// modify begin by qWX279398 DTS2015111900633
				window.parent.currLeftTime = "";
				// modify end by qWX279398 DTS2015111900633
				
				document.getElementById("errorMsg").innerHTML = ' 短信验证码已发送到手机，请注意查收！如需获取新短信验证码，请点击"点击获取"。';
				document.getElementById("nextRandomPwdBtn").disabled = false;
			}
		}
		
		// 弹出正在处理DIV(宁夏)
		openRecWaitLoading_NX = function(id){
		<%
			if(Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
			{
		%>
				wiWindow = new OpenWindow("recWaitLoading", 804, 515);
		<%
			}
		%>
		}
		</script>
	</head>
	<body scroll="no" onload="focusText();">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="selectReason" name="selectReason" value="<%=selectReason %>" />
			<input type="hidden" id="authCodeType" name="authCodeType" value="<%=authCodeType %>"/>
			<input type="hidden" id="resultAvaiCode" name="resultAvaiCode" value="<%=resultAvaiCode %>"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<div class="blank20"></div>
				<span class="yellow fs16 ml10" >
					&nbsp;&nbsp;
				</span>
				
				<div class="blank50">
				<%
				
				// modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
				// 非山东可选方式登录  或  不是新样式登录方式，使用原有按钮展示页面
				if (!(Constants.PROOPERORGID_SD.equals(pageProvince) && null != resultAvaiCode) || (!"1".equals(availableFlag)))
				{
				
					// 可切换至身份证认证页面
					if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
					{
					%>
						<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goIdPage(); return false;" style="display:inline">身份证认证登录</a>    
					<%
					}
					
					// 可切换至服务密码认证页面
					if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
					{
					%>		
						<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goServicePwdPage(); return false;" style="display:inline">服务密码认证登录</a>    
					<%
					}
				}
				// modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
				
				%>
				</div>
				
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<%
								if (null == custInformation)
								{
								%>
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.输入手机号码</i>
									<span id="redstar1" class="pl20 fl lh75">手机号码：</span>									
									<input type="text" id="servnumber" name="servnumber"
										maxlength="11" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 1);MoveLast(this);" value="<%=currServnumber %>"/>							    
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								<% 
								// 山东可选方式登录且为新样式标志
								if (Constants.PROOPERORGID_SD.equals(pageProvince) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">验证方式：</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92'; goServicePwdPage(); return false;" style="display:inline">服务密码登录</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">短信随机码登录</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92'; goIdPage(); return false;" style="display:inline">身份证认证登录</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								
								<li class="fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. 短信验证码 
									
									<%-- modify begin by qWX279398 DTS2015111900633--%>
									<a id="nextRandomPwdBtn" title="点击获取" href="javascript:void(0)" class="bt2"  onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';saveNumToPare();sendRandomPwd();">点击获取</a> 
									<%-- modify end by qWX279398 DTS2015111900633--%>
									
									</i>
									<span id="redstar2" class="pl20 fl lh75">短信验证码：</span>
									
									<%--modify begin by qWX279398 DTS2015111602468 --%>
									<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>					
									<input type="text" name="password" id="password"
										maxlength="6" class="text1 fl relative"
										onclick="changObj(this, 2);MoveLast(this);" />
									<%-- modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
									<%--modify end by qWX279398 DTS2015111602468 --%>
								</li>
								<%
								}
								else
								{
								%>
								<li class="fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.输入手机号码</i>
									<span id="redstar1" class="pl20 fl lh75">手机号码：</span>									
									<input type="text" id="servnumber" name="servnumber" value="<%=custInformation.getServNumber() %>"
										class="text1 fl relative" style="margin-left:20px;" />							    
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								<% 
								// 山东可选方式登录且为新样式标志
								if (Constants.PROOPERORGID_SD.equals(pageProvince) && "optional".equals(authCodeType) && null != resultAvaiCode  && ("1".equals(availableFlag)))
								{
								%>
								
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">验证方式：</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92'; goServicePwdPage(); return false;" style="display:inline">服务密码登录</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">短信随机码登录</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92'; goIdPage(); return false;" style="display:inline">身份证认证登录</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								
								<li class="on fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. 短信验证码 
									
									<%-- modify begin by qWX279398 DTS2015111900633--%>
									<a id="nextRandomPwdBtn" title="点击获取" href="javascript:void(0)" class="bt2"  onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';saveNumToPare();sendRandomPwd();">点击获取</a> 
									<%-- modify end by qWX279398 DTS2015111900633--%>
									
									</i>
									<span id="redstar2" class="pl20 fl lh75">短信验证码：</span>
									
									<%--modify begin by qWX279398 DTS2015111602468 --%>
									<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
									<input type="text" name="password" id="password"
										maxlength="6" class="text1 fl relative"
										onclick="changObj(this, 2);MoveLast(this);" />
									<%-- modify end by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
									<%--modify end by qWX279398 DTS2015111602468 --%>
								
								</li>								
								<%
								}
								%>
								
								<%
								if ("1".equals(keyFlag))
								{
								%>
								<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
         							<p class="p10">1. 信息输入完毕后，请按"确认"键提交；</p>
         							<p class="p10">2. 如需修改，请按"清除"键。</p>
         						</li>
								<%
								}
								%>
							</ul>
							
							<!--小键盘-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class=" numbox">
									<div class="blank10"></div>
									<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a
										href="javascript:void(0)">3</a>
									<a href="javascript:void(0)" class="func1" name="functionkey"
										id="numBoardBack" onmousedown="this.className='func1on'"
										onmouseup="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a
										href="javascript:void(0)">6</a>
									<a href="javascript:void(0)" class="func2" name="functionkey"
										id="numBoardClear" onmousedown="this.className='func2on'"
										onmouseup="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a
											href="javascript:void(0)">9</a>
										<a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a
											href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a href="javascript:void(0)" onclick="doSub();return false;"
											class="func3" name="functionkey" id="numBoardEnter"
											onmousedown="this.className='func3on'"
											onmouseup="this.className='func3'">1</a>
									</div>
									<div class="blank10"></div>
								</div>
							</div>
							
							<!--弹出框 正在处理 请稍候-->
							<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
								<div class="bg"></div>
								<p class="mt40">
									<img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." />
								</p>
								
								<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
								<p class="tips_txt">
									<%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %>
								</p>
								<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
								
								<div class="line"></div>
								<div class="popup_banner"></div>
							</div>
							
							<script type="text/javascript">
							openWindow_wait = function(id)
							{
							  	wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口
							}			
						    </script>
							<!--弹出窗结束-->

							<script type="text/javascript">	
	                			<%
									if("1".equals(redStarKey))
									{
								%>
									var textContent1 = document.getElementById('redstar1').innerHTML;
									document.getElementById('redstar1').innerHTML=textContent1 + '<font color="red">*</font>';
									
									var textContent2 = document.getElementById('redstar2').innerHTML;
									document.getElementById('redstar2').innerHTML=textContent2 + '<font color="red">*</font>';
								<%
									}
								%>
	                			
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('servnumber');
								var type = 1;
								<%
								if (null != custInformation)
								{
								%>
									lastObj = document.getElementById('password');
									type = 0;
								<%
								}
								%>
								
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
						    		
					     			if (numBoardBtns[i].name == 'functionkey')
					     			{
					     				continue;  
					     			}
						 
									numBoardBtns[i].onmousedown = function(){
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function(){
									
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			// servnumber输入完毕自动跳转到password
							  			if (pangu_getStrLen(lastObj.value) == 11 
							  					&& pangu_getStrLen(trim(document.forms[0].password.value)) == 0) 
							 			{
							 				document.forms[0].password.focus();
							 				
							 				changObj(document.forms[0].password, 2);
							 				
							 				return true;
							 			}
							   
									}					
								}
						
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										type = 0;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
									}
								}					
						
								function changValue(t, v)
								{
									lastObj.focus();
									lastObj.select();
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = "";
									}
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
									{	
										lastObj.value += v;
									}
									else if(lastObj.value.length < 6 && !isNaN(v) && type == 0)
									{
										lastObj.value += v;
									}
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
										
	              			</script>
							<!--小键盘end-->
							
							<!-- 红色错误提示信息 -->
							<div class="popup_confirm" id="openWin_ErrorMsg">
								<div class="bg"></div>
								<div class="tips_title">提示：</div>
								<div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg">
									
							  	</div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">关闭</a>
									</span>
								</div>
							</div>
							
							<div class="popup_confirm" id="openWin_successMsg">
								<div class="bg"></div>
								<div class="tips_title">提示：</div>
								<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_successMsg">
									
							  	</div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">确认</a>
									</span>
								</div>
							</div>
							
							<script type="text/javascript">								
								alertRedErrorMsg = function(content)
								{
									document.getElementById('winText_ErrorMsg').innerHTML = content;
									wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
								};
							</script>
						</div>
						<div class="blank10"></div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<!--弹出正在处理div-->
	<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
		<div class="bg"></div>
	    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
	    
	    <%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
	   	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %></p>                     
		<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
	</div>
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
	</script>
</html>
