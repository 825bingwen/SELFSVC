<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String successBz = (String)request.getAttribute("successBz");
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
<script type="text/javascript" src="${sessionScope.basePath }js/dummyKey.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}css/IE8.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	// modify begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
	<%
	if ("0".equals(successBz))
	{
	%>
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
			document.actform.submit();
		}
	<%
	}
	else
	{
	%>
		history.go(-1);
	<%
	}
	%>
	// modify end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面	
}

// 处理键盘事件
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

// 打印凭证
function printInfo()
{
	//已经点击了返回，不能再进行打印
	if (submitFlag == 1)
	{
		return;
	}
	var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
	var Ret3;
	try{
		Ret3 = DPsListPrinter1.PrintPicture(1);
	}
	catch(e)
	{
		alertError("由于打印机控件未安装，您的号码预约凭证打印失败，给您带来的不便，敬请原谅。");
		return;
	}
   	if (Ret3 == 1)
   	{
      	alertError("由于打印机缺纸或未知故障，您的号码预约凭证打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
   	else if (Ret3 == 41)
   	{
      	alertError("由于打印机设备底层驱动程序未安装，您的号码预约凭证打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
   	
   	var Ret4;
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      欢迎使用中国移动");
	Ret4 = DPsListPrinter1.PrintLine("      自助选号开户功能");
	Ret4 = DPsListPrinter1.PrintLine("      电话号码：");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='telnum' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      验证码：");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='coid' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      失效时间：");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='ctime' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
    Ret4 = DPsListPrinter1.PrintLine("      需预存金额：<s:property value='payfeeY' />元");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      选号营业厅：");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='locus' escape="false"/>");
    Ret4 = DPsListPrinter1.PrintLine("      友情提示：");
    <%         
         /*************打印输出友情提示***************/
         int n = 12;//一次输出字符个数         
         int L=((String)request.getAttribute("cremind")).length();    //字符串长度为L,
			   int num=(L /n);        // n为每次取得字符个数，num为有多少组这样的子串
				
			if (L==0)
			{
		%>
				Ret4 = DPsListPrinter1.PrintLine("      ");
		<%
		  			
			}else	if (L<=n)
			{
				 String s=(String)request.getAttribute("cremind");
	  %>
				 Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
		<%
		  			
			}else {
			int m=0;
			for(int i=0;i<num;i++){  	
				String s=((String)request.getAttribute("cremind")).substring(m,m+n);
	  %>
				Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
		<%
				m=m+n;
				}
				
		  if (m<L){
		  		String s=((String)request.getAttribute("cremind")).substring(m,L);
		%>
		  		Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
	  <%
		  	}	
		  }   
    %>       
	
	if (Ret4 == 1)
   	{
      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
   	else if (Ret4 == 41)
   	{
      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
   	
	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
   	if (Ret4 == 1)
   	{
      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
   	else if (Ret4 == 41)
   	{
      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
      	return;
   	}
  	
   	Ret4 = DPsListPrinter1.PrintLine("  以上内容，如有不详之处，请向营业员咨询。");
   	Ret4 = DPsListPrinter1.PrintLine("  客户服务热线：10086");
   	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
   	Ret4 = DPsListPrinter1.PrintLine("  打印地点：<s:property value='locus' escape="false"/>");
   	Ret4 = DPsListPrinter1.PrintLine("  打印时间: " + getCurrentTime());
   	
   	//切纸
	var Ret5 = DPsListPrinter1.SetCutPaper();
	if (Ret5 == 1)
	{
		alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		return;
	}
	else if (Ret5 == 41)
	{
		alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		return;
	}
}

// 页面加载时执行
function finish()
{
	if ('<s:property value="successBz" />' == '0')
	{
		printInfo();
	}
}

<!-- Add by LiFeng 直接打印 -->
function doFinish()
{
    finish();
}
</script>
</head>
<body scroll="no" onload="doFinish();">
<form name="actform" method="post"">			
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<a title="返回选号开户" href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回选号开户</a>
		<div class="b966 tc">
			<div class="blank30"></div>
			<div class=" p40">
				<p class="tit_info" align="left"><span class="bg"></span>自助选号</p>
				<div class="blank60"></div>
				<div class="blank60"></div>
				<span class="yellow fs30">
					<%
					if ("0".equals(successBz))
					{
					%>
					<ul><li>手机号：<s:property value="telnum"/>&nbsp;已成功预约,请妥善保管打印凭条!&nbsp;&nbsp;&nbsp;&nbsp;</li></ul>
					<br />
					<ul><li>并在48小时内到营业厅办理!&nbsp;&nbsp;&nbsp;&nbsp;</li></ul>
					<!-- Change by LiFeng 直接打印
					<br>
					<a href="#" class="bt2 fs16" onclick="finish()">打印凭证</a>
					 -->
					<%
					}
					else
					{
					%>
					<ul><li><s:property value="alertMsg"/></li></ul>
					<%
					}
					%>
				</span>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
