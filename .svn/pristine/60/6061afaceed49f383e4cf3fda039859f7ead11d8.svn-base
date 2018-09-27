<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/newAdd.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		
	}			
}

// 处理键盘事件
document.onkeydown = keyDown;
function keyDown(e)
{
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}

// 套餐查询
function qryTelProdList(qryType)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		var rightDivHtml = document.getElementById("rightDiv").innerHTML;
		document.getElementById("rightDivHtml").value = rightDivHtml;
			 
		document.getElementById("qryType").value = qryType;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }telProdDiy/qryTelProdList.action";
		document.actform.submit();
	}
}

// 下一页
function nextPage(linkURL)
{	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		var rightDivHtml = document.getElementById("rightDiv").innerHTML;
		document.getElementById("rightDivHtml").value = rightDivHtml;

		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}
}

// 点击加入我的套餐
function addClick(qryType,btn,goods_type)
{
	if('5'== goods_type)
	{
		
		var btns = document.getElementById(qryType).getElementsByTagName('a');
		
		if(btn.innerHTML == "<SPAN class=orange_FFD543>取消加入</SPAN>")
		{
			btn.innerHTML = "加入我的套餐";
			
			var inputs = document.getElementById("spSelect").getElementsByTagName('div');
			for(var i=0; i<inputs.length; i++)
			{
				if(inputs[i].id == btn.value)
				{
					inputs[i].parentNode.removeChild(inputs[i]);
				}
			}			
		}
		else
		{
			btn.innerHTML = "<span class=\"orange_FFD543\">取消加入</span>";
		}
				
		var addHtml = document.getElementById("spSelect").innerHTML;
		if(addHtml == "<DIV class=listContent></DIV>")
		{
			addHtml = "";
		}
		
		var inputs = document.getElementById("spSelect").getElementsByTagName('div');
		
		for(var i=0; i<btns.length; i++)
		{		
			var flag = 0;
			for(var j=0; j<inputs.length; j++)
			{
				if(inputs[j].id == btns[i].value)
				{
					flag = 1;
					break;
				}
			}		
			if(flag == 0)
			{
				if(btns[i].innerHTML == "<SPAN class=orange_FFD543>取消加入</SPAN>")
				{
					var selectHtml =  "<div class='listContent' id='" + btns[i].value + "'>" + btns[i].id + "</div>";
					addHtml = addHtml + selectHtml;
				}
			}
		}
		
		if(addHtml == "")
		{
			addHtml = "<div class='listContent'></div>";
		}
		
		document.getElementById("spSelect").innerHTML = addHtml;
				
		myScroll.moveDown(100);
	}
	else
	{
		var typeName; 
	
		if(goods_type == '1')
		{
			typeName = "本地通话";
		}
		else if(goods_type == '2')
		{
			typeName = "长途通话";
		}
		
		var btns = document.getElementById(qryType).getElementsByTagName('a');
		
		if(btn.innerHTML == "<SPAN class=orange_FFD543>取消加入</SPAN>")
		{
			btn.innerHTML = "加入我的套餐";
			
			if('1' == goods_type || '2' == goods_type)
			{
				document.getElementById("yySelect").innerHTML= "<div class='listContent'></div>";
				
			}
			else if('3' == goods_type)
			{
				document.getElementById("gprsSelect").innerHTML= "<div class='listContent'></div>";
			}
			else if('4' == goods_type)
			{
				document.getElementById("wlanSelect").innerHTML= "<div class='listContent'></div>";
			}
			
		}
		else
		{ 
			for(i=0; i<btns.length; i++)
			{		
				if(btns[i].className== "addMyPackage" )
				{
					if('1' == goods_type || '2' == goods_type || '5' == goods_type)
					{
						btns[i].innerHTML="加入我的套餐";
					}
					else if('3' == goods_type && btns[i].name == "href3")
					{
						btns[i].innerHTML="加入我的套餐";
					}
					else if ('4' == goods_type && btns[i].name == "href4")
					{
						btns[i].innerHTML="加入我的套餐";
					}
				}
			}
			
			btn.innerHTML="<span class=\"orange_FFD543\">取消加入</span>";
			
			if('1' == goods_type || '2' == goods_type)
			{
				document.getElementById("yySelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + typeName+ btn.id + "</div>";
			}
			else if('3' == goods_type)
			{
				document.getElementById("gprsSelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
			}
			else if('4' == goods_type)
			{
				document.getElementById("wlanSelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
			}
			
		}
	}
	var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
	var inputStr = "";
	for(var i=0; i<inputs.length; i++)
	{
		if(inputs[i].className == "listContent" && ''!= inputs[i].innerHTML)
		{
			if('' == inputStr)
			{
				inputStr = inputs[i].innerHTML;
			}
			else
			{
				inputStr = inputStr + "," + inputs[i].innerHTML;
			}
		}
	}
	var totleFee = 0;
	
	if('' == inputStr)
	{
		document.getElementById("totleFee").innerHTML = "0.00" ;
	}
	else
	{
		var inputArray = inputStr.split(",");
		for(var i=0; i<inputArray.length; i++)
		{
			var input = inputArray[i].split(" ")[1];
			totleFee = parseInt(totleFee) + parseInt(input.substring(0,input.length-3));
		}
		
		document.getElementById("totleFee").innerHTML = totleFee +".00" ;
		document.getElementById("totalHidden").value = totleFee +".00" ;
	}
}

// 查看详情(类型、名称、费用、适合人群、业务说明、资费)
function openDetail(goods_type,item_name,price,suitAble_people,busDesc,feestandard,goods_id)
{
	var typeid = goods_type;
	var winTextHtml= document.getElementById('winText').innerHTML;
	if(goods_type == '1')
	{
		goods_type = "本地通话 ";
	}
	else if(goods_type == '2')
	{
		goods_type = "长途通话 ";
	}
	else if(goods_type == '3')
	{
		goods_type = "数据流量 ";
	}
	else if(goods_type == '4')
	{
		goods_type = "WLAN ";
	}
	else if(goods_type == '5')
	{
		goods_type = "";
	}
	document.getElementById('winText').innerHTML = "";
	document.getElementById('winText').innerHTML = 
	  '<span class="yellow" style="text-align: center; width: 620px; font-size:28px; font-weight:bold; display: block;">' + goods_type + item_name + " " + price+'</span>'
	+ '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;" >适合人群</span>'
	+ '<span style="float:left; display:block; width:420px; text-align:left;">' + suitAble_people + '</span><span class="clear"></span>'
	+ '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">业务说明</span>'
	+ '<span style="float:left; display:block; width:420px; text-align:left;">' + busDesc + '</span><span class="clear"></span>'
	+ '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">资费说明</span>'
	+ '<span style="float:left; display:block; width:420px; text-align:left;">' + feestandard + '</span><span class="clear"></span>';
	
	document.getElementById('openDiv').value = 'listDiv' + ","+ item_name + "," + price + "," + typeid;
	
	document.getElementById("addbutton").innerHTML="加入我的套餐";
	
	var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
	for(var i=0; i<inputs.length; i++)
	{
		if(inputs[i].className == "listContent")
		{
			var good = inputs[i].id.split(';')[0];
			if(good == goods_id)
			{
				document.getElementById("addbutton").innerHTML = "取消加入";
				break;
			}
		}
	}
	
	openWindow('openWin1');
}

// 详情div，加入我的套餐
function divAdd()
{
	var addArray = document.getElementById('openDiv').value.split(",");
	var objid = addArray[1] + " " + addArray[2];
	addClick(addArray[0],document.getElementById(objid),addArray[3]);
	wiWindow.close();
}

// 一键办理
function recSubmit()
{	
	var yySelect = document.getElementById("yySelect").getElementsByTagName('div');
	if(''== yySelect[0].id)
	{
		alertRedErrorMsg("请选择语音通话！");
		return;
	}
	else
	{
		document.getElementById("ncode").value = yySelect[0].id.split(";")[1];
	}
	
	// 产品包,增值产品,优惠;产品包,增值产品,优惠
	var productset = "";
	var gprsSelect = document.getElementById("gprsSelect").getElementsByTagName('div');
	if(''!= gprsSelect[0].id)
	{
		productset = productset + gprsSelect[0].id.split(";")[2];
	}
	
	var wlanSelect = document.getElementById("wlanSelect").getElementsByTagName('div');
	if(''!= wlanSelect[0].id)
	{
		productset = productset + ";" + wlanSelect[0].id.split(";")[2] + ";" ;
	}
	
	document.getElementById("productset").value = productset;
	
	// 选择的sp
	var spStr = "";
	
	var spSelect = document.getElementById("spSelect").getElementsByTagName('div');
	
	for(var i=0; i<spSelect.length; i++)
	{
		if(''!= spSelect[i].id)
		{
			if('' == spStr)
			{
				spStr = spSelect[i].id.split(";")[3];
			}
			else
			{
				spStr = spStr + ";" + spSelect[i].id.split(";")[3];
			}
		}
	}
	document.getElementById("spStr").value = spStr;
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }telProdDiy/recSubmit.action";
		document.actform.submit();
	}
	
}
</script>

<style>
	.yellow{color:#ffd543;}
	.lh1_7{line-height:1.7em;}
	.clear{clear:both;}
</style>

</head>
<body scroll="no">
	<form name="actform" method="post">  
		<input type="hidden" name="qryType" id="qryType" value="<s:property value='qryType' />">
		<input type="hidden" name="ncode" id="ncode" value="">
		<input type="hidden" name="productset" id="productset" value="">
		<input type="hidden" name="spStr" id="spStr" value="">
		<input type="hidden" name="rightDivHtml" id="rightDivHtml" value="<s:property value='rightDivHtml' escape="false" />">
		<input type="hidden" name="openDiv" id="openDiv" value="">
		<input type="hidden" name="totalHidden" id="totalHidden" value="<s:property value='totalHidden'/>">	
		
		<%@ include file="/titleinc.jsp"%>			
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="selectPackage_mainContent">
		
				<!-- 左侧内容 开始 -->
				<div class="selectPackage_leftContent">
		
					<div class="titleNav">
						<s:if test="qryType == 'MAINPROD'">
							<a href="javascript:void(0)" class="btn1_selected"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn1"  onclick="qryTelProdList('MAINPROD');return false;"></a>
						</s:else>
						<div class="icon_plus"></div>
						
						<s:if test="qryType == 'GPRSWLAN'">
							<a href="javascript:void(0)" class="btn2_selected"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn2"  onclick="qryTelProdList('GPRSWLAN');return false;"></a>
						</s:else>
						<div class="icon_plus"></div>
						
						<s:if test="qryType == 'SP'">
							<a href="javascript:void(0)" class="btn3_selected"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn3"  onclick="qryTelProdList('SP');return false;"></a>
						</s:else>
						<div class="icon_equal"></div>
					</div>
					<div class="clear"></div>
					
					<!-- btn1对应的内容 开始 -->
					<div class="packageListBody" id="listDiv">
						<s:if test="telProdList.size == 0">
							<div class="packageList_title">
							没有可选套餐
							</div>
						</s:if>
						<s:if test="telProdList1 != null && telProdList1.size > 0 && page == 1">
							<div class="packageList_title">
								<s:iterator value="telProdList1" id="prod" status="status">
									<s:if test = "#status.index == 0">
										<s:property value="#prod.tcDesc" escape="false"/>
									</s:if>
								</s:iterator>
							</div>
 						</s:if>
						
						<s:if test="telProdList1 != null && telProdList1.size > 0">
							<div class="packageList_content">
								<div class="topBg"></div>
								<div class="middleBg">
									<s:iterator value="telProdList1" id="prod" >
	 									<div class="datalist">
											<div class="info">
												<a  href="javascript:void(0)"
													onclick="openDetail(
													'<s:property value="#prod.goods_type"/>',
													'<s:property value="#prod.item_name" escape="false"/>',
													'<s:property value="#prod.price" escape="false"/>',
													'<s:property value="#prod.suitAble_people" escape="false"/>',
													'<s:property value="#prod.busDesc" escape="false"/>',
													'<s:property value="#prod.feestandard" escape="false"/>',
													'<s:property value="#prod.goods_id"/>'
													);">
													<s:property value="#prod.item_name" escape="false"/>&nbsp;
													<s:property value="#prod.price" escape="false"/>
												</a>
											</div>
											<a href="javascript:void(0)" class="addMyPackage"  
												value="<s:property value="#prod.goods_id"/>;<s:property value="#prod.ncode"/>;<s:property value="#prod.packageID"/>,<s:property value="#prod.addProdID"/>,<s:property value="#prod.privID"/>;<s:property value="#prod.spID" />,<s:property value="#prod.spBusID" />"
												name="href3" id="<s:property value="#prod.item_name" escape="false"/> <s:property value="#prod.price" escape="false"/>" 
												onclick="addClick('listDiv',this,
												'<s:property value="#prod.goods_type" />'
												)" >
												加入我的套餐
											</a>
										</div>
		 							</s:iterator>
		 							<div class="clear"></div>
	 							</div>
								<div class="bottomBg"></div>
							</div>
 						</s:if>
						
 						<s:if test="telProdList2 != null && telProdList2.size > 0">
							<div class="packageList_title" id="listTitle2">
								<s:iterator value="telProdList2" id="prod" status="status">
									<s:if test = "#status.index == 0">
										<s:property value="#prod.tcDesc" escape="false"/>
									</s:if>
								</s:iterator>
							</div>
 						</s:if>
 						
						<s:if test="telProdList2 != null && telProdList2.size > 0">
							<div class="packageList_content">
								<div class="topBg"></div>
								<div class="middleBg">
									<s:iterator value="telProdList2" id="prod">
	 									<div class="datalist">
											<div class="info">
												<a  href="javascript:void(0)" 
													onclick="openDetail(
													'<s:property value="#prod.goods_type"/>',
													'<s:property value="#prod.item_name" escape="false"/>',
													'<s:property value="#prod.price" escape="false"/>',
													'<s:property value="#prod.suitAble_people" escape="false"/>',
													'<s:property value="#prod.busDesc" escape="false"/>',
													'<s:property value="#prod.feestandard" escape="false"/>',
													'<s:property value="#prod.goods_id"/>'
													);">
													<s:property value="#prod.item_name" escape="false"/>&nbsp;
													<s:property value="#prod.price" escape="false"/>
												</a>
											</div>
											<a href="javascript:void(0)" class="addMyPackage"  
												value="<s:property value="#prod.goods_id"/>;<s:property value="#prod.ncode"/>;<s:property value="#prod.packageID"/>,<s:property value="#prod.addProdID"/>,<s:property value="#prod.privID"/>;<s:property value="#prod.spID" />,<s:property value="#prod.spBusID" />"
												name="href4" id="<s:property value="#prod.item_name" escape="false"/> <s:property value="#prod.price" escape="false"/>" 
												onclick="addClick('listDiv',this,
												'<s:property value="#prod.goods_type" />'
												)" >
												加入我的套餐
											</a>
										</div>
		 							</s:iterator>
									<div class="clear"></div>
			 					</div>
								<div class="bottomBg"></div>
							</div>
 						</s:if>
						<div class="clear"></div>
						
						<!-- 分页 开始 -->
						<div class="sortPages">
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
						</div>
						<div class="clear"></div>
						<!-- 分页 结束 -->
					</div>
					<!-- btn1对应的内容 结束 -->
					<div class="clear"></div>
				</div>
				<!-- 左侧内容 结束 -->
		
				<!-- 右侧内容 开始 -->
				<div class="selectPackage_rightContent" style="position:relative;">
					<div class="rightTitle"></div>
					
					<!-- 滚动条 开始 -->
					<div class="scrollBody">
					
						<div class="packageList" id="inn">
							<div class="packageList_body" id="rightDiv">
								<div class="listTitle">语音通话（必选）</div>
								<div id="yySelect"><div class="listContent" ></div></div>
								
								<div class="listTitle">上网流量（可选）</div>
								<div id="gprsSelect"><div class="listContent" ></div></div>
								<div id="wlanSelect"><div class="listContent" ></div></div>
																
								<div class="listTitle">数据业务（可选）</div>
								<div id="spSelect"><div class="listContent" ></div></div>
							</div>
						</div>
						
						<div class="scrollBar">
							<input type="button" class="btnTop" onclick="myScroll.moveUp(30)" />
							<input type="button" style="left:-4px;" class="btnDown" onclick="myScroll.moveDown(30)" />
							<div class="iconScroll" id="gunDom">0%</div>
						</div>
							
					</div>
					<!-- 滚动条 结束 -->
					<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom","1");</script>
					
					<div class="rightContent_text" style="position:absolute; bottom:-120px; right:10px;">
						我的套餐总计<span class="orange_FFD543" id="totleFee"><s:property value="totalHidden"/></span>元<br />
						办理后次月生效<br /><br />
						<input type="button" class="btn_deal" onclick="recSubmit();return false;" />
					</div>
				</div>
				<!-- 右侧内容 结束 -->
				
			</div>
			
			<!--弹出窗 BEGIN-->
			<div class="detail_tip" id="openWin1">
				<div class="bg"></div>
				<div class=" blank60"></div>
				<p class="fs28 lh1_7 pl50 pr50" id="winText"
					name="winText" style="font-size:24px;">
				</p>
				<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
					<a  href="javascript:void(0)" class=" bt4"
						onmousedown="this.className='bt4on';"
						onmouseup="this.className='bt4';wiWindow.close();">关闭详情</a>
					<a id="addbutton" href="javascript:void(0)" class=" bt4 ml20"
						onmousedown="this.className='bt4on ml20';divAdd();return false;"
						onmouseup="this.className='bt4 ml20';wiWindow.close();">加入我的套餐</a>
				</div>
			</div>
			<!-- 弹出窗 END -->
			<script type="text/javascript">
			openWindow = function(id){
				wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
			}
			</script>
			
		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<script type="text/javascript">
var leftHtml = document.getElementById('rightDivHtml').value;
if('' != leftHtml)
{
	document.getElementById('rightDiv').innerHTML = document.getElementById('rightDivHtml').value;
	
	var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
	var inputStr = "";
	for(var i=0; i<inputs.length; i++)
	{
		if(inputs[i].className == "listContent")
		{
			inputStr = inputStr + "#" + inputs[i].id;
		}
	}
	
	var inputArray = inputStr.split('#');
	var btns = document.getElementById('listDiv').getElementsByTagName('a');
	
	for(var i=0;i< inputArray.length;i++)
	{  			
		for(var j=0;j<btns.length;j++ )
		{
			if(btns[j].className== "addMyPackage" && btns[j].value == inputArray[i])
			{	
				btns[j].innerHTML = "<span class=\"orange_FFD543\">取消加入</span>";
				break;
			}
		}
	}
	
}

</script>
</html>
