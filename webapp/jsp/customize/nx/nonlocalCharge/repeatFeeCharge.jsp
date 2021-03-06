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
	
	// 弹出提示框使用金属键盘按键关闭
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
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<style>
		.repeatcasherror
		{ 
			width:598px; 
			height:150px; 
			color:red; 
			font-size:20px; 
			padding-top: 30px; 
			text-align:left; 
            _background:none;
        }
		.mr201{ margin-right:201px;}
		</style>
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
    					<p class="recharge_tips">查看您的话费信息，并提交充值交费金额进入支付。</p>
    					<a href="javascript:void(0)">3. 支付</a> 
    					<a href="javascript:void(0)" class="on">5. 完成</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="recharge_result tc">
       							<div class="repeatcasherror">
      							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;非常抱歉，您本次缴费可能有误。
      							请到宁夏移动网站或持小票凭证至营业前台查询核实。
      							由此给您带来的不便，敬请谅解！
      						    </div>      						    
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
		
		function doFinish()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.terminalSeq' />交费重复");
						
			var pAmount = "";
			var money = parseInt("<s:property value='tMoney' />");
			if (money > 0)
			{
				pAmount = "<s:property value='tMoney' />元";
			}			
			
			//打印交费重复的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				writeDetailLog("<%=chargeLogDetail %>", "打印交费重复凭证");
				
				var pServNumber = "<s:property value='' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				var pDealNum = "";     //交易流水号
				var pDealTime = "";   //交易时间
				var pDealStatus = "需核实"; //交易状态
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
