<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js?ver=${jsVersion }"></script>	
</head>
<body scroll="no">
	<form name="readPwdForm" method="post" target="_self">
		<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />">
		<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
		<input type="hidden" id="cardnumber" name="cardPayLogVO.cardnumber" value="<s:property value='cardPayLogVO.cardnumber' />">
		<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
		<!-- 有价卡类型 -->
		<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
		<!-- 有价卡数量 -->
		<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
		<!-- 有价卡面值 -->
		<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		
		<%@include file="/jsp/customize/hub/common/fee/card/cardPassword.jsp" %>
	</form>
	<script>
		// 设置左侧页面的高亮显示
		document.getElementById("highLight6").className = "on";
		
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
				document.readPwdForm.action = "${sessionScope.basePath }valueCard/goCardError.action";
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
				document.readPwdForm.action = "${sessionScope.basePath }valueCard/makeSure.action";
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
						setException("系统设置明文模式异常，无法使用储蓄卡进行充值。请取走您的储蓄卡。");
					}
					else{
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
		clearTimeout(timeOut);
	    bodyLoad();     
	    // 标识控件使用
	   	closeStatus = 1; 
	</script>
</body>
</html>