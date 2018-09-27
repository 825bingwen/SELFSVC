<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	
	//用户信息session
	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	String servnumber = null;

	if (null != customer )
	{
		servnumber = customer.getServNumber();
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
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
		// 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			return;			
		}
		if(key == 49)
		{
			//topGo('rec', 'reception/receptionFunc.action');
			goback('<s:property value='curMenuId' />');
			return;
		}
	<%
	}
	else
	{
	%>
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	<%
	}
	%>
	
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

	// add begin wWX217192 2014-07-09 OR_huawei_201406_338 山东_营业厅全业务流程优化-业务分流(自助终端)_非实名制补卡
	// 非实名制补卡客户信息校验成功后，设备自动打印凭条
	var pServNumber = "";
	
	var pOrgName = "<%=pOrgName%>";  //打印地点
	var pPrintDate = getDateTime();  //打印日期
	
	// 非实名制凭条打印功能
	if("<s:property value='curMenuId' />" == 'recNonRegister')
	{
		pServNumber = "<s:property value='telNumber' />";
		
		var accessValue = "";
		if("<s:property value='accessName' />" == "短信验证")
		{
			accessValue = "短信验证";
		}
		else if("<s:property value='accessName' />" == "服务密码")
		{
			accessValue = "服务密码";
		}
		else if("<s:property value='accessName' />" == "SIM卡")
		{
			accessValue = "SIM卡： " + "<s:property value='simCardNo' />";
		}
		else if("<s:property value='accessName' />" == "充值记录")
		{
			accessValue = "充值记录： " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />元"
		}
		else if("<s:property value='accessName' />" == "通话记录")
		{
			accessValue = "通话记录： " + "<s:property value='callNums' />";
		}
		
		var printcontext = "尊敬的用户，您好，您已完成手机号码非实名制客户补卡认证，验证方式为 "+ accessValue + 
			"，请当天持此凭条到前台办理非实名制客户补卡，过期凭条自动作废，如果前台补卡后产生客户投诉需要恢复的，我们将注销此笔业务或补卡给原机主；" + 
			"同时该号码30天内不能办理过户业务；如有异议请咨询营业员。  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"自动打印非实名制认证补卡凭条");
		
		doPrintNoRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	
	// 实名制5选2凭条打印功能
	if("<s:property value='curMenuId' />" == 'realNameReg')
	{
		pServNumber = "<s:property value='servnumber' />";
		
		var accessValue = "";
		if("<s:property value='selectMethod' />" == "selectRandomPwd")
		{
			accessValue = "短信验证";
		}
		// modify begin wWX217192 2014-08-20 OR_huawei_201408_964 自助终端新增实名登记认证（自助终端5选2认证方式)优化
		else
		{
			accessValue = "SIM卡： " + "<s:property value='cardNum' />";
		}
		
		if("<s:property value='selectMethod2' />" == "selectServerPwd")
		{
			accessValue = accessValue + "和服务密码";
		}
		// modify end wWX217192 2014-08-20 OR_huawei_201408_964 自助终端新增实名登记认证（自助终端5选2认证方式)优化
		else if("<s:property value='selectMethod2' />" == "selectRechargeRecord")
		{
			accessValue = accessValue + "和充值记录： " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />元"
		}
		else if("<s:property value='selectMethod2' />" == "selectCallRecord")
		{
			accessValue = accessValue + "和通话记录： " + "<s:property value='calledNum' />";
		}
		
		var printcontext = "尊敬的用户，您好，您已完成手机号码实名制登记认证，验证方式为 " + accessValue +
			"，请当天持此凭条到前台办理实名制登记，过期凭条自动作废，如果前台过户后产生客户投诉需要恢复原资料的，我们将恢复原资料；" + 
			"同时该号码30天内不能办理过户业务；如有异议请咨询营业员。  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"自动打印实名制认证凭条");
		
		doPrintRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	// add end wWX217192 2014-07-09 OR_huawei_201406_338 山东_营业厅全业务流程优化-业务分流(自助终端)_实名制补卡
	
	// add begin wWX217192 2014-08-07 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)
	// 实名制5选3凭条打印功能
	if("<s:property value='curMenuId' />" == 'realNameReg53')
	{
		pServNumber = "<s:property value='servnumber' />";

		var accessValue = "";
		if("<s:property value='selectMethod' />" == "selectRandomPwd")
		{
			accessValue = "短信验证";
		}
		else if("<s:property value='selectMethod' />" == "selectServerPwd")
		{
			accessValue = "服务密码";
		}
		else if("<s:property value='selectMethod' />" == "selectSimNo")
		{
			accessValue = "SIM卡： " + "<s:property value='cardNum' />"
		}
		
		accessValue = accessValue + "、充值记录： " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />元； " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />元" + "、通话记录： " + "<s:property value='calledNum' />";
		
		var printcontext = "尊敬的用户，您好，您已完成手机号码实名制登记认证，验证方式为 " + accessValue +
			"，请当天持此凭条到前台办理实名制登记，过期凭条自动作废，如果前台过户后产生客户投诉需要恢复原资料的，我们将恢复原资料；" + 
			"同时该号码30天内不能办理过户业务；如有异议请咨询营业员。  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"自动打印实名制认证凭条");
		
		doPrintRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	// add end wWX217192 2014-08-07 OR_SD_201408_166 自助终端新增实名登记认证（自助终端5选3认证方式)

//add begin sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_自助终端营销功能优化
//山东，向智能营销平台反馈办理结果	
function feedbackByStatus()
{
	<%
	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(pageProvince))
	{
	%>	
		//菜单id
		var offerMenu = "<s:property value='curMenuId' />";
		
		//手机号码
		var servnumber = <%=servnumber%>;
		
		//参数status=1，表示业务订购成功
		var postStr ="offerMenu="+offerMenu+"&servnumber="+servnumber+"&status=1";  
		 
		var receptionUrl = "${sessionScope.basePath}ISSS/feedbackByStatus.action";
		
		//向智能营销平台反馈办理结果
		var loader11 = new net.ContentLoader(receptionUrl, netload = function () 
		{
		}, null, "POST", postStr, null);
	
	<%
	}
	%>
}
//add end sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_自助终端营销功能优化

</script>
</head>
	<body scroll="no" onload="feedbackByStatus()">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix reception_ok_pt60">
						<% 
			            if(Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
			            {
						%> 
							<br><br><br><br>
						<%
						}
						%>
						<dd class="tc" style="">
							<span class="transcact_ok">
							    <s:if test="successMessage == null">
								    业务办理成功!
								</s:if>
								<s:else>
								    <s:property value="successMessage"/>
								</s:else>
							</span>
							<p class="btn_box clearfix"> 
								<% 
					            if(Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
					            {
								%>
									
									<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';topGo('rec', 'reception/receptionFunc_hub.action'); return false;">更多业务</a>
								<%
								}
								else
								{
								%>
									<%
									if ("1".equals(keyFlag))
									{
									%>
										<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';goback('<s:property value='curMenuId' />'); return false;">更多业务(按1键)</a>
									<%
									}
									else
									{
									%>
										<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';topGo('rec', 'reception/receptionFunc.action'); return false;">更多业务</a>
									<%
									}
									%>
								<%
								}
								%> 
							</p>
						</dd>
					</dl>
					
					<div class="line"></div>
					<% 
		            if(!Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
		            {
					%>
					<!-- 添加推荐业务开始 -->
					  <div class="recommend_box">
					    <div class="recommend_title clearfix"><span>相关业务推荐：</span></div>
					    <ul class="recommend_list clearfix">
						    <%
						    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
						    {
						    	MenuInfoPO menu = null;
						    	int j = 0; int k = 0;
						    	for (int i = 0; i < titleTotalMenus.size(); i++)
						    	{
						    		menu = (MenuInfoPO) titleTotalMenus.get(i);
						    		
						    		 if ("rec".equalsIgnoreCase(menu.getParentid()) 
						                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
						                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))&& k<4) 
						            {
						            	k++;
										%>
											<li>
												<a class="relative reception_recommanded_a" id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><img src='${sessionScope.basePath}<%=menu.getImgpath2() %>' alt='<%=menu.getMenuname() %>' /></a>
												<p><a href="javascript:void(0);" class="reception_recommanded_p_a" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><%=menu.getMenuname() %></a></p>
											</li>
										<%
						            }
						    	}
						    }
				      		%>
					    </ul>
					  </div>
					<!-- 添加推荐业务结束 -->
					<%
					}
					%>
					
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
