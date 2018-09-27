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
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	           
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}

	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	String subsName = (String) request.getAttribute("subsName");
	if (subsName == null)
	{
		subsName = "";
	}
	
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	
	// add begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
	// 收据打印开关
	String receiptPrintSwitch = CommonUtil.getParamValue(Constants.SH_PRINTRECEIPT_SWITCH);
    // add end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var payType = "<s:property value='payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//取卡时间30秒
	
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
			
			//返回/退出(R)
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}	
			//继续充值交费(1)
			if (key == 49)
			{
				continueCharge();
			}
			
			// modify begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
			// 收据打印开关开启
		    <%
            if ("1".equals(receiptPrintSwitch))
            {                       
            %>
                // 打印收据(2)
	            if (key == 50)
	            {
	                printReceipt_nx();
	            }
	            // 缴费完成(3\P)
	            if (key == 51 || key == 80 )
	            {
	                goHomePage();
	            }
            <%
            }
            else
            {
            %>
				//打印小票(2)
				if (key == 50)
				{
					printReceipt();
				}
				//打印发票(3\P)
				if (key == 51 || key == 80 )
				{
					<%
					if ("1".equals(canPrintInvoice))
					{						
					%>
						printInvoice();
					<%
					}
					%>
				}
				//缴费完成(4)
				if (key == 52)
				{
					goHomePage();
				} 
		    <%
            }
            %>   
            // modify end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求
            
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
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />退出充值打印功能");
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}		
			
		// 打印发票
		function printInvoice()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
					"<s:property value='servnumber' />选择打印发票");
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.forms[0].invoiceType.value = "1";
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
				document.forms[0].submit();	
			}						
		}
			
			// 读取读卡器取卡状态
			function takeOutBankCardStatus() 
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"监控用户是否已取卡");
								
				limitTime = limitTime - 1;
				
				if (limitTime <= 0)
				{
					//超时，清除定时任务，同时吞卡
					clearInterval(waitingToken);
					
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"用户在规定时间内未取卡，吞卡");					
					
					captureUserCard();
					
					return;
				}
				
				try 
				{
					//生产用
					var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
					
					if (ret == "0" || ret == 0) 
					{	
						//已经取走卡，清除定时任务
						clearInterval(waitingToken);
						
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"用户已取卡");									
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
				
				if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				{
					// 初始化
					var ret = InitReadUserCard();

					// 退卡
					ret = TakeOutUserCard();
					
					//吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
					if (ret == "0")
					{
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"吐卡成功");
						
						if ("1" == "<%=needCaptureCard %>")
						{
							startClock();
						}
					}
					else if (ret != "0")
					{
						//吐卡异常
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"吐卡异常");
					}
				}
					
			}
			
			// 打印小票
			var printReceiptFlag = 0;
			function printReceipt()
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "<s:property value='servnumber' />选择打印小票");
				
				if (printReceiptFlag == 0)
				{
					printReceiptFlag = 1;
					
					// 按钮按下，置不可用状态
					document.getElementById('dayinButton').className = 'hover';
					document.getElementById('dayinButton').onmousedown = "";
					document.getElementById('dayinButton').onmouseup = "";
					document.getElementById('dayinButton').onclick = "";
					
					var pServNumber = "<s:property value='servnumber' />";
					var pOperID = "<%=termInfo.getOperid() %>";  //打印地点
					var pDealNum = "<s:property value='dealNum' />";     //交易流水号
					var pDealTime = "<s:property value='dealTime' />";   //交易时间
					var tMoney = "<s:property value='tMoney' />元";     //交易金额
					var pSubsName = "<%=subsName %>";
					var pLatestBalance = "<s:property value='balance' />元";
					
					//打印凭证
					if ("<%=canPrintReceipt %>" == "1")
					{	
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"自动打印凭条");
						
						doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					
						<%
						if ("1".equals(canPrintInvoice))
						{
						%>
							document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。如果需要，请选择打印发票。";
						<%
						}
						else
						{
						%>
							document.getElementById("resultMsg").innerHTML = "小票打印成功，请取走。";
						<%
						}
						%>
					}
				}
			}
			
			// 打印收据
			function printReceipt_nx()
            {
                writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.RECEIPT_PRINT) %>", "<s:property value='servnumber' />选择打印收据");
                
                if (printReceiptFlag == 0)
                {
                    printReceiptFlag = 1;
                    
                    // 按钮按下，置不可用状态
                    document.getElementById('dayinButton').className = 'hover';
                    document.getElementById('dayinButton').onmousedown = "";
                    document.getElementById('dayinButton').onmouseup = "";
                    document.getElementById('dayinButton').onclick = "";
                    
                    var pDealTime = "<s:property value='chnDealTime' />";  // 交易时间
                    var pName = "<%=subsName %>"; // 客户名称
                    var pServNumber = "<s:property value='servnumber' />";
                    var tMoney = "<s:property value='tMoney' />元"; // 交易金额
                    var upperTMoney = "<s:property value="upperTMoney"/>"; // 大写金额
                    var pDealNum = "<s:property value='dealNum' />"; // 交易流水号
                    var pOperID = "<%=termInfo.getOperid() %>";  // 开票人
                    var pOrgName = "<%=termInfo.getOrgname() %>"; // 营业厅
                    
                    //打印凭证
                    if ("<%=canPrintReceipt %>" == "1")
                    {   
                        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.RECEIPT_PRINT) %>", 
                                "打印收据");
                        
                        printReceipt_new(pDealTime, pName, pServNumber, tMoney, upperTMoney, pDealNum, pOperID, pOrgName);
                    
                        document.getElementById("resultMsg").innerHTML = "收据打印成功，请取走。";
                    }
                }
            }
            
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			
			<input type="hidden" id="dealNum" name="dealNum" value='<s:property value="dealNum" />'>
			<input type="hidden" id="dealTime" name="dealTime" value='<s:property value="dealTime" />'>
			<input type="hidden" id="balance" name="balance" value='<s:property value="balance" />'>
			<input type="hidden" id="subsName" name="subsName" value="<%=subsName %>">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>充值交费流程：</h2>
      					<div class="blank10"></div>
      					<%
						if ((String)request.getAttribute("payType") != Constants.PAY_BYCARD)
						{						
						%>						
      					<a href="#">1. 输入手机号码</a> 
      					<a href="#">2. 选择支付方式</a> 
      					<a href="#">3. 投入纸币</a>
          				<a href="#" class="on">4. 完成</a>
          				<%
          				}
          				else
          				{
          				%>
          				<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择支付方式</a>
    					<a href="javascript:void(0)">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 免责声明</a>
    					<a href="javascript:void(0)">5. 插入银联卡</a> 
    					<a href="javascript:void(0)">6. 核对信息</a>
    					<a href="javascript:void(0)" class="on">7. 完成</a>
          				<%
          				}
          				%>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="recharge_result tc">
       							<%
								if (!Constants.PAY_BYCARD.equals((String)request.getAttribute("payType")) )
								{
								%>
									<p class="title yellow pt30">您的充值交费已成功！</p>
									
									<!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->
                                    <%
                                    if ("1".equals(receiptPrintSwitch))
                                    {
                                    %>
                                       <p id='resultMsg' class="desc pt20">如果需要，请选择打印收据，并保存好。</p>                                    <%
                                    }
                                    else
                                    {
                                    %>
                                        <p id='resultMsg' class="desc pt20">如果需要，请选择打印小票或发票，并保存好。</p>
                                    <%
                                    }
                                    %>
                                    <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->
								<%
								}
								else
								{
								%>
									<p class="title yellow pt30">您的充值交费已成功！请取走您的银联卡。</p>
									<!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->
									<%
                                    if ("1".equals(receiptPrintSwitch))
                                    {
                                    %>
                                       <p id='resultMsg' class="desc pt20">如果需要，请选择打印收据，并保存好。</p>                                    <%
                                    }
                                    else
                                    {
                                    %>
                                        <p id='resultMsg' class="desc pt20">如果需要，请选择打印小票或发票，并保存好。</p>
                                    <%
	                                }
	                                %>
	                                <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->
								<%
								}
								%>
       							
          						<!--modify begin cKF76106 2012/09/25 OR_NX_201209_258-->
          						<div class="btn_box3_echo clearfix">
          						    <!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->
          							<%
	       							if ("1".equals(receiptPrintSwitch))
	       							{
          							%>
          							   <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费 (请按1键)</a>
          							   <a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt_nx();return false;" style="margin-left:20px; ">打印收据 (请按2键)</a>
          							   <a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;">缴费完成 (请按3键)</a>
          							<%
                                    }
                                    else
                                    {
                                    %>
	          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费 (请按1键)</a>
	          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">打印小票 (请按2键)</a>
										<%
										if ("1".equals(canPrintInvoice))
										{						
										%>
										<a href="javascript:void(0);" onclick="printInvoice();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''" style="margin-top:20px; ">打印发票 (请按3键)</a>
										<br>
										<%
										}
										%>
										<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="_margin-top:0px; margin-left:20px; ">缴费完成 (请按4键)</a>
									<%
	                                }
	                                %>  
	                                <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 关于变更自助终端卷式通用机打发票模板的需求 -->      							
          						</div>
          						<!--modify end cKF76106 2012/09/25 OR_NX_201209_258-->
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>	
			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-06.wav" id="player06" align="center" autostart=true border="0" style="height:0px;width:0px;">	
		</form>
	</body>
	<script type="text/javascript">
	//继续充值缴费
	function continueCharge()
	{	
		if (submitFlag == 0)
		{
			submitFlag = 1;
										
			// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			if (document.getElementById("backWaitingFlag").value == "1")
			{
				openRecWaitLoading_NX("recWaitLoading");
			}
			// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			
			document.payMoneyForm.target = "_self";
			document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
			document.payMoneyForm.submit();
		}		
	}
	
	// 关闭读卡器,退卡
	doFinish();
	</script>
</html>
