<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// �������ɷ���������ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
		return false;//��仰��ؼ�
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
	
	/**
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
	**/	
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
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�ر��������������:" + ret);
		
		// �رտ���
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�رտ���:" + ret);
									
		// �򿪼��̴���
		ret = OpenCom();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�򿪳��̵��������:" + ret);
									
		// ��������ģʽ
		ret = SetWorkMode(0);
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"��������ģʽ:" + ret);
		
		// �������򿪼���ʼ��
		ret = InitReadUserCard();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�򿪶�����:" + ret);
	
	// �ɷѲ�����ȡ��
	setException("�ɷѲ�����ȡ������ע��ȡ�����Ĵ����");
}

//�����쳣
function setException(errorMsg) 
{		
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "�����쳣:" + errorMsg);
				
	if (submitFlag == 0) 
	{
		// �ύ���
		submitFlag = 1;	
		
		// �������������ṩ�Ĺر��������
		ret = closePin();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�ر��������������:" + ret);
		
		// �رտ���
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�رտ���:" + ret);
									
		// �򿪼��̴���
		ret = OpenCom();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�򿪳��̵��������:" + ret);
									
		// ��������ģʽ
		ret = SetWorkMode(0);
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"��������ģʽ:" + ret);
		
		// �������򿪼���ʼ��
		ret = InitReadUserCard();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"�򿪶�����:" + ret);
	
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
		document.readPwdForm.action = "${sessionScope.basePath }charge/toMakeSure.action";
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
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "��ʼgetPass()ʱ�䣺");
		
		var ret = getPass();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "getPass()" + ret);
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "����getPass()ʱ�䣺" );
		
		//if (ret == 13) 
		if (true)
		{
				//if (document.getElementById("cardPwd").value.length==6)
				if (true)
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
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "δȡ��������� ret = -1");
		}
		// �쳣
		else if (ret == -2)
		{
			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "ȡ����������쳣 ret = -2");
			
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
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "��ȡ������� �쳣");
		
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
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "����ʱ���������");
		
		setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
	}
}

//ҳ�����ʱִ��
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "������������ҳ��");
		
		// �ر��������
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "���ó��̵�closeCom()");
		
		var ret = CloseCom();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "����:"+ret);
		               
		if (ret != "0" && ret != 0) 
		{
			setException("��ȡ�û����п�����ǰ���ù���������̴��ڷ����쳣��");
			return;
		}
		
		// �رն�����������������д򿪵��߳�
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "���ó��̵�CloseReadingCardFixing()");
		               
		ret = CloseReadingCardFixing();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "����:"+ret);
		
		if (ret != "0" && ret != 0) 
		{
			setException("��ȡ�û����п�����ǰ���ùرն�����������������д򿪵��̷߳����쳣��");
			return;
		}
		
		// �������������ṩ�Ĵ�������̽ӿ�
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "����������openPin()");
		var ret = openPin();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "����:"+ret);
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
      writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
             "������ʱ����");
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
		
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardnumber" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>��ֵ��������</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. �����ֻ�����</a> 
					<a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
   					<a href="javascript:void(0)">3. ѡ����</a> 
   					<a href="javascript:void(0)">4. ��������</a>
   					<a href="javascript:void(0)">5. ����������</a> 
   					<a href="javascript:void(0)" class="on">6. ��������</a>
   					<p class="recharge_tips">ͨ�������������봢����롣</p>
   					<a href="javascript:void(0)">7. �˶���Ϣ</a>
    				<a href="javascript:void(0)">8. ���</a>
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
