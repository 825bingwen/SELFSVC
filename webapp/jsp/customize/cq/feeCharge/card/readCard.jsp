<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%

// �������ɷѶ����ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_cq.js"></script>
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
			
			//����
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}		
		}
		
		function goback(menuid)
		{
			setException("�ɷѲ�����ȡ��");
		}
		
		// �����ȴ�ʱ��
		var limitTime = <%=limitTime %>;
		
		//�ȴ������ľ��
		var waitingToken;
		
		// �ύ��־
		var submitFlag = 0;
		
		// �ȴ���ȡ��־��1���ɹ���0��ʧ�ܡ����Ϊ1ʱ���û�����ȡ���ɷѲ����������ȡ��ˢ���ӿ�
		var readingCard = 0;
		
		//�����쳣
		function setException(errorMsg) 
		{			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//�����ʱ����
				clearInterval(waitingToken);
				
				//��ʾ������Ϣ
				document.getElementById("errormessage").value = errorMsg;
				
				//�ȴ������߳������ɹ��������쳣����ر�			
				if (readingCard == 1)
				{
					CancelReadingUserCard();
				}
				
				//�쳣������ֻҪ�������쳣��Ҫ��¼��־  
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }charge/goCardError.action";
				document.readCardForm.submit();
			}			
		}
		
		function doSub()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
			
				//�����ʱ����
				clearInterval(waitingToken);
				
				//ִ���ύ
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }charge/toMakeSure.action";
				document.readCardForm.submit();	
			}			
		}
		
		// ��ö�����־
		function getReadCardStatus() 
		{
			//����ʱ�����
			if (limitTime <= 0 && submitFlag == 0)
			{           	
       			setException("������ʱ���޷����нɷѲ���");
       			return;
			}
	
			try 
			{
				// �򿪲��Ž����û����п� ���꿨�󷵻�״̬ �������뵽��������Ҫ��needReturnCardֵΪ1�� 
				// 0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
				//var ret = ReadUserCardFinished();

				//������
				var ret = 0;
				// ��δ��ȡ������Ϣ
				if (ret == "1" || ret == 1)
				{
					// ����ʱ��һ��limitTime��
					limitTime = limitTime - 1;
			
					// �趨������ʾ
					document.getElementById("tTime").value = limitTime;
			
					// ��ʶ�ؼ�δʹ��
		   	 		closeStatus = 0;
				}
				// �����ɹ�
				else if (ret == "0" || ret == 0)
				{	
		   	 		// ��ȡ���
					readingCard = 0;
			
					// ��Ҫ�˿�
					document.getElementById("needReturnCard").value = "1";
					
					doSub();									
				} 
				// ����ʧ��
				else if (ret == "-1")
				{
					readingCard = 0;//��ȡʧ��
					
					setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
				}				
			}
			catch (e) 
			{
				readingCard = 0;//��ȡ�����쳣
				
				setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
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
				setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
			}
		}
		
		//ҳ�����ʱִ��
		function bodyLoad() 
		{
			try 
			{				
				//������
				//var ret = ReadingUserCard();// ׼��ˢ��

				// ��ʶ�ؼ�ʹ��
	   			closeStatus = 1;
				
				//������
				var ret = "0";
				if (ret != "0" && ret != 0) 
				{
					setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
                    return;
				}
				else
				{		
					//�ȴ�����
					readingCard = 1;
					
					//�ȴ��������������ɹ��󣬾���Ҫ�رոý��̡�������ҳ�������˳�����ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
                	document.getElementById("homeBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
							
					startclock();
				}
			}
			catch (ex) 
			{
				setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
                return;
			}
		}		
		</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<input type="hidden" id="cardnumber" name="cardnumber" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			
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
    					<a href="javascript:void(0)" class="on">5. ���봢�</a>
    					<p class="recharge_tips">�������Ĵ����ҵ�������������<br />ע��ȡ�ش����</p>
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)">7. ���</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>��������Ĵ����<span class="yellow">ҵ������������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
        					<div class="blank10"></div>   
       						<div class="blank20"></div>
        					<div class="blank10"></div>
       						<div class="gif_animation">
       							<img src="${sessionScope.basePath }images/gif1.gif" />
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