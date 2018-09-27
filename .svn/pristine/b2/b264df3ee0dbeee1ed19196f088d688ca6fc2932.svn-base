<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("recFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

// 交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>银行卡交费确认</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script language="javascript">
// 交费提交标识
var submitFlag = 0;

// 异常提交标志 0:未提交 1:已提交
var exSubmitFlag = 0;

// 核对时长
var limitTime = 60;

//计算剩余时间的句柄
var timeToken;		

var termid = "<%=termInfo.getTermid() %>";

// 商户类型
var unitType = "1";

// 交易类型 'A':消费交易 'B':重打印 'C':查余额
var businesstype = "A";// 

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
	var key = GetKeyCode(e);
	
	//提交
	if (key == 13 || key == 89 || key == 221)
	{
		commitBusi();
	}
	// 银联卡交费，确认金额界面，用户可主动退出
	//退出：82
	else if (key == 82)
	{
		cancelBusi();
	}
}

//出现异常
function setException(errorMsg) 
{
	//if (exSubmitFlag == 0)
	//{
		exSubmitFlag = 1;	//提交标记
		
		writeDetailLog("<%=chargelog_detailflag %>", "银联卡交费异常：" + errorMsg);
			
		//清除定时任务
		clearInterval(timeToken);
	
		document.getElementById("errormessage").value = errorMsg;
		
		//异常处理，只要出现了异常就要记录日志
		document.actForm.target = "_self";
		document.actForm.action = "${sessionScope.basePath }prodInstall/installError.action";
		document.actForm.submit();
	//}
}

// 与BOSS计帐(银联卡)
function goSuccess() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "扣款交费成功");
	document.getElementById("toFee").value = document.getElementById("tMoney").value;
	//提交缴费请求
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }prodInstall/installCardCommit.action";
	document.actForm.submit();			
}

function strlen(str)    
{    
    var i;    
    var len;    
        
    len = 0;    
    for (i=0;i<str.length;i++)    
    {    
        if (str.charCodeAt(i)>255) len+=2; else len++;    
    }
    return len;    
} 

//银联卡扣款
function fPosPay()
{
	try
	{
		writeDetailLog("<%=chargelog_detailflag %>", "扣款之前记录交费日志");
	
	document.getElementById("errorType").value = "add";
	
	//扣款之前记录日志
	var url = "${sessionScope.basePath }prodInstall/addChargeLog.action";
	
	var params = "telnum=" + <s:property value='telnum' /> ;
	params = params + "&paytype=1";
	params = params + "&tMoney=" + <s:property value='recFee' />*100;
	params = params	+ "&status=00";
	params = params + "&description=" + encodeURI(encodeURI('扣款之前记录日志'));
	params = params + "&acceptType=" + document.getElementById("acceptType").value;
	params = params	+ "&region=" + document.getElementById("region").value;

	var loader = new net.ContentLoader(url, netload = function () {
		var resXml = this.req.responseText;
		var dataArray = resXml.split("~~");
		
		//记录日志成功
		if (dataArray[0] == "1") 
		{
			writeDetailLog("<%=chargelog_detailflag %>", "日志记录成功");
		
			//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
			document.getElementById("errorType").value = "update";
				
			document.getElementById("chargeId").value = dataArray[1];							
			
			//记录受理编号（缴费记录编号）
			document.getElementById("dealNum").value = dataArray[1];				
			try
			{
  					writeDetailLog("<%=chargelog_detailflag %>", "卡机关闭");
  					
  				//生产用
  				// 关闭读卡器和密码键盘所有打开的线程
				var ret = CloseReadingCardFixing();

				if (ret != 0)
				{
					setException("关闭读卡器和密码键盘所有打开线程失败，请取走您的银联卡。");
						
					return;
				}
			}
			catch(e)
			{
				setException("关闭读卡器和密码键盘所有打开线程失败，请取走您的银联卡。");
						
				return;
			}
				
			// 设置密码键盘为密码键盘模式
			try
			{
				writeDetailLog("<%=chargelog_detailflag %>", "设置密码键盘工作模式为密文");
				
				//生产用
				var ret = SetWorkMode(1);// 设置密码键盘为密码键盘模式
				
				if (ret == -1)
				{
					setException("银联扣款之前设置密码键盘的工作模式失败，请取走您的银联卡。");
						
					return;
				}
			}
			catch(e)
			{
				setException("银联扣款之前设置密码键盘的工作模式失败，请取走您的银联卡。");
					
				return;
			}							
				
			// 发起银联卡扣款请求	
			var continueFlag = false;
			
			// 传入参数
			var bankrequest;
			
			// 传出参数
			var bankresponse;
			
			// LRC校验_3位随机数字
			var randomNumber = "" + Math.floor(Math.random()*10) + Math.floor(Math.random()*10) + Math.floor(Math.random()*10);
			
			try
			{
				// 交易请求报文
				// POS机号(8) + POS员工号(8) + 交易类型标志(2)_'41:缴费' + 金额(12) + 原交易日期(12) + 原交易参考号(12) + 原凭证号(6) 
				// LRC校验(3) + 电话号码(20) + 请求发票类型(2)_00:不返回发票；01:返回发票；冲正时为空格
				// 交易码(2)_01按号码交费,11按挂帐帐号交费 + 行业信息1(20) + 行业信息(20)
				//bankrequest = formatStr(termid,'right',' ',8);// POS机号(8)
				// POS机号(8)
				bankrequest = createBlankStr(8);
				
				// POS员工号(8)
				bankrequest = bankrequest + createBlankStr(8);
				
				// 交易类型标志(2)_'41:缴费'
				bankrequest = bankrequest + '41';
				
				// 金额(12)
				bankrequest = bankrequest + formatStr('<%=fenMoney %>','left','0',12);
				
				// 原交易日期(8)
				bankrequest = bankrequest + createBlankStr(8);
				
				// 原交易参考号(12)
				bankrequest = bankrequest + createBlankStr(12);
				
				// 原凭证号(6)
				bankrequest = bankrequest + createBlankStr(6);
				
				// LRC校验(3)
				bankrequest = bankrequest + randomNumber;
				
				// 电话号码(20)
				bankrequest = bankrequest + formatStr('<s:property value="telnum" />','right',' ',20);
				
				// 请求发票类型(2)_00:不返回发票；01:返回发票；冲正时为空格
				bankrequest = bankrequest + '01';
				
				// 交易码(2)_01按号码交费,11按挂帐帐号交费
				bankrequest = bankrequest + '01';
				
				// 行业信息1
				bankrequest = bankrequest + createBlankStr(20);
				
				// 行业信息2
				bankrequest = bankrequest + createBlankStr(20);
				
				writeDetailLog("<%=chargelog_detailflag %>", "扣款请求报文：" + bankrequest);
						
				// 生产用
				// 返回码:2,银行行号:4,卡号:20,凭证号:6,金额:12,错误说明:40,商户号:15,终端号:8,批次号:6,交易日期:4,
				// 交易时间:6,交易参考号:12,授权号:6,清算日期:4,LRC校验:3
				
				// 交易请求报文
				if (true)// true:生产 false:测试
				{
					window.parent.document.getElementById("unionpluginid").bankrequest = bankrequest;
					
					// 执行缴费
					window.parent.document.getElementById("unionpluginid").trans();
					
					// 交易返回报文
					bankresponse = window.parent.document.getElementById("unionpluginid").BankResponse;
				}
				else
				{
					// 成功
					bankresponse = '00    4563518600005509778 001778000000002000交易成功                                09510000000000100000002      1126164654000001217097          ' + randomNumber;
					
					// 失败
					//bankresponse = 'Y1                                          二磁道信息错误                                                                                          '; 
				}
				
				writeDetailLog("<%=chargelog_detailflag %>", "扣款应答报文：" + bankresponse);
				
				continueFlag = true;
			}
			catch (e){}
			
			// 设置密码键盘为普通键盘模式
			try
			{
				writeDetailLog("<%=chargelog_detailflag %>", "设置密码键盘工作模式为明文格式");
				
				//生产用
				var ret = initKeyBoard();// 设置密码键盘为普通键盘模式
			}
			catch(e)
			{
			}
				
			if (!continueFlag)
			{
				writeDetailLog("<%=chargelog_detailflag %>", "扣款异常");
				
				setException("银联卡缴费失败，请取走您的银联卡。");
				return;
			}
			
			// 扣款成功 定长148 成功00 比对随机数是否一样
			if (bankresponse.substring(0,2) == "00" && strlen(bankresponse) == 148 && bankresponse.substring(bankresponse.length-3,bankresponse.length) == randomNumber)
			{
			    // 凭证号 + 交易参考号
				document.getElementById("terminalSeq").value = bankresponse.substring(26,32) + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);
				document.getElementById("tMoney").value = bankresponse.substring(32,44);
				writeDetailLog("<%=chargelog_detailflag %>", "扣款交费成功，交费金额：" + document.getElementById("tMoney").value);
						
				//更新日志
				var url1 = "${sessionScope.basePath }prodInstall/updateCardChargeLog.action";
		
				var params1 = "chargeId=" + document.getElementById("chargeId").value;// id
				params1 = params1 + "&bankno=" + bankresponse.substring(2,6);// 银行行号 发卡行代码
				params1 = params1 + "&cardnumber=" + bankresponse.substring(6,26);// 卡号
				params1 = params1 + "&vouchernum=" + bankresponse.substring(26,32);// 评证号
				params1 = params1 + "&unionpayfee=" + bankresponse.substring(32,44);// 扣款金额
				params1 = params1 + "&unionpayuser=" + bankresponse.substring(bankresponse.length-64,bankresponse.length-49);// 商户号
				params1 = params1 + "&unionpaycode=" + bankresponse.substring(bankresponse.length-49,bankresponse.length-41);// 终端号
				params1 = params1 + "&busitype=" + encodeURI(encodeURI("缴费交易"));// 交易类型
				params1 = params1 + "&posnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// 批次号
				params1 = params1 + "&batchnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// 批次号
				params1 = params1 + "&unionpaytime=" + bankresponse.substring(bankresponse.length-35,bankresponse.length-31) + bankresponse.substring(bankresponse.length-31,bankresponse.length-25);// 银联扣款时间
				params1 = params1 + "&businessreferencenum=" + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);// 交易参考号
				params1 = params1 + "&authorizationcode=" + bankresponse.substring(bankresponse.length-13,bankresponse.length-7);// 授权码
				params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;// 终端流水
				params1 = params1 + "&status=01";
				params1 = params1 + "&description=" + encodeURI(encodeURI('开户时扣款成功'));
				params1 = params1 + "&telnum=<s:property value='telnum' />";
						
				var loader1 = new net.ContentLoader(url1, netload = function () {
					var resXml1 = this.req.responseText;
					
					//更新日志成功
					if (resXml1 == "1" || resXml1 == 1)
					{
						writeDetailLog("<%=chargelog_detailflag %>", "扣款交费成功后，更新日志记录成功");
						
						//交费
						goSuccess();									
					}
					//更新日志失败
					else
					{
						writeDetailLog("<%=chargelog_detailflag %>", "扣款交费成功后，更新日志记录失败");
						
						setException("银联卡缴费成功，但是更新日志失败。请取走您的银联卡。");
						
						return;
					}								
				}, null, "POST", params1, null);
				
			}
			//扣款失败
			else
			{
				writeDetailLog("<%=chargelog_detailflag %>", "扣款失败");
				
				setException("银联卡缴费失败，请取走您的银联卡。原因：" + bankresponse.replace(/\s/g,''));
				return;
			}		
		}
		//记录日志失败
		else 
		{
			writeDetailLog("<%=chargelog_detailflag %>", "日志记录失败");
				
				setException("缴费之前记录日志失败，请取走您的银联卡。");
				
				return;
			}					
		}, null, "POST", params, null);	
	}
	catch (e)
	{
		setException("银联卡缴费异常，请取走您的银联卡。");
	}				
}

//计算剩余时长
function waitForSubmit() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "等待用户确认缴费");

if (limitTime <= 0)
{
	return;
}

//读密码时长一共limitTime秒
limitTime = limitTime - 1;
			
document.getElementById("tTime").value = limitTime;
			
//读密码时间结束
if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
{
	submitFlag = 1;
	
	writeDetailLog("<%=chargelog_detailflag %>", "确认超时");
		
		//发生异常，增加日志
		document.getElementById("errorType").value = "add";
		
		setException("核对信息超时，请取走您的银联卡。");
	}
}

// 启动计时器
function startclock() 
{
	try 
	{
		timeToken = setInterval("waitForSubmit()", 1000);
	}
	catch (e) 
	{
		//发生异常，增加日志
		document.getElementById("errorType").value = "add";
		
		setException("核对信息失败，请取走您的银联卡。");
	}
}

// 确认缴费
function commitBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		writeDetailLog("<%=chargelog_detailflag %>", "确认交费");
			
		//清除定时任务
		clearInterval(timeToken);
				
		openChargeWaitLoading();
	
		setTimeout("fPosPay()", 500);
	}
}

<%--
* 用户可主动退出交费交易。 
*  银联卡交费，确认金额界面，用户可主动退出。
--%>
function cancelBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		writeDetailLog("<%=chargelog_detailflag %>", "退出交费");
			
		//用户主动退出
		document.getElementById("errorType").value = "add";
		document.getElementById("quitFlag").value = "1";
		
		// 关闭银联的密码键盘
		var ret = closePin();
		writeDetailLog("<%=chargelog_detailflag %>", "关闭银联的密码键盘:" + ret);
		
		// 打开厂商的密码键盘
		ret = OpenCom();
		writeDetailLog("<%=chargelog_detailflag %>", "打开厂商的密码键盘:" + ret);
		
		// 设置密码键盘为普通键盘模式
		ret = SetWorkMode(0);
		writeDetailLog("<%=chargelog_detailflag %>", "设置明文模式:" + ret);
		
		// 关闭卡机
		ret = CloseReadingCardFixing();
		writeDetailLog("<%=chargelog_detailflag %>", "关闭卡机:" + ret);
		
		// 读卡器打开及初始化
		ret = InitReadUserCard();
		writeDetailLog("<%=chargelog_detailflag %>", "打开读卡器:" + ret);
	
		setException("您已取消本次交费，感谢您的使用，请取走您的银联卡。");
	}			
}

function goBack(menu)
{
	cancelBusi();
}
</script>
</head>
<body scroll="no">
<form name="actForm" method="post">		
	<!-- 身份证信息 -->
	<!-- 姓名 -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- 性别 -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
	<!-- 身份证号码 -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- 证件地址 -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
	<!-- 开始时间 -->
	<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
	<!-- 结束时间 -->
	<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
	<!-- 卡信息 -->
	<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
	<!-- 照片信息 -->
	<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
	
	<!-- 套餐信息 -->
	<!-- 模板ID -->
	<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
	<!-- 模板名称 -->
	<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
	<!-- 产品ID -->
	<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
	<!-- 产品名称 -->
	<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
	<!-- 品牌 -->
	<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
	<!-- 套餐月费 -->
	<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
	<!-- 预存费用 -->
	<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
	
	<!-- 选号信息 -->
	<!-- 地市 -->
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
	<!-- 组织机构ID -->
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<!-- 地市名称 -->
	<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
	<!-- 选号规则 -->
	<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
	<!-- 前缀 -->
	<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
	<!-- 后缀 -->
	<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
	<!--空白卡序列号 -->
	<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
	<!--手机号码 -->
	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="iccid" value="<s:property value='iccid'/>" />
	<!--短消息中心号码 -->
	<input type="hidden" id="smsp" name="smsp" value="<s:property value='smsp'/>" />
	<!-- 产品ID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	<!-- 服务密码 -->
	<input type="hidden" id="pwd" name="pwd" value="<s:property value='pwd'/>"/>
	
	<!-- 缴费信息 -->
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	<!-- 投币器流水号 -->
	<input type="hidden" id="terminalSeq" name="terminalSeq" value="<s:property value='terminalSeq'/>"/>
	<!-- 实际缴费金额 -->
	<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
	
	<%-- 受理编号 --%>
	<input type="hidden" id="dealNum" name="dealNum" value='' />
	
	<%-- 开户日志 --%>
	<%-- 流水号 --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- 缴费流水号 --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- 缴费方式，1：银联卡；4：现金 --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
	<%-- 实收费用 --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>
    <%-- 默认2：初始状态 0：写卡成功 1：写卡失败 --%> 
    <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
    <%-- 默认2：初始状态 0：缴费成功 1：缴费失败 --%> 
    <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
    <%-- 默认2：初始状态 0：开户成功 1：开户失败 --%>
    <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
    <%-- 是否打印小票  1-不打印，0-打印 --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
	<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'/>
	<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'/>
	<input type="hidden" id="terminalSeq" name="terminalSeq" value=''/>
	<input type="hidden" id="errorType" name="errorType" value=''/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="quitFlag" name="quitFlag" value=""/>
	
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2>在线开户</h2>
				<div class="blank10"></div>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a> 
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)">5. 设置服务密码</a> 
				<a href="javascript:void(0)">6. 费用确认</a>
				<a href="javascript:void(0)" class="on">7. 开户缴费</a>
				<a href="javascript:void(0)">8. 取卡打印发票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
  					<div class="blank60"></div>
  					<div class="p40">
  						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
  						<p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="recFee" /></span> 元</p>
					<p class="tit_info_noheight"><span>核对信息时长共</span><span class="yellow">60</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />秒。</p>
					<p class="tit_info">确认交费，请按交费键。如要取消本次交费，请按退出键。</p>
					<div class="blank25"></div>
					<div class="line"></div>
  					<div class="blank60"></div>
  						
  					<div class="recharge_result tc">
  						<div class="btn_box2 clearfix">
  							<a href="javascript:void(0);" onclick="commitBusi();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">交费(按确认键)</a>
  						</div>
  					</div>				
  				</div>
			</div>
		</div>
	</div>
	
	<div class="footer" id="footer">
		<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'"></a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';"></a>
		<a id="quitBtn" href="javascript:void(0);" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'" onclick="cancelBusi();return false;"></a>
	</div>
</form>
</body>
<script type="text/javascript">
clearTimeout(timeOut);
startclock();

// 标识控件使用
closeStatus = 1;
</script>
</html>
