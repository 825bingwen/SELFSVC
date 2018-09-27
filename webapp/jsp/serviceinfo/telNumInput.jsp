<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

document.onkeydown = keyDown;
function keyDown(e) 
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
  	//如果输入的字符是在0-9之间，或者是backspace、DEL键
	var tel = document.forms[0].servnumber.value;
	
	if (KeyCode > 47 && KeyCode < 58)
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
		
		var tel = document.forms[0].servnumber.value;
	
		if (key == 8 || key == 32 || key == 66 ) 
		{
			var etarget = getEventTarget(e);
			if (etarget.type == "text" || etarget.type == "password") 
			{
				etarget.value = backString(etarget.value);
			}
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
			goback("<s:property value='curMenuId' />") 
			return;
		}
		var tel = document.forms[0].servnumber.value;
	
		if (key == 8 || key == 32 || key == 66 || key == 77) 
		{
			var etarget = getEventTarget(e);
			if (etarget.type == "text" || etarget.type == "password") 
			{
				etarget.value = backString(etarget.value);
			}
		}			
	
		if (key == 77 && pangu_getStrLen(trim(tel)) == 11) 
		{
			document.forms[0].servnumber.focus();
			return true;
		}
		
		return true;
	<%
	}
	%>
	
}		

function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
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
	
	openRecWaitLoading_NX();
	
	document.actform.target = "_self";
	document.actform.action="userRegion.action?qryServnumber="+telNumber;
	document.actform.submit();
}		

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
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
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();		
	}			
}
</script>
	</head>
	<body scroll="no" onload="document.getElementById('servnumber').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="blank50">
				<%-- 宁夏本应该使用按键0返回至父菜单，但是此页面是输入页面，0是可以正常输入的数字。所以此处不支持使用按键0返回至父菜单 --%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				</div>
				
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入手机号码</i>
          							<span id="redstar1" class="pl20 fl lh75">手机号码：</span>
            						<input type="text" maxlength="11" id="servnumber" name="servnumber" class="text1 fl relative" onclick="MoveLast(event);"/>
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
	            					<a href="javascript:void(0)">1</a>
	            					<a href="javascript:void(0)">2</a>
	            					<a href="javascript:void(0)">3</a> 
	            					<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a href="javascript:void(0)">4</a>
	            					<a href="javascript:void(0)">5</a>
	            					<a href="javascript:void(0)">6</a> 
	            					<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            					<a href="javascript:void(0)">7</a>
	            					<a href="javascript:void(0)">8</a>
	            					<a href="javascript:void(0)">9</a> 
	            					<a href="javascript:void(0)">x</a>
	            					<a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            					<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
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
									
									numBoardBtns[i].onclick = function(){
									
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							   
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
									//else if(lastObj.value.length < 6 && !isNaN(v) && type == 0)
									//{
									//	lastObj.value += v;
									//}
									//else
									//{
									//	type = 0;
									//}
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
		</form>
	</body>
</html>
