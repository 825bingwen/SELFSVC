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

//82��220 ����

document.onkeydown = pwdKeyboardDown;

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

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//����
	if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
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
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">			
		<%@ include file="/titleinc.jsp"%>
  		<div class="main" id="main">
  			<%@ include file="/customer.jsp"%>
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����ʵ��굥��ѯ</a>
	      	<!--������-->
			<div class="box842W fl ml45 relative">
				<div class="bg"></div>
				<div class="top"></div>
				<div class="con relative" >
					<div class="box747W fl">					
						<div class="div747w444h">
							<!-- �б����� -->
	                      	<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
							<p class="ptop180 tc p747w411h" id="inn" >
							<table width="80%" class="tb_blue" align="center">
								<tr class="tr_color">
									<th width="80%" colspan="2" class='list_title'>
										�������
									</th>
									<th width="20%" class='list_title'>
										�� �Ԫ��
									</th>
								</tr>
								<tr class="tr_color">
									<td colspan="2"  class="tc"><s:property value="serviceTitle[0]"/>��</td>
									<td class="tc"><span class="yellow"><s:property value="balanceStr[0]"/></span></td>  	
								</tr>
								<tr class="tr_color" >
									<td width="20%" rowspan="6" class="tc">
										����
									</td>
								</tr>
								<s:iterator value="serviceTitle" status="title" >
									<s:if test="#title.getIndex() > 0 && #title.getIndex() < 6">
										<tr class="tr_color">
											<td class="tc" >
											<s:property />��
											</td>
											<td class="tc" >
												<span class="yellow">
													<s:property value="balanceStr[#title.getIndex()]" />
												</span>
											</td>
											</tr>
									</s:if>
									<s:if test="#title.getIndex() > 5">
										<tr class="tr_color">
											<td colspan="2" class="tc" >
											<s:property />��
											</td>
											<td class="tc" >
												<span class="yellow">
													<s:property value="balanceStr[#title.getIndex()]" />
												</span>
											</td>
											</tr>
									</s:if>
								</s:iterator>		
							</table>										
						</div>							
					</div>
					<div class="box70W fr">
						<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
						<div class="div75w350h">
							<div class="blank10px"></div>
							<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
						</div>
						<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="btm"></div>
			</div>
			<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
	        <!--����������-->
   		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
<script type="text/javascript">
    openSurveyDialog();
</script>
<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
</html>
