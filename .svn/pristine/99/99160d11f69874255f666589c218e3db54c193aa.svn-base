<%@page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%@page import="java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	
	String listtype = request.getParameter("listtype");
    String isQueryAll = request.getParameter("isQueryAll");
	
	Integer titleCols = (Integer) request.getAttribute("titleCols");
	
	String[] summaryTitle = (String[]) request.getAttribute("summaryTitle");
	Map totalMap = (Map) request.getAttribute("totalMap");
	
	String printFlag1 = (String) request.getAttribute("printFlag");
	
	//终端机是否支持打印票据
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String termSpecial = termInfo.getTermspecial();
	String hasPrinter = termSpecial.substring(0, 1);
	
	String month = request.getParameter("month");
	
	String fee_type = request.getParameter("fee_type");
	if (null == fee_type)
	{
		fee_type = "";
	}
	
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);	
	String servNumber = customerSimp.getServNumber();
	
	String errormessage = (String) request.getAttribute("errormessage");
	if (errormessage == null || "".equals(errormessage))
	{
		errormessage = "未查询到详单记录";
	}
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	errormessage = CommonUtil.errorMessage(errormessage);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	
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
		function bodyLoad()
		{
			//不需要打印清单或者本终端机不支持打印票据
       		if ("1" != "<%=printFlag1 %>" || "1" != "<%=hasPrinter %>")
       		{
       			return;
       		}
       		
       		try
       		{
	       		var initPrt = fGetPrinterStatus();
		       	if (initPrt == 1)
		       	{
		        	alertError("票据打印机缺纸或故障，无法打印清单。");
		        	
		        	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}		           	
		       	}
		       	else if(initPrt == -99)
		       	{
		           	alertError("获取打印机状态失败。");
		           	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}
		      	}
		       	else if(initPrt == 2)
		       	{
		           	alertError("票据打印机缺纸，无法打印清单。");
		           	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}
		       	}
		       	else if(initPrt == 41)
		       	{
		           	alertError("票据打印机设备底层驱动程序未安装，无法打印清单。");
		           	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}
		       	}
		       	else if(initPrt == -1)
		       	{
		           	alertError("票据打印机状态发生异常，无法打印清单。");
		           	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}
		       	} 
		       	else if(initPrt != 0)
		       	{
		           	alertError("票据打印机故障，无法打印清单。");
		           	if (getObj("print") != null)
		        	{
		        		getObj("print").style.visibility = "hidden";
		        	}
		       	}
		   	}
		   	catch (e)
		   	{
		       	alertError("发生异常，无法初始化票据打印机。");
		       	
		       	if (getObj("print") != null)
		        {
		        	getObj("print").style.visibility = "hidden";
		        }
	       	}
   		}
   		
   		//记录分页
		function nextPage(URL, listtype) 
		{
			document.getElementById("listtype").value = listtype;
			
			document.forms[0].target = "_self";
			document.forms[0].action = URL;
			document.forms[0].submit();
									
		}
		
		function goback(curmenu) 
		{
    		document.getElementById("curMenuId").value = document.getElementById('parentMenuID').value;
    		
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}feeservice/selectType.action";
			document.actform.submit();
			document.actform.submit();    		    		
		}
		
		function printInfo()
		{
			//document.getElementById("print").style.disabled = true;
			//不需要打印清单或者本终端机不支持打印票据
       		if ("1" != "<%=printFlag1 %>" || "1" != "<%=hasPrinter %>")
       		{
       			return;
       		}
			
			var isQueryAll =  "<%=isQueryAll%>";
  			var listType = "";
  
  			if (isQueryAll == "true") 
  			{
  				listType = 0;
  			} 
  			else 
  			{
  				listType = "<%=listtype%>";
 			}
 			
 			var printTime = getCurrentTime();
  			doPrint('<%=hasPrinter%>', listType, '<%=servNumber%>', '<%=month %>', '<%=termInfo.getOrgname() %>', 
  					printTime, '${sessionScope.basePath}', document.getElementById('parentMenuID').value);
		}
		
		function keyDown(e) 
		{
      		var key = GetKeyCode(e);
      		var realkey = String.fromCharCode(key);
      		
      		//8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
    
		    //82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback(document.getElementById('parentMenuID').value);
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
    		var url = "${sessionScope.basePath }feeservice/updatePrintTimes.action";
			
			var loader = new net.ContentLoader(url, null, null, "POST", "listtype=<%=listtype %>&month=<%=month %>", null);    		
    		
		}
		<%--add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
		var XmlHttpRequest;   
		// 判断是否开通139邮箱，若开通发送详单到邮箱，没开通提示开通
		function qryMailbox()
		{
		//创建ajax核心 xmlHttpRequest            
		XmlHttpRequest = false;      
		//下面需要建立一个XMLHttpRequest对象,用它进行服务器请求,针cf 不同浏览器建立方法不同      
		if (window.XMLHttpRequest)      
		{      
		    XmlHttpRequest = new XMLHttpRequest();      
		    if (XmlHttpRequest.overrideMimeType)      
		    {      
		        XmlHttpRequest.overrideMimeType('text/xml');      
		    }      
		}      
		else if (window.ActiveXObject)      
		{     
		    try             
		    {      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		    }      
		    catch (e)      
		    {      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		    }      
		}      
		     
		if (!XmlHttpRequest) 
		{      
		    alert('出现错误,不能建立一个XMLHTTP实例!');      
		    return false;      
		}    
		
		var curMenuId = document.getElementById("curMenuId").value;
		var postStr = "listtype=<%=listtype %>&month=<%=month%>&fee_type=<%=fee_type %>";   
		XmlHttpRequest.onreadystatechange = callqryMailboxBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
		XmlHttpRequest.open("POST","${sessionScope.basePath}feeservice/qryMailbox.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
		XmlHttpRequest.send(postStr);//开始向服务器发送xmlHttpRequest   
		}   
  
		//<!--回调的方法-->   
		function callqryMailboxBack()
		{		       
		    if(XmlHttpRequest.readyState==4)   
		    {   
		        if(XmlHttpRequest.status==200)//以上两个判断 确定ajax请求已被成功相应   
		        {   
		        	var retStr=XmlHttpRequest.responseText;
			
		        	// 用户未开通139邮箱，弹出开通139邮箱（免费提示窗口）
		        	if(retStr == 0)
		        	{
			       		openAddMailConfirm('addMailConfirm');
		        	}
		        	// 用户已开通139邮箱
		        	if(retStr == 1)
		        	{
			       		openSendMailPrompt('sendMailPrompt');
		        	}
		        	
		        	// 查询用户是否开通139邮箱失败
		        	if(retStr == 2)
		        	{
		        		alertError("查询用户是否开通139邮箱失败！");
		        	}
		        }
		    }
		}
		
		// 开通139邮箱并发送详单到邮箱   
		function addBillMail()
		{
		//创建ajax核心 xmlHttpRequest            
		XmlHttpRequest = false;      
		//下面需要建立一个XMLHttpRequest对象,用它进行服务器请求,针cf 不同浏览器建立方法不同      
		if (window.XMLHttpRequest)      
		{       
		    XmlHttpRequest = new XMLHttpRequest();      
		    if (XmlHttpRequest.overrideMimeType)      
		    {      
		        XmlHttpRequest.overrideMimeType('text/xml');      
		    }      
		}      
		else if (window.ActiveXObject)      
		{    
		    try             
		    {      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		    }      
		    catch (e)      
		    {      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		    }      
		}      
		     
		if (!XmlHttpRequest) 
		{      
		    alert('出现错误,不能建立一个XMLHTTP实例!');      
		    return false;      
		}    
		
		var curMenuId = document.getElementById("curMenuId").value;
		var postStr = "listtype=<%=listtype %>&month=<%=month%>&fee_type=<%=fee_type %>";	   
		XmlHttpRequest.onreadystatechange=callBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
		XmlHttpRequest.open("POST","${sessionScope.basePath}feeservice/add139Mail.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
		XmlHttpRequest.send(postStr);//开始向服务器发送xmlHttpRequest   
		}   
  
		//<!--回调的方法-->   
		function callBack()
		{
		       
		    if(XmlHttpRequest.readyState==4)   
		    {   
		        if(XmlHttpRequest.status==200)//以上两个判断 确定ajax请求已被成功相应   
		        {   
		        	var retStr=XmlHttpRequest.responseText;
					// 开通成功提示窗口
		        	if(retStr == 1)
		        	{
			       		openSendMailPrompt('sendMailPrompt');
		        	}
		        	// 开通失败
		        	if(retStr == 0)
		        	{
			       		alertError("开通139邮箱失败！");
		        	}
		        }
		    }
		}
		<%--add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
		</script>
	</head>
	<!--  modifiy begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB  OR_HUB_201205_1032 -->
	<body scroll="no" onload="window.focus();bodyLoad();openSurveyDialog();">
	<!--  modifiy end m00227318 2012/09/11 eCommerce R003C12L08_EHUB  OR_HUB_201205_1032 -->
		<form name="actform" method="post">
			<input type="hidden" name="month" value="<%=month %>">
			<input type="hidden" name="fee_type" value="<%=fee_type %>">
			<!-- yKF28472 -->
			<input type="hidden" id="listtype" name="listtype" value="<%=listtype %>">

			<%@ include file="/titleinc.jsp"%>
				
				<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

        		<!--滚动条-->
        		<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) 
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
                <%
                	}
                	else
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
                <%
                	}
                %>
        		
        		<%--modify begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
				<div class="box842W fl ml20 relative">
				<%--modify end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">					
							<div class="div747w444h">
								<!-- 列表内容 -->
                        		<p class="tit_info" align="left"><span class="bg"></span><s:property value="month" />月<s:property value="tableHeader" /></p>
								<div class="ptop180 tc p747w411h" id="inn">


						<!-- Change by LiFeng 修改 "清单查询全部清单时，某一清单查询不到，哪后面的清单都不能看" 的BUG
<%--
						<s:if test="resultRecords == null || resultRecords.size() == 0">
							<table class="tb_blue02" width="100%">
								<tr>
									<td colspan="2" class="list_mess" align="center">
										未查询到详单记录
									<br /></td>
								</tr>
							</table>
						</s:if>
						<s:else>  --%>
						-->
							<table class="tb_blue02" align="center" width="100%">
								<tr class="tr_color">
									<s:iterator value="tableTitle" id="title">
										<th class="tr_color" align="center">
											<s:property value="title" />
										<br/></th>
									</s:iterator>
								</tr>
								<!-- Add by LiFeng 修改 "清单查询全部清单时，某一清单查询不到，哪后面的清单都不能看" 的BUG -->
								<s:if test="resultRecords == null || resultRecords.size() == 0">
								<tr>
									<td colspan="99" class="list_mess" align="center">
										<%=errormessage %>
									<br /></td>
								</tr>
								</s:if>
						<s:else> 
						<!-- End -->
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
									<td colspan="<%=titleCols.intValue() %>" align="left">
										<% int index = 0; %>
										<s:iterator value="tableTail" id="tail">											
											<strong><s:property value="tail" /></strong><%=(String) totalMap.get(summaryTitle[index++]) %>
											&nbsp;&nbsp;										
										</s:iterator>
									</td>
								</tr>
								<s:if test="tableDescription != null && tableDescription.length > 0">
									<tr>
										<td colspan="<%=titleCols.intValue() %>" align="left">										
											<s:iterator value="tableDescription" id="desc">											
												<s:property value="desc" /><br/>					
											</s:iterator>										
										</td>
									</tr>
								</s:if>
								<!-- Add by LiFeng 修改 "清单查询全部清单时，某一清单查询不到，哪后面的清单都不能看" 的BUG -->
								</s:else>
								<!-- End -->
								
										<%--modify begin yKF28472 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
										<%
						                	if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province) 
						                			&& "true".equals(isQueryAll)
						                			&& "1".equals(printFlag1) && "1".equals(hasPrinter)) {
						                %>
						                
										<s:if test="resultRecords != null && resultRecords.size > 0">
       									<tr>
									<td align="right" colspan="99">
       										<input id="print" type="button" class="bt2_liebiao white"
							                    	  value="打印" onmousedown="" onmouseup="" onclick="printInfo()">
       									</td>
								</tr>	
										</s:if>
										<%
						                	}
						                %>
										<%--modify end yKF28472 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
										
															
							</table>
							<!-- Change by LiFeng 修改 "清单查询全部清单时，某一清单查询不到，哪后面的清单都不能看" 的BUG
						<%--</s:else> --%>
 -->
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="feeservice/qryDetailedRecords.action" 
								cdrFlag="true" isQueryAll="${isQueryAll}" listtype="${listtype}" />

								</div>								
								<!-- 列表内容 -->
							</div>							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
                <%--modify begin g00140516 2011/12/19 R003C11L12n01 有详单记录时，才显示发送按钮--%>
                <%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) {
                %>
                <s:if test="resultRecords != null && resultRecords.size > 0">
                	<div class="box120W fl ml10">
					 <p class="blank10"></p>
					 <a  class="btn_120_88" href="javascript:void(0)" onclick="qryMailbox()" ></a>
				    </div>
                </s:if>                
				<%
                	}
                %>
                <%--modify end g00140516 2011/12/19 R003C11L12n01 有详单记录时，才显示发送按钮--%>
                <%
                	if (!Constants.PROOPERORGID_HUB.equalsIgnoreCase(province)) {
                %>
       			<s:if test="resultRecords != null && resultRecords.size > 0">
				<%
				if ("1".equals(printFlag1) && "1".equals(hasPrinter)){
				%>
					<div class="box120W fl ml10" id="print">
					<p class="blank10"></p>
					<a  class="btn_120_82" href="javascript:void(0)" onclick="printInfo()" ></a>
				</div>
				<%
				}
				%>
				</s:if>
				<%
					}
				%>
				<%--modify end yKF28472 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
				<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) {
                %>
				<div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_48_sd" href="javascript:void(0)" onclick="openDirectory('directory');"></a>
				</div>
                <%
                	}
                %>
                <!--弹出发送邮件后的提示信息窗口 -->
                <div class="openwin_tip div708w392h" id="sendMailPrompt">
                    <div class="bg"></div>
                    <div class="blank60"></div>
                    <div class="blank10"></div>
                    <div class="blank10"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的账单信息稍后将发送至您的139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">请登录mail.10086.cn或使用手机访问</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn查询</p>
                    <div class="blank10"></div>
                    <div class="tc">
                    <div class="blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <!--弹出开通139邮箱确认窗口-->   
                <div class="openwin_tip div708w392h" id="addMailConfirm">
                    <div class="bg"></div>
                    <div class="blank60"></div><div class=" blank60"></div>
                    <div class="blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您尚未开通139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">点击确定将为您开通139邮箱免费版（免费）</p>
                    <div class="blank10"></div>
                    <div class="tc">
                    <div class="blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();addBillMail()">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>             
				
                <!--清单阅读指南 -->
                <div class="openwin_tip openwin_big div804w515h" id="directory">
                    <div class="bg loading tc"></div>
                    <div class="blank60"></div>
                    <div class="fl ring_info">
						<p class="fs16 yellow pt30 tc pt30 pl20"><%=additionalInfo == null ? "&nbsp;" : additionalInfo%></p>
					</div>
                    <div class="blank10"></div>
                    <div class="tc">
                    <div class="blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <script type="text/javascript">				
				openAddMailConfirm = function(id){
					wiWindow = new OpenWindow("addMailConfirm",708,392);//弹出开通139邮箱确认窗口		
				}
				
				openSendMailPrompt = function(id){
					wiWindow = new OpenWindow("sendMailPrompt",708,192);//弹出发送邮件后的提示信息窗口
				}
				
				openDirectory = function(id){
					wiWindow = new OpenWindow("directory",708,392);//弹出发送邮件后的提示信息窗口
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
				<%--add end cKF48754 2011/11/15 R003C11L11n01 OR_SD_201108_652--%>
		    </div>
				
			<%@ include file="/backinc.jsp"%>
		</form>
		<iframe id="processFrm" frameborder="0" scrolling="no" style="display: none" src=""></iframe>
	</body>
</html>
