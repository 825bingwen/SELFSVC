<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value="0">			
			<s:hidden name="cardChargeLogVO.servnumber"/>
			<s:hidden name="cardChargeLogVO.provinceCode"/>
				
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
    					<a href="javascript:void(0)" class="on">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 免责声明</a>
    					<a href="javascript:void(0)">5. 插入储蓄卡</a>
    					<p class="recharge_tips">插入您的储蓄卡。业务办理结束后请<br />注意取回储蓄卡。</p>
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)">7. 核对信息</a>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="cardChargeLogVO.servnumber" /></span></p>
    						<div class="blank10"></div>
    						<div class="line"></div> 
    						<div class="blank10"></div>
    						<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择您要充值交费的金额：</span></p>
    						<div class="blank25"></div>        
        					<div class="blank25"></div>
        					<ul class="money_list clearfix">
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayType('20');return false;">20元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayType('50');return false;">50元</a></li>
         	 					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayType('100');return false;">100元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayType('300');return false;">300元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayType('500');return false;">500元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="inputMoney();return false;">其他金额</a></li>
        					</ul>	
    					</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<script type="text/javascript">
	//防止页面重复提交
	var submitFlag = 0;
	
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
		
		//返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
		}			
	}
		
	function selectPayType(money) 
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("yingjiaoFee").value = money;
		
			document.actform.action = "${sessionScope.basePath }nonlocalChargeHUB/dutyInfo.action";
			document.actform.submit();
		}			
	}
	
	function inputMoney()
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.actform.action = "${sessionScope.basePath }nonlocalChargeHUB/toInputMoney.action";
			document.actform.submit();
		}			
	}
	
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = menuid;
					
			document.forms[0].action = "${sessionScope.basePath }nonlocalChargeHUB/qryfeeChargeAccount.action";
			document.forms[0].submit();
		}
	}
	</script>
</html>
