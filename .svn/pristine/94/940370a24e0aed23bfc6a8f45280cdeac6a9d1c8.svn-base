<%@ page contentType="text/html; charset=GBK" import="java.util.*"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	// 是否使用弹出框
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    // 通话记录提示信息
    String callNote = (String) PublicCache.getInstance().getCachedData(Constants.SH_REALNAMEREG_CALLRECORD_NOTE);
	String chargeNote =  (String) PublicCache.getInstance().getCachedData(Constants.SH_REALNAMEREG_CHARGE_NOTE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<s:hidden name="servnumber"></s:hidden>
			<s:hidden id="thirdCommitNum" name="thirdCommitNum"></s:hidden>
			<!-- add begin by gWX301560 2015-8-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求 -->
			<s:hidden id="randomPwd" name="randomPwd"></s:hidden>
			<s:hidden id="cardNum" name="cardNum"></s:hidden>
			<s:hidden id="chargeRecord" name="chargeRecord"></s:hidden>
			<!-- add end by gWX301560 2015-8-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求 -->
			<!-- add begin wWX217192 2014-07-29 OR_huawei_201406_849 实名制认证 -->
			<!-- 为打印凭条准备数据 -->
			<input type="hidden" name="chargeRecordPO.currMonChargeDate" id="currMonChargeDate" value="<s:property value='chargeRecordPO.currMonChargeDate' />"/>
			<input type="hidden" name="chargeRecordPO.currMonChargeAmount" id="currMonChargeAmount" value="<s:property value='chargeRecordPO.currMonChargeAmount' />" />
			<input type="hidden" name="chargeRecordPO.lastMonChargeDate" id="lastMonChargeDate" value="<s:property value='chargeRecordPO.lastMonChargeDate' />" />
			<input type="hidden" name="chargeRecordPO.lastMonChargeAmount" id="lastMonChargeAmount" value="<s:property value='chargeRecordPO.lastMonChargeAmount' />" />
			<input type="hidden" name="chargeRecordPO.preLastMonChargeDate" id="preLastMonChargeDate" value="<s:property value='chargeRecordPO.preLastMonChargeDate' />" />
			<input type="hidden" name="chargeRecordPO.preLastMonChargeAmount" id="preLastMonChargeAmount" value="<s:property value='chargeRecordPO.preLastMonChargeAmount' />" />
			<s:hidden id="selectMethod" name="selectMethod" />
			<!-- add end wWX217192 2014-07-29 OR_huawei_201406_849 实名制认证 -->
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="blank30"></div>
				<div class="b966">
					<div class="blank30"></div>
					<div class=" p40">
						<p class="fs22 mb30"></p>
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<div class="keyboard_box">
								<div class="phone_num_list selectInput_list fl">
									<div class="fs20 textSelect clearfix ">
										<p class="tit_info" align="left"><span class="bg"></span>通话记录</p>
									</div>
								</div>
								<div class="keyboard_box_con" id="phone_input_call">
									<ul class="phone_num_list selectInput_list fl">
										<li class="on fs20  clearfix h70" id="phone_input_call1">
											<span class="pl20 fl lh75 textSelect_title">呼叫号码1：</span>
											<input type="text" class="text1 fl relative"  name="calledNum" id="phone_input_call_Text1" 
											      onclick="changObj('phone_input_call',1)" maxlength="30" />
										</li>
										<li class="fs20  clearfix h70" id="phone_input_call2">
											<span class="pl20 fl lh75 textSelect_title">呼叫号码2：</span>
											<input type="text" class="text1 fl relative" name="calledNum" id="phone_input_call_Text2"
												onclick="changObj('phone_input_call',2)" maxlength="30" />
										</li>
										<li class="fs20  clearfix h70" id="phone_input_call3">
											<span class="pl20 fl lh75 textSelect_title">呼叫号码3：</span>
											<input type="text" class="text1 fl relative" name="calledNum" id="phone_input_call_Text3"
												onclick="changObj('phone_input_call',3)" maxlength="30" />
										</li>
									</ul>
									<div class="keyboard_box_con">
										<div class="blank30"></div>
										<p class="tit_arrow ">
											<span class=" bg"></span>温馨提示：
										</p>
										<p class="p20">
											1.请输入三个您呼叫过的手机号码
										</p>
										<p class="p20">
											2.<%=(null == callNote)?"所有呼叫号码都须在30天以外，90天以内有通话记录":callNote %>
										</p>
									</div>
								</div>
							</div>

							<!--小键盘-->
							<div class="numboard numboard_big fl" id="numBoard">
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
							</div>
						</div>
					</div>
				</div>
				<!-- 红色错误提示信息 -->
				<div class="popup_confirm" id="openWin_ErrorMsg">
					<div class="bg"></div>
					<div class="tips_title">
						提示：
					</div>
					<div class="fs24 red pl55 pr30 pt40 line_height_12 h200"
						id="winText_ErrorMsg">

					</div>
					<div class="btn_box tc mt20">
						<span class=" inline_block "> <a class="btn_bg_146"
							href="javascript:void(0);"
							onmousedown="this.className='key_down'"
							onmouseup="this.className='btn_bg_146';wiWindow.close();">确定</a>
						</span>
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">

<%-- modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单  --%>
saveTelNum();
<%-- modify end by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单  --%>

if(null != '<s:property value="errorMsg"/>' && "" != '<s:property value="errorMsg"/>')
{
	alertRedErrorMsg('<s:property value="errorMsg"/>');
}

// 提交标识
var submitFlag = 0;
var lastObj= document.getElementById('phone_input_call_Text1');
var lastObj2=document.getElementById('phone_input_call');
var T=1;
var O='phone_input_sim';
var textInput=O + "_Text" + T;

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
    var ID = o + t,ID2=o,ID3= o + "_Text" + t;
	var phone_input_li=document.getElementById(ID2).getElementsByTagName('li');
    for(i=0;i<phone_input_li.length;i++)
    {
    	phone_input_li[i].className="fs20 clearfix";
	}
	
	document.getElementById(ID).className = "on fs20 clearfix";	
	lastObj=document.getElementById(ID3);
	lastObj2=document.getElementById(o);
	T=t;
	O=o;
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

// 错误提示框
alertRedErrorMsg = function(content)
{
	document.getElementById('winText_ErrorMsg').innerHTML = content;
	wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
};

// 提交前验证
function checkBeforeSub()
{
	var pattern = /^\d{11}$/;
	var calledNum1 = document.getElementById("phone_input_call_Text1").value;
	var calledNum2 = document.getElementById("phone_input_call_Text2").value;
	var calledNum3 = document.getElementById("phone_input_call_Text3").value;
	
	// 三个呼叫号码不能为空，最大为30位
	if("" == calledNum1 || "" == calledNum2 || "" == calledNum3 ||
	isNaN(calledNum1) || isNaN(calledNum2) || isNaN(calledNum3))
	{
		alertRedErrorMsg("请输入正确的呼叫号码！");
		return false;
	}
	
	// 三个呼叫号码各不相同
	if(calledNum1 == calledNum2 || calledNum2 == calledNum3 || calledNum1 == calledNum3)
	{
		alertRedErrorMsg("呼叫号码须为三个不同的电话号码！");
		return false;
	}
	return true;
}

// 提交
function doSub()
{
	if (submitFlag == 0 && checkBeforeSub())
	{
		submitFlag = 1;
		openRecWaitLoading();
		document.actform.action = "${sessionScope.basePath}realNameReg/regRealNameThirdAccess.action";
		document.actform.submit();
	}		
}

//上一页
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

<%-- modify begin by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单  --%>
// 将通话记录号码显示在输入框中
function saveTelNum()
{
	if (null != '<s:property value="reccceptNum"/>' && "" != '<s:property value="reccceptNum"/>')
	{
		// 将通话记录号码传递到页面中
		document.getElementById("phone_input_call_Text1").value = '<s:property value="reccceptNum[0]"/>';
		document.getElementById("phone_input_call_Text2").value = '<s:property value="reccceptNum[1]"/>';
		document.getElementById("phone_input_call_Text3").value = '<s:property value="reccceptNum[2]"/>';
	}
}
<%-- modify end by qWX279398 2015-10-27 OR_SD_201509_150_山东_关于优化客户身份认证菜单  --%>

</script>