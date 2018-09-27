<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// 投币超时时间
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);

// 终端信息
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

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
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
</head>
<body scroll="no" onload="loadContent();">
	<form name="actform" method="post">
		<input type="hidden" name="cardChargeLogVO.payType" value="<%=Constants.PAY_BYCASH%>"/>
		<s:hidden name="cardChargeLogVO.servnumber"/>
		<s:hidden name="cardChargeLogVO.provinceCode"/>
		<s:hidden id="terminalSeq" name="cardChargeLogVO.terminalSeq"/>
		<input type="hidden" id="errormessage" name="errormessage" value=''/>
		
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
					<a href="#">3. 选择金额</a> 
					<a href="#" class="on">4. 投入纸币</a>
     					<p class="recharge_tips">支持10、50、100元面额的纸币。</p>
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
       					 		<span style="padding-left:120px;">已投入：</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">元</span>
       					 	</p>
       					 	<div class="pay_state clearfix">
       					 		<span class="cash_arrow"></span>
             						<p class="fl fs22">
             							投币状态： 
										<s:if test="yingjiaoFee == '0'">
										<span id="promptMsg" class="yellow">投币结束后，请点击缴费按钮</span>
										</s:if>
										<s:else>
										<span id="promptMsg" class="yellow">请投入纸币...</span>
										</s:else>	
             							<br />
             							<span style="padding-left:119px;">投币时长共</span><span class="yellow"><%=timeout %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />秒
             							<br/>
             							<span style="padding-left:119px;">支持</span><span class="yellow">5、10、20、50、100元</span>面额的纸币
             						</p>
           					</div>
       					</div>
       					<div class="blank30"></div>
       					<div>
       					 	<img src="${sessionScope.basePath }images/rmb.gif" style="float:left; padding-left:160px;" alt="请投币" />
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box cancle fl" id="cancelBusi">
       					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">取消本次交易</a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box charge_unable fl" id="bCommitBusi">
       					 		<a href="#" onclick="return false;"></a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px; display:none" class="btn_box buy_enable fl" id="commitBusi">
       					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">缴费</a>
       					 	</div>
       					</div>
     					</div>
				 </div>
			</div>
			
			<!--弹出框 正在处理 请稍候-->
			<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
				<div class="bg"></div>
                  	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
                 	<p class="tips_txt">正在处理，请稍候......</p>
                	<div class="line"></div>
                 	<div class="popup_banner"></div>                
               </div>
               
               	<div class="popup_confirm" id="openWin_tipsMsg">
					<div class="bg"></div>
					<div class="tips_title">提示：</div>
					<div class="fs24 blue pl55 pr30 pt40 line_height_12 h200" id="winText_tipsMsg">
				  	</div>
					<div class="tc">
						<a  href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';wiWindow.close();">继续缴费</a> 
   						<a  class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';setException('您已取消办理本次充值交费业务');">取消办理</a>
					</div>
				</div>

			<script type="text/javascript">
			    var isDone = 0;
				openWindow_wait = function(id){
					if(isDone == 0)
					{
						isDone = 1;
						doSub();
					}
				}				
			</script>
			<!--弹出窗结束-->
		</div>
		
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<script type="text/javascript">
clearTimeout(timeOut);

//82、220 返回
document.onkeydown = pwdKeyboardDown;

//键盘按下
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

//只允许输入数字
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
		doSub();
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
var payMoneyTime = "<%=timeout %>";

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
	//已投币
	if (document.getElementById("tMoney").value != "" 
			&& parseInt(document.getElementById("tMoney").value) > 0)
	{
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//返回时，清除定时任务。同时关闭现金识币器			
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		clearInterval(readCashToken);
		
		document.getElementById("curMenuId").value = menuid;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }nonlocalChargeHUB/qryfeeChargeAccount.action";
		document.actform.submit();
	}
}

//投币不足时的弹出框
function openWindow(id,tipMsg)
{
	document.getElementById('winText_tipsMsg').innerHTML = tipMsg;
	wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子	
}
	
// 提交
function doSub()
{
	//未投币
	if (document.getElementById("tMoney").value == "" 
			|| parseInt(document.getElementById("tMoney").value) <= 0)
	{
		return;
	}
	
	//判断投币金额，不能少于最低金额
	if(parseInt(document.getElementById("tMoney").value) < "<s:property value='minMoney'/>")
	{
		var alsoFee = "<s:property value='minMoney'/>" - getValue("tMoney");
		var tipText = "尊敬的客户，单笔充值交费最低金额为<s:property value='minMoney'/>，请至少再投入"+alsoFee+"元";
		
		openWindow("openWin_tipsMsg",tipText);
		return;
	}
	
	//尚未提交请求
	if (submitFlag == 0)
	{
		document.getElementById('commitBusi').disabled = true;
		submitFlag = 1;
		
		//修改缴费重复提交问题
		wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口例子
	
		//关闭现金识币器
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		//清除定时任务
        clearInterval(readCashToken);

		//提交缴费请求
		document.actform.action = "${sessionScope.basePath }nonlocalChargeHUB/chargeCommit.action";
		document.actform.submit();
	}
}
	
//出现异常
function setException(errorMsg) 
{			
	document.getElementById("errormessage").value = errorMsg;
	
	// 出现异常后，清除定时任务。同时关闭现金识币器			
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	// 清除定时任务
	clearInterval(readCashToken);
	
	// 异常处理，只要出现了异常就要记录日志  
	document.actform.action = "${sessionScope.basePath }nonlocalChargeHUB/goCashError.action";
	document.actform.submit();
}		

/**
 * 初始化现金识币器
 * 1、获取手机号码
 * 2、检查终端识币器特性参数
 * 3、初始化现金识币器
 * 4、获取投币流水号
 * 5、禁用”首页“、“退出”按钮
 * 6、启动循环获取投币金额定时
 */
function loadContent() 
{
	var serverNumber = "<s:property value='cardChargeLogVO.servnumber' />";
	if (serverNumber == null || serverNumber == "")
    {            
		setException("对不起，用户信息获取失败，请返回重新操作。");
		return;
	}
    
    //检查现金识币器特性参数是否可用     
	<%if (!"1".equals(isCashEquip)){%>
		  setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
		  return;
	<%}%>
    
    try 
    {
    	// 初始化现金识币器(正常返回 0,20110509143345)
		var initData = initCashEquip(serverNumber);
	   	
	   	// 标识控件使用
	   	closeStatus = 2;
	   	
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

/**
 * 循环获取投币金额
 */
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
		//检测识币器状态 0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备
		var cashStatus = checkCashStatus();

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

/**
 * 获取投币金额
 * 循环调用，每隔1秒执行一次
 *
 */
function doCash() 
{
	if (leftTime <= 0)
	{
		return;
	}
	
	try 
	{	
		// 获取投币金额 0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
		var ret = getOnceCash();

		if (ret > 0) 
		{
			// 标识控件使用
	   		closeStatus = 1;
			
			// 时间重新开始
			leftTime = "<%=timeout %>";
			
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
				document.getElementById("promptMsg").innerHTML = "已投入纸币，请点击缴费按钮";
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
					//提交缴费
					doSub();
				} 
				else 
				{
					// 取消交易
					cancelBusi();
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
</html>
