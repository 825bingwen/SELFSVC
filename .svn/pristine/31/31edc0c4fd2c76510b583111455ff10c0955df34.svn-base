<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>NG2.3自助终端打印机校验</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
			<script type="text/javascript">
		
		function vali(){
			var prtObj;
			var valiMessage = "";
			try{
				prtObj = window.parent.document.getElementById("invprtpluginid");
				var v =  prtObj.CheckPaper();				
				document.forms[0].target = "_self";
				if(prtObj == "" || prtObj == undefined)
				{
				   document.getElementById("valiMess").value ="由于发票打印机控件未安装，您的发票打印失败，给您带来的不便，敬请原谅。";
					document.forms[0].action = "${sessionScope.basePath }invoice/valiPrint.action";
					document.forms[0].submit();
				}else if (v != 0 ){ // 检查发票打印机缺纸
				    
				    document.getElementById("valiMess").value  ="警告:发票打印机缺纸或故障!";
				    document.forms[0].action = "${sessionScope.basePath }invoice/valiPrint.action";
					document.forms[0].submit();
				}else{
				
					document.forms[0].action = "${sessionScope.basePath }invoice/invoiceList.action";
					document.forms[0].submit();
				}
			}
			catch(e)
			{
					
					 document.getElementById("valiMess").value ="由于发票打印机控件未安装，您的发票打印失败，给您带来的不便，敬请原谅。";
					document.forms[0].action = "${sessionScope.basePath }invoice/valiPrint.action";;
					document.forms[0].submit();
			}	
		
		}	
		
		</script>
	</head>
	<body scroll="no" onload='vali()'>
		<form id="actform" name="actform" method="post">
		<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
							
			</div>
				
			<%@ include file="/backinc.jsp"%>	
			<input type="hidden" id="valiMess" name="valiMess" value="">
					
		</form>
	</body>
</html>
