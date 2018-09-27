<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>

<%
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	String servNumber = "";
	String subsName = "";
	String brandName = "";
	String showTitle = "";
	if (customerSimp != null)
	{
		servNumber = customerSimp.getServNumber();
		subsName = customerSimp.getCustomerName();
		brandName = customerSimp.getBrandName();
		showTitle = "客户  "+subsName+"  手机号码  "+servNumber+"  品牌  "+brandName;
	}
	

	//Vector billItems = (Vector) request.getAttribute("allBillAnalysis");
	
	CRSet billItems = (CRSet) request.getAttribute("billAnalysisItems");
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String orgName = "";
	if (termInfo != null)
	{
		orgName = termInfo.getOrgname();
	}
	String month = (String)request.getAttribute("month");
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
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("  手机号码: <%=servNumber %>");
     		Ret4 = DPsListPrinter1.PrintLine("  用户名称: <%=subsName %>");    
     		Ret4 = DPsListPrinter1.PrintLine("  账单月份: <%=month %>"); 		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
     		//账单条目：20；费用(元)：10；开始日期：12
     		//Ret4 = DPsListPrinter1.PrintLine("  账单条目           费用(元)  开始日期    结束日期");
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
     		//打印
			<%
				 for(int i=0;i<billItems.GetRowCount();i++)
				 {
				 	if(billItems.GetValue(i,1).equals(""))
				 	{
				 		%>
				 			Ret4 = DPsListPrinter1.PrintLine("<%="    "+billItems.GetValue(i,0) %>");
				 		<%
				 	}
				 	else if(billItems.GetValue(i,2).equals("2"))
				 	{
				 		%>
						 	Ret4 = DPsListPrinter1.PrintLine("<%="	"+billItems.GetValue(i,0)+"		"+billItems.GetValue(i,1) %>");
						<%
				 	}
				 	else
				 	{
				 		%>
				 			Ret4 = DPsListPrinter1.PrintLine("<%=billItems.GetValue(i,0).trim()+"		"+billItems.GetValue(i,1).trim() %>");
				 		<%
				 	}
				 }
			 %>
     		
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
		
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';openWindow('openWin1'); return false;" style="display:inline"> 打印当前账单</a>
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
				<%
				 if(billItems==null||billItems.GetRowCount()==0){
				%>
				<div class="b966 tc">
			  	   <div class="p40">
			  	   	<div class="blank30"></div>
				      <p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span><%=month+menuName %></p> 
				      	<div class="blank30"></div>
					    <table class="tb_blue" width="80%">
							<tr>
								<th colspan="100%" style="text-align: left">客户账单分析查询</th>
							</tr>
							<tr>
								<td width="100%" style="text-align: center" colspan="2">未查询到任何账单分析信息</td>
							</tr>
	
					   </table>
				    </div>
				</div>
				<%}else{ %>
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
								<div class="blank30"></div>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
									<!-- 帐单内容 -->
									<table class="tb_blue" width="80%">
											<tr>
												<th colspan="100%" style="text-align: center"><%=showTitle %></th>
											</tr>
											 <%
												 for(int i=0;i<billItems.GetRowCount();i++)
												 {
												 	if(billItems.GetValue(i,1).equals(""))
												 	{
												 		%>
												 			<tr>
														 		<th colspan="100%" style="text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;<%=billItems.GetValue(i,0) %></th>
														 	</tr>
												 		<%
												 	}
												 	else if(billItems.GetValue(i,2).equals("2"))
												 	{
												 		%>
														 	<tr>
														 		<td width="65%" style="text-align: right"><%=billItems.GetValue(i,0) %>&nbsp;&nbsp;&nbsp;&nbsp;</td>
														 		<td width="35%" style="text-align: center"><%=billItems.GetValue(i,1) %>&nbsp;&nbsp;&nbsp;&nbsp;</td>
														 	</tr>
														 <%
												 	}else{
												 		%>
												 			<tr>
														 		<td width="65%" style="text-align: left"><%=billItems.GetValue(i,0) %>&nbsp;&nbsp;&nbsp;&nbsp;</td>
														 		<td width="35%" style="text-align: center"><%=billItems.GetValue(i,1) %>&nbsp;&nbsp;&nbsp;&nbsp;</td>
														 	</tr>
												 		<%
												 	
												 	}
												 }
											 %>
									</table>
								
								<!-- 列表内容 -->
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
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>

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
               <%} %>	
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
	</script>
</html>
