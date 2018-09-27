<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	// �ն���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
	
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
	
	
	String month = (String)request.getAttribute("month");
	String month1 = (String)request.getAttribute("month1");
	String month2 = (String)request.getAttribute("month2");
	String month3 = (String)request.getAttribute("month3");
	String month4 = (String)request.getAttribute("month4");
	String month5 = (String)request.getAttribute("month5");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
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
	//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = '<%=menuInfo.getParentid() %>';
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }feeservice/feeServiceFunc.action";
		document.actform.submit();
		
	}			
}

document.onkeydown = keyDown;
function keyDown(e)
{
	//8��32��66��77 ����
    //82��220 ����
    //13��89��221 ȷ��
    //80 ��ӡ
    //85 ��һҳ
    //68 ��һҳ
    
	//���ռ�����
	var key = GetKeyCode(e);
     
    //8:backspace 32:�ո� B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:����
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}

function btnClick(btn,btClass,month){

	document.getElementById("month").value = month;
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath}monthFee/monthFee.action";
	document.actform.submit();
}

</script>
</head>
<body scroll="no">
<form name="actform" method="post">	
	<input type="hidden" name="month" value="">
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����ʵ��굥��ѯ</a>
         
          <div class="blank30"></div>
          <p class="fs18 p20 ml50">��ѡ���˵��·ݣ�</p>
          <div class="blank15"></div>
          <div class="tc p120 ">
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month %>')"><%=month %></a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month1 %>')"><%=month1 %></a>
            <div class="blank25"></div>
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month2 %>')"><%=month2 %></a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month3 %>')"><%=month3 %></a>
            <div class="blank25"></div>
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month4 %>')"><%=month4 %></a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month5 %>')"><%=month5 %></a>
          </div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
