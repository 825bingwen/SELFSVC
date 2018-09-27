<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.DictItemPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
	// �ն˻���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
		<script type="text/javascript">
		
		// ��ֹ�ظ��ύ
		var submitFlag = 0;
		
		//8��32��66��77 ����
		//82��220 ����
		//13��89��221 ȷ��
		//80 ��ӡ
		//85 ��һҳ
		//68 ��һҳ
		
		/*
		 *��ȥ���������ߵĿո�
		 */
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
			
			//�Ժ�������ж�
			var pattern = /^\d{11}$/;
			
			var pattern1 = /^\d{6}$/;
			
			//getValue()��getObj()Ϊscript.js��װ�ķ���
			//����ʱ�Զ������Զ�ת��
			if (key == 8 || key == 32 || key == 66 || key ==77) 
			{
				//����ʱ��numBoardText2������ϣ��Զ���ת��numBoardText1
				if(pattern.test(trim(getValue("numBoardText1"))) && getValue("numBoardText3") == "")
				{
					getObj("numBoardText1").focus();
					changObj(getObj("numBoardText1"), 1);
					
					return true;
				}
				
				//����ʱ��numBoardText2������ϣ��Զ���ת��numBoardText1
				if(pattern1.test(trim(getValue("numBoardText3"))) && getValue("numBoardText2") == "")
				{
					getObj("numBoardText3").focus();
					changObj(getObj("numBoardText3"), 3);
					
					return true;
				}
				
			}
			
			//�������ʱ�Զ�������ת������һ��
			if (pattern.test(trim(getValue("numBoardText1"))) 
					&& getValue("numBoardText3") == "")
			{
				//numBoardText1������ϣ��Զ���ת��numBoardText2
				getObj("numBoardText3").focus();
				changObj(getObj("numBoardText3"), 3);
				
				return true;
			}
			
			if (pattern1.test(trim(getValue("numBoardText3"))) 
					&& getValue("numBoardText2") == "")
			{
				//numBoardText2������ϣ��Զ���ת��numBoardText3
				getObj("numBoardText2").focus();
				changObj(getObj("numBoardText2"), 2);
				
				return true;
			}			
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		//�ύ
		function doSub()
		{
			//�Ժ�������ж�
			var pattern = /^\d{11}$/;
			var authPassword = /^\d{6}$/;
			
			var telNumber = getValue("numBoardText1");
			
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(getObj('numBoardText1'), 1);
				alertRedErrorMsg("��������ȷ���ֻ�����");
				
				return;
			}
			
			var confirmTelNumber = getValue("numBoardText2");
			
			if (confirmTelNumber.length < 15)
			{
				changObj(getObj('numBoardText2'), 2);
				alertRedErrorMsg("���п����Ų��ܵ���15λ");
				
				return;
			}
			
			var password = getValue("numBoardText3");
			
			if("" == password || !authPassword.test(password))
			{
				changObj(getObj('numBoardText2'), 2);
				alertRedErrorMsg("��������λ��������");
				
				return;
			}
			
			if (submitFlag == 0) 
			{
				submitFlag = 1;	//�ύ���
				
				openRecWaitLoading();
				document.actform.action = "${sessionScope.basePath}bindBankCard/loginWithEasyPay.action";
				document.actform.submit();
			}	
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
		
		// ����ȷ�Ͽ�
		function openRealNameConfirm(content, noMsg)
		{
			chooseNoAlertMsg = noMsg;
			document.getElementById('printPromptId').innerHTML = content;
			wiWindow = new OpenWindow("printCheck_confirm",708,392);
		}
		
		// ��ʾ��Ϣѡ��"��"
		function chooseNo()
		{
			wiWindow.close();
			setException(chooseNoAlertMsg);
		}
			
		</script>
	</head>
	<body scroll="no" onload="getObj('numBoardText1').focus();">
		<form name="actform" method="post">	
			<%-- ������Ϣ --%>
			<input type="hidden" id="errormessage" name="errormessage" value='' />
					
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
          							<span class="pl20 fl lh75">�ֻ����룺</span><%--15827814444 15871503875--%>
            						<input type="text" maxlength="11" id="numBoardText1" name="servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus="" value=""/>
          						</li>
         						<li class="fs20 clearfix" id="phone_password">
          							<i class="lh30">2. �������������</i>
          							<span class="pl20 fl lh75">�������룺</span>
            						<input type="password" maxlength="6" id="numBoardText3" name="password" class="text1 fl relative" onclick="changObj(this, 3);MoveLast(this);" onfocus="" value=""/>
         						</li>  
         						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">3. ������󶨵����п���</i>
          							<span class="pl20 fl lh75">���п��ţ�</span>
            						<input type="text" maxlength="20" style="font-size:20px;" name="cardNo" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus="" value=""/>
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
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = getObj('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = getObj('numBoardText1');
								var type = 1;
								
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
										
										// �����п��Ž�����֤
										var pattern1 = /^\d{6}$/;
										
							  			if (pattern.test(trim(lastObj.value))
							  					&& getValue("numBoardText3") == "")
										{
											//numBoardText1������ϣ��Զ���ת��numBoardText2
											getObj("numBoardText3").focus();
											
											changObj(getObj("numBoardText3"), 3);
											
											return true;
										}
										
										if (pattern1.test(trim(getValue("numBoardText3")))
							  					&& getValue("numBoardText2") == "")
										{
											//numBoardText1������ϣ��Զ���ת��numBoardText2
											getObj("numBoardText2").focus();
											
											changObj(getObj("numBoardText2"), 2);
											
											return true;
										}		
									}					
								}
						
								//�ı佹��������������ʽ
								function changObj(o, t)
								{
									getObj("errorMsg").innerHTML = "";
									
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										getObj('phone_input_1').className = "on fs20 clearfix";
										getObj('phone_input_2').className = "fs20 clearfix";
										getObj('phone_password').className = "fs20 clearfix";
									}

									if (t == 2)									
									{
										type = 2;
										getObj('phone_input_1').className = "fs20 clearfix";
										getObj('phone_input_2').className = "on fs20 clearfix";
										getObj('phone_password').className = "fs20 clearfix";
									}
									
									if (t == 3)
									{
										type = 0;
										getObj('phone_input_1').className = "fs20 clearfix";
										getObj('phone_input_2').className = "fs20 clearfix";
										getObj('phone_password').className = "on fs20 clearfix";
									}
									
									//������õ����
									MoveLast();
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
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
									{								
										lastObj.value += v;							
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 0)
									{								
										lastObj.value += v;								
									}
									else if (lastObj.value.length < 20 && !isNaN(v) && type == 2)
									{
										lastObj.value += v;
									}
									
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
								
								alertRedErrorMsg = function(content)
								{
									getObj('winText_ErrorMsg').innerHTML = content;
									wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
								};
								
								if("" != "<%=errorMsg %>")
								{
									alertRedErrorMsg("<%=errorMsg %>");
								}
		
	              			</script>
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
