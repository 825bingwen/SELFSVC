<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
            
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    						<p class=" tit_info1">
    							�𾴵Ŀͻ���<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ã���л����Ϊ�����й��ƶ�ͨ�ż���ɽ�����޹�˾�Ŀͻ����������ҵ��ǰ���������Ķ��������<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ն�ҵ������ϵͳ������Ȩ������Ȩ���Լ�����������ҵ��ľ�ӪȨ���й��ƶ�ͨ�ż���ɽ�����޹�˾���У���������ȫͬ�����з�������ſ���ͨ�����ն˰�������ҵ��
			      				�ƶ��绰����ͷ�������������Ҫ�ĸ������ϣ�������Ϊ����ҵ���ƾ֤����ʹ�ñ��ն�ƾ�������������һ��ҵ�񣬾���Ϊ���������԰��������������˸����������ע���������ı��ܡ�
			      				�������ṩ׼ȷ�����ϣ��ҹ�˾���������ṩ�����Ͻ��к˶ԣ����в�����ϵͳ����������ҵ��
			      				Ŀǰ���ն���������������������õ���Ŀ������ҵ����������ɻ��ѣ���ӡ�嵥������Ʊ��ҵ��<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;�������ȫ�������ϵ���������밴[ͬ��]��������ҵ���������ͬ����������밴[��ҳ]��[�˳�]����ϵͳ���Զ����������档
    						</p>
    						<div class=" tr"> 
    							<a class=" btagree" style="margin-top:370px;_margin-top:0px;" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">ͬ��</a> 
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
		<script type="text/javascript">
			document.onkeyup = pwdKeyboardUp;
	
			function pwdKeyboardUp(e) 
			{
			    var key = GetKeyCode(e);
			    
			    //ȷ��
			    if (key == 13 || key == 89 || key == 221) 
			    {
			        doSub();
			        return;
			    }
			    //����
			    else if (key == 82 || key == 220) 
			    {
			        goback("<s:property value='curMenuId' />");
			        return;
			    }       
			}
		</script>
	</body>
</html>