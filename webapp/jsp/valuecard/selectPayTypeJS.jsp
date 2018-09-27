<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	// 取得相应省份信息
	String provinceId = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html> 
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />		
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<!-- 手机号码 -->
		<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='telnum' />" />
		<!-- 为返回上一页做准备 -->
		<input type="hidden" name="telnum" value="<s:property value='telnum' />" />
		<!-- 用户应缴费用 -->
		<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
		<!-- 支付方式 -->
		<input type="hidden" id="payType" name="payType" value="" />
		<!-- 有价卡类型 -->
		<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
		<!-- 有价卡数量 -->
		<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
		<!-- 有价卡面值 -->
		<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
		
		<!-- 引入公共选择交费方式页面 -->
		<s:if test="'HUB' == provinceId">
			<%@include file="/jsp/customize/hub/common/fee/selectPayType.jsp" %>
		</s:if>
		<s:else>
			<%@include file="/jsp/customize/sd/feeCharge/common/selectPayTypeCommon.jsp" %>
		</s:else>
	</form>
	
	<script>

		//防止页面重复提交
		var submitFlag = 0;
		
		// 设置左侧页面高亮
		document.getElementById("highLight3").className = "on";
		
		// 返回
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading();
				
				document.getElementById("curMenuId").value = menuid;
				
				// 左侧菜单显示时使用
				document.getElementById("payType").value = "4";
				
				document.actform.action = "${sessionScope.basePath }valueCard/chooseValueCard.action";
				document.actform.submit();
			}
		}
		
		// 选择相应的充值方式，进行交费
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
		
				// 选择现金缴费
				if (url == "valueCard/cashCharge.action")
				{
				
					// 支付方式设置为现金缴费
					document.getElementById("payType").value = "4";
					
					openRecWaitLoading();
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				else
				{	
					// 支付方式设置为银联卡缴费
					document.getElementById("payType").value = "1";
					
					if("SD" == "<%=provinceId %>")
					{
						// 银联卡交费，判断此时间段内是否可用
						var url1 = "${sessionScope.basePath}charge/checkTime.action";
						
						var loader = new net.ContentLoader(url1, netload = function () {
							var resXml1 = this.req.responseText;
							
							if (resXml1 == "1" || resXml1 == 1)
							{
								document.actform.target = "_self";
								document.actform.action = "${sessionScope.basePath}" + url;
								document.actform.submit();
							}
							else
							{
								submitFlag = 0;
								
								alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
								
								return;
							}
						}, null, "POST", null, null);
					}
					else
					{
						openRecWaitLoading();
						document.actform.action = "${sessionScope.basePath}" + url;
						document.actform.submit();
					}
				}
			}			
		}
	</script>
</body>
</html>