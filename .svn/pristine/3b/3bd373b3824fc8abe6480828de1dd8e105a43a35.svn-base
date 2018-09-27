<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>读卡页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
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
	
	//返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}		
}
function goback(menuid)
{
	setException("缴费操作被取消");
	return;
}

// 读卡等待时间
var limitTime = <%=limitTime %>;

//等待读卡的句柄
var waitingToken;

// 提交标志
var submitFlag = 0;

// 等待读取标志，1，成功；0，失败。如果为1时，用户主动取消缴费操作，需调用取消刷卡接口
var readingCard = 0;

// 出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
	    // 提交标志置为1
		submitFlag = 1;
		
		// 清除定时任务
		clearInterval(waitingToken);
		
		// 显示错误信息
		document.getElementById("errormessage").value = errorMsg;
		
		//等待读卡线程启动成功，出现异常后，需关闭			
		if (readingCard == 1)
		{
			CancelReadingUserCard();
		}
		
		//异常处理，只要出现了异常就要记录日志  
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }prepareCard/goCardError.action";
		document.readCardForm.submit();
	}			
}

// 读卡完成后提交转到输入密码页面
function doSub()
{
	var cardnumber = document.getElementById("cardnumber").value;

	if (submitFlag == 0)
	{
		submitFlag = 1;
	
		//清除定时任务
		clearInterval(waitingToken);
		
		// 执行提交
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }prepareCard/cardPwdPrepare.action";
		document.readCardForm.submit();	
	}			
}

// 获得读卡标志
function getReadCardStatus() 
{
	//读卡时间结束
	if (limitTime <= 0 && submitFlag == 0)
	{           	
       	setException("读卡超时，无法进行缴费操作");
       	return;
	}
	
	try 
	{
		// 打开仓门接收用户银行卡 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1） 
		// 0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
		var ret = ReadUserCardFinished();

		// 尚未读取到卡信息
		if (ret == "1" || ret == 1)
		{
			// 读卡时长一共limitTime秒
			limitTime = limitTime - 1;
			
			// 设定界面显示
			document.getElementById("tTime").value = limitTime;
			
			// 标识控件未使用
		    //closeStatus = 0;
		}
		// 读卡成功
		else if (ret == "0" || ret == 0)
		{	
		    // 读取完毕
			readingCard = 0;
			
			// 需要退卡
			document.getElementById("needReturnCard").value = "1";
			
			// 在接收到ReadBankCardFinished()事件读卡成功的通知后，应及时调用此接口，并保存到页面的元素中，以确保后续的页面能获取到银行卡号信息。
			var cardNO = GetUserCardInfo();
			
			if (cardNO == "")
			{
				//获取卡信息失败
				setException("获取卡信息失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
				return;
			}
			else
			{
				// 记录卡号
				document.getElementById("cardnumber").value = cardNO;
				
				// 提交转到输入密码页面
				doSub();
			}									
		} 
		// 读卡失败
		else if (ret == "-1")
		{
			//读取失败
			readingCard = 0;
			
			//change by LiFeng 修改读卡失败不退卡问题
			// 需要退卡
			document.getElementById("needReturnCard").value = "1";
			
			setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
			return;
		}				
	}
	catch (e) 
	{
		readingCard = 0;//读取出现异常
		
		setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
	}
}

//设置时间计算周期
function startclock() 
{	
	try 
	{
		waitingToken = setInterval("getReadCardStatus()", 1000);
	}
	catch (e) 
	{
		setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
	}
}

//页面加载时执行
function bodyLoad() 
{
	try 
	{	
	    // 接口调用返回信息
		var ret;
		
		// 打开提示框
		alertJd("正在加载,请稍候...");
		
		// 标识控件使用
		closeStatus = 1;

		// 关闭键盘串口
		try
		{
			ret = CloseCom();
		}
		catch(e)
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			setException("关闭密码键盘串口发生异常！");
			return;
		}
		
		if (ret != "0" && ret != 0) 
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			setException("关闭密码键盘串口发生异常！");
			return;
		}

		// 准备刷卡（此接口包括签到银联功能）
		try
		{
			ret = ReadingUserCard();
		}
		catch(e)
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			setException("调用准备刷卡发生异常！");
			return;
		}

		// 关闲提示框
		wiWindow.close()

		// 调用准备刷卡后返回
		if (ret != "0" && ret != 0) 
		{
			setException("读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。");
            return;
		}
		else
		{		
			//等待读卡
			readingCard = 1;
			
			//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
			
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			// 启动定时任务
			startclock();
		}
	}
	catch (ex) 
	{
		setException("读卡设备控件启动等待读卡线程失败，无法使用储蓄卡进行充值，请选用其它方式。");
        return;
    }
}		
</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<!-- 缴费方式，1：银联卡；4：现金 -->
			<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
			<!-- 总费用-->
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee'/>" />
			<!-- 是否打印小票  1-不打印，0-打印 -->
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<!--受理日志id，用于更新受理日志-->
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
    
			<!--------------身份证信息 -------------->
			<!-- 姓名 -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
			<!-- 性别 -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
			<!-- 证件地址 -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
			<!-- 开始时间 -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value="<s:property value='idCardPO.idCardStartTime' />" />
			<!-- 结束时间 -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value="<s:property value='idCardPO.idCardEndTime' />" />
			<!-- 卡信息 -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value="<s:property value='idCardPO.idCardContent' />" />
			<!-- 照片信息 -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value="<s:property value='idCardPO.idCardPhoto' />" />
			
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<!-- 银行卡号 -->
			<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value="<s:property value='cardChargeLogVO.cardnumber' />">
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			
			<!--------------空白卡信息 -------------->
		    <!-- 空白卡卡号-->
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value='cardBusiLogPO.blankCard'/>" />
			<!--IMSI -->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value='simInfoPO.imsi'/>" />
			<!--ICCID -->
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value='simInfoPO.iccid'/>" />
			<!-- 空白卡信息字符串（iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2） -->
			<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>" />
			
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
	   					<a href="javascript:void(0)" class="on">6. 备卡缴费</a>
	   					<a href="javascript:void(0)">7. 吐卡打印小票</a>
					</div>
					
					<div class="b712">
      					<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>请插入您的储蓄卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
        					<p class="tit_info"><span>读卡时长共</span><span class="yellow">30</span>秒，当前剩余<input type="text"  id="tTime" name="tTime" value="30" readonly="readonly" />秒</p>
        					<div class="blank10"></div>   
       						<div class="blank20"></div>
        					<div class="blank10"></div>
       						<div class="gif_animation">
       							<img src="${sessionScope.basePath }images/gif1.gif" alt="请插卡" />
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
		closeStatus = 1;
        bodyLoad();        
	</script>
</html>
