<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
	
	// �ն˻���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
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
		<link href="${sessionScope.basePath }css/newAdd.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
		<style>
			.scrollBody{width:712px; height:536px; background:url(${sessionScope.basePath }images/bg_1_right.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bg_1_right.png");_background:none; margin-top:0px; margin-left:0px;}
			.scrollBody .packageList{width:600px; height:400px; font-size:18px; margin-left:20px; _margin-left:10px; margin-top:20px; line-height:35px;}
			.scrollBody .scrollBar{width:70px; height:285px; margin-top:80px;}
			.scrollBody .scrollBar .btnTop{width:70px; top:-52px; height:52px; background:url(${sessionScope.basePath }images/arrow_up.png) no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/arrow_up.png");_background:none}
			.scrollBody .scrollBar .btnDown{width:70px; bottom:-52px; height:52px; background:url(${sessionScope.basePath }images/arrow_down.png) no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/arrow_down.png");_background:none}
			.scrollBody .scrollBar .iconScroll{width:66px; height:36px; line-height:36px; background:url(${sessionScope.basePath }images/bg_66_30.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bg_66_30.png");_background:none}
		</style>
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<%-- ������Ϣ --%>
			<input type="hidden" id="errormessage" name="errormessage" value="" />
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="0" />
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="11"/>
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)" class="on">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
	   					<a href="javascript:void(0)">4. ����ѡ��</a>
	   					<a href="javascript:void(0)">5. ���÷�������</a>
	   					<a href="javascript:void(0)">6. ����ȷ��</a>
	   					<a href="javascript:void(0)">7. �����ɷ�</a>
	   					<a href="javascript:void(0)">8. ȡ����ӡСƱ</a>
					</div>
					
					<!-- ������ ��ʼ -->
					<div class="scrollBody">
						<div class="packageList" id="inn">
							<div class="packageList_body" id="rightDiv">
								<s:iterator value="agreementList" id="agreement">
									<s:property value="#agreement.description" escapeHtml="false"/>
								</s:iterator>
							</div>
						</div>
						<div class="scrollBar">
							<input type="button" class="btnTop" onclick="myScroll.moveUp(30)" />
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
							<div class="iconScroll" id="gunDom">0%</div>
						</div>
						<div class=" tr" style="position: absolute; top: 470px; left: 800px;"> 
	  						<a class="tongyi" href="javascript:void(0);" onMouseDown="this.className='tongyi_on'" onMouseUp="this.className='tongyi';" onclick="doSub();return false;"></a> 
	   					</div>
					</div>
					<script type="text/javascript">
						myScroll = new virtualScroll("inn","gunDom");
						myScroll.parentTop = myScroll.parentTop - 121;
					</script>
					<!-- ������ ���� -->
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
		<div class="popup_confirm" id="printCheck_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				��ʾ��
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p id="printPromptId"></p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="#"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';printConfirm();">��</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';chooseNo();">��</a>
				</span>
			</div>
		</div>
	</body>
</html>
<script language="javascript">
			
	// ��ֹ�ظ��ύ
	var submitFlag = 0;
	
	// �Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
	var isPrintFlag = window.parent.isPrintFlag;

	// �Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
	var isCashEquip = window.parent.isCashEquip;

	// �Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
	var isUnionPay = window.parent.isUnionPay;

	// �Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
	var is2GIDFlag = window.parent.is2GIDFlag;

	// �Ƿ�֧��SIM��������
	var SIMreaderFlag = window.parent.SIMreaderFlag;

	// 0-��ӡ��������1-��ӡ���쳣��
	var printIsOk = 0;
	
	// У���ֽ�ʶ����
	var chkCashMsg = "";
	
	// У������������
    var chkCardMsg = "";
	
	// ѡ��No��ʾ��Ϣ
	var chooseNoAlertMsg = "";
	
	// 82��220 ����		
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
	
	// �����Ƿ��������ж�
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
		//ȷ��
		else if (key == 13 || key == 89 || key == 221)
		{
			doSub();
		}			
	}
	
	// ������ҳ
	function goback(menuid)
	{
		goHomePage();
	}
	
	// �ύת�������֤ҳ��
	function doSub() 
	{
		if (submitFlag == 0) 
		{
			// Ʊ�ݴ�ӡ���쳣���ܴ�ӡСƱ�����û�����ȷ��
			if (printIsOk == 1)
			{
				openRealNameConfirm("Ʊ�ݴ�ӡ���쳣���ܴ�ӡСƱ���Ƿ������", "Ʊ�ݴ�ӡ���쳣���û���ȡ������");
			}
			// �ֽ�ʶ���������⣬��������������
			else if (chkCashMsg != "" && chkCardMsg == "")
		 	{
		 		chkCashMsg = "";
		 		setValue("payTypeFlag","01");
		 		openRealNameConfirm("�ֽ�ʶ�������ϣ����ο���ֻ�����������������Ƿ������", "�ֽ�ʶ�����쳣���û���ȡ������");
		 	}
		 	// �ֽ�ʶ�������ã�����������������
		 	else if (chkCashMsg == "" && chkCardMsg != "")
		 	{
		 		chkCardMsg = "";
		 		setValue("payTypeFlag","10");
		 		openRealNameConfirm("�������������ϣ����ο���ֻ�����ֽ𿪻����Ƿ������", "�����������쳣���û���ȡ������");
		 	}
			else
			{
				submitFlag = 1;	//�ύ���
				openRecWaitLoading();
				document.dutyInfoForm.target = "_self";
				document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/readCert.action";
				document.dutyInfoForm.submit();
			}
		}
	}
	
	// �����쳣
	function setException(errorMsg) 
	{			
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			openRecWaitLoading();
			
			document.getElementById("errormessage").value = errorMsg;
	
			//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
			document.dutyInfoForm.target = "_self";
			document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/installError.action";
			document.dutyInfoForm.submit();
		}
	}	
	
	// ���ȼ���
	function doLoad()
	{
		// У���豸������
		chkEquipment();
		
		var msg = '<s:property value="blankCardTypeAlertMsg"/>';
		if (null != msg && msg != "")
		{
			// �ն˻�֧�ֿհ׿���д������ʾ��Ϣ
			alertSuccessMsg(msg);
		}
	}	
	
	// ����ȷ�Ͽ�
	function openRealNameConfirm(content, noMsg)
	{
		chooseNoAlertMsg = noMsg;
		document.getElementById('printPromptId').innerHTML = content;
		wiWindow = new OpenWindow("printCheck_confirm",708,392);
	}
	
	// �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ
	function printConfirm()
	{
		// �ֽ�ʶ���������⣬��������������
	 	if (chkCashMsg != "" && chkCardMsg == "")
	 	{
	 		chkCashMsg = "";
	 		setValue("payTypeFlag","01");
	 		wiWindow.close();
	 		openRealNameConfirm("�ֽ�ʶ�������ϣ����ο���ֻ�����������������Ƿ������", "�ֽ�ʶ�����쳣���û���ȡ������");
	 		return;
	 	}
	 	// �ֽ�ʶ�������ã�����������������
	 	else if (chkCashMsg == "" && chkCardMsg != "")
	 	{
	 		chkCardMsg = "";
	 		setValue("payTypeFlag","10");
	 		wiWindow.close();
	 		openRealNameConfirm("�������������ϣ����ο���ֻ�����ֽ𿪻����Ƿ������", "�����������쳣���û���ȡ������");
	 	}
	 	else
	 	{
	 		// Ʊ�ݴ�ӡ���쳣���ܴ�ӡСƱ�����û�����ȷ��
			if (printIsOk == 1)
			{
				document.getElementById('canNotPrint').value = "1";
			}
		 	
		 	if (submitFlag == 0) 
			{
				// �ύ���
			 	submitFlag = 1;	
				
				// �����ȴ�div
				openRecWaitLoading();
				
				document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/readCert.action";
				document.dutyInfoForm.submit();
			}
	 	}
	}	
	
	// У���豸������
	function chkEquipment()
	{
	    var message = "";
	 	
	    // У�������д����
	    if (SIMreaderFlag == 1)
	    {
	    	message = initBlankCardReader();
	    }
	    else
	    {
	    	message = "���ն˻���֧�ֿհ׿����������޷�������";
	    }
		
	    // ��ʾ��Ϣ
		if (message != "")
		{
			setException(message);
		}
		
		// ������д����������鿨���Ƿ��п����ӿ�ReadCardStatus()���ն˻������ṩ
		message = checkReadCardStatus();
		if (message != "")
		{
			setException(message);
		}
		
		// ��ȡ�հ׿����к�
		var blankCardSN = readBlankCardSN();
		
		if (blankCardSN.indexOf("1~") != -1)
		{
			setException(blankCardSN.split('~')[1]);
			return;
		}
		
		if(blankCardSN.length != 20)
		{
			setException("�Բ��𣬿����Ͳ���ȷ������ϵӪҵ������Ա!");
			return;
		}
		
	 	// У�����֤������
	 	if (is2GIDFlag == 1)
	    {
	 		message = initOpenIdCardReader();
	    }
	    else
	    {
	    	message = "���ն˻���֧�����֤���������޷�������";
	    }
	 	
	 	// ��ʾ��Ϣ
	    if (message != "")
		{
			setException(message);
		}
	    
	 	// У��Ʊ�ݴ�ӡ��
	 	if (isPrintFlag == 1)
	 	{
	 		// У��Ʊ�ݴ�ӡ��
		    message = initListPrinter();
		    if (message != "")
			{
		    	printIsOk = 1;
			}
	 	}
	 	else
	 	{
	 		printIsOk = 1;
	 	}
	 	
	 	// У���ֽ�ʶ����
	 	if (isCashEquip == 1)
	 	{
	 		chkCashMsg = initCashPayEquip();
	 	}
	 	else
	 	{
	 		chkCashMsg = "���ն˻���֧���ֽ�ʶ����";
	 	}

	    // У������������
	    if (isUnionPay == 1)
	    {
	    	chkCardMsg = initUnionCardPayEquip();
	    }
	    else
	    {
	    	chkCardMsg = "���ն˻���֧������������";
	    }
	    
	    // �ж����[�ֽ�ʶ����]��[����������]��������ʱ�����򵽴���ҳ��
	    if (chkCashMsg != "" && chkCardMsg != "")
	    {
	    	setException("�ֽ�ʶ���������������������ֹ��ϣ�");
	    }
	}
	
	// ��ʾ��Ϣѡ��"��"
	function chooseNo()
	{
		wiWindow.close();
		setException(chooseNoAlertMsg);
	}
	
</script>