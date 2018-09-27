<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) {
	doCancle();
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
		return false;
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
    
	//返回
	if (key == 82 ) 
	{
		doCancle();
	}
	//确认
	else if (key == 89 || key == 13)
	{
		doSub();
	}	
}

//提交到费用确认页面
function doSub()
{
	//弹出等待框
	openRecWaitLoading();
	
	//验证手机号码与身份证号码是否相符
	var res = authIdCard();
	
	if(res != "0")
	{
		setException("身份证信息和手机号码校验失败");
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.action = "${sessionScope.basePath }prepareCard/prepareFeeConfirm.action";
		document.actform.submit();	
	}
}


//验证手机号码与身份证号码是否相符
function authIdCard()
{
	//idCardNo:身份证号码
	var postStr ="idCardPO.idCardNo="+getValue("idCardNo");
		 
	var url = "${sessionScope.basePath}prepareCard/checkIdCard.action";
	var res;
	
	//调用接口异步方法
	var loader = new net.ContentLoaderSynchro(url, netload = function() 
	{	
		res = this.req.responseText;
	}, null, "POST", postStr, null);
	
	return res;
}

// 取消
function doCancle()
{
	if (submitFlag == 0)
	{	
		//弹出等待框
		openRecWaitLoading();
		submitFlag = 1;
		document.actform.action = "${sessionScope.basePath}prepareCard/validTelAndPwd.action";
		document.actform.submit();	
	}			
}

// 显示读取到的证件图片
function showIDImg()
{
    document.getElementById("idCardImg").innerHTML = "";
    document.getElementById("idCardImg").style.filter
        = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"<s:property value="idCardPO.idCardPhoto" />\")";
    document.getElementById("idCardImg").style.display = '';
}

// 出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//弹出等待框
		openRecWaitLoading();
		
		document.getElementById("errormessage").value = errorMsg;

		//异常处理，只要出现了异常就要记录日志  
		document.actform.action = "${sessionScope.basePath }prepareCard/addRecLog.action";
		document.actform.submit();
	}
}	
</script>
	</head>
	<body scroll="no" onload="showIDImg();">
		<form name="actform" method="post">
			<!-- 错误信息 -->
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			<!-- 是否打印小票  1-不打印，0-打印 -->
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint'/>" />
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<!--------------身份证信息 -------------->
			<!-- 姓名 -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
			<!-- 性别 -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
			<!-- 证件地址 -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
			<!-- 开始时间 -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value="<s:property value='idCardPO.idCardStartTime' />" />
			<!-- 结束时间 -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value="<s:property value='idCardPO.idCardEndTime' />" />
			<!-- 卡信息 -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value="<s:property value='idCardPO.idCardContent' />" />
			<!-- 照片信息 -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value="<s:property value='idCardPO.idCardPhoto' />" />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>备卡</h2>
						<div class="blank10"></div>
	   					<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择认证方式</a>
						<a href="javascript:void(0)" class="on">3. 读取身份证信息</a>
	   					<a href="javascript:void(0)">4. 费用确认</a>
	   					<a href="javascript:void(0)">5. 选择支付方式</a>
	   					<a href="javascript:void(0)">6. 备卡缴费</a>
	   					<a href="javascript:void(0)">7. 吐卡打印小票</a>
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
									<th width="40%" class="tc">姓名</th>
									<td width="40%" class="tc">
										<span class="yellow fs20"><s:property value="idCardPO.idCardName" /></span>
									</td>
									<td width="20%" class="tc" rowspan="2" colspan="1">
										<span class="yellow fs20">
											<div id="idCardImg" style="width:102px; height:126px;"/>
										</span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">性别</th>
									<td width="20%" class="tc" colspan="1">
										<span class="yellow fs20"><s:property value="idCardPO.idCardSex" /></span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										身份证号
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"><s:property value="idCardPO.idCardNo" /></span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件地址
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardAddr" /></span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件开始时间
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardStartTime" /> </span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										证件结束时间
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardEndTime" /> </span></td>
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
</html>
