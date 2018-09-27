<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%     
    // 终端信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
	List totalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
    
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
	
	MenuInfoPO menuInfo = null;
	if (totalMenus != null && totalMenus.size() > 0)
	{
		for (int i = 0; i < totalMenus.size(); i++)
		{
			menuInfo = (MenuInfoPO) totalMenus.get(i);
			if (currMenuID.equals(menuInfo.getMenuid()))
			{
				break;
			}
		}
	}
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
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		
	}			
}

function btnClick(btn,btClass,type){

	//如果已经点击，再次点击，不执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("type").value = type;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}prodOrder/queryProdList.action";
		document.actform.submit();
	}
}

</script>
</head>
<body scroll="no">
<form name="actform" method="post">	
	<input type="hidden" id="type" name="type" value="">
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
          <div class="blank30"></div>
          <p class="fs18 p20 ml50">请选择：</p>
          <div class="blank15"></div>
	    	<div class="tc p120 ">
	          <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','0')">已有业务</a>
	          <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','1')">可办理业务</a>
	        </div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
