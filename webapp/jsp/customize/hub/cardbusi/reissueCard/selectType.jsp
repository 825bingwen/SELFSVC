<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script type="text/javascript">
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
		
		/**
		 * ���ѷ�ʽ�ύ
		 */
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//�����ȴ���
				openRecWaitLoading();
				document.actform.action = "${sessionScope.basePath}" + url;
				document.actform.submit();
			}			
		}
		
		/**
		 * ����
		 */
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				//�����ȴ���
				openRecWaitLoading();
				
				submitFlag = 1;
				document.forms[0].action = "${sessionScope.basePath }reissueCard/inputTelnum.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" style="margin: 0 0 0 0" onload="window.focus();">
	<form name="actform" method="post">
	<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	
	<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
	<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
	
	<input type="hidden" name="errormessage" id="errormessage" />
	<%-- �հ׿�����--%>
	<input type="hidden" id="blankCard" name="cardBusiLogPO.blankCard" value="<s:property value = 'cardBusiLogPO.blankCard' />" />
	<!-- ���úϼ� -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
    
    <%--������־id�����ڸ���������־--%>
   	<input type="hidden" id="oid" name="cardBusiLogPO.oid" value="<s:property value = 'cardBusiLogPO.oid' />" />
    
    <!--------------simInfoPO��Ϣ -------------->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value="<s:property value="simInfoPO.smsp" />" />
	<input type="hidden" id="pin1" name="simInfoPO.pin1" value="<s:property value="simInfoPO.pin1" />" />
	<input type="hidden" id="pin2" name="simInfoPO.pin2" value="<s:property value="simInfoPO.pin2" />" />
	<input type="hidden" id="puk1" name="simInfoPO.puk1" value="<s:property value="simInfoPO.puk1" />" />
	<input type="hidden" id="puk2" name="simInfoPO.puk2" value="<s:property value="simInfoPO.puk2" />" />
	<input type="hidden" id="eki" name="simInfoPO.eki" value="<s:property value="simInfoPO.eki" />" />

	<%--���֤��Ϣ�� --%>
	<!-- ���� -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- �Ա� -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- ���֤���� -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- ֤����ַ -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
		
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��������</h2>
						<div class="blank10"></div>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
						<a href="javascript:void(0)">3. ����ȷ��</a>
						<a href="javascript:void(0)" class="on">4. ѡ��֧����ʽ</a>
						<a href="javascript:void(0)">5. �����ɷ�</a>
						<a href="javascript:void(0)">6. ȡ����ӡСƱ</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
      					<div class="blank60"></div>
      					<div class="p40">
   						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow">${sessionScope.CustomerSimpInfo.servNumber}</span></p>
   						<p class="fs22 fwb pl40 lh30">�û�������<span class="yellow fs22"><s:property value="idCardPO.idCardName" /></span></p>
						<p class="fs22 fwb pl40 lh30">�������ã�<span class="yellow fs22"><s:property value="recFee" /></span> Ԫ</p>
						<div class="blank10"></div>
						<div class="line"></div>
     						<div class="blank10"></div>
     						<p class="fs22 tit_arrow"><span class="bg"></span>ѡ��֧����ʽ��</p>
     						<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
       						<s:iterator value="usableTypes" id="type" status="st">
       							<s:if test="#st.index == 0">          								
          							<li class="mr70">          									
         						</s:if>
         						<s:else>
         							<li>          									
         						</s:else>
       							<s:if test="payTypeFlag == '11'">
       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
       							</s:if>
       							<s:elseif test="payTypeFlag == '10' && #type.menuid == 'cashChargeReissueCard'">
       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
       							</s:elseif>
       							<s:elseif test="payTypeFlag == '01' && #type.menuid == 'cardChargeReissueCard'">
       								<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
       							</s:elseif> 
       							</li>
          					</s:iterator>
        					</ul>
      					</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
