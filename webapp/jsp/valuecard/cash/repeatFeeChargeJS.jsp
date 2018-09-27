<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTermID = termInfo.getTermid();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/FeeChargePrinter_Hub.js?ver=${jsVersion }"></script>
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
			<%@include file="/jsp/customize/hub/common/fee/repeatFeeCharge.jsp" %>
		</form>
	</body>
	<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
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
			//打印缴费失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				var printInfo = new function()
				{
					//缴费号码
					this.pServNumber = "<s:property value='valueCardVO.servnumber' />";
					//终端编号
					this.pTermID = "<%=pTermID%>";
					//终端信息
					this.pTerminalInfo = "<%=pTerminalInfo%>";
					//终端机生成的交易流水
					this.pTerminalSeq = "<s:property value='valueCardVO.terminalSeq' />";
					//交易时间
					this.pRecDate = getDateTime();
					//投币金额
					this.pAmount = "<s:property value='valueCardVO.fee' />元";
					//交易状态
					this.pDealStatus = "需核实";
					//交易地址
					this.pOrgName = "<%=pOrgName%>";
					//打印时间
					this.pPrintDate = getDateTime();
				}
				
				repeatFeePrint(printInfo);
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				// 此处根据业务的需要，可进行判断返回URL
				document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
				document.forms[0].submit();
			}
		}
		
		function qryFeeHistory()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.action = "${sessionScope.basePath }charge/goQryFeeHistory.action";
				document.actform.submit();
			}
		}
		
	</script>
</html>