<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>银行卡扣款确认</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script language="javascript">
			// 提交标志 0:未提交 1:已提交
			var submitFlag = 0;
			
			// 核对时长
			var limitTime = 60;
			
			//计算剩余时间的句柄
			var timeToken;		
			
			//82、220 返回		
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
				
				//返回
				if (key == 82 || key == 220) 
				{
					goback("<s:property value='curMenuId' />");
					return;
				}
				else if (key == 13 || key == 89 || key == 221)
				{
					openWindow_wait('pls_wait');
				}		
			}
		
		function goback(menuid)
		{
			setException("缴费操作被取消，请注意取走您的银联卡。");
		}
		
		/********************软POS缴费流程*******************************/
		/**
		1：自助缴费终端向银商业务前置发起银行卡扣款请求。
		2：银商业务前置向银联POSP发起银行卡扣款交易。
		3：银联POSP向银商业务前置返回银行卡扣款结果。
		4：银商业务前置将银行卡扣款结果转发给自助缴费终端。
		5：自助缴费终端向自助缴费终端综合管理平台发起移动记账请求。
		6：自助缴费终端综合管理平台向BOSS发起移动记账请求终端。
		7：BOSS向自助缴费终端综合管理平台返回记账结果。
		8：自助缴费终端综合管理平台将记账结果返回给自助终端
		**/
		/**************************************************************/
		
		/************************************************
						软POS流程
		************************************************/
		var print_posNum = "";// 跟踪号
		var print_batchNum = "";// 批次号
		
		//出现异常
		function setException(errorMsg) 
		{
			submitFlag = 1;	//提交标记
			
			// 清除定时任务
			clearInterval(timeToken);
			
			// 显示错误信息
			document.getElementById("errormessage").value = errorMsg;
				
			// 异常处理，只要出现了异常就要记录日志  
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }activitiesRec/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
			document.actForm.submit();
			
		}
		
		// 与BOSS计帐(银联卡)
		function payToBoss() 
		{
			if (document.getElementById("unionpayfee").value == "" 
					|| parseInt(document.getElementById("unionpayfee").value) <= 0)
			{
				return;
			}
			
			//提交缴费请求
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }activitiesRec/cardFinish.action?cmtPosResCode=" + document.getElementById('posResCode').value;;
			document.actForm.submit();			
		}
		
		//银联卡扣款
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				// 生产用。一天之内跟踪号不可以相同，一次签到之内的批次号是相同的
				// 成功返回:0$跟踪号$批次号;
				// 失败返回:-1
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				// 获取跟踪号、批次号失败
				if (dataArray[0] != "0")
				{
					setException("获取跟踪号和批次号失败，无法使用银联卡进行活动受理。请取走您的银联卡。");
					return;
				}
				
				// 获取跟踪号、批次号成功
				print_posNum = dataArray[1];
				print_batchNum = dataArray[2];
				
				// 终端流水(跟踪号+批次号)
				document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
				
				// 扣款之前记录日志
				var url = "${sessionScope.basePath }activitiesRec/addChargeLog.action";
				
				// 组装扣款之前记录日志参数
				var params = "";
				params = params + "servnumber=<s:property value='servnumber' />";// 手机号码
				params = params + "&prepayFee=<s:property value='prepayFee' />";// 扣款金额
				params = params + "&cardnumber=" + document.getElementById("cardnumber").value;// 银联卡号
				params = params + "&terminalSeq=" + document.getElementById("terminalSeq").value;// 流水号(跟踪号+批次号)
				params = params	+ "&status=00";// 状态
				params = params + "&description=" + encodeURI(encodeURI('扣款之前记录日志'));
				params = params + "&posnum=" + print_posNum ;// 跟踪号
				params = params + "&batchnumbeforekoukuan=" + print_batchNum;// 批次号
			    
			    // ajax执行扣款之前记录日志,并取得chargeLogOID
				var loader = new net.ContentLoader(url, netload = function () {
						
						// 返回信息（成功：1~~chargeLogOID 失败：0）
						var resXml = this.req.responseText;
						
						// 更新成数组
						var dataArray = resXml.split("~~");
						
						//记录日志成功
						if (dataArray[0] == "1") 
						{
							//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
							document.getElementById("errorType").value = "update";
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							document.getElementById("chargeLogOID").value = oid;
							
							// 终端返回码
							var posResCode = '';
							
							//提交扣款请求
							try
							{
								var ret ;
								
								// 关闭密码键盘
								ret = CloseCom()
								if (ret != "0" || ret != 0)
								{
									setException("银联卡扣款失败，不能继续进行活动受理操作。请取走您的银联卡。");
									return;
								}
								
								// 银联扣款
								// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 扣款金额(分)
								var prepayFee_fen = <s:property value='prepayFee' /> * 100;
								var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',prepayFee_fen);
								
								// 打开键盘串口、设置明文模式
								ret = OpenCom();
								if (ret != "0" || ret != 0)
								{
									window.parent.showFrmErr("警告:打开键盘串口失败！");
								}
								ret = SetWorkMode(0);
								if (ret != "0" || ret != 0)
								{
									window.parent.showFrmErr("警告:设置键盘模式失败！");
								}
								
								// 扣款失败转到异常界面
								posResCode = payReturnStr.substring(0,2);
								document.getElementById('posResCode').value = posResCode;
								if (payReturnStr.substring(0,2) != "00")
								{
									setException("银联卡扣款失败，请取走您的银联卡。原因："+payReturnStr);
									return;
								}
								
								// 扣款正常取值
								var resultstr = payReturnStr.substring(0,34);
								var printcontext = payReturnStr.substring(34,payReturnStr.length);
								
								document.getElementById('printcontext').value = printcontext;
								
								/**
								//测试用
								var resultstr = "006222021602004724078A000000001234";
								//var printcontext = "302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
								
								// 湖北银联 授权码与凭证号 返回空值
								var printcontext = "898420148145268~00167923~消费交易~6225887840088682~000725~~171633071883~20110817171633~~10000";
								**/
								
								// 扣款成功 定长34 成功00
								if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
								{
								    
								    // 打开键盘串口、设置明文模式
									OpenCom();
									SetWorkMode(0);
									
									// 银联打印信息
									var printcontexts = printcontext.split('~');
										
									// 银联扣款金额
									document.getElementById("unionpayfee").value = printcontexts[9];
									
									//更新日志
									var url1 = "${sessionScope.basePath }activitiesRec/updateCardChargeLog.action";
									
									var params1 = "";
									params1 = params1 + "chargeLogOID=" + document.getElementById("chargeLogOID").value;// id
									params1 = params1 + "&unionpayuser=" + printcontexts[0];// 商户编号
									params1 = params1 + "&unionpaycode=" + printcontexts[1];// 终端编号
									
									<%-- modify by sWX219697 2015-7-20 09:56:43 busitype改为busiType findbugs修改 --%>
									params1 = params1 + "&busiType=" + encodeURI(encodeURI(printcontexts[2]));
									params1 = params1 + "&batchnum=" + printcontexts[4];// 批次号
									params1 = params1 + "&authorizationcode=" + printcontexts[5];// 授权码
									params1 = params1 + "&businessreferencenum=" + printcontexts[6];// 交易参考号
									params1 = params1 + "&unionpaytime=" + printcontexts[7];// 银联扣款时间
									params1 = params1 + "&vouchernum=" + printcontexts[8];// 评证号
									params1 = params1 + "&unionpayfee=" + printcontexts[9];// 银联扣款金额
									params1 = params1 + "&status=01";// 状态
									params1 = params1 + "&description=" + encodeURI(encodeURI('扣款成功'));// 描述信息
									params1 = params1 + "&initPosResCode=" + document.getElementById('posResCode').value;// POS返回码
									
									var loader1 = new net.ContentLoader(url1, netload = function () {
										
										// 返回信息
										var resXml1 = this.req.responseText;
										
										// 更新日志成功
										if (resXml1 == "1" || resXml1 == 1)
										{
											// 交费
											payToBoss();							
										}
										// 更新日志失败
										else
										{	
											setException("活动受理失败，请取走您的银联卡和小票，凭小票到到营业厅办理退款，谢谢使用!");
											return;
										}								
									}, null, "POST", params1, null);
								}
								//扣款失败
								else
								{
									setException("银联卡扣款失败，不能继续进行活动受理操作。请取走您的银联卡。");
									return;
								}
									
							}
							catch (e)
							{
								
								if (document.getElementById("unionpayfee").value != "" && parseInt(document.getElementById("unionpayfee").value) > 0)
								{
									setException("银联卡扣款成功，但是活动受理失败。请取走您的银联卡。");
								}
								else
								{
									setException("银联卡扣款失败，不能继续进行活动受理操作。请取走您的银联卡。");
								}
							}							
						}
						// 记录日志失败
						else 
						{						
							setException("日志记录失败，不能进行银联卡缴费操作。请取走您的银联卡。");
						}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("unionpayfee").value != "" && parseInt(document.getElementById("unionpayfee").value) > 0)
				{
					setException("银联卡扣款成功，但是活动受理失败。请取走您的银联卡。");
				}
				else
				{
					setException("银联卡扣款失败，不能继续进行活动受理操作。请取走您的银联卡。");
				}
			}				
		}
		
		//提交交费
		function doSub() 
		{		
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				fPosPay();				
			}
		}
		
		//计算剩余时长
		function waitForSubmit() 
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			//读密码时长一共limitTime秒
			limitTime = limitTime - 1;
						
			document.getElementById("tTime").value = limitTime;
						
			//读密码时间结束
			if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
			{           	
				//发生异常，增加日志
				document.getElementById("errorType").value = "add";
				
				setException("核对信息超时，请取走您的银联卡。");
			}
		}
		
		// 启动计时器
		function startclock() 
		{
			try 
			{
				timeToken = setInterval("waitForSubmit()", 1000);
			}
			catch (e) 
			{
				//发生异常，增加日志
				document.getElementById("errorType").value = "add";
				
				setException("核对信息失败，请取走您的银联卡。");
			}
		}
		
		function doLoad()
		{
			//禁用”首页“、“退出”按钮
	        document.getElementById("homeBtn").disabled = true;
	        document.getElementById("quitBtn").disabled = true;
		}
		</script>
	</head>
	<body scroll="no" onload="doLoad()">
		<form name="actForm" method="post">	
			<!-- 营销推荐标识 -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
				
			<input type="hidden" id="payType" name="payType" value="4"/>
			
			<!-- 活动组名称 -->
			<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
			<!-- 档次名称 -->
			<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
			<!-- 活动编码 -->
			<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
			<!-- 档次编码 -->
			<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
			<!-- 奖品编码串 -->
			<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
			<!-- 手机imeiid号 -->
			<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
			<!-- 赠品总价 -->
			<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
			<!-- 赠品数量 -->
			<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
			
			<%-- modify by sWX219697 2015-7-20 totalfee改为totalFee,findbugs修改--%>
			<!-- 用户投入的费用金额 -->
			<input type="hidden" id="totalFee" name="totalFee" value='<s:property value="#request.totalFee" />'/>
			<!-- 受理金额_自助终端定义的受理金额 -->
			<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
			<!-- 银联扣款金额 -->
			<input type="hidden" id="unionpayfee" name="unionpayfee" value=''>
			<!-- 是否需要退卡 0:不需要 1:需要 -->
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<!-- 银联卡号 -->
			<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
			<!-- 终端流水 -->
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<!-- 异常类型 -->
			<input type="hidden" id="errorType" name="errorType" value=''>
			<!-- 异常信息 -->
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<!-- 流水号 -->
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="">
			<!-- 银联打印信息 -->
			<input type="hidden" id="printcontext" name="printcontext" value="">
			<!-- 扣款返回码 -->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>活动受理流程：</h2>
						<div class="blank10"></div>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
    					<a title="插入储蓄卡" href="#">4. 插入储蓄卡</a>
    					<a title="输入密码" href="#">5. 输入密码</a>
    					<a title="核对信息" href="javascript:void(0)" class="on">6. 核对信息</a>
    					<a title="完成" href="javascript:void(0)">7. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">应缴金额：<span class="yellow fs22"><s:property value="#request.prepayFee" /></span> 元</p>
							<p class="tit_info"><span>核对信息时长共</span><span class="yellow">60</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />秒</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a title="交费" href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">交费</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--弹出框 正在处理 请稍候-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" /></p>
                  	<p class="tips_txt">正在处理，请稍候......</p>
                 	<div class="line"></div>
                  	<div class="popup_banner"></div>                
                </div>

				<script type="text/javascript">
					openWindow_wait = function(id){
						//清除定时任务
						clearInterval(timeToken);
						wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口例子
					    setTimeout("doSub()", 500);
					}				
				</script>
				<!--弹出窗结束-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
	clearTimeout(timeOut);
	startclock();
	
	// 标识控件使用
	closeStatus = 1;
	</script>
</html>
