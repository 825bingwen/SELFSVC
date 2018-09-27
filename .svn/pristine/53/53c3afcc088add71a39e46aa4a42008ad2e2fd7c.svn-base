<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>

<%
// 当前产品PO
ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);

// 产品名称
String prodName = "";

// 操作类型
String opertype = (String)request.getAttribute("opertype");

if(null != prodConfigPO.getProdName())
{
	prodName = prodConfigPO.getProdName();
}

// 生效方式
String effect = "";

// 开通
if("PCOpRec".equals(opertype))
{
	effect = prodConfigPO.getEffectRec();
}
// 变更
else if("PCOpMod".equals(opertype))
{
	effect = prodConfigPO.getEffectMod();
}
// 退订
else if("PCOpDel".equals(opertype))
{
	  effect = prodConfigPO.getEffectDel();
}
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>产品详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script>
var submitFlag = 0;

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
	//更正
	if (key == 8 || key == 32 || key == 66 || key ==77)
	{
		
		var etarget = getEventTarget(e);
		if (etarget.type == "text" ) 
		{
			etarget.value = backString(etarget.value);
		}
	}
	else if (key == 82 || key == 220)
	{
		goback();
		return;
	}
}

//弹出确认框
openWindow = function(id,opertype)
{
	// 操作类型
	document.getElementById("opertype").value = opertype;

	// 退订时，修改弹出div提示
	if(opertype == "PCOpDel")
	{
		document.getElementById("tipContent").innerHTML = "您选择退订：";
	}
	wiWindow = new OpenWindow("popup_confirm",708,392);
}

// 产品附加属性选择，btnDiv：附加属性ID,btn:点击的按钮，btClass：按钮要变更成的样式
function btnClick(btnDiv, btn, btClass)
{
	var inputFlag = document.getElementById("inputFlag").value;
	var btns=document.getElementById(btnDiv).getElementsByTagName('a');
	for(i=0;i<btns.length;i++)
	{
		btns[i].className=btClass;
	}
	btn.className=btClass+'on';
	if(btn.id == "5")
	{
		document.getElementById('strartTr').style.visibility='visible';
		document.getElementById('endTr').style.visibility='visible';	
	}
	else if(inputFlag == "1")
	{
		document.getElementById('strartTr').style.visibility='hidden';
		document.getElementById('endTr').style.visibility='hidden';	
	}	
}

// 产品受理校验
function prodRecCheck(operType)
{
	// 标志：只有指定生效时间一种生效方式
	var justDateFlag = document.getElementById("justDateFlag").value;
	
	if(justDateFlag == "1" )
	{
		// 生效时间
		var startDate = document.getElementById("startInput").value;
		// 失效时间
		var endDate = document.getElementById("endInput").value;
		
		var datePattern = /^(\d{4})(0\d{1}|1[0-2])(0\d{1}|[12]\d{1}|3[01])(0\d{1}|1\d{1}|2[0-3])[0-5]\d{1}([0-5]\d{1})$/;
					
		if(!datePattern.test(startDate) || !datePattern.test(endDate))
		{
			alertRedErrorMsg("时间输入格式错误，请重新输入");
			return;
		}
		document.getElementById("startDate").value = startDate;	
		document.getElementById("endDate").value = endDate;	
		document.getElementById("effectType").value = "5";			
	}
	
	// 多种生效方式
	else
	{
		var btns = document.getElementById("effectSelect").getElementsByTagName('a');
		var cnt = 0;
		var btn;
	    for(var j=0;j<btns.length;j++)
		{
			if(btns[j].className == "bt13on")
			{
				cnt++;
				
				btn = btns[j].id;		
			}
		}
		if(cnt == 0)
		{
			alertRedErrorMsg("请选择生效方式");
			return;
		}
		if(btn == "5")
		{
			// 生效时间
			var startDate = document.getElementById("startInput").value;
			// 失效时间
			var endDate = document.getElementById("endInput").value;
			
			var datePattern = /^(\d{4})(0\d{1}|1[0-2])(0\d{1}|[12]\d{1}|3[01])(0\d{1}|1\d{1}|2[0-3])[0-5]\d{1}([0-5]\d{1})$/;
						
			if(!datePattern.test(startDate) || !datePattern.test(endDate))
			{
				alertRedErrorMsg("时间输入格式错误，请重新输入");
				return;
			}
			document.getElementById("startDate").value = startDate;	
			document.getElementById("endDate").value = endDate;	
		}
		// 设置生效方式	
		document.getElementById("effectType").value =btn;
	}
	
	openWindow(popup_confirm,operType);
}

// 产品受理
function doSub()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		
		openRecWaitLoading('recWaitLoading');
		
		document.actform.target = "_self";
		var prodType = '<%=prodConfigPO.getProdType() %>';
		document.actform.target = "_self";
		if (prodType == '2' || prodType == '3')
		{
			document.actform.action = "${sessionScope.basePath}quickPublish/prodPackegRec.action";
		}
		else if (prodType == '1')
		{
			document.actform.action = "${sessionScope.basePath}quickPublish/prodRec.action";
		}
		document.actform.submit();
	}
}	

function moveLast(id)
{
	var r = document.getElementById(id).createTextRange(); 
	r.collapse(false); 
	r.select(); 
}	
</script>
</head>
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" id="opertype" name="opertype" value="<s:property value='opertype'/>"/>
		<input type="hidden" id="effectType" name="effectType" value=""/>
		<input type="hidden" id="startDate" name="startDate" value=""/>
		<input type="hidden" id="endDate" name="endDate" value=""/>
		<!-- 标志：生效方式只有指定生效时间 -->
		<input type="hidden" id="justDateFlag" name="justDateFlag" value=""/>
		<input type="hidden" id="selectAttrStr" name="selectAttrStr" value="<s:property value='selectAttrStr'/>"/>
		<!-- 标志：生效方式有多种，且有一种是指定生效时间 -->
		<input type="hidden" id="inputFlag" name="inputFlag" value=""/>
				
		<!-- 选中的子产品 -->
		<input type="hidden" id="childProdIds" name="childProdIds" value="<s:property value='childProdIds'/>"/>
		<!-- 选中的子产品属性 -->
		<input type="hidden" id="childProdAddAttrs" name="childProdAddAttrs" value="<s:property value='childProdAddAttrs'/>"/>
		<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="1"/>
		<input type="hidden" id="searchType" name="searchType" value="<s:property value='searchType'/>" />
		<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
		<input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
		
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">		
			<%@ include file="/customer.jsp"%>
			<div class="b966">
				<div class="blank30"></div>
				<div class="relative p40">
				<p class="tit_info" align="left"><span class="bg"></span>设置生效方式</p>
				<table width = "100%" >
             		<%
          			if(effect.length()>1)
          			{
           				String[] effectArray = effect.split(",");
             		%>

             		<tr id="effectSelect">
                    <td colspan="2" style="text-align:left">
                    <div class="blank20" ></div>
                    <%
                    	for (int i=0; i<effectArray.length; i++)
                    	{
                    	   //生效方式 2：立即 3：次月 4：次日 5：指定生效时间
                    	   String effectName = "";
                    	   if("2".equals(effectArray[i]))
                    	   {
                    	    	effectName = "立即生效";
                    	   }
                    	   else if("3".equals(effectArray[i]))
                    	   {
                    	   		effectName = "次月生效";
                    	   }
                    	   else if("4".equals(effectArray[i]))
                    	   {
                    	   		effectName = "次日生效";
                    	   }
                    	   else if("5".equals(effectArray[i]))
                    	   {
                    	   		effectName = "指定生效时间";
                    	   }
                    %>
                    		<a href="javascript:void(0)" id ="<%=effectArray[i] %>" class="bt13" style = "display:inline-block;"; onclick="btnClick('effectSelect',this,'bt13','1')"><%=effectName %></a>		
            		<%	   
                    	}
                   	%>
                   	</td>
                    </tr>
                    <%
                    	for(int i=0; i<effectArray.length; i++)
                    	{
							if("5".equals(effectArray[i]))
                    	    {  	   
                    %>
                     	<tr id="strartTr" style="visibility:hidden" >
                      	<td width = "30%" >
                      	<div class="blank20" ></div>
                      	<span style="font-size:18px" >生效时间:</span></br><span class="yellow fs16" >(格式：20120101093030)</span></td>
                      	<td>
                      	<div class="blank20" ></div>
                      	<input type="text" id="startInput" maxlength="14" name="startInput" value ="" class="text3 fl" onclick = "moveLast('startInput');"/>
                 		</td>
                		</tr>
                		<tr id="endTr" style="visibility:hidden">
                      	<td width = "30%" ><span style="font-size:18px" >失效时间:</span></br><span class="yellow fs16" >(格式：20120101093030)</span></td>
                      	<td>
                      	<input type="passward" id="endInput" maxlength="14" name="endInput" value ="" class="text3 fl" onclick = "moveLast('endInput');"/>
                 		</td>
                		</tr>
                		<script>
           					document.getElementById("inputFlag").value = "1";		
           				</script>
                    <%
                    		}
                    	}
                    %>
		           <%					
		                }
             			else if(effect.equals("5"))
             			{
                   %>
                   	<tr>
                    	<td width = "30%" >
                    	<div class="blank20" ></div>
                    	<span style="font-size:18px" >生效时间:</span></br><span class="yellow fs16" >(格式：20120101093030)</span></td>
                    	<td>
                    	<div class="blank20" ></div>
                    	<input type="text" id="startInput" maxlength="14" name="startInput" value ="" class="text3 fl" onclick = "moveLast('startInput');"/>
               			</td>
              			</tr>
              			<tr>
                    	<td width = "30%" ><span style="font-size:18px" >失效时间:</span></br><span class="yellow fs16" >(格式：20120101093030)</span></td>
                    	<td>
                    	<input type="text" id="endInput" maxlength="14" name="endInput" value ="" class="text3 fl" onclick = "moveLast('endInput');"/>
               			</td>
              			</tr>
						<script>
           					document.getElementById("justDateFlag").value = "1";		
           				</script>
                   <%
                   		}	
              	   %>
              	</table>
              	<table width = "100%">
              		<div class="blank20" ></div>
              		<tr>
              			<td width = "50%" style="text-align:right"><input type="button" class="bt2_liebiao white" value="确定" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';prodRecCheck('<%=opertype %>');"/></td>		
              			<td style="text-align:left"><input type="button" class="bt2_liebiao white" value="返回" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';goback();"/></td>			
              		</tr>		
                  </table>					
				</div>
			</div>
		</div>
			
		<div class="popup_confirm" id="popup_confirm">
              <div class="bg"></div>
	              <div class="tips_title">提示：</div>
	              <div class="tips_body">
			      <p><i id="tipContent">您选择办理： </i><i><%=prodName %></i><i>业务</i></p>
			      <div class="blank10"></div>
			      <p class="mt30">确认操作请点击"确认"提交。</p>
		      </div>
              <div class="btn_box tc mt20">
              	<span class="mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">确认</a></span>
              	<span class="inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
              </div>
        </div>       
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">
	// 返回产品列表
	function goback()
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			if ('2' == '<s:property value="searchType" />' || '3' == '<s:property value="searchType" />')
			{
				document.getElementById("curMenuId").value = "rec";
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
				document.forms[0].submit();
			}
			else
			{
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
				document.actform.submit();
			}
		}	
	}
</script>
</html>
