<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
<script>
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;
function pwdKeyboardDown(e)
{
	var key = GetKeyCode(e);
	//����
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//��仰��ؼ�
	}
}

function KeyIsNumber(KeyCode) 
{
	//ֻ��������0-9
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

function view(cycle,startdate,enddate,acctid,unionacct)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("cycle").value = cycle;
		document.getElementById("startdate").value = startdate;
		document.getElementById("enddate").value = enddate;
		document.getElementById("acctid").value = acctid;
		document.getElementById("unionacct").value = unionacct;

		<%-- add begin hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
		openRecWaitLoading();
		<%-- add end hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>

		// �ύ
		document.forms[0].target = "_self";
		//document.forms[0].action = "${sessionScope.basePath }monthFee/monthFee_new.action";
		document.forms[0].action = "${sessionScope.basePath }monthFee/isNewOrOld.action";
		document.forms[0].submit();
	}
}
</script>
</head>
	<body scroll="no">
	<form name="actform" method="post">
	
		<!-- �·� -->
		<input type="hidden" id="month" name="month" value="<s:property value="#request.month" />"/>
	
		<!-- �ͻ���Ϣ -->
		
		<!-- �ͻ����� -->
		<input type="hidden" id="custname" name="custname" value="<s:property value="#request.custname" />"/>
		<!-- Ʒ�� -->
		<input type="hidden" id="brandnm" name="brandnm" value="<s:property value="#request.brandnm" />"/>
		<%--add by lKF60882 2016-10-10 OR_SD_201604_913_ɽ��_�����ڸ��˵���ѯ���ܽ����������Ǽ�չʾ������--%>
		<!-- �ͻ��Ǽ� -->
		<input type="hidden" id="subsCreditName" name="subsCreditName" value="<s:property value="#request.subsCreditName" />"/>
		<!-- �����Ʒ -->
		<input type="hidden" id="productnm" name="productnm" value="<s:property value="#request.productnm" />"/>
		<!-- �û�ID -->
		<input type="hidden" id="subsid" name="subsid" value="<s:property value="#request.subsid" />"/>
		<!-- ���� -->
		<input type="hidden" id="token" name="token" value="<s:property value="#request.token" />"/>
		
	    <!-- ������Ϣ -->
	    
		<!-- ���� -->
		<input type="hidden" id="cycle" name="cycle" value=""/>
		<!-- ��ʼʱ�� -->
		<input type="hidden" id="startdate" name="startdate" value=""/>
		<!-- ����ʱ�� -->
		<input type="hidden" id="enddate" name="enddate" value=""/>
		<!-- ���˺� -->
		<input type="hidden" id="acctid" name="acctid" value=""/>		
		<!-- �Ƿ�ϻ��û� -->
		<input type="hidden" id="unionacct" name="unionacct" value=""/>		
		
		<%@ include file="/titleinc.jsp"%>

			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����˵��굥��ѯ</a>
				
				<div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">��ѡ���ѯ���ڣ�</p>
		          
		        <div class="blank15"></div>
		        
		        <div class="pl160">
		        	<ul>
		        		<s:iterator value="cycleList" id="list" status="st">
		        			<li class="cycle">
			        				<a href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="view('<s:property value="#list.cycle" />', '<s:property value="#list.startdate" />','<s:property value="#list.enddate" />','<s:property value="#list.acctid" />','<s:property value="#list.unionacct" />')">
			        					<s:property value="#list.startdate" /> - <s:property value="#list.enddate" />
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