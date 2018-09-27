<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>

<%
String errorMessage = (String) request.getAttribute("errormessage");
String exception_message = (String) request.getAttribute("exception.message");
String backStep = (String) request.getAttribute("backStep");

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

// 修改自动交费设置的弹出框提示信息
String autoChargeTips = (String) PublicCache.getInstance().getCachedData(Constants.SH_AUTOCHARGEHB_TIPS);

if(null == autoChargeTips || "" == autoChargeTips)
{
	autoChargeTips = "您确定要修改自动交费的设置吗？";
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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
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
	
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	<%	
	} 
	%>
}
function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }bindBankCard/unsignHeBao.action";
		document.actform.submit();
	}		
}


</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<!-- 最低余额值 -->
			<s:hidden id="trigamt" name="bankCardInfoPO.trigamt" value="1000" />
			<!-- 自动划扣金额 -->
			<s:hidden id="drawamt" name="bankCardInfoPO.drawamt" value="10" />
			<!-- 银行编码 -->
			<s:hidden id="bankId" name="bankCardInfoPO.bankId" />
			<!-- 快捷支付协议号 -->
			<s:hidden id="agrNo" name="bindBankCardPO.AGRNO" />
			<!-- 交易流水号 -->
			<s:hidden id="tradeNo" name="bindBankCardPO.appFlowCode" />
			<!-- 验证码类型 -->
			<s:hidden id="smsType" name="smsType" value="3" />
			<!-- 自动交费设置类型 查询0,新增：1,删除：2,修改：3 -->
			<s:hidden id="oprType" name="oprType" value="3" />
			<!-- 证件号码类型 -->
			<input type="hidden" id="idCardType" name="bindBankCardPO.idCardType" value="00"/>
			<!-- 证件号码 -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />"/>
			<!-- 银行卡代码 -->
			<s:hidden name="bindBankCardPO.bankAbbr" id="bankAbbr" />
			<!-- 姓名 -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- 卡片种类 0:借记卡 1:贷记卡 -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="<s:property value='bindBankCardPO.bankCardType' />"/>
			<!-- 获取登录页面输入的银行卡卡号 -->
			<input type="hidden" id="cardNo" name="cardNo" value="<s:property value='cardNo' />" />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main ycz" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="b966 hidden">
					<div class="blank30"></div>
					<div class=" p40">
						<p class="tit_info" align="left"><span class="bg"></span>易充值签约管理</p>

						<!--<div class="blank20"></div>-->
						<p class="fs18 mt20 clearfix">
							<span class="yellow fs22 fl lh48 pt10">扣款账户：</span>
							<input type="text" id="numBoardText" class="text2 fl" readOnly="true" value="银行卡尾号 <s:property value='bindBankCardPO.bankCardNum' />" />
							<a href="javascript:void(0)" class="btn_104 ml20 fl mt5"
								onmousedown="this.className='btn_104_hover ml20 fl mt5'"
								onmouseup="this.className='btn_104 ml20 fl mt5';" onclick="unsign(); return false;" >取消绑定</a>
						</p>
						<p class="fs18 mt20">
							<span class="fs18 yellow_tips_1 pl62 ml50">更换扣款账户，需先取消，再重新绑定</span>
						</p>
						<div class="fs18 mt20">
							<span class="yellow fs22 fl lh48 mt40">自动交费：</span>
							<s:if test="1 == payTypeFlag">
								<div class="bg_gray fl" style="height: 130px;">
									<div class="phone_num_list selectInput_list fl ml10">
										<div class="fs20 textSelect clearfix ">
											<div class="text1 resultSelect fl" id="trigamt0" onClick="showSelectTrigamt('trigamt','0')">
												<s:property value='balanceList[0].dictname' />
											</div>
											<div class="textSelect_con" style="right: 20px;" id="balanceDiv" style="display: none">
												<s:iterator value="balanceList">
													<span id="trigamt<s:property value='dictid' />" onClick="showSelectTrigamt('trigamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
												</s:iterator>
											</div>
											<div class="blank10"></div>
											<div class="text1 resultSelect fl" id="drawamt0" onClick="showSelectDrawamt('trigamt','0')">
												<s:property value='chargeList[0].dictname' />
											</div>
											<div class="textSelect_con" style="right: 20px; top:120px;" id="chargeDiv" style="display: none">
												<s:iterator value="chargeList">
													<span id="drawamt<s:property value='dictid' />" onClick="showSelectDrawamt('drawamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
												</s:iterator>
											</div>
										</div>
									</div>
								</div>
								<a href="javascript:void(0)" id="kt_tips"
									class="btn_104 ml10 fl mt40 fs14" style="font-size: 18px;"
									onmousedown="this.className='btn_104_hover ml10 fl mt40 fs14'"
									onmouseup="this.className='btn_104 ml10 fl mt40 fs14'; openWindow_Auto('popup_confirmAuto'); return false;">修改</a>
							</s:if>
							<s:else>
								<div style="margin-top:75px;"><span style="font-size: 18px;">已开通</span></div>
							</s:else>
						</div>



					</div>
					
					<div class="i_tips ml20 fl">
						<p class="tit_arrow mt10">
							<span class=" bg"></span>温馨提示：
							<br />
							<s:if test="HBManageTips != '' ">
								<p class="tit_arrow_hide pl40">
									<s:property value = 'HBManageTips' escape = 'false'/>
								</p>
							</s:if>
							<s:else>
								<p class="tit_arrow_hide pl40">
									1、预付费客户余额到达或低于设定的"触发余额"后，系统参照设置的划扣金额自动从已绑定账户中扣款，为客户交费并短信通知客户
									<br />
									2、后付费客户，每月出账完成后，系统按客户的出账金额自动从已绑定账户中扣款，为客户交费，并短信通知客户
								</p>
							</s:else>
						</p>
					</div>
				</div>
	            
	            <!-- 设置自动充值 -->
	            <div class="openwin_tip div708w392h" id="popup_confirmAuto">
	                <div class="bg"></div>
	                <div class=" blank60"></div><div class=" blank60"></div>
	               
	                <div class="  blank10n"></div>
	                <p class="fs30 yellow pt30 tc pt30 pl20"><%=autoChargeTips %></p>
	                <div class=" blank10"></div>
	                <div class="tc">
	                <div class=" blank60"></div>
	                <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();" onclick="setAutoValue(); return false;">确定</a>
	                <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
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
		
		// modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
		<%
		if (null != backStep && !"".equals(backStep.trim()))
		{
		%>
			history.go(parseInt("<%=backStep %>"));
		<%	
		}
		else
		{
		%>
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
		<%
		}
		%>
		// modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 出错时返回上一页面
	}
}

	// 修改自动交费的弹出窗口
	openWindow_Auto = function(id)
	{
		divFlag = id;
		wiWindow = new OpenWindow("popup_confirmAuto", 708, 392);// 打开弹出窗口的配置
	}
	
	// 关闭弹出div时，清空divFlag信息
	function windowClose()
	{
		divFlag = "";
		wiWindow.close();
	}
	
	// 和包易充值解约
	function unsign()
	{
		document.actform.action = "${sessionScope.basePath }bindBankCard/sendHeBaoRandom.action";
		document.actform.submit();
	}
	
	//切换自动充值的最低触发余额
	function showSelectTrigamt(m,n)
	{
		document.getElementById('chargeDiv').style.display="none";
		if(n==0)
		{
			if(document.getElementById('balanceDiv').style.display=='')
		    {
		    	document.getElementById('balanceDiv').style.display="none";
		    }
		    else
		    {
		    	document.getElementById('balanceDiv').style.display="";
		    }
	    }
	    else
	    {
	    	document.getElementById('balanceDiv').style.display="none";
    	
	    	// 选择的id
	        var M = m + n;
	        var a = document.getElementById(M).innerHTML;
	        document.getElementById(m+'0').innerHTML=a;
	        document.getElementById("trigamt").value = n;
	    }
	}
	
	//切换自动充值的最低充值金额
	function showSelectDrawamt(m,n)
	{
		document.getElementById('balanceDiv').style.display="none";
		if(n==0)
		{
			if(document.getElementById('chargeDiv').style.display=='')
		    {
		    	document.getElementById('chargeDiv').style.display="none";
		    }
		    else
		    {
		    	document.getElementById('chargeDiv').style.display="";
		    }
	    }
	    else
	    {
	    	document.getElementById('chargeDiv').style.display="none";
    	
	    	// 选择的id
	        var M = m + n;
	        var a = document.getElementById(M).innerHTML;
	        document.getElementById(m+'0').innerHTML=a;
	        document.getElementById("drawamt").value = n;
	    }
	}
	
	// 和包易充值自动交费设置
	function setAutoValue()
	{
		document.actform.action = "${sessionScope.basePath }bindBankCard/setHeBaoAutoValue.action";
		document.actform.submit();
	}
</script>


</html>
