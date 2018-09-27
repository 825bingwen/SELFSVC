<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.customize.hub.selfsvc.scorexecoup.model.RewardAction"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
//防止页面重复提交
var submitFlag = 0;

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
		goback("<s:property value='curMenuId' />");
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

	//免费抽奖
		function mianFeiChouJiang(actId){

		
		var resuInfo="抽奖出现异常！";
		url= "${sessionScope.basePath }charge/mianFeiChouJiangNew.action?curMenuId=<s:property value='curMenuId'/>&actId="+actId;
					var loader1 = new net.ContentLoader(url, netload = function () 
										{
											var resXml = this.req.responseXML;
										
											if (resXml)
											{
													var root = resXml.documentElement;
													
													
														
														document.getElementById("colBillQuery").innerHTML=root.text;
														wiWindow = new OpenWindow("colBillQueryWin1",708,392);
														return;
													
												    
												   				
											}
										    else
											{	
												
													document.getElementById("colBillQuery").innerHTML=resuInfo;
												    wiWindow = new OpenWindow("colBillQueryWin1",708,392);
												return;
											}								
										}, null, "POST", "", null);
	
}	
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main" >
				<%@ include file="/customer.jsp"%>
				<!-- modify begin m00227318 -->
			<p class="tit_info" align="left">
							<span class="bg" ></span><%="抽奖活动列表"%></p>
			      <br/>
					<table width="80%" class="tb_blue" style="align:center;margin-left:10%;">
								<tr>
                                    

									<th class="list_title" align="center"   width="30%">
									  活动名称
									</th>
									<th class="list_title" align="center" width="10%">
									花费积分
									</th>
									<th class="list_title" align="center" width="40%">
									活动介绍
									</th>
									
                                    <th class="list_title" align="center" width="20%">
									  抽奖
									</th>

								</tr>
								<s:if test="canActiveList != null && canActiveList.size > 0">
									<s:iterator value="canActiveList" status="result" id="rw">
									
										<tr align="center">
										
                                           
											<td>
												<s:property value="#rw.activename" />
											</td>
											<td >
												<s:property value="#rw.costdata" />
											</td>
											<td >
											
												<s:property value="#rw.note" />
											</td>
											<td >
											 <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';" onclick="mianFeiChouJiang('<s:property value="#rw.actid" />');return false;" style="color:yellow;">抽奖</a>
											
											</td>
                                          </tr>
									</s:iterator>
								</s:if>

							</table>
				
				
				
				
				
			</div>
			 <!--免费抽奖-->
                <div class="openwin_tip" id="colBillQueryWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class="blank30"></div><div class=" blank60"></div>
                   
                    <div class="blank10n"></div>
                    <p class="fs30 yellow pl20 lh2" id="colBillQuery" style="text-indent:60px;padding-right:20px;"></p>
                  
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    
                    </div>
                </div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>	
</html>
