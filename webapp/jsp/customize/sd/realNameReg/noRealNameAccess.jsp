<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.customize.sd.selfsvc.realNameReg.action.NoRealNameRegAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	String errorMsg = (String)request.getAttribute("errormessage");
	if(null == errorMsg)
	{
		errorMsg = "";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
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
	</head>

	<body>
		<form id="actform" name="actform" method="post">
			<!-- 手机号码 -->
			<s:hidden id="telNumber" name="telNumber"></s:hidden>

			<!-- 通话记录 -->
			<s:hidden id="callNums" name="callNums"></s:hidden>

			<!-- 充值日期 -->
			<input type="hidden" id="rechargeDate1" name="chargeRecordPO.currMonChargeDate" value="" />
			<input type="hidden" id="rechargeDate2" name="chargeRecordPO.lastMonChargeDate" value="" />
			<input type="hidden" id="rechargeDate3" name="chargeRecordPO.preLastMonChargeDate" value="" />
			
			<s:hidden id="accessName" name="accessName" />
			<s:hidden name="commitNum"></s:hidden>
			
			<%@ include file="/titleinc.jsp"%>

			<!-- <div class="main" id="main"> -->
			<div style="margin-left: 135px;">
				<%@ include file="/customer.jsp"%>
			</div>

			<div class="b966">

				<div class="blank30">
					<h1 style="margin-top:20px;">
						<span style="top:-2px;"></span>非实名制认证
					</h1>
				</div>
				<div class=" p40">
					<p class="fs22 mb30"></p>
					<!--键盘+输入框+温馨提示-->
					<div class="keyboard_wrap clearfix">
						<div class="keyboard_box">
							<div class="phone_num_list selectInput_list fl">
								<div class="fs20 textSelect clearfix ">
									<span class="pl20 textSelect_title fl lh75">认证方式：</span>
									<div class="text1 resultSelect fl" id="check0"
										onclick="showSelect('check',0)">
										短信验证
									</div>
									<div class="textSelect_con" id="checkcon" style="display: none">
										<span id="check1" onclick="showSelect('check',1)">短信验证</span><span
											id="check2" onclick="showSelect('check',2)">服务密码</span><span
											id="check3" onclick="showSelect('check',3)">通话记录</span><span
											id="check4" onclick="showSelect('check',4)">充值记录</span><span
											id="check5" onclick="showSelect('check',5)">SIM卡</span>
									</div>
								</div>
							</div>
							<div class="keyboard_box_con" id="phone_input_msg">
								<ul class="phone_num_list selectInput_list fl">
									<li class="on fs20 clearfix" id="phone_input_msg1">
										<span class="pl20 fl lh75 textSelect_title">短信验证码：</span>
										<input name="randomPwd" type="password" class="text1 fl relative"
											id="phone_input_msg_Text1"
											onclick="changObj('phone_input_msg',1)" maxlength="6" />
										<a href="javascript:void(0)" id="msg_btn" class="msg_btn">获取验证码</a>
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
									<li class="on fs20 clearfix h70" id="phone_input_pass1">
										<span class="pl20 fl lh75 textSelect_title">服务密码：</span>
										<input name="servPasswd" type="password" class="text1 fl relative"
											id="phone_input_pass_Text1"
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
							<div class="keyboard_box_con" id="phone_input_call"
								style="display: none">
								<ul class="phone_num_list selectInput_list fl">
									<li class="on fs20  clearfix h70" id="phone_input_call1">
										<span class="pl20 fl lh75 textSelect_title">呼叫号码1：</span>
										<input type="text" class="text1 fl relative"
											id="phone_input_call_Text1"
											onclick="changObj('phone_input_call',1)" maxlength="30" />
									</li>
									<li class="fs20  clearfix h70" id="phone_input_call2">
										<span class="pl20 fl lh75 textSelect_title">呼叫号码2：</span>
										<input type="text" class="text1 fl relative"
											id="phone_input_call_Text2"
											onclick="changObj('phone_input_call',2)" maxlength="30" />
									</li>
									<li class="fs20  clearfix h70" id="phone_input_call3">
										<span class="pl20 fl lh75 textSelect_title">呼叫号码3：</span>
										<input type="text" class="text1 fl relative"
											id="phone_input_call_Text3"
											onclick="changObj('phone_input_call',3)" maxlength="30" />
									</li>
								</ul>
								<div class="keyboard_box_con">
									<div class="blank30"></div>
									<p class="tit_arrow ">
										<span class=" bg"></span>温馨提示：
									</p>
									<p class="p20">
										1.请输入三个不同的您呼叫过的手机号码
									</p>
									<p class="p20">
										2.请提供一个月以上，三个月以内的主叫通话号码
									</p>
								</div>
							</div>
							<div class="keyboard_box_con" id="phone_input_recharge"
								style="display: none">
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
										2.充值记录验证的充值金额应不小于20元
									</p>
								</div>

							</div><div class="SPACE"></div>
							<div class="keyboard_box_con" id="phone_input_sim"
								style="display: none">
								<ul class="phone_num_list selectInput_list fl">
									<li class="on fs20  clearfix h70" id="phone_input_sim1">=<span class="pl20 fl lh75 textSelect_title">SIM卡：</span>
										<input name="simCardNo" type="text" class="text1 simText fl relative"
											id="phone_input_sim_Text1"
											onclick="changObj('phone_input_sim',1)" maxlength="20" />
									<br /></li>
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
									<a href="javascript:void(0)" onclick="btnChange();return false;" class="func3" name="functionkey" id="numBoardEnter"
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
								<a href="javascript:void(0)" onclick="btnChange();return false;" class="func3" name="functionkey" id="numBoardEnter"
									onmousedown="this.className='func3on'" onmouseup="this.className='func3'"></a>
								<div class="blank10"></div>
							</div>
						</div>
		<script>
		
		if("" != '<%=errorMsg %>')
		{
			alertRedErrorMsg('<%=errorMsg %>');
		}
		
		// 全局加载当前时间
		var curDate1 = document.getElementById("recharge_time1_0").innerHTML;
		var curDate2 = document.getElementById("recharge_time2_0").innerHTML;
		var curDate3 = document.getElementById("recharge_time3_0").innerHTML;
		dayOfMonth(curDate1, 'recharge_time1_');
		dayOfMonth(curDate2, 'recharge_time2_');
		dayOfMonth(curDate3, 'recharge_time3_');
		
		var submitFlag = 0;
		
		// 初始化下拉列表框的信息
		document.getElementById('check0').innerHTML = "短信验证";
		
		alertRedErrorMsg = function(content)
		{
			document.getElementById('winText_ErrorMsg').innerHTML = content;
			wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
		};
		
		var lastObj= document.getElementById('phone_input_msg_Text1');
		var lastObj2=document.getElementById('phone_input_msg');
		var T=1;
		var O='phone_input_msg';
		var textInput = O + "_Text" + T;
		
		
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
		
		//验证方式切换
		function showSelect(m,n){
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
			  document.getElementById('recharge_time1_con').style.display='none';
			  document.getElementById('recharge_time2_con').style.display='none';
			  document.getElementById('recharge_time3_con').style.display='none';
			  var M=m + n;
			  var a=document.getElementById(M).innerHTML;
			  document.getElementById(m+'0').innerHTML=a;
			  if(a=="短信验证"){
			     document.getElementById('phone_input_msg').style.display='';
				 document.getElementById('phone_input_call').style.display='none';
				 document.getElementById('phone_input_recharge').style.display='none';
				 document.getElementById('phone_input_sim').style.display='none';
				 document.getElementById('phone_input_pass').style.display='none';
				 document.getElementById("pointOrX").innerHTML = "x";
				 O='phone_input_msg';
				 lastObj= document.getElementById(textInput);
				 lastObj2=document.getElementById(O);
				 changObj(O,1);
			     }
			    else if(a=="服务密码"){
				 document.getElementById('phone_input_msg').style.display='none';
				 document.getElementById('phone_input_call').style.display='none';
				 document.getElementById('phone_input_recharge').style.display='none';
				 document.getElementById('phone_input_sim').style.display='none';
				 document.getElementById('phone_input_pass').style.display='';
				 document.getElementById("pointOrX").innerHTML = "x";
				 O='phone_input_pass';
				 lastObj= document.getElementById(textInput);
				 lastObj2=document.getElementById(O);
			     changObj(O,1);
				   }
				else if(a=="通话记录"){
				 document.getElementById('phone_input_msg').style.display='none';
				 document.getElementById('phone_input_call').style.display='';
				 document.getElementById('phone_input_recharge').style.display='none';
				 document.getElementById('phone_input_sim').style.display='none';
				 document.getElementById('phone_input_pass').style.display='none';
				 document.getElementById("pointOrX").innerHTML = "x";
				  O='phone_input_call';
				 lastObj= document.getElementById(textInput);
				 lastObj2=document.getElementById(O);
				 changObj(O,1);
				}
				else if(a=="充值记录"){
				 document.getElementById('phone_input_msg').style.display='none';
				 document.getElementById('phone_input_call').style.display='none';
				 document.getElementById('phone_input_recharge').style.display='';
				 document.getElementById('phone_input_sim').style.display='none';
				 document.getElementById('phone_input_pass').style.display='none';
				 document.getElementById("pointOrX").innerHTML = ".";
				  O='phone_input_recharge';
				 lastObj= document.getElementById(textInput);
				 lastObj2=document.getElementById(O);
				 changObj(O,1);
				}
				else if(a=="SIM卡"){
				 document.getElementById('phone_input_msg').style.display='none';
				 document.getElementById('phone_input_call').style.display='none';
				 document.getElementById('phone_input_recharge').style.display='none';
				 document.getElementById('phone_input_sim').style.display='';
				 document.getElementById('phone_input_pass').style.display='none';
				 document.getElementById("pointOrX").innerHTML = "x";
				 O='phone_input_sim';
				 lastObj= document.getElementById(textInput);
				 lastObj2=document.getElementById(O);
				 changObj(O,1);
			}
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
		    menuTAB=document.getElementById('numBoard_tabs').getElementsByTagName('span');
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
		
		function btnChange()
		{
		    var selectInput = lastObj2.getElementsByTagName('input');
		   
			// 获取用户选择的验证方式
		 	var accessName = document.getElementById('check0').innerHTML;
		 	
			document.getElementById("accessName").value = accessName;
		 	
		 	// document.getElementById(check+'0').innerHTML=accessName;
		 	
		    if ("短信验证" == accessName)
		    {
		    	// 获取短信随机码信息
				var randomPwd = document.getElementById("phone_input_msg_Text1").value;
		    	// isNaN()判断参数非数字,若为数字则返回false，非数字则返回true
				if (trim(randomPwd) == "" || isNaN(randomPwd)
					|| pangu_getStrLen(trim(randomPwd)) != 6) 
				{
					document.getElementById("phone_input_msg_Text1").focus();
					
					alertRedErrorMsg("随机密码输入有误，请重新获取!");
					
					return;
				}

				//已经点击了确认或返回，等待应答，不再执行任何操作
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.action = "${sessionScope.basePath}noRealNameReg/checkRandomPwd.action";
				}
		    }
		    if ("充值记录" == accessName)
		    {
		    	var currMonAmount = document.getElementById("phone_input_recharge_Text1").value;
				var lastMonAmount = document.getElementById("phone_input_recharge_Text2").value;
				var preLastMonAmount = document.getElementById("phone_input_recharge_Text3").value;
				if("" == currMonAmount || "" == lastMonAmount || "" == preLastMonAmount)
				{
					alertRedErrorMsg("请输入充值金额！");
					return;
				}
		
				// 金额应当为数字
				if(isNaN(currMonAmount) || isNaN(lastMonAmount) || isNaN(preLastMonAmount))
				{
					alertRedErrorMsg("请输入正确的充值金额！");
					return;
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
	            
	            if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.action = "${sessionScope.basePath}noRealNameReg/checkChargeRecord.action";
				}
	            
		    }
		    if ("通话记录" == accessName)
		    {
				var calledNum1 = document.getElementById("phone_input_call_Text1").value;
				var calledNum2 = document.getElementById("phone_input_call_Text2").value;
				var calledNum3 = document.getElementById("phone_input_call_Text3").value;
				
				// 三个呼叫号码不能为空
				if("" == calledNum1 || "" == calledNum2 || "" == calledNum3) 
				{
					alertRedErrorMsg("请输入正确的呼叫号码！");
					return;
				}
				
				// 三个呼叫号码各不相同
				if(calledNum1 == calledNum2 || calledNum2 == calledNum3 || calledNum1 == calledNum3)
				{
					alertRedErrorMsg("呼叫号码须为三个不同的手机号码！");
					return;
				}
				
	            document.getElementById('callNums').value=calledNum1+","+calledNum2+","+calledNum3;
	            
	            if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.action = "${sessionScope.basePath}noRealNameReg/checkCallRecord.action";
				}
		    }
		    if("服务密码" == accessName)
		    {
		    	// 获取个人服务密码信息
				var servPasswd = document.getElementById("phone_input_pass_Text1").value;
				
				// isNaN()判断参数非数字,若为数字则返回false，非数字则返回true
				if (trim(servPasswd) == "" || isNaN(servPasswd)
					|| pangu_getStrLen(trim(servPasswd)) != 6) 
				{
					document.getElementById("phone_input_pass_Text1").focus();
					
					alertRedErrorMsg("请输入正确的服务密码");
					
					return;
				}
				
				//已经点击了确认或返回，等待应答，不再执行任何操作
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.action = "${sessionScope.basePath }noRealNameReg/checkUserPwd.action";
			    }
		    }
		    if("SIM卡" == accessName)
		    {
		    	// 获取SIM卡卡号信息
				var simCardNo = document.getElementById("phone_input_sim_Text1").value;
				
				if (trim(simCardNo) == "")
				{
					alertRedErrorMsg("请输入正确的SIM卡号");
					
					return;
				}
				
				//已经点击了确认或返回，等待应答，不再执行任何操作
				if (submitFlag == 0) 
				{
					document.actform.action = "${sessionScope.basePath }noRealNameReg/checkSIMCardNo.action";
			    }
		    }
		    document.getElementById('actform').submit();
		}
		
		
		//发送短信验证码
		document.getElementById("msg_btn").onclick=function(){
		
			if(document.getElementById("msg_btn").className=="msg_btn")
			{
				changeMSG(this);
			}
		}
		
		var msgTime=10;
		var sendingFlag = false;
		
		function changeMSG(o) {
	        if (msgTime == 0) {
	            o.className='msg_btn';            
	            o.innerHTML="获取验证码";
	            msgTime = 10;
				
	        } else {
	        
			    if (sendingFlag)
				{
					return;
				}
				
				sendingFlag = true;
				
				var url = "${sessionScope.basePath}noRealNameReg/sendRandomPwd.action";
				var params = "telNumber=" + "<s:property value='telNumber' />";
				var loader = new net.ContentLoaderSynchro(url, netload = function () {
								sendingFlag = false;
								
								// 异步获取后台封装的数据
								var resXml = this.req.responseText;
								
								// 对字符串以';,;'进行分割
								var arrRes = resXml.split(';,;');
								
								
								// arrRes[0]的值为1时，接口调用成功，并成功返回手机验证码
								if('1' == arrRes[0])
								{
									o.className='msg_btn_dis'
										
									o.innerHTML=msgTime+'秒后可重发';
									msgTime--;
						            setTimeout(function(){
						                leftTime(o, msgTime)},1000);
									
								}
								// arrRes[0]的值为1时，接口调用成功，但未成功发送接口，返回错误信息
								else
								{
									alertRedErrorMsg(arrRes[1]);
									o.className='msg_btn';            
						            o.innerHTML="获取验证码";
						            msgTime = 10;
								}
							}, null, "POST", params, "application/x-www-form-urlencoded");
			    }
		    }
		    
		    // 计算重发随机密码的时间
		    function leftTime(o)
		    {
		    	if (msgTime == 0) {
		            o.className='msg_btn';            
		            o.innerHTML="获取验证码";
		            msgTime = 10;
				
	        	} 
	        	else 
	        	{
		            o.className='msg_btn_dis'
		            o.innerHTML=msgTime+'秒后可重发';
		            msgTime--;
		            setTimeout(function(){
						leftTime(o)},1000);
            	}
		    }
		    
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
					</div>
					<!--keyboard_wrap end-->
					<div class="blank10"></div>
				</div>
			</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
