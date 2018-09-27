<%--
 @User: zWX176560
 @De: 2013/9/17
 @comment: 显示用户真实信息
 @remark: create zWX176560 时间 2013/9/17 OR_SD_201309_66关于银联多渠道签约交费（无密绑定）的支撑需求（非网厅渠道） 版本号(R003C11L08n01)
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	// 终端机信息
	TerminalInfoPO terminalInfo2 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
	// 控件支持标志
	String termSpecial = terminalInfo2.getTermspecial();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Expires" CONTENT="0"/>
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">		
			<!-- 姓名 -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- 证件类型 -->
			<input type="hidden" id="id_type" name="bindBankCardPO.id_type" value="<s:property value='bindBankCardPO.id_type' />"/>
			<!-- 证件类型名称 -->
			<input type="hidden" id="idCardTypeText" name="bindBankCardPO.idCardTypeText" value="<s:property value='bindBankCardPO.idCardTypeText' />"/>
			<!-- 银行卡类型 -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="0"/>
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />"/>
			<%@ include file="/titleinc.jsp"%>
		 	<div class="main ycz" id="main">
		  		<%@ include file="/customer.jsp"%>
		        	<div class="b966 hidden" id="ycz">
					<div id="b_authen3_1" class="b_authen3_1"></div>
					<div class="blank30"></div>
						<div class=" pl62">
							<p class="tit_info" align="left"><span class="bg"></span>易充值签约</p>
							<div class="blank20 ml45" id="errorMsg"></div>
							<!--<div class="blank20"></div>-->
							<!--键盘+输入框+温馨提示-->
							<div class="keyboard_wrap authentication clearfix">
								<ul class="phone_num_list fl">
									<li class="sms_bg_1 fs20 mt15 clearfix " id="phone_input_1"><span class="fl lh75">身份证号码：</span>
										<input type="text" name="certid" id="certid" maxlength="18" class="text1 fl fs20" onfocus="MoveLast(event);" />
										<a href="javascript:void(0)" id="manuInput" class="btn_104 mt10" onmousedown="this.className='btn_104_hover fl mt10';" onmouseup="this.className='btn_104 fl mt10'; " onclick="inputCertid();return false;">手动输入</a>
										<!--小键盘-->
										<div class="numboard numboard_big fl" id="numBoard" style="display:none">
											<div class=" numbox">
												<div class="blank10"></div>
												<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
												<div class="clear"></div>
												<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
												<div class="clear"></div>
												<div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> </div>
												<div class="nright"> <a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'; inputCertidFinish();">1</a> </div>
												<div class="blank10"></div>
											</div>
										</div>
										<script type="text/javascript">
										var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
										var numBoardText=document.getElementById('certid');
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
											}
										}
										
										// 点击按键将其数字填充到输入框中
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
											else if (numBoardText.value.length < 18)
											{			
												numBoardText.value += v;																		
											}
											
											var r = numBoardText.createTextRange(); 
											r.collapse(false); 
											r.select();
										}
										 
										</script> 
										<!--小键盘end--> 
									</li>
									<li class="clearfix pt10 hidden" id="ycz_sd_text"><p class="fl pl62 fs18 lh30 yellow ml70">请手动输入身份证号码</p></li>
									
									<li class="clearfix pt20 hidden">
										<span class="fl lh75 fs18 width128 tr">卡类型：</span>
							  			<input type="button" id="debitCard" class="btn_on white" value="[√]借记卡" onmousedown="this.className='btn_off white'" onmouseup="this.className='btn_off white';" onclick="selectCard(0); return false;"/>
									    <input type="button" id="creditCard" class="btn_off white" value="[  ]信用卡" onmousedown="this.className='btn_off white'" onmouseup="this.className='btn_off white';" onclick="selectCard(1); return false;"/>
									</li>
									
									<li class="clearfix hidden  mt20">
										<span class="fl lh75 fs18 width128 tr"></span>
										<a href="javascript:void(0)" id="sd_btn" class=" btn_104" onmousedown="this.className='btn_104_hover'" onmouseup="this.className='btn_104';" onclick=" doSub();return false;">签约</a>
									</li>
									
									<li class="i_tips">
								  		<p class="tit_arrow"><span class="bg"></span>温馨提示：
									       <s:if test="userInfoTips != ''">
									       		<p class="tit_arrow_hide">
									       			<s:property value = 'userInfoTips' escape = 'false'/>
									       		</p>
									       	</s:if>
									       	<s:else>
										       	<p class="tit_arrow_hide">
										       		1."易充值（原短信快捷支付）业务目前仅限本省客户使用，暂不向外省移动客户提供此服务。<br />
													<%-- 2.目前信用卡签约的步骤比借记卡更为简便、易操作，且支持的银行更多，因此推荐您优先使用信用卡与手机号进行签约。<br /> --%>
													2."易充值"业务目前暂不支持退费。<br />
													3.交纳话费成功后,客户可在3个月内凭服务密码或身份证到手机号码归属地营业厅补打发票，赠送的话费不另外提供发票。公共事业等其他缴费项目请向缴费行业单位索取发票。
												</p>
											</s:else>
								        </p>
									</li>
							      	
								</ul>
							</div>
						</div>
						<div class="blank10"></div>
					</div>
				</div>
		    </div>                
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
			
			// 是否支持读取二代身份证信息标志,0不支持,1:支持
			var is2GIDFlag = <%=termSpecial.charAt(8)%>;	
			
			// 读卡等待时间
			var limitTime = 30;
			
			// 计算读卡剩余时间的句柄
			var timeToken;
			
			// 等待读卡的句柄
			var waitingToken;
			
			// 初始化身份证读卡器线程启动标志，1，已启动；0，未启动。如果为1时，用户主动取消操作，需调用关闭身份证阅读器接口
			var initCardReader = 0;
			
			// 防止页面重复提交
			var submitFlag = 0;
			
			// 提交
			function doSub() 
			{
				var certid = document.actform.certid.value;
				
				// 校验身份证号码
				if (certid == "")   
				{
					alertRedErrorMsg("请输入证件号。");
					return false;
				}
				
				// 校验输入身份证号与登记的证件号码是否符合
				if (document.actform.idCardNum.value != certid)
				{
					alertRedErrorMsg("该证件号与登记的证件号码不符。");
				 	return false;
				}
				
				if (submitFlag == 0)
				{
					submitFlag = 1;
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }bindBankCard/writeBankCardInfo.action";
					document.actform.submit();
				}			
			}
			
			// 选择银行卡类型
			function selectCard(type)
			{	
				if (type == 0)// 银行卡
				{
					document.getElementById('debitCard').value = '[√]借记卡';
					document.getElementById('creditCard').value = '[  ]信用卡';
					document.getElementById('debitCard').className = 'btn_on white';
					document.getElementById('creditCard').className = 'btn_off white';
					document.getElementById('bankCardType').value = "0";
				}
				else if (type == 1)// 信用卡
				{
					document.getElementById('debitCard').value = '[  ]借记卡';
					document.getElementById('creditCard').value = '[√]信用卡';
					document.getElementById('creditCard').className = 'btn_on white';
					document.getElementById('debitCard').className = 'btn_off white';
					document.getElementById('bankCardType').value = "1";
				}
			}
			
			// 返回上一页
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
			
			// 将光标移动到输入框最后面
			function MoveLast(e) 
			{
				var etarget = getEventTarget(e);
				var r = etarget.createTextRange();
				r.moveStart("character", etarget.value.length);
				r.collapse(true);
				r.select();
			}
		
			// 手工输入证件号码
			function inputCertid() 
			{
				document.getElementById("manuInput").style.display = 'none';
				document.getElementById("numBoard").style.display = '';
				
				document.getElementById("ycz").className = 'b966 hidden';
				document.getElementById("b_authen3_1").style.display = 'block';
			}
			
			// 手工输入证件号码完成后，点击确认按钮
			function inputCertidFinish() 
			{
				document.getElementById("manuInput").style.display = '';
				document.getElementById("numBoard").style.display = 'none';
				
				document.getElementById("ycz").className = 'b966 hidden';
				document.getElementById("b_authen3_1").style.display = 'none';
			}
			
			// 页面载入时加载身份证读卡器获取用户身份证信息
			function bodyLoad()
			{
			    document.getElementById("certid").focus();
			    
			    // 如果证件号不为空时，则不调用身份证读卡器
				if (document.getElementById("certid").value != "")
				{
					return;
				}
			    
			    // 判断如果证件类型为"01"身份证时，启动身份证读卡器设备
			    if (document.getElementById("id_type").value == "01")
			    {
			    	// 判断自助终端是否支持身份证读卡器
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
						
						//开启首页及退出按钮的响应方式
			            document.getElementById("homeBtn").disabled = false;
			            document.getElementById("quitBtn").disabled = false;
			            return;
					}
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
						
						//开启首页及退出按钮的响应方式
			            document.getElementById("homeBtn").disabled = false;
			            document.getElementById("quitBtn").disabled = false;
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
					
					//开启首页及退出按钮的响应方式
		            document.getElementById("homeBtn").disabled = false;
		            document.getElementById("quitBtn").disabled = false;
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
				
				setErrorInfoRegion("请在身份证读卡器上刷一下您的身份证，系统会将您的身份证号输入到页面。剩余" + limitTime + "秒");
				
				//读卡时间结束
				if (parseInt(limitTime) <= 0 && submitFlag == 0)
				{
		        	setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
				}
				
				document.getElementById("certid").focus();
			        	
		    	//调用关闭身份证阅读器接口
				//生产用
				CloseCardReader();
				
				//可以点击顶部菜单
				closeStatus = 0;
			}
			
			//设置错误信息
			function setErrorInfoRegion(errMsg)
			{
				document.getElementById("errorMsg").innerHTML = errMsg;		    
			}
		</script>
	</body>
</html>
