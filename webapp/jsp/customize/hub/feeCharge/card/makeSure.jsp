<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String yuanMoney = (String) request.getAttribute("yingjiaoFee");

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
			//modified by xkf57421 for XQ[2011]_10_082 begin
			//document.actForm.action = "${sessionScope.basePath }charge/goCardError.action";
			document.actForm.action = "${sessionScope.basePath }charge/goCardError.action?errPosResCode=" + document.getElementById('posResCode').value;
			//modified by xkf57421 for XQ[2011]_10_082 end
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
			
			//�ύ�ɷ�����
			document.actForm.target = "_self";
			//modified by xkf57421 for XQ[2011]_10_082 begin
			document.actForm.action = "${sessionScope.basePath }charge/cardChargeCommit.action?cmtPosResCode=" + document.getElementById('posResCode').value;;
			//modified by xkf57421 for XQ[2011]_10_082 end
			document.actForm.submit();			
		}
		
		//�������ۿ�
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				//�����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				//��ȡ���ٺš����κ�ʧ��
				if (dataArray[0] != "0")
				{
					setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������г�ֵ����ȡ��������������");
					
					return;
				}
				
				//��ȡ���ٺš����κųɹ�
				print_posNum = dataArray[1];
				print_batchNum = dataArray[2];
				
				document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
				
				//�ۿ�֮ǰ��¼��־
				var url = "${sessionScope.basePath }charge/addChargeLog.action";
				
				var params = "servnumber=" + <s:property value='servnumber' /> + "&paytype=1&tMoney=" + <s:property value='yingjiaoFee' />;
				params = params + "&cardnumber=" + document.getElementById("cardnumber").value + "&terminalSeq=" + document.getElementById("terminalSeq").value;
				params = params	+ "&status=00&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־')) + "&acceptType=" + document.getElementById("acceptType").value;
				params = params	+ "&servRegion=" + document.getElementById("servRegion").value + "&posnum=" + print_posNum + "&batchnumbeforekoukuan=" + print_batchNum;
			
				var loader = new net.ContentLoader(url, netload = function () {
						var resXml = this.req.responseText;
						var dataArray = resXml.split("~~");
						//��¼��־�ɹ�
						if (dataArray[0] == "1") 
						{
							//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
							document.getElementById("errorType").value = "update";
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							document.getElementById("chargeLogOID").value = oid;
							
							//add by xkf57421 for XQ[2011]_10_082 begin
							var posResCode = '';
							//add by xkf57421 for XQ[2011]_10_082 end
							
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
								var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',"<%=fenMoney %>");

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
								
								//modified by xkf57421 for XQ[2011]_10_082 begin
								// �ۿ�ʧ��ת���쳣����
								//if (payReturnStr.substring(0,2) == "ER")
								posResCode = payReturnStr.substring(0,2);
								document.getElementById('posResCode').value = posResCode;
								if (payReturnStr.substring(0,2) != "00")
								{
									setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
									return;
								}
								//modified by xkf57421 for XQ[2011]_10_082 end
								
								// �ۿ�����ȡֵ
								var resultstr = payReturnStr.substring(0,34);
								var printcontext = payReturnStr.substring(34,payReturnStr.length);
								
								document.getElementById('printcontext').value = printcontext;
								
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
									document.getElementById("tMoney").value = printcontexts[9];
									
									//������־
									var url1 = "${sessionScope.basePath }charge/updateCardChargeLog.action";
					
									var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value + "&unionpayuser=" + printcontexts[0];
									
									<%-- modify by sWX219697 2015-7-20 busitype��ΪbusiType findbugs�޸� --%>
									params1 = params1 + "&unionpaycode=" + printcontexts[1] + "&busiType=" + encodeURI(encodeURI(printcontexts[2]));
									params1 = params1 + "&batchnum=" + printcontexts[4] + "&authorizationcode=" + printcontexts[5];
									params1 = params1 + "&businessreferencenum=" + printcontexts[6] + "&unionpaytime=" + printcontexts[7];
									params1 = params1 + "&vouchernum=" + printcontexts[8] + "&unionpayfee=" + printcontexts[9];
									params1 = params1 + "&status=01&description=" + encodeURI(encodeURI('�ۿ�ɹ�')) + "&initPosResCode=" + document.getElementById('posResCode').value;
									
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
		</script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">		
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCARD %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="">
			<input type="hidden" id="printcontext" name="printcontext" value=""><!-- ������ӡ��Ϣ -->
			<!-- add by xkf57421 for XQ[2011]_10_082 begin-->
			<input type="hidden" id="posResCode" name="posResCode" value=''>
			<!-- add by xkf57421 for XQ[2011]_10_082 end-->
			
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
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> Ԫ</p>
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
