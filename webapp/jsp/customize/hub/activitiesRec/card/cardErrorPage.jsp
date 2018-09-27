<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
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
	
	String orgName = termInfo.getOrgname();
	
	String groupName = (String)request.getAttribute("groupName");
	String dangciName = (String)request.getAttribute("dangciName");
	String termName = (String)request.getAttribute("termName");
	
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// modify end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
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
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var tMoney = "<s:property value='unionpayfee' />";
		
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
				//如果用户有投币，才打印失败凭条
				var money = parseInt("<s:property value='unionpayfee' />");
				if (money <= 0)
				{
					return;
				}
				var p_activityId = "<s:property value='activityId' />";// 活动编码
				var p_dangciId = "<s:property value='dangciId' />";// 档次编码
				var p_servnumber = "<s:property value='servnumber' />";// 手机号码
				var p_orgName = "<%=orgName %>";  //打印地点
				var p_date = getDateTime();  //打印日期
				var p_termId = "<s:property value='termid' />"; //终端编码
				var p_termName = "<%=termName %>"; //终端名称
				var p_prepayFee = "<s:property value='prepayFee' />"; //受理金额
				var p_totalfee = <s:property value='unionpayfee' /> / 100;// 投币金额
				var p_terminalSeq = "<s:property value='terminalSeq' />";//终端流水
				var p_recoid = "<s:property value='recoid' />";// 交易流水
				var p_successBz = "<s:property value='successBz' />";//状态
				var yxfaFee_yuan = "<s:property value='yxfaFee_yuan' />";// 营销方案费用
				var ycFee_yuan = "<s:property value='ycFee_yuan' />";// 用户预存
				var p_dangciName = "<%=dangciName %>";// 档次名称
				var p_groupName = "<%=groupName %>";// 活动组名称
				var p_status = "促销活动受理失败,请凭交易凭条到营业厅办理退款！"; //交易状态
				
				//打印凭证
				if ("<%=canPrintReceipt %>" == "1")
				{	
					//modify begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
					var brandName = "${sessionScope.CustomerSimpInfo.brandName}";
					
					// modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
					// 缴费小票
					doPrintByActivity(p_activityId,p_dangciId,p_servnumber,p_orgName,p_date,p_termId,p_termName,p_prepayFee,p_totalfee,p_terminalSeq,p_recoid,p_status,yxfaFee_yuan,ycFee_yuan,p_dangciName,p_groupName, '', brandName);
					// modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
					//modify end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
				}
				
				// 打印银联小票
				var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
				if (printcontext != "")
				{
					doPrintUnionBill_hb(printcontext,p_terminalSeq,p_orgName,p_date);
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
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
			<!-- 营销推荐标识 -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
    					<a title="插入储蓄卡" href="javascript:void(0)">4. 插入储蓄卡</a>
    					<p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
    					<a title="输入密码" href="javascript:void(0)">5. 输入密码</a>
    					<a title="核对信息" href="javascript:void(0)">6. 核对信息</a>
    					<a title="完成" href="javascript:void(0)" class="on">7. 完成</a>
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
        						<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%> 
      							    							
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
