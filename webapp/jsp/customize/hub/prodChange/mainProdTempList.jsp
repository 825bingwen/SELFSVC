<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	
	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
%>
<html>
	<head>
		<title>主体产品的模板列表</title>
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

function mainProductRec(ncode,fromProdId,prodSN,toProdId,newProdName)
			{			
					document.getElementById("ncode").value = ncode;	
					document.getElementById("fromProdId").value = fromProdId;	
					document.getElementById("prodSN").value = prodSN;	
					document.getElementById("toProdId").value = toProdId;	
					document.getElementById("newProdName").value = newProdName;				
					document.actform.target="_self";
					document.actform.action="${sessionScope.basePath }prodChange/mainProductRecInfo.action";
					document.actform.submit();				
			}
</script>
	</head>
	<BODY scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="ncode" name="ncode" value="" />
			<input type="hidden" id="fromProdId" name="fromProdId" value="" />
			<input type="hidden" id="prodSN" name="prodSN" value="" />			
			<input type="hidden" id="toProdId" name="toProdId" value="" />
			<input type="hidden" id="newProdName" name="newProdName" value="" />
			<input type="hidden" id="region" name="region" value="<s:property value="region"/>" />
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
									<tr >
										<th scope="col">
											资费名称
										</th>
										<th scope="col" >
											月使用费(元)
										</th>
										<th scope="col" >
											通话时长(分)
										</th>
										<th scope="col" >
											免费被叫范围
										</th>
										<th scope="col" >
											含国内数据流量(MB)
										</th>
										<th scope="col" >
											套餐外单价(元)
										</th>
										<th scope="col" >
											包含数据业务
										</th>
										<th scope="col" >
											操作
										</th>
									</tr>
									<s:iterator value="mainProdTempletList" status="result_s" id ="result" >
			                            <tr>			                           
										<td scope="col">
											<s:property value='#result.newProdName'/>
										</td>
										<td scope="col" >
											<s:property value='#result.monthCost'/>
										</td>
										<td scope="col" >
											<s:property value='#result.callDuring'/>
										</td>
										<td scope="col" >
											<s:property value='#result.freeCalled'/>
										</td>
										<td scope="col" >
											<s:property value='#result.GPRSFlux'/>
										</td>
										<td scope="col" >
											<s:property value='#result.outPackaGefee'/>
										</td>
										<td scope="col" >
											<s:property value='#result.dataBaseService'/>
										</td>
										<td scope="col" >	
										<input type="button" class="bt2_liebiao" style="color:#FFFFFF;"
							                    	  value="变更" onmousedown="" onmouseup="" onclick="mainProductRec('<s:property value='#result.ncode'/>','<s:property value='#result.oldPordID'/>','<s:property value='#result.templetNO'/>','<s:property value='#result.newPordID'/>','<s:property value='#result.newProdName'/>')" >	
							            </td>								
			                            	</tr>
			                        	</s:iterator> 
								</table>
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

			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
