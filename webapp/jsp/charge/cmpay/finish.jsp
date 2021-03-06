<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
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
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var payType = "<s:property value='payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//取卡时间30秒
	
		var invoiceType = "";
		
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
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }mpaycharge/cmpay.action";
				document.forms[0].submit();
			}
		}
			
		function doFinish()
		{
			var pServNumber = "<s:property value='servnumber' />";
			var pOrgName = "<%=pOrgName%>";  //打印地点
			var pPrintDate = getDateTime();  //打印日期
			var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
			var pDealNum = "<s:property value='bossSeq' />";     //交易流水号
			var pDealTime = "<s:property value='dealTime' />";   //交易时间
			var tMoney = "<s:property value='tMoney' />元";     //交易金额
			var pDealStatus = "充值成功"; //交易状态
			var pTerminalSeq = "<s:property value='terminalSeq' />";//终端流水
			var pCmpaySeq = "<s:property value='cmpaySeq' />";//手机支付系统流水号
				
			//打印凭证
			if ("<%=canPrintReceipt %>" == "1")
			{	
				/*			
				alert(pServNumber + "	" + pOrgName + "	" + pPrintDate + "	" + pTerminalInfo + "	" 
						+ pDealNum + "	" + pDealTime + "	" + tMoney + "	" + pDealStatus + "	" 
						+ pTerminalSeq + "	" + pCmpaySeq);
				*/	
					
				doPrintPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
						pDealStatus, pTerminalSeq, pCmpaySeq, "1");
							
			}
		}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>主账户充值流程：</h2>
      					<div class="blank10"></div>
      					<a title="输入手机号码" href="#">1. 输入手机号码</a>       					
      					<a title="选择支付方式" href="#">2. 选择支付方式</a> 
      					<a title="选择金额" href="#">3. 选择金额</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#" class="on">5. 完成</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">充值金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">充值成功！</p>
          						<p class="desc pt20">请保存好您的的交易凭条。</p>          						
          						<div class="btn_box5 clearfix">          						         						
          							<a title="继续充值" href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值</a>          							        							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
	//继续充值
	function continueCharge()
	{	
		document.payMoneyForm.target = "_self";
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		document.payMoneyForm.submit();
	}
	</script>
</html>
