<%--
 @User: 高群/g00140516
 @De: 2012/09/05
 @comment: 个人银行划扣业务
 @remark create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
--%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%	
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
				
				//确认
				if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}
				
				//82:退出
				if (key == 82 || key == 220)
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;	
				}
				
				<%
				if("1".equals(keyFlag))
				{
				%>
					if(key == 48)
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
				
					document.dutyInfoForm.target="_self";
					document.dutyInfoForm.action="${sessionScope.basePath }login/backForward.action";
					document.dutyInfoForm.submit();
				}
			}
						
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
					//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
					openRecWaitLoading_NX("recWaitLoading");
					//add end m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
					
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }bankhuakou/selectHuakouInfo.action";
					document.dutyInfoForm.submit();
				}
			}	
		</script>
	</head>

	<body scroll="no">
		<form name="dutyInfoForm" method="post" target="_self">
			<input type="hidden" id="IDCard" name="IDCard" value="<s:property value='IDCard' />">
			<input type="hidden" id="pan" name="pan" value="<s:property value='pan' />">
			
			<%@ include file="/titleinc.jsp"%>
		
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>				
				<div class="blank50">
				<%
				if("1".equals(keyFlag))
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
				<div class="box842W fl ml45 relative">
	        		<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div class="div747w444h">
								<div class=" p747w411h mb10 relative" id="inn" style="font-size:18px; line-height:1.5em; width:680px;">
									<p style="padding-left: 50px; padding-top: 50px; ">
										<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_HUAKOU_PROTOCOL) %>
										
										<div class=" tr"> 
										<div class="btn_box right">
										<span class=" mr10 inline_block ">
										<%
										if("1".equals(keyFlag))
										{
										%>
			    							<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();return false;;">√同意(按确认键)</a>
										<%
										}
										else
										{
										%>
			    							<a class=" btagree" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">同意</a> 
										<%
										}
										%>
										</span>		
										</div>	
			    						</div>
									</p>											           			
								</div>
							</div>
						</div>
								        
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(300)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(300)"/>
						</div>
								        
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
	        	</div>
	        	<script type="text/javascript">
	        		myScroll = new virtualScroll("inn", "gunDom");
	        	</script>		
	    	</div>
	    	<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
