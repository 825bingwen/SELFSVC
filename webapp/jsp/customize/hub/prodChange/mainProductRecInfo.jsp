<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%	
	String isShowReason = (String)PublicCache.getInstance().getCachedData(Constants.SH_ISSHOWREASON);
%>
<html>
	<head>
		<title>可转换的主体产品信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script>
var submitFlag = 0;
	
function doSub(toProdId,toProdName,toProdBrand)
{	
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.getElementById("toProdId").value = toProdId;
		document.getElementById("toProdName").value = toProdName;
		document.getElementById("toProdBrand").value = toProdBrand;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }prodChange/getProdTmpList.action";
		document.actform.submit();
		fDisableBtn();
	}
}

function fDisableBtn()
{
    var obj = document.getElementsByTagName("input");
    for(var i=0; i<obj.length; i++)   
    {   
		var tmpObj = obj[i];   
		if(tmpObj.type == "button" && tmpObj.className.indexOf("bt2") != -1)   
		{
			tmpObj.disabled = true; 
		}   
	}   
}


document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;
function pwdKeyboardDown(e)
{
	var key = GetKeyCode(e);
	//更正
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//这句话最关键
	}
}

function KeyIsNumber(KeyCode) 
{
	//只允许输入0-9
	if (KeyCode >= 48 && KeyCode <= 57)
	{
		return true;
	}
	
	return false;
}


function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	if (key == 82 || key == 220)
	{
		goback("<s:property value='#request.curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

function mainProductRec(prinType,formnum)
			{		
					document.actform.target="_self";
					document.actform.action="${sessionScope.basePath }prodChange/mainProductRecInfo.action";
					document.actform.submit();				
			}
//确认按钮			
function mainProductChangeSubmit(prodName)
	{		
		document.getElementById('winText').innerHTML = '<span class="yellow">尊敬的客户<s:property value="servnumber"/></span><br />您正在办理主体产品变更业务 <br />您确定将主体产品变更为 '+prodName+' 吗？';
		openWindow('openWin1');
	}
//执行提交变更
function exeMainProdCHange()
{
	setTimeout(
		function(){
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }prodChange/mainProductChangeSubmit.action";
			document.actform.submit();							
		}, 500);	
	}


//取消按钮			
function mainProductChangeCancel()
	{	
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }prodChange/qryMainProdTempChangeList.action";
			document.actform.submit();			
	}
	
//显示详情信息内容

function shouDetailInfo (contentStr)
{
		document.getElementById('winText2').innerHTML = contentStr;
		openWindow2('openWin2');

}

</script>
	</head>
	<BODY scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="fromProdId" name="fromProdId" value="<s:property value="fromProdId"/>" />
			<input type="hidden" id="prodSN" name="prodSN" value="<s:property value="prodSN"/>" />			
			<input type="hidden" id="toProdId" name="toProdId" value="<s:property value="toProdId"/>" />
			<input type="hidden" id="ncode" name="ncode" value="<s:property value="ncode"/>" />
			<input type="hidden" id="newProdName" name="newProdName" value="<s:property value="newProdName"/>" />
			<input type="hidden" id="region" name="region" value="<s:property value="region"/>" />
			
			<input type="hidden" id="chgdesp" name="chgdesp" value="<s:property value="chgdesp"/>" />			
			<input type="hidden" id="servnumber" name="servnumber"
				value="<s:property value="servnumber"/>" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<h1>
					<span></span><%=menuName %></h1>

				<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div
								style="height: 444px; padding: 0px 0px 5px 0px; width: 747px; overflow: hidden;">
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn"  style="height: 411px; width: 747px; overflow: hidden;">								
								<table width="100%" class="tb_blue">
									<%--modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									<tr>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<th scope="col" colspan="8">
										</s:if>
										<s:else>
											<th scope="col" colspan="7">
										</s:else>										
											需开通的业务
										</th>
									</tr>
									<%--modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									<tr >
										<th scope="col" >
											套餐名称
										</th>
										<th scope="col" >
											套餐开始时间
										</th>
										<th scope="col" >
											套餐结束时间
										</th>
										<th scope="col" >
											套餐包名称
										</th>
										<th scope="col" >
											优惠名称
										</th>
										<th scope="col" >
											优惠开始时间
										</th>
										<th scope="col" >
											优惠结束时间
										</th>
										<%--add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<th scope="col" >
												特别提示
											</th>
										</s:if>
										<%--add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									</tr>
									<s:iterator value="openProdList" status="openProdList" id = "openProdList_i" >
									<tr>
										<td scope="col">
												<s:property value='#openProdList_i.prodname'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.begindate'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.enddate'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.pkgname'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.privname'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.privbegindate'/>
										</td>
										<td scope="col" >
												<s:property value='#openProdList_i.privenddate'/>
										</td>
										<%--add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<td scope="col" >
												<s:property value='tipsMap[#openProdList_i.prodid + "_Product"]'/>
											</td>
										</s:if>
										<%--add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									</tr>
									</s:iterator>
								</table>
								
								
									<br/>
								<table width="100%" class="tb_blue">
									<%--modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									<tr>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<th scope="col" colspan="9">
										</s:if>
										<s:else>
											<th scope="col" colspan="8">
										</s:else>										
											需取消的业务
										</th>
									</tr>
									<%--modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									<tr>
										<th scope="col">
											套餐名称
										</th>
										<th scope="col" >
											套餐开始时间
										</th>
										<th scope="col" >
											套餐结束时间
										</th>
										<th scope="col" >
											套餐包名称
										</th>
										<th scope="col" >
											优惠名称
										</th>
										<th scope="col" >
											优惠开始时间
										</th>
										<th scope="col" >
											优惠结束时间
										</th>
										<%if(!Constants.SH_ISSHOWREASON_N.equals(isShowReason))
										{
										 %>
										 <th scope="col" >
											取消原因
										</th> 
										<%   } %>
										<%--add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<th scope="col" >
												特别提示
											</th>
										</s:if>
										<%--add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									</tr>
									<s:iterator value="cancelProdList" status="cancelProdList" id = "cancelProdList_i" >
									<tr>
										<td scope="col">
												<s:property value='#cancelProdList_i.prodname'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.begindate'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.enddate'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.pkgname'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.privname'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.privbegindate'/>
										</td>
										<td scope="col" >
												<s:property value='#cancelProdList_i.privenddate'/>
										</td>	
										<%if(!Constants.SH_ISSHOWREASON_N.equals(isShowReason))
										{
										 %>
										<td scope="col" >
												<a href="javascript:void(0)" onclick ='shouDetailInfo("<s:property value='#cancelProdList_i.reason'/>")'> 详情 </a>
										</td>
										<%} %>
										<%--add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
										<s:if test="tipsMap != null && tipsMap.size() > 0">
											<td scope="col" >
												<s:property value='tipsMap[#cancelProdList_i.prodid + "_Product"]'/>
											</td>
										</s:if>
										<%--add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
									</tr>
									</s:iterator>
								</table>
								<input type="button" class="bt2_liebiao" style="color:#FFFFFF;"
							                    	  value="确认" onmousedown="" onmouseup="" onclick="mainProductChangeSubmit('<s:property value="newProdName"/>')" >	
							                    	  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
							                    	  <input type="button" class="bt2_liebiao" style="color:#FFFFFF;"
							                    	  value="取消" onmousedown="" onmouseup="" onclick="mainProductChangeCancel()" >	
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width: 75px; height: 350px;">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom"
									style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">
									0%
								</div>
							</div>
							<input type="button" class="btnDown"
								onclick="myScroll.moveDown(30)" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll('inn',"gunDom");</script>
				<!--滚动条结束-->
				<!--弹出窗-->
				<div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
				  	<div class="bg"></div>
					<div class=" blank60"></div>				
					<p class="fs28 lh2" style="padding-left:50px;padding-right:50px;" id="winText" name="winText">
						
					</p>
  					<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
    					<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();exeMainProdCHange()">确认</a> 
    					<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
    				</div>
				</div>
				
					<div class="openwin_tip" id="openWin2" style="width:708px; height:392px;">
				  	<div class="bg"></div>
					<div class=" blank60"></div>
					<div class=" blank60"></div>					
					<p class="fs28 lh2" style="padding-left:30px;padding-right:30px;" id="winText2" name="winText2">
						
					</p>
  					<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
    					<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">确定</a> 
    				</div>
				</div>
				<script type="text/javascript">
				openWindow = function(id){
					
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
				}
				openWindow2 = function(id){
					
					wiWindow = new OpenWindow("openWin2",708,392);//打开弹出窗口例子					
				}
				</script>
				<!--弹出窗结束-->		
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
