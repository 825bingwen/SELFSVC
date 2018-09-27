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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		var tMoney = "<s:property value='tMoney' />";
		
		var payType = "<s:property value='payType' />";
		
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
    					<s:if test="1 == morePhoneFlag">
    					    <a href="#">1. 输入手机号码</a> 
	                        <a href="#">2. 选择支付方式</a> 
	                        <a href="javascript:void(0)">3. 免责声明</a>
	                        <a href="javascript:void(0)">4. 插入银联卡</a>
	                        <a href="javascript:void(0)">5. 输入密码</a>
	                        <a href="javascript:void(0)" class="on">6. 完成</a>
    					</s:if>
    					<s:else>
    					    <a href="javascript:void(0)">1. 输入手机号码</a> 
	                        <a href="javascript:void(0)">2. 选择支付方式</a>
	                        <a href="javascript:void(0)">3. 选择金额</a> 
	                        <a href="javascript:void(0)">4. 免责声明</a>
	                        <a href="javascript:void(0)">5. 插入储蓄卡</a>
	                        <p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
	                        <a href="javascript:void(0)">6. 输入密码</a>
	                        <a href="javascript:void(0)" class="on">7. 完成</a>
    					</s:else>
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
<script type="text/javascript">
// 读取读卡器取卡状态
function takeOutBankCardStatus() 
{
    limitTime = limitTime - 1;
    
    if (limitTime <= 0)
    {
        //超时，清除定时任务，同时吞卡
        clearInterval(waitingToken);
        
        //吞卡
        CaptureBankCard();
        
        return;
    }
    
    try 
    {
        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
        var ret = getCardPosition();
        
        //测试用
        if (ret == "1" || ret == 1)
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
    if (payType == "<%=Constants.PAY_BYCARD %>")
    {
        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
        var ret = OpenComByCard();
        
        ret = getCardPosition();
        
        //吐卡成功，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
        if (ret == "2" || ret == 2)
        {
            startClock();
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
	    if ("1" == "<s:property value='morePhoneFlag'/>")
	    {
	        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
	        var ret = getCardPosition();
	        
	        //吐卡成功，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
	        if (ret == "2")
	        {
	            startClock();
	        }
	        
	        // 凭条标记为已打印
	        document.getElementById("printPayProFlag").value = "1";
	        
	        // 手机号码
	        var pServNumber = "";
	        
	        // 终端交易流水
	        var pTerminalSeq = "";
	        
	        // 交易流水
	        var pDealNum = "";
	        
	        // 客户名称
	        var pCustName = "";
	        
	        // 单笔业务缴费金额
	        var tMoney = "";
	        
	        var pStatus = "";
	        <s:iterator value="morePhones" id="morePhone">
	             pServNumber = pServNumber + "<s:property value='#morePhone.servnumber' />" + ",";
	             pTerminalSeq = pTerminalSeq + "<s:property value='#morePhone.chargeLogOID' />" + ",";
	             pDealNum = pDealNum + "<s:property value='#morePhone.dealnum'/>" + ",";
	             pCustName = pCustName + "<s:property value='#morePhone.custName'/>" + ",";
	             tMoney = tMoney + "<s:property value='#morePhone.tMoney'/>" + ",";
	             pStatus = pStatus + "<s:property value='#morePhone.chargeStatus'/>" + ",";
	        </s:iterator>
	        var pOrgName = "<%=pOrgName%>";  //打印地点
	        var pPrintDate = getDateTime();  //打印日期
	        var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
	        var pDealTime = "<s:property value='pDealTime' />";   //交易时间
	        var totalFee = "<s:property value='totalFee' />元";     //交易金额
	        
	        var printcontext = "<s:property value='printcontext' />";// 银联小票
	        
	        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	                "自动打印凭条");
	        
	        doPrintMorePhoneSD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,totalFee,
	                     pTerminalSeq, pCustName, pStatus, printcontext);
	    }
	    else
	    {
            var pServNumber = "<s:property value='servnumber' />";
            var pOrgName = "<%=pOrgName%>";  //打印地点
            var pPrintDate = getDateTime();  //打印日期
            var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
            var pDealNum = "<%=pDealNum%>";     //交易流水号
            var pDealTime = "<%=pDealTime%>";   //交易时间
            var pAmount = "<s:property value='tMoney' />元";     //交易金额
            var pDealStatus = "交费交易失败"; //交易状态
            var pTerminalSeq = "<s:property value='terminalSeq' />";

            // 调用打印
            doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
                    pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />");
                    
            // 打印银联小票
            var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';
            if (printcontext != "")
            {
                doPrintUnionBill_sd(printcontext, pTerminalSeq, pOrgName, pPrintDate);
            }
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
