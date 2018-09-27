<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
	</head>
        <body onload="window.focus();doFinish();" scroll="no">
	
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择支付方式</a> 
      					<a href="#">3. 选择金额</a> 
      					<a href="#">4. 支付</a>
          				<a href="#" class="on">5. 完成</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='cardChargeLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       							<div class="recharge_result tc">
       							<p class="title yellow pt30">您的充值交费已成功！</p>
       							<s:if test="cardChargeLogVO.payType == 'PAYTYPE_CARD'">
          							<p class="desc pt20">请保存好您的的交易凭条和银联卡。</p>
          						</s:if>
          						<s:else>
          							<p class="desc pt20">请保存好您的的交易凭条。</p>
          						</s:else>
          						<div class="btn_box2 clearfix">
          					    <a href="javascript:void(0);" style="margin-left:80px;" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费</a>         							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
	//继续充值缴费
	function continueCharge()
	{	
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		document.payMoneyForm.submit();
	}
	
	var submitFlag = 0;
	
	var payType = "<s:property value='cardChargeLogVO.payType' />";
	var needReturnCard = "<s:property value='needReturnCard' />";
	
	var limitTime = 30;//取卡时间30秒	
	
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
		}			
	}
	
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			document.getElementById("curMenuId").value = menuid;
			
			document.forms[0].action = "${sessionScope.basePath }nonlocalChargeHUB/feeCharge.action";
			document.forms[0].submit();
		}
	}		
	
	// 读取读卡器取卡状态
	function takeOutBankCardStatus() 
	{
		limitTime = limitTime - 1;
		
		if (limitTime <= 0)
		{
			//超时，清除定时任务，同时吞卡
			clearInterval(waitingToken);
			
			captureUserCard();
			
			return;
		}
		
		try 
		{
			//生产用
			var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
			
			//测试用
			//var ret = 0;
			if (ret == "0" || ret == 0) 
			{	
				//已经取走卡，清除定时任务
				clearInterval(waitingToken);									
			}			
		}
		catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
	}
	
	function startClock()
	{
		try 
		{
			waitingToken = setInterval("takeOutBankCardStatus()", 1000);
		}
		catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
	}
	
	function doFinish()
	{
		if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
		{
			// 退卡
			var ret = TakeOutUserCard();

			// 吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
			if (ret == "0" && "1" == "<%=needCaptureCard %>")
			{
				startClock();
			}
			else if (ret != "0")
			{
				//吐卡异常
			}
		}
		
		var pServNumber = "<s:property value='cardChargeLogVO.servnumber' />";
		var pOrgName = "<%=pOrgName%>";  //打印地点
		var pPrintDate = getDateTime();  //打印日期
		var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
		var pDealNum = "<s:property value='cardChargeLogVO.dealnum'/>";//交易流水号
		var pDealTime = getDateTime();   //交易时间
		var tMoney = "<s:property value='tMoney' />元";//交易金额
		var pDealStatus = "缴费成功"; //交易状态
		var pTerminalSeq = "<s:property value='cardChargeLogVO.terminalSeq' />";//终端流水
		var brand = ""; //品牌
		var presentFee = "";//赠送金额
		
		//打印凭证
		if ("<%=canPrintReceipt %>" == "1")
		{
			// 缴费小票
			doPrintPayProofhub(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
					pDealStatus, pTerminalSeq,brand,"", "0", presentFee);
			// 打印银联小票
			if (payType == "<%=Constants.PAY_BYCARD %>")
			{
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
			}
		}
	}
	</script>
</html>
