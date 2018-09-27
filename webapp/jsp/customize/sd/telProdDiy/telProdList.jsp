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
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/newAdd.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
<style>
	.yellow{color:#ffd543;}
	.lh1_7{line-height:1.7em;}
	.clear{clear:both;}
</style>

</head>
<body scroll="no">
	<form name="actform" method="post">  
	
		<s:hidden name="telProdPo.prodId" />
	    
	    <%--查询方式，VOICECALL：语音通话套餐，GPRSWLAN：上网流量套餐--%>
		<s:hidden name="telProdPo.qryType" id="qryType"/>
		
		<%--右侧div内容--%>
		<input type="hidden" name="rightDivHtml" id="rightDivHtml" value="<s:property value='rightDivHtml' escape="false" />">
		
		<%--弹出框内容--%>
		<input type="hidden" name="openDiv" id="openDiv" value="">
		
		<%--总费用--%>
		<input type="hidden" name="totalHidden" id="totalHidden" value="<s:property value='totalHidden'/>">	
        
        <%--语音通话套餐id--%>
        <s:hidden  name="telProdPo.voicePrivId" id="voicePrivId"/>
        
        <%--上网流量套餐id--%>
        <s:hidden  name="telProdPo.netPrivId" id="netPrivId"/>
        
        <%--语音通话套餐费用--%>
        <s:hidden id="voicePrice" name="telProdPo.voicePrice"/>	
        
        <%--上网流量套餐费用--%>
        <s:hidden id="netPrice" name="telProdPo.netPrice"/>	
		
		<%@ include file="/titleinc.jsp"%>			
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="selectPackage_mainContent">
		
				<!-- 左侧内容 开始 -->
				<div class="selectPackage_leftContent">
		
					<div class="titleNav">
						<s:if test="telProdPo.qryType == 'VOICECALL' || telProdPo.qryType == null">
							<a href="javascript:void(0)" class="btn1_selected"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn1" onclick="qryTelProdList('VOICECALL');return false;"></a>
						</s:else>
						<div class="icon_plus" style="margin-left:75px;"></div>
						
						<s:if test="telProdPo.qryType == 'GPRSWLAN'">
							<a href="javascript:void(0)" class="btn2_selected" style="margin-left:75px;"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn2" style="margin-left:75px;"  onclick="qryTelProdList('GPRSWLAN');return false;"></a>
						</s:else>
						
						<div class="icon_equal" style="margin-left:75px;"></div>
					</div>
					<div class="clear"></div>
					
					<!-- btn1对应的内容 开始 -->
					<div class="packageListBody" id="listDiv">
						<s:if test="voiceProdList.size > 0">
							<div class="packageList_title">
								<s:iterator value="voiceProdList" id="prod" status="status">
									<s:if test = "#status.index == 0">
										<s:property value="#prod.voiceDesc" escape="false"/>
									</s:if>
								</s:iterator>
							</div>
 						</s:if>
						
						<s:if test="voiceProdList.size > 0">
							<div class="packageList_content">
								<div class="topBg"></div>
								<div class="middleBg">
									<s:iterator value="voiceProdList" id="prod" >
	 									<div class="datalist">
											<div class="info">
												<a  href="javascript:void(0)"
													onclick="openDetail(
													'<s:property value="#prod.voicePrivId" escape="false"/>',
													'<s:property value="#prod.voiceName" escape="false"/>',
													'<s:property value="#prod.voiceFeeStan" escape="false"/>',
													'<s:property value="#prod.voicePrice" escape="false"/>',
													'<s:property value="#prod.voiceSuitPer" escape="false"/>',
													'<s:property value="#prod.voiceBusDesc" escape="false"/>',
													'1'
													)">
													<s:property value="#prod.voiceName" escape="false"/>
												</a>
											</div>
											<a href="javascript:void(0)" class="addMyPackage"  
												value="<s:property value="#prod.voicePrivId"/>,<s:property value="#prod.voiceName" escape="false"/>,"
												name="href3" id="<s:property value="#prod.voiceName" escape="false"/>" 
												onclick="addClick('<s:property value="#prod.voicePrivId" escape="false"/>','listDiv',this,'1','<s:property value="#prod.voicePrice"/>'
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
						
						<s:if test="netProdList.size > 0">
                            <div class="packageList_title">
                                <s:iterator value="netProdList" id="prod" status="status">
                                    <s:if test = "#status.index == 0">
                                        <s:property value="#prod.netDesc" escape="false"/>
                                    </s:if>
                                </s:iterator>
                            </div>
                        </s:if>
                        
                        <s:if test="netProdList.size > 0">
                            <div class="packageList_content">
                                <div class="topBg"></div>
                                <div class="middleBg">
                                    <s:iterator value="netProdList" id="prod" >
                                        <div class="datalist">
                                            <div class="info">
                                                <a  href="javascript:void(0)"
                                                    onclick="openDetail(
                                                    '<s:property value="#prod.netPrivId" escape="false"/>',
                                                    '<s:property value="#prod.netName" escape="false"/>',
                                                    '<s:property value="#prod.netFeeStan" escape="false"/>',
                                                    '<s:property value="#prod.netPrice" escape="false"/>',
                                                    '<s:property value="#prod.netSuitPer" escape="false"/>',
                                                    '<s:property value="#prod.netBusDesc" escape="false"/>',
                                                    '2'
                                                    )">
                                                    <s:property value="#prod.netName" escape="false"/>
                                                </a>
                                            </div>
                                            <a href="javascript:void(0)" class="addMyPackage"  
                                                value="<s:property value="#prod.netPrivId"/>,<s:property value="#prod.netName" escape="false"/>"
                                                name="href4" id="<s:property value="#prod.netName" escape="false"/>" 
                                                onclick="addClick('<s:property value="#prod.netPrivId" escape="false"/>','listDiv',this,'2','<s:property value="#prod.netPrice"/>'
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
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="telProdDiy/qryProdListByProdId.action" />
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
								
								<div class="listTitle">上网流量（必选）</div>
								<div id="gprsSelect"><div class="listContent" ></div></div>
							</div>
						</div>
						
						<div class="scrollBar">
							<input type="button" class="btnTop" onclick="myScroll.moveUp(30)" />
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
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
					    onmousedown="this.className='bt4on ml20';"
						onmouseup="this.className='bt4 ml20';divAdd();wiWindow.close();">加入我的套餐</a>
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
var submitFlag = 0;

// 上一页
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
		
		// 页面右侧内容
		var rightDivHtml = getObj("rightDiv").innerHTML;
		setValue("rightDivHtml",rightDivHtml);
		setValue("qryType",qryType);
		
		document.actform.action="${sessionScope.basePath }telProdDiy/qryProdListByProdId.action";
		document.actform.submit();
	}
}

// 下一页
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 页面右侧内容
		var rightDivHtml = getObj("rightDiv").innerHTML;
		setValue("rightDivHtml",rightDivHtml);

		document.actform.action=linkURL;
		document.actform.submit();
	}
}

// 点击加入我的套餐
function addClick(id,qryType,btn,goods_type,price)
{   
    <%--1：语音通话套餐，2：上网流量套餐--%>
    if ('1' == goods_type)
    {
        // 给隐藏域语音通话套餐id(voicePrivId)赋值
        setValue("voicePrivId",id);
    }
    else if ('2' == goods_type)
    {
        // 给隐藏域上网流量套餐id(netPrivId)赋值
        setValue("netPrivId",id);
    }
    
    // 获取id为"listDiv"div下的所有a标签
	var btns = document.getElementById(qryType).getElementsByTagName('a');
	
	// 当前对象内容为取消加入时，改成"加入我的套餐"
	if(btn.innerHTML == "<SPAN class=orange_FFD543>取消加入</SPAN>")
	{
		btn.innerHTML = "加入我的套餐";
		
		// 点击“取消加入”后，右侧框清空
		if('1' == goods_type)
		{
		    document.getElementById("voicePrice").value = "0";
			document.getElementById("yySelect").innerHTML= "<div class='listContent'></div>";
		}
		else if('2' == goods_type)
		{
		    document.getElementById("netPrice").value = "0";
			document.getElementById("gprsSelect").innerHTML= "<div class='listContent'></div>";
		}
	}
	
	// 当前对象内容为"加入我的套餐"时
	else
	{ 
	    // 所有a标签内容置为"加入我的套餐"
		for(i=0; i<btns.length; i++)
		{		
			if(btns[i].className== "addMyPackage" )
			{
				if('1' == goods_type)
				{
					btns[i].innerHTML="加入我的套餐";
				}
				else if('2' == goods_type && btns[i].name == "href4")
				{
					btns[i].innerHTML="加入我的套餐";
				}
			}
		}
		// 当前对象内容置为"取消加入"
		btn.innerHTML="<span class=\"orange_FFD543\">取消加入</span>";
		
		// 点击“加入我的套餐”后，右侧框显示相应内容
		<%--1：语音通话套餐，2：上网流量套餐--%>
		if('1' == goods_type)
		{
		    document.getElementById("voicePrice").value = price;
			document.getElementById("yySelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
		}
		else if('2' == goods_type)
		{
		    document.getElementById("netPrice").value = price;
			document.getElementById("gprsSelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
		}
	}
	
	// 获取右侧div下的所有div标签
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
    // 计算总费用
	if('' == inputStr)
	{	
		document.getElementById("totalHidden").value = "0.00" ;
		document.getElementById("totleFee").innerHTML = "0.00" ;
	} 
	else
	{   
	    // 语音通话套餐费用
        var voiceFee = getValue("voicePrice");
        
        if(voiceFee == "")
        {
        	voiceFee = "0";
        }
        
        // 上网流量套餐费用
        var netFee = getValue("netPrice");
        
        if(netFee == "")
        {
        	netFee = "0";
        }
        
        // 总费用
		var totleFee = parseInt(voiceFee) + parseInt(netFee);
		
		document.getElementById("totleFee").innerHTML = totleFee +".00" ;
		document.getElementById("totalHidden").value = totleFee +".00" ;
	}
}

// 查看详情(套餐id，套餐名称，资费标准，价格，套餐类型)
function openDetail(id,name,feestandard,price,suitPer,busDesc,goods_type)
{
	document.getElementById('winText').innerHTML = "";
    document.getElementById('winText').innerHTML = 
      '<span class="yellow" style="text-align: center; width: 620px; font-size:28px; font-weight:bold; display: block;">' + name +'</span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;" >适合人群</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + suitPer + '</span><span class="clear"></span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">业务说明</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + busDesc + '</span><span class="clear"></span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">资费说明</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + feestandard + '</span><span class="clear"></span>';
    
    document.getElementById('openDiv').value = 'listDiv' + ","+ name + "," + price + "," + goods_type + ',' +id;
    
    document.getElementById("addbutton").innerHTML="加入我的套餐";
    
    var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
    
    for(var i=0; i<inputs.length; i++)
    {
        if(inputs[i].className == "listContent")
        {
            var good = inputs[i].id.split(',')[0];
            if(good == id)
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
	var objid = addArray[1];
	addClick(addArray[4],addArray[0],document.getElementById(objid),addArray[3],addArray[2]);
	wiWindow.close();
}

// 一键办理
function recSubmit()
{	
	var yySelect = document.getElementById("yySelect").getElementsByTagName('div');
	var gprsSelect = document.getElementById("gprsSelect").getElementsByTagName('div');
	if(''== yySelect[0].id && ''== gprsSelect[0].id)
	{
		alertRedErrorMsg("尊敬的用户，语音通话套餐和上网流量套餐都是必选的，请选择！");
		return;
	}
	
	if(''== yySelect[0].id)
	{
		alertRedErrorMsg("尊敬的用户，语音通话套餐和上网流量套餐都是必选的，请选择语音通话套餐！");
		return;
	}
	
	if(''== gprsSelect[0].id)
	{
		alertRedErrorMsg("尊敬的用户，语音通话套餐和上网流量套餐都是必选的，请选择上网流量套餐！");
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }telProdDiy/recSubmit.action";
		document.actform.submit();
	}
	
}


//加入我的套餐
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
