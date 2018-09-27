<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
</head>
<body scroll="no">
	<form id="actform" name="actform" method="post" >
	<s:hidden name="telProdPo.prodId" id="prodId"/>
		
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="box842W fl ml45 relative" style="margin-left: 90px; display: inline;">
				<div class="bg"></div>
				<div class="top"></div>
				<div class="con relative">
					<div class="box747W fl">
						<div class="div747w444h">
							<!-- 列表内容 -->
							<p class="ptop180 tc p747w411h" id="inn">
							<table width="100%" class="tb_blue" id="actTable">
								<tr>
									<th class="list_title" align="center" colspan="20">
										套餐列表
									</th>
								</tr>
								<tr>
									<th scope="col">
										套餐名称
									</th>
									<th scope="col">
										套餐介绍
									</th>
									<th style="width: 10%" scope="col">
										操作
									</th>
								</tr>
								<s:iterator value="telProdList" id="list" status="st">
									<tr>
										<td>
											<s:property value="#list.prodName" />
										</td>
										<td>
											<s:property value="#list.prodDesc" />
										</td>
										<td>
											<input type="button" class="bt2_liebiao" style="color: #FFFFFF;" value="选择"
											onclick='selectProd("${list.prodId}")' />
										</td>
									</tr>
								</s:iterator>
							</table>
							<br />
						</div>
					</div>
					<div class="box70W fr">
						<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
						<div class="div75w350h">
							<div class="blank10px"></div>
							<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
						</div>
						<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
					</div>
					<div class="clear"></div>
				</div>
				<div class="btm"></div>
			</div>
			<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
			<%-- 滚动条结束 --%>
		</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">

var submitFlag = 0;
function goback(curmenu) 
{	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("curMenuId").value = curmenu;
		document.actform.action="${sessionScope.basePath }login/backForward.action";
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
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function selectProd(prodid)
{
	if(submitFlag == "0")
	{	
		submitFlag = "1";
		openRecWaitLoading();
		setValue("prodId",prodid);
		document.actform.action = "${sessionScope.basePath }telProdDiy/qryProdListByProdId.action";
		document.actform.submit();
	}

}

</script>
</html>
