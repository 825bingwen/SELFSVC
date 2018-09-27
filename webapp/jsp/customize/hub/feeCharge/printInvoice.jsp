<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%	           
	String invoiceData = (String) request.getAttribute("invoice");
	
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

				var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?servnumber=<s:property value='servnumber' />&cycle="+feeTime;
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
					document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
					document.forms[0].submit();
				}
			}
		//-->
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="payMoneyForm" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
  				<div class="pl30">
  					<div class="b257 ">
  						<div class="bg bg257"></div>
      					<h2>��ֵ�������̣�</h2>
      					<div class="blank10"></div>
      					<a href="#">1. �����ֻ�����</a> 
      					<a href="#">2. ѡ��֧����ʽ</a> 
      					<a href="#">3. ѡ����</a> 
      					<a href="#">4. Ͷ��ֽ��</a>
          				<a href="#" class="on">5. ���</a>
  					</div>
  					
  					<div class="b712">
  						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40 pr0">
      						<p class="tit_info "><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value='servnumber' /></span></p>
      						<p class="tit_desc pl40 ">���ѽ�<span class="yellow"><s:property value='tMoney' />Ԫ</span> </p>
      						<div class="blank20"></div>
        					<div class="line w625"></div>
       						<div class="blank30"></div>
       						<div class="recharge_result print tc">
       							<p id="resultMsg1" class="title yellow pt30">��Ʊ���ڴ�ӡ</p>
          						<p id="resultMsg2" class="desc pt20">���Ժ�</p>
          						<div class="btn_box4 clearfix">
          						<s:if test="actId!=null">
          					   
          					     
          						<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="mianFeiChouJiang('<s:property value="actId"/>');return false;">��ѳ齱</a>
          						</s:if>
          							<a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">������ֵ����</a>     							
          						</div>
       						</div>
      					</div>
  					</div>
  				</div>
			</div>
			 <!--��ѳ齱-->
                <div class="openwin_tip" id="colBillQueryWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank30"></div><div class=" blank60"></div>
                   
                    <div class="blank10n"></div>
                     <p class="fs30 yellow pl20 lh2" id="colBillQuery" style="text-indent:60px;padding-right:20px;"></p>
                   
                    
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    
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
			document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
			document.payMoneyForm.submit();
		}
		//��ѳ齱
		function mianFeiChouJiang(actId){

		
		var resuInfo="��ѳ齱�����쳣��";
		url= "${sessionScope.basePath }charge/mianFeiChouJiang.action?curMenuId=<s:property value='curMenuId'/>&actId="+actId;
					var loader1 = new net.ContentLoader(url, netload = function () 
										{
											var resXml = this.req.responseXML;
											
											if (resXml)
											{
													var root = resXml.documentElement;
													
													
														
														document.getElementById("colBillQuery").innerHTML=root.text;
														wiWindow = new OpenWindow("colBillQueryWin1",708,392);
														return;
													
												    
												   				
											}
										    else
											{	
												
													document.getElementById("colBillQuery").innerHTML=resuInfo;
												    wiWindow = new OpenWindow("colBillQueryWin1",708,392);
												return;
											}								
										}, null, "POST", "", null);
	
}	
	</script>
</html>
