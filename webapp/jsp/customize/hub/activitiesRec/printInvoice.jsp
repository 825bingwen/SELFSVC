<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	String invoiceData = (String) request.getAttribute("invoice");
	// 1:������ 4:�ֽ�
	String payType = (String)request.getAttribute("payType");
	TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript">
		<!--
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
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
		
		//ȷ��
		function doFinish() 
		{
			var invoiceXml = getDocument();
			if (invoiceXml == null)
			{
				return;
			}
			
			var allInvoices = invoiceXml.getElementsByTagName("entry");
			for (i = 0; i < allInvoices.length; i++)
			{
				printInvoice(allInvoices[i]);
			}
			
			document.getElementById("resultMsg1").innerHTML = "���ķ�Ʊ�Ѵ�ӡ�ɹ���";
			document.getElementById("resultMsg2").innerHTML = "�뱣������ķ�Ʊ��";
		}
		
			/**
			  * ȡ�û�����ط�Ʊxml���ݵ�documentElement����
			  * id xml�����ID����
			  */
			function getDocument() 
			{
   				try
   				{
	    			var xmlDoc; 
	    			if (IsIE() == 1)
	    			{
	        			xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
		    			xmlDoc.async = "false";
		    			xmlDoc.loadXML('<%=invoiceData%>');
		    			return xmlDoc;
	    			}
	    			else
	    			{
	        			var parser = new DOMParser();
	        			xmlDoc = parser.parseFromString('<%=invoiceData%>', "text/xml");
	        			return xmlDoc;
	    			}
    			} 
    			catch(e) 
    			{
        			pubErrShow("���������ʶ���޷����з�Ʊ��ӡ!");
        			
        			return null;
    			}
			}
			
			function printInvoice(invoice) 
			{
				//��ӡ������Ʊ
				printInvoiceHB(invoice,'<%=termInfo.getOperid() %>');
				var feeTime = getXmlInvoiceData(invoice, "chargeDate");
				
				var url = "${sessionScope.basePath }activitiesRec/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&recoid=<s:property value='recoid' />";
				var loader = new net.ContentLoader(url, null, null, "POST", "", null);
			}
			
			function getXmlInvoiceData(invoice, itemname) 
			{
				var items = invoice.getElementsByTagName(itemname);
				var itemdata = "";
				if (items.length > 0) {
					itemdata = items[0].childNodes[0].nodeValue;
				}
				return itemdata;
			}
			
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					document.getElementById("curMenuId").value = menuid;
					
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
					document.forms[0].submit();
				}
			}
		//-->
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />" />
						
			<!-- Ӫ���Ƽ���ʶ -->
			<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  					<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��������̣�</h2>
      					<div class="blank10"></div>
      					<%
      					if ("4".equals(payType))
      					{
      					%>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="����Э��" href="#">2. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
      					<a title="Ͷ��ֽ��" href="#">4. Ͷ��ֽ��</a>
          				<a title="���" href="#" class="on">5. ���</a>
          				<%
          				}
          				else
          				{
          				%>
						<a title="ѡ������" href="#">1. ѡ������</a>
    					<a title="����Э��" href="#">2. ����Э��</a> 
    					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a>
    					<a title="���봢�" href="#">4. ���봢�</a>
    					<a title="��������" href="#">5. ��������</a>
    					<a title="�˶���Ϣ" href="#">6. �˶���Ϣ</a>
    					<a title="���" href="#" class="on">7. ���</a>
          				<%
          				}
          				%>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value="#request.totalfee_yuan" />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">��Ʊ���ڴ�ӡ</p>
          						<p id="resultMsg2" class="desc pt20">���Ժ�</p>
          						<div class="btn_box4 clearfix">
	          						<s:if test='recommendActivityFlag == "1"'>
	          							<a href="javascript:void(0);" onclick="continueHandle();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">��������</a>
	          						</s:if>
	          						<s:else>
	          							<a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">���������</a>     							
	          						</s:else>
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>			
		</form>
	</body>
	<script type="text/javascript">
		doFinish();
		
		//������ֵ�ɷ�
		function continueCharge()
		{	
			document.payMoneyForm.target = "_self";
			document.payMoneyForm.action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
			document.payMoneyForm.submit();
		}
		
		// �ݲ���������ԭ��ҵ��
		function continueHandle()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading('recWaitLoading');
				document.payMoneyForm.target="_self";
				document.payMoneyForm.action = "${sessionScope.basePath}recommendActivity/continueHandle.action";
				document.payMoneyForm.submit();
			}
		}
	</script>
</html>
