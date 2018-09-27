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
			.tb_blue_08{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px;height:30px;}
			.tb_blue_08 td ,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
			.tb_blue_08 th{  background:#0b8ac1;border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
		</style>
  </head>
  
  <body onload="qryHisBillAcc();">
    <table id="hisBAccTab" width="100%" height="100%" class="tb_blue_08">
			<tr>
				<td width="100%" align="left">
					<p class="margin-top:0px;" align="left">
						<img src="${sessionScope.basePath }images/loading2.gif" />
						<font size="1">正在查询账户信息，请稍候......</font>
					</p>
				</td>
			</tr>
	</table>
	<script type="text/javascript">
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_ac").style.height = hei;
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
			
			function qryHisBillAcc()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillAccInfo.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
					
					var root = resXml.documentElement;
							
					if('error' == root.tagName)
					{
						//alert("查询账户信息错误！11");
						return;
					}
										
					if (resXml)
					{
						    disHisBillAcc(resXml);									
					}
				    else
					{	
						//alert("查询账户信息错误！22");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillDetail);
			}
			function disHisBillAcc(xmlData)
			{
				var tab  = document.getElementById('hisBAccTab');
				tab.deleteRow();
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
		  	 	var length = allNodes.length;
		  	 	var frmHeight = 0;
		  	 	
		  	 	var titleArray = new Array('账户类型', '上期结余','本期收入', '本期返还',
										   '本期退费', '本期可用', '消费支出',
										   '其他支出', '本期末结余');
										   
				var head = tab.insertRow();
				frmHeight += 20;
				for(var i=0;i<titleArray.length; i++)
				{
					var cell =document.createElement("th");
					
					
					//var cell = head.insertCell();
					head.appendChild(cell);
					cell.innerHTML = "<font size=1>" + titleArray[i] + "</font>";
					cell.align = 'center';
				}	
											   
				for(var i=0; i<length; i++)
				{
					var row = tab.insertRow();
					frmHeight += 20;
					var rowInfo = allNodes[i].text;
					var cells = rowInfo.split('%');
					for(var j=0; j<cells.length;j++)
					{
						var cell = row.insertCell();
						cell.innerHTML = "<font size=1>" + cells[j] + "</font>";
						cell.align = 'center';
					}
				}
				
				if(frmHeight < 100)
				{
					frmHeight += 60;
				}
				else if(frmHeight >= 100 && frmHeight < 300)
				{
					frmHeight += 80;
				}
				else if(frmHeight >= 300 && frmHeight < 400)
				{
					frmHeight += 100;
				}
				else if(frmHeight >= 400 && frmHeight < 500)
				{
					frmHeight += 140;
				}
				else if(frmHeight >= 500)
				{
					frmHeight = frmHeight*15/10;
				}
				changeHeight(frmHeight);
				
				changeStartPrint();
			}
	</script>
  </body>
</html>
