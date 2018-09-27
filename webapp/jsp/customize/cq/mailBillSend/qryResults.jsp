<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.cq.selfsvc.reception.action.MailBillSendAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	// ��ȡ���͵������ַ
	String emailBillServ = (String)request.getAttribute("emailBillServ");
	String emailDetailServ = (String)request.getAttribute("emailDetailServ");
	// ��ȡ��ѯʧ����Ϣ
	String errorMessageBill = (String)request.getAttribute("errormessageBill");
	String errorMessageDetail = (String)request.getAttribute("errormessageDetail");
	// ��ȡoid
	String oidBill = (String)request.getAttribute("oidBill");
	String oidDetail = (String)request.getAttribute("oidDetail");
	// ��ȡ�ͻ���Ϣ
    NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    String telnum = customer.getServNumber();
%>
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

//���˵����굥���͵�ַ���Ϊ139����
function change(oid,flag)
{
    document.actform.target = "_self";
    if(flag == '1')
    {
	    document.actform.action = "${sessionScope.basePath}mailBillSend/changeToBillMail.action?oidBill=" + oid + "&flag=" + flag;
    }
    else if(flag == '2')
    {
	    document.actform.action = "${sessionScope.basePath}mailBillSend/changeToBillMail.action?oidDetail=" + oid + "&flag=" + flag;
    }
	document.actform.submit();
}

//��ͨ139�����˵����굥���͹���
function openMail(flag)
{
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}mailBillSend/openBillOrDetailMail.action?flag=" + flag;
	document.actform.submit();
}

openWindow = function(id)
{
    wiWindow = new OpenWindow(id,708,392);//�򿪵�������
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
            <div class="blank30"></div>
            <div class="relative p40">
            <p class="fs22">�𾴵Ŀͻ��������˵�/�굥����״̬���£�</p>
            <div class="blank15"></div>
            <div class="p20">
                <table width="100%" class="tb_blue" >
                  <tr>
                    <th scope="col">�ʼ�����</th>
                    <th scope="col">״̬</th>
                    <th scope="col">���������ַ</th>
                    <th scope="col">����</th>
                  </tr>
                  <tr>
                    <td>�˵�</td>
                    <%if(errorMessageBill == null)
                      {
                          if("" != emailBillServ && null != emailBillServ && emailBillServ.contains("@139.com"))
                          { %>
                              <td>�ѿ�ͨ</td>
                              <td><%=emailBillServ %></td>
                              <td></td>
                        <%} 
                          else if("" != emailBillServ && null != emailBillServ)
                          {%>
                              <td>�ѿ�ͨ</td>
                              <td><%=emailBillServ %></td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('change_bill_confirm');">���</a></td>
                        <%}
                          else
                          {%>
                              <td>δ��ͨ</td>
                              <td><%=telnum%>@139.com</td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_bill_confirm');">��ͨ</a></td>
                        <%} 
                      }
                      else
                      {%>
                          <td>δ��ͨ</td>
                          <td><%=telnum%>@139.com</td>
                          <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_bill_confirm');">��ͨ</a></td>
                    <%}%>
                  </tr>
                  <tr>
                    <td>�굥</td>
                    <%if(errorMessageDetail == null)
                      {
                          if("" != emailDetailServ && null != emailDetailServ && emailDetailServ.contains("@139.com"))
                          { %>
                              <td>�ѿ�ͨ</td>
                              <td><%=emailDetailServ %></td>
                              <td></td>
                        <%} 
                          else if("" != emailDetailServ && null != emailDetailServ)
                          {%>
                              <td>�ѿ�ͨ</td>
                              <td><%=emailDetailServ %></td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('change_detail_confirm');">���</a></td>
                        <%}
                          else
                          {%>
                              <td>δ��ͨ</td>
                              <td><%=telnum%>@139.com</td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_detail_confirm');">��ͨ</a></td>
                        <%} 
                      }
                      else
                      {%>
                          <td>δ��ͨ</td>
                          <td><%=telnum%>@139.com</td>
                          <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_detail_confirm');">��ͨ</a></td>
                    <%}%>
                  </tr>
                </table>
            </div>
					
					
					
					
            <div class="popup_confirm" id="change_bill_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
                     <p>�𾴵Ŀͻ������Ѷ����˵��ʼĹ��ܣ������ַ��<%=emailBillServ %>����ȷ������Ϊ139������</p>
                     <div class="blank10"></div>
                     <p class="mt30">ȷ�ϱ������"ȷ��"�ύ��</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';change('<%=oidBill%>','1');">ȷ��</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
                     </span>
                  </div>
            </div>
                
                
            <div class="popup_confirm" id="open_bill_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
                     <p>�𾴵Ŀͻ�����ȷ����ͨ�˵��ʼĹ�����(�����ַ��<%=telnum%>@139.com)��</p>
                     <div class="blank10"></div>
                     <p class="mt30">ȷ�Ͽ�ͨ����"ȷ��"�ύ��</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openMail('1');">ȷ��</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
                     </span>
                  </div>
            </div>
            
            <div class="popup_confirm" id="change_detail_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
                     <p>�𾴵Ŀͻ������Ѷ����굥�ʼĹ��ܣ������ַ��<%=emailDetailServ %>����ȷ������Ϊ139������</p>
                     <div class="blank10"></div>
                     <p class="mt30">ȷ�ϱ������"ȷ��"�ύ��</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';change('<%=oidDetail%>','2');">ȷ��</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
                     </span>
                  </div>
            </div>
            
            <div class="popup_confirm" id="open_detail_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
                     <p>�𾴵Ŀͻ�����ȷ����ͨ�굥�ʼĹ�����(�����ַ��<%=telnum%>@139.com)��</p>
                     <div class="blank10"></div>
                     <p class="mt30">ȷ�Ͽ�ͨ����"ȷ��"�ύ��</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openMail('2');">ȷ��</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
                     </span>
                  </div>
            </div>
            
            
	
			</div>				
			    </div>	
		    </div>
				
			    <%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
