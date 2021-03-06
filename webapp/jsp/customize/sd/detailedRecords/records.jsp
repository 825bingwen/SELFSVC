<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_huawei_201112_953
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
	String printNum = (String)PublicCache.getInstance().getCachedData(Constants.SH_CDR_PRTTIMES); 
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
		
			//modify begin sWX219697 2014-6-3 OR_huawei_201405_877 详单二次打印密码
			//用户详单打印次数超出限制，弹出确认提示框，需要营业员输入详单打印密码 测试使用
       		if ("1" != "<s:property value='printFlag' />")
       		{
       			//openPrintDetailConfirm("printDetailConfirm");
       			alertError("打印次数超出系统限制！");
       			return;
       		}
 			//modify begin sWX219697 2014-6-3 OR_huawei_201405_877 详单二次打印密码
 			
 			var printTime = getCurrentTime();
  			doPrint_SDOld('1', "<s:property value='cdrType' />", '${sessionScope.CustomerSimpInfo.servNumber }', '<s:property value="startDate" />', 
  					'<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', printTime, '${sessionScope.basePath}', 
  					'<s:property value="curMenuId" />', "<s:property value='iscycle' />", "<s:property value='cycle' />", "<s:property value='month' />");
		}
		
		//add begin sWX219697 2014-6-3 OR_huawei_201405_877 详单二次打印密码
		//详单补打密码页面跳转
		function detailPrintPwd()
		{    	
			//document.getElementById("curMenuId").value = "<s:property value='curMenuId' />";
	        document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}cdrquery/detailPrintPwd.action";
			document.actform.submit();
		}
		//add end sWX219697 2014-6-3 OR_huawei_201405_877 详单二次打印密码
		
		function keyDown(e) 
		{
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
   
		// 判断是否开通139邮箱，若开通发送详单到邮箱，没开通提示开通
		function qryMailbox()
		{
			var url = "${sessionScope.basePath }cdrquery/qryMailbox.action";
			
			var params = "cdrType=<s:property value='cdrType' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />&curMenuId=<s:property value='curMenuId' />&verFlag=0&iscycle=<s:property value='iscycle' />&cycle=<s:property value='cycle' />&month=<s:property value='month' />"; 
			
			var loader = new net.ContentLoader(url, callqryMailboxBack, null, "POST", params, "application/x-www-form-urlencoded");    		
		}   
  
		//<!--回调的方法-->   
		function callqryMailboxBack()
		{		       
		    var retStr = this.req.responseText;
		    
		    // 用户未开通139邮箱，弹出开通139邮箱（免费提示窗口）
		   	if (retStr == 0)
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
		
		// 开通139邮箱并发送详单到邮箱   
		function addBillMail()
		{		
			var url = "${sessionScope.basePath }cdrquery/add139Mail.action";
			
			var params = "cdrType=<s:property value='cdrType' />&startDate=<s:property value='startDate' />&endDate=<s:property value='endDate' />&curMenuId=<s:property value='curMenuId' />&verFlag=0&iscycle=<s:property value='iscycle' />&cycle=<s:property value='cycle' />&month=<s:property value='month' />"; 
			
			var loader = new net.ContentLoader(url, callBack, null, "POST", params, "application/x-www-form-urlencoded");
		}   
  
		//<!--回调的方法-->   
		function callBack()
		{
		    var retStr = this.req.responseText;   
		    // 开通成功提示窗口
		    if(retStr == 1)
		    {
				//modify begin sWX219697 2014-5-14 09:55:22 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 
		        <%String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH); 
		          if("1".equals(sendRecords))
		          {%>
				      openSendNewMailPrompt('sendNewMailPrompt');
				<%}else
				  {%>
				      openSendMailPrompt('sendMailPrompt');
				<%}%>
				//modify end sWX219697 2014-5-14 09:55:22 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 
		   
		    }
		    // 开通失败
		    if(retStr == 0)
		    {
				alertError("139邮箱开通失败！");
		    }
		}
		
		window.onload = function() 
		{
			if("<s:property value='detailPwd' />" !="" )
			{
				var printTime = getCurrentTime();
	  			doPrint_SDOld('1', "<s:property value='cdrType' />", '${sessionScope.CustomerSimpInfo.servNumber }', '<s:property value="startDate" />', 
  					'<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', printTime, '${sessionScope.basePath}', 
  					'<s:property value="curMenuId" />', "<s:property value='iscycle' />", "<s:property value='cycle' />", "<s:property value='month' />");
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />">
			<input type="hidden" name="iscycle" value="<s:property value='iscycle' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="month" value="<s:property value='month' />"/>
			
			<%-- add begin sWX219697 2014-6-3 OR_huawei_201405_877--%>
			<input type="hidden" name="chQueryDate" value="<s:property value="chQueryDate" escape="false"/>" />
			<input type="hidden" name="chStartDate" value="<s:property value="chStartDate" escape="false"/>" />
			<input type="hidden" name="chEndDate" value="<s:property value="chEndDate" escape="false"/>" />
			<%-- add end sWX219697 2014-6-3 OR_huawei_201405_877--%>
			<%@ include file="/titleinc.jsp"%>
				
				<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

        		<!--滚动条-->
        		<!-- modify begin cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
        		<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
        		<!-- modify end cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
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
                <s:if test="resultRecords != null && resultRecords.size > 0">
                	<div class="box120W fl ml10">
					 <p class="blank10"></p>
					 <a  class="btn_120_88" href="javascript:void(0)" onclick="qryMailbox()" ></a>
				    </div>
                </s:if>
                
                <s:if test="resultRecords != null && resultRecords.size() > 0 && 1 == printFlag">
	                <div class="box120W fl ml10" id="print">
						<p class="blank10"></p>
						<a class="btn_120_82" href="javascript:void(0)" onclick="printInfo()" ></a>
					</div>
				</s:if>
             	
				<div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_48_sd" href="javascript:void(0)" onclick="openDirectory('directory');"></a>
				</div>
				
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
                
                <!--弹出新开通邮箱并发送邮件的提示信息窗口，提示用户重置邮箱密码-->
	                <!--add begin sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 -->
	                <div class="openwin_tip div708w392h" id="sendNewMailPrompt">
	                    <div class="bg"></div>
	                    <div class="blank60"></div>
	                    <div class="blank10"></div>
	                    <div class="blank10"></div>
	                    <p class="fs30 yellow pt30 tc pt30 pl20">您的账单信息稍后将发送至您的139邮箱</p>
	                   <!--<p class="fs30 yellow pt30 tc pt30 pl20">请登录mail.10086.cn或使用手机访问</p>
	                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn查询</p>-->
	                    <p class="fs30 yellow pt30 tc pt30 pl20">为保障您的信息安全，请妥善保存您的139邮箱</p>
	                    <p class="fs30 yellow pt30 tc pt30 pl20">密码，重置密码可发送“MM”至10658139</p>
	                    <div class="blank10"></div>
	                    <div class="tc">
	                    <div class="blank60"></div>
	                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
	                    </div>
	                </div>
	                <!--add end sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 -->
                
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
				
					<!--发票二次打印弹出框-->   
	                <%-- add begin sWX219697 2014-6-3 OR_huawei_201405_877--%>
	                <div class="openwin_tip div708w392h" id="printDetailConfirm">
	                    <div class="bg"></div>
	                    <div class="blank60"></div><div class=" blank60"></div>
	                    <div class="blank10n"></div>
	                    <p class="fs30 yellow pt30 tc pt30 pl20">用户已超出<%=printNum%>次限制</p>
	                    <p class="fs30 yellow pt30 tc pt30 pl20">如需再次打印请联系营业厅管理员</p>
	                    <div class="blank10"></div>
	                    <div class="tc">
	                    <div class="blank60"></div>
	                    <a href="javascript:detailPrintPwd()" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
	                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
	                    </div>
	                </div>
					<%-- add end sWX219697 2014-6-3 OR_huawei_201405_877--%>
				
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
				
									//新开通邮箱并发送邮件后提示其重置密码
					//add begin sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
					openSendNewMailPrompt = function(id){
						wiWindow = new OpenWindow("sendNewMailPrompt",708,192);
					}
					//add end sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
				
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
					  
					//add begin sWX219697 2014-6-3 OR_huawei_201405_877	  
				   openPrintDetailConfirm = function(id)
				   {
				   		wiWindow = new OpenWindow("printDetailConfirm",708,392);
				   }
				   //add end sWX219697 2014-6-3 OR_huawei_201405_877	
	
				</script>
                <!--弹出窗结束-->
		    </div>
				
			<%@ include file="/backinc.jsp"%>
		</form>
		<iframe id="processFrm" frameborder="0" scrolling="no" style="display: none" src=""></iframe>
	</body>
</html>
