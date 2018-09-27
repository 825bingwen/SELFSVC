<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	// �ն˻���Ϣ
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String pOrgName = termInfo.getOrgname(); // �ص�
	String termName = termInfo.getTermname(); // �ն˻�����
	String termId = termInfo.getTermid(); // �ն˻�����
	
	// �ն˻�����
	String termSpecial = termInfo.getTermspecial();
	
	// �Ƿ�ɴ�ӡƾ����־
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	// �ͻ���Ϣ
	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	
	// �ͻ�����
	String custName = customer.getCustomerName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
// ��ֹҳ���ظ��ύ
var submitFlag = 0;

// 82��220 ����

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	// ����
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
  		// ֻ��������0-9
  		if (KeyCode >= 48 && KeyCode <= 57)
  		{
  			return true;
  		}
  		
  		return false;
}	

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	// ����
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}			
}

function doFinish()
{
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='telNum' />����ʧ��");

	// ����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
	var money = parseInt("<s:property value='tMoney' />");
	if (money <= 0)
	{
		return;
	}
	
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"��ӡʧ��ƾ֤");		

	// ��ӡ�ɷ�ʧ�ܵ�ƾ֤
	if ("<%=canPrintReceipt %>" == "1")
	{
		var p_telNum = "<s:property value='telNum' />";// �ֻ�����
		var p_custName = "<%=custName %>"; // �ͻ�����
		var p_orgName = "<%=pOrgName %>";  //��ӡ�ص�
		var p_date = getDateTime();  //��ӡ����
		var p_termId = "<%=termId %>"; //�ն˱���
		var p_termName = "<%=termName %>"; //�ն�����
		var p_tMoney = "<s:property value='tMoney' />";// Ͷ�ҽ��
		var p_chargeId = "<s:property value='chargeLogOID'/>";// �ɷ���־��ˮ
		var p_terminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
		var p_recoid = "<s:property value='recoid' />";// ������ˮ
		var p_recFee = "<s:property value='recFee' />";// Ӫ����������
		var p_preFee = "<s:property value='preFee' />";// �û�Ԥ��
		var p_actLevelName = "<s:property value='actLevelName'/>";// ��������
		var p_groupName = "<s:property value='groupName'/>";// �������
		var p_status = "Ԥ����������ʧ��,��ƾ����ƾ����Ӫҵ�������˿"; //����״̬
		
		// ���ô�ӡ
		doPrintByActivitySD(p_telNum,p_orgName,p_date,p_termId,p_termName,p_tMoney,p_chargeId,p_terminalSeq,
            p_recoid,p_status,p_recFee,p_preFee,p_actLevelName,p_groupName,p_custName,"", '')
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
			
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
		document.forms[0].submit();
	}
}
</script>
</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>Ԥ�������������̣�</h2>
                        <div class="blank10"></div>
                        <a title="ѡ������" href="#">1. ѡ��</a>
                        <a title="��������" href="#">2. ����Э��</a>  
                        <a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
                        <a title="֧��" href="#">4. Ͷ��ֽ��</a>
                        <a title="���" href="#" class="on">5. ���</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<s:property value="errormessage" />
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
