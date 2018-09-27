<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	
	//add begin sWX219697 2015-6-23 14:08:19 修改小票格式开户金额和投币金额不一致的Bug 92717
	String yuanMoney = (String) request.getAttribute("tMoney");
	String fenMoney = CommonUtil.yuanToFen(yuanMoney);
	String ticketMoney = CommonUtil.fenToYuan(fenMoney);
	//add end sWX219697 2015-6-23 14:08:19 修改小票格式开户金额和投币金额不一致的Bug 92717
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/MoveCardManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/CardInstallManager_sd.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>

</head>
<body onload="window.focus();" scroll="no">
<form name="payMoneyForm" method="post">
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
  				<h2>在线开户</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a> 
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)">5. 设置服务密码</a>
				<a href="javascript:void(0)">6. 费用确认</a>
				<a href="javascript:void(0)">7. 开户缴费</a>
				<a href="javascript:void(0)" class="on">8. 取卡打印小票</a>
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
							<p id='tipResult' class="desc pt20"></p>
						<%
						}
						else 
						{
						%>
							<p class="title yellow pt30">您的开户已成功！请取走您的SIM卡！</p>
							<p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
							<p id='tipResult' class="desc pt20"></p>
						<%
						}
						%>
 							
  						<div class="btn_box3_echo clearfix">
  							<s:if test = "canNotPrint == 0">
		   						<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printTicket();return false;" style="margin-left:20px; ">打印小票(请按1键)</a>
		   						<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:20px; ">开户完成(请按2键)</a>
		   					</s:if>
		   					<s:else>
		   						<a href="javascript:void(0);" id="finishButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="margin-left:180px; ">开户完成(请按2键)</a>
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
// 缴费类型
var payType = "<s:property value='payType' />";

// 关闭读卡器,退卡
doFinish();


var submitFlag = 0;

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
		printTicket();
	}
	//开户完成(2)
	if (key == 50)
	{
		goHomePage();
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		openRecWaitLoading();
		document.forms[0].target = "_self";
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
        
        //吞卡
        CaptureBankCard();
        
        return;
    }
    
    try 
    {
        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
        var ret = getCardPosition();
        
        //测试用
        if (ret == "1" || ret == 1)
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
    //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
    var ret = OpenComByCard();
    
    ret = getCardPosition();
    
    //吐卡成功，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
    if (ret == "2" || ret == 2)
    {
        startClock();
    }
}		

function doFinish()
{
	if("1" == payType)
    {
        returnCard();
    }
    finishCard();
}
// 打印小票
var printReceiptFlag = 0;

// 票据打印机是否可用
var canNotPrint = "<s:property value = 'canNotPrint' />";
function printTicket()
{
   if (canNotPrint == "0" && printReceiptFlag == 0)
   {
   		// 按钮按下，置不可用状态
		document.getElementById('dayinButton').className = 'hover';
		document.getElementById('dayinButton').onmousedown = "";
		document.getElementById('dayinButton').onmouseup = "";
		document.getElementById('dayinButton').onclick = "";
		
		printReceiptFlag = 1;
		
	   	var piData = new function()
	   	{
	   		this.servnumber = "<s:property value='telnum' />";
	   		this.mainprodname = "<s:property value='tpltTempletPO.mainProdName' />";
	   		this.tpltname = "<s:property value='tpltTempletPO.templetName' />";
	   		this.termId = "<%=termId%>";
		   	this.recFee = "<s:property value='recFee' />";
	   		this.tMoney = "<%=ticketMoney%>";
		   	this.chargeId = "<s:property value='chargeId' />";     
		   	this.pDealTime = getDateTime();  
		   	this.pDealStatus = "自助终端空白卡开户受理成功"; 
		   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
		   	this.pOrgName = "<%=pOrgName%>";  
		   	this.formnum = "<s:property value='formnum' />";  // 开户流水 
		   	this.installStatus = "<s:property value='installStatus' />";
	   	}
	   	// 打印银联小票
		var printcontext = "<s:property value='printcontext' />";
	   
	   	printInstallTicket(piData,printcontext);
	   	
	   	document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。";
   }
   		
}


//后台开户成功后转到业务受理成功页面，并吐出SIM，打印业务受理凭条，界面提示受理成功并提醒用户取卡。
function finishCard()
{
	// 将卡移出			
	var ret = MoveOutCard();

	// 关闭卡，若失败则是硬件出现问题
	var status = CloseCard();
	
	if( ret != 0)
	{
		document.getElementById("tipResult").innerHTML = "吐卡操作异常！请联系营业厅管理员！";
	}
}
	
//出现异常
function setException(errorMsg) 
{
	// 提交标记
	exSubmitFlag = 1;	

	//清除定时任务
	clearInterval(timeToken);

	document.getElementById("errormessage").value = errorMsg;
	
	//异常处理，只要出现了异常就要记录日志
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/installFeeError.action";
	document.actForm.submit();
}
</script>
</html>