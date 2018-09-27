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
<!--
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

//确认
function doSub() 
{
	var password = document.actform.password.value;
	
	if (trim(password) == "" || isNaN(password) || pangu_getStrLen(trim(password)) != 6) 
	{
		document.actform.password.focus();
		document.actform.password.select();
		MoveLast(document.actform.password, 0);
		
		alertRedErrorMsg("请输入正确的服务密码");
		
		return;
	}
	
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		var menuId = document.getElementById("curMenuId").value;

		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/userLoginWithPwd.action";
		document.actform.submit();
       }
}

function goback(curmenu) 
{
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/backForward.action";
		document.actform.submit();
	}		
}
		
		//加载
function load()
{  
	document.getElementById('password').focus();
}
//-->
</script>
	</head>
	<body scroll="no" onload="document.getElementById('password').focus();">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="userSeq" name="userSeq" value="<s:property  value='userSeq'/>"/>	
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property  value='servnumber'/>"/>	
			<input type="hidden" id="feeChargeFlag" name="feeChargeFlag" value="<s:property  value='feeChargeFlag'/>"/>
			<input type="hidden" id="commitProductFlag" name="commitProductFlag" value="1"/>	
			<input type="hidden" id="commendOID" name="commendOID" value="<s:property  value='commendOID'/>"/>
			
			<%-- 营销活动编码 --%>
            <s:hidden id="actId" name="actId"></s:hidden>
            
            <%-- 推荐类型 --%>
			<s:hidden id="recmdType" name="recmdType"></s:hidden>
            
            <%-- 回复内容 --%>
            <s:hidden id="moContent" name="moContent"></s:hidden>
            
            <%-- 用户回馈定义受理标识 --%>
            <input type="hidden" id="feedBackDefFlag" name="feedBackDefFlag" 
            	value="<s:property value='%{#parameters.feedBackDefFlag}'/>"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 hidden">
					<div class="blank30" ></div>
					
    				<div class=" pl62">
    					<p class="fs18" style="visibility:hidden;">为确保您的个人信息安全，请输入服务密码。</p>
    					<div class="blank30"></div>
      					<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
      					
      					<!--键盘+输入框+温馨提示-->
      					<div class="keyboard_wrap authentication authentication_2n clearfix">
      						<ul class="phone_num_list fl">
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          						</li>
          						<li class="clearfix pt10 hidden" style="height:auto;">
          							<p class=" fs18 fl lh30 yellow" >请输入服务密码</p>
          						</li>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          							<div class="blank30"></div>
          							<span style="font-size:18px;">服务密码：</span>
            						<input type="password"  maxlength="6" id="password" name="password" class="text1 fl relative" onfocus="MoveLast(event);" value="" />
          						</li>        
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
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
		if ("" != "<%=errormessage %>")
		{			
			alertRedErrorMsg("<%=errormessage %>");
		}
	</script>
</html>
