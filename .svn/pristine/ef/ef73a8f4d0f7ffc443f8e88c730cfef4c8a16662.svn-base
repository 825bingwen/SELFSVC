<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
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
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String selectReason = (String)request.getAttribute("selectReason");
    if (selectReason == null)
    {
    	selectReason = "";
    }
    
    // add begin g00140516 2013/02/25 R003C13L02n01 OR_NX_201302_600
    String strLeftTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_RANDOMPWD_INTERVAL);
    if (strLeftTime == null || "".equals(strLeftTime.trim()))
    {
    	strLeftTime = "10";
    }
    
    int leftTime = Integer.parseInt(strLeftTime);
    
    String nextFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_RANDOMPWD_NEXTFLAG);
    if (nextFlag == null || "".equals(nextFlag.trim()))
    {
    	nextFlag = "0";
    }
    // add end g00140516 2013/02/25 R003C13L02n01 OR_NX_201302_600
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
<!--
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
			etarget.value = backString(etarget.value);
		
			var code = document.actform.randomPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.randomPwd.focus();
				return true;
			}
		}	
	<%
	}
	else
	{
	%>	
		// 返回
		if (key == 82 || key == 220)
		{
			goback("<%=currMenuID %>");
			return;
		}		
		//更正
		<%--modify begin lWX431760 2017-01-06 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		else if (key == 8 || key == 32 || key == 66 || key ==77)
		{
			<%
			if (Constants.PROOPERORGID_HUB.equals(pageProvince))
			{
			%>
				var etarget = getEventTarget(e);
				if (etarget.type == "text" || etarget.type == "randomCodeImage") 
				{
					etarget.value = backString(etarget.value);
				}
			
				if (etarget.name == 'randomCodeImage' && etarget.value == '' )
				{
					MoveLast(document.getElementById('randomPwd'));
				}
		
				var code = document.actform.randomPwd.value;
				var randomCode = document.actform.randomCodeImage.value;
			
				if ((key == 8 || key == 32 || key == 66 || key ==77)
			 			&& pangu_getStrLen(trim(randomCode)) == 0 && pangu_getStrLen(trim(code)) == 6) 
				{
					document.forms[0].randomPwd.focus();
				
					changObj(document.forms[0].randomPwd, 1);
				
					return true;
				}

				if (pangu_getStrLen(trim(code)) == 6 && pangu_getStrLen(trim(randomCode)) == 0)
				{
					document.forms[0].randomCode.focus();

					changObj(document.forms[0].randomCodeImage, 2);

					return true;
		 		}

		 		return true;
		 	<%
			}
			else
			{
			%>
				var etarget = getEventTarget(e);
				etarget.value = backString(etarget.value);
		
				var code = document.actform.randomPwd.value;
				if (pangu_getStrLen(trim(code)) == 6)
				{
					document.actform.randomPwd.focus();
					return true;
				}
			<%
			}
			%>
		}
		<%--modify end lWX431760 2017-01-06 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
	<%
	}
	%>
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

//确认
function doSub() 
{
	var randomPwd = document.actform.randomPwd.value;
	
	if (trim(randomPwd) == "" || isNaN(randomPwd) || pangu_getStrLen(trim(randomPwd)) != 6) 
	{
		document.actform.randomPwd.focus();
		document.actform.randomPwd.select();
		
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入正确的短信密码");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入正确的短信密码";
		}
		
		return;
	}
	
	<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
	<%
	if (Constants.PROOPERORGID_HUB.equals(pageProvince))
	{
	%>
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
	<%
	}
	%>
	
	<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%> 
	
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		var menuId = document.getElementById("curMenuId").value;
		
		// modify begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX('recWaitLoading');
		// modify end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/userLoginWithRandomPwd.action";
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
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/backForward.action";
		document.actform.submit();
	}		
}
		
		//加载
function load()
{  
	document.getElementById('randomPwd').focus();
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
//-->

		// 发送短信随机码
		function sendRandomPwd()
		{
			if (document.getElementById("nextRandomPwdBtn").disabled)
			{
				return;
			}
			
			var url = "${sessionScope.basePath}login/sendRandomPwd_hub.action";
			var params = "servnumber=${sessionScope.CustomerSimpInfo.servNumber }&number=" +  + Math.random();
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
									if ("1" == "<%=popupFlag %>")
									{
										alertSuccessMsg(" 短信验证码已发送到手机，请注意查收！");
									}
									else
									{
										document.getElementById("errorMsg").innerHTML = " 短信验证码已发送到手机，请注意查收！";
									}
									
									document.getElementById("nextRandomPwdBtn").disabled = true;
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
				document.getElementById("content").innerHTML = '如需获取新短信密码，请' + leftTime + '秒后点击"再次获取"。';
			}
			else
			{
				clearInterval(leftTimeToken);
				
				document.getElementById("content").innerHTML = '如需获取新短信密码，请点击"再次获取"。';
				document.getElementById("nextRandomPwdBtn").disabled = false;
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
		
		<%--add begin lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
		function changeImage()
		{
			document.getElementById("refreshRandomCodeImage").src = "${sessionScope.basePath}login/randomCodeImage.action?pageId=" + Math.random();
		}
		<%--add end lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
</script>
	</head>
	<%
	if (Constants.PROOPERORGID_HUB.equals(pageProvince))
	{
	%>
	<body scroll="no" onload="document.getElementById('randomPwd').focus();changObj(document.forms[0].randomPwd, 1);">
	<%
	}
	else
	{
	%>
	<body scroll="no" onload="document.getElementById('randomPwd').focus();">
	<%
	}
	%>
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="selectReason" name="selectReason" value="<%=selectReason %>" />			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 hidden">
					<div class="blank30" id="errorMsg"></div>
					
    				<div class=" pl62">
    					<p class="fs18" style="visibility:hidden;">为确保您的个人信息安全，请输入短信密码。</p>
      				<%
		            if(!Constants.PROOPERORGID_HUB.equals(pageProvince))
		            {
				  	%>
						<div class="blank30"></div>				    
					<%
				    }
				    %>
      					
      					<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
      					
      					<!--键盘+输入框+温馨提示-->
      					<div class="keyboard_wrap authentication authentication_2n ">
      						<ul class="phone_num_list fl">
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          						</li>
          						
          						<span style="font-size:18px;">获取短信密码：</span>
          						
          						<li class="clearfix pt10 hidden" style="height:auto;">
          							<p class=" fs18 fl lh10 yellow">短信密码已发送到手机，请注意查收！</p>          							
          						</li>
          						<%
	          					if ("1".equals(nextFlag))
	          					{
	          					%>
          						<li class="clearfix pt10 hidden" style="height:auto;">          							
          							<p class=" fs18 fl lh10 yellow" id="content">如需获取新短信密码，请<%=leftTime %>秒后点击"再次获取"。</p>          							
          						</li>
          						
          						<div class="blank10"></div>
          						<a id="nextRandomPwdBtn" title="再次获取" href="javascript:void(0)" class="bt2"  onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';sendRandomPwd();" disabled="true">再次获取</a> 
          						<%
          						}
          						%>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          							<div class="blank10"></div>
          							<span id="redstar1" style="font-size:18px;">短信密码：</span>
          							
          			<%
		            if(!Constants.PROOPERORGID_HUB.equals(pageProvince))
		            {
				  	%>
						<br />	
						<input type="text" id="randomPwd" name="randomPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(event);"  value="" />		    
					<%
				    }
		            else
		            {
				    %>
            						<input type="text" id="randomPwd" name="randomPwd" maxlength="6" class="text1 fl relative" onclick="changObj(this,1);MoveLast(this);"  value="" />
          						</li>
          						<%--add begin lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_2">
          							<div class="blank10"></div>
          							<span id="redstar2" style="font-size:18px;">验证码：&nbsp;&nbsp;&nbsp;</span>
          							<input type="text" name="randomCodeImage" id="randomCodeImage"
										maxlength="4" class="text3 fl ralative"
										onclick="changObj(this,2);MoveLast(this);"/>
									<img border="1" 
										src="${sessionScope.basePath}login/randomCodeImage.action" width="60"
										height="45" title="点击重新获取验证码" id="refreshRandomCodeImage"
										onclick="changeImage();" style="cursor: pointer;" />
          						</li>
          			<%
		            }
          			%>
          						<%--add end lWX431760 2017-01-03 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
          						<%
          						if ("1".equals(keyFlag))
          						{
          						%>
          						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
         							<p class="p10">1. 短信密码输入完毕后，请按"确认"键提交；</p>
         							<p class="p10">2. 如需修改，请按"清除"键。</p>
         						</li>
          						<%
          						}
          						%>
       					 	</ul>
       					 	
       					 	<!--小键盘-->
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
        						<%
									if("1".equals(redStarKey))
									{
								%>
									var textContent1 = document.getElementById('redstar1').innerHTML;
									document.getElementById('redstar1').innerHTML=textContent1 + '<font color="red">*</font>';
								<%
									}
								%>
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('randomPwd');
								
								<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								<%
								if (Constants.PROOPERORGID_HUB.equals(pageProvince))
								{
								%>
									var randomCode = document.getElementById('randomCodeImage');
								<%
								}
								%>
								<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								
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
						  				
						  				<%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
						  				<%
										if (Constants.PROOPERORGID_HUB.equals(pageProvince))
										{
										%>
						  					// randomPwd输入完毕自动跳转到randomCodeImage
							  				if (pangu_getStrLen(numBoardText.value) == 6 
							  						&& pangu_getStrLen(trim(document.forms[0].randomCodeImage.value)) == 0) 
							 				{
							 					document.forms[0].randomCodeImage.focus();
							 				
							 					changObj(document.forms[0].randomCodeImage, 2);
							 				
							 					return true;
							 				}
							  			<%
										}
										%>
							  			<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>						  									   						  									   
									}					
								}
								
								 <%--add begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								function changObj(o, t)
								{
									numBoardText = o;
							
									if (t == 1)
									{
										type = 1;										
									}
									else
									{
										type = 0;
									}
								}
								<%--add end lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								
								<%--modify begin lWX431760 2017-01-04 OR_HUB_201609_640_自助终端用户登录验证方式修改 --%>
								function changValue(t, v)
								{
									<%
									if (Constants.PROOPERORGID_HUB.equals(pageProvince))
									{
									%>
										numBoardText.focus();
										numBoardText.select();
										if (t == -1)
										{
											numBoardText.value = numBoardText.value.slice(0, -1);
										}
										else if(t == -2)
										{
											numBoardText.value = ""
										}
										else if (numBoardText.value.length < 6 && !isNaN(v) && type == 1)
										{								
											numBoardText.value += v;																													
										}
										else if (numBoardText.value.length < 4 && !isNaN(v) && type == 0)
										{
											numBoardText.value += v;	
										}
									<%
									}
									else
									{
									%>
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
									<%
									}
									%>
									var r = numBoardText.createTextRange(); 
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
		
		<%
        if ("1".equals(nextFlag))
        {
        %>
		leftTimeToken = setInterval("calLeftTime()", 1000);
		<%
		}
		%>
	</script>
</html>
