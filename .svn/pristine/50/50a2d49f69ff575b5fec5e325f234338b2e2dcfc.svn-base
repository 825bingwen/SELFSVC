<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
//add begin by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
//获取电子发票特性参数，true为开启
TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
//add end by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	</head>
	<body scroll="no">
		<form name="actform" method="post">
		
			<!-- 办理业务手机号码 -->
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<!-- 业务受理号 -->
			<input type="hidden" id="recoid" name="invoicePrint.recoid">
			<!-- 账期 -->
			<input type="hidden" id="billCycle" name="invoicePrint.billCycle">
			<!-- 账号 -->
			<input type="hidden" id="acctId" name="invoicePrint.acctId">
			<!-- 打印类型：0收据；1发票 -->
			<input type="hidden" id="invType" name="invoicePrint.invType">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main" align="center">
			
				<%@ include file="/customer.jsp"%>
				
				<!--滚动条-->
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName%>
								</p>
								<p class="ptop180 tc p747w411h" id="inn">
									<table class="tb_blue" width="100%" align="center">
										<tr>
											<!-- 标题行 -->
											<s:iterator value="servicetitle" status="title">
												<th class="list_title" align="center" width="18%"
													id="title<s:property value="#title.getIndex()"/>">
													<s:property />
												</th>
											</s:iterator>
											<th class="list_title" align="center" width="15%;">操作</th>
										</tr>
	
										<!-- 列表行 -->
										<s:iterator value="result" status="result">
											<tr>
												<s:iterator value="result.get(#result.index)" id="reData"
													status='st'>
													<s:if test="#st.index < 5">
														<s:if test="#st.index != 5">
															<td align="center">
																<s:property value='reData' />
															</td>
														</s:if>
													</s:if>
												</s:iterator>
												<td align="center">
													<input type="button" class="bt2_liebiao white" value="打印"
														onmousedown="" onmouseup=""
														onclick="initInvoicePrint('<s:property value="result.get(#result.index)[0]" />',
															'<s:property value="result.get(#result.index)[5]" />','<s:property value="result.get(#result.index)[6]" />',
															'<s:property value="result.get(#result.index)[7]" />');">
												</td>
											</tr>
										</s:iterator>
										<tr>
											<td colspan="100">
												<strong>&nbsp;&nbsp;&nbsp;&nbsp;合计条数：</strong>
												<s:property value="result.size" />条
											</td>
										</tr>
									</table>
								</p>
								<!-- 列表内容 -->
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">
					var myScroll = new virtualScroll("inn","gunDom");
				</script>
				<!--滚动条结束-->
				
				<a href="#" class="bt2_liebiao fr mr92" onmousedown="this.className='bt2_liebiao fr mr92'" onmouseup="this.className='bt2_liebiao fr mr92';topGo('rePrintInvoice', 'invoice/qryCurrentMonth.action'); return false;" style="display:inline">返回</a>
			</div>
			
			<%@ include file="/backinc.jsp"%>
			
		</form>
	</body>
</html>

<script type="text/javascript">
		
	// 防止页面重复提交
	var submitFlag = 0;
	
	// 返回业务办理
	function goback(curmenu) 
	{
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath}login/backForward.action";
		document.actform.submit();
	}
	
	// 处理键盘事件
	document.onkeydown = keyDown;
	
	function keyDown(e)
	{
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
			goback("<s:property value='curMenuId' />") ;
	   		return ;
		}
	}
	
	// 发票打印机
	function initInvoicePrint(recoid, billCycle, acctId, invType) 
	{
		// 发票打印机设备
		var prtObj;
		
		//modify begin by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
		//如果开启电子发票，不进行打印机校验
		var isElectronInvoice = "<%=isElectronInvoice %>";
		if ("true" != isElectronInvoice)
		{
			try {
				
				prtObj = window.parent.document.getElementById("invprtpluginid");
				
			    // 打开发票打印机串口
				var openCom = prtObj.OpenCom();
				if (openCom == 1) 
				{
					alertRedErrorMsg("警告:发票打印机串口故障!");
					return;
				} 
				else 
				{
					if (openCom == 61) 
					{
						alertRedErrorMsg("错误:发票打印机故障,无法初始化!");
						return;
					} 
					else if (openCom == 65) 
					{
						alertRedErrorMsg("警告:发票打印机缺纸!");
						return;
					} 
					else if (openCom != 0) 
					{
						alertRedErrorMsg("错误:打开设备异常,无法初始化发票打印机!");
						return;
					}
				}
				
				// 初始化发票打印机
				var initInvoicePrt = prtObj.InitVoicePrint();
				if (initInvoicePrt == 61) 
				{
					alertRedErrorMsg("错误:发票打印机故障,无法初始化!");
					return;
				} 
				else if (initInvoicePrt == 65) 
				{
					alertRedErrorMsg("警告:发票打印机缺纸!");
					return;
				}
				else if (openCom != 0) 
				{
					alertRedErrorMsg("错误:打开设备异常,无法初始化发票打印机!");
					return;
				}
				
				// 检查发票打印机缺纸
				var v = prtObj.CheckPaper();
				if (v != 0 )
				{
					alertRedErrorMsg("警告:发票打印机缺纸或故障!");
				    return;
				}
			}
			catch (e) 
			{
				alertRedErrorMsg("警告:发票打印机初故障,无法打印发票!");
				return;
			}
		}
		//modify end by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
		
		//防止重复提交
		if (submitFlag == 0) 
		{
			submitFlag = 1;	
		
			// 业务受理号
			document.getElementById("recoid").value = recoid;
			// 帐期
			document.getElementById("billCycle").value = billCycle;	
			// 账号
			document.getElementById("acctId").value = acctId;
			// 打印类型：0收据；1发票
			document.getElementById("invType").value = invType;
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath}invoice/printInvoice.action";
			document.actform.submit();
		}
	}
</script>