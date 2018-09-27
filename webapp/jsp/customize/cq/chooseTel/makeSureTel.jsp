<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/selfInstallPrt.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;	//提交标志，0表示未曾提交，1表示已经提交
		
		//82、220 返回		
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
			
			//返回
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}			
		}

		function doFinish()
		{
			var cityName = "<%=terminalInfoPO.getOrgname() %>";  //打印地点
			var pPrintDate = getDateTime();  //打印日期
			var pTerminalInfo = "<%=terminalInfoPO.getTermid() %>"; //终端信息
			var chooseTelNum = "<s:property value='telnum' />";   //选定号码
			var orderID = "<s:property value='orderID' />";
			
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(provinceID))
			{
			%>
				doPrintTheTel_NX(chooseTelNum, orderID, pTerminalInfo, cityName, pPrintDate);	
			<%
			}
			%>			
		}

		// 返回
		function goback(curmenu) 
		{
			//防止重复操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actionFrom.target = "_self";
				document.actionFrom.action = "${sessionScope.basePath }login/backForward.action";
				document.actionFrom.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="actionFrom" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);">
							1.选择查询方式
						</a>
						<a href="javascript:void(0);">
							2.输入查询条件
						</a>
						<a href="javascript:void(0);">
							3.选择号码
						</a>
						<a href="javascript:void(0);">
							4.输入个人信息
						</a>
						<a href="javascript:void(0);" class="on">
							5.完成
						</a>
					</div>
					
					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='telnum' /></span></p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">预订成功！</p>
          						<p class="desc pt20">请携带预订凭证和相关证件及时前往营业厅办理开户。</p>          						
       						</div>
      					</div>
      				</div>
				</div>
			</div>			
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
