<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// �������ɷ���������ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 
//���������Ƿ��ڳ�ʱ����ùر�������̽ӿ�
String IsNeedCloseEncryptKey = (String)PublicCache.getInstance().getCachedData("IsNeedCloseEncryptKey");

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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>	
<script language="javascript">
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
	
	//����
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
	else if (key == 8 || key == 32 || key == 66 || key ==77)
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

//�ȴ���ȡ����ľ��
var waitingToken;

// ������ȴ�ʱ��
var limitTime = <%=limitTime %>;

// ȡ��
function goback(menuid)
{
	setException("�ɷѲ�����ȡ������ע��ȡ�����Ĵ����");
}

//�����쳣
function setException(errorMsg) 
{		
	// �򿪼��̴��ڡ���������ģʽ
	var ret = OpenCom();
	ret = SetWorkMode(0);
				
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//�ύ���
	
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ��ʾ������Ϣ
		document.getElementById("errormessage").value = errorMsg;
		
		//�쳣������ֻҪ�������쳣��Ҫ��¼��־  
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }reissueCard/goCardError.action";
		document.readPwdForm.submit();	
	}				
}
			
// �ύת��ȷ��ҳ��
function doSub() 
{
	if (submitFlag == 0) 
	{
		//�����ȴ���
		openRecWaitLoading();
		submitFlag = 1;	//�ύ���
		
		//�����ʱ����
		clearInterval(waitingToken);
		
		// ִ���ύ
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }reissueCard/makeSure.action";
		document.readPwdForm.submit();
	}
}
//mod begin 20130901 for OR_HUB_201308_238 
//�ر�������̽ӿ�  OR_HUB_201308_238 20130901
function _closeEncryptKey(){
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CloseEncryptKey();
		return ret;
	}catch(e){
		return "";
	}
}
//��ȡ�������
function getCardPasswordStatus() 
{
	// ����Ƿ�ʱ
	if (limitTime <= 0 && submitFlag == 0)
	{ 
		//��ʱ����ùر�������̽ӿ�
		if("1" == <%=IsNeedCloseEncryptKey%>){
			var result = _closeEncryptKey();
			if(result == "0"){
				setException("��ȡ�û����п����볬ʱ���޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
			}
			else if(result == ""){
				setException("ϵͳ��������ģʽ�쳣���޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
			}
			else{
				setException("ϵͳ��������ģʽʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
			}
		}
		else{
			setException("��ȡ�û����п����볬ʱ���޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
		}
	}
	//mod begin 20130901 for OR_HUB_201308_238 
	try 
	{
	    // ����׼����������ӿڣ�ReadingPassword()���󣬶�ʱѭ�����ô˽ӿڻ�ȡ�û���������״̬��
		// 0 ��ʾ��������������-1 ��ʾ��������δ�꣬�쳣�������룻1 �ȴ��û��������
		var ret = ReadCardPasswordFinished();
		
		// �ȴ��û��������
		if (ret == "1" || ret == 1)
		{
			// ������ʱ��һ��limitTime��
			limitTime = limitTime - 1;
			
			// ����ҳ����ʾ
			document.getElementById("tTime").value = limitTime;
		}
		else if (ret == "0" || ret == 0) 
		{
			// �򿪼��̴��ڡ���������ģʽ
			ret = OpenCom();
			if (ret != "0" || ret != 0)
			{
				setException("���������ʧ�ܣ���ȡ�����Ĵ����");
				return;
			}
			ret = SetWorkMode(0);
			if (ret != "0" || ret != 0)
			{
				setException("��������ģʽʧ�ܡ���ȡ�����Ĵ����");
				return;
			}
			
            // �ύ
			doSub();															
		} 
		else if (ret == "-1")
		{
			setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
		}				
	}
	catch (e) 
	{
		setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
	}
}

//����ʱ���������
function startclock() 
{	
	try 
	{
		waitingToken = setInterval("getCardPasswordStatus()", 1000);
	}
	catch (e) 
	{
		setException("��ȡ�û����п�����ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
	}
}

//ҳ�����ʱִ��
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		// �رռ��̴���
		var ret = CloseCom();
		if (ret != "0" && ret != 0) 
		{
			setException("׼��ˢ��ǰ���ù���������̴��ڷ����쳣��");
			return;
		}
		
		// ׼���������� ֪ͨ�����ն˶����豸׼����ȡ�û����п����룬���л����������ģʽ
		var ret = ReadingPassword();
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
		<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
		<input type="hidden" id="payType" name="payType" value="1">
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>

		<input type="hidden" id="errorType" name="errorType" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<!-- ���úϼ� -->
		<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
		
		<!--------------������־��Ϣ -------------->
		<%-- �հ׿�����--%>
		<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
   	 	<%--������־id�����ڸ���������־--%>
   		<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
    
    	<%---------------������־��Ϣ ---------------%>
    	<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value='<s:property value="cardChargeLogVO.cardnumber" />'>
		<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
		
    	<%---------------simInfoPO��Ϣ -------------%>
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
					<a href="javascript:void(0)">2. ��ȡ����֤��Ϣ</a>
   					<a href="javascript:void(0)">3. ����ȷ��</a>
   					<a href="javascript:void(0)">4. ѡ��֧����ʽ</a>
   					<a href="javascript:void(0)" class="on">5. �����ɷ�</a>
   					<a href="javascript:void(0)">6. ȡ����ӡСƱ</a>
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