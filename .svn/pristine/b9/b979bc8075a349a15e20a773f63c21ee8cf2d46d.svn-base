<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String pOrgName = termInfo.getOrgname();

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
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			
			<%@include file="/jsp/customize/hub/common/fee/finish.jsp" %>
		</form>
	
	<script>
	
		var submitFlag = 0;
			
		var payType = "<s:property value='payType' />";
		
		if(payType == "1")
		{
			document.getElementById("highLight8").className = "on";
		}
		else
		{
			document.getElementById("highLight5").className = "on";
		}
		
		// 是否退还银联卡
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		//取卡时间30秒
		var limitTime = 30;
	
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
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				document.getElementById("curMenuId").value = menuid;
				
				document.forms[0].action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
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
			
			var pServNumber = "<s:property value='cardPayLogVO.servnumber' />";
			var pOrgName = "<%=pOrgName%>";  //打印地点
			var pPrintDate = getDateTime();  //打印日期
			var pTerminalSeq = "<s:property value='cardPayLogVO.terminalSeq' />";//终端流水
			var totalFee = "<s:property value='totalFee' />"; // 应缴费用
			var tMoney = "<s:property value='tMoney' />"; // 实际缴费
			
			// 打印小票
			doPrintValueCard(pServNumber, pOrgName, pPrintDate, totalFee, tMoney);
			
			if (payType == "1")
			{
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
			}
		}
		
		// 交费成功，有价卡购买成功时打印小票
		function doPrintValueCard(pServNumber,pOrgName,pPrintDate, totalFee, tMoney) 
		{
		  try {
		  	var ret;
		  	try{
		  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
		  	}
		  	catch(e)
		  	{
				alertError("警告:打印机控件未安装!");
				return;
		  	}
		  	if (ret == 1)
		  	{
		  	   alertError("警告:打印机缺纸或故障!");
		  	   return;
		  	}
		  	else if (ret == 41)
		  	{
		  	   alertError("错误:打印机设备低层驱动程序未安装!");
		  	   return;
		  	}
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端平台电子有价卡购买凭据");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  应收费用: "+totalFee+"元");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  实缴费用: "+tMoney+"元");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
		    if (ret == 1)
		    {
		        alertError("警告:打印机缺纸或故障!");
		        return;
		    }
		    	
		  	<s:iterator value='cardList' >
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡号: " + "<s:property value='cardNo' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡面值: " + "<s:property value='cntTotal' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡有效期: " + "<s:property value='expDate' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡业务类型: " + "<s:property value='cardType' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡业务属性值: " + "<s:property value='cardBusiPro' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine("  平台交易流水号: " + "<s:property value='transActionId' />");
				ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
				
				if (ret == 1)
		    	{
			        alertError("警告:打印机缺纸或故障!");
			        return;
		    	}
			</s:iterator>
			
		    if (ret == 1)
		    {
		        alertError("警告:打印机缺纸或故障!");
		        return;
		    }
		  	
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
		  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
		  	if (ret == 1)
		  	{
		  	    alertError("警告:打印机缺纸或故障!");
		  	    return;
		  	}
			cutPaper();
			} catch(ex) {
		 		alertError("打印电子充值卡信息出错,请联系营业厅服务员对电子充值卡购买状态进行确认！");
		 		cutPaper();//出现问题切纸
			}	
		}
	</script>
</body>
</html>