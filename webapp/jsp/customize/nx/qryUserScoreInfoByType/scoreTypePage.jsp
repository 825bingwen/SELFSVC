<%--
 @User: zWX176560
 @De: 2013/8/28
 @comment: 显示积分类型
 @remark: create zWX176560 时间 2013/8/28 OR_NX_201308_595_宁夏_新开发积分查询接口 版本号(R003C11L09n01)
--%>

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
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
<META HTTP-EQUIV="Expires" CONTENT="0"/>
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
			openRecWaitLoading_NX("recWaitLoading");
			goback("<s:property value='curMenuId' />") ;
			
		}
		<s:if test="userScoreTypeList != null && userScoreTypeList.size() > 0">
			<s:iterator value="userScoreTypeList" id="userScoreTypeList" status="st">
				if (key == <s:property value="#st.index + 49" />)
				{
					qryScoreValue('<s:property value="scoreId" />','<s:property value="scoreType" />');
					return;
				}
			</s:iterator>
		</s:if>
	<%
	}
	%>

}

//查询相应类型的积分
function qryScoreValue(id,type)
{
	openRecWaitLoading_NX("recWaitLoading");
	document.actform.target="_self";
	document.actform.action = "${sessionScope.basePath}qryUserScoreInfoByType/qryUserScoreInfoByType.action?userScorePO.scoreId="+id+"&userScorePO.scoreType="+type;
	document.actform.submit();
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
				<div class="service_brand w966 m0auto">
					<div class="service_list">
						<ul class="clearfix">
							
								<s:if
									test="userScoreTypeList != null && userScoreTypeList.size() > 0">
									<s:iterator value="userScoreTypeList" status="st">
										<li>
											<a class="awrap" href="javascript:void(0)"
												onclick="qryScoreValue('<s:property value="scoreId" />','<s:property value="scoreType" />'); return false;">
												<%
												if ("1".equals(keyFlag))
												{
												%>
													<h2 class="menutip">
														<s:property value="scoreType" />
														<br />
														(请按<s:property value='#st.index + 1' />键)
													</h2> <%
												}
												else
												{
												%>
													<h2>
														<s:property value="scoreType" />
													</h2> <%
												}
												%>
												<h3>
													点击可查询您的<s:property value="scoreType" />。
												</h3> </a>
										</li>
									</s:iterator>
								</s:if>
						
						</ul>
					</div>
				</div>	      	
			</div>
			
			<%@ include file="/backinc.jsp"%>		
			</form>
	</body>
</html>
