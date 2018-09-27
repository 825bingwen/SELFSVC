<%--
 @User: g00140516
 @De: 2011/11/23
 @comment: 新的业务办理页面，增加显示用户当前办理情况
 @remark: create g00140516 2011/11/23 R003C11L11n01 OR_huawei_201111_150
--%>
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

var submitFlag = 0;
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
var postStr ="operType="+operType+"&nCode=<s:property value='#request.nCode' />&curMenuId="+curMenuId;   
   
XmlHttpRequest.onreadystatechange=callBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
XmlHttpRequest.open("POST","${sessionScope.basePath }privilege/feeMessage.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
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
        	var msgWelcome=XmlHttpRequest.responseText;  //返回格式为"开通,全球通"
        	
        	//以","分割返回字符串
	       	var str = msgWelcome.split(",");  
            var operTypeName = document.getElementById("operTypeName");  
            var menuName = document.getElementById("menuName");
            var feeMessage = document.getElementById("feeMessage");
            
            //赋值
            operTypeName.innerHTML=str[0]+"  "; 
            menuName.innerHTML=str[1];
            feeMessage.innerHTML=str[2];
            
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
		submitFlag = 1;
		
		openRecWaitLoading_NX('recWaitLoading');
		
		document.getElementById("operType").value = opertype;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }privilege/commitReception.action";
      	document.actform.submit();
	}
}

//弹出确认框
openWindow = function(id)
{
	wiWindow = new OpenWindow("popup_confirm",708,392);//打开弹出窗口例子
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
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
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
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="operType" id="operType" value="" />
			<input type="hidden" name="nCode" value="<s:property value='#request.nCode' />" />	
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box ring_tone m0auto">
					<ul class="curr_meal clearfix">
						<li><span>您选择办理：</span><span class="charge_name"><%=menuName %>业务</span></li>
						<li>
							<span class="charge_name2">使用状态：</span>
							<span class="charge_name1"><s:property value="statusDesp" /></span>							
						</li>
					</ul>
    				<ul class="ring_detail">
	                    <li class="clearfix li1">
	       					<p class="fl cap">业务介绍：</p>
							<div class="fl ring_info">
								<table width="600" border="0">
									<tr>
										<td width="600" >
											<%=busiDetailPage == null ? "&nbsp;" : busiDetailPage %>
										</td>
									</tr>
								</table>
							 </div>
	      				</li>
						<li class="clearfix li2">
							      <p class="fl cap">温馨提示：</p>
							      <div class="fl ring_info">
							          <p><%=additionalInfo == null ? "&nbsp;" : additionalInfo%></p>
							      </div>						      
						</li>						
                    </ul>
                    <div class="btn_box tc">
	    				<s:if test="currStatus == 1">	    					
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">立即取消</a></span>
	    				</s:if>
	    				<s:else>
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(1);">立即开通</a></span>
	    				</s:else>	    								
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';goback('<%=curMenuId %>');">返回</a></span>
    				</div>
    			</div>
                  <!--box end--> 
                  
               <div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
				    <p>您选择办理： <i id="operTypeName"></i><i id="menuName"></i><i>业务</i><i id="feeMessage"></i></p>
				    <div class="blank10"></div>
				    <p class="mt30">确认办理请点击"确认"提交。</p>
				  </div>
                  <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认</a></span>
	                  <span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
                  </div>
                </div>
                
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<%--add begin g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
	<script type="text/javascript">
	<s:if test="tip != ''">		
		alertSuccessMsg("<s:property value='tip' />");
	</s:if>
	</script>
	<%--add end g00140516 2012/09/17 R003C12L09n01 OR_HUB_201207_1089 --%>
</html>
