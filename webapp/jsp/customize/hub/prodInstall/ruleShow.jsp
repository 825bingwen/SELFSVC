<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    //ʡ��
    String province = (String)PublicCache.getInstance().getCachedData("ProvinceID");
    
    // �ն˻���Ϣ
    TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // �ؼ�֧�ֱ�־
    String termSpecial = terminalInfo.getTermspecial();
    
    // �ؼ������ַ���
    String plugin = terminalInfo.getPlugin();
    
    // socket��ַ(�ն����ñ�������,״̬�ϴ���)
    String sockaddr = "";
    if (terminalInfo != null)
    {
        sockaddr = (String)PublicCache.getInstance().getCachedData("SH_SOCKADDR_" + terminalInfo.getRegion());
    }
    
    // FTP��ַ(�ն˻���־�ϴ���FTP��ַ,�û����������ն˳�����������)
    String logftpaddr = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGFTPADDR);
    if (logftpaddr == null)
    {
        logftpaddr = "";
    }
    
    // ��־�ϴ����������
    String cashInterval = (String)PublicCache.getInstance().getCachedData("SH_CASHINTERVAL");
    if (cashInterval == null || "".equals(cashInterval.trim()))
    {
        cashInterval = "60";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
			<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
				<META HTTP-EQUIV="Expires" CONTENT="0">
					<link href="${sessionScope.basePath }css/reset.css" type="text/css"
						rel="stylesheet" />
					<link href="${sessionScope.basePath }css/style.css" type="text/css"
						rel="stylesheet" />
					<script type="text/javascript"
						src="${sessionScope.basePath }js/public.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/script.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/dialyzer.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/net.js">
</script>
					<style type="text/css">
.tb_blue_08,.tb_blue_08 td,.tb_blue_08 th,.tb_num {
	border: 1px solid #1a9ae3;
	border-collapse: collapse;
	font-size: 18px;
	color: #fff;
	height: 30px;
}

.tb_blue_08 th {
	background: #056e99;
}
</style>
					<script type="text/javascript">
//�ؼ����
// socket��ַ(״̬�ϴ�ʱ��ͬ���ȹ���,�������ڲ�����������SH_SOCKADDR)
var sockaddr = '<%=sockaddr%>';

// FTP��ַ(�ϴ��ն˻���־��ָ��ftp������,�������ڲ����������SH_LOGFTPADDR)
var logftpaddr = '<%=logftpaddr%>';

//�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
var isPrintFlag= <%=termSpecial.charAt(0)%>;

//�Ƿ�֧�ִ�ӡ��Ʊ��־,0��֧��,1:֧��
var isInvoicePrint = <%=termSpecial.charAt(1)%>;

//�Ƿ�֧�ּ��ܼ��̱�־,0��֧��,1:֧��
var isKeyBoard = <%=termSpecial.charAt(2)%>;

//�Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
var isCashEquip = <%=termSpecial.charAt(3)%>;

//�Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
var isUnionPay = <%=termSpecial.charAt(4)%>;

//�Ƿ���й���ؼ���־,0��֧��,1:֧��
var isManageConf = <%=termSpecial.charAt(5)%>; 
  
//�Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
var is2GIDFlag = <%=termSpecial.charAt(8)%>;

//�Ƿ�֧�������ۿ�ӿ� 0��֧��,1:֧�� (�����޴˿ؼ�)
var isCardPay = <%=termSpecial.charAt(9)%>
//�Ƿ�֧��SIM��������
var SIMreaderFlag = <%=termSpecial.charAt(7)%>

// �ؼ����Ƶ�� 
var frequency = <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY)%>;

// �ն˹���ؼ�
function frmInitManageConf(sockaddr,logftpaddr) {
  if (isManageConf == 1) {
    <%if (null != sockaddr || null != logftpaddr)
            {%>
    // �����ն˳�ʼ��
    try {
  		var initMC = document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, "<%=cashInterval%>");
  		if (initMC == -1) {
  		  	showFrmErr("����:�ն˿�������ʼ��ʧ�ܣ�");
  			return 0;
  		}
  	}catch (e){
  		showFrmErr("����:�ն˿�������ʼ��ʧ�ܣ�");
  		return 0;
  	}
  	return 1;
  <%}
            else
            {%>
      
      return 0;
  <%}%>
  }else{
	  
	  return 0;
  }
}

// �ֽ�ʶ������ʼ��
function frmOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret = document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("����:�ֽ�ʶ��������!&nbsp;&nbsp;");
			    return 0;
			}
		}catch (e) {
    	    showFrmErr("����:�ֽ�ʶ��������!&nbsp;&nbsp;");
			return 0;
		}
		return 1;
	}else{
		
		return 0;
	}
}

// �������֤��ʼ��
function frmOpenIdCardReader(){
	if (is2GIDFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("����:���֤����������!&nbsp;&nbsp;");
			      goToExcPage();
			    return;
			}
		}catch(e){
		    showFrmErr("����:���֤����������!&nbsp;&nbsp;");
		      goToExcPage();
			return;
		}
	}else{
		  goToExcPage();
	}
}


// ��ʼ������������  ���� �����ն�ID �����̻���
 function frmInitReadUserCard() {
	if(isUnionPay == 1){
		try{
			var ret = 0;
			<%if (Constants.PROOPERORGID_HUB.equals(province))
            {%>
			ret = document.getElementById("cardpluginid").InitConfig("<%=terminalInfo.getUnionpaycode()%>", "<%=terminalInfo.getUnionuserid()%>");
			<%}
            else
            {%>
			ret = document.getElementById("cardpluginid").InitReadUserCard();
			<%}%>
			if (ret != 0) {
			    showFrmErr("����:������������ʼ��ʧ��!&nbsp;&nbsp;");
			    return 0;
			}
		}catch(e){
    	    showFrmErr("����:������������ʼ���쳣!&nbsp;&nbsp;");
    	    return 0;
		}
		return 1;
	}else{
		return 0;
	}
}

// �������
function frmInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//��ʼ�����ܼ���
		try {
			var ret = document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showFrmErr("����:�����ն˼��̴���ʧ�ܣ�");
				return;
			}
			ret = document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showFrmErr("����:���ü���ģʽʧ�ܣ�");
				return;
			}
		}catch (e) {
  			showFrmErr("����:������̹���,�޷�ʹ���������!");
			return;
		}
	}
}

// Ʊ�ݴ�ӡ����ʼ��
function frmInitListPrt() {
     //�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
	if (isPrintFlag == 1) {
		try {
            //��ʼ��Ʊ�ݴ�ӡ��
			var initListPrt1 = document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showFrmErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
    		      goToExcPage();
				return;
			} else if (initListPrt1 == 41) {
   			    showFrmErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
   			      goToExcPage();
				return;
			}
			
        	//���ô�ӡͼ���·�� 
			var initListPrt2 = document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showFrmErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
    		      goToExcPage();
				return;
			} else {
				if (initListPrt2 == 41) {
    			    showFrmErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
    			      goToExcPage();
					return;
				}
			}
		}catch (e) {
    		showFrmErr("�����쳣,Ʊ�ݴ�ӡ����ʼ��ʧ��,�޷���ӡСƱ!");
    		  goToExcPage();
			return;
		}
	}else{
		
		  goToExcPage();
	}
}

// ��Ʊ��ӡ��
function frmInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // �򿪷�Ʊ��ӡ������
			var openCom = document.getElementById("invprtpluginid").OpenCom();
			if (openCom == 1) {
  	        	showFrmErr("����:��Ʊ��ӡ�����ڹ���!");
				return 0;
			} else {
				if (openCom == 61) {
	  			    showFrmErr("����:��Ʊ��ӡ������,�޷���ʼ��!");
					return 0;
				} else if (openCom == 65) {
	  			    showFrmErr("����:��Ʊ��ӡ��ȱֽ!");
					return 0;
				} else if (openCom != 0) {
	 			    showFrmErr("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
					return 0;
				}
			}
			
			// ��ʼ����Ʊ��ӡ��
			var initInvoicePrt = document.getElementById("invprtpluginid").InitVoicePrint();
			if (initInvoicePrt == 61) {
  		    	showFrmErr("����:��Ʊ��ӡ������,�޷���ʼ��!");
				return 0;
			} else if (initInvoicePrt == 65) {
 			    showFrmErr("����:��Ʊ��ӡ��ȱֽ!");
				return 0;
			}else if (openCom != 0) {
 			    showFrmErr("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
				return 0;
			}
			
			// ��鷢Ʊ��ӡ��ȱֽ
			var v = document.getElementById("invprtpluginid").CheckPaper();
			if (v != 0 ){
			    showFrmErr("����:��Ʊ��ӡ��ȱֽ�����!");
			    return 0;
			}
			
		}catch (e) {
  			showFrmErr("����:��Ʊ��ӡ��������,�޷���ӡ��Ʊ!");
			return 0;
		}
		return 1;
	}else{
		return 0;
	}
}
//������д����
function initSimCardReader(){
	if (SIMreaderFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("simcardpluginid").InitCard();
			if (ret != 0) {
			    showFrmErr("����:SIM����������!&nbsp;&nbsp;");
			    goToExcPage();
			    return;
			}
		}catch(e){
		    showFrmErr("����:SIM����������!&nbsp;&nbsp;");
		    goToExcPage();
			return;
		}
	}else{
		
		  goToExcPage();
	}
}



var viewErrDiv;
var viewErr;
function showFrmErr(errMsg) {
	if (viewErr != "" && viewErr != undefined) {
		viewErr = viewErr + "<br>" + errMsg;
	} else {
		viewErr = errMsg;
	}
	if (viewErrDiv == null || viewErrDiv == undefined) {
		var sWidth, sHeight;
		sWidth = document.body.offsetWidth;
		sHeight = screen.height;
		viewErrDiv = document.createElement("div");
		viewErrDiv.setAttribute("id", "frmErrDiv");
		viewErrDiv.style.position = "absolute";
		if (frequency == 1){
			viewErrDiv.style.top = "76%";
		}else{
			viewErrDiv.style.top = "82%";
		}
		viewErrDiv.style.left = "5%";
		viewErrDiv.style.textAlign = "left";
		viewErrDiv.style.color = "red";
		viewErrDiv.style.font = "bold 20px,\u9ed1\u4f53";
		viewErrDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
		viewErrDiv.style.opacity = "0.6";
		viewErrDiv.style.width = sWidth + "px";
		viewErrDiv.style.height = sHeight + "px";
		viewErrDiv.style.zIndex = "10000";
		document.body.appendChild(viewErrDiv);
		viewErrDiv.innerHTML = viewErr;
	} else {
		viewErrDiv.innerHTML = viewErr;
	}
}
		
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
		goback("<s:property value='curMenuId' />");
		return;
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

function queryDangCiList(groupId,groupName)
{
	if (groupId == "" || groupName == "")
	{
		return;
	}
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("groupId").value = groupId;
		document.getElementById("groupName").value = groupName;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiList.action";
		document.forms[0].submit();
	}
}

// ��һҳ
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = linkURL;
		document.forms[0].submit();
	}
}


function goToExcPage()
{
window.location="${sessionScope.basePath}/jsp/customize/hub/prodInstall/installError.jsp";
}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="window.focus();bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" id="servnumber" name="servnumber"
				value="<s:property value='servnumber' />">
				<input type="hidden" id="servRegion" name="servRegion"
					value="<s:property value='servRegion' />">
					<input type="hidden" id="yingjiaoFee" name="yingjiaoFee"
						value='<s:property value="yingjiaoFee" />'>
						<%@ include file="/titleinc.jsp"%>

						<div class="main" id="main">
							<div class="pl30">
								<div class="b257 ">
									<div class="bg bg257"></div>
									<h2>
										ѡ�ſ�������
									</h2>
									<div class="blank10"></div>
									<a href="#" class="on">1. ����Э��ȷ��</a>
									<a href="#">2. ���֤��Ϣ��ȡ</a>
									<a href="#">3. ��Ʒѡ��</a>
									<a href="#">4. ����ѡ��</a>
									<a href="#">5. ����ȷ��</a>
									<a href="#">6. �����ɷ�</a>
									<a href="#">7. ȡ����ӡ��Ʊ</a>
								</div>

								<div class="b712">
									<div class="bg bg712"></div>
									<div class="blank30"></div>
									<div class="p40">
										<img src="${sessionScope.basePath}images/loading.gif"
											alt="�豸�����..." />
										<font size="6">�豸����У����Ժ�......</font>



									</div>

									<div class=" clear"></div>

								</div>
							</div>
						</div>

						<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<%=plugin%>
</html>
<script type="text/javascript">

//ҳ�����ʱ����豸
function bodyLoad() {
	// �ն˹���ؼ�
	if (frmInitManageConf(sockaddr, logftpaddr) == 0)
		goToExcPage();
	//������д����
	initSimCardReader();
	// ���֤������
	frmOpenIdCardReader();
	// Ʊ�ݴ�ӡ�� 
	frmInitListPrt();
	// �ֽ�ʶ����  
	var cash = frmOpenCashEquip();
	// ����������
	var ccard = frmInitReadUserCard();
    if(cash+ccard==0){
    	 goToExcPage();
    	}else if(cash==0){
    			if(!confirm("�ֽ�ʶ�������ϣ����ο���ֻ�����������������Ƿ�ͬ�⣿")){
    				 goToExcPage();
    			}
    	}else if(ccard==0){
    			if(!confirm("�������������ϣ����ο���ֻ�����ֽ𿪻����Ƿ�ͬ�⣿")){
    				 goToExcPage();
    			}
    	}
    	 // ��Ʊ��ӡ��
     if(frmInitInvoicePrint()==0){
	   if(!confirm("��Ʊ��ӡ�����ϣ����ο������ܴ�ӡ��Ʊ�Ƿ������"))
		    goToExcPage();
	 }  
    //��Э��ȷ��ҳ��
     window.location="${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
   // �������
   // frmInitKeyBoard();
    
}
        
		
</script>
