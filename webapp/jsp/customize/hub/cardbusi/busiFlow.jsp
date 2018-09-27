<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%
	// 获取当前菜单
	String currMenuID = request.getParameter(Constants.CUR_MENUID);

	// 业务流程Id
	String busiFlowId = request.getParameter("busiFlowId");
	
	// 步骤序号
	String stepNum = request.getParameter("stepNum");
	
	// busiFlowId为空时取菜单Id
	if (null == busiFlowId || "".equals(busiFlowId))
	{
		busiFlowId = currMenuID.substring(0, 1).toUpperCase() + currMenuID.substring(1) + "Flow";
	}

	// 获取业务流程项
	List<DictItemPO> busiFlowItems = (List<DictItemPO>) PublicCache.getInstance().getCachedData(busiFlowId);
	
	// 取得菜单名称
    List menuList = (List)PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
	
	// 当前菜单信息
    MenuInfoPO menuInfo = null;
	
	// 循环全部菜单取得当前菜单信息
    if (menuList != null && menuList.size() > 0)
    {
        for (int i = 0; i < menuList.size(); i++)
        {
        	menuInfo = (MenuInfoPO)menuList.get(i);
        	
            if (currMenuID.equals(menuInfo.getMenuid()))
            {
                break;
            }
        }
    }
%>
<div class="b257 ">
	<div class="bg bg257"></div>
	<h2><%=(null != menuInfo) ? menuInfo.getMenuname() : "无菜单信息" %></h2>
	<div class="blank10"></div>
	<%
		if (null != busiFlowItems && busiFlowItems.size() > 0)
		{
			for (int i = 0; i < busiFlowItems.size(); i++)
		    {
				DictItemPO busiFlowItem = busiFlowItems.get(i);
				
				// 是否选中
				boolean isSelect = false;
				
				if (busiFlowItem.getDictid().equals(stepNum))
				{
					isSelect = true;
				}
	%>
				<a href="javascript:void(0)" <%=(isSelect) ? "class='on'" : "" %>><%=busiFlowItem.getDictid() %>. <%=busiFlowItem.getDictname() %></a> 
	<%	        
		    }
		}
	%>
</div>
