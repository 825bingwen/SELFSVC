<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="">
		<meta http-equiv="description" content="">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
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

		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }chooseTel/selectRule.action";
				document.actform.submit();				
			}			
		}
		
		// 选择号码
		function selectTelNum(telnum, org_id, low_consum_fee, low_consum_pre, low_inservice_time)
		{
			if (telnum == '')
			{
				document.getElementById('chooseNum').style.visibility='hidden';
				document.getElementById('telnum').value = '';
				return;
			}
			
			document.getElementById('telnum').value = telnum;
			document.getElementById("org_id").value = org_id;
			
			document.getElementById('chooseNum').style.visibility='hidden';
			
			if(null != low_inservice_time && ''!= low_inservice_time)
			{
				document.getElementById('chooseNum').innerHTML = '<p class="fs16">' + telnum + '</p><p class="fs14">预存话费：' + low_consum_pre+ '元</p><p class="fs14">月最低消费：' + low_consum_fee+ '元</p><p class="fs14">签约时长：' + low_inservice_time + '年 </p>';
			}
			else
			{
				document.getElementById('chooseNum').innerHTML = '<p class="fs16">' + telnum + '</p><p class="fs14">预存话费：' + low_consum_pre+ '元</p><p class="fs14">月最低消费：' + low_consum_fee+ '元</p><p class="fs14">签约时长：长期有效</p>';
			}
			
			document.getElementById('chooseNum').style.visibility='visible';
			
			if(null != low_inservice_time && ''!= low_inservice_time)
			{
				document.getElementById('winText').innerHTML = '<span class="yellow">您选择预订号码：' + telnum + '</span><br /><span align="left"> 预存话费：</span><span class="">' + low_consum_pre + '元</span><br /><span align="left">月最低消费：</span><span class="">'+ low_consum_fee + '元</span><br /><span align="left">签约时长：</span><span class="">'+ low_inservice_time + '年</span>';
							
				document.getElementById('winText1').innerHTML = '<span class="yellow">您选择预订号码：' + telnum + '</span><br /><span align="left"> 预存话费：</span><span class="">' + low_consum_pre + '元</span><br /><span align="left">月最低消费：</span><span class="">'+ low_consum_fee + '元</span><br /><span align="left">签约时长：</span><span class="">'+ low_inservice_time + '年</span>';
			}
			else
			{
				document.getElementById('winText').innerHTML = '<span class="yellow">您选择预订号码：' + telnum + '</span><br /><span align="left"> 预存话费：</span><span class="">' + low_consum_pre + '元</span><br /><span align="left">月最低消费：</span><span class="">'+ low_consum_fee + '元</span><br /><span align="left">签约时长：</span><span class="">长期有效</span>';
							
				document.getElementById('winText1').innerHTML = '<span class="yellow">您选择预订号码：' + telnum + '</span><br /><span align="left"> 预存话费：</span><span class="">' + low_consum_pre + '元</span><br /><span align="left">月最低消费：</span><span class="">'+ low_consum_fee + '元</span><br /><span align="left">签约时长：</span><span class="">长期有效</span>';
			}
			
			openWindow('openWin2');
		}

		// 下一页
		function nextPage(linkURL)
		{
			// 执行查询
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
		  		document.actform.target = "_self";
				document.actform.action = linkURL;
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="org_id" name="org_id" value="" />
			<input type="hidden" id="telnum" name="telnum" value="" />
			
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
			<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
			<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
			
			<!-- 号码暂选接口返回的虚拟产品编码 -->
			<input type="hidden" id="vprodId" name="vprodId" value="<s:property value='vprodId' />" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);">
							1.选择查询方式
						</a>
						<a href="javascript:void(0);">
							2.输入查询条件
						</a>
						<a href="javascript:void(0);" class="on">
							3.选择号码
						</a>
						<a href="javascript:void(0);">
							4.输入个人信息
						</a>
						<a href="javascript:void(0);">
							5.完成
						</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>点击选定号码，每次只能预定<span class="yellow">1</span>个号码
						</p>
						<div class="blank10"></div>
						<div class="num_dis">
							<s:iterator value="serverNumList" id="po">
								<a href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="org_id" />', '<s:property value="low_consum_fee" />', '<s:property value="low_consum_pre" />', '<s:property value="low_inservice_time" />');" ><span></span><s:property value="telnum" /></a>
							</s:iterator>
							<div class="clear"></div>
  						</div>
  						
  						<!-- modify begin wWX217192 2015-03-17 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求 -->
  						<!-- webserviceFalg为1时，启用商城提供的webService接口来完成选号，webserviceFlag为0时，使用原有接口 -->
  						<s:if test="webserviceFlag == 1">
  							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="chooseTel/queryNum.action" />
  						</s:if>
  						<s:else>
  							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="chooseTel/telNumResult.action" />
  						</s:else>
  						<!-- modify end wWX217192 2015-03-17 OR_SD_201411_988_SD_关于自助终端选号规则优化的需求 -->
  						
  						<div class="num_foot fs18">  
	    					<div class="fright2"> 
	    						<span class="fl" >已选定号码：</span> 
								<a href="javascript:void(0)" class="fl" style=" visibility:hidden" id="chooseNum">
									<p class="fs16 tc" id="telnumText"></p>
									<p class="fs14 tc" id="payfeeText"></p>
	        					</a>
	        					<a href="javascript:void(0)" class="bt6 fr relative fl ml20"  onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';openWindow('openWin1')">预订</a> 
	        				</div>
						</div>
					</div>	
					
					<div class=" clear"></div>
					
					<!--弹出窗-->
					<div class="openwin_tip div708w392h" id="openWin1">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<p class="fs28 lh2 pl142" id="winText" name="winText">
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
	    					<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">确认</a> 
	    					<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
	    				</div>
					</div>
					
					<%--modify begin fKF59607 2012/05/10 OR_SD_201203_1057 添加弹层提示号码等级描述 --%>
					<div class="openwin_tip div708w392h" id="openWin2">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<p class="fs28 lh2 pl142" id="winText1" name="winText1">
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
	    					<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">确定</a> 
	    				</div>
					</div>
					<%--modify end fKF59607 2012/05/10 OR_SD_201203_1057 添加弹层提示号码等级描述 --%>
					
					<div class="openwin_tip openwin_big" id="openWinLoading">
	  					<div class="bg loading tc">
						    <div class="blank60"></div>
						    <div class="blank60"></div>
						    <img src="${sessionScope.basePath }images/loading.gif"  class="" alt="处理中..."/>
						    <div class="blank30"></div>
						    <p class="fs24   lh2">正在执行预定信息处理，请稍候...</p>
						    <p class=" fs18  lh2 yellow"></p>
	  					</div>
					</div>
					
					<script type="text/javascript">
					openWindow = function(id){
						// 检查是否已选中
						if (document.getElementById('telnum').value == '')
						{
							return;
						}
						
						wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子					
					}
					
					openWindowloading = function(id){
						wiWindow = new OpenWindow("openWinLoading",804,515);//打开弹出窗口例子
						gotoPrintSuccess();					
					}				
					
					function gotoPrintSuccess()
					{
						setTimeout(
							function(){
								wiWindow.close();
								
								document.actform.target="_self";
								
								if("<s:property value='webserviceFlag' />" == "1" )
								{
									document.actform.action="${sessionScope.basePath }chooseTel/pickNum.action";
								}
								else
								{
									document.actform.action="${sessionScope.basePath }chooseTel/inputCertid.action";
								}
								document.actform.submit();							
							},
						500);
					}
					</script>
					<!--弹出窗结束-->	
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
