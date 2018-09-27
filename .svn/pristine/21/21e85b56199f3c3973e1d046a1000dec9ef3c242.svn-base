<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.broadBandPay.model.WBandVO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

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

function qryPayType(ncode,prodName,detailDesc,fee)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 宽带产品NCODE
		document.getElementById('ncode').value = ncode;
		
		// 宽带产品名称
		document.getElementById('prodName').value = prodName;
		
		// 宽带产品描述
		document.getElementById('detailDesc').value = detailDesc;
		
		// 受理金额
		document.getElementById('fee').value = fee;

		// 提交
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }broadBandPay/qryPayType.action";
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
			
			<!-- 宽带产品NCODE -->
			<input type="hidden" id="ncode" name="ncode" value=""/>
			
			<!-- 宽带产品名称 -->
			<input type="hidden" id="prodName" name="prodName" value=""/>
			
			<!-- 宽带产品描述 -->
			<input type="hidden" id="detailDesc" name="detailDesc" value=""/>
			
			<!-- 受理金额 -->
			<input type="hidden" id="fee" name="fee" value=""/>
			
			<!-- 手机号所属region -->
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			
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
						<h2>宽带缴费流程：</h2>
      					<div class="blank10"></div>
      					<a title="输入宽带账号" href="#">1. 输入宽带账号</a>
      					<a title="选择宽带产品" href="#" class="on">2. 选择宽带产品</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#">5. 完成</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
					<div class="blank10"></div>
					<table width="100%" class="tb_blue" >
	                  <tr>
	                    <th scope="col">产品名称</th>
	                    <th scope="col">描述信息</th>
	                    <th scope="col">所需费用(元)</th>
	                    <th scope="col">操作</th>
	                  </tr>
	                  <s:iterator value="wbankList" id="list" status="st">
	                  <tr>
		                    <td><s:property value="#list.prodName" /></td>
		                    <td><s:property value="#list.detailDesc" /></td>
		                    <td><s:property value="#list.fee" /></td>
		                    <td>
	                    		<input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="确定" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';qryPayType('<s:property value="#list.ncode" />','<s:property value="#list.prodName" />','<s:property value="#list.detailDesc" />','<s:property value="#list.fee" />')"/>
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

