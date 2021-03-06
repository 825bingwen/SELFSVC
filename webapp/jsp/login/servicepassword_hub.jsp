<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ page import="com.huawei.boss.common.security.RSAUtil"%>
<%
    RSAUtil.generateKeyPair(session, 1024);
    String errorMsg = (String)request.getAttribute("errormessage");
    if (errorMsg == null)
    {
        errorMsg = "";
    }
    
    // add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求

    
    //add begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597
    
    // 产品大类
    String typeID = "";
    
    if(null != request.getParameter("typeID"))
    {
    	typeID = (String)request.getParameter("typeID");
    }
    
    // 热门产品受理标志
    String hotRecFlag = "";
    
    if(null != request.getParameter("hotRecFlag"))
    {
    	hotRecFlag = (String)request.getParameter("hotRecFlag");
    }
    
    // 产品快速发布标识
    String quickPubFlag = "";
    
    if(null != request.getParameter("quickPubFlag"))
    {
    	quickPubFlag = (String)request.getParameter("quickPubFlag");
    }
    
    String searchType = (String) request.getParameter("searchType");
    if (searchType == null)
    {
    	searchType = "";
    }
    //add end CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597
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
		<script type="text/javascript"  src="${sessionScope.basePath}js/BigInt.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath}js/Barrett.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath}js/RSA.js"></script>	
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">

		
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
			//返回
			else if (key == 82 || key == 220) 
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
					MoveLast(document.getElementById('servnumber'));
				}
			}
			
			var tel = document.forms[0].servnumber.value;
			var password = document.forms[0].password.value;

			<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
			var randomCode = document.forms[0].randomCodeImage.value;
			<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>		
	
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

 			<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
 			if (pangu_getStrLen(trim(password)) == 6 && pangu_getStrLen(trim(randomCode)) == 0)
 			{
				document.forms[0].randomCode.focus();

				changObj(document.forms[0].randomCodeImage, 3);

				return true;
 	 		}
 			<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
 			
			return true;
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
			
				alertRedErrorMsg("请输入正确的手机号码");
				
				return;
			}
			
			var password = document.getElementById("password").value;
			if (password.value == "" || pangu_getStrLen(trim(password)) != 6)
			{
				changObj(document.getElementById('password'), 2);
				
				alertRedErrorMsg("请正确输入密码");
				
				return;
			}

			<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
			var randomCode = document.getElementById("randomCodeImage").value;
			if (randomCode.value == "" || pangu_getStrLen(trim(randomCode)) != 4)
			{
				changObj(document.getElementById('randomCodeImage'), 3);

				alertRedErrorMsg("请正确输入验证码");

				return;
			}

			//验证图片验证码
			var ran = randomCodeSecurity();
			
			if (ran == "0")
			{
				alertRedErrorMsg("图片验证码输入错误");
				changeImage();
				return;
			}	
			
			if (ran != "1")
			{
				alertRedErrorMsg("图片验证码输入错误");
				changeImage();
				return;
			} 
			<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				encryptPassword("password","<%=(String)session.getAttribute(RSAUtil.PUBLIC_STRING)%>","<%=(String)session.getAttribute(RSAUtil.MODULUS_STRING)%>");
				openRecWaitLoading('recWaitLoading');	
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/userLoginWithPwd.action?loginflag=hub";
				document.actform.submit();
			}
		}

		<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		//校验图片验证码
		function randomCodeSecurity()
		{
			var url = "${sessionScope.basePath}login/randomCodeSecurity.action";
			var params = "randomCodeImage=" + getValue("randomCodeImage");
			var resXml;
			var loader = new net.ContentLoaderSynchro(url, netload = function()
			{
				resXml = this.req.responseText;
			}, null, "POST", params, "application/x-www-form-urlencoded");
			
			return resXml;
		}
		<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		
		// 转向到随机密码认证页面
		function goRamdomPage()
		{   
			if (submitFlag == 0)
			{
				submitFlag = 1;		
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goRamdomPage_hub.action";
				document.actform.submit();
			}
			
		}
		
		//返回上一页
		function goback(menuid)
		{
			window.history.back();
		}

		<%--add begin lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		function changeImage()
		{
			document.getElementById('refreshRandomCodeImage').src="${sessionScope.basePath}login/randomCodeImage.action?pageId=" + Math.random();
		}
		<%--add end lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		</script>
	</head>
	<body scroll="no"
		onload="document.getElementById('servnumber').focus();">
		<form name="actform" method="post">
			<!--add begin CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597-->
			<input type="hidden" id="typeID" name="typeID" value="<%=typeID %>"/>
			<input type="hidden" id="hotRecFlag" name="hotRecFlag" value="<%=hotRecFlag %>"/>
			<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="<%=quickPubFlag %>"/>
			<input type="hidden" id="searchType" name="searchType" value="<%=searchType %>" />
			<!--add end CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597-->
			
			<%@ include file="/titleinc.jsp"%>
		
		
			
			<div class="main" id="main" style="margin-top:-30px;">
			 
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goRamdomPage(); return false;" style="display:inline">短信验证码登录</a>
				<div class="b966">
					<div class="blank30"></div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.输入手机号码</i>
									<span class="pl20 fl lh75">手机号码：</span>
									<input type="text" id="servnumber" name="servnumber"
										maxlength="11" class="text1 fl relative"
										onclick="changObj(this, 1);MoveLast(this);" />
								</li>
								<li class="fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. 密码验证</i>
									<span class="pl20 fl lh75">密码验证：</span>
									<input type="password" name="password" id="password"
										maxlength="6" class="text1 fl relative"
										onclick="changObj(this, 2);MoveLast(this);" />
								</li>
								<%--add begin lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								<li class="fs20 clearfix" id="phone_input_3">
									<i class="lh30">3.图片验证码</i>
									<span class="pl20 fl lh75">验证码：&nbsp;&nbsp;&nbsp;</span>
									<input type="text" name="randomCodeImage" id="randomCodeImage"
										maxlength="4" class="text3 fl ralative"
										onclick="changObj(this, 3);MoveLast(this);"/>
									<img border="1" 
										src="${sessionScope.basePath}login/randomCodeImage.action" width="60"
										height="45" title="点击重新获取验证码" id="refreshRandomCodeImage"
										onclick="changeImage();" style="cursor: pointer;" />
								</li>
								<%--add end lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								
								<li class="fs20 clearfix">
								<div class="blank30"></div>
								<p class="tit_arrow">
									<span class="bg"></span>温馨提示：
								</p>
								<p class="p20">
									点击右上角的"短信验证码登录"可切换至
								</p>
								<p class="p20">
									短信验证码登录页面
								</p>
								</li>
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

							<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('servnumber');
								var lastpw = document.getElementById('password');
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

							  			<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
										// password输入完毕自动跳转到randomCodeImage
							 			if (pangu_getStrLen(lastpw.value) == 6
									 			&& pangu_getStrLen(trim(document.forms[0].randomCodeImage.value)) == 0) 
								 		{
											document.forms[0].randomCodeImage.focus();

											changObj(document.forms[0].randomCodeImage, 3);

											return true;
									 	}
							 			<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
							   
									}					
								}

								<%--modify begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if (t == 2)
									{
										type = 0;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else
									{
										type = 2;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 4 && !isNaN(v) && type == 2)
									{
										lastObj.value += v;
									}
									
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
								<%--modify end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
										
	              			</script>
							<!--小键盘end-->
						</div>
						<div class="blank10"></div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
		if ("" != "<%=errorMsg %>")
		{			
			alertRedErrorMsg("<%=errorMsg %>");
		}
	</script>
</html>
