<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%

// �������ɷѶ����ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>����ҳ��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
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
	return;
}

// �����ȴ�ʱ��
var limitTime = <%=limitTime %>;

//�ȴ������ľ��
var waitingToken;

// �ύ��־
var submitFlag = 0;

// �ȴ���ȡ��־��1���ɹ���0��ʧ�ܡ����Ϊ1ʱ���û�����ȡ���ɷѲ����������ȡ��ˢ���ӿ�
var readingCard = 0;

// �����쳣
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
	    // �ύ��־��Ϊ1
		submitFlag = 1;
		
		// �����ʱ����
		clearInterval(waitingToken);
		
		// ��ʾ������Ϣ
		document.getElementById("errormessage").value = errorMsg;
		
		//�ȴ������߳������ɹ��������쳣����ر�			
		if (readingCard == 1)
		{
			CancelReadingUserCard();
		}
		
		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }prepareCard/goCardError.action";
		document.readCardForm.submit();
	}			
}

// ������ɺ��ύת����������ҳ��
function doSub()
{
	var cardnumber = document.getElementById("cardnumber").value;

	if (submitFlag == 0)
	{
		submitFlag = 1;
	
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ִ���ύ
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }prepareCard/cardPwdPrepare.action";
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
			
			// ��ʶ�ؼ�δʹ��
		    //closeStatus = 0;
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
				return;
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
			//��ȡʧ��
			readingCard = 0;
			
			//change by LiFeng �޸Ķ���ʧ�ܲ��˿�����
			// ��Ҫ�˿�
			document.getElementById("needReturnCard").value = "1";
			
			setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
			return;
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
			
			setException("�ر�������̴��ڷ����쳣��");
			return;
		}
		
		if (ret != "0" && ret != 0) 
		{
			// �򿪼��̴��ڡ���������ģʽ
			OpenCom();
			SetWorkMode(0);
			
			setException("�ر�������̴��ڷ����쳣��");
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
			<!-- �ɷѷ�ʽ��1����������4���ֽ� -->
			<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
			<!-- �ܷ���-->
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee'/>" />
			<!-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ -->
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<!--������־id�����ڸ���������־-->
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
    
			<!--------------���֤��Ϣ -------------->
			<!-- ���� -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
			<!-- �Ա� -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
			<!-- ֤����ַ -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
			<!-- ��ʼʱ�� -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value="<s:property value='idCardPO.idCardStartTime' />" />
			<!-- ����ʱ�� -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value="<s:property value='idCardPO.idCardEndTime' />" />
			<!-- ����Ϣ -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value="<s:property value='idCardPO.idCardContent' />" />
			<!-- ��Ƭ��Ϣ -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value="<s:property value='idCardPO.idCardPhoto' />" />
			
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<!-- ���п��� -->
			<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value="<s:property value='cardChargeLogVO.cardnumber' />">
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			
			<!--------------�հ׿���Ϣ -------------->
		    <!-- �հ׿�����-->
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value='cardBusiLogPO.blankCard'/>" />
			<!--IMSI -->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value='simInfoPO.imsi'/>" />
			<!--ICCID -->
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value='simInfoPO.iccid'/>" />
			<!-- �հ׿���Ϣ�ַ�����iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2�� -->
			<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>" />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>����</h2>
						<div class="blank10"></div>
	   					<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ѡ����֤��ʽ</a>
						<a href="javascript:void(0)">3. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)">4. ����ȷ��</a>
	   					<a href="javascript:void(0)">5. ѡ��֧����ʽ</a>
	   					<a href="javascript:void(0)" class="on">6. �����ɷ�</a>
	   					<a href="javascript:void(0)">7. �¿���ӡСƱ</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>��������Ĵ����<span class="yellow">ҵ�����������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text"  id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
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
