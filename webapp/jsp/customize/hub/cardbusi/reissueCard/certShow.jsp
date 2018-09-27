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
		checkAndSub();
	}	
}

// 出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		setValue("errormessage",errorMsg);

		//异常处理，只要出现了异常就要记录日志  
		document.actform.action = "${sessionScope.basePath }reissueCard/addRecLog.action";
		document.actform.submit();
	}
}

/**
 * 校验空白卡资源
 *
 * 1、校验空白卡资源是否可用
 * 2、检验是否为预制空卡
 * 3、预占空白卡资源
 *
 */
function checkBlankCard()
{
	var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")
		+"&curMenuId="+getValue("curMenuId"); 
		 
	var url1 = "${sessionScope.basePath}reissueCard/authBlankCard.action";
	var resArray;
	
	//调用缴费前记录信息接口异步方法
	var loader1 = new net.ContentLoaderSynchro(
		url1, netload = function () 
		{
			var resXml1 = this.req.responseText;
			resArray = resXml1.split('~~');
			
			//调用缴费前记录信息接口成功
			if (resArray[0] == 0 || resArray[0] == "0")
			{
				setValue("iccid",resArray[1]);
				setValue("imsi",resArray[2]);
				setValue("eki",resArray[3]);
				setValue("smsp",resArray[4]);
				setValue("pin1",resArray[5]);
				setValue("pin2",resArray[6]);
				setValue("puk1",resArray[7]);
				setValue("puk2",resArray[8]);
	       	}
		}, 
		null, "POST", postStr, null);
		
	return resArray;
}

//移动卡到读卡位并读取
function moveCard()
{
	// 将空白卡走到读卡位置
	ret = checkReadCardStatus();
	
	if (ret != "")
	{
	 	return "读取空白卡失败！";
	}

	//获取空白卡卡号
	var blankCard = readBlankCardSN();
	
	//若为20为的新卡，继续，否则结束流程
	if (20 == blankCard.length)
	{
		setValue("blankCard",blankCard);
	}
	else
	{
		return "读取空白卡卡号失败！";
	}
	
	return "0";
}

/**
 * 补卡规则校验
 * 1、校验身份证和手机号码是否相符。
 * 2、查询用户信息。
 * 3、校验补卡业务规则
 */
function authIdCard()
{
	var postStr ="idCardPO.idCardNo="+getValue("idCardNo")+"&curMenuId="+getValue("curMenuId");  
	var url1 = "${sessionScope.basePath}reissueCard/checkIdCardAndReissueNum.action";
	var resArray;
	
	//调用缴费前记录信息接口异步方法
	var loader1 = new net.ContentLoaderSynchro(
		url1, netload = function () 
		{
			var resXml1 = this.req.responseText;
			resArray = resXml1.split('~~');
		}, 
		null, "POST", postStr, null);
		
	return resArray;
}

// 校验并提交提交
function checkAndSub()
{
	//弹出等待框
	openRecWaitLoading();
	
	//延时提交，解决等待框加载慢的情况
	setTimeout("certSub()", 500);
}

//提交
function certSub()
{
	if (submitFlag == 0)
	{
		//校验用户身份证信息和补卡次数
		var res = authIdCard();
		
		//校验结果 为0是校验通过
		if (res[0] != "0")
		{
			setException(res[1]);
			return;
		}
		
		//读取空白卡
		res = moveCard();
		
		if (res != "0")
		{
			setException(res);
			return;
		}
		
		//校验空白卡资源
		res = checkBlankCard();
		
		//校验结果 为0是校验通过
		if (res[0] != "0")
		{
			setException(res[1]);
			return;
		}
		
		submitFlag = 1;
		
		document.actform.action = "${sessionScope.basePath }reissueCard/feeConfirm.action";
		document.actform.submit();	
	}
}

// 取消
function doCancle()
{
	if (submitFlag == 0)
	{
		//弹出等待框
		openRecWaitLoading();
	
		submitFlag = 1;
		document.actform.action = "${sessionScope.basePath}reissueCard/inputTelnum.action";
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
</script>
	</head>
	<body scroll="no" onload="showIDImg();">
		<form name="actform" method="post">
			<%-- 错误信息 --%>
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			<%-- 空白卡卡号--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="" />
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<%-- simInfoPO信息--%>
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="" />
			<input type="hidden" id="smsp" name="simInfoPO.smsp" value="" />
			<input type="hidden" id="pin1" name="simInfoPO.pin1" value="" />
			<input type="hidden" id="pin2" name="simInfoPO.pin2" value="" />
			<input type="hidden" id="puk1" name="simInfoPO.puk1" value="" />
			<input type="hidden" id="puk2" name="simInfoPO.puk2" value="" />
			<input type="hidden" id="eki" name="simInfoPO.eki" value="" />

			<%--身份证信息类 --%>
			<!-- 姓名 -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<!-- 性别 -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<!-- 证件地址 -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>补卡</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)" class="on">2. 读取身份证信息</a>
    					<a href="javascript:void(0)">3. 费用确认</a>
    					<a href="javascript:void(0)">4. 选择支付方式</a>
    					<a href="javascript:void(0)">5. 补卡缴费</a>
    					<a href="javascript:void(0)">6. 取卡打印小票</a>
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
									onmouseup="this.className='btn_bg_146';checkAndSub();">确认</a>
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
