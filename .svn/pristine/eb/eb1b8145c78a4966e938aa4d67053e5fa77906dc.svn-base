<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	String servNumber = "";
	if (customerSimp != null)
	{
		servNumber = customerSimp.getServNumber();
	}
	
	String subsName = "";
	if (customerSimp != null)
	{
		subsName = customerSimp.getCustomerName();
	}
	
	List billItems = (List) request.getAttribute("billItems");
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String orgName = "";
	if (termInfo != null)
	{
		orgName = termInfo.getOrgname();
	}
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
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e)
		{
			// 返回首页重新计时
			resetGoHome();
			
			//接收键盘码
			var key = GetKeyCode(e);
		    //8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 退出
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return ;
			}
			// 0键
			else if (key == 48)
			{
				topGo('qryBill', 'feeservice/feeServiceFunc.action');
				return ;
			}
			// 1键
			else if (key == 49 || key == 80)
			{
				openWindowloading('openWinLoading');
				return ;
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
			/*
			var Ret3 = DPsListPrinter1.PrintPicture(1);
		   	if (Ret3 == 1)
		   	{
		      	alert("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret3 == 41)
		   	{
		      	alert("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	*/
		   	var Ret4;
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("  手机号码: <%=servNumber %>");
     		Ret4 = DPsListPrinter1.PrintLine("  用户名称: <%=subsName %>");     		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
     		//账单条目：20；费用(元)：10；开始日期：12
     		Ret4 = DPsListPrinter1.PrintLine("  账单条目                              金额(元)");
     		if (Ret4 == 1)
		   	{
		      	alert("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alert("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
     		
     		<%
			List subList = null;
			List leafList = null;
			String item = "";
			String[] pairs = null;
			String key = "";
			for (int i = 1; i < billItems.size(); )
			{
				subList = (List) billItems.get(i);
				for (int j = 0; j < subList.size();)
				{
					if (j == 0)
					{
						item = (String) subList.get(j);
						pairs = item.split("\\|");
						key = pairs[0].replaceAll("&nbsp;", " ");
						int len = 38 - key.getBytes().length;
						for (int n = 0; n < len; n++)
						{
							key += " ";
						}
			%>
			Ret4 = DPsListPrinter1.PrintLine("  " + "<%=key + pairs[1] %>");
			<%
					}
					else
					{
						leafList = (List) subList.get(j);
						for (int m = 0; m < leafList.size(); m++)
						{
							item = (String) leafList.get(m);
							pairs = item.split("\\|");
							key = pairs[0];
							key = pairs[0].replaceAll("&nbsp;", " ");
							int len = 38 - key.getBytes().length;
							for (int n = 0; n < len; n++)
							{
								key = key + " ";
							}
		    %>
			Ret4 = DPsListPrinter1.PrintLine("  " + "<%=key + pairs[1] %>");														
			<%
						}
					}
												
					j += 2;
				}
										
				i += 2;
			}
			%>
     		
     		if (Ret4 == 1)
		   	{
		      	alert("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alert("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
     		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
  	      	if (Ret4 == 1)
		   	{
		      	alert("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alert("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
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
      			alert("账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
  			else if (Ret5 == 41)
  			{
     			alert("账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
		}
		
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
			
        		<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
			
          		<div class="clear"></div>
		        <!--滚动条-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc p747w411h" id="inn">
								
									<!-- 帐单内容 -->
									<table class="tb_blue04" width="80%">
										<tr>
											<th class="tl">费用项目</th>
											<th class="tl">金额(元)</th>
										</tr>
										<s:iterator value="billItems" id="items" status="st1">
											<s:if test="#st1.even">
												<s:iterator value="items" id="item" status="st">
													<s:if test="#st.first">
														<s:generator separator="|" val="item">
															<tr>
																<s:iterator>
																	<th class="tl"><s:property escape="false"/></th>
																</s:iterator>
															</tr>
														</s:generator>
													</s:if>
													<s:elseif test="#st.odd">
														<s:iterator value="item" id="leafItem" status="st2">
															<s:generator separator="|" val="leafItem">
																<tr>
																	<s:iterator status="st2">
																		<td class="tl"><s:property escape="false" /></td>																		
																	</s:iterator>
																</tr>
															</s:generator>
														</s:iterator>
													</s:elseif>
												</s:iterator>
											</s:if>
										</s:iterator>										
									</table>
								
								<!-- 列表内容 -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(300);resetGoHome();" />
							<div class="div75w350h" onclick="resetGoHome();">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(300);resetGoHome();"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
                <div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_82_1" href="javascript:void(0)" onclick="openWindow('openWin1')" >打印<br/>(按1键)</a>
					
				</div>
                <div class=" clear"></div>
                
                <!--弹出窗-->
                
                <div class="openwin_tip div708w392h" id="openWin1">
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
                
                <div class="openwin_tip div708w192h" id="openWinLoading">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  alt="打印中..."  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">帐单正在打印，请稍候...</p>
                      </div>
                    </div>
                </div>
                
                <script type="text/javascript">
				openWindow = function(id){
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
				}
				
				openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",708,192);//打开弹出窗口例子
					printInfo();
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
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }<%=menuURL %>";
				document.actform.submit();
				
			}	
		}
	</script>
</html>
