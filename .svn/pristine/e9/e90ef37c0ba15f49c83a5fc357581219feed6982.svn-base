<%@page contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		//add by xkf57421 for ZG[2011]_11_006 begin
		String initBz = (String) request.getAttribute("initBz");
		//add by xkf57421 for ZG[2011]_11_006 end

		//add by sWX219697 2015-1-28 15:54:13 OR_HUB_201408_620_自助终端界面改版优化
		String curMenuId = (String) request.getAttribute("curMenuId");
	%>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<meta http-equiv="Page-Exit" ; content="blendTrans(Duration=0.5)">
		<link href="${sessionScope.basePath}css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript">
			function turnToMain()
			{
				window.location.href = "${sessionScope.basePath}chooseTel/selectRegion.action?bz=<%=initBz%>&curMenuId=<%=curMenuId%>";
			}
		</script>
	</head>
	<body onload="openWindow_wait();">
		<div class="header">
			<div class="nx_new_logo"></div>
		</div>
		<div class="index_main">
			<div class="blank60"></div>
			<div class="b966">
				<div class="blank60"></div>
				<div class="blank60"></div>
				<div class="relative tc">
					<img src="${sessionScope.basePath}images/loading.gif" alt="加载中..." />
					<div class="blank15"></div>
					<p class="tc fs22"></p>
				</div>
			</div>
		</div>

		<script type="text/javascript">		
				    var isDone = 0;
					openWindow_wait = function(){
						if(isDone == 0)
						{
							isDone = 1;
							turnToMain();
						}
					}	
				</script>
	</body>
</html>