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
  </head>
  
  <body onload="qryHisBillRec();">
    <table id="hisBRecTab">
					<tr>
						<td width="100%" align="left">
							<p class="margin-top:0px;" align="left">
								<img src="${sessionScope.basePath }images/loading2.gif" />
								<font size="1">正在查询资费评估建议，请稍候......</font>
							</p>
						</td>
					</tr>
	</table>
	<script type="text/javascript">
			function qryHisBillRec()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillValuate.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
					
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								alert("查询资费评估建议信息错误！");
								return;
							}
						    disHisBillRec(resXml);									
					}
				    else
					{	
						alert("查询资费评估建议信息错误！");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillMvalue);
			}
			
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_rev").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function disHisBillRec(xmlData)
			{
				var tab  = document.getElementById('hisBRecTab');
				tab.deleteRow();
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
				var frmHeight = 0;
		  	 	
		  	 	var row = tab.insertRow();
				row.height=20;
				frmHeight += 20;
				var cell = row.insertCell();
				cell.innerHTML = "<font size=1><b>资费方案评估∶</b></font>";
				cell.align = "left";
		  	 	
		  	 	for(var i=0; i<allNodes.length; i++)
		  	 	{
		  	 		var row = tab.insertRow();
					row.height=20;
					frmHeight += 20;
					var cell = row.insertCell();
					cell.innerHTML = "<font size=1><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" 
							         + allNodes[i].text + "</b></font>";
				
				    cell.align = "left";
		  	 	}
				
				changeHeight(frmHeight);
			}
	</script>
  </body>
</html>
