<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
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
}

// 读卡等待时间
var limitTime = <%=limitTime %>;

//计算读卡剩余时间的句柄
var timeToken;

//等待读卡的句柄
var waitingToken;

// 提交标志
var submitFlag = 0;

// 等待读取标志，1，成功；0，失败。如果为1时，用户主动取消缴费操作，需调用取消刷卡接口
var readingCard = 0;

//出现异常
function setException(errorMsg) 
{	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 清除定时任务
		clearInterval(waitingToken);
		
		// 显示异常信息
		document.getElementById("errormessage").value = errorMsg;
		
		//等待读卡线程启动成功，出现异常后，需关闭			
		if (readingCard == 1)
		{
			CancelReadingUserCard();
		}
		
		//异常处理，只要出现了异常就要记录日志  
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }privAccept/goCardError.action";
		document.readCardForm.submit();
	}			
}

// 转到密码输入页面
function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
	
		// 清除定时任务
		clearInterval(waitingToken);
		
		// 转到密码输入页面
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }privAccept/inputCardPwd.action";
		document.readCardForm.submit();	
	}			
}

// 获得读卡标志
function getReadCardStatus() 
{
	// 读卡时间结束
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
			
			setException("关闲密码键盘串口发生异常！");
			return;
		}
		
		if (ret != "0" && ret != 0) 
		{
			// 打开键盘串口、设置明文模式
			OpenCom();
			SetWorkMode(0);
			
			setException("关闲密码键盘串口发生异常！");
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
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />">
		<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
		<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
		<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
		<input type="hidden" id="nCode" name="nCode" value='<s:property value="nCode" />'>
		<input type="hidden" id="privId" name="privId" value='<s:property value="privId" />'>
		<input type="hidden" id="privMoney" name="privMoney" value='<s:property value="privMoney" />'>
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
		<input type="hidden" id="cardnumber" name="cardnumber" value=''>
		<input type="hidden" id="errorType" name="errorType" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
					<!-- Chagne by LiFeng 增加优惠名称 20111121 begin -->
			<input type="hidden" id="privName" name="privName" value='<s:property value="privName" />'/>
			<!-- Chagne by LiFeng 增加优惠名称 20111121 End -->	
				<!-- Chagne by LiFeng 增加优惠类型 20111121 begin -->
		<input type="hidden" id="favorabletype" name="favorabletype" value="<s:property value="favorabletype" />"/>
		<!-- Chagne by LiFeng 增加优惠类型 20111121 end -->
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>优惠交费流程</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. 输入手机号码</a> 
					<a href="javascript:void(0)">2. 选择优惠</a>
   					<a href="javascript:void(0)">3. 选择支付方式</a> 
   					<a href="javascript:void(0)">4. 免责声明</a>
   					<a href="javascript:void(0)" class="on">5. 插入储蓄卡</a>
   					<p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
   					<a href="javascript:void(0)">6. 输入密码</a>
   					<a href="javascript:void(0)">7. 完成</a>
				</div>
				
				<div class="b712">
     					<div class="bg bg712"></div>
     					<div class="blank60"></div>
     					<div class="p40 pr0">
       					<p class="tit_info"><span class="bg"></span>请插入您的储蓄卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
       					<p class="tit_info"><span>读卡时长共</span><span class="yellow">30</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />秒</p>
       					<div class="blank10"></div>   
      						<div class="blank20"></div>
       					<div class="blank10"></div>
      						<div class="gif_animation">
      							<img src="${sessionScope.basePath }images/gif1.gif" />
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
