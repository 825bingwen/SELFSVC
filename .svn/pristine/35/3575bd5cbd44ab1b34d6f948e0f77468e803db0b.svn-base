<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// 投币超时时间
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);

// 终端信息
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 现金识币器特性
String isCashEquip = termInfo.getTermspecial().substring(3, 4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
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
	
	//确认
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}
	//返回
	else if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}		
}
	
//投币的时长，单位秒		
var payMoneyTime = "<%=timeout %>";

//剩余时间
var leftTime = payMoneyTime;

//readCash定时器句柄
var readCashToken;

//关闭识币器。0：不需要；1：需要
var needClose = 0;

//提交标记，0表示未确认提交缴费，1表示已确认提交缴费
var submitFlag = 0;

/**
 * 校验空白卡资源
 * 流程：1、校验是否为预制空卡。2、校验卡资源是否可用。
 *      3、预占卡资源
 */
function checkBlankCard()
{
	var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")+"&curMenuId="+getValue("curMenuId");  
		 
	var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
	var resXml;
	
	//同步调用
	var loader = new net.ContentLoaderSynchro(
		url, netload = function () 
		{
			resXml = this.req.responseText;
			var resArray = resXml.split('~~');
			if (resArray[0] == 0 || resArray[0] == "0")
			{
				setValue("iccid",resArray[1]);
				setValue("imsi",resArray[2]);
				setValue("smsp",resArray[4]);
			}
		}, 
		null, "POST", postStr, null);
		
	return resXml;
}

/**
 * 二次写卡流程
 * 入参：basePath 项目路径，js中无法解析s标签
 * 流程：1、空白卡移到读卡位。2、读空白卡卡号。3、检验空白卡资源
 * 		4、写卡。5、修改写卡状态
 */
function writeAgain()
{
	//1、将卡移到读卡位
	var message = checkReadCardStatus();
	if (message != "")
	{
		return message;
	}
	
	//2、读取空白卡序列号
	var blankCardSN = readBlankCardSN();
	
	if (20 != blankCardSN.length)
	{
		return "读取空白卡卡号失败！";
	}
	
	setValue("blankCard",blankCardSN);
			
	//3、校验空白卡资源
	var resXml = checkBlankCard();
	
	//解析返回值
	var resArray = resXml.split('~~');
	
	//校验空白卡资源失败    
	if (resArray[0] != "0")
	{
		return resArray[1];
	}
	
	//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var simInfo = resXml.substring(3).replace("+", "^^");
	
	//4、写卡
	var writeData = writeCard(simInfo, getValue("blankCard"), "${sessionScope.basePath}", "${sessionScope.CustomerSimpInfo.servNumber}");
	
	return writeData;
}

/**
 * 第一次写卡
 * 
 */
function writeReissueCard()
{
	//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("eki")+ "~~" +getValue("smsp").replace("+", "^^");
	simInfo = simInfo+"~~"+getValue("pin1")+"~~"+getValue("pin2")+"~~"+getValue("puk1")+"~~"+getValue("puk2");
	
	//项目路径
	var basePath = "${sessionScope.basePath}";
	
	//写卡:入参：sim卡信息，空白卡卡号，项目路径，手机号码
	var writeData = writeCard(simInfo, getValue("blankCard"), basePath, "${sessionScope.CustomerSimpInfo.servNumber}");
	
	return writeData;
}

//投币不足时的弹出框
function openWindow(id,tipMsg)
{
	document.getElementById('winText_tipsMsg').innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子	
}

/**
 * 现金缴费提交优化，解决等待框加载慢的问题
 */
function doSub()
{
	//未投币
	if (getValue("tMoney") == "" 
			|| parseInt(getValue("tMoney")) <= 0)
	{
		return;
	}
	
	//判断投币金额，不能少于应缴金额
	if(getValue("tMoney") - getValue("recFee")  < 0)
	{
		var alsoFee = getValue("recFee") - getValue("tMoney");
		var tipText = "尊敬的客户，您所投币的金额不足，请再投入"+alsoFee+"元（"+alsoFee+"元=应缴金额-投币金额）";
		
		openWindow("openWin_tipsMsg",tipText);
		return;
	}
	
	//缴费按钮不可用
	document.getElementById('commitBusi').disabled = true;
	
	//弹出等待框
	openRecWaitLoading();
	
	//延时提交，解决等待框加载慢的情况
	setTimeout("cashSub()", 500);
}

/**
 * 现金缴费提交
 * 流程：1、判断投币金额。2、写卡
 *
 */
function cashSub()
{
	var res;
	
	//交费状态 默认2：初始状态 0：缴费成功 1：缴费失败
	setValue("payStatus","0");
	
	//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
	setValue("writeCardStatus","1");
	
	//写卡
	res = writeReissueCard();
	
	//第一次写卡时如不是写卡控件异常，直接跳转，不再二次写卡
	if(res != "0" && res.indexOf("11~") == -1)
	{
		setException(res.split("~")[1]);
		return;
	}
	
	//二次写卡
	if (res.indexOf("11~") != -1)
	{
		res = writeAgain();
	}
	
	//二次写卡失败
	if (res != 0)
	{
		setException("二次写卡失败");
		return;
	}
	
	//写卡成功
	setValue("writeCardStatus","0");
	
	//尚未提交请求
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//关闭现金识币器
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		//清除定时任务
        clearInterval(readCashToken);
		
		//提交缴费请求
		document.actform.action = "${sessionScope.basePath }reissueCard/reissueCommit.action";
		document.actform.submit();
	}
}

/**
 * 循环判断用户投币状态
 * 流程：获取用户投币金额，若已投币，则取消按钮不可用。
 *      超出投币时间，未操作，自动进行缴费或取消操作。
 */
function doCash() 
{
	if (leftTime <= 0)
	{
		return;
	}
	
	try 
	{	
		// 获取投币金额 0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
		var ret = getOnceCash();

		if (ret > 0) 
		{
			// 标识控件使用
	   		closeStatus = 1;
			
			// 时间重新开始
			leftTime = "<%=timeout %>";
			
			// 显示剩余时间
			document.getElementById("tTime").value = leftTime;
			
			// 投币后，不能返回
            document.getElementById("backBtn").disabled = true;
			
			// 计算投币金额 
			document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
			
			// 取消交易按扭不显示
			document.getElementById('cancelBusi').style.display = "none";
			
			// 投币金额大于0时取消交易按扭不显示,缴费按扭显示
			if (parseInt(document.getElementById("tMoney").value) > 0)
			{
				document.getElementById('bCommitBusi').style.display = "none";
				document.getElementById('commitBusi').style.display = "block";
				document.getElementById("promptMsg").innerHTML = "已投入纸币，请点击缴费按钮";
			}
			else
			{
				document.getElementById('bCommitBusi').style.display = "block";
			}
		}
		else
		{
			// 投币时长一共timeout秒
			leftTime = leftTime - 1;
			
			// 显示剩余时间
			document.getElementById("tTime").value = leftTime;
			
			//投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
			if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
			{           	 	
		        //投币金额大于0
		       	if (parseInt(document.getElementById("tMoney").value) > 0) 
				{
					// 提交缴费
					doSub();
				} 
				else 
				{
					// 取消交易
					cancelBusi("");
					//setException("投币时间结束，投币金额为0，无法进行缴费操作");
				}
			}
		}				
	}
	catch (e) 
	{
		setException("对不起，获取投币金额失败，无法使用现金进行缴费，请选用其它方式。");
	}
}

//检测识币器状态
function preparecash() 
{
	var msg = "对不起，现金识币器出现异常，无法使用现金进行缴费，请选用其它方式值。";

	try 
	{
		//检测识币器状态 0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备
		var cashStatus = checkCashStatus();

		if (cashStatus == 0)
		{
			msg = "";
		}
		else if (cashStatus == 1) 
		{
			msg = "对不起，现金识币器状态异常，无法使用现金进行缴费，请选用其它方式。";
		}
		else if (cashStatus == 2) 
		{
			msg = "对不起，钱箱已满，无法使用现金进行缴费，请选用其它方式。";
		}
		else if (cashStatus == 3) 
		{
			msg = "对不起，钱箱未正常关闭，无法使用现金进行缴费，请选用其它方式。";
		} 
		else if (cashStatus == 4) 
		{
			msg = "对不起，钱箱入钞口被夹，无法使用现金进行缴费，请选用其它方式。";
		}
		else if (cashStatus == 5) 
		{
			msg = "对不起，钱箱被夹，无法使用现金进行缴费，请选用其它方式。";
		} 
		else if (cashStatus == 6) 
		{
			msg = "对不起，现金识币器发生未知错误，无法使用现金进行缴费，请选用其它方式。";
		}
		else if (cashStatus == 9) 
		{
			msg = "对不起，现金识币器不存在，无法使用现金进行缴费，请选用其它方式。";
		}			
	}
	catch (e) 
	{
		msg = "对不起，现金识币器出现异常，无法使用现金进行缴费，请选用其它方式。";
	}
	
	// 返回
	return msg;
}

/**
 * 获取投币金额
 * 流程：1、检测识币器状态。
 *      2、获取投币金额。
 */
function startclock() 
{
	var msg = preparecash();
	
	//识币器状态检测失败，不允许投币，显示异常信息
	if (msg != "") 
	{
		setException(msg);
		return;
	}		
	
	try
	{
		// 获取投币金额,循环调用,每隔1秒执行一次
		readCashToken = setInterval("doCash()", 1000);
	}
	catch (e) 
	{
		setException("对不起，计时器发生异常，无法使用现金进行缴费，请使用其它方式或者到营业厅进行缴费。");
	}
}

/**
 * 现金缴费初始化
 * 步骤：1、获取手机号码。2、初始化现金识币器。3、获取投币流水。
 *      4、使首页和退出按钮不可用。
 *      5、启动循环获取投币金额。
 */
function loadContent() 
{
	var serverNumber = "${sessionScope.CustomerSimpInfo.servNumber}";
	if (serverNumber == null || serverNumber == "")
    {            
		setException("对不起，用户信息获取失败，请返回重新操作。");
		return;
	}
          
	<%
	if (!"1".equals(isCashEquip))
	{
	%>
		 setException("对不起，该终端机暂不支持现金缴费，请选用其它方式。");
		 return;
	<%
	}
	%>
    
    try 
    {
    	// 初始化现金识币器(正常返回 0,20110509143345)
		var initData = initCashEquip(serverNumber);
	   	
	   	// 标识控件使用
	   	closeStatus = 2;
	   	
        if (initData.substring(0, 1) != "0") 
        {
        	setException("现金识币器初始化失败，无法使用现金进行缴费，请选用其它方式。");
            return;                    
        }
        else
        {
        	//现金识币器初始化成功。不再接收用户投币时，需要关闭。
        	needClose = 1;
        	
        	//获取投币流水
        	document.getElementById("terminalSeq").value = initData.substring(2);
        	
        	//初始化成功，就需要关闭识币器。而首页、退出按钮无法执行此操作，所以禁用”首页“、“退出”按钮
        	document.getElementById("homeBtn").disabled = true;
        	document.getElementById("quitBtn").disabled = true;
        	
        	startclock();
        }               
    } 
    catch(e) 
    {
        setException("现金识币器初始化失败，无法使用现金进行缴费，请选用其它方式。");
        return;
    }           
}

//出现异常
function setException(errorMsg) 
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		setValue("errormessage",errorMsg);
		
		// 出现异常后，清除定时任务。同时关闭现金识币器			
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		// 清除定时任务
		clearInterval(readCashToken);
		
		// 异常处理，只要出现了异常就要记录日志  
		document.actform.action = "${sessionScope.basePath }reissueCard/goCashError.action";
		document.actform.submit();
	}
}

// 取消交易
function cancelBusi(msg)
{		
	if (needClose == 1)
	{
		// 关闭现金识币器
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	if (msg != "")
	{
		setException(msg);
	}
	else
	{
		// 返回首页
		setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
	}
}

//返回
function goback(menuid)
{
	//已投币
	if (document.getElementById("tMoney").value != "" 
			&& parseInt(document.getElementById("tMoney").value) > 0)
	{
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//返回时，清除定时任务。同时关闭现金识币器			
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		clearInterval(readCashToken);
		
		document.actform.action = "${sessionScope.basePath }reissueCard/inputTelnum.action";
		document.actform.submit();
	}
}

</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<%-- 是否打印小票  1-不打印，0-打印 --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
		<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYMONEY %>">
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		
		<!-- 费用合计 -->
		<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
		
		<%--------------补卡受理日志---------------%>
		<%--受理日志id，用于更新受理日志--%>
   		<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
		<%-- 空白卡卡号--%>
		<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
		<%-- 写卡状态  默认2：初始状态 0：写卡成功 1：写卡失败--%>
		<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
		<%-- 交费状态  默认2：初始状态 0：缴费成功 1：缴费失败--%>
		<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
		
		<%----------------交费日志信息-------------%>
		<%--缴费流水号--%>
		<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
		
		<%--是否需要回收卡，1 需要 --%>
		<input type="hidden" id="callBackCard" name="callBackCard" value="" />
		
	    <!--------------simInfoPO信息 -------------->
		<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
		<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
		<input type="hidden" id="smsp" name="simInfoPO.smsp" value="<s:property value="simInfoPO.smsp" />" />
		<input type="hidden" id="pin1" name="simInfoPO.pin1" value="<s:property value="simInfoPO.pin1" />" />
		<input type="hidden" id="pin2" name="simInfoPO.pin2" value="<s:property value="simInfoPO.pin2" />" />
		<input type="hidden" id="puk1" name="simInfoPO.puk1" value="<s:property value="simInfoPO.puk1" />" />
		<input type="hidden" id="puk2" name="simInfoPO.puk2" value="<s:property value="simInfoPO.puk2" />" />
		<input type="hidden" id="eki" name="simInfoPO.eki" value="<s:property value="simInfoPO.eki" />" />
		
		<%--客户信息 --%>
		<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
		<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
		<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
		<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
		
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
				<a href="javascript:void(0)" class="on">5. 补卡缴费</a>
				<a href="javascript:void(0)">6. 取卡打印小票</a>
				</div>
				
				 <div class="b712 fm_pay_money">
				 	<div class="bg bg712"></div>
     					<div class="blank30"></div>
     					<div class="p40 pr0">
     						<div class="blank10"></div>
       					<div class="blank20"></div>
       					<div class=" pay_money_wrap2">
       					 	<p class="pay_all">
       					 		<span style="padding-left:120px;">已投入：</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">元</span>
       					 	</p>
       					 	<div class="pay_state clearfix">
       					 		<span class="cash_arrow"></span>
             						<p class="fl fs22">
             							投币状态： 
										<span id="promptMsg" class="yellow">请投入纸币...</span>		
             							<br />
             							<span style="padding-left:119px;">投币时长共</span><span class="yellow"><%=timeout %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />秒
             							<br/>
             							<span style="padding-left:119px;">支持</span><span class="yellow">5、10、20、50、100元</span>面额的纸币
             						</p>
           					</div>
       					</div>
       					<div class="blank30"></div>
       					<div>
       					 	<img src="${sessionScope.basePath }images/rmb.gif" style="float:left; padding-left:160px;" alt="请投币" />
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box cancle fl" id="cancelBusi">
       					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi('');return false;">取消本次交易</a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box charge_unable fl" id="bCommitBusi">
       					 		<a href="#" onclick="return false;"></a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px; display:none" class="btn_box buy_enable fl" id="commitBusi">
       					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();;return false;">缴费</a>
       					 	</div>
       					</div>
     					</div>
				 </div>
			</div>
		</div>
		
		
					<div class="popup_confirm" id="openWin_tipsMsg">
						<div class="bg"></div>
						<div class="tips_title">提示：</div>
						<div class="fs24 blue pl55 pr30 pt40 line_height_12 h200" id="winText_tipsMsg">
					  	</div>
						<div class="tc">
							<a  href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';wiWindow.close();">继续缴费</a> 
    						<a  class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';cancelBusi('您已取消办理补卡业务');">取消办理</a>
						</div>
					</div>
		
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<script type="text/javascript">
	clearTimeout(timeOut);
	loadContent();
</script>
</html>
