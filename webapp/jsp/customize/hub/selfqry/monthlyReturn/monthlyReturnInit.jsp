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
				if (etarget.name == 'endDate_1' && etarget.value == '' )
				{
					moveLast(document.getElementById('startDate_1'));
				}
				
			}
			
			//对号码进行判断
			var pattern = /^\d{8}$/;
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
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
				
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}

		function doSub()
		{
			if (lastObj != document.getElementById('numBoardText2'))
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				return;
			}
			
			//对号码进行判断
			var pattern = /^\d{8}$/;
			
			var startDate = document.getElementById("numBoardText1").value;
			var endDate = document.getElementById("numBoardText2").value;
			if (startDate == "" || !pattern.test(startDate))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				document.getElementById("errorMsg").innerHTML = "请输入正确的日期格式";
				return;
			}
			
			if (endDate == "" || !pattern.test(endDate))
			{
				changObj(document.getElementById('numBoardText2'), 1);
				document.getElementById("errorMsg").innerHTML = "请输入正确的日期格式";
				return;
			}
			
			if(!fValidDate(startDate))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				document.getElementById("errorMsg").innerHTML = "请输入正确的开始日期";
				return;
			}
			
			if(!fValidDate(endDate))
			{
				changObj(document.getElementById('numBoardText2'), 1);
				document.getElementById("errorMsg").innerHTML = "请输入正确的结束日期";
				return;
			}
			
			if (endDate < startDate)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				document.getElementById("errorMsg").innerHTML = "开始日期要小于结束日期";
				return;
			}
			
			if(!compareDate(startDate,endDate))
			{
				changObj(document.getElementById('numBoardText2'), 2);
				document.getElementById("errorMsg").innerHTML = "开始日期与结束日期相差不能大于三个月";
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.target = "_self";
				document.actform.action="qryMonthlyReturnInfo.action?startDate="+startDate+"&endDate="+endDate;
				document.actform.submit();
			}	
		}
		
		//yyyymmdd
		function fValidDate(strDate)
		{
			var vYear = strDate.substring(0,4);
			var vMonth = strDate.substring(4,6);
			if(vMonth.indexOf("0") != -1)
			{
				vMonth = vMonth.substring(1,2);
			}
			var vDate = strDate.substring(6,8);
			if(vDate.indexOf("0") != -1)
			{
				vDate = vDate.substring(1,2);
			}
			if(vYear > 2099 || vYear < 1999)
			{
				return false;
			}
			if(vMonth > 12)
			{
				return false;
			}
			if(vDate > 31)
			{
				return false;
			}
			return true;
		}
		
		function compareDate(sdate,edate)
		{
		    var tmpSdate = getDateByStr(sdate);
		    var tmpEdate = getDateByStr(edate);
		    var num=(tmpEdate-tmpSdate)/(24*60*60*1000) + 1
		    //结束时间”-“开始时间”小于等于3个月,以92天计算
			if(num > 92)
			{
				return false;
			}
			return true;
		}
		
		function getDateByStr(strDate) 
		{
			var vYear = strDate.substring(0,4);
			var vMonth = strDate.substring(4,6);
			if(vMonth.indexOf("0") != -1)
			{
				vMonth = vMonth.substring(1,2);
			}
			var vDate = strDate.substring(6,8);
			var Datertn = new Date(vYear, vMonth - 1, vDate);
			return Datertn;
		}
		
		function moveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
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
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.请输入开始日期(格式：20110101)</i>
          							<span class="pl20 fl lh75">开始日期：</span>
            						<input type="text" maxlength="8" id="numBoardText1" name="startDate_1" class="text1 fl relative" onclick="moveLast(this);changObj(this, 1);" />
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2.请输入结束日期(格式：20110101)</i>
          							<span class="pl20 fl lh75">结束日期：</span>
            						<input type="text" maxlength="8" id="numBoardText2" name="endDate_1" class="text1 fl relative" onclick="moveLast(this);changObj(this, 2);" />
         						</li>         
        					</ul>
        					
        					<!--小键盘-->
	        				<div class="numboard numboard_big fl" id="numBoard">
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
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
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
							  			
							  			var pattern = /^\d{8}$/;
							  			
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
									document.getElementById("errorMsg").innerHTML = "";
									
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
									lastObj.focus();
									lastObj.select();
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);

									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 8 && !isNaN(v))
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
	</body>
</html>
