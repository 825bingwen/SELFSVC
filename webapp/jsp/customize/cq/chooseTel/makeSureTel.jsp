<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%
String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

TerminalInfoPO terminalInfoPO = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/selfInstallPrt.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;	//�ύ��־��0��ʾδ���ύ��1��ʾ�Ѿ��ύ
		
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

		function doFinish()
		{
			var cityName = "<%=terminalInfoPO.getOrgname() %>";  //��ӡ�ص�
			var pPrintDate = getDateTime();  //��ӡ����
			var pTerminalInfo = "<%=terminalInfoPO.getTermid() %>"; //�ն���Ϣ
			var chooseTelNum = "<s:property value='telnum' />";   //ѡ������
			var orderID = "<s:property value='orderID' />";
			
			<%
			if (Constants.PROOPERORGID_NX.equalsIgnoreCase(provinceID))
			{
			%>
				doPrintTheTel_NX(chooseTelNum, orderID, pTerminalInfo, cityName, pPrintDate);	
			<%
			}
			%>			
		}

		// ����
		function goback(curmenu) 
		{
			//��ֹ�ظ�����
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actionFrom.target = "_self";
				document.actionFrom.action = "${sessionScope.basePath }login/backForward.action";
				document.actionFrom.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();doFinish();" scroll="no">
		<form name="actionFrom" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>����</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);">
							1.ѡ���ѯ��ʽ
						</a>
						<a href="javascript:void(0);">
							2.�����ѯ����
						</a>
						<a href="javascript:void(0);">
							3.ѡ�����
						</a>
						<a href="javascript:void(0);">
							4.���������Ϣ
						</a>
						<a href="javascript:void(0);" class="on">
							5.���
						</a>
					</div>
					
					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='telnum' /></span></p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result tc">
       							<p class="title yellow pt30">Ԥ���ɹ���</p>
          						<p class="desc pt20">��Я��Ԥ��ƾ֤�����֤����ʱǰ��Ӫҵ����������</p>          						
       						</div>
      					</div>
      				</div>
				</div>
			</div>			
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
