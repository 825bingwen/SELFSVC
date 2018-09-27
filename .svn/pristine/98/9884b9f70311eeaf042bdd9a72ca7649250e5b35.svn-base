<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCardEquip = termInfo.getTermspecial().substring(4, 5);

// 省份信息
String provinceId = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>		
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<input type="hidden" id="telnum" name="cardPayLogVO.servnumber" value="<s:property value='cardPayLogVO.servnumber' />" />
			<input type="hidden" name="telnum" value="<s:property value='telnum' />" />	
			<input type="hidden" id="terminalSeq" name="cardPayLogVO.terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee' />" />
			<input type="hidden" id="payType" name="payType" value="<s:property value='payType' />" />
			<input type="hidden" id="errorType" name="errorType" value="" />
			<%-- 是否打印小票  1-不打印，0-打印 --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
			<!-- 有价卡类型 -->
			<input type="hidden" id="cardType" name="valueCardVO.cardType" value="<s:property value='valueCardVO.cardType' />" />
			<!-- 有价卡数量 -->
			<input type="hidden" id="cardNum" name="valueCardVO.cardNum" value="<s:property value='valueCardVO.cardNum' />" />
			<!-- 有价卡面值 -->
			<input type="hidden" id="cntTotal" name="valueCardVO.cntTotal" value="<s:property value='valueCardVO.cntTotal' />" />
			
			<!-- modify begin wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
			<s:if test="'HUB' == provinceId">
				<%@include file="/jsp/customize/hub/common/fee/card/dutyInfo.jsp" %>
			</s:if>
			<s:else>
				<%@include file="/jsp/customize/sd/feeCharge/common/dutyInfoCommon.jsp" %>
			</s:else>
			<!-- modify end wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
		</form>
		<script>
		
			//防止重复提交
			var submitFlag = 0;
			
			// 设置左侧页面的高亮
			document.getElementById("highlight4").className = "on";
			
			// 返回
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = 'recValueCardBuy';
					
					document.getElementById("payType").value = "4";
					
					document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/qryPayType.action";
					document.dutyInfoForm.submit();
				}
			}
			
			// 同意银联免责声明			
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
					if("HUB" == "<%=provinceId %>")
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/readCard.action";
					}
					else
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/readCard_sd.action";
					}
					document.dutyInfoForm.submit();
				}
			}
			
			//出现异常
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("errormessage").value = errorMsg;
					document.getElementById("errorType").value = "add";
		
					// 异常处理，只要出现了异常就要记录日志
					// modify begin wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造
					if("HUB" == "<s:property value='provinceId'/>")
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/goCardError.action";
					}
					else
					{
						document.dutyInfoForm.action = "${sessionScope.basePath }valueCard/chargeError.action";
					}
					// modify end wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造

					document.dutyInfoForm.submit();
				}			
			}	
			
			// 进入免责页面时加载用户信息
			function doLoad()
			{
				var serverNumber = "<s:property value='cardPayLogVO.servnumber' />";
				if (serverNumber == null || serverNumber == "")
		           {            
		           	setException("对不起，用户信息获取失败，请返回重新操作。");
		         		return;
		           }
				
				<%
				if (!"1".equals(isCardEquip))
				{
				%>
				 setException("对不起，该终端机暂不支持银联卡充值，请选用其它方式。");
				 return;
				<%
				}
				%>
			}
		</script>
	</body>
</html>