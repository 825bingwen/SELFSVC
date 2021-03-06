<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

//错误提示弹出标志
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入手机号码</i>
          							<span id="redstar1" class="pl20 fl lh75">手机号码：</span>
            						<input type="text" maxlength="11" id="numBoardText1" name="chargeLogVO.servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(event);" onfocus="MoveLast(event);"/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 再次输入手机号码</i>
          							<span id="redstar2" class="pl20 fl lh75">再次输入：</span>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(event);" onfocus="MoveLast(event);"/>
         						</li>
								
         						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
         							<p class="p10">1. 手机号码输入完毕后，请按"确认"键进行交费；</p>
         							<p class="p10">2. 如需修改，请按"清除"键。</p>
         						</li> 
								
        					</ul>
        					
        					<!--小键盘-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a href="javascript:play();">1</a><a href="javascript:play()">2</a><a href="javascript:play()">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a href="javascript:play()">4</a><a href="javascript:play()">5</a><a href="javascript:play()">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a href="javascript:play()">7</a><a href="javascript:play()">8</a><a href="javascript:play()">9</a> <a href="javascript:void(0)">x</a><a href="javascript:play()">0</a><a href="javascript:void(0)">#</a> 
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
									
									var textContent2 = document.getElementById('redstar2').innerHTML;
									document.getElementById('redstar2').innerHTML=textContent2 + '<font color="red">*</font>';
								<%
									}
								%>
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
						    		
						    		// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
					     			if (numBoardBtns[i].getAttribute("name") == 'functionkey')
					     			{
					     				continue;  
					     			}
						
									numBoardBtns[i].onmousedown = function()
									{
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function(){
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			//对号码进行判断
										var pattern = /^\d{11}$/;
										
							  			if (pattern.test(trim(lastObj.value))
							  					&& document.getElementById("numBoardText2").value == "")
										{
											//numBoardText1输入完毕，自动跳转到numBoardText2
											document.getElementById("numBoardText2").focus();
											
											changObj(document.getElementById("numBoardText2"), 2);
											return true;
										}
									}					
								}
						
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
									}
								}					
						
								function changValue(t, v)
								{
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 11 && !isNaN(v))
									{								
										lastObj.value += v;									
									}
									
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
	              			</script>
	        				<!--小键盘end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		//8、32、66、77 更正
		//82、220 返回
		//13、89、221 确认
		//80 打印
		//85 上一页
		//68 下一页
		
		document.onkeyup = pwdKeyboardUp;
		
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
			else if (key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}
			//退出：82
			else if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}
			//清除：77
			else if(key == 77)
			{
				changValue(-2);
				return;
			}	
			//更正
			else if (key == 8 || key == 32 || key == 66 )
			{
				var etarget = getEventTarget(e);
				if (etarget.type == "text" || etarget.type == "password") 
				{
					etarget.value = backString(etarget.value);
				}
			}
			
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			if ((key == 8 || key == 32 || key == 66) 
					&& pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
				document.getElementById("numBoardText1").focus();

				changObj(document.getElementById("numBoardText1"), 1);
				
				return true;
			}
			
			if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//numBoardText1输入完毕，自动跳转到numBoardText2
				document.getElementById("numBoardText2").focus();
				
				// 语音提示
				document.getElementById("player03").play();
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}
			
		// 确认提交
		function doSub()
		{
			// 对号码进行判断
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "")
			{
				changObj(document.getElementById('numBoardText1'), 1);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("请输入正确的手机号码");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "请输入正确的手机号码";
				<%
				}
				%>
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("两次输入的手机号码必须相同");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "两次输入的手机号码必须相同";
				<%
				}
				%>
				
				return;
			}
			
			// 检验用户输入的手机号是否在黑名单中(宁夏)
			if(checkBlackList(telNumber))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("对不起，您不能在自助终端办理业务");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "对不起，您不能在自助终端办理业务";
				}
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>",telNumber + "选择异地充值交费");
						
				openRecWaitLoading_NX();
				document.actform.target = "_self";
				
				// 客户应缴费用总额查询
				document.actform.action = "${sessionScope.basePath}nonlocalCharge/qryPayAmount.action";
				document.actform.submit();
			}	
		}
		
		// 返回
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>",document.getElementById("numBoardText1").value + "退出异地充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}	
		
		function play()
		{
			if (document.getElementById('numBoardText1').value.length == 11
			 	&& document.getElementById('numBoardText2').value.length == 0)
			{
				// 语音提示
				document.getElementById("player03").play();
			}
		}
		</script>
	</body>
</html>
