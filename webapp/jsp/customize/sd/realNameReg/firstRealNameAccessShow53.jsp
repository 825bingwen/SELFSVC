<%@ page contentType="text/html; charset=GBK" import="java.util.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	// 是否使用弹出框
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
	 String strLeftTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_RANDOMPWD_INTERVAL);
    if (strLeftTime == null || "".equals(strLeftTime.trim()))
    {
    	strLeftTime = "10";
    }
    
    int leftTime = Integer.parseInt(strLeftTime);
    
     String errormessage = (String)request.getAttribute("errormessage");
    if (errormessage == null)
    {
    	errormessage = "";
    }
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<s:hidden id="selectMethod" name="selectMethod" value="selectRandomPwd"></s:hidden>
			<s:hidden id="firstCommitNum" name="firstCommitNum" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					<div class=" p40">
						<p class="fs22 mb30"></p>
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<div class="keyboard_box">
								<ul class="phone_num_list selectInput_list fl"  id="phone_input_nu">
									<li class="on fs20 clearfix" id="phone_input_nu1">
										<span class="pl20 fl lh75 textSelect_title">服务号码：</span>
										<input type="text" class="text1 fl relative" id="phone_input_nu_Text1" name="servnumber"
											onclick="changObj('phone_input_nu',1)" maxlength="11" value="${sessionScope.CustomerSimpInfo.servNumber }" />
									</li>
								</ul>
								<div class="phone_num_list selectInput_list fl">
									<div class="fs20 textSelect clearfix ">
										<span class="pl20 textSelect_title fl">认证方式：</span>
										<div class="text1 resultSelect fl" id="check0" onClick="showSelect('check',0)">
											短信验证
										</div>
										<div class="textSelect_con" id="checkcon" style="display: none">
											<span id="check1" onClick="showSelect('check',1)">短信验证</span>
											<span id="check2" onClick="showSelect('check',2)">服务密码</span>
											<span id="check3" onclick="showSelect('check',3)">SIM卡</span>
										</div>
									</div>
								</div>
								<div class="keyboard_box_con" id="phone_input_msg">
									<ul class="phone_num_list selectInput_list fl">
										<li class="fs20 clearfix" id="phone_input_msg1">
											<span class="pl20 fl lh75 textSelect_title">短信验证码：</span>
											<input type="password" class="text1 fl relative" name="randomPwd" id="phone_input_msg_Text1"
												onclick="changObj('phone_input_msg',1)"  maxlength="6" />
											<a href="javascript:void(0)" id="nextRandomPwdBtn" class="msg_btn relative" onmouseup="sendRandomPwd();">获取验证码</a>
										</li>
									</ul>
									<div class="keyboard_box_con">
										<div class="blank30"></div>
										<p class="tit_arrow ">
											<span class=" bg"></span>温馨提示：
										</p>
										<p class="p20">
											1.您也可通过终端的金属键盘输入
										</p>
										<p class="p20">
											2.请输入六位短信验证码
										</p>
									</div>
								</div>
								<div class="keyboard_box_con" id="phone_input_pass"
									style="display: none">
									<ul class="phone_num_list selectInput_list fl">
										<li class="fs20 clearfix" id="phone_input_pass1">
											<span class="pl20 fl lh75 textSelect_title">服务密码：</span>
											<input type="password" class="text1 fl relative" name="password" id="phone_input_pass_Text1"
												onclick="changObj('phone_input_pass',1)" maxlength="6" />
										</li>
									</ul>
									<div class="keyboard_box_con">
										<div class="blank30"></div>
										<p class="tit_arrow ">
											<span class=" bg"></span>温馨提示：
										</p>
										<p class="p20">
											1.您也可通过终端的金属键盘输入
										</p>
										<p class="p20">
											2.请输入六位服务密码
										</p>
									</div>
								</div>
								
								<div class="keyboard_box_con" id="phone_input_sim"
									style="display: none">
									<ul class="phone_num_list selectInput_list fl">
										<li class="fs20  clearfix" id="phone_input_sim1">
											<span class="pl20 fl lh75 textSelect_title">SIM卡：</span>
											<input name="cardNum" type="text" class="text1 simText fl relative"
												id="phone_input_sim_Text1"
												onclick="changObj('phone_input_sim',1)" maxlength="20" />
										</li>
									</ul>
									<div class="keyboard_box_con">
										<div class="blank30"></div>
										<p class="tit_arrow ">
											<span class=" bg"></span>温馨提示：
										</p>
										<p class="p20">
											1.您也可通过终端的金属键盘输入
										</p>
										<p class="p20">
											2.请填写SIM卡卡号信息
										</p>
									</div>
								</div>
							</div>
							
							<!--小键盘-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class="numBoard_tabs" id="numBoard_tabs"
									style="display: none">
									<span onclick="menuChange('nu',0)" class="menuOn relative">数字</span>
									<span onclick="menuChange('en',1)" class="relative">字母</span>
								</div>
								<div class="numbox" id="numBoard_nu">
									<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a>
									<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'"
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
										<a id="pointOrX" href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a
											href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter"
											onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a>
									</div>
									<div class="blank10"></div>
								</div>
								<div class="numbox" id="numBoard_en" style="display: none">
									<a href="javascript:void(0)">A</a><a href="javascript:void(0)">B</a><a href="javascript:void(0)">C</a>
									<a href="javascript:void(0)">D</a><a href="javascript:void(0)">E</a><a href="javascript:void(0)">F</a>
									<div class="clear"></div>
									<a href="javascript:void(0)">G</a><a href="javascript:void(0)">H</a><a href="javascript:void(0)">I</a>
									<a href="javascript:void(0)">J</a><a href="javascript:void(0)">K</a>
									<a href="javascript:void(0)" class="func1 en" name="functionkey" id="numBoardBack"
										onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a href="javascript:void(0)">L</a><a href="javascript:void(0)">M</a><a href="javascript:void(0)">N</a>
									<a href="javascript:void(0)">O</a><a href="javascript:void(0)">P</a>
									<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" 
										onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a href="javascript:void(0)">Q</a><a href="javascript:void(0)">R</a><a href="javascript:void(0)">S</a>
										<a href="javascript:void(0)">T</a><a href="javascript:void(0)">U</a>
										<div class="clear"></div>
										<a href="javascript:void(0)">V</a><a href="javascript:void(0)">W</a><a href="javascript:void(0)">X</a>
										<a href="javascript:void(0)">Y</a><a href="javascript:void(0)">Z</a>
									</div>
									<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter"
										onmousedown="this.className='func3on'" onmouseup="this.className='func3'"></a>
									<div class="blank10"></div>
								</div>
							</div>
						</div>
						<!--keyboard_wrap end-->
						<div class="blank10"></div>
					</div>
				</div>
				
				<%-- 红色错误提示信息 --%>
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
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script>
// 弹出错误提示框
if(null != '<s:property value="errorMsg"/>' && "" != '<s:property value="errorMsg"/>')
{
	alertRedErrorMsg('<s:property value="errorMsg"/>');
}

// 提交标识
var submitFlag = 0;
var lastObj= document.getElementById('phone_input_nu_Text1');
var lastObj2=document.getElementById('phone_input_nu');
var  T=1;
var O='phone_input_nu';
var O2='phone_input_msg';
var textInput=O + "_Text" + 1;
lastObj.focus();

//验证方式切换
function showSelect(m,n)
{
	// 如果点击下拉框，则展示下拉列表
	if(n==0)
	{
		if(document.getElementById(m+'con').style.display=='')
	    {
	    	document.getElementById(m+'con').style.display="none";
	    }
	    else
	    {
	    	document.getElementById(m+'con').style.display="";
	    }
    }
    else
    {
    	document.getElementById(m+'con').style.display="none";
    	
    	// 选择的id
        var M=m + n;
        var a=document.getElementById(M).innerHTML;
        document.getElementById(m+'0').innerHTML=a;
        if(M=="check1")
        {
	        document.getElementById('phone_input_msg').style.display='';
		    document.getElementById('phone_input_pass').style.display='none';
		    document.getElementById('phone_input_sim').style.display='none';
		    O='phone_input_msg';
		    O2='phone_input_msg';
		    lastObj= document.getElementById(textInput);
		    lastObj2=document.getElementById(O);
		    changObj(O,1);
		    document.getElementById("selectMethod").value="selectRandomPwd";
    	}
        else if(M=="check2")
        {
			document.getElementById('phone_input_msg').style.display='none';
			document.getElementById('phone_input_pass').style.display='';
			document.getElementById('phone_input_sim').style.display='none';
			O='phone_input_pass';
			O2='phone_input_pass';
			lastObj= document.getElementById(textInput);
			lastObj2=document.getElementById(O);
			changObj(O,1);
			document.getElementById("selectMethod").value="selectServerPwd";
	   }
	   else if(M=="check3")
	   {
	        document.getElementById('phone_input_msg').style.display='none';
			document.getElementById('phone_input_pass').style.display='none';
			document.getElementById('phone_input_sim').style.display='';
			O='phone_input_sim';
			o2='phone_input_sim';
			lastObj= document.getElementById(textInput);
			lastObj2=document.getElementById(O);
			changObj(O,1);
			document.getElementById("selectMethod").value="selectSimNo";
	   }
	} 
}

//小键盘数字、字母切换
function menuChange(menuID,num)
{
	var menuTAB=document.getElementById('numBoard_tabs').getElementsByTagName('span');
	for(i=0;i<menuTAB.length;i++)
	{ 
		menuTAB[i].className="relative";
	}
	menuTAB[num].className="menuOn relative";
	document.getElementById('numBoard_nu').style.display="none";
	document.getElementById('numBoard_en').style.display="none";
	document.getElementById('numBoard_'+ menuID).style.display="";
}

//小键盘 
var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('a');

for(i=0;i<numBoardBtns.length;i++)
{
    if(!numBoardBtns[i].className) numBoardBtns[i].className='';
    if(numBoardBtns[i].name=='functionkey') continue ;  
 
	numBoardBtns[i].onmousedown=function()
	{
	    this.className+='on';
	}
	
	numBoardBtns[i].onmouseup=function()
	{
		changValue(0,this.innerHTML)
	    var fullClass=this.className;
	    this.className=fullClass.slice(0,fullClass.indexOf('on'));
	}
}
					
function changObj(o,t)
{
    menuTAB=document.getElementById('numBoard_tabs').getElementsByTagName('span');
    var ID = o + t,ID2=o,ID3= o + "_Text" + t;
	var phone_input_li=document.getElementById(ID2).getElementsByTagName('li');
    for(i=0;i<phone_input_li.length;i++)
    {
	    phone_input_li[i].className="fs20 clearfix";
	}
	document.getElementById(ID).className = "on fs20 clearfix";
	
	if(o!='phone_input_nu')
	{
		document.getElementById('phone_input_nu').getElementsByTagName('li')[0].className="fs20 clearfix";
	}
	else
	{
		document.getElementById('phone_input_msg').getElementsByTagName('li')[0].className="fs20 clearfix";
		document.getElementById('phone_input_pass').getElementsByTagName('li')[0].className="fs20 clearfix";
		document.getElementById('phone_input_sim').getElementsByTagName('li')[0].className="fs20 clearfix";
	}
		
	lastObj=document.getElementById(ID3);
	lastObj2=document.getElementById(o);
	T=t;
	O=o;
    if(O=='phone_input_sim')
    {
        document.getElementById('numBoard_tabs').style.display="";
    }
    else
    {
	    document.getElementById('numBoard_nu').style.display="";
	    document.getElementById('numBoard_en').style.display="none";
	    document.getElementById('numBoard_tabs').style.display="none";
	    menuTAB[0].className="menuOn relative";menuTAB[1].className="relative";
    }
}

function changValue(t,v)
{
	if(t==-1)
	{
	lastObj.value = lastObj.value.slice(0,-1);
	}
	else if(t==-2)
	{
	lastObj.value = ""
	}
	else
	{
		if(lastObj.value.length<lastObj.maxLength)
		{
			lastObj.value +=v;
		}
	}					
}
					
// 提示信息显示
function showMessage(message)
{
	if ("1" == "<%=popupFlag %>")
	{
		alertRedErrorMsg(message);
		return false;
	}
	else
	{
		document.getElementById("errorMsg").innerHTML = message;
		return false;
	}
}


// 发送短信随机码
var sendingFlag = false;
function sendRandomPwd()
{
	// 短信随机码已发送
	if (document.getElementById("nextRandomPwdBtn").disabled)
	{
		return;
	}
	/*
	if (sendingFlag)
	{
		return;
	}*/
	
//sendingFlag = true;

	//对手机号码进行判断
	var pattern = /^\d{11}$/;
	var servnumber = document.getElementById("phone_input_nu_Text1").value;
	if (servnumber == "" || !pattern.test(servnumber))
	{
		showMessage("请输入正确的手机号码!");
		sendingFlag = false;
		return;
	}
	var url = "${sessionScope.basePath}realNameReg/sendRandomPwd.action";
	var params = "servnumber=" + servnumber;
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
		sendingFlag = false;
		var resXml = this.req.responseText;
			if ('' != resXml)
			{									
				document.getElementById("errorMsg").innerHTML = resXml;
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

// 短信验证码发送剩余时间计算
function calLeftTime()
{
	if (leftTime <= 0)
	{
		return;
	}
	
	leftTime = leftTime - 1;
	
	if (leftTime > 0)
	{
		document.getElementById("errorMsg").innerHTML = ' 短信验证码已发送到手机，请注意查收！如需获取新短信验证码，请' + leftTime + '秒后点击"点击获取"。';
		//changeMSG(document.getElementById("nextRandomPwdBtn"));
	}
	else
	{
		clearInterval(leftTimeToken);
		
		document.getElementById("errorMsg").innerHTML = ' 短信验证码已发送到手机，请注意查收！如需获取新短信验证码，请点击"点击获取"。';
		document.getElementById("nextRandomPwdBtn").disabled = false;
	}
}

// 提交前验证
function checkBeforeSub()
{
	// 手机号码验证
	var pattern = /^\d{11}$/;
	var servnumber = document.getElementById("phone_input_nu_Text1").value;
	if (servnumber == "" || !pattern.test(servnumber))
	{
		showMessage("请输入正确的手机号码");
		return false;
	}
	
	// 认证方式
	var selectMethod =  document.getElementById("selectMethod").value;
	var patternPwd = /^\d{6}$/;
	
	// 短信随机码验证必须输入随机验证码
	if("selectRandomPwd" == selectMethod)
	{
		var randomPwd = document.getElementById("phone_input_msg_Text1").value;
		if (randomPwd == "" || !patternPwd.test(randomPwd))
		{
			showMessage("请输入正确的随机短信验证码");
			return false;
		}
	}
	
	// 服务密码验证必须输入服务密码
	if("selectServerPwd" == selectMethod)
	{
		var password = document.getElementById("phone_input_pass_Text1").value;
		if (password == "" || !patternPwd.test(password))
		{
			showMessage("请输入正确的服务密码");
			return false;
		}
	}
	
	// SIM卡验证，验证SIM卡号
	if("selectSimNo" == selectMethod)
	{
		var cardNum = trim(document.getElementById("phone_input_sim_Text1").value);
		if (cardNum == "")   
		{
			showMessage("请输入正确的卡号！");
			return false;
		}
	}
	return true;
}

alertRedErrorMsg = function(content)
{
	document.getElementById('winText_ErrorMsg').innerHTML = content;
	wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
};

// 提交实名制第一步验证
function doSub()
{
	// 如果已经点击了验证或者提交前得验证不通过，不再执行任何操作
	if (submitFlag == 0 && checkBeforeSub())
	{
		submitFlag = 1;
		openRecWaitLoading();
		document.actform.action = "${sessionScope.basePath}realNameReg/regRealNameFirstAccess.action";
		document.actform.submit();
	}
}

// 去除空格
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

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

</script>

