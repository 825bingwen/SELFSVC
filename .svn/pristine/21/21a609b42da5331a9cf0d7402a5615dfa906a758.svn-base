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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
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

function sendRandomPwd(prinType,formnum,billCycle)
{
		document.getElementById("formnum").value = formnum;
		document.getElementById("invoiceType").value = prinType;
		document.getElementById("billCycle").value = billCycle;						
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }invoice/validateByRandomPwd.action";
		document.actform.submit();				
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="formnum" name="formnum" value="<s:property value='formnum' />">
			<input type="hidden" id="billCycle" name="billCycle" value="">
			<input type="hidden" id="ifSendRrandPwd" name="ifSendRrandPwd"
				value=""><%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName%></p>
								<p class="ptop180 tc p747w411h" id="inn">
								<table class="tb_blue" width="100%">
									<tr>
										<!-- 标题行 -->
										<s:iterator value="servicetitle" status="title">
											<th class="list_title" align="center" width="18%"
												id="title<s:property value="#title.getIndex()"/>">
												<s:property />
											</th>
										</s:iterator>
										<th class="list_title" align="center">
											操作
										</th>
									</tr>

									<!-- 列表行 -->
									<s:iterator value="result" status="result">
										<tr>
											<s:iterator value="result.get(#result.index)" id="reData"
												status='st'>
												<s:if test="#st.index < 5">
													<s:if test="#st.index != 5">
														<td align="center">
															<s:property value='reData' />
														</td>
													</s:if>
												</s:if>
											</s:iterator>
											<td align="center">
												<input type="button" class="bt2_liebiao white" value="打印"
													onmousedown="" onmouseup=""
													onclick="sendRandomPwd(0,'<s:property value="result.get(#result.index)[5]" />','<s:property value="result.get(#result.index)[0]" />');">
											</td>
										</tr>
									</s:iterator>
									<tr>
										<td colspan="100">
											<strong>&nbsp;&nbsp;&nbsp;&nbsp;合计条数：</strong>
											<s:property value="result.size" />
											条
											<!-- 
											<input type="button" class="bt2_liebiao white" value="合打"
												onmousedown="" onmouseup=""
												onclick="sendRandomPwd(1,'<s:property value="totFormNum"/>');">
											 -->
										</td>
									</tr>
								</table>
								</p>
								<!-- 列表内容 -->
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">
									0%
								</div>
							</div>
							<input type="button" class="btnDown"
								onclick="myScroll.moveDown(30)" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">
				var myScroll = new virtualScroll("inn","gunDom");
				</script>
				<!--滚动条结束-->
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
