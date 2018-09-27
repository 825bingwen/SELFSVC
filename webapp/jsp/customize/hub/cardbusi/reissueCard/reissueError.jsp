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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js"></script>
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
		
		document.actform.action = "${sessionScope.basePath }reissueCard/inputTelnum.action";
		document.actform.submit();
	}
}

// �ɷѷ�ʽ  1 ������  4 �ֽ�
var payType = "<s:property value='payType' />";

//ȡ��ʱ��30��
var limitTime = 30;

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
	}
}

//��ת����ҳ��ʱ��ӡСƱ		
function doFinish()
{
	//����sim��
	if ("1" == "<s:property value='callBackCard' />")
	{
		callBackCard();
		CloseCard();
	}
	
	//����û���Ͷ�ң��Ŵ�ӡʧ��ƾ��
   var money = parseInt("<s:property value='tMoney' />");
   
   //�û�δͶ�ң������ӡСƱ
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   //�³�������
   if("1" == payType)
   {
       returnCard();
   }
   
   //��ӡ�ɷ�ʧ�ܵ�ƾ֤
   if ("<s:property value='canNotPrint' />" == "0")
   {
		var piData = 
		{
			//�ֻ�����
	   		servnumber:"${sessionScope.CustomerSimpInfo.servNumber}",
	   		
	   		//�ն˻����
	   		termId:"<%=termInfo.getTermid() %>",
	   		
	   		//��������
		   	receptionFee:"<s:property value='recFee' />",
		   	
		   	//ʵ�ɽ��
		   	tMoney:"<s:property value='tMoney' />",
		   	
		   	//������ˮ��
		   	dealNum:"<s:property value = 'cardChargeLogVO.chargeLogOID' />",
		   	
		   	//����ʱ��    
		   	pDealTime:getDateTime(),
		   	
		   	// �ɷѳɹ�0 
		   	payStatus:'0', 
		   	
		   	//ҵ�����ʧ��1
		   	installStatus:'1',
		   	
		   	//����״̬
		   	pDealStatus:"�����ն˲�������ʧ��",
		   	
		   	//�ն˻�������֯����
		   	pOrgName:"<%=termInfo.getOrgname()%>",
		   	
		   	//��ӡʱ��
		   	pPrintDate:getDateTime()
		}
	  
	  	printReissueTicket(piData);
	  	
	  	// ��ӡ����СƱ
		var printcontext = "<s:property value='printcontext' />";
		if (payType == "1" && printcontext != null && printcontext != "")
		{
			doPrintUnionBill_hb(printcontext,"<s:property value='cardChargeLogVO.terminalSeq' />","<%=termInfo.getOrgname()%>",getDateTime());
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
						<h2>����</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a>
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
    					<a href="javascript:void(0)">3. ����ȷ��</a>
    					<a href="javascript:void(0)">4. ѡ��֧����ʽ</a>
    					<a href="javascript:void(0)">5. �����ɷ�</a>
    					<a href="javascript:void(0)" class="on">6. ȡ����ӡСƱ</a>
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
