<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.activitiesrec.model.ActivityVO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	// 查询号码列表结果
	List<ActivityVO> activityList = (List<ActivityVO>)request.getAttribute("activityList");
	
	String errorMsg = request.getAttribute("errorMsg")==null?"":(String)request.getAttribute("errorMsg");

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
//防止页面重复提交
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
		goback("<s:property value='curMenuId' />");
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

function queryDangCiList(groupId,groupName)
{
	if (groupId == "" || groupName == "")
	{
		return;
	}
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("groupId").value = groupId;
		document.getElementById("groupName").value = groupName;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiList.action";
		document.forms[0].submit();
	}
}

function queryDangCiDesc(groupId,groupName,dangciId,dangciName,activityId,activityName,prepayFee)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 活动组编码
		document.getElementById('groupId').value = groupId;
		
		// 活动级名称
		document.getElementById('groupName').value = groupName;
		
		// 活动编码
		document.getElementById("activityId").value = activityId;
		
		// 活动名称
		document.getElementById("activityName").value = activityName;
		
		// 档次编码
		document.getElementById("dangciId").value = dangciId;
		
		// 档次名称
		document.getElementById("dangciName").value = dangciName;
		
		// 受理金额
		document.getElementById("prepayFee").value = prepayFee;

		// 提交
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiDesc.action";
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
			<form id="actform" name="actform" method="post">
			
			<!-- 活动组ID -->
			<input type="hidden" id="groupId" name="groupId" value=""/>
			
			<!-- 活动组名称 -->
			<input type="hidden" id="groupName" name="groupName" value=""/>
			
			<!-- 活动编码 -->
			<input type="hidden" id="activityId" name="activityId" value=""/>
			
			<!-- 活动名称 -->
			<input type="hidden" id="activityName" name="activityName" value=""/>
			
			<!-- 档次编码 -->
			<input type="hidden" id="dangciId" name="dangciId" value=""/>
			
			<!-- 档次名称 -->
			<input type="hidden" id="dangciName" name="dangciName" value=""/>
			
			<!-- 受理金额 -->
			<input type="hidden" id="prepayFee" name="prepayFee" value=""/>
			
			<!-- 当前页 -->
			<input type="hidden" name="currentPage" id="currentPage" value='<s:property value="#request.currentPage" />'/>
			<!-- 总条数 -->
			<input type="hidden" name="countNum" id="countNum" value='<s:property value="#request.countNum" />'/>
			<!-- 总页数 -->
			<input type="hidden" name="countPageNum" id="countPageNum" value='<s:property value="#request.countPageNum"/>'/>
			<!-- 每页条数 -->
			<input type="hidden" name="pageNum" id="pageNum" value='<s:property value="#request.pageNum" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<a title="选择活动档次" href="#" class="on">1. 选择活动档次</a>
      					<a title="受理协议" href="#">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#">5. 完成</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<!--
						<p class=" tit_info">
							<span class="bg"></span>档次列表<span class="yellow"></span>
						</p>
							-->
						<div class="blank10"></div>
					<table width="100%" class="tb_blue" >
	                  <tr>
	                    <th scope="col">活动</th>
	                    <th scope="col">档次</th>
	                    <th scope="col">赠品价值(元)</th>
	                    <th scope="col">受理金额(元)</th>
	                    <th scope="col">操作</th>
	                  </tr>
	                  <s:iterator value="activityList" id="list" status="st">
	                  <tr>
	                  		<td><s:property value="#list.groupName" /></td>
		                    <td><s:property value="#list.dangciName" /></td>
		                    <td><s:property value="#list.presentValue" /></td>
		                    <td><s:property value="#list.prepayFee" /></td>
		                    <td>
	                    		<input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="确定" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';queryDangCiDesc('<s:property value="#list.groupId" />','<s:property value="#list.groupName" />','<s:property value="#list.dangciId" />','<s:property value="#list.dangciName" />', '<s:property value="#list.activityId" />','<s:property value="#list.activityName" />','<s:property value="#list.prepayFee" />')"/>
	                    	</td>
	                    	            
	                  </tr>
	                  </s:iterator>
	                </table>
  						
  						
					</div>	
					
					<!-- 分页 -->
					<pg:paginator recordsCount="${recordCount }" pageSize="${pageNum }" page="${page }" linkUrl="activitiesRec/queryActivities.action" />
						
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
			
		</form>
	</body>
</html>

