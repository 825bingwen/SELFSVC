<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.chooseTel.model.ServerNumPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<%
	// 查询号码列表结果
	List<ServerNumPO> serverNumList = (List<ServerNumPO>)request.getAttribute("serverNumList");	
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
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<style type="text/css">
			.tab_966_brand1 { width:600px; height:539px; background:url(../images/bg_tab_966_brand.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_tab_966_brand.png");_background:none}
			</style>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
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
function selectTelNum(telnum,payfee,minimumCharge)
{
	if (telnum == '')
	{
		document.getElementById('chooseNum').style.visibility='hidden';
		document.getElementById('telnum').value = '';
		document.getElementById('payfee').value = '';
		document.getElementById('minCharge').value = '';
		return;
	}
	var displayFee = (Number(payfee)/100).toFixed(2);
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('telnum').value = telnum;
	document.getElementById('payfee').value = payfee;
	document.getElementById('chooseNum').innerHTML = '';
	document.getElementById('chooseNum').style.visibility='visible';
	///modify begin liutao 2016-07-30 OR_HUB_201603_1191  自助终端显示内容优化需求（张德伟）
	var displayMinimumCharge = (Number(minimumCharge)/100).toFixed(2);
	document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">' + telnum + '</p><p class="fs14 tc">预存费：' + displayFee+'</p>'+ ' <p class="fs14 tc">最低消费:'+displayMinimumCharge+' </p>';
	document.getElementById('winText').innerHTML = '<span class="yellow">您选择预订：</span><br />号码：' + telnum + '<span class="ml20">预存费：</span><span class="">' + displayFee + '</span>元'+'<span class="ml20">最低消费：</span><span class="">' + displayMinimumCharge + '</span>元';

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

// 自选号码搜索页面
function phoneSearch()
{
	// 执行搜索
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/phoneSearchByFinalNbr.action?bz=1";
		document.actform.submit();
	}	
}
</script>

	</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="telnum" id="telnum" value="">
			<input type="hidden" name="payfee" id="payfee" value="">
			<input type="hidden" name="minimumCharge" id="minimumCharge" value="">
			
			<%@ include file="/titleinc.jsp"%>			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a title="自选号码搜索" href="#" class="bt5 num_backtonum" onmousedown="this.className='bt5on num_backtonum'" onmouseup="this.className='bt5 num_backtonum';phoneSearch();">自选号码搜索 >></a>
				<!--滚动条-->
				<div class="box842W fl ml20 relative tab_966 tab_966_brand1" style="display:inline">
				  	<div class="bg2"></div>
				  	<div class="blank60"></div>
				  	<div class=" blank50"></div>
				 	<p class=" fs18 fl ml30">点击即可选定号码，您每次可以预订<span class="yellow">1</span>个号码。</p>
				  	<div class=" blank50"></div>
				  	<div class=" clear"></div>

				  	<div class="num_dis">				  
					    <%
					    for (int i = 0; i < serverNumList.size(); i++)
					    {
					    	ServerNumPO po = serverNumList.get(i);
					    %>
					  	<a title="" onclick="selectTelNum('<%=po.getTelnum() %>', '<%=po.getSeltel_prepayfee() %>',<%=po.getMinimumCharge() %>'')" href="javascript:void(0)"><span></span><%=po.getTelnum() %></a>
					  	<%
					  	}
					  	%>
				    	<div class="clear"></div>
				  	</div>
				  	
  					<div class="num_foot fs18 pl18">   					
    					<s:if test="pageFlag == 'true'">
    						<pg:paginator recordsCount="${recordCount }" pageSize="${pageviewnum }" page="${page }" linkUrl="chooseTel/selectRegion.action" alignType="left" />
    					</s:if>
    					<s:else>
    						<div class='page-left'></div>
    					</s:else>
    					<div class="fright1"> 
    						<span class="fl pt15">已选定号码：</span> 
							<a title="" href="javascript:void(0)" class="bt_choosenum2 fl" style=" visibility:hidden" id="chooseNum">
								<p class="fs16 tc" id="telnumText">13800000000</p>
								<p class="fs14 tc" id="payfeeText">预存费：100.00 元</p>
								<p class="fs14 tc" id="minChargeText">最低消费：100.00 元</p>
        					</a>
        					<a title="预订" href="javascript:void(0)" class="bt2 fr relative fl"  onmousedown="this.className='bt2on fl relative'" onmouseup="this.className='bt2 fl relative';openWindow('openWin1')">预订</a> 
        				</div>
					</div>
				</div>
				<!--滚动条结束-->
				<div class=" clear"></div>
				<!--弹出窗-->
				<div class="openwin_tip" id="openWin1">
				  	<div class="bg"></div>
					<div class=" blank60"></div>
					<div class=" blank60"></div>
					<div class="  blank10n"></div>
					<p class="fs28 lh2 pl142" id="winText" name="winText">
						<span class="yellow">您选择预订：</span> <br />
						号码：13948603946
						<span class="ml20">预存费：</span><span class="">100.00</span>元
						<span class="ml20">最小消费：</span><span class="">100.00</span>元
					</p>
  					<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
    					<a title="确认" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">确认</a> 
    					<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
    				</div>
				</div>

				<div class="openwin_tip openwin_big div804w515h" id="openWinLoading">
  					<div class="bg loading tc">
					    <div class="blank60"></div>
					    <div class="blank60"></div>
					    <img src="${sessionScope.basePath }images/loading.gif"  class="" alt="处理中..."/>
					    <div class="blank30"></div>
					    <p class="fs24   lh2">正在执行预定信息处理，请稍候...</p>
					    <p class=" fs18  lh2 yellow"></p>
  					</div>
				</div>

				<script type="text/javascript">
				openWindow = function(id){
					// 检查是否已选中
					if (document.getElementById('telnum').value == '')
					{
						return;
					}
					
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
				}
				
				openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",804,515);//打开弹出窗口例子
					gotoPrintSuccess();					
				}				
				
				function gotoPrintSuccess(){
					setTimeout(
						function(){
							wiWindow.close();
							if (window.parent.isPrintFlag == 1)// 支持打印
							{
								document.actform.target="_self";
								document.actform.action="${sessionScope.basePath }chooseTel/validateFinish.action";
								document.actform.submit();
							}
							else
							{
								document.actform.target="_self";
								document.actform.action="${sessionScope.basePath }chooseTel/inputIdCard.action";
								document.actform.submit();
							}
							
						},
					500);
				}
				</script>
				<!--弹出窗结束-->				
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
