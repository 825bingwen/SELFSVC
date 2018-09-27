<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // 省份信息
    String provinceSD = Constants.PROOPERORGID_SD;

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();
	
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">		
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript">
		
		//是否支持读取二代身份证信息标志,0不支持,1:支持
		var is2GIDFlag = <%=termSpecial.charAt(8)%>;	
		
		// 读卡等待时间
		var limitTime = 30;
		
		//计算读卡剩余时间的句柄
		var timeToken;
		
		//等待读卡的句柄
		var waitingToken;
		
		// 提交标志
		var submitFlag = 0;
		
		// 初始化身份证读卡器线程启动标志，1，已启动；0，未启动。如果为1时，用户主动取消操作，需调用关闭身份证阅读器接口
		var initCardReader = 0;
		
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
				if (etarget.name == 'password' && etarget.value == '' )
				{
					MoveLast(document.getElementById('servnumber'));
				}
			}
			
			var certid = document.forms[0].certid.value;
			var contacttel = document.forms[0].contacttel.value;		
	
			if ((key == 8 || key == 32 || key == 66)
			 		&& pangu_getStrLen(trim(contacttel)) == 0 
			 		&& (pangu_getStrLen(trim(certid)) == 15 || pangu_getStrLen(trim(certid)) == 18)) 
			{
				document.forms[0].certid.focus();
				
				changObj(document.forms[0].certid, 1);
				
				return true;
			}
			
 			if (pangu_getStrLen(trim(certid)) == 18 && document.forms[0].contacttel.value == "") 
 			{
 				//document.forms[0].contacttel.focus();
 				
 				//changObj(document.forms[0].contacttel, 2);
 				
 				return true;
 			}
 			
			return true;
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
		// 验证是控件值是否为正整型或0
		function validateNumberOrZero(value) {
		  var reg = /^[0-9]\d*$/;
		  return reg.test(value);
		}
		
		// 验证身份证区域编码
		function validateArea(areaCode){
			var aCity = {11 : "北京", 12 : "天津", 13 : "河北", 14 : "山西", 15 : "内蒙古", 21 : "辽宁",
		                22 : "吉林", 23 : "黑龙江", 31 : "上海", 32 : "江苏", 33 : "浙江", 34 : "安徽",
					    35 : "福建", 36 : "江西", 37 : "山东", 41 : "河南", 42 : "湖北", 43 : "湖南",
						44 : "广东", 45 : "广西", 46 : "海南", 50 : "重庆", 51 : "四川", 52 : "贵州",
				  	    53 : "云南", 54 : "西藏", 61 : "陕西", 62 : "甘肃", 63 : "青海", 64 : "宁夏",
						65 : "新疆", 71 : "台湾", 81 : "香港", 82 : "澳门", 91 : "国外"};
			if (aCity[parseInt(areaCode)]==null)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("非法地区代码 " + areaCode);
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "非法地区代码 " + areaCode;
				}
				
			   	return false;
			}
			return true;
		}
		
		// 验证出身日期
		function validateBirthday(birthday){
			var year=birthday.substring(0,4);
			var month=birthday.substring(4,6);
			var day=birthday.substring(6,8);
			if(year<1800)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"，出生年（"+year+"）太小");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生年（"+year+"）太小";
				}
				
				return false;
			}
			if(year>new Date().getYear())
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"出生年（"+year+"）太大（大于今年）");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生年（"+year+"）太大（大于今年）";
				}
				
				return false;
			}
			if(month<1)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"出生月（"+month+"）太小");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生月（"+month+"）太小";
				}
				
				return false;
			}
			else if(month>12)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"出生月（"+month+"）太大");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生月（"+month+"）太大";
				}
				
				return false;
			}
			var dayMax=31;
			if(month==2)
			{
				if(year%100==0)
				{
					if(year%400==0)
					{
						dayMax=29;
					}
					else
					{
						dayMax=28;
					}
				}
				else
				{
					if(year%4==0)
					{
						dayMax=29;
					}
					else
					{
					dayMax=28;
					}
				}
			}else if(month==4||month==6||month==9||month==11)
			{
				dayMax=30;
			}else{
				dayMax=31;
			}
			if(day<1)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"出生日（"+day+"）太小");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生日（"+day+"）太小";
				}
				
				return false;
			}
			if(day>dayMax)
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证号码出错:"+"出生日（"+day+"）太大（大于"+dayMax+"）");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证号码出错:"+"出生日（"+day+"）太大（大于"+dayMax+"）";
				}
				return false;
			} 
			return true;
		}
		
		// 验证身份证号码
		function validateIDCard(userCardID)
		{
			if(userCardID.length!=15&&userCardID.length!=18){
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("身份证必须为15位或18位!");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "身份证必须为15位或18位!";
				}
				
				return false;
			}
			if(userCardID.length==15)
			{
				if(!validateNumberOrZero(userCardID))
				{
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("15位身份证必须全部为数字!");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "15位身份证必须全部为数字!";
					}
					
					return false;
				}
				if(!validateArea(userCardID.substring(0,2))){
					return	false;
				}
				if(!validateBirthday("19"+userCardID.substring(6,12))){
					return false;
				}
			}
			if(userCardID.length==18)
			{
		
				var subCheck18=userCardID.substring(0,17);
				if(!validateNumberOrZero(subCheck18))
				{
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("18位身份证的前17位必须全部为数字!");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "18位身份证的前17位必须全部为数字!";
					}
					
					return false;
				}
				var lastStr = userCardID.substr(17,1);
				if(!validateNumberOrZero(lastStr))
				{
					if(lastStr!='X'&& lastStr!='x')
					{
						if ("1" == "<%=popupFlag %>")
						{
							alertRedErrorMsg("身份证号最后一位只能为'X'或'x'或数字！");
						}
						else
						{
							document.getElementById("errorMsg").innerHTML = "身份证号最后一位只能为'X'或'x'或数字！";
						}
					
						return false;
					}
				} 
				if(!validateArea(userCardID.substring(0,2))){
					return false;
				}
				if(!validateBirthday(userCardID.substring(6,14))){
					return false;
				}
			}
			return true;
		} 
		
		//密码更改提交
		function doSub()
		{
			var certid = trim(document.actform.certid.value);
			
			// 身份证校验
			if ('IdCard' == '<s:property value='certtype'/>')
		    {
				if (!validateIDCard(certid))
				{
					document.getElementById('certid').focus();

					return false; 
				}
			}
			// 其他证件校验
			else
			{
				if (certid == '')
				{
					document.getElementById('certid').focus();
					
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("<s:property value='certname'/>号码不能为空。");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "<s:property value='certname'/>号码不能为空。";
					}
					
					return false;
				}
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading_NX('recWaitLoading');
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/makeSureTel.action";
				document.actform.submit();
			}		
		}
		
		function goback(curmenu) 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }chooseTel/telNumResult.action";
				document.actform.submit();				
			}			
		}
		
		// 页面载入时加载身份证读卡器获取用户身份证信息
		function bodyLoad()
		{
		    document.getElementById("certid").focus();
		    
		    if ('IdCard' != '<s:property value='certtype'/>')
		    {
		    	return;
		    }
			if (is2GIDFlag != 1) 
			{
			    setErrorInfoRegion("该终端不支持身份证读卡器，请手工输入您的身份证号信息。");
				document.getElementById("certid").focus();
				return;
			}
			
			try 
			{				
				//生产用
				var ret = InitIdCardReader();// 设置身份证读卡器为可用状态

				//测试用
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{
					setErrorInfoRegion("初始化身份证读卡器异常，请您手工输入您的身份证号信息。");
                    return;
				}
				else
				{		
					//准备刷卡进程启动
					initCardReader = 1;
					
					//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
                	document.getElementById("homeBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
					
					//在调用初始化身份证读卡器后，获取身份证信息之前，需要调用此接口检查一下读卡器状态
					getIdCardStatus();
					
					// 开始记时，并循环调用接口
					startclock();
				}
			}
			catch (ex) 
			{
				setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
				document.getElementById("certid").focus();
                return;
			}
		}
		
		//获取身份证读卡器状态
		function getIdCardStatus()
		{
			try
			{
				//生产用
				var ret = GetIdCardStatus();// 获取身份证读卡器状态
				
				// 测试用
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{					
					setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
					document.getElementById("certid").focus();
				}
				
				// 点击顶部菜单需要关闭身份证打卡器
				closeStatus = 3;
			}
			catch (excep) 
			{		
				setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
				document.getElementById("certid").focus();
			}
		}
		
		//设置时间计算周期
		function startclock() 
		{			
			try 
			{
				setErrorInfoRegion("请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面。");
				
				// 获取身份证文字内容
				waitingToken = setInterval("getReadCardStatus()", 1000);
				
				// 计时器
				timeToken = setInterval("calLeftTime()", 1000);
			}
			catch (e) 
			{
				setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
				document.getElementById("certid").focus();
			}
		}
		
		// 循环调用获取身份证文字内容接口
		function getReadCardStatus() 
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			try 
			{
				//生产用
				var ret = GetIdCardContent();// 打开仓门接收用户身份证 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1）
				
				//测试用
				//var ret = "0姓名~男~民族~出生~住址~37061218840530402X~签发机关~有效期起始日期~有效期截止日期~最新住址";

				if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
				{	
					//调用关闭身份证阅读器接口
					//生产用
					CloseCardReader();
					
					//可以点击顶部菜单
					closeStatus = 0;
					
					//身份证信息：0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
					var idCardInfor = ret.substring(1,ret.length).split('~');
					
					//公民身份号码
					var idCardNo = idCardInfor[5];
					
					//取消定时器
					clearInterval(timeToken);
					
					document.getElementById("certid").value = idCardNo;
					
					// 将焦点定位到输入手机号码框
					changObj(document.getElementById('contacttel'), 2);
					document.getElementById("contacttel").focus();
				} 
				else if (ret == "-1")
				{
					setErrorInfoRegion("获取身份证文字内容失败，请手工输入您的身份证号信息。");
					document.getElementById("certid").focus();
				}
				else if (ret == "2")
				{	
					setErrorInfoRegion("证件无法识别，请手工输入您的身份证号信息。");
					document.getElementById("certid").focus();
				}						
			}
			catch (e) 
			{		
				setErrorInfoRegion("身份证读卡器读卡失败，请手工输入您的身份证号信息。");
				document.getElementById("certid").focus();
			}
		}
		
		//modify begin l00190940 2011-11-25 身份证倒计时不显示在输入框内
		//计算读卡剩余时间
		function calLeftTime()
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			//读卡时长一共limitTime秒
			limitTime = limitTime - 1;
			
			if (document.getElementById("province").value == document.getElementById("provinceSD").value)
			{
			    setErrorInfoRegion("请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面。剩余" + limitTime + "秒");
			
				//读卡时间结束
				if (parseInt(limitTime) <= 0 && submitFlag == 0)
				{
		        	setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
				}
			}
			else
			{
			    document.getElementById("certid").value = limitTime;
			
				//读卡时间结束
				if (parseInt(document.getElementById("certid").value) <= 0 && submitFlag == 0)
				{
		        	setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
		        	document.getElementById("certid").value = "";
				}
			}
			document.getElementById("certid").focus();
		        	
        	//调用关闭身份证阅读器接口
			//生产用
			CloseCardReader();
			
			//可以点击顶部菜单
			closeStatus = 0;
		}
		//modify end l00190940 2011-11-25 身份证倒计时不显示在输入框内
		
		//设置错误信息
		function setErrorInfoRegion(errMsg)
		{
			document.getElementById("errorMsg").innerHTML = errMsg;		    
		}
		
		</script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />			
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
			<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
			<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
			
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />	
			<input type="hidden" id="org_id" name="org_id" value="<s:property value='org_id'/>" />
			
			<input type="hidden" id="certtype" name="certtype" value="<s:property value='certtype'/>"/>
			<input type="hidden" id="name" name="name" value=""/>
			
			<%@ include file="/titleinc.jsp"%>
		
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<!-- add begin l00190940 2011-11-25 身份证倒计时不显示在输入框内（仅限山东） -->
				<input id = "province" value = "<%=province %>" type = "hidden">	
			    <input id = "provinceSD" value = "<%=provinceSD %>" type = "hidden">
				<!-- add end l00190940 2011-11-25 身份证倒计时不显示在输入框内（仅限山东） -->
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);">
							1.选择查询方式
						</a>
						<a href="javascript:void(0);">
							2.输入查询条件
						</a>
						<a href="javascript:void(0);">
							3.选择号码
						</a>
						<a href="javascript:void(0);" class="on">
							4.输入个人信息
						</a>
						<a href="javascript:void(0);">
							5.完成
						</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712" id="errorMsg"></div>
					<div class="blank40"></div>
					<div class="p40 pr0">
						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
                       	<div class="line w625"></div>
						<div class="blank10"></div>
						<div class="custom_money pl30 fl">
							<span class="pl40 fs20 fl lh48"><s:property value='certname'/>号码：</span>
							<input type="text" name="certid" id="certid" value="" maxlength="18" class="text1 fl fs24" onclick="changObj(this, 1);" onfocus="MoveLast(event);">											
       					</div>
       					<div class="blank10"></div>
       					<input type="hidden" name="contacttel" id="contacttel" value="" maxlength="12" class="text1 fl" onclick="changObj(this, 2);" onfocus="MoveLast(event);">
       					<!-- 
       					<div class="custom_money pl30 fl">
							<span class="pl40 fs20 fl lh48">联系电话：</span>
							<input type="text" name="contacttel" id="contacttel" value="" maxlength="12" class="text1 fl" onclick="changObj(this, 2);" onfocus="MoveLast(event);">					
       					</div>
       					 -->
       					
       					<div class="keyboard_wrap mt10 pl20 clearfix">
        					<div class="fl btn_back_box pt200">
        						<p class="fs16 pl10">&nbsp;&nbsp;</p>        							
        					</div>
        					<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
        						<div class=" numbox clearfix">
             						<div class="clearfix">
             							<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
           							</div>
            						<div class="clearfix"> 
            							<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            						</div>
            						<div class="nleft"> 
            							<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
            						</div>
            						<div class="nright"> 
            							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
            						</div>
            						<div class="blank10"></div>
          						</div>
        					</div>
        					<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var type = 1;
								var numBoardText = document.getElementById('certid');
								
								for (i = 0; i < numBoardBtns.length; i++)
								{
					    			if (!numBoardBtns[i].className) 
					    			{
					    				numBoardBtns[i].className = '';
					    			}
					    			//// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
				     				if (numBoardBtns[i].getAttribute("name") == 'functionkey')
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
						  				
						  				if (pangu_getStrLen(trim(numBoardText.value)) == 18 
						  						//&& document.forms[0].contacttel.value == ""
						  						) 
							 			{
							 				//document.forms[0].contacttel.focus();
							 				
							 				//changObj(document.forms[0].contacttel, 2);
							 			}			      							   
									}
								}
								
								function changObj(o, t)
								{
									type = t;
									
									document.getElementById("errorMsg").innerHTML = "";
									
									numBoardText = o;
								}	
								
								function changValue(t, v)
								{
									if (t == -1)
									{
										numBoardText.value = numBoardText.value.slice(0, -1);
									}
									else if(t == -2)
									{
										numBoardText.value = "";
									}
									else if (type == 1 && numBoardText.value.length < 18)
									{			
										numBoardText.value += v;																		
									}
									else if (type == 2 && numBoardText.value.length < 12 && !isNaN(v))
									{
										numBoardText.value += v;
									}
									
									var r = numBoardText.createTextRange(); 
									r.collapse(false); 
									r.select();
								}		
              				</script>
        				</div>
					</div>
				</div>		
			</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
