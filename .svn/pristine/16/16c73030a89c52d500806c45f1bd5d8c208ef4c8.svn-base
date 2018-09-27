<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<% 
String month = (String)request.getAttribute("month");
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
		<script type="text/javascript">
		var submitFlag = 0;
		
		function goback(curmenu) 
		{
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;				
				
				document.actform.target = "_self";
				document.actform.action="${sessionScope.basePath }cdrquery/selectFeeType.action";
				document.actform.submit();
			}
		}
		
		// 处理键盘事件
		document.onkeydown = keyDown;
		function keyDown(e)
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
				goback("<s:property value='curMenuId' />");
		   		return ;
			}
		}		
		
		// 查询对应月份数据
		function goMonth(monthStr) 
		{	
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openQryWaitLoading();
				
				document.actform.month.value = monthStr;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }cdrquery/qryDetailedRecords.action";
				document.actform.submit();
				
			}	
		}
		</script>
	</head>
	<body scroll="no" >
		<form name="actform" method="post">
			<input type="hidden" name="month" value="<s:property value='month' />">			
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />">
			<input type="hidden" name="feeType" value="<s:property value='feeType' />">
			
			<%@ include file="/titleinc.jsp"%>
			
				<div class="main" id="main">
					<%@ include file="/customer.jsp"%>
					
					<!--滚动条-->
        			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
        		    <%
					String detailErrorMsg = (String) PublicCache.getInstance().getCachedData("SH_DETAIL_STYLE");
					if (detailErrorMsg != null && "1".equals(detailErrorMsg))
					{
					%>
			        <div id="btns" class="fl" >
			        <span class="fs18 ml50">快速查看：</span>
				        <s:iterator value="months" id="currMonth" status="st">
							<s:if test="#currMonth==month">
								<a href="javascript:void(0)" class="bt222on fs16"><s:property value="#currMonth.substring(0,4)+'年'+#currMonth.substring(4,6)+'月'" /></a>
							</s:if>
							<s:else>
							    <a href="javascript:void(0);goMonth('<s:property value="currMonth"/>')" class="bt2 fs16" ><s:property value="#currMonth.substring(0,4)+'年'+#currMonth.substring(4,6)+'月'" /></a>
	           				</s:else>  
						</s:iterator>
					</div>
					<%
					}
					%>
        			<div class="box842W fl ml20 relative">
        				<div class="bg"></div>
						<div class="top"></div>
						<div class="con relative" >
							<div class="box747W fl">
								<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
									<p class="tit_info mb10" align="left"><span class="bg"></span><s:property value="cdrTypeName" /></p>
									<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
										
										<table class="tb_blue03 mb10" align="center" width="100%">
											<s:if test="billSummary != null && billSummary != ''">
												<tr>
													<s:generator separator="@_@" val="billSummary" var="summary">
														<s:iterator value="#summary" status="st">
															<th colspan="4">
																<s:if test="#st.first">
																	短信总条数：
																</s:if>
																<s:else>
																	彩信总条数：
																</s:else>
																<s:property/>
															</th>
														</s:iterator>
													</s:generator>
												</tr>
											</s:if>
											<tr class="tr_color">
												<th class="tr_color" width="11%">
													起始时间
												</th>
												<th class="tr_color" width="12%">
													通信地点
												</th>
												<th class="tr_color" width="14%">
													对方号码
												</th>
												<th class="tr_color" width="11%">
													通信方式
												</th>
												<th class="tr_color" width="11%">
													信息类型
												</th>
												<th class="tr_color" width="12%">
													业务名称
												</th>
												<th class="tr_color" width="19%">
													套餐
												</th>
												<th class="tr_color">
													通信费
												</th>
											</tr>
											<s:if test="resultRecords == null || resultRecords.size == 0">
												<tr>
													<td colspan="99" class="list_mess">
														<s:property value="resultMsg" />
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
																<s:iterator value="#singleRecord">
																	<td>
																		<s:property />
																	</td>
																</s:iterator>
															</tr>
														</s:iterator>															
													</s:else>													
												</s:iterator>
												<tr class="bold">
													<td class="leftBorder">
														合计
													</td>
													<td class="noBorder">
													</td>
													<td class="noBorder">
													</td>
													<td class="noBorder">
													</td>
													<td class="noBorder">
													</td>
													<td class="noBorder">
													</td>
													<td class="noBorder">
													</td>
													<td class="leftBorder">
														<s:property value="billTotal.totalFee" />
													</td>
												</tr>																												
											</s:else>											
										</table>								
									</p>
								</div>
							</div>
							<div class="box70W fr">
								<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
								<div class="boxPage" style="width:75px; height:350px; ">
									<div class="blank10px"></div>
									<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px" >0%</div>
								</div>
								<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
							</div>
							<div class="clear"></div>
						</div>
						<div class="btm"></div>
        			</div>
        			<script type="text/javascript">
        				myScroll = new virtualScroll("inn", "gunDom");
        			</script>
				</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
	<script type="text/javascript">
	    openSurveyDialog();
	</script>
	<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
</html>
