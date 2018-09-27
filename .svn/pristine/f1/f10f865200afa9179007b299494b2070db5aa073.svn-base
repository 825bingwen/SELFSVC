<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>读卡页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js?ver=${jsVersion }"></script>
</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />">
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<input type="hidden" id="cardnumber" name="cardPayLogVO.cardnumber" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
			<input type="hidden" id="errorType" name="errorType" value="" />
			<!-- 有价卡类型 -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- 有价卡数量 -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- 有价卡面值 -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
			<%@include file="/jsp/customize/hub/common/fee/card/readCard.jsp" %>
		</form>
		<script>
		
			// 设置左侧页面的高亮显示
			document.getElementById("highLight5").className = "on";
			
			function goback(menuid)
			{
				setException("缴费操作被取消");
			}
			
			// 读卡等待时间
			var limitTime = <%=limitTime %>;
			
			//等待读卡的句柄
			var waitingToken;
			
			// 提交标志
			var submitFlag = 0;
			
			// 等待读取标志，1，成功；0，失败。如果为1时，用户主动取消缴费操作，需调用取消刷卡接口
			var readingCard = 0;
			
			// 出现异常
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
				    // 提交标志置为1
					submitFlag = 1;
					
					// 清除定时任务
					clearInterval(waitingToken);
					
					// 显示错误信息
					document.getElementById("errormessage").value = errorMsg;
					
					document.getElementById("errorType").value = "add";
					
					//等待读卡线程启动成功，出现异常后，需关闭			
					if (readingCard == 1)
					{
						CancelReadingUserCard();
					}
					
					//异常处理，只要出现了异常就要记录日志  
					document.readCardForm.action = "${sessionScope.basePath }valueCard/goCardError.action";
					document.readCardForm.submit();
				}			
			}
			
			// 读卡完成后提交转到输入密码页面
			function doSub()
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
				
					//清除定时任务
					clearInterval(waitingToken);
					
					// 执行提交
					document.readCardForm.action = "${sessionScope.basePath }valueCard/inputCardPwd.action";
					document.readCardForm.submit();	
				}			
			}
			
			// 获得读卡标志
			function getReadCardStatus() 
			{
				//读卡时间结束
				if (limitTime <= 0 && submitFlag == 0)
				{           	
			       	setException("读卡超时，无法进行缴费操作");
			       	return;
				}
				
				try 
				{
					// 打开仓门接收用户银行卡 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1） 
					// 0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
					var ret = ReadUserCardFinished();
			
					// 尚未读取到卡信息
					if (ret == "1" || ret == 1)
					{
						// 读卡时长一共limitTime秒
						limitTime = limitTime - 1;
						
						// 设定界面显示
						document.getElementById("tTime").value = limitTime;
						
						// 标识控件未使用
					    //closeStatus = 0;
					}
					// 读卡成功
					else if (ret == "0" || ret == 0)
					{	
					    // 读取完毕
						readingCard = 0;
						
						// 需要退卡
						document.getElementById("needReturnCard").value = "1";
						
						// 在接收到ReadBankCardFinished()事件读卡成功的通知后，应及时调用此接口，并保存到页面的元素中，以确保后续的页面能获取到银行卡号信息。
						var cardNO = GetUserCardInfo();
						
						if (cardNO == "")
						{
							//获取卡信息失败
							setException("获取卡信息失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
						}
						else
						{
							// 记录卡号
							document.getElementById("cardnumber").value = cardNO;
							
							// 提交转到输入密码页面
							doSub();
						}									
					} 
					// 读卡失败
					else if (ret == "-1")
					{
						//读取失败
						readingCard = 0;
						
						//change by LiFeng 修改读卡失败不退卡问题
						// 需要退卡
						document.getElementById("needReturnCard").value = "1";
						
						setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
					}				
				}
				catch (e) 
				{
					readingCard = 0;//读取出现异常
					
					setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
				}
			}
			
			//设置时间计算周期
			function startclock() 
			{	
				try 
				{
					waitingToken = setInterval("getReadCardStatus()", 1000);
				}
				catch (e) 
				{
					setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
				}
			}
			
			//页面加载时执行
			function bodyLoad() 
			{
				try 
				{	
				    // 接口调用返回信息
					var ret;
					
					// 打开提示框
					alertJd("正在加载,请稍候...");
					
					// 标识控件使用
					closeStatus = 1;
			
					// 关闭键盘串口
					try
					{
						ret = CloseCom();
					}
					catch(e)
					{
						// 打开键盘串口、设置明文模式
						OpenCom();
						SetWorkMode(0);
						
						setException("关闲密码键盘串口发生异常！");
						return;
					}
					
					if (ret != "0" && ret != 0) 
					{
						// 打开键盘串口、设置明文模式
						OpenCom();
						SetWorkMode(0);
						
						setException("关闲密码键盘串口发生异常！");
						return;
					}
			
					// 准备刷卡（此接口包括签到银联功能）
					try
					{
						ret = ReadingUserCard();
					}
					catch(e)
					{
						// 打开键盘串口、设置明文模式
						OpenCom();
						SetWorkMode(0);
						
						setException("调用准备刷卡发生异常！");
						return;
					}
			
					// 关闲提示框
					wiWindow.close()
			
					// 调用准备刷卡后返回
					if (ret != "0" && ret != 0) 
					{
						setException("读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。");
			            return;
					}
					else
					{		
						//等待读卡
						readingCard = 1;
						
						//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
			            document.getElementById("homeBtn").disabled = true;
			            document.getElementById("quitBtn").disabled = true;
						
						// 打开键盘串口、设置明文模式
						OpenCom();
						SetWorkMode(0);
						
						// 启动定时任务
						startclock();
					}
				}
				catch (ex) 
				{
					setException("读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。");
			        return;
			    }
			}
			clearTimeout(timeOut);
			closeStatus = 1;
		    bodyLoad(); 
		</script>
	</body>
</html>