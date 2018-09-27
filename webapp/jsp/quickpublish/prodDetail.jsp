<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>

<%

NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
// 当前产品PO
ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);

// 图片路径
String imgPath = "";
// 温馨提示
String prodName = "";
// 业务描述
String busiDesc = "";
// 资费描述
String priceDesc = "";
// 温馨提示
String reminder = "";
// 产品类型  1：普通产品 2：产品包 3：模板 4、同组产品 5、主体产品
String prodType = "";
// 是否有附加属性
String isAddAttr = "";

// 开通生效方式
String effectRec = "";
// 退订生效方式
String effectDel = "";

if(null != prodConfigPO.getImgPath())
{
	imgPath = prodConfigPO.getImgPath();
}

if(null != prodConfigPO.getProdName())
{
	prodName = prodConfigPO.getProdName();
}

if(null != prodConfigPO.getDescription())
{
	busiDesc = prodConfigPO.getDescription();
}

if(null != prodConfigPO.getFeeDesc())
{
	priceDesc = prodConfigPO.getFeeDesc();
}

if(null != prodConfigPO.getReminder())
{
	reminder = prodConfigPO.getReminder();
}

if(null != prodConfigPO.getProdType())
{
	prodType = prodConfigPO.getProdType();
}

if(null != prodConfigPO.getHaveApdAttr())
{
	isAddAttr = prodConfigPO.getHaveApdAttr();
}

if(null != prodConfigPO.getEffectRec())
{
	effectRec = prodConfigPO.getEffectRec();
}

if(null != prodConfigPO.getEffectDel())
{
	effectDel = prodConfigPO.getEffectDel();
}

// 标志：未登录，登录后转向prodDetail.jsp
String loginFlag = "";
if(null != request.getAttribute("loginFlag"))
{
	loginFlag = (String)request.getAttribute("loginFlag");
}

// 产品是否已开通，1：已开通
String isOpen = "";

// 产品是否可以办理 1：可以办理
String isSupportRec = "";

if(null != prodConfigPO.getIsOpen())
{
	isOpen = prodConfigPO.getIsOpen();
}
if(null != prodConfigPO.getIsSupportRec())
{
	isSupportRec = prodConfigPO.getIsSupportRec();
}

// 热门产品受理标志
String hotRecFlag = "";
if(null != request.getAttribute("hotRecFlag"))
{
	hotRecFlag = (String)request.getAttribute("hotRecFlag");
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

//用户已登录进入相应的受理页面，未登录进入登录认证页面
function doSub()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.actform.target = "_self";
		
		openRecWaitLoading('recWaitLoading');
		document.actform.action = "${sessionScope.basePath}quickPublish/prodRec.action";
		
		document.actform.submit();	
	}
}

// 登录办理，拦截器拦截请求登录
function authPassword()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}quickPublish/authPassword.action";
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
	if (key == 82 || key == 220)
	{
		goback();
		return;
	}
}

// 普通产品附加属性展示
function goProdAddAttr(opertype)
{	
	if (submitFlag == 0) 
	{
		submitFlag = 1;// 提交标记
		
		openQryWaitLoading('qryWaitLoading');
		
		document.getElementById("opertype").value = opertype;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}quickPublish/goProdAddAttr.action";
		document.actform.submit();	
	}
}

function subsProd(opertype)
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;// 提交标记
		
		// 操作类型
		document.getElementById('opertype').value = opertype;
		
		if ('PCOpDel' == opertype)
		{
			openQryWaitLoading('qryWaitLoading');
			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}quickPublish/prodPackegRec.action";
			document.actform.submit();
			return;
		}
		else
		{
			openQryWaitLoading('qryWaitLoading');
			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}quickPublish/subsProd.action";
			document.actform.submit();
		}
	}
}

//弹出确认框
openWindow = function(id,opertype,effectType)
{
	// 操作类型
	document.getElementById("opertype").value = opertype;
	// 生效方式
	document.getElementById("effectType").value = effectType;	
	// 退订时，修改弹出div提示
	if(opertype == "PCOpDel")
	{
		document.getElementById("tipContent").innerHTML = "您选择退订：";
	}
	wiWindow = new OpenWindow("popup_confirm",708,392);
}

// 转向生效方式选择页面
function goEffectType(opertype)
{
	// 操作类型
	document.getElementById("opertype").value = opertype;
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}quickPublish/goEffectType.action";
		document.actform.submit();	
	}
}

// 退定产品包
function delProdPackeg()
{
	if ('<%=effectDel %>' == '' || '<%=effectDel %>'.length == 1 && '<%=effectDel %>' != '5')
	{
		document.getElementById('effectType').value = '<%=effectDel %>';
		document.getElementById("opertype").value = 'PCOpDel';
		document.actform.target = "_self";
		openRecWaitLoading('recWaitLoading');
		document.actform.action = "${sessionScope.basePath}quickPublish/prodPackegRec.action";
		document.actform.submit();	
	}
	else
	{
		document.getElementById('effectType').value = '<%=effectDel %>';
		goEffectType('PCOpDel');
	}
}

</script>
</head>
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" id="opertype" name="opertype" value="<s:property value='opertype'/>"/>
		<input type="hidden" id="effectType" name="effectType" value=""/>
		<input type="hidden" id="hotRecFlag" name="hotRecFlag" value="<s:property value='hotRecFlag'/>"/>
		<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="1"/>
		<input type="hidden" id="searchType" name="searchType" value="<s:property value='searchType'/>" />
		<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
		<input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
		
		<!-- 1:清除 其他不处理 -->
		<input type="hidden" id="initBz" name="initBz" value="1"/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
		        <!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h fs16" >
								<!-- 列表内容 -->
								<p class="ptop180 tc p747w411h" id="inn" >
								<br><br>
								<table>
									<tr>
										<td colspan="2" id="errorMsg" style="text-align:left"></td>
									</tr>
									
	            					<tr>
		                                <td style="vertical-align:top;width: 15%"><img style="width:105px;height:106px" src='${sessionScope.basePath}<%=imgPath %>' /></td>
		                                <td class="tl ti2 pl10"><%=busiDesc %></td>
	                                </tr>
	                                <tr height="4px;">
	                                	<td colspan="2" class="bluel">
	                                		---------------------------------------------------------------------------------------------------------
	                                	</td>
	                                </tr>
	                                <tr>
		                                <td colspan="2" class="fs20 tl">业务资费:</td>
	                                </tr>
	            					<tr>
		                                <td colspan="2" class="tl pl30"><%=priceDesc %></td>
	                                </tr>
	                                <tr height="6px;">
	                                	<td colspan="2"></td>
	                                </tr>
	                                <tr>
		                                <td colspan="2" class="fs20 tl">温馨提示:</td>
	                                </tr>
	            					<tr>
		                                <td colspan="2" class="tl pl30"><%=reminder %></td>
	                                </tr>
	                                <!-- add begin g00140516 2012/09/28 R003C12L09n01 OR_HUB_201207_1089 -->
	                                <s:if test="tip != ''">		
										<tr height="6px;">
		                                	<td colspan="2"></td>
		                                </tr>
										<tr>
			                                <td colspan="2" class="fs20 tl">特别提示:</td>
		                                </tr>
		            					<tr>
			                                <td colspan="2" class="tl pl30"><s:property value='tip' /></td>
		                                </tr>
									</s:if>
									<!-- add end g00140516 2012/09/28 R003C12L09n01 OR_HUB_201207_1089 -->
	                                <tr height="8px;">
	                                	<td colspan="2"></td>
	                                </tr>
	                                <tr>
					                    <td colspan="2">
					                    <%
				                    	if("1".equals(loginFlag))
				                    	{
				                    		// 可以办理
				                    		if("1".equals(isSupportRec))
				                    		{
					                    %>
						                    	<%
						                    	// 普通产品，未开通、推荐，无附加属性，按钮显示：开通
				        						if("1".equals(prodType) && !"1".equals(isOpen) && !"1".equals(isAddAttr))
				        						{
				        							if(effectRec.length()<2 && !effectRec.equals("5"))
				        							{
				        						%>
				        							<input type="button" class="bt2_liebiao white" value="开通" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpRec','<%=effectRec %>');"/>		
				        						<%
				        							}
				        							else
				        							{
				        						%>
				        							<input type="button" class="bt2_liebiao white" value="开通" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';goEffectType('PCOpRec');"/>			
				        						<%
				        							}
				        						}
				        						//  普通产品，未开通、推荐，有附加属性，按钮显示：下一步
				        						else if("1".equals(prodType) && !"1".equals(isOpen) && "1".equals(isAddAttr))
				        						{	
				        						%>
				        						<input type="button" class="bt2_liebiao white" value="下一步" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goProdAddAttr('PCOpRec');"/>		        						
				        						<%
				        						}
				        						// 普通产品，已开通，无附加属性，按钮显示：退订
				        						else if("1".equals(prodType) && "1".equals(isOpen) && !"1".equals(isAddAttr) && !"1".equals(hotRecFlag))
				        						{
				        							if(effectDel.length()<2 && !effectDel.equals("5"))
				        							{
				        						%>
				        							<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpDel','<%=effectDel %>');"/>		        						
				        						<%
				        							}
				        							else
				        							{
				        						%>
				        							<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goEffectType('PCOpDel');"/>		        						
				        						<%		
				        							}
				        						}
				        						// 普通产品，已开通，有附加属性，按钮显示：变更
				        						else if("1".equals(prodType) && "1".equals(isOpen) && "1".equals(isAddAttr))
				        						{
				        						%>
				        						<input type="button" class="bt2_liebiao white" value="变更" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goProdAddAttr('PCOpMod');"/>
				        						<%
				        							if(!"1".equals(hotRecFlag))
				        							{
				        								if(effectDel.length()<2 && !effectDel.equals("5"))
				        								{
				        						%>
				        								<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpDel','<%=effectDel %>');"/>		        								        						
				        						<%
				        								}
				        								else
				        								{
				        						%>
				        								<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goEffectType('PCOpDel');"/>		        								        						
				        						<%
				        								}
				        							}
				        						%>
				        						<% 
				        						}
				        						// 产品包或模板
				        						else if ("2".equals(prodType) || "3".equals(prodType) )
				        						{
				        							if(!"1".equals(isOpen))
				        							{
				        						%>
				        							
				        								<input type="button" class="bt2_liebiao white" value="下一步" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';subsProd('PCOpRec');"/>	
				        						<%
				        							}
				        							else
				        							{
				        						%>
				        								<input type="button" class="bt2_liebiao white" value="变更" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';subsProd('PCOpMod');"/>
				        								<%
				        									if(!"1".equals(hotRecFlag))
					        								{
					        								
				        								%>
				        									<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';delProdPackeg();"/>
				        									
				        								<%
				        									}
				        							}
				        						}
				        						%>
					                    <%
					                    	}
					                    %>
					                    <%
					                    	// 已开通产品
					                    	if(!"1".equals(isSupportRec))
					                    	{
					                    %>
					                    		<script type="text/javascript">
					                    				document.getElementById("errorMsg").innerHTML = "此产品不适用于您的手机号码，请点击“返回”按钮，选择办理其它业务";
					                    		</script>
					                    <%	
					                    	}
					                    }
					                    else
					                    {
					                    %>
					                    	<%									
		        						    // 未登录显示：查询或办理
		                                	if(null == custInformation)
		                                	{
		                               		%>
			        						<input type="button" class="bt2_liebiao white" value="查询或办理" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; authPassword();"/>	
			        						<%
			        						}
			        						// 普通产品，未开通，无附加属性，按钮显示：开通
			        						else if("1".equals(prodType) && !"1".equals(isOpen) && !"1".equals(isAddAttr))
			        						{
			        							if(effectRec.length()<2 && !effectRec.equals("5"))
			        							{
			        						%>
			        							<input type="button" class="bt2_liebiao white" value="开通" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpRec','<%=effectRec %>');"/>		
			        						<%
			        							}
			        							else
			        							{
			        						%>
			        							<input type="button" class="bt2_liebiao white" value="开通" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';goEffectType('PCOpRec');"/>			
			        						<%
			        							}
			        						}
			        						//  普通产品，有附加属性，按钮显示：下一步
			        						else if("1".equals(prodType) && !"1".equals(isOpen) && "1".equals(isAddAttr))
			        						{	
			        						%>
			        						<input type="button" class="bt2_liebiao white" value="下一步" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goProdAddAttr('PCOpRec');"/>		        						
			        						<%
			        						}
			        						// 普通产品，已开通，无附加属性，按钮显示：退订
			        						else if("1".equals(prodType) && "1".equals(isOpen) && !"1".equals(isAddAttr) && !"1".equals(hotRecFlag))
			        						{
			        							if(effectDel.length()<2 && !effectDel.equals("5"))
			        							{
			        						%>
			        							<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpDel','<%=effectDel %>');"/>		        						
			        						<%
			        							}
			        							else
			        							{
			        						%>
			        							<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goEffectType('PCOpDel');"/>		        						
			        						<%		
			        							}
			        						}
			        						// 普通产品，已开通，有附加属性，按钮显示：变更
			        						else if("1".equals(prodType) && "1".equals(isOpen) && "1".equals(isAddAttr))
			        						{
			        						%>
			        						<input type="button" class="bt2_liebiao white" value="变更" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goProdAddAttr('PCOpMod');"/>
			        						
			        						<%
			        							if(!"1".equals(hotRecFlag))
				        						{
				        							if(effectDel.length()<2 && !effectDel.equals("5"))
				        							{
			        						%>
			        								<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; openWindow(popup_confirm,'PCOpDel','<%=effectDel %>');"/>		        								        						
			        						<%
				        							}
				        							else
				        							{
			        						%>
			        								<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white'; goEffectType('PCOpDel');"/>		        								        						
			        						<%
			        								}
			        							}
			        						}
			        						// 产品包或模板
			        						else if ("2".equals(prodType) || "3".equals(prodType) )
			        						{
			        							if(!"1".equals(isOpen))
			        							{
			        						%>
			        							
			        								<input type="button" class="bt2_liebiao white" value="下一步" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';subsProd('PCOpRec');"/>	
			        						<%
			        							}
			        							else
			        							{
			        						%>
			        								<input type="button" class="bt2_liebiao white" value="变更" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';subsProd('PCOpMod');"/>
			        								<%
			        									if(!"1".equals(hotRecFlag))
			        									{
			        								%>
			        										<input type="button" class="bt2_liebiao white" value="退订" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';delProdPackeg();"/>
			        								<%
			        									}
			        								%>
			        						<%	
			        							}
			        						}
			        						%>
					                    <%
					                    }
					                    %>
					               		
			        					<input type="button" class="bt2_liebiao white" value="返回" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';goback();"/>	
				                    	</td>
				                    </tr>
                                </table>
				                <br/>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								
								<%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom" style="left:766px;top:39px;">0%</div>
								<%
								}
								else
								{ 
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								} 
								%>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
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
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
		          
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
