<%--
 @User: m00227318
 @De: 2012/09/20
 @comment: 显示积分兑换电子券办理成功
 @remark: create m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="java.util.List"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO"%>
<%
    String successMessage = (String) request.getAttribute("successMessage");

    TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
    // 当前客户
    NserCustomerSimp customerSimp = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    
    //手机号
    String pServNumber = customerSimp.getServNumber();
        
    //打印地点
	String pOrgName = termInfo.getOrgname();
	//终端信息
	String pTerminalInfo = termInfo.getTermname();
	//积分兑换电子券的交易流水
	String pExchNum = (String)request.getAttribute("recoid");
	//消费的积分数
	String pExScore = (String)request.getAttribute("exchangeScore");
	//用户可用积分数
	String pUsableScore = (String)request.getAttribute("usableScore");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
			//防止页面重复提交
			var submitFlag = 0;

			document.onkeydown = pwdKeyboardDown;
			document.onkeyup = pwdKeyboardUp;

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

			function pwdKeyboardUp(e)
			{
				var key = GetKeyCode(e);
				if (key == 82 || key == 220)
				{
					goback("<s:property value='curMenuId' />");
					return;
				}
			}

			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
			
					document.getElementById("curMenuId").value = menuid;					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
					document.forms[0].submit();
				}
			}
    		//打印积分兑换电子券凭证
			function doPrintProof()
			{
				//手机号
				var pServNumber = "<%=pServNumber%>";
				//打印地点
				var pOrgName = "<%=pOrgName%>";
				//打印日期 
				var pPrintDate = getDateTime();
				//终端信息 
				var pTerminalInfo = "<%=pTerminalInfo%>";
				//兑换流水
				var pExchNum = "<%=pExchNum%>";
				//消费积分
				var pExScore = "<%=pExScore%>";
				//电子券金额
				var pECoupon = pExScore/100;	
				//可用积分
				var pUsableScore = "<%=pUsableScore%>";
				// 打印积分兑换电子券小票
				doPrintScoreExECou(pServNumber,pOrgName,pPrintDate,pTerminalInfo,pExchNum,pExScore,pECoupon,pUsableScore);
			}
		</script>
	</head>
	<body onload="doPrintProof();" scroll="no">
		<form name="actform" method="post">
			<!--add begin CKF76106 2012/07/10 R003C12L07n01 OR_HUB_201206_597  -->
			<input type="hidden" id="buttonType" name="buttonType"
				value="<s:property value='buttonType'/>" />
			<!--add end CKF76106 2012/07/10 R003C12L07n01 OR_HUB_201206_597  -->
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok"> 
							<%
 								if (successMessage == null || "".equals(successMessage)) 
 								{
 									out.print("操作失败，请稍后再试。");
 								} 
 								else 
 								{
 									out.print(successMessage);
 								}
 							%> 
 							</span>
						</dd>
					</dl>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
