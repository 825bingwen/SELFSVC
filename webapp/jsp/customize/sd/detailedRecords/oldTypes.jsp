<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_huawei_201112_953
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
			
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				<%-- add begin hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
				openRecWaitLoading();
				<%-- add end hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/qryDetailedRecordsOld.action";
				document.actform.submit();				
			}		
		}
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/selectMonth.action";
				document.actform.submit();
			}			
		}
		
		document.onkeydown = keyDown;
		function keyDown(e)
		{
			//8、32、66、77 更正
		    //82、220 返回
		    //13、89、221 确认
		    //80 打印
		    //85 上一页
		    //68 下一页
		    
			//接收键盘码
			var key = GetKeyCode(e);
		     
		    //8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />") ;
		   		return ;
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" id="cdrType" name="cdrType" value="" />
			<input type="hidden" name="iscycle" value="<s:property value='iscycle' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="month" value="<s:property value='month' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!-- modify begin cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
				<!-- modify end cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
				<div class="b966 tc">
				    <div class=" p40">
				    	<div class="blank30"></div>
						<p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span>详单类型选择</p>
						<div class="blank30"></div>
						<div id="btns" class="fl" >							
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '1');">
				    				通话详单
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '2');">
				    				短信详单
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '3');">
				    				移动梦网详单
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '4');">
				    				GPRS详单
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '5');">
				    				WLAN详单
				    			</a>
							</span>							
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
