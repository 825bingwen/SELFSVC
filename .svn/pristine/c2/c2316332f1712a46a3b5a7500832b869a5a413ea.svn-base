<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    //省份
    String province = (String)PublicCache.getInstance().getCachedData("ProvinceID");
    
    // 终端机信息
    TerminalInfoPO terminalInfo1 = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // 控件支持标志
    String termSpecial = terminalInfo1.getTermspecial();
    
    // 控件加载字符串
    String plugin = terminalInfo1.getPlugin();
    
    // socket地址(终端配置表中配置,状态上传等)
    String sockaddr = "";
    if (terminalInfo1 != null)
    {
        sockaddr = (String)PublicCache.getInstance().getCachedData("SH_SOCKADDR_" + terminalInfo1.getRegion());
    }
    
    // FTP地址(终端机日志上传的FTP地址,用户名与密码终端厂商自已配置)
    String logftpaddr = (String)PublicCache.getInstance().getCachedData(Constants.SH_LOGFTPADDR);
    if (logftpaddr == null)
    {
        logftpaddr = "";
    }
    
    // 日志上传间隔分钟数
    String cashInterval = (String)PublicCache.getInstance().getCachedData("SH_CASHINTERVAL");
    if (cashInterval == null || "".equals(cashInterval.trim()))
    {
        cashInterval = "60";
    }
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
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
//定义全局错误消息
var errorMSG = "";
//控件检查
// socket地址(状态上传时间同步等工作,此配置在参数表在配置SH_SOCKADDR)
var sockaddr = window.parent.sockaddr;// '<%=sockaddr%>';

// FTP地址(上传终端机日志到指定ftp服务器,此配置在参数表表配置SH_LOGFTPADDR)
var logftpaddr = window.parent.logftpaddr;//'<%=logftpaddr%>';

//是否支持打印票据标志,0不支持,1:支持
var isPrintFlag = window.parent.isPrintFlag;//<%=termSpecial.charAt(0)%>;

//是否支持打印发票标志,0不支持,1:支持
var isInvoicePrint = window.parent.isInvoicePrint;//<%=termSpecial.charAt(1)%>;

//是否支持加密键盘标志,0不支持,1:支持
var isKeyBoard = window.parent.isKeyBoard;//<%=termSpecial.charAt(2)%>;

//是否支持现金缴费标志,0不支持,1:支持
var isCashEquip = window.parent.isCashEquip;// <%=termSpecial.charAt(3)%>;

//是否支持银行卡缴费标志,0不支持,1:支持
var isUnionPay = window.parent.isUnionPay;//<%=termSpecial.charAt(4)%>;

//是否带有管理控件标志,0不支持,1:支持
var isManageConf = window.parent.isManageConf;// <%=termSpecial.charAt(5)%>; 

//是否支持读取二代身份证信息标志,0不支持,1:支持
var is2GIDFlag = window.parent.is2GIDFlag;//<%=termSpecial.charAt(8)%>;

//是否支持银联扣款接口 0不支持,1:支持 (湖北无此控件)
var isCardPay = window.parent.isCardPay;//<%=termSpecial.charAt(9)%>;

//是否支持SIM卡读卡器
var SIMreaderFlag = window.parent.SIMreaderFlag;// <%=termSpecial.charAt(6)%>;

// 控件检查频率 
var frequency = window.parent.frequency;// <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY)%>;

// 终端管理控件
function frmInitManageConf(sockaddr, logftpaddr) {
if (isManageConf == 1) {
    <%if (null != sockaddr || null != logftpaddr)
            {%>
    // 本机终端初始化
    try {
  		var initMC = window.parent.document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, "<%=cashInterval%>");
  		if (initMC == -1) {
  		  	showFrmErr("警告:终端控制器初始化失败！");
  			return 0;
  		}
  	}catch (e){
  		showFrmErr("警告:终端控制器初始化异常！");
  		return 0;
  	}
  	return 1;
  <%}
            else
            {%>
       showFrmErr("警告,无法获取终端机的IP和MAC地址!");
      return 0;
  <%}%>
  }else{
	  showFrmErr("警告,此终端机不支持终端控制功能!");
	  return 0;
  }
}


// 现金识币器初始化
function frmOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret =  window.parent.document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:现金识币器故障!");
			    return 0;
			}
		}catch (e) {
    	    showFrmErr("警告:现金识币器初始化异常!");
			return 0;
		}
		return 1;
	}else{
		showFrmErr("警告,此终端机不支持现金识币服务!");
		return 0;
	}
}

// 二代身份证初始化
function frmOpenIdCardReader(){
	if (is2GIDFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:身份证读卡器初始化故障!");
			     
			    return 0;
			}
			return 1;
		}catch(e){
		    showFrmErr("警告:身份证读卡器初始化异常!");
		     
			return 0;
		}
	}else{
		showFrmErr("警告,此终端机不支持身份证识别功能!");
		  return 0;
	}
}


// 初始化银联读卡器  传入 银联终端ID 银联商户号
 function frmInitReadUserCard() {
	if(isUnionPay == 1){
		try{
			var ret = 0;
			
			ret =  window.parent.document.getElementById("cardpluginid").InitConfig("<%=terminalInfo1.getUnionpaycode() %>", "<%=terminalInfo1.getUnionuserid() %>");
			
			
			if (ret != 0) {
			    showFrmErr("警告:银联读卡器初始化失败!");
			    return 0;
			}
			return 1;
		}catch(e){
    	    showFrmErr("警告:银联读卡器初始化异常!");
    	    return 0;
		}
	}else{
		
		showFrmErr("警告,此终端机不支持银联读卡功能!");
		return 0;
	}
	}

// 密码键盘
function frmInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//初始化加密键盘
		try {
			var ret =  window.parent.document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showFrmErr("警告:开启终端键盘串口失败！");
				return;
			}
			ret =  window.parent.document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showFrmErr("警告:设置键盘模式失败！");
				return;
			}
		}catch (e) {
  			showFrmErr("警告:密码键盘异常,无法使用密码键盘!");
			return;
		}
	}
}

// 初始化票据打印机
function frmInitListPrt() {
	
	 //是否支持打印票据标志,0不支持,1:支持
	if (isPrintFlag == 1) {
		try {
            //初始化票据打印机
			var initListPrt1 =  window.parent.document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showFrmErr("警告:票据打印机缺纸或故障!");
				return 0;
			} else if (initListPrt1 == 41) {
   			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
				return 0;
			}
			
        	//设置打印图标的路径 
			var initListPrt2 =  window.parent.document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showFrmErr("警告:票据打印机缺纸或故障!");
				return 0;
			} else {
				if (initListPrt2 == 41) {
    			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
					return 0;
				}
			}
			return 1;
		}catch (e) {
    		showFrmErr("警告:票据打印机初始化异常,无法打印小票!");
			return 0;
		}
	}else{
		showFrmErr("警告:此终端机不支持票据打印服务,无法打印小票!");
		return 0;
		
		}
	
	}
//检查票据打印机的状态
function checkPrintStatus(){
	
     // 0 正常 1 故障  2 缺纸
	if (isPrintFlag == 1) {
	      try {
            
			var pstate =  window.parent.document.getElementById("prtpluginid").GetPrinterStatus();
			if (pstate == 1) {
    		    showFrmErr("警告:票据打印机故障！");
    		      
				return  0;
			}else if(pstate == 2){
				 showFrmErr("警告:票据打印机缺纸！");
    		      
				return  0;
			}
			
        
			return 1;
		}catch (e) {
    		 showFrmErr("警告:检查票据打印机状态异常,无法打印小票！");
    		  
			return 0;
		}
	}else{
		showFrmErr("警告:此终端机不支持票据打印服务,无法打印小票!");
		  return 0;
	}
}

// 发票打印机初始化
function frmInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // 打开发票打印机串口
			var openCom =  window.parent.document.getElementById("invprtpluginid").OpenCom();
			if (openCom == 1) {
  	        	showFrmErr("警告:发票打印机串口故障!");
				return 0;
			} else {
				if (openCom == 61) {
	  			    showFrmErr("错误:发票打印机故障,无法初始化!");
					return 0;
				} else if (openCom == 65) {
	  			    showFrmErr("警告:发票打印机缺纸!");
					return 0;
				} else if (openCom != 0) {
	 			    showFrmErr("错误:打开设备异常,无法初始化发票打印机!");
					return 0;
				}
			}
			
			// 初始化发票打印机
			var initInvoicePrt =  window.parent.document.getElementById("invprtpluginid").InitVoicePrint();
			if (initInvoicePrt == 61) {
  		    	showFrmErr("错误:发票打印机故障,无法初始化!");
				return 0;
			} else if (initInvoicePrt == 65) {
 			    showFrmErr("警告:发票打印机缺纸!");
				return 0;
			}else if (openCom != 0) {
 			    showFrmErr("错误:打开设备异常,无法初始化发票打印机!");
				return 0;
			}
			
			// 检查发票打印机缺纸
<%--			var v = document.getElementById("invprtpluginid").CheckPaper();--%>
<%--			if (v != 0 ){--%>
<%--			    showFrmErr("警告:发票打印机缺纸或故障!");--%>
<%--			    return 0;--%>
<%--			}--%>
			return 1;
		}catch (e) {
  			showFrmErr("警告:发票打印机初始化异常,无法打印发票!");
			return 0;
		}
	}else{
		showFrmErr("警告:此终端机不支持发票打印服务,无法打印发票!");
		return 0;
	}
}
// 检查发票打印机有无纸
function checkInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // 打开发票打印机串口
			var ipaper =  window.parent.document.getElementById("invprtpluginid").CheckPaper();
			if (ipaper != 0) {
  	        	showFrmErr("警告:发票打印机串口故障!");
				return 0;
			}
			
		}catch (e) {
  			showFrmErr("警告:检查发票打印机有无纸异常,无法打印发票！");
			return 0;
		}
		return 1;
	}else{
		showFrmErr("警告:此终端机不支持发票打印服务,无法打印发票!");
		return 0;
	}
}

//打开读卡、写卡器
function openSimCardReader(){
	
	if (SIMreaderFlag == 1) {
		
		
		try{
			
			
			var ret =  window.parent.document.getElementById("simcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:SIM读卡器打开故障!");
			    
			    return 0;
			}
			return 1;
		}catch(e){
		   
		    showFrmErr("警告:SIM读卡器打开异常!");
		    
			return 0;
		}
	}else{
		showFrmErr("警告:此终端机不支持读取SIM卡功能!");
		return 0;
		  
	}
}
//读卡、写卡器初始化
function initSimCardReader(){
	
	if (SIMreaderFlag == 1) {
		
		
		try{
			
			
			var ret =  window.parent.document.getElementById("simcardpluginid").InitCard();
			if (ret != 0) {
			    showFrmErr("警告:SIM读卡器故障!");
			    
			    return 0;
			}
			return 1;
		}catch(e){
		   
		    showFrmErr("警告:SIM读卡器初始化异常!");
		    
			return 0;
		}
	}else{
		showFrmErr("警告:此终端机不支持读取SIM卡功能!");
		return 0;
		  
	}
}


function checkCardStatus()
{
	try
	{
		if (SIMreaderFlag == 1) 
		{
			//读卡过程
			var stat=window.parent.document.getElementById("simcardpluginid").IsCardExist();
			
			if(stat == 0)
			{
				return 1;
			}
			
			
			stat=window.parent.document.getElementById("simcardpluginid").ReadCardStatus();
			
			if(stat == -1)
			{
				goToExcPage("检查读卡器状态失败！");
				return 0;
			}
			
			var arr = stat.split('~');
			if(arr[2] == 0)
			{
			     goToExcPage("卡箱中都没有SIM卡！");
				 	 return 0;
			}
			
			stat=window.parent.document.getElementById("simcardpluginid").MoveCardToWrite();
			if(stat==-1)
			{
					 goToExcPage("移卡失败！");
					 return 0;
			}
		  
		  return 1;
	  }
		return 0;
	}
	catch(e)
	{
		goToExcPage("读取SIM卡信息过程出现异常！");
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
		
		//防止页面重复提交
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	//更正
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//这句话最关键
	}
}

function KeyIsNumber(KeyCode) 
{
	//只允许输入0-9
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
  //提示
   function openWindow(){
	  document.getElementById().innerHTML="<span class='yellow'>你已经选择了产品，不能再选择！</span>";
	  wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
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
							选号开户流程
						</h2>
						<div class="blank10"></div>
						<a href="#" class="on">1. 入网协议确认</a>
						<a href="#">2. 身份证信息记取</a>
						<a href="#">3. 产品选择</a>
						<a href="#">4. 号码选择</a>
						<a href="#">5. 费用确认</a>
						<a href="#">6. 开户缴费</a>
						<a href="#">7. 取卡打印发票</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank30"></div>
						<div class="p40">
							<img src="${sessionScope.basePath}images/loading.gif"
								alt="设备检查中..." />
							<font size="6">设备检查中，请稍候......</font>

						</div>

						<div class=" clear"></div>

					</div>
					<div class="openwin_tip" id="openWin1">
						<div class="bg"></div>
						<div class=" blank60"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<p class="fs28 lh2 pl142" id="winText" name="winText">
							发票打印机故障，本次开户不能打印发票是否继续？
						</p>
						<div class="tc">
							<div class=" clear "></div>
							<div class=" blank30 "></div>
							<a title="确认" href="javascript:void(0)" class=" bt4"
								onmousedown="this.className='bt4on'"
								onmouseup="this.className='bt4';document.getElementById('confirmF').value='1';wiWindow.close();">是</a>
							<a title="" href="javascript:void(0)" class=" bt4 ml20"
								onmousedown="this.className='bt4on ml20'"
								onmouseup="this.className='bt4 ml20';document.getElementById('confirmF').value='0';wiWindow.close()">否</a>
						</div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>

		</form>
	</body>

	<script type="text/javascript">
//页面加载时检查设备
function bodyLoad() {
	// 终端管理控件
	if (frmInitManageConf(sockaddr, logftpaddr) == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}
	//读卡、写卡器
	if (initSimCardReader() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}
	if (openSimCardReader() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}

	if (checkCardStatus() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}

	// 身份证读卡器
	if (frmOpenIdCardReader() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}
	// 票据打印机 
	if (frmInitListPrt() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}
	if (checkPrintStatus() == 0) {
		goToExcPage("终端机设备异常（" + errorMSG + "），暂不能受理选号开户业务！");
		return;
	}

	// 现金识币器  
<%--	var cash = frmOpenCashEquip();--%>
	// 银联读卡器
<%--	var ccard = frmInitReadUserCard();--%>
<%--    if(cash+ccard==0){--%>
<%--    	 goToExcPage();--%>
<%--    	 return ;--%>
<%--    	}else if(cash==0){--%>
<%--    			if(!confirm("现金识币器故障，本次开户只能用银联卡开户，是否同意？")){--%>
<%--    				 goToExcPage("终端机设备异常（现金识币器故障），暂不能受理选号开户业务！");--%>
<%--    				 return ;--%>
<%--    			}--%>
<%--    	}else if(ccard==0){--%>
<%--    			if(!confirm("银联读卡器故障，本次开户只能用现金开户，是否同意？")){--%>
<%--    				 goToExcPage("终端机设备异常（银联读卡器故障），暂不能受理选号开户业务！");--%>
<%--    				 return ;--%>
<%--    			}--%>
<%--    	}--%>
     if(frmOpenCashEquip()==0){
    	  goToExcPage("终端机设备异常（"+errorMSG+"），暂不能受理选号开户业务！");
    				 return ;
    	 }
    	 // 发票打印机
    	 var iniInvo=frmInitInvoicePrint();
    	 var errorMSGf=errorMSG;
    	 var cheInvo=checkInvoicePrint();
    	 
     if(iniInvo+cheInvo!=2){
    	 openWindow();
    	 
	   if(document.getElementById('confirmF').value==0){
		    goToExcPage("终端机设备异常（"+errorMSGf+errorMSG+"），暂不能受理选号开户业务！");
	        return ;
	        }
	 }  
    	doSub("getRuleInfo.action");
    	 
    	
    //到协议确认页面
    // window.location="${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
   // 密码键盘
   // frmInitKeyBoard();
   }
</script>
		<script type="text/javascript">
removeAclick();
</script>
</html>

