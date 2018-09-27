<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("payAmount");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
		<script language="javascript">

		
		</script>
	</head>
	<body scroll="no">
		<form name="actForm" method="post">
			<%-- 手机号码 --%>
			<s:hidden id="servnumber" name="chargeLogVO.servnumber" ></s:hidden>
			
			<%-- 应缴金额 --%>
			<s:hidden id="payAmount" name="payAmount"></s:hidden>
			
			<%-- 省份编码 --%>
			<s:hidden id="telProvinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<%-- 手机号码归属地市--%>
			<s:hidden id="servRegion" name="chargeLogVO.servRegion"></s:hidden>
			
			<%-- 余额--%>
			<s:hidden id="balance" name="balance"></s:hidden>
			
			<%-- 金额--%>
			<s:hidden id="tMoney" name="tMoney" value=''></s:hidden>
			
			<%-- 是否需要退卡0：不需要 1：需要--%>
			<s:hidden id="needReturnCard" name="needReturnCard" value='1'></s:hidden>
			
			<%-- 终端交易流水--%>
			<s:hidden id="terminalSeq" name="chargeLogVO.terminalSeq"></s:hidden>
			
			<%-- 错误信息 --%>
			<s:hidden id="errormessage" name="errormessage" value=""></s:hidden>
			
			<%-- 缴费日志唯一标识  --%>
			<s:hidden id="chargeLogOID" name="chargeLogVO.chargeLogOID" value=""></s:hidden>
			
			<%-- 支付方式 1:银联卡 4：现金--%>
			<s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>	
			
			<%-- 主动退出标识--%>
			<s:hidden id="quitFlag" name="quitFlag" value=""></s:hidden>
			
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
    					<a href="javascript:void(0)">5. 插入银联卡</a>
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)" class="on">7. 核对信息</a>
    					<p class="recharge_tips">核对交费号码</p>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="chargeLogVO.servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="payAmount" /></span> 元</p>
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
	
	// 交易类型
	var businesstype = "A";// 'A':消费交易 'B':重打印 'C':查余额
	
	//82、220 返回		
	document.onkeyup = pwdKeyboardUp;

	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//返回
		if (key == 13 || key == 89 || key == 221)
		{
			commitBusi();
		}
		//退出：82
		else if (key == 82)
		{
			cancelBusi();
		}
	}
	
	clearTimeout(timeOut);
	startclock();

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
	
	//计算剩余时长
	function waitForSubmit() 
	{
		writeDetailLog("<%=chargeLogDetail %>",  "等待用户确认缴费");
	
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
			
			writeDetailLog("<%=chargeLogDetail %>", "确认超时");
			
			// 发生异常，增加日志
			setException("核对信息超时，请取走您的银联卡。");
		}
	}
	
	// 确认交费
	function commitBusi()
	{
		if (submitFlag == 0) 
		{
			submitFlag = 1;
			
			writeDetailLog("<%=chargeLogDetail %>", "确认交费");
				
			//清除定时任务
			clearInterval(timeToken);
					
			openChargeWaitLoading();
		
			setTimeout("fPosPay()", 500);
		}
	}
	
	// 银联卡扣款
	function fPosPay()
	{
		try
		{
			writeDetailLog("<%=chargeLogDetail %>", "扣款之前记录交费日志");
			
			//扣款之前记录日志
			var url = "${sessionScope.basePath }nonlocalCharge/addCardChargeLog.action";
			
			var params = "chargeLogVO.servnumber=" + <s:property value='chargeLogVO.servnumber' /> ;
			params = params + "&tMoney=" + <s:property value='payAmount' />*100;
			params = params	+ "&status=00";
			params = params + "&description=" + encodeURI(encodeURI('扣款之前记录日志'));
			params = params	+ "&chargeLogVO.servRegion=" + document.getElementById("servRegion").value;
			params = params + "&chargeLogVO.provinceCode="+document.getElementById("telProvinceCode").value;
		
			var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
				var dataArray = resXml.split("~~");
				
				// 记录日志成功
				if (dataArray[0] == "1") 
				{
					writeDetailLog("<%=chargeLogDetail %>", "日志记录成功");
				
					document.getElementById("chargeLogOID").value = dataArray[1];							
									
					try
					{
    					writeDetailLog("<%=chargeLogDetail %>", "卡机关闭");
    					
    					//生产用
						var ret = CloseReadingCardFixing();// 关闭读卡器和密码键盘所有打开的线程

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
						writeDetailLog("<%=chargeLogDetail %>", "设置密码键盘工作模式为密文");
						
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
						bankrequest = createBlankStr(8);// POS机号(8)
						bankrequest = bankrequest + createBlankStr(8);// POS员工号(8)
						bankrequest = bankrequest + '41';// 交易类型标志(2)_'41:缴费'
						bankrequest = bankrequest + formatStr('<%=fenMoney %>','left','0',12);// 金额(12)
						bankrequest = bankrequest + createBlankStr(8);// 原交易日期(8)
						bankrequest = bankrequest + createBlankStr(12);// 原交易参考号(12)
						bankrequest = bankrequest + createBlankStr(6);// 原凭证号(6)
						bankrequest = bankrequest + randomNumber;// LRC校验(3)
						bankrequest = bankrequest + formatStr('<s:property value="chargeLogVO.servnumber" />','right',' ',20);// 电话号码(20)
						bankrequest = bankrequest + '01';// 请求发票类型(2)_00:不返回发票；01:返回发票；冲正时为空格
						bankrequest = bankrequest + '01';// 交易码(2)_01按号码交费,11按挂帐帐号交费
						bankrequest = bankrequest + createBlankStr(20);// 行业信息1
						bankrequest = bankrequest + createBlankStr(20);// 行业信息2
						
						writeDetailLog("<%=chargeLogDetail %>", "扣款请求报文：" + bankrequest);
								
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
						
						writeDetailLog("<%=chargeLogDetail %>",  "扣款应答报文：" + bankresponse);
						
						continueFlag = true;
					}
					catch (e){}
					
					// 设置密码键盘为普通键盘模式
					try
					{
						writeDetailLog("<%=chargeLogDetail %>",  "设置密码键盘工作模式为明文格式");
						
						//生产用
						var ret = initKeyBoard();// 设置密码键盘为普通键盘模式
					}
					catch(e)
					{
					}
						
					if (!continueFlag)
					{
						writeDetailLog("<%=chargeLogDetail %>", "扣款异常");
						
						setException("银联卡缴费失败，请取走您的银联卡。");
						return;
					}
					
					// 扣款成功 定长148 成功00 比对随机数是否一样
					if (bankresponse.substring(0,2) == "00" && strlen(bankresponse) == 148 && bankresponse.substring(bankresponse.length-3,bankresponse.length) == randomNumber)
					{
					    // 凭证号 + 交易参考号
						document.getElementById("terminalSeq").value = bankresponse.substring(26,32) + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);
						document.getElementById("tMoney").value = bankresponse.substring(32,44);
						writeDetailLog("<%=chargeLogDetail %>", "扣款交费成功，交费金额：" + document.getElementById("tMoney").value);
								
						//更新日志
						var url1 = "${sessionScope.basePath }nonlocalCharge/updateCardChargeLog.action";

						var params1 = "chargeLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value;// id
						params1 = params1 + "&chargeLogVO.bankno=" + bankresponse.substring(2,6);// 银行行号 发卡行代码
						params1 = params1 + "&chargeLogVO.cardnumber=" + bankresponse.substring(6,26);// 卡号
						params1 = params1 + "&chargeLogVO.vouchernum=" + bankresponse.substring(26,32);// 评证号
						params1 = params1 + "&chargeLogVO.unionpayfee=" + bankresponse.substring(32,44);// 扣款金额
						params1 = params1 + "&chargeLogVO.unionpayuser=" + bankresponse.substring(bankresponse.length-64,bankresponse.length-49);// 商户号
						params1 = params1 + "&chargeLogVO.unionpaycode=" + bankresponse.substring(bankresponse.length-49,bankresponse.length-41);// 终端号
						params1 = params1 + "&chargeLogVO.busitype=" + encodeURI(encodeURI("缴费交易"));// 交易类型
						params1 = params1 + "&chargeLogVO.posNum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// 批次号
						params1 = params1 + "&chargeLogVO.batchnum=" + bankresponse.substring(bankresponse.length-41,bankresponse.length-35);// 批次号
						params1 = params1 + "&chargeLogVO.unionpaytime=" + bankresponse.substring(bankresponse.length-35,bankresponse.length-31) + bankresponse.substring(bankresponse.length-31,bankresponse.length-25);// 银联扣款时间
						params1 = params1 + "&chargeLogVO.businessreferencenum=" + bankresponse.substring(bankresponse.length-25,bankresponse.length-13);// 交易参考号
						params1 = params1 + "&chargeLogVO.authorizationcode=" + bankresponse.substring(bankresponse.length-13,bankresponse.length-7);// 授权码
						params1 = params1 + "&chargeLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;// 终端流水
						params1 = params1 + "&chargeLogVO.status=11";
						params1 = params1 + "&chargeLogVO.description=" + encodeURI(encodeURI('扣款并缴费成功'));
						params1 = params1 + "&chargeLogVO.servnumber=<s:property value='chargeLogVO.servnumber' />";
								
						var loader1 = new net.ContentLoader(url1, netload = function () {
							var resXml1 = this.req.responseText;
							
							//更新日志成功
							if (resXml1 == "1" || resXml1 == 1)
							{
								writeDetailLog("<%=chargeLogDetail %>", "扣款交费成功后，更新日志记录成功");
								
								//交费
								goSuccess();									
							}
							//更新日志失败
							else
							{
								writeDetailLog("<%=chargeLogDetail %>",  "扣款交费成功后，更新日志记录失败");
								
								setException("银联卡缴费成功，但是更新日志失败。请取走您的银联卡。");
								
								return;
							}								
						}, null, "POST", params1, null);
					}
					//扣款失败
					else
					{
						writeDetailLog("<%=chargeLogDetail %>", "扣款失败");
						
						setException("银联卡缴费失败，请取走您的银联卡。原因：" + bankresponse.replace(/\s/g,''));
						return;
					}				
				}
				//记录日志失败
				else 
				{
					writeDetailLog("<%=chargeLogDetail %>", 
							"日志记录失败");
					
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
	
			//出现异常
		function setException(errorMsg) 
		{
			exSubmitFlag = 1;	//提交标记
			
			writeDetailLog("<%=chargeLogDetail %>", 
					"银联卡交费异常：" + errorMsg);
				
				//清除定时任务
			clearInterval(timeToken);
		
			document.getElementById("errormessage").value = errorMsg;
			
			//异常处理，只要出现了异常就要记录日志
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }nonlocalCharge/goCardError.action";
			document.actForm.submit();
		}
		
		// 与BOSS计帐(银联卡)
		function goSuccess() 
		{
			writeDetailLog("<%=chargeLogDetail %>", "扣款交费成功");
			
			//提交缴费请求
			document.actForm.target = "_self";
			document.actForm.action = "${sessionScope.basePath }nonlocalCharge/cardChargeCommit.action";
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
		
		<%-- 用户可主动退出交费交易。 --%>
		function cancelBusi()
		{
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>",  "退出交费");
				
				document.getElementById("quitFlag").value = "1";
						
				setException("您已取消本次交费，感谢您的使用，请取走您的银联卡。");
			}			
		}
	
	// 标识控件使用
	closeStatus = 1;
	</script>
</html>
