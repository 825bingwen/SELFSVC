<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	//�̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
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
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
	</head>
        <body onload="window.focus();doFinish();" scroll="no">
	
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
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
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='cardChargeLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       							<div class="recharge_result tc">
       							<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
       							<s:if test="cardChargeLogVO.payType == 'PAYTYPE_CARD'">
          							<p class="desc pt20">�뱣������ĵĽ���ƾ������������</p>
          						</s:if>
          						<s:else>
          							<p class="desc pt20">�뱣������ĵĽ���ƾ����</p>
          						</s:else>
          						<div class="btn_box2 clearfix">
          					    <a href="javascript:void(0);" style="margin-left:80px;" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ����</a>         							
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
	
	var submitFlag = 0;
	
	var payType = "<s:property value='cardChargeLogVO.payType' />";
	var needReturnCard = "<s:property value='needReturnCard' />";
	
	var limitTime = 30;//ȡ��ʱ��30��	
	
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
		}			
	}
	
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			document.getElementById("curMenuId").value = menuid;
			
			document.forms[0].action = "${sessionScope.basePath }nonlocalChargeHUB/feeCharge.action";
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
			
			captureUserCard();
			
			return;
		}
		
		try 
		{
			//������
			var ret = TakeOutUserCardStatus();//��ȡ������ȡ��״̬
			
			//������
			//var ret = 0;
			if (ret == "0" || ret == 0) 
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
		if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
		{
			// �˿�
			var ret = TakeOutUserCard();

			// �¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
			if (ret == "0" && "1" == "<%=needCaptureCard %>")
			{
				startClock();
			}
			else if (ret != "0")
			{
				//�¿��쳣
			}
		}
		
		var pServNumber = "<s:property value='cardChargeLogVO.servnumber' />";
		var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
		var pPrintDate = getDateTime();  //��ӡ����
		var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
		var pDealNum = "<s:property value='cardChargeLogVO.dealnum'/>";//������ˮ��
		var pDealTime = getDateTime();   //����ʱ��
		var tMoney = "<s:property value='tMoney' />Ԫ";//���׽��
		var pDealStatus = "�ɷѳɹ�"; //����״̬
		var pTerminalSeq = "<s:property value='cardChargeLogVO.terminalSeq' />";//�ն���ˮ
		var brand = ""; //Ʒ��
		var presentFee = "";//���ͽ��
		
		//��ӡƾ֤
		if ("<%=canPrintReceipt %>" == "1")
		{
			// �ɷ�СƱ
			doPrintPayProofhub(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
					pDealStatus, pTerminalSeq,brand,"", "0", presentFee);
			// ��ӡ����СƱ
			if (payType == "<%=Constants.PAY_BYCARD %>")
			{
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
			}
		}
	}
	</script>
</html>
