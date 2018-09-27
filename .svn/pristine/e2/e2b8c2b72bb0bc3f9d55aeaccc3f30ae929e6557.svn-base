<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_huawei_201112_953
--%>
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
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript">
			var submitFlag = 0;
		
			function goback(curmenu) 
			{
				//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = curmenu;
					
					document.actform.target="_self";
					document.actform.action="${sessionScope.basePath }login/backForward.action";
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
		
			function btnClick(btn, btClass, month)
			{		
				//如果已经点击返回或者选择某一月份，再次点击，不执行任何操作
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("month").value = month;
					
					<%-- add begin hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					openRecWaitLoading();
					<%-- add end hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cdrquery/selectCycle.action";
					document.actform.submit();
				}
			}
			
			function inputTime()
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					<%-- add begin hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					openRecWaitLoading();
					<%-- add end hWX5316476 2015-6-26 OR_SD_201506_330  自助终端“详单查询”等页面增加‘正在努力查询，请稍后…’的等待画面--%>
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cdrquery/inputTime.action";
					document.actform.submit();
				}
			}	
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">	
			<input type="hidden" id="month" name="month" value="">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!-- modify begin cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
		        <!-- modify end cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->	
		        <div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">请选择查询时间：</p>
		          
		        <div class="blank15"></div>
		         
		        <div class="tc p120 ">
		        	<s:iterator value="months" id="currMonth" status="st">
					    <s:if test="#st.Odd">
					    	<div class="blank25"></div>
					    	<a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this, 'bt222', '<s:property value="currMonth"/>')"><s:property value="currMonth"/></a>
					    </s:if>
					    <s:else>
					    	<a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this, 'bt222', '<s:property value="currMonth"/>')"><s:property value="currMonth"/></a>
					    </s:else>
					</s:iterator>
					<div class="blank25"></div>
					<a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="inputTime();">时间段</a>
		        </div>	          
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
