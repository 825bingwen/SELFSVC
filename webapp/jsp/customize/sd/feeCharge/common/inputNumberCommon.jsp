<%@page contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
	    <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();doLoad();">
		<form name="actform" method="post">		
		    <%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="1" />
            
            <%-- ������Ϣ --%>
            <input type="hidden" id="errormessage" name="errormessage" value='' />
            
            <%-- У���ֽ��豸��ʶ --%>
            <input type="hidden" id="checkCashFlag" name="checkCashFlag" value='' />
            
            <%-- У���������豸��ʶ --%>
            <input type="hidden" id="checkCardFlag" name="checkCardFlag" value='' />    
            	
			<%@ include file="/titleinc.jsp"%>
	
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
		        						<li class="on fs20 clearfix" id="phone_input_1" >
		        							<i class="lh30">1.�����ֻ�����</i>
		        							<span class="pl20 fl lh75">�ֻ����룺</span>
		          						<input type="text" maxlength="11" id="numBoardText1" name="servNumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus=""/>
		        						</li>
		        						<li class="fs20 clearfix" id="phone_input_2">
		        							<i class="lh30">2. �ٴ������ֻ�����</i>
		        							<span class="pl20 fl lh75">�ٴ����룺</span>
		          						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus=""/>
		       						</li>         
		      					</ul>
		      					
		      					<!--С����-->
		       				<div class="numboard numboard_big fl" id="numBoard">
		         					<div class=" numbox">
		           					<div class="blank10"></div>
		           					<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
		           					<div class="clear"></div>
		           					<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
		           					<div class="clear"></div>
		           					<div class="nleft"> 
		           						<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> 
		           					</div>
		           					<div class="nright"> 
		           						<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
		           					</div>
		           					<div class="blank10"></div>
		         					</div>
		       				</div>
		       				<!--С����end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
		
			<%@ include file="/backinc.jsp"%>		
		</form> 
		<div class="popup_confirm" id="chkHardware_confirm">
            <div class="bg"></div>
            <div class="tips_title">
                ��ʾ��
            </div>
            <div class="tips_body">
                <p id="chkHardwareMsg" style="font-size: 19px; color: red;"></p>
            </div>
            <div class="btn_box tc mt20">
                <span class=" mr10 inline_block "><a href="#"
                    class="btn_bg_146" onmousedown="this.className='key_down'"
                    onmouseup="this.className='btn_bg_146';wiWindow.close();document.getElementById('numBoardText1').focus();">������ֵ</a>
                </span>
                <span class=" inline_block "><a class="btn_bg_146" href="#"
                    onmousedown="this.className='key_down'"
                    onmouseup="this.className='btn_bg_146';wiWindow.close();goback('feeCharge');">����</a>
                </span>
            </div>
        </div>                
	</body>
</html>
<script type="text/javascript">
//��ֹҳ���ظ��ύ
var submitFlag = 0;

function doSub()
{	
	//�Ժ�������ж�
	var pattern = /^\d{11}$/;
	
	var telNumber = document.getElementById("numBoardText1").value;
	if (telNumber == "" || !pattern.test(telNumber))
	{
		changObj(document.getElementById('numBoardText1'), 1);
		
		alertRedErrorMsg("��������ȷ���ֻ�����");
		
		return;
	}
	
	var confirmTelNumber = document.getElementById("numBoardText2").value;
	if (confirmTelNumber != telNumber)
	{
		changObj(document.getElementById('numBoardText2'), 2);
		
		alertRedErrorMsg("����������ֻ����������ͬ");
		
		return;
	}
	
	if (submitFlag == 0)
	{
	    openRecWaitLoading();
	    submitFlag = 1;
	    document.actform.action = url;
	    document.actform.submit();
	}	
}

// Ӳ�����
function doLoad()
{
    // ��ʾ��Ϣ
    var showMessage = "";
    
    // У���ֽ�ʶ���� =====start========
    var chkCashMsg = "";
    
    // У���Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
    if (window.parent.isCashEquip == 1)
    {
        chkCashMsg = initCashPayEquip();
    }
    else
    {
        chkCashMsg = "���ն˻���֧���ֽ�ʶ����";
    }
    
    if (chkCashMsg != "")
    {
        chkCashMsg = "���ڱ��ն˵��ֽ�ʶ�������ϣ���ʱ�޷�ʹ���ֽ���нɷѣ�";
        setValue("checkCashFlag", "casherror");
        showMessage = showMessage + chkCashMsg;
    }
    // У���ֽ�ʶ���� =====end========
    
    // У������������ =====start========
    var chkCardMsg = "";

    // У���Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
    if (window.parent.isUnionPay == 1)
    {
        chkCardMsg = initUnionCardPayEquip();
    }
    else
    {
        chkCardMsg = "���ն˻���֧������������";
    }
    
    if (chkCardMsg != "")
    {
        chkCardMsg = "���ڱ��ն˵��������������ϣ���ʱ�޷�ʹ�����������нɷѣ�";
        setValue("checkCardFlag", "carderror");
        showMessage = showMessage + chkCardMsg;
    }
    // У������������ =====end========
        
    if (chkCashMsg != "" && chkCardMsg != "")
    {
        goToError("�𾴵Ŀͻ������ڱ��ն˵��ֽ�ʶ�������������������ϣ���ʱ�޷����нɷѣ�");
        return;
    }
    
    // У����ܼ��� =====start========
    var chkKeyBoardMsg = "";
        
    // У���Ƿ�֧�ּ��ܼ��̱�־,0��֧��,1:֧��
    if (window.parent.isKeyBoard == 1)
    {
        chkKeyBoardMsg = initKeyBoard();
    }
    else
    {
        chkKeyBoardMsg = "���ն˻���֧���������";
    }
    
    if (chkKeyBoardMsg != "")
    {
        chkKeyBoardMsg = "���ڱ��ն˵�������̹��ϣ���ʱ�޷�ʹ�����������нɷѣ�";
        setValue("checkCardFlag", "carderror");
        showMessage = showMessage + chkKeyBoardMsg;
    }
    // У����ܼ��� =====end========
        
    if (chkCashMsg != "" && chkKeyBoardMsg != "")
    {
        goToError("�𾴵Ŀͻ������ڱ��ն˵��ֽ�ʶ������������̹��ϣ���ʱ�޷����нɷѣ�");
        return;
    }
    
    // У��Ʊ�ݴ�ӡ�� =====start========
    var chkPrintMsg = "";
    
    // У���Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
    if (window.parent.isPrintFlag == 1)
    {
        chkPrintMsg = initListPrinter();
    }
    else
    {
        chkPrintMsg = "���ն˻���֧��СƱ��ӡ��";
    }
    
    if (chkPrintMsg != "")
    {
        chkPrintMsg = "���ڱ��ն˵�СƱ��ӡ�����ϻ�ȱֽ����ʱ�޷���ӡСƱ��";
        showMessage = showMessage + chkPrintMsg;
    }
    // У��Ʊ�ݴ�ӡ�� =====end========
    
    // У��Ӳ���Ƿ���������ʾ�쳣��Ϣ
    if (showMessage != "")
    {
        openConfirmMessage("�𾴵Ŀͻ���" + showMessage);
    }
}

// ת������ҳ��
function goToError(errorMsg) 
{           
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        //�����ȴ���
        openRecWaitLoading();
        
        // ��¼������Ϣ
        setValue("errormessage", errorMsg);

        //�쳣������ֻҪ�������쳣��Ҫ��¼��־  
        document.actform.action = "${sessionScope.basePath }charge/goToError.action";
        document.actform.submit();
    }
}

// ����ȷ�Ͽ�
function openConfirmMessage(content)
{
    document.getElementById('chkHardwareMsg').innerHTML = content;
    wiWindow = new OpenWindow("chkHardware_confirm",708,392);
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}	

<%-- С����begin--%>
var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
var lastObj = document.getElementById('numBoardText1');
for (i = 0; i < numBoardBtns.length; i++)
{
    if (!numBoardBtns[i].className) 
    {
        numBoardBtns[i].className='';
    }
    // firfox�»�ȡname����ֵ��getAttribute("name"),������ֱ����obj.name
    if (numBoardBtns[i].getAttribute("name") == 'functionkey')
    {
        continue;  
    }

    numBoardBtns[i].onmousedown = function(){
            this.className = 'on';
    }
    
    numBoardBtns[i].onmouseup = function(){
        changValue(0, this.innerHTML);
        
            this.className = '';
            
            //�Ժ�������ж�
        var pattern = /^\d{11}$/;
        
            if (pattern.test(trim(lastObj.value))
                    && document.getElementById("numBoardText2").value == "")
        {
            //numBoardText1������ϣ��Զ���ת��numBoardText2
            document.getElementById("numBoardText2").focus();
            
            changObj(document.getElementById("numBoardText2"), 2);
            
            return true;
        }       
    }                   
}

function changObj(o, t)
{
    document.getElementById("errorMsg").innerHTML = "";
    
    lastObj = o;

    if (t == 1)
    {
        document.getElementById('phone_input_1').className = "on fs20 clearfix";
        document.getElementById('phone_input_2').className = "fs20 clearfix";
    }
    else
    {
        document.getElementById('phone_input_1').className = "fs20 clearfix";
        document.getElementById('phone_input_2').className = "on fs20 clearfix";
    }
}                   

function changValue(t, v)
{
    if (t == -1)
    {
        lastObj.value = lastObj.value.slice(0, -1);
    }
    else if(t == -2)
    {
        lastObj.value = ""
    }
    else if (lastObj.value.length < 11 && !isNaN(v))
    {                               
        lastObj.value += v;                                 
    }
    
    var r = lastObj.createTextRange(); 
    r.collapse(false); 
    r.select();
}
<%-- С����begin--%>

alertRedErrorMsg = function(content)
{
    document.getElementById('winText_ErrorMsg').innerHTML = content;
    wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
};

//8��32��66��77 ����
//82��220 ����
//13��89��221 ȷ��
//80 ��ӡ
//85 ��һҳ
//68 ��һҳ

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
    //����
    else if (key == 8 || key == 32 || key == 66 || key ==77)
    {
        var etarget = getEventTarget(e);
        if (etarget.type == "text" || etarget.type == "password") 
        {
            etarget.value = backString(etarget.value);
        }
    }
    
    //�Ժ�������ж�
    var pattern = /^\d{11}$/;
    
    if ((key == 8 || key == 32 || key == 66 || key ==77) 
            && pattern.test(trim(document.getElementById("numBoardText1").value)) 
            && document.getElementById("numBoardText2").value == "")
    {
        //����ʱ��numBoardText2������ϣ��Զ���ת��numBoardText1
        document.getElementById("numBoardText1").focus();
        
        changObj(document.getElementById("numBoardText1"), 1);
        
        return true;
    }
    
    if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
            && document.getElementById("numBoardText2").value == "")
    {
        //numBoardText1������ϣ��Զ���ת��numBoardText2
        document.getElementById("numBoardText2").focus();
        
        changObj(document.getElementById("numBoardText2"), 2);
        
        return true;
    }           
}
</script>