<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% 
NserCustomerSimp customer = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
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
			
		function selectPayType() 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }broadBandPay/selectFeeChargeType.action";
				document.actform.submit();
			}			
		}
		
		function selectOtherFee()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }broadBandPay/selectOtherFee.action";
				document.actform.submit();
			}		
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }broadBandPay/broadBandPay.action";
				document.forms[0].submit();
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
		
			<!-- 宽带产品NCODE -->
			<input type="hidden" id="ncode" name="ncode" value="<s:property value='ncode' />"/>
			
			<!-- 宽带产品名称 -->
			<input type="hidden" id="prodName" name="prodName" value="<s:property value='prodName' />"/>
			
			<!-- 宽带产品描述 -->
			<input type="hidden" id="detailDesc" name="detailDesc" value="<s:property value='detailDesc' />"/>
			
			<!-- 受理金额 -->
			<input type="hidden" id="fee" name="fee" value="<s:property value='fee' />"/>
			
			<!-- 手机号所属region -->
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			
			<!-- 宽带帐号 -->
			<input type="hidden" id="servnumber" name="servnumber" value="<%=customer.getServNumber() %>">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>宽带交费流程</h2>
						<div class="blank10"></div>
						<a title="输入宽带账号" href="#">1. 输入宽带账号</a>
      					<a title="选择宽带产品" href="#">2. 选择宽带产品</a>  
      					<a title="选择支付方式" href="#" class="on">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#">5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>宽带账号：<span class="yellow"><%=customer.getServNumber() %></span></p>
    						<p class="fs22 fwb pl40 lh30">应缴费用：<span class="yellow fs22"><s:property value="fee" /></span> 元</p>
							
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
          							<s:if test="!canPayWithCash && #type.menuid == 'cashcharge'">
          								<a href="javascript:void(0);"><img src='${sessionScope.basePath}<s:property value="#type.imgpath2" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:if>
          							<s:else>
          								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:else>          									
          							
          						</s:iterator><li><br /></li>
        					</ul>								
    					</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
