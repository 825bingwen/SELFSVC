<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// �ն˻���Ϣ
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // ʡ����Ϣ
    String provinceSD = Constants.PROOPERORGID_SD;

	// �ؼ�֧�ֱ�־
	String termSpecial = terminalInfo1.getTermspecial();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript">
		
//�Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
var is2GIDFlag = <%=termSpecial.charAt(8)%>;	

// �����ȴ�ʱ��
var limitTime = 30;

//�������ʣ��ʱ��ľ��
var timeToken;

//�ȴ������ľ��
var waitingToken;

// �ύ��־
var submitFlag = 0;

// ��ʼ�����֤�������߳�������־��1����������0��δ���������Ϊ1ʱ���û�����ȡ������������ùر����֤�Ķ����ӿ�
var initCardReader = 0;

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) 
{
	var key = GetKeyCode(e);
	
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
	//ȷ��
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}
	//����
	else if (key == 82 || key == 220) 
	{
		goback("<s:property value = 'curMenuId'/>");
		return;
	}
	// ���
	else if (key == 77)
	{
		changValue(-2);
		return;
	}	
	//����
	else if (key == 8 || key == 32 || key == 66)
	{
		var etarget = getEventTarget(e);
		if (etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value = backString(etarget.value);
		}
		if (etarget.name == 'passwd' && etarget.value == '' )
		{
			MoveLast(document.getElementById('passwd'));
		}
	}
	
	var passwd = document.forms[0].passwd.value;
	var pwdConfirm = document.forms[0].pwdConfirm.value;		

	if ((key == 8 || key == 32 || key == 66)
	 		&& pangu_getStrLen(trim(pwdConfirm)) == 0 
	 		&&  pangu_getStrLen(trim(passwd)) == 6) 
	{
		document.forms[0].passwd.focus();
		
		changObj(document.forms[0].passwd, 1);
		
		return true;
	}
	
		if (pangu_getStrLen(trim(passwd)) == 6 && document.forms[0].pwdConfirm.value == "") 
		{
			document.forms[0].pwdConfirm.focus();
			
			changObj(document.forms[0].pwdConfirm, 2);
			
			return true;
		}
		
	return true;
}

// ȥ���ո�
function trim(str) 
{
	while (str.charAt(str.length - 1) == " ") 
	{
		str = str.substring(0, str.length - 1);
	}
	
	while (str.charAt(0) == " ") 
	{
		str = str.substring(1, str.length);
	}
	
	return str;
}

function pangu_getStrLen(s) 
{
	var count = 0;
	var lenByte = s.length;
	for (i = 0; i < lenByte; i++) 
	{
		if (s.charCodeAt(i) > 256) 
		{
			count = count + 2;
		} 
		else 
		{
			count = count + 1;
		}
	}
	
	return count;
}	

function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
	r.select();
}

//��������ύ
function doSub()
{
	var passwd = document.getElementById('passwd').value;
	var pwdConfirm = document.getElementById('pwdConfirm').value;
	// �쳣��ʶ
	var errmsg = "";
	
	//�ж��������������Ƿ���ͬ
	if(passwd != pwdConfirm )
	{
		errmsg = "������������������ͬ���Ҳ�Ϊ�գ�������6λ�������룡";
	}
	//ҳ����ʾ������Ϣ
	else if( passwd == "" || pwdConfirm == "" )
	{
		errmsg = "���ã����벻��Ϊ�գ�������6λ�������룡";
	}
	
	else if( passwd.length != 6 || pwdConfirm.length != 6 )
	{
		errmsg = "���ã����볤��ӦΪ��λ����������ȷ�ķ������룡";
	}
	
	if( errmsg == "")
	{
	   if (submitFlag == 0)
		{
			submitFlag = 1;
			openRecWaitLoading();
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}cardInstall/feeConfirm.action";
			document.actform.submit();
		}
	}
	else
	{
		alertRedErrorMsg(errmsg);
	}
}

function goback(curmenu) 
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}			
}

// ҳ������ʱ�������֤��������ȡ�û����֤��Ϣ
function bodyLoad()
{
    document.getElementById("passwd").focus();
}

</script>
</head>
<body scroll="no" onload="bodyLoad();">
<form name="actform" method="post">
	<input type="hidden" id="errormessage" name="errormessage" value="<s:property value='errormessage'/>" />
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
    <input type="hidden" id="telSuffix" name="telSuffix" value="<s:property value='tel_suffix'/>"/>
    <!--�հ׿����к� -->
    <input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
    <!--�ֻ����� -->
    <input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
                
    <!--------------simInfoPO��Ϣ -------------->
    <input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
    <input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
    <input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
    <input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
    <input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value='simInfoPO.oldiccid' />"/>
    
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
				<a href="javascript:void(0)">4. ����ѡ��</a>
				<a href="javascript:void(0)" class="on">5. ���÷�������</a>
				<a href="javascript:void(0)">6. ����ȷ��</a>
				<a href="javascript:void(0)">7. �����ɷ�</a>
				<a href="javascript:void(0)">8. ȡ����ӡ��Ʊ</a>
			</div>
		</div>
		
		<div class="b712">
			<div class="bg bg712" id="errMsg"></div>
			<div class="p40 pr0">
				<div class="blank20"></div>
				<div class="blank20"></div>
				<p class=" tit_info"><span class="bg"></span>������6λ��������</p>
	            <div class="line w625"></div>
				<div class="blank10"></div>
				<div class="custom_money pl30 fl">
					<span class="pl40 fs20 fl lh48">�������룺</span>
					<input type="password" name="passwd" id="passwd" value="" maxlength="6" class="text1" onclick="changObj(this, 1);" onfocus="MoveLast(event);" />
				</div>
				<div class="blank10"></div>
				<div class="custom_money pl30 fl">
					<span class="pl40 fs20 fl lh48">ȷ�����룺</span>
					<input type="password" name="pwdConfirm" id="pwdConfirm" value=""  maxlength="6" class="text1" onclick="changObj(this, 2);" onfocus="MoveLast(event);" />
				</div>
				<div class="blank20"></div>
  					<div class=" mt10 pl20 clearfix" style="padding-left:60px;margin-top:35px;">
   					<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
   						<div class=" numbox clearfix">
       						<div class="clearfix">
       							<a href="javascript:void(0)">1</a>
       							<a href="javascript:void(0)">2</a>
       							<a href="javascript:void(0)">3</a> 
       							<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
     						</div>
       						<div class="clearfix"> 
       							<a href="javascript:void(0)">4</a>
       							<a href="javascript:void(0)">5</a>
       							<a href="javascript:void(0)">6</a>
								<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
       						</div>
       						<div class="nleft"> 
       							<a href="javascript:void(0)">7</a>
       							<a href="javascript:void(0)">8</a>
       							<a href="javascript:void(0)">9</a> 
       							<a href="javascript:void(0)">x</a>
       							<a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
       						</div>
       						<div class="nright"> 
       							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;"></a> 
       						</div>
       						<div class="blank10"></div>
   						</div>
   					</div>
   				</div>
			</div>
		</div>		
	</div>
	<%@ include file="/backinc.jsp"%>
</form>
	<script type="text/javascript">
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var type = 1;
	var numBoardText = document.getElementById('passwd');
	
	//��ȷ������
	var numBoardTextConfirm = document.getElementById('pwdConfirm');
	for (i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
		}
		// firfox�»�ȡname����ֵ��getAttribute("name"),������ֱ����obj.name
		if (numBoardBtns[i].getAttribute("name") == 'functionkey')
		{
			continue;
		}  
	
		numBoardBtns[i].onmousedown = function()
		{						 
			this.className = 'on';
		}
		
		numBoardBtns[i].onmouseup = function()
		{					  				
			changValue(0, this.innerHTML);
					
			this.className = '';
					
			if (pangu_getStrLen(trim(numBoardText.value)) == 6 ) 
			{
				document.forms[0].pwdConfirm.focus();
				
				changObj(document.forms[0].pwdConfirm, 2);
			}			      							   
		}
	}
	
	function changObj(o, t)
	{
		type = t;
		
		document.getElementById("errMsg").innerHTML = "";
		o.focus();
		
		numBoardText = o;
	}	
	
	function changValue(t, v)
	{
		if (t == -1)
		{
			numBoardText.value = numBoardText.value.slice(0, -1);
		}
		else if(t == -2)
		{
			numBoardText.value = "";
		}
		else if (type == 1 && numBoardText.value.length < 6)
		{			
			numBoardText.value += v;																		
		}
		else if (type == 2 && numBoardText.value.length < 6 && !isNaN(v))
		{
			numBoardText.value += v;
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}		
	</script>
</body>
</html>
