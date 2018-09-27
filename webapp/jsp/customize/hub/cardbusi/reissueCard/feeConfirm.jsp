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

//上一页
function goback(curmenu) 
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.action="${sessionScope.basePath }reissueCard/inputTelnum.action";
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
	
	//确认
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}
}

/**
 * 确认费用后提交
 * 流程：若补卡费用不为0，则跳转至选择交费类型页面。
 *      若补卡费用为0，则进入写卡流程。
 */
function doSub()
{
	//弹出等待框
	openRecWaitLoading();
			
	setTimeout("writeAndSub()",500);		
}

/**
 * 延时后提交，解决弹出框不能正常加载的问题
 */
function writeAndSub()
{
	//若补卡费用为0，则直接进行写卡流程
	if (getValue("recFee") == 0 && writeFlag == 0)
	{
		//写卡标识位
		writeFlag = 1;
		
		writeReissueCard();
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//补卡费用为0，则跳转至补卡提交页面
		if(getValue("recFee") == 0)
		{
			document.actform.action="${sessionScope.basePath}reissueCard/reissueCommit.action";
		}
		
		//否则提交至选择交费方式页面
		else
		{
			document.actform.action="${sessionScope.basePath}reissueCard/selectPayType.action";
		}
		
		document.actform.submit();	
	}
}

/**
 * 第一次写卡流程
 * 若调用写卡控件后写卡失败，则进行二次写卡
 * 否则，直接跳转至错误页面
 */
function writeReissueCard()
{
	//交费状态 默认2：初始状态 0：缴费成功 1：缴费失败
	setValue("payStatus","0");
	
	//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
	setValue("writeCardStatus","1");
	
	//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("eki")+ "~~" +getValue("smsp").replace("+", "^^");
	simInfo = simInfo+"~~"+getValue("pin1")+"~~"+getValue("pin2")+"~~"+getValue("puk1")+"~~"+getValue("puk2");
	
	//项目路径
	var basePath = "${sessionScope.basePath}";
	
	//写卡:入参：写卡加密数据，空白卡卡号，流水号（用于写卡结果校验，与申请写卡时的入参一致）   
	var writeData = writeCard(simInfo,getValue("blankCard"), basePath, "${sessionScope.CustomerSimpInfo.servNumber }");
	
	// 写卡失败 进行二次写卡
	if(writeData.indexOf("11~") != -1)
	{
		// 再次写卡
		writeAgain(basePath);
		return;
	}

	//第一次写卡时如不是写卡控件异常，直接跳转，不再二次写卡
	if(writeData != "0" && writeData.indexOf("11~") == -1)
	{
		setException(writeData.split("~")[1]);
		return;
	}
	
	//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
	setValue("writeCardStatus","0");
}

/**
 * 二次写卡流程
 * 入参：basePath 项目路径，js中无法解析s标签
 * 流程：1、空白卡移到读卡位。2、读空白卡卡号。3、检验空白卡资源
 * 		4、写卡。5、修改写卡状态
 */
function writeAgain(basePath)
{
	//1、将卡移到读卡位
	var message = checkReadCardStatus();
	if (message != "")
	{
		setException(message);
		return;
	}
	
	//2、读取空白卡序列号
	var blankCardSN = readBlankCardSN();
	
	if (20 != blankCardSN.length)
	{
		setException("读取空白卡卡号失败！");
		return;
	}
	
	setValue("blankCard",blankCardSN);
			
	//3、校验空白卡资源
	var resXml = checkBlankCard();
	var resArray = resXml.split('~~');      
	
	//校验失败
	if (resArray[0] != "0")
	{
		setException(resArray[1]);
		return;
	}
	
	//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var simInfo = resXml.substring(3).replace("+", "^^");
	
	//4、写卡
	var writeData = writeCard(simInfo, getValue("blankCard"), basePath, "${sessionScope.CustomerSimpInfo.servNumber}");
	
	// 二次写卡失败 跳转异常页面
	if(writeData != 0)
	{
		setException(writeData.split("~")[1]);
		return;
	}
	
	// 写卡成功
	setValue("writeCardStatus","0");
}

/**
 * 校验空白卡资源
 * 流程：1、校验是否为预制空卡。2、校验卡资源是否可用。
 *      3、预占卡资源
 */
function checkBlankCard()
{
	var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")+"&curMenuId="+getValue("curMenuId");  
		 
	var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
	var resXml;
	
	//同步调用
	var loader = new net.ContentLoaderSynchro(
		url, netload = function () 
		{
			resXml = this.req.responseText;
			var resArray = resXml.split('~~');
			if (resArray[0] == 0 || resArray[0] == "0")
			{
				setValue("iccid",resArray[1]);
				setValue("imsi",resArray[2]);
				setValue("smsp",resArray[4]);
			}
		}, 
		null, "POST", postStr, null);
		
	return resXml;
}

/**
 *  写卡异常时跳转
 */
function setException(errorMsg) 
{	
	if (submitFlag == 0)
	{
		// 提交标志置为1
		submitFlag = 1;
		
		//弹出等待框
		//openRecWaitLoading();
	
		//显示错误信息
		document.getElementById("errormessage").value = errorMsg;
		
		//异常处理，只要出现了异常就要记录日志  
		document.actform.action = "${sessionScope.basePath }reissueCard/goFreeWriteError.action";
		document.actform.submit();
	}			
}
</script>
</head>
<body scroll="no">
<form name="actform" method="post">
	<input type="hidden" name="errormessage" id="errormessage" />
	<%-- 是否打印小票  1-不打印，0-打印 --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
	<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
	
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
				
	<%--是否需要回收卡，1 需要 --%>
	<input type="hidden" id="callBackCard" name="callBackCard" value="" />
	
	<!-- 实际缴费金额 -->
	<input type="hidden" id="tMoney" name="tMoney" value="0" />
	
  	<%------补卡受理日志信息---------%>
 	<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
	<%-- 写卡状态  默认2：初始状态 0：写卡成功 1：写卡失败--%>
	<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
	<%-- 交费状态  默认2：初始状态 0：缴费成功 1：缴费失败--%>
	<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
	<%-- 空白卡卡号--%>
	<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
    
    <%----------------交费日志信息---------------%>
	<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" value="<s:property value = 'cardChargeLogVO.chargeLogOID' />">
			
    <!--------------空白卡信息 -------------->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value="<s:property value="simInfoPO.smsp" />" />
	<input type="hidden" id="pin1" name="simInfoPO.pin1" value="<s:property value="simInfoPO.pin1" />" />
	<input type="hidden" id="pin2" name="simInfoPO.pin2" value="<s:property value="simInfoPO.pin2" />" />
	<input type="hidden" id="puk1" name="simInfoPO.puk1" value="<s:property value="simInfoPO.puk1" />" />
	<input type="hidden" id="puk2" name="simInfoPO.puk2" value="<s:property value="simInfoPO.puk2" />" />
	<input type="hidden" id="eki" name="simInfoPO.eki" value="<s:property value="simInfoPO.eki" />" />
	
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
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)" class="on">3. 费用确认</a>
				<a href="javascript:void(0)">4. 选择支付方式</a>
				<a href="javascript:void(0)">5. 补卡缴费</a>
				<a href="javascript:void(0)">6. 取卡打印小票</a>
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
					<table width="100%" class="tb_blue02" align="center">
						<tr>
							<th width="25%" class="tc">号码</th>
							<td width="25%" class="tc"><span class="yellow fs16">${sessionScope.CustomerSimpInfo.servNumber}</span></td>
							<th width="25%" class="tc">品牌</th>
							<td width="25%" class="tc">
							<span class="yellow fs16">${sessionScope.CustomerSimpInfo.brandName}</span>
							 </td>
						</tr>
					</table>
					<div class="blank20"></div>
					<table id="maintab" align="center" width="100%" class="tb_blue02">
						<tr>
							<th width="50%" class="tc">费用名称</th>
							<th width="13%" class="tc">金额（元）</th>
						</tr>
						<s:iterator value="feeList" id="feeList">
						<tr>
							<td width="50%" class="tc"><s:property value="#feeList.feeName" /></td>
							<td width="13%" class="tc"><s:property value="#feeList.fee" /></td>
						</tr>
						</s:iterator>
					</table>
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