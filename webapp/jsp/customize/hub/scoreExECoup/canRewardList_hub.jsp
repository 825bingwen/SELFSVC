<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.customize.hub.selfsvc.scorexecoup.model.RewardAction"%>
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
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}
function showActiveList(dictId){
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("dictId").value = dictId;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }scoreExECoup/ShowActiveList.action";
		document.forms[0].submit();
	}
	
	
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
		<input typt="hidden" name="dictId" id="dictId"/>
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main" >
				<%@ include file="/customer.jsp"%>
				
				
				<div class="service_brand w966 m0auto">
			<p class="tit_info" align="left">
							<span class="bg" ></span><%=menuName%></p>
					<div class="service_list h505">
						<s:if test="canRewardList != null && canRewardList.size() > 0">
							<ul class="clearfix">
								<s:iterator value="canRewardList" id="rw">
									
									
										<li>
											<a class="awrap" href="javascript:void(0);"
												onclick="showActiveList('<s:property value='#rw.actid' />'); return false;">
												<h2>
													<s:property value='#rw.activename' />
												</h2>
												<h3>
													��
												<s:property value='#rw.activename' />�齱����<s:property value='#rw.costdata' />��
												</h3> 
											</a>
										</li>
									
									
								</s:iterator>
							</ul>
						</s:if>
						<s:else>
							<div class="showbutton02" align="center">
								<span class="yellow"><font size="5px;">
									��Ǹ�����ĵ�ǰ����δ�ﵽ���ֳ齱���Ҫ��  </font>
								</span>
							</div>
						</s:else>
					</div>
				</div>					     	
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>	
</html>
