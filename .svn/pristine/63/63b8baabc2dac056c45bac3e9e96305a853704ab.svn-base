<%--
 @User: g00140516
 @De: 2012/07/10
 @comment: 同组业务办理，不带附加属性
 @remark: create g00140516 2012/07/10 R003C12L07n01 OR_NX_201205_649
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
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
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
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
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
			{
			%>
				//82:R 退出
				if (key == 82 || key == 220)
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   			return ;
				}
			<%
			}
			else
			{
			%>
				//82:R 220:返回
				if (key == 82 || key == 220)
				{
					goback("<s:property value='curMenuId' />") ;
		   			return ;
				}
			<%	
			} 
			
			if ("1".equals(keyFlag))
			{
			%>
  				<s:if test="effectTypeList == null || effectTypeList.size > 1">
	  				<s:iterator value="effectTypeList" id="effectType" status="st">
						// 数字键
						if(key == <s:property value="#st.index + 49" />)
						{
							selectEffectType(<s:property value='#st.index' />, <s:property value='#effectType.dictid' escape="false"/>);	
						}
					</s:iterator>
				</s:if>	
				
				// 确认键
				if(key == 89)
				{
					commitPrivilege();
				}
				// 清除键
				else if(key == 77)
				{
					goback('');
				}
				
			<%
			}
			%>
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
				
				history.go(-1);
			}
		}
		
		function selectEffectType(index, effectType)
		{
			document.getElementById("effectType").value = effectType;
			
			var xx = document.getElementById("effectTypeList").getElementsByTagName("a");
    		for (i = 0; i < xx.length; i++)
    		{    
    			xx[i].className = "tc";
    		}
    		
    		xx[index].className += " hover";
		}
		
		function commitPrivilege()
		{
			if (document.getElementById("effectType").value == "")
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请选择生效方式");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请选择生效方式";
				}
				
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }privilege/commitPrivilege.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">				
			<input type="hidden" id="groupid" name="groupid" value="<s:property value='groupid' />" />
			<input type="hidden" id="currNCode" name="currNCode" value="<s:property value='currNCode' />" />
			<input type="hidden" id="nextNCode" name="nextNCode" value="<s:property value='nextNCode' />" />
			<input type="hidden" id="newNCode" name="newNCode" value="<s:property value='newNCode' />" />			
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>				
                  
  				<div class="box service_gprs" >
  					<div class="blank30" id="errorMsg"></div>					
					
					<s:if test="effectTypeList == null || effectTypeList.size == 0">
    					<input type="hidden" id="effectType" name="effectType" value="0" />
    					
    					<div class="pl160 pt60 blank240">
    						<p class="tit_info"><span class="bg"></span>套餐名称：<span class="yellow"><s:property value='newPrivName' /></span></p>	
	    					<p class="fs22 fwb pl40 lh30">套餐描述：<span class="yellow"><s:property value='newPrivDesp' /></span></p>
	    					<p class="fs22 fwb pl40 lh30">生效方式：<span class="yellow">默认</span></p>
    					</div>    					
    				</s:if>
    				<s:elseif test="effectTypeList.size == 1">
    					<input type="hidden" id="effectType" name="effectType" value="<s:property value='effectTypeList[0].dictid' />" />
    					
    					<div class="pl160 pt60 blank240">
    						<p class="tit_info"><span class="bg"></span>套餐名称：<span class="yellow"><s:property value='newPrivName' /></span></p>	
	    					<p class="fs22 fwb pl40 lh30">套餐描述：<span class="yellow"><s:property value='newPrivDesp' /></span></p>
	    					<p class="fs22 fwb pl40 lh30">生效方式：<span class="yellow"><s:property value="effectTypeList[0].dictname" /></span></p>
    					</div>    					
    				</s:elseif>
    				<s:else>
    					<input type="hidden" id="effectType" name="effectType" value="" />
    					
    					<div class="pl160 h400">
	    					<p class="tit_info"><span class="bg"></span>套餐名称：<span class="yellow"><s:property value='newPrivName' /></span></p>	
	    					<p class="fs22 fwb pl40 lh30">套餐描述：<span class="yellow"><s:property value='newPrivDesp' /></span></p>
	    					
	    					<div class="blank20"></div>
	    					<p class="tit_arrow fs22"><span class="bg"></span>选择生效方式：</p>
							<div class="blank10"></div>
	        				<ul class="money_list clearfix" id="effectTypeList">
		       	 				<s:iterator value="effectTypeList" id="effectType" status="st">
		       	 					<li class="first_money_key tc">
			       	 					<% 
			       	 					if ("1".equals(keyFlag))
										{
										%>
										<a href="javascript: void(0);" style="text-align:center;" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="selectEffectType(<s:property value='#st.index' />, <s:property value='#effectType.dictid' />)"><s:property value="#effectType.dictname" />&nbsp;(按<s:property value='#st.index + 1' />键)</a>
										<%
										}
										else
										{
										%>
										<a href="javascript: void(0);" style="text-align:center;" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="selectEffectType(<s:property value='#st.index' />, <s:property value='#effectType.dictid' />)"><s:property value="#effectType.dictname" /></a>
										<%
										}
									    %>
			       	 				</li>
		       	 				</s:iterator>
	        				</ul>
	    				</div>
    				</s:else>
					
    				<div class="btn_box tc ">
    					<% 
    					if ("1".equals(keyFlag))
						{
						%>
							<span class=" mr10 inline_block ">
  								<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="commitPrivilege(); return false;">确认(按确认键)</a>
	  						</span>
	  						<span class=" inline_block ">
	  							<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="goback(''); return false;">返回(按清除键)</a>
	  						</span>
						<%
						}
						else
						{
						%>
							<span class=" mr10 inline_block ">
  								<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="commitPrivilege(); return false;">确认</a>
	  						</span>
	  						<span class=" inline_block ">
	  							<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="goback(''); return false;">返回</a>
	  						</span>
						<%
						}
						%>
  						
  					</div> 
				</div>
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>