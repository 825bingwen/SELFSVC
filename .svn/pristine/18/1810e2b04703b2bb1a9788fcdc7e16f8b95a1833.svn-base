<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;
var writeFlag = 0;

// 返回服务密码设置
function goback(curmenu) {
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.action="${sessionScope.basePath }prepareCard/selectCertOrPwd.action";
		document.actform.submit();				
	}		
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
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
	
	if (key == 48)
	{
		goback("<s:property value='curMenuId' />") ; 
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
	
	//确认
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}	
}

function doCancle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("errormessage").value="身份证信息读取错误，请检查！";
		document.actform.action = "${sessionScope.basePath}hubprodinstall/handleError.action";
		document.actform.submit();	
	}			
}

// 再次写卡流程
function againWriteCardProcess()
{
	try
	{
		var res;
		// 写卡失败 1
		setValue("writeCardStatus","1");
		
		//读卡
		res = moveCard();
		
		// 读卡失败
		if (res != "0")
		{
			setException(res);
			return;
		}
		
		//校验卡资源
		res = checkBlankCard();
		
		//校验失败
		if (res[0] != "0")
		{
			setException(res[1]);
			return;
		}
		
		// 提交缴费请求前先写卡
		var writeData = writeCard(getValue("cardInfoStr"),getValue("blankCard"),
			"${sessionScope.basePath}","${sessionScope.CustomerSimpInfo.servNumber}");
		
		// 二次写卡失败 跳转异常页面
		if(writeData != 0)
		{
			setException(writeData.split("~")[1]);
			return;
		}
		
		// 写卡成功
		setValue("writeCardStatus","0");
	}
	catch(e)
	{
		//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
		setValue("writeCardStatus","1");
		setException("交费成功，写卡失败，补卡业务办理失败");
	}
}

/**
 * 第一次写卡流程
 * 若调用写卡控件后写卡失败，则进行二次写卡
 * 否则，直接跳转至错误页面
 */
function writePrepareCard()
{
	try
	{
		// 缴费状态  0 成功 1 失败
		setValue("payStatus","0");
		
		//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
		setValue("writeCardStatus","1");
		
		//检查发卡器状态，返回""表示成功
		var message = checkReadCardStatus();
		if (message != "")
		{
			setException(message);
			return;
		}
		
		//调用公共写卡方法
		var writeData = writeCard(getValue("cardInfoStr"),getValue("blankCard"),
				"${sessionScope.basePath}","${sessionScope.CustomerSimpInfo.servNumber}");
		
		// 写卡失败 进行二次写卡
		if(writeData.indexOf("11~") != -1)
		{
			// 再次写卡
			againWriteCardProcess();
			return;
		}
		
		// 写卡过程异常，跳转异常页面
		if(writeData.indexOf("1~") != -1)
		{
			setException(writeData.split("~")[1]);
			return;
		}
		
		// 写卡成功
		setValue("writeCardStatus","0");
	}
	catch(e)
	{
		//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
		setValue("writeCardStatus","1");
		setException("缴费成功，写卡失败，补卡业务办理失败");
	}
}

//点确认按钮
function doSub()
{
	//弹出等待框
	openRecWaitLoading();
		
	//若补卡费用为0，则直接进行写卡流程
	if ((getValue("totalFee") == 0 || getValue("totalFee") == "0") && writeFlag == 0)
	{	
		//写卡标识位
		writeFlag = 1;
		
		//写卡
		writePrepareCard();
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//补卡费用为0，则跳转至补卡提交页面
		if(getValue("totalFee") == 0 || getValue("totalFee") == "0")
		{
			document.actform.action="${sessionScope.basePath}prepareCard/freeCommit.action";
		}
		else
		{
			//提交至选择支付方式页面
			document.actform.action="${sessionScope.basePath }prepareCard/selectPayTypePrepare.action";
		}
		document.actform.submit();	
	}
}

//调用备卡算费接口
function prepareRecFee()
{
	var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")
		+"&simInfoPO.iccid="+getValue("iccid")
		+"&curMenuId="+getValue("curMenuId");   
		 
	var url = "${sessionScope.basePath}prepareCard/prepareRecFee.action";
	
	//调用备卡算费接口异步方法
	var loader = new net.ContentLoaderSynchro(url, netload = function () 
	{	
		var resXml = this.req.responseText;
		var resArray = resXml.split('~~');
		
		//调用备卡算费接口成功
		if (resArray[0] == 0 || resArray[0] == "0")
		{
			setValue("totalFee",resArray[1]);
			document.getElementById("totalFeeSpan").innerHTML = resArray[1];
       	}
		else
		{	
			setException(resArray[1]);
			return;
		}
	}, null, "POST", postStr, null);
}

/**
 * 1.校验卡资源是否可用
 * 2.查询空白卡是否是预置空卡
 * 3.空白卡资源暂选
 */
function checkBlankCard()
{
	//blankCard:空白卡号
	var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard");
		 
	var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
	var resArray;
	
	//调用接口异步方法
	var loader = new net.ContentLoaderSynchro(url, netload = function () 
	{	
		var resXml = this.req.responseText;
		resArray = resXml.split('~~');
		
		//调用接口成功
		if (resArray[0] == 0 || resArray[0] == "0")
		{
			setValue("iccid",resArray[1]);
			setValue("imsi",resArray[2]);
			setValue("cardInfoStr",resXml.substring(3).replace("+", "^^"));
       	}
	}, null, "POST", postStr, null);
	
	return resArray;
}

//移动卡到读卡位并读取
function moveCard()
{
	//检查发卡器状态，返回""表示成功
	var ret = checkReadCardStatus();
	if (ret != "")
	{
	 	return ret;
	}
	
	//获取空白卡卡号
	var blankCard = readBlankCardSN();
	
	//获取空白卡卡号失败返回 1~
	if (blankCard.indexOf("1~") != -1)
	{
		return blankCard.split('~')[1];
	}
	
	//若为20位的新卡，继续，否则结束流程
	if (20 == blankCard.length)
	{
		setValue("blankCard",blankCard);
	}
	else
	{
		return "读空白卡卡号失败！";
	}
	
	return "0";
}

/**
 * 页面初始化时加载
 * 1、读空白卡
 * 2、校验空白卡资源
 * 3、备卡算费
 */
function doLoad()
{
	var res;
	
	//读卡
	res = moveCard();
	
	// 读卡失败
	if (res != "0")
	{
		setException(res);
		return;
	}

	//校验卡资源
	res = checkBlankCard();
	
	//校验失败
	if (res[0] != "0")
	{
		setException(res[1]);
		return;
	}
	
	//备卡算费
	prepareRecFee();
	
}
		
//出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//弹出等待框
		openRecWaitLoading();
		
		document.getElementById("errormessage").value = errorMsg;

		//异常处理，只要出现了异常就要记录日志  
		if(getValue("writeCardStatus")==1 || getValue("writeCardStatus")=="1")
		{
			//写卡失败记录日志
			document.actform.action = "${sessionScope.basePath }prepareCard/goFreeWriteError.action";
		}
		else
		{
			document.actform.action = "${sessionScope.basePath }prepareCard/addRecLog.action";
		}
		
		document.actform.submit();
	}			
}	

</script>
</head>
<body scroll="no"  onload="doLoad();">
<form name="actform" method="post">
	<input type="hidden" name="errormessage" id="errormessage" />
	<!-- 空白卡卡号 -->
	<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value='cardBusiLogPO.blankCard'/>" />
	<!-- ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value='simInfoPO.iccid'/>" />
	<!-- IMSI -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value='simInfoPO.imsi'/>" />
	<!-- 空白卡信息字符串（iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2） -->
	<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>" />
	<!-- 总费用 -->
	<input type="hidden" id="totalFee" name="totalFee" value="<s:property value='totalFee'/>" />
	<!-- 实际缴费 -->
	<input type="hidden" id="tMoney" name="tMoney" value="0">
	
	<%--是否需要回收卡，1 需要 --%>
	<input type="hidden" id="callBackCard" name="callBackCard" value="" />
	
	<!-- 写卡状态  默认2：初始状态 0：写卡成功 1：写卡失败-->
	<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
	<!-- 交费状态  默认2：初始状态 0：缴费成功 1：缴费失败-->
	<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
	<!-- 是否打印小票  1-不打印，0-打印 -->
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint'/>" />
	<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
	<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value='payTypeFlag' />"/>
	
	<!--------------身份证信息 -------------->
	<!-- 姓名 -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
	<!-- 性别 -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
	<!-- 身份证号码 -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
	<!-- 证件地址 -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
	
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
				<a href="javascript:void(0)">3. 读取身份证信息</a>
				<a href="javascript:void(0)" class="on">4. 费用确认</a>
				<a href="javascript:void(0)">5. 选择支付方式</a>
				<a href="javascript:void(0)">6. 备卡缴费</a>
				<a href="javascript:void(0)">7. 吐卡打印小票</a>
			</div>

			<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank40"></div>
				<div class="p40">
					<p class=" tit_info">
						<span class="bg"></span>费用明细列表
						<span class="yellow"></span>
					</p>
					<div class="blank15"></div>
					<table id="maintab" align="center" width="100%" class="tb_blue02">
						<tr>
							<th width="50%" class="tc">号码</th>
							<th width="50%" class="tc">总费用（元）</th>
						</tr>
						<tr>
							<td width="50%" class="tc"><span class="yellow fs16">${sessionScope.CustomerSimpInfo.servNumber }</span></td>
							<td width="50%" class="tc"><b><span id="totalFeeSpan" class="yellow fs16">${totalFee}</span></b></td> 
						</tr>
					</table>
					<div class="blank20"></div>
				</div>
				<div class="blank20"></div>
						<a href="#" class="bt10 mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;float:right;right:210px;">确认(请按确认键)</a>
			</div>
			<div class=" clear"></div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
</body>
</html>