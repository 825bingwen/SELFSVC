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
		
		//选择账期
		function selectCycle(cycle) 
		{	
			document.getElementById("cycle").value = cycle;
			
			<s:iterator value="cycles" id="currCycle">
				if (cycle == "<s:property value="#currCycle.cycle" />")
				{
					document.getElementById("startDate").value = "<s:property value="#currCycle.startDate" />";
					document.getElementById("endDate").value = "<s:property value="#currCycle.endDate" />";
				}				
			</s:iterator>
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/selectType.action";
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
	
			// 1、2、3等数字键
			<s:iterator value="cycles" id="currCycle" status="st">
			if (key == <s:property value="#st.index + 49" />)
			{
				selectCycle(<s:property value='#currCycle.cycle' />)
				return;
			}
			</s:iterator>
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" id="cycle" name="cycle" value="" />
			<input type="hidden" id="startDate" name="startDate" value="" />
			<input type="hidden" id="endDate" name="endDate" value="" />
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="month" value="<s:property value='month' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
        		<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
				
				<div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">请选择查询账期：</p>
		          
		        <div class="blank15"></div>
		        
		        <div class="pl160">
		        	<ul>
		        		<s:iterator value="cycles" id="currCycle" status="st">
		        			<li class="cycle_1">
		        				<a href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="selectCycle(<s:property value='#currCycle.cycle' />)">
		        					<s:property value="#currCycle.startDate" /> - <s:property value="#currCycle.endDate" />&nbsp;(按<s:property value="#st.index + 1" />键)
		        				</a>
		        			</li>
		        		</s:iterator>
		        	</ul>
		        </div>		         			
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
