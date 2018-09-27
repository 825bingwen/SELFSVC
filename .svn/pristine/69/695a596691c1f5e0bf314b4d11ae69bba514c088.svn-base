<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	
	String pTermId = termInfo.getTermid();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>移动自助终端</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
	<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js?ver=${jsVersion }"></script>
	<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/UnionCard_PayFlow_Hub.js?ver=${jsVersion }"></script>
</head>
<body scroll="no" onload="doFinish();">
	<form name="actform" method="post">
		<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />">
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
		<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
		<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
		<input type="hidden" id="errorType" name="errorType" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		
		<%@include file="/jsp/customize/hub/common/fee/feeErrorPage.jsp" %>
	</form>
	<script>
		//防止页面重复提交
		var submitFlag = 0;
		
		document.getElementById("highLight8").className = "on";
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='tMoney' />";
		
		var limitTime = 30;//取卡时间30秒
		
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
			if (needReturnCard == "1")
			{			
				// 标识控件使用
			    closeStatus = 1;
			    
				//生产用
				var ret = TakeOutUserCard();
				
				// 标识控件未使用
				if (ret == "0")
				{
		        	closeStatus = 0;
		        }
				
				//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
				if (ret == "0" && "1" == "<%=needCaptureCard %>")
				{
					startClock();
				} 
				else if (ret != "0")
				{
					//吐卡异常
				}
			}
			//如果用户有投币，才打印失败凭条
			if (tMoney == null || tMoney == "" || tMoney == "null" || parseInt(tMoney) <= 0)
			{
				return;
			}			
			
			//打印有价卡购买失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				var pServNumber = "<s:property value='cardPayLogVO.servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTermId%>"; //终端信息
				var pDealTime = getDateTime();   //交易时间
				var pAmount = "<s:property value='tMoney' />元";     //实缴金额
				var pTotalFee = "<s:property value='totalFee' />元";  // 应缴金额 
				var pDealStatus = "有价卡购买失败"; //交易状态
				var pDealNum = "<s:property value='cardPayLogVO.terminalSeq' />";
				
				// 打印缴费小票
				doPrintValueCardError(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealTime, pAmount,
						pTotalFee, pDealStatus, pDealNum);
				
				// 打印银联小票
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
						
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
	</script>
</body>
</html>