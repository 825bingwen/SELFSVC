<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// 打印信息
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String orgName = termInfo.getOrgname();
	
	String groupName = (String)request.getAttribute("groupName");
	String dangciName = (String)request.getAttribute("dangciName");
	String termName = (String)request.getAttribute("termName");
	
	// 1:银联卡 4:现金
	String payType = (String)request.getAttribute("payType");
	
	// add begin hWX5316476 2013-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
	// 是否启用增值税开关（1：开启  0：关闭  默认关闭)
	String dealAddedTax = (String) PublicCache.getInstance().getCachedData("SH_DEALADDEDTAX");
	// add end hWX5316476 2013-3-24 OR_huawei_201312_668 [营改增 营业 二阶段][湖北] 缴费接口_票据处理_外围渠道改造
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript">
var submitFlag = 0;
document.onkeydown = pwdKeyboardDown;

// 交易方式
var payType = "<s:property value='payType' />";

// 是否需要退卡
var needReturnCard = "<s:property value='needReturnCard' />";

// 取卡时间30秒
var limitTime = 30;
		
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
	
	//返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
	}			
}
// 返回
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

// 读取读卡器取卡状态
function takeOutBankCardStatus() 
{
	limitTime = limitTime - 1;
	
	if (limitTime <= 0)
	{
		//超时，清除定时任务，同时吞卡
		clearInterval(waitingToken);
		
		captureUserCard();
		
		return;
	}
	
	try 
	{
		//生产用
		var ret = TakeOutUserCardStatus();//获取读卡器取卡状态
		
		if (ret == "0" || ret == 0) 
		{	
			//已经取走卡，清除定时任务
			clearInterval(waitingToken);									
		}			
	}
	catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

function startClock()
{
	try 
	{
		waitingToken = setInterval("takeOutBankCardStatus()", 1000);
	}
	catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

function doFinish()
{
	if (payType == "<%=Constants.PAYBYUNIONCARD %>" && needReturnCard == "1")
	{
		// 退卡
		var ret = TakeOutUserCard();

		// 吐卡成功，并且支持吞卡，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
		if (ret == "0" && "1" == "<s:property value='needReturnCard' />")
		{
			startClock();
		}
		else if (ret != "0")
		{
			//吐卡异常
		}
	}
				
	var p_activityId = "<s:property value='activityId' />";// 活动编码
	var p_dangciId = "<s:property value='dangciId' />";// 档次编码
	var p_servnumber = "<s:property value='servnumber' />";// 手机号码
	var p_orgName = "<%=orgName %>";  //打印地点
	var p_date = getDateTime();  //打印日期
	var p_termId = "<s:property value='termid' />"; //终端编码
	var p_termName = "<%=termName %>"; //终端名称
	var p_prepayFee = "<s:property value='prepayFee' />"; //受理金额
	var p_totalfee = "<s:property value='totalfee_yuan' />";// 投币金额
	var p_terminalSeq = "<s:property value='terminalSeq' />";//终端流水
	var p_recoid = "<s:property value='recoid' />";// 交易流水
	var p_successBz = "<s:property value='successBz' />";//状态
	var yxfaFee_yuan = "<s:property value='yxfaFee_yuan' />";// 营销方案费用
	var ycFee_yuan = "<s:property value='ycFee_yuan' />";// 用户预存
	var p_dangciName = "<%=dangciName %>";// 档次名称
	var p_groupName = "<%=groupName %>";// 活动组名称
	var p_status = ""; //交易状态
	if ("0" == p_successBz)
	{
		p_status = "促销活动受理成功！";
		document.getElementById('msg').innerHTML = p_status;
	}
	else
	{
		p_status = "活动受理失败,请凭交易凭条到营业厅办理退款！";
		document.getElementById('msg').innerHTML = p_status;
	}
	
	//modify begin g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
	var brandName = "${sessionScope.CustomerSimpInfo.brandName}";
	
	// modify begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
	// 打印缴费小票
	doPrintByActivity(p_activityId,p_dangciId,p_servnumber,p_orgName,p_date,p_termId,p_termName,p_prepayFee,p_totalfee,p_terminalSeq,p_recoid,p_status,yxfaFee_yuan,ycFee_yuan,p_dangciName,p_groupName, "<s:property value='tip' />", brandName);
	// modify end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089
	//modify end g00140516 2013/02/26 R003C13L02n01 OR_HUB_201301_345
	
	// 打印银联小票
	var printcontext = '<%=request.getAttribute("printcontext")==null ? "":(String)request.getAttribute("printcontext") %>';
	if (printcontext != "")
	{
		doPrintUnionBill_hb2(printcontext,p_terminalSeq,p_orgName,p_date);
	}
	
}

// 转到打印发票页面
function printInvoice()
{
	// 提交
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath }activitiesRec/goPrintInvoice.action";
	document.forms[0].submit();
}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="payMoneyForm" method="post">
		
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		<%-- modify by sWX219697 2015-7-20 totalfee改为totalFee,findbugs修改--%>
		<!-- 用户投入的费用金额 -->
		<input type="hidden" id="totalFee" name="totalFee" value='<s:property value="#request.totalFee" />'/>
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
		<!-- 实缴金额 -->
		<input type="hidden" id="totalfee_yuan" name="totalfee_yuan" value='<s:property value="#request.totalfee_yuan" />'/>
		<!-- 受理流水 -->
		<input type="hidden" id="recoid" name="recoid" value='<s:property value="#request.recoid" />'/>
		<!-- 缴费类型 -->
		<input type="hidden" id="payType" name="payType" value='<s:property value="#request.payType" />'>
		
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<%
      					if ("4".equals(payType))
      					{
      					%>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#" class="on">5. 完成</a>
          				<%
          				}
          				else
          				{
          				%>
						<a title="选择活动档次" href="#">1. 选择活动档次</a>
    					<a title="受理协议" href="#">2. 受理协议</a> 
    					<a title="选择支付方式" href="#">3. 选择支付方式</a>
    					<a title="插入储蓄卡" href="#">4. 插入储蓄卡</a>
    					<a title="输入密码" href="#">5. 输入密码</a>
    					<a title="核对信息" href="#">6. 核对信息</a>
    					<a title="完成" href="#" class="on">7. 完成</a>
          				<%
          				}
          				%>
  					</div>
  					   
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">实缴金额：<span class="yellow"><s:property value="#request.totalfee_yuan" />元</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30" id="msg">您选择的促销活动已受理成功！</p>
          						<p class="desc pt20">请保存好您的的交易凭条。</p>
          						<s:if test='hasMultiInvoices == "true"'>
          							<div class="btn_box3 clearfix">
          						</s:if>
          						<s:else>
          							<div class="btn_box2 clearfix">
          						</s:else>
          						<%
          						String successBz = (String)request.getAttribute("successBz");
          						if ("0".equals(successBz))
          						{
          							if ("0".equals(dealAddedTax))
									{
          						%>
          							<a href="javascript:void(0);" onclick="printInvoice();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">打印当前发票</a>
          						<%
          							}
          						}
          						%>
          						
          						<s:if test='recommendActivityFlag == "1"'>
          							<a href="javascript:void(0);" onclick="continueHandle();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">继续办理</a>
          						</s:if>
          						   						        							
          						</div>
       						</div>
       						
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
		<script type="text/javascript">
// 暂不办理，继续原有业务
function continueHandle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		document.forms[0].target="_self";
		document.forms[0].action = "${sessionScope.basePath}recommendActivity/continueHandle.action";
		document.forms[0].submit();
	}
}
		</script>
	</body>
</html>
