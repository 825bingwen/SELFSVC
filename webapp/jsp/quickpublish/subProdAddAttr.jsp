<%@ page contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ChildProdPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.AddAttrPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.SubsChildProdPO"%>

<%
NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

// 子产品的附加属性
List<AddAttrPO> addAttrPOList = (List<AddAttrPO>)request.getAttribute("addAttrPOListBySubProd");

// 当前产品PO
ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);

// 附加属性
String childProdAddAttrs = (String)request.getAttribute("childProdAddAttrs");

%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>子产品页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/cookie.js"></script>
<script>
var submitFlag = 0;

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

// 单选
function selectAttrByRadio(addAttrId,key,num,btn,btClass)
{
	var name = "";

	// 获取选择的按扭
	var ids = document.getElementsByName('SELECT_'+addAttrId+'_'+num);
	
	// 清空数据
	for(var i=0;i<ids.length;i++)
	{
		ids[i].className = 'bt13';
	}
	
	// 选中
	document.getElementById(addAttrId+'_'+key+'_'+num).className = 'bt13on';
}

// 提交

function doCommit()
{
	// 清空附加属性
	// 例attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2;
	document.getElementById('addAttrStr').value = '';
	
	// 组装附加属性
	<%
	for (AddAttrPO addAttrPO : addAttrPOList)
	{
		String attrtype = addAttrPO.getAttrtype();// 属性类型
		String attrid = addAttrPO.getAttrid();// 属性ID
		String attrsplit = addAttrPO.getAttrsplit();//附加属性值分隔符
		String ismandatory = addAttrPO.getIsmandatory();// 是否必选
		int attrnum = Integer.parseInt(addAttrPO.getAttrnum());//附加属性值个数
		
		// 文本
		if ("EDIT".equals(attrtype) || "PASSWORD".equals(attrtype) || "TEXTAREA".equals(attrtype) || "MONEY".equals(attrtype))
		{
	%>
			var EDIT_<%=attrid.replace(":","") %>s = document.getElementsByName('EDIT_<%=attrid %>');
			for(var i=0;i<EDIT_<%=attrid.replace(":","") %>s.length;i++)
			{
				// 货币型数值转化为元显示
				<%
					if ("MONEY".equals(attrtype))
					{
				%>
						updateAddAttr('<%=attrid %>',Number(EDIT_<%=attrid.replace(":","") %>s[i].value)*100,'<%=attrsplit %>');
				<%
					}
					else
					{
				%>
						updateAddAttr('<%=attrid %>',EDIT_<%=attrid.replace(":","") %>s[i].value,'<%=attrsplit %>');
				<%
					}
				%>
				
			}
	<%
		}
		// 单选
		if ("SELECT".equals(attrtype))
		{
			for (int i=1;i<=attrnum;i++)
			{
	%>
				var SELECT_<%=attrid.replace(":","") %>_<%=i %>s = document.getElementsByName('SELECT_<%=attrid+"_"+i %>');
				for (var i=0;i<SELECT_<%=attrid.replace(":","") %>_<%=i %>s.length;i++)
				{
					if (SELECT_<%=attrid.replace(":","") %>_<%=i %>s[i].className == 'bt13on')
					{
						var attrvalue = SELECT_<%=attrid.replace(":","") %>_<%=i %>s[i].id.replace(/<%=attrid+"_" %>/g,'').split('_')[0];
						updateAddAttr('<%=attrid %>',attrvalue,'<%=attrsplit %>');
					}
				}
	<%
			} 
		}
		
	}	
	%>
	
	// 校验
	<%
	for (AddAttrPO addAttrPO : addAttrPOList)
	{
		String attrtype = addAttrPO.getAttrtype();// 属性类型
		String attrid = addAttrPO.getAttrid();// 属性ID
		String attrname = addAttrPO.getAttrname();// 属性名称
		String attrsplit = addAttrPO.getAttrsplit();//附加属性值分隔符
		String ismandatory = addAttrPO.getIsmandatory();// 是否必选
		String minlength = addAttrPO.getMinlength();// 最小长度
		String maxlength = addAttrPO.getMaxlength();// 最大长度
		int attrnum = Integer.parseInt(addAttrPO.getAttrnum());//附加属性值个数
	%>
		// 取设定好的属性值
		var addAttrvalue = getAddAttr(document.getElementById('addAttrStr').value,'<%=attrid %>');
	<%	
		if ("1".equals(ismandatory))
		{
	%>
			if (addAttrvalue == '')
			{
				alertRedErrorMsg('<%=attrname %>不能为空,请选择!');
				return;
			}
	<%
		}
	%>
		var EDITs = document.getElementsByName('EDIT_<%=attrid %>');
		for(var i=0;i<EDITs.length;i++)
		{
			var value = EDITs[i].value;
			
			if ('<%=maxlength %>' == '' || '<%=minlength %>' == '')
			{
				continue;
			}
			if (value.length > parseInt('<%=maxlength %>') || value.length < parseInt('<%=minlength %>'))
			{
				alertRedErrorMsg('属性"<%=attrname %>"不符合长度要求<br>最小长度为：<%=minlength %><br>最大长度为：<%=maxlength %><br>请修改！');
				return;
			}
			
		}
	<%
	}
	%>
	
	// 取当前子产品ID pkgid~prodid~privid
	var subProdId = document.getElementById('subProdId').value;
	
	// 更新附加属性串
	updateChildProdAddAttrs(subProdId,document.getElementById('addAttrStr').value);
	
	// 清空临时变量
	document.getElementById('addAttrStr').value = '';
	
	// 提交
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath}quickPublish/subsProd.action";
	document.actform.submit();	
}

// 更新附加属性列表信息
function updateChildProdAddAttrs(id,addAttrs)
{
	var tmp = '';
	
	// 例如：pkgid~prodid~privid:attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2]
	var childProdAddAttrs = document.getElementById('childProdAddAttrs').value;
	
	var addAttrsList = childProdAddAttrs.split(']');
	
	for(var i=0;i<addAttrsList.length;i++)
	{
		if (addAttrsList[i].split('::')[0] == id)
		{
			tmp = tmp + id + '::' + addAttrs + ']';
		}
		else
		{
			tmp = tmp + addAttrsList[i] + ']';
		}
	}
	
	// 替换尾部;;
	tmp = tmp.replace(/]]/g,']');
	document.getElementById('childProdAddAttrs').value = tmp;
}

// 更新附加属性信息
// addAttrStr attrid1=13645319981@13645319999=optype1#attrid2=attrvalue2=optype2
function updateAddAttr(addAttrid,addAttrvalue,attrsplit)
{
	var addAttrStr = document.getElementById('addAttrStr').value;

	if (addAttrStr == '')
	{
		addAttrStr = addAttrid + '=' + addAttrvalue + '=#';
	}
	else
	{
			var bz = 0;
			var addAttrs = addAttrStr.split('#');
			for (var i=0;i<addAttrs.length;i++)
			{
				var addAttr = addAttrs[i];
				if (addAttr.split('=')[0] == addAttrid)
				{
					bz = 1;
				}
			}
			// 追加(找到)
			if (bz == 1)
			{   
				addAttrStr = addAttrStr.substr(0,addAttrStr.length-2) + attrsplit + addAttrvalue + '=#';
			}
			// 新增(未找到)
			else
			{
				addAttrStr = addAttrStr + addAttrid + '=' + addAttrvalue + '=#';
			}
	}
	
	document.getElementById('addAttrStr').value = addAttrStr;
}

// 初始化
function initAddAttr()
{
	document.getElementById('addAttrStr').value = '';
	
	// 取得包下所有子产品的附加属性串
	var childProdAddAttrs = document.getElementById('childProdAddAttrs').value;
	
	// 查询子产品下的附加属性信息
	var addAttrs = getAddAttrs(childProdAddAttrs,document.getElementById('subProdId').value);
	
	// 校验空
	if (addAttrs == null || addAttrs == '')
	{
		return;
	}
	
	<%
	for (AddAttrPO addAttrPO : addAttrPOList)
	{
		String attrtype = addAttrPO.getAttrtype();// 属性类型
		String attrid = addAttrPO.getAttrid();// 属性ID
		String attrsplit = addAttrPO.getAttrsplit();//附加属性值分隔符
		int attrnum = Integer.parseInt(addAttrPO.getAttrnum());//附加属性值个数		
		
		for (int i=1;i<=attrnum;i++)
		{
			// 文本
			if ("EDIT".equals(attrtype) || "PASSWORD".equals(attrtype) || "TEXTAREA".equals(attrtype) || "MONEY".equals(attrtype))
			{
		%>
				var attrvalue_EDIT = getAddAttr(addAttrs,'<%=attrid %>');
				var attrvalues_EDIT = attrvalue_EDIT.split('<%=attrsplit %>');
				var EDIT_<%=attrid.replace(":","") %>_<%=i %>_value = attrvalues_EDIT[<%=i-1 %>];
				if (EDIT_<%=attrid.replace(":","") %>_<%=i %>_value != '')
				{
					// 货币型数值转化为元显示
					<%
						if ("MONEY".equals(attrtype))
						{
					%>
							EDIT_<%=attrid.replace(":","") %>_<%=i %>_value = Number(EDIT_<%=attrid.replace(":","") %>_<%=i %>_value)/100;
					<%
						}
					%>
					
					document.getElementById('<%=attrid %>_<%=i %>').value = EDIT_<%=attrid.replace(":","") %>_<%=i %>_value;
				}
		<%
			}
			
			// 单选
			if ("SELECT".equals(attrtype))
			{
		%>
				var attrvalue_SELECT = getAddAttr(addAttrs,'<%=attrid %>');// 根据附加属性ID查询附加附加属性值
				var attrvalues_SELECT = attrvalue_SELECT.split('<%=attrsplit %>');// 附加属性值列表
				var SELECT_<%=attrid.replace(":","") %>_<%=i %>_value = attrvalues_SELECT[<%=i-1 %>];// 属性值
				var ids_<%=attrid.replace(":","") %>_<%=i %> = document.getElementsByName('SELECT_<%=attrid %>_<%=i %>');// 获取选择的按扭
				
				// 属性赋值
				for(var i=0;i<ids_<%=attrid.replace(":","") %>_<%=i %>.length;i++)
				{
					if (ids_<%=attrid.replace(":","") %>_<%=i %>[i].id.split('_')[1] == SELECT_<%=attrid.replace(":","") %>_<%=i %>_value)
					{
						ids_<%=attrid.replace(":","") %>_<%=i %>[i].className = 'bt13on';
					}
					else
					{
						ids_<%=attrid.replace(":","") %>_<%=i %>[i].className = 'bt13';
					}
				}
		<%
				
			}
		}

	}
	%>
}

// cookieValue(addarrtid_003=11111111111=#addarrtid_004=11111111111=#addarrtid_001=DXTCB8=#addarrtid_002=DJB5,DJB8=)
// key(addarrtid_003)
// 跟据附加属性编码取属加属性值
function getAddAttr(addAttrs,key)
{
	var objs = addAttrs.split('#');
	for(var i=0;objs.length;i++)
	{
		if (objs[i] == undefined || objs[i] == '')
		{
			break;
		}
		var objects = objs[i].split('=');
		if (key == objects[0])
		{
			return objects[1];
		}
	}
	return '';
}

// str(pkgid~prodid~privid::attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2;)
// key(pkgid~prodid~privid)
// 跟据产品编码查询产品下的所有附加属性
function getAddAttrs(str,key)
{
	if (str == '')
	{
		return '';
	}
	if (str.indexOf(key+'::') == -1)
	{
		return '';
	}
	var objs = str.split(']');
	for(var i=0;objs.length;i++)
	{
		var objects = objs[i].split('::');
		if (key == objects[0])
		{
			return objects[1];
		}
	}
	
	return '';
}

</script>
</head>
<BODY scroll="no" onload="window.focus();initAddAttr();">
	<form name="actform" method="post">
		<%@ include file="/titleinc.jsp"%>
			
			<!-- 当前子产品ID -->
			<input type="hidden" id="subProdId" name="subProdId" value="<s:property value='subProdId'/>"/>
			
			<!-- 操作类型 PCOpRec:开通 PCOpMod:修改 PCOpDel:关闭 -->
			<input type="hidden" id="opertype" name="opertype" value="<s:property value='opertype'/>"/>
			
			<!-- 处理后的临时变量 -->
			<input type="hidden" id="addAttrStr" name="addAttrStr" value=""/>
			<!-- 选中的子产品 -->
			<input type="hidden" id="childProdIds" name="childProdIds" value="<s:property value='childProdIds'/>"/>
			<!-- 选中的子产品属性 -->
			<input type="hidden" id="childProdAddAttrs" name="childProdAddAttrs" value="<s:property value='childProdAddAttrs'/>"/>
			
			<!-- 原附加属性 -->
			<input type="hidden" id="childProdIds_old" name="childProdIds_old" value="<s:property value='childProdIds_old'/>"/>
			<!-- 原子产品 -->
			<input type="hidden" id="childProdAddAttrs_old" name="childProdAddAttrs_old" value="<s:property value='childProdAddAttrs_old'/>"/>
			<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="1"/>
			<input type="hidden" id="searchType" name="searchType" value="<s:property value='searchType'/>" />			
			<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
			<input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
		        <!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span>附加属性</p>
								<p class="ptop180 tc p747w411h" id="inn" >
								<br>
									<%
									for (AddAttrPO addAttrPO : addAttrPOList)
									{
										String attrid = addAttrPO.getAttrid();// 属性ID
										String attrname = addAttrPO.getAttrname();// 属性名称
										String attrtype = addAttrPO.getAttrtype();// AGENTTREE:代理树 EDIT:输入框 SELECT:选择 MONEY:货币 PASSWORD:密码 TEXTAREA:多行文本
										String isShow = addAttrPO.getIsshow();// 是否界面展现 0：界面不展示 1：界面展示可以编辑 2：界面展示不能编辑
										int attrnum = Integer.parseInt(addAttrPO.getAttrnum());//附加属性值个数
										if ("EDIT".equals(attrtype) || "TEXTAREA".equals(attrtype) || "MONEY".equals(attrtype) || "PASSWORD".equals(attrtype))
										{
											// 控件类型 EDIT TEXTAREA MONEY = text PASSWORD = password
											String type = "";
											if ("EDIT".equals(attrtype) || "TEXTAREA".equals(attrtype) || "MONEY".equals(attrtype))
											{
												type = "text";
											}
											else if ("PASSWORD".equals(attrtype))
											{
												type = "password";
											}
											
											// 0：界面不展示 1：界面展示可以编辑 2：界面展示不能编辑
											if ("1".equals(isShow) || "2".equals(isShow))
											{
												out.println("<table class=\"tb_blue\" width=\"100%\">");
											}
											else if ("0".equals(isShow))
											{
												out.println("<table class=\"tb_blue\" width=\"100%\" style=\"display: none;\">");
											}
											out.println("<tr>");
											
											
											String disabled = "2".equals(isShow)?"disabled='disabled'":"";
											for (int i = 1;i<=attrnum;i++)
											{
												out.println("<td width = \"40%\">");
												if (i == 1)
												{
													out.println(attrname);
												}
												out.println("</td>");
												out.println("<td>");
												out.println("<input id=\""+attrid+"_"+i+"\" name=\"EDIT_"+attrid+"\" "+disabled+" maxlength=\""+addAttrPO.getMaxlength()+"\" value='' onclick=\"selectObj('"+attrid+"_"+i+"');\" class=\"text3 fl \" style=\"margin-left: 10px;\" maxlength=\"11\" type=\""+type+"\" />");
												if ("".equals(disabled))
												{
													out.println("<input type=\"button\" name=\"\" id=\"\" class=\"bt2_liebiao white\" value=\"软键盘\" onClick=\"selectObj('"+attrid+"_"+i+"');openKeyBorad('popup_keyBoard');\" onmousedown=\"this.className='bt2on_1 white'\" onmouseup=\"this.className='bt2_1 white';\"/>");
												}
												out.println("</td>");
												out.println("</tr>");
											}
											
											out.println("</table>");
											out.println("<br>");
										}
										// 单选
										else if ("SELECT".equals(addAttrPO.getAttrtype()))
										{
											// 0：界面不展示 1：界面展示可以编辑 2：界面展示不能编辑
											if ("1".equals(isShow) || "2".equals(isShow))
											{
												out.println("<table class=\"tb_blue\" width=\"100%\">");
											}
											else if ("3".equals(isShow))
											{
												out.println("<table class=\"tb_blue\" width=\"100%\" style=\"display: none;\">");
											}
											
											// 是否可用
											String disabled = "2".equals(isShow)?"return;":"";
											
											// 表头
											attrid = addAttrPO.getAttrid();// 属性ID
											attrname = addAttrPO.getAttrname();// 属性名称
											for (int i=1;i<=attrnum;i++)
											{
												out.println("<tr>");
												out.println("<td>"+attrname+("2".equals(isShow)?"(不可编辑)":"")+"</td>");
												out.println("</tr>");
												out.println("<tr>");
												out.println("<td>");
												String dictinfo =  addAttrPO.getDictinfo();
												String dictitems[] = dictinfo.split("\\|");
												for (int j=0;j<dictitems.length;j++)
												{
													String objs[] = dictitems[j].split("=");
													out.println("<a href=\"javascript:void(0)\" name=\"SELECT_"+attrid+"_"+i+"\" id=\""+attrid+"_"+objs[0]+"_"+i+"\" class=\"bt13\" style = \"display:inline-block;\"; onclick=\""+disabled+"selectAttrByRadio('"+attrid+"','"+objs[0]+"','"+i+"',this,'bt13');\">" + objs[1] + "</a>");
												}
												out.println("</td>");
												out.println("<tr>");
											}
											out.println("</table>");
											out.println("<br>");
										}
									}
									%>
	                                
                                
                                <table class="" width="100%">
                                	<tr>
					                    <td colspan="10" style="text-align: center;">
			        						<input type="button" class="bt2_liebiao white" value="确定" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_1 white';doCommit();"/>	
			        						<input type="button" class="bt2_liebiao white" value="返回" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_1 white';goback();"/>
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
								<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
	<%
	if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
	{
	%>
<div class="popup_confirm" style="width:400px; height:394px; padding-left:400px;" id="popup_keyBoard">
	<div class="bg" style="width:400px; height:394px; left:400px;"></div>	<%
	}
	else
	{
	%>
<div class="popup_confirm" style="width:400px; height:330px; padding-left:400px;" id="popup_keyBoard">
	<div class="bg" style="width:400px; height:330px; left:400px;"></div>	<%
	}
	%>

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
             <a title="确认" href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'; wiWindow.close();">1</a>
         </div>
         <div class="blank10"></div>
     	</div>
  	</div>                
	<!--小键盘-->
	</div>
</div>

<script type="text/javascript">
var numBoardText;
function selectObj(id)
{
	var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	numBoardText=document.getElementById(id);
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
			var textValue=document.getElementById(id).value;
			if(textValue.length<11)
			document.getElementById(id).value+=this.innerHTML;
			var numBoardText=document.getElementById(id);
			var fullClass=this.className;
			var fullValue=numBoardText.value;
			this.className=fullClass.slice(0,fullClass.indexOf('on'));
			moveLast(id);
		}
	}
} 

function moveLast(id)
{
	var r = document.getElementById(id).createTextRange(); 
	r.collapse(false); 
	r.select(); 
}

// 弹出正在处理DIV
openKeyBorad = function(id){
	wiWindow = new OpenWindow("popup_keyBoard", 120, 120);
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
