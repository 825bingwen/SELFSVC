<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// ��ȡ���֤��Ϣ����ʱ�䣨��λ���룩
	String readIdCardTime = (String)PublicCache.getInstance().getCachedData("SH_READIDCARD_LIMITTIME");
	if ("".equals(readIdCardTime) || null == readIdCardTime)
	{
		// Ĭ��30���ȡʱ��
		readIdCardTime = "30";
	}
	
	// �ն˻���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�����֤ҳ��</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
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

	// �����������
	if (!KeyIsNumber(key)) 
	{
		return false;
	}
}

// �����Ƿ�������У��
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

//�����¼�
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

//������һҳ
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//�����ȴ���
		openRecWaitLoading();
		
		//���ùر����֤�Ķ����ӿ�
		CloseCardReader();
		
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

// �����ȴ�ʱ��
var limitTime = <%=readIdCardTime%>;


//�������ʣ��ʱ��ľ��
var timeToken;

//�ȴ������ľ��
var waitingToken;

// �ύ��־
var submitFlag = 0;

// ��ʼ�����֤�������߳�������־��1����������0��δ���������Ϊ1ʱ���û�����ȡ������������ùر����֤�Ķ����ӿ�
var initCardReader = 0;

//�����쳣
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//�����ȴ���
		openRecWaitLoading();
		setValue("errormessage",errorMsg);
		
		//�����ʱ����
		clearInterval(waitingToken);
		
		clearInterval(timeToken);
		
		//��ʼ�����֤�������߳������ɹ��������쳣����ر�			
		if (initCardReader == 1)
		{
			//���ùر����֤�Ķ����ӿ�
			CloseCardReader();
		}
		
		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.readCardForm.action = "${sessionScope.basePath }reissueCard/addRecLog.action";
		document.readCardForm.submit();
	}			
}

function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;

		//�����ȴ���
		openRecWaitLoading();
		
		// �����ʱ����
		clearInterval(waitingToken);
		
		clearInterval(timeToken);
		
		document.readCardForm.action = "${sessionScope.basePath}reissueCard/certShow.action";
		document.readCardForm.submit();	
	}			
}

//�������ʣ��ʱ��
function calLeftTime()
{
	if (limitTime <= 0)
	{
		return;
	}
	
	//����ʱ��һ��limitTime��
	limitTime = limitTime - 1;
	
	document.getElementById("tTime").value = limitTime;
	
	//����ʱ�����
	if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
	{           	
       	setException("������ʱ!Ϊ�㱣֤������Ϣ��ȫ�밴\"�˳�\"������ҳ,������������밴\"��һҳ\",ллʹ��!");
	}
}

// ѭ�����û�ȡ���֤�������ݽӿ�
function getReadCardStatus() 
{
	if (limitTime <= 0)
	{
		return;
	}
	
	try 
	{
		// �򿪲��Ž����û����֤ ���꿨�󷵻�״̬ �������뵽��������Ҫ��needReturnCardֵΪ1��
		var ret = GetIdCardContent();
		
		if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
		{
			//���û�ȡ�û���Ƭ�ӿ�
			var photo = GetUserPhoto();
	
			if(photo == "-1")
			{
				setException("��ȡ���֤��Ƭʧ�ܣ��޷�������ҵ��");
			}
			else
			{
				document.getElementById("idCardPhoto").value = photo;
			}
			
			//���ùر����֤�Ķ����ӿ�
			CloseCardReader();
			
			//���֤��Ϣ��0����~�Ա�~����~����~סַ~������ݺ���~ǩ������~��Ч����ʼ����~��Ч�ڽ�ֹ����~����סַ
			var idCardInfor = ret.substring(1,ret.length).split('~');
			
			//����
			var idCardName = idCardInfor[0];
			
			//�Ա�
			var idCardSex = idCardInfor[1];
			
			//֤��סַ
			var idCardAddr = idCardInfor[4];
			
			//������ݺ���
			var idCardNo = idCardInfor[5];
			
			//֤����ʼʱ��
    		var idCardStartTime = idCardInfor[7];
    				
    		//֤������ʱ��
    		var idCardEndTime = idCardInfor[8];
    
			// �趨�ύ����
			document.getElementById('idCardContent').value = ret;
			document.getElementById("idCardName").value = idCardName;
			document.getElementById("idCardSex").value = idCardSex;
			document.getElementById("idCardNo").value = idCardNo;
			document.getElementById("idCardAddr").value = idCardAddr;
			document.getElementById("idCardStartTime").value = idCardStartTime;
			document.getElementById("idCardEndTime").value = idCardEndTime;
			
			// �ύ
			doSub();								
		}
		else if (ret == "-1")
		{			
			setException("��ȡ���֤��������ʧ�ܣ��޷�������ҵ��");
		}
		else if (ret == "2")
		{		
			setException("֤���޷�ʶ���޷�������ҵ��");
		}
		else if (ret == "1")
		{		
			if(limitTime<=0)
			{
				setException("δ��ȡ�����֤��Ϣ���޷�������ҵ��");
			}
		} 
	}
	catch (e) 
	{		
		setException("���֤����������ʧ�ܣ��޷�������ҵ��");
	}
}

//����ʱ���������
function startclock() 
{	
	try 
	{
		// ��ȡ���֤��������
		waitingToken = setInterval("getReadCardStatus()", 1000);
		
		// ��ʱ��
		timeToken = setInterval("calLeftTime()", 1000);
	}
	catch (e) 
	{
		setException("����������ʧ�ܣ��޷�������ҵ��");
	}
}

//��ȡ���֤������״̬
function getIdCardStatus()
{
	try
	{
		// ��ȡ���֤������״̬
		var ret = GetIdCardStatus();
		
		// ���֤������״̬�쳣
		if (ret != "0" && ret != 0) 
		{					
			setException("���֤������״̬�쳣���޷�������ҵ��");
		}
	}
	catch (excep) 
	{		
		setException("���֤������״̬�쳣���޷�������ҵ��");
	}
}

//ҳ�����ʱִ��
function bodyLoad() 
{
	try 
	{				
		// �������֤������Ϊ����״̬
		var ret = InitIdCardReader();
		
		if (ret != "0" && ret != 0) 
		{
			setException("��ʼ�����֤�������쳣���޷�������ҵ��");
            return;
		}
		else
		{		
			//׼��ˢ����������
			initCardReader = 1;
			
			//�ȴ��������������ɹ��󣬾���Ҫ�رոý��̡�������ҳ�������˳�����ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
			
			//�ڵ��ó�ʼ�����֤�������󣬻�ȡ���֤��Ϣ֮ǰ����Ҫ���ô˽ӿڼ��һ�¶�����״̬
			getIdCardStatus();
			
			// ��ʼ��ʱ����ѭ�����ýӿ�
			startclock();
		}
	}
	catch (ex) 
	{
		setException("��ʼ�����֤�������쳣���޷�������ҵ��");
        return;
	}
}

//Ӫ����Ƽ���ת����ҳ��ʱ��Ҫ����У�齻��Ӳ���ʹ�ӡӲ��
function checkAgain()
{
	//������Ӫ����ת����У��
	if("1" == "<s:property value='recommendActivityFlag' />")
	{
		// �Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
		var isPrintFlag = window.parent.isPrintFlag;
	
		// �Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
		var isCashEquip = window.parent.isCashEquip;
	
		// �Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
		var isUnionPay = window.parent.isUnionPay;
		
		// У��Ʊ�ݴ�ӡ�� 1-����ӡ��0-��ӡ
		var canNotPrint = isPrintFlag == 1 ? (initListPrinter() == "" ? "0" : "1") : "1";
	 	setValue("canNotPrint",canNotPrint);
	 	
	 	var chkCash = isCashEquip == 1 ? (initCashPayEquip() == "" ? "1" : "0") : "0";
	 	
	 	var chkCardMsg = initUnionCardPayEquip("<%=termInfo.getUnionpaycode()%>", "<%=termInfo.getUnionuserid()%>");
	 	
	 	var chkCard = isUnionPay == 1 ? (chkCardMsg == "" ? "1": "0") : "0";
	 	
	 	var payTypeFlag = chkCash + "" + chkCard;
	 	
	 	setValue("payTypeFlag",payTypeFlag);
	}
}		
</script>
</head>
<body scroll="no">
	<form name="readCardForm" method="post" target="_self">
		<!-- ������Ϣ -->
		<input type="hidden" id="errormessage" name="errormessage" value='' />
		<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
		
		<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
		<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
		<!-- ���� -->
		<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='' />
		<!-- �Ա� -->
		<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='' />
		<!-- ���֤���� -->
		<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='' />
		<!-- ֤����ַ -->
		<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='' />
		<!-- ��ʼʱ�� -->
		<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='' />
		<!-- ����ʱ�� -->
		<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='' />
		<!-- ����Ϣ -->
		<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='' />
		<!-- ��Ƭ��Ϣ -->
		<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='' />
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
					<a href="javascript:void(0)" class="on">2. ��ȡ���֤��Ϣ</a>
   					<a href="javascript:void(0)">3. ����ȷ��</a>
   					<a href="javascript:void(0)">4. ѡ��֧����ʽ</a>
   					<a href="javascript:void(0)">5. �����ɷ�</a>
   					<a href="javascript:void(0)">6. ȡ����ӡСƱ</a>
				</div>

				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank40"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>��ȡ���֤��Ϣ<span class="yellow"></span>
						</p>
						<div class="blank40"></div>
						<br></br>
						<p class="tit_info" align="center">
							<span style="color: yellow;">������Ķ������֤�ŵ���Ӧ��</span>
						</p>
						<br></br><br></br>
						<p class="tit_info" align="center">
							<input type="text" id="tTime" name="tTime" value="<%=readIdCardTime%>" readonly="readonly" />
						</p>
					</div>
				</div>
				<div class=" clear"></div>

			</div>
		</div>

		<%@ include file="/backinc.jsp"%>
	</form>
</body>

<script type="text/javascript">
clearTimeout(timeOut);
bodyLoad();
checkAgain();
</script>
</html>
