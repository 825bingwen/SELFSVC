<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) {
	doCancle();
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
    
	//����
	if (key == 82 ) 
	{
		doCancle();
	}
	//ȷ��
	else if (key == 89 || key == 13)
	{
		doSub();
	}	
}

//�ύ������ȷ��ҳ��
function doSub()
{
	//�����ȴ���
	openRecWaitLoading();
	
	//��֤�ֻ����������֤�����Ƿ����
	var res = authIdCard();
	
	if(res != "0")
	{
		setException("���֤��Ϣ���ֻ�����У��ʧ��");
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.action = "${sessionScope.basePath }prepareCard/prepareFeeConfirm.action";
		document.actform.submit();	
	}
}


//��֤�ֻ����������֤�����Ƿ����
function authIdCard()
{
	//idCardNo:���֤����
	var postStr ="idCardPO.idCardNo="+getValue("idCardNo");
		 
	var url = "${sessionScope.basePath}prepareCard/checkIdCard.action";
	var res;
	
	//���ýӿ��첽����
	var loader = new net.ContentLoaderSynchro(url, netload = function() 
	{	
		res = this.req.responseText;
	}, null, "POST", postStr, null);
	
	return res;
}

// ȡ��
function doCancle()
{
	if (submitFlag == 0)
	{	
		//�����ȴ���
		openRecWaitLoading();
		submitFlag = 1;
		document.actform.action = "${sessionScope.basePath}prepareCard/validTelAndPwd.action";
		document.actform.submit();	
	}			
}

// ��ʾ��ȡ����֤��ͼƬ
function showIDImg()
{
    document.getElementById("idCardImg").innerHTML = "";
    document.getElementById("idCardImg").style.filter
        = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"<s:property value="idCardPO.idCardPhoto" />\")";
    document.getElementById("idCardImg").style.display = '';
}

// �����쳣
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//�����ȴ���
		openRecWaitLoading();
		
		document.getElementById("errormessage").value = errorMsg;

		//�쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.actform.action = "${sessionScope.basePath }prepareCard/addRecLog.action";
		document.actform.submit();
	}
}	
</script>
	</head>
	<body scroll="no" onload="showIDImg();">
		<form name="actform" method="post">
			<!-- ������Ϣ -->
			<input type="hidden" id="errormessage" name="errormessage" value='' />
			<!-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ -->
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint'/>" />
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<!--------------���֤��Ϣ -------------->
			<!-- ���� -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value="<s:property value='idCardPO.idCardName' />" />
			<!-- �Ա� -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value="<s:property value='idCardPO.idCardSex' />" />
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value="<s:property value='idCardPO.idCardNo' />" />
			<!-- ֤����ַ -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value="<s:property value='idCardPO.idCardAddr' />" />
			<!-- ��ʼʱ�� -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value="<s:property value='idCardPO.idCardStartTime' />" />
			<!-- ����ʱ�� -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value="<s:property value='idCardPO.idCardEndTime' />" />
			<!-- ����Ϣ -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value="<s:property value='idCardPO.idCardContent' />" />
			<!-- ��Ƭ��Ϣ -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value="<s:property value='idCardPO.idCardPhoto' />" />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>����</h2>
						<div class="blank10"></div>
	   					<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ѡ����֤��ʽ</a>
						<a href="javascript:void(0)" class="on">3. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)">4. ����ȷ��</a>
	   					<a href="javascript:void(0)">5. ѡ��֧����ʽ</a>
	   					<a href="javascript:void(0)">6. �����ɷ�</a>
	   					<a href="javascript:void(0)">7. �¿���ӡСƱ</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank40"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>���֤��Ϣȷ��
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							<table width="100%" class="tb_blue" align="center">
								<tr>
									<th width="40%" class="tc">����</th>
									<td width="40%" class="tc">
										<span class="yellow fs20"><s:property value="idCardPO.idCardName" /></span>
									</td>
									<td width="20%" class="tc" rowspan="2" colspan="1">
										<span class="yellow fs20">
											<div id="idCardImg" style="width:102px; height:126px;"/>
										</span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">�Ա�</th>
									<td width="20%" class="tc" colspan="1">
										<span class="yellow fs20"><s:property value="idCardPO.idCardSex" /></span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										���֤��
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"><s:property value="idCardPO.idCardNo" /></span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤����ַ
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardAddr" /></span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤����ʼʱ��
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardStartTime" /> </span></td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤������ʱ��
									</th>
									<td width="60%" class="tc" colspan="2"><span class="yellow fs20"> <s:property value="idCardPO.idCardEndTime" /> </span></td>
								</tr>
							</table>
							<div class="blank20"></div>
							<div class="btn_box tc">
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doSub();">ȷ��</a>
								</span>
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doCancle();">ȡ��</a>
								</span>
							</div>
						</div>
					</div>
					<div class=" clear"></div>

				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
