<%--
 @User: 高群/g00140516
 @De: 2012/09/05
 @comment: 个人银行划扣业务
 @remark create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
--%>
<%@page contentType="text/html; charset=GBK"%>

<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();
	
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
	
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
			var key=GetKeyCode(e);
			//确认
			if (key == 13 || key == 89 || key == 221) 
			{
				doSub();
				return;
			}
			//退出
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
			else if (key == 8 || key == 32 || key == 66 )
			{
				var etarget = getEventTarget(e);
				if (etarget.type == "text" || etarget.type == "password") 
				{
					etarget.value = backString(etarget.value);
				}
			}
			
			//更正时如果银行账号为空，则自动转到身份证号输入框
			if ((key == 8 || key == 32 || key == 66 ) && document.getElementById("pan").value == "")
			{
				changObj(document.getElementById("IDCard"), 1);
				document.getElementById("IDCard").focus();				
				return true;
			}
			
			//身份证号输入完成后自动跳转到银行账号输入框
			if (pangu_getStrLen(trim(document.getElementById("IDCard").value)) == 18
					&& document.getElementById("pan").value == "")
			{
				changObj(document.getElementById('pan'), 2);
				document.getElementById("pan").focus();
				return true;
			}
		}

		function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		//过滤前后空字符串		
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
		
		//设置错误信息
		function setErrorInfoRegion(errMsg)
		{
		    document.getElementById("errorMsg").innerHTML = errMsg;		    
		}
		
		//提交
		function doSub()
		{
			var IDCard = trim(document.actform.IDCard.value);			
			if (IDCard == "" || !(/(^\d{15}$)|(^\d{17}([0-9]|x|X)$)/.test(IDCard)))
			{ 
				changObj(document.getElementById("IDCard"), 1);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入15位或18位号码的身份证号");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入15位或18位号码的身份证号";
				}
				
				return false; 
			}
			
			var pan = trim(document.actform.pan.value);
			if(!(/^\d{11,19}$/).test(pan))
			{
				changObj(document.getElementById("pan"), 2);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入正确的银行账号");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入正确的银行账号";
				}
				
				return false;
			}
						
			openRecWaitLoading();
			
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }bankhuakou/confirmBankInfo.action";
			document.actform.submit();
		}
		
		// 返回
		function goback(menuid)
		{
			if (closeStatus == 3)
			{
				try
				{
			    	// 关闭身份证读卡器
					window.parent.document.getElementById("idcardpluginid").CloseCardReader();
				}
				catch(e)
				{}
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.getElementById("curMenuId").value = menuid;
		
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }login/backForward.action";
				document.actform.submit();
			}
		}
		
		// 页面载入时加载身份证读卡器获取用户身份证信息
		function bodyLoad()
		{
		    document.getElementById('IDCard').focus();
			if (is2GIDFlag != 1) 
			{
				return;
			}
			
			try 
			{
				// 设置身份证读卡器为可用状态
				var ret = InitIdCardReader();
				
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
                return;
			}
		}
		
		//获取身份证读卡器状态
		function getIdCardStatus()
		{
			try
			{
				// 获取身份证读卡器状态
				var ret = GetIdCardStatus();
				
				if (ret != "0" && ret != 0) 
				{					
					setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
				}
				
				// 点击顶部菜单需要关闭身份证打卡器
				closeStatus = 3;
			}
			catch (excep) 
			{		
				setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
			}
		}
		
		//设置时间计算周期
		function startclock() 
		{			
			try 
			{
				setErrorInfoRegion("请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面中。");
				
				// 获取身份证文字内容
				waitingToken = setInterval("getReadCardStatus()", 1000);
				
				// 计时器
				timeToken = setInterval("calLeftTime()", 1000);
			}
			catch (e) 
			{
				setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
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
				// 打开仓门接收用户身份证 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1）
				var ret = GetIdCardContent();

				if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
				{	
					//调用关闭身份证阅读器接口
					CloseCardReader();
					
					//可以点击顶部菜单
					closeStatus = 0;
					
					//身份证信息：0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
					var idCardInfor = ret.substring(1,ret.length).split('~');
					
					//公民身份号码
					var idCardNo = idCardInfor[5];
					
					//取消定时器
					clearInterval(timeToken);
					
					document.getElementById("IDCard").value = idCardNo;
					
					// 将焦点定位到输入手机号码框
					changObj(document.getElementById('pan'), 2);
					document.getElementById("pan").focus();
				} 
				else if (ret == "-1")
				{
					setErrorInfoRegion("获取身份证文字内容失败，请手工输入您的身份证号信息。");
				}
				else if (ret == "2")
				{	
					setErrorInfoRegion("证件无法识别，请手工输入您的身份证号信息。");
				}						
			}
			catch (e) 
			{		
				setErrorInfoRegion("身份证读卡器读卡失败，请手工输入您的身份证号信息。");
			}
		}
		
		//计算读卡剩余时间
		function calLeftTime()
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			//读卡时长一共limitTime秒
			limitTime = limitTime - 1;
			
			document.getElementById("IDCard").value = limitTime;
			
			//读卡时间结束
			if (parseInt(document.getElementById("IDCard").value) <= 0 && submitFlag == 0)
			{
		        setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
		        document.getElementById("IDCard").value = "";
			}
			
			document.getElementById("IDCard").focus();
		        	
        	//调用关闭身份证阅读器接口
			CloseCardReader();
			
			//可以点击顶部菜单
			closeStatus = 0;
		}
		</script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="blank50">
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				</div>
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.输入身份证号</i>
									<span class="pl20 fl lh75">身份证号：</span>
									<input type="text" id="IDCard" name="IDCard" maxlength="18" style="font-size:22px;" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);"/>
								</li>
								<li class="fs20 clearfix" id="phone_input_2" >
									<i class="lh30">2.输入银行账号</i>
									<span class="pl20 fl lh75">银行账号：</span>
									<input type="text" id="pan" name="pan" maxlength="19" style="font-size:22px;" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);"/>
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
								var lastObj = document.getElementById('IDCard');
								var type = 0;
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
									
									numBoardBtns[i].onmouseup = function(){
									
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			//如果手机号码输入完成后自动跳转到身份证号输入框
										if (pangu_getStrLen(trim(lastObj.value)) == 18 
												&& document.getElementById("pan").value == "")
										{
											changObj(document.getElementById('pan'), 2);
											document.getElementById("pan").focus();
											return true;
										}
							   
									}					
								}
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										type = 0;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 18 && v != "#" && type == 1)
									{								
										lastObj.value += v;
									}
									else if (lastObj.value.length < 19 && !isNaN(v) && type == 0)
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
	</body>
	<script type="text/javascript">
		if ("" != "<%=errorMsg %>")
		{
			if ("1" == "<%=popupFlag %>")
			{
				alertRedErrorMsg("<%=errorMsg %>");
			}
			else
			{
				document.getElementById("errorMsg").innerHTML = "<%=errorMsg %>";
			}
		}
	</script>
</html>
