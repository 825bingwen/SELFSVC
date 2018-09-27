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
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 缴费类型
var payType = "<s:property value='payType' />";

// 是否退还银联卡
var needReturnCard = "<s:property value='needReturnCard' />";

//sim卡银联卡标志位
var cardFlag = "0";

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
	
	//返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
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
}

//返回
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
		
		document.forms[0].action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.forms[0].submit();
	}
}		
	
// 读取读卡器取卡状态
function takeOutBankCardStatus() 
{
	limitTime = limitTime - 1;
	
	if (limitTime <= 0)
	{
		//超时，清除定时任务，同时吞卡
		clearInterval(waitingToken);
		
		captureUserCard();
		
		return;
	}
	
	try 
	{
		//生产用
		var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
		
		//测试用
		if (ret == "0" || ret == 0) 
		{	
			//已经取走卡，清除定时任务
			clearInterval(waitingToken);									
		}			
	}
	catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

function startClock()
{
	try 
	{
		waitingToken = setInterval("takeOutBankCardStatus()", 1000);
	}
	catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}		

//吐出SIM
function finishCard()
{
	// 将卡移出			
	var ret = MoveOutCard();
	
	// 关闭卡，若失败则是硬件出现问题
	CloseCard();
	
	//吐卡异常
	if( ret != 0)
	{	
		if (cardFlag = "1")
		{
			document.getElementById("resultMsg").innerHTML = "sim卡和银联卡吐出异常，请联系请联系营业员取出";
		}
		else
		{
			document.getElementById("resultMsg").innerHTML = "sim卡吐卡操作异常！请联系营业员取出sim卡";
		}
		
	}
}	

function doFinish()
{
	// 缴费方式，1：银联卡；4：现金
	if (payType == "1" && needReturnCard == "1")
	{
		// 退卡
		var ret = TakeOutUserCard();

		// 吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
		if (ret == "0" && "1" == "<%=needCaptureCard %>")
		{
			startClock();
		}
		else if (ret != "0")
		{
			cardFlag = "1";
		}
	}
	
	//吐出sim卡
	finishCard();
}

// 打印小票
var printReceiptFlag = 0;

function printReceipt()
{
	if (printReceiptFlag == 0 && "<s:property value='canNotPrint'/>" == "0")
	{
		printReceiptFlag = 1;
		
		// 按钮按下，置不可用状态
		document.getElementById('dayinButton').className = 'hover';
		document.getElementById('dayinButton').onmousedown = "";
		document.getElementById('dayinButton').onmouseup = "";
		document.getElementById('dayinButton').onclick = "";
	
		var piData = 
		{
			//手机号码
	   		servnumber:"${sessionScope.CustomerSimpInfo.servNumber}",
	   		
	   		//终端机编号
	   		termId:"<%=termInfo.getTermid() %>",
	   		
	   		//补卡费用
		   	receptionFee:"<s:property value='recFee' />",
		   	
		   	//实缴金额
		   	tMoney:"<s:property value='tMoney' />",
		   	
		   	//交费流水号
		   	dealNum:"<s:property value = 'cardChargeLogVO.chargeLogOID' />",
		   	
		   	//受理流水号
		   	formnum:"<s:property value = 'cardBusiLogPO.formnum' />",
		   	
		   	//受理时间    
		   	pDealTime:getDateTime(),
		   	
		   	// 缴费成功0 
		   	payStatus:'0', 
		   	
		   	//业务办理失败1
		   	installStatus:'0',
		   	
		   	//处理状态
		   	pDealStatus:"自助终端补卡受理成功",
		   	
		   	//终端机所属组织机构
		   	pOrgName:"<%=termInfo.getOrgname()%>",
		   	
		   	//打印时间
		   	pPrintDate:getDateTime()
		}
   
   		printReissueTicket(piData);
	
		// 打印银联小票
		var printcontext = "<s:property value='printcontext' />";
		if (payType == "1" && printcontext != null && printcontext != "")
		{
			doPrintUnionBill_hb(printcontext,"<s:property value='cardChargeLogVO.terminalSeq'/>","<%=termInfo.getOrgname()%>",getDateTime());
		}
		
		document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。";
	}
}

</script>
</head>
<body onload="window.focus();" scroll="no">
<form name="payMoneyForm" method="post">
	<!-- 姓名 -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	<!-- 实际缴费金额 -->
	<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
	
   	<%--受理日志id，--%>
   	<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
	<%----------------交费日志信息---------------%>
	<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" value="<s:property value = 'cardChargeLogVO.chargeLogOID' />">
	<%-- 实收费用 --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>

	<%-- 是否打印小票  1-不打印，0-打印 --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2>补卡</h2>
				<div class="blank10"></div>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 输入手机号码</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
  				<a href="javascript:void(0)">3. 费用确认</a>
  				<a href="javascript:void(0)">4. 选择支付方式</a>
  				<a href="javascript:void(0)">5. 补卡缴费</a>
  				<a href="javascript:void(0)" class="on">6. 取卡打印小票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
 				<div class="blank60"></div>
 				<div class="p40 pr0">
					<p class="tit_info "><span class="bg"></span>手机号码：${sessionScope.CustomerSimpInfo.servNumber}</span></p>
					<p class="tit_desc pl40 ">补卡交费：<span class="yellow">
					<s:property value='tMoney' />元
					</span> 
					</p>
					<div class="blank20"></div>
  					<div class="line w625"></div>
					<div class="recharge_result tc">
						<s:if test = " payType == 1 ">
							<p class="title yellow pt30">补卡已办理成功！请取走您的银联卡和SIM卡！</p>
		   				</s:if>
		   				<s:else>
							<p class="title yellow pt30">补卡已办理成功！请取走您的SIM卡！</p>
						</s:else>
						<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票</p>
	  					<div class="btn_box3_echo clearfix">
		   					<s:if test = " canNotPrint == 0 ">
		   						<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:140px; ">打印小票 (请按1键)</a>
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