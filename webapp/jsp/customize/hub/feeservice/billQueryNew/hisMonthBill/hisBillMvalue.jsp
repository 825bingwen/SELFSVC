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
  
  <body onload="qryHisBillMvalue();">
    <table width="100%" height="100%">
    	<tr>
		    <table id="hisBmTab">
					<tr>
						<td width="100%" align="left">
							<p class="margin-top:0px;" align="left">
								<img src="${sessionScope.basePath }images/loading2.gif" />
								<font size="1">正在查询M值信息，请稍候......</font>
							</p>
						</td>
					</tr>
			</table>
			<table>
				<tr>
						<td>
							<p><font id="mark" color='yellow' size="1"></font></p>
						</td>
				</tr>
			</table>
		</tr>
	</table>
	<script type="text/javascript">
			function qryHisBillMvalue()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillMvalue.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
					
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								//alert("查询积分值信息错误！");
								return;
							}
						    disHisBillMvalue(resXml);									
					}
				    else
					{	
						//alert("查询积分值信息错误！");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillMvalue);
			}
			
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_mv").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function changeStartPrint()
			{
				var i = parent.document.getElementById("hisPntFlag").value;
				
				parent.document.getElementById("hisPntFlag").value = parseInt(i,10) + 1;
			}	
			
			function disHisBillMvalue(xmlData)
			{
				var tab  = document.getElementById('hisBmTab');
				tab.deleteRow();
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
		  	 	var length = allNodes.length;
		  	 	var frmHeight = 0;
		  	 	
		  	 	var p1 = '';
		  	 	var row = tab.insertRow();
		  	 	row.height=20;
		  	 	frmHeight +=20;
		  	 	var cell = row.insertCell();
		  	 	cell.align='left';
		  	 	cell.rowspan='6';
		  	 	var firstRow = allNodes[0].text;
		  	 	var array = firstRow.split('%');
		  	 	p1+=array[0];
				p1+=array[1];
		  	 	cell.innerHTML = "<font size=1 color='yellow'>" + p1 + "</font>";
		  	 	
		  	 	for(var i=0; i<2; i++)
		  	 	{
		  	 		var row = tab.insertRow();
		  	 		row.height=20;
		  	 		frmHeight +=20;
		  	 		for(var j=0; j<length; j++)
		  	 		{
		  	 			var rowInfo = allNodes[j].text;
		  	 			var cells = rowInfo.split('%');
		  	 			var cell = row.insertCell();
		  	 			if(i==0)
		  	 			{
		  	 				cell.align='left';
		  	 			}
		  	 			else
		  	 			{
		  	 				cell.align='center';
		  	 			}
		  	 			cell.innerHTML = "<font size=1>" + cells[i] + "</font>";
		  	 		}
		  	 	}
		  	 	document.getElementById('mark').innerHTML = '备注：积分商城http://jf.10086.cn';
				changeHeight(frmHeight+40);
				
				changeStartPrint();
			}
	</script>
  </body>
</html>
