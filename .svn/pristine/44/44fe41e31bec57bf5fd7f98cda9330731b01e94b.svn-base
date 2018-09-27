<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.common.base.CEntityString"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Vector dataVector = (Vector)request.getAttribute("invoiceList");
	request.setAttribute("invoiceList", dataVector);
	CEntityString sTitle = (CEntityString)dataVector.get(0);
	String[] titles = sTitle.EntityString.split(",");
	CRSet data = (CRSet)dataVector.get(1);
	String curmenuid = (String)request.getAttribute(Constants.CUR_MENUID);
%>
<html>
<head>
<title>发票打印列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script>
var submitFlag = 0;
	
function doSub(spBizName)
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.getElementById("spBizName").value = spBizName;
		document.actform.action="${sessionScope.basePath}service/invoicePrint.action";
		document.actform.submit();
	}
}

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
		goback("<s:property value='#request.curMenuId' />");
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
</script>
</head>
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" name="spBizName" value=""/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
		        <!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
		                        <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px; width:747px; overflow:hidden;" >
								
								
				                <table width="100%" class="tb_blue" >
				                  <tr>
			                    	<% 
			                  		for(int i=0;titles!=null&&i<titles.length;i++){
				                  	%>
				                  		<th scope="col"><%=titles[i]%></th>
				                  	<%
				                  	}
				                  %>
				                  </tr>
				                  <% 
				                  	if(dataVector != null && data.GetRowCount()!= 0)
									  {
									      for(int i =0 ;i < data.GetRowCount(); i++) 
									      {
									      	%>
									      		<tr>
								                    <td><%=data.GetValue(i, 3)%></td>
								                    <td><%=data.GetValue(i, 2)%></td>
								                    <td><%=data.GetValue(i, 0) %></td>
								                    <td>
							                    	<input type="button" class="bt2_liebiao" style="color:#FFFFFF;"
							                    	  value="打印" onmousedown="this.className='bt2on'" 
								                    	onmouseup="this.className='bt2';doSub('<%=data.GetValue(i, 4)%>');"/>
				                  				</tr>
									      	<%
									      }
								      }
				                  %>
				                </table>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
		          
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
