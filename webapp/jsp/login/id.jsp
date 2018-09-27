<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // 省份信息
    String province = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String provinceSD = Constants.PROOPERORGID_SD;

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();

	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
		
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
	
	// modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
	// 山东可选方式登录样式切换标志   1:新样式  0：老样式
    String availableFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_AVAILABLECODE_FLAG);
    
    // 服务密码页面、短信随机码页面手机号码获取
    String currServnumber = (String)request.getAttribute("servnumber");
    
    if (null == currServnumber)
    {
        currServnumber = "";
    }
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
	
	// add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
	// 初始密码登录标志
	String initPwdLoginFlag = (String) request.getAttribute("initPwdLoginFlag");
	
	if(null == initPwdLoginFlag)
	{
		initPwdLoginFlag = "";
	}
	// add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746
	
	//add begin m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
    String authCodeType = (String) request.getParameter("authCodeType");
    
    // 可选认证方式
    String resultAvaiCode = (String)request.getParameter("resultAvaiCode");
    
    // 用户已认证，手机号码自动填充且不可修改
    NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    //add end m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script	type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js?ver=${jsVersion }"></script>
		<script type="text/javascript">
		//是否支持读取二代身份证信息标志,0不支持,1:支持
		var is2GIDFlag = <%=termSpecial.charAt(8)%>;		
		
		// 读卡等待时间
		var limitTime = 30;
		
		//等待读卡的句柄
		var waitingToken;
		
		// 提交标志
		var submitFlag = 0;
		
		// modify begin by qWX279398 DTS2015111900633
		// 可选身份证页面跳转到随机密码页面标志 
		window.parent.currFlag = "idCard";
		// modify end by qWX279398 DTS2015111900633
		
		document.onkeydown = pwdKeyboardDown;

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

		document.onkeyup = pwdKeyboardUp;
   
		function pwdKeyboardUp(e)
		{
			var key=GetKeyCode(e);
			//确认
			if (key == 13 || key == 89 || key == 221) 
			{
				doSub();
				return;
			}
			
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
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
				else if (key == 8 || key == 32 || key == 66 )
				{
					var etarget = getEventTarget(e);
					if (etarget.type == "text" || etarget.type == "password") 
					{
						etarget.value = backString(etarget.value);
					}
				}
				
				<%
				if (null == custInformation)
				{
				%>
				//更正时如果身份证号为空，则自动转到手机号码输入框
				if ((key == 8 || key == 32 || key == 66 ) && document.getElementById("IDCard").value == "")
				{
					MoveLast(document.getElementById('servnumber'));
					
					//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
					document.getElementById("servnumber").focus();
					changObj(document.getElementById("servnumber"), 1);
					return true;
				}
				
				//如果手机号码输入完成后自动跳转到身份证号输入框
				if (pangu_getStrLen(trim(document.getElementById("servnumber").value)) == 11
						&& document.getElementById("IDCard").value == "")
				{
					changObj(document.getElementById('IDCard'), 2);
					document.getElementById("IDCard").focus();
					return true;
				}
				<%
				}
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
				}
				
				<%
				if (null == custInformation)
				{
				%>
				//更正时如果身份证号为空，则自动转到手机号码输入框
				if ((key == 8 || key == 32 || key == 66 || key ==77) && document.getElementById("IDCard").value == "")
				{
					MoveLast(document.getElementById('servnumber'));
					
					//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
					document.getElementById("servnumber").focus();
					changObj(document.getElementById("servnumber"), 1);
					return true;
				}
				
				//如果手机号码输入完成后自动跳转到身份证号输入框
				if (pangu_getStrLen(trim(document.getElementById("servnumber").value)) == 11
						&& document.getElementById("IDCard").value == "")
				{
					changObj(document.getElementById('IDCard'), 2);
					document.getElementById("IDCard").focus();
					return true;
				}
				<%
				}
		    }
		    %>
			
		}

		function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		//过滤前后空字符串		
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
		
		//设置错误信息
		function setErrorInfoRegion(errMsg)
		{
		    document.getElementById("errorMsg").innerHTML = errMsg;
		    
		    <%--add begin g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>
		    if (closeStatus == 3)
			{
				//调用关闭身份证阅读器接口
				CloseCardReader();
				
				//可以点击顶部菜单
				closeStatus = 0;
				
				//取消定时器
				clearInterval(waitingToken);
			
				document.getElementById("homeBtn").disabled = false;
            	document.getElementById("quitBtn").disabled = false;
			}
			<%--add end g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>
		}
		
		//提交
		function doSub()
		{
		 	//modify begin zWX176560 2013/08/15 OR_NX_201308_11
			if("<%=Constants.PROOPERORGID_NX%>" == "<%=((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))%>")
			{
				var servnumber = trim(document.actform.servnumber.value);
				
				if("" == servnumber)
				{
					changObj(document.getElementById("servnumber"), 2);
					
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("请输入正确的手机号码");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "请输入正确的手机号码";
					}
					
					return false;
				}
			}else
			{
				var servnumber = trim(document.actform.servnumber.value);
				//if(!(/^1(3[4-9]|5[012789]|8[78])\d{8}$/).test(servnumber))
				if(!(/^\d{11}$/).test(servnumber))
				{
					changObj(document.getElementById("servnumber"), 2);
					
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("请输入正确的手机号码");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "请输入正确的手机号码";
					}
					
					return false;
				}
			}
		    //modify end zWX176560 2013/08/15 OR_NX_201308_11
			
			var IDCard = trim(document.actform.IDCard.value);
			// modify begin l00190940 2011-11-23 身份证尾号大小写均能识别
			if (IDCard == "" || !(/(^\d{15}$)|(^\d{17}([0-9]|x|X)$)/.test(IDCard)))  
			// modify end l00190940 2011-11-23 身份证尾号大小写均能识别 
			{ 
				//alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
				changObj(document.getElementById("IDCard"), 1);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入15位或18位号码的身份证号");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入15位或18位号码的身份证号";
				}
				
				return false; 
			}
			//检验用户输入的手机号是否在黑名单中(宁夏)
			<%
				if(Constants.PROOPERORGID_NX.equals(province))
				{
			%>
					if(checkBlackList(servnumber))
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
			openWindow_wait('pls_wait');
			
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }login/userLoginWithID.action";
			document.actform.submit();
		}
		
		// 返回
		function goback(menuid)
		{
			// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			if (document.getElementById("backWaitingFlag").value == "1")
			{
				openWindow_wait('pls_wait');
			}
			// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
			if (closeStatus == 3)
			{
				try{
			    // 关闭身份证读卡器
				window.parent.document.getElementById("idcardpluginid").CloseCardReader();
				}catch(e){}
			}
			window.history.back();
		}
		
		// 页面载入时加载身份证读卡器获取用户身份证信息
		function bodyLoad()
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
				document.getElementById('IDCard').focus();
			<%
			}
			%>
		    
			if (is2GIDFlag != 1) 
			{
				return;
			}
			
			try 
			{				
				//生产用
				var ret = InitIdCardReader();// 设置身份证读卡器为可用状态

				//测试用
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{
					setErrorInfoRegion("初始化身份证读卡器异常，请您手工输入您的身份证号信息。");
                    return;
				}
				else
				{
					// 点击顶部菜单需要关闭身份证打卡器
					closeStatus = 3;
					
					//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
                	document.getElementById("homeBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
					
					//在调用初始化身份证读卡器后，获取身份证信息之前，需要调用此接口检查一下读卡器状态
					getIdCardStatus();
				}
			}
			catch (ex) 
			{
				setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
                return;
			}
		}
		
		//获取身份证读卡器状态
		function getIdCardStatus()
		{
			try
			{
				//生产用
				var ret = GetIdCardStatus();// 获取身份证读卡器状态
				
				// 测试用
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{					
					setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
				}
				else
				{
					// 开始记时，并循环调用接口
					startclock();
				}
			}
			catch (excep) 
			{		
				setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
			}
		}
		
		//设置时间计算周期
		function startclock() 
		{			
			try 
			{
				document.getElementById("errorMsg").innerHTML = "请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面中。剩余时间：" + limitTime + "秒";
				
				// 获取身份证文字内容
				waitingToken = setInterval("getReadCardStatus()", 1000);
			}
			catch (e) 
			{
				setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
			}
		}
		
		// 循环调用获取身份证文字内容接口
		function getReadCardStatus() 
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			try 
			{
				//生产用
				var ret = GetIdCardContent();// 打开仓门接收用户身份证 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1）
				
				//测试用
				//var ret = "0姓名~男~民族~出生~住址~37061218840530402X~签发机关~有效期起始日期~有效期截止日期~最新住址";

				if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
				{	
					//调用关闭身份证阅读器接口
					//生产用
					CloseCardReader();
					
					//可以点击顶部菜单
					closeStatus = 0;
					
					//取消定时器
					clearInterval(waitingToken);
					
					document.getElementById("homeBtn").disabled = false;
            		document.getElementById("quitBtn").disabled = false;
					
					//身份证信息：0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
					var idCardInfor = ret.substring(1,ret.length).split('~');
					
					//公民身份号码
					var idCardNo = idCardInfor[5];
					
					document.getElementById("IDCard").value = idCardNo;
					
					// 将焦点定位到输入手机号码框
					<%
					if (null == custInformation)
					{
					%>
					changObj(document.getElementById('servnumber'), 2);
					document.getElementById("servnumber").focus();
					<%
					}
					%>
				} 
				else if (ret == "-1")
				{
					setErrorInfoRegion("获取身份证文字内容失败，请手工输入您的身份证号信息。");
				}
				else if (ret == "2")
				{	
					setErrorInfoRegion("证件无法识别，请手工输入您的身份证号信息。");
				}
				else
				{
					//读卡时长一共limitTime秒
					limitTime = limitTime - 1;
					if (limitTime <= 0)
					{
						setErrorInfoRegion("身份证读卡器读卡超时，请手工输入您的身份证号信息。");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面中。剩余时间：" + limitTime + "秒";
					}
				}
			}
			catch (e) 
			{		
				setErrorInfoRegion("身份证读卡器读卡失败，请手工输入您的身份证号信息。");
			}
		}
		
		<%--
		* 转向到服务密码认证页面。
		* @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
		--%>
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
		
		<%--
		* 转向到短信验证码认证页面。
		* @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
		--%>
		function goRandomPwdPage()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;

				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goRandomPwdPage.action";
				document.actform.submit();
			}
		}
		
		// 弹出正在处理DIV(宁夏)
		openRecWaitLoading_NX = function(id){
		<%
			if(Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
			{
		%>
				wiWindow = new OpenWindow("recWaitLoading", 804, 515);
		<%
			}
		%>
		}
		</script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<!-- add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746-->
				<input type="hidden" id="initPwdLoginFlag" name="initPwdLoginFlag" value="<%=initPwdLoginFlag %>"/>
				<!-- add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746-->
			    <input id = "province" value = "<%=province %>" type = "hidden">	
			    <input id = "provinceSD" value = "<%=provinceSD %>" type = "hidden">
			    
			    <input type="hidden" id="authCodeType" name="authCodeType" value="<%=authCodeType %>"/>
				<input type="hidden" id="resultAvaiCode" name="resultAvaiCode" value="<%=resultAvaiCode %>"/>
			    
			    <div class="blank20"></div>
				<span class="yellow fs16 ml10" >
					&nbsp;&nbsp;
				</span>
			    
			    <div class="blank50">
			    <%
			    
			    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
			    // 非山东可选方式登录  或  不是新样式登录方式，使用原有按钮展示页面
			    if (!(Constants.PROOPERORGID_SD.equals(province) && null != resultAvaiCode) || (!"1".equals(availableFlag)))
			    {
			    
				    // 可切短信验证码认证页面
					if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
					{
					%>
						<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goRandomPwdPage(); return false;" style="display:inline">短信验证码登录</a>    
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
				// modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式
				
				%>
			    </div>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<%
								if (null == custInformation)
								{
								%>
								<li class="on fs20 clearfix" id="phone_input_2" >
									<i class="lh30">1.输入手机号码</i>
									<span id="redstar1" class="pl20 fl lh75">手机号码：</span>
									<input type="text" id="servnumber" name="servnumber" maxlength="11" class="text1 fl relative" style="margin-left:20px;" onclick="changObj(this, 2);MoveLast(this);" value="<%=currServnumber %>"/>
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								<% 
								// 山东可选方式登录且为新样式标志
								if (Constants.PROOPERORGID_SD.equals(province) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">验证方式：</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goServicePwdPage(); return false;" style="display:inline">服务密码登录</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">短信随机码登录</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">身份证认证登录</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								
								<li class="fs20 clearfix" id="phone_input_1">
									<i class="lh30">2.输入身份证号</i>
									<span id="redstar2" class="pl20 fl lh75">身份证号：</span>
									<input type="text" id="IDCard" name="IDCard" maxlength="18" style="font-size:22px; margin-left:20px;"  class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);"/>
								</li>
								<%
								}
								else
								{
								%>
								<li class="fs20 clearfix" id="phone_input_2" >
									<i class="lh30">1.输入手机号码</i>
									<span id="redstar1" class="pl20 fl lh75">手机号码：</span>									
									<input type="text" id="servnumber" name="servnumber" style="margin-left:20px;" value="<%=custInformation.getServNumber() %>" class="text1 fl relative" />
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								<% 
								// 山东可选方式登录且为新样式标志
								if (Constants.PROOPERORGID_SD.equals(province) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">验证方式：</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goServicePwdPage(); return false;" style="display:inline">服务密码登录</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">短信随机码登录</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">身份证认证登录</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">2.输入身份证号</i>
									<span id="redstar2" class="pl20 fl lh75">身份证号：</span>
									<input type="text" id="IDCard" name="IDCard" maxlength="18" style="font-size:22px; margin-left:20px;" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);"/>
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
	            					<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				
	        				<%--add begin l00190940 2011/12/12 OR_SD_201111_370 为服务密码认证添加正在处理页面 --%>
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
							  <%if(provinceSD.equalsIgnoreCase(province) || Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
							    {%>
								    wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口
							  <%}%>
							}			
						    </script>
							<!--弹出窗结束-->
							<%--add end l00190940 2011/12/12 OR_SD_201111_370 为服务密码认证添加正在处理页面 --%>
	        				
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
								var type = 0;
								<%
								if (null != custInformation)
								{
								%>
									lastObj = document.getElementById('IDCard');
									type = 1;
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
							  			
							  			//如果手机号码输入完成后自动跳转到身份证号输入框
										if(pangu_getStrLen(trim(lastObj.value)) == 11 
												&& document.getElementById("IDCard").value == "")
										{
											changObj(document.getElementById('IDCard'), 1);
											document.getElementById("IDCard").focus();
											return true;
										}
							   
									}					
								}
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									
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
									else if (lastObj.value.length < 18 && v != "#" && type == 1)
									{								
										lastObj.value += v;
									}
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 0)
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
		if ("" != "<%=errorMsg %>")
		{			
			if ("1" == "<%=popupFlag %>")
			{
				alertRedErrorMsg("<%=errorMsg %>");
			}
			else
			{
				document.getElementById("errorMsg").innerHTML = "<%=errorMsg %>";
			}
		}
	</script>
</html>
