<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/cdrlist-paginator.tld" prefix="pg"%>
<%@page import="java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp" %>
<%
	
	String[] summaryTitle = (String[]) request.getAttribute("summaryTitle");
	Map totalMap = (Map) request.getAttribute("totalMap");
	
	//add begin cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	String entryNumber = customerSimp.getServNumber();
	entryNumber = entryNumber.substring(0,3) + "****" + entryNumber.substring(entryNumber.length()-4, entryNumber.length());
	//add end cKF76106 2013/03/26 R003C12L12n01 OR_NX_201301_222
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
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
		<script type="text/javascript">
		var XmlHttpRequest;
		
		function bodyLoad()
		{
			//不能打印清单
       		if ("1" != "<s:property value='printFlag' />")
       		{
       			return;
       		}
       		
       		try
       		{
	       		var initPrt = fGetPrinterStatus();
		       	if (initPrt == 1)
		       	{
		        	alertError("票据打印机缺纸或故障，无法打印清单。");
		           	getObj("print").style.visibility = "hidden";
		       	}
		       	else if(initPrt == -99)
		       	{
		           	alertError("获取打印机状态失败。");
		           	getObj("print").style.visibility = "hidden";
		      	}
		       	else if(initPrt == 2)
		       	{
		           	alertError("票据打印机缺纸，无法打印清单。");
		           	getObj("print").style.visibility = "hidden";
		       	}
		       	else if(initPrt == 41)
		       	{
		           	alertError("票据打印机设备底层驱动程序未安装，无法打印清单。");
		           	getObj("print").style.visibility = "hidden";
		       	}
		       	else if(initPrt == -1)
		       	{
		           	alertError("票据打印机状态发生异常，无法打印清单。");
		           	getObj("print").style.visibility = "hidden";
		       	} 
		       	else if(initPrt != 0)
		       	{
		           	alertError("票据打印机故障，无法打印清单。");
		           	getObj("print").style.visibility = "hidden";
		       	}
		   	}
		   	catch (e)
		   	{
		       	alertError("发生异常，无法初始化票据打印机。");
		       	getObj("print").style.visibility = "hidden";
	       	}
   		}
		
		function goback(curmenu) 
		{
    		document.actform.target = "_self";
			
			document.actform.action = "${sessionScope.basePath}cdrquery/selectType.action";
			document.actform.submit();  		    		
		}
		
		function printInfo()
		{
			//不能打印清单
       		if ("1" != "<s:property value='printFlag' />")
       		{
       			return;
       		}
 			
 			var printTime = getCurrentTime();
  			doPrint_NXOld('1', "<s:property value='cdrType' />", '<%=entryNumber%>', 
  					'<s:property value="cycle" />', '<s:property value="startDate" />', 
  					'<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', printTime, '${sessionScope.basePath}', 
  					'<s:property value="curMenuId" />');
		}
		
		function keyDown(e) 
		{
      		// 返回首页重新计时
			resetGoHome();
      		
      		var key = GetKeyCode(e);
      		var realkey = String.fromCharCode(key);
      		
      		//82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback('');
		   		return ;
			}
      		//打印
      		else if (key == 80) 
      		{   
          		printInfo();
      		}
      	}
		
		document.onkeydown = keyDown;
		
		//修改清单打印次数
		function InsertPrintInfo()
		{
    		var url = "${sessionScope.basePath }cdrquery/updatePrintTimes.action";
			
			var loader = new net.ContentLoader(url, null, null, "POST", "cdrType=<s:property value='cdrType' />", null);    		
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />">
			
			<%@ include file="/titleinc.jsp"%>
				
				<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

        		<!--滚动条-->
        		<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回首页</a>
        		<div class="box842W fl ml20 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">					
							<div class="div747w444h">
								<!-- 列表内容 -->
                        		<p class="tit_info" align="left"><span class="bg"></span><s:property value="tableHeader" /></p>
								<div class="ptop180 tc p747w411h" id="inn">
									<table class="tb_blue02" align="center" width="100%">
										<tr class="tr_color">
											<s:iterator value="tableTitle" id="title">
												<th class="tr_color" align="center">
													<s:property value="title" />
												<br/></th>
											</s:iterator>
										</tr>
										<s:if test="resultRecords == null || resultRecords.size() == 0">
											<tr>
												<td colspan="99" class="list_mess" align="center">
													未查询到详单记录
												<br /></td>
											</tr>
										</s:if>
										<s:else>
											<s:iterator value="resultRecords" id="record">
												<tr>
													<s:generator separator="," val="record">
														<s:iterator status="st">										
															<td align='center'>
																<s:property/>
															<br /></td>
														</s:iterator>
													</s:generator>
												</tr>									
											</s:iterator>
											<tr>
												<td colspan="<s:property value='titleCols' />" align="left">
													<% int index = 0; %>
													<s:iterator value="tableTail" id="tail">											
														<strong><s:property value="tail" /></strong><%=(String) totalMap.get(summaryTitle[index++]) %>
														&nbsp;&nbsp;										
													</s:iterator>
												</td>
											</tr>
											<s:if test="tableDescription != null && tableDescription.length > 0">
												<tr>
													<td colspan="<s:property value='titleCols' />" align="left">										
														<s:iterator value="tableDescription" id="desc">											
															<s:property value="desc" /><br/>					
														</s:iterator>										
													</td>
												</tr>
											</s:if>
										</s:else>							
									</table>
								</div>								
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
                
                <s:if test="resultRecords != null && resultRecords.size() > 0 && 1 == printFlag">
	                <div class="box120W fl ml10" id="print">
						<p class="blank10"></p>
						<a class="btn_120_82" href="javascript:void(0)" onclick="printInfo()" ></a>
					</div>
				</s:if>
                
                
                <script type="text/javascript">			
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
		<iframe id="processFrm" frameborder="0" scrolling="no" style="display: none" src=""></iframe>
	</body>
</html>
