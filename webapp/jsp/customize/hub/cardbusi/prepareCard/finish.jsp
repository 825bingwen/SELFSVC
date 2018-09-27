<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

//吐卡退卡异常标识
var moveCardFlag = 0;

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
		
		//弹出等待框
		openRecWaitLoading();
		document.getElementById("curMenuId").value = menuid;
		
		document.actform.action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.actform.submit();
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
		// 获取读卡器取卡状态
		var ret = TakeOutUserCardStatus();
		
		if (ret == "0" || ret == 0) 
		{	
			//已经取走卡，清除定时任务
			clearInterval(waitingToken);
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
			
			if ("1" == "<%=needCaptureCard %>")
			{
				startClock();
			}
		}
		else if (ret != "0")
		{
			moveCardFlag = 1;
		}
	}
	
	finishCard();
	
	if(moveCardFlag==1){
		alertSuccessMsg("退银联卡操作异常！请带受理凭条到营业厅办理！");
	}else if(moveCardFlag==2){
		alertSuccessMsg("吐SIM卡操作异常！请带受理凭条到营业厅办理！");
	}else if(moveCardFlag==3){
		alertSuccessMsg("退银联卡和吐SIM卡操作异常！请带受理凭条到营业厅办理！");
	}
}

	
//后台备卡成功后转到业务受理成功页面，并吐出SIM，打印业务受理凭条，界面提示受理成功并提醒用户取卡。
function finishCard()
{
	// 将卡移出			
	var ret = MoveOutCard();
	
	// 关闭卡，若失败则是硬件出现问题
	var status = CloseCard();

	if( ret != 0)
	{
		if(moveCardFlag==1){
			moveCardFlag = 3;
		}else{
			moveCardFlag = 2;
		}
	}
}

// 打印小票标志位
var printReceiptFlag = 0;

//打印小票
function printReceipt()
{
	if (printReceiptFlag == 0 && "<s:property value = 'canNotPrint'/>" == "0")
	{
		printReceiptFlag = 1;
		
		// 按钮按下，置不可用状态
		document.getElementById('dayinButton').className = 'hover';
		document.getElementById('dayinButton').onmousedown = "";
		document.getElementById('dayinButton').onmouseup = "";
		document.getElementById('dayinButton').onclick = "";
		
		//打印凭证
		var piData = 
		{
			//手机号码
	   		servnumber:"${sessionScope.CustomerSimpInfo.servNumber}",
	   		
	   		//终端机编号
	   		termId:"<%=termInfo.getTermid() %>",
	   		
	   		//备卡费用
		   	receptionFee:"<s:property value='totalFee' />",
		   	
		   	//实缴金额
		   	tMoney:"<s:property value='tMoney' />",
		   	
		   	//交费流水号
		   	dealNum:"<s:property value = 'cardChargeLogVO.chargeLogOID' />",
		   	
		   	//备卡交易流水
		   	formnum:"<s:property value = 'cardBusiLogPO.formnum' />",
		   	
		   	//受理时间    
		   	pDealTime:getDateTime(),
		   	
		   	// 缴费成功0 
		   	payStatus:'0', 
		   	
		   	//业务办理失败1
		   	installStatus:'0',
		   	
		   	//处理状态
		   	pDealStatus:"自助终端备卡受理成功",
		   	
		   	//终端机所属组织机构
		   	pOrgName:"<%=termInfo.getOrgname()%>",
		   	
		   	//打印时间
		   	pPrintDate:getDateTime()
		}

   
   		printPrepareTicket(piData);
   		
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
<form name=actform method="post">
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
  				<h2>备卡</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 输入手机号码</a> 
				<a href="javascript:void(0)">2. 选择认证方式</a>
				<a href="javascript:void(0)">3. 读取身份证信息</a>
				<a href="javascript:void(0)">4. 费用确认</a>
				<a href="javascript:void(0)">5. 选择支付方式</a>
				<a href="javascript:void(0)">6. 备卡缴费</a>
				<a href="javascript:void(0)" class="on">7. 吐卡打印小票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
 				<div class="blank60"></div>
 				<div class="p40 pr0">
					<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow">${sessionScope.CustomerSimpInfo.servNumber }</span></p>
					<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
					<div class="blank20"></div>
  					<div class="line w625"></div>
					<div class="recharge_result tc">
						<%
						if ("1".equals((String)request.getAttribute("payType")) )
						{
						%>
							<p class="title yellow pt30">您的备卡已成功！请取走您的银联卡和SIM卡！</p>
							<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
						<%
						}
						else 
						{
						%>
							<p class="title yellow pt30">您的备卡已成功！请取走您的SIM卡！</p>
							<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
						<%
						}
						%>
 							
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
					<div class="tit_info_fs20" style="position:relative;padding-left:10px;padding-top:10px;line-height:18px">
	 					<p style="font-size:15px;">备卡激活手册：</p>
	 					<p style="font-size:14px;">1. 备份原(U)SIM卡中的通讯录、短彩信等信息，以免遗失</p>
						<p style="font-size:14px;">2. 客户号码处于正常状态（未欠费停机），原(U)SIM卡可用</p>
						<p style="font-size:14px;">3. 用原(U)SIM卡发送BKJH至10086，原(U)SIM卡自动作废，</p>
						<p style="font-size:14px;">新卡生效，号码不变。若激活失败，可拨打10086热线协助激活。</p>
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