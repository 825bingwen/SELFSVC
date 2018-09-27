<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
		<script type="text/javascript">
			var submitFlag = 0;
			
			var writeFlag = 0;
			
			//��һҳ
			function goback(curmenu) 
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = curmenu;
					document.actform.action="${sessionScope.basePath }reissueCard/inputTelnum.action";
					document.actform.submit();				
				}		
			}
			
			// ��������¼�
			document.onkeydown = pwdKeyboardDown;
			
			function pwdKeyboardDown(e) {
				var key = GetKeyCode(e);
			
				//����
				if (key == 77) {
					preventEvent(e);
				}
			
				if (!KeyIsNumber(key)) {
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
				//8��32��66��77 ����
			    //82��220 ����
			    //13��89��221 ȷ��
			    //80 ��ӡ
			    //85 ��һҳ
			    //68 ��һҳ
			    
				//���ռ�����
				var key = GetKeyCode(e);
			     
			    //8:backspace 32:�ո� B:66 M:77
			    if (key == 8 || key == 32 || key == 66 || key == 77)
			    {
			    	return false;
			    }
			    
			    //82:R 220:����
				if (key == 82 || key == 220)
				{
					goback("<s:property value='curMenuId' />") ;
			   		return ;
				}
				
				//ȷ��
				if (key == 13 || key == 89 || key == 221) 
				{
					doSub();
					return;
				}
			}
			
			/**
			 * ȷ�Ϸ��ú��ύ
			 * ���̣����������ò�Ϊ0������ת��ѡ�񽻷�����ҳ�档
			 *      ����������Ϊ0�������д�����̡�
			 */
			function doSub()
			{
				//�����ȴ���
				openRecWaitLoading();
						
				setTimeout("writeAndSub()",500);		
			}
			
			/**
			 * ��ʱ���ύ��������������������ص�����
			 */
			function writeAndSub()
			{
				//����������Ϊ0����ֱ�ӽ���д������
				if (getValue("recFee") == 0 && writeFlag == 0)
				{
					//д����ʶλ
					writeFlag = 1;
					
					writeReissueCard();
				}
				
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					// ��������Ϊ0������ת�������ύҳ��
					if (getValue("recFee") == 0)
					{
						document.actform.action="${sessionScope.basePath}reissueCard/reissueCommit.action";
					}
					
					// �����ύ��ѡ�񽻷ѷ�ʽҳ��
					else
					{
						document.actform.action="${sessionScope.basePath}reissueCard/selectPayType.action";
					}
					
					document.actform.submit();	
				}
			}
			
			/**
			 * ��һ��д������
			 * ������д���ؼ���д��ʧ�ܣ�����ж���д��
			 * ����ֱ����ת������ҳ��
			 */
			function writeReissueCard()
			{
				//����״̬ Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��
				setValue("payStatus","0");
				
				//����д��״̬ Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��
				setValue("writeCardStatus","1");
				
				//iccid~~imsi~~issueData~~formNum
				var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("issueData")+"~~"+getValue("formNum");
				
				//��Ŀ·��
				var basePath = "${sessionScope.basePath}";
				
				//д��:��Σ�д���������ݣ��հ׿����ţ���ˮ�ţ�����д�����У�飬������д��ʱ�����һ�£�   
				var writeData = writeCard(simInfo,getValue("blankCard"), basePath, "${sessionScope.CustomerSimpInfo.servNumber }");
				
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
				
				//����д��״̬ Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��
				setValue("writeCardStatus","0");
			}
			
			/**
			 * ����д������
			 * ��Σ�basePath ��Ŀ·����js���޷�����s��ǩ
			 * ���̣�1���հ׿��Ƶ�����λ��2�����հ׿����š�3������հ׿���Դ
			 * 		4��д����5���޸�д��״̬
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
				
				//У��ʧ��
				if (resArray[0] != "0")
				{
					setException(resArray[1]);
					return;
				}
				
				//iccid~~imsi~~issueData~~formNum
				var simInfo = resXml.substring(3);
				
				//4��д��
				var writeData = writeCard(simInfo, getValue("blankCard"), basePath, "${sessionScope.CustomerSimpInfo.servNumber}");
				
				return writeData;
			}
			
			/**
			 * У��հ׿���Դ
			 * ���̣�1��У���Ƿ�ΪԤ�ƿտ���2��У�鿨��Դ�Ƿ���á�
			 *      3��Ԥռ����Դ
			 */
			function checkBlankCard()
			{
				var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")+"&curMenuId="+getValue("curMenuId");  
					 
				var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
				var resXml;
				
				//ͬ������
				var loader = new net.ContentLoaderSynchro(
					url, netload = function () 
					{
						resXml = this.req.responseText;
						var resArray = resXml.split('~~');
						if (resArray[0] == 0 || resArray[0] == "0")
						{
							setValue("iccid",resArray[1]);
							setValue("imsi",resArray[2]);
							setValue("issueData",resArray[3]);
							setValue("formNum",resArray[4]);
						}
					}, 
					null, "POST", postStr, null);
					
				return resXml;
			}
			
			/**
			 *  д���쳣ʱ��ת
			 */
			function setException(errorMsg) 
			{	
				if (submitFlag == 0)
				{
					// �ύ��־��Ϊ1
					submitFlag = 1;
					
					//�����ȴ���
					//openRecWaitLoading();
				
					//��ʾ������Ϣ
					document.getElementById("errormessage").value = errorMsg;
					
					//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
					document.actform.action = "${sessionScope.basePath }reissueCard/goChargeError.action";
					document.actform.submit();
				}			
			}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" name="errormessage" id="errormessage" />
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />"/>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<!-- ���úϼ� -->
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
						
			<%--�Ƿ���Ҫ���տ���1 ��Ҫ --%>
			<input type="hidden" id="callBackCard" name="callBackCard" value="" />
			
			<!-- ʵ�ʽɷѽ�� -->
			<input type="hidden" id="tMoney" name="tMoney" value="0" />
			
		  	<%------����������־��Ϣ---------%>
		 	<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
			<%-- д��״̬  Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ��--%>
			<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
			<%-- ����״̬  Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ��--%>
			<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
			<%-- �հ׿�����--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
		    
		    <%----------------������־��Ϣ---------------%>
			<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" value="<s:property value = 'cardChargeLogVO.chargeLogOID' />"/>
			
			<%-- add begin hWX5316476 2015-6-19 �������Ϊ0ʱ�ɷ�ǰ��¼�ӿڱ���--%>
			<%--�ɷ���ˮ��--%>
			<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value="<s:property value = 'cardChargeLogVO.terminalSeq' />"/>
			<%-- add begin hWX5316476 2015-6-19 �������Ϊ0ʱ�ɷ�ǰ��¼�ӿڱ���--%>
			
		    <!--------------�հ׿���Ϣ -------------->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
			<input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value="simInfoPO.oldiccid" />" />
			<input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
			<input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
			
			<%--���֤��Ϣ�� --%>
			<!-- ���� -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<!-- �Ա� -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<!-- ֤����ַ -->
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
						<a href="javascript:void(0)" class="on">3. ����ȷ��</a>
						<a href="javascript:void(0)">4. ѡ��֧����ʽ</a>
						<a href="javascript:void(0)">5. �����ɷ�</a>
						<a href="javascript:void(0)">6. ȡ����ӡСƱ</a>
					</div>
		
					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank40"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>������ϸ�б�
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							<table width="100%" class="tb_blue02" align="center">
								<tr>
									<th width="25%" class="tc">����</th>
									<td width="25%" class="tc"><span class="yellow fs16">${sessionScope.CustomerSimpInfo.servNumber}</span></td>
									<th width="25%" class="tc">Ʒ��</th>
									<td width="25%" class="tc">
									<span class="yellow fs16">${sessionScope.CustomerSimpInfo.brandName}</span>
									 </td>
								</tr>
							</table>
							<div class="blank20"></div>
							<table id="maintab" align="center" width="100%" class="tb_blue02">
								<tr>
									<th width="40%" class="tc">��������</th>
									<th width="15%" class="tc">��Ԫ��</th>
									<th width="15%" class="tc">�Żݷ��ã�Ԫ��</th>
									<th width="15%" class="tc">ʵ�ս�Ԫ��</th>
								</tr>
								<s:iterator value="feeList" id="feeList">
								<tr>
									<td width="40%" class="tc"><s:property value="#feeList.feeName" /></td>
									<s:if test="%{'���úϼ�'.equals(#feeList.feeName)}">
										<td colspan="3" class="tc"><s:property value="#feeList.realFee" /></td>
									</s:if>
									<s:else>
										<td width="15%" class="tc"><s:property value="#feeList.fee" /></td>
										<td width="15%" class="tc"><s:property value="#feeList.privFee" /></td>
										<td width="15%" class="tc"><s:property value="#feeList.realFee" /></td>
									</s:else>
								</tr>
								</s:iterator>
							</table>
						</div>
						<div class="blank20"></div>
							<a href="#" class="bt10 mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;float:right;right:210px;">ȷ��(�밴ȷ�ϼ�)</a>
					</div>
					<div class=" clear"></div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>