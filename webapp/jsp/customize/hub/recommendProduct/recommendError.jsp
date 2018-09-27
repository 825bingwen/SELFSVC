<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
String errorMessage = (String) request.getAttribute("errormessage");

// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
errorMessage = CommonUtil.errorMessage(errorMessage);
// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������

String exception_message = (String) request.getAttribute("exception.message");
String backStep = (String) request.getAttribute("backStep");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
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
//��ֹҳ���ظ��ύ
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
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
		<%
		if (null != backStep && !"".equals(backStep.trim()))
		{
		%>
			history.go(parseInt("<%=backStep %>"));
		<%	
		}
		else
		{
		%>
			document.getElementById("curMenuId").value = menuid;
				
			//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
			document.forms[0].submit();
			//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
		<%
		}
		%>
		// modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
	}
}

// ��������ԭҵ��
function contineRec()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
						
		openRecWaitLoading('recWaitLoading');
		document.actform.target="_self";
		document.actform.action = "${sessionScope.basePath}/recommendProduct/contineRec.action";
		document.actform.submit();
	}
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="feeChargeFlag" name="feeChargeFlag" value="<s:property  value='feeChargeFlag'/>"/>
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property  value='servnumber'/>"/>	
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix">
						<dd class="tc" style="">
							<span class="transcact_ok">

						    <%
						    if (errorMessage == null || "".equals(errorMessage))
						    {
						    	if (exception_message != null && !"".equals(exception_message))
						    	{
						    		out.print(exception_message);
						    	}
						    	else
						    	{
						    		out.print("����ʧ�ܣ����Ժ����ԡ�");
						    	}
						    }
						    else
						    {
						    	out.print(errorMessage);
						    }  
						    %>
							   
							</span>
						</dd>
					</dl>
					<div class="btn_box tc">
						<span class=" mr10 inline_block ">
						<a title="��������ҵ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';contineRec();">
						     	��������
						</a>
						</span>
					</div>
				</div>		
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>