<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
    // 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
    
    // 投币超时时间
    String timeout = (String)PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);
    
    // 终端信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // 现金识币器特性
    String isCashEquip = termInfo.getTermspecial().substring(3, 4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript">
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
	
	//确认
	if (key == 13 || key == 89 || key == 221) 
	{
		<%--modify begin g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
		doSub();
		<%--modify end g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
		return;
	}
	//返回
	else if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}		
}
	
//投币的时长，单位秒		
var payMoneyTime = "<%=timeout%>";

//剩余时间
var leftTime = payMoneyTime;

//readCash定时器句柄
var readCashToken;

//关闭识币器。0：不需要；1：需要
var needClose = 0;

//提交标记，0表示未确认提交缴费，1表示已确认提交缴费
var submitFlag = 0;

function goback(menuid)
{
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='servnumber' />退出充值交费功能");
	
	//已投币
	if (document.getElementById("tMoney").value != "" 
			&& parseInt(document.getElementById("tMoney").value) > 0)
	{
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"返回充值交费页面");
		
		//返回时，清除定时任务。同时关闭现金识币器			
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		clearInterval(readCashToken);
		
		document.getElementById("curMenuId").value = menuid;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }charge/qryfeeChargeAccount.action";
		document.actform.submit();
	}
}
	
// 提交
function doSub()
{
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='servnumber' />确认交费");
	
	//未投币
	if (document.getElementById("tMoney").value == "" 
			|| parseInt(document.getElementById("tMoney").value) <= 0)
	{
		return;
	}
	
	//尚未提交请求
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"提交交费请求，号码：" + document.getElementById("servnumber").value + 
				"；金额：" + document.getElementById("tMoney").value + 
				"；流水：" + document.getElementById("terminalSeq").value);
		
		<%--modify begin g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
		wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口例子
		<%--modify end g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
	
		//关闭现金识币器
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		//清除定时任务
        clearInterval(readCashToken);

		//提交缴费请求
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }charge/cashChargeCommit.action";
		document.actform.submit();
	}
}
	
//出现异常
function setException(errorMsg) 
{			
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='servnumber' />交费过程中出现异常：" + errorMsg);
	
	document.getElementById("errormessage").value = errorMsg;
	
	// 出现异常后，清除定时任务。同时关闭现金识币器			
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 异常处理，只要出现了异常就要记录日志  
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }charge/goCashError.action";
	document.actform.submit();
}		

// 获取手机号码
// 初始化现金识币器
// 获取投币流水
// 使首页和退出按钮不可用
// 启动循环获取投币金额
function loadContent() 
{
	var serverNumber = "<s:property value='servnumber' />";
	if (serverNumber == null || serverNumber == "")
    {            
		setException("对不起，用户信息获取失败，请返回重新操作。");
		return;
	}
          
	<%if (!"1".equals(isCashEquip)){%>
		  setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
		  return;
	<%}%>
    
    try 
    {
    	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"初始化现金识币器：" + serverNumber);
    	// 初始化现金识币器(正常返回 0,20110509143345)
		var initData = initCashEquip_sd(serverNumber);
	   	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"结果：" + initData);
	   	
        if (initData.substring(0, 1) != "0") 
        {
        	setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
            return;                    
        }
        else
        {
        	//现金识币器初始化成功。不再接收用户投币时，需要关闭。
        	needClose = 1;
        	
        	//获取投币流水
        	document.getElementById("terminalSeq").value = initData.substring(2);        	
        	
			// 标识控件使用
	   		closeStatus = 1;
        	
        	//初始化成功，就需要关闭识币器。而首页、退出按钮无法执行此操作，所以禁用”首页“、“退出”按钮
        	document.getElementById("homeBtn").disabled = true;
        	document.getElementById("quitBtn").disabled = true;
        	
        	startclock();
        }               
    } 
    catch(e) 
    {
        setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
        return;
    }           
}

//循环获取投币金额
function startclock() 
{
	var msg = preparecash();
	
	//识币器状态检测失败，不允许投币，显示异常信息
	if (msg != "") 
	{
		setException(msg);
		return;
	}		
	
	try
	{
		// 获取投币金额,循环调用,每隔1秒执行一次
		readCashToken = setInterval("doCash()", 1000);
	}
	catch (e) 
	{
		setException("对不起，计时器发生异常，无法使用现金进行充值，请使用其它方式或者到营业厅进行充值。");
	}
}

//检测识币器状态
function preparecash() 
{
	var msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式值。";

	try 
	{
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"检测识币器状态");	
		//检测识币器状态 0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备
		var cashStatus = checkCashStatus();

		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"结果：" + cashStatus);	
		if (cashStatus == 0)
		{
			msg = "";
		}
		else if (cashStatus == 1) 
		{
			msg = "对不起，现金识币器状态异常，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 2) 
		{
			msg = "对不起，钱箱已满，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 3) 
		{
			msg = "对不起，钱箱未正常关闭，无法使用现金进行充值，请选用其它方式。";
		} 
		else if (cashStatus == 4) 
		{
			msg = "对不起，钱箱入钞口被夹，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 5) 
		{
			msg = "对不起，钱箱被夹，无法使用现金进行充值，请选用其它方式。";
		} 
		else if (cashStatus == 6) 
		{
			msg = "对不起，现金识币器发生未知错误，无法使用现金进行充值，请选用其它方式。";
		}
		else if (cashStatus == 9) 
		{
			msg = "对不起，现金识币器不存在，无法使用现金进行充值，请选用其它方式。";
		}			
	}
	catch (e) 
	{
		msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式。";
	}
	
	// 返回
	return msg;
}

// 获取投币金额
// 循环调用，每隔1秒执行一次
function doCash() 
{

	if (leftTime <= 0)
	{
		return;
	}
	
	try 
	{	
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"获取投币金额");	
		// 获取投币金额 0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
		var ret = getOnceCash();
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"结果：" + ret);
		
		if (ret > 0) 
		{
			// 时间重新开始
			leftTime = "<%=timeout%>";
			
			// 显示剩余时间
			document.getElementById("tTime").value = leftTime;
			
			// 投币后，不能返回
            document.getElementById("backBtn").disabled = true;
			
			// 计算投币金额 
			document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
			
			// 取消交易按扭不显示
			document.getElementById('cancelBusi').style.display = "none";
			
			// 投币金额大于0时取消交易按扭不显示,缴费按扭显示
			if (parseInt(document.getElementById("tMoney").value) > 0)
			{
				document.getElementById('bCommitBusi').style.display = "none";
				document.getElementById('commitBusi').style.display = "block";
				document.getElementById("promptMsg").innerHTML = "投币结束后，请点击交费按钮";
			}
			else
			{
				document.getElementById('bCommitBusi').style.display = "block";
			}
		}
		else
		{
			// 投币时长一共timeout秒
			leftTime = leftTime - 1;
			
			// 显示剩余时间
			document.getElementById("tTime").value = leftTime;
			
			//投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
			if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
			{           	 	
		        //投币金额大于0
		       	if (parseInt(document.getElementById("tMoney").value) > 0) 
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"交易超时，用户已投币。提交交费请求");
						
					<%--modify begin g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
					// 提交缴费
					doSub();
					<%--modify end g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
				} 
				else 
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"交易超时，用户未投币。流程结束");
					
					// 取消交易
					cancelBusi();
					//setException("投币时间结束，投币金额为0，无法进行缴费操作");
				}
			}
		}				
	}
	catch (e) 
	{
		setException("对不起，获取投币金额失败，无法使用现金进行充值，请选用其它方式。");
	}
}

// 取消交易
function cancelBusi()
{		
	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
			"<s:property value='servnumber' />取消充值交费交易");
	
	if (needClose == 1)
	{
		// 关闭现金识币器
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 返回首页
	setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="payType" name="payType"
				value="<%=Constants.PAY_BYCASH%>">
			<input type="hidden" id="servnumber" name="servnumber"
				value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion"
				value="<s:property value='servRegion' />">
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="acceptType" name="acceptType"
				value="<s:property value='acceptType' />">
			<%--add begin cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371--%>
			<input type="hidden" id="chargeType" name="chargeType"
				value="<s:property value='chargeType' />">
			<%--add end cKF48754 2011/11/10 R003C11L11n01  OR_SD_201111_371--%>
			<input type="hidden" id="custName" name="custName" value="<s:property value='custName' />">			
			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							充值交费流程
						</h2>
						<div class="blank10"></div>
						<a href="#">1. 输入手机号码</a>
						<a href="#">2. 选择支付方式</a>
						<a href="#">3. 选择金额</a>
						<a href="#" class="on">4. 投入纸币</a>
						<p class="recharge_tips">
							支持10、50、100元面额的纸币。
						</p>
						<a href="#">5. 完成</a>
					</div>

					<div class="b712 fm_pay_money">
						<div class="bg bg712"></div>
						<div class="blank30"></div>
						<div class="p40 pr0">
							<div class="blank10"></div>
							<div class="blank20"></div>
							<div class=" pay_money_wrap2">
								<p class="pay_all">
									<span class="pl120">已投入：</span>
									<input type="text" id="tMoney" name="tMoney" value="0"
										readonly="readonly" />
									<span class="yellow">元</span>
								</p>
								<div class="pay_state clearfix">
									<span class="cash_arrow"></span>
									<p class="fl fs22">
										投币状态：

										<span id="promptMsg" class="yellow">请投入纸币...</span>

										<br />
										<span class="pl119">投币时长共</span><span
											class="yellow"><%=timeout%></span>秒，当前剩余
										<input type="text" id="tTime" name="tTime"
											value="<%=timeout%>" readonly="readonly" />
										秒
										<br />
										<span class="pl119">支持</span><span
											class="yellow">5、10、20、50、100元</span>面额的纸币
									</p>
								</div>
							</div>
							<div class="blank30"></div>
							<div>
								<img src="${sessionScope.basePath }images/rmb.gif"
									class="fl pl160" alt='请投币' />
								<div 
									class="btn_box cancle_echo fl pt120 pl30" id="cancelBusi">
									<a href="javascript:void(0);"
										onmousedown="this.className='hover'"
										onmouseup="this.className=''"
										onclick="cancelBusi();return false;">取消本次交易 (请按取消键)</a>
								</div>
								<div 
									class="btn_box charge_unable fl pt120 pl30" id="bCommitBusi">
									<a href="#" onclick="return false;"></a>
								</div>
								<div
									style="display: none"
									class="btn_box buy_enable_echo fl pt120 pl30" id="commitBusi">
									<a href="javascript:void(0);"
										onmousedown="this.className='hover'"
										onmouseup="this.className=''"
										onclick="openWindow_wait('pls_wait');return false;">交费 (请按确认键)</a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--弹出框 正在处理 请稍候-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
					<p class="mt40">
						<img src="${sessionScope.basePath }images/loading.gif" alt='处理中...' />
					</p>
					
					<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					<p class="tips_txt">
						<%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %>
					</p>
					<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					
					<div class="line"></div>
					<div class="popup_banner"></div>
				</div>

				<script type="text/javascript">
			    <%--modify begin g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>
			    var isDone = 0;
				openWindow_wait = function(id){
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"<s:property value='servnumber' />点击交费按钮，isDone=" + isDone);
				
					if (isDone == 0)
					{
						isDone = 1;
						
						<%-- modify begin g00140516 2012/06/02 R003C12L04n01 记录日志，定位昂普重复交费问题 --%>
						document.getElementById('commitBusi').disabled = true;
						<%-- modify end g00140516 2012/06/02 R003C12L04n01 记录日志，定位昂普重复交费问题 --%>
						
						doSub();
					}
				}
				<%--modify end g00140516 2011/12/06 R003C11L12n01 参考湖北修改缴费重复提交问题 --%>				
			    </script>
				<!--弹出窗结束-->
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
	clearTimeout(timeOut);
	loadContent();
</script>
</html>
