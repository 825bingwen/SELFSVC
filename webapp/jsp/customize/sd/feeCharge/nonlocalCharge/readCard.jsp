<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>����ҳ��</title>
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
	// �ۿ���ɺ󣬸��½ɷ���־url
	var updateUrl = "${sessionScope.basePath }nonlocalChargeSD/updateCardChargeLog.action";
	
	// ҵ���ύurl
	var commitUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeCommit.action";
	
	// �ɷ��쳣ʱurl
	var errorUrl = "${sessionScope.basePath }nonlocalChargeSD/chargeError.action";
	
	// �������ɷ����ͣ�106��ʡ����ؽɷ�
	var chargeType = "106";
	</script>
	    
    <body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<!-- �ֻ����� -->
			<s:hidden id="servNumber" name="servNumber"></s:hidden>
			
			<!-- �ֻ����� -->
			<s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
			
			<!-- �ɷ���־��ˮ -->
			<s:hidden id="chargeLogOID" name="chargeLogVO.chargeLogOID"></s:hidden>
			
			<!-- ʡ�ݱ��� -->
			<s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<!-- �ͻ����� -->
			<s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
			
			<!-- �ɷѽ�� -->
			<s:hidden id="tMoney" name="chargeLogVO.tMoney"></s:hidden>
			
			<!-- �ն˽ɷ���ˮ�� -->
			<s:hidden id="terminalSeq" name="chargeLogVO.terminalSeq"></s:hidden>
			
			<!-- ֧����ʽ 1:��������4���ֽ� -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
			
			<!-- �������ش����� -->
			<s:hidden id="unionRetCode" name="unionRetCode"></s:hidden>
			
			<!-- ������ӡСƱ��Ϣ -->
			<s:hidden id="printcontext" name="printcontext"></s:hidden>
			
			<!-- ������Ϣ -->
			<s:hidden id="errormessage" name="errormessage"></s:hidden>
	        <%@ include file="/jsp/customize/sd/feeCharge/common/cardChargeCommon.jsp"%>
		</form>
    </body>
    <script type="text/javascript">
        clearTimeout(timeOut);
        bodyLoad();        
    </script>
</html>
