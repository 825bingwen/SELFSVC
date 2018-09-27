<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 返回上一页
function goback(curmenu) {
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading();
		}
	
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}		
}

// 处理键盘事件
document.onkeydown = pwdKeyboardDown;
function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//更正
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
		return false;
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
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
	else if (key == 13 || key == 89 || key == 221)
	{
		doSub();
	}
	
	if (key == 48)
	{
		goback("<s:property value='curMenuId' />") ; 
	}
	// 上翻
	else if (key == 85)
	{
		myScroll.moveUp(30);
		return;
	}
	// 下翻
	else if (key == 68)
	{
		myScroll.moveDown(30);
		return;
	}	
}

function doSub()
{
	if (submitFlag == 0)
	{
		openRecWaitLoading();
		
		submitFlag = 1;
		
		// 跳转缴费选择页面
		document.actform.action = "${sessionScope.basePath}cardInstall/selectPayType.action";
		document.actform.submit();
	}			
}

//出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading();
		
		document.getElementById("errormessage").value = errorMsg;

		//异常处理，只要出现了异常就要记录日志  
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
		document.actform.submit();
	}			
}	

</script>
</head>
<body scroll="no">
<form name="actform" method="post">
	<input type="hidden" name="errormessage" id="errormessage" />
	<!-- 空白卡信息 -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!-- ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!-- 短消息中心号码 -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />

	<!-- 卡信息串 -->
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
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />
	<!-- 产品ID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	
	<!-- 缴费信息 -->
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	
	<%-- 开户日志 --%>
	<%-- 流水号 --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- 缴费流水号 --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- 缴费方式，1：银联卡；4：现金 --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
	<%-- 实收费用 --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>
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
 				<a href="javascript:void(0)" class="on">5. 费用确认</a>
 				<a href="javascript:void(0)">6. 开户缴费</a>
 				<a href="javascript:void(0)">7. 取卡打印小票</a>
			</div>

			<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank40"></div>
				<div class="p40">
					<p class=" tit_info">
						<span class="bg"></span>费用明细列表
						<span class="yellow"></span>
					</p>
					<div class="blank15"></div>
					<table width="100%" class="tb_blue02" align="center">
						<tr>
							<th width="25%" class="tc">号码</th>
							<td width="25%" class="tc"><span class="yellow fs16"><s:property value='telnum'/></span></td>
							<th width="25%" class="tc">品牌</th>
							<td width="25%" class="tc">
								<span class="yellow fs16">
									<s:if test="tpltTempletPO.brand == 'BrandMzone'">
										动感地带
									</s:if>
									<s:elseif test="tpltTempletPO.brand == 'BrandSzx'">
										神州行
									</s:elseif>
									<s:elseif test="tpltTempletPO.brand =='BrandGotone'">
										全球通
									</s:elseif>
									<s:else>
										神州行
									</s:else>
							 	</span>
							 </td>
						</tr>
						<tr>
							<th width="25%" class="tc">产品</th>
							<td width="75%" class="tc" colspan="3">
								<span class="yellow fs16"><s:property value="tpltTempletPO.mainProdName" /></span>
							</td>
						</tr>
					</table>
					<div class="blank20"></div>
					<table id="maintab" align="center" width="100%" class="tb_blue02">
						<tr>
							<th width="70%" class="tc">费用名称</th>
							<th width="30%" class="tc">金额(元)</th>
						</tr>
						<s:iterator value="feeList" id="feeList">
							<tr>
								<td width="70%" class="tc"><s:property value="#feeList.feeName" /></td>
								<td width="30%" class="tc"><s:property value="#feeList.fee" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="blank20"></div>
				<a href="#" class="bt10 mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;float:right;right:210px;">确认(请按确认键)</a>
			</div>
			<div class=" clear"></div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
</body>
</html>