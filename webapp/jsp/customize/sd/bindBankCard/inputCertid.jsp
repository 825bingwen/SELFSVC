<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">
			
			<!-- ���� -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" 
				value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- ֤������ -->
			<input type="hidden" id="id_type" name="bindBankCardPO.id_type" 
				value="<s:property value='bindBankCardPO.id_type' />"/>
			<!-- ֤���������� -->
			<input type="hidden" id="idCardTypeText" name="bindBankCardPO.idCardTypeText" 
				value="<s:property value='bindBankCardPO.idCardTypeText' />"/>
			<!-- ���п����� -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" 
				value="<s:property value='bindBankCardPO.bankCardType' />"/>
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" 
				value="<s:property value='bindBankCardPO.idCardNum' />"/>
			
			<%@ include file="/titleinc.jsp"%>
		
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class=" p40">
						<div class="blank30"></div>
						<p class="blank30 tit_info"><span class="bg"></span>�׳�ֵǩԼ</p>
						
						<div class="blank15"></div>

						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix">
									<i class="lh30">������֤����</i>
									<span id="redstar1" class="pl20 fl lh75">֤���ţ�</span>
									<div class="custom_money">
										<input type="text" name="certid" id="certid" maxlength="18" class="text2 fl fs24" 
											onfocus="MoveLast(event);">
									</div>										
								</li>
							</ul>
							<div class="numboard numboard_big numboard_small2 fl" id="numBoard">
        						<div class=" numbox clearfix">
             						<div class="clearfix">
             							<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
           							</div>
            						<div class="clearfix"> 
            							<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            						</div>
            						<div class="nleft"> 
            							<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
            						</div>
            						<div class="nright"> 
            							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
            						</div>
            						<div class="blank10"></div>
          						</div>
        					</div>
        				</div>
        				
        				<div class="blank10"></div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">
		
	// �ύ��־
	var submitFlag = 0;
	
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
			if (etarget.name == 'password' && etarget.value == '' )
			{
				MoveLast(document.getElementById('servnumber'));
			}
		}
		
		var certid = document.forms[0].certid.value;

		if ((key == 8 || key == 32 || key == 66 || key ==77)
	 		&& (pangu_getStrLen(trim(certid)) == 15 || pangu_getStrLen(trim(certid)) == 18)) 
		{
			document.forms[0].certid.focus();
			
			return true;
		}
		
		return true;
	}
	
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
	
	// ������ƶ�������������
	function MoveLast(e) 
	{
		var etarget = getEventTarget(e);
		var r = etarget.createTextRange();
		r.moveStart("character", etarget.value.length);
		r.collapse(true);
		r.select();
	}
	
	// ֤���������ύ
	function doSub()
	{
		var certid = trim(document.actform.certid.value);
		
		if (certid == "")   
		{
			alertRedErrorMsg("������֤���š�");
			return false;
		}
		
		// У���������֤����Ǽǵ�֤�������Ƿ����
		if (document.actform.idCardNum.value != certid)
		{
			alertRedErrorMsg("��֤������Ǽǵ�֤�����벻�������������롣");
			return false;
		}
		
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.actform.action = "${sessionScope.basePath}bindBankCard/showUserInfo.action";
			document.actform.submit();
		}		
	}
	
	// ����ǰһ��ҳ��
	function goback(curmenu) 
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.actform.certid.value = "";
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath}bindBankCard/showUserInfo.action";
			document.actform.submit();				
		}			
	}
	
	// ҳ���ʼ����
	function bodyLoad()
	{
	    document.getElementById("certid").focus();
	}
	
	// ȡ�����а���
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var numBoardText = document.getElementById('certid');
	
	for (i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
		}
		//// firfox�»�ȡname����ֵ��getAttribute("name"),������ֱ����obj.name
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
		}
	}
	
	// �����������������䵽�������
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
		else if (numBoardText.value.length < 18)
		{			
			numBoardText.value += v;																		
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}
</script>
