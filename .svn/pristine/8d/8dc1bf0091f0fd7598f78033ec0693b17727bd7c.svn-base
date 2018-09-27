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
			
.my_box737W .bg { width:737px; height:600px; background:url(../images/bg_scroll.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_scroll.png");_background:none}
.my_box737W .top{height:10px; overflow:hidden;  width:735px; overflow:hidden;}
.my_box737W .con{padding:0 10px; width:715px; height:775px; overflow:hidden;}
.my_box737W .btm{height:15px; overflow:hidden; width:735px; overflow:hidden; }

.my_box662W{width:662px; overflow:hidden;}
.my_box662W .top{height:10px; overflow:hidden;width:662px; background:url(../images/bg_747_a.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_747_a.png");_background:none}
.my_box662W .con{height:544px; padding:0px; width:662px; overflow:hidden; background:url(../images/bg_747_b.png) repeat-y; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_747_b.png");_background:none;}
.my_box662W .btm{height:10px; overflow:hidden;width:662px; background:url(../images/bg_747_c.png) no-repeat; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_747_c.png");_background:none}

.tb_blue_08{border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px;height:30px;}
.tb_blue_08 td,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
.tb_blue_08 th{ background:#0b8ac1;border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}	
		</style>
  </head>
  
  <body onload="qryHisBillIoDe();">
   <!--滚动条-->
<div id="scrollDiv" style="display:block">
		<div class="my_box737W fl ml20">
			      <div class="bg"></div>
				  <div class="top"></div>
				  <div class="con relative" >
						<div class="my_box662W fl">
								 <div style="height:544px; padding:0px 0px 5px 0px;  overflow:hidden;">
								 <p class="ptop180 tc" id="inn" style="height:511px;   overflow:hidden;" >
								 <table id="hugeIoDeTab" class="tb_blue_08" width="85%">
												<tr>
													<td width="100%" align="left">
														<p class="margin-top:0px;" align="left">
														<img src="${sessionScope.basePath }images/loading2.gif" />
														<font size="1">正在查询账户明细，请稍候......</font>
														</p>
												    </td>
												</tr>
								 </table>
								 </div>
						</div>
						<div  style="height:550px;margin-top:0px;fr">
									<div class="boxPage" style="width:75px; height:511px; ">
											<div class="blank10px"></div>
											<div class="box66W tc f16 lh30" id="gunDom" style=" width:64px; height:40px; position:absolute; cursor:move; left:626px; top:0px; line-height:36px" >0%</div>
									</div>
						</div>
						<div class="clear"></div>
					</div>
		</div>
		 <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
</div>

<div id="defDiv" style="display:none">
				<table id="hisIoDeTab" class="tb_blue_08" width="100%">
										<tr>
											<td width="100%" align="left">
												<p class="margin-top:0px;" align="left">
												<img src="${sessionScope.basePath }images/loading2.gif" />
												<font size="1">正在查询账户明细，请稍候......</font>
												</p>
										    </td>
										</tr>
				</table>
</div>
<!--滚动条结束-->
  </body>
</html>
<script type="text/javascript">
			function changeHeight(hei)
			{
				try
				{
					parent.document.getElementById("ifr_his_iode").style.height = hei;
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
			
			function qryHisBillIoDe()
			{
				var url = "${sessionScope.basePath }hubfeeservice/hisBillAccDetail.action";
				
				var loader1 = new net.ContentLoader(url, netload = function () 
				{
					var resXml = this.req.responseXML;
					
					var root = resXml.documentElement;
							
					if('error' == root.tagName)
					{
						//alert("查询账户明细错误！");
						return;
					}
										
					if (resXml)
					{
						    disHisBillIoDe(resXml);									
					}
				    else
					{	
						//alert("查询账户明细错误！");
						return;
					}								
				}, null, "POST", "", null);
				
   				//execServiceXml(url,'',displayBillDetail);
			}
			function disHisBillIoDe(xmlData)
			{
				var tab;
				
				var root = xmlData.documentElement;
				var allNodes = root.childNodes;
		  	 	var length = allNodes.length;
		  	 	
		  	 	if(length >= 28)
		  	 	{
		  	 		document.getElementById('scrollDiv').style.display = 'block';
		  	 		document.getElementById('defDiv').style.display = 'none';
		  	 		tab = document.getElementById('hugeIoDeTab');
		  	 		tab.deleteRow();
		  	 	}
		  	 	else
		  	 	{
		  	 		document.getElementById('defDiv').style.display = 'block';
		  	 		document.getElementById('scrollDiv').style.display = 'none';
		  	 		tab = document.getElementById('hisIoDeTab');
		  	 		tab.deleteRow();
		  	 	}
		  	 	
		  	 	var frmHeight = 0;
											   
				for(var i=0; i<length; i++)
				{
					var row = tab.insertRow();
					
					
					
					
					
					
					frmHeight += 20;
					var rowInfo = allNodes[i].text;
					var cells = rowInfo.split('%');
					
					var tFlag = 'false';
					var cspanFlag = 'false';
					if('---------------------------------------' == rowInfo)
					{
						//取下一个
						rowInfo = allNodes[i+1].text;
						cells = rowInfo.split('%');
						if(0 == i)
						{
							rowInfo = cells[0] + '%时间%方式%入账金额%备注';
						}
						else
						{
							rowInfo = cells[0];
							cspanFlag = 'true';
						}
						//取Title
						cells = rowInfo.split('%');
						tFlag = 'true';
					}
					
					
					for(var j=0; j<cells.length;j++)
					{
							if('false' == tFlag)
							{
								cells[0] = '';
							}
							
							if(i==0){
					var cell =document.createElement("th");
					
					
					//var cell = head.insertCell();
					row.appendChild(cell);
					}else{
					var cell =document.createElement("td");
					
					
					//var cell = head.insertCell();
					row.appendChild(cell);
					
					
					}
							
							
							//var cell = row.insertCell();
							cell.innerHTML = "<font size=1>" + cells[j] + "</font>";
							cell.align = 'center';
							
							if('true' == cspanFlag)
							{
								cell.colspan = '5';
							}
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
				else if(frmHeight >= 500 && frmHeight < 600)
				{
					frmHeight = frmHeight*15/10;
				}
				else
			    {
			    	frmHeight = 800;
			    }
				
				changeHeight(frmHeight);
				
				changeStartPrint();
			}
</script>
