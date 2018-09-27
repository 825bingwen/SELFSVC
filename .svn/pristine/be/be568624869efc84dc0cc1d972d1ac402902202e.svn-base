<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
            
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
            
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// 弹出提示框使用金属键盘按键关闭开关
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	
	// 交费操作是否在终端机上记录详细日志。1，记；0，不记。
	String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
					
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
    					<p class="recharge_tips">插入您的银联卡。业务办理结束后请<br />注意取回银联卡。</p>
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)">7. 核对信息</a>
    					<a href="javascript:void(0)" class="on">8. 完成</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror_1">
      							<s:property value='errormessage' />
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='tMoney' />";
		
		var limitTime = 30;//取卡时间30秒
		
		//82、220 返回
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
			// 弹出提示框使用金属键盘按键关闭
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
		}
		
		// 读取读卡器取卡状态
		function takeOutBankCardStatus() 
		{
			writeDetailLog("<%=chargeLogDetail %>", "监控用户是否已取卡");
			
			limitTime = limitTime - 1;
			
			if (limitTime <= 0)
			{
				//超时，清除定时任务，同时吞卡
				clearInterval(waitingToken);
				
				writeDetailLog("<%=chargeLogDetail %>", "用户在规定时间内未取卡，吞卡");
				
				captureUserCard();
				
				return;
			}
			
			try 
			{
				//生产用
				var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
				
				if (ret == 0) 
				{	
					writeDetailLog("<%=chargeLogDetail %>", "用户已取卡");
					
					//已经取走卡，清除定时任务
					clearInterval(waitingToken);									
				}			
			}
			catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
		}
		
		function startClock()
		{
			try 
			{
				waitingToken = setInterval("takeOutBankCardStatus()", 1000);
			}
			catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
		}
		
		function doFinish()
		{
			if (needReturnCard == "1")
			{
				// 初始化
				var ret = InitReadUserCard();
				
				//生产用
				ret = TakeOutUserCard();
				
				//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
				if (ret == "0")
				{
					writeDetailLog("<%=chargeLogDetail %>", "吐卡成功");
							
					if ("1" == "<%=needCaptureCard %>")
					{
						startClock();
					}
				}
				else if (ret != "0")
				{
					//吐卡异常
					writeDetailLog("<%=chargeLogDetail %>", "吐卡失败");
				}
			}			
			
			// 打印缴费失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				var pServNumber = "<s:property value='chargeLogVO.servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				var pDealNum = "<%=pDealNum%>";     //交易流水号
				var pDealTime = "<%=pDealTime%>";   //交易时间
				var pAmount = "<s:property value='tMoney' />元";     //交易金额
				var pDealStatus = "交费交易失败"; //交易状态
				
				// quitFlag=1时，是用户主动退出交费
				if ("<s:property value='quitFlag' />" == "1")
				{
					pAmount = "主动退出交费交易";
				}
				
				var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";
				
				writeDetailLog("<%=chargeLogDetail %>", "打印跨省交费失败凭条");

				// 调用打印
				doPrintPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
						pDealStatus, pTerminalSeq, "", "0");
						
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />退出充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>
