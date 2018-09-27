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
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayPrinter.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js?ver=${jsVersion }"></script>
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
						<a href="#">1. �����ֻ�����</a> 
						<a href="#">2. ѡ��֧����ʽ</a>  
						<a href="#">3. ֧��</a>
      					<a href="#" class="on">4. ���</a>
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
// ��ֹҳ���ظ��ύ
var submitFlag = 0;

// ȡ��ʱ��30��
var limitTime = 30;

// �ɷѽ��
var tMoney = "<s:property value='chargeLogVO.tMoney' />";

// ֧����ʽ
var payType = "<s:property value='chargeLogVO.payType'/>";


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
    if (payType == "<%=Constants.PAYBYUNIONCARD %>")
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
    
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
               "<s:property value='servNumber' />����ʧ��");
   
   //����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
   if (null == tMoney || "" == tMoney || "null" == tMoney || parseInt(tMoney) <= 0)
   {
       return;
   }
   
   writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
               "��ӡʧ��ƾ֤");      
   
   //��ӡ�ɷ�ʧ�ܵ�ƾ֤
   if ("<s:property value='canNotPrint'/>" == "0")
   {
       var pServNumber = "<s:property value='servNumber' />";
       var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
       var pPrintDate = getDateTime();  //��ӡ����
       var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
       var pDealNum = "<s:property value='chargeLogVO.dealnum' />";     //������ˮ��
       var pDealTime = "<s:property value='chargeLogVO.dealTime' />";   //����ʱ��
       var pAmount = "<s:property value='chargeLogVO.tMoney' />Ԫ";     //���׽��
       var pDealStatus = "���ѽ���ʧ��"; //����״̬
       var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";
       
       var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';// ����СƱ
       
       // ���ô�ӡ
       doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
                pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />", printcontext);
    }
}

// ������һҳ
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
            
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/inputNumber.action";
        document.forms[0].submit();
    }
}
</script>