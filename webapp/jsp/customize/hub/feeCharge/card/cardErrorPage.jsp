<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	//modify  by  lWX162765 for R20130117009  begin
	NserCustomerSimp ncs = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	String uBrand = ncs.getBrandName();
	//modify by lWX162765  for R20130117009  end
            
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
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/UnionCard_PayFlow_Hub.js"></script>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='tMoney' />";
		
		var limitTime = 30;//取卡时间30秒
		
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
		
		// 读取读卡器取卡状态
		function takeOutBankCardStatus() 
		{
			limitTime = limitTime - 1;
			
			if (limitTime <= 0)
			{
				//超时，清除定时任务，同时吞卡
				clearInterval(waitingToken);
				
				captureUserCard();
				
				return;
			}
			
			try 
			{
				//生产用
				var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
				
				//测试用
				//var ret = 0;
				if (ret == "0" || ret == 0) 
				{	
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
				// 标识控件使用
			    closeStatus = 1;
			    
				//生产用
				var ret = TakeOutUserCard();
				
				// 标识控件未使用
				if (ret == "0")
				{
		        	closeStatus = 0;
		        }
				
				//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
				if (ret == "0" && "1" == "<%=needCaptureCard %>")
				{
					startClock();
				} 
				else if (ret != "0")
				{
					//吐卡异常
				}
			}
			//如果用户有投币，才打印失败凭条
			if (tMoney == null || tMoney == "" || tMoney == "null" || parseInt(tMoney) <= 0)
			{
				return;
			}			
			
			//打印缴费失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				var pDealNum = "<%=pDealNum%>";     //交易流水号
				var pDealTime = "<%=pDealTime%>";   //交易时间
				//modify begin by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求）
				//var pAmount = "<s:property value='tMoney' />元";     //交易金额
				var pAmount = "<s:property value='yingjiaoFee' />元";     //交易金额
				//modify end by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求）
				var pDealStatus = "缴费交易失败"; //交易状态
				var pTerminalSeq = "<s:property value='terminalSeq' />";
				var brand = "<%=uBrand%>"; //品牌
				
				//赠送金额 元
				//add by sWX219697 2015-1-4 11:55:20 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
				var presentFee = "0";
				
				// 打印缴费小票
				doPrintPayProofhub(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
						pDealStatus, pTerminalSeq,brand,"", "0", presentFee);
				
				// 打印银联小票
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
				}
						
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		</script>
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
    					<a href="javascript:void(0)">5. 插入储蓄卡</a>
    					<p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)" class="on">7. 完成</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>	
								<%=errorMessage %>      							
								<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>    							
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
