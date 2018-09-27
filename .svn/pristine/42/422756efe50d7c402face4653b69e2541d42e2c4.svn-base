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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js?ver=${jsVersion}"></script>
</head>
<body scroll="no" onload="doFinish();">
	<form name="actform" method="post">
		<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />" />	
		<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
		<input type="hidden" id="errorType" name="errorType" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
		
		<%@include file="/jsp/customize/hub/common/fee/feeErrorPage.jsp" %>
	</form>
<script>

	//防止页面重复提交
	var submitFlag = 0;
	
	// 设置左侧页面的高亮显示
	document.getElementById("highLight5").className = "on";
	
	// 页面自动加载打印缴费小票
	function doFinish()
	{
		//如果用户有投币，才打印失败凭条
		var money = parseInt("<s:property value='tMoney' />");
		if (money <= 0)
		{
			return;
		}			
		
		//打印缴费失败的凭证
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
			
			// 调用打印
			doPrintValueCardError(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealTime, pAmount,
					pTotalFee, pDealStatus, pDealNum);
		}
	}
	
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = menuid;
				
			document.forms[0].action = "${sessionScope.basePath }valueCard/inputNumber.action";
			document.forms[0].submit();
		}
	}
</script>
</body>
</html>