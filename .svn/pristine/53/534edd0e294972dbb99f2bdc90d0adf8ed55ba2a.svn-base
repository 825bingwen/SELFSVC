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
		function doinstall()
		{				
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }charge/intPayCetChargeCommt.action?chargeMoney=100&curMenuId=feeCharge&payModeId=10050&payId=123456788";
			document.forms[0].submit();

		}
		function donull()
		{
			window.location.href="${sessionScope.basePath }/jsp/customize/hub/feeCharge/errorPayCenter.jsp";
		}
	
	</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post" >
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value="0">
			<input type="hidden" id="servnumber" name="servnumber" value="" />
			<input type="hidden" id="servRegion" name="servRegion" value="" />
			<input type="hidden" id="payCerntWay" name="payCerntWay" value="" />
				
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				
				<div class="pl30" style="text-align: center;font-size:25px;margin-top:10px">
				模拟支付中心支付页面				
				</div>	
				<div style="text-align: center;margin-top:300px;font-size:20px;">
				<a href="javascript:void(0)" name="functionkey"  onclick="doinstall();return false;">支付成功</a> 
				<a href="javascript:void(0)" name="functionkey"  onclick="donull();return false;">支付失败</a> 
				</div>			
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
