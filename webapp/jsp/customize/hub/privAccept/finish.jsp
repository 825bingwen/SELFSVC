<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
            
    String successmessage = (String) request.getAttribute("successmessage");

    String payType = (String) request.getAttribute("payType");
    
    String tMoney = (String) request.getAttribute("tMoney");
    if(null != tMoney)
    {
        if (Constants.PAY_BYCARD.equals(payType))
        {
    	    tMoney = CommonUtil.fenToYuan(tMoney);
        }
    }
    
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
	
	String recStatus = (String) request.getAttribute("errormessage");
	if (recStatus == null)
	{
		recStatus = "";
	}
	
	String errFlag = (String)request.getAttribute("errFlag");
	if(errFlag == null){
		errFlag =  "";
	}
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	//String needRandomPwd = (String) PublicCache.getInstance().getCachedData("SH_PRTINVOICE_RANDOMPWD");
	String needRandomPwd = "0";
	//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 begin
    String dealAddedTax = (String) PublicCache.getInstance().getCachedData("DealAddedTax");
	//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案  end

	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	String message = (String) request.getAttribute("errormessage");
	String errorMessage = CommonUtil.errorMessage(message);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求

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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter_hb.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/SelfPrivPrinter_Hub.js"></script>
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
		}
		
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }privAccept/qryPriv.action";
					document.forms[0].submit();
				}
			}		
			
			//打印当前发票，有多张发票打印最后一张
			function printInvoice(invoicetype)
			{
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
				if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				{
					// 退卡
					var ret = TakeOutUserCard();

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
				
				var prtData = new function () 
			    {
					//用户号码
			        this.servnumber = "<s:property value='servnumber' />";
			        //终端信息
			        this.termID = '<%=termInfo.getTermid()%>';
			        this.username = '';
			        this.groupname = '<s:property value="privName" escape="false" />';
			        //活动应收
			        this.prepayfee = "<s:property value='privMoney' />";
			        //优惠受理时间
			        this.recDate = "<s:property value='priRecDate' />";
			        //投币金额
			        this.tMoney = '<%=tMoney == null ? "" : tMoney%>';
			        //缴费金额
			        this.tmpMoney = '<s:property value='tmpMoney' />';
			        //交易状态
			        this.payStatus = '<s:property value='payStatus' />';
			        //缴费流水
			        this.dealNum = '<%=pDealNum == null ? "" : pDealNum%>';
			        this.transResult = "<%=successmessage == null ? "受理成功" : successmessage%>";
			        //打印地点
			        this.pOrgName = '<%=pOrgName%>';
			        //缴费时间
			        this.dealTime = '<%=pDealTime == null ? "" : pDealTime%>';
			        //打印时间
			        this.pPrintDate = getDateTime();
			    }
				
				//var pServNumber = "<s:property value='servnumber' />";
				//var pOrgName = "<%=pOrgName%>";  //打印地点
				//var pPrintDate = getDateTime();  //打印日期
				//var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				//var priAmount = "<s:property value='privMoney' />元";   //活动金额
				//var privToMoney = "<s:property value='priBackMoney' />元";    //投币金额
				//var pRecDate = "<s:property value='priRecDate' />";       //活动受理时间
				//var pBackAmount =  "";	//退还金额
				//var pDealNum = "<%=pDealNum%>";     //交易流水号
				//var pDealTime = "<%=pDealTime%>";   //交易时间
				//var tMoney = "<s:property value='priBackMoney' />元";     //交易金额
				//var pDealStatus = "<%=recStatus%>"; //交易状态
				//var pTerminalSeq = "<s:property value='terminalSeq' />";//终端流水
				//var printFlag = "<%=errFlag%>";  //errFlag 为0时没有缴费金额
				//打印凭证
				if ("<%=canPrintReceipt %>" == "1")
				{	
								
					//alert(pServNumber + "	" + pOrgName + "	" + pPrintDate + "	" + pTerminalInfo + "	"  + pDealNum + "	" + pDealTime + "	" + tMoney + "	" + pDealStatus + "	" + pTerminalSeq);

					//doPrintPrivPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, priAmount, privToMoney,pRecDate, pBackAmount, pDealNum, pDealTime, tMoney, pDealStatus, pTerminalSeq,printFlag);
					recNotePrint(prtData);
					
					if (payType == "<%=Constants.PAY_BYCARD %>")
					{
						var printcontext = '<%=(String)request.getAttribute("printcontext") %>';
						//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
					}
				}
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="dealNum" name="dealNum" value="<%=pDealNum %>">
			<!-- Chagne by LiFeng 增加优惠名称 20111121 begin -->
			<input type="hidden" id="privId" name="privId" value='<s:property value="privId" />'/>
			<input type="hidden" id="privName" name="privName" value='<s:property value="privName" />'/>
			<!-- Chagne by LiFeng 增加优惠名称 20111121 End -->	
					<!-- Chagne by LiFeng 增加优惠类型 20111121 begin -->
		<input type="hidden" id="favorabletype" name="favorabletype" value="<s:property value="favorabletype" />"/>
		<!-- Chagne by LiFeng 增加优惠类型 20111121 end -->
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>优惠交费流程：</h2>
      					<div class="blank10"></div>
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择优惠</a> 
      					<a href="#">3. 选择支付方式</a> 
      					<a href="#">4. 投入纸币</a>
          				<a href="#" class="on">5. 完成</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><%=tMoney %>元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">业务办理成功！</p>
          						<p class="desc pt20">
          						
									<%--modify begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>	
									<%=errorMessage %>      							
									<%--modify end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求 --%>
								
								</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>          						
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续受理优惠</a>
          							<%
									if ("0".equals(canPrintInvoice)&&(!"0".equals(errFlag)))
									{	
									  //add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 begin
									    if(!"1".equals(dealAddedTax)){
									   //add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 end
									%>
									<a href="javascript:void(0);" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">打印当前发票</a>
									<%
									//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 begin
										}
										  //add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 end
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
