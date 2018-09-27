<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// 银联卡缴费密码输入等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>	
<script language="javascript">

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
	return true;
}	

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	/**
	//返回
	if (key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
	//清除：77
	else if(key == 77)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = "";
		}
	}
	//更正	
	else if (key == 8 || key == 32 || key == 66)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = backString(etarget.value);
		}
	}
	**/	
}

//防止重复提交
var submitFlag = 0;

// 等待读取密码的句柄
var waitingToken;

// 等待时间的句柄
var timeToken;

// 读密码等待时间
var limitTime = <%=limitTime %>;

// 取消
function goback(menuid)
{
		// 调用银联商务提供的关闭密码键盘
		ret = closePin();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"关闭银联的密码键盘:" + ret);
		
		// 关闭卡机
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"关闭卡机:" + ret);
									
		// 打开键盘串口
		ret = OpenCom();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"打开厂商的密码键盘:" + ret);
									
		// 设置明文模式
		ret = SetWorkMode(0);
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"设置明文模式:" + ret);
		
		// 读卡器打开及初始化
		ret = InitReadUserCard();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"打开读卡器:" + ret);
	
	// 缴费操作被取消
	setException("缴费操作被取消，请注意取走您的储蓄卡。");
}

//出现异常
function setException(errorMsg) 
{		
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "出现异常:" + errorMsg);
				
	if (submitFlag == 0) 
	{
		// 提交标记
		submitFlag = 1;	
		
		// 调用银联商务提供的关闭密码键盘
		ret = closePin();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"关闭银联的密码键盘:" + ret);
		
		// 关闭卡机
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"关闭卡机:" + ret);
									
		// 打开键盘串口
		ret = OpenCom();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"打开厂商的密码键盘:" + ret);
									
		// 设置明文模式
		ret = SetWorkMode(0);
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"设置明文模式:" + ret);
		
		// 读卡器打开及初始化
		ret = InitReadUserCard();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
									"打开读卡器:" + ret);
	
		//清除定时任务
		clearInterval(waitingToken);
		
		// 显示错误信息
		document.getElementById("errormessage").value = errorMsg;
		
		//异常处理，只要出现了异常就要记录日志  
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }charge/goCardError.action";
		document.readPwdForm.submit();	
	}				
}
			
// 提交转向到确认页面
function doSub() 
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		
		//清除定时任务
		clearInterval(waitingToken);
		
		// 执行提交
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }charge/toMakeSure.action";
		document.readPwdForm.submit();
	}
}

//获取储蓄卡密码
function getCardPasswordStatus()
{
	// 检查是否超时
	if (limitTime <= 0 && submitFlag == 0)
	{
				// 超时退出
       	setException("读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
       	return;
	}
		
	// 读密码时长一共limitTime秒
	limitTime = limitTime - 1;
	
	// 处理页面显示
	document.getElementById("tTime").value = limitTime;
	
	try 
	{
		// 获取用户密码输入状态,13 成功；失败返回-1
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "开始getPass()时间：");
		
		var ret = getPass();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "getPass()" + ret);
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "结束getPass()时间：" );
		
		//if (ret == 13) 
		if (true)
		{
				//if (document.getElementById("cardPwd").value.length==6)
				if (true)
				{
			      // 提交
						doSub();											
				}
				else
				{
						// 密码位数不够
						setException("请取走您的储蓄卡，重新选择银联卡缴费并输入六位正确的储蓄卡密码！");
				}
		}
		
		// 未取到储蓄卡密码
		else if (ret == -1)
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "未取到储蓄卡密码 ret = -1");
		}
		// 异常
		else if (ret == -2)
		{
			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "取到储蓄卡密码异常 ret = -2");
			
			//清除定时任务
			clearInterval(waitingToken);
			
			// 取到储蓄卡密码异常
			setException("取储蓄卡密码异常，请取走您的储蓄卡。");
		}
		// 0 - 9 * 
		else if (ret == 42)
		{
			document.getElementById("cardPwd").value = document.getElementById("cardPwd").value + "*";
		}
		// 取消
		else if (ret == 27)
		{
			//清除定时任务
			clearInterval(waitingToken);
			
			// 取到储蓄卡密码异常
			setException("缴费操作被取消，请取走您的储蓄卡。");
		}
		// 删除
		else if (ret == 8)
		{
			var pwdStr = document.getElementById("cardPwd").value;
			if (pwdStr.length > 0)
			{
				document.getElementById("cardPwd").value = pwdStr.substr(0,pwdStr.length-1);
			}
		}
	}
	catch (e) 
	{
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "获取储蓄卡密码 异常");
		
		setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
	}
}

//获取储蓄卡密码
function getTimeStatus() 
{
	// 检查是否超时
	if (limitTime <= 0 && submitFlag == 0)
	{
				// 超时退出
       	setException("读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
       	return;
	}
		
	// 读密码时长一共limitTime秒
	limitTime = limitTime - 1;
	
	// 处理页面显示
	document.getElementById("tTime").value = limitTime;
}

//设置时间计算周期
function startclock() 
{	
	try 
	{
		// 调用取密码接口
		waitingToken = setInterval("getCardPasswordStatus()", 500);
		
		// 计时器
		//timeToken = setInterval("getTimeStatus()", 1000);
	}
	catch (e) 
	{
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "设置时间计算周期");
		
		setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
	}
}

//页面加载时执行
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "进入密码输入页面");
		
		// 关闭密码键盘
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "调用厂商的closeCom()");
		
		var ret = CloseCom();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "返回:"+ret);
		               
		if (ret != "0" && ret != 0) 
		{
			setException("读取用户银行卡密码前调用关闲密码键盘串口发生异常！");
			return;
		}
		
		// 关闭读卡器和密码键盘所有打开的线程
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "调用厂商的CloseReadingCardFixing()");
		               
		ret = CloseReadingCardFixing();
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "返回:"+ret);
		
		if (ret != "0" && ret != 0) 
		{
			setException("读取用户银行卡密码前调用关闭读卡器和密码键盘所有打开的线程发生异常！");
			return;
		}
		
		// 调用银联商务提供的打开密码键盘接口
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "调用银联的openPin()");
		var ret = openPin();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
		               "返回:"+ret);
		if (ret != "0" && ret != 0) 
		{
			setException("准备读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
      return;
		}
		else
		{		
			// 禁用”首页“、“退出”按钮
      document.getElementById("homeBtn").disabled = true;
      document.getElementById("quitBtn").disabled = true;

      // 启动定时任务
      writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
             "启动定时任务");
			startclock();
		}
		
	}
	catch (ex) 
	{
		setException("准备读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
        return;
	}
}	
</script>
</head>
<body scroll="no">
	<form name="readPwdForm" method="post" target="_self">
		
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardnumber" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>充值交费流程</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. 输入手机号码</a> 
					<a href="javascript:void(0)">2. 选择支付方式</a>
   					<a href="javascript:void(0)">3. 选择金额</a> 
   					<a href="javascript:void(0)">4. 免责声明</a>
   					<a href="javascript:void(0)">5. 插入银联卡</a> 
   					<a href="javascript:void(0)" class="on">6. 输入密码</a>
   					<p class="recharge_tips">通过金属键盘输入储蓄卡密码。</p>
   					<a href="javascript:void(0)">7. 核对信息</a>
    				<a href="javascript:void(0)">8. 完成</a>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank60"></div>
   					<div class="p40 pr0">
   						<p class="tit_info"><span class="bg"></span>请通过<span style="color:#ffff00">下方的金属键盘</span>输入您的卡密码，并按<span style="color:#ffff00">"确认"</span>键交费：</p>
   						<p class="tit_info"><span>密码获取时长共</span><span class="yellow"><%=limitTime %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />秒</p>
   						<div class="blank25"></div>                
               			<div class="blank25"></div>
               			<div class="input" style="margin-left:100px">
               				<ul>
               					<li style="width:120px; margin-top:22px;">储蓄卡密码：</li>
               					<li style="width:255px;"><input class="text2" name="cardPwd" id="cardPwd" type="password" maxlength="6"/></li>
               				</ul>
                       	</div>
                       	<div class="blank50"></div>
       					<div class="blank50"></div>
       					<div class="gif_animation">
       						<img src="${sessionScope.basePath }images/jp.gif" alt="请输入密码" />
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
    bodyLoad();     
    // 标识控件使用
   	closeStatus = 1;   
</script>
</html>
