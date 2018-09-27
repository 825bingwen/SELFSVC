<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		//防止页面重复提交
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
			}			
		}
			
		function selectPayType() 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/selectFeeChargeType.action";
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />退出充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		
		//提交交费方式，flag为0时需提示异地缴费信息，为1时不需要提示，直接提交
		function doSub(menuid, url,flag)
		{
			
			//若为省内跨区交费，弹出提示框 regionFlag 0:跨区交费
			<%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>
			if ('<s:property value="regionFlag"/>' == "0" && flag == "0")
			{
				document.getElementById("typeUrl").value = url;
				openRegionConfirm("chargeConfirm");
				return;
			}
			
			//用户点击继续充值，跳转之前选择的充值方式
			if (flag == "1")
			{
				url = document.getElementById("typeUrl").value;
			}
			<%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />选择交费方式：" + menuid);
				
				<%--modify begin g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
				if (url == "charge/cardCharge.action")
				{
					// 银联卡交费，判断此时间段内是否可用
					var url1 = "${sessionScope.basePath}charge/checkTime.action";
					
					var loader = new net.ContentLoader(url1, netload = function () {
						var resXml1 = this.req.responseText;
						
						if (resXml1 == "1" || resXml1 == 1)
						{
							document.actform.target = "_self";
							document.actform.action = "${sessionScope.basePath}" + url;
							document.actform.submit();
						}
						else
						{
							submitFlag = 0;
							
							alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
							
							return;
						}
					}, null, "POST", null, null);
				}
				else
				{
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				<%--modify end g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
			}			
		}
		
	   //跨区交费提示框	
	   <%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>	
	   openRegionConfirm = function(id)
	   {
	   		wiWindow = new OpenWindow(id,708,392);
	   }
	   <%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>
	   
		<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>	
		// 页面加载
		function doLoad()
		{
			//modify by sWX219697 2015-8-6 19:46:20 修改页面报缺少对象的js问题
			try
			{
				// 现金识币器故障，不显示现金缴费
				if ("<s:property value="#parameters.checkCashFlag"/>" == "casherror")
				{
					if(document.getElementById("cashcharge"))
					{
						document.getElementById("cashcharge").style.display = "none";
					}
				}
				
				// 银联读卡器或密码键盘故障，不显示银联卡缴费
				if ("<s:property value="#parameters.checkCardFlag"/>" == "carderror")
				{
					if(document.getElementById("cardcharge"))
					{
						document.getElementById("cardcharge").style.display = "none";
					}
					
				}
			}
			catch(e){}

		}
		<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>	
		</script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actform" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />"/>
			<input type="hidden" id="custName" name="custName" value="<s:property value='custName' />"/>
			<!-- 跳转url -->
			<%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>
			<input type="hidden" id="typeUrl" name="typeUrl" value=""/>
			<%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_山东_支撑跨区缴费--%>	
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)" class="on">2. 选择支付方式</a>
    					<p class="recharge_tips">查看您的话费信息，并提交充值交费金额进入支付。</p>
    					<a href="javascript:void(0)">3. 选择金额</a> 
    					<a href="javascript:void(0)">4. 支付</a> 
    					<a href="javascript:void(0)">5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servnumber" /></span></p>
    						<s:if test="yingjiaoFee == null">
    							<p class="fs22 fwb pl40 lh30">话费余额：<span class="yellow fs22"><s:property value="balance" /></span> 元</p>
    						</s:if>
    						<s:else>    						
    							<p class="fs22 fwb pl40 lh30">应交话费：<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> 元</p>
    						</s:else>
    						<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank10"></div>
      						<p class="tit_arrow fs22"><span class="bg"></span>选择支付方式：</p>
							<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
          						<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>	
          						<s:iterator value="usableTypes" id="type" status="st">
          							<s:if test="#st.index == 0">          								
          								<li class="mr70">          									
          							</s:if>
          							<s:else>
          								<li>          									
          							</s:else>
          							<s:if test="!canPayWithCash && #type.menuid == 'cashcharge'">
          								<a href="javascript:void(0);" id="<s:property value="%{#type.menuid}"/>"><img src='${sessionScope.basePath}<s:property value="#type.imgpath2" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:if>
          							<s:else>
          								<a href="javascript:void(0);" id="<s:property value="%{#type.menuid}"/>" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />','0'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:else>          									
          							</li>
          						</s:iterator>
          						<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_山东_自助终端缴费后的‘异常’提示信息拿到缴费前就进行提示--%>	
        					</ul>								
    					</div>
					</div>
				</div>
			</div>
		    <%-- 跨区交费提示框 --%>
            <%-- add begin sWX219697 2014-7-16 11:27:00 OR_huawei_201406_1125_山东_营业厅全业务流程优化-业务分流(自助终端)_支撑跨区缴费--%>
             <div class="popup_confirm" id="chargeConfirm">
             	<div class="bg"></div>
             	<div class="tips_title">提示：</div>
             	<div class="tips_body">
				<p>该手机号码不归属于本地区
				<s:if test="regionName != null && regionName != ''">
                	，归属于${regionName}地区。
                </s:if>
				</p>
				<div class="blank10"></div>
				<p class="mt30">请确认是否继续交费？</p>
		        </div>
             	<div class="btn_box tc mt20">
             		<span class=" mr10 inline_block ">
             			<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="doSub('<s:property value="#type.menuid" />','','1'); return false;">继续</a>
             		</span>
             		<span class=" inline_block ">
             			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="goback('<s:property value="curMenuId" />'); return false;">返回</a>
             		</span>
             	</div>
             </div>
			<%-- add end sWX219697 2014-7-16 11:27:07 OR_huawei_201406_1125_山东_营业厅全业务流程优化-业务分流(自助终端)_支撑跨区缴费--%>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
