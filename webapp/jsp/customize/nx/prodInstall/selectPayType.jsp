<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.customize.nx.selfsvc.prodInstall.model.IdCardPO" %>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
	
	// �ͻ�������Ϣģ����չʾ
	String custName = ((IdCardPO)request.getAttribute("idCardPO")).getIdCardName();
	custName = CommonUtil.getVagueName(custName);
	
	// �����ֽ�ɷ�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// �ֽ𽻷�����ʹ����ʾ��Ϣ
	String limitTip = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_LIMITTIP");
	if (null == limitTip)
	{
		limitTip = "";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />		
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
//��ֹҳ���ظ��ύ
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
	if (key == 220 || key == 82) 
	{
		goback("<s:property value='curMenuId' />");
	}
	else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
	{
		try
		{
			wiWindow.close();
		}
		catch (ex){}
		
		return;
	}
	
	<s:iterator value="usableTypes" id="type" status="st">	
		if (key == <s:property value="#st.index + 49" />)
		{
			doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />');
		}			
	</s:iterator>
}

// ����
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }prodInstall/feeConfirm.action";
		document.forms[0].submit();
	}
}

function doSub(menuid, url)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("installStatus").value = "2";
		document.getElementById("payStatus").value = "2";
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"<s:property value='telnum' />ѡ�񽻷ѷ�ʽ��" + menuid);
		
		document.getElementById("curMenuId").value = menuid;

		// ѡ���ֽ�ɷ�
		if (url == "prodInstall/cashCharge.action")
		{
			// ֧����ʽ����Ϊ�ֽ�ɷ�
			document.getElementById("payType").value = "4";
			
			// �ֽ𽻷ѣ��жϴ�ʱ������Ƿ����
			var url1 = "${sessionScope.basePath}prodInstall/checkTime.action";
			
			var loader = new net.ContentLoader(url1, netload = function () {
				var resXml1 = this.req.responseText;
				// resXml1 = "1";//�����ã�������ע��
				if (resXml1 == "1" || resXml1 == 1)
				{
					openRecWaitLoading_NX("recWaitLoading");
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				else
				{
					submitFlag = 0;
					
					alertError('<%=limitTip%>');
					
					return;
				}
			}, null, "POST", null, null);
		}
		else
		{	
			// ֧����ʽ����Ϊ�������ɷ�
			document.getElementById("payType").value = "1";
			openRecWaitLoading_NX("recWaitLoading");
			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + url;
			document.actform.submit();
		}
	}			
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		<!-- ����֤��Ϣ -->
		<!-- ���� -->
		<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
		<!-- �Ա� -->
		<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
		<!-- ����֤���� -->
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
		<!--ICCID -->
		<input type="hidden" id="iccid" name="iccid" value="<s:property value='iccid'/>" />
		<!--����Ϣ���ĺ��� -->
		<input type="hidden" id="smsp" name="smsp" value="<s:property value='smsp'/>" />
		<!-- ��ƷID -->
		<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
		<!-- �������� -->
		<input type="hidden" id="pwd" name="pwd" value="<s:property value='pwd'/>"/>
		
		<!-- �ɷ���Ϣ -->
		<!-- ���úϼ� -->
		<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
		
		<input type="hidden" id="chargeFlag" name="chargeFlag" value="<s:property value='chargeFlag' />"/>
		
		<%-- ������־ --%>
		<%-- ��ˮ�� --%>
		<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
		<%-- �ɷѷ�ʽ��1����������4���ֽ� --%>
		<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
        <%-- Ĭ��2����ʼ״̬ 0��д���ɹ� 1��д��ʧ�� --%> 
        <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
        <%-- Ĭ��2����ʼ״̬ 0���ɷѳɹ� 1���ɷ�ʧ�� --%> 
        <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
        <%-- Ĭ��2����ʼ״̬ 0�������ɹ� 1������ʧ�� --%>
        <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
        <%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
           
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2>���߿���</h2>
					<div class="blank10"></div>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
					<a href="javascript:void(0)">2. ��ȡ����֤��Ϣ</a>
   					<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
   					<a href="javascript:void(0)">4. ����ѡ��</a>
   					<a href="javascript:void(0)">5. ���÷�������</a> 
   					<a href="javascript:void(0)">6. ����ȷ��</a>
   					<a href="javascript:void(0)"  class="on">7. �����ɷ�</a>
   					<a href="javascript:void(0)">8. ȡ����ӡ��Ʊ</a>						
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank60"></div>
   					<div class="p40">
   						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="telnum" /></span></p>
   						<p class="fs22 fwb pl40 lh30">�û�������<span class="yellow fs22"><%=custName%></span></p>
						<p class="fs22 fwb pl40 lh30">Ӧ�ɽ�<span class="yellow fs22"><s:property value="recFee" /></span> Ԫ</p>
						<div class="blank10"></div>
						<div class="line"></div>
     						<div class="blank10"></div>
     						<p class="fs22 tit_arrow"><span class="bg"></span>ѡ��֧����ʽ��</p>
     						<div class="blank20"></div>
       					<div class="blank5"></div>
       					<ul class="pay_way_list clearfix">
       						<s:iterator value="usableTypes" id="type" status="st">
     								<li>
										<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;">
		       								<img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' />
		       								<span class="chargeTypeDesp1">(�밴<s:property value="#st.index + 1" />��)</span>
		       							</a>
     								</li>
     								<div style = "width:300px;"></div>
       						</s:iterator>
       					</ul>				
   					</div>
				</div>
			</div>
		</div>
		<%@ include file="/backinc.jsp"%>		
		<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-04.wav" id="player04" align="center" autostart=true border="0" style="height:0px;width:0px;">
	</form>
</body>
</html>