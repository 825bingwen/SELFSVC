<%--
 @User: 高群/g00140516
 @De: 2012/09/05
 @comment: 个人银行划扣业务
 @remark create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
--%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
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
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>		
		<script language="javascript">
			//防止重复提交
			var submitFlag = 0;
			
			//82、220 返回		
			document.onkeydown = pwdKeyboardDown;
		
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
		
			document.onkeyup = pwdKeyboardUp;
		
			function pwdKeyboardUp(e) 
			{
				var key = GetKeyCode(e);
				
				//退出
				if (key == 82 || key == 220) 
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;	
				}
				//确认
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}
				<%
				if("1".equals(keyFlag))
				{
				%>
					if(key == 48)
					{
						topGo('rec', 'reception/receptionFunc.action');
					}
					<s:iterator value="drawTypes" id="drawType" status="st">
						if(key ==  <s:property value="#st.index + 49" />)
						{
							btnClick(document.getElementById('<s:property value="#drawType.dictid" />'));
							document.getElementById('drawType').value='<s:property value="#drawType.dictid" />';
						}
           		 	</s:iterator>
           		 	<s:iterator value="drawAmts" id="drawAmt" status="st">
						if(key ==  <s:property value="#st.index + drawTypes.size + 49" />)
						{
							btnClick1(document.getElementById('<s:property value="#drawAmt.dictid" />'));
							document.getElementById('drawAmt').value='<s:property value="#drawAmt.dictid" />';
						}
           		 	</s:iterator>
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
				
					document.actform.target="_self";
					document.actform.action="${sessionScope.basePath }login/backForward.action";
					document.actform.submit();
				}
			}
						
			function doSub() 
			{
				if (document.getElementById("drawType").value == "")
				{
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("请选择划扣类型");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "请选择划扣类型";
					}
				
					return;
				}
				
				if (document.getElementById("drawAmt").value == "")
				{
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("请选择划扣金额");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "请选择划扣金额";
					}
				
					return;
				}
			
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
					//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
					openRecWaitLoading_NX("recWaitLoading");
					//add end m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }bankhuakou/addBindInfo.action";
					document.actform.submit();
				}
			}
			
		function btnClick(btn)
		{
			var btns = document.getElementById('btns').getElementsByTagName('a');
		  	for (i = 0; i < btns.length; i++)
		  	{
			  	btns[i].className='b_bt2';
			}
			
		  	btn.className='b_bt2on';
		}
		
		function btnClick1(btn)
		{
			var btns = document.getElementById('btns1').getElementsByTagName('a');
		  	for (i = 0; i < btns.length; i++)
		  	{
			  	btns[i].className='b_bt2';
			}
			
		  	btn.className='b_bt2on';
		}
		</script>
	</head>

	<body scroll="no">
		<form name="actform" method="post" target="_self">
			<input type="hidden" id="IDCard" name="IDCard" value="<s:property value='IDCard' />">
			<input type="hidden" id="pan" name="pan" value="<s:property value='pan' />">
			<input type="hidden" id="drawType" name="drawType" value="">
			<input type="hidden" id="drawAmt" name="drawAmt" value="">
			
			<%@ include file="/titleinc.jsp"%>
		
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>				
				<div class="blank50">
				<%
 				if ("1".equals(keyFlag))
 				{
   				%>
					<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %>(按0键)</a>
   				<%
   				}
   				else
   				{
   				%>
					<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
   				<%
   				}
   				%>
   				</div>
        		<div class="b966">
	          		<div class="blank380 p40">
	          			<div class="blank30" id="errorMsg"></div>
	            		<p class="tit_info"><span class="bg"></span>请选择划扣类型：</p>
	            		<div class="b_card btn_box" id="btns">
				            <ul>
				            	<s:if test="drawTypes != null && drawTypes.size() > 0">
				            		<s:iterator value="drawTypes" id="drawType" status="st">
				            			<li>
				            				<%
			            					if ("1".equals(keyFlag))
			            					{
				            				%>
						    					<a href="javascript:void(0)" id="<s:property value="#drawType.dictid" />" class=" b_bt2" style="font-size:22px;font-weight" onmousedown="btnClick(this)" onMouseUp="document.getElementById('drawType').value='<s:property value="#drawType.dictid" />'"><s:property value="#drawType.dictname" />(按<s:property value='#st.index + 1' />键)</a>
				            				<%
				            				}
				            				else
				            				{
				            				%>
				            					<a href="javascript:void(0)" class=" b_bt2" style="font-size:22px;" onmousedown="btnClick(this)" onMouseUp="document.getElementById('drawType').value='<s:property value="#drawType.dictid" />'"><s:property value="#drawType.dictname" /></a>
				            				<%
				            				}
				            				%>
				            			</li>
				            		</s:iterator>
				            	</s:if>               
				            </ul>
            			</div>
	            		<div class="blank30"></div>
	            		<div class="b_card2">
	              			<div class="blank10"></div>
	              			<div class="bdt1px blank10"></div>
	              			<p class="tit_info"><span class="bg"></span>请选择划扣金额：</p>
	              			<div class="b_card btn_box" id="btns1">
					            <ul>
						            <s:if test="drawAmts != null && drawAmts.size() > 0">
					            		<s:iterator value="drawAmts" id="drawAmt" status="st">
					            			<li>
					            				<%
				            					if ("1".equals(keyFlag))
				            					{
					            				%>
					            					<a href="javascript:void(0)" id="<s:property value="#drawAmt.dictid" />" class=" b_bt2" style="font-size:22px;" onmousedown="btnClick1(this)" onMouseUp="document.getElementById('drawAmt').value='<s:property value="#drawAmt.dictid" />'"><s:property value="#drawAmt.dictname" /></br>(按<s:property value='#st.index + drawTypes.size + 1' />键)</a>
					            				<%
					            				}
					            				else
					            				{
					            				%>
					            					<a href="javascript:void(0)" class=" b_bt2" style="font-size:22px;" onmousedown="btnClick1(this)" onMouseUp="document.getElementById('drawAmt').value='<s:property value="#drawAmt.dictid" />'"><s:property value="#drawAmt.dictname" /></a>
					            				<%
					            				}
					            				%>
					            			</li>
					            		</s:iterator>
					            	</s:if>		                
					            </ul>	
	            			</div>
	            		</div>
	            	</div>
	            	
	            	<div class="btn_box ml380">
					    <%
        				if ("1".equals(keyFlag))
        				{
           				%>
					    	<span class=" inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="doSub();">确认(按确认键)</a></span>
           				<%
           				}
           				else
           				{
           				%>
					   		<span class=" inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="doSub();">确认</a></span>
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