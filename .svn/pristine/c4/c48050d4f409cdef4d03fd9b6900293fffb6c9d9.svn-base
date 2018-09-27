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
//防止页面重复提交
var submitFlag = 0;

//82、220 返回

document.onkeydown = pwdKeyboardDown;

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

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//R:82 退出
	if (key == 82 || key == 220) 
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		return;
	}
	// 0键
	else if (key == 48)
	{
		//topGo('qryBill', 'feeservice/feeServiceFunc.action');
		goback('<s:property value='curMenuId' />');
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
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">			
		<%@ include file="/titleinc.jsp"%>
  		<div class="main" id="main">
	 		<%@ include file="/customer.jsp"%>
	 		
			<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
	      	<!--滚动条-->
			<div class="box842W fl ml45 relative">
				<div class="bg"></div>
				<div class="top"></div>
				<div class="con relative" >
					<div class="box747W fl">					
						<div class="div747w444h">
							<!-- 列表内容 -->
	                      	<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
							<div class="blank30"></div>
							<div class="ptop180 tc p747w411h" id="inn" >
								<table width="80%" class="tb_blue" align="center">
									<tr class="tr_color">
										<th width="80%" class='list_title'>
											余额类型
										</th>
										<th width="20%" class='list_title'>
											金 额（元）
										</th>
									</tr>
									<s:iterator value="serviceTitle" status="title" >
										<tr class="tr_color">
											<td class="tc"><s:property />：</td>
											<td class="tc"><span class="yellow"><s:property value="balanceStr[#title.getIndex()]"/></span></td>
										</tr>
									</s:iterator>
								</table>
								<p class="blank10"></p>
							</div>
						</div>							
					</div>
					<div class="box70W fr">
						<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
						<div class="div75w350h">
							<div class="blank10px"></div>
							<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
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
