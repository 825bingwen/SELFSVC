<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if(null == errorMessage || "".equals(errorMessage.trim()))
{
	errorMessage = "����ʧ�ܣ����Ժ����ԡ�";
}

// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
errorMessage = CommonUtil.errorMessage(errorMessage);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������

String backStep = (String) request.getAttribute("backStep");

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_hb.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/SelfInstallPrinter_Hub.js"></script>
		<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
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
		
		// modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
		<%
		if (null != backStep && !"".equals(backStep.trim()))
		{
		%>
			history.go(parseInt("<%=backStep %>"));
		<%	
		}
		else
		{
		%>
			document.getElementById("curMenuId").value = menuid;
				window.history,back();
<%--			document.forms[0].target = "_self";--%>
<%--			document.forms[0].action = "${sessionScope.basePath }login/backForward.action";--%>
<%--			document.forms[0].submit();--%>
		<%
		}
		%>
		// modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
	}
}

function doFinish()
{
	//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
   var money = parseInt("<s:property value='tMoney' />");
   
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   //��ӡ�ɷ�ʧ�ܵ�ƾ֤
   if ("<%=canPrintReceipt %>" == "1")
   {
	   	var piData = new function()
	   	{
	   		this.servnumber = "<s:property value='telnum' />";
	   		this.mainprodname = "<s:property value='mainprodname' escape='false'/>";
	   		this.termId = "<%=termId%>";
		   	this.receptionFee = "<s:property value='receptionFee' />";
		   	this.tMoney = "<s:property value='tMoney' />";
		   	this.dealNum = "<s:property value='formnum' />";     
		   	this.pDealTime = getDateTime();  
		   	this.payStatus = '0';  
		   	this.pDealStatus = "�����ն˿�������ʧ��"; 
		   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
		   	this.pOrgName = "<%=pOrgName%>";  
		   	this.pPrintDate = getDateTime();  
	   	}
	   
	   	installPrint(piData);
   }				
}
</script>
</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>�����������̣�</h2>
      					<div class="blank10"></div>
      					<a title="����Э��ȷ��" href="#">1. ����Э��ȷ��</a>
      					<a title="���֤��Ϣ��֤" href="#">2. ���֤��Ϣ��ȡ</a>
      					<a title="��Ʒѡ��" href="#">3. ��Ʒѡ��</a>  
      					<a title="����ѡ��" href="#">4. ����ѡ��</a> 
      					<a title="����ȷ��" href="#">5. ����ȷ��</a>
      					<a title="�����ɷ�" href="#">6. �����ɷ�</a>
      					<a title="ȡ����ӡ��Ʊ" href="#" class="on">7. ȡ����ӡ��Ʊ</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<%
      								out.print(errorMessage);
      							%>
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<script type="text/javascript">
removeAclick();
</script>
</html>
