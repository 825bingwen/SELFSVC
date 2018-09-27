<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>
<%
List menuHubList2 = (List)request.getAttribute("menuHubList2");
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

// 产品详情查询
function prodDetail(prodID,region,brand)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("prodID").value = prodID;
		document.getElementById("region").value = region;
		document.getElementById("brand").value = brand;
		
		document.actform.target="_self";
		document.actform.action = "${sessionScope.basePath}quickpublish/prodDetail.action";
		document.actform.submit();	
	}
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<%--modify begin CKF76106 2012/10/12 R003C12L09n01 OR_HUB_201209_884 --%>
		<input type="hidden" id="firstFlag" name="firstFlag" value="1"/>
		<input type="hidden" id="prodID" name="prodID" value=""/>
		<input type="hidden" id="region" name="region" value=""/>
		<input type="hidden" id="brand" name="brand" value=""/>
		<input type="hidden" id="hotRecFlag" name="hotRecFlag" value="1"/>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="service_brand w966 m0auto">
			<div class="service_index_list">
				<p class="hot_service"></p>
				<ul class="clearfix">
				<%
					if(null != menuHubList2 && menuHubList2.size() > 0)
					{
						for(int i=0;i<menuHubList2.size();i++)
						{
							MenuInfoPO menupo = (MenuInfoPO)menuHubList2.get(i);
							// 展示产品配置表中产品
							if(menupo.getIsProd().equals("1"))
							{
								ProdConfigPO prodpo =  menupo.getProdConfigPO();
								
								String subName = "";
								if(null != prodpo.getProdName())
								{
									subName = prodpo.getProdName();
									if(subName.length()>8)
									{
										subName = subName.substring(0,6)+"...";
									}
								}
				%>
								<li>
									<a class="relative" href="javascript:void(0);" onclick="prodDetail('<%=prodpo.getNcode() %>','<%=prodpo.getAreaCode() %>','<%=prodpo.getBrand() %>'); return false;"><img style="width:105px;height:106px" src='${sessionScope.basePath}<%=prodpo.getImgPath() %>'  alt='<%=prodpo.getProdName() %>' /></a>
									<p><a href="javascript:void(0);" onclick="prodDetail('<%=prodpo.getNcode() %>','<%=prodpo.getAreaCode() %>','<%=prodpo.getBrand() %>'); return false;"><%=subName %></a></p>
								</li>
				<%
							}
							// 展示菜单
							else
							{
				%>
								<li>
									<a class="relative" id="nav_<%=menupo.getMenuid() %>" href="javascript:void(0);" onclick="topGo('<%=menupo.getMenuid() %>', '<%=menupo.getGuiobj() %>'); return false;"><img src='${sessionScope.basePath}<%=menupo.getImgpath2() %>' alt='<%=menupo.getMenuname() %>' /></a>
									<p><a href="javascript:void(0);" onclick="topGo('<%=menupo.getMenuid() %>', '<%=menupo.getGuiobj() %>'); return false;"><%=menupo.getMenuname() %></a></p>
								</li>
				<%
							}
						}
					}
				%>
				</ul>
			    
			  </div>
		   </div>
		   <pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
		</div>
		<%--modify end CKF76106 2012/10/12 R003C12L09n01 OR_HUB_201209_884 --%>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
