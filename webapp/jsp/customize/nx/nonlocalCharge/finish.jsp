<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<% 
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);

	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String termSpecial = termInfo.getTermspecial();
	
	String canPrintReceipt = termSpecial.substring(0, 1);
	String canPrintInvoice = termSpecial.substring(1, 2);
	           
	String invoiceData = (String) request.getAttribute("invoiceXml");
	if (invoiceData == null)
	{
		invoiceData = "";
	}

	// �̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
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
		<script type="text/javascript" src="${sessionScope.basePath}js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
      					<div class="blank10"></div>
      					<s:if test="chargeLogVO.payType == 4">
	      					<a href="#">1. �����ֻ�����</a> 
	      					<a href="#">2. ѡ��֧����ʽ</a> 
	      					<a href="#">3. Ͷ��ֽ��</a>
	          				<a href="#" class="on">4. ���</a>
          				</s:if>
          				<s:else>
          					<a href="javascript:void(0)">1. �����ֻ�����</a> 
							<a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
	    					<a href="javascript:void(0)">3. ѡ����</a> 
	    					<a href="javascript:void(0)">4. ��������</a>
	    					<a href="javascript:void(0)">5. ����������</a> 
	    					<a href="javascript:void(0)">6. ��������</a>
	    					<a href="javascript:void(0)">7. �˶���Ϣ</a>
	    					<a href="javascript:void(0)" class="on">8. ���</a>
          				</s:else>
  					</div>
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='chargeLogVO.servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="recharge_result tc">
									<s:if test="chargeLogVO.payType == 4">
										<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
                                    </s:if>
                                    <s:else>
										<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ�����ȡ��������������</p>
	                                </s:else>
	                                <p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡСƱ��������á�</p>
       							
          						<div class="btn_box3_echo clearfix">
          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;" style="margin-left:20px; ">�������� (�밴1��)</a>
          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">��ӡСƱ (�밴2��)</a>
									<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="_margin-top:0px; margin-left:20px; ">������� (�밴3��)</a>
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			<%@ include file="/backinc.jsp"%>	
		</form>
		<script type="text/javascript">
		// �رն�����,�˿�
		doFinish();
		
		var submitFlag = 0;
			
		var payType = "<s:property value='chargeLogVO.payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//ȡ��ʱ��30��
	
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����/�˳�(R)
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}	
			//������ֵ����(1)
			if (key == 49)
			{
				continueCharge();
			}
			// �ɷ����(3)
			if (key == 51)
			{
				goHomePage();
			}
			
			// ��ӡСƱ(2)
			if (key == 50)
			{
				printReceipt();
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
		
		// ��ȡ������ȡ��״̬
		function takeOutBankCardStatus() 
		{
			writeDetailLog("<%=chargeLogDetail %>", "����û��Ƿ���ȡ��");
							
			limitTime = limitTime - 1;
			
			if (limitTime <= 0)
			{
				//��ʱ�������ʱ����ͬʱ�̿�
				clearInterval(waitingToken);
				
				writeDetailLog("<%=chargeLogDetail %>", "�û��ڹ涨ʱ����δȡ�����̿�");					
				
				captureUserCard();
				
				return;
			}
				
			try 
			{
				//������
				var ret = TakeOutUserCardStatus();//��ȡ������ȡ��״̬
				
				if (ret == "0" || ret == 0) 
				{	
					//�Ѿ�ȡ�߿��������ʱ����
					clearInterval(waitingToken);
					
					writeDetailLog("<%=chargeLogDetail %>", "�û���ȡ��");									
				}			
			}
			catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
			
		function startClock()
		{
			try 
			{
				waitingToken = setInterval("takeOutBankCardStatus()", 1000);
			}
			catch (e) {}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
		}
				
		function doFinish()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />���ѳɹ�");
			
			if (payType == "1" && needReturnCard == "1")
			{
				// ��ʼ��
				var ret = InitReadUserCard();
	
				// �˿�
				ret = TakeOutUserCard();
				
				// �¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
				if (ret == "0")
				{
					writeDetailLog("<%=chargeLogDetail %>", "�¿��ɹ�");
					
					if ("1" == "<%=needCaptureCard %>")
					{
						startClock();
					}
				}
				else if (ret != "0")
				{
					//�¿��쳣
					writeDetailLog("<%=chargeLogDetail %>", "�¿��쳣");
				}
			}
				
		}
		
		//������ֵ�ɷ�
		function continueCharge()
		{	
			if (submitFlag == 0)
			{
				submitFlag = 1;
											
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				document.payMoneyForm.target = "_self";
				document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
				document.payMoneyForm.submit();
			}		
		}
		
		//  ��ӡСƱ
		var printReceiptFlag = 0;
		function printReceipt()
		{
			writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />ѡ���ӡСƱ");
			
			if (printReceiptFlag == 0)
			{
				printReceiptFlag = 1;
				
				// ��ť���£��ò�����״̬
				document.getElementById('dayinButton').className = 'hover';
				document.getElementById('dayinButton').onmousedown = "";
				document.getElementById('dayinButton').onmouseup = "";
				document.getElementById('dayinButton').onclick = "";
				
				// �ֻ�����
				var pServNumber = "<s:property value='chargeLogVO.servnumber' />";
				
				// ����Ա
				var pOperID = "<%=termInfo.getOperid() %>";  
				
				// ������ˮ��
				var pDealNum = "<s:property value='dealNum' />"; 
				
				// ����ʱ��
				var pDealTime = "<s:property value='dealTime' />";
				
				// ���ѽ��
				var tMoney = "<s:property value='tMoney' />Ԫ";
				
				// �ͻ�����
				var pSubsName = "<s:property value='custName' />";
				
				// �������
				var pLatestBalance = "<s:property value='balance' />Ԫ";
				
				//��ӡСƱ
				if ("<%=canPrintReceipt %>" == "1")
				{	
					writeDetailLog("<%=chargeLogDetail %>", "�Զ���ӡ��ʡ����СƱ");
					
					// ��ӡСƱ
					doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					document.getElementById("resultMsg").innerHTML = "СƱ��ӡ�ɹ�����ȡ�ߡ�";
				}
			}
		}
		
		// ����
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />�˳���ʡ��س�ֵ��ӡ����");
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/inputNumberPage.action";
				document.forms[0].submit();
			}
		}
	</script>
	</body>
</html>
