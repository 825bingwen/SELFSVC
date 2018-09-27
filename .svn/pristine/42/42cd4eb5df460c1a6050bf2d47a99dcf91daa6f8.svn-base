<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
NserCustomerSimp custInfor = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
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

// 推荐活动选择，btn:点击的按钮，btClass：按钮要变更成的样式,commendType:推荐类型,actName：活动名称
function btnClick(btn, btClass, commendType, actName, actId, eventType, isFeedBackDef)
{
	if(btn.className == btClass+'on')
	{
		btn.className = btClass;
		document.getElementById(btn.id).innerHTML = '【   】';
	}
	else
	{
		var btns=document.getElementById('actTable').getElementsByTagName('a');
	
		for(i=0;i<btns.length;i++)
		{
			document.getElementById(btns[i].id).innerHTML = '【   】';
			
			btns[i].className=btClass;
		}
		
		document.getElementById(btn.id).innerHTML = '【 √ 】';
		btn.className = btClass+'on';
		if("-1" == commendType)
		{
			document.getElementById('recButton').style.display='none';
		}
		else
		{
			document.getElementById('recButton').style.display='block';
		}
		
		document.getElementById("actId").value = actId;
		document.getElementById("actIds").value = actId;
		document.getElementById("recmdType").value = commendType;
		document.getElementById("eventTypes").value = eventType;
		document.getElementById("isFeedBackDef").value = isFeedBackDef;
		document.getElementById("actName").innerHTML = "";
		document.getElementById("actName").innerHTML = actName;
	}

}

// 提交前校验
function doCheck()
{
	// 选择的推荐活动数量
	var cnt = 0;
	var btns=document.getElementById('actTable').getElementsByTagName('a');
	for(i=0; i<btns.length; i++)
	{
		if(btns[i].className == 'bt14on')
		{
			cnt++;
			document.getElementById("commendOID").value = btns[i].id;
			document.getElementById("userSeq").value = btns[i].name;
			document.getElementById("userSeqs").value = btns[i].name;
		}
	}
	if(cnt == 0)
	{
		alertRedErrorMsg("请选择一个推荐活动！");
		return "0";
	}
	
	return "1";
}

// 继续办理、考虑一下、不要再打扰
function recommendFeedback(status)
{
	if("1" == doCheck())
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("status").value = status;
			
			openRecWaitLoading('recWaitLoading');
			document.actform.target="_self";
			document.actform.action = "${sessionScope.basePath}/recommendProduct/recommendFeedback.action";
			document.actform.submit();
		}
	}
}

// 推荐业务受理
function recommendProduct()
{
	if (submitFlag == 0)
	{
		var feeChargeFlag = document.getElementById("feeChargeFlag").value;
		
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		document.actform.target="_self";
		
		if("1" == feeChargeFlag)
		{
			<%
				if(null != custInfor)
				{
			%>
					document.actform.action = "${sessionScope.basePath}/recommendProduct/recommendProduct.action";
			<%
				}
				else
				{
			%>
					document.actform.action = "${sessionScope.basePath}/recommendProduct/checkPassword.action";
			<%
				}
			%>
			
		}
		else
		{
			document.actform.action = "${sessionScope.basePath}/recommendProduct/recommendProduct.action";
		}
		document.actform.submit();
	}
}	

//弹出确认框
openWindow = function(id)
{
	if("1" == doCheck())
	{
		// 判断isFeedBackDef值为1，表示"有回复信息定义"
		if (document.getElementById("isFeedBackDef").value == "1")
		{
			document.getElementById("actform").action = "${sessionScope.basePath}/recommendProduct/qryFeedBackDefList.action";
			document.getElementById("actform").submit();
		}
		else 
		{
			wiWindow = new OpenWindow("popup_confirm",708,392);
		}
	}
}	
</script>
</head>
<body scroll="no">
	<form name="actform" method="post" >
		<input type="hidden" id="commendOID" name="commendOID" value=""/>	
		<input type="hidden" id="userSeq" name="userSeq" value=""/>	
		<input type="hidden" id="servnumber" name="servnumber" value="<s:property  value='servnumber'/>"/>	
		<input type="hidden" id="feeChargeFlag" name="feeChargeFlag" value="<s:property  value='feeChargeFlag'/>"/>
		<input type="hidden" id="status" name="status" value=""/>
		
		<%-- 营销活动编码 --%>
		<s:hidden id="actId" name="actId"></s:hidden>
		
		<%-- 推荐类型 --%>
		<s:hidden id="recmdType" name="recmdType"></s:hidden>
		
		<%-- 营销活动编码集合,以逗号分隔 --%>
		<s:hidden id="actIds" name="actIds" value=""></s:hidden>
		
		<%-- 推荐事件类型集合,以逗号分隔 --%>
		<s:hidden id="eventTypes" name="eventTypes" value=""></s:hidden>
		
		<%-- 用户序号集合,以逗号分隔 --%>
		<s:hidden id="userSeqs" name="userSeqs" value=""></s:hidden>
		
		<%-- 是否有回复信息定义 --%>
		<s:hidden id="isFeedBackDef" name="isFeedBackDef"></s:hidden>
		
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>			
				<a href="#" class="bt10 fr mr92" style="margin-left:10px;" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';recommendFeedback('8'); return false;" style="display:inline">继续办理查询</a>
				<a href="#" class="bt10 fr" style="margin-left:10px;" onmousedown="this.className='bt10on fr'" onmouseup="this.className='bt10 fr';recommendFeedback('0'); return false;" style="display:inline">不要再打扰</a>
				<a href="#" class="bt10 fr" style="margin-left:10px;" onmousedown="this.className='bt10on fr'" onmouseup="this.className='bt10 fr';recommendFeedback('2'); return false;" style="display:inline">考虑一下</a>
				<a href="#" id="recButton" class="bt10 fr" style="margin-left:10px;" onmousedown="this.className='bt10on fr'" onmouseup="this.className='bt10 fr';openWindow('popup_confirm'); return false;" style="display:inline">活动受理</a>
		 		<!--滚动条-->
				<div class="box842W fl ml45 relative" style="margin-left:90px; display:inline;">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
								<p class="ptop180 tc p747w411h" id="inn" >
            						 <table width="100%" class="tb_blue" id="actTable">
					               	    <tr>
											<th class="list_title" align="center" colspan="20">营销推荐活动</th>
			                            </tr>
					                  <tr>
					                    <th scope="col">请选择</th>
					                    <th scope="col">活动名称</th>
					                    <th style="width: 30%" scope="col">活动内容</th>
					                    <th style="width: 30%" scope="col">营销用语</th>
					                  </tr>
				                  	  <s:iterator value="recmdProductList" id="prod">
				                  	  <tr>
					                  	  <td>
					                  	  	<a href="javascript:void(0)" id ="<s:property value='#prod.commendOID'/>" 
					                  	  		name="<s:property value='#prod.userSeq'/>" class="bt14" 
					                  	  		style="line-height:20px; padding-top:14px; display:inline-block;"; 
					                  	  		onclick="btnClick(this,'bt14','<s:property value='#prod.commendType'/>','<s:property value='#prod.actName' />','<s:property value='#prod.actID' />','<s:property value='#prod.eventType' />','<s:property value='#prod.isFeedBackDef' />')">【  】</a>		
					                  	  </td>
					                  	  <td><s:property  value='#prod.actName' /></td>
						                  <td><s:property  value='#prod.actContent' /></td>
						                  <td><s:property  value='#prod.actDict' /></td>
						             </tr>
				                  	 </s:iterator>
					                </table>
					                <br/>
							</div>
						</div>	
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<%--add begin cKF48754 2011/11/11 R003C11L11n01  添加确认提示--%>
				<div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
				    <p>您选择订购： <i id="actName"></i></p>
				    <div class="blank10"></div>
				    <p class="mt30">确认订购请点击"确认"提交。</p>
				  </div>
                  <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';recommendProduct();">确认</a></span>
	                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
                  </div>
                </div>
                <%--add end cKF48754 2011/11/11 R003C11L11n01  添加确认提示--%>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
