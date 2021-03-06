<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
			var submitFlag = 0;
		
			function goback(curmenu) 
			{
				//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = curmenu;
					
					// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					if (document.getElementById("backWaitingFlag").value == "1")
					{
						openRecWaitLoading_NX("recWaitLoading");
					}
					// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
					document.actform.target="_self";
					document.actform.action="${sessionScope.basePath }login/backForward.action";
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
				
				// 1、2、3等数字键
				<s:iterator value="months" id="currMonth" status="st">
					if (key == <s:property value="#st.index + 49" />)
					{
						btnClick(null, '', '<s:property value="currMonth"/>');
						return;
					}
				</s:iterator>
			}
		
			function btnClick(btn, btClass, month)
			{		
				//如果已经点击返回或者选择某一月份，再次点击，不执行任何操作
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					//modify begin m00227318 2012/10/20 V200R003C12L10 OR_huawei_201210_125
					openRecWaitLoading_NX("recWaitLoading"); 
					//modify end m00227318 2012/10/20 V200R003C12L10 OR_huawei_201210_125
					
					document.getElementById("month").value = month;
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cdrquery/selectCycle.action";
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
				
				<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回首页&nbsp;(按0键)</a>
		        	
		        <div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">请选择详单月份：</p>
		          
		        <div class="blank15"></div>
		         
		        <div class="tc p120 ">
		        	<s:iterator value="months" id="currMonth" status="st">
					    <s:if test="#st.Odd">
					    	<s:if test="!#st.First">
					    		<div class="blank25"></div>
					    		<a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this, 'bt222', '<s:property value="currMonth"/>')"><s:property value="currMonth"/>&nbsp;(按<s:property value="#st.index + 1" />键)</a>
					    	</s:if>
					    	<s:else>
					    		<a href="#" class="bt3" onmousedown="this.className='bt3on'" onmouseup="this.className='bt3'" onclick="btnClick(this, 'bt222', '<s:property value="currMonth"/>')"><s:property value="currMonth"/>&nbsp;(按<s:property value="#st.index + 1" />键)</a>
					    	</s:else>
					    </s:if>
					    <s:else>
					    	<a href="#" class="bt3 ml20" onmousedown="this.className='bt3on ml20'" onmouseup="this.className='bt3 ml20'" onclick="btnClick(this, 'bt222', '<s:property value="currMonth"/>')"><s:property value="currMonth"/>&nbsp;(按<s:property value="#st.index + 1" />键)</a>
					    </s:else>
					</s:iterator>
		        </div>		          
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
