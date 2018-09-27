<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
            
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
            
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='tMoney' />";
		
		var limitTime = 30;//ȡ��ʱ��30��
		
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
			// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�	
		}
		
		// ��ȡ������ȡ��״̬
		function takeOutBankCardStatus() 
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
					"����û��Ƿ���ȡ��");
			
			limitTime = limitTime - 1;
			
			if (limitTime <= 0)
			{
				//��ʱ�������ʱ����ͬʱ�̿�
				clearInterval(waitingToken);
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"�û��ڹ涨ʱ����δȡ�����̿�");
				
				captureUserCard();
				
				return;
			}
			
			try 
			{
				//������
				var ret = TakeOutUserCardStatus();//��ȡ������ȡ��״̬
				
				if (ret == "0" || ret == 0) 
				{	
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"�û���ȡ��");
					
					//�Ѿ�ȡ�߿��������ʱ����
					clearInterval(waitingToken);									
				}			
			}
			catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
		
		function startClock()
		{
			try 
			{
				waitingToken = setInterval("takeOutBankCardStatus()", 1000);
			}
			catch (e) {}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
		
		function doFinish()
		{
			if (needReturnCard == "1")
			{
				// ��ʼ��
				var ret = InitReadUserCard();
				
				//������
				ret = TakeOutUserCard();
				
				//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
				if (ret == "0")
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"�¿��ɹ�");
							
					if ("1" == "<%=needCaptureCard %>")
					{
						startClock();
					}
				}
				else if (ret != "0")
				{
					//�¿��쳣
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"�¿�ʧ��");
				}
			}			
			
			//��ӡ�ɷ�ʧ�ܵ�ƾ֤
			if ("<%=canPrintReceipt %>" == "1")
			{
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				var pDealNum = "<%=pDealNum%>";     //������ˮ��
				var pDealTime = "<%=pDealTime%>";   //����ʱ��
				var pAmount = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pDealStatus = "���ѽ���ʧ��"; //����״̬
				
				<%--add begin g00140516 2013/02/02 R003C13L01n01 ���������ѣ����ȷ��ҳ�棬�û��������˳� --%>
				// quitFlag=1ʱ�����û������˳�����
				if ("<s:property value='quitFlag' />" == "1")
				{
					pAmount = "�����˳����ѽ���";
				}
				<%--add end g00140516 2013/02/02 R003C13L01n01 ���������ѣ����ȷ��ҳ�棬�û��������˳� --%>
				
				var pTerminalSeq = "<s:property value='terminalSeq' />";
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"��ӡ����ʧ��ƾ��");

				// ���ô�ӡ
				doPrintPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
						pDealStatus, pTerminalSeq, "", "0");
						
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />�˳���ֵ���ѹ���");
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
					
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
    					<p class="recharge_tips">����������������ҵ������������<br />ע��ȡ����������</p>
    					<a href="javascript:void(0)">6. �˶���Ϣ</a>
    					<a href="javascript:void(0)" class="on">7. ���</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
        					<!-- add begin m00227318 2013/02/02 R003C13L01n01 �޸Ĵ���ҳ����ʽ -->
      						<div class="casherror_1">
      						<!-- add begin m00227318 2013/02/02 R003C13L01n01 �޸Ĵ���ҳ����ʽ -->
      							<s:property value='errormessage' />
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
