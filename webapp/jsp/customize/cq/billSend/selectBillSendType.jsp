<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
	var key=GetKeyCode(e);
	if(key == 82 || key == 220) 
	{
	  goback("<s:property value='curMenuId' />");
	  return;
	}
}

function billSendCommit(billSendType,oprtype)
{
	document.actform.target="_self";
	document.actform.action="${sessionScope.basePath}billSend/billSendCommit.action?billSendType=" + billSendType+"&oprtype="+oprtype;
	document.actform.submit();
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
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">			
		<%@ include file="/titleinc.jsp"%>
		<div class="main" id="main">
		
			<%@ include file="/customer.jsp"%>
			
				  <div class="service_brand w966 m0auto">
				    <div class="service_index_list">
				    <p class="hot_service"></p>
				    	<div class="p40">
							<div class="blank10"></div>
							<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">�𾴵Ŀͻ��������˵�����״̬���£�</span></p>
							<div class="blank25"></div>
							<div class="blank25"></div>
							<table width="100%" class="tb_blue" >
			                  <tr>
			                    <th scope="col">���ͷ�ʽ</th>
			                    <th scope="col">״̬</th>
			                    <th scope="col">����</th>
			                  </tr>
			                  <tr>
			                    <td>����</td>
			                    <s:if test="smsState==1">
			                    <td>�ѿ�ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpSms','0');">ȡ��</a></td>
			                    </s:if>
			                    <s:if test="smsState==0">
			                    <td>δ��ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpSms','1');">��ͨ</a></td>
			                    </s:if>
			                  </tr>
			                  <tr>
			                    <td>����</td>
			                    <s:if test="mmsState==1">
			                    <td>�ѿ�ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpMms','0');">ȡ��</a></td>
			                    </s:if>
			                    <s:if test="mmsState==0">
			                    <td>δ��ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpMms','1');">��ͨ</a></td>
			                    </s:if>
			                  </tr>
			                  <s:if test="emailService==1">
			                  <tr>
			                    <td>139����</td>
			                    <s:if test="emlState==1">
			                    <td>�ѿ�ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpEml','0');">ȡ��</a></td>
			                    </s:if>
			                    <s:if test="emlState==0">
			                    <td>δ��ͨ</td>
			                    <td><a class="bt2" href="#" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';billSendCommit('mltpEml','1');">��ͨ</a></td>
			                    </s:if>
			                  </tr>
			                  </s:if>
			                  </table>
       					</div>
				    </div>
				  </div>
			  
		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
