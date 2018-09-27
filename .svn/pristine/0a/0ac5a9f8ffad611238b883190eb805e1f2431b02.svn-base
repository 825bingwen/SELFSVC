<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
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
		<s:if test="menuMinChildNode != null && menuMinChildNode.size() > 0">
			<s:iterator value="menuMinChildNode" id="menu" status="st">
				if (key == <s:property value="#st.index + 48" />)
				{
					topGo('<s:property value="#menu.menuid" escape="false"/>', '<s:property value="#menu.guiobj" escape="false"/>');
					return;
				}
			</s:iterator>
		</s:if>
		<s:if test="pageCount > 1">
			// 上翻
			if (key == 85)
			{
				var page = parseInt('<s:property value="page" />')-1;
				
				if(page>0)
				{
					nextPage("${sessionScope.basePath }reception/receptionFunc.action?page="+page);
				}

			}
			// 下翻
			else if (key == 68)
			{
				<s:if test="page < pageCount">
					var page = parseInt('<s:property value="page" />')+1;
					nextPage("${sessionScope.basePath }reception/receptionFunc.action?page="+page);
				</s:if>
			}
		</s:if>
		
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

// 下一页
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{	 	
		submitFlag = 1;
		
		// add begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// add end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<input type="hidden" id="firstFlag" name="firstFlag" value="1"/>
		<%@ include file="/titleinc.jsp"%>
		
		<%--modify begin g00140516 2011/12/10 R003C11L11n01 OR_HUB_201112_16 --%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="service_brand w966 m0auto">
			
					<%
		            if(!Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
		            {
				  	%>
			  			<div class="service_index_list">
				    <%
				    }
				    else
				    {
				    %>
			  			<div class="service_index_list" style="height:450px; margin-bottom:20px;" >
				    <% 
				    }
				    
		            if(!Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
		            {
				  	%>
				    	<p class="hot_service">热点服务</p>
				    <%
				    }
				    else
				    {
				    %>
				    	<p class="hot_service"></p>
				    <% 
				    }
				    %>
			  		<s:if test="null != menuMinChildNode && menuMinChildNode.size() > 0">
			  		<%
					if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
					{
					%>
						<ul class="clearfix" style="margin-left:150px; margin-right:150px;">
					<%
					}
					else
					{
					%>
						<ul class="clearfix">
					<%
					}
					%>
			  				<s:iterator value="menuMinChildNode" id="menu" status="st">    
				  				<li>
				  					<a class="relative" id="nav_" href="javascript:void(0);" onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
										<img src='${sessionScope.basePath}<s:property value='#menu.imgpath2' />' alt='<s:property value='#menu.menuname' />' />
									</a>
									<p>
										<a href="javascript:void(0);" onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
											<s:property value='#menu.menuname' />
											<%
											if ("1".equals(keyFlag))
											{
											%>
												</br>(按<s:property value="#st.index" />键)
											<%
											}
											%>
											
										</a>
									</p>
								</li>
			  				</s:iterator>
			  			</ul>
			  		</s:if>			      	      
			  	</div>
		   	</div>
		   	<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince) || Constants.PROOPERORGID_HUB.equalsIgnoreCase(pageProvince))
			{
			%>
		   	<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
		   	<%
		   	}
		   	else if (Constants.PROOPERORGID_SD.equalsIgnoreCase(pageProvince))
		   	{
		   	%>
		   	<div class="service_quick_search bg952 mt_12 m0auto clearfix" id="showbutton">
				<input type="hidden" id="searchType" name="searchType"/>
				<p class="brand pbleft01" style="margin-left:50px;">
					<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('2'); return false;">
						<span class="cfff200">按品牌查看</span>
						<span>选择您的品牌，快速查<br />找您适用的业务</span>
					</a>
				</p>
				
				<p class="brand pbleft01" style="margin-left:20px;">
					<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('1'); return false;">
						<span class="cfff200">按分类查看</span>
						<span>选择业务分类，快速查<br />找您适用的业务</span>
					</a>
				</p>
				
				<p class="brand pbleft02" style="margin-left:20px;">
					<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('3'); return false;">
						<span class="cfff200">首字母搜索</span>
						<span>输入业务首字的拼音首<br />字母，快速查找业务</span>
					</a>
				</p>
			</div>
		   	<%
		   	}
		   	%>
		</div>
		<%--modify end g00140516 2011/12/10 R003C11L11n01 OR_HUB_201112_16 --%>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>

<!-- add begin wWX217192 2014-10-09 OR_SD_201402_795 山东_关于自助终端管理平台产品办理功能优化的需求 -->
<script type="text/javascript">

	function searchFunc(searchType){
	document.getElementById("searchType").value=searchType;
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath }reception/initFunctionList_sd.action";
	document.forms[0].submit();
}
	
</script>
<!-- add end wWX217192 2014-10-09 OR_SD_201402_795 山东_关于自助终端管理平台产品办理功能优化的需求 -->

</html>
