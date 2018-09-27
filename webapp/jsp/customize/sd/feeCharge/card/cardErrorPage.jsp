<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		var tMoney = "<s:property value='tMoney' />";
		
		var payType = "<s:property value='payType' />";
		
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
    					<s:if test="1 == morePhoneFlag">
    					    <a href="#">1. �����ֻ�����</a> 
	                        <a href="#">2. ѡ��֧����ʽ</a> 
	                        <a href="javascript:void(0)">3. ��������</a>
	                        <a href="javascript:void(0)">4. ����������</a>
	                        <a href="javascript:void(0)">5. ��������</a>
	                        <a href="javascript:void(0)" class="on">6. ���</a>
    					</s:if>
    					<s:else>
    					    <a href="javascript:void(0)">1. �����ֻ�����</a> 
	                        <a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
	                        <a href="javascript:void(0)">3. ѡ����</a> 
	                        <a href="javascript:void(0)">4. ��������</a>
	                        <a href="javascript:void(0)">5. ���봢�</a>
	                        <p class="recharge_tips">�������Ĵ����ҵ�������������<br />ע��ȡ�ش����</p>
	                        <a href="javascript:void(0)">6. ��������</a>
	                        <a href="javascript:void(0)" class="on">7. ���</a>
    					</s:else>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<s:property value="errormessage" />      							
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
<script type="text/javascript">
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
        var ret = getCardPosition();
        
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
    if (payType == "<%=Constants.PAY_BYCARD %>")
    {
        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
        var ret = OpenComByCard();
        
        ret = getCardPosition();
        
        //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
        if (ret == "2" || ret == 2)
        {
            startClock();
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
	    if ("1" == "<s:property value='morePhoneFlag'/>")
	    {
	        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
	        var ret = getCardPosition();
	        
	        //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
	        if (ret == "2")
	        {
	            startClock();
	        }
	        
	        // ƾ�����Ϊ�Ѵ�ӡ
	        document.getElementById("printPayProFlag").value = "1";
	        
	        // �ֻ�����
	        var pServNumber = "";
	        
	        // �ն˽�����ˮ
	        var pTerminalSeq = "";
	        
	        // ������ˮ
	        var pDealNum = "";
	        
	        // �ͻ�����
	        var pCustName = "";
	        
	        // ����ҵ��ɷѽ��
	        var tMoney = "";
	        
	        var pStatus = "";
	        <s:iterator value="morePhones" id="morePhone">
	             pServNumber = pServNumber + "<s:property value='#morePhone.servnumber' />" + ",";
	             pTerminalSeq = pTerminalSeq + "<s:property value='#morePhone.chargeLogOID' />" + ",";
	             pDealNum = pDealNum + "<s:property value='#morePhone.dealnum'/>" + ",";
	             pCustName = pCustName + "<s:property value='#morePhone.custName'/>" + ",";
	             tMoney = tMoney + "<s:property value='#morePhone.tMoney'/>" + ",";
	             pStatus = pStatus + "<s:property value='#morePhone.chargeStatus'/>" + ",";
	        </s:iterator>
	        var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
	        var pPrintDate = getDateTime();  //��ӡ����
	        var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
	        var pDealTime = "<s:property value='pDealTime' />";   //����ʱ��
	        var totalFee = "<s:property value='totalFee' />Ԫ";     //���׽��
	        
	        var printcontext = "<s:property value='printcontext' />";// ����СƱ
	        
	        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	                "�Զ���ӡƾ��");
	        
	        doPrintMorePhoneSD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,totalFee,
	                     pTerminalSeq, pCustName, pStatus, printcontext);
	    }
	    else
	    {
            var pServNumber = "<s:property value='servnumber' />";
            var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
            var pPrintDate = getDateTime();  //��ӡ����
            var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
            var pDealNum = "<%=pDealNum%>";     //������ˮ��
            var pDealTime = "<%=pDealTime%>";   //����ʱ��
            var pAmount = "<s:property value='tMoney' />Ԫ";     //���׽��
            var pDealStatus = "���ѽ���ʧ��"; //����״̬
            var pTerminalSeq = "<s:property value='terminalSeq' />";

            // ���ô�ӡ
            doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
                    pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />");
                    
            // ��ӡ����СƱ
            var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';
            if (printcontext != "")
            {
                doPrintUnionBill_sd(printcontext, pTerminalSeq, pOrgName, pPrintDate);
            }
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