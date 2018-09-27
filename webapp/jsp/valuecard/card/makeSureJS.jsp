<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String yuanMoney = (String) request.getAttribute("totalFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney.trim());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>���п��ɷ�ȷ��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardPayLogVO.cardnumber" value='<s:property value="cardPayLogVO.cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeLogOID" name="cardPayLogVO.chargeLogOID" value="<s:property value='cardPayLogVO.chargeLogOID' />">
			<input type="hidden" id="printcontext" name="printcontext" value=""><!-- ������ӡ��Ϣ -->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
			<input type="hidden" id="fee" name="cardPayLogVO.fee" value="" />
			<input type="hidden" id="errorType" name="errorType" value="" />
			<!-- �мۿ����� -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- �мۿ����� -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- �мۿ���ֵ -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
			<%@include file="/jsp/customize/hub/common/fee/card/makeSure.jsp" %>
		</form>
	
		<script>
		
			// �ύ��־ 0:δ�ύ 1:���ύ
			var submitFlag = 0;
			
			// �˶�ʱ��
			var limitTime = 60;
			
			// �������ҳ��ĸ�����ʾ
			document.getElementById("highLight7").className = "on";
			
			//����ʣ��ʱ��ľ��
			var timeToken;		
			
			function goback(menuid)
			{
				setException("�ɷѲ�����ȡ������ע��ȡ��������������");
			}
			
			/********************��POS�ɷ�����*******************************/
			/**
			1�������ɷ��ն�������ҵ��ǰ�÷������п��ۿ�����
			2������ҵ��ǰ��������POSP�������п��ۿ�ס�
			3������POSP������ҵ��ǰ�÷������п��ۿ�����
			4������ҵ��ǰ�ý����п��ۿ���ת���������ɷ��նˡ�
			5�������ɷ��ն��������ɷ��ն��ۺϹ���ƽ̨�����ƶ���������
			6�������ɷ��ն��ۺϹ���ƽ̨��BOSS�����ƶ����������նˡ�
			7��BOSS�������ɷ��ն��ۺϹ���ƽ̨���ؼ��˽����
			8�������ɷ��ն��ۺϹ���ƽ̨�����˽�����ظ������ն�
			**/
			/**************************************************************/
			
			/************************************************
							��POS����
			************************************************/
			var print_posNum = "";// ���ٺ�
			var print_batchNum = "";// ���κ�
			
			//�����쳣
			function setException(errorMsg) 
			{
				submitFlag = 1;	//�ύ���
				
				// �����ʱ����
				clearInterval(timeToken);
				
				// ��ʾ������Ϣ
				document.getElementById("errormessage").value = errorMsg;
					
				// �쳣����ֻҪ�������쳣��Ҫ��¼��־  
				document.actForm.action = "${sessionScope.basePath }valueCard/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
				document.actForm.submit();
				
			}
			
			// ��BOSS����(������)
			function payToBoss() 
			{
				if (document.getElementById("tMoney").value == "" 
						|| parseInt(document.getElementById("tMoney").value) <= 0)
				{
					return;
				}
				
				//�ύ�����мۿ�����
				
				document.getElementById("fee").value = document.getElementById("tMoney").value;
				
				document.actForm.action = "${sessionScope.basePath }valueCard/buyValueCards.action?cmtPosResCode=" + document.getElementById('posResCode').value;
				document.actForm.submit();			
			}
			
			//�������ۿ�
			function fPosPay()
			{
				try
				{
					// document.getElementById("errorType").value = "add";
					
					//�����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
					var result = GetPosBatchNum();
					var dataArray = result.split("$");
					
					//��ȡ���ٺš����κ�ʧ��
					if (dataArray[0] != "0")
					{
						document.getElementById("errorType").value = "add";
						setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������г�ֵ����ȡ��������������");
						
						return;
					}
					
					//��ȡ���ٺš����κųɹ�
					print_posNum = dataArray[1];
					print_batchNum = dataArray[2];
					
					document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
					
					//�ۿ�֮ǰ��¼��־
					var url = "${sessionScope.basePath }valueCard/addCardChargeLog.action";
					
					var params = "cardPayLogVO.servnumber=" + <s:property value='cardPayLogVO.servnumber' /> + "&cardPayLogVO.payType=1&tMoney=0";
					params = params + "&cardPayLogVO.cardnumber=" + document.getElementById("cardnumber").value + "&cardPayLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;
					params = params + "&cardPayLogVO.posNum=" + print_posNum + "&cardPayLogVO.batchNumBeforeKoukuan=" + print_batchNum;
					var loader = new net.ContentLoader(url, netload = function () {
							var resXml = this.req.responseText;
							var dataArray = resXml.split("~~");
							//��¼��־�ɹ�
							if (dataArray[0] == "1") 
							{
								// ���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
								document.getElementById("errorType").value = "update";
								
								var oid = dataArray[1].replace(/[\r\n]/g, "");
								document.getElementById("chargeLogOID").value = oid;
								
								var posResCode = '';
								
								//�ύ�ۿ�����
								try
								{
									var ret ;
									
									// �ر��������
									ret = CloseCom();
									
									if (ret != "0" || ret != 0)
									{
										setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
										return;
									}
									
									// �����ۿ�
									// posnum ���ٺ�;batchnum ���κţ� bankcardID���п��ţ�money �ɷѽ��(��)
									var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardPayLogVO.cardnumber" />',"<%=fenMoney %>");
		
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
									
									// �ۿ�ʧ��ת���쳣����
									//if (payReturnStr.substring(0,2) == "ER")
									posResCode = payReturnStr.substring(0,2);
									document.getElementById('posResCode').value = posResCode;
									if (payReturnStr.substring(0,2) != "00")
									{
										setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
										return;
									}
									
									// �ۿ�����ȡֵ
									var resultstr = payReturnStr.substring(0,34);
									var printcontext = payReturnStr.substring(34,payReturnStr.length);
									
									document.getElementById('printcontext').value = printcontext;
									
									//������
									
									// var resultstr = "006222021602004724078A000000001234";
									// var printcontext = "302370150210713~123456789012345~���ѽ���~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
									
									// �������� ��Ȩ����ƾ֤�� ���ؿ�ֵ
									// var printcontext = "898420148145268~00167923~���ѽ���~6225887840088682~000725~~171633071883~20110817171633~~1";
									
									
									// �ۿ�ɹ� ����34 �ɹ�00
									if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
									{
									    
									    // �򿪼��̴��ڡ���������ģʽ
										OpenCom();
										SetWorkMode(0);
										
										var printcontexts = printcontext.split('~');							
										document.getElementById("tMoney").value = printcontexts[9];
										
										//������־
										var url1 = "${sessionScope.basePath }valueCard/updateCardChargeLog.action";
						
										var params1 = "cardPayLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value + "&cardPayLogVO.unionpayuser=" + printcontexts[0];
										params1 = params1 + "&cardPayLogVO.unionpaycode=" + printcontexts[1] + "&cardPayLogVO.busiType=" + encodeURI(encodeURI(printcontexts[2]));
										params1 = params1 + "&cardPayLogVO.batchnum=" + printcontexts[4] + "&cardPayLogVO.authorizationcode=" + printcontexts[5];
										params1 = params1 + "&cardPayLogVO.businessreferencenum=" + printcontexts[6] + "&cardPayLogVO.unionpaytime=" + printcontexts[7];
										params1 = params1 + "&cardPayLogVO.vouchernum=" + printcontexts[8] + "&cardPayLogVO.unionpayfee=" + printcontexts[9];
										params1 = params1 + "&cardPayLogVO.status=01&cardPayLogVO.description=" + encodeURI(encodeURI('�ۿ�ɹ�')) + "&cardPayLogVO.posResCode=" + document.getElementById('posResCode').value;
										
										var loader1 = new net.ContentLoader(url1, netload = function () {
											var resXml1 = this.req.responseText;
											
											//������־�ɹ�
											if (resXml1 == "1" || resXml1 == 1)
											{
												//����
												payToBoss();									
											}
											//������־ʧ��
											else
											{	
												setException("�ɷ�ʧ�ܣ���ȡ��������������СƱ��ƾСƱ����Ӫҵ�������˿�������ллʹ��!");
												return;
											}								
										}, null, "POST", params1, null);
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
			
			//�ύ����
			function doSub() 
			{		
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					fPosPay();				
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
			clearTimeout(timeOut);
			startclock();
			
			// ��ʶ�ؼ�ʹ��
			closeStatus = 1;
		</script>
</body>
</html>