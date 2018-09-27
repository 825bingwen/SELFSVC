<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>���п��ۿ�ȷ��</title>
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
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }activitiesRec/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
			document.actForm.submit();
			
		}
		
		// ��BOSS����(������)
		function payToBoss() 
		{
			if (document.getElementById("unionpayfee").value == "" 
					|| parseInt(document.getElementById("unionpayfee").value) <= 0)
			{
				return;
			}
			
			//�ύ�ɷ�����
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }activitiesRec/cardFinish.action?cmtPosResCode=" + document.getElementById('posResCode').value;;
			document.actForm.submit();			
		}
		
		//�������ۿ�
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				// �����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
				// �ɹ�����:0$���ٺ�$���κ�;
				// ʧ�ܷ���:-1
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				// ��ȡ���ٺš����κ�ʧ��
				if (dataArray[0] != "0")
				{
					setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������л������ȡ��������������");
					return;
				}
				
				// ��ȡ���ٺš����κųɹ�
				print_posNum = dataArray[1];
				print_batchNum = dataArray[2];
				
				// �ն���ˮ(���ٺ�+���κ�)
				document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
				
				// �ۿ�֮ǰ��¼��־
				var url = "${sessionScope.basePath }activitiesRec/addChargeLog.action";
				
				// ��װ�ۿ�֮ǰ��¼��־����
				var params = "";
				params = params + "servnumber=<s:property value='servnumber' />";// �ֻ�����
				params = params + "&prepayFee=<s:property value='prepayFee' />";// �ۿ���
				params = params + "&cardnumber=" + document.getElementById("cardnumber").value;// ��������
				params = params + "&terminalSeq=" + document.getElementById("terminalSeq").value;// ��ˮ��(���ٺ�+���κ�)
				params = params	+ "&status=00";// ״̬
				params = params + "&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־'));
				params = params + "&posnum=" + print_posNum ;// ���ٺ�
				params = params + "&batchnumbeforekoukuan=" + print_batchNum;// ���κ�
			    
			    // ajaxִ�пۿ�֮ǰ��¼��־,��ȡ��chargeLogOID
				var loader = new net.ContentLoader(url, netload = function () {
						
						// ������Ϣ���ɹ���1~~chargeLogOID ʧ�ܣ�0��
						var resXml = this.req.responseText;
						
						// ���³�����
						var dataArray = resXml.split("~~");
						
						//��¼��־�ɹ�
						if (dataArray[0] == "1") 
						{
							//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
							document.getElementById("errorType").value = "update";
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							document.getElementById("chargeLogOID").value = oid;
							
							// �ն˷�����
							var posResCode = '';
							
							//�ύ�ۿ�����
							try
							{
								var ret ;
								
								// �ر��������
								ret = CloseCom()
								if (ret != "0" || ret != 0)
								{
									setException("�������ۿ�ʧ�ܣ����ܼ������л�����������ȡ��������������");
									return;
								}
								
								// �����ۿ�
								// posnum ���ٺ�;batchnum ���κţ� bankcardID���п��ţ�money �ۿ���(��)
								var prepayFee_fen = <s:property value='prepayFee' /> * 100;
								var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',prepayFee_fen);
								
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
								posResCode = payReturnStr.substring(0,2);
								document.getElementById('posResCode').value = posResCode;
								if (payReturnStr.substring(0,2) != "00")
								{
									setException("�������ۿ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
									return;
								}
								
								// �ۿ�����ȡֵ
								var resultstr = payReturnStr.substring(0,34);
								var printcontext = payReturnStr.substring(34,payReturnStr.length);
								
								document.getElementById('printcontext').value = printcontext;
								
								/**
								//������
								var resultstr = "006222021602004724078A000000001234";
								//var printcontext = "302370150210713~123456789012345~���ѽ���~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
								
								// �������� ��Ȩ����ƾ֤�� ���ؿ�ֵ
								var printcontext = "898420148145268~00167923~���ѽ���~6225887840088682~000725~~171633071883~20110817171633~~10000";
								**/
								
								// �ۿ�ɹ� ����34 �ɹ�00
								if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
								{
								    
								    // �򿪼��̴��ڡ���������ģʽ
									OpenCom();
									SetWorkMode(0);
									
									// ������ӡ��Ϣ
									var printcontexts = printcontext.split('~');
										
									// �����ۿ���
									document.getElementById("unionpayfee").value = printcontexts[9];
									
									//������־
									var url1 = "${sessionScope.basePath }activitiesRec/updateCardChargeLog.action";
									
									var params1 = "";
									params1 = params1 + "chargeLogOID=" + document.getElementById("chargeLogOID").value;// id
									params1 = params1 + "&unionpayuser=" + printcontexts[0];// �̻����
									params1 = params1 + "&unionpaycode=" + printcontexts[1];// �ն˱��
									
									<%-- modify by sWX219697 2015-7-20 09:56:43 busitype��ΪbusiType findbugs�޸� --%>
									params1 = params1 + "&busiType=" + encodeURI(encodeURI(printcontexts[2]));
									params1 = params1 + "&batchnum=" + printcontexts[4];// ���κ�
									params1 = params1 + "&authorizationcode=" + printcontexts[5];// ��Ȩ��
									params1 = params1 + "&businessreferencenum=" + printcontexts[6];// ���ײο���
									params1 = params1 + "&unionpaytime=" + printcontexts[7];// �����ۿ�ʱ��
									params1 = params1 + "&vouchernum=" + printcontexts[8];// ��֤��
									params1 = params1 + "&unionpayfee=" + printcontexts[9];// �����ۿ���
									params1 = params1 + "&status=01";// ״̬
									params1 = params1 + "&description=" + encodeURI(encodeURI('�ۿ�ɹ�'));// ������Ϣ
									params1 = params1 + "&initPosResCode=" + document.getElementById('posResCode').value;// POS������
									
									var loader1 = new net.ContentLoader(url1, netload = function () {
										
										// ������Ϣ
										var resXml1 = this.req.responseText;
										
										// ������־�ɹ�
										if (resXml1 == "1" || resXml1 == 1)
										{
											// ����
											payToBoss();							
										}
										// ������־ʧ��
										else
										{	
											setException("�����ʧ�ܣ���ȡ��������������СƱ��ƾСƱ����Ӫҵ�������˿ллʹ��!");
											return;
										}								
									}, null, "POST", params1, null);
								}
								//�ۿ�ʧ��
								else
								{
									setException("�������ۿ�ʧ�ܣ����ܼ������л�����������ȡ��������������");
									return;
								}
									
							}
							catch (e)
							{
								
								if (document.getElementById("unionpayfee").value != "" && parseInt(document.getElementById("unionpayfee").value) > 0)
								{
									setException("�������ۿ�ɹ������ǻ����ʧ�ܡ���ȡ��������������");
								}
								else
								{
									setException("�������ۿ�ʧ�ܣ����ܼ������л�����������ȡ��������������");
								}
							}							
						}
						// ��¼��־ʧ��
						else 
						{						
							setException("��־��¼ʧ�ܣ����ܽ����������ɷѲ�������ȡ��������������");
						}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("unionpayfee").value != "" && parseInt(document.getElementById("unionpayfee").value) > 0)
				{
					setException("�������ۿ�ɹ������ǻ����ʧ�ܡ���ȡ��������������");
				}
				else
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������л�����������ȡ��������������");
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
		</script>
	</head>
	<body scroll="no" onload="doLoad()">
		<form name="actForm" method="post">	
			<!-- Ӫ���Ƽ���ʶ -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
				
			<input type="hidden" id="payType" name="payType" value="4"/>
			
			<!-- ������� -->
			<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
			<!-- �������� -->
			<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
			<!-- ����� -->
			<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
			<!-- ���α��� -->
			<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
			<!-- ��Ʒ���봮 -->
			<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
			<!-- �ֻ�imeiid�� -->
			<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
			<!-- ��Ʒ�ܼ� -->
			<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
			<!-- ��Ʒ���� -->
			<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
			
			<%-- modify by sWX219697 2015-7-20 totalfee��ΪtotalFee,findbugs�޸�--%>
			<!-- �û�Ͷ��ķ��ý�� -->
			<input type="hidden" id="totalFee" name="totalFee" value='<s:property value="#request.totalFee" />'/>
			<!-- ������_�����ն˶���������� -->
			<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
			<!-- �����ۿ��� -->
			<input type="hidden" id="unionpayfee" name="unionpayfee" value=''>
			<!-- �Ƿ���Ҫ�˿� 0:����Ҫ 1:��Ҫ -->
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<!-- �������� -->
			<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
			<!-- �ն���ˮ -->
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<!-- �쳣���� -->
			<input type="hidden" id="errorType" name="errorType" value=''>
			<!-- �쳣��Ϣ -->
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<!-- ��ˮ�� -->
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="">
			<!-- ������ӡ��Ϣ -->
			<input type="hidden" id="printcontext" name="printcontext" value="">
			<!-- �ۿ���� -->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��������̣�</h2>
						<div class="blank10"></div>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="����Э��" href="#">2. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
    					<a title="���봢�" href="#">4. ���봢�</a>
    					<a title="��������" href="#">5. ��������</a>
    					<a title="�˶���Ϣ" href="javascript:void(0)" class="on">6. �˶���Ϣ</a>
    					<a title="���" href="javascript:void(0)">7. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">Ӧ�ɽ�<span class="yellow fs22"><s:property value="#request.prepayFee" /></span> Ԫ</p>
							<p class="tit_info"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />��</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a title="����" href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--������ ���ڴ��� ���Ժ�-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" /></p>
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
