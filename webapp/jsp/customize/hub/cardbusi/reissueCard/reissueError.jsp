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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js"></script>
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
		
		document.actform.action = "${sessionScope.basePath }reissueCard/inputTelnum.action";
		document.actform.submit();
	}
}

// 缴费方式  1 银联卡  4 现金
var payType = "<s:property value='payType' />";

//取卡时间30秒
var limitTime = 30;

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
	}
}

//跳转错误页面时打印小票		
function doFinish()
{
	//回收sim卡
	if ("1" == "<s:property value='callBackCard' />")
	{
		callBackCard();
		CloseCard();
	}
	
	//如果用户有投币，才打印失败凭条
   var money = parseInt("<s:property value='tMoney' />");
   
   //用户未投币，无需打印小票
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   //吐出银联卡
   if("1" == payType)
   {
       returnCard();
   }
   
   //打印缴费失败的凭证
   if ("<s:property value='canNotPrint' />" == "0")
   {
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
		   	
		   	//受理时间    
		   	pDealTime:getDateTime(),
		   	
		   	// 缴费成功0 
		   	payStatus:'0', 
		   	
		   	//业务办理失败1
		   	installStatus:'1',
		   	
		   	//处理状态
		   	pDealStatus:"自助终端补卡受理失败",
		   	
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
