<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ƶ������ն�</title>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
</head>
<body scroll="no">
	<form name="actform" method="post">
	<%@ include file ="/titleinc.jsp" %>
	<div class="main" id="main">
		<%@ include file="/customer.jsp" %>
		<!--������-->
		<div class="box842W fl ml45 relative">
			<div class="bg"></div>
			<div class="top"></div>
			<div class="con relative">
				<div class="box747W fl">
					<div class="div747w444h">
						<!-- �б����� -->
						<p class="tit_info" align="left">
							<span class="bg"></span><%=menuName%></p>
						<p class="ptop180 p747w411h" id="inn">
						<font size="3" color="yellow" >1)�۳����Ѳ��������ѣ�<s:property value="curFee"/>Ԫ��������ǰ���<s:property value="leftBalance"/>Ԫ���������ö�ȣ���������������<s:property value="credit" />���ö�ȡ������ܻ�ͣ������
						<br>2)�����۳����Ѳ��������ѣ����������ϸ��</font>
						
						<table class="tb_blue" width="100%">
							<tr>
								<th width="15%">������</th>
								<th width="15%">��Ŀ���</th>
								<th width="15%">�����ѷ�Χ</th>
								<th width="20%">��Ԫ����δ�˼��������ѽ�</th>
								<th width="20%">��Ч��</th>
								<th width="15%">�����˵���ר������ķ��ã�Ԫ��</th>
							</tr>
							<s:iterator value="balanceList">
								<tr>
									<td>
										<s:property value="subjectClass"/>
									</td>
									<td>
										<s:property value="subjectType" />
									</td>
									<td>
										<s:property value="range" />
									</td>
									<td>
										<s:property value="consume" />
									</td>
									<td>
										<s:property value="expiryDate" />
									</td>
									<td>
										<s:property value="funds" />
									</td>
								</tr>
							</s:iterator>
						</table>
								
						<!-- �б����� -->
					</div>
							
				</div>
				<div class="box70W fr">
					<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
					<div class="boxPage" style="width:75px; height:350px; ">
						<div class="blank10px"></div>
						<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px" >0%</div>
					</div>
					<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
				</div>
				<div class="clear"></div>
			</div>
			<p><font size="4" color="red">&nbsp;&nbsp;&nbsp;ע������ר��������δ����������ѣ���Ӧר���򲻿��ã����������ڿ�ͷ�ġ���ǰ���С�</font></p>
			<div class="btm"></div>
		</div>
		<script type="text/javascript">
		var myScroll = new virtualScroll("inn","gunDom");
		</script>
		<!--����������-->
	</div>
	<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">

	// ��������¼�
	document.onkeydown = keyDown;
	function keyDown(e)
	{
		//8��32��66��77 ����
	    //82��220 ����
	    //13��89��221 ȷ��
	    //80 ��ӡ
	    //85 ��һҳ
	    //68 ��һҳ
	    
		//���ռ�����
		var key = GetKeyCode(e);
	     
	    //8:backspace 32:�ո� B:66 M:77
	    if (key == 8 || key == 32 || key == 66 || key == 77)
	    {
	    	return false;
	    }
	    
	    //82:R 220:����
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
	   		return ;
		}
	}
</script>
</html>