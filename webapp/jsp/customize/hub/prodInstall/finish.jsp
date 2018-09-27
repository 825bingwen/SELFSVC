<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	// 控件支持标志
	String termSpecial = termInfo.getTermspecial();
	// 控件加载字符串
	String plugin = termInfo.getPlugin();
	
	String pOrgName = termInfo.getOrgname();
	String termId = termInfo.getTermid();
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	//发票打印控制
	String PrintInvoice = PublicCache.getInstance().getCachedData("SH_PRINTINVOICES") == null ? "0" : (String)PublicCache.getInstance().getCachedData("SH_PRINTINVOICES");
	
	
	// 是否需要随机密码
	String needRandomPwd = (String)request.getAttribute("needRandomPwd");
	
	//开户成功后可取卡标志
	String cardFlag = (String)request.getAttribute("cardFlag");
	//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 begin
    String dealAddedTax = (String) PublicCache.getInstance().getCachedData("DealAddedTax");
  
	//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案  end
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}jsp/customize/hub/js/SelfInstallPrinter_Hub.js"></script>
		<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
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
					document.forms[0].action = "${sessionScope.basePath }hubprodinstall/ruleConfirm.action";
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
						document.forms[0].action = "${sessionScope.basePath }hubprodinstall/printInvoiceWithoutPwd.action";
						document.forms[0].submit();
					}
				}				
			}
			
			function sendRandomPwd()
			{
				document.getElementById("invoiceType").value = invoiceType;
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action="${sessionScope.basePath }hubprodinstall/validateByRandomPwd.action";
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
			
			//出现异常
			function setException(errorMsg) 
			{			
				document.getElementById("errormessage").value = errorMsg;
				
				// 异常处理，只要出现了异常就要记录日志  
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }hubprodinstall/goCashError.action";
				document.actform.submit();
			}		
			
			function doFinish()
			{
				if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				{
					// 退卡
					var ret = TakeOutUserCard();

					// 吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
					if (ret == "0" && "1" == "<%=needCaptureCard %>")
					{
						startClock();
					}
					else if (ret != "0")
					{
						//吐卡异常
					}
				}
				//打印凭证
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
					   	this.payStatus = '1';  
					   	this.pDealStatus = "自助终端开户受理成功"; 
					   	this.pTerminalSeq = "<s:property value='terminalSeq' />";
					   	this.pOrgName = "<%=pOrgName%>";  
					   	this.pPrintDate = getDateTime();  
				   	}
				   	installPrint(piData);
				}
				
				//取卡
				if('YES' == '<%=cardFlag %>')
				{
					var ret = window.parent.document.getElementById("simcardpluginid").MoveOutCard();
					if(ret != 0)
					{
						setException("写卡失败，请凭小票联系当地营业厅！");
					}
					window.parent.document.getElementById("simcardpluginid").CloseCard();
				}
			}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="receptionFee" name="receptionFee" value='<s:property value="receptionFee" />'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="formnum" name="formnum" value="<s:property value='formnum' />">
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>开户入网流程：</h2>
      					<div class="blank10"></div>
      					<a title="入网协议确认" href="#">1. 入网协议确认</a>
      					<a title="身份证信息认证" href="#">2. 身份证信息记取</a>
      					<a title="产品选择" href="#">3. 产品选择</a>  
      					<a title="号码选择" href="#">4. 号码选择</a> 
      					<a title="费用确认" href="#">5. 费用确认</a>
      					<a title="开户缴费" href="#">6. 开户缴费</a>
      					<a title="取卡打印发票" href="#" class="on">7. 取卡打印发票</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='telnum' /></span></p>
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">您的入网开户已成功！</p>
          						<p class="desc pt20">请保存好您的的交易凭条。</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>          						
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueInstall();return false;">继续选号开户</a>
          							<%
									if ("1".equals(canPrintInvoice) && "1".equals(PrintInvoice))
									{	//add by yKF73963 20140221 for OR_HUB_201401_662  [营改增]营改增自助终端部分改造方案 begin
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
	<%=plugin%>
	<script type="text/javascript">
	//继续选号入网
	function continueInstall()
	{	
		document.payMoneyForm.target = "_self";
		document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
		//alert(document.payMoneyForm.action);
		document.payMoneyForm.submit();
	}
	</script>
		<script type="text/javascript">
removeAclick();
</script>
</html>
