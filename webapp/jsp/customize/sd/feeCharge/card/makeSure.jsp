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
		<title>���п�����ȷ��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js?ver=${jsVersion }"></script>
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
			setException("���Ѳ�����ȡ������ע��ȡ�����Ĵ����");
		}
		
		/********************RUN POS����*******************************/
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
						RUN POS����
		************************************************/
		
		//�����쳣
		function setException(errorMsg) 
		{			
			submitFlag = 1;	//�ύ���
			
			//�����ʱ����
			clearInterval(timeToken);
			
			document.getElementById("errormessage").value = errorMsg;
				
			//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }charge/goCardError.action";
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
			document.actForm.action = "${sessionScope.basePath }charge/cardChargeCommit.action";
			document.actForm.submit();			
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
		
		// ���������ַ���
		var resultstr;
		
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
				params = params	+ "&servRegion=" + document.getElementById("servRegion").value+ "&chargeType=" + <s:property value='chargeType' />;
			
				var loader = new net.ContentLoader(url, netload = function () {
					var resXml = this.req.responseText;
					var dataArray = resXml.split("~~");
					//��¼��־�ɹ�
					if (dataArray[0] == "1") 
					{
						//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
						document.getElementById("errorType").value = "update";
							
						document.getElementById("chargeLogOID").value = dataArray[1];							
										
						try
						{
	    					//������
							var ret = CloseCom();// �ر�����������д򿪵��߳�
							
							if (ret != 0)
							{
								setException("�ر�����������д��߳�ʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("�ر�����������д��߳�ʧ��");
									
							return;
						}
							
						try
						{
	    					//������
							var ret = CloseComByCard();// �رն��������д򿪵��߳�
							
							if (ret != 0)
							{
								setException("�رն��������д��߳�ʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("�رն��������д��߳�ʧ��");
									
							return;
						}						
							
						// �����������ۿ�����	
						var continueFlag = false;
						var printcontext;
						try
						{
							// ���������� 
							// ���״���(2)+���׽��(12)+POS��ˮ��(6)+��������(10)+����Ա��(10)+���м����ο���(15)+��Ȩ�ŷ��ڸ�������(6)
							// + ԭ��������(8)+��Ƭ����(1)+�Զ�����Ϣ(76)+�ڶ��ŵ�(37)+�����ŵ�(104)+ԭ������(2)+ԭ�ն˱��(15)+ԭ��Ȩ��(6)
							var strin = '';
							strin = strin + '01';// ���״���(2)
							strin = strin + formatStr('<%=fenMoney %>','left','0',12);// ���׽��(12)
							strin = strin + formatStr('','right',' ',55);// POS��ˮ��(6)+��������(10)+����Ա��(10)+���м����ο���(15)+��Ȩ�ŷ��ڸ�������(6)+ ԭ��������(8)
							strin = strin + 'H';// ��Ƭ����(1)
							strin = strin + formatStr('','right',' ',240);// �Զ�����Ϣ(76)+�ڶ��ŵ�(37)+�����ŵ�(104)+ԭ������(2)+ԭ�ն˱��(15)+ԭ��Ȩ��(6)
												
							//������	
						    if(true) // true:���� false:����
						    {	
								resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
								continueFlag = true;
							}
							else //������	
							{
								// ������(6)+	�����뺬��(40)+POS��ˮ��(6)+��Ȩ��(6)+���κ�(6)+����(19)+��Ч��(4)+���к�(2)+���м����ο���(12)+�ն˺�(15)+�̻���(15)
								// ���׽��(12)+���ܺ������򶨵���(16)+���ڸ�����Ϣ(74)+�����д���(8)+����������������(8)+������������ʱ��(6)
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
								resultstr = resultstr + "000000000001";// ���׽��(12)
								resultstr = resultstr + "****************";//���ܺ������򶨵���(16)
								resultstr = resultstr + formatStr('','right',' ',74);// ���ڸ�����Ϣ(74)
								resultstr = resultstr + formatStr('','right',' ',8);// �����д���(8)
								resultstr = resultstr + '20120101';// ����������������(8)
								resultstr = resultstr + '101010';// ������������ʱ��(6)
							}
							
							continueFlag = true;
												
						}
						catch (e){}
						
						try
						{
	    					//������
							var ret = OpenCom();// ���������
							
							if (ret != 0)
							{
								setException("���������ʧ��");
									
								return;
							}
						}
						catch(e)
						{
							setException("���������ʧ��");
									
							return;
						}			
						
						// �����������Ϊ��ͨ����ģʽ
						try
						{
							//������
							var ret = SetWorkMode(0);// �����������Ϊ��ͨ����ģʽ
						    
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
						
						// �ۿ�ɹ� ����255 �ɹ�000000
						if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
						{									

							// ��ˮ��_���ײο���
							document.getElementById("terminalSeq").value = resultstr.substring(resultstr.length-166,resultstr.length-154);
							document.getElementById("tMoney").value = resultstr.substring(resultstr.length-124,resultstr.length-112);
									
							//������־
							var url1 = "${sessionScope.basePath}charge/updateCardChargeLog.action";
					
							var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value;
							params1 = params1 + "&unionpayuser=" + resultstr.substring(resultstr.length-139,resultstr.length-124);// �̻�����
							params1 = params1 + "&unionpaycode=" + resultstr.substring(resultstr.length-154,resultstr.length-139);// POS�����
							params1 = params1 + "&busitype=" + encodeURI(encodeURI("�ɷѽ���"));// ��������
							params1 = params1 + "&cardnumber=" + resultstr.substring(resultstr.length-191,resultstr.length-172);// ����
							params1 = params1 + "&posnum=" + resultstr.substring(resultstr.length-209,resultstr.length-203);// POS��ˮ��
							params1 = params1 + "&batchnum=" + resultstr.substring(resultstr.length-197,resultstr.length-191);// ���κ�
							params1 = params1 + "&authorizationcode=" + resultstr.substring(resultstr.length-203,resultstr.length-197);// ��Ȩ��
							params1 = params1 + "&businessreferencenum=" + resultstr.substring(resultstr.length-166,resultstr.length-154);// ���ײο���
							params1 = params1 + "&unionpaytime=" + resultstr.substring(resultstr.length-14,resultstr.length);// �ۿ�ʱ��
							params1 = params1 + "&vouchernum=";// ƾ֤��
							params1 = params1 + "&unionpayfee=" + resultstr.substring(resultstr.length-124,resultstr.length-112);// �ۿ���
							params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;
							params1 = params1 + "&status=01";
							params1 = params1 + "&description=" + encodeURI(encodeURI(resultstr.substring(resultstr.length-249,resultstr.length-209)));// �����뺬��
									
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
			<input type="hidden" id="chargeType" name="chargeType" value="<s:property value='chargeType' />">
			
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
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
                   	
                   	<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
                  	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ������Ժ�......") %></p>
                 	<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
                 	
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
