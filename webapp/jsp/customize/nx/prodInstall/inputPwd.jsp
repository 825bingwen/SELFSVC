<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// �������ɷ���������ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 

// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>	
<script language="javascript">

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
	return true;
}	

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//����
	if (key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
	//�����77
	else if(key == 77)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = "";
		}
	}
	//����	
	else if (key == 8 || key == 32 || key == 66)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = backString(etarget.value);
		}
	}	
}

//��ֹ�ظ��ύ
var submitFlag = 0;

// �ȴ���ȡ����ľ��
var waitingToken;

// �ȴ�ʱ��ľ��
var timeToken;

// ������ȴ�ʱ��
var limitTime = <%=limitTime %>;

// ȡ��
function goback(menuid)
{
	// �������������ṩ�Ĺر��������
	ret = closePin();
	writeDetailLog("<%=chargelog_detailflag %>","�ر��������������:" + ret);
	
	// �رտ���
	ret = CloseReadingCardFixing();
	writeDetailLog("<%=chargelog_detailflag %>","�رտ���:" + ret);
								
	// �򿪼��̴���
	ret = OpenCom();
	writeDetailLog("<%=chargelog_detailflag %>","�򿪳��̵��������:" + ret);
								
	// ��������ģʽ
	ret = SetWorkMode(0);
	writeDetailLog("<%=chargelog_detailflag %>", "��������ģʽ:" + ret);
	
	// �������򿪼���ʼ��
	ret = InitReadUserCard();
	writeDetailLog("<%=chargelog_detailflag %>","�򿪶�����:" + ret);
	
	// �ɷѲ�����ȡ��
	setException("�ɷѲ�����ȡ������ע��ȡ�����Ĵ����");
}

//�����쳣
function setException(errorMsg) 
{		
	writeDetailLog("<%=chargelog_detailflag %>", "�����쳣:" + errorMsg);
				
	if (submitFlag == 0) 
	{
		// �ύ���
		submitFlag = 1;	
		
		// �������������ṩ�Ĺر��������
		ret = closePin();
		writeDetailLog("<%=chargelog_detailflag %>","�ر��������������:" + ret);
		
		// �رտ���
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=chargelog_detailflag %>", "�رտ���:" + ret);
									
		// �򿪼��̴���
		ret = OpenCom();
		writeDetailLog("<%=chargelog_detailflag %>", "�򿪳��̵��������:" + ret);
									
		// ��������ģʽ
		ret = SetWorkMode(0);
		writeDetailLog("<%=chargelog_detailflag %>","��������ģʽ:" + ret);
		
		// �������򿪼���ʼ��
		ret = InitReadUserCard();
		writeDetailLog("<%=chargelog_detailflag %>","�򿪶�����:" + ret);
	
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ��ʾ������Ϣ
		document.getElementById("errormessage").value = errorMsg;
		
		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }charge/goCardError.action";
		document.readPwdForm.submit();	
	}				
}
			
// �ύת��ȷ��ҳ��
function doSub() 
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//�ύ���
		
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ִ���ύ
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }prodInstall/toMakeSure.action";
		document.readPwdForm.submit();
	}
}

//��ȡ�������
function getCardPasswordStatus()
{
	// ����Ƿ�ʱ
	if (limitTime <= 0 && submitFlag == 0)
	{
				// ��ʱ�˳�
       	setException("��ȡ�û����п����볬ʱ���޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
       	return;
	}
		
	// ������ʱ��һ��limitTime��
	limitTime = limitTime - 1;
	
	// ����ҳ����ʾ
	document.getElementById("tTime").value = limitTime;
	
	try 
	{
		// ��ȡ�û���������״̬,13 �ɹ���ʧ�ܷ���-1
		writeDetailLog("<%=chargelog_detailflag %>", "��ʼgetPass()ʱ�䣺");
		
		var ret = getPass();
		
		writeDetailLog("<%=chargelog_detailflag %>", "getPass()" + ret);
		
		writeDetailLog("<%=chargelog_detailflag %>", "����getPass()ʱ�䣺" );
		
		if (ret == 13) 
		{
			if (document.getElementById("cardPwd").value.length==6)
			{
		      	// �ύ
				doSub();											
			}
			else
			{
				// ����λ������
				setException("��ȡ�����Ĵ��������ѡ���������ɷѲ�������λ��ȷ�Ĵ�����룡");
			}
		}
		
		// δȡ���������
		else if (ret == -1)
		{
			writeDetailLog("<%=chargelog_detailflag %>", "δȡ��������� ret = -1");
		}
		// �쳣
		else if (ret == -2)
		{
			
			writeDetailLog("<%=chargelog_detailflag %>", "ȡ����������쳣 ret = -2");
			
			//�����ʱ����
			clearInterval(waitingToken);
			
			// ȡ����������쳣
			setException("ȡ��������쳣����ȡ�����Ĵ����");
		}
		// 0 - 9 * 
		else if (ret == 42)
		{
			document.getElementById("cardPwd").value = document.getElementById("cardPwd").value + "*";
		}
		// ȡ��
		else if (ret == 27)
		{
			//�����ʱ����
			clearInterval(waitingToken);
			
			// ȡ����������쳣
			setException("�ɷѲ�����ȡ������ȡ�����Ĵ����");
		}
		// ɾ��
		else if (ret == 8)
		{
			var pwdStr = document.getElementById("cardPwd").value;
			if (pwdStr.length > 0)
			{
				document.getElementById("cardPwd").value = pwdStr.substr(0,pwdStr.length-1);
			}
		}
	}
	catch (e) 
	{
		writeDetailLog("<%=chargelog_detailflag %>", "��ȡ������� �쳣");
		
		setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
	}
}

//��ȡ�������
function getTimeStatus() 
{
	// ����Ƿ�ʱ
	if (limitTime <= 0 && submitFlag == 0)
	{
		// ��ʱ�˳�
       	setException("��ȡ�û����п����볬ʱ���޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
       	return;
	}
		
	// ������ʱ��һ��limitTime��
	limitTime = limitTime - 1;
	
	// ����ҳ����ʾ
	document.getElementById("tTime").value = limitTime;
}

//����ʱ���������
function startclock() 
{	
	try 
	{
		// ����ȡ����ӿ�
		waitingToken = setInterval("getCardPasswordStatus()", 500);
		
		// ��ʱ��
		//timeToken = setInterval("getTimeStatus()", 1000);
	}
	catch (e) 
	{
		writeDetailLog("<%=chargelog_detailflag %>", "����ʱ���������");
		
		setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
	}
}

//ҳ�����ʱִ��
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		writeDetailLog("<%=chargelog_detailflag %>", "������������ҳ��");
		
		// �ر��������
		writeDetailLog("<%=chargelog_detailflag %>", "���ó��̵�closeCom()");
		
		var ret = CloseCom();
		
		writeDetailLog("<%=chargelog_detailflag %>", "����:"+ret);
		               
		if (ret != "0" && ret != 0) 
		{
			setException("��ȡ�û����п�����ǰ���ù���������̴��ڷ����쳣��");
			return;
		}
		
		// �رն�����������������д򿪵��߳�
		writeDetailLog("<%=chargelog_detailflag %>", "���ó��̵�CloseReadingCardFixing()");
		               
		ret = CloseReadingCardFixing();
		
		writeDetailLog("<%=chargelog_detailflag %>", "����:"+ret);
		
		if (ret != "0" && ret != 0) 
		{
			setException("��ȡ�û����п�����ǰ���ùرն�����������������д򿪵��̷߳����쳣��");
			return;
		}
		
		// �������������ṩ�Ĵ�������̽ӿ�
		writeDetailLog("<%=chargelog_detailflag %>", "����������openPin()");
		var ret = openPin();
		writeDetailLog("<%=chargelog_detailflag %>", "����:"+ret);
		if (ret != "0" && ret != 0) 
		{
			setException("׼����ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
      return;
		}
		else
		{		
			// ���á���ҳ�������˳�����ť
      document.getElementById("homeBtn").disabled = true;
      document.getElementById("quitBtn").disabled = true;

      // ������ʱ����
      writeDetailLog("<%=chargelog_detailflag %>", "������ʱ����");
			startclock();
		}
		
	}
	catch (ex) 
	{
		setException("׼����ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
        return;
	}
}	
</script>
</head>
<body scroll="no">
	<form name="readPwdForm" method="post" target="_self">
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
		<input type="hidden" id="cardnumber" name="cardnumber" value=''/>
		<input type="hidden" id="errorType" name="errorType" value=''/>
		<input type="hidden" id="errormessage" name="errormessage" value=''/>
		
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
   					<div class="p40 pr0">
   						<p class="tit_info"><span class="bg"></span>��ͨ��<span style="color:#ffff00">�·��Ľ�������</span>�������Ŀ����룬����<span style="color:#ffff00">"ȷ��"</span>�����ѣ�</p>
   						<p class="tit_info"><span>�����ȡʱ����</span><span class="yellow"><%=limitTime %></span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />��</p>
   						<div class="blank25"></div>                
               			<div class="blank25"></div>
               			<div class="input" style="margin-left:100px">
               				<ul>
               					<li style="width:120px; margin-top:22px;">������룺</li>
               					<li style="width:255px;"><input class="text2" name="cardPwd" id="cardPwd" type="password" maxlength="6"/></li>
               				</ul>
                       	</div>
                       	<div class="blank50"></div>
       					<div class="blank50"></div>
       					<div class="gif_animation">
       						<img src="${sessionScope.basePath }images/jp.gif" alt="����������" />
       					</div>
   					</div>
   				</div>
			</div>
		</div>
		
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">
	clearTimeout(timeOut);
    bodyLoad();     
    // ��ʶ�ؼ�ʹ��
   	closeStatus = 1;   
</script>
</html>
