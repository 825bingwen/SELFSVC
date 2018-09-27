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
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ���̣�</h2>
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
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='chargeLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='chargeLogVO.tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
          						<p class="desc pt20" id="proofInfo"></p> 
          						<div class="btn_box3 clearfix ml100">  
          						    <s:if test="canNotPrint == 0">        						         						
									   <a href="javascript:void(0);" onclick="printProof();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">��ӡƾ��</a>
          							</s:if>
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goback('<s:property value="curMenuId" />');return false;">������ֵ����</a>
          						</div>
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

var payType = "<s:property value='chargeLogVO.payType' />";

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
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/inputNumber.action";
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
    
    if (payType == "<%=Constants.PAYBYUNIONCARD %>")
    {
        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
        var ret = getCardPosition();
        
        //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
        if (ret == "2")
        {
            startClock();
        }
    }
}

function printProof()
{
    if (printFlag == 0)
    {
	    var pServNumber = "<s:property value='chargeLogVO.servnumber' />";
	    var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
	    var pPrintDate = getDateTime();  //��ӡ����
	    var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
	    var pDealNum = "<s:property value='chargeLogVO.dealnum'/>";     //������ˮ��
	    var pDealTime = "<s:property value='chargeLogVO.dealTime'/>";   //����ʱ��
	    var tMoney = "<s:property value='chargeLogVO.tMoney' />Ԫ";     //���׽��
	    var pDealStatus = "���ѳɹ�"; //����״̬
	    var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";//�ն���ˮ
	    var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';// ����СƱ
	    
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	            "��ӡƾ��");
	    
	    doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
	                pDealStatus, pTerminalSeq, "", "0", "<s:property value='chargeLogVO.custName' escape='false' />", printcontext);
	    
	    document.getElementById("proofInfo").innerHTML = "ƾ����ӡ�ɹ�����ȡ������ƾ����";
	    
	    printFlag = 1;
    }
}

</script>
