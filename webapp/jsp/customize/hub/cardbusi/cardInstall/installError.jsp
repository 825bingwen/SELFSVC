<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if(null == errorMessage || "".equals(errorMessage.trim()))
{
	errorMessage = "����ʧ�ܣ����Ժ����ԡ�";
}

	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	errorMessage = CommonUtil.errorMessage(errorMessage);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	
	// ��Ҫ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript">
//��ֹҳ���ظ��ύ
var submitFlag = 0;

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
		
// �¿�
function returnCard()
{
	// �Ƿ���Ҫ�˿�
	var needReturnCard = "<s:property value='needReturnCard' />"; 
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
		
		// �رտ�����ʧ������Ӳ����������
		var status = CloseCard();
	}
}	
function doFinish()
{
	// �Ƿ񽫿��ƶ��������� 1������ 0��������
	if(1 == "<s:property value='callBackCard' />")
	{
		callBackCard();
	}
	
	//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
   var money = parseInt("<s:property value='tMoney' />");
   
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   if("1" == payType)
   {
   		returnCard();
   }
   
   // Ʊ�ݴ�ӡ���Ƿ����
	var canNotPrint = "<s:property value = 'canNotPrint' />";
   
   	// ��ӡСƱ
	var printReceiptFlag = 0;
	
   //��ӡ�ɷ�ʧ�ܵ�ƾ֤
   if (canNotPrint == "0" && printReceiptFlag == 0)
   {
   		printReceiptFlag = 1;
   		
	   	var piData = new function()
	   	{
	   		this.pSubsName = "<s:property value='idCardPO.idCardName' />";
	   		this.servnumber = "<s:property value='telnum' />";
	   		this.mainprodname = "<s:property value='tpltTempletPO.mainProdName' />";
	   		this.termId = "<%=termId%>";
		   	this.receptionFee = "<s:property value='recFee' />";
		   	if(payType == 4)
		   	{
		   		this.tMoney = "<s:property value='tMoney' />";
		   	}
		   	else
		   	{
		   		this.tMoney = "<s:property value='recFee' />";
		   	}
		   	this.dealNum = "<s:property value='chargeId' />";     
		   	this.pDealTime = getDateTime();  
		   	this.payStatus = '0';  // �ɷѳɹ�0
		   	this.pDealStatus = "�����ն˿հ׿���������ʧ��"; 
		   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
		   	this.pOrgName = "<%=pOrgName%>";  
		   	this.pPrintDate = getDateTime();  
	   	}
	   
	   	printInstallTicket(piData);
	   	
	   	// ��ӡ����СƱ
		var printcontext = "<s:property value='printcontext' />";
		if (payType == 1 && printcontext != "")
		{
			doPrintUnionBill_hb(printcontext,"<s:property value='terminalSeq' />","<%=pOrgName%>",getDateTime());
		}
   }				
}
</script>
</head>
	<body scroll="no" onload="doFinish()">
		<form name="actform" method="post">
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
						<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
						<a href="javascript:void(0)">4. ����ѡ��</a>
						<a href="javascript:void(0)">5. ����ȷ��</a>
						<a href="javascript:void(0)">6. �����ɷ�</a>
						<a href="javascript:void(0)" class="on">7. ȡ����ӡСƱ</a>
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
