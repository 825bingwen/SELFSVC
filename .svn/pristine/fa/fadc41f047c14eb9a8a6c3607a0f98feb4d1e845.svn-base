<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="java.util.Map"%>
<%
	String sType = request.getParameter("sType");
	String isInput = request.getParameter("isInput");
	
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
	
	String selectReasonFlag = (String) PublicCache.getInstance().getCachedData("SH_SELECTREASON_FLAG");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>停开机</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 弹出div
var divFlag = "";

var opertype;
var XmlHttpRequest;   
function callAjax(operType)
{
//创建ajax核心 xmlHttpRequest            
XmlHttpRequest = false;      
//下面需要建立一个XMLHttpRequest对象,用它进行服务器请求,针cf 不同浏览器建立方法不同      
if (window.XMLHttpRequest)      
{ // Mozilla, Safari,...      
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
    alert('出现错误,不能建立一个XMLHTTP实例!');      
    return false;      
}    

//此处是设置全局opertype的值，选择业务时确定值，确认提交时使用
opertype = operType;

var curMenuId = document.getElementById("curMenuId").value;
var postStr ="operType="+operType+"&sType=<%=sType%>&isInput=<%=isInput%>";   
   
XmlHttpRequest.onreadystatechange=callBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
XmlHttpRequest.open("POST","${sessionScope.basePath }baseService/feeMessage.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
XmlHttpRequest.send(postStr);//开始向服务器发送xmlHttpRequest   
}   
  
//<!--回调的方法-->   
function callBack()
{
       
    if(XmlHttpRequest.readyState==4)   
    {   
        if(XmlHttpRequest.status==200)//以上两个判断 确定ajax请求已被成功相应   
        {   
        	var msgWelcome=XmlHttpRequest.responseText; 
        	
        	//以","分割返回字符串
	       	var str = msgWelcome.split(",");  
	       	var confirmMsg = document.getElementById("confirmMsg");  
            document.getElementById("sType").value=str[1];  
            if(str[2] == "开机")
			{
				document.getElementById("operType").value = 1;
			}
			else
			{
				document.getElementById("operType").value = 0;
			}
			
            document.getElementById("isInput").value=str[3];
            //赋值
            confirmMsg.innerHTML=str[0];
            
            //弹出确认框
	       	openWindow('popup_confirm');	
        }   
    }   
}



//提交业务
function submitReception()
{      		
	//防止重复提交
	if (submitFlag == 0) 
	{
		var sType = document.getElementById("sType").value;
		var operType = document.getElementById("operType").value;
		var isInput = document.getElementById("isInput").value;
		submitFlag = 1;
		if ("SHBalanceUD" == sType)
		{
			document.actform.actionCase.value = "SHBalanceUD";
		}
		else
		{
			if (isInput == "1")
			{
				if ("0" == operType)
				{
					document.actform.actionCase.value = sType;
				}
					
				if ("1" == operType)
				{
					document.actform.actionCase.value = sType + "Page";
				}
			}
			else if (isInput == "2")
			{
			  	document.actform.actionCase.value = "commonServ2";
			}
			else if (isInput == "0" && sType == "<%=Constants.BUSITYPE_STOPOPEN%>")
			{
				if ("0" == operType)
				{
					document.actform.actionCase.value = "toValidate";
				}
				
				if ("1" == operType)
				{
					document.actform.actionCase.value = sType + "Page";
				}
			}
			else
			{
			 	document.actform.actionCase.value = "commonServ";	
			}
	}
	
	// add begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	openRecWaitLoading_NX("recWaitLoading");
	// add end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	
	document.actform.action="${sessionScope.basePath }baseService/recOpenAndStopSubs.action";
	
	// 宁夏，业务类型是停开机，转向停开机原因选择页面
	<%
		if(sType.equals("SHstopOpen") && "1".equals(selectReasonFlag))
		{
	%>
			document.actform.action="${sessionScope.basePath }baseService/selectReason.action";
	<%
		}
	%>
	
	document.actform.submit();
}
}

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;
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


function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if ((key == 82 || key == 220) && divFlag == '')
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		// 1键
		if(key == 49 &&　divFlag == '')
		{
			callAjax(0);
		}
		// 2键
		else if(key == 50 &&　divFlag == '')
		{
			callAjax(1);
		}
		// 确认键
		else if(key == 89 &&　divFlag != '')
		{
			submitReception();
		}
		// 清除键
		else if(key == 77 &&　divFlag != '')
		{
			windowClose();
		}
	<%
	}
	%>
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

function trim(str) 
{
   while (str.charAt(str.length - 1)==" ") 
   {
     str = str.substring(0, str.length - 1);
   }
   while (str.charAt(0)==" ") 
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
           count = count + 2;                
       else                                  
           count = count + 1;                
   }                                         
   return count;                             
}  

openWindow = function(id)
{
	divFlag = id;
	wiWindow = new OpenWindow("popup_confirm",708,392);//打开弹出窗口例子
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}
</script>
</head>

<body scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" id="actionCase" name="actionCase" value="" />
		<input type="hidden" id="sType" name="sType" value="" />
		<input type="hidden" id="operType" name="operType" value="" />
		<input type="hidden" id="isInput" name="isInput" value="" />
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">			
			<%@ include file="/customer.jsp"%>
			
					  <div class="service_brand w966 m0auto">
					    <div class="service_index_list">
					    <p class="hot_service"></p>
					    	
					    	<div class="p40">
      
								<div class="blank10"></div>
								<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择业务类型：</span></p>
								<div class="blank25"></div>
								<div class="blank25"></div>
        						<div class="btn_box tc">
       								<%
									if ("1".equals(keyFlag))
									{
									%>
										 <span class=" mr10 inline_block ">
											<a title="停机或者取消业务" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">
												 <s:if test="sType == 'SHstopOpen'">
											     	停机申请(按1键)
											    </s:if>
											    <s:else>
											    	取消业务(按1键)
											    </s:else>
											</a>
										</span>
										<span class=" mr10 inline_block ">
											<a title="开机或者申请业务" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(1);">
												<s:if test="sType == 'SHstopOpen'">
											     	开机申请(按2键)
											    </s:if>
											    <s:else>
											    	申请业务(按2键)
											    </s:else>
											</a>
										</span>
									<%
									}
									else
									{
									%>
										<span class=" mr10 inline_block ">
											<a title="停机或者取消业务" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">
												 <s:if test="sType == 'SHstopOpen'">
											     	停机申请
											    </s:if>
											    <s:else>
											    	取消业务
											    </s:else>
											</a>
										</span>
										<span class=" mr10 inline_block ">
											<a title="开机或者申请业务" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(1);">
												<s:if test="sType == 'SHstopOpen'">
											     	开机申请
											    </s:if>
											    <s:else>
											    	申请业务
											    </s:else>
											</a>
										</span>
									<%
									}
									%>			
									
			    				</div>
        					</div>
					    </div>
					  </div>
					
					<div class="popup_confirm" id="popup_confirm">
	                  <div class="bg"></div>
	                  <div class="tips_title">提示：</div>
	                  <div class="tips_body">
					    <p id="confirmMsg"></p>
					    <div class="blank10"></div>
					  </div>
					    <%
						if ("1".equals(keyFlag))
						{
						%>
							 <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认(按确认键)</a></span>
				                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消(按清除键)</a></span>
			                 </div>
						<%
						}
						else
						{
						%>
							 <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认</a></span>
				                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
			                 </div>
						<%
						}
						%>			
	                  
	                </div>
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
