<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	// 单位
	String pOrgName = termInfo.getOrgname();
	
	// 终端名称
	String pTerminalInfo = termInfo.getTermname();
	
	// 终端特性
	String termSpecial = termInfo.getTermspecial();
	
	// 是否支持打印票据
	String canPrintReceipt = termSpecial.substring(0, 1);
            
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	// 弹出提示框使用金属键盘按键关闭
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
						<a href="#">1. 输入手机号码</a> 
						<a href="#">2. 选择支付方式</a> 
						<a href="#">3. 投入纸币</a>
      					<p class="recharge_tips">支持10、50、100元面额的纸币。</p>
      					<a href="#" class="on">4. 完成</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror_1">
      							<s:property value="errormessage" />
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
		
		//82、220 返回
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
			//退出：82
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
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
		
		// 打印小票	
		function doFinish()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />交费失败");
						
			var pAmount = "";
			var money = parseInt("<s:property value='tMoney' />");
			if (money > 0)
			{
				pAmount = "<s:property value='tMoney' />元";
			}			
			
			//打印缴费失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				writeDetailLog("<%=chargeLogDetail %>", "打印跨省交费失败凭证");
				
				var pServNumber = "<s:property value='chargeLogVO.servnumber' />";
				
				// 打印地点
				var pOrgName = "<%=pOrgName%>";
				
				// 打印日期  
				var pPrintDate = getDateTime();
				
				// 终端信息
				var pTerminalInfo = "<%=pTerminalInfo%>"; 
				
				// 交易流水号
				var pDealNum = "<s:property value='dealNum' />";
				
				// 交易时间
				var pDealTime = "<%=pDealTime%>";
				
				// 交易状态
				var pDealStatus = "缴费交易失败"; 
				
				// 终端流水
				var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";
				
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
				document.getElementById("curMenuId").value = menuid;
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>
