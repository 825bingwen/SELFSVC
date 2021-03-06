<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>读卡页面</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
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

			//返回
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}		
		}
		
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
		
		// 读卡等待时间
		var limitTime = 30;
		
		//计算读卡剩余时间的句柄
		var timeToken;
		
		//等待读卡的句柄
		var waitingToken;
		
		// 提交标志
		var submitFlag = 0;
		
		// 初始化身份证读卡器线程启动标志，1，已启动；0，未启动。如果为1时，用户主动取消操作，需调用关闭身份证阅读器接口
		var initCardReader = 0;
		
		//出现异常
		function setException(errorMsg) 
		{			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//清除定时任务
				clearInterval(waitingToken);
				
				clearInterval(timeToken);
				
				document.getElementById("errormessage").value = errorMsg;
				
				//初始化身份证读卡器线程启动成功，出现异常后，需关闭			
				if (initCardReader == 1)
				{
					//调用关闭身份证阅读器接口
					CloseCardReader();
				}
				
				//异常处理，只要出现了异常就要记录日志  
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }idCardBook/goCardError.action";
				document.readCardForm.submit();
			}			
		}
		
		function doSub()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
			
				//清除定时任务
				clearInterval(waitingToken);
				
				clearInterval(timeToken);
				
				document.readCardForm.target = "_self";
				document.readCardForm.action = "${sessionScope.basePath }idCardBook/getCardInfor.action";
				document.readCardForm.submit();	
			}			
		}
		
		//计算读卡剩余时间
		function calLeftTime()
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			//读卡时长一共limitTime秒
			limitTime = limitTime - 1;
			
			document.getElementById("tTime").value = limitTime;
			
			//读卡时间结束
			if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
			{           	
	        	setException("操作超时!为你保证您的信息安全请按\"退出\"返回首页,如需继续办理请按\"上一页\",谢谢使用!");
			}
		}
		
		// 循环调用获取身份证文字内容接口
		function getReadCardStatus() 
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			try 
			{
				//生产用
				var ret = GetIdCardContent();// 打开仓门接收用户身份证 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1）
				
				//测试用
				//var ret = "0姓名~男~民族~出生~住址~37061218840530402X~签发机关~有效期起始日期~有效期截止日期~最新住址";

				if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
				{	
					//调用关闭身份证阅读器接口
					CloseCardReader();
					
					//身份证信息：0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
					var idCardInfor = ret.substring(1,ret.length).split('~');
					
					//姓名
					var idCardName = idCardInfor[0];
					
					//性别
					var idCardSex = idCardInfor[1];
					
					//证件住址
					var idCardAddr = idCardInfor[4];
					
					//公民身份号码
					var idCardNo = idCardInfor[5];
					
					//证件开始时间
      				var idCardStartTime = idCardInfor[7];
      				
      				//证件结束时间
      				var idCardEndTime = idCardInfor[8];
      
					// 设定提交内容
					document.getElementById('idCardContent').value = ret;
					document.getElementById("idCardName").value = idCardName;
					document.getElementById("idCardSex").value = idCardSex;
					document.getElementById("idCardNo").value = idCardNo;
					document.getElementById("idCardAddr").value = idCardAddr;
					document.getElementById("idCardStartTime").value = idCardStartTime;
					document.getElementById("idCardEndTime").value = idCardEndTime;
					
					// 提交
					doSub();								
				} 
				else if (ret == "-1")
				{			
					setException("获取身份证文字内容失败，无法使用身份证入网预约。");
				}
				else if (ret == "2")
				{		
					setException("证件无法识别，无法使用身份证入网预约。");
				}						
			}
			catch (e) 
			{		
				setException("身份证读卡器读卡失败，无法使用身份证入网预约。");
			}
		}
		
		//设置时间计算周期
		function startclock() 
		{	
			try 
			{
				// 获取身份证文字内容
				waitingToken = setInterval("getReadCardStatus()", 1000);
				
				// 计时器
				timeToken = setInterval("calLeftTime()", 1000);
			}
			catch (e) 
			{
				setException("读卡器读卡失败，无法使用身份证入网预约。");
			}
		}
		
		//获取身份证读卡器状态
		function getIdCardStatus()
		{
			try
			{
				//生产用
				var ret = GetIdCardStatus();// 获取身份证读卡器状态
				
				if (ret != "0" && ret != 0) 
				{					
					setException("身份证读卡器状态异常，无法使用身份证入网预约。");
				}
			}
			catch (excep) 
			{		
				setException("身份证读卡器状态异常，无法使用身份证入网预约。");
			}
		}
		
		//页面加载时执行
		function bodyLoad() 
		{
			try 
			{				
				//生产用
				var ret = InitIdCardReader();// 设置身份证读卡器为可用状态

				if (ret != "0" && ret != 0) 
				{
					setException("初始化身份证读卡器异常，无法使用身份证入网预约。");
                    return;
				}
				else
				{		
					//准备刷卡进程启动
					initCardReader = 1;
					
					//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
                	document.getElementById("homeBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
					
					//在调用初始化身份证读卡器后，获取身份证信息之前，需要调用此接口检查一下读卡器状态
					getIdCardStatus();
					
					// 开始记时，并循环调用接口
					startclock();
				}
			}
			catch (ex) 
			{
				setException("初始化身份证读卡器异常，无法使用身份证入网预约。");
                return;
			}
		}		
		</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			<input type="hidden" id="idCardName" name="idCardName" value='' />
			<input type="hidden" id="idCardSex" name="idCardSex" value='' />
			<input type="hidden" id="idCardNo" name="idCardNo" value='' />
			<input type="hidden" id="idCardAddr" name="idCardAddr" value='' />
			<input type="hidden" id="idCardStartTime" name="idCardStartTime" value='' />
			<input type="hidden" id="idCardEndTime" name="idCardEndTime" value='' />
			<input type="hidden" id="idCardContent" name="idCardContent" value = '' />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966 tc">
					<div class="b712">
						<div class="blank60"></div>
						<div class="p40 pr0">
							<p class="tit_info tl" align="left">
								<span class="bg"></span><font class="yellow">身份证入网预约</font>
							</p>
							<div class="blank60"></div>
							<p class="tit_info">
								<span>请把您的二代身份证身份证放到感应区</span><br><br>
								<input type="text" id="tTime" name="tTime" value="30"
									readonly="readonly" />
							</p>
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
