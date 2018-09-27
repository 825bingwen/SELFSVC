<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- add begin lwx439898 2018-03-14 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改 --%>
<%@ page import="com.huawei.boss.common.security.RSAUtil"%>
<%-- add end lwx439898 2018-03-14 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改 --%>
<%
//add begin lwx439898 2018-03-14 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改 
RSAUtil.generateKeyPair(session, 1024);
//加密标识
String encryptFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_PASSWORD_ENCRYPT_FLAG);
//add end lwx439898 2018-03-14 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>密码更改</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<%-- add begin lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改 --%>
<script type="text/javascript"  src="${sessionScope.basePath}js/BigInt.js"></script>
<script type="text/javascript"  src="${sessionScope.basePath}js/RSA.js"></script>
<script type="text/javascript"  src="${sessionScope.basePath}js/Barrett.js"></script>	
<%-- add end lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改 --%>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script language="JavaScript" type="text/javascript">

//防止页面重复提交
var submitFlag = 0;

//密码更改提交
function doSub()
{
	var oldpassword = trim(document.actform.oldPasswd.value);
	//判断旧密码不能为空
	if(oldpassword == "" || pangu_getStrLen(oldpassword) != 6)
	{
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入旧密码！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入旧密码！";
		}
		
		changObj(document.getElementById("oldPasswd"), 1);
		return ;
	}
	
	//判断新密码不能为空
	var password = trim(document.actform.newPasswd.value);
	if(password == "" || pangu_getStrLen(password) != 6)
	{
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入新密码！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入新密码！";
		}
		
		changObj(document.getElementById("newPasswd"), 2);
		return ;
	}
	
	//确认密码不能为空
	var confirmPwd = trim(document.actform.confirmPwd.value);
	if(confirmPwd == "" || pangu_getStrLen(confirmPwd) != 6)
	{
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入确认密码！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入确认密码！";
		}
		
		changObj(document.getElementById('confirmPwd'), 3);
		return ;
	}
	
	//判断确认密码和新密码要一致
	if(password != confirmPwd)
	{	    
	    if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("新密码和确认密码不一致，请重新输入！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "新密码和确认密码不一致，请重新输入！";
		}
		
		document.actform.confirmPwd.value = "" ;
		changObj(document.getElementById('confirmPwd'), 3);
		return ;
	}
	
	//判断新密码和旧密码不能相同
	if(oldpassword == password)
	{
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("新密码和旧密码不能相同，请重新输入！");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "新密码和旧密码不能相同，请重新输入！";
		}
		document.actform.confirmPwd.value = "" ;
		document.actform.newPasswd.value = "" ;
		changObj(document.getElementById("newPasswd"), 2);
		return ;
	}
	
	// add begin cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-提示输入的新密码不能为弱密码
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		if(riskPassword(password))
		{
			if ("1" == "<%=popupFlag %>")
			{
				alertRedErrorMsg("您输入的新密码安全性低，请重新设置！");
			}
			else
			{
				document.getElementById("errorMsg").innerHTML = "您输入的新密码安全性低，请重新设置！";
			}
			
			document.actform.confirmPwd.value = "" ;
			document.actform.newPasswd.value = "" ;
			changObj(document.getElementById("newPasswd"), 2);
			return ;
		}
	<%
	}
	%>
	// add end cKF76106 20130207 NG3.3&3.5 规范符合度测试项目软件测试问题报告-提示输入的新密码不能为弱密码

	// add begin lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	//对密码进行RSA加密		
	if ("1" == <%=encryptFlag %>)
	{	
	    encryptPassword("oldPasswd","<%=(String)session.getAttribute(RSAUtil.PUBLIC_STRING)%>","<%=(String)session.getAttribute(RSAUtil.MODULUS_STRING)%>");
	    encryptPassword("newPasswd","<%=(String)session.getAttribute(RSAUtil.PUBLIC_STRING)%>","<%=(String)session.getAttribute(RSAUtil.MODULUS_STRING)%>");
	    encryptPassword("confirmPwd","<%=(String)session.getAttribute(RSAUtil.PUBLIC_STRING)%>","<%=(String)session.getAttribute(RSAUtil.MODULUS_STRING)%>");
	}
	// add end lwx439898 2018-03-05 R005C20LG2701 OR_HUB_201802_516_自助终端缴费机安全问题整改
	
	openRecWaitLoading_NX('recWaitLoading');
	document.actform.submit();
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
	  
function pwdKeyboardDown(e)
{
	var key=GetKeyCode(e);
	if(key == 77) 
	{
	   preventEvent(e);
	}  
	if(!KeyIsNumber(key))
	{
	   return false;//这句话最关键
	} 
}
document.onkeydown = pwdKeyboardDown;
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
			if (etarget.name == 'confirmPwd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('newPasswd'));
				changObj(document.getElementById("newPasswd"), 2);
			}
			if (etarget.name == 'newPasswd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('oldPasswd'));
				changObj(document.getElementById("oldPasswd"), 1);
			}
		}
		
		//密码限制为6位数字
		var pattern = /^\d{6}$/;
		
		//更正时，确认密码为空转到新密码
		if ((key == 8 || key == 32 || key == 66 )
			&& pattern.test(trim(document.getElementById("newPasswd").value))
			&& document.getElementById("confirmPwd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//更正时，新密码为空时转到旧密码
		if ((key == 8 || key == 32 || key == 66 )
			&& pattern.test(trim(document.getElementById("oldPasswd").value))
			&& document.getElementById("newPasswd").value == "")
		{
			document.getElementById('oldPasswd').focus();
			changObj(document.getElementById("oldPasswd"), 1);
			return true;
		}
		
		//旧密码输入完成后自动跳转到新密码输入框
		if(pattern.test(trim(document.getElementById("oldPasswd").value)) && document.getElementById("newPasswd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//新密码输入完成后自动跳转到确认密码输入框
		if(pattern.test(trim(document.getElementById("newPasswd").value)) && document.getElementById("confirmPwd").value == "")
		{
			changObj(document.getElementById('confirmPwd'), 3);
			document.getElementById('confirmPwd').focus();
			return true;
		}
	<%
	}
	else
	{
	%>	
		//返回
		if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
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
			if (etarget.name == 'confirmPwd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('newPasswd'));
				changObj(document.getElementById("newPasswd"), 2);
			}
			if (etarget.name == 'newPasswd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('oldPasswd'));
				changObj(document.getElementById("oldPasswd"), 1);
			}
		}
		
		//密码限制为6位数字
		var pattern = /^\d{6}$/;
		
		//更正时，确认密码为空转到新密码
		if ((key == 8 || key == 32 || key == 66 || key ==77)
			&& pattern.test(trim(document.getElementById("newPasswd").value))
			&& document.getElementById("confirmPwd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//更正时，新密码为空时转到旧密码
		if ((key == 8 || key == 32 || key == 66 || key ==77)
			&& pattern.test(trim(document.getElementById("oldPasswd").value))
			&& document.getElementById("newPasswd").value == "")
		{
			document.getElementById('oldPasswd').focus();
			changObj(document.getElementById("oldPasswd"), 1);
			return true;
		}
		
		//旧密码输入完成后自动跳转到新密码输入框
		if(pattern.test(trim(document.getElementById("oldPasswd").value)) && document.getElementById("newPasswd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//新密码输入完成后自动跳转到确认密码输入框
		if(pattern.test(trim(document.getElementById("newPasswd").value)) && document.getElementById("confirmPwd").value == "")
		{
			changObj(document.getElementById('confirmPwd'), 3);
			document.getElementById('confirmPwd').focus();
			return true;
		}
	<%
	}
	%>			

	
}
      
function trim(str) 
{
   while (str.charAt(str.length - 1)==" ") 
   {
     str = str.substring(0, str.length - 1);
   }
   while (str.charAt(0)==" ") 
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
           count = count + 2;                
       else                                  
           count = count + 1;                
   }                                         
   return count;                             
}  
	 
function MoveLast(lastObj)
{
	var r = lastObj.createTextRange(); 
	r.collapse(false); 
	r.select();
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

/*
 * 检查密码是否高危密码.
 */
function riskPassword(value)
{
  var reg = /^(123456|654321|111111|222222|333333|444444|555555|666666|777777|888888|999999|000000)$/;
  return reg.test(value);    
}
</script>
</head>
<body scroll="no" onload="document.getElementById('oldPasswd').focus();">
	<form name="actform" method="post" action="${sessionScope.basePath }baseService/changePassword.action">
		<%@ include file="/titleinc.jsp"%>
		<input type="hidden" name="additionalInfo" value="<%=additionalInfo %>" />	
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入旧密码</i>
          							<span class="pl20 fl lh75">&nbsp;&nbsp;&nbsp;旧密码：</span>
            						<input type="password" id="oldPasswd" name="oldPasswd" maxlength="6" class="text1 fl relative" onclick="MoveLast(this);changObj(this, 1);"/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 输入新密码</i>
          							<span class="pl20 fl lh75">&nbsp;&nbsp;&nbsp;新密码：</span>
            						<input type="password" id="newPasswd" name="newPasswd" maxlength="6" class="text1 fl relative" onclick="MoveLast(this);changObj(this, 2);"/>
         						</li>
         						<li class="fs20 clearfix" id="phone_input_3">
          							<i class="lh30">3. 输入确认密码</i>
          							<span class="pl20 fl lh75">确认密码：</span>
            						<input type="password" id="confirmPwd" name="confirmPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(this);changObj(this, 3);"/>
         						</li>
        					</ul>
        					
        					<!--小键盘-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a title="1" href="javascript:void(0)">1</a><a title="2" href="javascript:void(0)">2</a><a title="3" href="javascript:void(0)">3</a> <a title="退格" href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a title="4" href="javascript:void(0)">4</a><a title="5" href="javascript:void(0)">5</a><a title="6" href="javascript:void(0)">6</a> <a title="清除" href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a> <a title="x" href="javascript:void(0)">x</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a title="确认" href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('oldPasswd');
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
							  			
							  			//密码限制为6位数字
										var pattern = /^\d{6}$/;
										
							  			//旧密码输入完成后自动跳转到新密码输入框
										if (pattern.test(trim(lastObj.value)) && type == 1
												&& document.getElementById('newPasswd').value == "")
										{
											document.getElementById('newPasswd').focus();
											changObj(document.getElementById("newPasswd"), 2);
											return true;
										}
										
										//新密码输入完成后自动跳转到确认密码输入框
										if (pattern.test(trim(lastObj.value)) && type == 2
												&& document.getElementById('confirmPwd').value == "")
										{
											changObj(document.getElementById('confirmPwd'), 3);
											document.getElementById('confirmPwd').focus();
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
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if(t == 2)
									{
										type = 2;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else
									{
										type = 3;
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
										lastObj.value = ""
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 1)
									{								
										lastObj.value += v;								
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 2)
									{								
										lastObj.value += v;								
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 3)
									{								
										lastObj.value += v;								
									}
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
	              			</script>
	        				<!--小键盘end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
		<%@ include file="/backinc.jsp"%>
		</div>
	</form>
</body>
</html>
</html>
