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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>		
		<script language="javascript">
			//防止重复提交
			var submitFlag = 0;
			
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
				//确认
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
			}
			
			function goback(menuid)
            {
                if (submitFlag == 0)
                {
                    document.getElementById("curMenuId").value = menuid;
                
                    document.dutyInfoForm.target = "_self";
                    
                    // 多人缴费走新的读取银联卡action
                    if ("1" == '<s:property value="morePhoneFlag"/>')
                    {
                        return;
                    }
                    else
                    {
	                    submitFlag = 1;
                        document.dutyInfoForm.action = "${sessionScope.basePath }charge/cardCharge.action";
                    }
                    document.dutyInfoForm.submit();
                }
            }
                        
            function doSub() 
            {
                if (submitFlag == 0) 
                {
                    submitFlag = 1; //提交标记
                    
                    // 多人缴费走新的读取银联卡action
                    if ("1" == '<s:property value="morePhoneFlag"/>')
                    {
                       document.dutyInfoForm.action = "${sessionScope.basePath }charge/readCardMorePhone.action";
                    }
                    else
                    {
                       document.dutyInfoForm.action = "${sessionScope.basePath }charge/toReadCard.action";
                    }
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
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/goCardError.action";
					document.dutyInfoForm.submit();
				}			
			}	
			
			function doLoad()
			{
			    if ("1" != '<s:property value="morePhoneFlag"/>')
			    {
					var serverNumber = "<s:property value='servnumber' />";
					if (serverNumber == null || serverNumber == "")
		            {            
		            	setException("对不起，用户信息获取失败，请返回重新操作。");
		          		return;
		            }
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
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCARD %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			<input type="hidden" id="chargeType" name="chargeType" value="<s:property value='chargeType' />">
			<input type="hidden" id="custName" name="custName" value="<s:property value='custName' />">
			
			<!-- add begin jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
            <!-- 话费连缴标志，1：话费连缴 -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- 话费连缴总费用 -->
            <s:hidden name="totalFee" id="totalFee"></s:hidden>
            <!-- add end jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
            
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
						
						<!-- modify begin jWX216858 2015-4-21 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
						<s:if test="1 == morePhoneFlag">
	    					<a href="javascript:void(0)" class="on">3. 免责声明</a>
	    					<a href="javascript:void(0)">4. 插入银联卡</a> 
	    					<a href="javascript:void(0)">5. 输入密码</a>
	    					<a href="javascript:void(0)">6. 完成</a>
						</s:if>
						<s:else>
						    <a href="javascript:void(0)">3. 选择金额</a> 
                            <a href="javascript:void(0)" class="on">4. 免责声明</a>
                            <a href="javascript:void(0)">5. 插入银联卡</a> 
                            <a href="javascript:void(0)">6. 输入密码</a>
                            <a href="javascript:void(0)">7. 完成</a>
						</s:else>
						<!-- modify end jWX216858 2015-4-21 OR_SD_201501_1063 自助终端支撑连缴功能优化 -->
						
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    					<!--modify begin l00190940 2011/10/25 R003C11L10n01 去掉10086字样-->
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
    					<!--modify end l00190940 2011/10/25 R003C11L10n01 去掉10086字样-->
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
