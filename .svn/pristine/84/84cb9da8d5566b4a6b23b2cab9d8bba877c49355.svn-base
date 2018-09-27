<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("yingjiaoFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>银行卡交费确认</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js?ver=${jsVersion }"></script>
		<script language="javascript">
			// 提交标志 0:未提交 1:已提交
			var submitFlag = 0;
			
			// 核对时长
			var limitTime = 60;
			
			//计算剩余时间的句柄
			var timeToken;		
			
			var termid = "<%=termInfo.getTermid() %>";
			
			// 商户类型
			var unitType = "1";
			
			// 交易类型
			var businesstype = "A";// 'A':消费交易 'B':重打印 'C':查余额
			
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
				else if (key == 13 || key == 89 || key == 221)
				{
					openWindow_wait('pls_wait');
				}		
			}
		
		function goback(menuid)
		{
			setException("交费操作被取消，请注意取走您的储蓄卡。");
		}
		
		/********************RUN POS流程*******************************/
		/**
		1：自助缴费终端向银商业务前置发起银行卡扣款请求。
		2：银商业务前置向银联POSP发起银行卡扣款交易。
		3：银联POSP向银商业务前置返回银行卡扣款结果。
		4：银商业务前置将银行卡扣款结果转发给自助缴费终端。
		5：自助缴费终端向自助缴费终端综合管理平台发起移动记账请求。
		6：自助缴费终端综合管理平台向BOSS发起移动记账请求终端。
		7：BOSS向自助缴费终端综合管理平台返回记账结果。
		8：自助缴费终端综合管理平台将记账结果返回给自助终端
		**/
		/**************************************************************/
		
		/************************************************
						RUN POS流程
		************************************************/
		
		//出现异常
		function setException(errorMsg) 
		{			
			submitFlag = 1;	//提交标记
			
			//清除定时任务
			clearInterval(timeToken);
			
			document.getElementById("errormessage").value = errorMsg;
				
			//异常处理，只要出现了异常就要记录日志  
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }charge/goCardError.action";
			document.actForm.submit();		
		}
		
		// 与BOSS计帐(银联卡)
		function payToBoss() 
		{
			if (document.getElementById("tMoney").value == "" 
					|| parseInt(document.getElementById("tMoney").value) <= 0)
			{
				return;
			}
			
			//提交缴费请求
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }charge/cardChargeCommit.action";
			document.actForm.submit();			
		}
		
		// 中英文字符串长度
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
		
		// 银联返回字符串
		var resultstr;
		
		//银联卡扣款
		function fPosPay()
		{
			try
			{
				document.getElementById("errorType").value = "add";
				
				//扣款之前记录日志
				var url = "${sessionScope.basePath }charge/addChargeLog.action";
				
				var params = "servnumber=" + <s:property value='servnumber' /> + "&paytype=1&tMoney=" + <s:property value='yingjiaoFee' />;
				params = params	+ "&status=00&description=" + encodeURI(encodeURI('扣款之前记录日志')) + "&acceptType=" + document.getElementById("acceptType").value;
				params = params	+ "&servRegion=" + document.getElementById("servRegion").value+ "&chargeType=" + <s:property value='chargeType' />;
			
				var loader = new net.ContentLoader(url, netload = function () {
					var resXml = this.req.responseText;
					var dataArray = resXml.split("~~");
					//记录日志成功
					if (dataArray[0] == "1") 
					{
						//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
						document.getElementById("errorType").value = "update";
							
						document.getElementById("chargeLogOID").value = dataArray[1];							
										
						try
						{
	    					//生产用
							var ret = CloseCom();// 关闭密码键盘所有打开的线程
							
							if (ret != 0)
							{
								setException("关闭密码键盘所有打开线程失败");
									
								return;
							}
						}
						catch(e)
						{
							setException("关闭密码键盘所有打开线程失败");
									
							return;
						}
							
						try
						{
	    					//生产用
							var ret = CloseComByCard();// 关闭读卡器所有打开的线程
							
							if (ret != 0)
							{
								setException("关闭读卡器所有打开线程失败");
									
								return;
							}
						}
						catch(e)
						{
							setException("关闭读卡器所有打开线程失败");
									
							return;
						}						
							
						// 发起银联卡扣款请求	
						var continueFlag = false;
						var printcontext;
						try
						{
							// 交易请求报文 
							// 交易代码(2)+交易金额(12)+POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)
							// + 原交易日期(8)+卡片类型(1)+自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
							var strin = '';
							strin = strin + '01';// 交易代码(2)
							strin = strin + formatStr('<%=fenMoney %>','left','0',12);// 交易金额(12)
							strin = strin + formatStr('','right',' ',55);// POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)+ 原交易日期(8)
							strin = strin + 'H';// 卡片类型(1)
							strin = strin + formatStr('','right',' ',240);// 自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
												
							//生产用	
						    if(true) // true:生产 false:测试
						    {	
								resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
								continueFlag = true;
							}
							else //测试用	
							{
								// 返回码(6)+	返回码含义(40)+POS流水号(6)+授权码(6)+批次号(6)+卡号(19)+有效期(4)+银行号(2)+银行检索参考号(12)+终端号(15)+商户号(15)
								// 交易金额(12)+加密后的密码或定单号(16)+分期付款信息(74)+发卡行代码(8)+银联主机主易日期(8)+银联主机主易时间(6)
								resultstr = '000000';// 返回码(6)
								resultstr = resultstr + formatStr('','right',' ',40);// 返回码含义(40)
								resultstr = resultstr + "pos001";// POS流水号(6)
								resultstr = resultstr + "sqm001";// 授权码(6)
								resultstr = resultstr + "pch001";// 批次号(6)
								resultstr = resultstr + "kahao12345678901234";// 卡号(19)
								resultstr = resultstr + "0313";// 有效期(4)
								resultstr = resultstr + "01";// 银行号(2)
								resultstr = resultstr + "chankaohao99";// 银行检索参考号(12)
								resultstr = resultstr + "zdh123456789012";// 终端号(15)
								resultstr = resultstr + "shh123456789012";// 商户号(15)
								resultstr = resultstr + "000000000001";// 交易金额(12)
								resultstr = resultstr + "****************";//加密后的密码或定单号(16)
								resultstr = resultstr + formatStr('','right',' ',74);// 分期付款信息(74)
								resultstr = resultstr + formatStr('','right',' ',8);// 发卡行代码(8)
								resultstr = resultstr + '20120101';// 银联主机主易日期(8)
								resultstr = resultstr + '101010';// 银联主机主易时间(6)
							}
							
							continueFlag = true;
												
						}
						catch (e){}
						
						try
						{
	    					//生产用
							var ret = OpenCom();// 打开密码键盘
							
							if (ret != 0)
							{
								setException("打开密码键盘失败");
									
								return;
							}
						}
						catch(e)
						{
							setException("打开密码键盘失败");
									
							return;
						}			
						
						// 设置密码键盘为普通键盘模式
						try
						{
							//生产用
							var ret = SetWorkMode(0);// 设置密码键盘为普通键盘模式
						    
							if (ret == -1)
							{
								setException("银联扣款后设置密码键盘的工作模式失败");
								
								return;
							}
						}
						catch(e)
						{
							setException("银联扣款后设置密码键盘的工作模式失败");
									
							return;
						}
							
						if (!continueFlag)
						{
							setException("银联卡扣款失败，不能继续进行充值操作。请取走您的储蓄卡。");
								
							return;
						}
						
						// 扣款成功 定长255 成功000000
						if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
						{									

							// 流水号_交易参考号
							document.getElementById("terminalSeq").value = resultstr.substring(resultstr.length-166,resultstr.length-154);
							document.getElementById("tMoney").value = resultstr.substring(resultstr.length-124,resultstr.length-112);
									
							//更新日志
							var url1 = "${sessionScope.basePath}charge/updateCardChargeLog.action";
					
							var params1 = "chargeLogOID=" + document.getElementById("chargeLogOID").value;
							params1 = params1 + "&unionpayuser=" + resultstr.substring(resultstr.length-139,resultstr.length-124);// 商户编码
							params1 = params1 + "&unionpaycode=" + resultstr.substring(resultstr.length-154,resultstr.length-139);// POS机编号
							params1 = params1 + "&busitype=" + encodeURI(encodeURI("缴费交易"));// 交易类型
							params1 = params1 + "&cardnumber=" + resultstr.substring(resultstr.length-191,resultstr.length-172);// 卡号
							params1 = params1 + "&posnum=" + resultstr.substring(resultstr.length-209,resultstr.length-203);// POS流水号
							params1 = params1 + "&batchnum=" + resultstr.substring(resultstr.length-197,resultstr.length-191);// 批次号
							params1 = params1 + "&authorizationcode=" + resultstr.substring(resultstr.length-203,resultstr.length-197);// 授权码
							params1 = params1 + "&businessreferencenum=" + resultstr.substring(resultstr.length-166,resultstr.length-154);// 交易参考号
							params1 = params1 + "&unionpaytime=" + resultstr.substring(resultstr.length-14,resultstr.length);// 扣款时间
							params1 = params1 + "&vouchernum=";// 凭证号
							params1 = params1 + "&unionpayfee=" + resultstr.substring(resultstr.length-124,resultstr.length-112);// 扣款金额
							params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;
							params1 = params1 + "&status=01";
							params1 = params1 + "&description=" + encodeURI(encodeURI(resultstr.substring(resultstr.length-249,resultstr.length-209)));// 返回码含义
									
							var loader1 = new net.ContentLoader(url1, netload = function () {
								var resXml1 = this.req.responseText;
										
								//更新日志成功
								if (resXml1 == "1" || resXml1 == 1)
								{
									//交费
									payToBoss();									
								}
								//更新日志失败
								else
								{										
									setException("银联扣款成功，但是更新日志失败，不能进行充值。请取走您的储蓄卡。");
									
									return;
								}								
							}, null, "POST", params1, null);
						}
						//扣款失败
						else
						{
							setException("银联卡扣款失败，不能继续进行充值操作。请取走您的储蓄卡。");
									
							return;
						}				
					}
					//记录日志失败
					else 
					{						
						setException("扣款之前记录用户信息失败，不能继续进行充值操作。请取走您的储蓄卡。");
					}					
				}, null, "POST", params, null);	
			}
			catch (e)
			{
				if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
				{
					setException("银联卡扣款成功，但是交费失败。请取走您的储蓄卡。");
				}
				else
				{
					setException("银联卡扣款失败，不能继续进行充值操作。请取走您的储蓄卡。");
				}
			}				
		}
		
		//提交交费
		function doSub() 
		{		
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				fPosPay();				
			}
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
				//发生异常，增加日志
				document.getElementById("errorType").value = "add";
				
				setException("核对信息超时，请取走您的储蓄卡。");
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
				
				setException("核对信息失败，请取走您的储蓄卡。");
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
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">		
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCARD %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeLogOID" name="chargeLogOID" value="">
			<input type="hidden" id="chargeType" name="chargeType" value="<s:property value='chargeType' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择支付方式</a>
    					<a href="javascript:void(0)">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 免责声明</a>
    					<a href="javascript:void(0)">5. 插入储蓄卡</a> 
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)" class="on">7. 核对信息</a>
    					<p class="recharge_tips">核对交费号码</p>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> 元</p>
							<p class="tit_info"><span>核对信息时长共</span><span class="yellow">60</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />秒</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">交费</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--弹出框 正在处理 请稍候-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
                   	
                   	<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
                  	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %></p>
                 	<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
                 	
                 	<div class="line"></div>
                  	<div class="popup_banner"></div>                
                </div>

				<script type="text/javascript">
					openWindow_wait = function(id){
						//清除定时任务
						clearInterval(timeToken);
						
						wiWindow = new OpenWindow("pls_wait", 804, 515);//打开弹出窗口例子
					    setTimeout("doSub()", 500);
					}				
				</script>
				<!--弹出窗结束-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
	clearTimeout(timeOut);
	startclock();
	
	// 标识控件使用
	closeStatus = 1;
	</script>
</html>
