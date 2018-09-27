<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
        // 用户信息
        NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);
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
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiDesc.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="window.focus();">
		<form name="actform" method="post">
		
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- 活动组ID -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
		
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
      					<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#" class="on">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#" >5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40">
      						<p class="tit_info"><span class="bg"></span>手机号码：<span class="yellow fs22"><%=customerSimp.getServNumber() %></span></p>
      						<p class="fs22 fwb pl40 lh30">应缴金额：<span class="yellow fs22"><s:property value="#request.prepayFee" /></span> 元</p>
							
							<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank20"></div>
      						<p class="tit_arrow" style="font-size:22px;"><span class="bg"></span>选择支付方式：</p>
        					
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
