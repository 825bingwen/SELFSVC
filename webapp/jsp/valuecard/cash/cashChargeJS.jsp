<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// Ͷ�ҳ�ʱʱ��
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);

// �ն���Ϣ
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// �ֽ�ʶ��������
String isCashEquip = termInfo.getTermspecial().substring(3, 4);

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion }"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />" />
		<input type="hidden" id="chargeLogOID" name="cardPayLogVO.chargeLogOID" value="<s:property value='cardPayLogVO.chargeLogOID' />" />
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType'/>"/>
		<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=""/>
		<input type="hidden" id="fee" name="cardPayLogVO.fee" value="" />
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<!-- �мۿ����� -->
		<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
		<!-- �мۿ����� -->
		<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
		<!-- �мۿ���ֵ -->
		<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		
		<%@include file="/jsp/customize/hub/common/fee/cash/cashCharge.jsp" %>
	</form>
	<script>
	
		//��ֹ�ظ��ύ
		var submitFlag = 0;
		
		// ����ҳ�����˵�����
		document.getElementById("highLight4").className = "on";
		
		// Ͷ�ҵ�ʱ������λ��		
		var payMoneyTime = "<%=timeout %>";
		
		// ʣ��ʱ��
		var leftTime = payMoneyTime;
		
		// readCash��ʱ�����
		var readCashToken;
		
		// �ر�ʶ������0������Ҫ��1����Ҫ
		var needClose = 0;
		
		// �ύ��ǣ�0��ʾδȷ���ύ�ɷѣ�1��ʾ��ȷ���ύ�ɷ�
		var submitFlag = 0;
		
		function goback(menuid)
		{
			// ��Ͷ��
			if (document.getElementById("tMoney").value != "" 
					&& parseInt(document.getElementById("tMoney").value) > 0)
			{
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// ����ʱ�������ʱ����ͬʱ�ر��ֽ�ʶ����			
				if (needClose == 1)
				{
					fCloseCashBill();
				}
				
				clearInterval(readCashToken);
				
				document.getElementById("curMenuId").value = menuid;
				
				document.actform.action = "${sessionScope.basePath }valueCard/qryPayType.action";
				document.actform.submit();
			}
		}
			
		// �ύ
		function doSub()
		{
			// δͶ��
			if (document.getElementById("tMoney").value == "" 
					|| parseInt(document.getElementById("tMoney").value) <= 0)
			{
				return;
			}
			
			// ��δ�ύ����
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// �޸Ľɷ��ظ��ύ���� change by LiFeng
				wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
			
				// �ر��ֽ�ʶ����
				if (needClose == 1)
				{
					fCloseCashBill();
				}
				
				// �����ʱ����
		        clearInterval(readCashToken);
				
				// �û����ѽ��
				document.getElementById("fee").value = document.getElementById("tMoney").value;
				
				// �ύ�ɷ�����
				document.actform.action = "${sessionScope.basePath }valueCard/buyValueCards.action";
				document.actform.submit();
			}
		}
			
		// �����쳣
		function setException(errorMsg) 
		{			
			document.getElementById("errormessage").value = errorMsg;
			
			// �����쳣�������ʱ����ͬʱ�ر��ֽ�ʶ����			
			if (needClose == 1)
			{
				fCloseCashBill();
			}
			
			// �����ʱ����
			clearInterval(readCashToken);
			
			// �쳣����ֻҪ�������쳣��Ҫ��¼��־  
			document.actform.action = "${sessionScope.basePath }valueCard/goCashError.action";
			document.actform.submit();
		}		
		
		// ��ȡ�ֻ�����
		// ��ʼ���ֽ�ʶ����
		// ��ȡͶ����ˮ
		// ʹ��ҳ���˳���ť������
		// ����ѭ����ȡͶ�ҽ��
		function loadContent() 
		{
			var serverNumber = "<s:property value='cardPayLogVO.servnumber' />";
			if (serverNumber == null || serverNumber == "")
		    {            
				setException("�Բ����û���Ϣ��ȡʧ�ܣ��뷵�����²�����");
				return;
			}
		          
			<%if (!"1".equals(isCashEquip)){%>
				  setException("�Բ��𣬸��ն˻��ݲ�֧���ֽ��ֵ����ѡ��������ʽ��");
				  return;
			<%}%>
		    
		    try 
		    {
		    	// ��ʼ���ֽ�ʶ����(�������� 0,20110509143345)
				var initData = initCashEquip(serverNumber);
			   	
			   	// ��ʶ�ؼ�ʹ��
			   	closeStatus = 2;
			   	
		        if (initData.substring(0, 1) != "0") 
		        {
		        	setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
		            return;                    
		        }
		        else
		        {
		        	//�ֽ�ʶ������ʼ���ɹ������ٽ����û�Ͷ��ʱ����Ҫ�رա�
		        	needClose = 1;
		        	
		        	//��ȡͶ����ˮ
		        	document.getElementById("terminalSeq").value = initData.substring(2);
		        	
		        	//��ʼ���ɹ�������Ҫ�ر�ʶ����������ҳ���˳���ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
		        	document.getElementById("homeBtn").disabled = true;
		        	document.getElementById("quitBtn").disabled = true;
		        	
		        	startclock();
		        }               
		    } 
		    catch(e) 
		    {
		        setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
		        return;
		    }           
		}
		
		//ѭ����ȡͶ�ҽ��
		function startclock() 
		{
			var msg = preparecash();
			
			//ʶ����״̬���ʧ�ܣ�������Ͷ�ң���ʾ�쳣��Ϣ
			if (msg != "") 
			{
				setException(msg);
				return;
			}		
			
			try
			{
				// ��ȡͶ�ҽ��,ѭ������,ÿ��1��ִ��һ��
				readCashToken = setInterval("doCash()", 1000);
			}
			catch (e) 
			{
				setException("�Բ��𣬼�ʱ�������쳣���޷�ʹ���ֽ���г�ֵ����ʹ��������ʽ���ߵ�Ӫҵ�����г�ֵ��");
			}
		}
		
		//���ʶ����״̬
		function preparecash()
		{
			var msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽֵ��";
		
			try 
			{
				//���ʶ����״̬ 0-���� 1-�쳣 2-Ǯ���� 3-Ǯ��� 4-��ڱ��� 5-Ǯ�䱻�� 6-�������� 9-�޴��豸
				var cashStatus = checkCashStatus();
		
				if (cashStatus == 0)
				{
					msg = "";
				}
				else if (cashStatus == 1) 
				{
					msg = "�Բ����ֽ�ʶ����״̬�쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				}
				else if (cashStatus == 2) 
				{
					msg = "�Բ���Ǯ���������޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				}
				else if (cashStatus == 3) 
				{
					msg = "�Բ���Ǯ��δ�����رգ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				} 
				else if (cashStatus == 4) 
				{
					msg = "�Բ���Ǯ���볮�ڱ��У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				}
				else if (cashStatus == 5) 
				{
					msg = "�Բ���Ǯ�䱻�У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				} 
				else if (cashStatus == 6) 
				{
					msg = "�Բ����ֽ�ʶ��������δ֪�����޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				}
				else if (cashStatus == 9) 
				{
					msg = "�Բ����ֽ�ʶ���������ڣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
				}			
			}
			catch (e) 
			{
				msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
			}
			
			// ����
			return msg;
		}
		
		// ��ȡͶ�ҽ��
		// ѭ�����ã�ÿ��1��ִ��һ��
		function doCash() 
		{
			if (leftTime <= 0)
			{
				return;
			}
			
			try 
			{	
				// ��ȡͶ�ҽ�� 0 ��ʾû��Ͷ�ң����� ΪͶ����ֵ(���ܵ�ֵΪ��1,2,5,10,20,50,100)��
				var ret = getOnceCash();
		
				if (ret > 0) 
				{
					// ��ʶ�ؼ�ʹ��
			   		closeStatus = 1;
					
					// ʱ�����¿�ʼ
					leftTime = "<%=timeout %>";
					
					// ��ʾʣ��ʱ��
					document.getElementById("tTime").value = leftTime;
					
					// Ͷ�Һ󣬲��ܷ���
		            document.getElementById("backBtn").disabled = true;
					
					// ����Ͷ�ҽ�� 
					document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
					
					// ȡ�����װ�Ť����ʾ
					document.getElementById('cancelBusi').style.display = "none";
					
					// Ͷ�ҽ�����0ʱȡ�����װ�Ť����ʾ,�ɷѰ�Ť��ʾ
					if (parseInt(document.getElementById("tMoney").value) > 0)
					{
						document.getElementById('bCommitBusi').style.display = "none";
						document.getElementById('commitBusi').style.display = "block";
						document.getElementById("promptMsg").innerHTML = "��Ͷ��ֽ�ң������ɷѰ�ť";
					}
					else
					{
						document.getElementById('bCommitBusi').style.display = "block";
					}
					
					var postStr = "cardPayLogVO.chargeLogOID="+getValue("chargeLogOID")+"&cardPayLogVO.cashMoney="+ret+"&cardPayLogVO.terminalSeq="+getValue("terminalSeq");  
					
					// ͬ�����ã������ֽ�Ͷ����־
					var loader = new net.ContentLoaderSynchro(
						"${sessionScope.basePath}valueCard/updateCashChargeLog.action", netload = function () 
						{	
							if (this.req.responseText != "1")
							{
								setException("�ֽ𽻷Ѽ�¼�ֽ�Ͷ����ϸ��־ʧ�ܡ�");
								
								return;
							}
						}, 
						null, "POST", postStr, null);
				}
				else
				{
					// Ͷ��ʱ��һ��timeout��
					leftTime = leftTime - 1;
					
					// ��ʾʣ��ʱ��
					document.getElementById("tTime").value = leftTime;
					
					//Ͷ��ʱ����������û�û�������ύ�ɷ����󣬴�ʱ����Ҫ�ύ�ɷ�����
					if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
					{           	 	
				        //Ͷ�ҽ�����0
				       	if (parseInt(document.getElementById("tMoney").value) > 0) 
						{
							// �ύ�ɷ�
							doSub();
						} 
						else 
						{
							// ȡ������
							cancelBusi();
							//setException("Ͷ��ʱ�������Ͷ�ҽ��Ϊ0���޷����нɷѲ���");
						}
					}
				}				
			}
			catch (e) 
			{
				setException("�Բ��𣬻�ȡͶ�ҽ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
			}
		}
		
		// ȡ������
		function cancelBusi()
		{		
			if (needClose == 1)
			{
				// �ر��ֽ�ʶ����
				fCloseCashBill();
			}
			
			// �����ʱ����
			clearInterval(readCashToken);
			
			// ������ҳ
			setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
		}
		
		clearTimeout(timeOut);
		loadContent();
	</script>

</body>
</html>