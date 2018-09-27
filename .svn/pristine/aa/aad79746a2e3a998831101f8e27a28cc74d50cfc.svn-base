<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
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
	String recStatus = (String) request.getAttribute("errormessage");
	if (recStatus == null)
	{
		recStatus = "";
	}
	String errFlag = (String)request.getAttribute("errFlag");
	if(errFlag == null){
		errFlag =  "";
	}
		
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_hb.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
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
		
		function doFinish()
		{
			//如果用户有投币，才打印失败凭条
			var money = parseInt("<s:property value='tMoney' />");
			if (money <= 0)
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
				var priAmount = "<s:property value='privMoney' />元";   //活动金额
				var privToMoney = "<s:property value='tMoney' />元"     //投币金额
				var pRecDate = "<s:property value='priRecDate' />";       //活动受理时间
				var pBackAmount = "<s:property value='priBackMoney' />元";	//退还金额
				var pDealNum = "<%=pDealNum%>";     //交易流水号
				var pDealTime = "<%=pDealTime%>";   //交易时间	
				var pAmount = "<s:property value='priBackMoney' />元";     //交易金额(缴费金额)
				var pDealStatus = "<%=recStatus%>"; //交易状态
				var pTerminalSeq = "<s:property value='terminalSeq' />";
				var printFlag = "<%=errFlag%>";  //errFlag 为0时没有缴费金额
				
				/*
				alert(pDealStatus + "   " +priAmount+" "+backAmount+" "+pAmount+"  "+recDate);
				*/
				
				/*
				alert(pServNumber + "	" + pOrgName + "	" + pPrintDate + "	" + pTerminalInfo + "	" 
						+ pDealNum + "	" + pDealTime + "	" + pAmount + "	" + recDate + " " + backAmount
						+ pDealStatus + "	" + pTerminalSeq);
				*/
				
				
				doPrintPrivPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, priAmount, privToMoney,
									pRecDate, pBackAmount, pDealNum, pDealTime, pAmount, pDealStatus, pTerminalSeq,printFlag);
						
			}
		}
		
		function goback(menuid)
		{	
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }privAccept/privFeeChargeType.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
			<input type="hidden" id="nCode" name="nCode" value='<s:property value="nCode" />'>
			<input type="hidden" id="privId" name="privId" value='<s:property value="privId" />'>
			<input type="hidden" id="privMoney" name="privMoney" value='<s:property value="privMoney" />'>			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>办理优惠交费流程</h2>
						<div class="blank10"></div>
						<a href="#">1. 输入手机号码</a> 
						<a href="#">2. 选择优惠</a> 
						<a href="#">3. 选择支付方式</a> 
						<a href="#">4. 投入纸币</a>
      					<p class="recharge_tips">支持10、50、100元面额的纸币。</p>
      					<a href="#" class="on">5. 完成</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
      							<s:property value="errormessage" />
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
