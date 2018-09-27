<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    //ʡ��
    String province = (String)PublicCache.getInstance().getCachedData("ProvinceID");
    
    // �ն˻���Ϣ
    TerminalInfoPO terminalInfo1 = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // �ؼ�֧�ֱ�־
    String termSpecial = terminalInfo1.getTermspecial();
    
    // �ؼ������ַ���
    String plugin = terminalInfo1.getPlugin();
    
    // socket��ַ(�ն����ñ�������,״̬�ϴ���)
    String sockaddr = "";
    if (terminalInfo1 != null)
    {
        sockaddr = (String)PublicCache.getInstance().getCachedData("SH_SOCKADDR_" + terminalInfo1.getRegion());
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
		<script type="text/javascript"
			src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
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
//����ȫ�ִ�����Ϣ
var errorMSG = "";
//�ؼ����
// socket��ַ(״̬�ϴ�ʱ��ͬ���ȹ���,�������ڲ�����������SH_SOCKADDR)
var sockaddr = window.parent.sockaddr;// '<%=sockaddr%>';

// FTP��ַ(�ϴ��ն˻���־��ָ��ftp������,�������ڲ����������SH_LOGFTPADDR)
var logftpaddr = window.parent.logftpaddr;//'<%=logftpaddr%>';

//�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
var isPrintFlag = window.parent.isPrintFlag;//<%=termSpecial.charAt(0)%>;

//�Ƿ�֧�ִ�ӡ��Ʊ��־,0��֧��,1:֧��
var isInvoicePrint = window.parent.isInvoicePrint;//<%=termSpecial.charAt(1)%>;

//�Ƿ�֧�ּ��ܼ��̱�־,0��֧��,1:֧��
var isKeyBoard = window.parent.isKeyBoard;//<%=termSpecial.charAt(2)%>;

//�Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
var isCashEquip = window.parent.isCashEquip;// <%=termSpecial.charAt(3)%>;

//�Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
var isUnionPay = window.parent.isUnionPay;//<%=termSpecial.charAt(4)%>;

//�Ƿ���й���ؼ���־,0��֧��,1:֧��
var isManageConf = window.parent.isManageConf;// <%=termSpecial.charAt(5)%>; 

//�Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
var is2GIDFlag = window.parent.is2GIDFlag;//<%=termSpecial.charAt(8)%>;

//�Ƿ�֧�������ۿ�ӿ� 0��֧��,1:֧�� (�����޴˿ؼ�)
var isCardPay = window.parent.isCardPay;//<%=termSpecial.charAt(9)%>;

//�Ƿ�֧��SIM��������
var SIMreaderFlag = window.parent.SIMreaderFlag;// <%=termSpecial.charAt(6)%>;

// �ؼ����Ƶ�� 
var frequency = window.parent.frequency;// <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY)%>;

// �ն˹���ؼ�
function frmInitManageConf(sockaddr, logftpaddr) {
if (isManageConf == 1) {
    <%if (null != sockaddr || null != logftpaddr)
            {%>
    // �����ն˳�ʼ��
    try {
  		var initMC = window.parent.document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, "<%=cashInterval%>");
  		if (initMC == -1) {
  		  	showFrmErr("����:�ն˿�������ʼ��ʧ�ܣ�");
  			return 0;
  		}
  	}catch (e){
  		showFrmErr("����:�ն˿�������ʼ���쳣��");
  		return 0;
  	}
  	return 1;
  <%}
            else
            {%>
       showFrmErr("����,�޷���ȡ�ն˻���IP��MAC��ַ!");
      return 0;
  <%}%>
  }else{
	  showFrmErr("����,���ն˻���֧���ն˿��ƹ���!");
	  return 0;
  }
}


// �ֽ�ʶ������ʼ��
function frmOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret =  window.parent.document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("����:�ֽ�ʶ��������!");
			    return 0;
			}
		}catch (e) {
    	    showFrmErr("����:�ֽ�ʶ������ʼ���쳣!");
			return 0;
		}
		return 1;
	}else{
		showFrmErr("����,���ն˻���֧���ֽ�ʶ�ҷ���!");
		return 0;
	}
}

// �������֤��ʼ��
function frmOpenIdCardReader(){
	if (is2GIDFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("����:���֤��������ʼ������!");
			     
			    return 0;
			}
			return 1;
		}catch(e){
		    showFrmErr("����:���֤��������ʼ���쳣!");
		     
			return 0;
		}
	}else{
		showFrmErr("����,���ն˻���֧�����֤ʶ����!");
		  return 0;
	}
}


// ��ʼ������������  ���� �����ն�ID �����̻���
 function frmInitReadUserCard() {
	if(isUnionPay == 1){
		try{
			var ret = 0;
			
			ret =  window.parent.document.getElementById("cardpluginid").InitConfig("<%=terminalInfo1.getUnionpaycode() %>", "<%=terminalInfo1.getUnionuserid() %>");
			
			
			if (ret != 0) {
			    showFrmErr("����:������������ʼ��ʧ��!");
			    return 0;
			}
			return 1;
		}catch(e){
    	    showFrmErr("����:������������ʼ���쳣!");
    	    return 0;
		}
	}else{
		
		showFrmErr("����,���ն˻���֧��������������!");
		return 0;
	}
	}

// �������
function frmInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//��ʼ�����ܼ���
		try {
			var ret =  window.parent.document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showFrmErr("����:�����ն˼��̴���ʧ�ܣ�");
				return;
			}
			ret =  window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showFrmErr("����:���ü���ģʽʧ�ܣ�");
				return;
			}
		}catch (e) {
  			showFrmErr("����:��������쳣,�޷�ʹ���������!");
			return;
		}
	}
}

// ��ʼ��Ʊ�ݴ�ӡ��
function frmInitListPrt() {
	
	 //�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
	if (isPrintFlag == 1) {
		try {
            //��ʼ��Ʊ�ݴ�ӡ��
			var initListPrt1 =  window.parent.document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showFrmErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
				return 0;
			} else if (initListPrt1 == 41) {
   			    showFrmErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
				return 0;
			}
			
        	//���ô�ӡͼ���·�� 
			var initListPrt2 =  window.parent.document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showFrmErr("����:Ʊ�ݴ�ӡ��ȱֽ�����!");
				return 0;
			} else {
				if (initListPrt2 == 41) {
    			    showFrmErr("����:Ʊ�ݴ�ӡ���豸�Ͳ���������δ��װ!");
					return 0;
				}
			}
			return 1;
		}catch (e) {
    		showFrmErr("����:Ʊ�ݴ�ӡ����ʼ���쳣,�޷���ӡСƱ!");
			return 0;
		}
	}else{
		showFrmErr("����:���ն˻���֧��Ʊ�ݴ�ӡ����,�޷���ӡСƱ!");
		return 0;
		
		}
	
	}
//���Ʊ�ݴ�ӡ����״̬
function checkPrintStatus(){
	
     // 0 ���� 1 ����  2 ȱֽ
	if (isPrintFlag == 1) {
	      try {
            
			var pstate =  window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
			if (pstate == 1) {
    		    showFrmErr("����:Ʊ�ݴ�ӡ�����ϣ�");
    		      
				return  0;
			}else if(pstate == 2){
				 showFrmErr("����:Ʊ�ݴ�ӡ��ȱֽ��");
    		      
				return  0;
			}
			
        
			return 1;
		}catch (e) {
    		 showFrmErr("����:���Ʊ�ݴ�ӡ��״̬�쳣,�޷���ӡСƱ��");
    		  
			return 0;
		}
	}else{
		showFrmErr("����:���ն˻���֧��Ʊ�ݴ�ӡ����,�޷���ӡСƱ!");
		  return 0;
	}
}

// ��Ʊ��ӡ����ʼ��
function frmInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // �򿪷�Ʊ��ӡ������
			var openCom =  window.parent.document.getElementById("invprtpluginid").OpenCom();
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
			var initInvoicePrt =  window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
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
<%--			var v = document.getElementById("invprtpluginid").CheckPaper();--%>
<%--			if (v != 0 ){--%>
<%--			    showFrmErr("����:��Ʊ��ӡ��ȱֽ�����!");--%>
<%--			    return 0;--%>
<%--			}--%>
			return 1;
		}catch (e) {
  			showFrmErr("����:��Ʊ��ӡ����ʼ���쳣,�޷���ӡ��Ʊ!");
			return 0;
		}
	}else{
		showFrmErr("����:���ն˻���֧�ַ�Ʊ��ӡ����,�޷���ӡ��Ʊ!");
		return 0;
	}
}
// ��鷢Ʊ��ӡ������ֽ
function checkInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // �򿪷�Ʊ��ӡ������
			var ipaper =  window.parent.document.getElementById("invprtpluginid").CheckPaper();
			if (ipaper != 0) {
  	        	showFrmErr("����:��Ʊ��ӡ�����ڹ���!");
				return 0;
			}
			
		}catch (e) {
  			showFrmErr("����:��鷢Ʊ��ӡ������ֽ�쳣,�޷���ӡ��Ʊ��");
			return 0;
		}
		return 1;
	}else{
		showFrmErr("����:���ն˻���֧�ַ�Ʊ��ӡ����,�޷���ӡ��Ʊ!");
		return 0;
	}
}

//�򿪶�����д����
function openSimCardReader(){
	
	if (SIMreaderFlag == 1) {
		
		
		try{
			
			
			var ret =  window.parent.document.getElementById("simcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("����:SIM�������򿪹���!");
			    
			    return 0;
			}
			return 1;
		}catch(e){
		   
		    showFrmErr("����:SIM���������쳣!");
		    
			return 0;
		}
	}else{
		showFrmErr("����:���ն˻���֧�ֶ�ȡSIM������!");
		return 0;
		  
	}
}
//������д������ʼ��
function initSimCardReader(){
	
	if (SIMreaderFlag == 1) {
		
		
		try{
			
			
			var ret =  window.parent.document.getElementById("simcardpluginid").InitCard();
			if (ret != 0) {
			    showFrmErr("����:SIM����������!");
			    
			    return 0;
			}
			return 1;
		}catch(e){
		   
		    showFrmErr("����:SIM��������ʼ���쳣!");
		    
			return 0;
		}
	}else{
		showFrmErr("����:���ն˻���֧�ֶ�ȡSIM������!");
		return 0;
		  
	}
}


function checkCardStatus()
{
	try
	{
		if (SIMreaderFlag == 1) 
		{
			//��������
			var stat=window.parent.document.getElementById("simcardpluginid").IsCardExist();
			
			if(stat == 0)
			{
				return 1;
			}
			
			
			stat=window.parent.document.getElementById("simcardpluginid").ReadCardStatus();
			
			if(stat == -1)
			{
				goToExcPage("��������״̬ʧ�ܣ�");
				return 0;
			}
			
			var arr = stat.split('~');
			if(arr[2] == 0)
			{
			     goToExcPage("�����ж�û��SIM����");
				 	 return 0;
			}
			
			stat=window.parent.document.getElementById("simcardpluginid").MoveCardToWrite();
			if(stat==-1)
			{
					 goToExcPage("�ƿ�ʧ�ܣ�");
					 return 0;
			}
		  
		  return 1;
	  }
		return 0;
	}
	catch(e)
	{
		goToExcPage("��ȡSIM����Ϣ���̳����쳣��");
		return 0;
	}
	
}




<%--var viewErrDiv;--%>
<%--var viewErr;--%>
function showFrmErr(errMsg) {
	errorMSG=errMsg;
<%--	if (viewErr != "" && viewErr != undefined) {--%>
<%--		viewErr = viewErr + "<br>" + errMsg;--%>
<%--	} else {--%>
<%--		viewErr = errMsg;--%>
<%--	}--%>
<%--	if (viewErrDiv == null || viewErrDiv == undefined) {--%>
<%--		var sWidth, sHeight;--%>
<%--		sWidth = document.body.offsetWidth;--%>
<%--		sHeight = screen.height;--%>
<%--		viewErrDiv = document.createElement("div");--%>
<%--		viewErrDiv.setAttribute("id", "frmErrDiv");--%>
<%--		viewErrDiv.style.position = "absolute";--%>
<%--		if (frequency == 1){--%>
<%--			viewErrDiv.style.top = "76%";--%>
<%--		}else{--%>
<%--			viewErrDiv.style.top = "82%";--%>
<%--		}--%>
<%--		viewErrDiv.style.left = "5%";--%>
<%--		viewErrDiv.style.textAlign = "left";--%>
<%--		viewErrDiv.style.color = "red";--%>
<%--		viewErrDiv.style.font = "bold 20px,\u9ed1\u4f53";--%>
<%--		viewErrDiv.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";--%>
<%--		viewErrDiv.style.opacity = "0.6";--%>
<%--		viewErrDiv.style.width = sWidth + "px";--%>
<%--		viewErrDiv.style.height = sHeight + "px";--%>
<%--		viewErrDiv.style.zIndex = "10000";--%>
<%--		document.body.appendChild(viewErrDiv);--%>
<%--		viewErrDiv.innerHTML = viewErr;--%>
<%--	} else {--%>
<%--		viewErrDiv.innerHTML = viewErr;--%>
<%--	}--%>
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
<%--	if (submitFlag == 0)--%>
<%--	{--%>
<%--		submitFlag = 1;--%>
<%--		--%>
<%--		document.getElementById("curMenuId").value = menuid;--%>
<%--				--%>
<%--		document.forms[0].target = "_self";--%>
<%--		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";--%>
<%--		document.forms[0].submit();--%>
<%--	}--%>
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

	function doSub(url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
			
				
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}hubprodinstall/"+url;
				document.actform.submit();	
			}			
		}


function goToExcPage(errormessage)
{
	document.getElementById("errormessage").value=errormessage;
	doSub("handleErrorg.action");
	}
  //��ʾ
   function openWindow(){
	  document.getElementById().innerHTML="<span class='yellow'>���Ѿ�ѡ���˲�Ʒ��������ѡ��</span>";
	  wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������					
	}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" name="errormessage" id="errormessage" />
			<input type="hidden" name="" confirmF"" id="confirmF" />
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
					<div class="openwin_tip" id="openWin1">
						<div class="bg"></div>
						<div class=" blank60"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<p class="fs28 lh2 pl142" id="winText" name="winText">
							��Ʊ��ӡ�����ϣ����ο������ܴ�ӡ��Ʊ�Ƿ������
						</p>
						<div class="tc">
							<div class=" clear "></div>
							<div class=" blank30 "></div>
							<a title="ȷ��" href="javascript:void(0)" class=" bt4"
								onmousedown="this.className='bt4on'"
								onmouseup="this.className='bt4';document.getElementById('confirmF').value='1';wiWindow.close();">��</a>
							<a title="" href="javascript:void(0)" class=" bt4 ml20"
								onmousedown="this.className='bt4on ml20'"
								onmouseup="this.className='bt4 ml20';document.getElementById('confirmF').value='0';wiWindow.close()">��</a>
						</div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>

		</form>
	</body>

	<script type="text/javascript">
//ҳ�����ʱ����豸
function bodyLoad() {
	// �ն˹���ؼ�
	if (frmInitManageConf(sockaddr, logftpaddr) == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}
	//������д����
	if (initSimCardReader() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}
	if (openSimCardReader() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}

	if (checkCardStatus() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}

	// ���֤������
	if (frmOpenIdCardReader() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}
	// Ʊ�ݴ�ӡ�� 
	if (frmInitListPrt() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}
	if (checkPrintStatus() == 0) {
		goToExcPage("�ն˻��豸�쳣��" + errorMSG + "�����ݲ�������ѡ�ſ���ҵ��");
		return;
	}

	// �ֽ�ʶ����  
<%--	var cash = frmOpenCashEquip();--%>
	// ����������
<%--	var ccard = frmInitReadUserCard();--%>
<%--    if(cash+ccard==0){--%>
<%--    	 goToExcPage();--%>
<%--    	 return ;--%>
<%--    	}else if(cash==0){--%>
<%--    			if(!confirm("�ֽ�ʶ�������ϣ����ο���ֻ�����������������Ƿ�ͬ�⣿")){--%>
<%--    				 goToExcPage("�ն˻��豸�쳣���ֽ�ʶ�������ϣ����ݲ�������ѡ�ſ���ҵ��");--%>
<%--    				 return ;--%>
<%--    			}--%>
<%--    	}else if(ccard==0){--%>
<%--    			if(!confirm("�������������ϣ����ο���ֻ�����ֽ𿪻����Ƿ�ͬ�⣿")){--%>
<%--    				 goToExcPage("�ն˻��豸�쳣���������������ϣ����ݲ�������ѡ�ſ���ҵ��");--%>
<%--    				 return ;--%>
<%--    			}--%>
<%--    	}--%>
     if(frmOpenCashEquip()==0){
    	  goToExcPage("�ն˻��豸�쳣��"+errorMSG+"�����ݲ�������ѡ�ſ���ҵ��");
    				 return ;
    	 }
    	 // ��Ʊ��ӡ��
    	 var iniInvo=frmInitInvoicePrint();
    	 var errorMSGf=errorMSG;
    	 var cheInvo=checkInvoicePrint();
    	 
     if(iniInvo+cheInvo!=2){
    	 openWindow();
    	 
	   if(document.getElementById('confirmF').value==0){
		    goToExcPage("�ն˻��豸�쳣��"+errorMSGf+errorMSG+"�����ݲ�������ѡ�ſ���ҵ��");
	        return ;
	        }
	 }  
    	doSub("getRuleInfo.action");
    	 
    	
    //��Э��ȷ��ҳ��
    // window.location="${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
   // �������
   // frmInitKeyBoard();
   }
</script>
		<script type="text/javascript">
removeAclick();
</script>
</html>

