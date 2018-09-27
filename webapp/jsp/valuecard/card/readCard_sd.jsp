<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
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
	var updateUrl = "${sessionScope.basePath }valueCard/updateCardChargeLog_sd.action";
	
	// ҵ���ύurl
	var commitUrl = "${sessionScope.basePath }valueCard/buyValueCards_sd.action";
	
	// �ɷ��쳣ʱurl
	var errorUrl = "${sessionScope.basePath }valueCard/chargeError.action";
	
	var chargeType = "108";
	</script>
	    
    <body scroll="no">
		<form name="readCardForm" method="post">
			<!-- �ֻ����� -->
			<s:hidden id="servNumber" name="servNumber"></s:hidden>
			
			<!-- �ֻ����� -->
			<s:hidden id="servnumber" name="cardPayLogVO.servnumber"></s:hidden>
			
			<!-- �ɷ���־��ˮ -->
			<s:hidden id="chargeLogOID" name="cardPayLogVO.chargeLogOID"></s:hidden>
			
			<!-- ʡ�ݱ��� -->
			<s:hidden id="provinceCode" name="cardPayLogVO.provinceCode"></s:hidden>
			
			<!-- �ͻ����� -->
			<s:hidden id="custName" name="cardPayLogVO.custName"></s:hidden>
			
			<!-- �ɷѽ�� -->
			<input type="hidden" id="tMoney" name="cardPayLogVO.tMoney" value="" />
			
			<!-- �ն˽ɷ���ˮ�� -->
			<s:hidden id="terminalSeq" name="cardPayLogVO.terminalSeq"></s:hidden>
			
			<!-- ֧����ʽ 1:��������4���ֽ� -->
            <s:hidden id="payType" name="payType"></s:hidden>
			
			<!-- �������ش����� -->
			<s:hidden id="unionRetCode" name="unionRetCode"></s:hidden>
			
			<!-- ������ӡСƱ��Ϣ -->
			<s:hidden id="printcontext" name="printcontext"></s:hidden>
			
			<!-- ������Ϣ -->
			<s:hidden id="errormessage" name="errormessage"></s:hidden>
			
			<!-- �мۿ����� -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- �мۿ����� -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- �мۿ���ֵ -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
	        <%@ include file="/jsp/customize/sd/feeCharge/common/cardChargeCommon.jsp"%>
		</form>
    </body>
    <script type="text/javascript">
        clearTimeout(timeOut);
        bodyLoad();        
    </script>
</html>
