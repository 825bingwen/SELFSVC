<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>移动自助终端</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
	<%@ include file ="/titleinc.jsp" %>
	<div class="main" id="main">
		<%@ include file="/customer.jsp" %>
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
						<p class="ptop180 p747w411h" id="inn">
						<font size="3" color="yellow" >1)扣除您已产生的消费（<s:property value="curFee"/>元）后，您当前余额<s:property value="leftBalance"/>元（不含信用额度）。余额用完后，您有<s:property value="credit" />信用额度。可享受缓停机服务。
						<br>2)若不扣除您已产生的消费，最新余额明细：</font>
						
						<table class="tb_blue" width="100%">
							<tr>
								<th width="15%">余额大类</th>
								<th width="15%">科目类别</th>
								<th width="15%">可消费范围</th>
								<th width="20%">余额（元）（未核减当月消费金额）</th>
								<th width="20%">有效期</th>
								<th width="15%">当月账单中专款可销的费用（元）</th>
							</tr>
							<s:iterator value="balanceList">
								<tr>
									<td>
										<s:property value="subjectClass"/>
									</td>
									<td>
										<s:property value="subjectType" />
									</td>
									<td>
										<s:property value="range" />
									</td>
									<td>
										<s:property value="consume" />
									</td>
									<td>
										<s:property value="expiryDate" />
									</td>
									<td>
										<s:property value="funds" />
									</td>
								</tr>
							</s:iterator>
						</table>
								
						<!-- 列表内容 -->
					</div>
							
				</div>
				<div class="box70W fr">
					<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
					<div class="boxPage" style="width:75px; height:350px; ">
						<div class="blank10px"></div>
						<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px" >0%</div>
					</div>
					<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
				</div>
				<div class="clear"></div>
			</div>
			<p><font size="4" color="red">&nbsp;&nbsp;&nbsp;注：对于专款余额，您若未产生相关消费，对应专款则不可用，故来计算在开头的“当前余额”中。</font></p>
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
<script type="text/javascript">

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
</html>