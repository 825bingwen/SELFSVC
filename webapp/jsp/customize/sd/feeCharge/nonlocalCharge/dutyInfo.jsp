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
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>		
	</head>

	<body scroll="no" onload="doLoad();">
		<form name="dutyInfoForm" method="post" target="_self">
			<!-- �ֻ����� -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- �ֻ����� -->
            <s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
            
            <!-- ʡ�ݱ��� -->
            <s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
            
            <!-- �ͻ����� -->
            <s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
            
            <!-- �ɷѽ�� -->
            <s:hidden id="yingjiaoFee" name="chargeLogVO.yingjiaoFee"></s:hidden>
            
            <!-- ֧����ʽ 1:��������4���ֽ� -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
            
            <!-- ������Ϣ -->
            <s:hidden id="errormessage" name="errormessage"></s:hidden>
            
            <%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
            
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<%@ include file="/jsp/customize/sd/feeCharge/common/chargeHeader.jsp"%>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    						<p class=" tit_info1">
    							�𾴵Ŀͻ���<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ã���л����Ϊ�����й��ƶ�ͨ�ż���ɽ�����޹�˾�Ŀͻ����������ҵ��ǰ���������Ķ��������<br/>
			      				&nbsp;&nbsp;&nbsp;&nbsp;���ն�ҵ������ϵͳ������Ȩ������Ȩ���Լ����������ҵ��ľ�ӪȨ���й��ƶ�ͨ�ż���ɽ�����޹�˾���У���������ȫͬ�����з�������ſ���ͨ�����ն˰������ҵ��
			      				�ƶ��绰����ͷ�������������Ҫ�ĸ������ϣ�������Ϊ����ҵ���ƾ֤����ʹ�ñ��ն�ƾ������������һ��ҵ�񣬾���Ϊ���������԰������������˸����������ע���������ı��ܡ�
			      				�������ṩ׼ȷ�����ϣ��ҹ�˾���������ṩ�����Ͻ��к˶ԣ����в�����ϵͳ����������ҵ��
			      				Ŀǰ���ն���������������������õ���Ŀ������ҵ��������ɻ��ѣ���ӡ�嵥������Ʊ��ҵ��<br/>
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
	</body>
</html>
<script language="javascript">
document.getElementById("highLight4").className = "on";

//��ֹ�ظ��ύ
var submitFlag = 0;

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

// ������һҳ
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
    
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/cardCharge.action";
        document.dutyInfoForm.submit();
    }
}
      
// �ύ      
function doSub() 
{
    if (submitFlag == 0) 
    {
        submitFlag = 1; //�ύ���
        
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/toReadCard.action";
        document.dutyInfoForm.target = "_self";
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
        document.dutyInfoForm.action = "${sessionScope.basePath }nonlocalChargeSD/chargeError.action";
        document.dutyInfoForm.submit();
    }           
}   

function doLoad()
{
    var serverNumber = '<s:property value="servNumber" />';
    if (serverNumber == null || serverNumber == "")
    {         
        setException("�Բ����û���Ϣ��ȡʧ�ܣ��뷵�����²�����");
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
