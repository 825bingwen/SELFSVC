<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

// 弹出div
var divFlag = "";

// modify begin l00190940 2011-11-23 业务查询退订需二次确认
function doSub()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		
		//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		
		document.actform.action="${sessionScope.basePath}baseService/cancelRecSP.action";
		document.actform.submit();
	}
}
// modify end l00190940 2011-11-23 业务查询退订需二次确认

// add begin l00190940 2011-11-23 业务查询退订需二次确认
function passParam(spBizName,dealType,domain,spId,spBizCode,bizType)
{
    document.getElementById("spBizName").value = spBizName;
	document.getElementById("dealType").value = dealType;
	document.getElementById("domain").value = domain;
	document.getElementById("spId").value = spId;
	document.getElementById("spBizCode").value = spBizCode;
	document.getElementById("bizType").value = bizType;
	
	return "1";
}
// add end l00190940 2011-11-23 业务查询退订需二次确认

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
		if ((key == 82 || key == 220) && divFlag == '')
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
		<s:if test="spList != null && spList.size() > 0">
			<s:iterator value="spList" id="sp" status="st">
				if (key == <s:property value="#st.index + 49" />)
				{
					if('1' == passParam('<s:property value="#sp.spBizName" escape="false"/>','<s:property value="#sp.dealType" escape="false"/>',
						'<s:property value="#sp.domain" escape="false"/>','<s:property value="#sp.spID" escape="false"/>',
						'<s:property value="#sp.spBizID" escape="false"/>','<s:property value="#sp.spBizType" escape="false"/>'))
					{
						openWindow('drop');
					}
					return;
				}
			</s:iterator>
		</s:if>
		<s:if test="pageCount > 1">
			// 上翻
			if (key == 85 &&　divFlag == '')
			{
				var page = parseInt('<s:property value="page" />')-1;
				
				if(page>0)
				{
					nextPage("${sessionScope.basePath }baseService/recSpService.action?page="+page);
				}

			}
			// 下翻
			else if (key == 68 &&　divFlag == '')
			{
				<s:if test="page < pageCount">
					var page = parseInt('<s:property value="page" />')+1;
					nextPage("${sessionScope.basePath }baseService/recSpService.action?page="+page);
				</s:if>
			}
		</s:if>
		// 确认键
		if(key == 89 &&　divFlag != '')
		{
			doSub();
		}
		// 清除键
		else if(key == 77 &&　divFlag != '')
		{
			windowClose();
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

//add begin l00190940 2011-11-25 业务查询退订增加二次确认
function openWindow(id)
{
    divFlag = id;
    wiWindow = new OpenWindow(id,708,392);//打开弹出窗口
}
//add end l00190940 2011-11-25 业务查询退订增加二次确认

// 下一页
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}
</script>
</head>
<body scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" name="spBizName" id="spBizName" value=""/>
		<input type="hidden" name="dealType" id="dealType" value=""/>
		<input type="hidden" name="domain" id="domain" value=""/>
		<input type="hidden" name="spId" id="spId" value=""/>
		<input type="hidden" name="spBizCode" id="spBizCode" value=""/>
		<input type="hidden" name="bizType" id="bizType" value=""/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
					<div class="b966s">
	          		<div class="p40">
	          			<div class="blank30"></div>
	            		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
	            		<div class="blank10"></div>
	            		<div class="relative p40">	            
	            			<div class="p20">
				               <table width="100%" class="tb_blue" >
					              <tr>
				                    <th scope="col">业务名称</th>
				                    <th scope="col">提供商</th>
				                    <th scope="col">资费</th>
				                    <th scope="col">开通时间</th>
				                    <th scope="col">操作</th>
					              </tr>
				                  <s:if test="spList != null && spList.size() > 0">
									<s:iterator value="spList" id="sp" status="st">
										<tr>
						                    <td><s:property value="#sp.spBizName" /></td>
						                    <td><s:property value="#sp.spName" /></td>
						                    <td><s:property value="#sp.price" /></td>
						                    <td><s:property value="#sp.startTime" /></td>
						                    <td>
						                    <%
											if ("1".equals(keyFlag))
											{
											%>
												<input type="button" class="bt2_liebiao white"
					                    	 	value="退订(按<s:property value='#st.index + 1' />键)" onmousedown="this.className='bt2on white'" 
						                    	onmouseup="this.className='bt2 white';passParam('<s:property value="#sp.spBizName" />','<s:property value="#sp.dealType" />','<s:property value="#sp.domain" />','<s:property value="#sp.spID" />','<s:property value="#sp.spBizID" />','<s:property value="#sp.spBizType" />');openWindow('drop');"/>
											<%
											}
											else
											{
											%>
											<input type="button" class="bt2_liebiao white"
					                    	value="退订" onmousedown="this.className='bt2on white'" 
						                    onmouseup="this.className='bt2 white';passParam('<s:property value="#sp.spBizName" />','<s:property value="#sp.dealType" />','<s:property value="#sp.domain" />','<s:property value="#sp.spID" />','<s:property value="#sp.spBizID" />','<s:property value="#sp.spBizType" />');openWindow('drop');"/>
											<%
											}
											%>
					                    	
		                  				</tr>
									</s:iterator>
								</s:if>								
				              </table>
	             			</div>	             
						</div>
						
						
	            	</div>
	          	</div>
	          	<div class="blank10"></div>
	          	<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
	          	
		  	</div>
   
	        <!-- add begin l00190940 2011-11-23 业务查询退订需二次确认 -->  
	        <div class="popup_confirm" id="drop">
                 <div class="bg"></div>
                 <div class="tips_title">提示：</div>
                 <div class="tips_body">
                    <p>尊敬的客户，您确认退订此业务吗？</p>
                    <div class="blank10"></div>
                    <p class="mt30">确认退订请点击"确认"。</p>
                 </div>
                 <%
				 if ("1".equals(keyFlag))
				 {
				 %>
					<div class="btn_box tc mt20">
                 
	                    <span class=" mr10 inline_block ">
	                       <a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">确认(按确认键)</a>
	                    </span>
	                    <span class=" inline_block ">
	                       <a title="" class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消(按清除键)</a>
	                    </span>
                 	</div>
				 <%
				 }
				 else
				 {
				 %>
					<div class="btn_box tc mt20">
                 
	                    <span class=" mr10 inline_block ">
	                       <a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">确认</a>
	                    </span>
	                    <span class=" inline_block ">
	                       <a title="" class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
	                    </span>
                 	</div>
				 <%
				 }
				 %>
                 
               </div>
               <!-- add end l00190940 2011-11-23 业务查询退订需二次确认 -->    
		          
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
