<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintInvoice = termSpecial.substring(1, 2);
          
    //BOSS 交易流水
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
    //BOSS 交易时间
    // modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	String pDealTime = (String) request.getAttribute("pDealTime");
	// modify end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	// 是否需要随机密码
	String needRandomPwd = (String)request.getAttribute("needRandomPwd");
	
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
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
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var payType = "<s:property value='payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//取卡时间30秒
	
		var invoiceType = "";
		
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
			}
			
			// 继续充值交费
			if (key == 49)
			{
				continueCharge();
			}
			
			// 打印发票
			if (key == 50)
			{
				printInvoice('Last');
			}			
		}
		
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
					document.forms[0].submit();
				}
			}		
			
			//打印当前发票，有多张发票打印最后一张
			function printInvoice(invoicetype)
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />选择打印发票");
				
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					invoiceType = invoicetype;
					
					//打印发票时需要随机密码
					if ("1" == "<%=needRandomPwd %>")
					{
						openWindow('popup_confirm');
					}
					else
					{
						document.getElementById("invoiceType").value = invoiceType;
						document.forms[0].target = "_self";
						document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
						document.forms[0].submit();
					}
				}				
			}
			
			function sendRandomPwd()
			{
				document.getElementById("invoiceType").value = invoiceType;
						
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action="${sessionScope.basePath }charge/validateByRandomPwd.action";
				document.payMoneyForm.submit();
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
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />交费成功");
				
				if (payType == "<%=Constants.PAY_BYCARD %>")
				{
					//生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
					var ret = getCardPosition();
					
					//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
					if (ret == "2")
					{
						startClock();
					}
					else if (ret != "0")
					{
						//吐卡异常
					}
				}
				
				<%-- modify begin cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371--%>
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				var pDealNum = "<%=pDealNum%>";     //交易流水号
				var pDealTime = "<%=pDealTime%>";   //交易时间
				var tMoney = "<s:property value='tMoney' />元";     //交易金额
				var pDealStatus = "交费成功"; //交易状态
				var pTerminalSeq = "<s:property value='terminalSeq' />";//终端流水
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"自动打印凭条");
				
				//modify begin g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
				doPrintPayProof_SD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
							pDealStatus, pTerminalSeq, "", "0", "<s:property value='custName' escape='false' />");
				//modify end g00140516 2012/04/24 R003C12L03n01 凭条打印时打印客户名称
				
				<%-- modify end cKF48754 2011/11/25 R003C11L11n01 OR_SD_201111_371--%>	
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择支付方式</a> 
      					<a href="#">3. 选择金额</a> 
      					<a href="#">4. 投入纸币</a>
          				<a href="#" class="on">5. 完成</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">您的充值交费已成功！</p>
          						<p class="desc pt20">请保存好您的的交易凭条。</p>
          						<div class="btn_box2_echo clearfix">         						
          							<a href="javascript:void(0);" class="ml5" onmousedown="this.className='hover ml5'" onmouseup="this.className='ml5'" onclick="continueCharge();return false;" >继续充值交费 (请按1键)</a>
          							
          							<%
									if ("1".equals(canPrintInvoice))
									{						
									%>
									<%--modify begin g00140516 2012/05/03 R003C12L04n01 打印全部充值交费发票 --%>
									<a href="javascript:void(0);" class="ml20" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover ml20'" onmouseup="this.className='ml20'" >打印发票 (请按2键)</a>
									<%--modify end g00140516 2012/05/03 R003C12L04n01 打印全部充值交费发票 --%>
									<%
									}
									%>          							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
  				
  				<div class="popup_confirm" id="popup_confirm">
                  	<div class="bg"></div>
                  	<div class="tips_title">提示：</div>
                  	<div class="tips_body">
	    				<p>您将打印发票，为了确保您信息的安全，系统将下发短信校验码到您的手机，请将收到的短信校验码输入下个页面的校验码框内。</p>
	    				<div class="blank10"></div>
	    				<p class="mt30">请确认是否继续？</p>
  					</div>
                  	<div class="btn_box tc mt20">
                  		<span class=" mr10 inline_block ">
                  			<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="sendRandomPwd();return false;">确认</a>
                  		</span>
                  		<span class=" inline_block ">
                  			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();submitFlag=0;">取消</a>
                  		</span>
                  	</div>
                </div>
				<script type="text/javascript">
				openWindow = function(id)
				{
					wiWindow = new OpenWindow("popup_confirm", 708, 392);//打开弹出窗口例子
				}				
				</script>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
	//继续充值缴费
	function continueCharge()
	{	
		document.payMoneyForm.target = "_self";
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		document.payMoneyForm.submit();
	}
	</script>
</html>
