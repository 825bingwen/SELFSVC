<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp" %>

<%	
	// 当前菜单ID
	String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}

	// 终端信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);	
	
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);

	String brandID = "";
	if (customerSimp != null)
	{
		brandID = customerSimp.getBrandID();
	}
	
	//根据终端组自缓存中获取菜单列表
    String groupID = termInfo.getTermgrpid();
                    
	List<MenuInfoPO> menus = null;
                    
	if (groupID != null && !"".equals(groupID))
	{                    
		menus = (List<MenuInfoPO>) PublicCache.getInstance().getCachedData(groupID);
	}
                    
	// 当页菜单列表
    List menuList = CommonUtil.getChildrenMenuInfo(menus, currMenuID, brandID);

	// 处理页面分页
	String pageStr = request.getParameter("pagecount");
	int currPage = (pageStr == null) ? 0 : Integer.parseInt(pageStr);
	
	String month = request.getParameter("month");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>NG2.3自助终端系统--移动话费查询--月详单查询</title>
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
		
		//查询详单
		function go(btn,btClass,menuid,url) 
		{	
			document.getElementById("curMenuId").value = menuid;			
			document.getElementById("menuURL").value = url;
			document.getElementById("pagecount").value = 0;
			
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();				
			}	
		}
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}feeservice/detailedRecords.action";
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
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="month" value="<%=month %>">
			<input type="hidden" name="type" value="">			
			<input type="hidden" id="pagecount" name="pagecount" value="<%=currPage %>">
			<input type="hidden" id="menuURL" name="menuURL" value="">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) 
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
                <%
                	}
                	else
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
                <%
                	}
                %>					
				
				<div class="b966 tc">
				    <div class=" p40">
				    	<div class="blank30"></div>
						<p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span>详单类型选择</p>
						<div class="blank30"></div>
						<div id="btns" class="fl" >
							<%
							for (int i=0;i<menuList.size();i++)
							{
								MenuInfoPO menu = (MenuInfoPO) menuList.get(i);
							%>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this,'bt222','<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>');">
				    				<%=menu.getMenuname() %>
				    			</a>
							</span>
							<%
							}
							%>
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
