<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME);

// 是否启动银联密码框 (0：银联密码框   1：华为密码框)
int pwdBz = PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ") == null ? 0 : Integer.parseInt((String) PublicCache.getInstance().getCachedData("SH_PAY_PWD_BZ"));

// 交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargelog_detailflag = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>读卡页面</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script type="text/javascript">
document.onkeydown = pwdKeyboardDown;
var pwdBz = <%=pwdBz %>;
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
}

// 读卡等待时间
var limitTime = <%=limitTime %>;

//等待读卡的句柄
var waitingToken;

// 提交标志
var submitFlag = 0;

// 等待读取标志，1，成功；0，失败。如果为1时，用户主动取消缴费操作，需调用取消刷卡接口
var readingCard = 0;

//出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		writeDetailLog("<%=chargelog_detailflag %>", "银联卡交费异常：" + errorMsg);
		
		//清除定时任务
		clearInterval(waitingToken);
		
		//显示错误信息
		document.getElementById("errormessage").value = errorMsg;
		
		//等待读卡线程启动成功，出现异常后，需关闭			
		if (readingCard == 1)
		{
			writeDetailLog("<%=chargelog_detailflag %>", "取消刷卡");
				
			CancelReadingUserCard();
		}
		
		//异常处理，只要出现了异常就要记录日志  
		document.readCardForm.target = "_self";
		document.readCardForm.action = "${sessionScope.basePath }prodInstall/installError.action";
		document.readCardForm.submit();
	}			
}

// 读卡完成后提交转到输入银联卡密码页面
function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 是否启动银联密码框 (0：银联密码框   1：华为密码框)
		if (pwdBz == 0)
		{
			writeDetailLog("<%=chargelog_detailflag %>", "转至缴费确认页面");
		}
		else
		{
			writeDetailLog("<%=chargelog_detailflag %>", "转至输入银联卡密码页面");
		}
	
		//清除定时任务
		clearInterval(waitingToken);
		
		//执行提交
		document.readCardForm.target = "_self";
		
		// 是否启动银联密码框 (0：银联密码框   1：华为密码框)
		if (pwdBz == 0)
		{
			document.readCardForm.action = "${sessionScope.basePath }prodInstall/toMakeSure.action";
		}
		else
		{
			document.readCardForm.action = "${sessionScope.basePath }prodInstall/inputPwd.action";
		}
		document.readCardForm.submit();	
	}			
}
// modify end cKF76106 2013/04/07 R003C13L03n01 银联密码框美化

// 获得读卡标志
function getReadCardStatus() 
{
	writeDetailLog("<%=chargelog_detailflag %>", "读卡中。。。");

	//读卡时间结束
	if (limitTime <= 0 && submitFlag == 0)
	{           	
     			writeDetailLog("<%=chargelog_detailflag %>", "读卡超时");
     			
     			setException("读卡超时，无法进行交费操作");
     			return;
	}

	try 
	{
		// 打开仓门接收用户银行卡 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1） 
		// 0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
		var ret = ReadUserCardFinished();

		// 尚未读取到卡信息
		if (ret == "1" || ret == 1)
		{
			// 读卡时长一共limitTime秒
			limitTime = limitTime - 1;
	
			// 设定界面显示
			document.getElementById("tTime").value = limitTime;
		}
		// 读卡成功
		else if (ret == "0" || ret == 0)
		{
			writeDetailLog("<%=chargelog_detailflag %>", "读卡成功");
			
   	 		// 读取完毕
			readingCard = 0;
	
			// 需要退卡
			document.getElementById("needReturnCard").value = "1";
			
			doSub();									
		} 
		// 读卡失败
		else if (ret == "-1")
		{
			writeDetailLog("<%=chargelog_detailflag %>", "读卡失败");
			
			readingCard = 0;//读取失败

			// 需要退卡
			document.getElementById("needReturnCard").value = "1";	
			
			setException("读卡器读卡失败，无法使用银联卡进行充值，请选用其它方式。");
		}				
	}
	catch (e) 
	{
		writeDetailLog("<%=chargelog_detailflag %>", "读卡异常");
		
		readingCard = 0;//读取出现异常
		
		setException("读卡器读卡失败，无法使用银联卡进行充值，请选用其它方式。");
	}
}

//设置时间计算周期
function startclock() 
{	
	try 
	{
		waitingToken = setInterval("getReadCardStatus()", 1000);				
	}
	catch (e) 
	{
		setException("读卡器读卡失败，无法使用银联卡进行充值，请选用其它方式。");
	}
}

//页面加载时执行
function bodyLoad() 
{
	try 
	{
		// 初始化	
           	var ret = InitReadUserCard();
		
		//生产用
		ret = ReadingUserCard();// 准备刷卡
		
		if (ret != "0" && ret != 0) 
		{
			writeDetailLog("<%=chargelog_detailflag %>", "准备刷卡失败");
			
			setException("读卡设备控件启动等待读卡线程失败，无法使用银联卡进行充值，请选用其它方式。");
                  return;
		}
		else
		{
			writeDetailLog("<%=chargelog_detailflag %>", "准备刷卡成功");
			
			//等待读卡
			readingCard = 1;
			
			// 顶部菜单不可用。
  				closeStatus = 1;
              	
              	//初始化成功，就需要关闭识币器。而“首页”、“上一页”、“退出”按钮无法执行此操作，所以禁用三个按钮
              	document.getElementById("homeBtn").disabled = true;
            document.getElementById("backBtn").disabled = true;
              	document.getElementById("quitBtn").disabled = true;
					
			startclock();
		}
	}
	catch (ex) 
	{
		setException("读卡设备控件启动等待读卡线程失败，无法使用银联卡进行充值，请选用其它方式。");
              return;
	}
}		
</script>
</head>
<body scroll="no">
<form name="readCardForm" method="post" target="_self">
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
	<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'/>
	<input type="hidden" id="cardnumber" name="cardnumber" value=''/>
	<input type="hidden" id="errorType" name="errorType" value=''/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
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
    					<div class="p40 pr0">
      					<p class="tit_info"><span class="bg"></span>请插入您的银联卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
      					<p class="tit_info"><span>读卡时长共</span><span class="yellow">30</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />秒</p>
      					<div class="blank10"></div>   
     						<div class="blank20"></div>
      					<div class="blank10"></div>
     						<div class="gif_animation">
     							<img src="${sessionScope.basePath }images/gif1.gif" alt="请插卡" />
     						</div>        
    					</div>
  				</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
</body>
<script type="text/javascript">
	clearTimeout(timeOut);
    bodyLoad();        
</script>
</html>