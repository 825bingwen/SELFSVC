<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String successMessage = (String) request.getAttribute("successMessage");
if (successMessage == null)
{
	successMessage = "";
}
 %>
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
document.onkeyup = pwdKeyboardUp;

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

function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
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
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

function chgBalance()
{
	// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading_NX("recWaitLoading");
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	
    	document.actform.target = "_self";
    	document.actform.action = "${sessionScope.basePath}baseService/chgBalanceUrgeSelect.action?operType=2"; 
    	document.actform.submit();
    }
}

function cancel()
{
	openWindow("openWin1");
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 tc">
                    <div class=" blank60"></div>
                    <div class=" blank60"></div>
            
                    <p class="fs22w pt30 pl20">您当前的余额提醒值为： <span class="yellow fs30"><%=successMessage %>元</span></p>
                    <p class="blank10"></p>
                    <div class=" blank60"></div>
                    <div class="tc">
                         <a title="变更余额提醒值" href="#" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';chgBalance();">变更</a>
                         <a title="取消余额提醒" href="#" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';cancel()">取消提醒</a>
                    </div>
                </div>
                <!--弹出窗-->
					<div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<p class="fs28 lh2" style="padding-left:142px;" id="winText" name="winText">
							<span>您确认取消余额提醒吗？</span>
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
						    <div class=" blank30 "></div>
	    					<a title="确认取消余额提醒" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();gotoPrintSuccess();">确认</a> 
	    					<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
	    				</div>
					</div>
					
					<script type="text/javascript">
					openWindow = function(id){
						wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子					
					}
					
					function gotoPrintSuccess()
					{
						openRecWaitLoading();
						
						setTimeout(
							function(){
								wiWindow.close();
								
								document.actform.target = "_self";
								document.actform.action = "${sessionScope.basePath}baseService/chgBalanceUrgeDef.action?operType=0";
								document.actform.submit();					
							},
						500);
					}
					</script>
					<!--弹出窗结束-->	
					    </div>			
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
