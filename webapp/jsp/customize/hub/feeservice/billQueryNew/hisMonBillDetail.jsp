<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String orgName = "";
	if (termInfo != null)
	{
		orgName = termInfo.getOrgname();
	}
	
	String month = (String)request.getAttribute("month");
	
	String menuid = (String)request.getAttribute("curMenuId");
	
	String qryType = (String)request.getAttribute("qryType");
	
	String custType = (String)request.getAttribute("custType");
	String unitelnumCon = (String)request.getAttribute("unitenumc");
   
    String billMonth = (null == month ? "" : month);
    
    String billMenuid = (null == month ? "" : menuid);
    
    String billQryType = (null == qryType ? "" : qryType);
    
    String billCustType =(null == custType ? "" : custType);
    String billunitelnumCon =(null == unitelnumCon ? "" : unitelnumCon); 
    
    session.setAttribute("HIS_BILL_MONTH",billMonth);
    session.setAttribute("HIS_BILL_MENUID",billMenuid);
    session.setAttribute("HIS_BILL_QRYTYPE",billQryType);
    session.setAttribute("HIS_BILL_CUSTTYPE",billCustType);
    session.setAttribute("HIS_BILL_UNITELNUMCON",billunitelnumCon);
    
    
    String custInfo = (String)request.getAttribute("custInfo");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<style type="text/css">
		.btn_120_832{width:120px;height:82px;display:block;background:url(${sessionScope.basePath }images/btn_120_82_1.png) no-repeat;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/btn_120_82_1.png");_background:none}
		.btn_120_832_1{text-align:center;display:block;margin-top:25px;font-weight:bold;font-size:14pt;}
		</style>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/Bill_Printer_Hub.js"></script>
		<script type="text/javascript">
		
		var submitFlag = 0;
		
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
				goback("<s:property value='curMenuId' />") ;
		   		return ;
			}
			
			//80:打印
			if (key == 80)
			{
				openWindow('openWin1');
			}
		}
		
		function printInfo()
		{
			//已经点击了返回，不能再进行打印
			if (submitFlag == 1)
			{
				return;
			}
			wiWindow.close();
			
			var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
			var Ret3;
			try{
				Ret3 = DPsListPrinter1.PrintPicture(1);
			}
			catch(e)
			{
				alertError("由于打印机控件未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
				return;
			}
		   	if (Ret3 == 1)
		   	{
		      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret3 == 41)
		   	{
		      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	
		   	var Ret4;
     				
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
     		if (Ret4 == 1)
		   	{
		      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	
		   	//历史账单信息打印
     		var hisPntFlag = document.getElementById('hisPntFlag').value;
		   	if(8 != hisPntFlag)
		   	{
		   		alert("历史账单信息未全部查询出来，请稍候......");
		   		return;
		   	}
		   	var tab1 = document.getElementById('hisUserTab');
		   	var tab2 = document.getElementById('hisBalance');
		   	var tab3 = document.frames('ifr_his_bd').document.getElementById('hisBdTab');
		   	var tab4 = document.frames('ifr_his_ac').document.getElementById('hisBAccTab');
		   	var tab5 = document.frames('ifr_his_mv').document.getElementById('hisBmTab');
		   	var tab6 = document.frames('ifr_his_bss').document.getElementById('hisBssTab1');
		   	var tab7 = document.frames('ifr_his_bss').document.getElementById('hisBssTab2');
		   	var tab8 = document.frames('ifr_his_asv').document.getElementById('hisBaSvTab');
		   	var tab9 = document.frames('ifr_his_comm').document.getElementById('hisBcommTab1');
		   	var tabA = document.frames('ifr_his_comm').document.getElementById('hisBcommTab2');
		   	var tabB = document.frames('ifr_his_iode').document.getElementById('hisIoDeTab');
		   	var isUnite = '<%=billCustType%>';
		   	
		   	var custArray = createCustArray(tab1, tab2);
		   	var bdArray = createBdArray(tab3,isUnite);
		   	var baccArray = createBaccArray(tab4);
		   	var bmArray = createBmArray(tab5);
		    
		   	var bssArray = createSsArray(tab6,tab7,tab8);
		   	//var commArray = createCommArray(tab9,tabA);
		   	var commArray=document.frames('ifr_his_comm').getPrintArray();
		   	var iodeArray = createIodeArray(tabB);
		   	
		   	var printerArray = new Array();
		    printerArray = printerArray.concat(custArray);
		    printerArray = printerArray.concat(bdArray);
		    printerArray = printerArray.concat(baccArray);
		    printerArray = printerArray.concat(bmArray);
			printerArray = printerArray.concat(bssArray);
		    printerArray = printerArray.concat(commArray);
			printerArray = printerArray.concat(iodeArray);
			
			for(var i=0; i<printerArray.length; i++)
			{
				var rowInfo = printerArray[i];
				
				Ret4 = DPsListPrinter1.PrintLine(rowInfo);
			}
     		//历史账单信息打印
     		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
  	      	if (Ret4 == 1)
		   	{
		      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	
  	      	Ret4 = DPsListPrinter1.PrintLine("  声明：本账单信息仅供核对之用，不做任何凭证。");
  	      	Ret4 = DPsListPrinter1.PrintLine("  以上内容，如有不详之处，请向营业员咨询。");
  	      	Ret4 = DPsListPrinter1.PrintLine("  客户服务热线：10086");
  	      	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  	      	Ret4 = DPsListPrinter1.PrintLine("  打印地点：<%=orgName %>");
  	      	Ret4 = DPsListPrinter1.PrintLine("  打印时间: " + getCurrentTime());
  	      	
  	      	//切纸
  			var Ret5 = DPsListPrinter1.SetCutPaper();
  			if (Ret5 == 1)
  			{
      			alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
  			else if (Ret5 == 41)
  			{
     			alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
		}
			function goMonth(monthStr) 
		{	
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.month.value = monthStr;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}hubfeeservice/queryMonthBill.action";
				document.actform.submit();
			}	
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
		<input type="hidden" name="month" value="<s:property value='month' />">			
			<input type="hidden" id="hisPntFlag" value='0' />
			<%@ include file="/titleinc.jsp"%>
       <div class="main" id="main">
			<%@ include file="/customer.jsp"%>
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
          <div class="clear"></div>
		        <!--滚动条-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span><%=month+menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
								
								<!-- 客户信息 1 2 -->
								 <table id="userInfoTab"  width="100%" border="0" cellpadding="0" cellspacing="0">
						  			   <tr>
						  					<td width="55%">
							    				<table id="hisUserTab"  width="100%">
													<tr>
														<td width="100%" align="left">
															<p class="margin-top:0px;" align="left">
															<img src="${sessionScope.basePath }images/loading2.gif" />
															<font size="1">正在查询客户信息，请稍候......</font>
															</p>
														</td>
													</tr>			
														<script type="text/javascript">
														var tab  = document.getElementById('hisUserTab');
														tab.deleteRow();
														var custStr = '<%=custInfo%>';
														var rowArray = custStr.split('%');
														var length = rowArray.length;
														var titleArray = new Array('客&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户：',
																		  	 	     '手机号码：','归&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;属：',
																		  	 	     '品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌：',
																		  	 	     '计费周期：','','');
														for(var i=0; i<titleArray.length; i++)
														{
															var row = tab.insertRow();
															row.height=20;
															var cell = row.insertCell();
															cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
															if(i < length)
															{
																var content = titleArray[i] + rowArray[i].toString();
															}
															else
															{
																var content = titleArray[i];
															}
															cell.innerHTML = "<font size=2>" + content + "</font>";
															cell.align = "left";
														}
													</script>																		
												</table>
											</td>
											<td width="45%">
											    <table id="hisBalance"  width="100%">
														<tr>
															<td width="100%" align="left">
																<p class="margin-top:0px;" align="left">
																<img src="${sessionScope.basePath }images/loading2.gif" />
																<font size="1">正在查询客户期末余额信息，请稍候......</font>
																</p>
															</td>
														</tr>			
														<script type="text/javascript">
														var url = "${sessionScope.basePath }hubfeeservice/hisBillBalance.action";
														var loader1 = new net.ContentLoader(url, netload = function () 
														{
															var resXml = this.req.responseXML;
															
															if (resXml)
															{
																	var root = resXml.documentElement;
																	
																	if('error' == root.tagName)
																	{
																		//alert("查询用户期末余额信息错误！");
																		return;
																	}
																    
																    disHisBillBalance(resXml);								
															}
														    else
															{	
																//alert("查询用户期末余额信息错误！");
																return;
															}								
														}, null, "POST", "", null);
														
														function disHisBillBalance(xmlData)
														{
															var tab  = document.getElementById('hisBalance');
															tab.deleteRow();
															
															var titleArray = new Array('本期末余额',
																					 '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话费账户余额：',
																		  	 	     '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;专款账户余额：',
																		  	 	     '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;返还账户余额：',
																		  	 	     '个人积分余额：','本期消费：','本期末欠费：');
															
															var root = xmlData.documentElement;
															var allNodes = root.childNodes;
						  	 								var length = allNodes.length;
						  	 								
						  	 								var row = tab.insertRow();
															row.height=20;
															var cell = row.insertCell();
															cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
															var content = titleArray[0];
															cell.innerHTML = "<font size=2>" + content + "</font>";
															cell.align = "left";
						  	 									
						  	 								for(var i=0; i<length; i++)
															{
																	var nodeValue = allNodes[i].text;
																	if('EMPTY' == nodeValue)
																	{
																		continue;
																	}
																	
																	var row = tab.insertRow();
																	row.height=20;
																	var cell = row.insertCell();
																	cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
																	var content = titleArray[i+1] + allNodes[i].text;
																	if(i == 3)
																	{
																		content = titleArray[i+1] + allNodes[i + 2].text;
																	}
																	else if(i == 5)
																	{
																		content = titleArray[i+1] + allNodes[i - 2].text;
																	}
																	cell.innerHTML = "<font size=2>" + content + "</font>";
																	cell.align = "left";
															}
															
															var pntFlag = document.getElementById("hisPntFlag").value;
				
															document.getElementById("hisPntFlag").value = parseInt(pntFlag,10) + 1;
														}
														</script>																		
												</table>
											</td>
										</tr>	 
								</table>
								
								 <!-- 消费信息 3 4 5 6 -->
							    <table id="consumeTab" width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="55%">
												<!-- 文字信息 -->
												<iframe id="ifr_his_bd" width="100%"
													onload="this.height=(window.frames['ifr_his_bd'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillDetail.action">
												</iframe>
											</td>
											<td width="45%">
												<!-- 图表信息 -->
												<iframe id="ifr_his_bti" width="100%"
													onload="this.height=(window.frames['ifr_his_bti'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillTrendImg.action">
												</iframe>
												<iframe id="ifr_his_bsi" width="100%"
													onload="this.height=(window.frames['ifr_his_bsi'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillStructImg.action">
												</iframe>
												<!--  
												<iframe id="ifr_his_rev" width="100%"
													onload="this.height=(window.frames['ifr_his_rev'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillValuate.action">
												</iframe>
												-->
											</td>
										</tr>	 
								</table>
								
								<!-- 账户信息 7 -->
								<table width="100%">
										<tr height='20'>
												<td width="100%" align="left">
													<p><font color='black' size="1"><b>账户信息（单位：元）</b></font></p>
												</td>
										</tr>
								</table>
								<table id="accInfoTab"  width="100%">
										<tr>
											<td>
												<iframe id="ifr_his_ac" width="100%" 
												  onload="this.height=(window.frames['ifr_his_ac'].document.body.scrollHeight);"
												  	marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillAccInfo.action">
												</iframe>
											</td>
										</tr>	 
								</table>
								
								<!-- M值信息 8 -->
								<table id="mvalueTab" width="100%">
										<tr>
											<td>
												<iframe id="ifr_his_mv" width="100%" 
													onload="this.height=(window.frames['ifr_his_mv'].document.body.scrollHeight);"
												  	marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toHisBillMvalue.action">
												</iframe>
											</td>
										</tr>	 
								</table>
								
								<table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">一、费用明细</font></p>
											</td>
									</tr>
								</table>
								
								<!-- 业务费用信息 9 10 -->
								<table id="svTab" width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr height='20'>
														<td width="100%" align="left">
															<p><font color='black' size="1">1、中国移动自有业务费用</font></p>
														</td>
												</tr>
											</table>
											<iframe id='ifr_his_bss' width="100%"
												onload="this.height=(window.frames['ifr_his_bss'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toHisBillSelfSv.action">
											</iframe>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%">
												<tr height='20'>
														<td width="100%" align="left">
															<p><font color='black' size="1">2、代收费业务费用</font></p>
														</td>
												</tr>
											</table>
											<iframe id='ifr_his_asv' width="100%" 
												onload="this.height=(window.frames['ifr_his_asv'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toHisBillAgentSv.action">
											</iframe>
										</td>
									</tr> 
								</table>
								
								<table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">二、通信量使用信息明细</font></p>
											</td>
									</tr>
								</table>	
								
								<!-- 通信费用信息 11 -->
								<table id="commTab" width="100%">
									<tr>
										<td>
											<iframe id='ifr_his_comm' width="100%"
												onload="this.height=(window.frames['ifr_his_comm'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toHisBillCommDetail.action">
											</iframe>
										</td>
									</tr>	 
								</table>
								
								<table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">三、账户明细</font></p>
											</td>
									</tr>
								</table>	
								
								<!-- 账户明细 12 -->
								<table id="accDeTab" width="100%">
									<tr>
										<td>
											<iframe id='ifr_his_iode' width="100%" 
												onload="this.height=(window.frames['ifr_his_iode'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toHisBillAccDetail.action">
											</iframe>
										</td>
									</tr>	 
								</table>
								<!-- 列表内容 -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px"  >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
                <div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_83" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>

				</div>
				<!-- OR_HUB_201508_493  账单通信量明细修改(二阶段) 后台不支持，先屏蔽
				 <div class="box120W fl ml10" >
					<p class="blank10"></p>
					
					<a class="btn_120_832" href="javascript:void(0)" onclick="colQuerybill('139Email');" >
					<span class="btn_120_832_1" >
					
					139邮箱接收</span></a>

				</div> -->
				<div class="box120W fl ml10" >
					<p class="blank10"></p>
					<a class="btn_120_832" href="javascript:void(0)" onclick="colQuerybill('message');" >
					
					<span class="btn_120_832_1" >
					
					短信接收</span></a>

				</div>
				<div class="box120W fl ml10" >
					<p class="blank10"></p>
					<a class="btn_120_832" href="javascript:void(0)" onclick="colQuerybill('colMessage');"><span class="btn_120_832_1" >
					
					彩信接收</span></a></a>

				</div>
				
				
                <div class=" clear"></div>
                
                <!--弹出窗-->
                
                <div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确定要打印当前帐单吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <div class="openwin_tip" id="openWinLoading" style="width:708px; height:192px;">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">帐单正在打印，请稍候...</p>
                      </div>
                    </div>
                </div>
                 <!--账单协同查询提示-->
                <div class="openwin_tip" id="colBillQueryWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20" id="colBillQuery"></p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    
                    </div>
                </div>
                
                <script type="text/javascript">
                
				openWindow = function(id){
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
					
				}
				
				openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",708,192);//打开弹出窗口例子
					printInfo();
					//gotoPrintSuccess();
					
				}
				
				openWindowSuccess = function(id){
					wiWindow = new OpenWindow("openWinSuccess",708,392);//打开弹出窗口例子
				}
				
				function btnClick(btn,btClass){
					  var btns=document.getElementById('btns').getElementsByTagName('a');
					  for(i=0;i<btns.length;i++){
						  btns[i].className=btClass;
						  }
					  btn.className=btClass+'on';
					  }
				</script>
                <!--弹出窗结束-->
				
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
			
		function goback(curmenu) 
		{
			//已经点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }<%=menuURL %>";
				document.actform.submit();
				
			}	
		}
	function colQuerybill(type){
	var resuInfo="";
	var url="";
	if(type=="139Email"){
	 url= "${sessionScope.basePath }hubfeeservice/billColQuery139Email.action?isCurr=no";
	 resuInfo="139邮箱接收账单信息出现异常！";
	 }
	else if(type=="message") {url= "${sessionScope.basePath }hubfeeservice/billColQueryMessage.action?isCurr=no";
	resuInfo="短信接收账单信息出现异常！";}
	else {
		
		resuInfo="彩信接收账单信息出现异常！";
		url= "${sessionScope.basePath }hubfeeservice/billColQueryColorMessage.action?isCurr=no";
		}
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
