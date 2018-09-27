<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
    //BOSS ������ˮ
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
    //BOSS ����ʱ��
    // modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	String pDealTime = (String) request.getAttribute("pDealTime");
	// modify end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	if (pDealTime == null)
	{
		pDealTime = "";
	}
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
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_agent_sd.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var limitTime = 30;//ȡ��ʱ��30��
	
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
			}			
		}
		
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].action = "${sessionScope.basePath }agentCharge/agentChargePage.action";
					document.forms[0].submit();
				}
			}		
			
			// ��ȡ������ȡ��״̬
			function takeOutBankCardStatus() 
			{
				limitTime = limitTime - 1;
				
				if (limitTime <= 0)
				{
					//��ʱ�������ʱ����ͬʱ�̿�
					clearInterval(waitingToken);
					
					//�̿�
					CaptureBankCard();
					
					return;
				}
				
				try 
				{
					//������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
					var ret = OpenComByCard();
				
					ret = getCardPosition();
					
					//������
					if (ret == "1" || ret == 1) 
					{	
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
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />���ѳɹ�");
				
					//������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
					var ret = getCardPosition();
					
					//�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
					if (ret == "2")
					{
						startClock();
					}
				
				<%-- modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371--%>
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				var pDealNum = "<%=pDealNum%>";     //������ˮ��
				var pDealTime = "<%=pDealTime%>";   //����ʱ��
				var tMoney = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pDealStatus = "���ѳɹ�"; //����״̬
				var pTerminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
				var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';// ����СƱ
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"�Զ���ӡƾ��");
				
				//��ӡƾ��
				doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
							pDealStatus, pTerminalSeq, "", "0", "<s:property value='agentName' />", printcontext);
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>"/>
			<input type="hidden" id="agentAccount" name="agentAccount" value="<s:property value='agentAccount' />"/>
			<input type="hidden" id="agentName" name="agentName" value="<s:property value='agentName' />"/>
			
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>�����̽������̣�</h2>
      					<div class="blank10"></div>
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ��֧����ʽ</a> 
      					<a href="#">3. ѡ����</a> 
      					<a href="#">4. ֧��</a>
          				<a href="#" class="on">5. ���</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ʽ��˻���<span class="yellow"><s:property value='agentAccount' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
          						<p class="desc pt20">�뱣������ĵĽ���ƾ����</p>          						
          						<div class="btn_box2 clearfix" style="padding-left:240px;">          						         						
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ����</a>
        							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
	//������ֵ�ɷ�
	function continueCharge()
	{	
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		document.payMoneyForm.submit();
	}
	</script>
</html>
