<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
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
			
			<%-- 支付方式 1:银联卡 4：现金--%>
			<s:hidden id="payType" name="chargeLogVO.payType" value="1"></s:hidden>	
				
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
    					<a href="javascript:void(0)">5. 插入银联卡</a> 
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)">7. 核对信息</a>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="chargeLogVO.servnumber" /></span></p>
    						<div class="blank10"></div>
    						<div class="line"></div> 
    						<div class="blank10"></div>
    						<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择您要充值交费的金额：</span></p>
    						<div class="blank25"></div>        
        					<div class="blank25"></div>
        					<ul class="money_list clearfix">
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayAmount('20');return false;">20元(1键)</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayAmount('50');return false;">50元(2键)</a></li>
         	 					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayAmount('100');return false;">100元(3键)</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayAmount('300');return false;">300元(4键)</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectPayAmount('500');return false;">500元(5键)</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="inputMoney();return false;">其他金额(6键)</a></li>
        					</ul>	
    					</div>
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
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
			if (KeyCode == 49)// 20元
			{
				selectPayAmount('20');
			}
			else if (KeyCode == 50)// 50元
			{
				selectPayAmount('50');
			}
			else if (KeyCode == 51)// 100元
			{
				selectPayAmount('100');
			}
			else if (KeyCode == 52)// 300元
			{
				selectPayAmount('300');
			}
			else if (KeyCode == 53)// 500元
			{
				selectPayAmount('500');
			}
			else if (KeyCode == 54)// 其他金额
			{
				inputMoney();
			}
			
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
		
		// 选择交费金额
		function selectPayAmount(money) 
		{
			if (submitFlag == 0)
			{	
				submitFlag = 1;
				
				// 等待框
				openRecWaitLoading_NX("recWaitLoading");
				
				writeDetailLog("<%=chargeLogDetail %>", "选择交费金额：" + money);
				
				document.getElementById("payAmount").value = money;
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }nonlocalCharge/dutyInfo.action";
				document.actform.submit();
			}			
		}
		
		// 选择输入交费金额
		function inputMoney()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openRecWaitLoading_NX("recWaitLoading");
				
				writeDetailLog("<%=chargeLogDetail %>", "选择其他交费金额");
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }nonlocalCharge/toInputMoney.action";
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>", 
						"<s:property value='chargeLogVO.servnumber' />退出跨省异地充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/qryPayAmount.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>
