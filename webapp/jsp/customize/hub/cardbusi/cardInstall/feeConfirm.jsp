<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// ������һҳ
function goback(curmenu) {
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading();
		}
	
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}		
}

// ��������¼�
document.onkeydown = pwdKeyboardDown;
function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//����
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
		return false;
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
	//8��32��66��77 ����
    //82��220 ����
    //13��89��221 ȷ��
    //80 ��ӡ
    //85 ��һҳ
    //68 ��һҳ
    
	//���ռ�����
	var key = GetKeyCode(e);
     
    //8:backspace 32:�ո� B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:����
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
	else if (key == 13 || key == 89 || key == 221)
	{
		doSub();
	}
	
	if (key == 48)
	{
		goback("<s:property value='curMenuId' />") ; 
	}
	// �Ϸ�
	else if (key == 85)
	{
		myScroll.moveUp(30);
		return;
	}
	// �·�
	else if (key == 68)
	{
		myScroll.moveDown(30);
		return;
	}	
}

function doSub()
{
	if (submitFlag == 0)
	{
		openRecWaitLoading();
		
		submitFlag = 1;
		
		// ��ת�ɷ�ѡ��ҳ��
		document.actform.action = "${sessionScope.basePath}cardInstall/selectPayType.action";
		document.actform.submit();
	}			
}

//�����쳣
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading();
		
		document.getElementById("errormessage").value = errorMsg;

		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
		document.actform.submit();
	}			
}	

</script>
</head>
<body scroll="no">
<form name="actform" method="post">
	<input type="hidden" name="errormessage" id="errormessage" />
	<!-- �հ׿���Ϣ -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!-- ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!-- ����Ϣ���ĺ��� -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />

	<!-- ����Ϣ�� -->
	<input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>	
	
	<!-- ���֤��Ϣ -->
	<!-- ���� -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- �Ա� -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
	<!-- ���֤���� -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- ֤����ַ -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
	<!-- ��ʼʱ�� -->
	<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
	<!-- ����ʱ�� -->
	<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
	<!-- ����Ϣ -->
	<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
	<!-- ��Ƭ��Ϣ -->
	<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
	
	<!-- �ײ���Ϣ -->
	<!-- ģ��ID -->
	<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
	<!-- ģ������ -->
	<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
	<!-- ��ƷID -->
	<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
	<!-- ��Ʒ���� -->
	<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
	<!-- Ʒ�� -->
	<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
	<!-- �ײ��·� -->
	<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
	<!-- Ԥ����� -->
	<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
	
	<!-- ѡ����Ϣ -->
	<!-- ���� -->
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
	<!-- ��֯����ID -->
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<!-- �������� -->
	<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
	<!-- ѡ�Ź��� -->
	<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
	<!-- ǰ׺ -->
	<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
	<!-- ��׺ -->
	<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
	<!--�հ׿����к� -->
	<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
	<!--�ֻ����� -->
	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />
	<!-- ��ƷID -->
	<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
	
	<!-- �ɷ���Ϣ -->
	<!-- ���úϼ� -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	
	<%-- ������־ --%>
	<%-- ��ˮ�� --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- �ɷ���ˮ�� --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- �ɷѷ�ʽ��1����������4���ֽ� --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
	<%-- ʵ�շ��� --%>
	<input type="hidden" id="toFee" name="toFee" value='<s:property value="toFee" />'/>
    <%-- Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� --%> 
    <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
    <%-- Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� --%> 
    <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
    <%-- Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ�� --%>
    <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
    
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
 				<a href="javascript:void(0)">4. ����ѡ��</a>
 				<a href="javascript:void(0)" class="on">5. ����ȷ��</a>
 				<a href="javascript:void(0)">6. �����ɷ�</a>
 				<a href="javascript:void(0)">7. ȡ����ӡСƱ</a>
			</div>

			<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank40"></div>
				<div class="p40">
					<p class=" tit_info">
						<span class="bg"></span>������ϸ�б�
						<span class="yellow"></span>
					</p>
					<div class="blank15"></div>
					<table width="100%" class="tb_blue02" align="center">
						<tr>
							<th width="25%" class="tc">����</th>
							<td width="25%" class="tc"><span class="yellow fs16"><s:property value='telnum'/></span></td>
							<th width="25%" class="tc">Ʒ��</th>
							<td width="25%" class="tc">
								<span class="yellow fs16">
									<s:if test="tpltTempletPO.brand == 'BrandMzone'">
										���еش�
									</s:if>
									<s:elseif test="tpltTempletPO.brand == 'BrandSzx'">
										������
									</s:elseif>
									<s:elseif test="tpltTempletPO.brand =='BrandGotone'">
										ȫ��ͨ
									</s:elseif>
									<s:else>
										������
									</s:else>
							 	</span>
							 </td>
						</tr>
						<tr>
							<th width="25%" class="tc">��Ʒ</th>
							<td width="75%" class="tc" colspan="3">
								<span class="yellow fs16"><s:property value="tpltTempletPO.mainProdName" /></span>
							</td>
						</tr>
					</table>
					<div class="blank20"></div>
					<table id="maintab" align="center" width="100%" class="tb_blue02">
						<tr>
							<th width="70%" class="tc">��������</th>
							<th width="30%" class="tc">���(Ԫ)</th>
						</tr>
						<s:iterator value="feeList" id="feeList">
							<tr>
								<td width="70%" class="tc"><s:property value="#feeList.feeName" /></td>
								<td width="30%" class="tc"><s:property value="#feeList.fee" /></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="blank20"></div>
				<a href="#" class="bt10 mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;float:right;right:210px;">ȷ��(�밴ȷ�ϼ�)</a>
			</div>
			<div class=" clear"></div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
</body>
</html>