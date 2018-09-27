<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
	//接收键盘码
	var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 退出
	if (key == 82 || key == 220)
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   		return ;
	}
	// 0键
	else if (key == 48)
	{
		//topGo('qryBill', 'feeservice/feeServiceFunc.action');
		goback('<s:property value='curMenuId' />');
		return;
	}
	
	// 1、2、3等数字键
	<s:iterator value="cycleList" id="list" status="st">
		if (key == <s:property value="#st.index + 49" />)
		{
			view('<s:property value="#list.cycle" />', '<s:property value="#list.startdate" />','<s:property value="#list.enddate" />','<s:property value="#list.acctid" />','<s:property value="#list.unionacct" />');
			return;
		}
	</s:iterator>
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }nxfeeservice/monthBill.action";
		document.forms[0].submit();
	}
}

function view(cycle,startdate,enddate,acctid,unionacct)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// modify begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// modify end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("cycle").value = cycle;
		document.getElementById("startdate").value = startdate;
		document.getElementById("enddate").value = enddate;
		document.getElementById("acctid").value = acctid;
		document.getElementById("unionacct").value = unionacct;

		// 提交
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }nxfeeservice/isNewOrOld.action";
		document.forms[0].submit();
	}
}
</script>
</head>
	<body scroll="no">
	<form name="actform" method="post">
	
		<!-- 月份 -->
		<input type="hidden" id="month" name="month" value="<s:property value="#request.month" />"/>
	
		<!-- 客户信息 -->
		
		<!-- 客户名称 -->
		<input type="hidden" id="custname" name="custname" value="<s:property value="#request.custname" />"/>
		<!-- 品牌 -->
		<input type="hidden" id="brandnm" name="brandnm" value="<s:property value="#request.brandnm" />"/>
		<!-- 主体产品 -->
		<input type="hidden" id="productnm" name="productnm" value="<s:property value="#request.productnm" />"/>
		<!-- 用户ID -->
		<input type="hidden" id="subsid" name="subsid" value="<s:property value="#request.subsid" />"/>
		<!-- 令牌 -->
		<input type="hidden" id="token" name="token" value="<s:property value="#request.token" />"/>
		
	    <!-- 账期信息 -->
	    
		<!-- 账期 -->
		<input type="hidden" id="cycle" name="cycle" value=""/>
		<!-- 开始时间 -->
		<input type="hidden" id="startdate" name="startdate" value=""/>
		<!-- 结束时间 -->
		<input type="hidden" id="enddate" name="enddate" value=""/>
		<!-- 主账号 -->
		<input type="hidden" id="acctid" name="acctid" value=""/>		
		<!-- 是否合户用户 -->
		<input type="hidden" id="unionacct" name="unionacct" value=""/>		
		
		<%@ include file="/titleinc.jsp"%>

			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
        		<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
				
				<div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">请选择查询账期：</p>
		          
		        <div class="blank15"></div>
		        
		        <div class="pl160">
		        	<ul>
		        		<s:iterator value="cycleList" id="list" status="st">
		        			<li class="cycle_1">
			        				<a href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="view('<s:property value="#list.cycle" />', '<s:property value="#list.startdate" />','<s:property value="#list.enddate" />','<s:property value="#list.acctid" />','<s:property value="#list.unionacct" />')">
			        					<s:property value="#list.startdate" /> - <s:property value="#list.enddate" />&nbsp;(按<s:property value="#st.index + 1" />键)
			        				</a>
		        			</li>
		        		</s:iterator>
		        	</ul>
		        </div>		         			
			</div>
			
			<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
