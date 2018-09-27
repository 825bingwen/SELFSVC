<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    //省份
    String province = (String)PublicCache.getInstance().getCachedData("ProvinceID");
    
    // 终端机信息
    TerminalInfoPO terminalInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
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
//控件检查
// socket地址(状态上传时间同步等工作,此配置在参数表在配置SH_SOCKADDR)
var sockaddr = '<%=sockaddr%>';

// FTP地址(上传终端机日志到指定ftp服务器,此配置在参数表表配置SH_LOGFTPADDR)
var logftpaddr = '<%=logftpaddr%>';

//是否支持打印票据标志,0不支持,1:支持
var isPrintFlag= <%=termSpecial.charAt(0)%>;

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
var isCardPay = <%=termSpecial.charAt(9)%>
//是否支持SIM卡读卡器
var SIMreaderFlag = <%=termSpecial.charAt(7)%>

// 控件检查频率 
var frequency = <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY)%>;

// 终端管理控件
function frmInitManageConf(sockaddr,logftpaddr) {
  if (isManageConf == 1) {
    <%if (null != sockaddr || null != logftpaddr)
            {%>
    // 本机终端初始化
    try {
  		var initMC = document.getElementById("mgrpluginid").InitManageConf(sockaddr, logftpaddr, "<%=cashInterval%>");
  		if (initMC == -1) {
  		  	showFrmErr("警告:终端控制器初始化失败！");
  			return 0;
  		}
  	}catch (e){
  		showFrmErr("警告:终端控制器初始化失败！");
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

// 现金识币器初始化
function frmOpenCashEquip() {
	if (isCashEquip == 1) {
		try {
			var ret = document.getElementById("cashpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:现金识币器故障!&nbsp;&nbsp;");
			    return 0;
			}
		}catch (e) {
    	    showFrmErr("警告:现金识币器故障!&nbsp;&nbsp;");
			return 0;
		}
		return 1;
	}else{
		
		return 0;
	}
}

// 二代身份证初始化
function frmOpenIdCardReader(){
	if (is2GIDFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("idcardpluginid").OpenCom();
			if (ret != 0) {
			    showFrmErr("警告:身份证读卡器故障!&nbsp;&nbsp;");
			      goToExcPage();
			    return;
			}
		}catch(e){
		    showFrmErr("警告:身份证读卡器故障!&nbsp;&nbsp;");
		      goToExcPage();
			return;
		}
	}else{
		  goToExcPage();
	}
}


// 初始化银联读卡器  传入 银联终端ID 银联商户号
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
			    showFrmErr("警告:银联读卡器初始化失败!&nbsp;&nbsp;");
			    return 0;
			}
		}catch(e){
    	    showFrmErr("警告:银联读卡器初始化异常!&nbsp;&nbsp;");
    	    return 0;
		}
		return 1;
	}else{
		return 0;
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
    		      goToExcPage();
				return;
			} else if (initListPrt1 == 41) {
   			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
   			      goToExcPage();
				return;
			}
			
        	//设置打印图标的路径 
			var initListPrt2 = document.getElementById("prtpluginid").SetPicturePath("");
			if (initListPrt2 == 1) {
    		    showFrmErr("警告:票据打印机缺纸或故障!");
    		      goToExcPage();
				return;
			} else {
				if (initListPrt2 == 41) {
    			    showFrmErr("错误:票据打印机设备低层驱动程序未安装!");
    			      goToExcPage();
					return;
				}
			}
		}catch (e) {
    		showFrmErr("发生异常,票据打印机初始化失败,无法打印小票!");
    		  goToExcPage();
			return;
		}
	}else{
		
		  goToExcPage();
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
			var initInvoicePrt = document.getElementById("invprtpluginid").InitVoicePrint();
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
			var v = document.getElementById("invprtpluginid").CheckPaper();
			if (v != 0 ){
			    showFrmErr("警告:发票打印机缺纸或故障!");
			    return 0;
			}
			
		}catch (e) {
  			showFrmErr("警告:发票打印机初故障,无法打印发票!");
			return 0;
		}
		return 1;
	}else{
		return 0;
	}
}
//读卡、写卡器
function initSimCardReader(){
	if (SIMreaderFlag == 1) {
		try{
			var ret = window.parent.document.getElementById("simcardpluginid").InitCard();
			if (ret != 0) {
			    showFrmErr("警告:SIM读卡器故障!&nbsp;&nbsp;");
			    goToExcPage();
			    return;
			}
		}catch(e){
		    showFrmErr("警告:SIM读卡器故障!&nbsp;&nbsp;");
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

// 下一页
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
							</div>
						</div>

						<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<%=plugin%>
</html>
<script type="text/javascript">

//页面加载时检查设备
function bodyLoad() {
	// 终端管理控件
	if (frmInitManageConf(sockaddr, logftpaddr) == 0)
		goToExcPage();
	//读卡、写卡器
	initSimCardReader();
	// 身份证读卡器
	frmOpenIdCardReader();
	// 票据打印机 
	frmInitListPrt();
	// 现金识币器  
	var cash = frmOpenCashEquip();
	// 银联读卡器
	var ccard = frmInitReadUserCard();
    if(cash+ccard==0){
    	 goToExcPage();
    	}else if(cash==0){
    			if(!confirm("现金识币器故障，本次开户只能用银联卡开户，是否同意？")){
    				 goToExcPage();
    			}
    	}else if(ccard==0){
    			if(!confirm("银联读卡器故障，本次开户只能用现金开户，是否同意？")){
    				 goToExcPage();
    			}
    	}
    	 // 发票打印机
     if(frmInitInvoicePrint()==0){
	   if(!confirm("发票打印机故障，本次开户不能打印发票是否继续？"))
		    goToExcPage();
	 }  
    //到协议确认页面
     window.location="${sessionScope.basePath}login/goHomePage.action?timeoutFlag=<s:property value='timeoutFlag' />";
   // 密码键盘
   // frmInitKeyBoard();
    
}
        
		
</script>
