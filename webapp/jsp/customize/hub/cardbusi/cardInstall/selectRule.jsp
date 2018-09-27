<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content=""/>
		<meta http-equiv="description" content=""/>
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
			var submitFlag = 0;
			
			//82��220 ����		
			document.onkeydown = pwdKeyboardDown;
			
			//�������̰����¼�
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
			
			//���������ͷ��¼�
			function pwdKeyboardUp(e) 
			{
				var key = GetKeyCode(e);
				
				//����
				if (key == 82 || key == 220) 
				{
					window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
					return;		
				}
				
				if(key == 49)
				{
					doSub(4);
				}
				else if(key == 50)
				{
					doSub(2);
				}	
				else if(key == 51)
				{
					doSub(3);
				}
			}
			
			// �ύ
			function doSub(selTelRule) 
			{			
				//��ֹ�ظ�����
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					document.actform.selTelRule.value = selTelRule;
			  		document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cardInstall/inputTelnumByRule.action";
					document.actform.submit();
				}
			}
			
			// ����
			function goback(curmenu) 
			{
				//��ֹ�ظ�����
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = curmenu;
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }login/backForward.action";
					document.actform.submit();
				}
			}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actform" method="post">
			<%-- ���֤��Ϣ --%>
			<%-- ���� --%>
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<%-- �Ա� --%>
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<%-- ���֤���� --%>
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<%-- ֤����ַ --%>
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			<%-- ��ʼʱ�� --%>
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
			<%-- ����ʱ�� --%>
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
			<%-- ����Ϣ --%>
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
			<%-- ��Ƭ��Ϣ --%>
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
			
			<%-- �ײ���Ϣ --%>
			<%-- ģ��ID --%>
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
			<%-- ģ������ --%>
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
			<%-- ��ƷID --%>
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
			<%-- ��Ʒ���� --%>
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
			<%-- Ʒ�� --%>
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
			<%-- �ײ��·� --%>
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
			<%-- Ԥ����� --%>
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
			
			<%-- ѡ����Ϣ --%>
			<%-- ���� --%>
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<%-- ��֯����ID --%>
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<%-- �������� --%>
			<input type="hidden" id="regionName" name="regionName" value="<s:property value='regionName'/>" />
			<%-- ѡ�Ź���  2����ǰ׺��ѯ 3������׺��ѯ 4��ȡ�������--%>
			<input type="hidden" id="selTelRule" name="selTelRule" value=""/>
			
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
	   					<a href="javascript:void(0)" class="on">4. ����ѡ��</a>
	   					<a href="javascript:void(0)">5. ����ȷ��</a>
	   					<a href="javascript:void(0)">6. �����ɷ�</a>
	   					<a href="javascript:void(0)">7. ȡ����ӡСƱ</a>
					</div>
				</div>
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40">
						<p class=" tit_info"><span class="bg"></span>���У�<s:property value='regionName'/></p>
						<div class="blank10"></div>
						<div class="line"></div>
						<div class="blank10"></div>
						<p class="tit_arrow fs22"><span class="bg"></span>��ѡ������ѯ��ʽ��</p>
						<div class="blank20"></div>
	       				<div class="blank5"></div>
						
						<ul class="money_list_clearfix">
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(4); return false;">
									���(��1��)
								</a>
							</li>
							<li class="pt10"></li>	
							<li class="tel_selectrule tc">
								<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
									ǰ��λ(��2��)
								</a>
							</li>
							<li class="pt10"></li>							
							<li class="tel_selectrule tc">
								<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(3); return false;">
									����λ(��3��)
								</a>
							</li>
						</ul>
					</div>	
				</div>	
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
