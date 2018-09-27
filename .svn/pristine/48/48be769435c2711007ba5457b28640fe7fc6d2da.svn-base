<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.chooseTel.model.ServerNumPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

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
function selectTelNum(telnum,payfee)
{
	if (telnum == '')
	{
		return;
	}
	var displayFee = (Number(payfee)/100).toFixed(2);
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('luckyNumRule').value = telnum;
	document.getElementById('chooseNum').innerHTML = '';
	document.getElementById('chooseNum').style.visibility='visible';
	document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc" style="padding-top: 10px;">' + telnum + '</p>';
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

function qryTelnumsWithType(telnumType)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("telNumType").value = telnumType;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/qryTelnumsWithType.action";
		document.actform.submit();
	}
}

function qryLuckyNums()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/qryLuckyNums.action";
		document.actform.submit();
	}
}
</script>

	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" name="telNumType" id="telNumType" value="<s:property value='telNumType' />">			
			<input type="hidden" name="luckyNumRule" id="luckyNumRule" value="<s:property value='luckyNumRule' />">
			
			<%@ include file="/titleinc.jsp"%>			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<a title="自选号码搜索" href="#" class="bt5 num_backtonum" onmousedown="this.className='bt5on num_backtonum'" onmouseup="this.className='bt5 num_backtonum';phoneSearch();">自选号码搜索 >></a>
				<!--滚动条-->
				<div class="box842W fl ml20 relative tab_966 tab_966_brand" style="display:inline">
					<div class="bg2"></div>
  					<div class="blank60"></div>
  					<div class=" blank50"></div>
  					<p class=" fs18 fl ml30">点击即可选定吉祥号码规则，您每次可以选择<span class="yellow">1</span>种规则。</p>
  					
  					<div class=" clear"></div>
  					
  					<div class="tab_num" style="margin-top: 30px;" >
  						<s:if test="telNumType == 'ALL'">
  							<a href="javascript:void(0)" class="on">所有号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('ALL');return false;">所有号码</a>
  						</s:else>
  						
  						<s:if test="telNumType == 'GOOD'">
  							<a href="javascript:void(0)" class="on">吉祥号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('GOOD');return false;">吉祥号码</a> 
  						</s:else>
  						
  						<s:if test="telNumType == 'LOVE'">
  							<a href="javascript:void(0)" class="on">情侣号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('LOVE');return false;">情侣号码</a>
  						</s:else>
  						
  						<s:if test="telNumType == 'AABB'">
  							<a href="javascript:void(0)" class="on">AABB组合号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('AABB');return false;">AABB组合号码</a>
  						</s:else>
  						
  						<s:if test="telNumType == 'ABAB'">
  							<a href="javascript:void(0)" class="on">ABAB组合号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('ABAB');return false;">ABAB组合号码</a>
  						</s:else>
  						
  						<s:if test="telNumType == 'ABBB'">
  							<a href="javascript:void(0)" class="on">ABBB组合号码</a>
  						</s:if>
  						<s:else>
  							<a href="javascript:void(0)" onclick="qryTelnumsWithType('ABBB');return false;">ABBB组合号码</a>
  						</s:else>
  						
    					<div class="clear"></div>
 					</div>
 					
 					<div class="num_dis">
 						<s:if test="luckyNumRules != null && luckyNumRules.size > 0">
 							<s:iterator value="luckyNumRules" id="rule">
	 							<a title="" onclick="selectTelNum('<s:property value="#rule.dictid" />', '')" href="javascript:void(0)">
	 								<span></span><s:property value="#rule.dictname" />
	 							</a>
	 						</s:iterator>
 						</s:if>
 						
 						<div class="clear"></div>
  					</div>
  					
  					<div class="num_foot fs18 pl18">   					
    					<div class='page-left'></div>
    					
    					<div class="fright1"> 
    						<span class="fl pt15">已选定规则：</span> 
							<a title="吉祥号码规则" href="javascript:void(0)" class="bt_choosenum2 fl" style=" visibility:hidden" id="chooseNum">
								<p class="fs16 tc" id="telnumText" style="padding-top: 10px;">13800000000</p>
        					</a>
        					<a title="搜索" href="javascript:void(0)" class="bt2 fr relative fl"  onmousedown="this.className='bt2on fl relative'" onmouseup="this.className='bt2 fl relative';" onclick="qryLuckyNums();return false;">搜索</a> 
        				</div>
					</div>
				</div>						
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
