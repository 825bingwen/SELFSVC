<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>NG2.3�����ն�ϵͳ���������֤</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="document.getElementById('randomPwd').focus();">
		<form id="actform" name="actform" method="post">
			<!-- ǩԼ���� -->
			<input type="hidden" id="heBaoCommitFlag" name="heBaoCommitFlag" value="<s:property value='heBaoCommitFlag' />"/>
			<!-- ��Ƭ���� 0:��ǿ� 1:���ǿ� -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="<s:property value='bindBankCardPO.bankCardType' />"/>
			<!-- ���� -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- ֤���������� -->
			<input type="hidden" id="idCardTypeText" name="bindBankCardPO.idCardTypeText" value="<s:property value='bindBankCardPO.idCardTypeText' />"/>
		    <!-- ���֤���� -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />"/>
			<!-- ��ǿ��Ż����ÿ��� -->
			<input type="hidden" id="bankCardNum" name="bindBankCardPO.bankCardNum" value="<s:property value='bindBankCardPO.bankCardNum' />"/>
			<!-- ���ÿ���Ч��-�� -->
			<input type="hidden" id="expire1" name="expire1" value="<s:property value='expire1' />"/>
			<!-- ���ÿ���Ч��-�� -->
			<input type="hidden" id="expire2" name="expire2" value="<s:property value='expire2' />"/>
			<!-- ���ÿ�CVN2 -->
			<input type="hidden" id="cvn2" name="bindBankCardPO.cvn2" value="<s:property value='bindBankCardPO.cvn2' />"/>
			<!-- ������������������ -->
			<input type="hidden" id="randomPwdErrTimes" name="randomPwdErrTimes" value="<s:property value='randomPwdErrTimes' />"/>
			<!-- ���д��� -->
			<input type="hidden" id="bankId" name="bindBankCardPO.bankAbbr" value="<s:property value='bindBankCardPO.bankAbbr' />" />
			<!-- ֤�����ͣ��Ͱ��׳�ֵʹ�ã� -->
			<input type="hidden" id="idCardType" name="bindBankCardPO.idCardType" value="00"/>
			<!-- ���֧��Э��� -->
			<s:hidden id="agrNo" name="bindBankCardPO.AGRNO" />
			<!-- ������ˮ�� -->
			<s:hidden id="tradeNo" name="bindBankCardPO.appFlowCode" />
			<!-- ������ֵ -->
			<s:hidden id="trigamt" name="bankCardInfoPO.trigamt" value="10" />
			<!-- �Զ����۽�� -->
			<s:hidden id="drawamt" name="bankCardInfoPO.drawamt" value="10" />
			<!-- ���б��� -->
			<s:hidden id="bankId" name="bankCardInfoPO.bankId" />
			<!-- ��ȡ��¼ҳ����������п����� -->
			<input type="hidden" id="cardNo" name="cardNo" value="<s:property value='cardNo' />" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="pt20 pl31">
						<!-- �б����� -->
                		<p class="tit_info" align="left"><span class="bg"></span>�׳�ֵǩԼ</p>
					</div>
					
					<div class="blank15"></div>
					
    				<div class=" pl62">
      					
      					<!--����+�����+��ܰ��ʾ-->
      					<div class="keyboard_wrap authentication authentication_2n clearfix">
      						<ul class="phone_num_list fl">
          						<s:if test="%{#request.errormessage != '' && #request.errormessage != null}">
          							<li class="fs20 mt10 clearfix" style="height:auto;">
	          							<span class="fs18" id="errorMsg"><s:property value="#request.errormessage"/></span>
	          						</li>
          						</s:if>
          						<s:else>
          							<li class="fs20 mt10 clearfix" style="height:auto;">
	          							<span class="fs18">��ȡ�������룺</span>
	          						</li>
	          						<li class="clearfix pt10 hidden" style="height:auto;">
	          							<p class=" fs18 fl lh30 yellow" >���������ѷ��͵��ֻ�����ע����գ�</p>
	          						</li>
          						</s:else>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;">
          							<div class="blank5"></div>
          							<span class="fs18">�������룺</span>
          							<br />
            						<input type="password" id="randomPwd" name="smsCode" maxlength="6" class="text1 fl relative" 
            							onfocus="MoveLast(event);" value="" />
          						</li>        
       					 	</ul>
       					 	
       					 	<!--С����-->
        					<div class="numboard numboard_big fl numboard_bg_short" id="numBoard">
        						<div class=" numbox">
            						<div class="blank10"></div>
            						<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
            						<div class="clear"></div>
            						<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
            						<div class="clear"></div>
            						<div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> </div>
            						<div class="nright"> <a href="javascript:void(0);" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> </div>
            						<div class="blank10"></div>
          						</div>
        					</div>
        					
							<!--������ ���ڴ��� ���Ժ�-->
							<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
								<div class="bg"></div>
								<p class="mt40">
									<img src="${sessionScope.basePath }images/loading.gif" alt="������..." />
								</p>
								
								<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
								<p class="tips_txt">
									<%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ������Ժ�......") %>
								</p>
								<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
								
								<div class="line"></div>
								<div class="popup_banner"></div>
							</div>
	
							<script type="text/javascript">
								openWindow_wait = function(id)
								{
									wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�������
								}			
						    </script>
							<!--����������-->
        					<!--С����end--> 
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
	var submitFlag = 0;

	//�����ַ����ĳ���
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
	
	// ȥ��ǰ��ո�
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
	
	// ������Ƶ�����������
	function MoveLast(e) 
	{
		var etarget = getEventTarget(e);
		var r = etarget.createTextRange();
		r.moveStart("character", etarget.value.length);
		r.collapse(true);
		r.select();
	}
	
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
	}

	//ȷ��
	function doSub() 
	{
		var randomPwd = document.actform.randomPwd.value;	
		if (trim(randomPwd) == "" || isNaN(randomPwd) || pangu_getStrLen(trim(randomPwd)) != 6) 
		{
			alertRedErrorMsg("��������ȷ�Ķ�������");
			return;
		}
		
		//�Ѿ������ȷ�ϻ򷵻أ��ȴ�Ӧ�𣬲���ִ���κβ���
		if (submitFlag == 0) 
		{
			submitFlag = 1;
			
			openWindow_wait('pls_wait');
			
			if("1" == "<s:property value='heBaoCommitFlag' />")
			{
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/signHeBaoCommit.action";
			}
			else
			{
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/unsignHeBao.action";
			}
			
			document.forms[0].submit();
        }
	}
	
	// ������һҳ
	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = menuid;
			
			if("1" == "<s:property value='heBaoCommitFlag' />")
			{
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/writeBankCardInfo.action";
			}
			else
			{
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/checkLoginUserIsFactUser.action";
			}
			document.forms[0].submit();
		}
	}
	
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var numBoardText = document.getElementById('randomPwd');
	for ( i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
		}
		
		if (numBoardBtns[i].name=='functionkey')
		{
			continue ;
		}  

		numBoardBtns[i].onmousedown = function() {
				this.className = 'on';
		}
		
		numBoardBtns[i].onmouseup = function() {
				changValue(0, this.innerHTML);
				
				this.className = '';						  									   
		}					
	}
	
	function changValue(t, v)
	{
		if (t == -1)
		{
			numBoardText.value = numBoardText.value.slice(0, -1);
		}
		else if(t == -2)
		{
			numBoardText.value = ""
		}
		else if (numBoardText.value.length < 6)
		{								
			numBoardText.value += v;																													
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}
</script>
