<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// �������ɷѶ����ȴ�ʱ��(��)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME);

// �Ƿ�������������� (0�����������   1����Ϊ�����)
int pwdBz = PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ") == null ? 0 : Integer.parseInt((String) PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ"));

// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>����ҳ��</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<%-- �ֻ����� --%>
			<s:hidden id="servnumber" name="chargeLogVO.servnumber" ></s:hidden>
			
			<%-- ʡ�ݱ��� --%>
			<s:hidden id="telProvinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<%-- �ֻ������������--%>
			<s:hidden id="servRegion" name="chargeLogVO.servRegion"></s:hidden>
			
			<%-- ��Ҫ�ۿ��� --%>
			<s:hidden id="payAmount" name="payAmount"></s:hidden>
			
			<%-- �Ƿ���Ҫ�˿�0������Ҫ 1����Ҫ--%>
			<s:hidden id="needReturnCard" name="needReturnCard" value='0'></s:hidden>
			
			<%-- ������Ϣ --%>
			<s:hidden id="errormessage" name="errormessage"></s:hidden>
			
			<%-- ֧����ʽ 1:������ 4���ֽ�--%>
			<s:hidden id="payType" name="chargeLogVO.payType" value="1"></s:hidden>
			
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
    					<a href="javascript:void(0)" class="on">5. ����������</a>
    					<p class="recharge_tips">����������������ҵ�������������<br />ע��ȡ����������</p>
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)">7. �˶���Ϣ</a>
    					<a href="javascript:void(0)">8. ���</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>�����������������<span class="yellow">ҵ������������˿�����ע��ȡ����</span></p>
        					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
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
		<script type="text/javascript">
		var pwdBz = <%=pwdBz %>;
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
		
		// �ȴ���ȡ��־��1���ɹ���0��ʧ�ܡ����Ϊ1ʱ���û�����ȡ���ɷѲ����������ȡ��ˢ���ӿ�
		var readingCard = 0;
		
		clearTimeout(timeOut);
        bodyLoad();
        
        // ҳ�����ʱִ��
		function bodyLoad() 
		{
			try 
			{
				// ��ʼ��������	
             	var ret = InitReadUserCard();
				
				// ׼��ˢ��
				ret = ReadingUserCard();
				
				if (ret != "0" && ret != 0) 
				{
					writeDetailLog("<%=chargeLogDetail %>", "׼��ˢ��ʧ��");
					
					setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
                    return;
				}
				else
				{
					writeDetailLog("<%=chargeLogDetail %>", "׼��ˢ���ɹ�");
					
					// �ȴ�����
					readingCard = 1;
					
					// �����˵������á�
	   				closeStatus = 1;
                	
                	// ��ʼ���ɹ�������Ҫ�ر�ʶ������������ҳ��������һҳ�������˳�����ť�޷�ִ�д˲��������Խ���������ť
                	document.getElementById("homeBtn").disabled = true;
		            document.getElementById("backBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
							
					startclock();
				}
			}
			catch (ex) 
			{
				setException("�����豸�ؼ������ȴ������߳�ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
                return;
			}
		}
		
		// ����ʱ���������
		function startclock() 
		{	
			try 
			{
				waitingToken = setInterval("getReadCardStatus()", 1000);				
			}
			catch (e) 
			{
				setException("����������ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
			}
		}
		
		// ��ö�����־
		function getReadCardStatus() 
		{
			writeDetailLog("<%=chargeLogDetail %>", "�����С�����");
		
			//����ʱ�����
			if (limitTime <= 0 && submitFlag == 0)
			{           	
       			writeDetailLog("<%=chargeLogDetail %>", "������ʱ");
       			
       			setException("������ʱ���޷����н��Ѳ���");
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
				}
				// �����ɹ�
				else if (ret == "0" || ret == 0)
				{
					writeDetailLog("<%=chargeLogDetail %>", "�����ɹ�");
					
		   	 		// ��ȡ���
					readingCard = 0;
			
					// ��Ҫ�˿�
					document.getElementById("needReturnCard").value = "1";
					
					doSub();									
				} 
				// ����ʧ��
				else if (ret == "-1")
				{
					writeDetailLog("<%=chargeLogDetail %>", 
							"����ʧ��");
					
					readingCard = 0;//��ȡʧ��

					// ��Ҫ�˿�
					document.getElementById("needReturnCard").value = "1";	
					
					setException("����������ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
				}				
			}
			catch (e) 
			{
				writeDetailLog("<%=chargeLogDetail %>", "�����쳣");
				
				readingCard = 0;//��ȡ�����쳣
				
				setException("����������ʧ�ܣ��޷�ʹ�����������г�ֵ����ѡ��������ʽ��");
			}
		}
		
		// ������ɺ��ύת����������������ҳ��
		function doSub()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				if (pwdBz == 0)// �Ƿ�������������� (0�����������   1����Ϊ�����)
				{
					writeDetailLog("<%=chargeLogDetail %>", "ת���ɷ�ȷ��ҳ��");
				}
				else
				{
					writeDetailLog("<%=chargeLogDetail %>", "ת����������������ҳ��");
				}
			
				//�����ʱ����
				clearInterval(waitingToken);
				
				//ִ���ύ
				document.readCardForm.target = "_self";
				if (pwdBz == 0)// �Ƿ�������������� (0�����������   1����Ϊ�����)
				{
					document.readCardForm.action = "${sessionScope.basePath }nonlocalCharge/toMakeSure.action";
				}
				else
				{
					document.readCardForm.action = "${sessionScope.basePath }nonlocalCharge/inputCardPwd.action";
				}
				document.readCardForm.submit();	
			}			
		}
        
        //�����쳣
		function setException(errorMsg) 
		{			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>", "�����������쳣��" + errorMsg);
				
				//�����ʱ����
				clearInterval(waitingToken);
				
				//��ʾ������Ϣ
				document.getElementById("errormessage").value = errorMsg;
				
				//�ȴ������߳������ɹ��������쳣����ر�			
				if (readingCard == 1)
				{
					writeDetailLog("<%=chargeLogDetail %>", "ȡ��ˢ��");
						
					CancelReadingUserCard();
				}
				
				//�쳣������ֻҪ�������쳣��Ҫ��¼��־  
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }nonlocalCharge/goCardError.action";
				document.readCardForm.submit();
			}			
		} 
		</script>
	</body>
</html>