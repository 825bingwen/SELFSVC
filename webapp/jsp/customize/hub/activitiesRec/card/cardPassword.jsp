<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// 银联卡缴费密码输入等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_INPUTCARDPWD_TIME); 
//用来控制是否在超时后调用关闭密码键盘接口
String IsNeedCloseEncryptKey = (String)PublicCache.getInstance().getCachedData("IsNeedCloseEncryptKey");

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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>	
<script language="javascript">
//82、220 返回		
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
	
	//返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
	else if (key == 8 || key == 32 || key == 66 || key ==77)
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

//等待读取密码的句柄
var waitingToken;

// 读密码等待时间
var limitTime = <%=limitTime %>;

// 取消
function goback(menuid)
{
	setException("缴费操作被取消，请注意取走您的储蓄卡。");
}

//出现异常
function setException(errorMsg) 
{		
	// 打开键盘串口、设置明文模式
	var ret = OpenCom();
	ret = SetWorkMode(0);
				
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
	
		//清除定时任务
		clearInterval(waitingToken);
		
		// 显示错误信息
		document.getElementById("errormessage").value = errorMsg;
		
		//异常处理，只要出现了异常就要记录日志  
		document.readPwdForm.target = "_self";
		document.readPwdForm.action = "${sessionScope.basePath }activitiesRec/goCardError.action";
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
		document.readPwdForm.action = "${sessionScope.basePath }activitiesRec/makeSure.action";
		document.readPwdForm.submit();
	}
}
//mod begin 20130901 for OR_HUB_201308_238 
//关闭密码键盘接口  OR_HUB_201308_238 20130901
function _closeEncryptKey(){
	var ret;
	try{
		ret = window.parent.document.getElementById("cardpluginid").CloseEncryptKey();
		return ret;
	}catch(e){
		return "";
	}
}
//获取储蓄卡密码
function getCardPasswordStatus() 
{
	// 检查是否超时
	if (limitTime <= 0 && submitFlag == 0)
	{ 
		//超时后调用关闭密码键盘接口
		if("1" == <%=IsNeedCloseEncryptKey%>){
			var result = _closeEncryptKey();
			if(result == "0"){
				setException("读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
			}
			else if(result == ""){
				//alert("调用超时后调用关闭密码键盘接口异常！");
				setException("系统设置明文模式异常，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
			}
			else{
				//alert("调用超时后调用关闭密码键盘接口失败！");
				setException("系统设置明文模式失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
			}
		}
		else{
			setException("读取用户银行卡密码超时，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
		}
	}
	//mod begin 20130901 for OR_HUB_201308_238 
	try 
	{
	    // 调用准备输入密码接口（ReadingPassword()）后，定时循环调用此接口获取用户密码输入状态。
		// 0 表示输入正常结束；-1 表示密码输入未完，异常结束输入；1 等待用户输入完毕
		var ret = ReadCardPasswordFinished();
		
		// 等待用户输入完毕
		if (ret == "1" || ret == 1)
		{
			// 读密码时长一共limitTime秒
			limitTime = limitTime - 1;
			
			// 处理页面显示
			document.getElementById("tTime").value = limitTime;
		}
		else if (ret == "0" || ret == 0) 
		{
			// 打开键盘串口、设置明文模式
			ret = OpenCom();
			if (ret != "0" || ret != 0)
			{
				setException("打开密码键盘失败，请取走您的储蓄卡。");
				return;
			}
			ret = SetWorkMode(0);
			if (ret != "0" || ret != 0)
			{
				setException("设置明文模式失败。请取走您的储蓄卡。");
				return;
			}
			
            // 提交
			doSub();															
		} 
		else if (ret == "-1")
		{
			setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
		}				
	}
	catch (e) 
	{
		setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
	}
}
//设置时间计算周期
function startclock() 
{	
	try 
	{
		waitingToken = setInterval("getCardPasswordStatus()", 1000);
	}
	catch (e) 
	{
		setException("读取用户银行卡密码失败，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
	}
}

//页面加载时执行
function bodyLoad() 
{
	document.getElementById("cardPwd").focus();
	
	try 
	{				
		// 关闭键盘串口
		var ret = CloseCom();
		if (ret != "0" && ret != 0) 
		{
			setException("准备刷卡前调用关闲密码键盘串口发生异常！");
			return;
		}
		
		// 准备输入密码 通知自助终端读卡设备准备读取用户银行卡密码，并切换到密码键盘模式
		var ret = ReadingPassword();
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
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />"/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		
		<%-- modify by sWX219697 2015-7-20 totalfee改为totalFee,findbugs修改--%>
		<!-- 用户投入的费用金额 -->
		<input type="hidden" id="totalFee" name="totalFee" value='<s:property value="#request.totalFee" />'/>
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
		<!-- 银联卡号 -->
		<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="#request.cardnumber" />'>
		<!-- 是否需要退卡 0:不需要 1:需要 -->
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
		<!-- 异常类型 -->
		<input type="hidden" id="errorType" name="errorType" value=''>
		<!-- 异常信息 -->
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>活动受理流程</h2>
					<div class="blank10"></div>
					<a title="选择活动档次" href="javascript:void(0)">1. 选择活动档次</a>
   					<a title="受理协议" href="javascript:void(0)">2. 受理协议</a> 
   					<a title="选择支付方式" href="javascript:void(0)">3. 选择支付方式</a>
   					<a title="插入储蓄卡" href="javascript:void(0)">4. 插入储蓄卡</a>
   					<a title="输入密码" href="javascript:void(0)" class="on">5. 输入密码</a>
   					<p class="recharge_tips">通过金属键盘输入储蓄卡密码。</p>
   					<a title="核对信息" href="javascript:void(0)">6. 核对信息</a>
   					<a title="完成" href="javascript:void(0)">7. 完成</a>
    			    
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
       						<img src="${sessionScope.basePath }images/jp.gif" />
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
