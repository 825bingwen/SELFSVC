<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script>
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
		
		if("<s:property value='recommendActivityFlag' />" == "1")
		{
			document.actform.action = "${sessionScope.basePath }login/backForward.action";
		}
		else
		{
			document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
		}
		document.forms[0].submit();
	}
}

// 查询赠品列表
function queryPresentsList()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// modify begin wWX217192 2015-03-04 
		if(document.getElementById("prepayFee").value == 0)
		{
			//弹出等待框
			openRecWaitLoading();
			document.forms[0].action = "${sessionScope.basePath}activitiesRec/finish.action";
		}
		else
		{
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }activitiesRec/selectType.action";
		}
		document.forms[0].submit();
	}
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
		
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>

		<!-- 活动名称 -->
		<input type="hidden" id="activityName" name="activityName" value="<s:property value="#request.activityName" />"/>
		
		<!-- 活动组ID -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>活动受理流程：</h2>
	     					<div class="blank10"></div>
	     					<a title="选择活动档次" href="#">1. 选择活动档次</a>
	     					<a title="档次描述" href="#" class="on">2. 档次描述</a>  
	     					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
	     					<a title="支付" href="#">4. 支付</a>
	         				<a title="完成" href="#">5. 完成</a>
					</div>
				</div>
	            <!--滚动条-->
			    <div class="box710W fl ml10 relative" style="margin-top: 10px;">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box615W fl">
							<div class="top"></div>
							<div class="con" style="height:501px; padding:0px; overflow:hidden;">

								<!-- 列表内容 -->
								<div class="ptop180 tc" id="inn" style="height:468px;  overflow:hidden;" >
									<table class="fs16" style="text-align: left;" >
										<tr>
											<td class="fs22">
												档次名称
											</td>
										</tr>
										<tr>
												<td><s:property value="dangciDesc" /></td>
										</tr>
										<s:if test="tip != ''">
											<td>特别提示：<s:property value='tip' /></td>
										</s:if>
										<tr>
											<td style="text-align: right;">
											<!-- 
											<input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="同意" onmouseup="this.className='bt2';queryPresentsList();">
											 -->
											<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';queryPresentsList(); return false;" style="display:inline">同意协议并办理</a>
											</td>
										</tr>
										<%-- modify begin jWX216858 2015-2-9 OR_HUB_201501_167_HUB_关于自助终端菜单层级优化需求 --%>
										<tr><td>
										<s:iterator value="agreementList" id="agreement">
		                                    <s:property value="#agreement.description" escapeHtml="false"/>
		                                </s:iterator>
									   </td></tr>
									   <%-- modify end jWX216858 2015-2-9 OR_HUB_201501_167_HUB_关于自助终端菜单层级优化需求 --%>
								    </table></div>
								<!-- 列表内容 -->
							     </div>
							<div class="btm"></div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:407px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style="width:66px; height:40px; position:absolute; cursor:move; left:634px; top:39px; line-height:36px" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                
                <script type="text/javascript">
                  
					myScroll = new virtualScroll("inn","gunDom");
				
                </script>
                <!--滚动条结束-->
                
                <div class=" clear"></div>
                <!--弹出窗-->
                <script type="text/javascript">
				openWindow = function(id){
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
					
				}
				openWindowMail = function(id){
					wiWindow = new OpenWindow("openWin2",708,392);//打开弹出窗口例子
					
				}
				
				openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",708,192);//打开弹出窗口例子
					gotoPrintSuccess();
					
				}
				openWindowSuccess = function(id){
					wiWindow = new OpenWindow("openWinSuccess",708,392);//打开弹出窗口例子
					
					
				}
				function gotoPrintSuccess(){
					setTimeout(function(){window.location='main.html?src=billquery_detail_ok&sign=4';wiWindow.close()},3000);
					;
					
					}
			    function btnClick(btn,btClass){
					  var btns=document.getElementById('btns').getElementsByTagName('a');
					  for(i=0;i<btns.length;i++){
						  btns[i].className=btClass;
						  }
					  btn.className=btClass+'on';
					  }
				</script>
                <!--弹出窗结束-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
		
		</form>
	</body>
</html>
