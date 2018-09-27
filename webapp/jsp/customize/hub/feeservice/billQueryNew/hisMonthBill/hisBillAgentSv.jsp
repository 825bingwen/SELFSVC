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
			.tb_blue_08{border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px;height:30px;}
			.tb_blue_08 td ,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
			.tb_blue_08 th{ background:#0b8ac1;  border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}	
		</style>
  </head>
  
  <body onload="qryHisBillAgentSv();">
    <table id="hisBaSvTab" class="tb_blue_08" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" align="left">
					<p class="margin-top:0px;" align="left">
						<img src="${sessionScope.basePath }images/loading2.gif" />
						<font size="1">正在查询代收费业务费用，请稍候......</font>
					</p>
				</td>
			</tr>
	</table>
	<script type="text/javascript">
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_asv").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function qryHisBillAgentSv()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillAgentSv.action";
   				
   				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
										
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								//alert("查询代收费业务费用错误！");
								return;
							}else if(root && 'warn' == root.tagName)
							{
								//alert(root.text);
								return;
							}
							
						    disHisBillAgentSv(resXml);									
					}
				    else
					{	
						//alert("查询代收费业务费用错误！");
						return;
					}								
				}, null, "POST", "", null);
   				
   				//execServiceXml(url,'',displayBillAgentSv);
			}
			
			function changeStartPrint()
			{
				var i = parent.document.getElementById("hisPntFlag").value;
				
				parent.document.getElementById("hisPntFlag").value = parseInt(i,10) + 1;
			}	
			
			function disHisBillAgentSv(xmlData)
			{
				var tab = document.getElementById('hisBaSvTab');
				tab.deleteRow();
				
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
				var length = allNodes.length;
		  	 	var frmHeight = 0;
		  	 	
			    var heads = new Array('业务端口','业务名称','企业名称','使用方式','费用类型','金额(元)');
			    createHeadOrTail(tab,heads);
			    frmHeight += 30;
			    
		  	 	for(var i=0; i<length-1; i++)
				{
					var row = tab.insertRow();
					row.height=20;
					frmHeight += 20;
					var rowInfo = allNodes[i].text;
					var cells = rowInfo.split('%');
					for(var j=0; j<cells.length; j++)
					{
						var cell = row.insertCell();
						cell.innerHTML = "<font size=1>" + cells[j] + "</font>";
						cell.align = "left";
					}
				}
				
				
			    var totalFee = allNodes[length-1].text;
			    var tails = new Array('费用合计','','','','',totalFee);
			    createHeadOrTail1(tab,tails);
			    
			    if(frmHeight>=80 && frmHeight<200)
		  	 	{
		  	 		frmHeight += 120;
		  	 	}
		  	 	else if(frmHeight>=200 && frmHeight<400)
		  	 	{
		  	 		frmHeight += 240;
		  	 	}
		  	 	else if(frmHeight >= 400)
		  	 	{
		  	 		frmHeight = frmHeight*16/10
		  	 	}
		  	 	else
		  	 	{
		  	 		frmHeight += 100;
		  	 	}
			    
				changeHeight(frmHeight);
				
				changeStartPrint();
			}
			
			function createHeadOrTail(tab,array)
			{
				var row = tab.insertRow();
		  	 	row.style.backgroundColor ='#8B4513';
		  	 	row.height=20;
			    for(var k=0; k<array.length; k++)
			    {var cell =document.createElement("th");
					
					
					//var cell = head.insertCell();
					row.appendChild(cell);
			    	//var cell = row.insertCell();
			    	cell.innerHTML = "<font size=1>" + array[k] + "</font>";
					cell.align = "left";
			    }
			}
			function createHeadOrTail1(tab,array)
			{
				var row = tab.insertRow();
		  	 	//row.style.backgroundColor ='#8B4513';
		  	 	row.height=20;
			    for(var k=0; k<array.length; k++)
			    {//var cell =document.createElement("th");
					
					
				//var cell = head.insertCell();
					//row.appendChild(cell);
			    	var cell = row.insertCell();
			    	cell.innerHTML = "<font size=1>" + array[k] + "</font>";
					cell.align = "left";
			    }
			}
	
	</script>
  </body>
</html>
