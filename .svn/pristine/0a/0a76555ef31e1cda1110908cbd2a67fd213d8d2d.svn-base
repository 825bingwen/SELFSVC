<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
	
	// 终端机信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
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
		<link href="${sessionScope.basePath }css/newAdd.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
		<style>
			.scrollBody{width:712px; height:536px; background:url(${sessionScope.basePath }images/bg_1_right.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bg_1_right.png");_background:none; margin-top:0px; margin-left:0px;}
			.scrollBody .packageList{width:600px; height:400px; font-size:18px; margin-left:20px; _margin-left:10px; margin-top:20px; line-height:35px;}
			.scrollBody .scrollBar{width:70px; height:285px; margin-top:80px;}
			.scrollBody .scrollBar .btnTop{width:70px; top:-52px; height:52px; background:url(${sessionScope.basePath }images/arrow_up.png) no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/arrow_up.png");_background:none}
			.scrollBody .scrollBar .btnDown{width:70px; bottom:-52px; height:52px; background:url(${sessionScope.basePath }images/arrow_down.png) no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/arrow_down.png");_background:none}
			.scrollBody .scrollBar .iconScroll{width:66px; height:36px; line-height:36px; background:url(${sessionScope.basePath }images/bg_66_30.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bg_66_30.png");_background:none}
		</style>
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<%-- 错误信息 --%>
			<input type="hidden" id="errormessage" name="errormessage" value="" />
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="0" />
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="11"/>
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>在线开户</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)" class="on">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
	   					<a href="javascript:void(0)">3. 产品选择</a> 
	   					<a href="javascript:void(0)">4. 号码选择</a>
	   					<a href="javascript:void(0)">5. 设置服务密码</a>
	   					<a href="javascript:void(0)">6. 费用确认</a>
	   					<a href="javascript:void(0)">7. 开户缴费</a>
	   					<a href="javascript:void(0)">8. 取卡打印小票</a>
					</div>
					
					<!-- 滚动条 开始 -->
					<div class="scrollBody">
						<div class="packageList" id="inn">
							<div class="packageList_body" id="rightDiv">
								<s:iterator value="agreementList" id="agreement">
									<s:property value="#agreement.description" escapeHtml="false"/>
								</s:iterator>
							</div>
						</div>
						<div class="scrollBar">
							<input type="button" class="btnTop" onclick="myScroll.moveUp(30)" />
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
							<div class="iconScroll" id="gunDom">0%</div>
						</div>
						<div class=" tr" style="position: absolute; top: 470px; left: 800px;"> 
	  						<a class="tongyi" href="javascript:void(0);" onMouseDown="this.className='tongyi_on'" onMouseUp="this.className='tongyi';" onclick="doSub();return false;"></a> 
	   					</div>
					</div>
					<script type="text/javascript">
						myScroll = new virtualScroll("inn","gunDom");
						myScroll.parentTop = myScroll.parentTop - 121;
					</script>
					<!-- 滚动条 结束 -->
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
		<div class="popup_confirm" id="printCheck_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p id="printPromptId"></p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="#"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';printConfirm();">是</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';chooseNo();">否</a>
				</span>
			</div>
		</div>
	</body>
</html>
<script language="javascript">
			
	// 防止重复提交
	var submitFlag = 0;
	
	// 是否支持打印票据标志,0不支持,1:支持
	var isPrintFlag = window.parent.isPrintFlag;

	// 是否支持现金缴费标志,0不支持,1:支持
	var isCashEquip = window.parent.isCashEquip;

	// 是否支持银行卡缴费标志,0不支持,1:支持
	var isUnionPay = window.parent.isUnionPay;

	// 是否支持读取二代身份证信息标志,0不支持,1:支持
	var is2GIDFlag = window.parent.is2GIDFlag;

	// 是否支持SIM卡读卡器
	var SIMreaderFlag = window.parent.SIMreaderFlag;

	// 0-打印机正常，1-打印机异常；
	var printIsOk = 0;
	
	// 校验现金识币器
	var chkCashMsg = "";
	
	// 校验银联读卡器
    var chkCardMsg = "";
	
	// 选择No提示信息
	var chooseNoAlertMsg = "";
	
	// 82、220 返回		
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
	
	// 按键是否是数字判断
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
		//确认
		else if (key == 13 || key == 89 || key == 221)
		{
			doSub();
		}			
	}
	
	// 返回首页
	function goback(menuid)
	{
		goHomePage();
	}
	
	// 提交转到读身份证页面
	function doSub() 
	{
		if (submitFlag == 0) 
		{
			// 票据打印机异常不能打印小票，需用户进行确认
			if (printIsOk == 1)
			{
				openRealNameConfirm("票据打印机异常不能打印小票，是否继续？", "票据打印机异常，用户已取消操作");
			}
			// 现金识币器有问题，银联读卡器可用
			else if (chkCashMsg != "" && chkCardMsg == "")
		 	{
		 		chkCashMsg = "";
		 		setValue("payTypeFlag","01");
		 		openRealNameConfirm("现金识币器故障，本次开户只能用银联卡开户，是否继续？", "现金识币器异常，用户已取消操作");
		 	}
		 	// 现金识币器可用，银联读卡器有问题
		 	else if (chkCashMsg == "" && chkCardMsg != "")
		 	{
		 		chkCardMsg = "";
		 		setValue("payTypeFlag","10");
		 		openRealNameConfirm("银联读卡器故障，本次开户只能用现金开户，是否继续？", "银联读卡器异常，用户已取消操作");
		 	}
			else
			{
				submitFlag = 1;	//提交标记
				openRecWaitLoading();
				document.dutyInfoForm.target = "_self";
				document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/readCert.action";
				document.dutyInfoForm.submit();
			}
		}
	}
	
	// 出现异常
	function setException(errorMsg) 
	{			
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			openRecWaitLoading();
			
			document.getElementById("errormessage").value = errorMsg;
	
			//异常处理，只要出现了异常就要记录日志  
			document.dutyInfoForm.target = "_self";
			document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/installError.action";
			document.dutyInfoForm.submit();
		}
	}	
	
	// 首先加载
	function doLoad()
	{
		// 校验设备可用性
		chkEquipment();
		
		var msg = '<s:property value="blankCardTypeAlertMsg"/>';
		if (null != msg && msg != "")
		{
			// 终端机支持空白卡类写卡型提示信息
			alertSuccessMsg(msg);
		}
	}	
	
	// 弹出确认框
	function openRealNameConfirm(content, noMsg)
	{
		chooseNoAlertMsg = noMsg;
		document.getElementById('printPromptId').innerHTML = content;
		wiWindow = new OpenWindow("printCheck_confirm",708,392);
	}
	
	// 是否打印小票  1-不打印，0-打印
	function printConfirm()
	{
		// 现金识币器有问题，银联读卡器可用
	 	if (chkCashMsg != "" && chkCardMsg == "")
	 	{
	 		chkCashMsg = "";
	 		setValue("payTypeFlag","01");
	 		wiWindow.close();
	 		openRealNameConfirm("现金识币器故障，本次开户只能用银联卡开户，是否继续？", "现金识币器异常，用户已取消操作");
	 		return;
	 	}
	 	// 现金识币器可用，银联读卡器有问题
	 	else if (chkCashMsg == "" && chkCardMsg != "")
	 	{
	 		chkCardMsg = "";
	 		setValue("payTypeFlag","10");
	 		wiWindow.close();
	 		openRealNameConfirm("银联读卡器故障，本次开户只能用现金开户，是否继续？", "银联读卡器异常，用户已取消操作");
	 	}
	 	else
	 	{
	 		// 票据打印机异常不能打印小票，需用户进行确认
			if (printIsOk == 1)
			{
				document.getElementById('canNotPrint').value = "1";
			}
		 	
		 	if (submitFlag == 0) 
			{
				// 提交标记
			 	submitFlag = 1;	
				
				// 开启等待div
				openRecWaitLoading();
				
				document.dutyInfoForm.action = "${sessionScope.basePath }cardInstall/readCert.action";
				document.dutyInfoForm.submit();
			}
	 	}
	}	
	
	// 校验设备可用性
	function chkEquipment()
	{
	    var message = "";
	 	
	    // 校验读卡、写卡器
	    if (SIMreaderFlag == 1)
	    {
	    	message = initBlankCardReader();
	    }
	    else
	    {
	    	message = "该终端机不支持空白卡读卡器，无法开户！";
	    }
		
	    // 提示信息
		if (message != "")
		{
			setException(message);
		}
		
		// 发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
		message = checkReadCardStatus();
		if (message != "")
		{
			setException(message);
		}
		
		// 读取空白卡序列号
		var blankCardSN = readBlankCardSN();
		
		if (blankCardSN.indexOf("1~") != -1)
		{
			setException(blankCardSN.split('~')[1]);
			return;
		}
		
		if(blankCardSN.length != 20)
		{
			setException("对不起，卡类型不正确，请联系营业厅管理员!");
			return;
		}
		
	 	// 校验身份证读卡器
	 	if (is2GIDFlag == 1)
	    {
	 		message = initOpenIdCardReader();
	    }
	    else
	    {
	    	message = "该终端机不支持身份证读卡器，无法开户！";
	    }
	 	
	 	// 提示信息
	    if (message != "")
		{
			setException(message);
		}
	    
	 	// 校验票据打印机
	 	if (isPrintFlag == 1)
	 	{
	 		// 校验票据打印机
		    message = initListPrinter();
		    if (message != "")
			{
		    	printIsOk = 1;
			}
	 	}
	 	else
	 	{
	 		printIsOk = 1;
	 	}
	 	
	 	// 校验现金识币器
	 	if (isCashEquip == 1)
	 	{
	 		chkCashMsg = initCashPayEquip();
	 	}
	 	else
	 	{
	 		chkCashMsg = "该终端机不支持现金识币器";
	 	}

	    // 校验银联读卡器
	    if (isUnionPay == 1)
	    {
	    	chkCardMsg = initUnionCardPayEquip();
	    }
	    else
	    {
	    	chkCardMsg = "该终端机不支持银联读卡器";
	    }
	    
	    // 判断如果[现金识币器]和[银联读卡器]都有问题时，导向到错误页面
	    if (chkCashMsg != "" && chkCardMsg != "")
	    {
	    	setException("现金识币器和银联读卡器都出现故障！");
	    }
	}
	
	// 提示信息选择"否"
	function chooseNo()
	{
		wiWindow.close();
		setException(chooseNoAlertMsg);
	}
	
</script>