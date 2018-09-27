<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	           
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}

	// 吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	
	// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
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
		<script type="text/javascript" src="${sessionScope.basePath}js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<s:if test="chargeLogVO.payType == 4">
	      					<a href="#">1. 输入手机号码</a> 
	      					<a href="#">2. 选择支付方式</a> 
	      					<a href="#">3. 投入纸币</a>
	          				<a href="#" class="on">4. 完成</a>
          				</s:if>
          				<s:else>
          					<a href="javascript:void(0)">1. 输入手机号码</a> 
							<a href="javascript:void(0)">2. 选择支付方式</a>
	    					<a href="javascript:void(0)">3. 选择金额</a> 
	    					<a href="javascript:void(0)">4. 免责声明</a>
	    					<a href="javascript:void(0)">5. 插入银联卡</a> 
	    					<a href="javascript:void(0)">6. 输入密码</a>
	    					<a href="javascript:void(0)">7. 核对信息</a>
	    					<a href="javascript:void(0)" class="on">8. 完成</a>
          				</s:else>
  					</div>
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='chargeLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="recharge_result tc">
									<s:if test="chargeLogVO.payType == 4">
										<p class="title yellow pt30">您的充值交费已成功！</p>
                                    </s:if>
                                    <s:else>
										<p class="title yellow pt30">您的充值交费已成功！请取走您的银联卡。</p>
	                                </s:else>
	                                <p id='resultMsg' class="desc pt20">如果需要，请选择打印小票，并保存好。</p>
       							
          						<div class="btn_box3_echo clearfix">
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;" style="margin-left:20px; ">继续交费 (请按1键)</a>
          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">打印小票 (请按2键)</a>
									<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="_margin-top:0px; margin-left:20px; ">交费完成 (请按3键)</a>
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			<%@ include file="/backinc.jsp"%>	
		</form>
		<script type="text/javascript">
		// 关闭读卡器,退卡
		doFinish();
		
		var submitFlag = 0;
			
		var payType = "<s:property value='chargeLogVO.payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//取卡时间30秒
	
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//返回/退出(R)
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}	
			//继续充值交费(1)
			if (key == 49)
			{
				continueCharge();
			}
			// 缴费完成(3)
			if (key == 51)
			{
				goHomePage();
			}
			
			// 打印小票(2)
			if (key == 50)
			{
				printReceipt();
			}
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
				
				if (ret == "0" || ret == 0) 
				{	
					//已经取走卡，清除定时任务
					clearInterval(waitingToken);
					
					writeDetailLog("<%=chargeLogDetail %>", "用户已取卡");									
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
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />交费成功");
			
			if (payType == "1" && needReturnCard == "1")
			{
				// 初始化
				var ret = InitReadUserCard();
	
				// 退卡
				ret = TakeOutUserCard();
				
				// 吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
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
					writeDetailLog("<%=chargeLogDetail %>", "吐卡异常");
				}
			}
				
		}
		
		//继续充值缴费
		function continueCharge()
		{	
			if (submitFlag == 0)
			{
				submitFlag = 1;
											
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				document.payMoneyForm.target = "_self";
				document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
				document.payMoneyForm.submit();
			}		
		}
		
		//  打印小票
		var printReceiptFlag = 0;
		function printReceipt()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />选择打印小票");
			
			if (printReceiptFlag == 0)
			{
				printReceiptFlag = 1;
				
				// 按钮按下，置不可用状态
				document.getElementById('dayinButton').className = 'hover';
				document.getElementById('dayinButton').onmousedown = "";
				document.getElementById('dayinButton').onmouseup = "";
				document.getElementById('dayinButton').onclick = "";
				
				// 手机号码
				var pServNumber = "<s:property value='chargeLogVO.servnumber' />";
				
				// 操作员
				var pOperID = "<%=termInfo.getOperid() %>";  
				
				// 交易流水号
				var pDealNum = "<s:property value='dealNum' />"; 
				
				// 交易时间
				var pDealTime = "<s:property value='dealTime' />";
				
				// 交费金额
				var tMoney = "<s:property value='tMoney' />元";
				
				// 客户姓名
				var pSubsName = "<s:property value='custName' />";
				
				// 最新余额
				var pLatestBalance = "<s:property value='balance' />元";
				
				//打印小票
				if ("<%=canPrintReceipt %>" == "1")
				{	
					writeDetailLog("<%=chargeLogDetail %>", "自动打印跨省交费小票");
					
					// 打印小票
					doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。";
				}
			}
		}
		
		// 返回
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />退出跨省异地充值打印功能");
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
	</script>
	</body>
</html>
