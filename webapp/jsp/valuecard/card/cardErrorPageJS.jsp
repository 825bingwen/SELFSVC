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
	<title>�ƶ������ն�</title>
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
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		document.getElementById("highLight8").className = "on";
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='tMoney' />";
		
		var limitTime = 30;//ȡ��ʱ��30��
		
		// ��ȡ������ȡ��״̬
		function takeOutBankCardStatus() 
		{
			limitTime = limitTime - 1;
			
			if (limitTime <= 0)
			{
				//��ʱ�������ʱ����ͬʱ�̿�
				clearInterval(waitingToken);
				
				captureUserCard();
				
				return;
			}
			
			try 
			{
				//������
				var ret = TakeOutUserCardStatus();//��ȡ������ȡ��״̬
				
				//������
				//var ret = 0;
				if (ret == "0" || ret == 0) 
				{	
					//�Ѿ�ȡ�߿��������ʱ����
					clearInterval(waitingToken);									
				}			
			}
			catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
		
		function startClock()
		{
			try 
			{
				waitingToken = setInterval("takeOutBankCardStatus()", 1000);
			}
			catch (e) {}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
		
		function doFinish()
		{
			if (needReturnCard == "1")
			{			
				// ��ʶ�ؼ�ʹ��
			    closeStatus = 1;
			    
				//������
				var ret = TakeOutUserCard();
				
				// ��ʶ�ؼ�δʹ��
				if (ret == "0")
				{
		        	closeStatus = 0;
		        }
				
				//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
				if (ret == "0" && "1" == "<%=needCaptureCard %>")
				{
					startClock();
				} 
				else if (ret != "0")
				{
					//�¿��쳣
				}
			}
			//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
			if (tMoney == null || tMoney == "" || tMoney == "null" || parseInt(tMoney) <= 0)
			{
				return;
			}			
			
			//��ӡ�мۿ�����ʧ�ܵ�ƾ֤
			if ("<%=canPrintReceipt %>" == "1")
			{
				var pServNumber = "<s:property value='cardPayLogVO.servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTermId%>"; //�ն���Ϣ
				var pDealTime = getDateTime();   //����ʱ��
				var pAmount = "<s:property value='tMoney' />Ԫ";     //ʵ�ɽ��
				var pTotalFee = "<s:property value='totalFee' />Ԫ";  // Ӧ�ɽ�� 
				var pDealStatus = "�мۿ�����ʧ��"; //����״̬
				var pDealNum = "<s:property value='cardPayLogVO.terminalSeq' />";
				
				// ��ӡ�ɷ�СƱ
				doPrintValueCardError(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealTime, pAmount,
						pTotalFee, pDealStatus, pDealNum);
				
				// ��ӡ����СƱ
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