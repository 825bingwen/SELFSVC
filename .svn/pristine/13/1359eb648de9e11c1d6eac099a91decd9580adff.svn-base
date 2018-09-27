<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
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
		
		function go(btn, btClass, type) 
		{	
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("gprsWlanType").value = type;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }cdrquery/selectFeeType.action";;
				document.actform.submit();				
			}	
		}
		
		function goback(curmenu) 
		{
			//如果已经选择了详单类型或者点击了返回，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action="${sessionScope.basePath }cdrquery/selectType.action";
				document.actform.submit();
			}
		}
		
		// 处理键盘事件
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
				goback();
		   		return ;
			}
		}		
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="month" value="<s:property value='month' />">			
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />">
			<input type="hidden" id="gprsWlanType" name="gprsWlanType" value="">
			
			    <%@ include file="/titleinc.jsp"%>
				<div class="main" id="main">
					<%@ include file="/customer.jsp"%>
					<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
				<div class="b966 tc">
				    <div class=" p40">
				    <div class="blank30"></div>
					<p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span>请选择上网详单类型</p>
					<div class="blank30"></div>
					<div id="btns" class="fl" >

					<span class="relative mr10 inline_block ">
						<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', 'ALL');">
	    					全部上网详单
	    				</a>
	    				</span>
	    				<span class="relative mr10 inline_block ">
	    				<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', 'GPRS');">
	    					GPRS上网详单
	    				</a>
	    				</span>
	    				<span class="relative mr10 inline_block ">
	    				<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', 'WLAN');">
	    					WLAN上网详单
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
