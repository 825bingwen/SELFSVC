<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
	// �������ɷѶ����ȴ�ʱ��(��)
	String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

	// ȡ��Ӧ�շ��ò�ת��Ϊ��
	String yuanMoney = (String) request.getAttribute("recFee");
	String fenMoney = CommonUtil.yuanToFen(yuanMoney);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
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
			
			// ���ز���
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
					
					try
					{
						// �رն����豸
						CloseComByCard();
						
						// ��������̲���������ģʽ
						OpenCom();
						SetWorkMode(0)
					}
					catch (e)
					{}
					
					//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
					document.readCardForm.action = "${sessionScope.basePath }reissueCard/goChargeError.action";
					document.readCardForm.submit();
				}			
			}
			
			//----------------------------------�ۿ�ɷ�-------------------------------------------------
			//�������ۿ�
			function pay()
			{
				//�����ʱ����
				clearInterval(waitingToken);
				
				// �رն����豸
				try
				{
				
					//ƽ̨ϵͳ��⵽�û��Ѳ���������������ô˽ӿڹرն����豸���ͷŴ��ڡ�
					var ret = CloseComByCard();
					
					if (ret != 0 && ret != "0")
					{
						setException("�رն����豸ʧ�ܣ��޷�ʹ�����������нɷѣ�����ϵӪҵ��Աȡ��������������");
								
						return;
					}
				}
				catch(e)
				{
					setException("�رն����豸ʧ�ܣ��޷�ʹ�����������нɷѣ�����ϵӪҵ��Աȡ��������������");
								
					return;
				}
				
				// �����������ۿ�����
				var resultstr = "";
				try
				{
					// ����������
					// ���״���(2)+���׽��(12)+POS��ˮ��(6)+��������(10)+����Ա��(10)+���м����ο���(15)+��Ȩ�ŷ��ڸ�������(6)
					// + ԭ��������(8)+��Ƭ����(1)+�Զ�����Ϣ(76)+�ڶ��ŵ�(37)+�����ŵ�(104)+ԭ������(2)+ԭ�ն˱��(15)+ԭ��Ȩ��(6)
					var strin = '01';// ���״���(2)
					strin = strin + formatStr('<%=fenMoney %>','left','0',12);// ���׽��(12)
					strin = strin + formatStr('','right',' ',55);// POS��ˮ��(6)+��������(10)+����Ա��(10)+���м����ο���(15)+��Ȩ�ŷ��ڸ�������(6)+ ԭ��������(8)
					strin = strin + 'H';// ��Ƭ����(1)
					strin = strin + formatStr('','right',' ',240);// �Զ�����Ϣ(76)+�ڶ��ŵ�(37)+�����ŵ�(104)+ԭ������(2)+ԭ�ն˱��(15)+ԭ��Ȩ��(6)
					// add begin cKF76106 2013/04/01 R003C13L03n01 OR_SD_201303_1171
					//add begin sWX219697 2014-6-17 10:27:25 OR_huawei_201404_1118
					//������Ϣ��101�����˽��ѣ�102�������̽��ѣ�105����������
					strin = strin + formatStr("105"+document.getElementById("chargeLogOID").value,'right',' ',100);// �������Ѹ�����Ϣ105
					//add end sWX219697 2014-6-17 10:27:25 OR_huawei_201404_1118
					// add end cKF76106 2013/04/01 R003C13L03n01 OR_SD_201303_1171
					
					// ������	
					if (true) // true:���� false:����
					{	
						resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
					}
					// ������	
					else
					{
						// ������(6)+	�����뺬��(40)+POS��ˮ��(6)+��Ȩ��(6)+���κ�(6)+����(19)+��Ч��(4)+���к�(2)+���м����ο���(12)+�ն˺�(15)+�̻���(15)
						// ���׽��(12)+���ܺ������򶨵���(16)+���ڸ�����Ϣ(74)+�����д���(8)+����������������(8)+������������ʱ��(6)
//						resultstr = '000001';// ������(6)
//						resultstr = resultstr + formatStr('error', 'right', ' ', 40);// �����뺬��(40)
						resultstr = '000000';// ������(6)
						resultstr = resultstr + formatStr('','right',' ',40);// �����뺬��(40)					
						resultstr = resultstr + "pos001";// POS��ˮ��(6)
						resultstr = resultstr + "sqm001";// ��Ȩ��(6)
						resultstr = resultstr + "pch001";// ���κ�(6)
						resultstr = resultstr + "kahao12345678901234";// ����(19)
						resultstr = resultstr + "0313";// ��Ч��(4)
						resultstr = resultstr + "01";// ���к�(2)
						resultstr = resultstr + "chankaohao99";// ���м����ο���(12)
						resultstr = resultstr + "zdh123456789012";// �ն˺�(15)
						resultstr = resultstr + "shh123456789012";// �̻���(15)
						resultstr = resultstr + "000000004000";// ���׽��(12)
						resultstr = resultstr + "****************";//���ܺ������򶨵���(16)
						resultstr = resultstr + formatStr('','right',' ',74);// ���ڸ�����Ϣ(74)
						resultstr = resultstr + formatStr('','right',' ',8);// �����д���(8)
						resultstr = resultstr + '20120101';// ����������������(8)
						resultstr = resultstr + '101010';// ������������ʱ��(6)
					}
				}
				catch (e)
				{}
				
				try
				{
	   				// ��������̣���������ģʽ
					OpenCom();
					SetWorkMode(0);
				}
				catch(e)
				{}
				
				try
				{
					// ���ó������⣬û��ȡ������ֵ
					if (resultstr == "")
					{
						setException("�����ۿ�ʧ�ܣ��޷��ɷѣ�����ϵӪҵ��Աȡ��������������");
								
						return;
					}
					// �ۿ�ɹ� ����255 �ɹ�000000
					else if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
					{
						// ��ˮ��_���ײο���
						setValue("terminalSeq", trim(resultstr.substring(resultstr.length-166, resultstr.length-154)));
						setValue("tMoney", trim(resultstr.substring(resultstr.length-124, resultstr.length-112)));
						setValue("printcontext", trim(resultstr.substring(resultstr.length-209)));
							
						// ������־��¼��Ϣ
						setValue("unionpayuser", trim(resultstr.substring(resultstr.length-139,resultstr.length-124)));// �̻�����
						setValue("unionpaycode", trim(resultstr.substring(resultstr.length-154,resultstr.length-139)));// POS�����
						setValue("busitype", "����");// ��������
						setValue("cardnumber", trim(resultstr.substring(resultstr.length-191,resultstr.length-172)));// ����
						setValue("posnum", trim(resultstr.substring(resultstr.length-209,resultstr.length-203)));// POS��ˮ��
						setValue("batchnum", trim(resultstr.substring(resultstr.length-197,resultstr.length-191)));// ���κ�
						setValue("authorizationcode", trim(resultstr.substring(resultstr.length-203,resultstr.length-197)));// ��Ȩ��
						setValue("businessreferencenum", trim(resultstr.substring(resultstr.length-166,resultstr.length-154)));// ���ײο���
						setValue("unionpaytime", trim(resultstr.substring(resultstr.length-14,resultstr.length)));// �ۿ�ʱ��
						setValue("vouchernum", "");// ƾ֤��
						setValue("unionpayfee", trim(resultstr.substring(resultstr.length-124,resultstr.length-112)));// �ۿ���
						
						//����״̬ Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��
						setValue("payStatus","0");
						
						//д��
						writeAndSub();
					}
					//�ۿ�ʧ��
					else
					{
						document.getElementById("unionRetCode").value = resultstr.substring(0,6);
						
						setException(trim(resultstr.substring(6)) + "����ȡ��������������");
						
						return;
					}
				}
				catch (e)
				{
					if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
					{
						setException("�����ۿ�ɹ�������д��ʧ�ܡ���ȡ��������������");
					}
					else
					{
						setException("�����ۿ�ʧ�ܡ���ȡ��������������");
					}
				}				
			}
			
			//-----------------------------------------ѭ������-------------------------------------------------
			// ��ö�����־
			function getReadCardStatus() 
			{
				// ����ʱ�����
				if (limitTime <= 0 && submitFlag == 0)
				{           	
					setException("������ʱ���޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
	       			return;
				}
				
				try 
				{
					// 0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
					var ret = getCardPosition();// ��ȡ��λ�ã����ж��û��Ƿ��Ѳ���������

					// ����ʧ��
					if (ret == "-1")
					{
						setException("����ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
						return;
					}
					// ���ڶ�������
					else if (ret == "0" || ret == 0)
					{	
			   	 		pay();
					}
					// ��δ��ȡ������Ϣ
					else
					{
						// ����ʱ��һ��limitTime��
						limitTime = limitTime - 1;
				
						// �趨������ʾ
						document.getElementById("tTime").value = limitTime;
					}
				}
				catch (e) 
				{
					setException("����ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
					return;
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
					setException("����������ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
				}
			}
			
			//----------------------------------ҳ���ʼ��-------------------------------------------------
			//ҳ�����ʱִ��
			function bodyLoad() 
			{
				// �ر��������
				try
				{
					// ���������ۿ�ؼ�ǰ�ر��豸���ô��ڲ�������������
					var ret = CloseCom();
					if (ret != 0)
					{
						setException("�ر��������ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
					
						return;
					}
				}
				catch(e)
				{
					setException("�ر��������ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");

					return;
				}
				
				// �򿪶����豸
				try 
				{
					var ret = OpenComByCard();
					if (ret != "0" && ret != 0) 
					{
						setException("�򿪶����豸ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
	                    return;
					}
					else
					{		
						//��ʼ���ɹ�������Ҫ�رն��������������а�ť������ҳ���ϲ����²���
		   				closeStatus = 1;
						
	                	document.getElementById("homeBtn").disabled = true;
			            document.getElementById("backBtn").disabled = true;
	                	document.getElementById("quitBtn").disabled = true;
							
	                	// ������ʱ����
						startclock();
					}
				}
				catch (ex) 
				{
					setException("�򿪶����豸ʧ�ܣ��޷�ʹ�����������нɷѣ���ѡ��������ʽ��");
	                return;
				}
			}	
			
			// ��Ӣ���ַ�������
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
			
			// ȥ���ַ����ո�
			function trim(str) 
			{
				while (str.charAt(str.length - 1) == " ") 
				{
					str = str.substring(0, str.length - 1);
				}
				
				while (str.charAt(0) == " ") 
				{
					str = str.substring(1, str.length);
				}
				
				return str;
			}
			
			/**
			 * У��հ׿���Դ
			 *
			 * 1�������Ƿ�ΪԤ�ƿտ���
			 * 2��У��հ׿���Դ�Ƿ����
			 * 3��Ԥռ�հ׿���Դ
			 *
			 */
			function checkBlankCard()
			{
				var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")
					+"&curMenuId="+getValue("curMenuId");  
					 
				var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
				var resXml;
				
				//ͬ������
				var loader = new net.ContentLoaderSynchro(
					url, netload = function () 
					{	
						resXml = this.req.responseText;
					}, 
					null, "POST", postStr, null);
					
				return resXml;
			}
			
			/**
	 		 * ����д������
	         * ��Σ���Ŀ·��
	         * ���̣�1�������Ƶ�����λ��2�����հ׿���
	         *      3��У��հ׿���4��д��
	         */
			function writeAgain(basePath)
			{
				//1�������Ƶ�����λ
				var message = checkReadCardStatus();
				if (message != "")
				{
					setException(message);
					return;
				}
				
				//2����ȡ�հ׿����к�
				var blankCardSN = readBlankCardSN();
				
				if (20 != blankCardSN.length)
				{
					setException("�հ׿����ű�����20λԤ�ÿտ���");
					return;
				}
				
				setValue("blankCard",blankCardSN);
						
				//3��У��հ׿���Դ
				var resXml = checkBlankCard();
				      
				var resArray = resXml.split('~~');
				
				//�հ׿���Դ��ѡ    
				if (resArray[0] == 0 || resArray[0] == "0")
				{
					setValue("iccid",resArray[1]);
					setValue("imsi",resArray[2]);
					setValue("issueData",resArray[3]);
					setValue("formNum",resArray[4]);
				}
				else
				{	
					setException(resArray[1]);
					return;
				}
				
				//4��д��
				var writeData = writeCard(resXml.substring(3),getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
				
				return writeData;
			}
			
			/**
			 * д�����ύ
			 * ��һ��д��ʧ�ܣ�����ж���д����
			 */
			function writeAndSub() 
			{
				//��Ŀ·��
				var basePath = "${sessionScope.basePath}";
			
				if (document.getElementById("tMoney").value == "" 
						|| parseInt(document.getElementById("tMoney").value) <= 0)
				{
					setException("�����ۿ����쳣");
					return;
				}
	
				//����д��״̬ Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��
				setValue("writeCardStatus","1");
				
				//iccid~~imsi~~issueData~~formNum
				var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("issueData")+"~~"+getValue("formNum");
		
				//д��:��Σ�д���������ݣ��հ׿����ţ���ˮ�ţ�����д�����У�飬������д��ʱ�����һ�£�   
				var writeData = writeCard(simInfo,getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
		
				// д��ʧ�� ���ж���д��
				if(writeData.indexOf("11~") != -1)
				{
					// �ٴ�д��
					writeData = writeAgain(basePath);
				}
				
				// д��ʧ�� ��ת�쳣ҳ��
				if(writeData.indexOf("1~") != -1)
				{
					setException(writeData.split("~")[1]);
					return;
				}
				
				//д���ɹ�
				setValue("writeCardStatus","0");
			
				//�ύ�ɷ�����
				document.readCardForm.action = "${sessionScope.basePath}reissueCard/reissueCommit.action";
				document.readCardForm.submit();
			}
		</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint' />" />
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYUNIONCARD %>">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<!-- ���úϼ� -->
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
			
			<!--------------������־��Ϣ -------------->
			<%-- �հ׿�����--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard"
				value="<s:property value='cardBusiLogPO.blankCard' />" />
   	 		<%--������־id�����ڸ���������־--%>
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
   			<%-- д��״̬  Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��--%>
			<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
			<%-- ����״̬  Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��--%>
			<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
    
    		<!--------------������־��Ϣ -------------->
    		<%-- �ƶ����ѽ�����־Ψһ��ʶ --%>
    		<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" 
    			value="<s:property value = 'cardChargeLogVO.chargeLogOID' />">
    		<%-- �̻���ţ��ڿ�����ʶ�� --%>
			<input type="hidden" id="unionpayuser" name="cardChargeLogVO.unionpayuser" value=''>
			<%-- �ն˱�� --%>
			<input type="hidden" id="unionpaycode" name="cardChargeLogVO.unionpaycode" value=''>
			<%-- �������� --%>
			<input type="hidden" id="busitype" name="cardChargeLogVO.busiType" value=''>
			<%-- ���п��� --%>
    		<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value=''>
    		<%--���ٺ�--%>
    		<input type="hidden" id="posnum" name="cardChargeLogVO.posNum" value=''>
    		<%-- �ն����κ� --%>
    		<input type="hidden" id="batchnum" name="cardChargeLogVO.batchnum" value=''>
    		<%-- ��Ȩ�� --%>
    		<input type="hidden" id="authorizationcode" name="cardChargeLogVO.authorizationcode" value=''>
    		<%-- ���ײο��� --%>
    		<input type="hidden" id="businessreferencenum" name="cardChargeLogVO.businessreferencenum" value=''>
    		<%-- ����ʵ�ʿۿ�ʱ�� --%>
    		<input type="hidden" id="unionpaytime" name="cardChargeLogVO.unionpaytime" value=''>
    		<%-- ƾ֤�� --%>
    		<input type="hidden" id="vouchernum" name="cardChargeLogVO.vouchernum" value=''>
    		<%-- ����ʵ�ʿۿ����λ���֣� --%>
    		<input type="hidden" id="unionpayfee" name="cardChargeLogVO.unionpayfee" value=''>
    		<%-- ������ˮ�ţ����ն���ˮ�� --%>
    		<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
    		<%-- ���������ر���:�ɷ�����+�к� --%>
    		<input type="hidden" id="unionRetCode" name="cardChargeLogVO.posResCode" value=''>
    		
    		<!--------------simInfoPO��Ϣ -------------->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
			<input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
			<input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
			
			<%--�Ƿ���Ҫ���տ���1 ��Ҫ --%>
			<input type="hidden" id="callBackCard" name="callBackCard" value="" />
			
			<%---������ӡ��Ϣ--%>
			<input type="hidden" id="printcontext" name="printcontext" value="">
			
			<%--�ͻ���Ϣ --%>
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��������</h2>
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
        					<p class="tit_info"><span class="bg"></span>�����������������<span class="yellow">ҵ�����������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow"><%=limitTime %></span>�룬��ǰʣ��<input type="text"  id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />��</p>
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
