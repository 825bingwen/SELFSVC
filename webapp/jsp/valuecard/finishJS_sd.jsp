<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>

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
	<body onload="window.focus();onload()" scroll="no">
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='cardPayLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='cardPayLogVO.tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">您购买的电子有价卡已出票成功！</p>
       							<p class="desc pt20">请保存好您的的交易凭条。</p>
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
var submitFlag = 0;

var limitTime = 30;//取卡时间30秒

// 凭条打印标志
var printFlag = 0;

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
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
        document.forms[0].submit();
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
        var ret = OpenComByCard();
    
        ret = getCardPosition();
        
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

function onload()
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='servNumber' />交费成功");
    
    if ("<s:property value='payType' />" == "<%=Constants.PAYBYUNIONCARD %>")
    {
    	document.getElementById("highLight7").className = "on";
        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
        var ret = getCardPosition();
        
        //吐卡成功，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
        if (ret == "2")
        {
            startClock();
        }
    }
    else
    {
    	document.getElementById("highLight5").className = "on";
    }
    
    var pServNumber = "<s:property value='cardPayLogVO.servnumber' />";
	var pOrgName = "<%=pOrgName%>";  //打印地点
	var pPrintDate = getDateTime();  //打印日期
	var pTerminalSeq = "<s:property value='cardPayLogVO.terminalSeq' />";// 终端流水
	var totalFee = "<s:property value='valueCardVO.minMoney' />"; // 应缴费用
	var tMoney = "<s:property value='cardPayLogVO.tMoney' />"; // 实际缴费
	
	// 打印小票
	doPrintValueCard(pServNumber, pOrgName, pPrintDate, totalFee, tMoney);
	
	if ("<s:property value='payType' />" == "1")
	{
		var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
		if (printcontext != "")
		{
			doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
		}
	}
}

// 交费成功，有价卡购买成功时打印小票
function doPrintValueCard(pServNumber,pOrgName,pPrintDate,totalFee,tMoney) 
{
  try {
  	var ret;
  	try{
  		ret = window.parent.document.getElementById("prtpluginid").PrintPicture(1);
  	}
  	catch(e)
  	{
		alertError("警告:打印机控件未安装!");
		return;
  	}
  	if (ret == 1)
  	{
  	   alertError("警告:打印机缺纸或故障!");
  	   return;
  	}
  	else if (ret == 41)
  	{
  	   alertError("错误:打印机设备低层驱动程序未安装!");
  	   return;
  	}
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  ");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  自助终端平台电子有价卡购买凭据");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  手机号码: "+pServNumber);
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  应收费用: "+totalFee+"元");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  实缴费用: "+tMoney+"元");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");  	    
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
    	
  	<s:iterator value='cardList' >
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡号: " + "<s:property value='cardNo' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡面值: " + "<s:property value='cntTotal' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡有效期: " + "<s:property value='expDate' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡业务类型: " + "<s:property value='cardType' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  卡业务属性值: " + "<s:property value='cardBusiPro' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine("  平台交易流水号: " + "<s:property value='transActionId' />");
		ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
		
		if (ret == 1)
    	{
	        alertError("警告:打印机缺纸或故障!");
	        return;
    	}
	</s:iterator>
	
    if (ret == 1)
    {
        alertError("警告:打印机缺纸或故障!");
        return;
    }
  	
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  以上内容,如有不详之处,请向营业员查询.");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine(" ---------------------------------------------");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印地点:"+pOrgName+".");
  	ret = window.parent.document.getElementById("prtpluginid").PrintLine("  打印时间:"+pPrintDate+".");
  	if (ret == 1)
  	{
  	    alertError("警告:打印机缺纸或故障!");
  	    return;
  	}
	cutPaper();
	} catch(ex) {
 		alertError("打印电子充值卡信息出错,请联系营业厅服务员对电子充值卡购买状态进行确认！");
 		cutPaper();//出现问题切纸
	}	
}

</script>
