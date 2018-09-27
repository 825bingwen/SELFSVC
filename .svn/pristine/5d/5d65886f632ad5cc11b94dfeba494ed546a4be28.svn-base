<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    	<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
		<style type="text/css">
			body{ background:url(../images/bill_bg.png) center top}
		</style>
  </head>
  
  <body onload="qryHisBillStruct();">
  	<font id="title" color='black' size="1"></font>
    <div id="curBsiTab" align="center">
			<img id="structImg" src="${sessionScope.basePath }images/loading2.gif" />
			<font id="loading" size="1">正在加载费用结构图，请稍候</font>
	</div>
	<script type="text/javascript">
			
			//setTimeout("qryCurBillStruct()", 2000);
			
			function qryHisBillStruct()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillStructImg.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
										
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								alert("查询消费结构图错误！");
								return;
							}
							
						    disHisBillStruct(resXml);									
					}
				    else
					{	
						alert("查询消费结构图错误！");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillStruct);
			}
			
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_bsi").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function disHisBillStruct(imgXml)
			{
				document.getElementById('title').innerHTML = '<b>当前月费用结构图</b>';
				var root = imgXml.documentElement;
				var allNodes = root.childNodes;
				var length = 7;
				var map = new Array(7);
				for(var i=0; i<length; i++)
				{
					map[i] = allNodes[i].text;
				}
				
				var params = '?structInfo=' + encodeURI(encodeURI(map.toString()))
					+ '&height=190'
					+ '&width=300';
					
				var structImg = document.getElementById('structImg');
				structImg.src='${sessionScope.basePath}hubfeeservice/printStructImg.action' + params;
				changeHeight(200);
				document.getElementById('loading').innerHTML = '';
			}
			
	</script>
  </body>
</html>
