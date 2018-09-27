<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// 终端机信息
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // 省份信息
    String provinceSD = Constants.PROOPERORGID_SD;

	// 控件支持标志
	String termSpecial = terminalInfo1.getTermspecial();
	
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">		
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript">
		
		//是否支持读取二代身份证信息标志,0不支持,1:支持
		var is2GIDFlag = <%=termSpecial.charAt(8)%>;	
		
		// 读卡等待时间
		var limitTime = 30;
		
		//计算读卡剩余时间的句柄
		var timeToken;
		
		//等待读卡的句柄
		var waitingToken;
		
		// 提交标志
		var submitFlag = 0;
		
		// 初始化身份证读卡器线程启动标志，1，已启动；0，未启动。如果为1时，用户主动取消操作，需调用关闭身份证阅读器接口
		var initCardReader = 0;
		
		document.onkeydown = pwdKeyboardDown;
		
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
				if (etarget.name == 'password' && etarget.value == '' )
				{
					MoveLast(document.getElementById('servnumber'));
				}
			}
			
			var sendTelNum = document.forms[0].sendTelNum.value;
			var contacttel = document.forms[0].contacttel.value;		
	
			if ((key == 8 || key == 32 || key == 66)
			 		&& pangu_getStrLen(trim(contacttel)) == 0 
			 		&& (pangu_getStrLen(trim(sendTelNum)) == 15 || pangu_getStrLen(trim(sendTelNum)) == 11)) 
			{
				document.forms[0].sendTelNum.focus();
				
				changObj(document.forms[0].sendTelNum, 1);
				
				return true;
			}
			
 			if (pangu_getStrLen(trim(sendTelNum)) == 11 && document.forms[0].contacttel.value == "") 
 			{
 				//document.forms[0].contacttel.focus();
 				
 				//changObj(document.forms[0].contacttel, 2);
 				
 				return true;
 			}
 			
			return true;
		}
		
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

		function pangu_getStrLen(s) 
		{
			var count = 0;
			var lenByte = s.length;
			for (i = 0; i < lenByte; i++) 
			{
				if (s.charCodeAt(i) > 256) 
				{
					count = count + 2;
				} 
				else 
				{
					count = count + 1;
				}
			}
			
			return count;
		}	
		
		function MoveLast(e) 
		{
			var etarget = getEventTarget(e);
			var r = etarget.createTextRange();
			r.moveStart("character", etarget.value.length);
			r.collapse(true);
			r.select();
		}
		
		// 提交
		function doSub()
		{
			if (document.getElementById('sendTelNum').value == '')
			{
				setErrorInfoRegion("请输入手机号码！");
				return;
			}
			if (document.getElementById('sendTelNum').value.length != 11)
			{
				setErrorInfoRegion("请正确输入手机号码！");
				return;
			}
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading_NX('recWaitLoading');
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/sendNumFinish.action";
				document.actform.submit();
			}		
		}
		
		function goback(curmenu) 
		{
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
				document.actform.action="${sessionScope.basePath }chooseTel/telNumResult.action";
				document.actform.submit();				
			}			
		}
		
		//设置错误信息
		function setErrorInfoRegion(errMsg)
		{
			document.getElementById("errorMsg").innerHTML = errMsg;		  
		}
		
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('sendTelNum').focus();">
		<form name="actform" method="post">
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
			<input type="hidden" name="additionalInfo" id="additionalInfo" value="<s:property value='additionalInfo' />"/>
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
						<a href="javascript:void(0);">
							3.选择号码
						</a>
						<a href="javascript:void(0);">
							4.输入个人信息
						</a>
						<a href="javascript:void(0);" class="on">
							5.发送凭证到手机
						</a>
						<a href="javascript:void(0);">
							6.完成
						</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712" id="errorMsg"></div>
					<div class="blank40"></div>
					<div class="p40 pr0">
						<p class=" tit_info"><span class="bg"></span>请输入接收预订凭证的手机号码，按"确认"提交。</span></p>
                       	<div class="line w625"></div>
						<div class="blank10"></div>
						<div class="custom_money pl30 fl">
							<span class="pl40 fs20 fl lh48"><s:property value='certname'/>手机号码：</span>
							<input type="text" name="sendTelNum" id="sendTelNum" value="" maxlength="11" class="text1 fl fs24" onclick="changObj(this, 1);" onfocus="MoveLast(event);">											
       					</div>
       					<div class="blank10"></div>
       					<input type="hidden" name="contacttel" id="contacttel" value="" maxlength="12" class="text1 fl" onclick="changObj(this, 2);" onfocus="MoveLast(event);">
       					
       					<div class="keyboard_wrap mt10 pl20 clearfix">
        					<div class="fl btn_back_box pt200">
        						<p class="fs16 pl10">&nbsp;&nbsp;</p>        							
        					</div>
        					<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
        						<div class=" numbox clearfix">
             						<div class="clearfix">
             							<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
           							</div>
            						<div class="clearfix"> 
            							<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            						</div>
            						<div class="nleft"> 
            							<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
            						</div>
            						<div class="nright"> 
            							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
            						</div>
            						<div class="blank10"></div>
          						</div>
        					</div>
        					<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var type = 1;
								var numBoardText = document.getElementById('sendTelNum');
								
								for (i = 0; i < numBoardBtns.length; i++)
								{
					    			if (!numBoardBtns[i].className) 
					    			{
					    				numBoardBtns[i].className = '';
					    			}
					    			//// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
				     				if (numBoardBtns[i].getAttribute("name") == 'functionkey')
				     				{
				     					continue;
				     				}  
					 
									numBoardBtns[i].onmousedown = function()
									{						 
						  				this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function()
									{					  				
						  				changValue(0, this.innerHTML);
						  				
						  				this.className = '';
						  				
						  				if (pangu_getStrLen(trim(numBoardText.value)) == 11 
						  						//&& document.forms[0].contacttel.value == ""
						  						) 
							 			{
							 				//document.forms[0].contacttel.focus();
							 				
							 				//changObj(document.forms[0].contacttel, 2);
							 			}			      							   
									}
								}
								
								function changObj(o, t)
								{
									type = t;
									
									document.getElementById("errorMsg").innerHTML = "";
									
									numBoardText = o;
								}	
								
								function changValue(t, v)
								{
									if (t == -1)
									{
										numBoardText.value = numBoardText.value.slice(0, -1);
									}
									else if(t == -2)
									{
										numBoardText.value = "";
									}
									else if (type == 1 && numBoardText.value.length < 11)
									{			
										numBoardText.value += v;																		
									}
									else if (type == 2 && numBoardText.value.length < 12 && !isNaN(v))
									{
										numBoardText.value += v;
									}
									
									var r = numBoardText.createTextRange(); 
									r.collapse(false); 
									r.select();
								}		
              				</script>
        				</div>
					</div>
				</div>		
			</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
