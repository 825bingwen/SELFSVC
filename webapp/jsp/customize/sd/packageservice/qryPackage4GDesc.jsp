<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<style type="text/css">
		.btn_back_104 {
			width: 104px;
			height: 56px;
			left: 862px;
			_left: 882px;
			background: url(images/can/back_btn.png) no-repeat;
			_filter: progid : DXImageTransform . Microsoft .
				AlphaImageLoader(enabled = true, sizingMethod = scale, src =
				"images/can/back_btn.png");
			_background: none;
		}
		
		.fav_title {
			margin-top: 30px;
			margin-left: 32px;
		}
		
		.tab_966 .con {
			padding-top: 32px;
		}
		
		.tab_966 .bg {
			background: url("images/can/fav_bg.png") repeat scroll 0 0 transparent
				!important;
			_filter: progid : DXImageTransform . Microsoft .
				AlphaImageLoader(enabled = true, sizingMethod = scale, src =
				"images/can/fav_bg.png");
			_background: none;
			height: 526px;
			width: 966px;
		}
		
		.box747W p {
			font-size: 18px;
			line-height: 24px;
			padding: 0 20px;
			margin-bottom: 40px;
		}
		
		.box747W .con {
			height: 444px;
			padding: 0px;
			width: 747px;
			overflow: hidden;
			_filter: progid : DXImageTransform . Microsoft .
				AlphaImageLoader(enabled = true, sizingMethod = scale, src = "");
			background: none;
		}
		
		.tab_966 .bg2{width:966px; height:562px; background:url(../images/can/fav_bg-1.png) repeat scroll 0 0 transparent!important;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/can/fav_bg-1.png");_background:none}
		</style>
		
	</head>
	<body scroll="no" onload="">
		<form name="actform" method="post" target="_self">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<p class="fav_title fs18" style="margin-top: 10px;">
					<%=menuName %>
				</p>
				<div class=" fl ml20 relative tab_966 fav_detail" style="margin-top: 0px; height: 580px;">
					<div class="bg2"></div>
					<div class="con relative" style="margin-top: 3px; padding-left: 0px;">
						<div class="box747W fl">
							<div class="con" id="inn1" style="height: 500px; padding: 0px; width: 740px; margin: 0px auto; overflow: hidden;">
								<s:if test="package4GDescList != null && package4GDescList.size > 0">
									<s:iterator value="package4GDescList" id="dictItemPO">
										<div class="package">
											<div class="kage_top">
												<s:property value="#dictItemPO.dictname" />
											</div>
											<div class="kage_conter">
												<s:property value="#dictItemPO.description"
													escapeHtml="false" />
											</div>
										</div>
										<div class="border_bottom"></div>
									</s:iterator>
								</s:if>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width: 75px; height: 395px;">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style="width: 66px; height: 30px; position: absolute; cursor: move; left: 827px; top: 85px; line-height: 30px">
									0%
								</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">
				    myScroll = new virtualScroll("inn1","gunDom");
				    myScroll.parentTop = myScroll.parentTop + 33;
				</script>
				<!--滚动条结束-->
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	// 点击了返回，等待应答，不再执行任何操作
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
    
	//82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
  		return ;
	}
}

</script>
</html>
