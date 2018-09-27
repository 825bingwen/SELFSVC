<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	String pTerminalInfo = termInfo.getTermname();
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	
	// ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	
	// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
	String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<style>
		.repeatcasherror
		{ 
			width:598px; 
			height:150px; 
			color:red; 
			font-size:20px; 
			padding-top: 30px; 
			text-align:left; 
            _background:none;
        }
		.mr201{ margin-right:201px;}
		</style>
	</head>
	<body scroll="no" onload="doFinish();">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��ֵ��������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
    					<p class="recharge_tips">�鿴���Ļ�����Ϣ�����ύ��ֵ���ѽ�����֧����</p>
    					<a href="javascript:void(0)">3. ֧��</a> 
    					<a href="javascript:void(0)" class="on">5. ���</a>
					</div>
					
					 <div class="b712">
					 	<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<div class="blank10"></div>     
        					<div class="blank20"></div>
        					<div class="blank40"></div>
      						<div class="recharge_result tc">
       							<div class="repeatcasherror">
      							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ǳ���Ǹ�������νɷѿ�������
      							�뵽�����ƶ���վ���СƱƾ֤��Ӫҵǰ̨��ѯ��ʵ��
      							�ɴ˸��������Ĳ��㣬�����½⣡
      						    </div>      						    
      						</div>
      					</div>
					 </div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		//82��220 ����
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}
			//�˳���82
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
		}
		
		function doFinish()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.terminalSeq' />�����ظ�");
						
			var pAmount = "";
			var money = parseInt("<s:property value='tMoney' />");
			if (money > 0)
			{
				pAmount = "<s:property value='tMoney' />Ԫ";
			}			
			
			//��ӡ�����ظ���ƾ֤
			if ("<%=canPrintReceipt %>" == "1")
			{
				writeDetailLog("<%=chargeLogDetail %>", "��ӡ�����ظ�ƾ֤");
				
				var pServNumber = "<s:property value='' />";
				var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
				var pPrintDate = getDateTime();  //��ӡ����
				var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
				var pDealNum = "";     //������ˮ��
				var pDealTime = "";   //����ʱ��
				var pDealStatus = "���ʵ"; //����״̬
				var pTerminalSeq = "<s:property value='chargeLogVO.terminalSeq' />";
								
				doPrintPayProof(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, pAmount,
						pDealStatus, pTerminalSeq, "", "0");
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				document.getElementById("curMenuId").value = menuid;
					
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>
