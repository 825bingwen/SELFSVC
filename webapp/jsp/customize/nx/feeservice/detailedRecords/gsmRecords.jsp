<%--
 @User: ��Ⱥ/g00140516
 @De: 2012/02/09
 @comment: NG3.5���굥����֮�굥��ѯ
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	
	//add begin cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	String entryNumber = customerSimp.getServNumber();
	entryNumber = entryNumber.substring(0,3) + "****" + entryNumber.substring(entryNumber.length()-4, entryNumber.length());
	//add end cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
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
			//����Ѿ�ѡ�����굥���ͻ��ߵ���˷��أ�����ִ���κβ���
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
		
		// ���������¼�
		document.onkeydown = keyDown;
		function keyDown(e)
		{
			//���ռ�����
			var key = GetKeyCode(e);
		    
		    // ������ҳ���¼�ʱ
			resetGoHome();
			
		    //8:backspace 32:�ո� B:66 M:77
		    if (key == 8 || key == 32 || key == 66)
		    {
		    	return false;
		    }
		    
		    //82:R �˳�
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return ;
			}
			// 0��
			else if (key == 48)
			{
				//topGo('qryBill', 'feeservice/feeServiceFunc.action');
				goback('<s:property value='curMenuId' />');
			}
			// 1��
			else if (key == 49 || key == 80)
			{
				printInfo();
			}
			// �Ϸ�
			else if (key == 85)
			{
				myScroll.moveUp(300);
				return;
			}
			// �·�
			else if (key == 68)
			{
				myScroll.moveDown(300);
				return;
			}
			// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
		}
		
		function printInfo()
		{
			<%if("1".equals((String) PublicCache.getInstance().getCachedData("SH_PRINT_MONTHLIMT")))
			{
			%>
				var currentMonth = getCurrentTime1().substring(0,6);
				if('<s:property value='month' />' == currentMonth)
				{
					alertError("�����굥ֻ�ܲ�ѯ���ܴ�ӡ�������½⣡");
					return;
				}
			<%
			}
			%>
			<%--modify begin g00140516 2013/02/02 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ�� --%> 
			// ʹ��ajax��ʽ�ж��û��Ƿ���Դ�ӡ�굥
			var url = "${sessionScope.basePath }cdrquery/checkPrintTimes.action";
					
			var params = "month=<s:property value='month' />&cdrType=<s:property value='cdrType' />&number=" + Math.random();
			
			new net.ContentLoader(url, netload = function () {
				var resMsg = this.req.responseText;
				
				if (resMsg == "")
				{
					alertJdNX("���ڴ�ӡ�����Ժ�...");
					
					var printTime = getCurrentTime();
  					doPrint_NXNew('1', "<s:property value='cdrType' />", '<%=entryNumber%>', 
  							"<s:property value='cycle' />", '<s:property value="startDate" />', '<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', 
  							printTime, '${sessionScope.basePath}', '<s:property value="curMenuId" />', 'ͨ���굥', 
  							'<s:property value="chQueryDate" escape="false"/>', '<s:property value="chStartDate" escape="false"/>',
  							'<s:property value="chEndDate" escape="false"/>', '<s:property value="custName" escape="false"/>');
				}
				else
				{
					alertError(resMsg);
				}
			}, null, "POST", params, null);
			<%--modify end g00140516 2013/02/02 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ�� --%> 
		}
		
		//��¼�嵥��ӡ����
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
					
					<!--������-->
        			<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">�����ϼ�&nbsp;(��0��)</a>
        		
        			<div class="box842W fl ml20 relative">
        				<div class="bg"></div>
						<div class="top"></div>
						<div class="con relative" >
							<div class="box747W fl">
								<div class="div747w444h">
									<p class="tit_info mb10" align="left"><span class="bg"></span><s:property value="cdrTypeName" /></p>
									<div class="ptop180 tc p747w411h" id="inn">
										<table class="tb_blue03 mb20 bold" align="center" width="100%">
											<tr>
												<th colspan="4" class="tl">
													<span class="cdr_logo" onclick='javascript:f_setTimeout(0);'></span>
													<span class="pl130">�й��ƶ�ͨ�� �ͻ��굥</span>
												</th>
											</tr>
											<tr>
												<td class="tl">�ͻ�����</td>
												<td class="tl"><s:property value='custName' /></td>
												<td class="tl">�ֻ�����</td>
												<td class="tl"><%=entryNumber%></td>
											</tr>
											<tr>
												<td class="tl">��ѯʱ��</td>
												<td class="tl"><s:property value="chStartDate" escape="false"/>-<s:property value="chEndDate" escape="false"/></td>
												<td class="tl">��ѯ����</td>
												<td class="tl"><s:property value="chQueryDate" escape="false"/></td>
											</tr>
										</table>
										
										<s:if test="null != resultRecords && 0 < resultRecords.size">
											<table class="tb_blue03" align="center" width="100%">
												<tr>
													<td class="tr">
														����ͨ���ѣ�
													</td>
													<td>
														<s:property value="billTotal.localcall" />
													</td>
													<td class="tr">
														�л��ѣ�
													</td>
													<td>
														<s:property value="billTotal.citycall" />
													</td>
													<td class="tr">
														����ҵ��ѣ�
													</td>
													<td>
														<s:property value="billTotal.othercall" />
													</td>
													<td>
														&nbsp;
													</td>
													<td>
														&nbsp;
													</td>
												</tr>
												<tr>
													<td class="tr">
														��;ͨ���ѣ�
													</td>
													<td>
														<s:property value="billTotal.longcall" />
													</td>
													<td class="tr">
														���ڳ�;��
													</td>
													<td>
														<s:property value="billTotal.inlancall" />
													</td>
													<td class="tr">
														�۰�̨��;��
													</td>
													<td>
														<s:property value="billTotal.colonycall" />
													</td>
													<td class="tr">
														���ʳ�;��
													</td>
													<td>
														<s:property value="billTotal.interncall" />
													</td>
												</tr>
												<tr>
													<td class="tr">
														����ͨ���ѣ�
													</td>
													<td>
														<s:property value="billTotal.roamcall" />
													</td>
													<td class="tr">
														�������Σ�
													</td>
													<td>
														<s:property value="billTotal.inlanroam" />
													</td>
													<td class="tr">
														�۰�̨���Σ�
													</td>
													<td>
														<s:property value="billTotal.colonyroam" />
													</td>
													<td class="tr">
														�������Σ�
													</td>
													<td>
														<s:property value="billTotal.internroam" />
													</td>
												</tr>
											</table>
										</s:if>
										<table class="tb_blue03 mb10" align="center" width="100%">
											<tr class="tr_color">
												<th class="tl" width="11%">
													��ʼʱ��(hh:mm:ss)
												</th>
												<th class="tl" width="11%">
													ͨ�ŵص�
												</th>
												<th class="tl" width="7%">
													ͨ�ŷ�ʽ
												</th>
												<th class="tl" width="17%">
													�Է�����
												</th>
												<th class="tl" width="12%">
													ͨ��ʱ��
												</th>
												<th class="tl" width="7%">
													ͨ������
												</th>
												<th class="tl" width="19%">
													�ײ��Ż�
												</th>
												<th class="tl">
													ͨ�ŷ�(Ԫ)
												</th>
											</tr>
											<s:if test="resultRecords == null || resultRecords.size == 0">
												<tr>
													<td colspan="99" class="list_mess">
														�޶�Ӧ���굥��¼
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
																		<s:if test="#st.index == 0 || #st.index == 3 || #st.index == 7">
																			<td class="tr">
																		</s:if>
																		<s:else>
																			<td class="tl">
																		</s:else>																		
																			<s:property />
																		</td>
																	</s:iterator>
																</s:generator>
															</tr>
														</s:iterator>															
													</s:else>													
												</s:iterator>
												<tr class="bold">
													<td class="tl">
														�ϼ�
													</td>
													<td>
													</td>
													<td>
													</td>
													<td>
													</td>
													<td>
													</td>
													<td>
													</td>
													<td>
													</td>
													<td class="tr">
														<s:property value="billTotal.totalFee" />
													</td>
												</tr>																												
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
							<a class="btn_120_82_1" href="javascript:void(0)" onclick="printInfo()" >��ӡ<br/>(��1��)</a>
						</div>
	             	</s:if>
				</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
		<iframe id="processFrm" frameborder="0" scrolling="no" style="display: none" src=""></iframe>
	</body>
</html>