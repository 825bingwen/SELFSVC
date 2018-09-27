<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
    </head>
    <script type="text/javascript">
	// 返回上一页url
	var goBackUrl = "${sessionScope.basePath }nonlocalChargeSD/qryfeeChargeAccount.action";
	
	// 提交url
	var commitUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeCommit.action";
	
	// 缴费异常url
	var errorUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeError.action";
	</script>
    <body scroll="no">
        <form name="actform" method="post">
            <!-- 手机号码 -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- 手机号码 -->
            <s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
            
            <!-- 缴费日志流水 -->
            <s:hidden id="chargeLogOID" name="chargeLogVO.chargeLogOID"></s:hidden>
            
            <!-- 省份编码 -->
            <s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
            
            <!-- 缴费方式，1：银联卡；4：现金 -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
            
            <!-- 客户名称 -->
            <s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
            
            <!-- 终端交易流水 -->
            <s:hidden id="terminalSeq" name="chargeLogVO.terminalSeq"></s:hidden>
            
            <!-- 缴费明细 -->
            <s:hidden id="cashDetail" name="cashDetail"></s:hidden>
            
            <!-- 错误信息 -->
            <s:hidden id="errormessage" name="errormessage"></s:hidden>

            <%@ include file="/jsp/customize/sd/feeCharge/common/cashChargeCommon.jsp"%>
        </form>
    </body>
    <script type="text/javascript">
        clearTimeout(timeOut);
        loadContent();
    </script>
</html>


