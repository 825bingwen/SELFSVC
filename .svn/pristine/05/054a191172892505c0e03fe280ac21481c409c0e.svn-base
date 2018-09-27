<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String yuanMoney = (String) request.getAttribute("yingjiaoFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>银行卡缴费确认</title>
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
			//modified by xkf57421 for XQ[2011]_10_082 begin
			//document.actForm.action = "${sessionScope.basePath }charge/goCardError.action";
			document.actForm.action = "${sessionScope.basePath }charge/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
			//modified by xkf57421 for XQ[2011]_10_082 end
			document.actForm.submit();
			
		}
		
		// 与BOSS计帐(银联卡)
		function payToBoss() 
		{
			if (document.getElementById("tMoney").value == "" 
					|| parseInt(document.getElementById("tMoney").value) <= 0)
			{
				return;
			}
			
			//提交缴费请求
			document.actForm.target = "_self";
			//modified by xkf57421 for XQ[2011]_10_082 begin
			document.actForm.action = "${sessionScope.basePath }charge/cardChargeCommit.action?cmtPosResCode=" + document.getElementById('posResCode').value;;
			//modified by xkf57421 for XQ[2011]_10_082 end
			document.actForm.submit();			
		}
		
		//银联卡扣款
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				//生产用。一天之内跟踪号不可以相同，一次签到之内的批次号是相同的
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				//获取跟踪号、批次号失败
				if (dataArray[0] != "0")
				{
					setException("获取跟踪号和批次号失败，无法使用银联卡进行充值。请取走您的银联卡。");
					
					return;
				}
				
				//获取跟踪号、批次号成功
				print_posNum = dataArray[1];
				print_batchNum = dataArray[2];
				
				document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
				
				//扣款之前记录日志
				var url = "${sessionScope.basePath }charge/addChargeLog.action";
				
				var params = "servnumber=" + <s:property value='servnumber' /> + "&paytype=1&tMoney=" + <s:property value='yingjiaoFee' />;
				params = params + "&cardnumber=" + document.getElementById("cardnumber").value + "&terminalSeq=" + document.getElementById("terminalSeq").value;
				params = params	+ "&status=00&description=" + encodeURI(encodeURI('扣款之前记录日志')) + "&acceptType=" + document.getElementById("acceptType").value;
				params = params	+ "&servRegion=" + document.getElementById("servRegion").value + "&posnum=" + print_posNum + "&batchnumbeforekoukuan=" + print_batchNum;
			
				var loader = new net.ContentLoader(url, netload = function () {
						var resXml = this.req.responseText;
						var dataArray = resXml.split("~~");
						//记录日志成功
						if (dataArray[0] == "1") 
						{
							//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
							document.getElementById("errorType").value = "update";
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							document.getElementById("chargeLogOID").value = oid;
							
							//add by xkf57421 for XQ[2011]_10_082 begin
							var posResCode = '';
							//add by xkf57421 for XQ[2011]_10_082 end
							
							//提交扣款请求
							try
							{
								var ret ;
								
								// 关闭密码键盘
								ret = CloseCom()
								if (ret != "0" || ret != 0)
								{
									setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
									return;
								}
								
								// 银联扣款
								// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 缴费金额(分)
								var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',"<%=fenMoney %>");

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
								
								//modified by xkf57421 for XQ[2011]_10_082 begin
								// 扣款失败转到异常界面
								//if (payReturnStr.substring(0,2) == "ER")
								posResCode = payReturnStr.substring(0,2);
								document.getElementById('posResCode').value = posResCode;
								if (payReturnStr.substring(0,2) != "00")
								{
									setException("银联卡缴费失败，请取走您的银联卡。原因："+payReturnStr);
									return;
								}
								//modified by xkf57421 for XQ[2011]_10_082 end
								
								// 扣款正常取值
								var resultstr = payReturnStr.substring(0,34);
								var printcontext = payReturnStr.substring(34,payReturnStr.length);
								
								document.getElementById('printcontext').value = printcontext;
								
								//测试用
								/**
								var resultstr = "006222021602004724078A000000001234";
								//var printcontext = "302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
								
								// 湖北银联 授权码与凭证号 返回空值
								var printcontext = "898420148145268~00167923~消费交易~6225887840088682~000725~~171633071883~20110817171633~~1";
								**/	
								
								// 扣款成功 定长34 成功00
								if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
								{
								    
								    // 打开键盘串口、设置明文模式
									OpenCom();
									SetWorkMode(0);
									
									var printcontexts = printcontext.split('~');							
									document.getElementById("tMoney").value = printcontexts[9];
									
									//更新日志
									var url1 = "${sessionScope.basePath }charge/updateCardChargeLog.action";
					
									var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value + "&unionpayuser=" + printcontexts[0];
									
									<%-- modify by sWX219697 2015-7-20 busitype改为busiType findbugs修改 --%>
									params1 = params1 + "&unionpaycode=" + printcontexts[1] + "&busiType=" + encodeURI(encodeURI(printcontexts[2]));
									params1 = params1 + "&batchnum=" + printcontexts[4] + "&authorizationcode=" + printcontexts[5];
									params1 = params1 + "&businessreferencenum=" + printcontexts[6] + "&unionpaytime=" + printcontexts[7];
									params1 = params1 + "&vouchernum=" + printcontexts[8] + "&unionpayfee=" + printcontexts[9];
									params1 = params1 + "&status=01&description=" + encodeURI(encodeURI('扣款成功')) + "&initPosResCode=" + document.getElementById('posResCode').value;
									
									var loader1 = new net.ContentLoader(url1, netload = function () {
										var resXml1 = this.req.responseText;
										
										//更新日志成功
										if (resXml1 == "1" || resXml1 == 1)
										{
											//交费
											payToBoss();									
										}
										//更新日志失败
										else
										{	
											setException("缴费失败，请取走您的银联卡和小票，凭小票到到营业厅办理退款手术，谢谢使用!");
											return;
										}								
									}, null, "POST", params1, null);
								}
								//扣款失败
								else
								{
									setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
									return;
								}
									
							}
							catch (e)
							{
								
								if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
								{
									setException("银联卡扣款成功，但是交费失败。请取走您的银联卡。");
								}
								else
								{
									setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
								}
							}							
						}
						//记录日志失败
						else 
						{						
							setException("日志记录失败，不能进行银联卡缴费操作。请取走您的银联卡。");
						}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
				{
					setException("银联卡扣款成功，但是交费失败。请取走您的银联卡。");
				}
				else
				{
					setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
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
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">		
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCARD %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="">
			<input type="hidden" id="printcontext" name="printcontext" value=""><!-- 银联打印信息 -->
			<!-- add by xkf57421 for XQ[2011]_10_082 begin-->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			<!-- add by xkf57421 for XQ[2011]_10_082 end-->
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择支付方式</a>
    					<a href="javascript:void(0)">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 免责声明</a>
    					<a href="javascript:void(0)">5. 插入银联卡</a> 
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)" class="on">7. 核对信息</a>
    					<p class="recharge_tips">核对交费号码</p>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> 元</p>
							<p class="tit_info"><span>核对信息时长共</span><span class="yellow">60</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />秒</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">交费</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--弹出框 正在处理 请稍候-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
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
