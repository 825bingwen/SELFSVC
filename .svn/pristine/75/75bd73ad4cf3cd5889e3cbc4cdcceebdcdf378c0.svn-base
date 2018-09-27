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
			}			
		}
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }hubprodinstall/ckTelSimCard.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />">
			<input type="hidden" id="receptionFee" name="receptionFee" value='<s:property value="receptionFee" />'>
			<input type="hidden" id="prodtempletid" name="prodtempletid" value="<s:property value="prodtempletid" />"/>
			<input type="hidden" id="mainprodname" name="mainprodname" value='<s:property value="mainprodname" />'/>
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>开户入网流程：</h2>
      					<div class="blank10"></div>
      					<a title="入网协议确认" href="#">1. 入网协议确认</a>
      					<a title="身份证信息认证" href="#">2. 身份证信息记取</a>
      					<a title="产品选择" href="#">3. 产品选择</a>  
      					<a title="号码选择" href="#">4. 号码选择</a> 
      					<a title="费用确认" href="#">5. 费用确认</a>
      					<a title="开户缴费" href="#"  class="on">6. 开户缴费</a>
      					<a title="取卡打印发票" href="#">7. 取卡打印发票</a>
          				<a title="完成" href="#">8. 完成</a>
					</div>
				</div>	
				<div class="b712">
						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40">
      						<p class="tit_info"><span class="bg"></span>请选择充值交费的支付方式</p>
      						<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
          						<s:iterator value="usableTypes" id="type" status="st">
          							<s:if test="#st.index == 0">          								
          								<li class="mr70">          									
          							</s:if>
          							<s:else>
          								<li>          									
          							</s:else>
          								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
          							</li>
          						</s:iterator>
        					</ul>
      					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
