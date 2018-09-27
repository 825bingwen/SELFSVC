<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.chooseTel.model.NetNbrPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	// 全部数据
	List<NetNbrPO> netNbrTmpList = (List<NetNbrPO>)request.getAttribute("netNbrTmpList");
	
	// 查询网号列表结果
	List<NetNbrPO> netNbrList = (List<NetNbrPO>)request.getAttribute("netNbrList");
	
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
<script type="text/javascript">
var submitFlag = 0;
		
function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		/**
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		**/
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
		document.actform.submit();
		
	}			
}
// 处理键盘事件
document.onkeydown = keyDown;
function keyDown(e)
{
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:返回
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}

// 选择号码
function selectNetNbr(netNbr)
{
	if (netNbr == '')
	{
		document.getElementById('netNbr').value == '';
		return;
	}
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('netNbr').value = netNbr;
	document.getElementById('chooseNum').innerHTML = '';
	if ('' != netNbr)
	{
		document.getElementById('chooseNum').style.visibility='visible';
		document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">号段：'+netNbr+'</p>';
	}
}

// 下一页
function nextPage(linkURL)
{
	// 执行查询
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}		
}

// 转到按网号搜索页面
function finalNbrSearch()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/phoneSearchByFinalNbr.action?bz=1";
		document.actform.submit();
	}		
}

// 转到按号段搜索页面
function netValueSearch()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/phoneSearchByNetValue.action?bz=1";
		document.actform.submit();
	}		
}

// 按网号进行搜索
function doNetNbrSearch()
{
	if (document.getElementById('netNbr').value == '')
	{
		return;
	}
	document.actform.target="_self";
	document.actform.action="${sessionScope.basePath }chooseTel/netNbrSearchResult.action?bz=1";
	document.actform.submit();	
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
			<input type="hidden" name="netNbr" id="netNbr" value="">
			<input type="hidden" name="finalNbr" id="finalNbr" value="">
				
          <%@ include file="/customer.jsp"%>
          <a title="按网段搜索" href="#" class="bt5 num_intro x_66" onmousedown="this.className='bt5on num_intro x_66'" onmouseup="this.className='bt5 num_intro x_66';netValueSearch()">按网段搜索</a>
          <a title="按尾号搜索" href="#" class="bt5 num_backtonum" onmousedown="this.className='bt5on num_backtonum'" onmouseup="this.className='bt5 num_backtonum';finalNbrSearch()">按尾号搜索</a>
          <div class="num_brand fs24 lh2 p20">
           按网号搜索
          </div>
       
		<div class="box842W fl ml20 relative tab_966  tab_966_result " style="display:inline">
                    
                    <div class="bg2"></div>
                    <div class="blank50"></div><div class="  blank50"></div>
                    <div class="num_dis" >
                      <p class="fs18">共<span class="yellow"><%=netNbrTmpList.size() %>个</span>搜索结果。点击网号即可放入选定号码区。</p>
                      <div class="blank10"></div>
                      <%
					  for(NetNbrPO po:netNbrList)
					  {
					  %>
                      <a title="" onclick="selectNetNbr('<%=po.getNetNbr() %>')" href="javascript:void(0)"><span></span><%=po.getNetNbr() %></a>
                      <%
                      }
                      %>
                    </div>
                    
                    
                    <div class="  blank10"></div>
                    
                    <div class="num_foot fs18 pt0 pl23"> 
                      <s:if test="pageFlag == 'true'">
    						<pg:paginator recordsCount="${recordCount }" pageSize="${pageviewnum }" page="${page }" linkUrl="chooseTel/phoneSearchByNetNbr.action" alignType="left" />
    				</s:if>
    				<s:else>
    						<div class='page-left'></div>
    				</s:else>
                      <div class="fright1">
                        <span class="fl pt15">已选定号段：</span>
                        <a title="号段" href="javascript:void(0)" class="bt_choosenum2 fl" style=" visibility:hidden" id="chooseNum">
                         		<p class="fs16 tc" id="telnumText">号段：138</p>
                        </a>
                        <a title="搜索" href="javascript:void(0)" class="bt2 fr relative fl"  onmousedown="this.className='bt2on fl relative'" onmouseup="this.className='bt2 fl relative';" onclick="doNetNbrSearch();return false;">搜索</a>
                      </div>
                    </div>
					
				</div>
                
				
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
