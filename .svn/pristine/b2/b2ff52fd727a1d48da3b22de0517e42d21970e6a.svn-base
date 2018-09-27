<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

int nValueForPopWindow = 0;

// 关闭弹出提示框所使用的按键对应的值
String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
if (valueForPopWindow != null && !"".equals(valueForPopWindow))
{
	nValueForPopWindow = Integer.parseInt(valueForPopWindow);
}

String limitTip = CommonUtil.getParamValue("SH_CASHCHARGE_LIMITTIP","");

// 交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%-- 手机号码 --%>
			<s:hidden id="servnumber" name="chargeLogVO.servnumber" ></s:hidden>
			
			<%-- 应缴金额 --%>
			<s:hidden id="payAmount" name="payAmount"></s:hidden>
			
			<%-- 省份编码 --%>
			<s:hidden id="telProvinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<%-- 手机号码归属地市--%>
			<s:hidden id="servRegion" name="chargeLogVO.servRegion"></s:hidden>
			
			<%-- 余额--%>
			<s:hidden id="balance" name="balance"></s:hidden>
			
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
	    					<a href="javascript:void(0)">3. 支付</a> 
	    					<a href="javascript:void(0)">4. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
	   					<div class="blank60"></div>
	   					<div class="p40">
	   						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="chargeLogVO.servnumber" /></span></p>
							<p class="fs22 fwb pl40 lh30">用户姓名：<span class="yellow fs22"><s:property value="custName" /></span></p>		
							<s:if test="payAmount == 0.00">
								<p class="fs22 fwb pl40 lh30">话费余额：<span class="yellow fs22"><s:property value="balance" /></span> 元</p>
							</s:if>
							<s:else>
								<p class="fs22 fwb pl40 lh30">应交金额：<span class="yellow fs22"><s:property value="payAmount" /></span> 元</p>
							</s:else>
							<div class="blank10"></div>
							<div class="line"></div>
	     						<div class="blank10"></div>
	     						<p class="fs22 tit_arrow"><span class="bg"></span>选择支付方式：</p>
	     						<div class="blank20"></div>
	       					<div class="blank5"></div>
	       					<ul class="pay_way_list clearfix">
	       						<s:iterator value="usableTypes" id="type" status="st">
     								<li>
										<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;">
		       								<img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' />
		       								<span class="chargeTypeDesp1">(请按<s:property value="#st.index + 1" />键)</span>
		       							</a>
     								</li>
     								<div style = "width:300px;"></div>
	       						</s:iterator>
	       					</ul>				
	   					</div>
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		//82、220 返回
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			// 返回
			if (key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}
			// 关闭提示框
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			<s:iterator value="usableTypes" id="type" status="st">	
				if (key == <s:property value="#st.index + 49" />)
				{
					doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />');
				}			
			</s:iterator>
			
			// 退出：82
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}			
		}
					
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// 弹出等待框
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />退出跨省异地交费功能");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />选择交费方式：" + menuid);
				
				if (url == "nonlocalCharge/cashCharge.action")
				{
					// 现金交费，判断此时间段内是否可用
					var url1 = "${sessionScope.basePath}charge/checkTime.action";
					
					var loader = new net.ContentLoader(url1, netload = function () {
						var resXml1 = this.req.responseText;
						if (resXml1 == "1" || resXml1 == 1)
						{
							openRecWaitLoading_NX("recWaitLoading");
							
							document.actform.target = "_self";
							document.actform.action = "${sessionScope.basePath}" + url;
							document.actform.submit();
						}
						else
						{
							submitFlag = 0;
							
							alertError('<%=limitTip%>');
							
							return;
						}
					}, null, "POST", null, null);
				}
				else
				{
					openRecWaitLoading_NX("recWaitLoading");
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
			}			
		}
		</script>
	</body>
</html>
