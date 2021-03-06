<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	           
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}

	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	String subsName = (String) request.getAttribute("subsName");
	if (subsName == null)
	{
		subsName = "";
	}
	
	//  弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	// 是否支持金属键盘操作。1，支持
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	
	// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
	String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/MoveCardManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 缴费类型
var payType = "<s:property value='payType' />";

// 是否退还银联卡
var needReturnCard = "<s:property value='needReturnCard' />";

//取卡时间30秒
var limitTime = 30;

document.onkeydown = pwdKeyboardDown;

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

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//返回/退出(R)
	if(key == 82)
	{
		goback("<s:property value = 'curMenuId'/>");
	}
	//打印小票(1)
	if (key == 49)
	{
		printReceipt();
	}
	//缴费完成(2)
	if (key == 50)
	{
		goHomePage();
	}
	// 弹出提示框使用金属键盘按键关闭
	else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
	{
		try
		{
			wiWindow.close();
		}
		catch (ex){}
		
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		
		writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />退出在线开户功能");
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.forms[0].submit();
	}
}		
	
// 读取读卡器取卡状态
function takeOutBankCardStatus() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "监控用户是否已取卡");
					
	limitTime = limitTime - 1;
	
	if (limitTime <= 0)
	{
		//超时，清除定时任务，同时吞卡
		clearInterval(waitingToken);
		
		writeDetailLog("<%=chargelog_detailflag %>", "用户在规定时间内未取卡，吞卡");					
		
		captureUserCard();
		
		return;
	}
	
	try 
	{
		// 获取读卡器取卡状态
		var ret = TakeOutUserCardStatus();
		
		if (ret == "0" || ret == 0) 
		{	
			//已经取走卡，清除定时任务
			clearInterval(waitingToken);
			
			writeDetailLog("<%=chargelog_detailflag %>", "用户已取卡");									
		}			
	}
	catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

// 循环吐卡
function startClock()
{
	try 
	{
		waitingToken = setInterval("takeOutBankCardStatus()", 1000);
	}
	catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}			

function doFinish()
{
	writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />交费成功");
	
	// 缴费方式，1：银联卡；4：现金
	if (payType == 1 && needReturnCard == "1")
	{
		// 初始化
		var ret = InitReadUserCard();

		// 退卡
		ret = TakeOutUserCard();
		
		//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
		if (ret == "0")
		{
			writeDetailLog("<%=chargelog_detailflag %>", "吐卡成功");
			
			if ("1" == "<%=needCaptureCard %>")
			{
				startClock();
			}
		}
		else if (ret != "0")
		{
			// 吐卡异常
			writeDetailLog("<%=chargelog_detailflag %>", "吐卡异常");
		}
	}
		finishCard();
}

// 打印小票
var printReceiptFlag = 0;
function printReceipt()
{
	writeDetailLog("<%=chargelog_detailflag %>", "<s:property value='telnum' />选择打印小票");
	
	if (printReceiptFlag == 0)
	{
		printReceiptFlag = 1;
		
		// 按钮按下，置不可用状态
		document.getElementById('dayinButton').className = 'hover';
		document.getElementById('dayinButton').onmousedown = "";
		document.getElementById('dayinButton').onmouseup = "";
		document.getElementById('dayinButton').onclick = "";
		
		// 手机号
		var ptelnum = "<s:property value='telnum' />";
		
		// 操作员工号
		var pOperID = "<%=termInfo.getOperid() %>";  
		
		// 交易流水号(缴费记录编号）
		var pDealNum = "<s:property value='dealNum' />";
		
		// 交易时间
		var pDealTime = "<s:property value='dealTime' />";
		
		// 交易金额
		var tMoney = "<s:property value='tMoney' />元"; 
		
		// 姓名
		var pSubsName = "<s:property value='idCardPO.idCardName' />";
		
		//打印凭证
		if ("<%=canPrintReceipt %>" == "1")
		{	
			writeDetailLog("<%=chargelog_detailflag %>", "自动打印凭条");
			
			doPrintPayProofProdInstall_NX(ptelnum, pOperID, pDealNum, pDealTime, tMoney, pSubsName);
		
			document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。";
			
		}
	}
}
	
//后台开户成功后转到业务受理成功页面，并吐出SIM，打印业务受理凭条，界面提示受理成功并提醒用户取卡。
function finishCard()
{
	var params1 = "installId=" + document.getElementById("installId").value;
	
	// 将卡移出			
	var ret = MoveOutCard();
	
	// 关闭卡，若失败则是硬件出现问题
	var status = CloseMoveCard();
	
	if( ret != 0)
	{
		params1 = params1+"&notes=1";
		
		var url = "${sessionScope.basePath }prodInstall/updateInstallLogNotes.action";
		var loader = new net.ContentLoader(url, netload = function () {}, null, "POST", params1, null);	
		setException("吐卡操作异常！吐卡操作异常！请带受理凭条到营业厅办理！");
	}
	else
	{
		params1 = params1+"&notes=0";
		
		var url = "${sessionScope.basePath }prodInstall/updateInstallLogNotes.action";
		var loader = new net.ContentLoader(url, netload = function () {}, null, "POST", params1, null);	
	}
	
}
	
//出现异常
function setException(errorMsg) 
{
	// 提交标记
	exSubmitFlag = 1;	
	
	writeDetailLog("<%=chargelog_detailflag %>", "开户银联卡交费异常：" + errorMsg);
		
	//清除定时任务
	clearInterval(timeToken);

	document.getElementById("errormessage").value = errorMsg;
	
	//异常处理，只要出现了异常就要记录日志
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }prodInstall/installError.action";
	document.actForm.submit();
}
</script>
</head>
<body onload="window.focus();" scroll="no">
<form name="payMoneyForm" method="post">
	<!-- 身份证信息 -->
	<!-- 姓名 -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- 性别 -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
	<!-- 身份证号码 -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- 证件地址 -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
	<!-- 开始时间 -->
	<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
	<!-- 结束时间 -->
	<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
	<!-- 卡信息 -->
	<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
	<!-- 照片信息 -->
	<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
	
	<!-- 套餐信息 -->
	<!-- 模板ID -->
	<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
	<!-- 模板名称 -->
	<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
	<!-- 产品ID -->
	<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
	<!-- 产品名称 -->
	<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
	<!-- 品牌 -->
	<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
	<!-- 套餐月费 -->
	<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
	<!-- 预存费用 -->
	<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
	
	<!-- 选号信息 -->
	<!-- 地市 -->
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
	<!-- 组织机构ID -->
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<!-- 地市名称 -->
	<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
	<!-- 选号规则 -->
	<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
	<!-- 前缀 -->
	<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
	<!-- 后缀 -->
	<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
	<!--空白卡序列号 -->
	<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
	<!--手机号码 -->
	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="iccid" value="<s:property value='iccid'/>" />
	<!--短消息中心号码 -->
	<input type="hidden" id="smsp" name="smsp" value="<s:property value='smsp'/>" />
	<!-- 产品ID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	<!-- 服务密码 -->
	<input type="hidden" id="pwd" name="pwd" value="<s:property value='pwd'/>"/>
	
	<!-- 缴费信息 -->
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	<!-- 投币器流水号 -->
	<input type="hidden" id="terminalSeq" name="terminalSeq" value="<s:property value='terminalSeq'/>"/>
	<!-- 实际缴费金额 -->
	<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
	
	<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'/>
	<input type="hidden" id="invoiceType" name="invoiceType" value=""/>
	
	<%-- 受理编号 --%>
	<input type="hidden" id="dealNum" name="dealNum" value='<s:property value="dealNum" />'/>
	<input type="hidden" id="dealTime" name="dealTime" value='<s:property value="dealTime" />'/>
	<input type="hidden" id="balance" name="balance" value='<s:property value="balance" />'/>
	<input type="hidden" id="subsName" name="subsName" value="<%=subsName %>"/>
	
	<%-- 开户日志 --%>
	<%-- 流水号 --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- 缴费流水号 --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- 缴费方式，1：银联卡；4：现金 --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
	<%-- 实收费用 --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>
    <%-- 默认2：初始状态 0：写卡成功 1：写卡失败 --%> 
    <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
    <%-- 默认2：初始状态 0：缴费成功 1：缴费失败 --%> 
    <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
    <%-- 默认2：初始状态 0：开户成功 1：开户失败 --%>
    <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
	<%-- 是否打印小票  1-不打印，0-打印 --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
  				<h2>在线开户</h2>
				<div class="blank10"></div>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a> 
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)">5. 设置服务密码</a> 
				<a href="javascript:void(0)" >6. 费用确认</a>
				<a href="javascript:void(0)">7. 开户缴费</a>
				<a href="javascript:void(0)" class="on">8. 取卡打印发票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
 				<div class="blank60"></div>
 				<div class="p40 pr0">
					<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='telnum' /></span></p>
					<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
					<div class="blank20"></div>
  					<div class="line w625"></div>
					<div class="recharge_result tc">
						<%
						if ("1".equals((String)request.getAttribute("payType")) )
						{
						%>
							<p class="title yellow pt30">您的开户已成功！请取走您的银联卡和SIM卡！</p>
							<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
						<%
						}
						else 
						{
						%>
							<p class="title yellow pt30">您的开户已成功！请取走您的SIM卡！</p>
							<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
						<%
						}
						%>
 							
  						<div class="btn_box3_echo clearfix">
	   						<s:if test = " canNotPrint == 0 ">
	   							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">打印小票 (请按1键)</a>
	   							<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:20px; ">缴费完成 (请按2键)</a>
	   						</s:if>
	   						<s:else>
	   							<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:180px; ">缴费完成 (请按2键)</a>
	   						</s:else>
  						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>	
</form>
</body>
<script type="text/javascript">
// 关闭读卡器,退卡
doFinish();
</script>
</html>