<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%

session.removeAttribute(Constants.USER_INFO);

//省份
String province = (String) PublicCache.getInstance().getCachedData("ProvinceID");

String onOffFrequency =  (String) PublicCache.getInstance().getCachedData(Constants.SH_ON_OFF_FREQUENCY);
   
// 终端机信息
TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 控件支持标志
String termSpecial = terminalInfo.getTermspecial();

// 控件加载字符串
String plugin = terminalInfo.getPlugin();

// socket地址(终端配置表中配置,状态上传等)
String sockaddr = "";
if (terminalInfo != null)
{
	sockaddr = (String)PublicCache.getInstance().getCachedData("SH_SOCKADDR_" + terminalInfo.getRegion());
}

// FTP地址(终端机日志上传的FTP地址,用户名与密码终端厂商自已配置)
String logftpaddr = (String) PublicCache.getInstance().getCachedData(Constants.SH_LOGFTPADDR);
if (logftpaddr == null)
{
	logftpaddr = "";
}

// 日志上传间隔分钟数
String cashInterval = (String) PublicCache.getInstance().getCachedData("SH_CASHINTERVAL");
if (cashInterval == null || "".equals(cashInterval.trim()))
{
	cashInterval = "60";
}

//add begin cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
// 页面渐进效果时间
String lateTime = "0.5";

if(Constants.PROOPERORGID_NX.equals(province))
{
	lateTime = "0.0";
}
//add end cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果

//add begin by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
 //获取电子发票特性参数，true为开启
 String isElectronInvoice = CommonUtil.getDictNameById(terminalInfo.getRegion(), "SH_ELECTRON_INVOICE");
 //add end by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
%>
<script type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
<script language="javascript">

// modify begin by qWX279398 DTS2015111900633
// 短信验证码当前验证时间
var currLeftTime = "";

// 短信验证码时手机号
var currNumber = "";

// 可选服务密码页面和省份证认证页面跳转标志
var currFlag = "";
// modify end by qWX279398 DTS2015111900633

// socket地址(状态上传时间同步等工作,此配置在参数表在配置SH_SOCKADDR)
var sockaddr = '<%=sockaddr %>';

// FTP地址(上传终端机日志到指定ftp服务器,此配置在参数表表配置SH_LOGFTPADDR)
var logftpaddr = '<%=logftpaddr %>';

//是否支持打印票据标志,0不支持,1:支持
var isPrintFlag = <%=termSpecial.charAt(0)%>;

//是否支持打印发票标志,0不支持,1:支持
var isInvoicePrint = <%=termSpecial.charAt(1)%>;

//是否支持加密键盘标志,0不支持,1:支持
var isKeyBoard = <%=termSpecial.charAt(2)%>;

//是否支持现金缴费标志,0不支持,1:支持
var isCashEquip = <%=termSpecial.charAt(3)%>;

//是否支持银行卡缴费标志,0不支持,1:支持
var isUnionPay = <%=termSpecial.charAt(4)%>;

//是否带有管理控件标志,0不支持,1:支持
var isManageConf = <%=termSpecial.charAt(5)%>; 
  
//是否支持读取二代身份证信息标志,0不支持,1:支持
var is2GIDFlag = <%=termSpecial.charAt(8)%>;

//是否支持银联扣款接口 0不支持,1:支持 (湖北无此控件)
var isCardPay = <%=termSpecial.charAt(9) %>

// 控件检查频率 
var frequency = <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY) %>;
//是否支持SIM卡读卡器
var SIMreaderFlag = <%=termSpecial.charAt(6)%>;

// 终端管理控件
function frmInitManageConf(sockaddr,logftpaddr) {
  if (isManageConf == 1) {
    <%if(null != sockaddr || null != logftpaddr) {%>
    // 本机终端初始化
    try {
  		var initMC = document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, "<%=cashInterval %>");
  		if (initMC == -1) {
  		  	showFrmErr("警告:终端控制器初始化失败！");
  			return;
  		}
  	}catch (e){
  		showFrmErr("警告:终端控制器初始化失败！");
  		return;
  	}
  <%}%>
  }
}

// 现金识币器初始化
function frmOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret = document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:现金识币器故障!&nbsp;&nbsp;");
			    return;
			}
		}catch (e) {
    	    showFrmErr("警告:现金识币器故障!&nbsp;&nbsp;");
			return;
		}
	}
}

// 二代身份证初始化
function frmOpenIdCardReader(){
	if (is2GIDFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:身份证读卡器故障!&nbsp;&nbsp;");
			    return;
			}
		}catch(e){
		    showFrmErr("警告:身份证读卡器故障!&nbsp;&nbsp;");
			return;
		}
	}
}


// 初始化银联读卡器  传入 银联终端ID 银联商户号
function frmInitReadUserCard() {
	if(isUnionPay == 1){
		try{
			var ret = 0;
			<%
			if (Constants.PROOPERORGID_HUB.equals(province)){// 湖北
			%>
				ret = document.getElementById("cardpluginid").InitConfig("<%=terminalInfo.getUnionpaycode() %>", "<%=terminalInfo.getUnionuserid() %>");
			<%
			}
			else if (Constants.PROOPERORGID_SD.equals(province))// 山东
			{
			}
			else if (Constants.PROOPERORGID_NX.equals(province))// 宁夏
			{
			%>
				ret = document.getElementById("cardpluginid").InitReadUserCard();
			<%
			}
			%>
			if (ret != 0) {
			    showFrmErr("警告:银联读卡器初始化失败!&nbsp;&nbsp;");
			    return;
			}
		}catch(e){
    	    showFrmErr("警告:银联读卡器初始化异常!&nbsp;&nbsp;");
    	    return;
		}
	}
}

// 密码键盘
function frmInitKeyBoard() {
	if (isKeyBoard == 1) {
    	//初始化加密键盘
		try {
			var ret = document.getElementById("keybrdpluginid").OpenCom();
			if (ret != 0) {
  				showFrmErr("警告:开启终端键盘串口失败！");
				return;
			}
			ret = document.getElementById("keybrdpluginid").SetWorkMode(0);
			if (ret != 0) {
  				showFrmErr("警告:设置键盘模式失败！");
				return;
			}
		}catch (e) {
  			showFrmErr("警告:密码键盘故障,无法使用密码键盘!");
			return;
		}
	}
}

// 票据打印机初始化
function frmInitListPrt() {
     //是否支持打印票据标志,0不支持,1:支持
	if (isPrintFlag == 1) {
		try {
            //初始化票据打印机
			var initListPrt1 = document.getElementById("prtpluginid").InitListPrinter();
			if (initListPrt1 == 1) {
    		    showFrmErr("警告:票据打印机缺纸或故障!");
				return;
			} else if (initListPrt1 == 41) {
   			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
				return;
			}
			
        	//设置打印图标的路径 
			var initListPrt2 = document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showFrmErr("警告:票据打印机缺纸或故障!");
				return;
			} else {
				if (initListPrt2 == 41) {
    			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
					return;
				}
			}
		}catch (e) {
    		showFrmErr("发生异常,票据打印机初始化失败,无法打印小票!");
			return;
		}
	}
}

// 发票打印机
function frmInitInvoicePrint() {
	if (isInvoicePrint == 1) {
		try {
		    // 打开发票打印机串口
			var openCom = document.getElementById("invprtpluginid").OpenCom();
			if (openCom == 1) {
  	        	showFrmErr("警告:发票打印机串口故障!");
				return;
			} else {
				if (openCom == 61) {
	  			    showFrmErr("错误:发票打印机故障,无法初始化!");
					return;
				} else if (openCom == 65) {
	  			    showFrmErr("警告:发票打印机缺纸!");
					return;
				} else if (openCom != 0) {
	 			    showFrmErr("错误:打开设备异常,无法初始化发票打印机!");
					return;
				}
			}
			
			// 初始化发票打印机
			var initInvoicePrt = document.getElementById("invprtpluginid").InitVoicePrint();
			if (initInvoicePrt == 61) {
  		    	showFrmErr("错误:发票打印机故障,无法初始化!");
				return;
			} else if (initInvoicePrt == 65) {
 			    showFrmErr("警告:发票打印机缺纸!");
				return;
			}else if (openCom != 0) {
 			    showFrmErr("错误:打开设备异常,无法初始化发票打印机!");
				return;
			}
			
			// 检查发票打印机缺纸
			var v = document.getElementById("invprtpluginid").CheckPaper();
			if (v != 0 ){
			    showFrmErr("警告:发票打印机缺纸或故障!");
			    return;
			}
			
		}catch (e) {
  			showFrmErr("警告:发票打印机初故障,无法打印发票!");
			return;
		}
	}
}




//读卡、写卡器daikai
function openSimCardReader(){
	 if(SIMreaderFlag==1){try{ 
			
			var ret=document.getElementById("simcardpluginid").OpenCom();
			
			if (ret != 0) {
			    showFrmErr("警告:SIM读卡器故障!&nbsp;&nbsp;");
			    
			   
			}
			
		}catch(e){
		   
		    showFrmErr("警告:SIM读卡器故障!&nbsp;&nbsp;");
		    
			
		}
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

var first_frame = 0;

// add begin lWX5316086 终端监控平台

// 插入开机信息
function termOn(){
	var url = "${sessionScope.basePath }login/termOnOff.action";
	var postStr = "OK";

	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
				
		//插入信息失败
		if (resXml != "1" || resXml != 1)
		{
			return;
		}								
	}, null, "POST", postStr, null);
}   

// 每隔15分钟发送终端信息  
function getTermStatus()
{
	// modify begin hWX5316476 2014-9-26 V200R003C10LG1001 OR_SD_201407_1077_自助终端管理平台告警及维护管理优化的需求
	<%
	if (StringUtils.isEmpty(onOffFrequency)){// 湖北 宁夏
	%>
		window.setInterval(updateTermStatus,1000*60*15);
	<%
	}
	else// 山东
	{
	%>
		// 每隔cycleTime 分钟 发送一次终端信息
		var cycleTime = <%=onOffFrequency%>;
		window.setInterval(updateTermStatus, 1000*60*cycleTime);
	<% 
	}
	%>
	// modify end hWX5316476 2014-9-26 V200R003C10LG1001 OR_SD_201407_1077_自助终端管理平台告警及维护管理优化的需求
}

// 更新心跳数据
function updateTermStatus()
{

	var url = "${sessionScope.basePath }login/updateTermHeart.action";
	var postStr = "OK";

	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
				
		//插入信息失败
		if (resXml != "1" || resXml != 1)
		{
			return;
		}								
	}, null, "POST", postStr, null);
	
}

// add end lWX5316086 终端监控平台

// 框架加载时初始化控件
function bodyLoad(){

	//设备开启
	termOn();
	
	//每隔15分钟发送状态信息
	getTermStatus();
	
	// 校验设备可用性
	chkEquipment();
}

// 校验设备可用性
function chkEquipment()
{
	if (viewErrDiv != null)
	{
		viewErrDiv.innerHTML = "";
		viewErr = "";
	}
	
	if (frequency == 1){
	    
	    // 票据打印机
	    frmInitListPrt();
	    var isElectronInvoice = "<%=isElectronInvoice %>";
	    if ("true" != isElectronInvoice)
        {
		    // 打印电子发票 不检查发票打印机
		    frmInitInvoicePrint();
        }
    }
    
    // 现金识币器
    frmOpenCashEquip();

    // 银联读卡器
    frmInitReadUserCard();
    
    // 终端管理控件
    frmInitManageConf(sockaddr,logftpaddr);
    
    // 身份证读卡器
    frmOpenIdCardReader();
    
    // 密码键盘
    frmInitKeyBoard();
     // dukaqi
    openSimCardReader();
}

<%
	// 主动检查硬件设备是否可用开关(0：关闭，1：开启)
	String chkDeviceSwitch = (String) PublicCache.getInstance().getCachedData("SH_DEVICE_CHECK_SWITCH");
	
	// 开启主动检查硬件设备开关
	if ("1".equals(chkDeviceSwitch))
	{
		// 主动检查硬件设备时间间隔(单位：分钟)
		String chkDeviceTime = (String) PublicCache.getInstance().getCachedData("SH_DEVICE_CHECK_TIME");
		if (chkDeviceTime == null || "".equals(chkDeviceTime.trim()))
		{
			chkDeviceTime = "30";
		}
%>
		// 校验设备设定时间间隔
		var chkEquipmentPeriod = <%=chkDeviceTime %> * 60 * 1000;
		  
		// 记录当前时间
		var chkEquipmentCurTime = new Date().getTime();
		
		// 时间间隔校验设备
		function chkEquipmentInterval() 
		{
			// 计算等待时间
			var chkEquipmentDualTime = parseInt(new Date().getTime() - chkEquipmentCurTime) ;
			
			// 判断等待时间大于校验设备设定时间间隔时，开始校验设备可用性
			if (chkEquipmentDualTime > chkEquipmentPeriod)
			{
				// 取得iFrame对象及访问的url
				var mainIFrame = document.frames("mainfrm");
				var locaStr = mainIFrame.window.location.href;
				
				// 判断访问url是首页或屏保时，校验设备的可用性
				if (locaStr.indexOf("goHomePage.action") > 0 || locaStr.indexOf("selfScreen.jsp") > 0)
				{
					// 校验设备可用性
					chkEquipment();
					
					// 重新记录当前时间
					chkEquipmentCurTime = new Date().getTime();
				}
				
				// 判断访问url是屏保时，如果硬件校验有问题，则需要关闭屏保
				if (locaStr.indexOf("selfScreen.jsp") > 0 && viewErr != "") 
				{
					// 关闭屏保
					mainIFrame.screenCancel();
				}
			}
		}
		
		// 设定每秒执行一次
		window.setInterval("chkEquipmentInterval()", 1000);
<%
	}
%>


<%-- add begin hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面 --%>

<%
	// 系统升级页面跳转周期（秒）
	String chkCycleTime =  (String) PublicCache.getInstance().getCachedData("SH_SYSUPD_CHKCYCLE_TIME");
	
	if(StringUtils.isEmpty(chkCycleTime))
	{
		chkCycleTime = "120";
	}
%>

var sysChkCycleTime = '<%=chkCycleTime%>';

function chkSysUpdateInterval()
{
	var url = "${sessionScope.basePath }login/qrySysUpdateTipPage.action";

	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
				
		if (resXml == "1" || resXml == 1)
		{
			top.mainfrm.location.replace("${sessionScope.basePath }login/goSysUpdateTipPage.action");
		}								
	}, null, "POST", null, null);
	
}
<%
	// 是否周期检测系统升级
	String isChk = (String) PublicCache.getInstance().getCachedData("SH_ISCHK_SYSUPDATE");
	
	if("1".equals(isChk))
	{
%>
	// 设定每sysChkCycleTime秒执行一次
	window.setInterval("chkSysUpdateInterval()", sysChkCycleTime*1000);
<% 
	}
%>
<%-- add end hWX5316476 2014-12-5 OR_HUB_201408_628_湖北_新增自助终端上线提醒页面 --%>
</script>
<html>
	<head>
	<title>NG2.3自助终端系统</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="0">	
	<meta http-equiv="Page-Exit"; content="blendTrans(Duration=<%=lateTime %>)"> 
	</head>
	<body scroll="no" onload="bodyLoad();downloadPromptList();" style="margin: 0 0 0 0">
	
		<iframe height="100%" width="100%" align="top" frameborder="0" name="mainfrm" id="mainfrm" src="login/index.action"></iframe>
	</body>
	<%=plugin %>
</html>
<script language="javascript">
// 下载语言提示信息
function downloadPromptList()
{
	<%
	if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
			&& "1".equals((String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
	{
	%>
		// 本地存放路径 参数表中配置：SH_LOCAL_FILE_PATH
		var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';
		
		// 远程存放路径 应用下面的文件路径
		var remoteURL = '${sessionScope.basePath }<%=Constants.PROMPT_FILE_RELATIVELY_PATH %>';
		
		// 创建FileSystemObject对象
		var fileSysObj = new ActiveXObject("Scripting.FileSystemObject");
		
		// 本地文件夹
		if (!fileSysObj.FolderExists(localURL))
		{
			fileSysObj.CreateFolder(localURL);
		}
		
		// Charge-01.wav：尊敬的用户，欢迎您使用中国移动自助服务终端，请点击屏幕上您需要的服务，谢谢！
		if (!fileSysObj.FileExists(localURL + "/Charge-01.wav") && isResourceExist(remoteURL + "/Charge-01.wav"))
		{
			downloadFile(remoteURL + "/Charge-01.wav",localURL + "/Charge-01.wav");
		}
		
		// Charge-02.wav：请输入您的手机号码
		if (!fileSysObj.FileExists(localURL + "/Charge-02.wav") && isResourceExist(remoteURL + "/Charge-02.wav"))
		{
			downloadFile(remoteURL + "/Charge-02.wav",localURL + "/Charge-02.wav");
		}
		
		// Charge-03.wav：请再次输入您的手机号码，并点击‘确认’键
		if (!fileSysObj.FileExists(localURL + "/Charge-03.wav") && isResourceExist(remoteURL + "/Charge-03.wav"))
		{
			downloadFile(remoteURL + "/Charge-03.wav",localURL + "/Charge-03.wav");
		}
		
		// Charge-04.wav：请选择支付方式
		if (!fileSysObj.FileExists(localURL + "/Charge-04.wav") && isResourceExist(remoteURL + "/Charge-04.wav"))
		{
			downloadFile(remoteURL + "/Charge-04.wav",localURL + "/Charge-04.wav");
		}
		
		// Charge-05.wav：请在投币口投入纸币，并点击确认键确认交费
		if (!fileSysObj.FileExists(localURL + "/Charge-05.wav") && isResourceExist(remoteURL + "/Charge-05.wav"))
		{
			downloadFile(remoteURL + "/Charge-05.wav",localURL + "/Charge-05.wav");
		}
		
		// Charge-06.wav：您的充值交费已成功，如果需要，请选择打印小票或发票或选择继续充值交费，谢谢
		if (!fileSysObj.FileExists(localURL + "/Charge-06.wav") && isResourceExist(remoteURL + "/Charge-06.wav"))
		{
			downloadFile(remoteURL + "/Charge-06.wav",localURL + "/Charge-06.wav");
		}
	<%
	}
	%>
}

// js下载文件
function downloadFile(strRemoteURL,strLocalURL) 
{ 
	try{ 
	<%
		// 为防止被骗杀毒软件杀掉
	    out.println("var xmlHTTP;");
	    out.println("if(window.ActiveXObject){");
	    out.println("xmlHTTP=new ActiveXObject( \"Microsoft.XMLHTTP\");");
	    out.println("}else if(window.XMLHttpRequest){");
	    out.println("xmlHTTP=new XMLHttpRequest();");
	    out.println("}");
	    out.println("xmlHTTP.open( \"Get\",strRemoteURL,false); ");
	    out.println("xmlHTTP.send();"); 
	    out.println("var adodbStream=new ActiveXObject( \"ADODB.Stream\");"); 
	    out.println("adodbStream.Type=1;");
	    out.println("adodbStream.Open();"); 
	    out.println("adodbStream.write(xmlHTTP.responseBody);"); 
	    out.println("adodbStream.SaveToFile(strLocalURL,2);"); 
	    out.println("adodbStream.Close();"); 
	    out.println("adodbStream=null;"); 
	    out.println("xmlHTTP=null;"); 
	%>
	}catch(e){}
}

// 判断服务器资源是否存在
function isResourceExist(url) {  
	var xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");  
	xmlhttp.open("GET", url, false);  
	xmlhttp.send();  
	if (xmlhttp.readyState == 4) {  
	    if (xmlhttp.Status != 200)  
	        return false; 
	    return xmlhttp.Status == 200;  
	}  
	return true;  
}  
</script>