<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
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
			
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
			{
			%>		
				// 退出
				if (key == 82 || key == 220)
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;			
				}
				// 清除
				else if (key == 77)
				{
					changValue(-2);
					return;
				}
				//更正
				else if (key == 8 || key == 32 || key == 66)
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
				
				if ((key == 8 || key == 32 || key == 66 ) 
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
			<%
			}
			else
			{
			%>
			//返回
			if (key == 82 || key == 220) 
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
			<%
			}
			%>
		
			
		}
		
		//验证日期格式
 		function checkAskDate(StartDate,EndDate)
 		{   
	        if(!isDate(StartDate,null))
	        {   
	        	alertRedErrorMsg("开始日期格式不正确!正确格式为:20120501");
	            // StartDate.focus();   
	            return false;   
	         }
	         else if(!isDate(EndDate,null))
	         { 
	         	alertRedErrorMsg("结束日期格式不正确!正确格式为:20120531");  
	         	// EndDate.focus();   
	         	return false;   
	         }  
	        return true;   
        } 
        
        // 判断输入日期是否在规定的区间内
        function isDate(date,fmt) 
        { 
				if (fmt==null)
				{
					fmt="yyyyMMdd";
				} 
				
				var yIndex = fmt.indexOf("yyyy"); 
				
				if(yIndex==-1)
				{
					return false;
				}
				
				var year = date.substring(yIndex,yIndex+4);
				
				var mIndex = fmt.indexOf("MM"); 
				
				if(mIndex==-1)
				{
					return false;
				}
				
				var month = date.substring(mIndex,mIndex+2); 
				var dIndex = fmt.indexOf("dd"); 
				
				if(dIndex==-1)
				{
					return false;
				}
				
				var day = date.substring(dIndex,dIndex+2); 
				
				if(!isNumber(year)||year>"2100" || year< "1900")
				{
					return false;
				}
				
				if(!isNumber(month)||month>"12" || month< "01")
				{
					return false;
				}
				
				if(day>getMaxDay(year,month) || day< "01")
				{
					return false;
				}
				
				return true; 
		}
		
		// 判断是否为数字
		function isNumber(s)
		{ 
			var regu = "^[0-9]+$"; 
			var re = new RegExp(regu); 
			if (s.search(re) != -1)
			{ 
				return true; 
			}
			else
			{ 
				return false; 
			} 
		}
		
		// 获取相应月份的最大天数
		function getMaxDay(year,month)
		{
			if(month==4||month==6||month==9||month==11)
			{
				return "30";
			}
			if(month==2)
			{ 
				if(year%4==0&&year%100!=0 || year%400==0)
				{ 
					return "29";
				} 
				else
				{ 
					return "28";
				}
			} 
			return "31"; 
		}

		function doSub()
		{
			//对号码进行判断
			var pattern = /^\d{8}$/;
			
			var startDate = document.getElementById("numBoardText1").value;
			var endDate = document.getElementById("numBoardText2").value;
			if (startDate == "" || !pattern.test(startDate))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入正确的开始日期");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入正确的开始日期";
				}
				
				return;
			}
			
			if (endDate == "" || !pattern.test(endDate))
			{
				changObj(document.getElementById('numBoardText2'), 1);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("请输入正确的结束日期");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "请输入正确的结束日期";
				}
				
				return;
			}
			
			//判断日期是否符合日期格式
			if(!checkAskDate(startDate,endDate))
			{
				return ;
			}
			
			if (endDate < startDate)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("开始日期要小于结束日期");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "开始日期要小于结束日期";
				}
				
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
				document.actform.target = "_self";
				document.actform.action="qryServiceHistory.action?startDate="+startDate+"&endDate="+endDate;
				document.actform.submit();
			}	
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
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
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
				<div class="blank50">
				<%-- 宁夏本应该使用按键0返回至父菜单，但是此页面是输入页面，0是可以正常输入的数字。所以此处不支持使用按键0返回至父菜单 --%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				</div>
				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.请输入开始日期(格式：20110101)</i>
          							<span id="redstar1" class="pl20 fl lh75">开始日期：</span>
            						<input type="text" maxlength="8" id="numBoardText1" name="startDate_1" class="text1 fl relative" onclick="moveLast(this);changObj(this, 1);" onfocus="moveLast(this);" />
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2.请输入结束日期(格式：20110101)</i>
          							<span id="redstar2" class="pl20 fl lh75">结束日期：</span>
            						<input type="text" maxlength="8" id="numBoardText2" name="endDate_1" class="text1 fl relative" onclick="moveLast(this);changObj(this, 2);" onfocus="moveLast(this);" />
         						</li>
         						<%
								if ("1".equals(keyFlag))
							    {
								%>
									<li class="fs18 mt30 line_height_12">
	         							<p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
	         							<p class="p10">1. 信息输入完毕后，请按"确认"键提交；</p>
	         							<p class="p10">2. 如需修改，请按"清除"键。</p>
	         						</li>
								<%
							    }
								%>         
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
