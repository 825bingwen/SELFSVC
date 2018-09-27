<%--
 @User: 高群/g00140516
 @De: 2012/03/11
 @comment: 宁夏现金稽核功能优化
 @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>现金稽核成功</title>
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>

<script type="text/javascript">

var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) 
{
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
   		return ;
	}
	// add begin jWX216858 2014/08/21 OR_NX_201408_645_宁夏_自助终端票据明细打印增加数字键盘控制功能
	if (key == 13 || key == 89 || key == 221)
	{
	   printReceipt();
	}
	// add end jWX216858 2014/08/21 OR_NX_201408_645_宁夏_自助终端票据明细打印增加数字键盘控制功能
	
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
	{
		try
		{
			wiWindow.close();
		}
		catch (ex){}
				
		return;
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
}	

function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;			
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }managerOperation/selectOperationType.action";
		document.actform.submit();
	}
}

//现金识币器状态检查定时器句柄
var checkCashToken;	 

// 检测现金识币器状态，如果为0-正常，则转向首页
function toFramePage()
{
	var cashStatus = "";
			
	try
	{
		// 调用识币器状态检测接口，取识币器状态
		cashStatus = checkCashStatus();
	}
	catch (e) 
	{
		clearInterval(checkCashToken);
		alert("现金识币器出现异常,检查识币器状态失败");
		return;
	}
	
	if (cashStatus == 0)
	{
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "识币器正常，返回首页");
		
		clearInterval(checkCashToken);
		
		window.location.href = "${sessionScope.basePath}login/goHomePage.action";
	}
}

// 系统页面刷新时间
var checkInterval = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CASHSTATUS_CHECKINTERVAL) %>;
	
function printReceipt()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"进行现金结款单打印");
		
		// 启动进度条
		openRecWaitLoading_NX("recWaitLoading");
		
		// 打印结账单
		var url = "${sessionScope.basePath }managerOperation/getPrintedData.action";
		var params = "lastAuditEndTime=" + document.getElementById("lastAuditEndTime").value + "&sysMoney=<s:property value='sysMoney' />&realMoney=<s:property value='realMoney' />&number=" + Math.random();
		var flag = "";
		var loader = new net.ContentLoaderSynchro(url, netload = function () {
						var resXml = this.req.responseText;
						flag = printAuditReceipt("<s:property value='regionName' escape='false' />", 
										  "${sessionScope.TERMINALINFO.orgname }", 
									      "${sessionScope.TERMINALINFO.operid }", getDateTime(), resXml);
									      if(flag == "success")
										  {
										  	 writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "打印成功，更新打印日志记录");
										  
										     var auditId = document.getElementById("auditId").value;
										     
										     // 更新打印状态
										  	 new net.ContentLoaderSynchro("${sessionScope.basePath }managerOperation/updatePringFlag.action?number=" + Math.random()+"&auditId=" + auditId, netload = function () {}, null, "POST", "", null);
										  	 
										  	 checkCashToken = setInterval("toFramePage()", checkInterval);		
										  }
										  else
										  {
										  	  writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "打印失败");
										  	  
										  	  checkCashToken = setInterval("toFramePage()", checkInterval);
										  }	
						}, null, "POST", params, null);
	
	}
}

function doLoad()
{	
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"现金稽核操作结束");
}
</script>
</head>
<body scroll="no" onload="doLoad();">
	<form name="actform" method="post">			
		<input type="hidden" id="auditEndTime" name="auditEndTime" value="<s:property value='auditEndTime' />">
		<input type="hidden" id="lastAuditEndTime" name="lastAuditEndTime" value="<s:property value='lastAuditEndTime' />">
		<input type="hidden" id="auditId" name="auditId" value="<s:property value='auditId' />">
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<h1><span></span>业务办理</h1>
				
				<div class="service_brand w966 m0auto">
					<div class="service_index_list">
						<p class="hot_service"></p>
						
						<div class="p40">
					      	<div class="blank10"></div>
							<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">自助终端提醒您：</span></p>
							<div class="blank25"></div>
							<div class="blank25"></div>
							<!-- modify begin jWX216858 2014-09-09 OR_NX_201408_645_宁夏_自助终端票据明细打印增加数字键盘控制功能 -->
							<div class="btn_box tc">
								<p class="fs22w"><span class="yellow fs30"><s:property value="#request.successMessage"/></span></p>
          						<div class=" blank60"></div>								     						
							</div>
							<a  class="bt_280 ml300"  onmouseup="printReceipt()">稽核账单打印(请按确认键)</a>
				  	 	    <!-- modify end jWX216858 2014-09-09 OR_NX_201408_645_宁夏_自助终端票据明细打印增加数字键盘控制功能 -->
				  	 	</div>
				  	 </div>
			 	</div>		 	
			</div>		    
	</form>
</body>
</html>
