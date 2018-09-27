<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
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
					
					document.getElementById("curMenuId").value = menuid;
				
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }activitiesRec/queryPresentsList.action";
					document.dutyInfoForm.submit();
				}
			}
						
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//�ύ���
					
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }activitiesRec/selectType.action";
					document.dutyInfoForm.submit();
				}
			}
			
			//�����쳣
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("errormessage").value = errorMsg;

					//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/goCardError.action";
					document.dutyInfoForm.submit();
				}			
			}	
			
			
		</script>
	</head>

	<body scroll="no" onload="">
		<form name="dutyInfoForm" method="post" target="_self">
		
		<!-- Ӫ���Ƽ���ʶ -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- �������� -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- ������� -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- ������� -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
		
		<!-- ����� -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- ���α��� -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- ��Ʒ���봮 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- �ֻ�imeiid�� -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- ��Ʒ�ܼ� -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- ��Ʒ���� -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		<!-- ������ -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
      					<h2>��������̣�</h2>
      					<div class="blank10"></div>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="����Э��" href="#" class="on">2. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
      					<a title="Ͷ��ֽ��" href="#">4. Ͷ��ֽ��</a>
          				<a title="���" href="#" >5. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    						<p class=" tit_info1">
    							�𾴵Ŀͻ���
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;���ã���л����Ϊ�����й��ƶ�ͨ�ż��ź������޹�˾�Ŀͻ����������ҵ��ǰ���������Ķ��������
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;���ն�ҵ������ϵͳ������Ȩ������Ȩ���Լ����������ҵ��ľ�ӪȨ���й��ƶ�ͨ�ż��ź������޹�˾���У���������ȫͬ�����з�������ſ���ͨ�����ն˰������ҵ��
									�ƶ��绰����ͷ�������������Ҫ�ĸ������ϣ�������Ϊ����ҵ���ƾ֤����ʹ�ñ��ն�ƾ������������һ��ҵ�񣬾���Ϊ���������԰������������˸����������ע���������ı��ܡ�
									�������ṩ׼ȷ�����ϣ��ҹ�˾���������ṩ�����Ͻ��к˶ԣ����в�����ϵͳ����������ҵ��
									Ŀǰ���ն���������������������õ���Ŀ������ҵ��������ɻ��ѣ���ӡ�嵥������Ʊ��ҵ��
									��������ҵ�������Ҫ��ѯ���������������ѯ�ն˵������Ŀ���߲���10086��ѯ��
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;�������ȫ�������ϵ���������밴[ͬ��]��������ҵ���������ͬ����������밴[�˳�]����ϵͳ���Զ����������档
    						</p>
    						<div class=" tr"> 
    							<a title="ͬ��" class=" btagree" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">ͬ��</a> 
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
