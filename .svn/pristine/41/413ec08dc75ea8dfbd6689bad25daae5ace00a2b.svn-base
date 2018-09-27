<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
	// 银联卡缴费读卡等待时间(秒)
	String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

	// 取得应收费用并转换为分
	String yuanMoney = (String) request.getAttribute("recFee");
	String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>读卡页面</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
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
			
			// 返回操作
			function goback(menuid)
			{
				setException("缴费操作被取消");
			}
			
			// 读卡等待时间
			var limitTime = <%=limitTime %>;
			
			//等待读卡的句柄
			var waitingToken;
			
			// 提交标志
			var submitFlag = 0;
			
			// 出现异常
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
				    // 提交标志置为1
					submitFlag = 1;
					
					// 清除定时任务
					clearInterval(waitingToken);
					
					// 显示错误信息
					document.getElementById("errormessage").value = errorMsg;
					
					try
					{
						// 关闭读卡设备
						CloseComByCard();
						
						// 打开密码键盘并设置明文模式
						OpenCom();
						SetWorkMode(0)
					}
					catch (e)
					{}
					
					//异常处理，只要出现了异常就要记录日志  
					document.readCardForm.action = "${sessionScope.basePath }reissueCard/goChargeError.action";
					document.readCardForm.submit();
				}			
			}
			
			//----------------------------------扣款及缴费-------------------------------------------------
			//银联卡扣款
			function pay()
			{
				//清除定时任务
				clearInterval(waitingToken);
				
				// 关闭读卡设备
				try
				{
				
					//平台系统检测到用户已插入银联卡后，需调用此接口关闭读卡设备，释放串口。
					var ret = CloseComByCard();
					
					if (ret != 0 && ret != "0")
					{
						setException("关闭读卡设备失败，无法使用银联卡进行缴费，请联系营业人员取走您的银联卡。");
								
						return;
					}
				}
				catch(e)
				{
					setException("关闭读卡设备失败，无法使用银联卡进行缴费，请联系营业人员取走您的银联卡。");
								
					return;
				}
				
				// 发起银联卡扣款请求
				var resultstr = "";
				try
				{
					// 交易请求报文
					// 交易代码(2)+交易金额(12)+POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)
					// + 原交易日期(8)+卡片类型(1)+自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
					var strin = '01';// 交易代码(2)
					strin = strin + formatStr('<%=fenMoney %>','left','0',12);// 交易金额(12)
					strin = strin + formatStr('','right',' ',55);// POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)+ 原交易日期(8)
					strin = strin + 'H';// 卡片类型(1)
					strin = strin + formatStr('','right',' ',240);// 自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
					// add begin cKF76106 2013/04/01 R003C13L03n01 OR_SD_201303_1171
					//add begin sWX219697 2014-6-17 10:27:25 OR_huawei_201404_1118
					//附加信息，101：个人交费，102：代理商交费，105：补卡交费
					strin = strin + formatStr("105"+document.getElementById("chargeLogOID").value,'right',' ',100);// 补卡交费附加信息105
					//add end sWX219697 2014-6-17 10:27:25 OR_huawei_201404_1118
					// add end cKF76106 2013/04/01 R003C13L03n01 OR_SD_201303_1171
					
					// 生产用	
					if (true) // true:生产 false:测试
					{	
						resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
					}
					// 测试用	
					else
					{
						// 返回码(6)+	返回码含义(40)+POS流水号(6)+授权码(6)+批次号(6)+卡号(19)+有效期(4)+银行号(2)+银行检索参考号(12)+终端号(15)+商户号(15)
						// 交易金额(12)+加密后的密码或定单号(16)+分期付款信息(74)+发卡行代码(8)+银联主机主易日期(8)+银联主机主易时间(6)
//						resultstr = '000001';// 返回码(6)
//						resultstr = resultstr + formatStr('error', 'right', ' ', 40);// 返回码含义(40)
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
						resultstr = resultstr + "000000004000";// 交易金额(12)
						resultstr = resultstr + "****************";//加密后的密码或定单号(16)
						resultstr = resultstr + formatStr('','right',' ',74);// 分期付款信息(74)
						resultstr = resultstr + formatStr('','right',' ',8);// 发卡行代码(8)
						resultstr = resultstr + '20120101';// 银联主机主易日期(8)
						resultstr = resultstr + '101010';// 银联主机主易时间(6)
					}
				}
				catch (e)
				{}
				
				try
				{
	   				// 打开密码键盘，设置明文模式
					OpenCom();
					SetWorkMode(0);
				}
				catch(e)
				{}
				
				try
				{
					// 调用出现问题，没有取到返回值
					if (resultstr == "")
					{
						setException("银联扣款失败，无法缴费，请联系营业人员取走您的银联卡。");
								
						return;
					}
					// 扣款成功 定长255 成功000000
					else if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
					{
						// 流水号_交易参考号
						setValue("terminalSeq", trim(resultstr.substring(resultstr.length-166, resultstr.length-154)));
						setValue("tMoney", trim(resultstr.substring(resultstr.length-124, resultstr.length-112)));
						setValue("printcontext", trim(resultstr.substring(resultstr.length-209)));
							
						// 交费日志记录信息
						setValue("unionpayuser", trim(resultstr.substring(resultstr.length-139,resultstr.length-124)));// 商户编码
						setValue("unionpaycode", trim(resultstr.substring(resultstr.length-154,resultstr.length-139)));// POS机编号
						setValue("busitype", "消费");// 交易类型
						setValue("cardnumber", trim(resultstr.substring(resultstr.length-191,resultstr.length-172)));// 卡号
						setValue("posnum", trim(resultstr.substring(resultstr.length-209,resultstr.length-203)));// POS流水号
						setValue("batchnum", trim(resultstr.substring(resultstr.length-197,resultstr.length-191)));// 批次号
						setValue("authorizationcode", trim(resultstr.substring(resultstr.length-203,resultstr.length-197)));// 授权码
						setValue("businessreferencenum", trim(resultstr.substring(resultstr.length-166,resultstr.length-154)));// 交易参考号
						setValue("unionpaytime", trim(resultstr.substring(resultstr.length-14,resultstr.length)));// 扣款时间
						setValue("vouchernum", "");// 凭证号
						setValue("unionpayfee", trim(resultstr.substring(resultstr.length-124,resultstr.length-112)));// 扣款金额
						
						//交费状态 默认2：初始状态 0：缴费成功 1：缴费失败
						setValue("payStatus","0");
						
						//写卡
						writeAndSub();
					}
					//扣款失败
					else
					{
						document.getElementById("unionRetCode").value = resultstr.substring(0,6);
						
						setException(trim(resultstr.substring(6)) + "。请取走您的银联卡。");
						
						return;
					}
				}
				catch (e)
				{
					if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
					{
						setException("银联扣款成功，但是写卡失败。请取走您的银联卡。");
					}
					else
					{
						setException("银联扣款失败。请取走您的银联卡。");
					}
				}				
			}
			
			//-----------------------------------------循环读卡-------------------------------------------------
			// 获得读卡标志
			function getReadCardStatus() 
			{
				// 读卡时间结束
				if (limitTime <= 0 && submitFlag == 0)
				{           	
					setException("读卡超时，无法使用银联卡进行缴费，请选用其它方式。");
	       			return;
				}
				
				try 
				{
					// 0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
					var ret = getCardPosition();// 获取卡位置，以判断用户是否已插入银联卡

					// 读卡失败
					if (ret == "-1")
					{
						setException("读卡失败，无法使用银联卡进行缴费，请选用其它方式。");
						return;
					}
					// 卡在读卡器内
					else if (ret == "0" || ret == 0)
					{	
			   	 		pay();
					}
					// 尚未读取到卡信息
					else
					{
						// 读卡时长一共limitTime秒
						limitTime = limitTime - 1;
				
						// 设定界面显示
						document.getElementById("tTime").value = limitTime;
					}
				}
				catch (e) 
				{
					setException("读卡失败，无法使用银联卡进行缴费，请选用其它方式。");
					return;
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
					setException("读卡器读卡失败，无法使用银联卡进行缴费，请选用其它方式。");
				}
			}
			
			//----------------------------------页面初始化-------------------------------------------------
			//页面加载时执行
			function bodyLoad() 
			{
				// 关闭密码键盘
				try
				{
					// 调用银联扣款控件前关闭设备所用串口并创建监听程序
					var ret = CloseCom();
					if (ret != 0)
					{
						setException("关闭密码键盘失败，无法使用银联卡进行缴费，请选用其它方式。");
					
						return;
					}
				}
				catch(e)
				{
					setException("关闭密码键盘失败，无法使用银联卡进行缴费，请选用其它方式。");

					return;
				}
				
				// 打开读卡设备
				try 
				{
					var ret = OpenComByCard();
					if (ret != "0" && ret != 0) 
					{
						setException("打开读卡设备失败，无法使用银联卡进行缴费，请选用其它方式。");
	                    return;
					}
					else
					{		
						//初始化成功，就需要关闭读卡器。禁用所有按钮，包括页面上部和下部。
		   				closeStatus = 1;
						
	                	document.getElementById("homeBtn").disabled = true;
			            document.getElementById("backBtn").disabled = true;
	                	document.getElementById("quitBtn").disabled = true;
							
	                	// 启动定时任务
						startclock();
					}
				}
				catch (ex) 
				{
					setException("打开读卡设备失败，无法使用银联卡进行缴费，请选用其它方式。");
	                return;
				}
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
			
			// 去除字符串空格
			function trim(str) 
			{
				while (str.charAt(str.length - 1) == " ") 
				{
					str = str.substring(0, str.length - 1);
				}
				
				while (str.charAt(0) == " ") 
				{
					str = str.substring(1, str.length);
				}
				
				return str;
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
					setException("空白卡卡号必须是20位预置空卡！");
					return;
				}
				
				setValue("blankCard",blankCardSN);
						
				//3、校验空白卡资源
				var resXml = checkBlankCard();
				      
				var resArray = resXml.split('~~');
				
				//空白卡资源暂选    
				if (resArray[0] == 0 || resArray[0] == "0")
				{
					setValue("iccid",resArray[1]);
					setValue("imsi",resArray[2]);
					setValue("issueData",resArray[3]);
					setValue("formNum",resArray[4]);
				}
				else
				{	
					setException(resArray[1]);
					return;
				}
				
				//4、写卡
				var writeData = writeCard(resXml.substring(3),getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
				
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
				
				//iccid~~imsi~~issueData~~formNum
				var simInfo = getValue("iccid")+"~~"+getValue("imsi")+"~~"+getValue("issueData")+"~~"+getValue("formNum");
		
				//写卡:入参：写卡加密数据，空白卡卡号，流水号（用于写卡结果校验，与申请写卡时的入参一致）   
				var writeData = writeCard(simInfo,getValue("blankCard"),basePath,"${sessionScope.CustomerSimpInfo.servNumber}");
		
				// 写卡失败 进行二次写卡
				if(writeData.indexOf("11~") != -1)
				{
					// 再次写卡
					writeData = writeAgain(basePath);
				}
				
				// 写卡失败 跳转异常页面
				if(writeData.indexOf("1~") != -1)
				{
					setException(writeData.split("~")[1]);
					return;
				}
				
				//写卡成功
				setValue("writeCardStatus","0");
			
				//提交缴费请求
				document.readCardForm.action = "${sessionScope.basePath}reissueCard/reissueCommit.action";
				document.readCardForm.submit();
			}
		</script>
	</head>
	<body scroll="no">
		<form name="readCardForm" method="post" target="_self">
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint' />" />
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAYBYUNIONCARD %>">
			<input type="hidden" id="tMoney" name="tMoney" value=''>
			<input type="hidden" id="needReturnCard" name="needReturnCard" value='0'>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<!-- 费用合计 -->
			<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
			
			<!--------------补卡日志信息 -------------->
			<%-- 空白卡卡号--%>
			<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard"
				value="<s:property value='cardBusiLogPO.blankCard' />" />
   	 		<%--受理日志id，用于更新受理日志--%>
   			<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
   			<%-- 写卡状态  默认2：初始状态 0：写卡成功 1：写卡失败--%>
			<input type="hidden" id="writeCardStatus" name="cardBusiLogPO.writeCardStatus" value="" />
			<%-- 交费状态  默认2：初始状态 0：缴费成功 1：缴费失败--%>
			<input type="hidden" id="payStatus" name="cardBusiLogPO.payStatus" value="" />
    
    		<!--------------交费日志信息 -------------->
    		<%-- 移动话费缴纳日志唯一标识 --%>
    		<input type="hidden" id="chargeLogOID" name="cardChargeLogVO.chargeLogOID" 
    			value="<s:property value = 'cardChargeLogVO.chargeLogOID' />">
    		<%-- 商户编号（授卡方标识） --%>
			<input type="hidden" id="unionpayuser" name="cardChargeLogVO.unionpayuser" value=''>
			<%-- 终端编号 --%>
			<input type="hidden" id="unionpaycode" name="cardChargeLogVO.unionpaycode" value=''>
			<%-- 交易类型 --%>
			<input type="hidden" id="busitype" name="cardChargeLogVO.busiType" value=''>
			<%-- 银行卡号 --%>
    		<input type="hidden" id="cardnumber" name="cardChargeLogVO.cardnumber" value=''>
    		<%--跟踪号--%>
    		<input type="hidden" id="posnum" name="cardChargeLogVO.posNum" value=''>
    		<%-- 终端批次号 --%>
    		<input type="hidden" id="batchnum" name="cardChargeLogVO.batchnum" value=''>
    		<%-- 授权码 --%>
    		<input type="hidden" id="authorizationcode" name="cardChargeLogVO.authorizationcode" value=''>
    		<%-- 交易参考号 --%>
    		<input type="hidden" id="businessreferencenum" name="cardChargeLogVO.businessreferencenum" value=''>
    		<%-- 银联实际扣款时间 --%>
    		<input type="hidden" id="unionpaytime" name="cardChargeLogVO.unionpaytime" value=''>
    		<%-- 凭证号 --%>
    		<input type="hidden" id="vouchernum" name="cardChargeLogVO.vouchernum" value=''>
    		<%-- 银联实际扣款金额，单位（分） --%>
    		<input type="hidden" id="unionpayfee" name="cardChargeLogVO.unionpayfee" value=''>
    		<%-- 交易流水号，即终端流水号 --%>
    		<input type="hidden" id="terminalSeq" name="cardChargeLogVO.terminalSeq" value=''>
    		<%-- 银联卡返回编码:缴费渠道+行号 --%>
    		<input type="hidden" id="unionRetCode" name="cardChargeLogVO.posResCode" value=''>
    		
    		<!--------------simInfoPO信息 -------------->
			<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
			<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
			<input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
			<input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
			
			<%--是否需要回收卡，1 需要 --%>
			<input type="hidden" id="callBackCard" name="callBackCard" value="" />
			
			<%---银联打印信息--%>
			<input type="hidden" id="printcontext" name="printcontext" value="">
			
			<%--客户信息 --%>
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>自助补卡</h2>
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
      					<div class="p40 pr0">
        					<p class="tit_info"><span class="bg"></span>请插入您的银联卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
        					<p class="tit_info"><span>读卡时长共</span><span class="yellow"><%=limitTime %></span>秒，当前剩余<input type="text"  id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />秒</p>
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
		closeStatus = 1;
        bodyLoad();        
	</script>
</html>
