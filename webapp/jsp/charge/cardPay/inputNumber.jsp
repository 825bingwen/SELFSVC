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
		//防止页面重复提交
		var submitFlag = 0;
		
		//8、32、66、77 更正
		//82、220 返回
		//13、89、221 确认
		//80 打印
		//85 上一页
		//68 下一页
		
		/*
		 *　去掉左右两边的空格
		 */
		function trim(str) 
		{
			while (str.charAt(str.length - 1) == " ") 
			{
				str = str.substring(0, str.length - 1);
			}
			
			while (str.charAt(0) == " ") 
			{
				str = str.substring(1, str.length);
			}
			
			return str;
		}
		
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
			
			//确认
			if (key == 13 || key == 89 || key == 221) 
			{
				doSub();
				return;
			}
			//返回
			else if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
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
			
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			//对充值卡密码进行判断
			var cardpattern = /^\d{18}$/;
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//更正时，numBoardText2更正完毕，自动跳转到numBoardText1
				document.getElementById("numBoardText1").focus();
				changObj(document.getElementById("numBoardText1"), 1);
				return true;
			}
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText2").value)) 
					&& document.getElementById("numBoardText3").value == "")
			{
				//更正时，numBoardText3更正完毕，自动跳转到numBoardText2
				document.getElementById("numBoardText2").focus();
				changObj(document.getElementById("numBoardText2"), 2);	
				return true;
			}		
			
			if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//numBoardText1输入完毕，自动跳转到numBoardText2
				document.getElementById("numBoardText2").focus();
				changObj(document.getElementById("numBoardText2"), 2);
				return true;
			}
			
			var telNumber = document.getElementById("numBoardText1").value;
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			
			if (pattern.test(trim(document.getElementById("numBoardText2").value)) 
					&& document.getElementById("numBoardText3").value == "")
			{
				if(confirmTelNumber == telNumber)
				{
					//numBoardText2输入完毕，自动跳转到numBoardText3
					document.getElementById("numBoardText3").focus();
					changObj(document.getElementById("numBoardText3"), 3);
					return true;
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "两次输入的手机号码必须相同";
					return true;
				}
			}	
		}
		function MoveLast(e) 
		{
			var etarget = getEventTarget(e);
			var r = etarget.createTextRange();
			r.moveStart("character", etarget.value.length);
			r.collapse(true);
			r.select();
		}

		function doSub()
		{
			 
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				document.getElementById("errorMsg").innerHTML = "请输入11位手机号码";
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				document.getElementById("numBoardText2").focus();
				if(confirmTelNumber!="")
				{
					document.getElementById("errorMsg").innerHTML = "两次输入的手机号码必须相同";
				}
				return;
			}
			
			if (lastObj != document.getElementById('numBoardText3'))
			{
				changObj(document.getElementById('numBoardText3'), 3);
				return;
			}
			//对充值卡密码进行判断
			var cardpattern = /^\d{18}$/;
			var cardPwd = document.getElementById("numBoardText3").value;
			if (cardPwd == "" || !cardpattern.test(cardPwd))
			{
				changObj(document.getElementById('numBoardText3'), 3);
				document.getElementById("numBoardText3").focus();
				document.getElementById("errorMsg").innerHTML = "请输入18位充值卡密码";
				return;
			}
			
			var confirmMsg = document.getElementById("confirmMsg");  
			confirmMsg.innerHTML="您输入的充值卡密码为:<font color='yellow'>" + cardPwd + "</font>";
			openWindow('popup_confirm');	
			
		}
		
		function cardPayCommit()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading("recWaitLoading");
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}freecharge/cardPayCommit.action";
				document.actform.submit();
			}	
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		
		openWindow = function(id)
		{
			wiWindow = new OpenWindow("popup_confirm",708,392);//打开弹出窗口例子
		}	
		</script>
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
          							<span id="redstar1" class="pl20 fl lh75">&nbsp&nbsp手机号码：</span>
            						<input type="text" maxlength="11" id="numBoardText1" name="telnum" class="text1 fl relative" onclick="changObj(this, 1);" onfocus="MoveLast(event);"/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 再次输入手机号码</i>
          							<span id="redstar2" class="pl20 fl lh75">&nbsp&nbsp再次输入：</span>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);" onfocus="MoveLast(event);"/>
         						</li>     
         							<li class="fs20 clearfix" id="phone_input_3">
          							<i class="lh30">3. 输入充值卡密码</i>
          							<span id="redstar3" class="pl20 fl lh75">充值卡密码：</span>
            						<input type="text" maxlength="18" id="numBoardText3"  name="cardPwd"  style="font-size:22px;" class="text1 fl relative" onclick="changObj(this, 3);" onfocus="MoveLast(event);"/>
         						</li>          
        					</ul>
        					
        					<!--小键盘-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a title="1" href="javascript:void(0)">1</a><a title="2" href="javascript:void(0)">2</a><a title="3" href="javascript:void(0)">3</a> <a title="退格" href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a title="4" href="javascript:void(0)">4</a><a title="5" href="javascript:void(0)">5</a><a title="6" href="javascript:void(0)">6</a> <a title="清除" href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a> <a title="x" href="javascript:void(0)">x</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a title="确认" href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
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
									
									var textContent3 = document.getElementById('redstar3').innerHTML;
									document.getElementById('redstar3').innerHTML=textContent3 + '<font color="red">*</font>';
								<%
									}
								%>	
								
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
								var type = 1;
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		
						    		if (!numBoardBtns[i].className) 
						    		{	
						    			numBoardBtns[i].className='';
						    		}
						    		
					     			if (numBoardBtns[i].getAttribute("name") == 'functionkey')
					     			{
					     				continue;  
					     			}
						
									numBoardBtns[i].onmousedown = function(){
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function(){
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			//对号码进行判断
										var pattern = /^\d{11}$/;
										
							  			//手机号码输入完成后自动跳转到再次输入手机号码
										if (pattern.test(trim(lastObj.value)) && type == 1
												&& document.getElementById('numBoardText2').value == "")
										{
											document.getElementById('numBoardText2').focus();
											changObj(document.getElementById("numBoardText2"), 2);
											return true;
										}
										
										//再次输入手机号码完成后自动跳转到充值卡密码输入框
										if (pattern.test(trim(lastObj.value)) && type == 2
												&& document.getElementById('numBoardText3').value == "")
										{
											changObj(document.getElementById('numBoardText3'), 3);
											document.getElementById('numBoardText3').focus();
											return true;
										}  
									}					
								}
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if(t==2)
									{
										type = 2;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else
									{
										type = 3;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 11 && !isNaN(v)&& type == 1)
									{								
										lastObj.value += v;									
									}
									else if (lastObj.value.length < 11 && !isNaN(v)&& type == 2)
									{								
										lastObj.value += v;									
									}
									else if (lastObj.value.length < 18 && !isNaN(v)&& type == 3)
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
			<div class="popup_confirm" id="popup_confirm">
                 <div class="bg"></div>
                 <div class="tips_title">提示：</div>
                 <div class="tips_body">
			     <p id="confirmMsg"></p>
			     <div class="blank60"></div>
			     </div>
				 <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();cardPayCommit();">确认</a></span>
	                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
                 </div>
            </div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
