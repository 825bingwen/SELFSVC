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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
	}			
}

document.onkeydown = keyDown;
function keyDown(e)
{
	//8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
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
					<div class="service_list h505">
						<s:if test="menuHubList2 != null && menuHubList2.size() > 0">
							<ul class="clearfix">
								<s:iterator value="menuHubList2" id="menu">
									<!-- 产品展示 -->
									<s:if test="#menu.isProd == 1">
										<li>
											<a class="awrap" href="javascript:void(0);"
												onclick="prodDetail('<s:property value='#menu.prodConfigPO.ncode' />', '<s:property value='#menu.prodConfigPO.areaCode' />', '<s:property value='#menu.prodConfigPO.brand' />'); return false;">
												<h2>
													<s:property value='#menu.prodConfigPO.prodName' />
												</h2>
												<h3>
													·
													<s:property value='#menu.prodConfigPO.prodName' />
												</h3> 
											</a>
										</li>
									</s:if>
									<!-- 菜单展示 -->
									<s:else>
										<li>
											<a class="awrap" href="javascript:void(0);"
												onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
												<h2>
													<s:property value='#menu.menuname' />
												</h2>
												<h3>
													·
													<s:property value='#menu.tiptext' />
												</h3> 
											</a>
										</li>
									</s:else>
								</s:iterator>
							</ul>
						</s:if>
					</div>
				</div>
					<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
			</div>
			<%@ include file="/backinc.jsp"%>
			<%--modify end CKF76106 2012/10/12 R003C12L09n01 OR_HUB_201209_884 --%>
		</form>
	</body>
</html>
