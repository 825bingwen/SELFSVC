<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>

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
	<body onload="window.focus();onload()" scroll="no">
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='cardPayLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='cardPayLogVO.tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">������ĵ����мۿ��ѳ�Ʊ�ɹ���</p>
       							<p class="desc pt20">�뱣������ĵĽ���ƾ����</p>
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
var submitFlag = 0;

var limitTime = 30;//ȡ��ʱ��30��

// ƾ����ӡ��־
var printFlag = 0;

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
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
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

function onload()
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='servNumber' />���ѳɹ�");
    
    if ("<s:property value='payType' />" == "<%=Constants.PAYBYUNIONCARD %>")
    {
    	document.getElementById("highLight7").className = "on";
        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
        var ret = getCardPosition();
        
        //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
        if (ret == "2")
        {
            startClock();
        }
    }
    else
    {
    	document.getElementById("highLight5").className = "on";
    }
    
    var pServNumber = "<s:property value='cardPayLogVO.servnumber' />";
	var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
	var pPrintDate = getDateTime();  //��ӡ����
	var pTerminalSeq = "<s:property value='cardPayLogVO.terminalSeq' />";// �ն���ˮ
	var totalFee = "<s:property value='valueCardVO.minMoney' />"; // Ӧ�ɷ���
	var tMoney = "<s:property value='cardPayLogVO.tMoney' />"; // ʵ�ʽɷ�
	
	// ��ӡСƱ
	doPrintValueCard(pServNumber, pOrgName, pPrintDate, totalFee, tMoney);
	
	if ("<s:property value='payType' />" == "1")
	{
		var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
		if (printcontext != "")
		{
			doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
		}
	}
}

// ���ѳɹ����мۿ�����ɹ�ʱ��ӡСƱ
function doPrintValueCard(pServNumber,pOrgName,pPrintDate,totalFee,tMoney) 
{
  try {
  	var ret;
  	try{
  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	}
  	catch(e)
  	{
		alertError("����:��ӡ���ؼ�δ��װ!");
		return;
  	}
  	if (ret == 1)
  	{
  	   alertError("����:��ӡ��ȱֽ�����!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("����:��ӡ���豸�Ͳ���������δ��װ!");
  	   return;
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �����ն�ƽ̨�����мۿ�����ƾ��");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  �ֻ�����: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  Ӧ�շ���: "+totalFee+"Ԫ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ʵ�ɷ���: "+tMoney+"Ԫ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("����:��ӡ��ȱֽ�����!");
        return;
    }
    	
  	<s:iterator value='cardList' >
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����: " + "<s:property value='cardNo' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����ֵ: " + "<s:property value='cntTotal' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ����Ч��: " + "<s:property value='expDate' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ҵ������: " + "<s:property value='cardType' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ҵ������ֵ: " + "<s:property value='cardBusiPro' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ƽ̨������ˮ��: " + "<s:property value='transActionId' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		
		if (ret == 1)
    	{
	        alertError("����:��ӡ��ȱֽ�����!");
	        return;
    	}
	</s:iterator>
	
    if (ret == 1)
    {
        alertError("����:��ӡ��ȱֽ�����!");
        return;
    }
  	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��������,���в���֮��,����ӪҵԱ��ѯ.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡ�ص�:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ��ӡʱ��:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("����:��ӡ��ȱֽ�����!");
  	    return;
  	}
	cutPaper();
	} catch(ex) {
 		alertError("��ӡ���ӳ�ֵ����Ϣ����,����ϵӪҵ������Ա�Ե��ӳ�ֵ������״̬����ȷ�ϣ�");
 		cutPaper();//����������ֽ
	}	
}

</script>
