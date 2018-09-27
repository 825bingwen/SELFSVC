<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%
	// 终端机信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>选择身份证或随机密码页面</title>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/nx/js/romoveAclick_NX.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

//82、220 返回		
document.onkeydown = pwdKeyboardDown;

//监听键盘按下事件
function pwdKeyboardDown(e) 
{
	var key = GetKeyCode(e);

	//更正
	if (key == 77) 
	{
		preventEvent(e);
	}

	// 如果不是数字
	if (!KeyIsNumber(key)) 
	{
		return false;
	}
}

// 输入是否是数字校验
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

//键盘事件
function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);

	//返回
	if (key == 82 || key == 220) 
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		return;		
	}
	
	if(key == 49)
	{
		doSub(1);
	}
	else if(key == 50)
	{
		doSub(2);
	}		
}

//返回上一页
function goback(menuid)
{
	if (submitFlag == 0)
	{
		//弹出等待框
		openRecWaitLoading();
	
		submitFlag = 1;
		
		document.actform.action = "${sessionScope.basePath }prepareCard/validTelAndPwd.action";
		document.actform.submit();
	}
}

// 提交
function doSub(sele_rule) 
{		
	//防止重复操作
	if (submitFlag == 0) 
	{
		//弹出等待框
		openRecWaitLoading();
		
		submitFlag = 1;
	
		//身份证认证
		if(sele_rule==1)
		{
			document.actform.action = "${sessionScope.basePath}prepareCard/selectCert.action";
				
		}
		
		//短信随机码认证
		else if(sele_rule==2)
		{
			var tip = sendRandomPwd();
			if (tip != "") 
			{
				alertRedErrorMsg(tip);
				return;
			}
			
			document.actform.action = "${sessionScope.basePath}prepareCard/selectRandomPwd.action";
		}
		
		document.actform.submit();
	}
}

// 发送短信随机码
function sendRandomPwd()
{
	var url = "${sessionScope.basePath}login/sendRandomPwd_hub.action";
	var params = "servnumber=${sessionScope.CustomerSimpInfo.servNumber }";
	var resXml;
	
	var loader = new net.ContentLoaderSynchro(url, netload = function () 
	{
		resXml = this.req.responseText;
	}, null, "POST", params, "application/x-www-form-urlencoded");	
	
	return resXml;		
}

// add begin hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
function checkActFlag()
{
	// 从营销活动推荐过来的则再次校验打印机和缴费硬件
	if("1" == "<s:property value='recommendActivityFlag' />")
	{
		// 是否支持打印票据标志,0不支持,1:支持
		var isPrintFlag = window.parent.isPrintFlag;

		// 是否支持现金缴费标志,0不支持,1:支持
		var isCashEquip = window.parent.isCashEquip;
		
		// 是否支持银行卡缴费标志,0不支持,1:支持
		var isUnionPay = window.parent.isUnionPay;
		
		// 校验票据打印机 1-不打印，0-打印
		var canNotPrint = isPrintFlag == 1 ? (initListPrinter() == "" ? "0" : "1") : "1";
 
	 	setValue("canNotPrint",canNotPrint);
	 	
	 	var chkCash = isCashEquip == 1 ? (initCashPayEquip() == "" ? "1" : "0") : "0";
	 	
	 	var chkCardMsg = initUnionCardPayEquip("<%=termInfo.getUnionpaycode()%>", "<%=termInfo.getUnionuserid()%>");
	 	
	 	var chkCard = isUnionPay == 1 ? (chkCardMsg == "" ? "1": "0") : "0";
	 	
	 	var payTypeFlag = chkCash + "" + chkCard;
	 	
	 	setValue("payTypeFlag",payTypeFlag);
	}
}
// add end hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
</script>
</head>
<body scroll="no" onload="checkActFlag();">
	<form name="actform" method="post" target="_self">
		<!-- 错误信息 -->
		<input type="hidden" id="errormessage" name="errormessage" value='' />
			
		<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
		<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value='payTypeFlag' />"/>
		<!-- 是否打印小票  1-不打印，0-打印 -->
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />

		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>备卡</h2>
					<div class="blank10"></div>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. 输入手机号码</a> 
					<a href="javascript:void(0)" class="on">2. 选择认证方式</a>
					<a href="javascript:void(0)">3. 读取身份证信息</a>
   					<a href="javascript:void(0)">4. 费用确认</a>
   					<a href="javascript:void(0)">5. 选择支付方式</a>
   					<a href="javascript:void(0)">6. 备卡缴费</a>
   					<a href="javascript:void(0)">7. 吐卡打印小票</a>
				</div>
				
				<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank60"></div>
				<div class="p40">
					<p class=" tit_info"><span class="bg"></span>请选择认证方式：<span class="yellow"></span></p>
					<div class="blank10"></div>
					<div class="line"></div>
					<div class="blank20"></div>
					<div class="blank20"></div>
					
					<ul class="money_list_clearfix">
						<li class="tel_selectrule tc">
							<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(1); return false;">
								二代身份证(按1键)
							</a>
						</li>
						<li class="pt10"></li>	
						<li class="tel_selectrule tc">
							<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
								随机密码(按2键)
							</a>
						</li>
					</ul>
				</div>
			</div>	
			
				<div class=" clear"></div>

			</div>
		</div>

		<%@ include file="/backinc.jsp"%>
	</form>
</body>

</html>
