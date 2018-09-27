<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	int pageCount = (Integer)request.getAttribute("pageCount");
	String firstFlag = (String)request.getAttribute("firstFlag");
	if(firstFlag == null)
	{
		pageCount =1;
	}
	List list = new ArrayList();
	int pageNum = 12;
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
// 上一页
function previousPage()
{
	if (submitFlag == 0)
	{
		// 如果当前页为第一页则不进行上一页操作
		if(<%=pageCount%>==1)
		{
			return;
		}
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }reception/receptionFunc.action?pageCount=<%=pageCount-1%>";
		document.actform.submit();
	}
}
// 下一页
function nextPage(totalNum)
{
	if (submitFlag == 0)
	{
	 	// 如果为最后一页则不进行下一页操作
		if(<%=pageCount * pageNum%> > totalNum )
		{
			return;
		}
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }reception/receptionFunc.action?pageCount=<%=pageCount+1%>";
		document.actform.submit();
	}
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<input type="hidden" id="firstFlag" name="firstFlag" value="1"/>
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			  <%@ include file="/customer.jsp"%>
			  <div class="service_brand w966 m0auto">
			  <div class="service_index_list">
			    <p class="hot_service">热点服务</p>
			      <ul class="clearfix">
			      <%
					    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
					    {
					    	for (int i = 0; i < titleTotalMenus.size(); i++)
					    	{
					    		MenuInfoPO menu = (MenuInfoPO) titleTotalMenus.get(i);
					    		
					    		 if ("serv".equalsIgnoreCase(menu.getParentid()) 
					                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
					                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))) 
					            {
					            	list.add(menu);
					            }
					    	}
					    	int start = (pageCount-1) * pageNum;
					    	int end = (pageCount) * pageNum ;
					    	if(end > list.size())
					    	{
					    		end = list.size();
					    	}
					    	for(int j = start;j < end ;j++)
					    	{
					    		MenuInfoPO menu = (MenuInfoPO)list.get(j);
					    		%>
									<li>
										<a class="relative" id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><img src='${sessionScope.basePath}<%=menu.getImgpath2() %>' /></a>
										<p><a href="javascript:void(0);" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><%=menu.getMenuname() %></a></p>
									</li>
								<%
					    	}
					    }
			       %>
			      </ul>
			      <!-- 如果只有一页则不显示分页 -->
			      <%
			      if(list.size() > pageNum)
			      {
			      	%>
			      		<div class="num_foot fs18">  
							<div class="recption"> 
								<a href="javascript:previousPage()" class="btleft relative"></a> 
								<span class="ml20">第<%=pageCount %>页，共<%=(list.size()/pageNum)+1 %>页</span> 
							<a href="javascript:nextPage(<%=list.size() %>)" class="btright ml20 relative"></a> 
						</div>
					</div>
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
