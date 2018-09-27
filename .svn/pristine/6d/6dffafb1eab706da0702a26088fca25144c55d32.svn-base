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
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayPrinter.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js?ver=${jsVersion }"></script>
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
						<a href="#">3. 支付</a>
      					<a href="#" class="on">4. 完成</a>
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
// 防止页面重复提交
var submitFlag = 0;

// 取卡时间30秒
var limitTime = 30;

// 缴费金额
var tMoney = "<s:property value='chargeLogVO.tMoney' />";

// 支付方式
var payType = "<s:property value='chargeLogVO.payType'/>";


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
    if (payType == "<%=Constants.PAYBYUNIONCARD %>")
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
    
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
               "<s:property value='servNumber' />交费失败");
   
   //如果用户有投币，才打印失败凭条
   if (null == tMoney || "" == tMoney || "null" == tMoney || parseInt(tMoney) <= 0)
   {
       return;
   }
   
   writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
               "打印失败凭证");      
   
   //打印缴费失败的凭证
   if ("<s:property value='canNotPrint'/>" == "0")
   {
       var pServNumber = "<s:property value='servNumber' />";
       var pOrgName = "<%=pOrgName%>";  //打印地点
       var pPrintDate = getDateTime();  //打印日期
       var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
       var pDealNum = "<s:property value='chargeLogVO.dealnum' />";     //交易流水号
       var pDealTime = "<s:property value='chargeLogVO.dealTime' />";   //交易时间
       var pAmount = "<s:property value='chargeLogVO.tMoney' />元";     //交易金额
       var pDealStatus = "交费交易失败"; //交易状态
       var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";
       
       var printcontext = '<%=request.getAttribute("printcontext") == null ? "" : (String) request.getAttribute("printcontext") %>';// 银联小票
       
       // 调用打印
       doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
                pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />", printcontext);
    }
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
            
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/inputNumber.action";
        document.forms[0].submit();
    }
}
</script>