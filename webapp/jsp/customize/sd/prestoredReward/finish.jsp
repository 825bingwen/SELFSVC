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
    
    // �ն˻���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
    String pOrgName = termInfo.getOrgname(); // �ص�
    String termName = termInfo.getTermname(); // �ն˻�����
    String termId = termInfo.getTermid(); // �ն˻�����
    
    // �ն˻�����
    String termSpecial = termInfo.getTermspecial();
    
    // �Ƿ�ɴ�ӡƾ����־
    String canPrintReceipt = termSpecial.substring(0, 1);
    
    // �ͻ���Ϣ
    NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    
    // �ͻ�����
    String custName = customer.getCustomerName();
            
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
<script type="text/javascript">
var submitFlag = 0;

var payType = "<s:property value='payType' />";

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
         
         document.forms[0].target = "_self";
         document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
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
            "<s:property value='telNum' />���ѳɹ�");
    
    if (payType == "card")
    {
        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
        var ret = getCardPosition();
        
        //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
        if (ret == "2")
        {
            startClock();
        }
    }
    
    // �Ƿ�ɴ�ӡƾ����־
    if ("<%=canPrintReceipt %>" == "1")
    {
        var p_telNum = "<s:property value='telNum' />";// �ֻ�����
        var p_custName = "<%=custName %>"; // �ͻ�����
        var p_orgName = "<%=pOrgName %>";  //��ӡ�ص�
        var p_date = getDateTime();  //��ӡ����
        var p_termId = "<%=termId %>"; //�ն˱���
        var p_termName = "<%=termName %>"; //�ն�����
        var p_tMoney = "<s:property value='tMoney' />";// Ͷ�ҽ��
        var p_chargeId = "<s:property value='chargeLogOID'/>";// �ɷ���־��ˮ
        var p_terminalSeq = "<s:property value='terminalSeq' />";//�ն���ˮ
        var p_recoid = "<s:property value='recoid' />";// ������ˮ
        var p_recFee = "<s:property value='recFee' />";// Ӫ����������
        var p_preFee = "<s:property value='preFee' />";// �û�Ԥ��
        var p_actLevelName = "<s:property value='actLevelName'/>";// ��������
        var p_groupName = "<s:property value='groupName'/>";// �������
        var p_status = "Ԥ����������ɹ�"; //����״̬
        
        var p_awardDesc = "";
        
        // СƱ��Ʒ�����������Ϊʵ��ʹ�ӡ
        if ("1" == document.getElementById("isGoods").value)
        {
            p_awardDesc = "<s:property value='awardDesc'/>";
        }
        var printcontext = "<s:property value='printcontext'/>";
        
        // ���ô�ӡ
        doPrintByActivitySD(p_telNum,p_orgName,p_date,p_termId,p_termName,p_tMoney,p_chargeId,p_terminalSeq,
            p_recoid,p_status,p_recFee,p_preFee,p_actLevelName,p_groupName,p_custName, p_awardDesc, printcontext)                        
    }
}

</script>
</head>
    <body onload="doFinish();" scroll="no">
        <form name="payMoneyForm" method="post">
        
        <!-- �ֻ����� -->
        <input type="hidden" id="telNum" name="telNum" value='<s:property value="#request.telNum" />'>

        <!-- ����� -->
        <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
        
        <!-- ���α��� -->
        <input type="hidden" id="actLevelId" name="actLevelId" value='<s:property value="#request.actLevelId" />'/>

        <!-- ���ID -->
        <input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
        
        <!-- ������� -->
        <input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
        
        <!-- �������� -->
        <input type="hidden" id="actLevelName" name="actLevelName" value="<s:property value="#request.actLevelName" />"/>
        
        <!-- ������ -->
        <%-- modify by sWX219697 2015-7-20 prePayFee��ΪprepayFee--%>
        <input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
        
        <!-- ��Ʒ���봮 -->
        <input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward"/>'/>
        
        <!-- Ӧ����� -->
        <input type="hidden" id="recFee" name="recFee" value='<s:property value="#request.recFee"/>'/>
        
        <!-- �ն˻���ˮ -->
        <input type="hidden" id="terminalSeq" name="terminalSeq" value=""/>
            
        <!-- ������Ϣ -->
        <input type="hidden" id="errormessage" name="errormessage" value=""/>  
        
        <!-- ��־��ˮ -->
        <input type="hidden" id="chargeLogOID" name="chargeLogOID" value='<s:property value="chargeLogOID"/>'/>
        
        <!-- �Ƿ�Ϊʵ�� 1:ʵ�0����ʵ�� -->        
        <input type="hidden" id="isGoods" name="isGoods" value='<s:property value="#request.isGoods"/>'/>
        
        <!-- СƱ��Ʒ���� -->
        <input type="hidden" id="awardDesc" name="awardDesc" value='<s:property value="#request.awardDesc"/>'/>
        
            <%@ include file="/titleinc.jsp"%>
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>Ԥ�������������̣�</h2>
                        <div class="blank10"></div>
                        <a title="ѡ������" href="#">1. ѡ��</a>
                        <a title="��������" href="#">2. ����Э��</a>  
                        <a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
                        <a title="֧��" href="#">4. ֧��</a>
                        <a title="���" href="#" class="on">5. ���</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40 pr0">
                            <p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='telNum' /></span></p>
                            <p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
                            <div class="blank20"></div>
                            <div class="line w625"></div>
                            <div class="blank30"></div>
                            <div class="recharge_result tc">
                                <p class="title yellow pt30" id="msg">Ԥ������������ɹ���</p>
	                            <%
	                               if ("1".equals(canPrintReceipt))
	                               {
	                             %>
	                            <p class="desc pt20">�뱣������ĵĽ���ƾ����</p>
	                            <%} %>
                                
	                            <div class="btn_box3 clearfix" style="padding-left:245px;">                                                                
                                    <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="topGo('rec', 'reception/receptionFunc.action');return false;">����ҵ��</a>
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
