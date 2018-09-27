<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCardEquip = termInfo.getTermspecial().substring(4, 5);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
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
    					<div class="blank15"></div>
    					<div class="p40">
    						<p class=" tit_info1">
    							尊敬的客户：<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团山东有限公司的客户。在您申办业务前，请认真阅读以下条款：<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;本终端业务受理系统的所有权和运作权，以及所受理具体业务的经营权归中国移动通信集团山东有限公司所有，您必须完全同意所有服务条款，才可以通过本终端办理各类业务。
			      				移动电话号码和服务密码是您重要的个人资料，可以作为办理业务的凭证。凡使用本终端凭服务密码办理的一切业务，均视为您本人亲自办理，并由您本人负责。请您务必注意服务密码的保密。
			      				您必须提供准确的资料，我公司将根据您提供的资料进行核对，如有不符，系统将不予受理业务。
			      				目前本终端受理以下免办理手续费用的项目，包括业务办理，缴纳话费，打印清单，补打发票等业务。<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意]继续受理业务。如果您不同意以上条款，请按[首页]或[退出]，本系统将自动退至主界面。
    						</p>
    						<div class=" tr"> 
    							<a class=" btagree" style="margin-top:370px;_margin-top:0px;" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">同意</a> 
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script language="javascript">
document.getElementById("highLight4").className = "on";

//防止重复提交
var submitFlag = 0;

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
    //确认
    else if (key == 13 || key == 89 || key == 221)
    {
        doSub();
    }           
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
    
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/cardCharge.action";
        document.dutyInfoForm.submit();
    }
}
      
// 提交      
function doSub() 
{
    if (submitFlag == 0) 
    {
        submitFlag = 1; //提交标记
        
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/toReadCard.action";
        document.dutyInfoForm.target = "_self";
        document.dutyInfoForm.submit();
    }
}

//出现异常
function setException(errorMsg) 
{           
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("errormessage").value = errorMsg;

        //异常处理，只要出现了异常就要记录日志  
        document.dutyInfoForm.target = "_self";
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/chargeError.action";
        document.dutyInfoForm.submit();
    }           
}   

function doLoad()
{
    var serverNumber = '<s:property value="servNumber" />';
    if (serverNumber == null || serverNumber == "")
    {         
        setException("对不起，用户信息获取失败，请返回重新操作。");
    }
    
    <%
    if (!"1".equals(isCardEquip))
    {
    %>
        setException("对不起，该终端机暂不支持银联卡充值，请选用其它方式。");
        return;
    <%
    }
    %>
}   
</script>
