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
			.tb_blue_08{border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px;word-break:break-all}
			.tb_blue_08 td ,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; word-break:break-all}
			.tb_blue_08 th{ background:#0b8ac1;border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; word-break:break-all}	
		</style>
  </head>
  
  <body onload="qryHisBillSelfSv();">
  	  <table id="totalTab" border="0" cellpadding="0" cellspacing="0">
  	  	<tr>
  	  		<td  width="50%">
			   <table id="hisBssTab1" class="tb_blue_08" height="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" align="left">
								<p class="margin-top:0px;" align="left">
									<img src="${sessionScope.basePath }images/loading2.gif" />
									<font size="1">正在查询自有业务费用明细，请稍候......</font>
								</p>
							</td>
						</tr>
			  </table>
		  	<td/>
		  	<td width="50%">
			   <table id="hisBssTab2" class="tb_blue_08" height="100%"  border="0" cellpadding="0" cellspacing="0">
			   </table>
		  	<td/>
		 </tr>
	  </table>
	<script type="text/javascript">
function calLength(str)
{
    var length=0;
		for(var i=0;i<str.length;i++)
    {
		    ch=str.substr(i,1);
		    chs=escape(ch);
		    if(chs.length !=ch.length)
		    {
				    length=length+2;
				}
				else
				{
				    length++;
				}
		}
		return length;
}
function trim(s) {
	return s.replace(/^\s*/, "").replace(/\s*$/, "");
}
			var leftTabRows=0;
      var rightTabRows=0;
	  var cssHeight = 25;
      
			String.prototype.getByteLength = 
	 		function () 
	 		{
					var trimedStr = this.replace(/(^\s*)|(\s*$)/g, "");
					return trimedStr.replace(/[^\x00-\xff]/g, "***").length;
			};
			
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_bss").style.height = hei;
				}
				catch(e1)
				{
				}
			}
			
			function getRowsByByteLen(str)
			{

				var len = calLength(str);
				var desLen = 0;
				if(len > 23)
				{
				    desLen = (len - len%23)/23 ;
			  }
			  return desLen;
			}
			
			function changeStartPrint()
			{
				var i = parent.document.getElementById("hisPntFlag").value;
				
				parent.document.getElementById("hisPntFlag").value = parseInt(i,10) + 1;
			}	
			
			function qryHisBillSelfSv()
			{
			    var url = "${sessionScope.basePath }hubfeeservice/hisBillSelfSv.action";
			    
			    var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
										
					if (resXml)
					{
							var root = resXml.documentElement;
							
							if('error' == root.tagName)
							{
								//alert("查询自有业务费用明细错误！");
								return;
							}else if(root && 'warn' == root.tagName)
							{
								alert(root.text);
								return;
							}
							
						    disHisBillSelfSv(resXml);									
					}
				    else
					{	
						//alert("查询自有业务费用明细错误！");
						return;
					}								
				}, null, "POST", "", null);
			    
   				//execServiceXml(url,'',displayBillSelfSv);
			}
			
			function disHisBillSelfSv(xmlData)
			{
				var tab1  = document.getElementById('hisBssTab1');
				var tab2  = document.getElementById('hisBssTab2');
				tab1.deleteRow();
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
		  	 	var length = allNodes.length;
		  	 	var lrFlag = 0;
		  	 	var totalLr = 0;
		  	 	
		  	 	var isBr = 0;
		  	 	
		  	 	createHead(tab1,tab2);
		  	 	
		  	 	
		  	 	for(var i=0; i<length-1; i++)
				{
					var rowInfo = allNodes[i].text;
					
					if(rowInfo.indexOf('@') == 0)
					{
						totalLr++;
					}
			    }
			    
			    if(totalLr%2 == 0)
			    {
			    	totalLr = totalLr/2;
			    }
			    else
			    {
			    	totalLr = (totalLr+1)/2;
			    }
		  	 	
				for(var i=0; i<length -1; i++)
				{
					var rowInfo = allNodes[i].text;
					
					if(rowInfo.indexOf('@') == 0)
					{
						rowInfo = rowInfo.substring(1);
						lrFlag++;
					}
					if(lrFlag <= totalLr)
					{
						var row = tab1.insertRow();
					}
					else
					{
						var row = tab2.insertRow();
					}
					row.height=cssHeight;
					var cells = rowInfo.split('%');
					
					for(var j=0; j<cells.length; j++)
					{
						var cell = row.insertCell();
						if(rowInfo.indexOf('#') == 0)
					    {
							if(j==0)
							{
								cells[j]=cells[j].substring(1);
								
								var subItem = trim(cells[j]);
								
								var tmplen = getRowsByByteLen(subItem);
								if(lrFlag <= totalLr)
								{
									leftTabRows ++;
									if(tmplen > 0)
									{
										  isBr = tmplen;
									    leftTabRows +=tmplen;
								  }
								}
								else
								{
									rightTabRows ++;
									if(tmplen > 0)
									{
										  isBr = tmplen;
									    rightTabRows +=tmplen;
									}
								}
								
								if(isBr > 0)
								{
									 cellH = (isBr+1)*cssHeight;
									 cell.style.height = cellH;
									 isBr = 0;
								}


								
								cell.innerHTML = "<font size=1>" + subItem + "</font>";
								cell.align = "left";
								cell.style.paddingLeft = "30px";
							}
							else
							{
								cell.innerHTML = "<font size=2>" + cells[j] + "</font>";
								//cell.align = "left";
								cell.style.paddingRight = "20px";
							}
						}
						else
						{
							cell.innerHTML = "<font size=1 color='yellow'><b>" + cells[j] + "</b></font>";
						    cell.align = "left";
						}
					}	
				}
				
				var totalFeeDesc = allNodes[length-1].text;
				
				var feeArray = totalFeeDesc.split('%');
				
				var frmHeight = totalLr * cssHeight;
				var maxRows = getMaxRows(tab1,tab2);
		  	 	frmHeight += maxRows*cssHeight;
		  	 	
		  	 	createTail(tab1,tab2,'',feeArray[1]);
		  	 	
		  	 	frmHeight+=30;
		  	 	
		  	 	if(frmHeight < 80)
		  	 	{
		  	 		frmHeight += 120;
		  	 	}
		  	 	else if(frmHeight >= 80 && frmHeight < 300)
		  	 	{
		  	 		frmHeight += 160;
		  	 	}
		  	 	else if(frmHeight >= 300 && frmHeight < 400)
		  	 	{
		  	 		frmHeight += 200;
		  	 	}
		  	 	else if(frmHeight >= 400 && frmHeight < 500)
		  	 	{
		  	 		frmHeight += 240;
		  	 	}
		  	 	else if(frmHeight >= 500)
		  	 	{
 		  	 		frmHeight = frmHeight*15/10;
		  	 	}
		  	 	else
		  	 	{
		  	 		frmHeight += 120;
		  	 	}
		  	 	
				changeHeight(frmHeight);
				
				changeStartPrint();
			}
			
			function getMaxRows(tab1,tab2)
			{
				var l_tab1 = leftTabRows;
				var l_tab2 = rightTabRows;
				
				return (l_tab1 > l_tab2)? (l_tab1 -2) : (l_tab2 -2);
			}
			
			function createHead(tab1,tab2)
			{
				for(var k=0; k<2;k++)
		  	 	{
		  	 		if(0 == k)
		  	 		{
		  	 			var row = tab1.insertRow();
		  	 		}
		  	 		if(1 == k)
		  	 		{
		  	 			var row = tab2.insertRow();
		  	 		}
		  	 		row.style.backgroundColor ='#8B4513';
		  	 		row.height=30;
					//var cell = row.insertCell();
						var cell =document.createElement("th");
					
					
					
					row.appendChild(cell);
					cell.width=250;
					cell.innerHTML = "<font size=1>费用名称</font>";
					cell.align='left';
					//var cell = row.insertCell();
						var cell =document.createElement("th");
					
					
					
					row.appendChild(cell);
					cell.width=150;
					cell.innerHTML = "<font size=1>金额(元)</font>";
					cell.align='left';
		  	 	}
			}
			
			function createTail(tab1,tab2,fee1,fee2)
			{
				var l_tab1 = leftTabRows;
				var l_tab2 = rightTabRows;

				if(l_tab1 > l_tab2)
				{
					for(var i=l_tab2; i<l_tab1; i++)
					{
						var row = tab2.insertRow();
						row.height=cssHeight;
						var cell = row.insertCell();
						var cell = row.insertCell();
					}
				}
				if(l_tab1 < l_tab2)
				{
					for(var i=l_tab1; i<l_tab2; i++)
					{
						var row = tab1.insertRow();
						row.height=cssHeight;
						var cell = row.insertCell();
						var cell = row.insertCell();
					}
				}
				
		  	 	var row = tab1.insertRow();
		  	 	//row.style.backgroundColor ='#8B4513';
		  	 	row.height=30;
				//var cell = row.insertCell();\]
				var cell =document.createElement("th");
				row.appendChild(cell);
				cell.width=250;
				cell.innerHTML = "<font size=1><b>" +  '费用合计' + "</b></font>";
				cell.align='left';
				//var cell = row.insertCell();
				var cell =document.createElement("th");
				row.appendChild(cell);
				cell.width=150;
				cell.innerHTML = "<font size=1>" + fee1 + "</font>";
				cell.align='left';
				
				var row = tab2.insertRow();
		  	 	//row.style.backgroundColor ='#8B4513';
		  	 	row.height=30;
				//var cell = row.insertCell();
				var cell =document.createElement("th");
				row.appendChild(cell);
				cell.width=250;
				cell.innerHTML = "<font size=1><b>" +  '' + "</b></font>";
				cell.align='left';
				//var cell = row.insertCell();
				var cell =document.createElement("th");
				row.appendChild(cell);
				cell.width=150;
				cell.innerHTML = "<font size=1>" + fee2 + "</font>";
				cell.align='left';
			}
	</script>
  </body>
</html>
