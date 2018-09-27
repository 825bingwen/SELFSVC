<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	
	// 终端机信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript">
		
		// 防止重复提交
		var submitFlag = 0;
		
		// 是否支持打印票据标志,0不支持,1:支持
		var isPrintFlag = window.parent.isPrintFlag;
	
		// 是否支持现金缴费标志,0不支持,1:支持
		var isCashEquip = window.parent.isCashEquip;
	
		// 是否支持银行卡缴费标志,0不支持,1:支持
		var isUnionPay = window.parent.isUnionPay;
	
		// 是否支持读取二代身份证信息标志,0不支持,1:支持
		var is2GIDFlag = window.parent.is2GIDFlag;
	
		// 是否支持SIM卡读卡器
		var SIMreaderFlag = window.parent.SIMreaderFlag;
	
		// 0-打印机正常，1-打印机异常；
		var printIsOk = 0;
		
		// 校验现金识币器
		var chkCashMsg = "";
		
		// 校验银联读卡器
	    var chkCardMsg = "";
		
		// 选择No提示信息
		var chooseNoAlertMsg = "";
		
		//8、32、66、77 更正
		//82、220 返回
		//13、89、221 确认
		//80 打印
		//85 上一页
		//68 下一页
		
		/*
		 *　去掉左右两边的空格
		 */
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
		
		document.onkeydown = pwdKeyboardDown;
		
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
			}
			
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			//getValue()和getObj()为script.js封装的方法
			//更正时自动焦点自动转换
			if (key == 8 || key == 32 || key == 66 || key ==77) 
			{
				//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
				if(pattern.test(trim(getValue("numBoardText1"))) && getValue("numBoardText2") == "")
				{
					getObj("numBoardText1").focus();
					changObj(getObj("numBoardText1"), 1);
					
					return true;
				}
				
				//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
				if(pattern.test(trim(getValue("numBoardText2"))) && getValue("numBoardText3") == "")
				{
					getObj("numBoardText2").focus();
					changObj(getObj("numBoardText2"), 2);
					
					return true;
				}
				
			}
			
			//输入号码时自动将焦点转换到下一行
			if (pattern.test(trim(getValue("numBoardText1"))) 
					&& getValue("numBoardText2") == "")
			{
				//numBoardText1输入完毕，自动跳转到numBoardText2
				getObj("numBoardText2").focus();
				changObj(getObj("numBoardText2"), 2);
				
				return true;
			}
			
			if (pattern.test(trim(getValue("numBoardText2"))) 
					&& getValue("numBoardText3") == "")
			{
				//numBoardText2输入完毕，自动跳转到numBoardText3
				getObj("numBoardText3").focus();
				changObj(getObj("numBoardText3"), 3);
				
				return true;
			}			
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		//提交
		function doSub()
		{
			//对号码进行判断
			var pattern = /^\d{11}$/;
			var authPassword = /^\d{6}$/;
			
			var telNumber = getValue("numBoardText1");
			
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(getObj('numBoardText1'), 1);
				alertRedErrorMsg("请输入正确的手机号码");
				
				return;
			}
			
			var confirmTelNumber = getValue("numBoardText2");
			
			if (confirmTelNumber != telNumber)
			{
				changObj(getObj('numBoardText2'), 2);
				alertRedErrorMsg("两次输入的手机号码必须相同");
				
				return;
			}
			
			var password = getValue("numBoardText3");
			
			if("" == password || !authPassword.test(password))
			{
				changObj(getObj('numBoardText2'), 2);
				alertRedErrorMsg("请输入六位服务密码");
				
				return;
			}
			
			if (submitFlag == 0) 
			{
				// 票据打印机异常不能打印小票，需用户进行确认
				if (printIsOk == 1)
				{
					openRealNameConfirm("票据打印机异常不能打印小票，是否继续？", "票据打印机异常，用户已取消操作");
				}
				
				// 现金识币器有问题，银联读卡器可用
				else if (chkCashMsg != "" && chkCardMsg == "")
			 	{
			 		chkCashMsg = "";
			 		setValue("payTypeFlag","01");
			 		openRealNameConfirm("现金识币器故障，本次补卡只能用银联卡补卡，是否继续？", "现金识币器异常，用户已取消操作");
			 	}
			 	
			 	// 现金识币器可用，银联读卡器有问题
			 	else if (chkCashMsg == "" && chkCardMsg != "")
			 	{
			 		chkCardMsg = "";
			 		setValue("payTypeFlag","10");
			 		openRealNameConfirm("银联读卡器故障，本次补卡只能用现金补卡，是否继续？", "银联读卡器异常，用户已取消操作");
			 	}
				else
				{
					submitFlag = 1;	//提交标记
					openRecWaitLoading();
					document.actform.action = "${sessionScope.basePath}reissueCard/readCert.action";
					document.actform.submit();
				}
			}	
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		
		//硬件检查
		function doLoad()
		{
		    var message = "";
		 	
		    // 校验读卡、写卡器
		    if (SIMreaderFlag == 1)
		    {
		    	message = initBlankCardReader();
		    }
		    else
		    {
		    	message = "该终端机不支持空白卡读卡器，无法补卡！";
		    }
			
		    // 提示信息
			if (message != "")
			{
				setException(message);
			}
			
			// 发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
			message = checkReadCardStatus();
			if (message != "")
			{
				setException(message);
			}
			
		 	// 校验身份证读卡器
		 	if (is2GIDFlag == 1)
		    {
		 		message = initOpenIdCardReader();
		    }
		    else
		    {
		    	message = "该终端机不支持身份证读卡器，无法补卡！";
		    }
		 	
		 	// 提示信息
		    if (message != "")
			{
				setException(message);
			}
		    
		 	// 校验票据打印机
		 	if (isPrintFlag == 1)
		 	{
		 		// 校验票据打印机
			    message = initListPrinter();
			    if (message != "")
				{
			    	printIsOk = 1;
				}
		 	}
		 	else
		 	{
		 		printIsOk = 1;
		 	}
		 	
		 	// 校验现金识币器
		 	if (isCashEquip == 1)
		 	{
		 		chkCashMsg = initCashPayEquip();
		 	}
		 	else
		 	{
		 		chkCashMsg = "该终端机不支持现金识币器";
		 	}
	
		    // 校验银联读卡器
		    if (isUnionPay == 1)
		    {
		    	chkCardMsg = initUnionCardPayEquip("<%=termInfo.getUnionpaycode()%>", "<%=termInfo.getUnionuserid()%>");
		    }
		    else
		    {
		    	chkCardMsg = "该终端机不支持银联读卡器";
		    }
		    
		    // 判断如果[现金识币器]和[银联读卡器]都有问题时，导向到错误页面
		    if (chkCashMsg != "" && chkCardMsg != "")
		    {
		    	setException("现金识币器和银联读卡器都出现故障！");
		    }
		}
		
		// 是否打印小票  1-不打印，0-打印
		function printConfirm()
		{
			// 现金识币器有问题，银联读卡器可用
		 	if (chkCashMsg != "" && chkCardMsg == "")
		 	{
		 		chkCashMsg = "";
		 		setValue("payTypeFlag","01");
		 		wiWindow.close();
		 		openRealNameConfirm("现金识币器故障，本次补卡只能用银联卡补卡，是否继续？", "现金识币器异常，用户已取消操作");
		 		return;
		 	}
		 	// 现金识币器可用，银联读卡器有问题
		 	else if (chkCashMsg == "" && chkCardMsg != "")
		 	{
		 		chkCardMsg = "";
		 		setValue("payTypeFlag","10");
		 		wiWindow.close();
		 		openRealNameConfirm("银联读卡器故障，本次补卡只能用现金补卡，是否继续？", "银联读卡器异常，用户已取消操作");
		 	}
		 	else
		 	{
		 		// 票据打印机异常不能打印小票，需用户进行确认
				if (printIsOk == 1)
				{
					document.getElementById('canNotPrint').value = "1";
				}
			 	
			 	if (submitFlag == 0) 
				{
					// 提交标记
				 	submitFlag = 1;	
					
					// 开启等待div
					openRecWaitLoading();
					
					document.actform.action = "${sessionScope.basePath}reissueCard/readCert.action";
					document.actform.submit();
				}
		 	}
		}
		
		// 出现异常
		function setException(errorMsg) 
		{			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//弹出等待框
				openRecWaitLoading();
				setValue("errormessage",errorMsg);
		
				//异常处理，只要出现了异常就要记录日志  
				document.actform.action = "${sessionScope.basePath }reissueCard/addRecLog.action";
				document.actform.submit();
			}
		}
		
		// 弹出确认框
		function openRealNameConfirm(content, noMsg)
		{
			chooseNoAlertMsg = noMsg;
			document.getElementById('printPromptId').innerHTML = content;
			wiWindow = new OpenWindow("printCheck_confirm",708,392);
		}
		
		// 提示信息选择"否"
		function chooseNo()
		{
			wiWindow.close();
			setException(chooseNoAlertMsg);
		}
			
		</script>
	</head>
	<body scroll="no" onload="getObj('numBoardText1').focus();doLoad()">
		<form name="actform" method="post">	
			<%-- 错误信息 --%>
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="0" />
			
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="11"/>
					
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入手机号码</i>
          							<span class="pl20 fl lh75">手机号码：</span><%--15827814444 15871503875--%>
            						<input type="text" maxlength="11" id="numBoardText1" name="servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus="" value=""/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 再次输入手机号码</i>
          							<span class="pl20 fl lh75">再次输入：</span>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus="" value=""/>
         						</li>
         						    <li class="fs20 clearfix" id="phone_password">
          							<i class="lh30">3. 请输入服务密码</i>
          							<span class="pl20 fl lh75">服务密码：</span>
            						<input type="password" maxlength="6" id="numBoardText3" name="password" class="text1 fl relative" onclick="changObj(this, 3);MoveLast(this);" onfocus="" value=""/>
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
	                			var numBoardBtns = getObj('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = getObj('numBoardText1');
								var type = 1;
								
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
						    		
						    		// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
					     			if (numBoardBtns[i].getAttribute("name") == 'functionkey')
					     			{
					     				continue;  
					     			}
						
									numBoardBtns[i].onmousedown = function(){
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function(){
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			//对号码进行判断
										var pattern = /^\d{11}$/;
										
							  			if (pattern.test(trim(lastObj.value))
							  					&& getValue("numBoardText2") == "")
										{
											//numBoardText1输入完毕，自动跳转到numBoardText2
											getObj("numBoardText2").focus();
											
											changObj(getObj("numBoardText2"), 2);
											
											return true;
										}
										
										if (pattern.test(trim(getValue("numBoardText2")))
							  					&& getValue("numBoardText3") == "")
										{
											//numBoardText1输入完毕，自动跳转到numBoardText2
											getObj("numBoardText3").focus();
											
											changObj(getObj("numBoardText3"), 3);
											
											return true;
										}		
									}					
								}
						
								//改变焦点所在输入框的样式
								function changObj(o, t)
								{
									getObj("errorMsg").innerHTML = "";
									
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										getObj('phone_input_1').className = "on fs20 clearfix";
										getObj('phone_input_2').className = "fs20 clearfix";
										getObj('phone_password').className = "fs20 clearfix";
									}

									if (t == 2)									
									{
										type = 1;
										getObj('phone_input_1').className = "fs20 clearfix";
										getObj('phone_input_2').className = "on fs20 clearfix";
										getObj('phone_password').className = "fs20 clearfix";
									}
									
									if (t == 3)
									{
										type = 0;
										getObj('phone_input_1').className = "fs20 clearfix";
										getObj('phone_input_2').className = "fs20 clearfix";
										getObj('phone_password').className = "on fs20 clearfix";
									}
									
									//将光标置到最后
									MoveLast();
								}					
						
								function changValue(t, v)
								{
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
									{								
										lastObj.value += v;							
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 0)
									{								
										lastObj.value += v;								
									}
									
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
								
								alertRedErrorMsg = function(content)
								{
									getObj('winText_ErrorMsg').innerHTML = content;
									wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
								};
								
	              			</script>
	        				<!--小键盘end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>		
		</form>
		
		<%--票据打印机异常提示 --%>
		<div class="popup_confirm" id="printCheck_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p id="printPromptId"></p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="#"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';printConfirm();">继续</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';chooseNo();">取消</a>
				</span>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		if ("" != "<%=errorMsg %>")
		{			
			alertRedErrorMsg("<%=errorMsg %>");
		}
		else
		{
			// 终端机支持空白卡类写卡型提示信息
			alertSuccessMsg("<s:property value="blankCardTypeAlertMsg"/>");
		}
	</script>
</html>
