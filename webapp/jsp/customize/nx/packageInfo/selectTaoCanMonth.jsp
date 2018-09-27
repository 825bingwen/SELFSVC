<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
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
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		
	}			
}

document.onkeyup = pwdKeyboardUp;
		
function pwdKeyboardUp(e)
{
	//接收键盘码
	var key = GetKeyCode(e);
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 退出
	if (key == 82 || key == 220)
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   		return ;
	}
	// 0键
	else if (key == 48)
	{
		//topGo('qryService', 'serviceinfo/serviceInfoFunc.action');
		goback("<s:property value='curMenuId' />") ; 
		return;
	}
	
	// 1、2、3、4、5、6键
	if(key == 49)
	{
		btnClick(null, '', '<%=month %>');
	}
	else if(key == 50)
	{
		btnClick(null, '', '<%=month1 %>');
	}
	else if(key == 51)
	{
		btnClick(null, '', '<%=month2 %>');
	}
	else if(key == 52)
	{
		btnClick(null, '', '<%=month3 %>');
	}
	else if(key == 53)
	{
		btnClick(null, '', '<%=month4 %>');
	}
	else if(key == 54)
	{
		btnClick(null, '', '<%=month5 %>');
	}
	
}


function btnClick(btn,btClass,month)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	
		document.getElementById("month").value = month;
	
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}comboInfo/qryComboInfo.action";
		document.actform.submit();
	}	
}
</script>
</head>
<body scroll="no">
<form name="actform" method="post">	
	<input type="hidden" name="month" id="month" value="">
	<input type="hidden" name="qryType" id="qryType" value="<s:property value="#request.qryType"/>">
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		  <a href="#" class="bt10_1 fr mr92" onmousedown="this.className='bt10_1on fr mr92'" onmouseup="this.className='bt10_1 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
          <div class="blank30"></div>
          <p class="fs18 p20 ml50">请选择查询月份：</p>
          <div class="blank15"></div>
          <div class="tc p120 ">
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month %>')"><%=month %>&nbsp;(按1键)</a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month1 %>')"><%=month1 %>&nbsp;(按2键)</a>
            <div class="blank25"></div>
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month2 %>')"><%=month2 %>&nbsp;(按3键)</a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month3 %>')"><%=month3 %>&nbsp;(按4键)</a>
            <div class="blank25"></div>
            <a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this,'bt222','<%=month4 %>')"><%=month4 %>&nbsp;(按5键)</a>
            <a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this,'bt222','<%=month5 %>')"><%=month5 %>&nbsp;(按6键)</a>
          </div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
