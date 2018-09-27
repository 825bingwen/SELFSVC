<%--
 @User: g00140516
 @De: 2011/11/23
 @comment: 新的业务办理页面，增加显示用户当前办理情况
 @remark: create g00140516 2011/11/23 R003C11L11n01 OR_huawei_201111_150
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
	
	// add begin hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能
	// 
	List<DictItemPO> shieldOpenGroup = (List<DictItemPO>)PublicCache.getInstance().getCachedData(Constants.SHIELDOPEN_LIST);
	
	// 是否屏蔽当前菜单的开通按钮
	String isShieldOpen = "";   	
   	// add end hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能
   	
   	String spReceptionFlag = (String)request.getAttribute("spReceptionFlag");
   	
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
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("operType").value = opertype;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }privilege/commitReception.action";
      	document.actform.submit();
	}
}

//弹出确认框
openWindow = function(id)
{
	divFlag = "popup_confirm";
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
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R 退出
		if ((key == 82 || key == 220) &&　divFlag == '')
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
			callAjax(1);
		}
		// 2键
		else if(key == 50 &&　divFlag == '')
		{
			callAjax(0);
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
		
		document.getElementById("curMenuId").value = menuid;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}

// 提交梦网订购业务
function orderSP()
{
	//防止重复提交
	if (submitFlag == 0) 
  	{
		submitFlag = 1;
		
		openRecWaitLoading_NX("recWaitLoading");
		
		document.actform.action = "${sessionScope.basePath }privilege/orderSP.action";
		document.actform.submit();
	}
}

</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="operType" id="operType" value="" />
			<input type="hidden" name="nCode" value="<s:property value='#request.nCode' />" />
			<!-- add begin wWX217192 2015-04-03 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求 -->
			<input type="hidden" name="spid" value="<s:property value='spid' />"/>
			<input type="hidden" name="bizid" value="<s:property value='bizid' />"/>
			<!-- add end wWX217192 2015-04-03 OR_SD_201502_373_山东_关于自助终端承载和娱乐包新业务的支撑需求 -->
			
			<%@ include file="/titleinc.jsp"%>
			<%-- add begin hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能 --%>
			<%
			if (shieldOpenGroup != null)
		   	{
		   		for (int i = 0; i < shieldOpenGroup.size(); i++)
		   	    {
		   	        DictItemPO dictItemPO = shieldOpenGroup.get(i);
		   	        
		   	        // 当前菜单为需要屏蔽开通按钮的
		   	        if (curMenuId.equals(dictItemPO.getDictid()))
		   	        {
		   	        	// 需要屏蔽开通按钮
		   	        	isShieldOpen = "1";
		   	            break;
		   	        }
		   	    }
			}
			%>
			<%-- add end hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能 --%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box ring_tone m0auto">
					<ul class="curr_meal clearfix">
						<% 
						if("1".equals(spReceptionFlag))
						{
						%>
						<li><span>您选择办理：</span><span class="charge_name"><%=menuName %></span></li>
						<%
						}
						else
						{
						 %>
						<li><span>您选择办理：</span><span class="charge_name"><%=menuName %>业务</span></li>
						<%
						}
						 %>
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
    					<%-- modify begin hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能 --%>
    					<%
    					// 支持金属键盘
						if ("1".equals(keyFlag))
						{
							// 屏蔽开通按钮
							if("1".equals(isShieldOpen))
							{
						%>
								<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">取消业务(按2键)</a></span>
							<%
							}
							else
							{
							%>
							<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(1);">开通业务(按1键)</a></span>
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">取消业务(按2键)</a></span>
						
						<%
							}
						}
						else if("1".equals(isShieldOpen))
						{
						%>
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">取消业务</a></span>
						<%
						}
						else if ("1".equals(spReceptionFlag))
						{
						%>
							<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindow('popup_confirm');return false;">订购</a></span>
						<%
						}
						else
						{
						%>
							<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(1);">开通业务</a></span>
							<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';callAjax(0);">取消业务</a></span>
						<%
						}
						%>
						<%-- modify end hWX5316476 2014-07-03 OR_huawei_201407_86 营业厅全业务流程优化-业务分流(自助终端)_国际长话及漫游业务屏蔽开通功能 --%>
    				</div>
    			</div>
                  <!--box end--> 
                  
               <div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
                  	<%
                  	if ("1".equals(spReceptionFlag))
					{
					 %>
				    <p>您选择办理： <i>开通<%=menuName %></i><i>业务。</i></p>
				    <%
				    }
				    else
				    {
				    %>
				    <p>您选择办理： <i id="operTypeName"></i><i id="menuName"></i><i>业务</i><i id="feeMessage"></i></p>
				    <%
				    }
				     %>
				    <div class="blank10"></div>
				    <p class="mt30">确认办理请点击"确认"提交。</p>
				  </div>
                  <div class="btn_box tc mt20">
	                 
	                  	<%
						if ("1".equals(keyFlag))
						{
						%>
							 <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认(按确认键)</a></span>
	                  		 <span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消(按清除键)</a></span>
						<%
						}
						else if("1".equals(spReceptionFlag))
						{
						%>
							 <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';orderSP();">确认</a></span>
	                 		 <span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
						<%
						}
						else
						{
						%>
							<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">确认</a></span>
	                 		<span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
						<%
						}
						%>
						
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
