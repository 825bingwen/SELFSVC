<%--create by sWX219697 2014-6-10 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ���� --%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%

// �������ɷѶ����ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

String yuanMoney = (String) request.getAttribute("yingjiaoFee");

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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
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
		}
		
		// �����ȴ�ʱ��
		var limitTime = <%=limitTime %>;
		
		//�ȴ������ľ��
		var waitingToken;
		
		// �ύ��־
		var submitFlag = 0;
		
		//�����쳣
		function setException(errorMsg)
		{			
			//��ʾ������Ϣ
			document.getElementById("errormessage").value = errorMsg;
				
			//�����ʱ����
			clearInterval(waitingToken);
				
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
				
			//�쳣������ֻҪ�������쳣��Ҫ��¼��־
			document.readCardForm.target = "_self";
			document.readCardForm.action = "${sessionScope.basePath }agentCharge/goCardError.action";
			document.readCardForm.submit();			
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
		
		//ȡ���ַ���ͷβ�Ŀո�
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
		
		//���½�����־���ύ
		function updateAndCommit(resultstr)
		{
			// ��ˮ��_���ײο���
			document.getElementById("terminalSeq").value = trim(resultstr.substring(resultstr.length-166, resultstr.length-154));
			document.getElementById("tMoney").value = trim(resultstr.substring(resultstr.length-124, resultstr.length-112));
			document.getElementById("printcontext").value = trim(resultstr.substring(resultstr.length-209));
				
			//������־
			var url1 = "${sessionScope.basePath}agentCharge/updateCardChargeLog.action";
	
			var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value;//��־���
			params1 = params1 + "&unionpayuser=" + trim(resultstr.substring(resultstr.length-139,resultstr.length-124));// �̻�����
			params1 = params1 + "&unionpaycode=" + trim(resultstr.substring(resultstr.length-154,resultstr.length-139));// POS�����
			params1 = params1 + "&busitype=" + encodeURI(encodeURI("����"));//��������
			params1 = params1 + "&cardnumber=" + trim(resultstr.substring(resultstr.length-191,resultstr.length-172));// ����
			params1 = params1 + "&posnum=" + trim(resultstr.substring(resultstr.length-209,resultstr.length-203));// POS��ˮ��
			params1 = params1 + "&batchnum=" + trim(resultstr.substring(resultstr.length-197,resultstr.length-191));// ���κ�
			params1 = params1 + "&authorizationcode=" + trim(resultstr.substring(resultstr.length-203,resultstr.length-197));// ��Ȩ��
			params1 = params1 + "&businessreferencenum=" + trim(resultstr.substring(resultstr.length-166,resultstr.length-154));// ���ײο���
			params1 = params1 + "&unionpaytime=" + trim(resultstr.substring(resultstr.length-14,resultstr.length));// �ۿ�ʱ��
			params1 = params1 + "&vouchernum=";// ƾ֤��
			params1 = params1 + "&unionpayfee=" + trim(resultstr.substring(resultstr.length-124,resultstr.length-112));// �ۿ���
			params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;//������ˮ�ţ��ն���ˮ��
			params1 = params1 + "&status=01";//����״̬ 01��ʾ�����ۿ�ɹ�����ʱ״̬
			params1 = params1 + "&description=" + encodeURI(encodeURI("���׳ɹ�"));
					
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
				var resXml1 = this.req.responseText;
						
				//������־�ɹ�
				if (resXml1 == "1" || resXml1 == 1)
				{
					//�ύ�ɷ�����readCardForm
					document.readCardForm.target = "_self";
					document.readCardForm.action = "${sessionScope.basePath }agentCharge/cardChargeCommit.action";
					document.readCardForm.submit();										
				}
				//������־ʧ��
				else
				{										
					setException("�����ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
					
					return;
				}								
			}, null, "POST", params1, null);
		}
		
		//��������������
		function parseResult(resultstr)
		{
			try
			{
				// ���ó������⣬û��ȡ������ֵ
				if (resultstr == "")
				{
					setException("�����ۿ�ʧ�ܣ��޷���ֵ������ϵӪҵ��Աȡ��������������");
							
					return;
				}
				// �ۿ�ɹ� ����255 �ɹ�000000
				else if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
				{
					//�����ۿ�ɹ���������־���ύ
					updateAndCommit(resultstr);
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
					setException("�����ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
				}
				else
				{
					setException("�����ۿ�ʧ�ܣ��޷���ֵ����ȡ��������������");
				}
			}
		}
		
		//�����ۿ�
		function doCardTrans()
		{
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
				//102�������̽��ѣ�101�����˽���
				//���������ĸ�����Ϣ:�������ͣ�3��+������־Ψһ��ʶ��14��+�����̽���ǰ��ˮ��
				strin = strin + formatStr("102"+document.getElementById("chargeLogOID").value+document.getElementById("beforeChargeNo").value,'right',' ',100);// �ƶ��ɷѸ�����Ϣ100

				// ������	
				if (true) // true:���� false:����
				{	
					//�����ۿ�
					resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
				}
				// ������	
				else
				{
					// ������(6)+	�����뺬��(40)+POS��ˮ��(6)+��Ȩ��(6)+���κ�(6)+����(19)+��Ч��(4)+���к�(2)+���м����ο���(12)+�ն˺�(15)+�̻���(15)
					// ���׽��(12)+���ܺ������򶨵���(16)+���ڸ�����Ϣ(74)+�����д���(8)+����������������(8)+������������ʱ��(6)
					//resultstr = '000001';// ������(6)
					//resultstr = resultstr + formatStr('error', 'right', ' ', 40);// �����뺬��(40)
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
					resultstr = resultstr + "000000000100";// ���׽��(12)
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
			
			//��������������
			parseResult(resultstr);
		}
		
		//�ɷ�ǰ��ˮ��
		function getOrderNo()
		{
			//���ýɷ�ǰ��Ϣ��¼�ӿڻ�ȡ��ˮ��
			var orgId = document.readCardForm.orgId.value;
			var agentAccount = document.readCardForm.agentAccount.value;
			var yingjiaoFee = document.readCardForm.yingjiaoFee.value;
			var subjectId = document.readCardForm.subjectId.value;
			var servnumber = document.readCardForm.servnumber.value;
			var curMenuId = document.getElementById("curMenuId").value;
			var postStr ="orgId="+orgId+"&agentAccount="+agentAccount+"&yingjiaoFee="+yingjiaoFee+"&subjectId="+subjectId
							+"&servnumber="+servnumber+"&curMenuId="+curMenuId;   
			var url1 = "${sessionScope.basePath}agentCharge/beforeAgentCharge.action";
			
			//���ýɷ�ǰ��¼��Ϣ�ӿ��첽����
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
						
				var resXml1 = this.req.responseText;
				var resArray = resXml1.split('~~');
				var beforeChargeNo = resArray[1];
				
				//���ýɷ�ǰ��¼��Ϣ�ӿڳɹ�
				if (resArray[0] == 1 || resArray[0] == "1")
				{
					//���ý���ǰ��ˮ��
					document.getElementById("beforeChargeNo").value = resArray[1];
					
					//�����ۿ�
					doCardTrans();
		        }
				//��ȡ��ˮ��ʧ��
				else
				{	
					setException("����ǰ��Ϣ��¼ʧ�ܣ������ۿ�ʧ�ܣ��޷���ֵ������ϵӪҵ��Աȡ��������������");
					return;
				}								
			}, null, "POST", postStr, null);
		}
		
		//�����ʱ���񣬲��رն����豸
   	 	function pay()
		{
			//�����ʱ����
			clearInterval(waitingToken);
			
			// �رն����豸
			try
			{
			
				//ƽ̨ϵͳ��⵽�û��Ѳ���������������ô˽ӿڹرն����豸���ͷŴ��ڡ�
				var ret = CloseComByCard();
				//������
				 //var ret = 0;
				
				if (ret != 0 && ret != "0")
				{
					setException("�رն����豸ʧ�ܣ��޷�ʹ�����������г�ֵ������ϵӪҵ��Աȡ��������������");
							
					return;
				}
			}
			catch(e)
			{
				setException("�رն����豸ʧ�ܣ��޷�ʹ�����������г�ֵ������ϵӪҵ��Աȡ��������������");
							
				return;
			}
			
			//�첽��ȡ�ɷ�ǰ��ˮ��
			getOrderNo();
			
		}
		
		//-----------------------------------------ѭ������------------------------------------------------------------------------
		// ��ö�����־
		function getReadCardStatus() 
		{
			try 
			{
				// 0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
				var ret = getCardPosition();// ��ȡ��λ�ã����ж��û��Ƿ��Ѳ���������

				//������
				//var ret = 0;
				// ����ʧ��
				if (ret == "-1")
				{
					setException("����ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
				}
				// ���ڶ�������
				else if (ret == "0" || ret == 0)
				{	
		   	 		document.getElementById("flowDesc2").style.display = "block";
		   	 		document.getElementById("flowDesc1").style.display = "none";
		   	 		pay();
				}
				// ��δ��ȡ������Ϣ
				else
				{
					// ����ʱ��һ��limitTime��
					limitTime = limitTime - 1;
			
					// �趨������ʾ
					document.getElementById("tTime").value = limitTime;
					
					//����ʱ�����
					if (limitTime <= 0)
					{
		       			setException("������ʱ���޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
		       			return;
					}
				}
			}
			catch (e) 
			{
				setException("����ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
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
				setException("����ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
			}
		}
		
		//----------------------------------ҳ���ʼ��---------------------------------------------------------------------------------
		//ҳ�����ʱִ��
		function bodyLoad() 
		{
		
			// �ر��������
			try
			{
			
				//���������ۿ�ؼ�ǰ�ر��豸���ô��ڲ�������������
				var ret = CloseCom();
				
				//����
				//var ret = 0;
				if (ret != 0)
				{
					setException("�ر��������ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
				
					return;
				}
			}
			catch(e)
			{
				setException("�ر��������ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");

				return;
			}
			
			// �򿪶����豸
			try 
			{
				var ret = OpenComByCard();
				
				//����
				//var ret = 0;
				if (ret != "0" && ret != 0) 
				{
					setException("�򿪶����豸ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
                    return;
				}
				else
				{		
					//��ʼ���ɹ�������Ҫ�رն��������������а�ť������ҳ���ϲ����²���
	   				closeStatus = 1;
					
                	document.getElementById("homeBtn").disabled = true;
		            document.getElementById("backBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
							
					startclock();
				}
			}
			catch (ex) 
			{
				setException("�򿪶����豸ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
                return;
			}
		}		
		</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<!-- �ֻ����� -->
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<!-- �ֻ�������REGION -->
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />"/>
			<!-- �ɷ����� -->
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'/>
			<!-- �������� -->
			<input type="hidden" id="errorType" name="errorType" value='update'/>
			<!-- ������Ϣ -->
			<input type="hidden" id="errormessage" name="errormessage" value=''/>
			<input type="hidden" id="tMoney" name="tMoney" value=''/>
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''/>
			<!-- �ɷ�ǰ��ˮ�� -->
			<input type="hidden" id="beforeChargeNo" name="beforeChargeNo"/>
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="<s:property value='chargeLogOID' />"/>
			<input type="hidden" id="agentName" name="agentName" value="<s:property value='agentName' />"/>
			<input type="hidden" id="agentAccount" name="agentAccount" value="<s:property value='agentAccount' />"/>
			<input type="hidden" id="subjectId" name="subjectId" value="<s:property value='subjectId' />"/>
			<input type="hidden" id="orgId" name="orgId" value="<s:property value='orgId' />"/>
			<input type="hidden" id="unionRetCode" name="unionRetCode" value=''/>
			<input type="hidden" id="printcontext" name="printcontext" value=""/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 " id="flowDesc1">
						<div class="bg bg257"></div>
						<h2>�����̽�������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
    					<a href="javascript:void(0)">2. ѡ����</a> 
    					<a href="javascript:void(0)">3. ��������</a>
    					<a href="javascript:void(0)" class="on">4. ����������</a>
    					<p class="recharge_tips">����������������ҵ�������������<br />ע��ȡ����������</p>
    					<a href="javascript:void(0)">5. ��������</a>
    					<a href="javascript:void(0)">6. ���</a>
					</div>
					
					<div class="b257 " id="flowDesc2" style="display: none">
						<div class="bg bg257"></div>
						<h2>�����̽�������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
    					<a href="javascript:void(0)">2. ѡ����</a> 
    					<a href="javascript:void(0)">3. ��������</a>
    					<a href="javascript:void(0)">4. ����������</a>
    					<a href="javascript:void(0)" class="on">5. ��������</a>
    					<a href="javascript:void(0)">6. ���</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class=" tit_info"><span class="bg"></span>�ʽ��˻���<span class="yellow"><s:property value="agentAccount" /></span></p>
    						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> Ԫ</p>
    						
    						<div class="blank60"></div>
							<div class="line"></div>
      						<div class="blank25"></div>
        					<p class="tit_info">�����������������<span class="yellow">ҵ������������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow"><%=limitTime %></span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />�롣</p>        					
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
        bodyLoad();        
	</script>
</html>