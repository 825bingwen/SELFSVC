<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String successBz = (String)request.getAttribute("successBz");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}css/IE8.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/ajax_0.1.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript">
var submitFlag = 0;
var checkBz = 0;//身份证是否已被限制使用 0:未限制 1:已限制
var checkText = '';//身份证受限时的提示信息

// 读卡等待时间
var limitTime = 30;
		
//计算读卡剩余时间的句柄
var timeToken;
		
//等待读卡的句柄
var waitingToken;

// 初始化身份证读卡器线程启动标志，1，已启动；0，未启动。如果为1时，用户主动取消操作，需调用关闭身份证阅读器接口
var initCardReader = 0;

function doSub()
{	
	var idCard = trim(document.actform.idCard.value);
	if (idCard == "")
	{
		alertRedErrorMsg("身份证号码不能为空！");
		return ;
	}

	var ren = checkIDNo(idCard);

	if (ren != "1")
	{
		alertRedErrorMsg(ren);
		return;
	}

	/**
	checkIdCard();
	
	if (checkBz == 1)
	{
		document.getElementById('winText').innerHTML = checkText;
		wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
		return;
	}
	**/
	
	//避免重复提交
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }chooseTel/idCardFinish.action";
		document.actform.submit();

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
      
document.onkeyup = pwdKeyboardUp;
      	      
function pwdKeyboardUp(e)
{
	var key	= GetKeyCode(e);
	
	//确认
	if (key == 13 || key == 89 || key == 221)
	{
	   	doSub();	
	   	return ;
	}	  	
	//返回
	else if (key == 82 || key == 220)
	{
	   	goback("<s:property value='curMenuId' />");
	   	return ;
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
	 
function goback(curmenu) 
{
       //防止重复提交
	if (submitFlag == 0) 
	{
		if(closeStatus == 3)
		{
			//  关闭身份证读卡器
			try{
				window.parent.document.getElementById("idcardpluginid").CloseCardReader();
			}catch(e){}
		}
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
		document.actform.submit();
	}
}

//核心对象变量 
var xmlHttp; 

//ajax核心执行方法（此处为提交到servlet处理后,返回纯文本） 
function checkIdCard(){ 
	//区分浏览器创建XMLHttpRequest核心对象 
	if(window.XMLHttpRequset){ 
	  xmlHttp = new XMLHttpRequest(); 
	}else if(window.ActiveXObject){ 
	  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	}
	var idCard = document.getElementById('idCard').value;
	var URL = "${sessionScope.basePath }chooseTel/checkIdCard.action?idCard="+idCard; 
	xmlHttp.open("post",URL,false); 
	xmlHttp.onreadystatechange=callback; 
	xmlHttp.send(null); 
} 

//回调函数 
function callback(){
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			var v = xmlHttp.responseText; 
			checkText = v;
			checkBz = 0;
			if ('' != v)
			{
				checkBz = 1;
			}
		} 
	} 
} 

// 页面载入时加载身份证读卡器获取用户身份证信息
function bodyLoad()
{
	try 
	{				
		//生产用
		var ret = InitIdCardReader();// 设置身份证读卡器为可用状态

		//测试用
		//var ret = 0;
		
		if (ret != "0" && ret != 0) 
		{
			setErrorInfoRegion("初始化身份证读卡器异常，请您手工输入您的身份证号信息。");
            return;
		}
		else
		{		
			//准备刷卡进程启动
			initCardReader = 1;
			
			//等待读卡进程启动成功后，就需要关闭该进程。而”首页“、“退出”按钮无法执行此操作，所以禁用”首页“、“退出”按钮
              	document.getElementById("homeBtn").disabled = true;
              	document.getElementById("quitBtn").disabled = true;
			
			//在调用初始化身份证读卡器后，获取身份证信息之前，需要调用此接口检查一下读卡器状态
			getIdCardStatus();
			
			// 开始记时，并循环调用接口
			startclock();
		}
	}
	catch (ex) 
	{
		setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
        return;
	}
}

//获取身份证读卡器状态
function getIdCardStatus()
{
	try
	{
		//生产用
		var ret = GetIdCardStatus();// 获取身份证读卡器状态
		
		// 测试用
		//var ret = 0;
		
		if (ret != "0" && ret != 0) 
		{					
			setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
		}
		
		// 点击顶部菜单需要关闭身份证打卡器
		closeStatus = 3;
	}
	catch (excep) 
	{		
		setErrorInfoRegion("初始化身份证读卡器异常，请手工输入您的身份证号信息。");
	}
}

//设置时间计算周期
function startclock() 
{	
	
	try 
	{
		setErrorInfoRegion("请在身份证读卡器上刷一下您的身份证，系统将自动将您的身份证号输入到页面的身份证号信息框中。");
		
		// 获取身份证文字内容
		waitingToken = setInterval("getReadCardStatus()", 1000);
		
		// 计时器
		timeToken = setInterval("calLeftTime()", 1000);
	}
	catch (e) 
	{
		setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
	}
}

// 循环调用获取身份证文字内容接口
function getReadCardStatus() 
{
	if (limitTime <= 0)
	{
		return;
	}
	
	try 
	{
		//生产用
		var ret = GetIdCardContent();// 打开仓门接收用户身份证 读完卡后返回状态 （卡放入到读卡器内要把needReturnCard值为1）
		
		//测试用
		//var ret = "0姓名~男~民族~出生~住址~37061218840530402X~签发机关~有效期起始日期~有效期截止日期~最新住址";

		if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
		{	
			//调用关闭身份证阅读器接口
			//生产用
			CloseCardReader();
			
			//可以点击顶部菜单
			closeStatus = 0;
			
			//身份证信息：0姓名~性别~民族~出生~住址~公民身份号码~签发机关~有效期起始日期~有效期截止日期~最新住址
			var idCardInfor = ret.substring(1,ret.length).split('~');
			
			//公民身份号码
			var idCardNo = idCardInfor[5];
			
			//取消定时器
			clearInterval(timeToken);
			
			// 将身份证号嵌入到页面输入框中
			document.getElementById("idCard").value = idCardNo;
		} 
		else if (ret == "-1")
		{
			setErrorInfoRegion("获取身份证文字内容失败，请手工输入您的身份证号信息。");
		}
		else if (ret == "2")
		{	
			setErrorInfoRegion("证件无法识别，请手工输入您的身份证号信息。");
		}						
	}
	catch (e) 
	{		
		setErrorInfoRegion("身份证读卡器读卡失败，请手工输入您的身份证号信息。");
	}
}
		
//计算读卡剩余时间
function calLeftTime()
{
	if (limitTime <= 0)
	{
		return;
	}
	
	//读卡时长一共limitTime秒
	limitTime = limitTime - 1;
	
	document.getElementById("idCard").value = limitTime;
	
	//读卡时间结束
	if (parseInt(document.getElementById("idCard").value) <= 0 && submitFlag == 0)
	{
       	setErrorInfoRegion("打卡器读卡失败，请手工输入您的身份证号信息。");
       	document.getElementById("idCard").value = "";
       	document.getElementById("idCard").focus();
       	
       	//调用关闭身份证阅读器接口
		//生产用
		CloseCardReader();
       	
       	//可以点击顶部菜单
		closeStatus = 0;
	}
}

// 记录错误信息
function setErrorInfoRegion(errMsg)
{
	document.getElementById("errorMsg").innerHTML = errMsg;
}

<%--add begin lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
//手动输入身份证号码
function manualIdCard()
{
	document.getElementById("phone_input_1").style.display="block";
}

//读卡器读入身份证号码
function automaticIdCard()
{
	document.getElementById("phone_input_1").style.display="block";
	bodyLoad();
}
<%--add end lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="telnum" id="telnum" value="<s:property value='telnum' />"/>
			<input type="hidden" name="payfee" id="payfee" value="<s:property value='payfee' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">				
				<%@ include file="/customer.jsp"%>
				<a title="返回选号开户" href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回选号开户</a>
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<%--modify begin lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
								<a title="手动输入身份证号" href="javascript:void(0)" class=" bt3" id="manual" name="manual" onclick="manualIdCard();">手动输入身份证号</a><br/>
								<a title="读卡器读取身份证号" href="javascript:void(0)" class=" bt3" id="automatic" name="automatic" onclick="automaticIdCard();">读卡器读取身份证号</a>
          						<li class="on fs20 clearfix" id="phone_input_1" style="display:none;">
          							<i class="lh30">1.输入身份证号码</i>
          							<span class="pl20 fl lh75">身份证号码：</span>
            						<input type="text" maxlength="18" id="idCard" name="idCard" class="text1 fl relative fs22" onfocus="MoveLast(this)" />
          						</li>
          						<%--modify end lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
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
	            						<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a> <a title="X" href="javascript:void(0)">X</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a title="确认" href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('idCard');
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
									else if (lastObj.value.length < 18 && v != "#")
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
				
				<!--弹出窗-->
				<div class="openwin_tip" id="openWin1">
				  	<div class="bg"></div>
				  	<div class=" blank60"></div>
				  	<div class=" blank60"></div>
				  	<div class="  blank10n"></div>
				  	<p class="fs28 lh2 pl142">
						<span class="yellow">&nbsp;&nbsp;预约限制：</span> <br />
						<span class="ml20" id="winText"></span>
				 	</p>
				  	<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
					    <a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<%--add begin lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
	<script type="text/javascript">
		if (window.parent. is2GIDFlag == "0")
		{
			document.getElementById("manual").style.display = "none";
			document.getElementById("automatic").style.display="none";
			document.getElementById("phone_input_1").style.display="block";
		}
		
	</script>
	<%--add end lWX431760 2017-02-24 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求 --%>
</html>
