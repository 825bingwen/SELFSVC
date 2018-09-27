<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("payAmount");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>���п�����ȷ��</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
		<script language="javascript">

		
		</script>
	</head>
	<body scroll="no">
		<form name="actForm" method="post">
			<%-- �ֻ����� --%>
			<s:hidden id="servnumber" name="chargeLogVO.servnumber" ></s:hidden>
			
			<%-- Ӧ�ɽ�� --%>
			<s:hidden id="payAmount" name="payAmount"></s:hidden>
			
			<%-- ʡ�ݱ��� --%>
			<s:hidden id="telProvinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<%-- �ֻ������������--%>
			<s:hidden id="servRegion" name="chargeLogVO.servRegion"></s:hidden>
			
			<%-- ���--%>
			<s:hidden id="balance" name="balance"></s:hidden>
			
			<%-- ���--%>
			<s:hidden id="tMoney" name="tMoney" value=''></s:hidden>
			
			<%-- �Ƿ���Ҫ�˿�0������Ҫ 1����Ҫ--%>
			<s:hidden id="needReturnCard" name="needReturnCard" value='1'></s:hidden>
			
			<%-- �ն˽�����ˮ--%>
			<s:hidden id="terminalSeq" name="chargeLogVO.terminalSeq"></s:hidden>
			
			<%-- ������Ϣ --%>
			<s:hidden id="errormessage" name="errormessage" value=""></s:hidden>
			
			<%-- �ɷ���־Ψһ��ʶ  --%>
			<s:hidden id="chargeLogOID" name="chargeLogVO.chargeLogOID" value=""></s:hidden>
			
			<%-- ֧����ʽ 1:������ 4���ֽ�--%>
			<s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>	
			
			<%-- �����˳���ʶ--%>
			<s:hidden id="quitFlag" name="quitFlag" value=""></s:hidden>
			
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
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)" class="on">7. �˶���Ϣ</a>
    					<p class="recharge_tips">�˶Խ��Ѻ���</p>
    					<a href="javascript:void(0)">8. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="chargeLogVO.servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="payAmount" /></span> Ԫ</p>
							<p class="tit_info_noheight"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />�롣</p>
							<p class="tit_info">ȷ�Ͻ��ѣ��밴���Ѽ�����Ҫȡ�����ν��ѣ��밴�˳�����</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" onclick="commitBusi();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����(��ȷ�ϼ�)</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
			</div>

			<div class="footer" id="footer">
				<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'"></a>
				<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';"></a>
				<a id="quitBtn" href="javascript:void(0);" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'" onclick="cancelBusi();return false;"></a>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	// �����ύ��ʶ
	var submitFlag = 0;
	
	// �쳣�ύ��־ 0:δ�ύ 1:���ύ
	var exSubmitFlag = 0;
	
	// �˶�ʱ��
	var limitTime = 60;
	
	//����ʣ��ʱ��ľ��
	var timeToken;		
	
	var termid = "<%=termInfo.getTermid() %>";
	
	// �̻�����
	var unitType = "1";
	
	// ��������
	var businesstype = "A";// 'A':���ѽ��� 'B':�ش�ӡ 'C':�����
	
	//82��220 ����		
	document.onkeyup = pwdKeyboardUp;

	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//����
		if (key == 13 || key == 89 || key == 221)
		{
			commitBusi();
		}
		//�˳���82
		else if (key == 82)
		{
			cancelBusi();
		}
	}
	
	clearTimeout(timeOut);
	startclock();

	// ������ʱ��
	function startclock() 
	{
		try 
		{
			timeToken = setInterval("waitForSubmit()", 1000);
		}
		catch (e) 
		{
			setException("�˶���Ϣʧ�ܣ���ȡ��������������");
		}
	}
	
	//����ʣ��ʱ��
	function waitForSubmit() 
	{
		writeDetailLog("<%=chargeLogDetail %>",  "�ȴ��û�ȷ�Ͻɷ�");
	
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
			submitFlag = 1;
			
			writeDetailLog("<%=chargeLogDetail %>", "ȷ�ϳ�ʱ");
			
			// �����쳣��������־
			setException("�˶���Ϣ��ʱ����ȡ��������������");
		}
	}
	
	// ȷ�Ͻ���
	function commitBusi()
	{
		if (submitFlag == 0) 
		{
			submitFlag = 1;
			
			writeDetailLog("<%=chargeLogDetail %>", "ȷ�Ͻ���");
				
			//�����ʱ����
			clearInterval(timeToken);
					
			openChargeWaitLoading();
		
			setTimeout("fPosPay()", 500);
		}
	}
	
	// �������ۿ�
	function fPosPay()
	{
		try
		{
			writeDetailLog("<%=chargeLogDetail %>", "�ۿ�֮ǰ��¼������־");
			
			//�ۿ�֮ǰ��¼��־
			var url = "${sessionScope.basePath }nonlocalCharge/addCardChargeLog.action";
			
			var params = "chargeLogVO.servnumber=" + <s:property value='chargeLogVO.servnumber' /> ;
			params = params + "&tMoney=" + <s:property value='payAmount' />*100;
			params = params	+ "&status=00";
			params = params + "&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־'));
			params = params	+ "&chargeLogVO.servRegion=" + document.getElementById("servRegion").value;
			params = params + "&chargeLogVO.provinceCode="+document.getElementById("telProvinceCode").value;
		
			var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
				var dataArray = resXml.split("~~");
				
				// ��¼��־�ɹ�
				if (dataArray[0] == "1") 
				{
					writeDetailLog("<%=chargeLogDetail %>", "��־��¼�ɹ�");
				
					document.getElementById("chargeLogOID").value = dataArray[1];							
									
					try
					{
    					writeDetailLog("<%=chargeLogDetail %>", "�����ر�");
    					
    					//������
						var ret = CloseReadingCardFixing();// �رն�����������������д򿪵��߳�

						if (ret != 0)
						{
							setException("�رն�����������������д��߳�ʧ�ܣ���ȡ��������������");
								
							return;
						}
					}
					catch(e)
					{
						setException("�رն�����������������д��߳�ʧ�ܣ���ȡ��������������");
								
						return;
					}
						
					// �����������Ϊ�������ģʽ
					try
					{
						writeDetailLog("<%=chargeLogDetail %>", "����������̹���ģʽΪ����");
						
						//������
						var ret = SetWorkMode(1);// �����������Ϊ�������ģʽ
						
						if (ret == -1)
						{
							setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ�ܣ���ȡ��������������");
								
							return;
						}
					}
					catch(e)
					{
						setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ�ܣ���ȡ��������������");
							
						return;
					}							
						
					// �����������ۿ�����	
					var continueFlag = false;
					
					// �������
					var bankrequest;
					
					// ��������
					var bankresponse;
					
					// LRCУ��_3λ�������
					var randomNumber = "" + Math.floor(Math.random()*10) + Math.floor(Math.random()*10) + Math.floor(Math.random()*10);
					
					try
					{
						// ����������
						// POS����(8) + POSԱ����(8) + �������ͱ�־(2)_'41:�ɷ�' + ���(12) + ԭ��������(12) + ԭ���ײο���(12) + ԭƾ֤��(6) 
						// LRCУ��(3) + �绰����(20) + ����Ʊ����(2)_00:�����ط�Ʊ��01:���ط�Ʊ������ʱΪ�ո�
						// ������(2)_01�����뽻��,11�������ʺŽ��� + ��ҵ��Ϣ1(20) + ��ҵ��Ϣ(20)
						//bankrequest = formatStr(termid,'right',' ',8);// POS����(8)
						bankrequest = createBlankStr(8);// POS����(8)
						bankrequest = bankrequest + createBlankStr(8);// POSԱ����(8)
						bankrequest = bankrequest + '41';// �������ͱ�־(2)_'41:�ɷ�'
						bankrequest = bankrequest + formatStr('<%=fenMoney %>','left','0',12);// ���(12)
						bankrequest = bankrequest + createBlankStr(8);// ԭ��������(8)
						bankrequest = bankrequest + createBlankStr(12);// ԭ���ײο���(12)
						bankrequest = bankrequest + createBlankStr(6);// ԭƾ֤��(6)
						bankrequest = bankrequest + randomNumber;// LRCУ��(3)
						bankrequest = bankrequest + formatStr('<s:property value="chargeLogVO.servnumber" />','right',' ',20);// �绰����(20)
						bankrequest = bankrequest + '01';// ����Ʊ����(2)_00:�����ط�Ʊ��01:���ط�Ʊ������ʱΪ�ո�
						bankrequest = bankrequest + '01';// ������(2)_01�����뽻��,11�������ʺŽ���
						bankrequest = bankrequest + createBlankStr(20);// ��ҵ��Ϣ1
						bankrequest = bankrequest + createBlankStr(20);// ��ҵ��Ϣ2
						
						writeDetailLog("<%=chargeLogDetail %>", "�ۿ������ģ�" + bankrequest);
								
						// ������
						// ������:2,�����к�:4,����:20,ƾ֤��:6,���:12,����˵��:40,�̻���:15,�ն˺�:8,���κ�:6,��������:4,
						// ����ʱ��:6,���ײο���:12,��Ȩ��:6,��������:4,LRCУ��:3
						
						// ����������
						if (true)// true:���� false:����
						{
							window.parent.document.getElementById("unionpluginid").bankrequest = bankrequest;
							
							// ִ�нɷ�
							window.parent.document.getElementById("unionpluginid").trans();
							
							// ���׷��ر���
							bankresponse = window.parent.document.getElementById("unionpluginid").BankResponse;
						}
						else
						{
							// �ɹ�
							bankresponse = '00    4563518600005509778 001778000000002000���׳ɹ�                                09510000000000100000002      1126164654000001217097          ' + randomNumber;
							
							// ʧ��
							//bankresponse = 'Y1                                          ���ŵ���Ϣ����                                                                                          '; 
						}
						
						writeDetailLog("<%=chargeLogDetail %>",  "�ۿ�Ӧ���ģ�" + bankresponse);
						
						continueFlag = true;
					}
					catch (e){}
					
					// �����������Ϊ��ͨ����ģʽ
					try
					{
						writeDetailLog("<%=chargeLogDetail %>",  "����������̹���ģʽΪ���ĸ�ʽ");
						
						//������
						var ret = initKeyBoard();// �����������Ϊ��ͨ����ģʽ
					}
					catch(e)
					{
					}
						
					if (!continueFlag)
					{
						writeDetailLog("<%=chargeLogDetail %>", "�ۿ��쳣");
						
						setException("�������ɷ�ʧ�ܣ���ȡ��������������");
						return;
					}
					
					// �ۿ�ɹ� ����148 �ɹ�00 �ȶ�������Ƿ�һ��
					if (bankresponse.substring(0,2) == "00" && strlen(bankresponse) == 148 && bankresponse.substring(bankresponse.length-3,bankresponse.length) == randomNumber)
					{
					    // ƾ֤�� + ���ײο���
						document.getElementById("terminalSeq").value = bankresponse.substring(26,32) + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);
						document.getElementById("tMoney").value = bankresponse.substring(32,44);
						writeDetailLog("<%=chargeLogDetail %>", "�ۿ�ѳɹ������ѽ�" + document.getElementById("tMoney").value);
								
						//������־
						var url1 = "${sessionScope.basePath }nonlocalCharge/updateCardChargeLog.action";

						var params1 = "chargeLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value;// id
						params1 = params1 + "&chargeLogVO.bankno=" + bankresponse.substring(2,6);// �����к� �����д���
						params1 = params1 + "&chargeLogVO.cardnumber=" + bankresponse.substring(6,26);// ����
						params1 = params1 + "&chargeLogVO.vouchernum=" + bankresponse.substring(26,32);// ��֤��
						params1 = params1 + "&chargeLogVO.unionpayfee=" + bankresponse.substring(32,44);// �ۿ���
						params1 = params1 + "&chargeLogVO.unionpayuser=" + bankresponse.substring(bankresponse.length-64,bankresponse.length-49);// �̻���
						params1 = params1 + "&chargeLogVO.unionpaycode=" + bankresponse.substring(bankresponse.length-49,bankresponse.length-41);// �ն˺�
						params1 = params1 + "&chargeLogVO.busitype=" + encodeURI(encodeURI("�ɷѽ���"));// ��������
						params1 = params1 + "&chargeLogVO.posNum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// ���κ�
						params1 = params1 + "&chargeLogVO.batchnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// ���κ�
						params1 = params1 + "&chargeLogVO.unionpaytime=" + bankresponse.substring(bankresponse.length-35,bankresponse.length-31) + bankresponse.substring(bankresponse.length-31,bankresponse.length-25);// �����ۿ�ʱ��
						params1 = params1 + "&chargeLogVO.businessreferencenum=" + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);// ���ײο���
						params1 = params1 + "&chargeLogVO.authorizationcode=" + bankresponse.substring(bankresponse.length-13,bankresponse.length-7);// ��Ȩ��
						params1 = params1 + "&chargeLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;// �ն���ˮ
						params1 = params1 + "&chargeLogVO.status=11";
						params1 = params1 + "&chargeLogVO.description=" + encodeURI(encodeURI('�ۿ�ɷѳɹ�'));
						params1 = params1 + "&chargeLogVO.servnumber=<s:property value='chargeLogVO.servnumber' />";
								
						var loader1 = new net.ContentLoader(url1, netload = function () {
							var resXml1 = this.req.responseText;
							
							//������־�ɹ�
							if (resXml1 == "1" || resXml1 == 1)
							{
								writeDetailLog("<%=chargeLogDetail %>", "�ۿ�ѳɹ��󣬸�����־��¼�ɹ�");
								
								//����
								goSuccess();									
							}
							//������־ʧ��
							else
							{
								writeDetailLog("<%=chargeLogDetail %>",  "�ۿ�ѳɹ��󣬸�����־��¼ʧ��");
								
								setException("�������ɷѳɹ������Ǹ�����־ʧ�ܡ���ȡ��������������");
								
								return;
							}								
						}, null, "POST", params1, null);
					}
					//�ۿ�ʧ��
					else
					{
						writeDetailLog("<%=chargeLogDetail %>", "�ۿ�ʧ��");
						
						setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��" + bankresponse.replace(/\s/g,''));
						return;
					}				
				}
				//��¼��־ʧ��
				else 
				{
					writeDetailLog("<%=chargeLogDetail %>", 
							"��־��¼ʧ��");
					
					setException("�ɷ�֮ǰ��¼��־ʧ�ܣ���ȡ��������������");
					
					return;
				}					
			}, null, "POST", params, null);	
		}
		catch (e)
		{
			setException("�������ɷ��쳣����ȡ��������������");
		}				
	}
	
			//�����쳣
		function setException(errorMsg) 
		{
			exSubmitFlag = 1;	//�ύ���
			
			writeDetailLog("<%=chargeLogDetail %>", 
					"�����������쳣��" + errorMsg);
				
				//�����ʱ����
			clearInterval(timeToken);
		
			document.getElementById("errormessage").value = errorMsg;
			
			//�쳣����ֻҪ�������쳣��Ҫ��¼��־
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }nonlocalCharge/goCardError.action";
			document.actForm.submit();
		}
		
		// ��BOSS����(������)
		function goSuccess() 
		{
			writeDetailLog("<%=chargeLogDetail %>", "�ۿ�ѳɹ�");
			
			//�ύ�ɷ�����
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }nonlocalCharge/cardChargeCommit.action";
			document.actForm.submit();			
		}
		
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
		
		<%-- �û��������˳����ѽ��ס� --%>
		function cancelBusi()
		{
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>",  "�˳�����");
				
				document.getElementById("quitFlag").value = "1";
						
				setException("����ȡ�����ν��ѣ���л����ʹ�ã���ȡ��������������");
			}			
		}
	
	// ��ʶ�ؼ�ʹ��
	closeStatus = 1;
	</script>
</html>
