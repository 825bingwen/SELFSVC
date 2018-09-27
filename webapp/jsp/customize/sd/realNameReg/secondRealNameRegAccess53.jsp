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
			<s:hidden id="secondCommitNum" name="secondCommitNum"></s:hidden>
			<s:hidden name="chargeRecordPO.currMonChargeDate" id="rechargeDate1"  value="1"></s:hidden>
			<s:hidden name="chargeRecordPO.lastMonChargeDate" id="rechargeDate2" value="1"></s:hidden>
			<s:hidden name="chargeRecordPO.preLastMonChargeDate" id="rechargeDate3" value="1"></s:hidden>
			<!-- add begin by gWX301560 2015-8-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求 -->
			<s:hidden id="randomPwd" name="randomPwd"></s:hidden>
			<s:hidden id="cardNum" name="cardNum"></s:hidden>
			<!-- add end by gWX301560 2015-8-12 OR_SD_201506_971_山东_关于存量非实名制客户补登记相关系统支撑的需求 -->
			
			<!-- add begin wWX217192 2014-07-29 OR_huawei_201406_849 实名制认证 -->
			<!-- 为打印凭条准备数据 -->
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
										<p class="tit_info" align="left"><span class="bg"></span>充值记录</p>
									</div>
								</div>
								<div class="SPACE"></div>
								<div class="keyboard_box_con" id="phone_input_recharge" >
									<ul class="phone_num_list selectInput_list fl">
										<li class="on fs20 clearfix h70" id="phone_input_recharge1">
											<span class="fl lh75 ">1：</span>
											<div class="recharge_time recharge_time1 fl">
												<div class="recharge_time_title fl" id="recharge_time1_0"
													onclick="changObj('phone_input_recharge',1);showTime('recharge_time1_',0);">
													<s:property value="monthList[0]"/>
												</div>
												<div class="recharge_time_con" id="recharge_time1_con"
													style="display: none">
													<s:iterator value="monthList" status="index">
														<span style="width:100px;" id="recharge_time1_<s:property value='#index.index+1'/>"
															onclick="showTime('recharge_time1_','<s:property value="#index.index+1"/>')"><s:property/></span>
													</s:iterator>
												</div>
											</div>
											<div class="recharge_day recharge_time1 fl">
												<div class="recharge_day_title fl" id="recharge_day1_0"
													onclick="changObj('phone_input_recharge',1);showDay('recharge_day1_',0);">1日
												</div>
												<div class="recharge_time_con" id="recharge_day1_con"
													style="display: none">
												</div>
											</div>
											<div class="text1 rechargeTime fl">
												<input name="chargeRecordPO.currMonChargeAmount" type="text"
													class="rechargeTime_input relative" id="phone_input_recharge_Text1"
													onclick="changObj('phone_input_recharge',1)" maxlength="6" />
												元
											</div>
										</li>
										<li class="fs20 clearfix h70" id="phone_input_recharge2">
											<span class="fl lh75">2：</span>
											<div class="recharge_time recharge_time2 fl">
												<div class="recharge_time_title fl" id="recharge_time2_0"
													onclick="changObj('phone_input_recharge',2);showTime('recharge_time2_',0);">
													<s:property value="monthList[1]"/>
												</div>
												<div class="recharge_time_con" id="recharge_time2_con"
													style="display: none">
													<s:iterator value="monthList" status="index">
														<span style="width:100px;" id="recharge_time2_<s:property value='#index.index+1'/>"
															onclick="showTime('recharge_time2_','<s:property value="#index.index+1"/>')"><s:property/></span>
													</s:iterator>
												</div>
											</div>
											<div class="recharge_day recharge_time2 fl">
												<div class="recharge_day_title fl" id="recharge_day2_0"
													onclick="changObj('phone_input_recharge',2);showDay('recharge_day2_',0);">1日
												</div>
												<div class="recharge_time_con" id="recharge_day2_con"
													style="display: none">
												</div>
											</div>
											<div class="text1 rechargeTime fl">
												<input name="chargeRecordPO.lastMonChargeAmount" type="text"
													class="rechargeTime_input relative" id="phone_input_recharge_Text2"
													onclick="changObj('phone_input_recharge',2)" maxlength="6" />
												元
											</div>
										</li>
										<li class="fs20 clearfix h70" id="phone_input_recharge3">
											<span class="fl lh75">3：</span>
											<div class="recharge_time recharge_time3 fl">
												<div class="recharge_time_title fl" id="recharge_time3_0"
													onclick="changObj('phone_input_recharge',3);showTime('recharge_time3_',0);">
													<s:property value="monthList[2]"/>
												</div>
												<div class="recharge_time_con" id="recharge_time3_con"
													style="display: none">
													<s:iterator value="monthList" status="index">
														<span style="width:100px;" id="recharge_time3_<s:property value='#index.index+1'/>"
															onclick="showTime('recharge_time3_','<s:property value="#index.index+1"/>')"><s:property/></span>
													</s:iterator>
												</div>
											</div>
											<div class="recharge_day recharge_time3 fl">
												<div class="recharge_day_title fl" id="recharge_day3_0"
													onclick="changObj('phone_input_recharge',3);showDay('recharge_day3_',0);">1日
												</div>
												<div class="recharge_time_con" id="recharge_day3_con"
													style="display: none">
												</div>
											</div>
											<div class="text1 rechargeTime fl">
												<input name="chargeRecordPO.preLastMonChargeAmount" type="text"
													class="rechargeTime_input relative" id="phone_input_recharge_Text3"
													onclick="changObj('phone_input_recharge',3)" maxlength="6" />
												元
											</div>
										</li>
									</ul>
								
									<div class="keyboard_box_con">
										<div class="blank30"></div>
										<p class="tit_arrow ">
											<span class=" bg"></span>温馨提示：
										</p>
										<p class="p20">
											1.请在对应月份选择充值日期并输入对应充值金额
										</p>
										<p class="p20">
											2.<%=(null == chargeNote)?"充值记录验证的充值金额应不小于20元":chargeNote %>
										</p>
									</div>
								</div><div class="SPACE"></div>
							</div>

							<!--小键盘-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class=" numbox">
									<div class="blank10"></div>
									<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a>
									<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" 
										onMouseDown="this.className='func1on'" onMouseUp="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a>
									<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" 
										onMouseDown="this.className='func2on'" onMouseUp="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a>
										<a href="javascript:void(0)">.</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a href="javascript:void(0)" onClick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter"
											onMouseDown="this.className='func3on'"  onMouseUp="this.className='func3'">1</a>
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
if(null != '<s:property value="errorMsg"/>' && "" != '<s:property value="errorMsg"/>')
{
	alertRedErrorMsg('<s:property value="errorMsg"/>');
}

// 全局加载当前时间
var curDate1 = document.getElementById("recharge_time1_0").innerHTML;
var curDate2 = document.getElementById("recharge_time2_0").innerHTML;
var curDate3 = document.getElementById("recharge_time3_0").innerHTML;
dayOfMonth(curDate1, 'recharge_time1_');
dayOfMonth(curDate2, 'recharge_time2_');
dayOfMonth(curDate3, 'recharge_time3_');

// 提交标识
var submitFlag = 0;
var lastObj= document.getElementById('phone_input_recharge_Text1');
var lastObj2=document.getElementById('phone_input_recharge');
var T=1;
var O='phone_input_recharge';
var textInput=O + "_Text" + T;


//年月的切换
function  showTime(m,n)
{
	var curDate = document.getElementById(m + n).innerHTML;
	
	// 根据当前月份计算当月的天数
	dayOfMonth(curDate, m);
	
    if(m == 'recharge_time1_')
    {
  		document.getElementById('recharge_time2_con').style.display='none';
  		document.getElementById('recharge_time3_con').style.display='none';
  		document.getElementById('recharge_day1_con').style.display='none';
  		document.getElementById('recharge_day2_con').style.display='none';
  		document.getElementById('recharge_day3_con').style.display='none';
  	}
    else if(m == 'recharge_time2_')
    {
    	document.getElementById('recharge_time1_con').style.display='none';
    	document.getElementById('recharge_time3_con').style.display='none';
    	document.getElementById('recharge_day1_con').style.display='none';
  		document.getElementById('recharge_day2_con').style.display='none';
  		document.getElementById('recharge_day3_con').style.display='none';
    }
    else if(m == 'recharge_time3_')
    {
    	document.getElementById('recharge_time1_con').style.display='none';
    	document.getElementById('recharge_time2_con').style.display='none';
    	document.getElementById('recharge_day1_con').style.display='none';
  		document.getElementById('recharge_day2_con').style.display='none';
  		document.getElementById('recharge_day3_con').style.display='none';
    }
    
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
  
  		var M = m + n;
  		
  		var a=document.getElementById(M).innerHTML;
  		
  		document.getElementById(m+'0').innerHTML=a;
  		
  		var dayTemp = m.replace('time', 'day');
  		
  		document.getElementById(dayTemp + '0').innerHTML = '1日';
  	}
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
	var currMonAmount = document.getElementById("phone_input_recharge_Text1").value;
	var lastMonAmount = document.getElementById("phone_input_recharge_Text2").value;
	var preLastMonAmount = document.getElementById("phone_input_recharge_Text3").value;
	
	if("" == currMonAmount || "" == lastMonAmount || "" == preLastMonAmount)
	{
		alertRedErrorMsg("请输入充值金额！");
		return false;
	}
	
	// 金额应当为数字
	if(isNaN(currMonAmount) || isNaN(lastMonAmount) || isNaN(preLastMonAmount))
	{
		alertRedErrorMsg("请输入正确的充值金额！");
		return false;
	}
	
	// 判断金额是否不小于20
	if(currMonAmount < 20 || lastMonAmount < 20 || preLastMonAmount < 20)
	{
		alertRedErrorMsg("请输入充值金额不小于20的充值记录！");
		return false;
	}
	
	var month1 = trim(document.getElementById('recharge_time1_0').innerHTML);
	var month2 = trim(document.getElementById('recharge_time2_0').innerHTML);
	var month3 = trim(document.getElementById('recharge_time3_0').innerHTML);
	
	if(month1 == month2 || month1 == month3 || month2 == month3)
	{
		alertRedErrorMsg("请输入3个不同月份的充值记录！");
		return false;
	}
	
	var rechargeDate1 = trim(document.getElementById('recharge_time1_0').innerHTML) + trim(document.getElementById('recharge_day1_0').innerHTML);
    var rechargeDate2 = trim(document.getElementById('recharge_time2_0').innerHTML) + trim(document.getElementById('recharge_day2_0').innerHTML);
    var rechargeDate3 = trim(document.getElementById('recharge_time3_0').innerHTML) + trim(document.getElementById('recharge_day3_0').innerHTML);
    document.getElementById('rechargeDate1').value = converDate(rechargeDate1);
    document.getElementById('rechargeDate2').value = converDate(rechargeDate2);
    document.getElementById('rechargeDate3').value = converDate(rechargeDate3);
    
    return true;
}

// 提交
function doSub()
{
	if (submitFlag == 0 && checkBeforeSub())
	{
		submitFlag = 1;
		openRecWaitLoading();
		document.actform.action = "${sessionScope.basePath}realNameReg/regRealNameSecondAccess.action";
		document.actform.submit();
	}		
}

// 转换日期格式
function converDate(date)
{
  if(date.length == 10)
  {
      date = date.replace('年', '-').replace('月', '-0').replace('日', '');
  }
  else
  {
      date = date.replace('年', '-').replace('月', '-').replace('日', ''); 
  }
  return date;
}

// 选定当月具体的日期，天数的切换
function showDay(m,n)
{
	if(m == 'recharge_day1_')
    {
    	document.getElementById('recharge_time1_con').style.display='none';
  		document.getElementById('recharge_time2_con').style.display='none';
  		document.getElementById('recharge_time3_con').style.display='none';
  		document.getElementById('recharge_day2_con').style.display='none';
  		document.getElementById('recharge_day3_con').style.display='none';
  	}
    else if(m == 'recharge_day2_')
    {
    	document.getElementById('recharge_time1_con').style.display='none';
  		document.getElementById('recharge_time2_con').style.display='none';
  		document.getElementById('recharge_time3_con').style.display='none';
    	document.getElementById('recharge_day1_con').style.display='none';
    	document.getElementById('recharge_day3_con').style.display='none';
    }
    else if(m == 'recharge_day3_')
    {
    	document.getElementById('recharge_time1_con').style.display='none';
  		document.getElementById('recharge_time2_con').style.display='none';
  		document.getElementById('recharge_time3_con').style.display='none';
    	document.getElementById('recharge_day1_con').style.display='none';
    	document.getElementById('recharge_day2_con').style.display='none';
    }
    
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
  
  		var M = m + n;
  		
  		var a=document.getElementById(M).innerHTML;
  		
  		document.getElementById(m+'0').innerHTML=a;
  	}
}

// 根据当前月份展示相应的天数信息
function dayOfMonth(date, m)
{
	// 获取用户选中的年份及月份信息
	var index1 = date.indexOf('年');
	var index2 = date.indexOf('月');
	
	var year = 0;
	
	var month = 0;
	
	if (4 == index1)
	{
	    year = date.substring(0,4);
		if (6 == index2)
		{
		   month = '0' + date.substring(5,6);
		}
		else if (7 == index2)
		{
		   month = date.substring(5,7);
		}
	}
	
	// 获取当前时间
	var d = new Date();
	
	// 获取当前的年份信息
	var curYear = d.getFullYear();
	
	// 获取当前月份信息，获取当前月份信息需要+1
	var curMonth = d.getMonth() + 1;
	
	// 获取当前日期
	var dt = d.getDate();
	
	// 将当前字符串进行替换
	m = m.replace('time', 'day');
	
	var dayDiv = document.getElementById(m + "con");
	
	dayDiv.innerHTML = "";
	
	var dayList = "";
	
	// 判断用户选择的日期是否为当前年份的信息
	if(curMonth == month && curYear == year)
	{
		for(var i = 1; i <= dt; i++)
		{
			// 拼接当月的日期信息
			dayList = dayList + "<span id='" + m + i + "' " + "onclick=showDay('" + m + "'," + i + ") >"+ i + "日</span>";
		}
	}
	// 判断非当月日期的联动情况
	else
	{
		// 选择的年份是否为闰年
		if((year%4 == 0)&&((year%100 != 0)|(year%400 == 0)))
		{
			// 按照29天进行2月份信息的拼接
			if(2 == month)
			{
				for(var i = 1; i <= 29; i++)
				{
					dayList = dayList + "<span id='" + m + i + "' " + "onclick=showDay('" + m + "'," + i + ") >"+ i + "日</span>";
				}
			}
			
		}
		else
		{
			// 2月份按照28天进行拼接
			if(2 == month)
			{
				for(var i = 1; i <= 28; i++)
				{
					dayList = dayList + "<span id='" + m + i + "' " + "onclick=showDay('" + m + "'," + i + ") >"+ i + "日</span>";
				}
			}
		}
		
		// 判断当前选择的月份是否为每月包含31天
		if(1 == month || 3 == month || 5 == month || 7 == month || 8== month || 10 == month || 12 == month)
		{
			for(var i = 1; i <= 31; i++)
			{
				dayList = dayList + "<span id='" + m + i + "' " + "onclick=showDay('" + m + "'," + i + ") >"+ i + "日</span>";
			}
		}
		// 判断当前选择的月份是否为每月包含30天
		else if(4 == month || 6 == month || 9 == month || 11 == month)
		{
			for(var i = 1; i<= 30; i++)
			{
				dayList = dayList + "<span id='" + m + i + "' " + "onclick=showDay('" + m + "'," + i + ") >"+ i + "日</span>";
			}
		}
	}
	dayDiv.innerHTML = dayList;
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


</script>