<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
// add begin qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
errorMsg = CommonUtil.errorMessage(errorMsg);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 关于整改自助缴费机安全漏洞的需求
	
	
	// add begin cKF76106 2012/08/21 R003C12L08n01 OR_HUB_201206_96
	String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	// 菜单支持营销推荐活动标志
	String actFlag = "";

	// modify begin hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
	// 支持营销推荐活动的菜单
    List<DictItemPO> actMenuList = null;
    
    // 原营销推荐活动开关 1：开  0：关 
    String recommendSwitchOld = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND);
    
    // 新营销推荐活动开关1：开  0：关
    String recommendSwitchNew = (String)PublicCache.getInstance().getCachedData(Constants.SH_ACT_RECOMMEND_NEW);
    
    // 新营销推荐活动
    if("1".equals(recommendSwitchNew))
    {
        // 获取支持新营销推荐活动的菜单
        actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENUNEW);
    }
    // 原营销推荐活动
    else if("1".equals(recommendSwitchOld))
    {
        // 获取支持旧营销推荐活动的菜单
        actMenuList = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.ACTMENU);
	}
	
	// 支持营销推荐活动的菜单
    if (actMenuList != null)
    {
        for (int i = 0; i < actMenuList.size(); i++)
        {
            DictItemPO dictItemPO = actMenuList.get(i);
            if (currMenuID.equals(dictItemPO.getDictid()))
            {
                actFlag = "1";
                break;
            }
        }
    }
    //  modify end hWX5316476 2015-2-6 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销     
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
			
			/**
			//对号码进行判断
			var pattern = /^\d{11}$/;
			
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
			**/		
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		function doSub()
		{
			
			//对号码进行判断
			//var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "" )
			{
				changObj(document.getElementById('numBoardText1'), 1);
				
				alertRedErrorMsg("请输入正确的宽带账号");
				
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				alertRedErrorMsg("两次输入的宽带账号必须相同");
				//document.getElementById("errorMsg").innerHTML = "两次输入的手机号码必须相同";
				
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				// modify begin cKF76106 2012/08/21 R003C12L08n01 OR_HUB_201206_96
				// 现金缴费支持营销推荐活动
				if("<%=actFlag%>" == "1")
				{
					<%session.setAttribute(Constants.ALREADY_REC_FLAG, "1");%>
					
					// modify begin hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
					if("<%=recommendSwitchNew%>" == "1")
					{
						document.actform.action = "${sessionScope.basePath}recommendActivity/qryRecommendActList.action";
					}
					else
					{
						document.actform.action = "${sessionScope.basePath}recommendProduct/getRecommendProductList.action";
					}
					// modify end hWX5316476 2015-2-9 V200R005C10LG0201 OR_HUB_201501_96_湖北_自助终端存量活动主动营销
				}
				else
				{
					document.actform.action = "${sessionScope.basePath}broadBandPay/qryWBankList.action";
				}
				// modify end cKF76106 2012/08/21 R003C12L08n01 OR_HUB_201206_96
				
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
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30"></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.输入宽带账号</i>
          							<span class="pl20 fl lh75">宽带账号：</span>
            						<input type="text" id="numBoardText1" maxlength="16" name="servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus=""/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. 再次输入宽带账号</i>
          							<span class="pl20 fl lh75">再次输入：</span>
            						<input type="text" id="numBoardText2" maxlength="16" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus=""/>
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
						    		//alert (numBoardBtns[i].getAttribute("name")+"|"+numBoardBtns[i].id+"|"+numBoardBtns[i].className);
						    		// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
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
							  			
							  			/**
							  			//对号码进行判断
										var pattern = /^\d{11}$/;
										
							  			if (pattern.test(trim(lastObj.value))
							  					&& document.getElementById("numBoardText2").value == "")
										{
											numBoardText1输入完毕，自动跳转到numBoardText2
											document.getElementById("numBoardText2").focus();
											
											changObj(document.getElementById("numBoardText2"), 2);
											
											return true;
										}	
										**/	
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
									else if (lastObj.value.length < 16 && !isNaN(v))
									//else if (!isNaN(v))
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
	<script type="text/javascript">
		if ("" != "<%=errorMsg %>")
		{			
			alertRedErrorMsg("<%=errorMsg %>");
		}
	</script>
</html>
