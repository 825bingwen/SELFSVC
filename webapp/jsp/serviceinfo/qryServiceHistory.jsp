<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
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
		// 上翻
		else if (key == 85)
		{
			myScroll.moveUp(30);
			return;
		}
		// 下翻
		else if (key == 68)
		{
			myScroll.moveDown(30);
			return;
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
        		<!--滚动条-->
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn" >
			                 		<table width="85%" class="tb_blue">
			                     		<tr>
				                      		<s:iterator value="servicetitle" status="title">
				                          		<th class="list_title" align="center" id="title<s:property value="#title.getIndex()"/>"><s:property/></td>
				                      		</s:iterator>
							     		</tr>
								    	<s:if test="result != null && result.size > 0">
								        	<s:iterator value="result" status="result">
								            	<tr>
								                	<s:iterator value="result.get(#result.index)">
								                    	<td align="center"><s:property/></td>
								                	</s:iterator>
								            	</tr>
								        	</s:iterator>
								    	</s:if>
				                     	<tr>
				                        	<td colspan="100"> <strong>&nbsp;&nbsp;&nbsp;&nbsp;合计条数：</strong><s:property value="result.size"/>条</td>
				                     	</tr>
				                  	</table>
				                  	<p class="blank10"></p>
								</div>								
								<!-- 列表内容 -->
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h" >
								<div class="blank10px"></div>
								<%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
								<%
								}
								else
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								}
								%>
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
