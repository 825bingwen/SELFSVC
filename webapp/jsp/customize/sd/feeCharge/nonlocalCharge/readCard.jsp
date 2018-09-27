<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js?ver=${jsVersion }"></script>
    </head>
    <script type="text/javascript">
	// 扣款完成后，更新缴费日志url
	var updateUrl = "${sessionScope.basePath }nonlocalChargeSD/updateCardChargeLog.action";
	
	// 业务提交url
	var commitUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeCommit.action";
	
	// 缴费异常时url
	var errorUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeError.action";
	
	// 银联卡缴费类型，106：省外异地缴费
	var chargeType = "106";
	</script>
	    
    <body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<!-- 手机号码 -->
			<s:hidden id="servNumber" name="servNumber"></s:hidden>
			
			<!-- 手机号码 -->
			<s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
			
			<!-- 缴费日志流水 -->
			<s:hidden id="chargeLogOID" name="chargeLogVO.chargeLogOID"></s:hidden>
			
			<!-- 省份编码 -->
			<s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<!-- 客户名称 -->
			<s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
			
			<!-- 缴费金额 -->
			<s:hidden id="tMoney" name="chargeLogVO.tMoney"></s:hidden>
			
			<!-- 终端缴费流水号 -->
			<s:hidden id="terminalSeq" name="chargeLogVO.terminalSeq"></s:hidden>
			
			<!-- 支付方式 1:银联卡，4：现金 -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
			
			<!-- 银联返回错误码 -->
			<s:hidden id="unionRetCode" name="unionRetCode"></s:hidden>
			
			<!-- 银联打印小票信息 -->
			<s:hidden id="printcontext" name="printcontext"></s:hidden>
			
			<!-- 错误信息 -->
			<s:hidden id="errormessage" name="errormessage"></s:hidden>
	        <%@ include file="/jsp/customize/sd/feeCharge/common/cardChargeCommon.jsp"%>
		</form>
    </body>
    <script type="text/javascript">
        clearTimeout(timeOut);
        bodyLoad();        
    </script>
</html>
