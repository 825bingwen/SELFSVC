<%--
* 业务推荐营业员的手机号码维护
* 
* @remark create g00140516 2012/07/03 R003C12L07n01 OR_NX_201206_310
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>管理员密码输入页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
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
		goback();
			    
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

function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
	r.select();
}

function doSub()
{	
	var pattern = /^\d{11}$/;
	var rectel = document.getElementById("rectel").value;
	if (rectel == "" ||  !pattern.test(rectel))
	{
		if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("请输入11位的手机号码");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "请输入11位的手机号码";
		}
		
		return ;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target = "_self";
		document.actform.action="${sessionScope.basePath}rectelInfo/registerRectel.action";
		document.actform.submit();	
	}
}

function goback()
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
				
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/index.action";
		document.actform.submit();
	}
}		
</script>

</head>
	<body scroll="no" onload="document.getElementById('rectel').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					<div class=" p40">						
					<p class="fs22 mb30"></p>
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="rectelInput" >
          							<i class="lh30">1.请输入业务推荐营业员的手机号码</i>
          							<span class="pl20 fl lh75">手机号码：</span>
            						<input type="password" maxlength="11" id="rectel" name="rectel" class="text1 fl relative" onclick="changObj(this, 1);" onfocus="MoveLast(event);"/>
         						</li>         
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
	        				<script type="text/javascript">	
							var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
							var lastObj = document.getElementById('rectel');
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
								numBoardBtns[i].onmousedown = function()
								{
									this.className = 'on';
								}
								numBoardBtns[i].onmouseup = function()
								{
									changValue(0, this.innerHTML);
									this.className = '';
								}					
							}
							
							function changObj(o, t)
							{
								lastObj = o;
								document.getElementById('rectelInput').className = "on fs20 clearfix";
							}					
							
							function changValue(t, v)
							{
								if (t == -1)
								{
									lastObj.value = lastObj.value.slice(0, -1);
								}
								else if(t == -2)
								{
									lastObj.value = "";
								}
								else if (lastObj.value.length < 11 && !isNaN(v))
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
				
				<!-- 红色错误提示信息 -->
				<div class="popup_confirm" id="openWin_ErrorMsg">
					<div class="bg"></div>
					<div class="tips_title">提示：</div>
					<div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg">
						
				  	</div>
					<div class="btn_box tc mt20">
						<span class=" inline_block ">
							<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
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
			
			<%@ include file="/backinc.jsp"%>	    	
		</form>
	</body>
</html>
