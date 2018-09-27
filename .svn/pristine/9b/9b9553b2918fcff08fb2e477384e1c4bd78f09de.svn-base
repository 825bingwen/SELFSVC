<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
String yuanMoney = (String) request.getAttribute("recFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>银行卡缴费确认</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
		<script language="javascript">
			// 提交标志 0:未提交 1:已提交
			var submitFlag = 0;
			
			// 核对时长
			var limitTime = 60;
			
			//计算剩余时间的句柄
			var timeToken;		
			
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
		
		//返回上一页
		function goback(menuid)
		{
			setException("缴费操作被取消，请注意取走您的银联卡。");
		}
		
		//出现异常
		function setException(errorMsg) 
		{
			// 清除定时任务
			clearInterval(timeToken);
		
			// 显示错误信息
			document.getElementById("errormessage").value = errorMsg;
			
			//异常处理，只要出现了异常就要记录日志  
			document.actForm.action = "${sessionScope.basePath }reissueCard/goCardError.action";
			document.actForm.submit();
		}
		
		/**
		 * 校验空白卡资源
		 *
		 * 1、检验是否为预制空卡。
		 * 2、校验空白卡资源是否可用
		 * 3、预占空白卡资源
		 *
		 */
		function checkBlankCard()
		{
			var postStr ="cardBusiLogPO.blankCard="+getValue("blankCard")
				+"&curMenuId="+getValue("curMenuId");  
				 
			var url = "${sessionScope.basePath}reissueCard/authBlankCard.action";
			var resXml;
			
			//同步调用
			var loader = new net.ContentLoaderSynchro(
				url, netload = function () 
				{	
					resXml = this.req.responseText;
				}, 
				null, "POST", postStr, null);
				
			return resXml;
		}
		
		/**
 		 * 二次写卡流程
         * 入参：项目路径
         * 流程：1、将卡移到读卡位。2、读空白卡。
         *      3、校验空白卡。4、写卡
         */
		function writeAgain(basePath)
		{
			//1、将卡移到读卡位
			var message = checkReadCardStatus();
			if (message != "")
			{
				setException(message);
				return;
			}
			
			//2、读取空白卡序列号
			var blankCardSN = readBlankCardSN();
			
			if (20 != blankCardSN.length)
			{
				return "读取空白卡卡号失败！";
			}
			
			setValue("blankCard",blankCardSN);
					
			//3、校验空白卡资源
			var resXml = checkBlankCard();
			      
			var resArray = resXml.split('~~');
			
			if (resArray[0] == 0 || resArray[0] == "0")
			{
				setValue("iccid",resArray[1]);
				setValue("imsi",resArray[2]);
				setValue("smsp",resArray[4]);
			}
			else
			{	
				setException(resArray[1]);
				return;
			}
			
			//4、写卡
			var writeData = writeCard(resXml.substring(3).replace("+", "^^"),getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
			
			// 二次写卡失败 跳转异常页面
			if(writeData != 0)
			{
				setException(writeData.split("~")[1]);
				
				return;
			}
			
			return writeData;
		}
		
		/**
		 * 写卡并提交
		 * 若一次写卡失败，则进行二次写卡。
		 */
		function writeAndSub() 
		{
			//项目路径
			var basePath = "${sessionScope.basePath}";
		
			if (document.getElementById("tMoney").value == "" 
					|| parseInt(document.getElementById("tMoney").value) <= 0)
			{
				setException("银联扣款金额异常");
				return;
			}

			//设置写卡状态 默认2：初始状态 0：写卡成功 1：写卡失败
			setValue("writeCardStatus","1");
			
			//iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
			var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("eki")+ "~~" +getValue("smsp").replace("+", "^^");
			simInfo = simInfo+"~~"+getValue("pin1")+"~~"+getValue("pin2")+"~~"+getValue("puk1")+"~~"+getValue("puk2");
	
			//写卡:入参：写卡加密数据，空白卡卡号，流水号（用于写卡结果校验，与申请写卡时的入参一致）   
			var writeData = writeCard(simInfo,getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
	
			//第一次写卡时如不是写卡控件异常，直接跳转，不再二次写卡
			if(0 != writeData && writeData.indexOf("11~") == -1)
			{
				setException(writeData.split("~")[1]);
				return;
			}
			
			// 写卡失败 进行二次写卡
			if(writeData.indexOf("11~") != -1)
			{
				// 再次写卡
				writeData = writeAgain(basePath);
			}
			
			//写卡成功	
			if (writeData == 0)
			{
				//写卡成功
				setValue("writeCardStatus","0");
			
				//提交缴费请求
				document.actForm.action = "${sessionScope.basePath}reissueCard/reissueCommit.action";
				document.actForm.submit();
			}
		}
		
		/**
		 * 扣款后更新日志
		 */
		function updateUnionCardLog(printcontexts)
		{
			//更新日志
			var url1 = "${sessionScope.basePath }reissueCard/updateUnionCardLog.action";
	
			var params1 = "cardChargeLogVO.chargeLogOID=" + getValue("chargeLogOID") + "&cardChargeLogVO.unionpayuser=" + printcontexts[0];
			params1 = params1 + "&cardChargeLogVO.unionpaycode=" + printcontexts[1] + "&cardChargeLogVO.busiType=" + encodeURI(encodeURI(printcontexts[2]));
			params1 = params1 + "&cardChargeLogVO.batchnum=" + printcontexts[4] + "&cardChargeLogVO.authorizationcode=" + printcontexts[5];
			params1 = params1 + "&cardChargeLogVO.businessreferencenum=" + printcontexts[6] + "&cardChargeLogVO.unionpaytime=" + printcontexts[7];
			params1 = params1 + "&cardChargeLogVO.vouchernum=" + printcontexts[8] + "&cardChargeLogVO.unionpayfee=" + printcontexts[9];
			params1 = params1 + "&cardChargeLogVO.posResCode=" + getValue('posResCode');
			params1 = params1 + "&cardBusiLogPO.oid=" + getValue("oid");
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
				var resXml1 = this.req.responseText;
				
				//更新日志成功
				if (resXml1 == "0" || resXml1 == 0)
				{
					//写卡
					writeAndSub();	
				}
				//更新日志失败
				else
				{	
					setException("缴费失败，请取走您的银联卡和小票，凭小票到到营业厅办理退款手术，谢谢使用!");
					return;
				}								
			}, null, "POST", params1, null);
		}
		
		/**
		 * 软POS缴费流程
		 * 1、自助缴费终端向银商业务前置发起银行卡扣款请求。
		 * 2、银商业务前置向银联POSP发起银行卡扣款交易。
		 * 3、银联POSP向银商业务前置返回银行卡扣款结果。
		 * 4、银商业务前置将银行卡扣款结果转发给自助缴费终端。
		 * 5、自助缴费终端向自助缴费终端综合管理平台发起移动记账请求。
		 * 6、自助缴费终端综合管理平台向BOSS发起移动记账请求终端。
		 * 7、BOSS向自助缴费终端综合管理平台返回记账结果。
		 * 8、自助缴费终端综合管理平台将记账结果返回给自助终端
		 */
		function unionCardPay()
		{
			var posResCode = '';
			
			//提交扣款请求
			try
			{
				var ret ;
				
				// 关闭密码键盘
				ret = CloseCom()
				if (ret != "0" || ret != 0)
				{
					setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
					return;
				}
				
				// 银联扣款
				// posnum 跟踪号;batchnum 批次号； bankcardID银行卡号；money 缴费金额(分)
				var payReturnStr = Pay(getValue("posNum"),getValue("batchNumBeforeKoukuan"),getValue("cardnumber"),"<%=fenMoney %>");
	
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
				
				posResCode = payReturnStr.substring(0,2);
				
				setValue("posResCode",posResCode);
				
				if (payReturnStr.substring(0,2) != "00")
				{
					setException("银联卡缴费失败，请取走您的银联卡。原因："+payReturnStr);
					return;
				}
				
				// 扣款正常取值
				var resultstr = payReturnStr.substring(0,34);
				var printcontext = payReturnStr.substring(34,payReturnStr.length);
				
				setValue("printcontext",printcontext);
				
				//测试用
				/**
				var resultstr = "006222021602004724078A000000001234";
				//var printcontext = "302370150210713~123456789012345~消费交易~6222021602004724078~000001~100001~000000000003~20101019 155445~000003~000000002000";
				
				// 湖北银联 授权码与凭证号 返回空值
				var printcontext = "898420148145268~00167923~消费交易~6225887840088682~000725~~171633071883~20110817171633~~1";
				**/	
				
				// 扣款成功 定长34 成功00
				if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
				{
				    
				    // 打开键盘串口、设置明文模式
					OpenCom();
					SetWorkMode(0);
					
					var printcontexts = printcontext.split('~');							
					
					//设置扣款金额
					setValue("tMoney",printcontexts[9]);
					
					//交费状态 默认2：初始状态 0：缴费成功 1：缴费失败
					setValue("payStatus","0");
					
					//更新扣款日志
					updateUnionCardLog(printcontexts);
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
					setException("银联卡扣款成功，但是交费失败。请取走您的银联卡。");
				}
				else
				{
					setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
				}
			}
		}
		
		/**
		 * 银联扣款前记录日志
		 */
		function addUnionCardLog()
		{
			try
			{
				//更新日志类型：add
				setValue("errorType","add");
				
				//生产用。一天之内跟踪号不可以相同，一次签到之内的批次号是相同的
				var result = GetPosBatchNum();
				
				var dataArray = result.split("$");
				
				//获取跟踪号、批次号失败
				if (dataArray[0] != "0")
				{
					setException("获取跟踪号和批次号失败，无法使用银联卡进行充值。请取走您的银联卡。");
					
					return;
				}
				
				//获取跟踪号
				setValue("posNum",dataArray[1]);
				
				//获取批次号成功
				setValue("batchNumBeforeKoukuan",dataArray[2]);
				
				//流水号
				setValue("terminalSeq",getValue("batchNumBeforeKoukuan") + getValue("posNum"));
				
				//扣款之前记录日志
				var url = "${sessionScope.basePath }reissueCard/addUnionCardLog.action";
				
				var params = "&recFee=" + <s:property value='recFee' />;
				params = params + "&cardChargeLogVO.cardnumber=" + getValue("cardnumber");
				//流水号
				params = params	+ "&cardChargeLogVO.terminalSeq=" + getValue("terminalSeq");
				//跟踪号
				params = params	+ "&cardChargeLogVO.posNum=" + getValue("posNum");
				//批次号
				params = params + "&cardChargeLogVO.batchNumBeforeKoukuan=" + getValue("batchNumBeforeKoukuan");
				//补卡日志id
				params = params + "&cardBusiLogPO.oid=" + getValue("oid");
			
				var loader = new net.ContentLoader(url, netload = function () {
						var resXml = this.req.responseText;
						var dataArray = resXml.split("~~");
						//记录日志成功
						if (dataArray[0] == "0") 
						{
							//本次交费对应的日志已经添加到表中，之后的操作只是更新此条记录，而不是再新增
							setValue("errorType","update");
							var oid = dataArray[1].replace(/[\r\n]/g, "");
							
							//设置交费日志id，用于更新交费日志
							setValue("chargeLogOID",oid);
							
							//银联扣款
							unionCardPay();
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
					setException("银联卡扣款成功，但是交费失败。请取走您的银联卡。");
				}
				else
				{
					setException("银联卡扣款失败，不能继续进行充值操作。请取走您的银联卡。");
				}
			}				
		}
		
		/**
		 * 银联交费提交流程
		 * 1、新增银联扣款日志。
		 * 2、银联扣款。
		 * 3、更新银联扣款日志。
		 * 4、写卡
		 * 5、若第一次写卡失败，则进行二次写卡
		 * 6、提交
		 */
		function doSub() 
		{		
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				// 投币后，不能返回
            	document.getElementById("backBtn").disabled = true;
				
				//扣款钱记录扣款日志
				addUnionCardLog();
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
				//发生异常，增加日志
				document.getElementById("errorType").value = "add";
				
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
	<body scroll="no" onload="doLoad();">
		<form name="actForm" method="post">
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />		
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYUNIONCARD%>">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='1'>
			<input type="hidden" id="errorType" name="errorType" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<%-- 费用合计 --%>
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
			
			<%--------------补卡日志信息---------------%>
			<%--空白卡卡号--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
   	 		<%--受理日志id，--%>
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
			<%-- 写卡状态  默认2：初始状态 0：写卡成功 1：写卡失败--%>
			<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
			<%-- 交费状态  默认2：初始状态 0：缴费成功 1：缴费失败--%>
			<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
			
			<%--是否需要回收卡，1 需要 --%>
			<input type="hidden" id="callBackCard" name="callBackCard" value="" />
			
			<%---银联打印信息--%>
			<input type="hidden" id="printcontext" name="printcontext" value="">
			
			<%----------------交费日志信息---------------%>
			<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" value="">
			<input type="hidden" id="posResCode" name="cardChargeLogVO.posResCode" value=''>
			<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value='<s:property value="cardChargeLogVO.cardnumber" />'>
			<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
			<%--跟踪号--%>
			<input type="hidden" id="posNum" name="cardChargeLogVO.posNum" value=''>
			<%--批次号--%>
			<input type="hidden" id="batchNumBeforeKoukuan" name="cardChargeLogVO.batchNumBeforeKoukuan" value=''>
			
			<%--------------simInfoPO信息--------------%>
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
			<input type="hidden" id="smsp" name="simInfoPO.smsp" value="<s:property value="simInfoPO.smsp" />" />
			<input type="hidden" id="pin1" name="simInfoPO.pin1" value="<s:property value="simInfoPO.pin1" />" />
			<input type="hidden" id="pin2" name="simInfoPO.pin2" value="<s:property value="simInfoPO.pin2" />" />
			<input type="hidden" id="puk1" name="simInfoPO.puk1" value="<s:property value="simInfoPO.puk1" />" />
			<input type="hidden" id="puk2" name="simInfoPO.puk2" value="<s:property value="simInfoPO.puk2" />" />
			<input type="hidden" id="eki" name="simInfoPO.eki" value="<s:property value="simInfoPO.eki" />" />
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>补卡</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
    					<a href="javascript:void(0)">3. 费用确认</a>
    					<a href="javascript:void(0)">4. 选择支付方式</a>
    					<a href="javascript:void(0)" class="on">5. 补卡缴费</a>
    					<a href="javascript:void(0)">6. 取卡打印小票</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：${sessionScope.CustomerSimpInfo.servNumber}</span></p>
    						<p class="fs22 fwb pl40 lh30">补卡费用：<span class="yellow fs22"><s:property value="recFee" /></span> 元</p>
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
                  	<p class="tips_txt">正在处理，请稍候......</p>
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
