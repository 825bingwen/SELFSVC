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
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
				//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597   
			}
		}
		
		// 继续办理原业务
		function contineRec()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
								
				openRecWaitLoading('recWaitLoading');
				document.actform.target="_self";
				document.actform.action = "${sessionScope.basePath}/recommendProduct/contineRec.action";
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<input type="hidden" id="feeChargeFlag" name="feeChargeFlag" value="<s:property  value='feeChargeFlag'/>"/>
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property  value='servnumber'/>"/>	
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok">
							    <s:if test="successMessage == null">
								    业务办理成功!
								</s:if>
								<s:else>
								    <s:property value="successMessage"/>
								</s:else>
							</span>
						</dd>
						
					</dl>
					<div class="btn_box tc">
						<span class=" mr10 inline_block ">
						<a title="继续办理业务" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';contineRec();">
						     	继续办理
						</a>
						</span>
					</div>
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
