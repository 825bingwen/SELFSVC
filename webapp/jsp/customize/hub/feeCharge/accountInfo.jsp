<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
				document.actform.action = "${sessionScope.basePath }charge/selectFeeChargeType.action";
				document.actform.submit();
			}			
		}
		
		function selectOtherFee()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/selectOtherFee.action";
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
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;				
				document.actform.target = "_self";
				//add begin by lwx439898 2017-10-11 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
				if("paycetcharge" == menuid)
				{
					document.getElementById("payCerntWay").value = "true";							
				}
				//add end by lwx439898 2017-10-11 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();	
			}			
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value="0">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="payCerntWay" name="payCerntWay" value="" />
				
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)" class="on">2. 选择支付方式</a>
    					<p class="recharge_tips">查看您的话费信息，并提交充值交费金额进入支付。</p>
    					<a href="javascript:void(0)">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 支付</a> 
    					<a href="javascript:void(0)">5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">应缴话费：<span class="yellow fs22"><s:property value="balance" /></span> 元</p>
							
							<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank10"></div>
      						<p class="tit_arrow" style="font-size:22px;"><span class="bg"></span>选择支付方式：</p>
      						<!-- 
	      					<div class="blank10"></div>
	      					<ul class="money_list clearfix">
	        					<li class="first_money_key tc">
	        						<a style="text-align:center;" href="javascript:void(0);" onclick="selectOtherFee();return false;" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'">选择金额充值</a>
	        					</li>
	      					</ul> 
							-->
							<!-- <div class="blank20"></div> -->
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
          							<!-- add begin by lwx439898 2017-10-11 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造 -->
          							<s:elseif test=" (agentParam || termParam != 'true') && #type.menuid == 'paycetcharge' ">
          							</s:elseif>
          							<!-- add end by lwx439898 2017-10-11 OR_HUB_201709_456_湖北_支付中心需要 自助终端配合接口改造 -->
          							<s:else>
          								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:else>          									
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
