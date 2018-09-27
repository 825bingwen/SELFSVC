<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	// 终端机信息
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String pOrgName = termInfo.getOrgname(); // 地点
	String termName = termInfo.getTermname(); // 终端机名称
	String termId = termInfo.getTermid(); // 终端机编码
	
	// 终端机特性
	String termSpecial = termInfo.getTermspecial();
	
	// 是否可打印凭条标志
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	// 客户信息
	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	
	// 客户名称
	String custName = customer.getCustomerName();
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
<script type="text/javascript">
// 防止页面重复提交
var submitFlag = 0;

// 82、220 返回

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	// 更正
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
  		// 只允许输入0-9
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
	
	// 返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}			
}

function doFinish()
{
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='telNum' />交费失败");

	// 如果用户有投币，才打印失败凭条
	var money = parseInt("<s:property value='tMoney' />");
	if (money <= 0)
	{
		return;
	}
	
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"打印失败凭证");		

	// 打印缴费失败的凭证
	if ("<%=canPrintReceipt %>" == "1")
	{
		var p_telNum = "<s:property value='telNum' />";// 手机号码
		var p_custName = "<%=custName %>"; // 客户名称
		var p_orgName = "<%=pOrgName %>";  //打印地点
		var p_date = getDateTime();  //打印日期
		var p_termId = "<%=termId %>"; //终端编码
		var p_termName = "<%=termName %>"; //终端名称
		var p_tMoney = "<s:property value='tMoney' />";// 投币金额
		var p_chargeId = "<s:property value='chargeLogOID'/>";// 缴费日志流水
		var p_terminalSeq = "<s:property value='terminalSeq' />";//终端流水
		var p_recoid = "<s:property value='recoid' />";// 交易流水
		var p_recFee = "<s:property value='recFee' />";// 营销方案费用
		var p_preFee = "<s:property value='preFee' />";// 用户预存
		var p_actLevelName = "<s:property value='actLevelName'/>";// 档次名称
		var p_groupName = "<s:property value='groupName'/>";// 活动组名称
		var p_status = "预存有礼受理失败,请凭交易凭条到营业厅办理退款！"; //交易状态
		
		// 调用打印
		doPrintByActivitySD(p_telNum,p_orgName,p_date,p_termId,p_termName,p_tMoney,p_chargeId,p_terminalSeq,
            p_recoid,p_status,p_recFee,p_preFee,p_actLevelName,p_groupName,p_custName,"", '')
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
			
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
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
						<h2>预存有礼受理流程：</h2>
                        <div class="blank10"></div>
                        <a title="选择活动档次" href="#">1. 选择活动</a>
                        <a title="档次描述" href="#">2. 受理协议</a>  
                        <a title="选择支付方式" href="#">3. 选择支付方式</a> 
                        <a title="支付" href="#">4. 投入纸币</a>
                        <a title="完成" href="#" class="on">5. 完成</a>
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
