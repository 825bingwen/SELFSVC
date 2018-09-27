<%--
 @User: m00227318
 @De: 2012/09/20
 @comment: ��ʾ���ֶһ�����ȯ����ɹ�
 @remark: create m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="java.util.List"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO"%>
<%
    String successMessage = (String) request.getAttribute("successMessage");

    TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
    // ��ǰ�ͻ�
    NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    
    //�ֻ���
    String pServNumber = customerSimp.getServNumber();
        
    //��ӡ�ص�
	String pOrgName = termInfo.getOrgname();
	//�ն���Ϣ
	String pTerminalInfo = termInfo.getTermname();
	//���ֶһ�����ȯ�Ľ�����ˮ
	String pExchNum = (String)request.getAttribute("recoid");
	//���ѵĻ�����
	String pExScore = (String)request.getAttribute("exchangeScore");
	//�û����û�����
	String pUsableScore = (String)request.getAttribute("usableScore");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
			//��ֹҳ���ظ��ύ
			var submitFlag = 0;

			document.onkeydown = pwdKeyboardDown;
			document.onkeyup = pwdKeyboardUp;

			function pwdKeyboardDown(e)
			{	
				var key = GetKeyCode(e);
	
				//����
				if (key == 77) 
				{
					preventEvent(e);
				}
		
				if (!KeyIsNumber(key))
				{
					return false;//��仰��ؼ�
				}
			}

			function KeyIsNumber(KeyCode) 
			{
				//ֻ��������0-9
				if (KeyCode >= 48 && KeyCode <= 57)
				{
					return true;
				}
	
				return false;
			}

			function pwdKeyboardUp(e)
			{
				var key = GetKeyCode(e);
				if (key == 82 || key == 220)
				{
					goback("<s:property value='curMenuId' />");
					return;
				}
			}

			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
			
					document.getElementById("curMenuId").value = menuid;					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
					document.forms[0].submit();
				}
			}
    		//��ӡ���ֶһ�����ȯƾ֤
			function doPrintProof()
			{
				//�ֻ���
				var pServNumber = "<%=pServNumber%>";
				//��ӡ�ص�
				var pOrgName = "<%=pOrgName%>";
				//��ӡ���� 
				var pPrintDate = getDateTime();
				//�ն���Ϣ 
				var pTerminalInfo = "<%=pTerminalInfo%>";
				//�һ���ˮ
				var pExchNum = "<%=pExchNum%>";
				//���ѻ���
				var pExScore = "<%=pExScore%>";
				//����ȯ���
				var pECoupon = pExScore/100;	
				//���û���
				var pUsableScore = "<%=pUsableScore%>";
				// ��ӡ���ֶһ�����ȯСƱ
				doPrintScoreExECou(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pExchNum,pExScore,pECoupon,pUsableScore);
			}
		</script>
	</head>
	<body onload="doPrintProof();" scroll="no">
		<form name="actform" method="post">
			<!--add begin CKF76106 2012/07/10 R003C12L07n01 OR_HUB_201206_597  -->
			<input type="hidden" id="buttonType" name="buttonType"
				value="<s:property value='buttonType'/>" />
			<!--add end CKF76106 2012/07/10 R003C12L07n01 OR_HUB_201206_597  -->
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok"> 
							<%
 								if (successMessage == null || "".equals(successMessage)) 
 								{
 									out.print("����ʧ�ܣ����Ժ����ԡ�");
 								} 
 								else 
 								{
 									out.print(successMessage);
 								}
 							%> 
 							</span>
						</dd>
					</dl>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
