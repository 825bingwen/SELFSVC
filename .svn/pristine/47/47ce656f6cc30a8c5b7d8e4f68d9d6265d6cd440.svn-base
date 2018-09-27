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
		var p_status = "预存有礼受理失败，详情请咨询营业员！"; //交易状态
		var printcontext = "<s:property value='printcontext'/>";
		
		// 调用打印
		doPrintByActivitySD(p_telNum,p_orgName,p_date,p_termId,p_termName,p_tMoney,p_chargeId,p_terminalSeq,
		    p_recoid,p_status,p_recFee,p_preFee,p_actLevelName,p_groupName,p_custName,"", printcontext)                        
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
                        <a title="插入银联卡" href="#"">4. 插入银联卡</a>
                        <p class="recharge_tips">插入您的银联卡。业务办理结束后请<br />注意取回银联卡。</p>
                        <a title="输入密码" href="#">5. 输入密码</a>
                        <a title="完成" href="#" class="on">6. 完成</a>
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
