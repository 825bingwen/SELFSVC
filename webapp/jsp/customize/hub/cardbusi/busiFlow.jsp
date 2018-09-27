<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%
	// ��ȡ��ǰ�˵�
	String currMenuID = request.getParameter(Constants.CUR_MENUID);

	// ҵ������Id
	String busiFlowId = request.getParameter("busiFlowId");
	
	// �������
	String stepNum = request.getParameter("stepNum");
	
	// busiFlowIdΪ��ʱȡ�˵�Id
	if (null == busiFlowId || "".equals(busiFlowId))
	{
		busiFlowId = currMenuID.substring(0, 1).toUpperCase() + currMenuID.substring(1) + "Flow";
	}

	// ��ȡҵ��������
	List<DictItemPO> busiFlowItems = (List<DictItemPO>) PublicCache.getInstance().getCachedData(busiFlowId);
	
	// ȡ�ò˵�����
    List menuList = (List)PublicCache.getInstance().getCachedData(Constants.MENU_INFO);
	
	// ��ǰ�˵���Ϣ
    MenuInfoPO menuInfo = null;
	
	// ѭ��ȫ���˵�ȡ�õ�ǰ�˵���Ϣ
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
	<h2><%=(null != menuInfo) ? menuInfo.getMenuname() : "�޲˵���Ϣ" %></h2>
	<div class="blank10"></div>
	<%
		if (null != busiFlowItems && busiFlowItems.size() > 0)
		{
			for (int i = 0; i < busiFlowItems.size(); i++)
		    {
				DictItemPO busiFlowItem = busiFlowItems.get(i);
				
				// �Ƿ�ѡ��
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
