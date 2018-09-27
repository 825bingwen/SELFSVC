<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
String yuanMoney = (String) request.getAttribute("recFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>���п��ɷ�ȷ��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
		<script language="javascript">
			// �ύ��־ 0:δ�ύ 1:���ύ
			var submitFlag = 0;
			
			// �˶�ʱ��
			var limitTime = 60;
			
			//����ʣ��ʱ��ľ��
			var timeToken;		
			
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
				else if (key == 13 || key == 89 || key == 221)
				{
					openWindow_wait('pls_wait');
				}		
			}
		
		//������һҳ
		function goback(menuid)
		{
			setException("�ɷѲ�����ȡ������ע��ȡ��������������");
		}
		
		//�����쳣
		function setException(errorMsg) 
		{
			// �����ʱ����
			clearInterval(timeToken);
		
			// ��ʾ������Ϣ
			document.getElementById("errormessage").value = errorMsg;
			
			//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
			document.actForm.action = "${sessionScope.basePath }reissueCard/goCardError.action";
			document.actForm.submit();
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
				return "��ȡ�հ׿�����ʧ�ܣ�";
			}
			
			setValue("blankCard",blankCardSN);
					
			//3��У��հ׿���Դ
			var resXml = checkBlankCard();
			      
			var resArray = resXml.split('~~');
			
			if (resArray[0] == 0 || resArray[0] == "0")
			{
				setValue("iccid",resArray[1]);
				setValue("imsi",resArray[2]);
				setValue("smsp",resArray[4]);
			}
			else
			{	
				setException(resArray[1]);
				return;
			}
			
			//4��д��
			var writeData = writeCard(resXml.substring(3).replace("+", "^^"),getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
			
			// ����д��ʧ�� ��ת�쳣ҳ��
			if(writeData != 0)
			{
				setException(writeData.split("~")[1]);
				
				return;
			}
			
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
			
			//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
			var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("eki")+ "~~" +getValue("smsp").replace("+", "^^");
			simInfo = simInfo+"~~"+getValue("pin1")+"~~"+getValue("pin2")+"~~"+getValue("puk1")+"~~"+getValue("puk2");
	
			//д��:��Σ�д���������ݣ��հ׿����ţ���ˮ�ţ�����д�����У�飬������д��ʱ�����һ�£�   
			var writeData = writeCard(simInfo,getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
	
			//��һ��д��ʱ�粻��д���ؼ��쳣��ֱ����ת�����ٶ���д��
			if(0 != writeData && writeData.indexOf("11~") == -1)
			{
				setException(writeData.split("~")[1]);
				return;
			}
			
			// д��ʧ�� ���ж���д��
			if(writeData.indexOf("11~") != -1)
			{
				// �ٴ�д��
				writeData = writeAgain(basePath);
			}
			
			//д���ɹ�	
			if (writeData == 0)
			{
				//д���ɹ�
				setValue("writeCardStatus","0");
			
				//�ύ�ɷ�����
				document.actForm.action = "${sessionScope.basePath}reissueCard/reissueCommit.action";
				document.actForm.submit();
			}
		}
		
		/**
		 * �ۿ�������־
		 */
		function updateUnionCardLog(printcontexts)
		{
			//������־
			var url1 = "${sessionScope.basePath }reissueCard/updateUnionCardLog.action";
	
			var params1 = "cardChargeLogVO.chargeLogOID=" + getValue("chargeLogOID") + "&cardChargeLogVO.unionpayuser=" + printcontexts[0];
			params1 = params1 + "&cardChargeLogVO.unionpaycode=" + printcontexts[1] + "&cardChargeLogVO.busiType=" + encodeURI(encodeURI(printcontexts[2]));
			params1 = params1 + "&cardChargeLogVO.batchnum=" + printcontexts[4] + "&cardChargeLogVO.authorizationcode=" + printcontexts[5];
			params1 = params1 + "&cardChargeLogVO.businessreferencenum=" + printcontexts[6] + "&cardChargeLogVO.unionpaytime=" + printcontexts[7];
			params1 = params1 + "&cardChargeLogVO.vouchernum=" + printcontexts[8] + "&cardChargeLogVO.unionpayfee=" + printcontexts[9];
			params1 = params1 + "&cardChargeLogVO.posResCode=" + getValue('posResCode');
			params1 = params1 + "&cardBusiLogPO.oid=" + getValue("oid");
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
				var resXml1 = this.req.responseText;
				
				//������־�ɹ�
				if (resXml1 == "0" || resXml1 == 0)
				{
					//д��
					writeAndSub();	
				}
				//������־ʧ��
				else
				{	
					setException("�ɷ�ʧ�ܣ���ȡ��������������СƱ��ƾСƱ����Ӫҵ�������˿�������ллʹ��!");
					return;
				}								
			}, null, "POST", params1, null);
		}
		
		/**
		 * ��POS�ɷ�����
		 * 1�������ɷ��ն�������ҵ��ǰ�÷������п��ۿ�����
		 * 2������ҵ��ǰ��������POSP�������п��ۿ�ס�
		 * 3������POSP������ҵ��ǰ�÷������п��ۿ�����
		 * 4������ҵ��ǰ�ý����п��ۿ���ת���������ɷ��նˡ�
		 * 5�������ɷ��ն��������ɷ��ն��ۺϹ���ƽ̨�����ƶ���������
		 * 6�������ɷ��ն��ۺϹ���ƽ̨��BOSS�����ƶ����������նˡ�
		 * 7��BOSS�������ɷ��ն��ۺϹ���ƽ̨���ؼ��˽����
		 * 8�������ɷ��ն��ۺϹ���ƽ̨�����˽�����ظ������ն�
		 */
		function unionCardPay()
		{
			var posResCode = '';
			
			//�ύ�ۿ�����
			try
			{
				var ret ;
				
				// �ر��������
				ret = CloseCom()
				if (ret != "0" || ret != 0)
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
					return;
				}
				
				// �����ۿ�
				// posnum ���ٺ�;batchnum ���κţ� bankcardID���п��ţ�money �ɷѽ��(��)
				var payReturnStr = Pay(getValue("posNum"),getValue("batchNumBeforeKoukuan"),getValue("cardnumber"),"<%=fenMoney %>");
	
				// �򿪼��̴��ڡ���������ģʽ
				ret = OpenCom();
				if (ret != "0" || ret != 0)
				{
					window.parent.showFrmErr("����:�򿪼��̴���ʧ�ܣ�");
				}
				ret = SetWorkMode(0);
				if (ret != "0" || ret != 0)
				{
					window.parent.showFrmErr("����:���ü���ģʽʧ�ܣ�");
				}
				
				posResCode = payReturnStr.substring(0,2);
				
				setValue("posResCode",posResCode);
				
				if (payReturnStr.substring(0,2) != "00")
				{
					setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
					return;
				}
				
				// �ۿ�����ȡֵ
				var resultstr = payReturnStr.substring(0,34);
				var printcontext = payReturnStr.substring(34,payReturnStr.length);
				
				setValue("printcontext",printcontext);
				
				//������
				/**
				var resultstr = "006222021602004724078A000000001234";
				//var printcontext = "302370150210713~123456789012345~���ѽ���~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
				
				// �������� ��Ȩ����ƾ֤�� ���ؿ�ֵ
				var printcontext = "898420148145268~00167923~���ѽ���~6225887840088682~000725~~171633071883~20110817171633~~1";
				**/	
				
				// �ۿ�ɹ� ����34 �ɹ�00
				if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
				{
				    
				    // �򿪼��̴��ڡ���������ģʽ
					OpenCom();
					SetWorkMode(0);
					
					var printcontexts = printcontext.split('~');							
					
					//���ÿۿ���
					setValue("tMoney",printcontexts[9]);
					
					//����״̬ Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��
					setValue("payStatus","0");
					
					//���¿ۿ���־
					updateUnionCardLog(printcontexts);
				}
				//�ۿ�ʧ��
				else
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
					return;
				}
					
			}
			catch (e)
			{
				
				if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
				{
					setException("�������ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
				}
				else
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
				}
			}
		}
		
		/**
		 * �����ۿ�ǰ��¼��־
		 */
		function addUnionCardLog()
		{
			try
			{
				//������־���ͣ�add
				setValue("errorType","add");
				
				//�����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				//��ȡ���ٺš����κ�ʧ��
				if (dataArray[0] != "0")
				{
					setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������г�ֵ����ȡ��������������");
					
					return;
				}
				
				//��ȡ���ٺ�
				setValue("posNum",dataArray[1]);
				
				//��ȡ���κųɹ�
				setValue("batchNumBeforeKoukuan",dataArray[2]);
				
				//��ˮ��
				setValue("terminalSeq",getValue("batchNumBeforeKoukuan") + getValue("posNum"));
				
				//�ۿ�֮ǰ��¼��־
				var url = "${sessionScope.basePath }reissueCard/addUnionCardLog.action";
				
				var params = "&recFee=" + <s:property value='recFee' />;
				params = params + "&cardChargeLogVO.cardnumber=" + getValue("cardnumber");
				//��ˮ��
				params = params	+ "&cardChargeLogVO.terminalSeq=" + getValue("terminalSeq");
				//���ٺ�
				params = params	+ "&cardChargeLogVO.posNum=" + getValue("posNum");
				//���κ�
				params = params + "&cardChargeLogVO.batchNumBeforeKoukuan=" + getValue("batchNumBeforeKoukuan");
				//������־id
				params = params + "&cardBusiLogPO.oid=" + getValue("oid");
			
				var loader = new net.ContentLoader(url, netload = function () {
						var resXml = this.req.responseText;
						var dataArray = resXml.split("~~");
						//��¼��־�ɹ�
						if (dataArray[0] == "0") 
						{
							//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
							setValue("errorType","update");
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							
							//���ý�����־id�����ڸ��½�����־
							setValue("chargeLogOID",oid);
							
							//�����ۿ�
							unionCardPay();
						}
						//��¼��־ʧ��
						else 
						{						
							setException("��־��¼ʧ�ܣ����ܽ����������ɷѲ�������ȡ��������������");
						}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
				{
					setException("�������ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
				}
				else
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
				}
			}				
		}
		
		/**
		 * ���������ύ����
		 * 1�����������ۿ���־��
		 * 2�������ۿ
		 * 3�����������ۿ���־��
		 * 4��д��
		 * 5������һ��д��ʧ�ܣ�����ж���д��
		 * 6���ύ
		 */
		function doSub() 
		{		
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				// Ͷ�Һ󣬲��ܷ���
            	document.getElementById("backBtn").disabled = true;
				
				//�ۿ�Ǯ��¼�ۿ���־
				addUnionCardLog();
			}
		}
		
		//����ʣ��ʱ��
		function waitForSubmit() 
		{
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
		
		function doLoad()
		{
			//���á���ҳ�������˳�����ť
	        document.getElementById("homeBtn").disabled = true;
	        document.getElementById("quitBtn").disabled = true;
		}
		</script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />		
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYUNIONCARD%>">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<%-- ���úϼ� --%>
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
			
			<%--------------������־��Ϣ---------------%>
			<%--�հ׿�����--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
   	 		<%--������־id��--%>
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
			<%-- д��״̬  Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��--%>
			<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
			<%-- ����״̬  Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��--%>
			<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
			
			<%--�Ƿ���Ҫ���տ���1 ��Ҫ --%>
			<input type="hidden" id="callBackCard" name="callBackCard" value="" />
			
			<%---������ӡ��Ϣ--%>
			<input type="hidden" id="printcontext" name="printcontext" value="">
			
			<%----------------������־��Ϣ---------------%>
			<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" value="">
			<input type="hidden" id="posResCode" name="cardChargeLogVO.posResCode" value=''>
			<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value='<s:property value="cardChargeLogVO.cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
			<%--���ٺ�--%>
			<input type="hidden" id="posNum" name="cardChargeLogVO.posNum" value=''>
			<%--���κ�--%>
			<input type="hidden" id="batchNumBeforeKoukuan" name="cardChargeLogVO.batchNumBeforeKoukuan" value=''>
			
			<%--------------simInfoPO��Ϣ--------------%>
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
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺${sessionScope.CustomerSimpInfo.servNumber}</span></p>
    						<p class="fs22 fwb pl40 lh30">�������ã�<span class="yellow fs22"><s:property value="recFee" /></span> Ԫ</p>
							<p class="tit_info"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />��</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--������ ���ڴ��� ���Ժ�-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
                  	<p class="tips_txt">���ڴ������Ժ�......</p>
                 	<div class="line"></div>
                  	<div class="popup_banner"></div>                
                </div>

				<script type="text/javascript">
					openWindow_wait = function(id){
					
						//�����ʱ����
						clearInterval(timeToken);
						
						wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
					    setTimeout("doSub()", 500);
					}				
				</script>
				<!--����������-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
	clearTimeout(timeOut);
	startclock();
	
	// ��ʶ�ؼ�ʹ��
	closeStatus = 1;
	</script>
</html>
