<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.AddAttrPO"%>
<%
	// 当前产品PO
	ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);
	
	// 产品名称
	String prodName = "";
	
	if(null != prodConfigPO.getProdName())
	{
		prodName = prodConfigPO.getProdName();
	}
	
	// 产品类型  1：普通产品 2：产品包 3：模板 4、同组产品 5、主体产品
	String prodType = "";
	
	// 普通产品的附加属性列表
	List<AddAttrPO> addAttrPOList = null;
	
	
	if(null != prodConfigPO.getProdType())
	{
		prodType = prodConfigPO.getProdType();
	}
	
	if(null != prodConfigPO.getAddAttrPOList())
	{
		addAttrPOList = prodConfigPO.getAddAttrPOList();
	}
	
	// 操作类型， PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 
    String opertype = (String)request.getAttribute("opertype");
    
	// 开通生效方式
	String effectRec = "";
	// 变更生效方式
	String effectMod = "";
	
	if(null != prodConfigPO.getEffectRec())
	{
		effectRec = prodConfigPO.getEffectRec();
	}
	
	if(null != prodConfigPO.getEffectMod())
	{
		effectMod = prodConfigPO.getEffectMod();
	}
	// 生效方式
	String effect = "";
	// 开通
	if("PCOpRec".equals(opertype))
	{
		effect = effectRec;
	}
	// 变更
	else if("PCOpMod".equals(opertype))
	{
		effect = effectMod;
	}
        
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
			if (key == 8)
			{
				numBoardText.value = numBoardText.value.substr(0,numBoardText.value.length-1);
			}
			if (key == 82 || key == 220)
			{
				goback();
				return;
			}
		}	
		
		// 产品附加属性选择，btnDiv：附加属性ID,btn:点击的按钮，btClass：按钮要变更成的样式
		function btnClick(btnDiv, btn, btClass, isshow)
		{
			if(isshow != '2')
			{
				var btns=document.getElementById(btnDiv).getElementsByTagName('a');
				for(i=0;i<btns.length;i++)
				{
					btns[i].className=btClass;
				}
				btn.className=btClass+'on';
			}
		}
		
		//用户选择的附加属性串(attrid1=attrvalue1=optype;:;attrid2=attrvalue2=optype……),action中将;:;替换为#
		var selectAttrStr = "";
		
		// 产品受理
		function prodRecCheck(goEffect)
		{
			selectAttrStr = "";
			<%
			if(null != addAttrPOList && addAttrPOList.size() > 0)
			{	
				for(AddAttrPO addAttrPO : addAttrPOList)
				{
					String attrtype = addAttrPO.getAttrtype();//附加属性类型
					String attrid = addAttrPO.getAttrid();//附加属性id
					String attrname = addAttrPO.getAttrname();//附加属性名称
					String ismandatory = addAttrPO.getIsmandatory();//是否必输
					String minlength = addAttrPO.getMinlength();//最小长度
					String isshow = addAttrPO.getIsshow();//界面是否展示
					String attrnum = addAttrPO.getAttrnum();//属性值数量
					String attrsplit = addAttrPO.getAttrsplit();//属性值分隔符
					if("".equals(attrnum) || null == attrnum)
					{
						attrnum = "1";
					}
					// 是否界面展现 0：界面不展示 1：界面展示可以编辑 2：界面展示不能编辑
					if("1".equals(isshow) || "2".equals(isshow))
					{				
			%>
						if("EDIT" == "<%=attrtype%>" || "MONEY" == "<%=attrtype%>" || "PASSWORD" == "<%=attrtype%>" || "TEXTAREA" == "<%=attrtype%>")
						{	
							var textStr = "";
							
							for(var i = 0; i<<%=attrnum%>; i++)
							{
								var inputValue = document.getElementById("<%=attrid%>"+i).value;
								if( "" != inputValue)
								{
									if(inputValue.length < parseInt("<%=minlength%>"))
									{
										alertRedErrorMsg("<%=attrname%>" + "长度不符合要求，最小长度：" + "<%=minlength%>");
										return;
									}
									
									if("MONEY" == "<%=attrtype%>" )
									{
										// 金额由元转换为分
										inputValue = inputValue*100;
									}
									
									if("" == textStr)
									{
										textStr = "<%=attrid%>" + "=" + inputValue ;		
									}
									else
									{
										textStr = textStr+ "<%=attrsplit%>" + inputValue;
									}
								}
							}
							// 是否必须 0：不是 1：是
							if("1" == "<%=ismandatory%>" && "" == textStr)
							{
								alertRedErrorMsg("<%=attrname%>" + "必须输入");
								return;
							}
					
							if("" != textStr)
							{
								textStr = textStr + "=optype" ;
								if("" == selectAttrStr)
								{
									selectAttrStr = textStr;		
								}
								else
								{
									selectAttrStr = selectAttrStr + ";:;" + textStr;
								}
							}		
						
					}
					else if("DATE" == "<%=attrtype%>")
					{
						var inputValue = document.getElementById("<%=attrid%>").value;
						
						// 是否必须 0：不是 1：是
						if("1" == "<%=ismandatory%>" && "" == inputValue)
						{
							alertRedErrorMsg("<%=attrname%>" + "必须输入");
							return;
						}
						
						if("" != inputValue)
						{
							
							var datePattern = /^(\d{4})(0\d{1}|1[0-2])(0\d{1}|[12]\d{1}|3[01])(0\d{1}|1\d{1}|2[0-3])[0-5]\d{1}([0-5]\d{1})$/;
							
							if(!datePattern.test(inputValue))
							{
								alertRedErrorMsg("<%=attrname%>" + "输入格式错误");
								return;
							}
							else
							{
								inputValue = inputValue.substr(0,4) + "-" + inputValue.substr(4,2) + "-" + inputValue.substr(6,2) + " " + inputValue.substr(8,2)+ ":" + inputValue.substr(10,2)+ ":" + inputValue.substr(12,2);
							}
							
							if("" == selectAttrStr)
							{
								selectAttrStr = "<%=attrid%>" + "=" + inputValue  + "=optype" ;		
							}
							else
							{
								selectAttrStr = selectAttrStr + ";:;" + "<%=attrid%>" + "=" + inputValue  + "=optype";
							}
						}		
						
					}
					// 如果附加属性类型是SELECT：下拉列表
					else if("SELECT" == "<%=attrtype%>")
					{
		   				var textStr = "";
		   				for(var i = 0; i< <%=attrnum%>; i++)
						{
							var btns = document.getElementById("<%=attrid%>"+i).getElementsByTagName('a');
						    for(var j=0;j<btns.length;j++)
							{
								if(btns[j].className == "bt13on")
								{
									var selectValue = btns[j].id;
									
									if("" == textStr)
									{
										textStr = "<%=attrid%>" + "=" + selectValue ;		
									}
									else
									{
										textStr = textStr+ "<%=attrsplit%>" + selectValue;
									}
								}
							}
						}
						// 是否必须 0：不是 1：是
						if("1" == "<%=ismandatory%>" && "" == textStr)
						{
							alertRedErrorMsg("<%=attrname%>" + "必须选择");
							return;
						}
						if("" != textStr)
						{
							textStr = textStr + "=optype" ;
							if("" == selectAttrStr)
							{
								selectAttrStr = textStr;		
							}
							else
							{
								selectAttrStr = selectAttrStr + ";:;" + textStr;
							}
						}		
					}
			<%
					}
				}
			}
			%>	
			
			// 有附加属性产品变更,需重新拼装selectAttrStr,只拼接用户修改的
			<%
			if(null != addAttrPOList && addAttrPOList.size()>0)
			{
			%>
	        if("" != selectAttrStr && "PCOpMod" == "<%=opertype%>")
	        {		            
	            var modStr = selectAttrStr;
	            // 清空，重新组装
	            selectAttrStr = "";
	            var modArray = modStr.split(";:;");
	           	
	           	<%
	           		// 用户已订购附加属性			
		            for (AddAttrPO addAttrPO : addAttrPOList)
		            {
	           	%>
	                for (var i = 0; i < modArray.length ;i++)
	                {
	                    var oneAttr = modArray[i].split("=");
	                    
	                    if("<%=addAttrPO.getAttrid()%>" == oneAttr[0])
	                    {
	                        // 如果属性值未修改
	                        if("<%=addAttrPO.getAttrvalue()%>"==oneAttr[1] || "<%=addAttrPO.getAttrvalue()%>" == (oneAttr[1] + "<%=addAttrPO.getAttrsplit()%>"))
	                        {
	                            break;
	                        }
	                        else
	                        {
	                            if ("" != selectAttrStr)
	                            {
	                                selectAttrStr = selectAttrStr + ";:;" + modArray[i];
	                            }
	                            else
	                            {
	                                selectAttrStr = modArray[i];
	                            }
	                        }
	                    }
	                }
		        <%
		            }
	            %>
                if(selectAttrStr == "")
	            {
	            	alertRedErrorMsg("您还没有修改附加属性值！");
	            	return;
	            }
       		}
			<%
			}
			%>
			
			document.getElementById("selectAttrStr").value = selectAttrStr;	
			if(goEffect == "1")
			{
				goEffectType();
			}
			else
			{
				document.getElementById("effectType").value = "<%=effect%>";			
				openWindow(popup_confirm);
			}
		}
		
		// 产品受理
		function prodRec()
		{
			if (submitFlag == 0) 
			{
				submitFlag = 1;	//提交标记
				openRecWaitLoading('recWaitLoading');
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}quickPublish/prodRec.action";
				document.actform.submit();
			}
		}
		
		// 转向生效方式选择页面
		function goEffectType()
		{
			// 操作类型
			if (submitFlag == 0) 
			{
				submitFlag = 1;	//提交标记
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}quickPublish/goEffectType.action";
				document.actform.submit();	
			}
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="opertype" name="opertype" value="<s:property value='opertype'/>"/>
			<input type="hidden" id="selectAttrStr" name="selectAttrStr" value=""/>	
			<input type="hidden" id="effectType" name="effectType" value=""/>
			<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="1"/>
			<input type="hidden" id="searchType" name="searchType" value="<s:property value='searchType'/>" />
			<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
			<input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span>选择产品附加属性</p>
								
								<p class="ptop180 tc p747w411h " id="inn" >
								<br><br>
								<table class="tb_blue" >
								<%
									if(null != addAttrPOList && addAttrPOList.size() > 0)
									{	
										for(AddAttrPO addAttrPO : addAttrPOList)
										{
											// 是否界面展现 0：界面不展示 1：界面展示可以编辑 2：界面展示不能编辑
											String isshow = addAttrPO.getIsshow();
											if("1".equals(isshow) || "2".equals(isshow))
											{
												String attrtype = addAttrPO.getAttrtype();//附加属性类型
												String attrname = addAttrPO.getAttrname();//附加属性名称
												String attrid = addAttrPO.getAttrid();//附加属性ID
												String dictinfo = addAttrPO.getDictinfo();//字典dictid=dictname|dictid=dictname ...
												String[] dictinfoArray = dictinfo.split("\\|");//字典数组
												String attrvalue = addAttrPO.getAttrvalue();//附加属性默认值
												
												String attrsplit = addAttrPO.getAttrsplit();//附加属性值分隔符	
												String attrnum = addAttrPO.getAttrnum();//附加属性值个数

												if("".equals(attrnum))
												{
													attrnum = "1";
												}
												// 存放附加属性默认值
												String[] valueArray = new String[Integer.parseInt(attrnum)];
													
													if(Integer.parseInt(attrnum)>1)
													{
														String[] attrvalueArray = attrvalue.split(attrsplit);
														
														for(int i = 0; i<attrvalueArray.length; i++)
														{
															valueArray[i] = attrvalueArray[i];
														} 
													}
													else if(Integer.parseInt(attrnum) == 1)
													{
														valueArray[0] = attrvalue;
													}
													
											
								%>	
												<table width = "100%" class="tb_blue">
											<%		
												// 如果附加属性类型是 SINGLE：单行编辑框
												if("EDIT".equals(attrtype) || "MONEY".equals(attrtype) || "PASSWORD".equals(attrtype) || "TEXTAREA".equals(attrtype))
												{
													
													String maxlength = addAttrPO.getMaxlength();//附加属性最大值		
																								
													String type = "text";//输入框类型
													if("PASSWORD".equals(attrtype))
													{
														type = "password";
													}
													for(int i = 0; i< Integer.parseInt(attrnum); i++)
													{
														if(null == valueArray[i])
														{
															valueArray[i] = "";
														}
														// 货币由分转化为元
														if("MONEY".equals(attrtype) && !"".equals(valueArray[i]))
														{
															valueArray[i] = String.valueOf(Integer.parseInt(valueArray[i])/100);
														}
											%>
												<tr>
												<td width = "40%"><%=attrname %></td>
												<td>
												<%
													if("2".equals(isshow)) 
													{
												%>
														<input type="<%=type %>" id="<%=attrid%><%=i %>" maxlength="<%=maxlength%>" name="finalNbr" readonly = "true" value ="<%=valueArray[i]%>" class="text3 fl " onclick="setNumBoardText('<%=attrid%><%=i %>')	"/>
												<%		
													}
													else
													{
												%>
													<input type="<%=type %>" id="<%=attrid%><%=i %>" maxlength="<%=maxlength%>" name="finalNbr" value ="<%=valueArray[i]%>" class="text3 fl " onclick="setNumBoardText('<%=attrid%><%=i %>')	"/>
													<input type="button" class="bt2_liebiao white" value="软键盘" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white'; keyBoradFunction('<%=attrid%><%=i %>','<%=maxlength%>');openKeyBorad('popup_keyBoard');"/>										
												<%
													}
												%>
												</td>
												</tr>
										<% 
													}
											}
											// 如果附加属性类型是 SELECT：下拉列表
											else if("SELECT".equals(attrtype))
											{
												for(int i = 0; i< Integer.parseInt(attrnum); i++)
												{
													if(null == valueArray[i])
													{
														valueArray[i] = "";
													}
													
										%>
												<tr>
											    	<td><%=attrname %></td>
												</tr>
												<tr id = "<%=attrid%><%=i %>">
													<td style="text-align:left">															
														<% 
															if(null != dictinfoArray && dictinfoArray.length >0)
															{
																for(int j = 0; j< dictinfoArray.length; j++)
																{
																	//拆分字典项，dictid=dictname
																	String[] dictInfor = dictinfoArray[j].split("=");
																	
																	// 附加属性已开通，选中状态
																	if(valueArray[i].equals(dictInfor[0]))
																	{
														%>
																		<a href="javascript:void(0)" id ="<%=dictInfor[0] %>" class="bt13on" style = "display:inline-block;"; onclick="btnClick('<%=attrid%><%=i %>',this,'bt13','<%=isshow %>')"><%=dictInfor[1] %></a>		
														<%	
																	}
																	else
																	{
														%>
																		<a href="javascript:void(0)" id ="<%=dictInfor[0] %>" class="bt13" style = "display:inline-block;"; onclick="btnClick('<%=attrid%><%=i %>',this,'bt13','<%=isshow %>')"><%=dictInfor[1] %></a>		
														<% 
																	}
																}
															}
														%>
													</td>
												</tr>
										<%
												}
											}
										%>
										<table>
										<div class="blank30" ></div>	
								<%
										}
									}
								}
								%>
								<tr>
								<td >
									<%
										// 操作类型， PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 
										if(effect.length()<2 && !effect.equals("5"))
										{
									%>
											<input type="button" class="bt2_liebiao white" value="确定" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';  prodRecCheck('0');"/>	
									<%
										}
										else
										{
									%>
											<input type="button" class="bt2_liebiao white" value="确定" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';  prodRecCheck('1');"/>	
									<%
										}
									%>
									<input type="button" class="bt2_liebiao white" value="返回" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white'; goback('');"/>		        								        																
								</td>
								</tr>
							    </table>
								<br/> 
								<p/>
							 </div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30);" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30);"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<div class="popup_confirm" id="popup_confirm">
					<div class="bg"></div>
						<div class="tips_title">提示：</div>
						<div class="tips_body">
							<p><i>您选择办理： </i><i><%=prodName %></i><i>业务</i></p>
							<div class="blank10"></div>
							<p class="mt30">确认操作请点击"确认"提交。</p>
						</div>
						<div class="btn_box tc mt20">
							<span class="mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';prodRec();">确认</a></span>
							<span class="inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a></span>
					  	</div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<%
	if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
	{
	%>
	<div class="popup_confirm" style="width:400px; height:394px; padding-left:300px;" id="popup_keyBoard">
	<%
	}
	else
	{
	%>
	<div class="popup_confirm" style="width:400px; height:320px; padding-left:300px;" id="popup_keyBoard">
	<%
	}
	%>
	<div class="bg" style="left:300px;"></div>
	<div class="tips_body">
 	<!--小键盘-->
    <div class="num_r_input fr">
      	<div class="numboard numboard_big numboard_small m0a no_bg" id="numBoard"> 
          	<p class="  blank15 fs14"></p>
  			<div class=" numbox"> 
         <a title="1" href="javascript:void(0)">1</a><a title="2" href="javascript:void(0)">2</a><a title="3" href="javascript:void(0)">3</a>
         <a title="退格" href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';var allNumBoardText=numBoardText.value;numBoardText.value=allNumBoardText.slice(0,-1);"></a>
         <div class="clear"></div>
         <a title="4" href="javascript:void(0)">4</a><a title="5" href="javascript:void(0)">5</a><a title="6" href="javascript:void(0)">6</a>
         <a title="清除" href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';numBoardText.value='';"></a>
         <div class="clear"></div>
         <div class="nleft">
             <a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a>
             <a title="x" href="javascript:void(0)" name="functionkey">x</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)" name="functionkey">#</a>
         </div>
         <div class="nright">
             <a title="确认" href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3';wiWindow.close();">1</a>
         </div>
         <div class="blank10"></div>
     	</div>
  	</div>                
	<!--小键盘-->
	</div>
</div>
             
<script type="text/javascript">
var numBoardText = "";
function setNumBoardText(textId)
{
	numBoardText=document.getElementById(textId);	
}
function keyBoradFunction(textId,maxlength)
{
	var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	numBoardText=document.getElementById(textId);
	for(i=0;i<numBoardBtns.length;i++){
		if(!numBoardBtns[i].className)
		{
			numBoardBtns[i].className='';
		}
		
	    if(numBoardBtns[i].name=='functionkey')
	    {
	    	continue ;
	    }  
		 
		numBoardBtns[i].onmousedown=function(){
		 	this.className+='on';
		}
		
		numBoardBtns[i].onmouseup=function(){
			var textValue=document.getElementById(textId).value;
			if("" != maxlength && textValue.length<parseInt(maxlength))
			{
				document.getElementById(textId).value+=this.innerHTML;
			}
			else if("" == maxlength)
			{
				document.getElementById(textId).value+=this.innerHTML;	
			}
			var numBoardText=document.getElementById(textId);
			var fullClass=this.className;
			var fullValue=numBoardText.value;
			this.className=fullClass.slice(0,fullClass.indexOf('on'));
			moveLast(textId);
		}
	}	
}

function moveLast(textId)
{
	var r = document.getElementById(textId).createTextRange(); 
	r.collapse(false); 
	r.select(); 
}			
</script>         
<script type="text/javascript">

// 弹出软键盘
openKeyBorad = function(id){
	wiWindow = new OpenWindow("popup_keyBoard", 708,392);
}

//弹出确认框
openWindow = function(id)
{
	wiWindow = new OpenWindow("popup_confirm",708,392);
}
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
