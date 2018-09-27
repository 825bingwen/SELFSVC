<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.common.base.CEntityString"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<%@page import="com.gmcc.boss.selfsvc.util.CurrencyUtil"%>
<%@page import="com.customize.cq.selfsvc.prodOrder.model.ProdOrderPO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String curmenuid = (String)request.getAttribute(Constants.CUR_MENUID);
	
	List<ProdOrderPO> availProdList = (List)request.getAttribute("availProdList");
	int pageCount = 1;
	String firstFlag = "";
	if(request.getAttribute("firstFlag") != null)
	{
		firstFlag = (String)request.getAttribute("firstFlag");
	}
	if(request.getAttribute("pageCount") != null)
	{
		pageCount = Integer.valueOf((String)request.getAttribute("pageCount"));
	}
	int pageNum = 5;
%>
<html>
<head>
<title>产品查询受理</title>
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

function doCommit(ncode,effType)
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		document.getElementById("ncode").value = ncode;
		document.getElementById("effType").value = effType;
		document.getElementById("operType").value = "ADD";
		document.actform.action="${sessionScope.basePath}prodOrder/orderProd.action";
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
		goback("<s:property value='#request.curMenuId' />");
		return;
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
// 上一页
function previousPage()
{
	if (submitFlag == 0)
	{
		// 如果当前页为第一页则不进行上一页操作
		if(<%=pageCount%>==1)
		{
			return;
		}
		submitFlag = 1;
		document.getElementById("pageCount").value = "<%=pageCount-1%>";
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }prodOrder/queryProdList.action";
		document.actform.submit();
	}
}
// 下一页
function nextPage(totalNum)
{
	if (submitFlag == 0)
	{
	 	// 如果为最后一页则不进行下一页操作
		if(<%=pageCount * pageNum%> > totalNum )
		{
			return;
		}
		submitFlag = 1;
		document.getElementById("pageCount").value = "<%=pageCount+1%>";
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }prodOrder/queryProdList.action";
		document.actform.submit();
	}
}
</script>
</head>
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" name="ncode" value=""/>
		<input type="hidden" name="effType" value=""/>
		<input type="hidden" name="operType" value=""/>
		<input type="hidden" id="firstFlag" name="firstFlag" value="1"/>
		<input type="hidden" id="type" name="type" value="1"/>
		<input type="hidden" id="pageCount" name="pageCount" value=""/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('recProdOrder', 'prodOrder/showType.action'); return false;" style="display:inline">返回类型选择</a>
		        <!-- 如果只有一页则不显示分页 -->
		      <%
		      if(availProdList.size() > pageNum)
		      {
		      	%>
		      		<div class="num_foot fs18">  
						<div class="recption"> 
							<a href="javascript:previousPage()" class="btleft relative"></a> 
							<span class="ml20">第<%=pageCount %>页，共<%=(availProdList.size()/pageNum)+1 %>页</span> 
						<a href="javascript:nextPage(<%=availProdList.size() %>)" class="btright ml20 relative"></a> 
						</div>
					</div>
		      	<%
		      }
		       %>  
		        <!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
		                        <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px; width:747px; overflow:hidden;" >
								
				                <table width="100%" class="tb_blue" >
				               		<tr>
										<th class="list_title" align="center" colspan="20">可订购业务</th>
		                            </tr>
				                  <tr>
				                    <th scope="col">业务名称</th>
				                    <th scope="col">资费说明</th>
				                    <th scope="col">操作</th>
				                  </tr>
				                  <% 
				                  	if(availProdList != null && availProdList.size() > 0)
				                  	{
				                  		int start = (pageCount-1) * pageNum;
								    	int end = (pageCount) * pageNum ;
								    	if(end > availProdList.size())
								    	{
								    		end = availProdList.size();
								    	}
				                  		for(int j =start;j < end;j++)
				                  		{
				                  			ProdOrderPO prodAvail = availProdList.get(j);
				                  			String curGroupId = "";//当前分组ID
				                  			if(prodAvail.getGroupcolor()!=null)curGroupId = prodAvail.getGroupcolor();
									      	%>
									      		<tr>
								                    <td><%if(!"".equals(curGroupId)){%>
								                    <font color="<%=curGroupId%>"/><%}%><%= prodAvail.getName()%></td>
								                    <td><%= prodAvail.getRemark()%></td>
								                    <td width="200px">
								                    <%if("0".equals(prodAvail.getEfftype())||"2".equals(prodAvail.getEfftype())){ %>
							                    	<input type="button" class="bt2_liebiaoCQ" style="color:#FFFFFF;"
							                    	  value="立即开通" onmousedown="this.className='bt2onCQ'" 
								                    	onmouseup="this.className='bt2CQ';doCommit('<%=prodAvail.getNcode() %>','2');"/>
				                  					<%}
				                  					if("1".equals(prodAvail.getEfftype())||"2".equals(prodAvail.getEfftype())){
				                  					 %>
				                  					<input type="button" class="bt2_liebiaoCQ" style="color:#FFFFFF;"
							                    	  value="下月开通" onmousedown="this.className='bt2onCQ'" 
								                    	onmouseup="this.className='bt2CQ';doCommit('<%=prodAvail.getNcode() %>','3');"/>
				                  					</td>
				                  					<%} %>
				                  				</tr>
									      	<%
									      }
								      }
				                  %>
				                </table>
				                
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
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
</html>
