<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

String yuanMoney = (String) request.getAttribute("recFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>���п�����ȷ��</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>		
<script language="javascript">
// �����ύ��ʶ
var submitFlag = 0;

// �쳣�ύ��־ 0:δ�ύ 1:���ύ
var exSubmitFlag = 0;

// �˶�ʱ��
var limitTime = 60;

//����ʣ��ʱ��ľ��
var timeToken;		

var termid = "<%=termInfo.getTermid() %>";

// �̻�����
//var unitType = "1";

// �������� 'A':���ѽ��� 'B':�ش�ӡ 'C':�����
//var businesstype = "A";// 

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
	
	//�ύ
	if (key == 13 || key == 89 || key == 221)
	{
		commitBusi();// �ɷ�ȡ������ȡ������������
	}
	// ���������ѣ�ȷ�Ͻ����棬�û��������˳�
	//�˳���82
	else if (key == 82 || key == 220)
	{
		cancelBusi();
	}
}

//�����쳣
function setException(errorMsg) 
{
	//�����ʱ����
	clearInterval(timeToken);

	// ��ʾ������Ϣ
	document.getElementById("errormessage").value = errorMsg;
	
	//�쳣����ֻҪ�������쳣��Ҫ��¼��־
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/installError.action";
	document.actForm.submit();
}



function strlen(str)    
{    
    var i;    
    var len;    
        
    len = 0;    
    for (i=0;i<str.length;i++)    
    {    
        if (str.charCodeAt(i)>255) len+=2; else len++;    
    }
    return len;    
} 


// ȷ�Ͻɷ�
function commitBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		//�����ʱ����
		clearInterval(timeToken);
				
		openRecWaitLoading();
	
		setTimeout("fPosPay()", 500);
	}
}

<%--
* �û��������˳����ѽ��ס� 
*  ���������ѣ�ȷ�Ͻ����棬�û��������˳���
--%>
function cancelBusi()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		openRecWaitLoading();
		setException("����ȡ�����νɷѣ���л����ʹ�ã���ȡ��������������");
	}			
}

function goBack(menu)
{
	cancelBusi();
}

//����ʣ��ʱ��
function waitForSubmit() 
{
	if (limitTime <= 0)
	{
		return;
	}
	
	//������ʱ��һ��limitTime��
	limitTime = limitTime - 1;
				
	document.getElementById("tTime").value = limitTime;
				
	//������ʱ�����
	if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
	{           	
		setException("�˶���Ϣ��ʱ����ȡ��������������");
	}
}

// ������ʱ��
function startclock() 
{
	try 
	{
		timeToken = setInterval("waitForSubmit()", 1000);
	}
	catch (e) 
	{
		setException("�˶���Ϣʧ�ܣ���ȡ��������������");
	}
}

function doLoad()
{
	//���á���ҳ�������˳�����ť
	document.getElementById("homeBtn").disabled = true;
	document.getElementById("quitBtn").disabled = true;
}
</script>
</head>
<body scroll="no">
<form name="actForm" method="post">		
	<!-- �հ׿���Ϣ -->
	<input type="hidden" id="imsi" name="simInfoPO.imsi" value='<s:property value="simInfoPO.imsi" />' />
	<!--ICCID -->
	<input type="hidden" id="iccid" name="simInfoPO.iccid" value='<s:property value="simInfoPO.iccid" />' />
	<!--����Ϣ���ĺ��� -->
	<input type="hidden" id="smsp" name="simInfoPO.smsp" value='<s:property value="simInfoPO.smsp" />' />
	<input type="hidden" id="pin1" name="simInfoPO.pin1" value='<s:property value="simInfoPO.pin1" />' />
	<input type="hidden" id="pin2" name="simInfoPO.pin2" value='<s:property value="simInfoPO.pin2" />' />
	<input type="hidden" id="puk1" name="simInfoPO.puk1" value='<s:property value="simInfoPO.puk1" />' />
	<input type="hidden" id="puk2" name="simInfoPO.puk2" value='<s:property value="simInfoPO.puk2" />' />
	<input type="hidden" id="eki" name="simInfoPO.eki" value='<s:property value="simInfoPO.eki" />' />
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
	<!-- Ͷ������ˮ�� -->
	<input type="hidden" id="terminalSeq" name="terminalSeq" value="<s:property value='terminalSeq'/>"/>
	<!-- ʵ�ʽɷѽ�� -->
	<input type="hidden" id="tMoney" name="tMoney" value='<s:property value="tMoney" />'/>
	
	<%-- ������ --%>
	<input type="hidden" id="dealNum" name="dealNum" value='' />
	
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
	
	<%-- �Ƿ񽫿��ƶ��������� 1������ 0�������� --%>
	<input type="hidden" id="callBackCard" name="callBackCard" value="0"/>
	
	<%-- ������ӡ��Ϣ --%>
	<input type="hidden" id="printcontext" name="printcontext" value=""/>
	<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
	<input type="hidden" id="needReturnCard" name="needReturnCard" value="<s:property value='needReturnCard' />"/>
	<input type="hidden" id="cardnumber" name="cardnumber" value='<s:property value="cardnumber" />'/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="quitFlag" name="quitFlag" value=""/>
	<input type="hidden" id="posResCode" name="posResCode" value=''/>
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
				<a href="javascript:void(0)">5. ����ȷ��</a>
				<a href="javascript:void(0)" class="on">6. �����ɷ�</a>
				<a href="javascript:void(0)">7. ȡ����ӡСƱ</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
  					<div class="blank60"></div>
  					<div class="p40">
  						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="telnum" /></span></p>
  						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="recFee" /></span> Ԫ</p>
					<p class="tit_info"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />�롣</p>
					<div class="blank25"></div>
					<div class="line"></div>
  					<div class="blank60"></div>
  						
  					<div class="recharge_result tc">
  						<div class="btn_box2 clearfix">
  							<a href="javascript:void(0);" onclick="commitBusi();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">ȷ��</a>
  						</div>
  					</div>				
  				</div>
			</div>
		</div>
	</div>
	
	<div class="footer" id="footer">
		<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'"></a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';"></a>
		<a id="quitBtn" href="javascript:void(0);" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'" onclick="cancelBusi();return false;"></a>
	</div>
</form>
</body>
<script type="text/javascript">
clearTimeout(timeOut);
startclock();

// ��ʶ�ؼ�ʹ��
closeStatus = 1;


//�������ۿ�
function fPosPay()
{
	try
	{
		//�����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
		var result = GetPosBatchNum();
		
		var dataArray = result.split("$");
		
		//��ȡ���ٺš����κ�ʧ��
		if (dataArray[0] != "0")
		{
			setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������г�ֵ����ȡ��������������");
			
			return;
		}
		
		//��ȡ���ٺš����κųɹ�
		print_posNum = dataArray[1];
		print_batchNum = dataArray[2];
		
		document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
		
		//�ۿ�֮ǰ��¼��־
		var url = "${sessionScope.basePath }cardInstall/addChargeLog.action";

		var params = "telnum=" + <s:property value='telnum' /> ;
		params = params + "&payType=1";
		params = params + "&tMoney=" + "<s:property value='recFee' />";
		params = params	+ "&status=00";
		params = params + "&description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־'));
		params = params + "&acceptType=" + document.getElementById("acceptType").value;
		params = params	+ "&region=" + document.getElementById("region").value;
		params = params + "&cardnumber=" + document.getElementById("cardnumber").value + "&terminalSeq=" + document.getElementById("terminalSeq").value;
		params = params	+  "&posnum=" + print_posNum + "&batchnumbeforekoukuan=" + print_batchNum;
		
		var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
				var dataArray = resXml.split("~~");
				
				//��¼��־�ɹ�
				if (dataArray[0] == "1") 
				{
					//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼
					var oid = dataArray[1].replace(/[\r\n]/g, "");
					document.getElementById("chargeId").value = oid;
					
					var posResCode = '';
					
					//�ύ�ۿ�����
					try
					{
						var ret ;
						
						// �ر��������
						ret = CloseCom();
						if (ret != "0" || ret != 0)
						{
							setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
							return;
						}
						
						// �����ۿ�
						// posnum ���ٺ�;batchnum ���κţ� bankcardID���п��ţ�money �ɷѽ��(��)
						var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardnumber" />',"<%=fenMoney %>");

						// �򿪼��̴��ڡ���������ģʽ
						ret = OpenCom();
						if (ret != "0" || ret != 0)
						{
							window.parent.showFrmErr("����:�򿪼��̴���ʧ�ܣ�");
						}
						ret = SetWorkMode(0);
						if (ret != "0" || ret != 0)
						{
							window.parent.showFrmErr("����:���ü���ģʽʧ�ܣ�");
						}
						
						// �ۿ�ʧ��ת���쳣����
						posResCode = payReturnStr.substring(0,2);
						document.getElementById('posResCode').value = posResCode;
						if (payReturnStr.substring(0,2) != "00")
						{
							setException("�������ۿ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
							return;
						}
						
						// �ۿ�����ȡֵ
						var resultstr = payReturnStr.substring(0,34);
						var printcontext = payReturnStr.substring(34,payReturnStr.length);
						
						document.getElementById('printcontext').value = printcontext;
						
						// �ۿ�ɹ� ����34 �ɹ�00
						if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
						{
						    // �򿪼��̴��ڡ���������ģʽ
							OpenCom();
							SetWorkMode(0);

							var printcontexts = printcontext.split('~');							
							document.getElementById("tMoney").value = printcontexts[9];

							var params1 = "chargeId=" + document.getElementById("chargeId").value + "&unionpayuser=" + printcontexts[0];// id���̻���
							params1 = params1 + "&unionpaycode=" + printcontexts[1] + "&busitype=" + encodeURI(encodeURI(printcontexts[2]));// �ն˺�
							params1 = params1 + "&batchnum=" + printcontexts[4] + "&authorizationcode=" + printcontexts[5];// ���κź���Ȩ��
							params1 = params1 + "&businessreferencenum=" + printcontexts[6] + "&unionpaytime=" + printcontexts[7];// ���ײο��źͽ���ʱ��
							params1 = params1 + "&vouchernum=" + printcontexts[8] + "&unionpayfee=" + printcontexts[9];// ƾ֤�źͿۿ���
							params1 = params1 + "&status=01";
							params1 = params1 + "&description=" + encodeURI(encodeURI('����ʱ�ۿ�ɹ�'));
							params1 = params1 + "&posResCode=" + document.getElementById('posResCode').value;// unionretcode���������ֶ�
							params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;// �ն���ˮ
				
							//������־
							var url1 = "${sessionScope.basePath }cardInstall/updateCardChargeLog.action";
							
							var loader1 = new net.ContentLoader(url1, netload = function () {
								var resXml1 = this.req.responseText;
								
								//������־�ɹ�
								if (resXml1 == "1" || resXml1 == 1)
								{
									//����
									//payToBoss();	
									// д������
									goSuccess();								
								}
								//������־ʧ��
								else
								{	
									setException("����ʧ�ܣ���ȡ��������������СƱ��ƾСƱ����Ӫҵ�������˿ллʹ��!");
									return;
								}								
							}, null, "POST", params1, null);
						}
						//�ۿ�ʧ��
						else
						{
							setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
							return;
						}
							
					}
					catch (e)
					{
						
						if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
						{
							setException("�������ۿ�ɹ������ǿ���ʧ�ܡ���ȡ��������������");
						}
						else
						{
							setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
						}
					}							
				}
				//��¼��־ʧ��
				else 
				{				
					setException("��־��¼ʧ�ܣ����ܽ����������ɷѲ�������ȡ��������������");
				}					
		}, null, "POST", params, null);	
	}
	catch (e)
	{
		if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
		{
			setException("�������ۿ�ɹ������ǿ���ʧ�ܡ���ȡ��������������");
		}
		else
		{
			setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
		}
	}				
}

// ��BOSS����(������)
function goSuccess() 
{
	// Ӧ��У��һ���Ƿ�Ϊ0
	if (document.getElementById("tMoney").value == "" 
			|| parseInt(document.getElementById("tMoney").value) <= 0)
	{
		return;
	}
	
	// �ж�Ͷ�ҽ���������Ӧ�ɽ��
	var recFee = document.getElementById("recFee").value;
	var tMoney = document.getElementById("tMoney").value;
	
	// ����ʵ�ʽɷ�
	document.getElementById("toFee").value = recFee;

	// �ɷ�״̬  0 �ɹ�
	document.getElementById("payStatus").value = "0";
	
	// �հ׿���
	var blankno = '<s:property value="blankno"/>';
	
	var cardInfoStr = document.getElementById("cardInfoStr").value;
	var blankno = document.getElementById("blankno").value;
		
	// �ύ�ɷ�����ǰ��д��
	var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
	
	// д��ʧ�� ���ж���д��
	if(writeData.indexOf("11~") != -1)
	{
		// �ٴ�д��
		againWriteCardProcess();
		return;
	}
	
	// д�������쳣����ת�쳣ҳ��
	if(writeData.indexOf("1~") != -1)
	{
		// д��ʧ�� 1
		document.getElementById("writeCardStatus").value = "1";
	
		writeCardException(writeData.split("~")[1]);
		return;
	}
	
	// д���ɹ�
	document.getElementById("writeCardStatus").value = "0";
	
	// ����״̬ Ĭ������2 0 �ɹ�  1 ʧ��
	document.getElementById("installStatus").value = "2";
		
	//�ύ�ɷ�����
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
	document.actForm.submit();			
}

// �ٴ�д������
function againWriteCardProcess()
{
	/**
	* д���쳣�������̣�
	*1.	���ó��̻��տ��ӿڰѷѿ��ߵ�������  
	*2.	���¿�������λ
	*3.	���ÿ��̵Ķ��տ����кŴų����к�
	*4.	����BOSS�ӿ�У�鿨��Դ�Ƿ����
	*5.	ת��������ϸȷ��ҳ�����д����ֱ��д�������ﵽ��
	*/
	// 1.���ó��̻��տ��ӿڰѷѿ��ߵ������� ��д��ʧ�ܵ�ʱ���Ѿ��ƶ�����������
	// 2.������д����������鿨���Ƿ��п����ӿ�ReadCardStatus()���ն˻������ṩ
	var message = checkReadCardStatus();
	if (message != "")
	{
		writeCardException(message);
		return;
	}
	
	// ��ȡ�հ׿����к�
	var blankCardSN = readBlankCardSN();
	
	if (blankCardSN.indexOf("1~") != -1)
	{
		writeCardException(blankCardSN.split('~')[1]);
		return;
	}
	
	if(blankCardSN.length != 20)
	{
	    writeCardException("�Բ��𣬿����Ͳ���ȷ������ϵӪҵ������Ա!");
		return;
	}
			
	// ��ȡ�հ׿����к�
	document.getElementById('blankno').value = blankCardSN;
        
	// 6.У�鿨��Դ�Ƿ����
	ret = chkBlankNo();
	if (ret != 0)
	{
		writeCardException("�Բ��𣬿���Դ�����ã�");
		return;
	}
	// 7. �հ׿���Դ��ѡ
	ret = blankCardTmpPick();
	var resArray = ret.split('~~');
	if (resArray[0] != 0)
	{
		writeCardException("�հ׿���Դ��ѡʧ�ܣ�");
		return;
	}
	else
	{
		setValue("iccid",resArray[1]);
		setValue("imsi",resArray[2]);
		setValue("smsp",resArray[4]);
		setValue("cardInfoStr",ret.substring(3).replace("+", "^^"));
	}
	
	var cardInfoStr = document.getElementById("cardInfoStr").value;
	var blankno = document.getElementById("blankno").value;
	
	// �ύ�ɷ�����ǰ��д��
	var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
	
	// ����д��ʧ�� ��ת�쳣ҳ��
	if(writeData.indexOf("1~") != -1)
	{
		// д��ʧ�� 1
		document.getElementById("writeCardStatus").value = "1";
		writeCardException(writeData.split("~")[1]);
		return;
	}
	
	// д���ɹ�
	document.getElementById("writeCardStatus").value = "0";
	
	// ����״̬ Ĭ������2 0 �ɹ�  1 ʧ��
	document.getElementById("installStatus").value = "2";
	
	
	//�ύ�ɷ�����
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
	document.actform.submit();
}

// У��հ׿���Դ�Ƿ����
function chkBlankNo()
{
	// ���� 0 1~~ʧ��ԭ��
	var ret = 1;// 0:�ɹ� 1:ʧ��
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/chkBlankNo.action";
	
	// ����
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "orgid=" + document.getElementById("orgid").value ;
	
	// ����
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// ����
	return ret;
}
	
// �հ׿���Դ��ѡ
function blankCardTmpPick()
{
	// ���� 1 0~~iccid~~imsi~~eki~~smsp~~pin1~~pin2~~puk1~~puk2
	var ret = 1;// 0:�ɹ� 1:ʧ��
	
	// URL
	var url = "${sessionScope.basePath}cardInstall/blankCardTmpPick.action";
	
	// ����
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "telnum=" + document.getElementById("telnum").value;
	
	// ����
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// ����
	return ret;
}

// ����д���쳣
function writeCardException(errorMsg)
{
	document.getElementById("errormessage").value = errorMsg;
	
	//�����ʱ����
	clearInterval(timeToken);
		
	// д���쳣��¼�쳣��־�����ӽɷ���־����¿�����־��
	document.actForm.target = "_self";
	document.actForm.action = "${sessionScope.basePath }cardInstall/makeErrLog.action";
	document.actForm.submit();
}
</script>
</html>
