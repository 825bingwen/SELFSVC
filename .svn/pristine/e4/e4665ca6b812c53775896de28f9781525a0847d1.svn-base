<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%
TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// 终端特性
String termSpecial = terminalInfo.getTermspecial();

//add begin cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能
//省份
String province = (String) PublicCache.getInstance().getCachedData("ProvinceID");
//add end cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	
		<title>移动自助终端</title>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<meta http-equiv="Page-Exit"; content="blendTrans(Duration=0.5)"> 
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/net.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/media/SelfMedia.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/initControl.js"></script>
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
			
			
			<s:iterator value="centerMenus" id="menu" status="st">	
				if (key == <s:property value="#st.index + 49" />)
				{
					go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
				}			
			</s:iterator>			
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
		        window.open("${sessionScope.basePath}/jsp/SelfMedia/SelfScreen/selfScreen.jsp?termIP=<%=termIP %>&termMac=<%=termMac %>", "_self");
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
	  		// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
	  		top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=1");
	  		// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
		}

		// 页面初始化
		function bodyLoad()
		{		    
			if(hasScreen == 1)
			{
				setInterval('screenAD()', 1000);
				getMediaList("media/getMediaScList.action", scPLFileName, '<%=Constants.SC_TYPE%>', scWplFileName);
			}
			else
			{
				setTimeout("f_setTimeout()", timeRefresh);	
			}
		}
		
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
		</script>
	</head>
	<body scroll="no" class="margin0" onclick="screenCancel()" onload="dofocus();bodyLoad();">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="curMenuId" name="curMenuId" value="">
			
			<div class="header">
			    <%
				if (!Constants.PROOPERORGID_SD.equals(province))
				{
				%>
			    	<div class="nx_new_logo"></div>
			    <%
				}
				else
				{
				%>
					<div class="logo"></div>
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
					<%
					}
					else
					{	
					%>
			    	<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" onclick="goProvideGoods();" alt="当前时间">
			    	<%
					} 
					%>
					<%--modify end cKF48754 2011/11/10 R003C11L11n01 屏蔽山东前台现金稽核功能 --%>
			    	<span id="_ShowTime"></span>
			    	<script type="text/javascript">startTime();</script>
			    </div>			    
			</div>
			  
			<div class="index_main">
				<s:if test="centerMenus != null && centerMenus.size > 0">
					<p class="fs20 pt20 pl160 yellow">本终端不支持触摸屏方式操作，请根据页面提示，通过金属键盘进行操作。</p>
				   	<div class="index_left">
				   		<s:iterator value="centerMenus" id="menu" status="st">		
				   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   				<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='pic<s:property value="#st.index" /> bg' alt='<s:property value="#menu.menuname" />' />
				   				<span class="menuDesp3">(请按<s:property value='#st.index + 1' />键)</span>
				   			</a>						   				   		
				  		</s:iterator>
				   	</div>
			   	</s:if>
			   
	  		</div>
  		</form>
	</body>
	
</html>
