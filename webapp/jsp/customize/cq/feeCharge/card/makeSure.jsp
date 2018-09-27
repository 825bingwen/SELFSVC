<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_cq.js"></script>
		<script language="javascript">
			// �ύ��־ 0:δ�ύ 1:���ύ
			var submitFlag = 0;
			
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
			setException("�ɷѲ�����ȡ������ע��ȡ�����Ĵ����");
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
			if (submitFlag == 0) 
			{
				submitFlag = 1;	//�ύ���
				
				//�����ʱ����
				clearInterval(timeToken);
				
				document.getElementById("errormessage").value = errorMsg;
					
				//�쳣������ֻҪ�������쳣��Ҫ��¼��־  
				document.actForm.target = "_self";
				document.actForm.action = "${sessionScope.basePath }charge/goCardError.action";
				document.actForm.submit();
			}			
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
			document.actForm.action = "${sessionScope.basePath }charge/cardChargeCommit.action";
			document.actForm.submit();			
		}
		
		//�������ۿ�
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				//�ۿ�֮ǰ��¼��־
				var url = "${sessionScope.basePath }charge/addChargeLog.action";
				
				var params = "servnumber=" + <s:property value='servnumber' /> + "&paytype=1&tMoney=" + <s:property value='yingjiaoFee' />;
				params = params	+ "&status=00&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־')) + "&acceptType=" + document.getElementById("acceptType").value;
				params = params	+ "&servRegion=" + document.getElementById("servRegion").value;
				
				var loader = new net.ContentLoader(url, netload = function () {
					var resXml = this.req.responseText;
					var dataArray = resXml.split("~~");
					//��¼��־�ɹ�
					if (dataArray[0] == "1") 
					{
						//���ν��Ѷ�Ӧ����־�Ѿ����ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
						document.getElementById("errorType").value = "update";
							
						document.getElementById("chargeLogOID").value = dataArray[1];							
										
						try
						{
	    					//������
							//var ret = CloseReadingCardFixing();// �رն�����������������д򿪵��߳�
							
							//������
							var ret = 0;
							if (ret != 0)
							{
								setException("�رն�����������������д��߳�ʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("�رն�����������������д��߳�ʧ��");
									
							return;
						}
							
						// �����������Ϊ�������ģʽ
						try
						{
							//������
							//var ret = SetWorkMode(1);// �����������Ϊ�������ģʽ
								
							//������
							var ret = 1;
							if (ret == -1)
							{
								setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("�����ۿ�֮ǰ����������̵Ĺ���ģʽʧ��");
								
							return;
						}							
							
						// �����������ۿ�����	
						var continueFlag = false;
						var resultstr;
						var printcontext;
						try
						{
							//���������� ����41λ ����Ա��+�̻�����+�ֻ���+�������ͱ�־+���
							//var strin = formatStr(termid,'right',' ',10) + unitType + formatStr('<s:property value="servnumber" />','right',' ',18) + businesstype + formatStr('<%=fenMoney %>','left','0',12);
																
							//������		
						    			
							//window.parent.document.getElementById("unionpluginid").cardPay(strin);
							
							//resultstr = window.parent.document.getElementById("unionpluginid").resultstr;
							//printcontext = window.parent.document.getElementById("unionpluginid").printcontext;
							//continueFlag = true;
							
														
							//������	
							resultstr = '00622202160200472407815864500526       A000000001000';
							printcontext = '302370150210713~123456789014145~���ѽ���~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000001200';
							continueFlag = true;	
						}
						catch (e){}
							
						// �����������Ϊ��ͨ����ģʽ
						try
						{
							//������
							//var ret = SetWorkMode(0);// �����������Ϊ��ͨ����ģʽ
								
							//������
							var ret = 1;
							if (ret == -1)
							{
								setException("�����ۿ������������̵Ĺ���ģʽʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("�����ۿ������������̵Ĺ���ģʽʧ��");
									
							return;
						}
						if (!continueFlag)
						{
							setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ�����Ĵ����");
								
							return;
						}
						// �ۿ�ɹ� ����52 �ɹ�00
						if (resultstr.substring(0,2) == "00" && resultstr.length == 52)
						{									
							var printcontexts = printcontext.split('~');							
							
							document.getElementById("terminalSeq").value = printcontexts[1] + printcontexts[8];
							document.getElementById("tMoney").value = printcontexts[9];
									
							//������־
							var url1 = "${sessionScope.basePath }charge/updateCardChargeLog.action";
					
							var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value + "&unionpayuser=" + printcontexts[0];
							params1 = params1 + "&unionpaycode=" + printcontexts[1] + "&busitype=" + encodeURI(encodeURI(printcontexts[2]));
							params1 = params1 + "&cardnumber=" + printcontexts[3] + "&posnum=&batchnum=" + printcontexts[4];
							params1 = params1 + "&authorizationcode=" + printcontexts[5];
							params1 = params1 + "&businessreferencenum=" + printcontexts[6] + "&unionpaytime=" + printcontexts[7];
							params1 = params1 + "&vouchernum=" + printcontexts[8] + "&unionpayfee=" + printcontexts[9];
							params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value + "&status=01&description=" + encodeURI(encodeURI('�ۿ�ɹ�'));
									
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
									setException("�����ۿ�ɹ������Ǹ�����־ʧ�ܣ����ܽ��г�ֵ����ȡ�����Ĵ����");
									
									return;
								}								
							}, null, "POST", params1, null);
						}
						//�ۿ�ʧ��
						else
						{
							setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ�����Ĵ����");
									
							return;
						}				
					}
					//��¼��־ʧ��
					else 
					{						
						setException("�ۿ�֮ǰ��¼�û���Ϣʧ�ܣ����ܼ������г�ֵ��������ȡ�����Ĵ����");
					}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
				{
					setException("�������ۿ�ɹ������ǽ���ʧ�ܡ���ȡ�����Ĵ����");
				}
				else
				{
					setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ�����Ĵ����");
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
				
				setException("�˶���Ϣ��ʱ����ȡ�����Ĵ����");
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
				
				setException("�˶���Ϣʧ�ܣ���ȡ�����Ĵ����");
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
		<form name="actForm">		
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
    					<a href="javascript:void(0)">5. ���봢�</a> 
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
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" /></p>
                  	<p class="tips_txt">���ڴ��������Ժ�......</p>
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