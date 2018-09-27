<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="">
		<meta http-equiv="description" content="">
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />		
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
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
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
				return;				
			}
			<%	
			if ("1".equals(keyFlag))
			{
			%>
				<s:iterator value="certTypeList" id="po" status="st">
					if(key ==  <s:property value="#st.index + 49" />)
					{
						doSub('<s:property value="dictid"  escape="false"/>','<s:property value="dictname"  escape="false"/>');
					}
				</s:iterator>
			<%
			}
			%>			
		}

		// 提交
		function doSub(dictid,dictname) 
		{			
			//防止重复操作
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				document.getElementById('certtype').value = dictid;
				document.getElementById('certname').value = dictname;
				
				//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading"); 
				//add end m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
				
		  		document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/inputCertid.action";
				document.actform.submit();
			}
		}
		
		// 返回
		function goback(curmenu) 
		{
			//防止重复操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.getElementById("curMenuId").value = curmenu;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }login/backForward.action";
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />			
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
			<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
			<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
			
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />	
			<input type="hidden" id="org_id" name="org_id" value="<s:property value='org_id'/>" />
			
			<!-- 证件类型 -->
			<input type="hidden" id="certtype" name="certtype" value=""/>
			<!-- 证件名称 -->
			<input type="hidden" id="certname" name="certname" value=""/>
			
			
			<input type="hidden" id="name" name="name" value=""/>
			
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
						<a href="javascript:void(0);" class="on">
							4.输入个人信息
						</a>
						<a href="javascript:void(0);">
							5.完成
						</a>
					</div>
				</div>
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40">
						<p class=" tit_info"><span class="bg"></span>地市：&nbsp;&nbsp;&nbsp;&nbsp;<s:property value='regionname'/></p>
						<div class="blank5"></div>
						<p class=" tit_info">手机号码：<s:property value='telnum'/></p>
						<div class="blank10"></div>
						<div class="line"></div>
						<div class="blank10"></div>
						<p class="tit_arrow fs22"><span class="bg"></span>请选择证件类型：</p>
						<div class="blank20"></div>
        				
						
						<ul class="money_list_clearfix">
							<s:iterator value="certTypeList" id="po" status="st">
								<!-- 
								<li class="first_money_key tc">
								<a style="text-align:center;" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub('<s:property value="dictid" />','<s:property value="dictname" />'); return false;">
									<s:property value="dictname" />
								</a>
								</li>
								<li style="padding-top:15px"></li>	
								 -->
							<%	
							if ("1".equals(keyFlag))
							{
							%>
							<a href="javascript:doSub('<s:property value="dictid" />','<s:property value="dictname" />');" class="bt6 fr relative fl ml20"  onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';"><s:property value="dictname" />(按<s:property value='#st.index + 1' />键)</a>
							<%
							}
							else
							{
							%> 
							<a href="javascript:doSub('<s:property value="dictid" />','<s:property value="dictname" />');" class="bt6 fr relative fl ml20"  onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';"><s:property value="dictname" /></a>
							<%
							}
							%>	
							</s:iterator>
						</ul>
					</div>	
				</div>	
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
