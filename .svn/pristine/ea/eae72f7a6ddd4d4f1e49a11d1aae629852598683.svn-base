<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.gmcc.boss.selfsvc.login.model.NserCustomerSimp" %>
<%
String currProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

//add begin xkf29026 2011/10/19 当前状态查询：用户名字不显示全部，只显示姓，后面的字用*代替。仅限山东
String custName = ((NserCustomerSimp)request.getAttribute("customer")).getCustomerName();

if(Constants.PROOPERORGID_SD.equalsIgnoreCase(currProvince))
{
    if(custName != null && custName.length() > 0)
    {
        //modify begin l00190940 2011/10/25 R003C11L10n01 当前状态查询的时候不显示用户全名，固定显示两个**和名字的最后一个字，如用户叫闫涛，显示**涛 仅限山东
        String custNameString = custName.substring(custName.length()-1);
        custName = "**" + custNameString;
        //modify end l00190940 2011/10/25 R003C11L10n01 当前状态查询的时候不显示用户全名，固定显示两个**和名字的最后一个字，如用户叫闫涛，显示**涛 仅限山东
    }
}
//add end xkf29026 2011/10/19 当前状态查询：用户名字不显示全部，只显示姓，后面的字用*代替。仅限山东

//add begin yWX163692 2013/11/21  OR_NX_201310_1186  宁夏客户界面信息模糊化展示
if(Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
{
	custName = CommonUtil.getVagueName(custName);
}
//add end yWX163692 2013/11/21  OR_NX_201310_1186  宁夏客户界面信息模糊化展示

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
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

// 处理键盘事件
document.onkeyup = pwdKeyboardUp;
		
function pwdKeyboardUp(e)
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
    
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		if (key == 48)
		{
			//topGo('qryService', 'serviceinfo/serviceInfoFunc.action'); 
			goback("<s:property value='curMenuId' />") ;
		}
	<%
	}
	%>
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
		  	<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
			  	<%@ include file="/customer.jsp"%>
				
				<div class="blank50">
				<%
				if ("1".equals(keyFlag))
				{
				%>
					<a href="#" class="bt10_1 fr mr92" onmousedown="this.className='bt10_1on fr mr92'" onmouseup="this.className='bt10_1 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
				<%
				}
				else
				{
				%>
					<a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				<%
				}
				%>
				</div>
				<div class="b966">
	          		<div class=" p40">
	          			<div class="blank30"></div>
	            		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
	            		<div class="blank30"></div>
	            		<div class="relative p40">	            
	            			<div class="blank15"></div>
	            			<div class="p20">
				                <table width="100%" class="tb_blue" >
				                  <tr>
				                    <th scope="col">手机号码</th>
				                    <th scope="col">客户姓名</th>
				                    <th scope="col">所属地市</th>
				                    <%
				                    if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
				                    {
				                    %>
				                    <th scope="col">品牌名称</th>
				                    <%
				                    }
				                     %>				                    
				                    <th scope="col">当前状态</th>
				                  </tr>
				                  <tr>
				                    <td><s:property value="customer.servNumber"/></td>
				                    <td><%=custName %></td>
				                    <td><s:property value="customer.regionName"/></td>
				                    <%
				                    if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
				                    {
				                    %>
				                    <td><s:property value="customer.brandName"/></td>
				                    <%
				                    }
				                    %>				                    
				                    <td><s:property value="currentStatus"/></td>
				                  </tr>
				                </table>	
	             			</div>	             
						</div>
	            	</div>
	          	</div>
		  	</div>
		  	<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
	<script type="text/javascript">
	    openSurveyDialog();
	</script>
	<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
</html>
