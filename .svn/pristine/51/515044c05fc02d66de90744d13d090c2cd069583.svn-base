<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="java.util.*"%>

<%
	//当前页面跳至首页的等待时间
    String timeRefresh = (String) PublicCache.getInstance().getCachedData(Constants.PAGE_TIMEOUT);
    
    // 湖北自助终端倒计时功能的开关 0: 关闭，1: 开启
    String countDownFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_SWITCH_COUNTDOWN);
    
    // 湖北自助终端倒计时功能的开始时间
    String countDownLength = (String) PublicCache.getInstance().getCachedData(Constants.SH_COUNTDOWN_LENGTH);
    
    String parentMenuID = "";
    String parentMenuName = "";
    String parentMenuURL = "";
    
	String curMenuId = (String) request.getAttribute("curMenuId");
	if (curMenuId == null)
	{
		curMenuId = request.getParameter("curMenuId");
		
		if (curMenuId == null)
		{
			curMenuId = "";
		}
	}
	String ancestorMenuID = CommonUtil.getAncestorMenuInfo(request,curMenuId);
	
	String menuURL = "";
	
	String menuName = "";
	
	//add begin l00190940 R003C11L10n01 增加温馨提示		
	String additionalInfo = "";
    //add end l00190940 R003C11L10n01 增加温馨提示
    
    //add begin g00140516 2011/10/29 R003C11L10n01 增加业务介绍
    String busiDetailPage = "";
    //add end g00140516 2011/10/29 R003C11L10n01 增加业务介绍
    
    //省份
	String proID = (String) PublicCache.getInstance().getCachedData("ProvinceID");
	
	//宁夏现金缴费投币页面标志 
	String isCashCharge = (String) request.getAttribute("isCashCharge");
	
	//add begin cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
	// 页面渐进效果时间
	String lateTime = "0.5";
	
	if(Constants.PROOPERORGID_NX.equals(proID))
	{
		lateTime = "0.0";
	}
	//add end cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
	
	// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	String backWaitingFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_BACKWAITING_FLAG);
	if (backWaitingFlag == null)
	{
		backWaitingFlag = "";
	}
	// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	
	// 必输文本框红星标识开关
    String redStarKey = (String) PublicCache.getInstance().getCachedData("SH_REDSTAR_KEY");
    
    // 自选套餐标志(自选套餐接口调用多，页面不超时)
    String telProdFlag = (String) request.getAttribute("telProdFlag");
    
    // 湖北倒计时功能提示信息
    String countDownTips = (String) PublicCache.getInstance().getCachedData("SH_COUNTDOWN_INFO");
%>
<meta http-equiv="Page-Exit"; content="blendTrans(Duration=<%=lateTime %>)"> 

<input type="hidden" id="curMenuId" name="curMenuId" value="<%=curMenuId %>">

<%--add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125 --%>
<input type="hidden" id="backWaitingFlag" name="backWaitingFlag" value="<%=backWaitingFlag %>">
<%--add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125 --%>

<div class="header" id="header">

	<%-- modify by sWX219697 2015-1-21 17:36:37 湖北自助终端改版--%>
	<%
	if (Constants.PROOPERORGID_NX.equals(proID))
	{
	%>
		<div class="nx_new_logo" onclick='javascript:f_setTimeout(0);'></div>
	<%
	}
	else
	{
	%>
		<div class="logo" onclick='javascript:f_setTimeout(0);'></div>
	<%
	}  
	    TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	    //modify begin g00140516 2012/01/21 R003C12L01n01 Bug 19330
	    List titleTotalMenus = (List) PublicCache.getInstance().getCachedData(terminalInfo.getTermgrpid());
	    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
	    {
	    	MenuInfoPO menu = null;
	    	
	    	// 因页面空间有限，头部最多只能显示7个菜单
	    	int menuCount = 0;
	    	for (int i = 0; i < titleTotalMenus.size(); i++)
	    	{
	    		menu = (MenuInfoPO) titleTotalMenus.get(i);
	    		
	    		 if ("root".equalsIgnoreCase(menu.getParentid()) 
	    		 			&& menuCount < 7
	    		 			&& null != menu.getImgpath2()
	                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
	                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))) 
	            {
	            	menuCount++;
	%>
				<a id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="switchLevelOneMenu('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;" title='<%=menu.getMenuname() %>'>
					<p>
						<img src='${sessionScope.basePath}<%=menu.getImgpath2() %>' alt='<%=menu.getMenuname() %>' />
					</p>
				</a>
				
				<%-- <a id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="switchLevelOneMenu('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;" title='<%=menu.getMenuname() %>'>
					<img class="width110 height67" src='${sessionScope.basePath}<%=menu.getImgpath2() %>' alt='<%=menu.getMenuname() %>' />
				</a> --%>
	<%
	            }
	            
	            if (curMenuId.equalsIgnoreCase(menu.getMenuid()))
	            {
	            	parentMenuID = menu.getParentid();
	            	menuURL = menu.getGuiobj();
	            	menuName = menu.getMenuname();
	            	//add begin l00190940 R003C11L10n01 增加温馨提示
	            	additionalInfo = menu.getAdditionalInfo();
	            	//add end l00190940 R003C11L10n01 增加温馨提示
	            	
	            	//add begin g00140516 2011/10/29 R003C11L10n01 增加业务介绍
	            	busiDetailPage = menu.getBusidetail();
	            	//add end g00140516 2011/10/29 R003C11L10n01 增加业务介绍
	            }
	    	}
	    	
	    	parentMenuURL = CommonUtil.getMenuLink(parentMenuID);
    		parentMenuName = CommonUtil.getMenuName(parentMenuID);
	    }
	    //modify end g00140516 2012/01/21 R003C12L01n01 Bug 19330
	%>
</div>
<input type="hidden" id="parentMenuID" name="parentMenuID" value="<%=parentMenuID %>">
<script	type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>

<script type="text/javascript">
	// 0:可以使用顶部菜单
	// 1:不可使用顶部菜单
	var closeStatus = 0;
	
	// 超时返回首页定时器句柄
	var goHomeToken;
	
	// 记录当前时间
	var occurtime;
	
	// 现金稽核相关页面不能切换一级菜单（宁夏）
	<%
		if (Constants.PROOPERORGID_NX.equals(proID) && "1".equals(request.getAttribute("checkCashStatus")) ){
	%>
			closeStatus = 1;				
	<%
		}
	%>

	// 指定多少毫秒无动作将跳转
	var timeout_goHome = <%=timeRefresh %>;
	
	// 记录当前时间
	occurtime=new Date().getTime();
	  
	// 更新最新动作时间
	document.onmousemove=function(){       
	  	occurtime=new Date().getTime();
	}
	
	// 也可以用窗口失去焦点来更新时间
	window.onblur=function(){
	  	occurtime=new Date().getTime();
	}
 		
	function f_setTimeout(flag) 
	{
		if (closeStatus == 1)// 顶部菜单不可用
		{
			return;
		}
		else if (closeStatus == 2)// 关闭现金识币器
		{
			try
			{
				window.parent.document.getElementById("cashpluginid").CloseCashBill();
			}
			catch(e){}
		}
		else if (closeStatus == 3)// 关闭身份证读卡器
		{
			try
			{
				window.parent.document.getElementById("idcardpluginid").CloseCardReader();
			}
			catch(e){}
		}
  		
  		// modify begin wWX217192 2015-02-27 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
  		if(0 == flag)
  		{
  			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=" + flag);
  		}
  		
  		<%
		if (Constants.PROOPERORGID_HUB.equals(proID) && "1".equals(countDownFlag))
		{
		%>
			var countdownDiv = document.getElementById("countdownDiv");
					
			countdownDiv.innerHTML = '';
			
			var residualtime = parseInt(new Date().getTime() - occurtime);
			
			var leftTime = timeout_goHome - residualtime;
			
			if("1" == <%=countDownFlag %> && leftTime <= <%=countDownLength %>)
			{
				var message = '<%=countDownTips %>';
				
				if('' != message && 'null' != message)
				{
					message = message.replace('**', parseInt(leftTime/1000));
				}
				else
				{
					message = "尊敬的客户，您好！您所浏览的页面还有<font style='font-weight: bold;' color='red' size='5'>" + parseInt(leftTime/1000) + "</font>秒即将退出，请您进入下一环节！";
				}
				countdownDiv.innerHTML = message;
			}
			// modify end wWX217192 2015-02-27 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
					
			if (residualtime > timeout_goHome)
			{
		  		// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
		  		top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=" + flag);
				// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
			}
		<%
		}
		else
		{
		%>
			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=" + flag);
		<%
		}
		%>
	}

	// 超时返回首页
	<%
		if ((Constants.PROOPERORGID_NX.equals(proID) && !"1".equals((String)request.getAttribute("checkCashStatus"))
				&& !"1".equals(isCashCharge))
				|| (Constants.PROOPERORGID_SD.equals(proID) && !"1".equals((String) request.getAttribute("sdCashFlag"))))
	  {
	%>
			// 指定多少毫秒无动作将跳转
			var timeout_goHome = <%=timeRefresh %>;
			
			// 记录当前时间
			occurtime=new Date().getTime() ;
			  
			// 更新最新动作时间
			document.onmousemove=function(){       
			  	occurtime=new Date().getTime();
			}
			
			// 也可以用窗口失去焦点来更新时间
			window.onblur=function(){
			  	occurtime=new Date().getTime() ;
			}
			
			// 到时间后返回首页
			function f_setTimeout_goHome(flag) 
			{
				var residualtime = parseInt(new Date().getTime() - occurtime) ;
				
				if (residualtime > timeout_goHome)
				{
		  			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?lockTerm=lockTerm&timeoutFlag=" + flag);
		  		}
		  		
			}
			
			//返回首页面
			goHomeToken = setInterval("f_setTimeout_goHome(1)", 1000);
	<%
		}
	%>	

	//切换一级菜单
	function switchLevelOneMenu(menuid, url)
	{
		// 1:顶部菜单不可用 2:关闲识币器 3:关闭身份证读卡器
		if (closeStatus == 1)// 顶部菜单不可用
		{
			//alertError("业务办理中...");
			return;
		}
		else if (closeStatus == 2)// 关闭现金识币器
		{
			try{
				window.parent.document.getElementById("cashpluginid").CloseCashBill();
			}catch(e){}
		}
		else if (closeStatus == 3)// 关闭身份证读卡器
		{
			try{
				window.parent.document.getElementById("idcardpluginid").CloseCardReader();
			}catch(e){}
		}
		
		// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
		//清除session数据
		var url1 = "${sessionScope.basePath }login/clearSession.action?timeoutFlag=0";
		// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
		
		//modify begin yKF28472 2011/11/23 R003C11L11n01
		var loader = new net.ContentLoaderSynchro(url1, null, null, "POST", null, null);
		//modify end yKF28472 2011/11/23 R003C11L11n01
		
		//切换菜单
		topGo(menuid, url, true);
	}
	
	function topGo(menuid, url, ignoreFlag) 
	{
		// add begin cKF76106 2013/04/16  R003C13L04n01 OR_HUB_201303_548
		<%
			if (Constants.PROOPERORGID_HUB.equals(proID))
			{
				if(null != session.getAttribute("CDRQRY_TELNUM"))
			    {
			    	session.removeAttribute("CDRQRY_TELNUM");
			    }
		%>
			   
			    if('qryMuster' == menuid)
			    {				    
				    <%
					    NserCustomerSimp customerInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
					    
					    if(customerInfor != null && customerInfor.getServNumber() != null )
					    {
					    	session.setAttribute("CDRQRY_TELNUM", customerInfor.getServNumber());
					    }
				    %>
				    //清除session数据
					var url1 = "${sessionScope.basePath }login/clearSession.action?timeoutFlag=0";
			
					var loader = new net.ContentLoaderSynchro(url1, null, null, "POST", null, null);
			    }
		<%
			}
		%>
		// add end cKF76106 2013/04/16  R003C13L04n01 OR_HUB_201303_548
		
		if (!ignoreFlag)
		{
			openRecWaitLoading_NX();
		}
		
		document.getElementById("curMenuId").value = menuid;
			
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath}" + url;
		document.forms[0].submit();
	}
		
	function topLoad()
	{
		<%
			if (ancestorMenuID != null && !"".equals(ancestorMenuID.trim()))
			{
		%>
			navSign = new MyMenu();
			if (document.getElementById('nav_<%=ancestorMenuID %>') != null)
			{
				navSign.activate('nav_<%=ancestorMenuID %>');
			}
		<%
			}
		%>				
	}
	
	<%
	if (Constants.PROOPERORGID_HUB.equals(proID) && !"1".equals(telProdFlag))
	{
	
		if("1".equals(countDownFlag))
		{
	%>
			// modify begin wWX217192 2015-02-27 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
			//返回首页面
			
			timeOut = setInterval("f_setTimeout(1)", 1000);
	<% 
		}
		else
		{
	%>
			timeOut = setInterval("f_setTimeout(1)", <%=timeRefresh %>);
	<%
		}
	}
	%>
	// modify end wWX217192 2015-02-27 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求
	
	topLoad();
	
	//现金识币器状态检查定时器句柄
	var checkCashToken;	 
	
	// 检测现金识币器状态，如果为3-钱箱打开，则转向现金稽核页面（宁夏）
	function doCashAuditFwd()
	{
		var cashStatus = "";
			
		try
		{
			// 调用识币器状态检测接口，取识币器状态
			cashStatus = checkCashStatus();
		}
		catch (e) 
		{
			clearInterval(checkCashToken);
			alert("现金识币器出现异常,检查识币器状态失败");
			return;
		}
	
		if(cashStatus == 3)
		{
			clearInterval(checkCashToken);
			window.location.href = "${sessionScope.basePath}managerOperation/doCashAudit.action?from=titleinc";
		}
	}
	// 非现金稽核页面，定时检测现金识币器（宁夏）
	<%
		if (Constants.PROOPERORGID_NX.equals(proID) && !"1".equals((String)request.getAttribute("checkCashStatus")) && terminalInfo.getTermspecial().charAt(3) == '1' && !"1".equals(isCashCharge)){
	%>
			// 系统页面刷新时间
			var checkInterval = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CASHSTATUS_CHECKINTERVAL) %>;
			checkCashToken = setInterval("doCashAuditFwd()", checkInterval);				
	<%
		}
	%>
//-->
	
	// 返回首页重新计时,用于帐详单查询KeyUP事件，滚动条滚动事件
	function resetGoHome()
	{
		clearInterval(goHomeToken);	

		// 记录当前时间
		occurtime=new Date().getTime() ;
				
		goHomeToken = setInterval("f_setTimeout_goHome(1)", 1000);
	}
</script>