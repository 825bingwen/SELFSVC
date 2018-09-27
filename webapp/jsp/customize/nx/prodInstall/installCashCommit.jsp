<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	           
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}

	//�̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	String subsName = (String) request.getAttribute("subsName");
	if (subsName == null)
	{
		subsName = "";
	}
	
	//  ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	// �Ƿ�֧�ֽ������̲�����1��֧��
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	
	// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
	String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/MoveCardManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// �ɷ�����
var payType = "<s:property value='payType' />";

// �Ƿ��˻�������
var needReturnCard = "<s:property value='needReturnCard' />";

//ȡ��ʱ��30��
var limitTime = 30;

document.onkeydown = pwdKeyboardDown;

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

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//����/�˳�(R)
	if(key == 82)
	{
		goback("<s:property value = 'curMenuId'/>");
	}
	//��ӡСƱ(1)
	if (key == 49)
	{
		printReceipt();
	}
	//�ɷ����(2)
	if (key == 50)
	{
		goHomePage();
	}
	// ������ʾ��ʹ�ý������̰����ر�
	else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
	{
		try
		{
			wiWindow.close();
		}
		catch (ex){}
		
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		
		writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />�˳����߿�������");
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.forms[0].submit();
	}
}		
	
// ��ȡ������ȡ��״̬
function takeOutBankCardStatus() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "����û��Ƿ���ȡ��");
					
	limitTime = limitTime - 1;
	
	if (limitTime <= 0)
	{
		//��ʱ�������ʱ����ͬʱ�̿�
		clearInterval(waitingToken);
		
		writeDetailLog("<%=chargelog_detailflag %>", "�û��ڹ涨ʱ����δȡ�����̿�");					
		
		captureUserCard();
		
		return;
	}
	
	try 
	{
		// ��ȡ������ȡ��״̬
		var ret = TakeOutUserCardStatus();
		
		if (ret == "0" || ret == 0) 
		{	
			//�Ѿ�ȡ�߿��������ʱ����
			clearInterval(waitingToken);
			
			writeDetailLog("<%=chargelog_detailflag %>", "�û���ȡ��");									
		}			
	}
	catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
}

// ѭ���¿�
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
	writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />���ѳɹ�");
	
	// �ɷѷ�ʽ��1����������4���ֽ�
	if (payType == 1 && needReturnCard == "1")
	{
		// ��ʼ��
		var ret = InitReadUserCard();

		// �˿�
		ret = TakeOutUserCard();
		
		//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
		if (ret == "0")
		{
			writeDetailLog("<%=chargelog_detailflag %>", "�¿��ɹ�");
			
			if ("1" == "<%=needCaptureCard %>")
			{
				startClock();
			}
		}
		else if (ret != "0")
		{
			// �¿��쳣
			writeDetailLog("<%=chargelog_detailflag %>", "�¿��쳣");
		}
	}
		finishCard();
}

// ��ӡСƱ
var printReceiptFlag = 0;
function printReceipt()
{
	writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />ѡ���ӡСƱ");
	
	if (printReceiptFlag == 0)
	{
		printReceiptFlag = 1;
		
		// ��ť���£��ò�����״̬
		document.getElementById('dayinButton').className = 'hover';
		document.getElementById('dayinButton').onmousedown = "";
		document.getElementById('dayinButton').onmouseup = "";
		document.getElementById('dayinButton').onclick = "";
		
		// �ֻ���
		var ptelnum = "<s:property value='telnum' />";
		
		// ����Ա����
		var pOperID = "<%=termInfo.getOperid() %>";  
		
		// ������ˮ��(�ɷѼ�¼��ţ�
		var pDealNum = "<s:property value='dealNum' />";
		
		// ����ʱ��
		var pDealTime = "<s:property value='dealTime' />";
		
		// ���׽��
		var tMoney = "<s:property value='tMoney' />Ԫ"; 
		
		// ����
		var pSubsName = "<s:property value='idCardPO.idCardName' />";
		
		//��ӡƾ֤
		if ("<%=canPrintReceipt %>" == "1")
		{	
			writeDetailLog("<%=chargelog_detailflag %>", "�Զ���ӡƾ��");
			
			doPrintPayProofProdInstall_NX(ptelnum, pOperID, pDealNum, pDealTime, tMoney, pSubsName);
		
			document.getElementById("resultMsg").innerHTML = "СƱ��ӡ�ɹ�����ȡ�ߡ�";
			
		}
	}
}
	
//��̨�����ɹ���ת��ҵ������ɹ�ҳ�棬���³�SIM����ӡҵ������ƾ����������ʾ����ɹ��������û�ȡ����
function finishCard()
{
	var params1 = "installId=" + document.getElementById("installId").value;
	
	// �����Ƴ�			
	var ret = MoveOutCard();
	
	// �رտ�����ʧ������Ӳ����������
	var status = CloseMoveCard();
	
	if( ret != 0)
	{
		params1 = params1+"&notes=1";
		
		var url = "${sessionScope.basePath }prodInstall/updateInstallLogNotes.action";
		var loader = new net.ContentLoader(url, netload = function () {}, null, "POST", params1, null);	
		setException("�¿������쳣���¿������쳣���������ƾ����Ӫҵ������");
	}
	else
	{
		params1 = params1+"&notes=0";
		
		var url = "${sessionScope.basePath }prodInstall/updateInstallLogNotes.action";
		var loader = new net.ContentLoader(url, netload = function () {}, null, "POST", params1, null);	
	}
	
}
	
//�����쳣
function setException(errorMsg) 
{
	// �ύ���
	exSubmitFlag = 1;	
	
	writeDetailLog("<%=chargelog_detailflag %>", "���������������쳣��" + errorMsg);
		
	//�����ʱ����
	clearInterval(timeToken);

	document.getElementById("errormessage").value = errorMsg;
	
	//�쳣����ֻҪ�������쳣��Ҫ��¼��־
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }prodInstall/installError.action";
	document.actForm.submit();
}
</script>
</head>
<body onload="window.focus();" scroll="no">
<form name="payMoneyForm" method="post">
	<!-- ���֤��Ϣ -->
	<!-- ���� -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- �Ա� -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
	<!-- ���֤���� -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- ֤����ַ -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
	<!-- ��ʼʱ�� -->
	<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
	<!-- ����ʱ�� -->
	<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
	<!-- ����Ϣ -->
	<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
	<!-- ��Ƭ��Ϣ -->
	<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
	
	<!-- �ײ���Ϣ -->
	<!-- ģ��ID -->
	<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
	<!-- ģ������ -->
	<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
	<!-- ��ƷID -->
	<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
	<!-- ��Ʒ���� -->
	<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
	<!-- Ʒ�� -->
	<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
	<!-- �ײ��·� -->
	<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
	<!-- Ԥ����� -->
	<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
	
	<!-- ѡ����Ϣ -->
	<!-- ���� -->
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
	<!-- ��֯����ID -->
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<!-- �������� -->
	<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
	<!-- ѡ�Ź��� -->
	<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
	<!-- ǰ׺ -->
	<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
	<!-- ��׺ -->
	<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
	<!--�հ׿����к� -->
	<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
	<!--�ֻ����� -->
	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="iccid" value="<s:property value='iccid'/>" />
	<!--����Ϣ���ĺ��� -->
	<input type="hidden" id="smsp" name="smsp" value="<s:property value='smsp'/>" />
	<!-- ��ƷID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	<!-- �������� -->
	<input type="hidden" id="pwd" name="pwd" value="<s:property value='pwd'/>"/>
	
	<!-- �ɷ���Ϣ -->
	<!-- ���úϼ� -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	<!-- Ͷ������ˮ�� -->
	<input type="hidden" id="terminalSeq" name="terminalSeq" value="<s:property value='terminalSeq'/>"/>
	<!-- ʵ�ʽɷѽ�� -->
	<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
	
	<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'/>
	<input type="hidden" id="invoiceType" name="invoiceType" value=""/>
	
	<%-- ������ --%>
	<input type="hidden" id="dealNum" name="dealNum" value='<s:property value="dealNum" />'/>
	<input type="hidden" id="dealTime" name="dealTime" value='<s:property value="dealTime" />'/>
	<input type="hidden" id="balance" name="balance" value='<s:property value="balance" />'/>
	<input type="hidden" id="subsName" name="subsName" value="<%=subsName %>"/>
	
	<%-- ������־ --%>
	<%-- ��ˮ�� --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- �ɷ���ˮ�� --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- �ɷѷ�ʽ��1����������4���ֽ� --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
	<%-- ʵ�շ��� --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>
    <%-- Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� --%> 
    <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
    <%-- Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� --%> 
    <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
    <%-- Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ�� --%>
    <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
	<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
  				<h2>���߿���</h2>
				<div class="blank10"></div>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
				<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
				<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
				<a href="javascript:void(0)">4. ����ѡ��</a>
				<a href="javascript:void(0)">5. ���÷�������</a> 
				<a href="javascript:void(0)" >6. ����ȷ��</a>
				<a href="javascript:void(0)">7. �����ɷ�</a>
				<a href="javascript:void(0)" class="on">8. ȡ����ӡ��Ʊ</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
 				<div class="blank60"></div>
 				<div class="p40 pr0">
					<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='telnum' /></span></p>
					<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
					<div class="blank20"></div>
  					<div class="line w625"></div>
					<div class="recharge_result tc">
						<%
						if ("1".equals((String)request.getAttribute("payType")) )
						{
						%>
							<p class="title yellow pt30">���Ŀ����ѳɹ�����ȡ��������������SIM����</p>
							<p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡСƱ��������á�</p>
						<%
						}
						else 
						{
						%>
							<p class="title yellow pt30">���Ŀ����ѳɹ�����ȡ������SIM����</p>
							<p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡСƱ��������á�</p>
						<%
						}
						%>
 							
  						<div class="btn_box3_echo clearfix">
	   						<s:if test = " canNotPrint == 0 ">
	   							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">��ӡСƱ (�밴1��)</a>
	   							<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:20px; ">�ɷ���� (�밴2��)</a>
	   						</s:if>
	   						<s:else>
	   							<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:180px; ">�ɷ���� (�밴2��)</a>
	   						</s:else>
  						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>	
</form>
</body>
<script type="text/javascript">
// �رն�����,�˿�
doFinish();
</script>
</html>