<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.cq.selfsvc.reception.action.MailBillSendAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	// ��ȡ�ͻ���Ϣ
    NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    String telnum = customer.getServNumber();
    
    // ��ȡ�ͻ�����ֵ
    String leftScore = (String)request.getAttribute("leftScore");
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

//�һ�����
function openScore()
{
    var activeno = document.getElementById("activeno").value;
    var nlevel = document.getElementById("nlevel").value;
    var itemid = document.getElementById("itemid").value;
    
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}scoreExchange/scoreToBalance.action?activeno=" + activeno + "&nlevel=" + nlevel + "&serviceid=" + itemid;
	document.actform.submit();
}

function passParam(activeno, nlevel, itemid)
{
    document.getElementById("activeno").value = activeno;
    document.getElementById("nlevel").value = nlevel;
    document.getElementById("itemid").value = itemid;
}

function openWindow(id)
{
    wiWindow = new OpenWindow(id,708,392);//�򿪵�������
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<input id="activeno" value="" type="hidden">
			<input id="nlevel" value="" type="hidden">
			<input id="itemid" value="" type="hidden">
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				
				<!--������-->
							<div class="box842W fl ml45 relative">
								<div class="bg"></div>
								<div class="top"></div>
								<div class="con relative" >
									<div class="box747W fl">					
										<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
											<!-- �б����� -->
					                      	<p class="tit_info" align="left"><span class="bg"></span>�𾴵Ŀͻ�����Ŀǰ�Ļ���ֵΪ��<%=leftScore%>��</p>
											<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
											<table width="95%" class="tb_blue" align="center">
												<tr class="tr_color">
													<th class='list_title'>
														ҵ������
													</th>
													<th class='list_title'>
														�������
													</th>
													<th class='list_title'>
														���ֶһ�
													</th>
												</tr>
												
												<s:iterator value="#request.scoreExchangeInfo" id="data" status="st">
														<tr>
															<td>
																<s:property value="#data.note" />
															</td>
															<td>
																<s:property value="#data.score" />
															</td>
															<s:if test="#data.score <= #request.leftScore"> 
															    <td>
															        <a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="passParam('<s:property value="#data.activeno" />','<s:property value="#data.nlevel" />','<s:property value="#data.itemid" />');this.className='bt2';openWindow('open_exchange_confirm');">�һ�</a>
															    </td>
															</s:if>
															<s:else> 
															    <td>
															        <a class="bt2" href="javascript:void(0)" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';" disabled>�һ�</a>
															    </td>
															</s:else>
														</tr>
												</s:iterator>		
											</table>										
										</div>							
									</div>
									<div class="box70W fr">
										<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
										<div class="boxPage" style="width:75px; height:350px; ">
											<div class="blank10px"></div>
											<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
										</div>
										<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
									</div>
									<div class="clear"></div>
								</div>
								<div class="btm"></div>
							</div>
							<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
					        <!--����������-->
				
				
		            
							
		                
				            <div class="popup_confirm" id="open_exchange_confirm">
				                  <div class="bg"></div>
				                  <div class="tips_title">��ʾ��</div>
				                  <div class="tips_body">
				                     <p>�𾴵Ŀͻ�����ȷ���һ�������</p>
				                     <div class="blank10"></div>
				                     <p class="mt30">ȷ�϶һ�����"ȷ��"�ύ��</p>
				                  </div>
				                  <div class="btn_box tc mt20">
				                     <span class=" mr10 inline_block ">
				                        <a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openScore();">ȷ��</a>
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
