<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content=""/>
		<meta http-equiv="description" content=""/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
		<style>
			.bt_choosenum{ width:148px; height:55px; line-height:14px; padding:13px 0 0 11px;text-align:left;display:inline-block;*zoom:1; _display:inline; background:url(${sessionScope.basePath }images/bt_choosenum2.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bt_choosenum2.png");_background:none }
		</style>
	</head>
	<body scroll="no">
		<form id="actform" name="actform" method="post">
			
			<input type="hidden" id="errormessage" name="errormessage" value="" />
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
			<input type="hidden" id="selTelRule" name="selTelRule" value="<s:property value='selTelRule'/>"/>
			<!-- ǰ׺ -->
			<input type="hidden" id="telPrefix" name="telPrefix" value="<s:property value='telPrefix'/>"/>
			<!-- ��׺ -->
			<input type="hidden" id="telSuffix" name="telSuffix" value="<s:property value='telSuffix'/>"/>
			<!--�հ׿����к� -->
			<input type="hidden" id="blankno" name="blankno" value=""/>
			<!--�ֻ����� -->
			<input type="hidden" id="telnum" name="telnum" value="" />
						
			<!--------------simInfoPO��Ϣ -------------->
            <input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
            <input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
            <input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
            <input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
			<input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value='simInfoPO.oldiccid'/>"/>
			
			<%-- д��������Ϣ --%>
            <input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
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
	   					<a href="javascript:void(0)">5. ���÷�������</a>
	   					<a href="javascript:void(0)">6. ����ȷ��</a>
	   					<a href="javascript:void(0)">7. �����ɷ�</a>
	   					<a href="javascript:void(0)">8. ȡ����ӡСƱ</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank10"></div>
					<div class="p40">
						<div class="blank10"></div>
						<p class="tit_info_fs20">
							<span class="bg"></span>�𾴵��û�������ѡ�����룬ÿ��ֻ��Ԥ��<span class="yellow">1</span>�����롣
						</p>
						<div class="blank10"></div>
						<div class="num_dis_fs16">
							<s:iterator value="serverNumList" id="po" status="st">
								<a id="telNum_<s:property value="telnum" />" href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />', '<s:property value="lowConsumFee" />');" ><s:property value="telnum" /></a>
							</s:iterator>
							<div class="clear"></div>
	 						</div>
	 						
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="cardInstall/telnumResult.action" />
	 						
	 						<div class="fs18" style="padding-top:10px;margin-left:160px;">  
	    					<div class="fright2"> 
	    						<span class="fl pt15">��ѡ�����룺</span> 
								<a href="javascript:void(0)" class="bt_choosenum fl" style="visibility:hidden" id="chooseNum">
									<p class="fs16 tc" id="telnumText"></p>
									<p class="fs14 tc" id="payfeeText"></p>
	        					</a>
								<a href="javascript:void(0)" class="bt6 fr relative fl ml20" onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';openWindow('openWin1')">Ԥ��</a> 
	        				</div>
						</div>
					</div>	
					
					<div class=" clear"></div>
					
					<!--������-->
					<div class="openwin_tip div708w392h" id="openWin1">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<p class="fs28 lh2 pl142" id="winText" name="winText">
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
						    <div class=" blank10 "></div>
							<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';windowClose();" 
								onclick="gotoPrintSuccess();return false;">ȷ��</a> 
    						<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';windowClose()">ȡ��(�������)</a> 
	    				</div>
					</div>
					
					<div class="openwin_tip openwin_big div804w515h" id="openWinLoading">
	  					<div class="bg loading tc">
						    <div class="blank60"></div>
						    <div class="blank60"></div>
						    <img src="${sessionScope.basePath }images/loading.gif"  class="" alt="������..."/>
						    <div class="blank30"></div>
						    <p class="fs24   lh2">����ִ��Ԥ����Ϣ�������Ժ�...</p>
						    <p class=" fs18  lh2 yellow"></p>
	  					</div>
					</div>
					<!--����������-->	
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">
var submitFlag = 0;

// ����div
var divFlag = "";

// 82��220 ����		
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
	var key = GetKeyCode(e);
	
	//����
	if (key == 82 || key == 220) 
	{
		goback("<s:property value = 'curMenuId'/>");
	}

	// ��ȷ�ϼ�
	if((key == 13 || key == 89 || key == 221) && divFlag != '')
	{
		windowClose();
		gotoPrintSuccess();
	}
	
	// �������
	if(key == 77 && divFlag != '')
	{
		windowClose();
	}
}

<s:if test="%{null == serverNumList || serverNumList.size() == 0}">
	// û�в�ѯ�����������ĺ���ʱ����ʾ��Ϣ
	alertSuccessMsg("û�в�ѯ�����������Ŀ��ú���");
</s:if>

function goback(curmenu) 
{
	// ѡ�����ֻ��ŵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}			
}

// ѡ�����
function selectTelNum(telnum, payfee, orgid, lowConsumFee)
{
	<s:iterator value="serverNumList" id="po" status="st">
		document.getElementById('telNum_<s:property value="telnum" />').innerHTML = '<s:property value="telnum" />';
	</s:iterator>
	
	// ����ѡ�еĺ���Ϊ����			
	document.getElementById('telNum_'+telnum).innerHTML = '<B>'+telnum+'</B>';
	
	if (telnum == '')
	{
		document.getElementById('chooseNum').style.visibility='hidden';
		document.getElementById('telnum').value = '';
		return;
	}
	
	document.getElementById('telnum').value = telnum;
	document.getElementById("orgid").value = orgid;
	
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">' + telnum + '</p><p class="fs14 tc">ѡ�ŷѣ�' + payfee + ' Ԫ </p><p class="fs14 tc">��������ѣ�' + lowConsumFee + ' Ԫ</p>';
	document.getElementById('chooseNum').style.visibility='visible';			
	
	document.getElementById('winText').innerHTML = '<span class="yellow">��ѡ�񿪻���</span><br />���룺' + telnum + '<br />ѡ�ŷѣ�' + payfee + ' Ԫ<br />��������ѣ�' + lowConsumFee + ' Ԫ';
}

// ��һҳ
function nextPage(linkURL)
{			
	// ִ�в�ѯ
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading();
		
  		document.actform.target = "_self";
		document.actform.action = linkURL;
		document.actform.submit();
	}
}

// �رյ���divʱ�����divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}

// �����쳣
function setException(errorMsg) 
{			
	document.getElementById("errormessage").value = errorMsg;
	
	//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
	document.actform.submit();
}

// ��������
openWindow = function(id)
{
	// ����Ƿ���ѡ��
	if (document.getElementById('telnum').value == '')
	{
	    alertRedErrorMsg("���ã���ѡ���ֻ����룡");
		return;
	}
	
	divFlag = "openWin1";
	wiWindow = new OpenWindow("openWin1",708,392);				
}

// ����
function gotoPrintSuccess()
{
	// �ر���ʾ
	windowClose();
	
	// �ȴ���
	openRecWaitLoading();
	
	// ������Դռѡ
    var ret = telnumTmpPick();
    if (ret != 0)
    {
        setException("������Դռѡʧ�ܣ�");
        return;
    }
	// ������д����������鿨���Ƿ��п����ӿ�ReadCardStatus()���ն˻������ṩ
	var message = checkReadCardStatus();
	if (message != "")
	{
		setException(message);
		return;
	}
	
	// ��ȡ�հ׿����к�
	var blankCardSN = readBlankCardSN();
	
	if (blankCardSN.indexOf("1~") != -1)
	{
		setException(blankCardSN.split('~')[1]);
		return;
	}
	
	if(blankCardSN.length != 20)
	{
		setException("�Բ��𣬿����Ͳ���ȷ������ϵӪҵ������Ա!");
		return;
	}
		
	// ��ȡ�հ׿����к�
	document.getElementById('blankno').value = blankCardSN;
	
	// У��հ׿���Ϣ
    ret = chkBlankCardInfo();
    var resArray = ret.split('~~');
    if (resArray[0] == 0 || resArray[0] == "0")
    {
        setValue("iccid",resArray[1]);
        setValue("imsi",resArray[2]);
        setValue("issueData",resArray[3]);
        setValue("formNum",resArray[4]);
        setValue("oldiccid",resArray[5]);
    }
    else
    {
        setException(resArray[1]);
        return;
    }

    // �ύ ��������
	setTimeout(
	    function(){
	        document.actform.target="_self";
	        document.actform.action="${sessionScope.basePath }cardInstall/setPasswd.action";
	        document.actform.submit();                          
	    },
	500);
}

// ������Դռѡ
function telnumTmpPick()
{
    // ���� 0 1~~ʧ��ԭ��
    var ret = 1;// 0:�ɹ� 1:ʧ��
    
    // URL
    var url = "${sessionScope.basePath}cardInstall/telnumTmpPick.action";
    
    // ����
    var params = "telnum=" + document.getElementById('telnum').value  ;
    
    // ����
    var loader = new net.ContentLoaderSynchro(url, netload = function () {
            ret = this.req.responseText;
    }, null, "POST", params, null);
    
    // ����
    return ret;
}

// У��հ׿���Ϣ
function chkBlankCardInfo()
{
    // ���� 0 1~~ʧ��ԭ��
    var ret = 1;// 0:�ɹ� 1:ʧ��
    
    // URL
    var url = "${sessionScope.basePath}cardInstall/chkBlankCardInfo.action";
    
    // ����
    var params = "blankno=" + document.getElementById('blankno').value + "&";
        params = params + "prodid=" + document.getElementById("mainProdId").value + "&";
        params = params + "telnum=" + document.getElementById("telnum").value ;
    
    // ����
    var loader = new net.ContentLoaderSynchro(url, netload = function () {
            ret = this.req.responseText;
		    setValue("cardInfoStr",ret.substring(3));
    }, null, "POST", params, null);
    
    // ����
    return ret;
}

</script>
