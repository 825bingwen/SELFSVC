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
						src="${sessionScope.basePath }js/public.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/script.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/dialyzer.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
					<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) {
<%--	if (submitFlag == 0) {--%>
<%--		submitFlag = 1;--%>
<%----%>
<%--		document.getElementById("curMenuId").value = curmenu;--%>
<%----%>
<%--		document.actform.target = "_self";--%>
<%--		document.actform.action = "${sessionScope.basePath }hubprodinstall/getCardInfo.action";--%>
<%--		document.actform.submit();--%>
<%----%>
<%--	}--%>
}

// 处理键盘事件
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//更正
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
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

function doSub(){

	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		
		document.actform.action = "${sessionScope.basePath }hubprodinstall/cardInfoConfirm.action";
		document.actform.submit();	
	}			
}

function doCancle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("errormessage").value="身份证信息读取错误，请检查！";
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}hubprodinstall/handleError.action";
		document.actform.submit();	
	}			
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" name="errormessage" id="errormessage" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">

				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							选号开户流程
						</h2>
						<div class="blank10"></div>
						<a href="#">1. 入网协议确认</a>
						<a href="#" class="on">2. 身份证信息记取</a>
						<a href="#">3. 产品选择</a>
						<a href="#">4. 号码选择</a>
						<a href="#">5. 费用确认</a>
						<a href="#">6. 开户缴费</a>
						<a href="#">7. 取卡打印发票</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank40"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>身份证信息确认
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							<table width="100%" class="tb_blue" align="center">
								<tr>
									<th width="40%" class="tc">
										姓名
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardName" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										性别
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardSex" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										身份证号
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardNo" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件地址
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardAddr" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件开始时间
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardStartTime" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件结束时间
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardEndTime" /> </span>
									</td>
								</tr>
							</table>
							<div class="blank20"></div>
							<div class="btn_box tc">
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doSub();">确认</a>
								</span>
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doCancle();">取消</a>
								</span>
							</div>
						</div>
					</div>
					<div class=" clear"></div>

				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
removeAclick();
</script>
</html>
