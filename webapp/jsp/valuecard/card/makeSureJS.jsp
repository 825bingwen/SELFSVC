<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String yuanMoney = (String) request.getAttribute("totalFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney.trim());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>银行卡缴费确认</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardPayLogVO.cardnumber" value='<s:property value="cardPayLogVO.cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeLogOID" name="cardPayLogVO.chargeLogOID" value="<s:property value='cardPayLogVO.chargeLogOID' />">
			<input type="hidden" id="printcontext" name="printcontext" value=""><!-- 银联打印信息 -->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
			<input type="hidden" id="fee" name="cardPayLogVO.fee" value="" />
			<input type="hidden" id="errorType" name="errorType" value="" />
			<!-- 有价卡类型 -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- 有价卡数量 -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- 有价卡面值 -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
			<%@include file="/jsp/customize/hub/common/fee/card/makeSure.jsp" %>
		</form>
	
		<script>
		
			// 提交标志 0:未提交 1:已提交
			var submitFlag = 0;
			
			// 核对时长
			var limitTime = 60;
			
			// 设置左侧页面的高亮显示
			document.getElementById("highLight7").className = "on";
			
			//计算剩余时间的句柄
			var timeToken;		
			
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
				document.actForm.action = "${sessionScope.basePath }valueCard/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
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
				
				//提交购买有价卡请求
				
				document.getElementById("fee").value = document.getElementById("tMoney").value;
				
				document.actForm.action = "${sessionScope.basePath }valueCard/buyValueCards.action?cmtPosResCode=" + document.getElementById('posResCode').value;
				document.actForm.submit();			
			}
			
			//银联卡扣款
			function fPosPay()
			{
				try
				{
					// document.getElementById("errorType").value = "add";
					
					//生产用。一天之内跟踪号不可以相同，一次签到之内的批次号是相同的
					var result = GetPosBatchNum();
					var dataArray = result.split("$");
					
					//获取跟踪号、批次号失败
					if (dataArray[0] != "0")
					{
						document.getElementById("errorType").value = "add";
						setException("获取跟踪号和批次号失败，无法使用银联卡进行充值。请取走您的银联卡。");
						
						return;
					}
					
					//获取跟踪号、批次号成功
					print_posNum = dataArray[1];
					print_batchNum = dataArray[2];
					
					document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
					
					//扣款之前记录日志
					var url = "${sessionScope.basePath }valueCard/addCardChargeLog.action";
					
					var params = "cardPayLogVO.servnumber=" + <s:property value='cardPayLogVO.servnumber' /> + "&cardPayLogVO.payType=1&tMoney=0";
					params = params + "&cardPayLogVO.cardnumber=" + document.getElementById("cardnumber").value + "&cardPayLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;
					params = params + "&cardPayLogVO.posNum=" + print_posNum + "&cardPayLogVO.batchNumBeforeKoukuan=" + print_batchNum;
					var loader = new net.ContentLoader(url, netload = function () {
							var resXml = this.req.responseText;
							var dataArray = resXml.split("~~");
							//记录日志成功
							if (dataArray[0] == "1") 
							{
								// 本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
								document.getElementById("errorType").value = "update";
								
								var oid = dataArray[1].replace(/[\r\n]/g, "");
								document.getElementById("chargeLogOID").value = oid;
								
								var posResCode = '';
								
								//提交扣款请求
								try
								{
									var ret ;
									
									// 关闭密码键盘
									ret = CloseCom();
									
									if (ret != "0" || ret != 0)
									{
										setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
										return;
									}
									
									// 银联扣款
									// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 缴费金额(分)
									var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardPayLogVO.cardnumber" />',"<%=fenMoney %>");
		
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
									//if (payReturnStr.substring(0,2) == "ER")
									posResCode = payReturnStr.substring(0,2);
									document.getElementById('posResCode').value = posResCode;
									if (payReturnStr.substring(0,2) != "00")
									{
										setException("银联卡缴费失败，请取走您的银联卡。原因："+payReturnStr);
										return;
									}
									
									// 扣款正常取值
									var resultstr = payReturnStr.substring(0,34);
									var printcontext = payReturnStr.substring(34,payReturnStr.length);
									
									document.getElementById('printcontext').value = printcontext;
									
									//测试用
									
									// var resultstr = "006222021602004724078A000000001234";
									// var printcontext = "302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
									
									// 湖北银联 授权码与凭证号 返回空值
									// var printcontext = "898420148145268~00167923~消费交易~6225887840088682~000725~~171633071883~20110817171633~~1";
									
									
									// 扣款成功 定长34 成功00
									if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
									{
									    
									    // 打开键盘串口、设置明文模式
										OpenCom();
										SetWorkMode(0);
										
										var printcontexts = printcontext.split('~');							
										document.getElementById("tMoney").value = printcontexts[9];
										
										//更新日志
										var url1 = "${sessionScope.basePath }valueCard/updateCardChargeLog.action";
						
										var params1 = "cardPayLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value + "&cardPayLogVO.unionpayuser=" + printcontexts[0];
										params1 = params1 + "&cardPayLogVO.unionpaycode=" + printcontexts[1] + "&cardPayLogVO.busiType=" + encodeURI(encodeURI(printcontexts[2]));
										params1 = params1 + "&cardPayLogVO.batchnum=" + printcontexts[4] + "&cardPayLogVO.authorizationcode=" + printcontexts[5];
										params1 = params1 + "&cardPayLogVO.businessreferencenum=" + printcontexts[6] + "&cardPayLogVO.unionpaytime=" + printcontexts[7];
										params1 = params1 + "&cardPayLogVO.vouchernum=" + printcontexts[8] + "&cardPayLogVO.unionpayfee=" + printcontexts[9];
										params1 = params1 + "&cardPayLogVO.status=01&cardPayLogVO.description=" + encodeURI(encodeURI('扣款成功')) + "&cardPayLogVO.posResCode=" + document.getElementById('posResCode').value;
										
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
			clearTimeout(timeOut);
			startclock();
			
			// 标识控件使用
			closeStatus = 1;
		</script>
</body>
</html>