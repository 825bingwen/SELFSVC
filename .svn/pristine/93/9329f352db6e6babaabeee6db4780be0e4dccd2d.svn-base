<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 终端特性
String termSpecial = terminalInfo.getTermspecial();

//add begin cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能
//省份
String province = (String) PublicCache.getInstance().getCachedData("ProvinceID");
//add end cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

//add begin cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果
// 页面渐进效果时间
String lateTime = "0.5";

if(Constants.PROOPERORGID_NX.equals(province))
{
	lateTime = "0.0";
}
//add end cKF76106 2011/11/17 R003C12L09n01 宁夏去掉页面渐进效果

String lockedFlag = (String) PublicCache.getInstance().getCachedData("SH_LOCKED_FLAG");

// add begin hWX5316476 2014-11-20 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
// 图片屏保开关（0：关闭  1：开启）
String imageSwitch = (String) PublicCache.getInstance().getCachedData("SH_IMAGESCREEN_SWITCH");
// add end hWX5316476 2014-11-20 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	
		<title>移动自助终端</title>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Expires" CONTENT="0"/>
		<meta http-equiv="Page-Exit"; content="blendTrans(Duration=<%=lateTime %>)"/> 
		<link href="${sessionScope.basePath}css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/newAdd.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/net.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/media/SelfMedia.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/initControl.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion}"></script>
		<script type="text/javascript">
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//更正
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//这句话最关键
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{
    		//只允许输入0-9
    		if (KeyCode >= 48 && KeyCode <= 57)
    		{
    			return true;
    		}
    		
    		return false;
		}	
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			<%--add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
			
			<%
			if ("1".equals(keyFlag))
			{
			%>
				<s:iterator value="centerMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
				
				<s:iterator value="topRightMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48 + centerMenus.size" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
				
				<s:iterator value="bottomRightMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48 + centerMenus.size + topRightMenus.size" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
			<%
			}
			%>
						   				
			<%--add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
		}
		
		//是否支持打印票据标志,0不支持,1:支持
		var isPrintFlag = <%=termSpecial.charAt(0)%>;
		
		//是否支持打印发票标志,0不支持,1:支持
		var isInvoicePrint = <%=termSpecial.charAt(1)%>;
		
		//是否支持加密键盘标志,0不支持,1:支持
		var isKeyBoard = <%=termSpecial.charAt(2)%>;
		
		//是否支持现金缴费标志,0不支持,1:支持
		var isCashEquip = <%=termSpecial.charAt(3)%>;
		
		//是否支持银行卡缴费标志,0不支持,1:支持
		var isUnionPay = <%=termSpecial.charAt(4)%>;
		  
		//是否支持读取二代身份证信息标志,0不支持,1:支持
		var is2GIDFlag = <%=termSpecial.charAt(8)%>;
		
		//是否支持银联扣款接口 0不支持,1:支持
		var isCardPay = <%=termSpecial.charAt(9) %>
		
		// 控件检查频率 
		var frequency = <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY) %>;
		
		//计时。首页面若一定时间内没有任何操作，则跳转至屏保页面
		var screenTime = 0;
		
		// js下载屏保文件

		// 服务器根路径
		var rootPath = '<%=request.getContextPath() %>';
		
		// 文件下载后保存到的本地路径
		var localURL = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';
		
		// 本地屏保播放文件列表(sc.wpl)
		var scWplFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCWPL_LIST) %>';
		
		// 本地广告播放文件列表(adv.wpl)
		var advWplFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>';
		
		// 本地屏保播放列表(scfile.xml)
		var scPLFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCPLAY_LIST) %>';
		
		// 本地广告播放列表(advfile.xml)
		var advPLFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVPLAY_LIST) %>';
		
		// 是否有屏保
		var hasScreen = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_IS_THERE_SCREENSAVERS) %>;
		
		// 首页跳至屏保的等待时间
		var goSCTime = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_GO_SCEEN_TIME) %>;
		
		// 系统页面刷新时间
		var timeRefresh = <%=(String) PublicCache.getInstance().getCachedData(Constants.PAGE_TIMEOUT) %>;

		// 控件初始化
		function initControl()
		{
			 if (frequency == 0){
			 
			    // 票据打印机初始化
			    indexInitListPrt();
			    
			    // 发票打印机初始化
			    indexInitInvoicePrint();
		    }
		}

		function go(menuid, url) 
		{
			document.getElementById("curMenuId").value = menuid;
			
			//modify begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
			openRecWaitLoading_NX("recWaitLoading"); 
			//modify end m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + url;
			document.actform.submit();
		}

		function dofocus()
		{
		    window.focus();
			
			//进行初始化工作			
			initControl();
		}

		// 后台数据库可以设置屏保启动时间默认60秒
		function screenAD(){
		    screenTime ++ ;
		    <%--modify begin g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149 --%>
		    <%
		   	 String termIP = (String)request.getSession().getAttribute("termIP");
		   	 String termMac = (String) request.getSession().getAttribute("termMac");
		    %>
		    
		    if(screenTime == goSCTime)
		    {
		    	// add begin by l00263786 20150616 OR_SD_201504_102 自助终端屏保实现全省统一配置
		    	// 判断是否存在屏保资源（包括描述文件和对应的资源文件）
		        if(!scResExist(localURL,scWplFileName,scPLFileName))
		        {
		        	screenTime = 0;
		        	return;
		        }
		    	// add end by l00263786 20150616 OR_SD_201504_102 自助终端屏保实现全省统一配置
		    	<%
		    	// modify begin hWX5316476 2014-11-20 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
				// 图片屏保播放开关开启
				if("1".equals(imageSwitch))
		    	{
		    	%>
		        	window.open("${sessionScope.basePath}jsp/SelfMedia/SelfScreen/selfScreenImages.jsp?termIP=<%=termIP %>&termMac=<%=termMac %>", "_self");
		    	<%
				}
				else
				{
				%>
		    		window.open("${sessionScope.basePath}jsp/SelfMedia/SelfScreen/selfScreen.jsp?termIP=<%=termIP %>&termMac=<%=termMac %>", "_self");
		    	<%
				}
				// modify end hWX5316476 2014-11-20 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
				%>
		    }
		    <%--modify end g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149 --%>
		}

		// 取消屏保
		function screenCancel(){
		    screenTime = 0;
		    
		    document.getElementById()
		}
		
		function f_setTimeout() 
		{
			<%
			if ("1".equals(lockedFlag))
			{
			%>
			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?lockTerm=lockTerm&timeoutFlag=1");
			<%
			}
			else
			{
			%>
			// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
	  		top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=1");
	  		// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
			<%
			}
			%>
		}

		function welcomePlay()
		{
			document.getElementById("player").play();
		}
		
		// 页面初始化
		function bodyLoad()
		{
			// add begin lWX5316086 宁夏 限制同一号码同一渠道多次登录
			<%
			if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
			{
			%>
				// 清除终端机登录信息表数据
				deleteLoginCheckByTermId();
			<%
			}
			%>  
			// add end lWX5316086 宁夏 限制同一号码同一渠道多次登录
			
			// 播放提示语音
			<%
			if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
					&& "1".equals((String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
			{
				String interval = (String) PublicCache.getInstance().getCachedData("SH_WELCOME_INTERVAL");
			%>
				setInterval("welcomePlay()", <%=interval %> * 1000);
			<%
			}
			%>   
			if(hasScreen == 1)
			{
				// modify begin hWX5316476 2014-12-01 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
				<%
				if (Constants.PROOPERORGID_SD.equals(province))
				{
				%>
			        // 这里从后台获取屏保文件列表,并下载到指定目录
					var isHaveSetScreen = getMediaList("media/getMediaScList.action", scPLFileName, '<%=Constants.SC_TYPE%>', scWplFileName);
					// 如果屏保文件下载成功，则打开屏保
					if(isMediaScDownloaded(isHaveSetScreen))
					{
						setInterval('screenAD()', 1000);
					}
					
			    <%
				}
				else
				{
				%>
					setInterval('screenAD()', 1000);
					getMediaList("media/getMediaScList.action", scPLFileName, '<%=Constants.SC_TYPE%>', scWplFileName);
				<%
				}
				%>
				// modify end hWX5316476 2014-12-01 OR_SD_201411_622_山东_关于自助终端管理平台支撑屏保信息联播的需求
			}
			else
			{
				setTimeout("f_setTimeout()", timeRefresh);	
			}
			
		}
		
		// add begin lWX5316086 宁夏 限制同一号码同一渠道多次登录
		function deleteLoginCheckByTermId()
		{
			var url = "${sessionScope.basePath }login/deleteLoginCheckByTermId.action";
		
			var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
						
			}, null, "POST", null, null);
			
		}
		// add end lWX5316086 宁夏 限制同一号码同一渠道多次登录

		
		// 下载屏保文件到本地
		function downMediaFile(strRemoteURL,strLocalURL) 
		{
		    //防止被杀毒软件杀掉
			<%
			    out.println("var xmlHTTP;");
			    out.println("if(window.ActiveXObject){");
			    out.println("xmlHTTP=new ActiveXObject( \"Microsoft.XMLHTTP\");");
			    out.println("}else if(window.XMLHttpRequest){");
			    out.println("xmlHTTP=new XMLHttpRequest();");
			    out.println("}");
			    out.println("xmlHTTP.open( \"Get\",strRemoteURL,false); ");
			    out.println("xmlHTTP.send();"); 
			    out.println("var adodbStream=new ActiveXObject( \"ADODB.Stream\");"); 
			    out.println("adodbStream.Type=1;");
			    out.println("adodbStream.Open();"); 
			    out.println("adodbStream.write(xmlHTTP.responseBody);"); 
			    out.println("adodbStream.SaveToFile(strLocalURL,2);"); 
			    out.println("adodbStream.Close();"); 
			    out.println("adodbStream=null;"); 
			    out.println("xmlHTTP=null;"); 
			%>
		}
		
		function goProvideGoods()
		{
			document.actform.target="_self";
			document.actform.action = "${sessionScope.basePath}managerOperation/selectOperationType.action";
			document.actform.submit();
		}
		
		function inputMgtPwd()
		{
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}rectelInfo/inputMgtPwd.action";
			document.actform.submit();	
		}	
		
		// 现金识币器状态检查定时器句柄
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
				// 清除定时任务
				clearInterval(checkCashToken);
				window.location.href = "${sessionScope.basePath}managerOperation/doCashAudit.action?from=index2";
			}
		}
		<%
			// modify begin zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
			// 现金稽核开关
			String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
		
			if ((Constants.PROOPERORGID_NX.equals(province) || "1".equals(cashAuditSwitch)) 
				&& termSpecial.charAt(3) == '1'){
		%>
				// 识币器状态检查间隔时间
				var checkInterval = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CASHSTATUS_CHECKINTERVAL) %>;
				checkCashToken = setInterval("doCashAuditFwd()", checkInterval);				
		<%
			}
			// modify end zKF69263 2015-4-1 OR_SD_201502_169_山东_自助终端实现现金稽核功能
		%>
		</script>
	</head>
	<body scroll="no" class="margin0" onclick="screenCancel()" onload="dofocus();bodyLoad();">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="curMenuId" name="curMenuId" value=""/>
			
			<%
			if (Constants.PROOPERORGID_HUB.equals(province))
			{
			%>
				<div class="header" style="height:90px; margin-top:32px">
			<%
			}
			else
			{
			%>
				<div class="header">
			<%
			}
			%>
				<%
				if (Constants.PROOPERORGID_NX.equals(province))
				{
				%>
			    	<div class="nx_new_logo"></div>
			    <%
				}
				else if (Constants.PROOPERORGID_SD.equals(province))
				{
				%>
					<div class="logo"></div>
				<%
				}
				else if (Constants.PROOPERORGID_HUB.equals(province))
				{
				%>
					<div class="logo" style="margin:24px 38px 0px 53px; _margin:24px 38px 0px 26px;"></div>
				<%
				}
				%>
			    <div class="tit"></div>
			    <div class="time">
			    	<%--modify begin cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能 --%>
			   	 	<%
					if (Constants.PROOPERORGID_SD.equals(province))
					{
					%>
					<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" alt="当前时间">
					<span id="_ShowTime"></span>
					<%
					}
					else if (Constants.PROOPERORGID_NX.equals(province))
					{
					%>
					<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" alt="当前时间" onclick="inputMgtPwd();">
					<span id="_ShowTime"></span>
					<%
					}
					else
					{	
					%>
			    	<span onclick="goProvideGoods();" style="width:40px; height:24px; background:#DBEEFA; float:left" title="现金稽核"></span>
			    	<div id="_ShowTime" style="display:none;"></div>
			    	<%
					}
					%>
					<%--modify end cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能 --%>
			    	
			    	<script type="text/javascript">startTime();</script>
			    </div>			    
			</div>
                  
			<div class="index_main">
				<s:if test="centerMenus != null && centerMenus.size > 0">
					<%
					if (Constants.PROOPERORGID_HUB.equals(province))
					{
					%>
						<div class="index_left">
				   		<s:iterator value="centerMenus" id="menu" status="st">
				   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   				<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='pic<s:property value="#st.index+1" /> bg' alt='<s:property value="#menu.menuname" />' />
				   			</a>
				   		</s:iterator>
				   		</div>
					<%
					}
					else
					{
					%>
						
						<div class="index_left">
							<%
							if (Constants.PROOPERORGID_NX.equals(province))
							{
								List centerMenus = (List)request.getAttribute("centerMenus");
								
							%>
								<%
								if(centerMenus.size()==2||centerMenus.size()==3||centerMenus.size()==4||centerMenus.size()==5) 
								{
								%>
									<div class="newAdd_main newAdd_menu<%=centerMenus.size() %>">
										<s:iterator value="centerMenus" id="menu" status="st">
											<!--
											<div onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" class="newAdd_navBody navBody<s:property value="#st.index" />"><span>（请按<s:property value="#st.index" />键）</span></div>
											<img class="newAdd_navBody_img navBody<s:property value="#st.index" />" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
											-->
											<a onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;"  class="navBody body<s:property value="#st.index + 1" />" href="javascript:void(0)">
												<img style="position:absolute; top:0px; left:0px;" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
												<span>（请按<s:property value="#st.index" />键）</span>
											</a>									 
										</s:iterator>
									</div>
								<%
								}
								else{
								 %>
								 	<div class="newAdd_main newAdd_menu8 %>">
										<s:iterator value="centerMenus" id="menu" status="st">
											<!--
											<div onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" class="newAdd_navBody navBody<s:property value="#st.index" />"><span>（请按<s:property value="#st.index" />键）</span></div>
											<img class="newAdd_navBody_img navBody<s:property value="#st.index" />" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
											-->
											<a onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;"  class="navBody body<s:property value="#st.index + 1" />" href="javascript:void(0)">
												<img style="position:absolute; top:0px; left:0px;" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
												<span>（请按<s:property value="#st.index" />键）</span>
											</a>									 
										</s:iterator>
									</div>
								<%
								} 
								%>
					   		<%
					   		}
					   		else
					   		{
					   		%>
								<s:iterator value="centerMenus" id="menu" status="st">
						   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
						   				<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='pic<s:property value="#st.index" /> bg' alt='<s:property value="#menu.menuname" />' />
						   				<%--add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
						   				<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span class="centerMenuTip<s:property value="#st.index" />">(请按<s:property value='#st.index + 1' />键)</span>
						   				<%
						   				}
						   				%>
						   				<%--add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
						   			</a>
						   		</s:iterator>
					   		<%
					   		}
					   		%>
					   	 </div>
					   	 <%
					   	 }
					   	 %>
						
				   	
			   	</s:if>
			   	
			   	<s:if test="rightMenus != null && rightMenus.size > 0">
			   		<div class="index_right">
			   			<s:iterator value="rightMenus" id="menu" status="st">
				   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
		   						<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='index_right bt<s:property value="#st.index+1" />' alt='<s:property value="#menu.menuname" />' />
		   						<%
				   				if ("1".equals(keyFlag))
				   				{
				   				%>
				   					<span style="font-size:16px; margin-left: 60px;" >(请按<s:property value='#st.index + centerMenus.size + 1'/>键)</span>
				   				<%
				   				}
				   				%>
		   					</a>
	   					</s:iterator>
			   		</div>
			   	</s:if>
			   	
			   	<s:elseif test="(topRightMenus != null && topRightMenus.size > 0) || (bottomRightMenus != null && bottomRightMenus.size > 0)">
				   	<%
					if (Constants.PROOPERORGID_NX.equals(province))
					{
					%>
					<div class="index_right_nx">
					<%
					}
					else
					{
					%>
					<div class="index_right">
					<%
					}
					%>			   	
				   		<s:if test="topRightMenus != null && topRightMenus.size > 0">
				   			<s:iterator value="topRightMenus" id="menu" status="st">
				   				<s:if test="#st.index == 0">
				   					<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   						<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt11' alt='<s:property value="#menu.menuname" />' />
				   						<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span style="font-size:16px; margin-left: 60px;" >(请按<s:property value='#st.index + centerMenus.size + 1'/>键)</span>
						   				<%
						   				}
						   				%>
				   					</a>
				   				</s:if>
					   			<s:else>
					   				<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
					   					<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt21' alt='<s:property value="#menu.menuname" />' />
					   					<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span style="font-size:16px; margin-left: 60px;" >(请按<s:property value='#st.index + centerMenus.size + 1'/>键)</span>
						   				<%
						   				}
						   				%>
					   				</a>
					   			</s:else>
					   		</s:iterator>
				   		</s:if>
				   		<s:if test="bottomRightMenus != null && bottomRightMenus.size > 0">
				   			<s:iterator value="bottomRightMenus" id="menu" status="st">
				   				<s:if test="#st.index == 0">
				   					<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   						<s:if test="topRightMenus == null || topRightMenus.size == 0">
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt3111' alt='<s:property value="#menu.menuname" />' />
				   						</s:if>
				   						<s:elseif test="topRightMenus.size == 1">
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt311' alt='<s:property value="#menu.menuname" />' />
				   						</s:elseif>
				   						<s:else>
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt31' alt='<s:property value="#menu.menuname" />' />
				   						</s:else>
				   						
				   						<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<%--modify begin g00140516 2013/02/16 R003C13L02n01 样式与页面分离 --%>
						   					<span class="fs16 ml60">(请按<s:property value='#st.index + centerMenus.size + topRightMenus.size'/>键)</span>
						   					<%--modify end g00140516 2013/02/16 R003C13L02n01 样式与页面分离 --%>
						   				<%
						   				}
						   				%>
				   					</a>
				   				</s:if>
					   			<s:else>
					   				<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
					   					<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt41' alt='<s:property value="#menu.menuname" />' />
					   					
										<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<%--modify begin g00140516 2013/02/16 R003C13L02n01 样式与页面分离 --%>
						   					<span class="fs16 ml60">(请按<s:property value='#st.index + centerMenus.size + topRightMenus.size'/>键)</span>
						   					<%--modify end g00140516 2013/02/16 R003C13L02n01 样式与页面分离 --%>
						   				<%
						   				}
						   				%>
						   			</a>
					   			</s:else>
					   		</s:iterator>
				   		</s:if>					   		
				   	</div>
			   	</s:elseif>   
	  		</div>
  		</form>
  		<%
  		// 尊敬的用户，欢迎您使用中国移动自助服务终端，请点击屏幕上您需要的服务，谢谢！
		if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
				&& "1".equals((String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
		{
		%>
  			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-01.wav" id="player" align="center" border="0" style="height:0px;width:0px;">
  		<%
  		}
  		%>
  		<%-- modify begin m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 --%>
  		<%-- modify begin hWX5316476 2015-6-24 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
  		<!--弹出正在处理div-->
		<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
			<div class="bg"></div>
    		<p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
   			<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %></p>
   		</div>
   		<%-- modify end hWX5316476 2015-6-24 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
   		
   		<script type="text/javascript">
   		// 弹出正在处理DIV(宁夏)
		openRecWaitLoading_NX = function(id)
		{
		<%
		if(Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
		{
		%>
			wiWindow = new OpenWindow("recWaitLoading", 804, 515);
		<%
		}
		%>
		} 
    	</script>
    	<%-- modify end m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 --%> 
	</body>
</html>
