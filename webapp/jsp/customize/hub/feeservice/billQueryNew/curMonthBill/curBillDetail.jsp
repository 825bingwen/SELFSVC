<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String custType1=(String)session.getAttribute("BILL_CUSTTYPE");
String unitelCon=(String)session.getAttribute("HIS_BILL_UNITELNUMCON");
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
			.tb_blue_08 th{ background:#0b8ac1; border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}	
		</style>
  </head>
  
  <body onload="qryCurBillInfo();">
    <table id="curBdTab" width="100%" height="100%" class="tb_blue_08">
			<tr>
				<td width="100%" align="left">
					<p class="margin-top:0px;" align="left">
						<img src="${sessionScope.basePath }images/loading2.gif" />
						<font size="1">正在查询账单信息，请稍候......</font>
					</p>
				</td>
			</tr>
	</table>
	<script type="text/javascript">
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_bd").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function changeStartPrint()
			{
				var i = parent.document.getElementById("startPrint").value;
				
				parent.document.getElementById("startPrint").value = parseInt(i,10) + 1;
			}	
			
			function qryCurBillInfo()
			{
				var url = "${sessionScope.basePath }hubfeeservice/curBillDetail.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
					
					var root = resXml.documentElement;
							
					if('error' == root.tagName)
					{
						//alert("查询当期账单信息错误!");
						return;
					}else if(root && 'warn' == root.tagName)
					{
						//alert(root.text);
						return;
					}
										
					if (resXml)
					{
					
						    displayBillDetail(resXml);									
					}
				    else
					{	
						//alert("查询当期账单信息错误!");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillDetail);
			}
			function displayBillDetail(xmlData)
			{
				var tab  = document.getElementById('curBdTab');
				tab.deleteRow();
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
		  	 	var length = allNodes.length;
		  	 	var frmHeight = 0;
		  	 	var custType1='<%=custType1%>';
		  	 	
				for(var i=0; i<length; i++)
				{
					var row = tab.insertRow();
					row.height=20;
					frmHeight += 20;
					var rowInfo = allNodes[i].text;
					var cells = rowInfo.split('%');
					
					for(var j=0; j<cells.length; j++)
					{   	
							if(custType1=='1'&&i==0&&j==1){
								cells[j]="合户账户，含本机在内共<%=unitelCon%>个号码";
								
							}
							if(custType1=='1'&&i==0&&j==2){
								
								cells[j]="";
								
							}
							
						//var cell = row.insertCell();
						if(i==0||i==custType1){
							var cell = document.createElement("th");
							
							row.appendChild(cell);
							cell.className="list_title";
							if(i==0&&custType1=='1'&&j==1){
								cell.style.borderRightWidth="0";
								
							}
							if(i==0&&custType1=='1'&&j==2){
								cell.style.borderLeftWidth="0";
								
							}
							
						}else{
							var cell = document.createElement("td");
							row.appendChild(cell);
							
							
							
						}
						
						
						
						
						if(rowInfo.indexOf('#') == 0)
						{
							if(j==0)
							{
								cells[j]=cells[j].substring(1);
								var feeItem = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;●" + cells[j];
								cell.innerHTML = "<font size=2>" + feeItem + "</font>";
								cell.align = "left";
							}
							else
							{
								cell.innerHTML = "<font size=2>" + cells[j] + "</font>";
								cell.align = "left";
							}
						}
						else
						{
							cell.innerHTML = "<font size=2>" + cells[j] + "</font>";
							cell.align = "left";
						}
					}
				}
				
				if(frmHeight < 400)
				{
					frmHeight = 400;
				}
				changeHeight(frmHeight);
				
				changeStartPrint();
			}
	</script>
  </body>
</html>
