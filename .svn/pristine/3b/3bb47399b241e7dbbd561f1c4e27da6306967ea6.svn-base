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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>		
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
//var unitType = "1";

// 交易类型 'A':消费交易 'B':重打印 'C':查余额
//var businesstype = "A";// 

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
		commitBusi();// 缴费取消，请取走您的银联卡
	}
	// 银联卡交费，确认金额界面，用户可主动退出
	//退出：82
	else if (key == 82 || key == 220)
	{
		cancelBusi();
	}
}

//出现异常
function setException(errorMsg) 
{
	//清除定时任务
	clearInterval(timeToken);

	// 显示错误信息
	document.getElementById("errormessage").value = errorMsg;
	
	//异常处理，只要出现了异常就要记录日志
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/installError.action";
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


// 确认缴费
function commitBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		//清除定时任务
		clearInterval(timeToken);
				
		openRecWaitLoading();
	
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
		
		openRecWaitLoading();
		setException("您已取消本次缴费，感谢您的使用，请取走您的银联卡。");
	}			
}

function goBack(menu)
{
	cancelBusi();
}

//计算剩余时长
function waitForSubmit() 
{
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
		setException("核对信息失败，请取走您的银联卡。");
	}
}

function doLoad()
{
	//禁用”首页“、“退出”按钮
	document.getElementById("homeBtn").disabled = true;
	document.getElementById("quitBtn").disabled = true;
}
</script>
</head>
<body scroll="no">
<form name="actForm" method="post">		
	<!-- 空白卡信息 -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!--短消息中心号码 -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />
	<input type="hidden" id="pin1" name="simInfoPO.pin1" value='<s:property value="simInfoPO.pin1" />' />
	<input type="hidden" id="pin2" name="simInfoPO.pin2" value='<s:property value="simInfoPO.pin2" />' />
	<input type="hidden" id="puk1" name="simInfoPO.puk1" value='<s:property value="simInfoPO.puk1" />' />
	<input type="hidden" id="puk2" name="simInfoPO.puk2" value='<s:property value="simInfoPO.puk2" />' />
	<input type="hidden" id="eki" name="simInfoPO.eki" value='<s:property value="simInfoPO.eki" />' />
	<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
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
	<!-- 产品ID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	
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
	
	<%-- 是否将卡移动到回收箱 1：回收 0：不回收 --%>
	<input type="hidden" id="callBackCard" name="callBackCard" value="0"/>
	
	<%-- 银联打印信息 --%>
	<input type="hidden" id="printcontext" name="printcontext" value=""/>
	<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
	<input type="hidden" id="needReturnCard" name="needReturnCard" value="<s:property value='needReturnCard' />"/>
	<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="quitFlag" name="quitFlag" value=""/>
	<input type="hidden" id="posResCode" name="posResCode" value=''/>
	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2>在线开户</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a> 
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)">5. 费用确认</a>
				<a href="javascript:void(0)" class="on">6. 开户缴费</a>
				<a href="javascript:void(0)">7. 取卡打印小票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
  					<div class="blank60"></div>
  					<div class="p40">
  						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
  						<p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="recFee" /></span> 元</p>
					<p class="tit_info"><span>核对信息时长共</span><span class="yellow">60</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />秒。</p>
					<div class="blank25"></div>
					<div class="line"></div>
  					<div class="blank60"></div>
  						
  					<div class="recharge_result tc">
  						<div class="btn_box2 clearfix">
  							<a href="javascript:void(0);" onclick="commitBusi();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">确认</a>
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


//银联卡扣款
function fPosPay()
{
	try
	{
		//生产用。一天之内跟踪号不可以相同，一次签到之内的批次号是相同的
		var result = GetPosBatchNum();
		
		var dataArray = result.split("$");
		
		//获取跟踪号、批次号失败
		if (dataArray[0] != "0")
		{
			setException("获取跟踪号和批次号失败，无法使用银联卡进行充值。请取走您的银联卡。");
			
			return;
		}
		
		//获取跟踪号、批次号成功
		print_posNum = dataArray[1];
		print_batchNum = dataArray[2];
		
		document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
		
		//扣款之前记录日志
		var url = "${sessionScope.basePath }cardInstall/addChargeLog.action";

		var params = "telnum=" + <s:property value='telnum' /> ;
		params = params + "&payType=1";
		params = params + "&tMoney=" + "<s:property value='recFee' />";
		params = params	+ "&status=00";
		params = params + "&description=" + encodeURI(encodeURI('扣款之前记录日志'));
		params = params + "&acceptType=" + document.getElementById("acceptType").value;
		params = params	+ "&region=" + document.getElementById("region").value;
		params = params + "&cardnumber=" + document.getElementById("cardnumber").value + "&terminalSeq=" + document.getElementById("terminalSeq").value;
		params = params	+  "&posnum=" + print_posNum + "&batchnumbeforekoukuan=" + print_batchNum;
		
		var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
				var dataArray = resXml.split("~~");
				
				//记录日志成功
				if (dataArray[0] == "1") 
				{
					//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录
					var oid = dataArray[1].replace(/[\r\n]/g, "");
					document.getElementById("chargeId").value = oid;
					
					var posResCode = '';
					
					//提交扣款请求
					try
					{
						var ret ;
						
						// 关闭密码键盘
						ret = CloseCom();
						if (ret != "0" || ret != 0)
						{
							setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
							return;
						}
						
						// 银联扣款
						// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 缴费金额(分)
						var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',"<%=fenMoney %>");

						// 打开键盘串口、设置明文模式
						ret = OpenCom();
						if (ret != "0" || ret != 0)
						{
							window.parent.showFrmErr("警告:打开键盘串口失败！");
						}
						ret = SetWorkMode(0);
						if (ret != "0" || ret != 0)
						{
							window.parent.showFrmErr("警告:设置键盘模式失败！");
						}
						
						// 扣款失败转到异常界面
						posResCode = payReturnStr.substring(0,2);
						document.getElementById('posResCode').value = posResCode;
						if (payReturnStr.substring(0,2) != "00")
						{
							setException("银联卡扣款失败，请取走您的银联卡。原因："+payReturnStr);
							return;
						}
						
						// 扣款正常取值
						var resultstr = payReturnStr.substring(0,34);
						var printcontext = payReturnStr.substring(34,payReturnStr.length);
						
						document.getElementById('printcontext').value = printcontext;
						
						// 扣款成功 定长34 成功00
						if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
						{
						    // 打开键盘串口、设置明文模式
							OpenCom();
							SetWorkMode(0);

							var printcontexts = printcontext.split('~');							
							document.getElementById("tMoney").value = printcontexts[9];

							var params1 = "chargeId=" + document.getElementById("chargeId").value + "&unionpayuser=" + printcontexts[0];// id和商户号
							params1 = params1 + "&unionpaycode=" + printcontexts[1] + "&busitype=" + encodeURI(encodeURI(printcontexts[2]));// 终端号
							params1 = params1 + "&batchnum=" + printcontexts[4] + "&authorizationcode=" + printcontexts[5];// 批次号和授权码
							params1 = params1 + "&businessreferencenum=" + printcontexts[6] + "&unionpaytime=" + printcontexts[7];// 交易参考号和交易时间
							params1 = params1 + "&vouchernum=" + printcontexts[8] + "&unionpayfee=" + printcontexts[9];// 凭证号和扣款金额
							params1 = params1 + "&status=01";
							params1 = params1 + "&description=" + encodeURI(encodeURI('开户时扣款成功'));
							params1 = params1 + "&posResCode=" + document.getElementById('posResCode').value;// unionretcode银联返回字段
							params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;// 终端流水
				
							//更新日志
							var url1 = "${sessionScope.basePath }cardInstall/updateCardChargeLog.action";
							
							var loader1 = new net.ContentLoader(url1, netload = function () {
								var resXml1 = this.req.responseText;
								
								//更新日志成功
								if (resXml1 == "1" || resXml1 == 1)
								{
									//交费
									//payToBoss();	
									// 写卡开户
									goSuccess();								
								}
								//更新日志失败
								else
								{	
									setException("开户失败，请取走您的银联卡和小票，凭小票到到营业厅办理退款，谢谢使用!");
									return;
								}								
							}, null, "POST", params1, null);
						}
						//扣款失败
						else
						{
							setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
							return;
						}
							
					}
					catch (e)
					{
						
						if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
						{
							setException("银联卡扣款成功，但是开户失败。请取走您的银联卡。");
						}
						else
						{
							setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
						}
					}							
				}
				//记录日志失败
				else 
				{				
					setException("日志记录失败，不能进行银联卡缴费操作。请取走您的银联卡。");
				}					
		}, null, "POST", params, null);	
	}
	catch (e)
	{
		if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
		{
			setException("银联卡扣款成功，但是开户失败。请取走您的银联卡。");
		}
		else
		{
			setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
		}
	}				
}

// 与BOSS计帐(银联卡)
function goSuccess() 
{
	// 应当校验一下是否为0
	if (document.getElementById("tMoney").value == "" 
			|| parseInt(document.getElementById("tMoney").value) <= 0)
	{
		return;
	}
	
	// 判断投币金额，不能少于应缴金额
	var recFee = document.getElementById("recFee").value;
	var tMoney = document.getElementById("tMoney").value;
	
	// 设置实际缴费
	document.getElementById("toFee").value = recFee;

	// 缴费状态  0 成功
	document.getElementById("payStatus").value = "0";
	
	// 空白卡号
	var blankno = '<s:property value="blankno"/>';
	
	var cardInfoStr = document.getElementById("cardInfoStr").value;
	var blankno = document.getElementById("blankno").value;
		
	// 提交缴费请求前先写卡
	var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
	
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
		// 写卡失败 1
		document.getElementById("writeCardStatus").value = "1";
	
		writeCardException(writeData.split("~")[1]);
		return;
	}
	
	// 写卡成功
	document.getElementById("writeCardStatus").value = "0";
	
	// 开户状态 默认数据2 0 成功  1 失败
	document.getElementById("installStatus").value = "2";
		
	//提交缴费请求
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
	document.actForm.submit();			
}

// 再次写卡流程
function againWriteCardProcess()
{
	/**
	* 写卡异常处理流程：
	*1.	设用厂商回收卡接口把费卡走到回收箱  
	*2.	走新卡到读卡位
	*3.	调用卡商的读空卡序列号磁出序列号
	*4.	调用BOSS接口校验卡资源是否可用
	*5.	转到费用明细确认页面进行写卡，直接写卡次数达到。
	*/
	// 1.设用厂商回收卡接口把费卡走到回收箱 在写卡失败的时候已经移动到回收箱了
	// 2.发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
	var message = checkReadCardStatus();
	if (message != "")
	{
		writeCardException(message);
		return;
	}
	
	// 读取空白卡序列号
	var blankCardSN = readBlankCardSN();
	
	if (blankCardSN.indexOf("1~") != -1)
	{
		writeCardException(blankCardSN.split('~')[1]);
		return;
	}
	
	if(blankCardSN.length != 20)
	{
	    writeCardException("对不起，卡类型不正确，请联系营业厅管理员!");
		return;
	}
			
	// 获取空白卡序列号
	document.getElementById('blankno').value = blankCardSN;
        
	// 6.校验卡资源是否可用
	ret = chkBlankNo();
	if (ret != 0)
	{
		writeCardException("对不起，卡资源不可用！");
		return;
	}
	// 7. 空白卡资源暂选
	ret = blankCardTmpPick();
	var resArray = ret.split('~~');
	if (resArray[0] != 0)
	{
		writeCardException("空白卡资源暂选失败！");
		return;
	}
	else
	{
		setValue("iccid",resArray[1]);
		setValue("imsi",resArray[2]);
		setValue("smsp",resArray[4]);
		setValue("cardInfoStr",ret.substring(3).replace("+", "^^"));
	}
	
	var cardInfoStr = document.getElementById("cardInfoStr").value;
	var blankno = document.getElementById("blankno").value;
	
	// 提交缴费请求前先写卡
	var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
	
	// 二次写卡失败 跳转异常页面
	if(writeData.indexOf("1~") != -1)
	{
		// 写卡失败 1
		document.getElementById("writeCardStatus").value = "1";
		writeCardException(writeData.split("~")[1]);
		return;
	}
	
	// 写卡成功
	document.getElementById("writeCardStatus").value = "0";
	
	// 开户状态 默认数据2 0 成功  1 失败
	document.getElementById("installStatus").value = "2";
	
	
	//提交缴费请求
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
	document.actform.submit();
}

// 校验空白卡资源是否可用
function chkBlankNo()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/chkBlankNo.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "orgid=" + document.getElementById("orgid").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}
	
// 空白卡资源暂选
function blankCardTmpPick()
{
	// 返回 1 0~~iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/blankCardTmpPick.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "telnum=" + document.getElementById("telnum").value;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

// 出现写卡异常
function writeCardException(errorMsg)
{
	document.getElementById("errormessage").value = errorMsg;
	
	//清除定时任务
	clearInterval(timeToken);
		
	// 写卡异常记录异常日志（增加缴费日志与更新开户日志）
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/makeErrLog.action";
	document.actForm.submit();
}
</script>
</html>
