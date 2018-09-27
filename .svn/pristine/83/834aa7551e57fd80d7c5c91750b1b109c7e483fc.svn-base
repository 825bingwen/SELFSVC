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
.tb_blue_08 th{ background:#0b8ac1; border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}		
		</style>
  </head>
  
  <body onload="qryHisBillComm();">
  		<table id="totalTab" border="0" cellpadding="0" cellspacing="0">  			  
  			  <p class="tit_info" align="left">通信量总量</p>
		      <table id="hisBcommTab1" width="100%" class="tb_blue_08" >
					<tr>
						<td width="100%" align="left">
							<p class="margin-top:0px;" align="left">
								<img src="${sessionScope.basePath }images/loading2.gif" />
								<font size="1">正在查询通信详情，请稍候......</font>
							</p>
						</td>
					</tr>
			 </table>
			 <table id="hisBcommTab2" width="100%" class="tb_blue_08" >
			 </table>
			 <p class="tit_info" align="left">套餐内通信量明细</p>
			 <table id="hisBcommTab3" width="100%" class="tb_blue_08" >
			 </table>
		</table>
	 <script type="text/javascript">
	 		
	 		String.prototype.getByteLength = 
	 		function () 
	 		{
					var trimedStr = this.replace(/(^\s*)|(\s*$)/g, "");
					return trimedStr.replace(/[^\x00-\xff]/g, "***").length;
			};
			
			function getRowsByByteLen(pkgName,str)
			{
				var len = str.getByteLength();
				
				var desLen = (len - len%76)/76 + 1;
				
				var l = pkgName.getByteLength();
				
				var nameLen = (l - l%32)/32 + 1;
				
				return desLen > nameLen ? desLen : nameLen;
			}
	 		
	 		function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_comm").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function createHeads(tab,array)
			{
				var row = tab.insertRow();
				row.height = 20;
		  	 	row.style.backgroundColor ='#8B4513';
			    for(var k=0; k<array.length; k++)
			    {
			    	//var cell = row.insertCell();
			    	
			    	var cell =document.createElement("th");
					
					
					//var cell = head.insertCell();
					row.appendChild(cell);
			    	
			    	
			    	
			    	
			    	if(0==k)
			    	{
			    		cell.width = '30%';
			    	}
			    	cell.innerHTML = "<font size=1>" + array[k] + "</font>";
					cell.align = "center";
			    }
			}
			
			function changeStartPrint()
			{
				var i = parent.document.getElementById("hisPntFlag").value;
				
				parent.document.getElementById("hisPntFlag").value = parseInt(i,10) + 1;
			}	
			
			function qryHisBillComm()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillCommDetail.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
						
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								//alert("查询通信使用详细信息错误！");
								return;
							}else if(root && 'warn' == root.tagName)
							{
								//alert(root.text);
								return;
							}
							
						    disHisBillComm(resXml);									
					}
				    else
					{	
						//alert("查询通信使用详细信息错误！");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillComm);
			}
			
			function getPkgDesc(array)
			{
				var res = '';
				if(array.length > 1)
				{
					var descArray = array[1].split("；");
					for(var i=0; i<descArray.length; i++)
					{
						res += descArray[i] + '；';
					}
				}
				return res;
			}
			
			function getPkgUsage(array)
			{
				var res = '';
				
				if(array.length > 2)
				{
					var usageArray = array[2].split("；");
					
					for(var i=0; i<usageArray.length; i++)
					{
						res += usageArray[i] + '；';
					}
				}
				return res;
			}
			
			function getPkgName(array)
			{
				if(array.length > 0)
				{
					return array[0];
				}
				
				return '';
			}
			
			function getRowsByPkgDesc(desc)
			{
				var descArray = desc.split("；");
					
				return descArray.length;
			}
			function insertCell(row,_node,nodeName){
				var _nodeValue=getXmlData(_node, nodeName);
				var cell = row.insertCell();
				cell.innerHTML = "<font size=1>" + _nodeValue + "</font>";
				cell.align = "left";
			}
			var printMess=null;
			var printCommArray=null;
			function getPrintArray(){
				if(printCommArray==null){
					printCommArray = new Array();
					//alert(printMess);
					if(printMess!=null){
						var _array=printMess.split("\n");
						for(var i=0;i<_array.length;i++){
							var _line=_array[i];
							printCommArray.push("  "+_line);
						}					
					}						
				}
				return printCommArray;
			}
			function disHisBillComm(xmlData)
			{
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
				//alert(root.xml);
				printMess=getXmlData(root, "printInfo");
				var _oNodes =root.selectNodes("./bcallLst");	
				var _size = _oNodes.length;
				var _height=0;
				if(_size>0){
					var tab = document.getElementById('hisBcommTab1');
		  	 		tab.deleteRow();
					createHeads(tab,new Array('通信量类别','包含通信量','使用通信量','剩余通信量'));
					for(var i=0;i<_size;i++){
						var _node=_oNodes[i];
						var row = tab.insertRow();
						insertCell(row,_node, "className");
						insertCell(row,_node, "includeDesc");
						insertCell(row,_node, "usageDesc");
						insertCell(row,_node, "remainderDesc");						
					}
					_height+=tab.scrollHeight;
				}
				var _oNodes =root.selectNodes("./overLst");	
				var _size = _oNodes.length;				
				if(_size>0){
					var tab = document.getElementById('hisBcommTab2');
		  	 		//tab.deleteRow();
					createHeads(tab,new Array('通信量类别','超出通信量'));
					for(var i=0;i<_size;i++){
						var _node=_oNodes[i];
						var row = tab.insertRow();
						insertCell(row,_node, "className");
						insertCell(row,_node, "includeDesc");
					}
					_height+=tab.scrollHeight;
				}
				var _oNodes =root.selectNodes("./pkgLst");	
				var _size = _oNodes.length;
				if(_size>0){
					var tab = document.getElementById('hisBcommTab3');
		  	 		//tab.deleteRow();
					createHeads(tab,new Array('套餐名称','通信量类别','套餐包含通信量','套餐使用通信量','套餐剩余通信量'));
					for(var i=0;i<_size;i++){
						var _node=_oNodes[i];
						var row = tab.insertRow();
						insertCell(row,_node, "packageName");
						insertCell(row,_node, "className");
						insertCell(row,_node, "includeDesc");
						insertCell(row,_node, "usageDesc");	
						insertCell(row,_node, "remainderDesc");	
					}
					_height+=tab.scrollHeight;
				}				
				parent.document.all("ifr_his_comm").style.height=(_height+70);
				changeStartPrint();
			}
			function getXmlData(xml, itemName) {
				var items = xml.getElementsByTagName(itemName);
				var itemData = "";
				if (items.length > 0) {
					if(items[0].childNodes.length > 0)
						itemData = items[0].childNodes[0].nodeValue;
				}
				return itemData;
			}
			
	 </script>
  </body>
</html>
