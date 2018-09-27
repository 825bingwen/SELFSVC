<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/MoveCardManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
<script language="javascript">
//防止重复提交
var submitFlag = 0;

// 0-打印机正常，1-打印机异常；
var printIsOk = 0;

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

// 按键是否是数字判断
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
		return;
	}
	//确认
	else if (key == 13 || key == 89 || key == 221)
	{
		doSub();
	}			
}

// 返回首页
function goback(menuid)
{
	goHomePage();
}

// 提交转到读身份证页面
function doSub() 
{
	if (submitFlag == 0) 
	{
		// 票据打印机异常不能打印小票，需用户进行确认
		if(printIsOk == 1)
		{
			openRealNameConfirm("票据打印机异常不能打印小票，是否继续？");
		}
		else
		{
			submitFlag = 1;	//提交标记
			openRecWaitLoading_NX("recWaitLoading");
			document.dutyInfoForm.target = "_self";
			document.dutyInfoForm.action = "${sessionScope.basePath }prodInstall/readCert.action";
			document.dutyInfoForm.submit();
		}
	}
}

// 出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading_NX("recWaitLoading");
		
		document.getElementById("errormessage").value = errorMsg;

		//异常处理，只要出现了异常就要记录日志  
		document.dutyInfoForm.target = "_self";
		document.dutyInfoForm.action = "${sessionScope.basePath }prodInstall/installError.action";
		document.dutyInfoForm.submit();
	}
}	

// 首先加载
function doLoad()
{
	/**
	功能：初始化发卡器/写卡器，使其处于可用状态
	参数：无 
	返回：成功0；失败-1
	场景：发卡器/写卡器端口打开后，使用发卡器/写卡器之前，调用该接口将发卡器/写卡器设置为可用状态。
	**/
	var ret = InitMoveCard();
	if (ret != 0)
	{
		setException("发写卡器初始化失败！");
		return;
	}
	
	/**
	功能：检查发卡器状态
	参数：无
	返回：失败：-1；成功：0~通道卡片位置~卡箱卡片状态
	举例：如返回 0~0~1 表示 通道无卡，卡箱卡片不足,提醒需要加卡
	           0~2~0 表示 IC卡位置有卡，卡箱无卡
	通道卡片位置：长度一个字节。
	0：通道无卡
	1：读磁卡位置有卡
	2：IC卡位置有卡
	3：前端夹卡位置有卡
	4：前端不夹卡位置有卡
	注意：当卡不在以上四个位置时，自动将卡移至回收箱，再返回通道和卡片箱状态。
	卡箱卡片状态：
	  0:  卡箱无卡
	  1:  卡箱卡片不足,提醒需要加卡
	  2:  卡箱卡片足够
	场景：初始化发卡器后，需要调用此接口检查设备状态。
	**/
	ret = ReadCardStatus();
	if (ret == '0~0~1')
	{
		setException("卡箱卡片不足,需要加卡！");
		return;
	}
	
	/**
	功能：初始化票据打印机
	参数：无
	返回：0:成功 其他失败
	场景：初始化票据打印机
	**/
	ret = getPrinterStatus();
	if (ret != 0)
	{
	    // 异常：提示用户票据打印机异常，不能打印小票，是否继续。如果用户选择继续那么流程继续，如果选择否，那么结束开户。
		printIsOk = 1;
		return;
	}
	
	// 检测识币器状态
	var ret1 = checkCashStatus();
	
	// 初始化银联读卡器
	var ret2 = InitReadUserCard();
	
	if (ret1 != 0 && ret2 != 0)
	{
		if (ret != 0)
		{
			setException("缴费设备异常不能进行开户！");
			return;
		}
	}
}	

//弹出确认框
openRealNameConfirm = function(content)
{
	document.getElementById('printPromptId').innerHTML = content;
	wiWindow = new OpenWindow("printCheck_confirm",708,392);
}

// 是否打印小票  1-不打印，0-打印
function printConfirm()
{
 	document.getElementById('canNotPrint').value = "1";
 	if (submitFlag == 0) 
	{
		//提交标记
	 	submitFlag = 1;	
		openRecWaitLoading_NX("recWaitLoading");
		document.dutyInfoForm.target = "_self";
		document.dutyInfoForm.action = "${sessionScope.basePath }prodInstall/readCert.action";
		document.dutyInfoForm.submit();
	}
}	
</script>
</head>

<body scroll="no" onload="doLoad();">
	<form name="dutyInfoForm" method="post" target="_self">
		<%-- 错误信息 --%>
		<input type="hidden" id="errormessage" name="errormessage" value="" />
		<%-- 是否打印小票  1-不打印，0-打印 --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="0" />
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>在线开户</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)" class="on">1. 入网协议确认</a> 
					<a href="javascript:void(0)">2. 读取身份证信息</a>
   					<a href="javascript:void(0)">3. 产品选择</a> 
   					<a href="javascript:void(0)">4. 号码选择</a>
   					<a href="javascript:void(0)">5. 设置服务密码</a> 
   					<a href="javascript:void(0)">6. 费用确认</a>
   					<a href="javascript:void(0)">7. 开户缴费</a>
   					<a href="javascript:void(0)">8. 取卡打印发票</a>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank15"></div>
   					<div class="p40">
   						<p class=" tit_info1">
   							尊敬的客户：<br/>
		      				&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团宁夏有限公司的客户。在您申办业务前，请认真阅读以下条款：<br/>
		      				&nbsp;&nbsp;&nbsp;&nbsp;本终端业务受理系统的所有权和运作权，以及所受理具体业务的经营权归中国移动通信集团宁夏有限公司所有，您必须完全同意所有服务条款，才可以通过本终端办理各类业务。
		      				移动电话号码和服务密码是您重要的个人资料，可以作为办理业务的凭证。凡使用本终端凭服务密码办理的一切业务，均视为您本人亲自办理，并由您本人负责。请您务必注意服务密码的保密。
		      				您必须提供准确的资料，我公司将根据您提供的资料进行核对，如有不符，系统将不予受理业务。
		      				目前本终端受理以下免办理手续费用的项目，包括业务办理，缴纳话费，打印清单，补打发票等业务。<br/>
		      				&nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意]继续受理业务。如果您不同意以上条款，请按[首页]或[退出]，本系统将自动退至主界面。
   						</p>
   						<div class=" tr" style="position: absolute; top: 410px; left: 500px;"> 
   							<a class=" tongyi" href="javascript:void(0);" onMouseDown="this.className='tongyi_on'" onMouseUp="this.className='tongyi';" onclick="doSub();return false;"></a> 
   						</div>
   					</div>
   				</div>
			</div>
		</div>
		
		<%@ include file="/backinc.jsp"%>
	</form>
		<div class="popup_confirm" id="printCheck_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p id="printPromptId"></p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="#"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';printConfirm();">是</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();setException('票据打印机异常，用户已取消操作');">否</a>
				</span>
			</div>
		</div>
</body>
</html>
