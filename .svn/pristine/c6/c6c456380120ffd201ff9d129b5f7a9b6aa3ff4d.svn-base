<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>移动自助终端</title>
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
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiDesc.action";
		document.forms[0].submit();
	}
}

function goSelectType()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/recDutyInfo.action";
		document.forms[0].submit();
	}
}

// 下一页
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = linkURL;
		document.forms[0].submit();
	}
}

</script>
</head>
	<body scroll="no">
	<form name="actform" method="post">
	
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- 活动组ID -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
		
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
		
		<!-- 当前页 -->
		<input type="hidden" name="currentPage_present" id="currentPage_present" value='<s:property value="#request.currentPage_present" />'/>
		<!-- 总条数 -->
		<input type="hidden" name="countNum_present" id="countNum_present" value='<s:property value="#request.countNum_present" />'/>
		<!-- 总页数 -->
		<input type="hidden" name="countPageNum_present" id="countPageNum_present" value='<s:property value="#request.countPageNum_present"/>'/>
		<!-- 每页条数 -->
		<input type="hidden" name="pageNum_present" id="pageNum_present" value='<s:property value="#request.pageNum_present" />'/>
		
		<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<a title="选择促销活动" href="#">1. 选择促销活动</a>
      					<a title="选择活动档次" href="#" class="on">2. 选择活动档次</a>
      					<a title="受理协议" href="#">3. 受理协议</a>  
      					<a title="选择支付方式" href="#">4. 选择支付方式</a> 
      					<a title="投入纸币" href="#">5. 投入纸币</a>
          				<a title="完成" href="#">6. 完成</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>赠品列表<span class="yellow"></span>
						</p>
						<div class="blank10"></div>
					
		                <table width="100%" class="tb_blue" >
		                  <tr>
		                    <th scope="col" style="display: none;">赠品编码</th>
		                    <th scope="col">赠品名称</th>
		                    <th scope="col">赠品类型</th>
		                    <th scope="col">赠品价值(元)</th>
		                    <th scope="col">积分扣减类型</th>
		                    <th scope="col">扣减积分数额</th>
		                    <th scope="col">赠品说明</th>
		                  </tr>
		                  
		                  <s:iterator value="awardList" id="list" status="st">
		                  <tr>
			                    <td style="display: none;"><s:property value="#list.rewardId" /></td>
			                    <td><s:property value="#list.rewardName" /></td>
			                    <td><s:property value="#list.rewardTypeName" /></td>
			                    <td><s:property value="#list.rewardValue" /></td>
			                    <td><s:property value="#list.scoreDealTypeName" /></td>
			                    <td><s:property value="#list.userScore" /></td>
			                    <td><s:property value="#list.rewardNote" /></td>
		                  </tr>
		                  </s:iterator>
		                  <tr>
		                  	<td colspan="7">
		                  		<input type="button" class="bt2_liebiao" style="color:#FFFFFF;"
		                    	  value="办理" onmousedown="this.className='bt2on'" 
			                    	onmouseup="this.className='bt2';goSelectType()"/>
		                  	</td>
		                  </tr>
		                </table>
				                
					</div>	
					
					<!-- 分页 -->
					<pg:paginator recordsCount="${recordCount }" pageSize="${pageNum_present }" page="${page }" linkUrl="activitiesRec/queryPresentsList.action" />
						
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
			
	</form>
</body>
</html>
