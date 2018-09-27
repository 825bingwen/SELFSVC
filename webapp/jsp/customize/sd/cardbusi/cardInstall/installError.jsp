<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if(null == errorMessage || "".equals(errorMessage.trim()))
{
	errorMessage = "����ʧ�ܣ����Ժ����ԡ�";
}
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	
	// ��Ҫ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	//add begin sWX219697 2015-6-23 14:08:19 �޸�СƱ��ʽ��������Ͷ�ҽ�һ�µ�Bug 92717
	String yuanMoney = (String) request.getAttribute("tMoney");
	String fenMoney = CommonUtil.yuanToFen(yuanMoney);
	String ticketMoney = CommonUtil.fenToYuan(fenMoney);
	//add end sWX219697 2015-6-23 14:08:19 �޸�СƱ��ʽ��������Ͷ�ҽ�һ�µ�Bug 92717
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/CardInstallManager_sd.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
<script type="text/javascript">
//��ֹҳ���ظ��ύ
var submitFlag = 0;

//ȡ��ʱ��30��
var limitTime = 30;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

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

function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	if (key == 82 || key == 220)
	{
		goback();
		return;
	}
}

function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.actform.submit();
	}
}

// �ɷѷ�ʽ  1 ������  4 �ֽ�
var payType = "<s:property value='payType' />";

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
		
// �¿�
function returnCard()
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
function doFinish()
{
	// �Ƿ񽫿��ƶ��������� 1������ 0��������
	if(1 == "<s:property value='callBackCard' />")
	{
		callBackCard();
		CloseCard();
	}
	
    if("1" == payType)
    {
   		returnCard();
    }
   
	 //����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
    var money = parseInt("<s:property value='tMoney' />");
   
    if (isNaN(money) || money <= 0)
    {
        return;
    }
   
    // Ʊ�ݴ�ӡ���Ƿ����
    var canNotPrint = "<s:property value = 'canNotPrint' />";
   
    // ��ӡСƱ
    var printReceiptFlag = 0;
	
    // ��ӡ�ɷ�ʧ�ܵ�ƾ֤
    if (canNotPrint == "0" && printReceiptFlag == 0)
    {
   		printReceiptFlag = 1;
   		
	   	var piData = new function()
        {
            this.servnumber = "<s:property value='telnum' />";
            this.mainprodname = "<s:property value='tpltTempletPO.mainProdName' />";
            this.tpltname = "<s:property value='tpltTempletPO.templetName' />";
            this.termId = "<%=termId%>";
            this.recFee = "<s:property value='recFee' />";
            this.tMoney = "<%=ticketMoney%>";
            this.chargeId = "<s:property value='chargeId' />";     
            this.pDealTime = getDateTime();  
            this.pDealStatus = "�����ն˿հ׿���������ʧ��"; 
            this.pTerminalSeq = "<s:property value='terminalSeq' />";
            this.pOrgName = "<%=pOrgName%>";  
            this.formnum = "<s:property value='formnum' />";  // ������ˮ 
            this.installStatus = "<s:property value='installStatus' />";
        }
        // ��ӡ����СƱ
        var printcontext = "<s:property value='printcontext' />";
       
        printInstallTicket(piData,printcontext);
   }				
}
</script>
</head>
	<body scroll="no" onload="doFinish()">
		<form name="actform" method="post">
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
			    <%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
						<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
						<a href="javascript:void(0)">4. ����ѡ��</a>
						<a href="javascript:void(0)">5. ���÷�������</a>
						<a href="javascript:void(0)">6. ����ȷ��</a>
						<a href="javascript:void(0)">7. �����ɷ�</a>
						<a href="javascript:void(0)" class="on">8. ȡ����ӡСƱ</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
  							<%
  								out.print(errorMessage);
  							%>
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>

</html>
