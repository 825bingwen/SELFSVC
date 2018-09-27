<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="java.util.Map"%>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
	
	// 开机原因
	List<DictItemPO> openList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("OpenReason");
	
	// 停机原因
	List<DictItemPO> stopList = (List<DictItemPO>)PublicCache.getInstance().getCachedData("StopReason");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>停开机</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 弹出div
var divFlag = "";

//提交业务
function submitReception()
{      		
	//防止重复提交
	if (submitFlag == 0) 
	{
		var operType = document.getElementById("operType").value;
		var isInput = document.getElementById("isInput").value;
		submitFlag = 1;
		
		if (isInput == "0")
		{
			if ("0" == operType)
			{
				document.actform.actionCase.value = "toValidate";
			}
		}
				
		openRecWaitLoading_NX("recWaitLoading");
		
		document.actform.action="${sessionScope.basePath }baseService/recOpenAndStopSubs.action";
		
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

	//82:R 退出
	if ((key == 82 || key == 220) && divFlag == '')
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
  			return ;
	}
	
	<s:if test="operType == 1">														
		<% 
			if(null != openList && openList.size() >0)
			{
				for(int i=0; i<openList.size(); i++)
				{
					DictItemPO dictItemPO = (DictItemPO)openList.get(i);
		%>
					if(key == 48 + <%=i%> &&　divFlag == '')
					{
						setReason('<%=dictItemPO.getDictid() %>','<%=dictItemPO.getDictname() %>');
					}
		<% 
				}
			}
		%>
	</s:if>
	<s:else>
		<% 
		if(null != stopList && stopList.size() >0)
		{
			for(int i=0; i<stopList.size(); i++)
			{
				DictItemPO dictItemPO = (DictItemPO)stopList.get(i);
		%>
				if(key == 48 + <%=i%> &&　divFlag == '')
				{
					setReason('<%=dictItemPO.getDictid() %>','<%=dictItemPO.getDictname() %>');
				}
		<% 
			}
		}
		%>
	</s:else>

	// 确认键
	if(key == 89 &&　divFlag != '')
	{
		windowClose();
		submitReception();
	}
	// 清除键
	else if(key == 77 &&　divFlag != '')
	{
		windowClose();
	}
	
}	

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

// 打开确认窗口
function setReason(reasonID, reasonName)
{
	document.getElementById("selectReason").value = reasonID;
	
	document.getElementById("reasonValue").innerHTML = reasonName;
	
	divFlag = "openWin1";
		
	wiWindow = new OpenWindow("openWin1",708,392);
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}
</script>
</head>

<body scroll="no">
	<form name="actform" method="post">
	<input type="hidden" id="actionCase" name="actionCase" value="<s:property value="actionCase"/>" />
	<input type="hidden" id="operType" name="operType" value="<s:property value="operType"/>" />
	<input type="hidden" id="isInput" name="isInput" value="<s:property value="isInput"/>" />	
	<input type="hidden" id="selectReason" name="selectReason" value="" />			
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">			
		<%@ include file="/customer.jsp"%>
			  <div class="service_brand w966 m0auto">
			    <div class="service_index_list">
			    <p class="hot_service"></p>
			    	<div class="p40">
						<div class="blank10"></div>
						<s:if test="operType == 1">
							<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择开机原因：</span></p>
							<div class="blank25"></div>
	      						<dl class="clearfix favorable_list">
							      <dd class="fl">															
									<% 
										if(null != openList && openList.size() >0)
										{
											for(int i=0; i<openList.size(); i++)
											{
												DictItemPO dictItemPO = (DictItemPO)openList.get(i);
									%>
												<a href="#" onmousedown="this.className='hover'" onmouseup="this.className='';setReason('<%=dictItemPO.getDictid() %>','<%=dictItemPO.getDictname() %>');"><span class="double_line"><%=dictItemPO.getDictname() %><br>(请按<%=i %>键)</span></a>
									<% 
											}
										}
									%>
							 	</dd>
						    </dl>		
						</s:if>
						<s:else>
							<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择停机原因：</span></p>
							<div class="blank25"></div>
							<dl class="clearfix favorable_list">
							      <dd class="fl">
							      <% 
										if(null != stopList && stopList.size() >0)
										{
											for(int i=0; i<stopList.size(); i++)
											{
												DictItemPO dictItemPO = (DictItemPO)stopList.get(i);
								  %>
												<a href="#" onmousedown="this.className='hover'" onmouseup="this.className='';setReason('<%=dictItemPO.getDictid() %>','<%=dictItemPO.getDictname() %>');"><span class="double_line"><%=dictItemPO.getDictname() %><br>(请按<%=i %>键)</span></a>
								  <% 
											}
										}
								  %>
							      </dd>
						    </dl>
						</s:else>
      				</div>
      				<div class=" clear"></div>
					<!--弹出窗-->
					<div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<s:hidden id="balanceType" name="balanceType"></s:hidden>
						<p class="fs28 lh2" style="padding-left:142px;" id="winText" name="winText">
							<s:if test="operType == 1">
								<span>您选择开机原因为：<br></span>
							</s:if>
							<s:else>
								<span>您选择停机原因为：<br></span>
							</s:else>
							<span id="reasonValue" class="yellow"></span>
							<br/>
						</p>
		 					<div class="tc">
						    <div class=" clear "></div>
						    <div class=" blank30 "></div>
		   					<a title="" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';windowClose();submitReception();" onmouseup="this.className='bt4';">确认(按确认键)</a> 
		   					<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';windowClose();">取消(按清除键)</a> 
		   				</div>
					</div>
					
					<script type="text/javascript">
					openWindow = function(id){
						wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子					
					}
					</script>
					<!--弹出窗结束-->	
			    </div>
			  </div>
		</div>
	<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
