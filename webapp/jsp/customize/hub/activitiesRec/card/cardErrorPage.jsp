<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
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
	
	String orgName = termInfo.getOrgname();
	
	String groupName = (String)request.getAttribute("groupName");
	String dangciName = (String)request.getAttribute("dangciName");
	String termName = (String)request.getAttribute("termName");
	
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// modify begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// modify end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
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
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='unionpayfee' />";
		
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
				//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
				var money = parseInt("<s:property value='unionpayfee' />");
				if (money <= 0)
				{
					return;
				}
				var p_activityId = "<s:property value='activityId' />";// �����
				var p_dangciId = "<s:property value='dangciId' />";// ���α���
				var p_servnumber = "<s:property value='servnumber' />";// �ֻ�����
				var p_orgName = "<%=orgName %>";  //��ӡ�ص�
				var p_date = getDateTime();  //��ӡ����
				var p_termId = "<s:property value='termid' />"; //�ն˱���
				var p_termName = "<%=termName %>"; //�ն�����
				var p_prepayFee = "<s:property value='prepayFee' />"; //������
				var p_totalfee = <s:property value='unionpayfee' /> / 100;// Ͷ�ҽ��
				var p_terminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
				var p_recoid = "<s:property value='recoid' />";// ������ˮ
				var p_successBz = "<s:property value='successBz' />";//״̬
				var yxfaFee_yuan = "<s:property value='yxfaFee_yuan' />";// Ӫ����������
				var ycFee_yuan = "<s:property value='ycFee_yuan' />";// �û�Ԥ��
				var p_dangciName = "<%=dangciName %>";// ��������
				var p_groupName = "<%=groupName %>";// �������
				var p_status = "���������ʧ��,��ƾ����ƾ����Ӫҵ�������˿"; //����״̬
				
				//��ӡƾ֤
				if ("<%=canPrintReceipt %>" == "1")
				{	
					//modify begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
					var brandName = "${sessionScope.CustomerSimpInfo.brandName}";
					
					// modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
					// �ɷ�СƱ
					doPrintByActivity(p_activityId,p_dangciId,p_servnumber,p_orgName,p_date,p_termId,p_termName,p_prepayFee,p_totalfee,p_terminalSeq,p_recoid,p_status,yxfaFee_yuan,ycFee_yuan,p_dangciName,p_groupName, '', brandName);
					// modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
					//modify end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
				}
				
				// ��ӡ����СƱ
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,p_terminalSeq,p_orgName,p_date);
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
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
			<!-- Ӫ���Ƽ���ʶ -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��������̣�</h2>
      					<div class="blank10"></div>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="����Э��" href="#">2. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
    					<a title="���봢�" href="javascript:void(0)">4. ���봢�</a>
    					<p class="recharge_tips">�������Ĵ����ҵ������������<br />ע��ȡ�ش����</p>
    					<a title="��������" href="javascript:void(0)">5. ��������</a>
    					<a title="�˶���Ϣ" href="javascript:void(0)">6. �˶���Ϣ</a>
    					<a title="���" href="javascript:void(0)" class="on">7. ���</a>
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
        						<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©�������� --%> 
      							    							
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
