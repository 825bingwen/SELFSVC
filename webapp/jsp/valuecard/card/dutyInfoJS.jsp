<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCardEquip = termInfo.getTermspecial().substring(4, 5);

// ʡ����Ϣ
String provinceId = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>		
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />" />
			<input type="hidden" name="telnum" value="<s:property value='telnum' />" />	
			<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
			<input type="hidden" id="errorType" name="errorType" value="" />
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
			<!-- �мۿ����� -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- �мۿ����� -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- �мۿ���ֵ -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
			<!-- modify begin wWX217192 2015-06-18 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸��� -->
			<s:if test="'HUB' == provinceId">
				<%@include file="/jsp/customize/hub/common/fee/card/dutyInfo.jsp" %>
			</s:if>
			<s:else>
				<%@include file="/jsp/customize/sd/feeCharge/common/dutyInfoCommon.jsp" %>
			</s:else>
			<!-- modify end wWX217192 2015-06-18 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸��� -->
		</form>
		<script>
		
			//��ֹ�ظ��ύ
			var submitFlag = 0;
			
			// �������ҳ��ĸ���
			document.getElementById("highlight4").className = "on";
			
			// ����
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = 'recValueCardBuy';
					
					document.getElementById("payType").value = "4";
					
					document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/qryPayType.action";
					document.dutyInfoForm.submit();
				}
			}
			
			// ͬ��������������			
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//�ύ���
					
					if("HUB" == "<%=provinceId %>")
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/readCard.action";
					}
					else
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/readCard_sd.action";
					}
					document.dutyInfoForm.submit();
				}
			}
			
			//�����쳣
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("errormessage").value = errorMsg;
					document.getElementById("errorType").value = "add";
		
					// �쳣����ֻҪ�������쳣��Ҫ��¼��־
					// modify begin wWX217192 2015-06-18 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸���
					if("HUB" == "<s:property value='provinceId'/>")
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/goCardError.action";
					}
					else
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/chargeError.action";
					}
					// modify end wWX217192 2015-06-18 OR_SD_201505_1022_ɽ��_ɽ�����ӳ�ֵ����������_�����ն˸���

					document.dutyInfoForm.submit();
				}			
			}	
			
			// ��������ҳ��ʱ�����û���Ϣ
			function doLoad()
			{
				var serverNumber = "<s:property value='cardPayLogVO.servnumber' />";
				if (serverNumber == null || serverNumber == "")
		           {            
		           	setException("�Բ����û���Ϣ��ȡʧ�ܣ��뷵�����²�����");
		         		return;
		           }
				
				<%
				if (!"1".equals(isCardEquip))
				{
				%>
				 setException("�Բ��𣬸��ն˻��ݲ�֧����������ֵ����ѡ��������ʽ��");
				 return;
				<%
				}
				%>
			}
		</script>
	</body>
</html>