<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>�ƶ������ն�</title>
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
	// ������һҳurl
	var goBackUrl = "${sessionScope.basePath }valueCard/qryPayType.action";
	
	// �ύurl
	var commitUrl = "${sessionScope.basePath }valueCard/buyValueCards_sd.action";
	
	// �ɷ��쳣url
	var errorUrl = "${sessionScope.basePath }valueCard/chargeError.action";
	</script>
    <body scroll="no">
        <form name="actform" method="post">
            <!-- �ֻ����� -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- �ֻ����� -->
            <s:hidden id="servnumber" name="cardPayLogVO.servnumber"></s:hidden>
            
            <!-- �ɷ���־��ˮ -->
            <s:hidden id="chargeLogOID" name="cardPayLogVO.chargeLogOID"></s:hidden>
            
            <!-- ʡ�ݱ��� -->
            <s:hidden id="provinceCode" name="cardPayLogVO.provinceCode"></s:hidden>
            
            <!-- �ɷѷ�ʽ��1����������4���ֽ� -->
            <s:hidden id="payType" name="payType"></s:hidden>
            
            <!-- Ӧ���ܽ�� -->
            <s:hidden id="totalFee" name="totalFee"></s:hidden>
            
            <!-- �ͻ����� -->
            <s:hidden id="custName" name="cardPayLogVO.custName"></s:hidden>
            
            <!-- �ն˽�����ˮ -->
            <s:hidden id="terminalSeq" name="cardPayLogVO.terminalSeq"></s:hidden>
            
            <!-- �ɷ���ϸ -->
            <s:hidden id="cashDetail" name="cashDetail"></s:hidden>
            
            <!-- ������Ϣ -->
            <s:hidden id="errormessage" name="errormessage"></s:hidden>
            
            <!-- �мۿ����� -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			
			<!-- �мۿ����� -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			
			<!-- �мۿ���ֵ -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		

            <%@ include file="/jsp/customize/sd/feeCharge/common/cashChargeCommon.jsp"%>
        </form>
    </body>
    <script type="text/javascript">
        clearTimeout(timeOut);
        loadContent();
    </script>
</html>


