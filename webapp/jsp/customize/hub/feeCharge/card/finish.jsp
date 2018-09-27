<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<% 
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	
	// add begin hWX5316476 2013-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
	// 是否启用增值税开关（1：开启  0：关闭  默认关闭)
	String dealAddedTax = (String) PublicCache.getInstance().getCachedData("SH_DEALADDEDTAX");
	// add end hWX5316476 2013-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
	
	//modify  by  lWX162765 for R20130117009  begin
	NserCustomerSimp ncs = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	String uBrand = ncs.getBrandName();
	//modify by lWX162765  for R20130117009  end
            
    //BOSS 交易流水
    String pDealNum = (String) request.getAttribute("dealNum");
    if (pDealNum == null)
    {
		pDealNum = "";
	}
    
    //BOSS 交易时间
	String pDealTime = (String) request.getAttribute("dealTime");
	if (pDealTime == null)
	{
		pDealTime = "";
	}
	
	//吞卡标记 0为不吞卡，1为吞卡
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	// 是否需要随机密码
	String needRandomPwd = (String)request.getAttribute("needRandomPwd");
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
					document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
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
				
				var pServNumber = "<s:property value='servnumber' />";
				var pOrgName = "<%=pOrgName%>";  //打印地点
				var pPrintDate = getDateTime();  //打印日期
				var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
				var pDealNum = "<%=pDealNum%>";     //交易流水号
				var pDealTime = "<%=pDealTime%>";   //交易时间
				//modify begin by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求）
				//tMoney单位为分，yingjiaoFee单位为元
			//	var tMoney = "<s:property value='tMoney' />元";     //交易金额
				var tMoney = "<s:property value='yingjiaoFee' />元";     //交易金额
				//modify end by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求）
				var pDealStatus = "缴费成功"; //交易状态
				var pTerminalSeq = "<s:property value='terminalSeq' />";//终端流水
				var brand = "<%=uBrand%>"; //品牌
				
				//赠送金额 元
				//add by sWX219697 2015-1-4 11:55:20 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动
				var presentFee = "<s:property value = 'presentFee'/>";
				var isNot4GCard = "<s:property value = 'isNot4GCard'/>";
				var availIntegral = "<s:property value = 'availIntegral'/>";
				//打印凭证
				if ("<%=canPrintReceipt %>" == "1")
				{	
					// 缴费小票
					doPrintPayProofhub(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,
							pDealStatus, pTerminalSeq,brand,"", "0", presentFee);
					// 打印银联小票
					if (payType == "<%=Constants.PAY_BYCARD %>")
					{
						var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
						if (printcontext != "")
						{
							//doPrintUnionBill_hb(printcontext,pTerminalSeq,pOrgName,pPrintDate);
						}
					}
				}
			}
		</script>
	</head>
	<!--  modifiy begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
        <body onload="window.focus();doFinish();openSurveyDialog();" scroll="no">
	
	<!--  modifiy end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
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
      						<!--modify begin by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求） -->
      						<!-- <p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>  -->
      						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='yingjiaoFee' />元</span> </p> 
      						<!--modify end by wWX191797 at 20140403 for [OR_HUB_201401_880] 自助终端发票打印（bug转需求） -->
      						
      						<%--赠送金额 add begin sWX219697 2015-1-4 10:11:57 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动--%>
    						<s:if test="presentFee != '' && presentFee != 0">
    						<p class="fs22 fwb pl40 lh30">赠送金额：<span class="yellow"><s:property value='presentFee' />元</span></p>
      						</s:if>
      						<%--赠送金额 add end sWX219697 2015-1-4 10:11:57 OR_HUB_201412_399_HUB_自助终端开展交话费小额赠送营销活动--%>      						<div class="blank20"></div>
							<%--是否4G卡 add begin liutao00194861 2016-8-16 10:11:57 OR_HUB_201603_1191	【BOSS常规需求】自助终端显示内容优化需求（张德伟）--%>
							<s:if test="availIntegral != ''">
								<p class="fs22 fwb pl40 lh30">剩余积分：<span class="yellow"><s:property value='availIntegral' /></span></p>
							</s:if>
							<s:if test="isNot4GCard != 1">
								<p class="fs22 fwb pl40 lh30">您现在用的卡不是4G卡请尽快到营业厅更换</p>
							</s:if>
							<%--是否4G卡 add begin liutao00194861 2016-8-16 10:11:57 OR_HUB_201603_1191	【BOSS常规需求】自助终端显示内容优化需求（张德伟）--%>

        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">您的充值交费已成功！</p>


          						<p class="desc pt20">请保存好您的的交易凭条。</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>
          						  <input type="hidden" id="actId" vlaue="%{actId}" name="actId"/>
          						<s:if test="actId!=null">
          						<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="mianFeiChouJiang('<s:property value="actId"/>');return false;">免费抽奖</a>
          						</s:if>
          					      <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费</a>
          							<%
									if ("1".equals(canPrintInvoice))
									{		
										if ("0".equals(dealAddedTax))
										{				
									%>
									<a href="javascript:void(0);" onclick="printInvoice('Last');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">打印当前发票</a>
									<%
										}
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
			  <!--免费抽奖-->
                <div class="openwin_tip" id="colBillQueryWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class="blank30"></div><div class=" blank60"></div>
                   
                    <div class="blank10n"></div>
                    <p class="fs30 yellow pl20 lh2" id="colBillQuery" style="text-indent:60px;padding-right:20px;"></p>
                  
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    
                    </div>
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
		//免费抽奖
		function mianFeiChouJiang(actId){

		
		var resuInfo="免费抽奖出现异常！";
		url= "${sessionScope.basePath }charge/mianFeiChouJiang.action?curMenuId=<s:property value='curMenuId'/>&actId="+actId;
					var loader1 = new net.ContentLoader(url, netload = function () 
										{
											var resXml = this.req.responseXML;
											
											if (resXml)
											{
													var root = resXml.documentElement;
													
													
														
														document.getElementById("colBillQuery").innerHTML=root.text;
														wiWindow = new OpenWindow("colBillQueryWin1",708,392);
														return;
													
												    
												   				
											}
										    else
											{	
												
													document.getElementById("colBillQuery").innerHTML=resuInfo;
												    wiWindow = new OpenWindow("colBillQueryWin1",708,392);
												return;
											}								
										}, null, "POST", "", null);
	
}	
	</script>
</html>
