<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");
if(null == errorMessage || "".equals(errorMessage.trim()))
{
	errorMessage = "操作失败，请稍后再试。";
}
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	String termSpecial = termInfo.getTermspecial();
	String canPrintReceipt = termSpecial.substring(0, 1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_hb.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/SelfInstallPrinter_Hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js"></script>
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
		goback();
		return;
	}
}

function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		document.actform.submit();
	}
}

function doFinish()
{
	//如果用户有投币，才打印失败凭条
   var money = parseInt("<s:property value='tMoney' />");
   
   if (isNaN(money) || money <= 0)
   {
   		return;
   }
   
   //打印缴费失败的凭证
   if ("<%=canPrintReceipt %>" == "1")
   {
	   	var piData = new function()
	   	{
	   		this.servnumber = "<s:property value='telnum' />";
	   		this.mainprodname = "<s:property value='mainprodname' escape='false'/>";
	   		this.termId = "<%=termId%>";
		   	this.receptionFee = "<s:property value='receptionFee' />";
		   	this.tMoney = "<s:property value='tMoney' />";
		   	this.dealNum = "<s:property value='formnum' />";     
		   	this.pDealTime = getDateTime();  
		   	this.payStatus = '0';  
		   	this.pDealStatus = "自助终端开户受理失败"; 
		   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
		   	this.pOrgName = "<%=pOrgName%>";  
		   	this.pPrintDate = getDateTime();  
	   	}
	   
	   	installPrint(piData);
   }				
}
</script>
</head>
	<body scroll="no" onload="doFinish()">
		<form name="actform" method="post">
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>在线开户</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
    					<a href="javascript:void(0)">3. 产品选择</a> 
    					<a href="javascript:void(0)">4. 号码选择</a>
    					<a href="javascript:void(0)">5. 设置服务密码</a>
    					<a href="javascript:void(0)">6. 费用确认</a>
    					<a href="javascript:void(0)">7. 开户缴费</a>
    					<a href="javascript:void(0)">8. 取卡打印发票</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="casherror">
  							<%
  								out.print(errorMessage);
  							%>
        					</div>
      					</div>
					 </div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>

</html>
