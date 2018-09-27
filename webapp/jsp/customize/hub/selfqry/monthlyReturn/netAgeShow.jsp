<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
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
</script>
</head>
<body scroll="no">
<form name="actform" method="post">			
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">		
		<%@ include file="/customer.jsp"%>
		<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
		<div class="b966">
			<div class="blank30"></div>
			<div class="relative p40">
				<p class="tit_info tl"><span class="bg"></span><%=menuName %></p>
				<div class="blank30"></div>
				<p class="p20">
					<%--modify begin g00140516 2011/10/29 R003C11L10n01 修改页面样式 --%>          				
					<table width="60%" class="tb_blue" align="center">
						<tr>
						<th width="30%" class="tc">
										入网时间：
									</th>
									<td width="70%" class="tc">
										<span class="yellow">
											<s:property value="#session.CustomerSimpInfo.onlineTime" />
										</span>
									</td>
						</tr>
						<tr>
						<th width="15%" class="tc">
										在网时长：
									</th>
									<td width="35%" class="tc">
										<span class="yellow">
											<s:property value="#session.CustomerSimpInfo.subage" />
										</span>
									</td>
						</tr>
					
					
<!--						<s:generator val="title" separator=",">-->
<!--							<s:iterator id="st" status='offset'>-->
<!--								<s:if test="#offset.Odd"> -->
<!--									<tr>-->
<!--								</s:if>-->
<!--									<th width="15%" class="tc">-->
<!--										<s:property value="#st" />：-->
<!--									</th>-->
<!--									<td width="35%" class="tc">-->
<!--										<span class="yellow">-->
<!--											<s:property value="result.get(#st)" />-->
<!--										</span>-->
<!--									</td>-->
<!--								<s:if test="#offset.Even"> -->
<!--									</tr>-->
<!--								</s:if>-->
<!--							</s:iterator>-->
<!--						</s:generator>				-->
					</table>
                    				
					
                    <%--modify end g00140516 2011/10/29 R003C11L10n01 修改页面样式 --%>  										
				</p>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
