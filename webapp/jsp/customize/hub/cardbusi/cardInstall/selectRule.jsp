<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content=""/>
		<meta http-equiv="description" content=""/>
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
			var submitFlag = 0;
			
			//82、220 返回		
			document.onkeydown = pwdKeyboardDown;
			
			//监听键盘按下事件
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
			
			//监听键盘释放事件
			function pwdKeyboardUp(e) 
			{
				var key = GetKeyCode(e);
				
				//返回
				if (key == 82 || key == 220) 
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;		
				}
				
				if(key == 49)
				{
					doSub(4);
				}
				else if(key == 50)
				{
					doSub(2);
				}	
				else if(key == 51)
				{
					doSub(3);
				}
			}
			
			// 提交
			function doSub(selTelRule) 
			{			
				//防止重复操作
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.selTelRule.value = selTelRule;
			  		document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cardInstall/inputTelnumByRule.action";
					document.actform.submit();
				}
			}
			
			// 返回
			function goback(curmenu) 
			{
				//防止重复操作
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = curmenu;
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }login/backForward.action";
					document.actform.submit();
				}
			}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actform" method="post">
			<%-- 身份证信息 --%>
			<%-- 姓名 --%>
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<%-- 性别 --%>
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<%-- 身份证号码 --%>
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<%-- 证件地址 --%>
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			<%-- 开始时间 --%>
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
			<%-- 结束时间 --%>
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
			<%-- 卡信息 --%>
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
			<%-- 照片信息 --%>
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
			
			<%-- 套餐信息 --%>
			<%-- 模板ID --%>
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
			<%-- 模板名称 --%>
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
			<%-- 产品ID --%>
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
			<%-- 产品名称 --%>
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
			<%-- 品牌 --%>
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
			<%-- 套餐月费 --%>
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
			<%-- 预存费用 --%>
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
			
			<%-- 选号信息 --%>
			<%-- 地市 --%>
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<%-- 组织机构ID --%>
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<%-- 地市名称 --%>
			<input type="hidden" id="regionName" name="regionName" value="<s:property value='regionName'/>" />
			<%-- 选号规则  2：按前缀查询 3：按后缀查询 4：取随机号码--%>
			<input type="hidden" id="selTelRule" name="selTelRule" value=""/>
			
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
	   					<a href="javascript:void(0)" class="on">4. 号码选择</a>
	   					<a href="javascript:void(0)">5. 费用确认</a>
	   					<a href="javascript:void(0)">6. 开户缴费</a>
	   					<a href="javascript:void(0)">7. 取卡打印小票</a>
					</div>
				</div>
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40">
						<p class=" tit_info"><span class="bg"></span>地市：<s:property value='regionName'/></p>
						<div class="blank10"></div>
						<div class="line"></div>
						<div class="blank10"></div>
						<p class="tit_arrow fs22"><span class="bg"></span>请选择号码查询方式：</p>
						<div class="blank20"></div>
	       				<div class="blank5"></div>
						
						<ul class="money_list_clearfix">
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(4); return false;">
									随机(按1键)
								</a>
							</li>
							<li class="pt10"></li>	
							<li class="tel_selectrule tc">
								<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
									前七位(按2键)
								</a>
							</li>
							<li class="pt10"></li>							
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(3); return false;">
									后四位(按3键)
								</a>
							</li>
						</ul>
					</div>	
				</div>	
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
