<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if(null == errorMessage || "".equals(errorMessage.trim()))
{
	errorMessage = "操作失败，请稍后再试。";
}

	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errorMessage = CommonUtil.errorMessage(errorMessage);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	
	// 需要吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
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

// 缴费方式  1 银联卡  4 现金
var payType = "<s:property value='payType' />";

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
		//var ret = 0;
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
		
// 吐卡
function returnCard()
{
	// 是否需要退卡
	var needReturnCard = "<s:property value='needReturnCard' />"; 
	if (needReturnCard == "1")
	{			
		// 标识控件使用
	    closeStatus = 1;
	    
		//生产用
		var ret = TakeOutUserCard();
		
		// 标识控件未使用
		if (ret == "0")
		{
        	closeStatus = 0;
        }
		
		//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
		if (ret == "0" && "1" == "<%=needCaptureCard %>")
		{
			startClock();
		} 
		else if (ret != "0")
		{
			//吐卡异常
		}
		
		// 关闭卡，若失败则是硬件出现问题
		var status = CloseCard();
	}
}	
function doFinish()
{
	// 是否将卡移动到回收箱 1：回收 0：不回收
	if(1 == "<s:property value='callBackCard' />")
	{
		callBackCard();
	}
	
	//如果用户有投币，才打印失败凭条
   var money = parseInt("<s:property value='tMoney' />");
   
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   if("1" == payType)
   {
   		returnCard();
   }
   
   // 票据打印机是否可用
	var canNotPrint = "<s:property value = 'canNotPrint' />";
   
   	// 打印小票
	var printReceiptFlag = 0;
	
   //打印缴费失败的凭证
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
		   	this.payStatus = '0';  // 缴费成功0
		   	this.pDealStatus = "自助终端空白卡开户受理失败"; 
		   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
		   	this.pOrgName = "<%=pOrgName%>";  
		   	this.pPrintDate = getDateTime();  
	   	}
	   
	   	printInstallTicket(piData);
	   	
	   	// 打印银联小票
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
						<h2>在线开户</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
						<a href="javascript:void(0)">3. 产品选择</a> 
						<a href="javascript:void(0)">4. 号码选择</a>
						<a href="javascript:void(0)">5. 费用确认</a>
						<a href="javascript:void(0)">6. 开户缴费</a>
						<a href="javascript:void(0)" class="on">7. 取卡打印小票</a>
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
