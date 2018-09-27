<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@ page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
	
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>缴费历史查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = '<%=menuInfo.getParentid() %>';
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }feeservice/feeServiceFunc.action";
		document.actform.submit();
		
	}			
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

    //8：backspace
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
		//R：返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		// 0键
		if (key == 48)
		{
			//topGo('qryBill', 'feeservice/feeServiceFunc.action');
			goback("<s:property value='curMenuId' />")
			return;
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
	 	
function fireOnClick(typeName) 
{
	oLink = document.getElementById(typeName);
	if (oLink != null && typeof (oLink) != "undefined") 
	{
		oLink.onclick();
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
		 	<div class="main" id="main">
		  		<%@ include file="/customer.jsp"%>
				
				<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) 
                	{
                %>
                <a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
                <%
                	}
                	else if ("1".equals(keyFlag))
                	{
                %>
                <a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
                <%
                	}
                	else
                	{
                %>
                <a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
                <%
                	}
                %>				
				
		        <!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							
							<div class="div747w444h">
								<!-- 列表内容 -->
		                        <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn">
							
								<table class="tb_blue" align="center" width="100%">
									<tr class="tr_color">
										<s:generator val="tableTitle" separator=",">
											<s:iterator status="title">
												<th class="list_title" align="center">
													<s:property />
												</th>
											</s:iterator>
										</s:generator>										
									</tr>
									<s:iterator value="records" id="record" status="st">
										<tr class="tr_font">
											<s:generator val="#record" separator=",">
												<s:iterator status="item">
													<td align="center">
														<s:property />
													</td>
												</s:iterator>
											</s:generator>
										</tr>									
									</s:iterator>
								</table>
									<p class="blank10"></p>
								</div>	
										
								<!-- 列表内容 -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								
								<%
                				if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province)) 
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