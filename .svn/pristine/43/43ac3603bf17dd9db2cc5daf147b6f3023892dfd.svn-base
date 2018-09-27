<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>手机支付主账户充值</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		//防止页面重复提交
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

		function goback(curmenu) 
		{
			//防止重复操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }mpaycharge/cmpay.action";
				document.actform.submit();
			}
		}
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();
			}			
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">		
				
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>主账户充值流程</h2>
						<div class="blank10"></div>
						<a title="输入手机号码" href="javascript:void(0)">1. 输入手机号码</a> 
						<a title="选择支付方式" href="javascript:void(0)" class="on">2. 选择支付方式</a>
    					<p class="recharge_tips">查看您的话费信息，并选择支付方式进入支付。</p>
    					<a title="选择金额" href="javascript:void(0)">3. 选择金额</a> 
    					<a title="支付" href="javascript:void(0)">4. 支付</a> 
    					<a title="完成" href="javascript:void(0)">5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">现金账户余额：<span class="yellow fs22"><s:property value="cashBalance" /></span> 元</p>
    						<p class="fs22 fwb pl40 lh30">充值卡账户余额：<span class="yellow fs22"><s:property value="cardBalance" /></span> 元</p>
    						<p class="fs22 fwb pl40 lh30">待充值金额：<span class="yellow fs22"><s:property value="shouldPay" /></span> 元</p>
							
							<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank10"></div>
      						<p class="tit_arrow" style="font-size:22px;"><span class="bg"></span>选择支付方式：</p>
      						<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
          						<s:iterator value="usableTypes" id="type" status="st">
          							<s:if test="#st.index == 0">          								
          								<li class="mr70">          									
          							</s:if>
          							<s:else>
          								<li>          									
          							</s:else>
          								<a title='<s:property value="#type.menuname" />' href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>         									
          							</li>
          						</s:iterator>
        					</ul>								
    					</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
