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
		
		function go_HomePage()
		{
			obj = document.getElementById("backWaitingFlag");
	
			if (obj != null && obj != "undefined" && document.getElementById("backWaitingFlag").value == "1")
			{
				openRecWaitLoading_NX("recWaitLoading");
			}
			
			window.location.href = "${sessionScope.basePath }login/goHomePage.action?timeoutFlag=0";
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				 <div class="b966">
		            <div class=" blank60"></div>
		            <div class="pic_ok fl ml160"></div>
		            <div class=" fl">
		            <p class=" fs28  pl20" style="padding-top:4px;"><span class="yellow">业务更改成功！</span></p>

		            <p class=" fs18 pt30 pl20">更改后，月消费为每月<s:property value='totalHidden'/>元。</p>
		           
		            <div class=" blank60"></div>
		            <div class=" blank60"></div>
		            <div class="p20">
		                <a href="javascript:void(0);" class=" bt4 ml20"  style="font-size:20px;" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';" onclick="go_HomePage();return false;">返回首页</a>
		            </div>
		            
		            </div>
		            <div class=" blank30 clear"></div>
		            <div class="blank10 m36" style="border-bottom:1px dashed #0082b3"></div>
		             <div class=" blank10"></div>
		            <div class="ml100">
		            <p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
		            <table width="600" border="0">
						<tr>
							<td width="600" >
								 <p class="fs18 ml20 lh30"><%=additionalInfo %></p>
							</td>
						</tr>
					</table>
		            </div>
		         </div>	
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
<script type="text/javascript">
	function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.getElementById("curMenuId").value = menuid;
				//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597
				
				// 产品快速发布标志  
				var quickPubFlag = "<s:property value='quickPubFlag' />";
				
				// 产品开通（通过产品开通菜单进入，或者从热门业务进入）
				if('1' == quickPubFlag)
				{
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
					document.actform.submit();
				}
				else
				{						
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
					document.forms[0].submit();
				}
				//modify end CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
			}
		}
</script>
</html>
