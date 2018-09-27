<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

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
				goback("<s:property value='curMenuId' />");
			}			
		}

		// 提交
		function doSub(sele_rule) 
		{			
			//防止重复操作
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				document.actform.sele_rule.value = sele_rule;
				
		  		document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/inputTelNumByRule.action";
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
				
				document.actform.target = "_self";
				//modify begin l00190940 2011-12-5 返回上一页报错
				//document.actform.action = "${sessionScope.basePath }chooseTel/selectRegion.action";
				document.actform.action = "${sessionScope.basePath }login/backForward.action";
				//modify end l00190940 2011-12-5 返回上一页报错
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value=""/>
			<input type="hidden" id="webserviceFlag" name="webserviceFlag" value="<s:property value='webserviceFlag' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);" class="on">
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
							5.完成
						</a>
					</div>
				</div>
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40">
						<p class=" tit_info"><span class="bg"></span>地市：<s:property value='regionname'/></p>
						<div class="blank10"></div>
						<div class="line"></div>
						<div class="blank10"></div>
						<p class="tit_arrow fs22"><span class="bg"></span>请选择号码查询方式：</p>
						<div class="blank20"></div>
        				<div class="blank5"></div>
						
						<ul class="money_list_clearfix">
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
									前七位
								</a>
							</li>
							<li class="pt30">
							<br /></li>							
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(3); return false;">
									后四位
								</a>
							</li>
							
							<s:if test="manuTelnumFlag == 1">
								<li class="pt30">
								<li class="tel_selectrule tc">
									<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(4); return false;">
										自定义号码
									</a>
								</li>
							</s:if>
						</ul>
					</div>	
				</div>	
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
