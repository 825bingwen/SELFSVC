<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// 投币超时时间
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);

// 终端信息
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 现金识币器特性
String isCashEquip = termInfo.getTermspecial().substring(3, 4);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion }"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />" />
		<input type="hidden" id="chargeLogOID" name="cardPayLogVO.chargeLogOID" value="<s:property value='cardPayLogVO.chargeLogOID' />" />
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType'/>"/>
		<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=""/>
		<input type="hidden" id="fee" name="cardPayLogVO.fee" value="" />
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<!-- 有价卡类型 -->
		<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
		<!-- 有价卡数量 -->
		<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
		<!-- 有价卡面值 -->
		<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		
		<%@include file="/jsp/customize/hub/common/fee/cash/cashCharge.jsp" %>
	</form>
	<script>
	
		//防止重复提交
		var submitFlag = 0;
		
		// 设置页面左侧菜单高亮
		document.getElementById("highLight4").className = "on";
		
		// 投币的时长，单位秒		
		var payMoneyTime = "<%=timeout %>";
		
		// 剩余时间
		var leftTime = payMoneyTime;
		
		// readCash定时器句柄
		var readCashToken;
		
		// 关闭识币器。0：不需要；1：需要
		var needClose = 0;
		
		// 提交标记，0表示未确认提交缴费，1表示已确认提交缴费
		var submitFlag = 0;
		
		function goback(menuid)
		{
			// 已投币
			if (document.getElementById("tMoney").value != "" 
					&& parseInt(document.getElementById("tMoney").value) > 0)
			{
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// 返回时，清除定时任务。同时关闭现金识币器			
				if (needClose == 1)
				{
					fCloseCashBill();
				}
				
				clearInterval(readCashToken);
				
				document.getElementById("curMenuId").value = menuid;
				
				document.actform.action = "${sessionScope.basePath }valueCard/qryPayType.action";
				document.actform.submit();
			}
		}
			
		// 提交
		function doSub()
		{
			// 未投币
			if (document.getElementById("tMoney").value == "" 
					|| parseInt(document.getElementById("tMoney").value) <= 0)
			{
				return;
			}
			
			// 尚未提交请求
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// 修改缴费重复提交问题 change by LiFeng
				wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口例子
			
				// 关闭现金识币器
				if (needClose == 1)
				{
					fCloseCashBill();
				}
				
				// 清除定时任务
		        clearInterval(readCashToken);
				
				// 用户交费金额
				document.getElementById("fee").value = document.getElementById("tMoney").value;
				
				// 提交缴费请求
				document.actform.action = "${sessionScope.basePath }valueCard/buyValueCards.action";
				document.actform.submit();
			}
		}
			
		// 出现异常
		function setException(errorMsg) 
		{			
			document.getElementById("errormessage").value = errorMsg;
			
			// 出现异常后，清除定时任务。同时关闭现金识币器			
			if (needClose == 1)
			{
				fCloseCashBill();
			}
			
			// 清除定时任务
			clearInterval(readCashToken);
			
			// 异常处理，只要出现了异常就要记录日志  
			document.actform.action = "${sessionScope.basePath }valueCard/goCashError.action";
			document.actform.submit();
		}		
		
		// 获取手机号码
		// 初始化现金识币器
		// 获取投币流水
		// 使首页和退出按钮不可用
		// 启动循环获取投币金额
		function loadContent() 
		{
			var serverNumber = "<s:property value='cardPayLogVO.servnumber' />";
			if (serverNumber == null || serverNumber == "")
		    {            
				setException("对不起，用户信息获取失败，请返回重新操作。");
				return;
			}
		          
			<%if (!"1".equals(isCashEquip)){%>
				  setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
				  return;
			<%}%>
		    
		    try 
		    {
		    	// 初始化现金识币器(正常返回 0,20110509143345)
				var initData = initCashEquip(serverNumber);
			   	
			   	// 标识控件使用
			   	closeStatus = 2;
			   	
		        if (initData.substring(0, 1) != "0") 
		        {
		        	setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
		            return;                    
		        }
		        else
		        {
		        	//现金识币器初始化成功。不再接收用户投币时，需要关闭。
		        	needClose = 1;
		        	
		        	//获取投币流水
		        	document.getElementById("terminalSeq").value = initData.substring(2);
		        	
		        	//初始化成功，就需要关闭识币器。而首页、退出按钮无法执行此操作，所以禁用”首页“、“退出”按钮
		        	document.getElementById("homeBtn").disabled = true;
		        	document.getElementById("quitBtn").disabled = true;
		        	
		        	startclock();
		        }               
		    } 
		    catch(e) 
		    {
		        setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
		        return;
		    }           
		}
		
		//循环获取投币金额
		function startclock() 
		{
			var msg = preparecash();
			
			//识币器状态检测失败，不允许投币，显示异常信息
			if (msg != "") 
			{
				setException(msg);
				return;
			}		
			
			try
			{
				// 获取投币金额,循环调用,每隔1秒执行一次
				readCashToken = setInterval("doCash()", 1000);
			}
			catch (e) 
			{
				setException("对不起，计时器发生异常，无法使用现金进行充值，请使用其它方式或者到营业厅进行充值。");
			}
		}
		
		//检测识币器状态
		function preparecash()
		{
			var msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式值。";
		
			try 
			{
				//检测识币器状态 0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备
				var cashStatus = checkCashStatus();
		
				if (cashStatus == 0)
				{
					msg = "";
				}
				else if (cashStatus == 1) 
				{
					msg = "对不起，现金识币器状态异常，无法使用现金进行充值，请选用其它方式。";
				}
				else if (cashStatus == 2) 
				{
					msg = "对不起，钱箱已满，无法使用现金进行充值，请选用其它方式。";
				}
				else if (cashStatus == 3) 
				{
					msg = "对不起，钱箱未正常关闭，无法使用现金进行充值，请选用其它方式。";
				} 
				else if (cashStatus == 4) 
				{
					msg = "对不起，钱箱入钞口被夹，无法使用现金进行充值，请选用其它方式。";
				}
				else if (cashStatus == 5) 
				{
					msg = "对不起，钱箱被夹，无法使用现金进行充值，请选用其它方式。";
				} 
				else if (cashStatus == 6) 
				{
					msg = "对不起，现金识币器发生未知错误，无法使用现金进行充值，请选用其它方式。";
				}
				else if (cashStatus == 9) 
				{
					msg = "对不起，现金识币器不存在，无法使用现金进行充值，请选用其它方式。";
				}			
			}
			catch (e) 
			{
				msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式。";
			}
			
			// 返回
			return msg;
		}
		
		// 获取投币金额
		// 循环调用，每隔1秒执行一次
		function doCash() 
		{
			if (leftTime <= 0)
			{
				return;
			}
			
			try 
			{	
				// 获取投币金额 0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
				var ret = getOnceCash();
		
				if (ret > 0) 
				{
					// 标识控件使用
			   		closeStatus = 1;
					
					// 时间重新开始
					leftTime = "<%=timeout %>";
					
					// 显示剩余时间
					document.getElementById("tTime").value = leftTime;
					
					// 投币后，不能返回
		            document.getElementById("backBtn").disabled = true;
					
					// 计算投币金额 
					document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
					
					// 取消交易按扭不显示
					document.getElementById('cancelBusi').style.display = "none";
					
					// 投币金额大于0时取消交易按扭不显示,缴费按扭显示
					if (parseInt(document.getElementById("tMoney").value) > 0)
					{
						document.getElementById('bCommitBusi').style.display = "none";
						document.getElementById('commitBusi').style.display = "block";
						document.getElementById("promptMsg").innerHTML = "已投入纸币，请点击缴费按钮";
					}
					else
					{
						document.getElementById('bCommitBusi').style.display = "block";
					}
					
					var postStr = "cardPayLogVO.chargeLogOID="+getValue("chargeLogOID")+"&cardPayLogVO.cashMoney="+ret+"&cardPayLogVO.terminalSeq="+getValue("terminalSeq");  
					
					// 同步调用，更新现金投币日志
					var loader = new net.ContentLoaderSynchro(
						"${sessionScope.basePath}valueCard/updateCashChargeLog.action", netload = function () 
						{	
							if (this.req.responseText != "1")
							{
								setException("现金交费记录现金投币明细日志失败。");
								
								return;
							}
						}, 
						null, "POST", postStr, null);
				}
				else
				{
					// 投币时长一共timeout秒
					leftTime = leftTime - 1;
					
					// 显示剩余时间
					document.getElementById("tTime").value = leftTime;
					
					//投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
					if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
					{           	 	
				        //投币金额大于0
				       	if (parseInt(document.getElementById("tMoney").value) > 0) 
						{
							// 提交缴费
							doSub();
						} 
						else 
						{
							// 取消交易
							cancelBusi();
							//setException("投币时间结束，投币金额为0，无法进行缴费操作");
						}
					}
				}				
			}
			catch (e) 
			{
				setException("对不起，获取投币金额失败，无法使用现金进行充值，请选用其它方式。");
			}
		}
		
		// 取消交易
		function cancelBusi()
		{		
			if (needClose == 1)
			{
				// 关闭现金识币器
				fCloseCashBill();
			}
			
			// 清除定时任务
			clearInterval(readCashToken);
			
			// 返回首页
			setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
		}
		
		clearTimeout(timeOut);
		loadContent();
	</script>

</body>
</html>