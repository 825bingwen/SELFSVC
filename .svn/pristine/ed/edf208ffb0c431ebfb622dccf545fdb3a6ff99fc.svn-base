<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
</head>
	<body scroll="no" onload="readLoad();">
		<form name="readCardForm" method="post" target="_self">
		<input type="hidden" id="payType" name="cardChargeLogVO.payType" value="<%=Constants.PAY_BYCARD %>">
			<s:hidden id="servnumber" name="cardChargeLogVO.servnumber"/>
			<s:hidden name="cardChargeLogVO.provinceCode"/>
			<s:hidden id="yingjiaoFee" name="yingjiaoFee"/>
			<s:hidden id="needReturnCard" name="needReturnCard" value='0'/>
			<s:hidden id="cardnumber" name="cardChargeLogVO.cardnumber"/>
			<s:hidden id="errormessage" name="errormessage"/>
			
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
    					<a href="javascript:void(0)" class="on">5. 插入储蓄卡</a>
    					<p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)">7. 核对信息</a>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>请插入您的储蓄卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
        					<p class="tit_info"><span>读卡时长共</span><span class="yellow">30</span>秒，当前剩余<input type="text"  id="tTime" name="tTime" value="30" readonly="readonly" />秒</p>
        					<div class="blank10"></div>   
       						<div class="blank20"></div>
        					<div class="blank10"></div>
       						<div class="gif_animation">
       							<img src="${sessionScope.basePath }images/gif1.gif" alt="请插卡" />
       						</div>        
      					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
		clearTimeout(timeOut);
		var closeStatus = 1;
		
		//82、220 返回
		document.onkeydown = pwdKeyboardDown;
		
		//键盘按下
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
		
		//只允许输入数字
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
		}
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
				
				//等待读卡线程启动成功，出现异常后，需关闭			
				if (readingCard == 1)
				{
					CancelReadingUserCard();
				}
				
				//异常处理，只要出现了异常就要记录日志  
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }nonlocalChargeHUB/goCardError.action";
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
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }nonlocalChargeHUB/inputCardPwd.action";
				document.readCardForm.submit();	
			}			
		}
		
		// 获得读卡标志
		function getReadCardStatus() 
		{
			//读卡时间结束
			if (limitTime <= 1 && submitFlag == 0)
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
		
		/**
		 * 银联卡硬件初始化
		 * 初始化成功后，启用定时任务
		 */
		function readLoad()
		{
			//初始化银联读卡器
			var initMsg = readCardInit();
			
			if("success" == initMsg)
			{
				//等待读卡
				readingCard = 1;
				
				//启用定时任务
				waitingToken = setInterval("getReadCardStatus()", 1000);
			}
			else
			{
				setException(initMsg);
			}
			
		}
	</script>
</html>
