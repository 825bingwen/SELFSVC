<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	//modify  by  lWX162765 for R20130117009  begin
	NserCustomerSimp ncs = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	String uBrand = ncs.getBrandName();
	//modify by lWX162765  for R20130117009  end
            
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
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/UnionCard_PayFlow_Hub.js"></script>
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
			if (needReturnCard == "1")
			{			
				// ��ʶ�ؼ�ʹ��
			    closeStatus = 1;
			    
				//������
				var ret = TakeOutUserCard();
				
				// ��ʶ�ؼ�δʹ��
				if (ret == "0")
				{
		        	closeStatus = 0;
		        }
				
				//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
				if (ret == "0" && "1" == "<%=needCaptureCard %>")
				{
					startClock();
				} 
				else if (ret != "0")
				{
					//�¿��쳣
				}
			}
			//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
			if (tMoney == null || tMoney == "" || tMoney == "null" || parseInt(tMoney) <= 0)
			{
				return;
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
				//modify begin by wWX191797 at 20140403 for [OR_HUB_201401_880] �����ն˷�Ʊ��ӡ��bugת����
				//var pAmount = "<s:property value='tMoney' />Ԫ";     //���׽��
				var pAmount = "<s:property value='yingjiaoFee' />Ԫ";     //���׽��
				//modify end by wWX191797 at 20140403 for [OR_HUB_201401_880] �����ն˷�Ʊ��ӡ��bugת����
				var pDealStatus = "�ɷѽ���ʧ��"; //����״̬
				var pTerminalSeq = "<s:property value='terminalSeq' />";
				var brand = "<%=uBrand%>"; //Ʒ��
				
				//���ͽ�� Ԫ
				//add by sWX219697 2015-1-4 11:55:20 OR_HUB_201412_399_HUB_�����ն˿�չ������С������Ӫ���
				var presentFee = "0";
				
				// ��ӡ�ɷ�СƱ
				doPrintPayProofhub(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
						pDealStatus, pTerminalSeq,brand,"", "0", presentFee);
				
				// ��ӡ����СƱ
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
						
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
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
    					<a href="javascript:void(0)">5. ���봢�</a>
    					<p class="recharge_tips">�������Ĵ����ҵ������������<br />ע��ȡ�ش����</p>
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)" class="on">7. ���</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©�������� --%>	
								<%=errorMessage %>      							
								<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©�������� --%>    							
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
