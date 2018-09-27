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
			if("prepareCard/cashPayPrepare.action" == url){
				// 支付方式设置为现金缴费
				document.getElementById("payType").value = "4";
			}else{
				// 支付方式设置为银联卡缴费
				document.getElementById("payType").value = "1";
			}
			if (submitFlag == 0)
			{
				var iccid = document.getElementById("iccid").value;
				var totalFee = document.getElementById("totalFee").value;
				var payType = document.getElementById("payType").value;
		
				submitFlag = 1;
				
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
						
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }prepareCard/prepareFeeConfirm.action";
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="errormessage" id="errormessage" />
			<!-- 总费用-->
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee'/>" />
			<!-- 是否打印小票  1-不打印，0-打印 -->
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
			<%-- add begin hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销--%>
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			<%-- add end hWX5316476 2015-2-12 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销--%>
					
			<!-- 缴费方式，1：银联卡；4：现金 -->
			<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
			
			<!--------------身份证信息 -------------->
			<!-- 姓名 -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
			<!-- 性别 -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
			<!-- 证件地址 -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
			<!-- 开始时间 -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value="<s:property value='idCardPO.idCardStartTime' />" />
			<!-- 结束时间 -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value="<s:property value='idCardPO.idCardEndTime' />" />
			<!-- 卡信息 -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value="<s:property value='idCardPO.idCardContent' />" />
			<!-- 照片信息 -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value="<s:property value='idCardPO.idCardPhoto' />" />
			
		    <!--------------空白卡信息 -------------->
		    <!-- 空白卡卡号 -->
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value='cardBusiLogPO.blankCard'/>" />
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value='simInfoPO.imsi'/>" />
			<!-- ICCID -->
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value='simInfoPO.iccid'/>" />
			<!-- 空白卡信息字符串（iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2） -->
			<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>" />
			<!--受理日志id，用于更新受理日志-->
		   	<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
		    
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>备卡</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择认证方式</a>
						<a href="javascript:void(0)">3. 读取身份证信息</a>
						<a href="javascript:void(0)">4. 费用确认</a>
						<a href="javascript:void(0)" class="on">5. 选择支付方式</a>
						<a href="javascript:void(0)">6. 备卡缴费</a>
						<a href="javascript:void(0)">7. 吐卡打印小票</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40">
      						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow">${sessionScope.CustomerSimpInfo.servNumber }</span></p>
	   						<p class="fs22 fwb pl40 lh30">用户姓名：<span class="yellow fs22">${sessionScope.CustomerSimpInfo.customerName}</span></p>
							<p class="fs22 fwb pl40 lh30">应交金额：<span class="yellow fs22"><s:property value="totalFee" /></span> 元</p>
							<div class="blank10"></div>
							<div class="line"></div>
     						<div class="blank10"></div>
     						<p class="fs22 tit_arrow"><span class="bg"></span>选择支付方式：</p>
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
	       							<s:if test="payTypeFlag == '11'">
	       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
	       							</s:if>
	       							<s:elseif test="payTypeFlag == '10' && #type.menuid == 'cashChargePrepareCard'">
	       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
	       							</s:elseif>
	       							<s:elseif test="payTypeFlag == '01' && #type.menuid == 'cardChargePrepareCard'">
	       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
	       							</s:elseif> 
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
