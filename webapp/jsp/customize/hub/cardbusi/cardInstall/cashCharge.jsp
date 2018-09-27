<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// 现金缴费投币等待时间(秒)
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);
if (null == timeout || "".equals(timeout.trim()))
{
	timeout = "60";
}

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCashEquip = termInfo.getTermspecial().substring(3, 4);

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
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<%-- 现金缴费使用  --%>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
// 弹出div
var divFlag = "";

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
		return false;
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
	
	// 投币后，接着点击交费，最后一次投币金额获取不到
	//89:Y 确认 13:回车 确认
	if (key == 13 || key == 89 || key == 221) 
	{
		if (parseInt(document.getElementById("tMoney").value) > 0 && divFlag == "")
		{
			doSub();
			return;
		}
		if(divFlag != "")
		{
			windowClose();
			return;
		}
	}
	//82:R 返回/退出
	if (key == 82 || key == 220)
	{
		if (parseInt(document.getElementById("tMoney").value) == 0)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	}
	
	// 按清除键
	if(key == 77 && divFlag != "")
	{
		closeRec();
		return;
	}
}

//提交标记，0表示未确认提交缴费，1表示已确认提交缴费
var submitFlag = 0;

//投币的时长，单位秒		
var payMoneyTime = "<%=timeout %>";

//剩余时间
var leftTime = payMoneyTime;

//readCash定时器句柄
var readCashToken;

//关闭识币器。0：不需要；1：需要
var needClose = 0;		

function goback(menuid)
{
	//已投币
	if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
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
		
		document.getElementById("curMenuId").value = menuid;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }cardInstall/cashCommitInstall.action";
		document.actform.submit();
	}
}


</script>
</head>
<body scroll="no">
<form name="actform" method="post">
	<!--------------空白卡信息 -------------->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!--短消息中心号码 -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />
	<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
	
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
	<input type="hidden" id="terminalSeq" name="terminalSeq" value=''/>
	
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
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="cashDetail" name="cashDetail" value=""/>
	<%-- 是否将卡移动到回收箱 1：回收 0：不回收 --%>
	<input type="hidden" id="callBackCard" name="callBackCard" value="0"/>
	
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
				<a href="javascript:void(0)">5. 费用确认</a>
				<a href="javascript:void(0)"  class="on">6. 开户缴费</a>
				<a href="javascript:void(0)">7. 取卡打印小票</a>	
			</div>
			<div class="b712 fm_pay_money">
				<div class="bg bg712"></div>
   					<div class="blank30"></div>
   					<div class="p40 pr0">
 					<div class="blank10"></div>
   					<div class="blank20"></div>
   					<div class=" pay_money_wrap2">
   					 	<p class="pay_all">
   					 		<span class="pl120">已投入：</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">元</span>
   					 	</p>
   					 	<div class="pay_state clearfix">
   					 		<span class="cash_arrow"></span>
        						<p class="fl fs22">
        							投币状态：
        							<s:if test="recFee == '0'">
										<span id="promptMsg" class="yellow">投币结束后，请点击缴费按钮</span>
									</s:if>
									<s:else>
										<span id="promptMsg" class="yellow">请投入纸币...</span>
									</s:else> 
        							<br />
        							<span class="pl119">投币时长共</span><span class="yellow"><%=timeout %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />秒
        							<br/>
        							<%--提示信息 --%>
        							<span class="pl119">支持</span><span class="yellow">5、10、20、50、100元</span>面额的纸币
        						</p>
       					</div>
   					</div>
   					<div class="blank30"></div>
   					<div>
   					 	<img src="${sessionScope.basePath }images/rmb.gif" class="fl pl160" alt="请投币" />
   					 	<div style="display:none" class="btn_box buy_enable_echo fl pl30 pt120" id="commitBusi">
   					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">缴费</a>
   					 	</div>
   					 	<div style="display:block;" class="btn_box buy_enable_echo fl pl30 pt120" id="cancelBusi">
   					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">取消</a>
   					 	</div>
   					</div>
   					<div class="popup_confirm" id="openWin_tipsMsg">
						<div class="bg"></div>
						<div class="tips_title">提示：</div>
						<div class="fs24 blue pl55 pr30 pt40 line_height_12 h200" id="winText_tipsMsg">
					  	</div>
						<div class="tc">
							<a  href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';windowClose();">继续缴费</a> 
    						<a  class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';closeRec();">取消办理</a>
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
clearTimeout(timeOut);
loadContent();



function loadContent() 
{
	var serverNumber = "<s:property value='telnum' />";
	if (serverNumber == null || serverNumber == "")
    {            
    	setException("对不起，用户信息获取失败，请返回重新操作。");
  		return;
    }
         
    <%
    if (!"1".equals(isCashEquip))
    {
    %>
    	setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
    	return;
    <%
    }
    %>
         
      // 用户进入投币页面后，上下按钮均不可用
      try 
      {
      	// 初始化现金识币器(正常返回 0,20110509143345)
		var initData = initCashEquip(serverNumber);
   	
   		// 标识控件使用
   		closeStatus = 2;
   	
        if (initData.substring(0, 1) != "0") 
        {
        	setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
            return;                    
        }
        else
        {
        	//现金识币器初始化成功。不再接收用户投币时，需要关闭。
        	needClose = 1;
        	
        	//获取投币流水
        	document.getElementById("terminalSeq").value = initData.substring(2);
        	
        	// 顶部菜单不可用
        	closeStatus = 1;
        	
        	//初始化成功，就需要关闭识币器。而首页、退出按钮无法执行此操作，所以禁用”首页“、“退出”、“上一页”按钮
        	document.getElementById("homeBtn").disabled = true;
        	document.getElementById("quitBtn").disabled = true;        	
        	// 调用定时器
        	startclock();
        }            
    } 
    catch(e) 
    {
        setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
        return;
    }
}

//循环获取投币金额
function startclock() 
{
	// 检测识币器状态
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
		setException("对不起，计时器发生异常，无法使用现金进行充值，请使用其它方式或者到营业厅进行充值。");
	}
}

//检测识币器状态
function preparecash() 
{
	var msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式值。";

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
			msg = "对不起，现金识币器状态异常，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 2) 
		{
			msg = "对不起，钱箱已满，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 3) 
		{
			msg = "对不起，钱箱未正常关闭，无法使用现金进行充值，请选用其它方式。";
		} 
		else if (cashStatus == 4) 
		{
			msg = "对不起，钱箱入钞口被夹，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 5) 
		{
			msg = "对不起，钱箱被夹，无法使用现金进行充值，请选用其它方式。";
		} 
		else if (cashStatus == 6) 
		{
			msg = "对不起，现金识币器发生未知错误，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 9) 
		{
			msg = "对不起，现金识币器不存在，无法使用现金进行充值，请选用其它方式。";
		}			
	}
	catch (e) 
	{
		msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式。";
	}
	
	// 返回
	return msg;
}

// 获取投币金额
// 循环调用，每隔1秒执行一次
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
			// 记录纸币面额信息
			document.getElementById("cashDetail").value = document.getElementById("cashDetail").value + ret + ";";
			
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
				document.getElementById('commitBusi').style.display = "block";
				document.getElementById("promptMsg").innerHTML = "已投入纸币，请点击缴费按钮";
			}
		}
		else
		{
			// 投币时长一共timeout秒
			leftTime = leftTime - 1;
			
			// 显示剩余时间
			document.getElementById("tTime").value = leftTime;
			
			//投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
			if (leftTime <= 0 && submitFlag == 0)
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
					cancelBusi();
				}
			}
		}				
	}
	catch (e) 
	{
		setException("对不起，获取投币金额失败，无法使用现金进行充值，请选用其它方式。");
	}
}

function doSub()
{
	//尚未提交请求
	if (submitFlag == 0)
	{
		// 判断投币金额，不能少于应缴金额
		var recFee = document.getElementById("recFee").value;
		var tMoney = document.getElementById("tMoney").value;
		if(tMoney - recFee < 0)
		{
			var alsoFee = recFee - tMoney;
			var tipText = "尊敬的客户，您所投币的金额不足，请再投入"+alsoFee+"元("+alsoFee+"元=应缴金额-投币金额)。";
			openWindow("openWin_tipsMsg",tipText);
			return;
		}
		
		submitFlag = 1;
		
		// 确认按钮按下，置不可用状态
		document.getElementById('commitBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
		
		// 取消按钮按下，置不可用状态
		document.getElementById('cancelBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
		
		openRecWaitLoading();
	
		//延时提交，解决等待框加载慢的情况
		setTimeout("commitCharge()", 500);
	}
}

// 投币后，接着点击交费
function commitCharge()
{
	//关闭现金识币器
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	//清除定时任务
    clearInterval(readCashToken);
    
    var recFee = document.getElementById("recFee").value;
    
    // 校验金额并提交后台
    if (parseInt(document.getElementById("tMoney").value) >= parseInt(recFee))
    {        
    	// 缴费状态  0 成功 1 失败
		document.getElementById("payStatus").value = "0";
			
    	// 设置实际缴费
    	document.getElementById("toFee").value = document.getElementById("tMoney").value;
		
		var cardInfoStr = document.getElementById("cardInfoStr").value;
		var blankno = document.getElementById("blankno").value;
		
		// 提交缴费请求前先写卡
		var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
		
		// 写卡失败 进行二次写卡
		if(writeData.indexOf("11~") != -1)
		{
			// 再次写卡
			againWriteCardProcess();
			return;
		}
		
		// 写卡过程异常，跳转异常页面
		if(writeData.indexOf("1~") != -1)
		{
			// 写卡失败 1
			document.getElementById("writeCardStatus").value = "1";
		
			writeCardException(writeData.split("~")[1]);
			return;
		}
		
		// 写卡成功
		document.getElementById("writeCardStatus").value = "0";
		
		// 开户状态 默认数据2 0 成功  1 失败
		document.getElementById("installStatus").value = "2";
		
		
		//提交缴费请求
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }cardInstall/cashCommitInstall.action";
		document.actform.submit();
    }
    else
    {
     	// 返回首页			
		setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
	}
}
 
// 再次写卡流程
function againWriteCardProcess()
{
	/**
	* 写卡异常处理流程：
	*1.	设用厂商回收卡接口把费卡走到回收箱  
	*2.	走新卡到读卡位
	*3.	调用卡商的读空卡序列号磁出序列号
	*4.	调用BOSS接口校验卡资源是否可用
	*5.	转到费用明细确认页面进行写卡，直接写卡次数达到。
	*/
	// 1.设用厂商回收卡接口把费卡走到回收箱 在写卡失败的时候已经移动到回收箱了
	
	// 2.发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
	// 3.写卡前判断写卡器是否已经插入卡
	// 4. 如果没有插入卡 将卡走到读卡位
	var message = checkReadCardStatus();
	if (message != "")
	{
		writeCardException(message);
		return;
	}
	
	// 读取空白卡序列号
	var blankCardSN = readBlankCardSN();
	
	if (blankCardSN.indexOf("1~") != -1)
	{
		writeCardException(blankCardSN.split('~')[1]);
		return;
	}
	
	if(blankCardSN.length != 20)
	{
	    writeCardException("对不起，卡类型不正确，请联系营业厅管理员!");
		return;
	}
			
	// 获取空白卡序列号
	document.getElementById('blankno').value = blankCardSN;
        
	// 6.校验卡资源是否可用
	ret = chkBlankNo();
	if (ret != 0)
	{
		writeCardException("对不起，卡资源不可用！");
		return;
	}
	// 不必再次号码资源占用了
	// 7. 空白卡资源暂选
	ret = blankCardTmpPick();
	var resArray = ret.split('~~');
	if (resArray[0] != 0)
	{
		writeCardException("空白卡资源暂选失败！");
		return;
	}
	else
	{
		setValue("iccid",resArray[1]);
		setValue("imsi",resArray[2]);
		setValue("smsp",resArray[4]);
		setValue("cardInfoStr",ret.substring(3).replace("+", "^^"));
	}
	
	var cardInfoStr = document.getElementById("cardInfoStr").value;
	var blankno = document.getElementById("blankno").value;
	
	// 提交缴费请求前先写卡
	var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
	
	// 二次写卡失败 跳转异常页面
	if(writeData.indexOf("1~") != -1)
	{
		// 写卡失败 1
		document.getElementById("writeCardStatus").value = "1";
	
		writeCardException(writeData.split("~")[1]);
		return;
	}
	
	// 写卡成功
	document.getElementById("writeCardStatus").value = "0";
	
	// 开户状态 默认数据2 0 成功  1 失败
	document.getElementById("installStatus").value = "2";
	
	
	//提交缴费请求
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/cashCommitInstall.action";
	document.actform.submit();
}

// 校验空白卡资源是否可用
function chkBlankNo()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/chkBlankNo.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "orgid=" + document.getElementById("orgid").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}
	
// 空白卡资源暂选
function blankCardTmpPick()
{
	// 返回 1 0~~iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/blankCardTmpPick.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "telnum=" + document.getElementById("telnum").value;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}
	
 
// 取消充值交费交易  
function cancelBusi()
{
	// 需要看是否投币了，看是否打印小票
	// 按钮按下，置不可用状态
	document.getElementById('cancelBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
	
	//打开等待窗口
	openRecWaitLoading();  // hWX 
	
	if (needClose == 1)
	{
		// 关闭现金识币器
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 返回首页
	setTimeout('window.location="${sessionScope.basePath}cardInstall/goHomePage.action"', 500);
}

// 取消办理，将所投金额使用小票打印
function closeRec()
{
	if (parseInt(document.getElementById("tMoney").value) > 0)
	{
		
		// 缴费状态  0 成功 1 失败
		document.getElementById("payStatus").value = "0";
			
    	// 设置实际缴费
    	document.getElementById("toFee").value = document.getElementById("tMoney").value;
    	
		writeCardException("用户取消办理，请取小票！如有疑问，请咨询移动营业厅管理员。"); 
	}
	else
	{
		setException("用户取消办理！如有疑问，请咨询移动营业厅管理员。");
	}
	
}

//出现异常
function setException(errorMsg) 
{	
	document.getElementById("errormessage").value = errorMsg;
	
	// 出现异常后，清除定时任务。同时关闭现金识币器			
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 异常处理，只要出现了异常就要记录日志  
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
	document.actform.submit();
}

// 出现写卡异常
function writeCardException(errorMsg)
{
	document.getElementById("errormessage").value = errorMsg;
			
	// 出现异常后，清除定时任务。同时关闭现金识币器			
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 写卡异常记录异常日志（增加缴费日志与更新开户日志）
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/makeErrLog.action";
	document.actform.submit();
}
function openWindow(id,tipMsg)
{
	divFlag = "winText_tipsMsg";
	document.getElementById('winText_tipsMsg').innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子	
}


// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	wiWindow.close();
}
</script>
</html>
