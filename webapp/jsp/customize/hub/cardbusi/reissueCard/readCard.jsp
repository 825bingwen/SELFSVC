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
		document.readCardForm.action = "${sessionScope.basePath }reissueCard/goCardError.action";
		document.readCardForm.submit();
	}			
}

// ������ɺ��ύת����������ҳ��
function doSub()
{
	if (submitFlag == 0)
	{
		//�����ȴ���
		openRecWaitLoading();
		
		submitFlag = 1;
	
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ִ���ύ
		document.readCardForm.action = "${sessionScope.basePath}reissueCard/inputCardPwd.action";
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
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYUNIONCARD %>">
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<!-- ���úϼ� -->
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
			
			<!--------------������־��Ϣ -------------->
			<%-- �հ׿�����--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
   	 		<%--������־id�����ڸ���������־--%>
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
    
    		<!--------------������־��Ϣ -------------->
    		<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value=''>
    		
    		<!--------------simInfoPO��Ϣ -------------->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
			<input type="hidden" id="smsp" name="simInfoPO.smsp" value="<s:property value="simInfoPO.smsp" />" />
			<input type="hidden" id="pin1" name="simInfoPO.pin1" value="<s:property value="simInfoPO.pin1" />" />
			<input type="hidden" id="pin2" name="simInfoPO.pin2" value="<s:property value="simInfoPO.pin2" />" />
			<input type="hidden" id="puk1" name="simInfoPO.puk1" value="<s:property value="simInfoPO.puk1" />" />
			<input type="hidden" id="puk2" name="simInfoPO.puk2" value="<s:property value="simInfoPO.puk2" />" />
			<input type="hidden" id="eki" name="simInfoPO.eki" value="<s:property value="simInfoPO.eki" />" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>����</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
    					<a href="javascript:void(0)">3. ����ȷ��</a>
    					<a href="javascript:void(0)">4. ѡ��֧����ʽ</a>
    					<a href="javascript:void(0)" class="on">5. �����ɷ�</a>
    					<a href="javascript:void(0)">6. ȡ����ӡСƱ</a>
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
