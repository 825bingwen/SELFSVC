<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	// ȡ����Ӧʡ����Ϣ
	String provinceId = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html> 
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />		
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<!-- �ֻ����� -->
		<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='telnum' />" />
		<!-- Ϊ������һҳ��׼�� -->
		<input type="hidden" name="telnum" value="<s:property value='telnum' />" />
		<!-- �û�Ӧ�ɷ��� -->
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<!-- ֧����ʽ -->
		<input type="hidden" id="payType" name="payType" value="" />
		<!-- �мۿ����� -->
		<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
		<!-- �мۿ����� -->
		<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
		<!-- �мۿ���ֵ -->
		<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		
		<!-- ���빫��ѡ�񽻷ѷ�ʽҳ�� -->
		<s:if test="'HUB' == provinceId">
			<%@include file="/jsp/customize/hub/common/fee/selectPayType.jsp" %>
		</s:if>
		<s:else>
			<%@include file="/jsp/customize/sd/feeCharge/common/selectPayTypeCommon.jsp" %>
		</s:else>
	</form>
	
	<script>

		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		// �������ҳ�����
		document.getElementById("highLight3").className = "on";
		
		// ����
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading();
				
				document.getElementById("curMenuId").value = menuid;
				
				// ���˵���ʾʱʹ��
				document.getElementById("payType").value = "4";
				
				document.actform.action = "${sessionScope.basePath }valueCard/chooseValueCard.action";
				document.actform.submit();
			}
		}
		
		// ѡ����Ӧ�ĳ�ֵ��ʽ�����н���
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
		
				// ѡ���ֽ�ɷ�
				if (url == "valueCard/cashCharge.action")
				{
				
					// ֧����ʽ����Ϊ�ֽ�ɷ�
					document.getElementById("payType").value = "4";
					
					openRecWaitLoading();
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				else
				{	
					// ֧����ʽ����Ϊ�������ɷ�
					document.getElementById("payType").value = "1";
					
					if("SD" == "<%=provinceId %>")
					{
						// ���������ѣ��жϴ�ʱ������Ƿ����
						var url1 = "${sessionScope.basePath}charge/checkTime.action";
						
						var loader = new net.ContentLoader(url1, netload = function () {
							var resXml1 = this.req.responseText;
							
							if (resXml1 == "1" || resXml1 == 1)
							{
								document.actform.target = "_self";
								document.actform.action = "${sessionScope.basePath}" + url;
								document.actform.submit();
							}
							else
							{
								submitFlag = 0;
								
								alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
								
								return;
							}
						}, null, "POST", null, null);
					}
					else
					{
						openRecWaitLoading();
						document.actform.action = "${sessionScope.basePath}" + url;
						document.actform.submit();
					}
				}
			}			
		}
	</script>
</body>
</html>