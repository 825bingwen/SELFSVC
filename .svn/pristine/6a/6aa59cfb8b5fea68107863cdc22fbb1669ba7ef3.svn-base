<%--
create by sWX219697 2014-6-4 10:48:31 OR_huawei_201405_877
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
		
    String errormessage = (String)request.getAttribute("errormessage");
    if (errormessage == null)
    {
    	errormessage = "";
    }
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String selectReason = (String)request.getAttribute("selectReason");
    if (selectReason == null)
    {
    	selectReason = "";
    }

%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NG2.3自助终端系统补打密码认证</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script type="text/javascript">

var submitFlag = 0;
		
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
		
document.onkeydown = pwdKeyboardDown;		

//计算字符串的长度
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
			etarget.value = backString(etarget.value);
		
			var code = document.actform.detailPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.detailPwd.focus();
				return true;
			}
		}	
	<%
	}
	else
	{
	%>	
		// 返回
		if (key == 82 || key == 220)
		{
			goback("<%=currMenuID %>");
			return;
		}		
		//更正
		else if (key == 8 || key == 32 || key == 66 || key ==77)
		{
			var etarget = getEventTarget(e);
			etarget.value = backString(etarget.value);
		
			var code = document.actform.detailPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.detailPwd.focus();
				return true;
			}
		}
	<%
	}
	%>
}
		
document.onkeyup = pwdKeyboardUp;
		
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

//确认
function doSub() 
{
	var detailPwd = document.actform.detailPwd.value;
	
	if (trim(detailPwd) == "" || isNaN(detailPwd) || pangu_getStrLen(trim(detailPwd)) != 6) 
	{
		document.actform.detailPwd.focus();
		document.actform.detailPwd.select();
		document.getElementById("errorMsg").innerHTML = "请输入正确的补打密码";
		
		return;
	}
	
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		//异步验证补打密码
		authPwd();
    }
}

//异步验证密码
function authPwd()
{
    //创建ajax核心 xmlHttpRequest            
    XmlHttpRequest = false;      
    //下面需要建立一个XMLHttpRequest对象,用它进行服务器请求,针cf 不同浏览器建立方法不同      
	if (window.XMLHttpRequest)      
	{ 
	    // Mozilla, Safari,...      
		XmlHttpRequest = new XMLHttpRequest();      
		if (XmlHttpRequest.overrideMimeType)      
		{      
		    XmlHttpRequest.overrideMimeType('text/xml');      
		}      
	}      
	else if (window.ActiveXObject)      
	{ // IE      
        try             
		{      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		}      
		catch (e)      
		{      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		}      
	}      
		     
	if (!XmlHttpRequest) 
    {      
        // alert('出现错误,不能建立一个XMLHTTP实例!');      
		return false;      
	}    
		
	var detailPwd = document.actform.detailPwd.value;
	var postStr ="detailPwd="+detailPwd;   
		
	//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名  
	XmlHttpRequest.onreadystatechange=CallBack;
	
	//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址 
	XmlHttpRequest.open("POST","<%=basePath %>cdrquery/authDetailPrintPwd.action",true);    
	XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
	
	//开始向服务器发送xmlHttpRequest    
	XmlHttpRequest.send(postStr);   
}

//回调函数
function CallBack()
{
	if((XmlHttpRequest.readyState==4)&&(XmlHttpRequest.status==200))   
	{   
		var msgWelcome=XmlHttpRequest.responseText;
		
		//输入的补打密码不正确
		if(msgWelcome == 0)
		{
			document.getElementById("errorMsg").innerHTML = "补打密码错误！";
			document.actform.detailPwd.value="";
			document.actform.detailPwd.focus();
			submitFlag = 0;//改变状态位，使上一页按钮可用
		    return;
		}
		
		//填写正确，打印详单，并跳转
		if(msgWelcome == 1)
		{
		    //打印详单
			//printInfo();
			
			//跳转至详单展示页面
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}cdrquery/qryDetailedRecords.action";
			document.actform.submit();
		}
	}
}

//打印详单
function printInfo()
{
	//根据详单类型获取对应的详单名称
	var cdrName = {'FIXFEE':'套餐及固定费详单','GSM':'通话详单','SMS':'短/彩信详单',
		'GPRSWLAN':'上网详单','ISMG':'增值业务扣费记录 ','INFOFEE':'代收费业务扣费记录'};
	var cdrType = "<s:property value='cdrType' />";
    var printTime = getCurrentTime();

    //打印
  	doPrint_SDNew('1', "<s:property value='cdrType' />", '${sessionScope.CustomerSimpInfo.servNumber }', 
  			'<s:property value="startDate" />', '<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', 
  			printTime, '${sessionScope.basePath}', '<s:property value="curMenuId" />', cdrName[cdrType], 
  			'<s:property value="chQueryDate" escape="false"/>', '<s:property value="chStartDate" escape="false"/>',
  			'<s:property value="chEndDate" escape="false"/>', '<s:property value="custName" escape="false"/>',
  			"<s:property value='iscycle' />", "<s:property value='cycle' />", "<s:property value='month' />");
  			
}

//记录清单打印次数
function InsertPrintInfo()
{
    var url = "${sessionScope.basePath }cdrquery/updatePrintTimes.action";
			
	var loader = new net.ContentLoader(url, null, null, "POST", "cdrType=<s:property value='cdrType' />", null);    		
}

//上一页按钮
function goback(curmenu) 
{
	//已经点击了确认或返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action="${sessionScope.basePath}cdrquery/qryDetailedRecords.action";
		document.actform.submit();
	}		
}

// 点击输入框，光标定位在文本内容后
function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
	r.select();
}
</script>
	</head>
	<body scroll="no" onload="document.getElementById('detailPwd').focus();">
		<form id="actform" name="actform" method="post">
		<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />" />
			<input type="hidden" name="iscycle" value="<s:property value='iscycle' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="month" value="<s:property value='month' />" />
			<input type="hidden" name="chQueryDate" value="<s:property value="chQueryDate" escape="false"/>" />
			<input type="hidden" name="chQueryDate" value="<s:property value="chQueryDate" escape="false"/>" />
			<input type="hidden" name="chStartDate" value="<s:property value="chStartDate" escape="false"/>" />
			<input type="hidden" name="chEndDate" value="<s:property value="chEndDate" escape="false"/>" />
			
			<input type="hidden" id="selectReason" name="selectReason" value="<%=selectReason %>" />	
			
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 hidden">
					<div class="blank30" id="errorMsg"></div>
					
    				<div class=" pl62">
    					<p class="fs18" style="visibility:hidden;">为确保您的个人信息安全，请输入短信密码。</p>
    					<div class="blank30"></div>
      					<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
      					
      					<!--键盘+输入框+温馨提示-->
      					<div class="keyboard_wrap authentication authentication_2n ">
      						<ul class="phone_num_list fl">
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          						</li>
          						
          						<li class="clearfix pt10 hidden" style="height:auto;">
          							<p class=" fs18 fl lh10 yellow">请联系营业厅管理员输入补打密码</p>          							
          						</li>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          							<div class="blank10"></div>
          							<span id="redstar1" style="font-size:18px;">详单补打密码：</span>
          							<br />
            						<input type="password" id="detailPwd" name="detailPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(event);"  value="" />
          						</li>
          						<%
          						if ("1".equals(keyFlag))
          						{
          						%>
          						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>温馨提示：</p>
         							<p class="p10">1. 密码输入完毕后，请按"确认"键提交；</p>
         							<p class="p10">2. 如需修改，请按"清除"键。</p>
         						</li>
                     			<%
          						}
          						%>
       					 	</ul>
       					 	
       					 	<!--小键盘-->
        					<div class="numboard numboard_big fl numboard_bg_short" id="numBoard">
        						<div class=" numbox">
            						<div class="blank10"></div>
            						<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
            						<div class="clear"></div>
            						<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            						<div class="clear"></div>
            						<div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> </div>
            						<div class="nright"> <a href="javascript:void(0);" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> </div>
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
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('detailPwd');
								for ( i = 0; i < numBoardBtns.length; i++)
								{
					    			if (!numBoardBtns[i].className) 
					    			{
					    				numBoardBtns[i].className = '';
					    			}
					    			
				     				if (numBoardBtns[i].name=='functionkey')
				     				{
				     					continue ;
				     				}  
					 
									numBoardBtns[i].onmousedown = function() {
						  				this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function() {
  										changValue(0, this.innerHTML);
  										
						  				this.className = '';						  									   
									}					
								}
								
								function changValue(t, v)
								{
									if (t == -1)
									{
										numBoardText.value = numBoardText.value.slice(0, -1);
									}
									else if(t == -2)
									{
										numBoardText.value = ""
									}
									else if (numBoardText.value.length < 6)
									{								
										numBoardText.value += v;																													
									}
									
									var r = numBoardText.createTextRange(); 
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
