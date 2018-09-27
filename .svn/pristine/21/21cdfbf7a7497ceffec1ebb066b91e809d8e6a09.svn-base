<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>NG2.3自助终端系统--移动话费查询--月详单查询</title>
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
		var submitFlag = 0;
		
		//查询详单
		function go(btn, btClass, cdrType) 
		{	
			document.getElementById("cdrType").value = cdrType;
			
			var url = "${sessionScope.basePath}cdrquery/qryDetailedRecords.action";
			
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//modify begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading"); 
				//modify end m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
					
				document.actform.target = "_self";
				document.actform.action = url;
				document.actform.submit();				
			}	
		}
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/selectMonth.action";
				document.actform.submit();
			}			
		}
		
		document.onkeydown = keyDown;
		function keyDown(e)
		{
			//接收键盘码
			var key = GetKeyCode(e);
		     
		    //8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 退出
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return ;
			}
			// 0键
			else if (key == 48)
			{
				//topGo('qryBill', 'feeservice/feeServiceFunc.action');
				goback('<s:property value='curMenuId' />');
				return;
			}
			// 1键
			else if (key == 49)
			{
				go(null, '', 'FIXFEE');
				return;
			}
			// 2键
			else if (key == 50)
			{
				go(null, '', 'GSM');
				return;
			}
			// 3键
			else if (key == 51)
			{
				go(null, '', 'SMS');
				return;
			}
			// 4键
			else if (key == 52)
			{
				go(null, '', 'GPRSWLAN');
				return;
			}
			// 5键
			else if (key == 53)
			{
				go(null, '', 'ISMG');
				return;
			}
			// 6键
			else if (key == 54)
			{
				go(null, '', 'INFOFEE');
				return;
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" id="cdrType" name="cdrType" value="" />
			<input type="hidden" name="month" value="<s:property value='month' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="blank50">				
				<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回上级&nbsp;(按0键)</a>
				</div>
				
				<div class="b966 tc">
				    <div class=" p40">
				    	<div class="blank30"></div>
						<p class="fs22 fwb  tit_info rel" align="left">
							<span class="bg"></span>请选择详单类型
						</p>
						<div class="blank30"></div>
						<div id="btns" class="fl ml10" >
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'FIXFEE');">
					    			套餐及固定费详单&nbsp;(按1键)
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'GSM');">
					    			通话详单&nbsp;(按2键)
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'SMS');">
					    			短/彩信详单&nbsp;(按3键)
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'GPRSWLAN');">
					    			上网详单&nbsp;(按4键)
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'ISMG');">
					    			增值业务扣费记录&nbsp;(按5键)
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_240_1" onmouseup="this.className='btn_bg_240_hover_1';go(this, 'bt222', 'INFOFEE');">
					    			代收费业务扣费记录&nbsp;(按6键)
					    		</a>
							</span>
							
							<div class="Bill_present fl">
						    	<div class="zi">详单介绍</div>
						        <div class="Bill_present_bg">
						        	<div class="Bill_list">
						        		<%=(String)request.getAttribute("additionalInfo") %>
						            </div>
						        </div>
					    	</div>
					    	
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
