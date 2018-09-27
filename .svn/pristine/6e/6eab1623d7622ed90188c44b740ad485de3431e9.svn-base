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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
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
  				<s:iterator value="privilegeList" id="privilegeInfo" status="st">
	  				<s:if test="currNCode != null && currNCode != '' && currNCode == #privilegeInfo.dictid">
						// 数字键
						if(key == <s:property value="#st.index + 48" />)
						{
							return false;
						}
					</s:if>
					<s:else>
						// 数字键
						if(key == <s:property value="#st.index + 48" />)
						{
							addHover(<s:property value='#st.index' />, '<s:property value="#privilegeInfo.dictid" escape="false"/>');
						}
					</s:else>	
				</s:iterator>
				// 确认键
				if(key == 89)
				{
					confirmPrivilege();
				}
				// 清除键
				else if(key == 77)
				{
					topGo('rec', 'reception/receptionFunc.action');
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
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		
		function addHover(j, ncode)
		{
			document.getElementById("newNCode").value = ncode;
			
			var xx = document.getElementById("meal_list").getElementsByTagName("a");
    		for (i = 0; i < xx.length; i++)
    		{    
    			xx[i].className = "relative";
    		}
    		
    		xx[j].className += " hover";
		}
		
		function confirmPrivilege()
		{
			if (document.getElementById("newNCode").value == "")
			{
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请选择套餐");
				}
				else
				{
					document.getElementById("promtMsg").style.color = "#C71D02";
					document.getElementById("promtMsg").innerHTML = "请先选择套餐后再点击确认按钮：";
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
				document.forms[0].action = "${sessionScope.basePath }privilege/confirmPrivilege.action";
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
			<input type="hidden" id="newNCode" name="newNCode" value="" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>				
                  
  				<div class="box service_gprs" >
  					<h2 class="mt20"></h2>
					
					<ul class="curr_meal clearfix">
						<li>
						    <span style="padding-left:10px;">您选择办理：</span>
						    <span class="charge_name" style="width:222px;"><%=menuName %>业务</span>
						</li>
						<li class="meal_3">
						    <span style="width:100px;padding-left:10px;">使用状态：</span>
						    <span class="charge_name" style="width:370px;">本月: <s:property value="currDesp" />&nbsp;&nbsp;&nbsp;下月：<s:property value="nextDesp" /></span>
						</li>
					</ul>  	
  					<div class="line" style="width:916px;"></div>
  					<h2 class="mt10" id="promtMsg">将您的套餐修改为：</h2>
					
  					<ul class="meal_list clearfix" id="meal_list">
    					<s:iterator value="privilegeList" id="privilegeInfo" status="st">
    						<li class="clearfix">
	    						<span class="meal_charge"><s:property value="#privilegeInfo.dictname" /></span>
	    						<span class="meal_content" style="text-align: left;"><s:property value="#privilegeInfo.description" escape="false" /></span>
	    					</li>
	    					 <% 
	    					 if ("1".equals(keyFlag))
							 {
							 %>
								<s:if test="currNCode != null && currNCode != '' && currNCode == #privilegeInfo.dictid">
		    						<li class="blank_li choose_meal_1">
		    							<a class="relative" href="javascript:void(0);" onclick="return false;" class="default">
		    							<%-- add by yWX163692 2013年11月7日 解决页面字体换行的问题 --%>
		    								<span style="width:60px; display:block; text-align:center;white-space: nowrap;">
		    									选择</br>按<s:property value='#st.index' />键)
		    								</span>
		    							</a>
		    						</li>
		    					</s:if>
		    					<s:else>
		    						<li class="blank_li choose_meal_1">
									    <a class="relative" href="javascript:void(0);" onclick="addHover(<s:property value='#st.index' />, '<s:property value="#privilegeInfo.dictid" />'); return false;">
									    	<span style="width:60px; display:block; text-align:center;white-space: nowrap;">
									    		选择</br>(按<s:property value='#st.index ' />键)
									    	</span>
									    	<%-- end by yWX163692 2013年11月7日 解决页面字体换行的问题 --%>
									    </a>
									</li>
		    					</s:else>	
							 <%
							 }
							 else
							 {
							 %>
								<s:if test="currNCode != null && currNCode != '' && currNCode == #privilegeInfo.dictid">
		    						<li class="blank_li choose_meal">
		    							<a class="relative" href="javascript:void(0);" onclick="return false;" class="default">
		    								<span>选择</span>
		    							</a>
		    						</li>
		    					</s:if>
		    					<s:else>
		    						<li class="blank_li choose_meal">
									    <a class="relative" href="javascript:void(0);" onclick="addHover(<s:property value='#st.index' />, '<s:property value="#privilegeInfo.dictid" />'); return false;">
									    	<span>选择</span>
									    </a>
									</li>
		    					</s:else>		
							 <%
							 }
							 %>
	    										
    					</s:iterator>
  					</ul>
  						
  					<h2><%=busiDetailPage %></h2> 
  					
  					<div class="btn_box tc ">
 						<% 
    					if ("1".equals(keyFlag))
						{
						%>
							<span class=" mr10 inline_block ">
  								<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="confirmPrivilege(); return false;">确认(按确认键)</a>
	  						</span>
	  						<span class=" inline_block ">
	  							<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="goback('<%=curMenuId %>'); return false;">返回(按清除键)</a>
	  						</span>
						<%
						}
						else
						{
					    %>
					    	<span class=" mr10 inline_block ">
  								<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="confirmPrivilege(); return false;">确认</a>
	  						</span>
	  						<span class=" inline_block ">
	  							<a href="javascript:void(0);" class="btn_bg_146" onMouseDown="this.className='key_down'" onMouseUp="this.className='btn_bg_146';" onclick="goback('<%=curMenuId %>'); return false;">返回</a>
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