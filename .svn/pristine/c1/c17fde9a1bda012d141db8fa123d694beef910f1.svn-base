<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.cq.selfsvc.reception.action.MailBillSendAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	// 获取寄送的邮箱地址
	String emailBillServ = (String)request.getAttribute("emailBillServ");
	String emailDetailServ = (String)request.getAttribute("emailDetailServ");
	// 获取查询失败信息
	String errorMessageBill = (String)request.getAttribute("errormessageBill");
	String errorMessageDetail = (String)request.getAttribute("errormessageDetail");
	// 获取oid
	String oidBill = (String)request.getAttribute("oidBill");
	String oidDetail = (String)request.getAttribute("oidDetail");
	// 获取客户信息
    NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    String telnum = customer.getServNumber();
%>
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

//将账单或详单寄送地址变更为139邮箱
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

//开通139邮箱账单或详单寄送功能
function openMail(flag)
{
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}mailBillSend/openBillOrDetailMail.action?flag=" + flag;
	document.actform.submit();
}

openWindow = function(id)
{
    wiWindow = new OpenWindow(id,708,392);//打开弹出窗口
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
            <p class="fs22">尊敬的客户，您的账单/详单寄送状态如下：</p>
            <div class="blank15"></div>
            <div class="p20">
                <table width="100%" class="tb_blue" >
                  <tr>
                    <th scope="col">邮寄类型</th>
                    <th scope="col">状态</th>
                    <th scope="col">寄送邮箱地址</th>
                    <th scope="col">操作</th>
                  </tr>
                  <tr>
                    <td>账单</td>
                    <%if(errorMessageBill == null)
                      {
                          if("" != emailBillServ && null != emailBillServ && emailBillServ.contains("@139.com"))
                          { %>
                              <td>已开通</td>
                              <td><%=emailBillServ %></td>
                              <td></td>
                        <%} 
                          else if("" != emailBillServ && null != emailBillServ)
                          {%>
                              <td>已开通</td>
                              <td><%=emailBillServ %></td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('change_bill_confirm');">变更</a></td>
                        <%}
                          else
                          {%>
                              <td>未开通</td>
                              <td><%=telnum%>@139.com</td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_bill_confirm');">开通</a></td>
                        <%} 
                      }
                      else
                      {%>
                          <td>未开通</td>
                          <td><%=telnum%>@139.com</td>
                          <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_bill_confirm');">开通</a></td>
                    <%}%>
                  </tr>
                  <tr>
                    <td>详单</td>
                    <%if(errorMessageDetail == null)
                      {
                          if("" != emailDetailServ && null != emailDetailServ && emailDetailServ.contains("@139.com"))
                          { %>
                              <td>已开通</td>
                              <td><%=emailDetailServ %></td>
                              <td></td>
                        <%} 
                          else if("" != emailDetailServ && null != emailDetailServ)
                          {%>
                              <td>已开通</td>
                              <td><%=emailDetailServ %></td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('change_detail_confirm');">变更</a></td>
                        <%}
                          else
                          {%>
                              <td>未开通</td>
                              <td><%=telnum%>@139.com</td>
                              <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_detail_confirm');">开通</a></td>
                        <%} 
                      }
                      else
                      {%>
                          <td>未开通</td>
                          <td><%=telnum%>@139.com</td>
                          <td><a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';openWindow('open_detail_confirm');">开通</a></td>
                    <%}%>
                  </tr>
                </table>
            </div>
					
					
					
					
            <div class="popup_confirm" id="change_bill_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
                     <p>尊敬的客户，您已定制账单邮寄功能，邮箱地址：<%=emailBillServ %>，您确定更改为139邮箱吗？</p>
                     <div class="blank10"></div>
                     <p class="mt30">确认变更请点击"确认"提交。</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';change('<%=oidBill%>','1');">确认</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
                     </span>
                  </div>
            </div>
                
                
            <div class="popup_confirm" id="open_bill_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
                     <p>尊敬的客户，您确定开通账单邮寄功能吗(邮箱地址：<%=telnum%>@139.com)？</p>
                     <div class="blank10"></div>
                     <p class="mt30">确认开通请点击"确认"提交。</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openMail('1');">确认</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
                     </span>
                  </div>
            </div>
            
            <div class="popup_confirm" id="change_detail_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
                     <p>尊敬的客户，您已定制详单邮寄功能，邮箱地址：<%=emailDetailServ %>，您确定更改为139邮箱吗？</p>
                     <div class="blank10"></div>
                     <p class="mt30">确认变更请点击"确认"提交。</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';change('<%=oidDetail%>','2');">确认</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
                     </span>
                  </div>
            </div>
            
            <div class="popup_confirm" id="open_detail_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">提示：</div>
                  <div class="tips_body">
                     <p>尊敬的客户，您确定开通详单邮寄功能吗(邮箱地址：<%=telnum%>@139.com)？</p>
                     <div class="blank10"></div>
                     <p class="mt30">确认开通请点击"确认"提交。</p>
                  </div>
                  <div class="btn_box tc mt20">
                     <span class=" mr10 inline_block ">
                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openMail('2');">确认</a>
                     </span>
                     <span class=" inline_block ">
                        <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
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
