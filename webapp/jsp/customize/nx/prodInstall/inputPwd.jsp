<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// 银联卡缴费密码输入等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 

// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
		return false;
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
	writeDetailLog("<%=chargelog_detailflag %>","关闭银联的密码键盘:" + ret);
	
	// 关闭卡机
	ret = CloseReadingCardFixing();
	writeDetailLog("<%=chargelog_detailflag %>","关闭卡机:" + ret);
								
	// 打开键盘串口
	ret = OpenCom();
	writeDetailLog("<%=chargelog_detailflag %>","打开厂商的密码键盘:" + ret);
								
	// 设置明文模式
	ret = SetWorkMode(0);
	writeDetailLog("<%=chargelog_detailflag %>", "设置明文模式:" + ret);
	
	// 读卡器打开及初始化
	ret = InitReadUserCard();
	writeDetailLog("<%=chargelog_detailflag %>","打开读卡器:" + ret);
	
	// 缴费操作被取消
	setException("缴费操作被取消，请注意取走您的储蓄卡。");
}

//出现异常
function setException(errorMsg) 
{		
	writeDetailLog("<%=chargelog_detailflag %>", "出现异常:" + errorMsg);
				
	if (submitFlag == 0) 
	{
		// 提交标记
		submitFlag = 1;	
		
		// 调用银联商务提供的关闭密码键盘
		ret = closePin();
		writeDetailLog("<%=chargelog_detailflag %>","关闭银联的密码键盘:" + ret);
		
		// 关闭卡机
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=chargelog_detailflag %>", "关闭卡机:" + ret);
									
		// 打开键盘串口
		ret = OpenCom();
		writeDetailLog("<%=chargelog_detailflag %>", "打开厂商的密码键盘:" + ret);
									
		// 设置明文模式
		ret = SetWorkMode(0);
		writeDetailLog("<%=chargelog_detailflag %>","设置明文模式:" + ret);
		
		// 读卡器打开及初始化
		ret = InitReadUserCard();
		writeDetailLog("<%=chargelog_detailflag %>","打开读卡器:" + ret);
	
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
		document.readPwdForm.action = "${sessionScope.basePath }prodInstall/toMakeSure.action";
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
		writeDetailLog("<%=chargelog_detailflag %>", "开始getPass()时间：");
		
		var ret = getPass();
		
		writeDetailLog("<%=chargelog_detailflag %>", "getPass()" + ret);
		
		writeDetailLog("<%=chargelog_detailflag %>", "结束getPass()时间：" );
		
		if (ret == 13) 
		{
			if (document.getElementById("cardPwd").value.length==6)
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
			writeDetailLog("<%=chargelog_detailflag %>", "未取到储蓄卡密码 ret = -1");
		}
		// 异常
		else if (ret == -2)
		{
			
			writeDetailLog("<%=chargelog_detailflag %>", "取到储蓄卡密码异常 ret = -2");
			
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
		writeDetailLog("<%=chargelog_detailflag %>", "获取储蓄卡密码 异常");
		
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
		writeDetailLog("<%=chargelog_detailflag %>", "设置时间计算周期");
		
		setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
	}
}

//页面加载时执行
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		writeDetailLog("<%=chargelog_detailflag %>", "进入密码输入页面");
		
		// 关闭密码键盘
		writeDetailLog("<%=chargelog_detailflag %>", "调用厂商的closeCom()");
		
		var ret = CloseCom();
		
		writeDetailLog("<%=chargelog_detailflag %>", "返回:"+ret);
		               
		if (ret != "0" && ret != 0) 
		{
			setException("读取用户银行卡密码前调用关闲密码键盘串口发生异常！");
			return;
		}
		
		// 关闭读卡器和密码键盘所有打开的线程
		writeDetailLog("<%=chargelog_detailflag %>", "调用厂商的CloseReadingCardFixing()");
		               
		ret = CloseReadingCardFixing();
		
		writeDetailLog("<%=chargelog_detailflag %>", "返回:"+ret);
		
		if (ret != "0" && ret != 0) 
		{
			setException("读取用户银行卡密码前调用关闭读卡器和密码键盘所有打开的线程发生异常！");
			return;
		}
		
		// 调用银联商务提供的打开密码键盘接口
		writeDetailLog("<%=chargelog_detailflag %>", "调用银联的openPin()");
		var ret = openPin();
		writeDetailLog("<%=chargelog_detailflag %>", "返回:"+ret);
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
      writeDetailLog("<%=chargelog_detailflag %>", "启动定时任务");
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
	
		<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'/>
		<input type="hidden" id="cardnumber" name="cardnumber" value=''/>
		<input type="hidden" id="errorType" name="errorType" value=''/>
		<input type="hidden" id="errormessage" name="errormessage" value=''/>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>在线开户</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
    					<a href="javascript:void(0)">3. 产品选择</a> 
    					<a href="javascript:void(0)">4. 号码选择</a>
    					<a href="javascript:void(0)">5. 设置服务密码</a> 
    					<a href="javascript:void(0)">6. 费用确认</a>
    					<a href="javascript:void(0)" class="on">7. 开户缴费</a>
    					<a href="javascript:void(0)">8. 取卡打印发票</a>
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
