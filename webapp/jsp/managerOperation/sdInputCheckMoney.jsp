<%--
 @User: 张磊/zKF69263
 @De: 2015/03/30
 @comment: 自助终端实现现金稽核功能
 @remark: create zKF69263 2015/03/30 R003C15L03n01 OR_SD_201502_169
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>输入钞箱中的现金数</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no" onload="doLoad();">
		<!--弹出提示窗-->
	    <div class="openwin_tip openwin_tip" id="openWin1" style="">
		    <div class="bg"></div>
		    <div class=" blank60"></div>
		    <div class=" blank40"></div>
		    <div class="  blank10n"></div>
		    <p class="tc fs26">您输入的金额和系统统计的金额不相同，如确认输入无</p>
		    <p class="tc fs26 pt30">误，请点击“确定”，否则点击“取消”重新输入金额。</p>
		    <div class="tc">
		    <div class=" blank40"></div>
		    <a  class=" bt4"  class=" bt4" onmousedown="this.className='bt4on';qdbuttonClick()" onmouseup="this.className='bt4'" >确认</a>
		    <a  class=" bt4 ml20"  onmousedown="this.className='bt4on ml20';" onmouseup="this.className='bt4 ml20';wiWindow.close();">取消</a>
		    </div>
		</div>
		<form name="actform" method="post">
			
			<%-- 稽核结束时间 --%>
			<input type="hidden" name="auditEndTime" value="<s:property value='auditEndTime' />">
			<%-- 稽核结束时间内容 --%>
			<input type="hidden" name="auditEndTimeFen" value="<s:property value='auditEndTimeFen' />">
			<%-- 上次稽核开始时间内容 --%>
			<input type="hidden" name="lastAuditStartTimeFen" value="<s:property value='lastAuditStartTimeFen' />">
			<%-- 上次稽核结束时间 --%>
			<input type="hidden" name="lastAuditEndTime" value="<s:property value='lastAuditEndTime' />">
			<%-- 上次稽核结束时间内容 --%>
			<input type="hidden" name="lastAuditEndTimeFen" value="<s:property value='lastAuditEndTimeFen' />">
			<%-- 系统统计金额 --%>
			<input type="hidden" name="sysMoney" value="<s:property value='sysMoney' />">
			<%-- 稽核id --%>
			<input type="hidden" name="auditId" value="<s:property value='auditId' />">
			<%-- 账单打印标识 --%>
			<input type="hidden" name="updateFlag" value="<s:property value='updateFlag' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					<div class=" p20">						
						<p class="fs22 mb30"></p>
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="audit_info fl">
          						<!-- 之前未进行过现金稽核，则不显示上次稽核时间段 -->
          						<s:if test="lastAuditStartTimeFen == '' && lastAuditEndTimeFen == ''">          							
          						</s:if>
          						<!-- 第一次进行现金稽核时，是没有开始时间的 -->
          						<s:elseif test="lastAuditStartTimeFen == ''">
          							<li class="fs18 clearfix">
	          							上次稽核时间段：开始-<s:property value="lastAuditEndTimeFen" />
	          						</li>
          						</s:elseif>
          						<!-- 非首次进行现金稽核 -->
          						<s:else>
          							<li class="fs18 clearfix">
	          							上次稽核时间段：<s:property value="lastAuditStartTimeFen" />-<s:property value="lastAuditEndTimeFen" />
	          						</li>
          						</s:else>
          						
          						<!-- 首次进行现金稽核时，是没有开始时间的 -->
          						<s:if test="lastAuditEndTimeFen == ''">
	          						<li class="fs18 clearfix">
	          							本次稽核时间段：开始-<s:property value="auditEndTimeFen" />
	          						</li>
          						</s:if>
          						<!-- 非首次进行现金稽核时，上次稽核结束时间就是本次稽核开始时间 -->
          						<s:else>          							       						
          							<li class="fs18 clearfix">
          								本次稽核时间段：<s:property value="lastAuditEndTimeFen" />-<s:property value="auditEndTimeFen" />
          							</li>
          						</s:else> 
          						
          						<li class="fs18 clearfix">
          							&nbsp;&nbsp;&nbsp;&nbsp;系统统计金额：<s:property value="sysMoney" />元
          						</li>
        					</ul>
        					<!--小键盘-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class="custom_money pt10">
	          						<span id="redstar1" class="pl20 fs18 fl lh48">钞箱实际金额：</span>
	          						<s:if test="1 == updateFlag">
	          							<input type="text" id="realMoney" name="realMoney" value="<s:property value='realMoney' />" class="text1 fl relative" onfocus="MoveLast(event);" readonly="true" />
	          						</s:if>
	          						<s:else>
	          							<input type="text"  id="realMoney" name="realMoney" class="text1 fl relative" onclick="changObj(this, 2);" onfocus="MoveLast(event);" maxlength='6' />
	          						</s:else>
        						</div>
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				<script type="text/javascript">	
	        					<%
									if("1".equals(redStarKey))
									{
								%>
									var textContent1 = document.getElementById('redstar1').innerHTML;
									document.getElementById('redstar1').innerHTML=textContent1 + '<font color="red">*</font>';
								<%
									}
								%>
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[1].getElementsByTagName('a');
								var lastObj = document.getElementById('realMoney');
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
					     			if (numBoardBtns[i].name == 'functionkey')
					     			{
					     				continue;  
					     			}
						 			numBoardBtns[i].onmousedown = function(){
							 			this.className = 'on';
									}
									numBoardBtns[i].onmouseup = function(){
									changValue(0, this.innerHTML);
									this.className = '';
									}					
								}
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									lastObj = o;
									
								}					
						
								function changValue(t, v)
								{
									<s:if test="1 == updateFlag">
										return;
									</s:if>
									
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 6 && !isNaN(v))
									{								
										lastObj.value += v;									
									}
									if ("undefind" != lastObj.id)
									{
										document.getElementById(lastObj.id).focus();
									}
								}
	              			</script>
	        				<!--小键盘end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
		</form>
	</body>
</html>
<script type="text/javascript">
		
	var submitFlag = 0;
	document.onkeydown = pwdKeyboardDown;
	
	// 键盘按下事件
	function pwdKeyboardDown(e) 
	{
		var key = GetKeyCode(e);
		if (key == 77) 
		{
			preventEvent(e);
		}
		if (!KeyIsNumber(key)) 
		{
			return false;//这句话最关键
		}
	}
	
	// 校验-只允许输入0-9数字
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
	
	// 键盘抬起事件
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		//确认
		if (key == 13 || key == 89 || key == 221) 
		{
			doSub();
			return;
		}
		//返回
		else if (key == 82 || key == 220) 
		{
			return;
		}
		//更正
	    else if (key == 8 || key == 32 || key == 66 || key ==77)
	    {
		    var etarget = getEventTarget(e);
		    if (etarget.type == "text" || etarget.type == "password") 
		    {
	            etarget.value = backString(etarget.value);
		    }
	    }	
		return true;
	}	
	
	// 移动到最后
	function MoveLast(e) 
	{
		var etarget = getEventTarget(e);
		var r = etarget.createTextRange();
		r.moveStart("character", etarget.value.length);
		r.collapse(true);
		r.select();
	}
	
	var checkSubmitFlag = false;
	
	// 确认提交
	function doSub()
	{
	    if (checkSubmitFlag)
	    {
	        document.getElementById('errorMsg').innerHTML = '请勿重复提交！';
	        return ;
	    }
	    
		var realMoney = document.getElementById("realMoney").value;
		if (realMoney == "")
		{
			changObj(document.getElementById('realMoney'), 2);
			document.getElementById('errorMsg').innerHTML = '请输入从钞箱中取得的现金数！';
			return ;
		}
		
		if (realMoney != parseInt("<s:property value='sysMoney' />"))
		{
		    wiWindow = new OpenWindow("openWin1",508,192);
		    return; 
		}
		
		checkSubmitFlag = true;
		
		// 开启等待图标
		openRecWaitLoading("recWaitLoading");
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"确认稽核：钞箱实际金额与系统统计金额一致。");
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}managerOperation/insertSDAuditLog.action";
		document.actform.submit();	
	}
	
	// 确认提交
	function qdbuttonClick()
	{
	    if (checkSubmitFlag)
	    {
	        return ;
	    }
	    
	    checkSubmitFlag = true;
	    
		wiWindow.close();
		
		// 开启等待图标
		openRecWaitLoading("recWaitLoading");
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"确认稽核：钞箱实际金额与系统统计金额不一致。");
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}managerOperation/insertSDAuditLog.action";
		document.actform.submit();
	}
	
	// 返回
	function goback()
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath }managerOperation/selectOperationType.action";
			document.actform.submit();
		}
	}
	
	// 初始加载
	function doLoad()
	{	
		// 顶部菜单不可用
   		closeStatus = 1;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"进行现金稽核");
				
		document.getElementById('realMoney').focus();
	}
</script>