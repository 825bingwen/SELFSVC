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

	//�̿���� 0Ϊ���̿���1Ϊ�̿�
	String needCaptureCard = (String) PublicCache.getInstance().getCachedData("isCaptureBankCard");
	
	String subsName = (String) request.getAttribute("subsName");
	if (subsName == null)
	{
		subsName = "";
	}
	
	// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	
	// add begin jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ�������
	// �վݴ�ӡ����
	String receiptPrintSwitch = CommonUtil.getParamValue(Constants.SH_PRINTRECEIPT_SWITCH);
    // add end jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ�������
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_nx.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		var payType = "<s:property value='payType' />";
		var needReturnCard = "<s:property value='needReturnCard' />";
		
		var limitTime = 30;//ȡ��ʱ��30��
	
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
			
			// modify begin jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ�������
			// �վݴ�ӡ���ؿ���
		    <%
            if ("1".equals(receiptPrintSwitch))
            {                       
            %>
                // ��ӡ�վ�(2)
	            if (key == 50)
	            {
	                printReceipt_nx();
	            }
	            // �ɷ����(3\P)
	            if (key == 51 || key == 80 )
	            {
	                goHomePage();
	            }
            <%
            }
            else
            {
            %>
				//��ӡСƱ(2)
				if (key == 50)
				{
					printReceipt();
				}
				//��ӡ��Ʊ(3\P)
				if (key == 51 || key == 80 )
				{
					<%
					if ("1".equals(canPrintInvoice))
					{						
					%>
						printInvoice();
					<%
					}
					%>
				}
				//�ɷ����(4)
				if (key == 52)
				{
					goHomePage();
				} 
		    <%
            }
            %>   
            // modify end jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ�������
            
			// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />�˳���ֵ��ӡ����");
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}		
			
		// ��ӡ��Ʊ
		function printInvoice()
		{
			writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
					"<s:property value='servnumber' />ѡ���ӡ��Ʊ");
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.forms[0].invoiceType.value = "1";
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
				document.forms[0].submit();	
			}						
		}
			
			// ��ȡ������ȡ��״̬
			function takeOutBankCardStatus() 
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"����û��Ƿ���ȡ��");
								
				limitTime = limitTime - 1;
				
				if (limitTime <= 0)
				{
					//��ʱ�������ʱ����ͬʱ�̿�
					clearInterval(waitingToken);
					
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"�û��ڹ涨ʱ����δȡ�����̿�");					
					
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
						
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"�û���ȡ��");									
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
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />���ѳɹ�");
				
				if (payType == "<%=Constants.PAY_BYCARD %>" && needReturnCard == "1")
				{
					// ��ʼ��
					var ret = InitReadUserCard();

					// �˿�
					ret = TakeOutUserCard();
					
					//�¿��ɹ�������֧���̿���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
					if (ret == "0")
					{
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"�¿��ɹ�");
						
						if ("1" == "<%=needCaptureCard %>")
						{
							startClock();
						}
					}
					else if (ret != "0")
					{
						//�¿��쳣
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"�¿��쳣");
					}
				}
					
			}
			
			// ��ӡСƱ
			var printReceiptFlag = 0;
			function printReceipt()
			{
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", "<s:property value='servnumber' />ѡ���ӡСƱ");
				
				if (printReceiptFlag == 0)
				{
					printReceiptFlag = 1;
					
					// ��ť���£��ò�����״̬
					document.getElementById('dayinButton').className = 'hover';
					document.getElementById('dayinButton').onmousedown = "";
					document.getElementById('dayinButton').onmouseup = "";
					document.getElementById('dayinButton').onclick = "";
					
					var pServNumber = "<s:property value='servnumber' />";
					var pOperID = "<%=termInfo.getOperid() %>";  //��ӡ�ص�
					var pDealNum = "<s:property value='dealNum' />";     //������ˮ��
					var pDealTime = "<s:property value='dealTime' />";   //����ʱ��
					var tMoney = "<s:property value='tMoney' />Ԫ";     //���׽��
					var pSubsName = "<%=subsName %>";
					var pLatestBalance = "<s:property value='balance' />Ԫ";
					
					//��ӡƾ֤
					if ("<%=canPrintReceipt %>" == "1")
					{	
						writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
								"�Զ���ӡƾ��");
						
						doPrintPayProof_NX(pServNumber, pOperID, pDealNum, pDealTime, tMoney, pSubsName, pLatestBalance);
					
						<%
						if ("1".equals(canPrintInvoice))
						{
						%>
							document.getElementById("resultMsg").innerHTML = "СƱ��ӡ�ɹ�����ȡ�ߡ������Ҫ����ѡ���ӡ��Ʊ��";
						<%
						}
						else
						{
						%>
							document.getElementById("resultMsg").innerHTML = "СƱ��ӡ�ɹ�����ȡ�ߡ�";
						<%
						}
						%>
					}
				}
			}
			
			// ��ӡ�վ�
			function printReceipt_nx()
            {
                writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.RECEIPT_PRINT) %>", "<s:property value='servnumber' />ѡ���ӡ�վ�");
                
                if (printReceiptFlag == 0)
                {
                    printReceiptFlag = 1;
                    
                    // ��ť���£��ò�����״̬
                    document.getElementById('dayinButton').className = 'hover';
                    document.getElementById('dayinButton').onmousedown = "";
                    document.getElementById('dayinButton').onmouseup = "";
                    document.getElementById('dayinButton').onclick = "";
                    
                    var pDealTime = "<s:property value='chnDealTime' />";  // ����ʱ��
                    var pName = "<%=subsName %>"; // �ͻ�����
                    var pServNumber = "<s:property value='servnumber' />";
                    var tMoney = "<s:property value='tMoney' />Ԫ"; // ���׽��
                    var upperTMoney = "<s:property value="upperTMoney"/>"; // ��д���
                    var pDealNum = "<s:property value='dealNum' />"; // ������ˮ��
                    var pOperID = "<%=termInfo.getOperid() %>";  // ��Ʊ��
                    var pOrgName = "<%=termInfo.getOrgname() %>"; // Ӫҵ��
                    
                    //��ӡƾ֤
                    if ("<%=canPrintReceipt %>" == "1")
                    {   
                        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.RECEIPT_PRINT) %>", 
                                "��ӡ�վ�");
                        
                        printReceipt_new(pDealTime, pName, pServNumber, tMoney, upperTMoney, pDealNum, pOperID, pOrgName);
                    
                        document.getElementById("resultMsg").innerHTML = "�վݴ�ӡ�ɹ�����ȡ�ߡ�";
                    }
                }
            }
            
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'>
			<input type="hidden" id="invoiceXML" name="invoiceXML" value='<%=invoiceData%>'>
			<input type="hidden" id="invoiceType" name="invoiceType" value="">
			
			<input type="hidden" id="dealNum" name="dealNum" value='<s:property value="dealNum" />'>
			<input type="hidden" id="dealTime" name="dealTime" value='<s:property value="dealTime" />'>
			<input type="hidden" id="balance" name="balance" value='<s:property value="balance" />'>
			<input type="hidden" id="subsName" name="subsName" value="<%=subsName %>">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
      					<div class="blank10"></div>
      					<%
						if ((String)request.getAttribute("payType") != Constants.PAY_BYCARD)
						{						
						%>						
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ��֧����ʽ</a> 
      					<a href="#">3. Ͷ��ֽ��</a>
          				<a href="#" class="on">4. ���</a>
          				<%
          				}
          				else
          				{
          				%>
          				<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
    					<a href="javascript:void(0)">3. ѡ����</a> 
    					<a href="javascript:void(0)">4. ��������</a>
    					<a href="javascript:void(0)">5. ����������</a> 
    					<a href="javascript:void(0)">6. �˶���Ϣ</a>
    					<a href="javascript:void(0)" class="on">7. ���</a>
          				<%
          				}
          				%>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="recharge_result tc">
       							<%
								if (!Constants.PAY_BYCARD.equals((String)request.getAttribute("payType")) )
								{
								%>
									<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ���</p>
									
									<!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->
                                    <%
                                    if ("1".equals(receiptPrintSwitch))
                                    {
                                    %>
                                       <p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡ�վݣ�������á�</p>                                    <%
                                    }
                                    else
                                    {
                                    %>
                                        <p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡСƱ��Ʊ��������á�</p>
                                    <%
                                    }
                                    %>
                                    <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->
								<%
								}
								else
								{
								%>
									<p class="title yellow pt30">���ĳ�ֵ�����ѳɹ�����ȡ��������������</p>
									<!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->
									<%
                                    if ("1".equals(receiptPrintSwitch))
                                    {
                                    %>
                                       <p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡ�վݣ�������á�</p>                                    <%
                                    }
                                    else
                                    {
                                    %>
                                        <p id='resultMsg' class="desc pt20">�����Ҫ����ѡ���ӡСƱ��Ʊ��������á�</p>
                                    <%
	                                }
	                                %>
	                                <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->
								<%
								}
								%>
       							
          						<!--modify begin cKF76106 2012/09/25 OR_NX_201209_258-->
          						<div class="btn_box3_echo clearfix">
          						    <!--  modify begin jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->
          							<%
	       							if ("1".equals(receiptPrintSwitch))
	       							{
          							%>
          							   <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ���� (�밴1��)</a>
          							   <a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt_nx();return false;" style="margin-left:20px; ">��ӡ�վ� (�밴2��)</a>
          							   <a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;">�ɷ���� (�밴3��)</a>
          							<%
                                    }
                                    else
                                    {
                                    %>
	          							<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ���� (�밴1��)</a>
	          							<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="printReceipt();return false;" style="margin-left:20px; ">��ӡСƱ (�밴2��)</a>
										<%
										if ("1".equals(canPrintInvoice))
										{						
										%>
										<a href="javascript:void(0);" onclick="printInvoice();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''" style="margin-top:20px; ">��ӡ��Ʊ (�밴3��)</a>
										<br>
										<%
										}
										%>
										<a href="javascript:void(0);" id="dayinButton" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="goHomePage();return false;" style="_margin-top:0px; margin-left:20px; ">�ɷ���� (�밴4��)</a>
									<%
	                                }
	                                %>  
	                                <!--  modify end jWX216858 2014-8-25 OR_NX_201407_1188 ���ڱ�������ն˾�ʽͨ�û���Ʊģ������� -->      							
          						</div>
          						<!--modify end cKF76106 2012/09/25 OR_NX_201209_258-->
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>	
			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-06.wav" id="player06" align="center" autostart=true border="0" style="height:0px;width:0px;">	
		</form>
	</body>
	<script type="text/javascript">
	//������ֵ�ɷ�
	function continueCharge()
	{	
		if (submitFlag == 0)
		{
			submitFlag = 1;
										
			// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			if (document.getElementById("backWaitingFlag").value == "1")
			{
				openRecWaitLoading_NX("recWaitLoading");
			}
			// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			
			document.payMoneyForm.target = "_self";
			document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
			document.payMoneyForm.submit();
		}		
	}
	
	// �رն�����,�˿�
	doFinish();
	</script>
</html>
