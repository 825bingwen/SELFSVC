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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
</head>
	<body scroll="no" onload="readLoad();">
		<form name="readCardForm" method="post" target="_self">
		<input type="hidden" id="payType" name="cardChargeLogVO.payType" value="<%=Constants.PAY_BYCARD %>">
			<s:hidden id="servnumber" name="cardChargeLogVO.servnumber"/>
			<s:hidden name="cardChargeLogVO.provinceCode"/>
			<s:hidden id="yingjiaoFee" name="yingjiaoFee"/>
			<s:hidden id="needReturnCard" name="needReturnCard" value='0'/>
			<s:hidden id="cardnumber" name="cardChargeLogVO.cardnumber"/>
			<s:hidden id="errormessage" name="errormessage"/>
			
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
    					<p class="recharge_tips">�������Ĵ����ҵ������������<br />ע��ȡ�ش����</p>
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)">7. �˶���Ϣ</a>
    					<a href="javascript:void(0)">8. ���</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>��������Ĵ����<span class="yellow">ҵ�����������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text"  id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
        					<div class="blank10"></div>   
       						<div class="blank20"></div>
        					<div class="blank10"></div>
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
		var closeStatus = 1;
		
		//82��220 ����
		document.onkeydown = pwdKeyboardDown;
		
		//���̰���
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
		
		//ֻ������������
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
		
		// �����쳣
		function setException(errorMsg) 
		{			
			if (submitFlag == 0)
			{
			    // �ύ��־��Ϊ1
				submitFlag = 1;
				
				// �����ʱ����
				clearInterval(waitingToken);
				
				// ��ʾ������Ϣ
				document.getElementById("errormessage").value = errorMsg;
				
				//�ȴ������߳������ɹ��������쳣����ر�			
				if (readingCard == 1)
				{
					CancelReadingUserCard();
				}
				
				//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }nonlocalChargeHUB/goCardError.action";
				document.readCardForm.submit();
			}			
		}
		
		// ������ɺ��ύת����������ҳ��
		function doSub()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
			
				//�����ʱ����
				clearInterval(waitingToken);
				
				// ִ���ύ
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }nonlocalChargeHUB/inputCardPwd.action";
				document.readCardForm.submit();	
			}			
		}
		
		// ��ö�����־
		function getReadCardStatus() 
		{
			//����ʱ�����
			if (limitTime <= 1 && submitFlag == 0)
			{           	
		       	setException("������ʱ���޷����нɷѲ���");
		       	return;
			}
			
			try 
			{
				// �򿪲��Ž����û����п� ���꿨�󷵻�״̬ �������뵽��������Ҫ��needReturnCardֵΪ1�� 
				// 0 �����ɹ���-1 ����ʧ�ܣ�1 ��δ��ȡ������Ϣ
				var ret = ReadUserCardFinished();
		
				// ��δ��ȡ������Ϣ
				if (ret == "1" || ret == 1)
				{
					// ����ʱ��һ��limitTime��
					limitTime = limitTime - 1;
					
					// �趨������ʾ
					document.getElementById("tTime").value = limitTime;
					
					// ��ʶ�ؼ�δʹ��
				    //closeStatus = 0;
				}
				// �����ɹ�
				else if (ret == "0" || ret == 0)
				{	
				    // ��ȡ���
					readingCard = 0;
					
					// ��Ҫ�˿�
					document.getElementById("needReturnCard").value = "1";
					
					// �ڽ��յ�ReadBankCardFinished()�¼������ɹ���֪ͨ��Ӧ��ʱ���ô˽ӿڣ������浽ҳ���Ԫ���У���ȷ��������ҳ���ܻ�ȡ�����п�����Ϣ��
					var cardNO = GetUserCardInfo();
					
					if (cardNO == "")
					{
						//��ȡ����Ϣʧ��
						setException("��ȡ����Ϣʧ�ܣ��޷�ʹ�ô�����г�ֵ����ȡ�����Ĵ����");
					}
					else
					{
						// ��¼����
						document.getElementById("cardnumber").value = cardNO;
						
						// �ύת����������ҳ��
						doSub();
					}									
				} 
				// ����ʧ��
				else if (ret == "-1")
				{
					//��ȡʧ��
					readingCard = 0;
					
					//change by LiFeng �޸Ķ���ʧ�ܲ��˿�����
					// ��Ҫ�˿�
					document.getElementById("needReturnCard").value = "1";
					
					setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
				}				
			}
			catch (e) 
			{
				readingCard = 0;//��ȡ�����쳣
				
				setException("����������ʧ�ܣ��޷�ʹ�ô�����г�ֵ����ѡ��������ʽ��");
			}
		}
		
		/**
		 * ������Ӳ����ʼ��
		 * ��ʼ���ɹ������ö�ʱ����
		 */
		function readLoad()
		{
			//��ʼ������������
			var initMsg = readCardInit();
			
			if("success" == initMsg)
			{
				//�ȴ�����
				readingCard = 1;
				
				//���ö�ʱ����
				waitingToken = setInterval("getReadCardStatus()", 1000);
			}
			else
			{
				setException(initMsg);
			}
			
		}
	</script>
</html>
