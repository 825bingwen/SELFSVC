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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/FeeChargePrinter_Hub.js"></script>
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
			//打印缴费失败的凭证
			if ("<%=canPrintReceipt %>" == "1")
			{
				var printInfo = new function()
				{
					//缴费号码
					this.pServNumber = "<s:property value='servnumber' />";
					//终端编号
					this.pTermID = "<%=pTermID%>";
					//终端信息
					this.pTerminalInfo = "<%=pTerminalInfo%>";
					//终端机生成的交易流水
					this.pTerminalSeq = "<s:property value='terminalSeq' />";
					//交易时间
					this.pRecDate = "<s:property value='dealTime' />";
					//投币金额
					this.pAmount = "<s:property value='tMoney' />元";
					//交易状态
					this.pDealStatus = "需核实";
					//交易地址
					this.pOrgName = "<%=pOrgName%>";
					//打印时间
					this.pPrintDate = getDateTime();
					
				}
				
				//测试打印
				//_repeatFeePrint(printInfo);	
				repeatFeePrint(printInfo);
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
		
		function qryFeeHistory()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/goQryFeeHistory.action";
				document.actform.submit();
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
      						<div class="recharge_result tc">
       							<div class="repeatcasherror">
      							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;非常抱歉，您本次缴费可能有误。
      							请在自助终端(帐单详单查询->交费历史查询)、湖北移动网站或持小票凭证至营业前台查询核实。
      							由此给您带来不便，敬请谅解！
      						    </div>
      						    <a href="javascript:void(0);" class="bt10 fr mr201" onmousedown="this.className='bt10on fr mr201'" onmouseup="this.className='bt10 fr mr201';" style="display:inline;" onclick="qryFeeHistory();return false;">缴费历史查询</a>
      						</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
