<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("recFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

// ���Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>���п�����ȷ��</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script language="javascript">
// �����ύ��ʶ
var submitFlag = 0;

// �쳣�ύ��־ 0:δ�ύ 1:���ύ
var exSubmitFlag = 0;

// �˶�ʱ��
var limitTime = 60;

//����ʣ��ʱ��ľ��
var timeToken;		

var termid = "<%=termInfo.getTermid() %>";

// �̻�����
var unitType = "1";

// �������� 'A':���ѽ��� 'B':�ش�ӡ 'C':�����
var businesstype = "A";// 

//82��220 ����		
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
		return false;
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
	
	//�ύ
	if (key == 13 || key == 89 || key == 221)
	{
		commitBusi();
	}
	// ���������ѣ�ȷ�Ͻ����棬�û��������˳�
	//�˳���82
	else if (key == 82)
	{
		cancelBusi();
	}
}

//�����쳣
function setException(errorMsg) 
{
	//if (exSubmitFlag == 0)
	//{
		exSubmitFlag = 1;	//�ύ���
		
		writeDetailLog("<%=chargelog_detailflag %>", "�����������쳣��" + errorMsg);
			
		//�����ʱ����
		clearInterval(timeToken);
	
		document.getElementById("errormessage").value = errorMsg;
		
		//�쳣����ֻҪ�������쳣��Ҫ��¼��־
		document.actForm.target = "_self";
		document.actForm.action = "${sessionScope.basePath }prodInstall/installError.action";
		document.actForm.submit();
	//}
}

// ��BOSS����(������)
function goSuccess() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�ѳɹ�");
	document.getElementById("toFee").value = document.getElementById("tMoney").value;
	//�ύ�ɷ�����
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }prodInstall/installCardCommit.action";
	document.actForm.submit();			
}

function strlen(str)    
{    
    var i;    
    var len;    
        
    len = 0;    
    for (i=0;i<str.length;i++)    
    {    
        if (str.charCodeAt(i)>255) len+=2; else len++;    
    }
    return len;    
} 

//�������ۿ�
function fPosPay()
{
	try
	{
		writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�֮ǰ��¼������־");
	
	document.getElementById("errorType").value = "add";
	
	//�ۿ�֮ǰ��¼��־
	var url = "${sessionScope.basePath }prodInstall/addChargeLog.action";
	
	var params = "telnum=" + <s:property value='telnum' /> ;
	params = params + "&paytype=1";
	params = params + "&tMoney=" + <s:property value='recFee' />*100;
	params = params	+ "&status=00";
	params = params + "&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־'));
	params = params + "&acceptType=" + document.getElementById("acceptType").value;
	params = params	+ "&region=" + document.getElementById("region").value;

	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
		var dataArray = resXml.split("~~");
		
		//��¼��־�ɹ�
		if (dataArray[0] == "1") 
		{
			writeDetailLog("<%=chargelog_detailflag %>", "��־��¼�ɹ�");
		
			//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
			document.getElementById("errorType").value = "update";
				
			document.getElementById("chargeId").value = dataArray[1];							
			
			//��¼�����ţ��ɷѼ�¼��ţ�
			document.getElementById("dealNum").value = dataArray[1];				
			try
			{
  					writeDetailLog("<%=chargelog_detailflag %>", "�����ر�");
  					
  				//������
  				// �رն�����������������д򿪵��߳�
				var ret = CloseReadingCardFixing();

				if (ret != 0)
				{
					setException("�رն�����������������д��߳�ʧ�ܣ���ȡ��������������");
						
					return;
				}
			}
			catch(e)
			{
				setException("�رն�����������������д��߳�ʧ�ܣ���ȡ��������������");
						
				return;
			}
				
			// �����������Ϊ�������ģʽ
			try
			{
				writeDetailLog("<%=chargelog_detailflag %>", "����������̹���ģʽΪ����");
				
				//������
				var ret = SetWorkMode(1);// �����������Ϊ�������ģʽ
				
				if (ret == -1)
				{
					setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ�ܣ���ȡ��������������");
						
					return;
				}
			}
			catch(e)
			{
				setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ�ܣ���ȡ��������������");
					
				return;
			}							
				
			// �����������ۿ�����	
			var continueFlag = false;
			
			// �������
			var bankrequest;
			
			// ��������
			var bankresponse;
			
			// LRCУ��_3λ�������
			var randomNumber = "" + Math.floor(Math.random()*10) + Math.floor(Math.random()*10) + Math.floor(Math.random()*10);
			
			try
			{
				// ����������
				// POS����(8) + POSԱ����(8) + �������ͱ�־(2)_'41:�ɷ�' + ���(12) + ԭ��������(12) + ԭ���ײο���(12) + ԭƾ֤��(6) 
				// LRCУ��(3) + �绰����(20) + ����Ʊ����(2)_00:�����ط�Ʊ��01:���ط�Ʊ������ʱΪ�ո�
				// ������(2)_01�����뽻��,11�������ʺŽ��� + ��ҵ��Ϣ1(20) + ��ҵ��Ϣ(20)
				//bankrequest = formatStr(termid,'right',' ',8);// POS����(8)
				// POS����(8)
				bankrequest = createBlankStr(8);
				
				// POSԱ����(8)
				bankrequest = bankrequest + createBlankStr(8);
				
				// �������ͱ�־(2)_'41:�ɷ�'
				bankrequest = bankrequest + '41';
				
				// ���(12)
				bankrequest = bankrequest + formatStr('<%=fenMoney %>','left','0',12);
				
				// ԭ��������(8)
				bankrequest = bankrequest + createBlankStr(8);
				
				// ԭ���ײο���(12)
				bankrequest = bankrequest + createBlankStr(12);
				
				// ԭƾ֤��(6)
				bankrequest = bankrequest + createBlankStr(6);
				
				// LRCУ��(3)
				bankrequest = bankrequest + randomNumber;
				
				// �绰����(20)
				bankrequest = bankrequest + formatStr('<s:property value="telnum" />','right',' ',20);
				
				// ����Ʊ����(2)_00:�����ط�Ʊ��01:���ط�Ʊ������ʱΪ�ո�
				bankrequest = bankrequest + '01';
				
				// ������(2)_01�����뽻��,11�������ʺŽ���
				bankrequest = bankrequest + '01';
				
				// ��ҵ��Ϣ1
				bankrequest = bankrequest + createBlankStr(20);
				
				// ��ҵ��Ϣ2
				bankrequest = bankrequest + createBlankStr(20);
				
				writeDetailLog("<%=chargelog_detailflag %>", "�ۿ������ģ�" + bankrequest);
						
				// ������
				// ������:2,�����к�:4,����:20,ƾ֤��:6,���:12,����˵��:40,�̻���:15,�ն˺�:8,���κ�:6,��������:4,
				// ����ʱ��:6,���ײο���:12,��Ȩ��:6,��������:4,LRCУ��:3
				
				// ����������
				if (true)// true:���� false:����
				{
					window.parent.document.getElementById("unionpluginid").bankrequest = bankrequest;
					
					// ִ�нɷ�
					window.parent.document.getElementById("unionpluginid").trans();
					
					// ���׷��ر���
					bankresponse = window.parent.document.getElementById("unionpluginid").BankResponse;
				}
				else
				{
					// �ɹ�
					bankresponse = '00    4563518600005509778 001778000000002000���׳ɹ�                                09510000000000100000002      1126164654000001217097          ' + randomNumber;
					
					// ʧ��
					//bankresponse = 'Y1                                          ���ŵ���Ϣ����                                                                                          '; 
				}
				
				writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�Ӧ���ģ�" + bankresponse);
				
				continueFlag = true;
			}
			catch (e){}
			
			// �����������Ϊ��ͨ����ģʽ
			try
			{
				writeDetailLog("<%=chargelog_detailflag %>", "����������̹���ģʽΪ���ĸ�ʽ");
				
				//������
				var ret = initKeyBoard();// �����������Ϊ��ͨ����ģʽ
			}
			catch(e)
			{
			}
				
			if (!continueFlag)
			{
				writeDetailLog("<%=chargelog_detailflag %>", "�ۿ��쳣");
				
				setException("�������ɷ�ʧ�ܣ���ȡ��������������");
				return;
			}
			
			// �ۿ�ɹ� ����148 �ɹ�00 �ȶ�������Ƿ�һ��
			if (bankresponse.substring(0,2) == "00" && strlen(bankresponse) == 148 && bankresponse.substring(bankresponse.length-3,bankresponse.length) == randomNumber)
			{
			    // ƾ֤�� + ���ײο���
				document.getElementById("terminalSeq").value = bankresponse.substring(26,32) + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);
				document.getElementById("tMoney").value = bankresponse.substring(32,44);
				writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�ѳɹ������ѽ�" + document.getElementById("tMoney").value);
						
				//������־
				var url1 = "${sessionScope.basePath }prodInstall/updateCardChargeLog.action";
		
				var params1 = "chargeId=" + document.getElementById("chargeId").value;// id
				params1 = params1 + "&bankno=" + bankresponse.substring(2,6);// �����к� �����д���
				params1 = params1 + "&cardnumber=" + bankresponse.substring(6,26);// ����
				params1 = params1 + "&vouchernum=" + bankresponse.substring(26,32);// ��֤��
				params1 = params1 + "&unionpayfee=" + bankresponse.substring(32,44);// �ۿ���
				params1 = params1 + "&unionpayuser=" + bankresponse.substring(bankresponse.length-64,bankresponse.length-49);// �̻���
				params1 = params1 + "&unionpaycode=" + bankresponse.substring(bankresponse.length-49,bankresponse.length-41);// �ն˺�
				params1 = params1 + "&busitype=" + encodeURI(encodeURI("�ɷѽ���"));// ��������
				params1 = params1 + "&posnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// ���κ�
				params1 = params1 + "&batchnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// ���κ�
				params1 = params1 + "&unionpaytime=" + bankresponse.substring(bankresponse.length-35,bankresponse.length-31) + bankresponse.substring(bankresponse.length-31,bankresponse.length-25);// �����ۿ�ʱ��
				params1 = params1 + "&businessreferencenum=" + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);// ���ײο���
				params1 = params1 + "&authorizationcode=" + bankresponse.substring(bankresponse.length-13,bankresponse.length-7);// ��Ȩ��
				params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;// �ն���ˮ
				params1 = params1 + "&status=01";
				params1 = params1 + "&description=" + encodeURI(encodeURI('����ʱ�ۿ�ɹ�'));
				params1 = params1 + "&telnum=<s:property value='telnum' />";
						
				var loader1 = new net.ContentLoader(url1, netload = function () {
					var resXml1 = this.req.responseText;
					
					//������־�ɹ�
					if (resXml1 == "1" || resXml1 == 1)
					{
						writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�ѳɹ��󣬸�����־��¼�ɹ�");
						
						//����
						goSuccess();									
					}
					//������־ʧ��
					else
					{
						writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�ѳɹ��󣬸�����־��¼ʧ��");
						
						setException("�������ɷѳɹ������Ǹ�����־ʧ�ܡ���ȡ��������������");
						
						return;
					}								
				}, null, "POST", params1, null);
				
			}
			//�ۿ�ʧ��
			else
			{
				writeDetailLog("<%=chargelog_detailflag %>", "�ۿ�ʧ��");
				
				setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��" + bankresponse.replace(/\s/g,''));
				return;
			}		
		}
		//��¼��־ʧ��
		else 
		{
			writeDetailLog("<%=chargelog_detailflag %>", "��־��¼ʧ��");
				
				setException("�ɷ�֮ǰ��¼��־ʧ�ܣ���ȡ��������������");
				
				return;
			}					
		}, null, "POST", params, null);	
	}
	catch (e)
	{
		setException("�������ɷ��쳣����ȡ��������������");
	}				
}

//����ʣ��ʱ��
function waitForSubmit() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "�ȴ��û�ȷ�Ͻɷ�");

if (limitTime <= 0)
{
	return;
}

//������ʱ��һ��limitTime��
limitTime = limitTime - 1;
			
document.getElementById("tTime").value = limitTime;
			
//������ʱ�����
if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
{
	submitFlag = 1;
	
	writeDetailLog("<%=chargelog_detailflag %>", "ȷ�ϳ�ʱ");
		
		//�����쳣��������־
		document.getElementById("errorType").value = "add";
		
		setException("�˶���Ϣ��ʱ����ȡ��������������");
	}
}

// ������ʱ��
function startclock() 
{
	try 
	{
		timeToken = setInterval("waitForSubmit()", 1000);
	}
	catch (e) 
	{
		//�����쳣��������־
		document.getElementById("errorType").value = "add";
		
		setException("�˶���Ϣʧ�ܣ���ȡ��������������");
	}
}

// ȷ�Ͻɷ�
function commitBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		writeDetailLog("<%=chargelog_detailflag %>", "ȷ�Ͻ���");
			
		//�����ʱ����
		clearInterval(timeToken);
				
		openChargeWaitLoading();
	
		setTimeout("fPosPay()", 500);
	}
}

<%--
* �û��������˳����ѽ��ס� 
*  ���������ѣ�ȷ�Ͻ����棬�û��������˳���
--%>
function cancelBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		writeDetailLog("<%=chargelog_detailflag %>", "�˳�����");
			
		//�û������˳�
		document.getElementById("errorType").value = "add";
		document.getElementById("quitFlag").value = "1";
		
		// �ر��������������
		var ret = closePin();
		writeDetailLog("<%=chargelog_detailflag %>", "�ر��������������:" + ret);
		
		// �򿪳��̵��������
		ret = OpenCom();
		writeDetailLog("<%=chargelog_detailflag %>", "�򿪳��̵��������:" + ret);
		
		// �����������Ϊ��ͨ����ģʽ
		ret = SetWorkMode(0);
		writeDetailLog("<%=chargelog_detailflag %>", "��������ģʽ:" + ret);
		
		// �رտ���
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=chargelog_detailflag %>", "�رտ���:" + ret);
		
		// �������򿪼���ʼ��
		ret = InitReadUserCard();
		writeDetailLog("<%=chargelog_detailflag %>", "�򿪶�����:" + ret);
	
		setException("����ȡ�����ν��ѣ���л����ʹ�ã���ȡ��������������");
	}			
}

function goBack(menu)
{
	cancelBusi();
}
</script>
</head>
<body scroll="no">
<form name="actForm" method="post">		
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
	
	<%-- ������ --%>
	<input type="hidden" id="dealNum" name="dealNum" value='' />
	
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
	
	<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
	<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'/>
	<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'/>
	<input type="hidden" id="terminalSeq" name="terminalSeq" value=''/>
	<input type="hidden" id="errorType" name="errorType" value=''/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="quitFlag" name="quitFlag" value=""/>
	
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
				<a href="javascript:void(0)">6. ����ȷ��</a>
				<a href="javascript:void(0)" class="on">7. �����ɷ�</a>
				<a href="javascript:void(0)">8. ȡ����ӡ��Ʊ</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
  					<div class="blank60"></div>
  					<div class="p40">
  						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="telnum" /></span></p>
  						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="recFee" /></span> Ԫ</p>
					<p class="tit_info_noheight"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />�롣</p>
					<p class="tit_info">ȷ�Ͻ��ѣ��밴���Ѽ�����Ҫȡ�����ν��ѣ��밴�˳�����</p>
					<div class="blank25"></div>
					<div class="line"></div>
  					<div class="blank60"></div>
  						
  					<div class="recharge_result tc">
  						<div class="btn_box2 clearfix">
  							<a href="javascript:void(0);" onclick="commitBusi();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����(��ȷ�ϼ�)</a>
  						</div>
  					</div>				
  				</div>
			</div>
		</div>
	</div>
	
	<div class="footer" id="footer">
		<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'"></a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';"></a>
		<a id="quitBtn" href="javascript:void(0);" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'" onclick="cancelBusi();return false;"></a>
	</div>
</form>
</body>
<script type="text/javascript">
clearTimeout(timeOut);
startclock();

// ��ʶ�ؼ�ʹ��
closeStatus = 1;
</script>
</html>
