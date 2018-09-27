<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // 省份信息
    String provinceSD = Constants.PROOPERORGID_SD;

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>		
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript">
		
//是否支持读取二代身份证信息标志,0不支持,1:支持
var is2GIDFlag = <%=termSpecial.charAt(8)%>;	

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

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) 
{
	var key = GetKeyCode(e);
	
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
	// 清除
	else if (key == 77)
	{
		changValue(-2);
		return;
	}	
	//更正
	else if (key == 8 || key == 32 || key == 66)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = backString(etarget.value);
		}
		if (etarget.name == 'passwd' && etarget.value == '' )
		{
			MoveLast(document.getElementById('passwd'));
		}
	}
	
	var passwd = document.forms[0].passwd.value;
	var pwdConfirm = document.forms[0].pwdConfirm.value;		

	if ((key == 8 || key == 32 || key == 66)
	 		&& pangu_getStrLen(trim(pwdConfirm)) == 0 
	 		&&  pangu_getStrLen(trim(passwd)) == 6) 
	{
		document.forms[0].passwd.focus();
		
		changObj(document.forms[0].passwd, 1);
		
		return true;
	}
	
		if (pangu_getStrLen(trim(passwd)) == 6 && document.forms[0].pwdConfirm.value == "") 
		{
			document.forms[0].pwdConfirm.focus();
			
			changObj(document.forms[0].pwdConfirm, 2);
			
			return true;
		}
		
	return true;
}

// 去除空格
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

function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
	r.select();
}

//密码更改提交
function doSub()
{
	var passwd = document.getElementById('passwd').value;
	var pwdConfirm = document.getElementById('pwdConfirm').value;
	// 异常标识
	var errmsg = "";
	
	//判断两次输入密码是否相同
	if(passwd != pwdConfirm )
	{
		errmsg = "两次输入的密码必须相同并且不为空，请输入6位服务密码！";
	}
	//页面提示错误信息
	else if( passwd == "" || pwdConfirm == "" )
	{
		errmsg = "您好，密码不能为空，请输入6位服务密码！";
	}
	
	else if( passwd.length != 6 || pwdConfirm.length != 6 )
	{
		errmsg = "您好，密码长度应为六位，请输入正确的服务密码！";
	}
	
	if( errmsg == "")
	{
	   if (submitFlag == 0)
		{
			submitFlag = 1;
			openRecWaitLoading();
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}cardInstall/feeConfirm.action";
			document.actform.submit();
		}
	}
	else
	{
		alertRedErrorMsg(errmsg);
	}
}

function goback(curmenu) 
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}			
}

// 页面载入时加载身份证读卡器获取用户身份证信息
function bodyLoad()
{
    document.getElementById("passwd").focus();
}

</script>
</head>
<body scroll="no" onload="bodyLoad();">
<form name="actform" method="post">
	<input type="hidden" id="errormessage" name="errormessage" value="<s:property value='errormessage'/>" />
    <!-- 身份证信息 -->
    <!-- 姓名 -->
    <input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
    <!-- 性别 -->
    <input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
    <!-- 身份证号码 -->
    <input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
    <!-- 证件地址 -->
    <input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
    <!-- 开始时间 -->
    <input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
    <!-- 结束时间 -->
    <input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
    <!-- 卡信息 -->
    <input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
    <!-- 照片信息 -->
    <input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
    
    <!-- 套餐信息 -->
    <!-- 模板ID -->
    <input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
    <!-- 模板名称 -->
    <input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
    <!-- 产品ID -->
    <input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
    <!-- 产品名称 -->
    <input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
    <!-- 品牌 -->
    <input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
    <!-- 套餐月费 -->
    <input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
    <!-- 预存费用 -->
    <input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
    
    <!-- 选号信息 -->
    <!-- 地市 -->
    <input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
    <!-- 组织机构ID -->
    <input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
    <!-- 地市名称 -->
    <input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
    <!-- 选号规则 -->
    <input type="hidden" id="selTelRule" name="selTelRule" value="<s:property value='selTelRule'/>"/>
    <!-- 前缀 -->
    <input type="hidden" id="telPrefix" name="telPrefix" value="<s:property value='telPrefix'/>"/>
    <!-- 后缀 -->
    <input type="hidden" id="telSuffix" name="telSuffix" value="<s:property value='tel_suffix'/>"/>
    <!--空白卡序列号 -->
    <input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
    <!--手机号码 -->
    <input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
                
    <!--------------simInfoPO信息 -------------->
    <input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
    <input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
    <input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
    <input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
    <input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value='simInfoPO.oldiccid' />"/>
    
    <%-- 写卡所需信息 --%>
    <input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
    <%-- 是否打印小票  1-不打印，0-打印 --%>
    <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
    <%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
    <input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>

	<%@ include file="/titleinc.jsp"%>

	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<div class="b257 ">
				<div class="bg bg257"></div>
				<h2>在线开户</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a>
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)" class="on">5. 设置服务密码</a>
				<a href="javascript:void(0)">6. 费用确认</a>
				<a href="javascript:void(0)">7. 开户缴费</a>
				<a href="javascript:void(0)">8. 取卡打印发票</a>
			</div>
		</div>
		
		<div class="b712">
			<div class="bg bg712" id="errMsg"></div>
			<div class="p40 pr0">
				<div class="blank20"></div>
				<div class="blank20"></div>
				<p class=" tit_info"><span class="bg"></span>请输入6位服务密码</p>
	            <div class="line w625"></div>
				<div class="blank10"></div>
				<div class="custom_money pl30 fl">
					<span class="pl40 fs20 fl lh48">服务密码：</span>
					<input type="password" name="passwd" id="passwd" value="" maxlength="6" class="text1" onclick="changObj(this, 1);" onfocus="MoveLast(event);" />
				</div>
				<div class="blank10"></div>
				<div class="custom_money pl30 fl">
					<span class="pl40 fs20 fl lh48">确认密码：</span>
					<input type="password" name="pwdConfirm" id="pwdConfirm" value=""  maxlength="6" class="text1" onclick="changObj(this, 2);" onfocus="MoveLast(event);" />
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
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
	<script type="text/javascript">
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var type = 1;
	var numBoardText = document.getElementById('passwd');
	
	//待确认密码
	var numBoardTextConfirm = document.getElementById('pwdConfirm');
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
					
			if (pangu_getStrLen(trim(numBoardText.value)) == 6 ) 
			{
				document.forms[0].pwdConfirm.focus();
				
				changObj(document.forms[0].pwdConfirm, 2);
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
		else if (type == 1 && numBoardText.value.length < 6)
		{			
			numBoardText.value += v;																		
		}
		else if (type == 2 && numBoardText.value.length < 6 && !isNaN(v))
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
