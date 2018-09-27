<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// �������ɷѶ����ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>����ҳ��</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript">
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
	
	//����
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	setException("�ɷѲ�����ȡ��");
}

// �����ȴ�ʱ��
var limitTime = <%=limitTime %>;

//�ȴ������ľ��
var waitingToken;

// �ύ��־
var submitFlag = 0;

// �ȴ���ȡ��־��1���ɹ���0��ʧ�ܡ����Ϊ1ʱ���û�����ȡ���ɷѲ����������ȡ��ˢ���ӿ�
var readingCard = 0;

//�����쳣
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		// �ύ��־��Ϊ1
		submitFlag = 1;
		
		//�����ʱ����
		clearInterval(waitingToken);
		
		//��ʾ������Ϣ
		document.getElementById("errormessage").value = errorMsg;
		
		//�ȴ������߳������ɹ��������쳣����ر�			
		if (readingCard == 1)
		{
			CancelReadingUserCard();
		}
		
		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }cardInstall/installError.action";
		document.readCardForm.submit();
	}			
}

// ������ɺ��ύת����������ҳ��
function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
	
		//�����ʱ����
		clearInterval(waitingToken);
		
		//ִ���ύ
		document.readCardForm.target = "_self";
		
		document.readCardForm.action = "${sessionScope.basePath }cardInstall/inputPwd.action";

		document.readCardForm.submit();	
	}			
}


// ��ö�����־
function getReadCardStatus() 
{
	//����ʱ�����
	if (limitTime <= 0 && submitFlag == 0)
	{           	
		setException("������ʱ���޷����нɷѲ���");
		return;
	}

	try 
	{
		// �򿪲��Ž����û����п� ���꿨�󷵻�״̬ �������뵽��������Ҫ��needReturnCardֵΪ1�� 
		// 0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
		var ret = ReadUserCardFinished();

		// ��δ��ȡ������Ϣ
		if (ret == "1" || ret == 1)
		{
			// ����ʱ��һ��limitTime��
			limitTime = limitTime - 1;
	
			// �趨������ʾ
			document.getElementById("tTime").value = limitTime;
		}
		// �����ɹ�
		else if (ret == "0" || ret == 0)
		{
   	 		// ��ȡ���
			readingCard = 0;
	
			// ��Ҫ�˿�
			document.getElementById("needReturnCard").value = "1";
			
			// �ڽ��յ�ReadBankCardFinished()�¼������ɹ���֪ͨ��Ӧ��ʱ���ô˽ӿڣ������浽ҳ���Ԫ���У���ȷ��������ҳ���ܻ�ȡ�����п�����Ϣ��
			var cardNO = GetUserCardInfo();
			
			if (cardNO == "")
			{
				//��ȡ����Ϣʧ��
				setException("��ȡ����Ϣʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
			}
			else
			{
				// ��¼����
				document.getElementById("cardnumber").value = cardNO;
				
				// �ύת����������ҳ��
				doSub();
			}	
									
		} 
		// ����ʧ��
		else if (ret == "-1")
		{
			readingCard = 0;//��ȡʧ��

			// ��Ҫ�˿�
			document.getElementById("needReturnCard").value = "1";	
			
			setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
		}				
	}
	catch (e) 
	{
		
		readingCard = 0;//��ȡ�����쳣
		
		setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
	}
}

//����ʱ���������
function startclock() 
{	
	try 
	{
		waitingToken = setInterval("getReadCardStatus()", 1000);				
	}
	catch (e) 
	{
		setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
	}
}

//ҳ�����ʱִ��
function bodyLoad() 
{
	try 
	{	
	    // �ӿڵ��÷�����Ϣ
		var ret;
		
		// ����ʾ��
		alertJd("���ڼ���,���Ժ�...");
		
		// ��ʶ�ؼ�ʹ��
		closeStatus = 1;

		// �رռ��̴���
		try
		{
			ret = CloseCom();
		}
		catch(e)
		{
			// �򿪼��̴��ڡ���������ģʽ
			OpenCom();
			SetWorkMode(0);
			
			setException("����������̴��ڷ����쳣��");
			return;
		}
		
		if (ret != "0" && ret != 0) 
		{
			// �򿪼��̴��ڡ���������ģʽ
			OpenCom();
			SetWorkMode(0);
			
			setException("����������̴��ڷ����쳣��");
			return;
		}

		// ׼��ˢ�����˽ӿڰ���ǩ���������ܣ�
		try
		{
			ret = ReadingUserCard();
		}
		catch(e)
		{
			// �򿪼��̴��ڡ���������ģʽ
			OpenCom();
			SetWorkMode(0);
			
			setException("����׼��ˢ�������쳣��");
			return;
		}

		// ������ʾ��
		wiWindow.close()

		// ����׼��ˢ���󷵻�
		if (ret != "0" && ret != 0) 
		{
			setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
            return;
		}
		else
		{		
			//�ȴ�����
			readingCard = 1;
			
			//�ȴ��������������ɹ��󣬾���Ҫ�رոý��̡�������ҳ�������˳�����ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
			
			// �򿪼��̴��ڡ���������ģʽ
			OpenCom();
			SetWorkMode(0);
			
			// ������ʱ����
			startclock();
		}
	}
	catch (ex) 
	{
		setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
        return;
    }
}
	
</script>
</head>
<body scroll="no">
<form name="readCardForm" method="post" target="_self">

	<!-- �հ׿���Ϣ -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!--����Ϣ���ĺ��� -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />
	<input type="hidden" id="pin1" name="simInfoPO.pin1" value='<s:property value="simInfoPO.pin1" />' />
	<input type="hidden" id="pin2" name="simInfoPO.pin2" value='<s:property value="simInfoPO.pin2" />' />
	<input type="hidden" id="puk1" name="simInfoPO.puk1" value='<s:property value="simInfoPO.puk1" />' />
	<input type="hidden" id="puk2" name="simInfoPO.puk2" value='<s:property value="simInfoPO.puk2" />' />
	<input type="hidden" id="eki" name="simInfoPO.eki" value='<s:property value="simInfoPO.eki" />' />
	<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
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
	<!-- ��ƷID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />

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
	<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'/>
	<input type="hidden" id="cardnumber" name="cardnumber" value=''/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2>���߿���</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
				<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
				<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
				<a href="javascript:void(0)">4. ����ѡ��</a>
				<a href="javascript:void(0)">5. ����ȷ��</a>
				<a href="javascript:void(0)" class="on">6. �����ɷ�</a>
				<a href="javascript:void(0)">7. ȡ����ӡСƱ</a>
			</div>
			
			<div class="b712">
    					<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40 pr0">
      					<p class="tit_info"><span class="bg"></span>��������Ĵ����<span class="yellow">ҵ�����������˿�����ע��ȡ����</span></p>
      					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
      					<div class="blank10"></div>   
     						<div class="blank20"></div>
      					<div class="blank10"></div>
     						<div class="gif_animation">
     							<img src="${sessionScope.basePath }images/gif1.gif" alt="��忨" />
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
	closeStatus = 1;
    bodyLoad();        
</script>
</html>
