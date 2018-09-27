<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCardEquip = termInfo.getTermspecial().substring(4, 5);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script language="javascript">
			//��ֹ�ظ��ύ
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
					return;
				}
				//ȷ��
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
			}
			
			function goback(menuid)
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
					
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"<s:property value='servnumber' />�˳���ֵ���ѹ���");
					
					document.getElementById("curMenuId").value = menuid;
				
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/cardCharge.action";
					document.dutyInfoForm.submit();
				}
			}
						
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//�ύ���
					
					// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					openRecWaitLoading_NX("recWaitLoading");
					// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"ȷ��ʹ�����������н���");
					
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/toReadCard.action";
					document.dutyInfoForm.submit();
				}
			}
			
			//�����쳣
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					openRecWaitLoading_NX("recWaitLoading");
					// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
					
					writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
							"���������ѳ����쳣��" + errorMsg);
					
					document.getElementById("errormessage").value = errorMsg;

					//�쳣������ֻҪ�������쳣��Ҫ��¼��־  
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/goCardError.action";
					document.dutyInfoForm.submit();
				}			
			}	
			
			function doLoad()
			{
				var serverNumber = "<s:property value='servnumber' />";
				if (serverNumber == null || serverNumber == "")
	            {            
	            	setException("�Բ����û���Ϣ��ȡʧ�ܣ��뷵�����²�����");
	          		return;
	            }
				
				<%
	            if (!"1".equals(isCardEquip))
	            {
	            %>
		            setException("�Բ��𣬸��ն˻��ݲ�֧����������ֵ����ѡ��������ʽ��");
		            return;
	            <%
	            }
	            %>
			}	
		</script>
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCARD %>">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />">
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>	
			<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
			<input type="hidden" id="errormessage" name="errormessage" value=''>
			
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
    					<a href="javascript:void(0)">3. ѡ����</a> 
    					<a href="javascript:void(0)" class="on">4. ��������</a>
    					<a href="javascript:void(0)">5. ����������</a> 
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)">7. �˶���Ϣ</a>
    					<a href="javascript:void(0)">8. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    					<!--modify begin l00190940 2011/10/25 R003C11L10n01 ȥ��10086����-->
    						<p class=" tit_info1">
    							�𾴵Ŀͻ���<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ã���л����Ϊ�����й��ƶ�ͨ�ż����������޹�˾�Ŀͻ����������ҵ��ǰ���������Ķ��������<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ն�ҵ������ϵͳ������Ȩ������Ȩ���Լ�����������ҵ��ľ�ӪȨ���й��ƶ�ͨ�ż����������޹�˾���У���������ȫͬ�����з�������ſ���ͨ�����ն˰�������ҵ��
			      				�ƶ��绰����ͷ�������������Ҫ�ĸ������ϣ�������Ϊ����ҵ���ƾ֤����ʹ�ñ��ն�ƾ�������������һ��ҵ�񣬾���Ϊ���������԰��������������˸����������ע���������ı��ܡ�
			      				�������ṩ׼ȷ�����ϣ��ҹ�˾���������ṩ�����Ͻ��к˶ԣ����в�����ϵͳ����������ҵ��
			      				Ŀǰ���ն���������������������õ���Ŀ������ҵ����������ɻ��ѣ���ӡ�嵥������Ʊ��ҵ��<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;�������ȫ�������ϵ���������밴[ͬ��]��������ҵ���������ͬ����������밴[��ҳ]��[�˳�]����ϵͳ���Զ����������档
    						</p>
    						<div class=" tr" style="position: absolute; top: 410px; left: 500px;"> 
    							<a class=" tongyi" href="javascript:void(0);" onMouseDown="this.className='tongyi_on'" onMouseUp="this.className='tongyi';" onclick="doSub();return false;"></a> 
    						</div>
    					<!--modify end l00190940 2011/10/25 R003C11L10n01 ȥ��10086����-->
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>