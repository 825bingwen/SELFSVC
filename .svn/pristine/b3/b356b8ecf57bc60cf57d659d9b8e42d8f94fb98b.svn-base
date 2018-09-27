<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // 省份信息
    String provinceSD = Constants.PROOPERORGID_SD;

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();
	
	// 获取硬件开关是否打开
	// add begin qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示
	String hardwareSwitch = (String) PublicCache.getInstance().getCachedData(Constants.SH_HARDWARE_ISOPEN);
	// add end qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>		
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js?ver=${jsVersion }"></script>
<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
</head>
<body scroll="no" onload="bodyLoad();" >
<form name="actform" method="post">
	<input type="hidden" id="errormessage" name="errormessage" value="<s:property value='errormessage'/>" />
	
	<%-- 错误信息 --%>
	<input type="hidden" id="errormessage" name="errormessage" value='' />
	
	<%-- 校验现金设备标识 --%>
	<input type="hidden" id="checkCashFlag" name="checkCashFlag" value='' />
	
	<%-- 校验银联卡设备标识 --%>
	<input type="hidden" id="checkCardFlag" name="checkCardFlag" value='' />	
	
	<%@ include file="/titleinc.jsp"%>

	<div class="main" id="main" onLoad="bodyLoad();">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<%@ include file="/jsp/valuecard/valueCardHeader.jsp" %>
		</div>
		
		<div class="b712">
			<div class="bg bg712" id="errMsg"></div>
			<div class="p40 pr0">
				<div class="blank20"></div>
				<div class="custom_money pl30 fl">
					<span class="pl40 fs20 fl lh48">手机号码：</span>
					<input type="text" name="telnum" id="telnum" value="${sessionScope.CustomerSimpInfo.servNumber }" maxlength="11" class="text1" onclick="changObj(this, 1);" onfocus="MoveLast(event);" />
				</div>
				<div class="blank20"></div>
  				<div class=" mt10 pl20 clearfix" style="padding-left:60px;margin-top:35px;">
   					<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
   						<div class=" numbox clearfix">
       						<div class="clearfix">
       							<a href="javascript:void(0)">1</a>
       							<a href="javascript:void(0)">2</a>
       							<a href="javascript:void(0)">3</a> 
       							<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
     						</div>
       						<div class="clearfix"> 
       							<a href="javascript:void(0)">4</a>
       							<a href="javascript:void(0)">5</a>
       							<a href="javascript:void(0)">6</a>
								<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
       						</div>
       						<div class="nleft"> 
       							<a href="javascript:void(0)">7</a>
       							<a href="javascript:void(0)">8</a>
       							<a href="javascript:void(0)">9</a> 
       							<a href="javascript:void(0)">x</a>
       							<a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
       						</div>
       						<div class="nright"> 
       							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;"></a> 
       						</div>
       						<div class="blank10"></div>
   						</div>
   					</div>
   				</div>
			</div>
		</div>	
		<div class="i_tips ml20 fl" style="margin-top:-170px">
	    	<p class="tit_arrow mt10">
	   		<span class=" bg"></span>温馨提示：
	           <p class="pl40" style="font-size:14px; position:relative; width:635px;">
	           	<s:if test="null != tipsList && tipsList.size > 0">
	           		<s:iterator value="tipsList">
	           			<%-- html标签转义--%>
	           			<s:property value="description" escape="false"/>
	           		</s:iterator>
	           	</s:if>
	           	<s:else>
	            	1、手机号码为必填信息，请您务必正确填写。<br />
	
					2、请您认真核对输入的手机号码信息，有价卡购买成功后，有价卡密码会以短信的形式发送到此手机号码上。<br />
	           	</s:else>
			</p>
	   </div>	
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
<div class="popup_confirm" id="chkHardware_confirm">
   <div class="bg"></div>
   <div class="tips_title">
       提示：
   </div>
   <div class="tips_body">
       <p id="chkHardwareMsg" style="font-size: 19px; color: red;"></p>
   </div>
   <div class="btn_box tc mt20">
       <span class=" mr10 inline_block "><a href="#"
           class="btn_bg_146" onmousedown="this.className='key_down'"
           onmouseup="this.className='btn_bg_146';wiWindow.close();document.getElementById('telnum').focus();">继续充值</a>
       </span>
       <span class=" inline_block "><a class="btn_bg_146" href="#"
           onmousedown="this.className='key_down'"
           onmouseup="this.className='btn_bg_146';wiWindow.close();goback('feeCharge');">返回</a>
       </span>
    </div>
</div>
	<script type="text/javascript">
	// 提交标志
	var submitFlag = 0;
	
	// 设置左侧页面的高亮显示
	document.getElementById("highLight1").className = "on";
	
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
			goback("<s:property value = 'curMenuId'/>");
			return;
		}
	}
	
	function pangu_getStrLen(s) 
	{
		var count = 0;
		var lenByte = s.length;
		for (i = 0; i < lenByte; i++) 
		{
			if (s.charCodeAt(i) > 256) 
			{
				count = count + 2;
			} 
			else 
			{
				count = count + 1;
			}
		}
		
		return count;
	}
		
	// 购买有价卡的手机号码
	function doSub()
	{
		var telNum = document.getElementById("telnum");
		
		// 判断手机号码是否符合条件，不符合的话弹出提示框，符合的话页面跳转至选择有价卡页面
		if("" == telNum.value ||　pangu_getStrLen(telNum.value) < 11)
		{
			alertRedErrorMsg("请输入11位的手机号码！");
		}
		else
		{
			document.actform.action = "${sessionScope.basePath}valueCard/chooseValueCard.action";
			document.actform.submit();
		}
	}
	
	function goback(curmenu) 
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.actform.action="${sessionScope.basePath }login/backForward.action";
			document.actform.submit();				
		}			
	}
	
	// 页面载入时加载身份证读卡器获取用户身份证信息
	function bodyLoad()
	{
	    document.getElementById("telnum").focus();
	    
	    <%-- modify begin qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>
		// 硬件开关关闭
		if ('<%=hardwareSwitch%>' == '0')
		{
			return;
		}
	    <%-- modify begin qWX279398 2015-7-30 OR_SD_201504_1108自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>

	    // 提示信息
		var showMessage = "";
		
		// 校验现金识币器 =====start========
		var chkCashMsg = "";
		
		// 校验是否支持现金缴费标志,0不支持,1:支持
	 	if (window.parent.isCashEquip == 1)
	 	{
	 		chkCashMsg = initCashPayEquip();
	 	}
	 	else
	 	{
	 		chkCashMsg = "该终端机不支持现金识币器";
	 	}
		
		if (chkCashMsg != "")
		{
			chkCashMsg = "由于本终端的现金识币器故障，请使用银联卡进行有价卡购买！";
			setValue("checkCashFlag", "casherror");
			showMessage = showMessage + chkCashMsg;
		}
		// 校验现金识币器 =====end========
		
		// 校验银联读卡器 =====start========
	    var chkCardMsg = "";
	
	    // 校验是否支持银行卡缴费标志,0不支持,1:支持
	    if (window.parent.isUnionPay == 1)
	    {
	    	chkCardMsg = initUnionCardPayEquip();
	    }
	    else
	    {
	    	chkCardMsg = "该终端机不支持银联读卡器";
	    }
	    
	    if (chkCardMsg != "")
	    {
	    	chkCardMsg = "由于本终端的银联读卡器故障，请使用现金进行有价卡购买！";
	    	setValue("checkCardFlag", "carderror");
	    	showMessage = showMessage + chkCardMsg;
	    }
	 	// 校验银联读卡器 =====end========
	 		
	 	if (chkCashMsg != "" && chkCardMsg != "")
	    {
	    	goToError("尊敬的客户，由于本终端的现金识币器和银联读卡器故障，暂时无法进行有价卡购买！");
	    	return;
	    }
	    
	 	// 校验加密键盘 =====start========
	 	var chkKeyBoardMsg = "";
	 		
	 	// 校验是否支持加密键盘标志,0不支持,1:支持
	 	if (window.parent.isKeyBoard == 1)
	 	{
	 		chkKeyBoardMsg = initKeyBoard();
	 	}
	 	else
	 	{
	 		chkKeyBoardMsg = "该终端机不支持密码键盘";
	 	}
	 	
	 	if (chkKeyBoardMsg != "")
	 	{
	 		chkKeyBoardMsg = "由于本终端的密码键盘故障，请使用现金进行有价卡购买！";
	 		setValue("checkCardFlag", "carderror");
	 		showMessage = showMessage + chkKeyBoardMsg;
	 	}
	 	// 校验加密键盘 =====end========
	 		
	 	if (chkCashMsg != "" && chkKeyBoardMsg != "")
	    {
	    	goToError("尊敬的客户，由于本终端的现金识币器和密码键盘故障，暂时无法进行有价卡购买！");
	    	return;
	    }
	 	
	 	// 校验票据打印机 =====start========
	 	var chkPrintMsg = "";
	    
	 	// 校验是否支持打印票据标志,0不支持,1:支持
		if (window.parent.isPrintFlag == 1)
		{
			chkPrintMsg = initListPrinter();
		}
		else
		{
			chkPrintMsg = "该终端机不支持小票打印机";
		}
	 	
	 	if (chkPrintMsg != "")
	 	{
	 		chkPrintMsg = "由于本终端的小票打印机故障或缺纸，暂时无法打印小票！";
	 		showMessage = showMessage + chkPrintMsg;
	 	}
	 	// 校验票据打印机 =====end========
	 	
	    // 校验硬件是否正常并提示异常信息
	 	if (showMessage != "")
	 	{
	 		openConfirmMessage("尊敬的客户，" + showMessage);
	 	}
	}
	
	// 转到错误页面
	function goToError(errorMsg) 
	{			
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			//弹出等待框
			openRecWaitLoading();
			
			// 记录错误信息
			setValue("errormessage", errorMsg);
	
			//异常处理，只要出现了异常就要记录日志  
			document.actform.action = "${sessionScope.basePath }charge/goToError.action";
			document.actform.submit();
		}
	}
	
	// 弹出确认框
	function openConfirmMessage(content)
	{
		document.getElementById('chkHardwareMsg').innerHTML = content;
		wiWindow = new OpenWindow("chkHardware_confirm",708,392);
	}
		
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var type = 1;
	var numBoardText = document.getElementById('telnum');
	
	for (i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
		}
		// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
		if (numBoardBtns[i].getAttribute("name") == 'functionkey')
		{
			continue;
		}  
	
		numBoardBtns[i].onmousedown = function()
		{						 
			this.className = 'on';
		}
		
		numBoardBtns[i].onmouseup = function()
		{					  				
			changValue(0, this.innerHTML);
					
			this.className = '';
			
			if (pangu_getStrLen(trim(numBoardText.value)) == 11 ) 
			{
				return false;
			}	
		}
	}
	
	function changObj(o, t)
	{
		type = t;
		
		document.getElementById("errMsg").innerHTML = "";
		o.focus();
		
		numBoardText = o;
	}	
	
	function changValue(t, v)
	{
		if (t == -1)
		{
			numBoardText.value = numBoardText.value.slice(0, -1);
		}
		else if(t == -2)
		{
			numBoardText.value = "";
		}
		else if (type == 1 && !isNaN(v) && numBoardText.value.length < 11)
		{			
			numBoardText.value += v;																		
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}
			
	</script>
</body>
</html>
