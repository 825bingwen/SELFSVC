<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix ="s" uri="/struts-tags"%>

<%
    String errorMsg = (String)request.getAttribute("errormessage");
    if (errorMsg == null)
    {
        errorMsg = "";
    }
	    
    String invoiceData = (String) request.getAttribute("invoiceXML");
    String invoiceType = (String) request.getAttribute("invoiceType");
    String printType = (String) request.getAttribute("printType");
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String currServnumber = (String)request.getAttribute("servnumber");
    if (null == currServnumber)
    {
        currServnumber = "";
    }
    
    // 产品大类
    String typeID = "";
    
    if(null != request.getParameter("typeID"))
    {
    	typeID = (String)request.getParameter("typeID");
    }
    
    // 产品快速发布标识
    String quickPubFlag = "";
    
    if(null != request.getParameter("quickPubFlag"))
    {
    	quickPubFlag = (String)request.getParameter("quickPubFlag");
    }
    
    // 认证方式    服务密码、随机码
    String resultAvaiCode = "1100";
    
    //修改bug，从营销业务跳转过来后点击上一页显示空白页
    //add by sWX219697 2015-2-13 17:17:45 修改bug86167
    String isssGoBackFlag = (String)session.getAttribute("isssGoBackFlag");
%>
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
		
		// modify begin by qWX279398 DTS2015111900633
		// 可选服务密码跳转到随机密码页面标志 
		window.parent.currFlag = "servicePWD";
		// modify end by qWX279398 DTS2015111900633
		
		var submitFlag = 0;
		
		document.onkeydown = pwdKeyboardDown;
		
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
						MoveLast(document.getElementById('servnumber'));
					}
				}
			
				
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
	 			
				return true;
			<%
			}
			else
			{
			%>	
				//返回
				if (key == 82 || key == 220) 
				{
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
						MoveLast(document.getElementById('servnumber'));
					}
				}
			
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
		
		function goback(menuid)
		{
			// 多人缴费
			if ('<s:property value="morePhonesFlag"/>' == "morePhones")
			{
				document.forms[0].target = "_self";
    			document.forms[0].action = "${sessionScope.basePath }charge/finish.action";
   				document.forms[0].submit();
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

		// 宁夏非实名制登记确认后提交
		function submitReception()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openWindow_wait('pls_wait');
				
				document.actform.target = "_self";
				
				<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
				window.parent.currFlag = "";
				<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
				
				if ("<s:property value='printFlag' />" == "YJ")
				{
					document.actform.action = "${sessionScope.basePath}charge/userLoginWithPwdYJ.action";
				}
				else
				{
					document.actform.action = "${sessionScope.basePath}charge/userLoginWithPwd.action";
				}
				
				document.actform.submit();
			}
		}		

		function doSub()
		{
			var password = document.getElementById("password").value;
			if (password.value == "" || pangu_getStrLen(trim(password)) != 6)
			{
				changObj(document.getElementById('password'), 2);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请正确输入密码");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请正确输入密码！";
				}
				
				return;
			}
			
			// 登录时候是否是弱密码验证
			submitReception();
		}
		
		// 登录时候校验是否是弱密码验证
		function weakPwdCheckLogin(telNumber,password)
		{
			var url="${sessionScop.basePath}login/weakPwdCheckLogin.action";
			var param="servnumber="+telNumber+"&password="+password;
			var data = "";
			var contentLoader=new net.ContentLoaderSynchro(url,function(){
				data=this.req.responseText;
			},null,"POST",param,"application/x-www-form-urlencoded");
			return data;
			
		}
		
		//弹出弱密码确认框
		openWeakPwdConfirm = function(content)
		{
			document.getElementById('weakPwdPromptId').innerHTML = content;
			wiWindow = new OpenWindow("weakPwdCheck_confirm",708,392);//打开弹出窗口例子
		}
		
		// 非实名制验证
		function toRealNameCheck()
		{
			// 是否实名制信息登记
			var ret = realNameCheck(document.getElementById("servnumber").value);
			if(ret.split('^')[0] == '0')
			{
				openRealNameConfirm(ret.split('^')[1]);
				return;
			}
			submitReception();
		}
		
		// 非实名制信息提示
		function realNameCheck(telNumber)
		{
			var url="${sessionScop.basePath}login/realNameCheck.action";
			var param="servnumber="+telNumber;
			var data = "";
			var contentLoader=new net.ContentLoaderSynchro(url,function(){
				data=this.req.responseText;
			},null,"POST",param,"application/x-www-form-urlencoded");
			return data;
		}
		
		//弹出确认框
		openRealNameConfirm = function(content)
		{
			document.getElementById('realnamePromptId').innerHTML = content;
			wiWindow = new OpenWindow("realNameCheck_confirm",708,392);//打开弹出窗口例子
		}
		
		// 转向到随机密码认证页面
		function goRamdomPage()
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
			
			if (submitFlag == 0)
			{
				submitFlag = 1;		
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/randomcode_cq.action";
				document.actform.submit();
			}
		}
		
		// 聚焦密码输入框
		function focusText()
		{
			
			document.getElementById('servnumberInfo').disabled = true;
			document.getElementById('password').click();
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
				document.actform.action = "${sessionScope.basePath}charge/goRandomPwdPage.action";
				document.actform.submit();
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
		<form name="actform" method="post">
			<%-- add begin jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
            <!-- 话费连缴标志，1：话费连缴 -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- 交费金额 -->
            <s:hidden id="tMoney" name="tMoney" />
            
            <!-- 打印月结发票标志，1：打印月结发票 -->
            <s:hidden id="monthInvoiceFlag" name="monthInvoiceFlag"/>
            
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- 缴费总金额 -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- 凭条打印标记，1：已打印 -->
            <s:hidden name="printPayProFlag" id="printPayProFlag"/>
            
            <!-- 月结发票打印账期 -->
            <s:hidden name="invoicePrint.billCycle" id="month" />
            
            <!-- 打印全部发票标志，1:正打印 -->
            <s:hidden name="printAllInvFlag" id="printAllInvFlag"/>
            
            <!-- 主动营销是否已推荐，1：已推荐 -->
            <s:hidden name="serviceDialogFlag" id="serviceDialogFlag"/>
            
            <!-- 已打印手机号码字符串 -->
            <s:hidden id="printInvTelnum" name="printInvTelnum"/>
			
			<!-- 月结标识     "YJ"月结     ""预存       -->
			<input type="hidden" name="printFlag" id="printFlag" value="<s:property value='printFlag' />"/>
			
			<input type="hidden" id="servnumber" name="servnumber" value="<%=currServnumber %>"/>
			<input type="hidden" id="recoid" name="recoid" value="<s:property value='recoid'/>"/>
			
			<%--多人缴费已打印预存发票号码个数 --%>
			<input type="hidden" name="printInvTelnumLen" id="printInvTelnumLen" value="<s:property value='printInvTelnumLen' />"/>
			
			<%--多人缴费号码总个数 --%>
			<input type="hidden" name="morePhonesLen" id="morePhonesLen" value="<s:property value='morePhonesLen' />"/>
			
			<%-- 多人缴费打印全部打印标识       "all"全部打印      ""非全部打印 --%>
			<input type="hidden" name="printAllFlag" id="printAllFlag" value="<s:property value='printAllFlag' />"/>
			
			<%--多人缴费标识    "morePhones" 多人缴费         ""单人缴费    --%>
			<input type="hidden" name="morePhonesFlag" id="morePhonesFlag" value="<s:property value='morePhonesFlag' />"/>
			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<div class="blank20"></div>
				<span class="yellow fs16 ml10" >
					&nbsp;&nbsp;
				</span>

				<div class="b966">
					<div class="blank30" id="errorMsg"></div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.输入手机号码</i>
									<span id="redstar1" class="pl20 fl lh75">手机号码：</span>
									<input type="text" id="servnumberInfo" name="servnumberInfo"
										maxlength="11" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 1);MoveLast(this);" value="<%=currServnumber %>" />
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_山东_自助终端’用户登陆鉴权’增加短信随机码方式 --%>
								<% 
								// 省份判断
								if (Constants.PROOPERORGID_SD.equals(pageProvince)&& null != resultAvaiCode)
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">验证方式：</span>
									<span>
									<% 
							    	if (resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">服务密码登录</a>
									<% 
									}
									if (resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">短信随机码登录</a>
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
									<i class="lh30">2. 密码验证</i>
									<span id="redstar2" class="pl20 fl lh75">密码验证：</span>
									<input type="password" name="password" id="password"
										maxlength="6" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 2);MoveLast(this);" />
								</li>

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
								<div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg"></div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">关闭</a>
									</span>
								</div>
							</div>
							
							<div class="popup_confirm" id="openWin_successMsg">
								<div class="bg"></div>
								<div class="tips_title">提示：</div>
								<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_successMsg"></div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">确认</a>
									</span>
								</div>
							</div>
							
							<div class="popup_confirm" id="weakPwdCheck_confirm">
			                  <div class="bg"></div>
			                  <div class="tips_title">提示：</div>
			                  <div class="tips_body">
			                  	<div class="blank30"></div>
							    <p id="weakPwdPromptId"></p>
							    <div class="blank30"></div>
							  </div>
			                  <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();toRealNameCheck();">确认</a></span>
			                  </div>
			                </div>
							
							<div class="popup_confirm" id="realNameCheck_confirm">
			                  <div class="bg"></div>
			                  <div class="tips_title">提示：</div>
			                  <div class="tips_body">
			                  	<div class="blank30"></div>
							    <p id="realnamePromptId"></p>
							    <div class="blank30"></div>
							  </div>
			                  <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认</a></span>
				                  <span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
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
		
		//页面跳转之前清除session：isssGoBackFlag
		//add by sWX219697 2015-2-13 17:26:48 修改bug：86167
		window.onbeforeunload = function()
		{
		    <%
			if(StringUtils.isNotBlank(isssGoBackFlag))
			{
				session.removeAttribute("isssGoBackFlag");
			}
			%>
		}
	</script>
</html>
