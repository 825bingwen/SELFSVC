<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />		
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
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
	if (key == 220 || key == 82) 
	{
		goback("<s:property value='curMenuId' />");
	}
}

// 返回
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading();
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }cardInstall/feeConfirm.action";
		document.forms[0].submit();
	}
}

function doSub(menuid, url)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("installStatus").value = "2";
		document.getElementById("payStatus").value = "2";
		document.getElementById("curMenuId").value = menuid;

		// 选择现金缴费
		if (url == "cardInstall/cashChargeInstall.action")
		{
		
			// 支付方式设置为现金缴费
			document.getElementById("payType").value = "4";
			
			openRecWaitLoading();
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + url;
			document.actform.submit();
		}
		else
		{	
			// 支付方式设置为银联卡缴费
			document.getElementById("payType").value = "1";
			
			openRecWaitLoading();
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + url;
			document.actform.submit();
		}
	}			
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<!-- 空白卡信息 -->
		<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
		<!--ICCID -->
		<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
		<!--短消息中心号码 -->
		<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />

		<!-- 空信息字符串 -->
		<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
		
		<!-- 身份证信息 -->
		<!-- 姓名 -->
		<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
		<!-- 性别 -->
		<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
		<!-- 身份证号码 -->
		<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
		<!-- 证件地址 -->
		<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
		<!-- 开始时间 -->
		<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
		<!-- 结束时间 -->
		<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
		<!-- 卡信息 -->
		<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
		<!-- 照片信息 -->
		<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
		
		<!-- 套餐信息 -->
		<!-- 模板ID -->
		<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
		<!-- 模板名称 -->
		<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
		<!-- 产品ID -->
		<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
		<!-- 产品名称 -->
		<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
		<!-- 品牌 -->
		<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
		<!-- 套餐月费 -->
		<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
		<!-- 预存费用 -->
		<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
		
		<!-- 选号信息 -->
		<!-- 地市 -->
		<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
		<!-- 组织机构ID -->
		<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
		<!-- 地市名称 -->
		<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
		<!-- 选号规则 -->
		<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
		<!-- 前缀 -->
		<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
		<!-- 后缀 -->
		<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
		<!--空白卡序列号 -->
		<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
		<!--手机号码 -->
		<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
		<!-- 产品ID -->
		<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />

		<!-- 缴费信息 -->
		<!-- 费用合计 -->
		<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
		
		<%-- 开户日志 --%>
		<%-- 流水号 --%>
		<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
		<%-- 缴费方式，1：银联卡；4：现金 --%>
		<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
        <%-- 默认2：初始状态 0：写卡成功 1：写卡失败 --%> 
        <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
        <%-- 默认2：初始状态 0：缴费成功 1：缴费失败 --%> 
        <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
        <%-- 默认2：初始状态 0：开户成功 1：开户失败 --%>
        <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
        <%-- 是否打印小票  1-不打印，0-打印 --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
        <%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
		<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			   
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>在线开户</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. 入网协议确认</a> 
					<a href="javascript:void(0)">2. 读取身份证信息</a>
   					<a href="javascript:void(0)">3. 产品选择</a> 
   					<a href="javascript:void(0)">4. 号码选择</a>
   					<a href="javascript:void(0)">5. 费用确认</a>
   					<a href="javascript:void(0)"  class="on">6. 开户缴费</a>
   					<a href="javascript:void(0)">7. 取卡打印小票</a>						
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank60"></div>
   					<div class="p40">
   						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
   						<p class="fs22 fwb pl40 lh30">用户姓名：<span class="yellow fs22"><s:property value="idCardPO.idCardName" /></span></p>
						<p class="fs22 fwb pl40 lh30">应交金额：<span class="yellow fs22"><s:property value="recFee" /></span> 元</p>
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
       							<s:elseif test="payTypeFlag == '10' && #type.menuid == 'cashChargeInstall'">
       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
       							</s:elseif>
       							<s:elseif test="payTypeFlag == '01' && #type.menuid == 'cardChargeInstall'">
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
