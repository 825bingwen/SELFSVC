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
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<!-- 手机号码 -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- 手机号码 -->
            <s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
            
            <!-- 省份编码 -->
            <s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
            
            <!-- 客户名称 -->
            <s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
            
            <!-- 缴费金额 -->
            <s:hidden id="yingjiaoFee" name="chargeLogVO.yingjiaoFee"></s:hidden>
            
            <!-- 支付方式 1:银联卡，4：现金 -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
            
            <!-- 错误信息 -->
            <s:hidden id="errormessage" name="errormessage"></s:hidden>
            
            <%-- 是否打印小票  1-不打印，0-打印 --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
						
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<%@ include file="/jsp/customize/sd/feeCharge/common/chargeHeader.jsp"%>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servNumber" /></span></p>
    						<div class="blank10"></div>
    						<div class="line"></div> 
    						<div class="blank10"></div>
    						<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择您要充值交费的金额：</span></p>
    						<div class="blank25"></div>        
        					<div class="blank25"></div>
        					<ul class="money_list clearfix">
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectMoney('20');return false;">20元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectMoney('50');return false;">50元</a></li>
         	 					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectMoney('100');return false;">100元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectMoney('300');return false;">300元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="selectMoney('500');return false;">500元</a></li>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="inputMoney();return false;">其他金额</a></li>
        					</ul>	
    					</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
<script type="text/javascript">
document.getElementById("highLight3").className = "on";

//防止页面重复提交
var submitFlag = 0;

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

// 选择支付金额
function selectMoney(money) 
{
    if (submitFlag == 0)
    {   
        submitFlag = 1;
        
        document.getElementById("yingjiaoFee").value = money;
    
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }nonlocalChargeSD/dutyInfo.action";
        document.actform.submit();
    }           
}

// 输入缴费金额
function inputMoney()
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }nonlocalChargeSD/toInputMoney.action";
        document.actform.submit();
    }           
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
                
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/qryfeeChargeAccount.action";
        document.forms[0].submit();
    }
}
</script>