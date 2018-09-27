<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
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
			// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
		}

		// 返回
		function goback(curmenu) 
		{
			//防止重复操作
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
				
				document.actionFrom.target = "_self";
				document.actionFrom.action = "${sessionScope.basePath }login/backForward.action";
				document.actionFrom.submit();
			}
		}
		
		// 提交
		function doSub()
		{			
			//防止重复操作
			if (submitFlag == 0) 
			{
				submitFlag = 1;

		  		document.actionFrom.target = "_self";
				document.actionFrom.action = "${sessionScope.basePath}chooseTel/inputSendNum.action";
				document.actionFrom.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actionFrom" method="post">
			<input type="hidden" name="telnum" id="telnum" value="<s:property value='telnum' />"/>
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
						<a href="javascript:void(0);">
							5.发送凭证到手机
						</a>
						<a href="javascript:void(0);" class="on">
							6.完成
						</a>
					</div>
					
					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='telnum' /></span></p>
      						<div class="blank10"></div>
        					<div class="line w625"></div>
       						<div class="blank20"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt25" style="text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;预订短信已发送到手机！请注意查收。</p>
          						<p class="desc pt20" style="text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请携带有效证件和身份证及时前往营业厅办理开户。</p>
          						<br>
          						<p class="fs18 ml20 lh30" style="text-align: left;">&nbsp;&nbsp;&nbsp;&nbsp;短信凭证或纸制凭证均可办理开户。</p>
          						<br>
          						
       						</div>
       						<div class="blank30 clear"></div>
       						<div class="blank30"></div>
				          	<div class="line w625"></div>
				            <div class="blank30"></div>
				            <p class="tit_arrow"><span class="bg"></span>温馨提示：</p>
							<table width="600" border="0">
								<tr>
									<td width="600" >
										 <p class="fs18 ml20 lh30"><s:property	escape="false" value="additionalInfo" /></p>
									</td>
								</tr>
							</table>
							<%--modify end cKF48754 2011/10/28 R003C11L10n01 增加温馨提示 --%>
				            </div>
      					</div>
      				</div>
				</div>
			</div>			
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
