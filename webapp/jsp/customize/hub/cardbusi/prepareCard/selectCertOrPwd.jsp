<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%
	// �ն˻���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>ѡ�����֤���������ҳ��</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/nx/js/romoveAclick_NX.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

//82��220 ����		
document.onkeydown = pwdKeyboardDown;

//�������̰����¼�
function pwdKeyboardDown(e) 
{
	var key = GetKeyCode(e);

	//����
	if (key == 77) 
	{
		preventEvent(e);
	}

	// �����������
	if (!KeyIsNumber(key)) 
	{
		return false;
	}
}

// �����Ƿ�������У��
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

//�����¼�
function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);

	//����
	if (key == 82 || key == 220) 
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		return;		
	}
	
	if(key == 49)
	{
		doSub(1);
	}
	else if(key == 50)
	{
		doSub(2);
	}		
}

//������һҳ
function goback(menuid)
{
	if (submitFlag == 0)
	{
		//�����ȴ���
		openRecWaitLoading();
	
		submitFlag = 1;
		
		document.actform.action = "${sessionScope.basePath }prepareCard/validTelAndPwd.action";
		document.actform.submit();
	}
}

// �ύ
function doSub(sele_rule) 
{		
	//��ֹ�ظ�����
	if (submitFlag == 0) 
	{
		//�����ȴ���
		openRecWaitLoading();
		
		submitFlag = 1;
	
		//���֤��֤
		if(sele_rule==1)
		{
			document.actform.action = "${sessionScope.basePath}prepareCard/selectCert.action";
				
		}
		
		//�����������֤
		else if(sele_rule==2)
		{
			var tip = sendRandomPwd();
			if (tip != "") 
			{
				alertRedErrorMsg(tip);
				return;
			}
			
			document.actform.action = "${sessionScope.basePath}prepareCard/selectRandomPwd.action";
		}
		
		document.actform.submit();
	}
}

// ���Ͷ��������
function sendRandomPwd()
{
	var url = "${sessionScope.basePath}login/sendRandomPwd_hub.action";
	var params = "servnumber=${sessionScope.CustomerSimpInfo.servNumber }";
	var resXml;
	
	var loader = new net.ContentLoaderSynchro(url, netload = function () 
	{
		resXml = this.req.responseText;
	}, null, "POST", params, "application/x-www-form-urlencoded");	
	
	return resXml;		
}

// add begin hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
function checkActFlag()
{
	// ��Ӫ����Ƽ����������ٴ�У���ӡ���ͽɷ�Ӳ��
	if("1" == "<s:property value='recommendActivityFlag' />")
	{
		// �Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
		var isPrintFlag = window.parent.isPrintFlag;

		// �Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
		var isCashEquip = window.parent.isCashEquip;
		
		// �Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
		var isUnionPay = window.parent.isUnionPay;
		
		// У��Ʊ�ݴ�ӡ�� 1-����ӡ��0-��ӡ
		var canNotPrint = isPrintFlag == 1 ? (initListPrinter() == "" ? "0" : "1") : "1";
 
	 	setValue("canNotPrint",canNotPrint);
	 	
	 	var chkCash = isCashEquip == 1 ? (initCashPayEquip() == "" ? "1" : "0") : "0";
	 	
	 	var chkCardMsg = initUnionCardPayEquip("<%=termInfo.getUnionpaycode()%>", "<%=termInfo.getUnionuserid()%>");
	 	
	 	var chkCard = isUnionPay == 1 ? (chkCardMsg == "" ? "1": "0") : "0";
	 	
	 	var payTypeFlag = chkCash + "" + chkCard;
	 	
	 	setValue("payTypeFlag",payTypeFlag);
	}
}
// add end hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_����_�����ն˴��������Ӫ��
</script>
</head>
<body scroll="no" onload="checkActFlag();">
	<form name="actform" method="post" target="_self">
		<!-- ������Ϣ -->
		<input type="hidden" id="errormessage" name="errormessage" value='' />
			
		<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
		<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value='payTypeFlag' />"/>
		<!-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ -->
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />

		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>����</h2>
					<div class="blank10"></div>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. �����ֻ�����</a> 
					<a href="javascript:void(0)" class="on">2. ѡ����֤��ʽ</a>
					<a href="javascript:void(0)">3. ��ȡ���֤��Ϣ</a>
   					<a href="javascript:void(0)">4. ����ȷ��</a>
   					<a href="javascript:void(0)">5. ѡ��֧����ʽ</a>
   					<a href="javascript:void(0)">6. �����ɷ�</a>
   					<a href="javascript:void(0)">7. �¿���ӡСƱ</a>
				</div>
				
				<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank60"></div>
				<div class="p40">
					<p class=" tit_info"><span class="bg"></span>��ѡ����֤��ʽ��<span class="yellow"></span></p>
					<div class="blank10"></div>
					<div class="line"></div>
					<div class="blank20"></div>
					<div class="blank20"></div>
					
					<ul class="money_list_clearfix">
						<li class="tel_selectrule tc">
							<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(1); return false;">
								�������֤(��1��)
							</a>
						</li>
						<li class="pt10"></li>	
						<li class="tel_selectrule tc">
							<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
								�������(��2��)
							</a>
						</li>
					</ul>
				</div>
			</div>	
			
				<div class=" clear"></div>

			</div>
		</div>

		<%@ include file="/backinc.jsp"%>
	</form>
</body>

</html>
