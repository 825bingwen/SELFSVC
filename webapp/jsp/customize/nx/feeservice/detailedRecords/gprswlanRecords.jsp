<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	
	//add begin cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	String entryNumber = customerSimp.getServNumber();
	entryNumber = entryNumber.substring(0,3) + "****" + entryNumber.substring(entryNumber.length()-4, entryNumber.length());
	//add end cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>		
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		function goback(curmenu) 
		{
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;				
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action="${sessionScope.basePath }cdrquery/selectType.action";
				document.actform.submit();
			}
		}
		
		// 处理键盘事件
		document.onkeydown = keyDown;
		function keyDown(e)
		{
			// 返回首页重新计时
			resetGoHome();
			
			//接收键盘码
			var key = GetKeyCode(e);
		    
		    //8:backspace 32:空格 B:66
		    if (key == 8 || key == 32 || key == 66)
		    {
		    	return false;
		    }
		    
		    //82:R 退出
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return;
			}
			// 0键
			else if (key == 48)
			{
				//topGo('qryBill', 'feeservice/feeServiceFunc.action');
				goback('<s:property value='curMenuId' />');
			}
			// 1键
			else if (key == 49 || key == 80)
			{
				printInfo();
			}
			// 上翻
			else if (key == 85)
			{
				myScroll.moveUp(300);
				return;
			}
			// 下翻
			else if (key == 68)
			{
				myScroll.moveDown(300);
				return;
			}
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
		
		function printInfo()
		{
			<%if("1".equals((String) PublicCache.getInstance().getCachedData("SH_PRINT_MONTHLIMT")))
			{
			%>
				var currentMonth = getCurrentTime1().substring(0,6);
				if('<s:property value='month' />' == currentMonth)
				{
					alertError("当月详单只能查询不能打印，敬请谅解！");
					return;
				}
			<%
			}
			%>
			<%--modify begin g00140516 2013/02/02 R003C13L01n01 每年的12个月，每月详单均只能打印一次 --%> 
			// 使用ajax方式判断用户是否可以打印详单
			var url = "${sessionScope.basePath }cdrquery/checkPrintTimes.action";
					
			var params = "month=<s:property value='month' />&cdrType=<s:property value='cdrType' />&number=" + Math.random();
			
			new net.ContentLoader(url, netload = function () {
				var resMsg = this.req.responseText;
				
				if (resMsg == "")
				{
					alertJdNX("正在打印，请稍候...");
					
					var printTime = getCurrentTime();
  					doPrint_NXNew('1', "<s:property value='cdrType' />", '<%=entryNumber%>', 
  							"<s:property value='cycle' />", '<s:property value="startDate" />', '<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', 
  							printTime, '${sessionScope.basePath}', '<s:property value="curMenuId" />', '上网详单', 
  							'<s:property value="chQueryDate" escape="false"/>', '<s:property value="chStartDate" escape="false"/>',
  							'<s:property value="chEndDate" escape="false"/>', '<s:property value="custName" escape="false"/>');
				}
				else
				{
					alertError(resMsg);
				}
			}, null, "POST", params, null);
			<%--modify end g00140516 2013/02/02 R003C13L01n01 每年的12个月，每月详单均只能打印一次 --%> 
		}
		
		//记录清单打印次数
		function InsertPrintInfo()
		{
    		var url = "${sessionScope.basePath }cdrquery/updatePrintTimes.action";
			
			var loader = new net.ContentLoader(url, null, null, "POST", "month=<s:property value='month' />&cdrType=<s:property value='cdrType' />&number=" + Math.random(), null);    		
		}
		
		</script>		
	</head>
	<body scroll="no" >
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />">
			<input type="hidden" name="month" value="<s:property value='month' />">
			
			<%@ include file="/titleinc.jsp"%>
			
				<div class="main" id="main">
					<%@ include file="/customer.jsp"%>
					
					<!--滚动条-->
        			<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回上级&nbsp;(按0键)</a>
        		
        			<div class="box842W fl ml20 relative">
        				<div class="bg"></div>
						<div class="top"></div>
						<div class="con relative" >
							<div class="box747W fl">
								<div class="div747w444h">
									<p class="tit_info mb10" align="left">
										<span class="bg"></span>
										上网详单
									</p>
									<div class="ptop180 tc p747w411h" id="inn">
										
										<table class="tb_blue03 mb20 bold" align="center" width="100%">
											<tr>
												<th colspan="4" class="tl">
													<span class="cdr_logo" onclick='javascript:f_setTimeout(0);'></span>
													<span class="pl130">中国移动通信 客户详单</span>
												</th>
											</tr>
											<tr>
												<td class="tl">客户名称</td>
												<td class="tl"><s:property value='custName' /></td>
												<td class="tl">手机号码</td>
												<td class="tl"><%=entryNumber%></td>
											</tr>											
											<tr>
												<td class="tl">查询时段</td>
												<td class="tl"><s:property value="chStartDate" escape="false"/>-<s:property value="chEndDate" escape="false"/></td>
												<td class="tl">查询日期</td>
												<td class="tl"><br /><br /></td>
											</tr>
										</table>
										<s:if test="null != resultRecords && 0 < resultRecords.size">
											<table class="tb_blue03" align="center" width="100%">
												<tr>
													<th>
														GPRS话单条数<s:property value="billTotal.gprssum" />
													</th>
													<th>
														收费流量<s:property value="billTotal.chargeflux" />
													</th>
													<th>
														免费流量<s:property value="billTotal.freechargeflux" />
													</th>
													<th>
														总流量<s:property value="billTotal.sumflux" />
													</th>
												</tr>
												<tr>
													<td>
														其中：CMWAP条数<s:property value="billTotal.cmwapsum" />
													</td>
													<td>
														其中：CMWAP收费流量<s:property value="billTotal.cmwapflux" />
													</td>
													<td>
														其中：CMWAP免费流量<s:property value="billTotal.cmwapfreeflux" />
													</td>
													<td>
														其中：CMWAP总流量<s:property value="billTotal.cmwapsumflux" />
													</td>
												</tr>
												<tr>
													<td>
														其中：CMNET条数<s:property value="billTotal.cmnetsum" />
													</td>
													<td>
														其中：CMNET收费流量<s:property value="billTotal.cmnetflux" />
													</td>
													<td>
														其中：CMNET免费流量<s:property value="billTotal.cmnetfreeflux" />
													</td>
													<td>
														其中：CMNET总流量<s:property value="billTotal.cmnetsumflux" />
													</td>
												</tr>
												<tr>
													<th>
														WLAN话单条数<s:property value="billTotal.wlansum" />
													</th>
													<th>
														WLAN总流量<s:property value="billTotal.wlansumflux" />
													</th>
													<th>
														WLAN总时长<s:property value="billTotal.wlansumtime" />
													</th>
													<th>
														WLAN总费用<s:property value="billTotal.wlansumfee" />
													</th>
												</tr>
												<tr>
													<td>
														其中：公众WLAN条数<s:property value="billTotal.pubwlansum" />
													</td>
													<td>
														其中：公众WLAN流量<s:property value="billTotal.pubwlanflux" />
													</td>
													<td>
														其中：公众WLAN时长<s:property value="billTotal.pubwlantime" />
													</td>
													<td>
														其中：公众WLAN费用<s:property value="billTotal.pubwlanfee" />
													</td>
												</tr>
												<tr>
													<td>
														其中：校园WLAN条数<s:property value="billTotal.campuswlansum" />
													</td>
													<td>
														其中：校园WLAN流量<s:property value="billTotal.campuswlanflux" />
													</td>
													<td>
														其中：校园WLAN时长<s:property value="billTotal.campuswlantime" />
													</td>
													<td>
														其中：校园WLAN费用<s:property value="billTotal.campuswlanfee" />
													</td>
												</tr>
											</table>
										</s:if>
										<table class="tb_blue03 mb10" align="center" width="100%">
											
											<tr class="tr_color">
												<th width="12%">起始时间(hh:mm:ss)</th>
												<th width="12%">通信地点</th>
												<th width="12%">上网方式</th>
												<th width="12%">时长</th>
												<th width="12%">流量</th>
												<th width="12%">套餐优惠</th>
												<th width="12%">2G/3G标识</th>
												<th width="12%">通信费(元)</th>
											</tr>
											
											<s:if test="resultRecords == null || resultRecords.size == 0">
												<tr>
													<td colspan="99" class="list_mess">
														无对应的详单记录
													</td>
												</tr>									
											</s:if>
											<s:else>											
												<s:iterator value="resultRecords" id="record" status="st">													
													<s:if test="#st.odd">
														<tr>
															<td colspan="99" class="list_mess tl date">
																<s:property value="record" />
															</td>
														</tr>
													</s:if>
													<s:else>
														<s:iterator value="#record" id="singleRecord">
															<tr>
																<s:generator separator="@_@" val="#singleRecord" var="fields">
																	<s:iterator value="#fields" status="st">
																		<s:if test="#st.index == 2">
																			<td class="tl">	
																		</s:if>
																		<s:else>
																			<td>	
																		</s:else>		
																			<s:property />
																		</td>
																	</s:iterator>
																</s:generator>
															</tr>
														</s:iterator>																														
													</s:else>													
												</s:iterator>																																							
											</s:else>											
										</table>
										<p class="blank10"></p>									
									</div>
								</div>
							</div>
							<div class="box70W fr">
								<input type="button" class="btnUp" onclick="myScroll.moveUp(300);resetGoHome();" />
								<div class="div75w350h" onclick="resetGoHome();">
									<div class="blank10px"></div>
									<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
								</div>
								<input type="button" class="btnDown" onclick="myScroll.moveDown(300);resetGoHome();"/>
							</div>
							<div class="clear"></div>
						</div>
						<div class="btm"></div>
        			</div>
        			<script type="text/javascript">
        				myScroll = new virtualScroll("inn", "gunDom");
        			</script>
        			<s:if test="resultRecords != null && resultRecords.size != 0">
	                    <div class="box120W fl ml10" id="print">
							<p class="blank10"></p>
							<a class="btn_120_82_1" href="javascript:void(0)" onclick="printInfo()" >打印<br/>(按1键)</a>
						</div>
	             	</s:if>
				</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
		<iframe id="processFrm" frameborder="0" scrolling="no" style="display: none" src=""></iframe>
	</body>
</html>
