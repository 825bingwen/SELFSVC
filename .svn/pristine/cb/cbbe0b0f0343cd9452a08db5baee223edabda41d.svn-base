<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js?ver=${jsVersion }"></script>
		<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
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
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
				document.getElementById("numBoardText1").focus();
				
				changObj(document.getElementById("numBoardText1"), 1);
				
				return true;
			}
			
			if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//numBoardText1输入完毕，自动跳转到numBoardText2
				document.getElementById("numBoardText2").focus();
				
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		function doSub()
		{
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(document.getElementById('numBoardText1'), 1);
			
				alertRedErrorMsg("请输入正确的手机号码");
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				alertRedErrorMsg("两次输入的手机号码必须相同");
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openWindow_wait('pls_wait');
				
				if("<s:property value='curMenuId' />" == 'recNonRegister')
				{
					document.getElementById("nonRegNumber").value = telNumber;
					document.actform.action = "${sessionScope.basePath}noRealNameReg/showRegisterAccess.action";
					document.actform.submit();
				}
				else
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						telNumber + "选择充值交费");
						
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}charge/qryfeeChargeAccount.action";
					document.actform.submit();
				}
			}	
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", document.getElementById("numBoardText1").value + "退出充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}	
		
	    // 多人缴费
		function morePhone()
		{
	    	<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
	    	// 校验银联卡读卡器是否可用
	    	if (getValue("checkCardFlag") == "carderror")
	    	{
	    		openConfirmMessage("尊敬的客户，由于本终端的银联读卡器或密码键盘故障，暂时无法进行多人交费！");
	    		return;
	    	}
	    	<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
	    	
		    // 标志为多人缴费
		    document.getElementById("morePhoneFlag").value = "1";
            document.forms[0].target = "_self";
            document.forms[0].action = "${sessionScope.basePath }charge/morePhone.action";
            document.forms[0].submit();
		}
	    
		</script>
	</head>
	<body scroll="no" onload="">
		<form name="actform" method="post">
			<s:hidden id="nonRegNumber" name="telNumber" />
			<s:hidden id="morePhoneFlag" name="morePhoneFlag" />
			
			<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
			<%-- 错误信息 --%>
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			
			<%-- 校验现金设备标识 --%>
			<input type="hidden" id="checkCashFlag" name="checkCashFlag" value='' />
			
			<%-- 校验银联卡设备标识 --%>
			<input type="hidden" id="checkCardFlag" name="checkCardFlag" value='' />	
			<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
			
			<!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
				    <!-- add begin jWX216858 2015-4-21 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
				    <s:if test="1 == morePhoneSwitch && curMenuId == 'feeCharge'">
    					<div class="changebtn"><a href="javascript:void(0)" onclick="morePhone();return false;">多人交费</a></div>    
				    </s:if>
				    <!-- add end jWX216858 2015-4-21 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
					<div class="blank5" ></div>
					
					<div class="fl ml50 mt20">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入手机号码</i>
          							<span class="pl20 fl lh75">手机号码：</span>
            						<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
            						<input type="text" maxlength="11" id="numBoardText1" name="servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus="MoveLast(this);" value="${sessionScope.CustomerSimpInfo.servNumber }" />
            						<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 再次输入手机号码</i>
          							<span class="pl20 fl lh75">再次输入：</span>
          							<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus="MoveLast(this);" value="${sessionScope.CustomerSimpInfo.servNumber }" />
            						<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
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
	        				
	        				<%--add begin l00190940 2011/12/12 OR_SD_201111_370 为服务密码认证添加正在处理页面 --%>
							<!--弹出框 正在处理 请稍候-->
							<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
								<div class="bg"></div>
								<p class="mt40">
									<img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." />
								</p>
								<p class="tips_txt">
									正在处理，请稍候......
								</p>
								<div class="line"></div>
								<div class="popup_banner"></div>
							</div>

							<script type="text/javascript">
							openWindow_wait = function(id)
							{
								wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口
							}			
						    </script>
							<!--弹出窗结束-->
							<%--add end l00190940 2011/12/12 OR_SD_201111_370 为服务密码认证添加正在处理页面 --%>	
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
						    		//alert (numBoardBtns[i].getAttribute("name")+"|"+numBoardBtns[i].id+"|"+numBoardBtns[i].className);
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
										
							  			if (pattern.test(lastObj.value)
							  					&& document.getElementById("numBoardText2").value == "")
										{
											//numBoardText1输入完毕，自动跳转到numBoardText2
											document.getElementById("numBoardText2").focus();
											
											changObj(document.getElementById("numBoardText2"), 2);
											
											return true;
										}		
							  			
									}					
								}
						
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
									}
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
			</div>

			<%@ include file="/backinc.jsp"%>		
		</form>
		
		<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
		<%--检测硬件异常提示 --%>
		<div class="popup_confirm" id="chkHardware_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<p id="chkHardwareMsg" style="font-size: 20px; color: red;"></p>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="#"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();document.getElementById('numBoardText1').focus();">继续充值</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();goback('feeCharge');">返回</a>
				</span>
			</div>
		</div>
		<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
	</body>
</html>
