<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// 现金缴费投币等待时间(秒)
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);
if (null == timeout || "".equals(timeout.trim()))
{
	timeout = "60";
}

// 识币器关闭次数
String closeCashBillNum = (String) PublicCache.getInstance().getCachedData("SH_CLOSECASHBILL_NUM"); 
if (null == closeCashBillNum || "".equals(closeCashBillNum.trim()))
{
	closeCashBillNum = "5";
}

// 识币器关闭后读取投币金额次数
String readPauseTimeEnd = (String) PublicCache.getInstance().getCachedData("SH_READ_PAUSE_END_TIME"); 
if (null == readPauseTimeEnd || "".equals(readPauseTimeEnd.trim()))
{
	readPauseTimeEnd = "3";
}

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCashEquip = termInfo.getTermspecial().substring(3, 4);

//add begin l00190940 2011-12-22 宁夏识币器停顿时间从缓存中读取
String readPauseTime = (String) PublicCache.getInstance().getCachedData("SH_READ_PAUSE_TIME");
if (null == readPauseTime || "".equals(readPauseTime.trim()))
{
	readPauseTime = "5";
}
int nReadPauseTime = 1000 * Integer.parseInt(readPauseTime);
//add end l00190940 2011-12-22 宁夏识币器停顿时间从缓存中读取
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
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
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
			
			// modify begin g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到
			//89:Y 确认 13:回车 确认
			if (key == 89 || key == 13) 
			{
				if (parseInt(document.getElementById("tMoney").value) > 0)
				{
					doSub();
					return;
				}
			}
			//82:R 返回/退出
			if (key == 82)
			{
				if (parseInt(document.getElementById("tMoney").value) == 0)
				{
					//cancelBusi();
					doSub();
					return;
				}
			}
			// modify end g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到		
		}
		
		//投币的时长，单位秒		
		var payMoneyTime = "<%=timeout %>";
		
		//剩余时间
		var leftTime = payMoneyTime;
		
		//readCash定时器句柄
		var readCashToken;		
		
		//定时器启动标志(识币器打开时的定时器)
		var timerBz = 0;// 0:启动 1:停止
		
		//识币器关闭后读取投币金额次数
		var readPauseTimeEnd = <%=readPauseTimeEnd %>;
		
		//关闭识币器次数
		var closeCashBillNum = <%=closeCashBillNum %>;
		
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
					//fCloseCashBill();
					// 关闭识币器
					doCloseCashBill();
				}
				
				//clearInterval(readCashToken);				
				timerBz = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/qryfeeChargeAccount.action";
				document.actform.submit();
			}
		}
		
		function doSub()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "<s:property value='servnumber' />确认交费");
			
			//尚未提交请求
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// 确认按钮按下，置不可用状态
				document.getElementById('commitBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
				
				// 取消按钮按下，置不可用状态
				document.getElementById('cancelBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
				
				// modify begin g00140516 2011/12/17 R003C11L12n01 投币后，接着点击交费，最后一次投币金额获取不到	
				if (parseInt(document.getElementById("tMoney").value) == 0)
				{
					//打开等待窗口(正在处理，请稍候......)
					openRecWaitLoading_NX();
				}
				else
				{
					//打开等待窗口(您的充值交费请求已被接收，请等待处理结果)
					openChargeWaitLoading();
				}
				
				// 为了获取最后一次投币金额，重置剩余时间。如果不重置，若leftTime=0，不管推迟多长时间提交交费，都不会获取到投币金额。
				leftTime = "<%=timeout %>";
			
				//关闭现金识币器
				if (needClose == 1)
				{
					// fCloseCashBill();
					// 循环关闭识币器
					doCloseCashBill();
				}
			
				//add begin l00190940 2011-12-22 隔nReadPauseTime秒之后再关闭读币的定时任务，防止最后一次投币的金额获取不到
				setTimeout("commitCharge()", <%=nReadPauseTime %>);
				//add end l00190940 2011-12-22 隔nReadPauseTime秒之后再关闭读币的定时任务，防止最后一次投币的金额获取不到
			}
		}
		
		// modify begin g00140516 2011/12/17 R003C11L12n01 投币后，接着点击交费，最后一次投币金额获取不到
		function commitCharge()
		{	
			// 清除首次定时任务
	        //clearInterval(readCashToken);
	        timerBz = 1;	
	        
	        for (var i=0;i<readPauseTimeEnd;i++)
	        {
	        	doCashByEnd();
	        }
				
	        // 校验金额并提交后台
	        if (parseInt(document.getElementById("tMoney").value) > 0)
	        {        	
	        	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"提交交费请求，号码：" + document.getElementById("servnumber").value + 
						"；金额：" + document.getElementById("tMoney").value + 
						"；流水：" + document.getElementById("terminalSeq").value);
		
				//提交缴费请求
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/cashChargeCommit.action";
				document.actform.submit();
	        }
	        else
	        {
	        	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"号码：" + document.getElementById("servnumber").value + 	"未投币，流程结束");
	        	
	        	// 返回首页			
				setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
	        }
	    }
	    
		// modify end g00140516 2011/12/17 R003C11L12n01 投币后，接着点击交费，最后一次投币金额获取不到
		
		function cancelBusi()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
					"<s:property value='servnumber' />取消充值交费交易");
					
			// 按钮按下，置不可用状态
			document.getElementById('cancelBusi').className = 'btn_box buy_enable_echo_hover fl pl30 pt120';
			
			// modify begin g00140516 2011/12/17 R003C11L12n01 投币后，接着点击交费，最后一次投币金额获取不到				
			//打开等待窗口
			openRecWaitLoading_NX();
			
			//取消交易时，清除定时任务。同时关闭现金识币器			
			if (needClose == 1)
			{
				//fCloseCashBill();
				// 关闭识币器
				doCloseCashBill();
			}
			
			//clearInterval(readCashToken);
			timerBz = 1;
			
			// 返回首页			
			setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 5000);
		}
		
		//出现异常
		function setException(errorMsg) 
		{			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
					"<s:property value='servnumber' />交费过程中出现异常：" + errorMsg);
					
			document.getElementById("errormessage").value = errorMsg;
			
			//出现异常后，清除定时任务。同时关闭现金识币器			
			if (needClose == 1)
			{
				//fCloseCashBill();
				// 关闭识币器
				doCloseCashBill();
			}
			
			//clearInterval(readCashToken);
			timerBz = 1;			
			
			//异常处理，只要出现了异常就要记录日志  
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath }charge/goCashError.action";
			document.actform.submit();
		}
		
		// modify begin g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到
		/*
         * 获取投币金额
		 * 循环调用，每隔1秒执行一次
		 * 
		 **/
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
						
				var ret = getOnceCash();
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"结果：" + ret);
				
				if (ret > 0) 
				{		
					// 记录纸币面额信息
					document.getElementById("cashDetail").value = document.getElementById("cashDetail").value + ret + ";";
								
					// 时间重新开始
					leftTime = "<%=timeout %>";
					
					// 显示剩余时间
					document.getElementById("tTime").value = leftTime;
					
					// 计算投币金额 
					document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
					
					// 投币金额大于0时取消交易按扭不显示,缴费按扭显示
					if (parseInt(document.getElementById("tMoney").value) > 0)
					{
						document.getElementById('commitBusi').style.display = "block";
						document.getElementById('cancelBusi').style.display = "none";
						document.getElementById('promptMsg').innerHTML = "投入纸币结束后，请点击交费按钮";
					}
				}
				else
				{
					// 投币时长一共timeout秒
					leftTime = leftTime - 1;
					
					// 显示剩余时间
					document.getElementById("tTime").value = leftTime;
					
					//投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
					if (parseInt(leftTime) <= 0 && submitFlag == 0)
					{
						// 提交缴费
						doSub();
					}
				}
				
				if (timerBz == 0)
				{
					setTimeout("doCash()", 1000);
				}						
			}
			catch (e) 
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"获取投币金额失败：" + e.message);
				
				setException("对不起，获取投币金额失败，无法使用现金进行充值，请选用其它方式。");
			}
		}
		// modify end g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到
		
		// 关闭识币器后循环读币
		var timerEndNum = 0;
		function doCashByEnd() 
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "关闭识币器后循环读币");
			
			var ret = getOnceCash();
			
			timerEndNum = timerEndNum + 1;
			
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "第"+timerEndNum+"次结果：" + ret);
			
			if (ret > 0) 
			{		
				// 记录纸币面额信息
				document.getElementById("cashDetail").value = document.getElementById("cashDetail").value + ret + ";";
				
				// 计算投币金额 
				document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
			}
		}
		
		// 循环关闭识币器
		var closeEndNum = 0;
		function doCloseCashBill()
		{
			// 记录关闭次数
			closeEndNum = closeEndNum + 1;
			
			// 关闭识币器
			var closeBz = 1;
			
			for (var i=0;i<closeCashBillNum;i++)
			{
				closeBz = fCloseCashBill();
				
				if (closeBz == 0)// 成功
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "第"+(i+1)+"次关闭识币器:成功！");
				}
				else// 失败
				{
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "第"+(i+1)+"次关闭识币器:失败！");
				}
				
				// 关闭识币器成功停止循环
				if (closeBz == 0)
				{
					break;
				}
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
				
				// modify begin g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到
				var cashStatus = checkCashStatus();
				// modify end g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到

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
			
			return msg;
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
				//readCashToken = setInterval("doCash()", 1000);
				doCash();
			}
			catch (e) 
			{
				setException("对不起，计时器发生异常，无法使用现金进行充值，请使用其它方式或者到营业厅进行充值。");
			}
		}
		
		function loadContent() 
        {
			var serverNumber = "<s:property value='servnumber' />";
			if (serverNumber == null || serverNumber == "")
            {            
            	setException("对不起，用户信息获取失败，请返回重新操作。");
          		return;
            }
            
            <%
            if (!"1".equals(isCashEquip))
            {
            %>
            setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
            return;
            <%
            }
            %>
            
            // modify begin g00140516 2012/09/28 R003C12L09n01 用户进入投币页面后，上下按钮均不可用
            try 
            {
            	writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"初始化现金识币器：" + serverNumber);
            	
            	//初始化现金识币器
           	 	var initData = initCashEquip(serverNumber);
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"结果：" + initData);
                	
                if (initData == -99 || initData.substring(0, 1) != "0") 
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
                	
                	// 顶部菜单不可用。
	   				closeStatus = 1;
                	
                	// 初始化成功，就需要关闭识币器。而“首页”、“上一页”、“退出”按钮无法执行此操作，所以禁用三个按钮
                	document.getElementById("homeBtn").disabled = true;
		            document.getElementById("backBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
                	
                	// 屏蔽 “首页”、“上一页”、“退出” 展现
					document.getElementById('footer').style.display = "none";
                	
                	
                	//调用定时器
                	startclock();
                }               
            } 
            catch(e) 
            {
                setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
                return;
            }
            // modify end g00140516 2012/09/28 R003C12L09n01 用户进入投币页面后，上下按钮均不可用         
        }
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCASH %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeFlag" name="chargeFlag" value="<s:property value='chargeFlag' />">
			<input type="hidden" id="cashDetail" name="cashDetail" value="">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="#">1. 输入手机号码</a> 
						<a href="#">2. 选择支付方式</a> 
						<a href="#" class="on">3. 投入纸币</a>
      					<p class="recharge_tips">支持10、50、100元面额的纸币。</p>
      					<a href="#">4. 完成</a>
					</div>
					
					 <div class="b712 fm_pay_money">
					 	<div class="bg bg712"></div>
      					<div class="blank30"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>
        					<div class="blank20"></div>
        					<%-- modify begin g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到 --%>
        					<div class=" pay_money_wrap2">
        					 	<p class="pay_all">
        					 		<span class="pl120">已投入：</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">元</span>
        					 	</p>
        					 	<div class="pay_state clearfix">
        					 		<span class="cash_arrow"></span>
              						<p class="fl fs22">
              							投币状态： 
             							<span id="promptMsg" class="yellow">投入纸币后，显示交费按钮</span>			
              							<br />
              							<span class="pl119">投币时长共</span><span class="yellow"><%=timeout %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />秒
              							<br/>
              							<%--modify begin g00140516 2011/11/04 R003C11L11n01 修改提示信息 --%>
              							<span class="pl119">支持</span><span class="yellow">5、10、20、50、100元</span>面额的纸币
              							<%--modify end g00140516 2011/11/04 R003C11L11n01 修改提示信息 --%>
              						</p>
            					</div>
        					</div>
        					<div class="blank30"></div>
        					<div>
        					 	<img src="${sessionScope.basePath }images/rmb.gif" class="fl pl160" alt="请投币" />
        					 	<!--modify begin cKF76106 2012/09/25 OR_NX_201209_258-->
        					 	<div style="display:none" class="btn_box buy_enable_echo fl pl30 pt120" id="commitBusi">
        					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">确定 (请按确认键)</a>
        					 	</div>
        					 	<div style="display:block;" class="btn_box buy_enable_echo fl pl30 pt120" id="cancelBusi">
        					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">取消 (请按退出键)</a>
        					 	</div>
        					 	<!--modify end cKF76106 2012/09/25 OR_NX_201209_258-->
        					</div>
        					<%-- modify end g00140516 2011/11/17 R003C11L11n01 投币后，接着点击交费，最后一次投币金额获取不到 --%>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-05.wav" id="player05" align="center" autostart=true border="0" style="height:0px;width:0px;">
		</form>
	</body>
	<script type="text/javascript">
		clearTimeout(timeOut);
        loadContent();        
	</script>
</html>
