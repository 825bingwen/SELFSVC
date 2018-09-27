<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
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
			
			//�Գ�ֵ����������ж�
			var cardpattern = /^\d{18}$/;
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//����ʱ��numBoardText2������ϣ��Զ���ת��numBoardText1
				document.getElementById("numBoardText1").focus();
				changObj(document.getElementById("numBoardText1"), 1);
				return true;
			}
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText2").value)) 
					&& document.getElementById("numBoardText3").value == "")
			{
				//����ʱ��numBoardText3������ϣ��Զ���ת��numBoardText2
				document.getElementById("numBoardText2").focus();
				changObj(document.getElementById("numBoardText2"), 2);	
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
			
			var telNumber = document.getElementById("numBoardText1").value;
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			
			if (pattern.test(trim(document.getElementById("numBoardText2").value)) 
					&& document.getElementById("numBoardText3").value == "")
			{
				if(confirmTelNumber == telNumber)
				{
					//numBoardText2������ϣ��Զ���ת��numBoardText3
					document.getElementById("numBoardText3").focus();
					changObj(document.getElementById("numBoardText3"), 3);
					return true;
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "����������ֻ����������ͬ";
					return true;
				}
			}	
		}
		function MoveLast(e) 
		{
			var etarget = getEventTarget(e);
			var r = etarget.createTextRange();
			r.moveStart("character", etarget.value.length);
			r.collapse(true);
			r.select();
		}

		function doSub()
		{
			 
			//�Ժ�������ж�
			var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				document.getElementById("errorMsg").innerHTML = "������11λ�ֻ�����";
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				document.getElementById("numBoardText2").focus();
				if(confirmTelNumber!="")
				{
					document.getElementById("errorMsg").innerHTML = "����������ֻ����������ͬ";
				}
				return;
			}
			
			if (lastObj != document.getElementById('numBoardText3'))
			{
				changObj(document.getElementById('numBoardText3'), 3);
				return;
			}
			//�Գ�ֵ����������ж�
			var cardpattern = /^\d{18}$/;
			var cardPwd = document.getElementById("numBoardText3").value;
			if (cardPwd == "" || !cardpattern.test(cardPwd))
			{
				changObj(document.getElementById('numBoardText3'), 3);
				document.getElementById("numBoardText3").focus();
				document.getElementById("errorMsg").innerHTML = "������18λ��ֵ������";
				return;
			}
			
			var confirmMsg = document.getElementById("confirmMsg");  
			confirmMsg.innerHTML="������ĳ�ֵ������Ϊ:<font color='yellow'>" + cardPwd + "</font>";
			openWindow('popup_confirm');	
			
		}
		
		function cardPayCommit()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading("recWaitLoading");
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}freecharge/cardPayCommit.action";
				document.actform.submit();
			}	
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		
		openWindow = function(id)
		{
			wiWindow = new OpenWindow("popup_confirm",708,392);//�򿪵�����������
		}	
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
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
          							<span id="redstar1" class="pl20 fl lh75">&nbsp&nbsp�ֻ����룺</span>
            						<input type="text" maxlength="11" id="numBoardText1" name="telnum" class="text1 fl relative" onclick="changObj(this, 1);" onfocus="MoveLast(event);"/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. �ٴ������ֻ�����</i>
          							<span id="redstar2" class="pl20 fl lh75">&nbsp&nbsp�ٴ����룺</span>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);" onfocus="MoveLast(event);"/>
         						</li>     
         							<li class="fs20 clearfix" id="phone_input_3">
          							<i class="lh30">3. �����ֵ������</i>
          							<span id="redstar3" class="pl20 fl lh75">��ֵ�����룺</span>
            						<input type="text" maxlength="18" id="numBoardText3"  name="cardPwd"  style="font-size:22px;" class="text1 fl relative" onclick="changObj(this, 3);" onfocus="MoveLast(event);"/>
         						</li>          
        					</ul>
        					
        					<!--С����-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a title="1" href="javascript:void(0)">1</a><a title="2" href="javascript:void(0)">2</a><a title="3" href="javascript:void(0)">3</a> <a title="�˸�" href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a title="4" href="javascript:void(0)">4</a><a title="5" href="javascript:void(0)">5</a><a title="6" href="javascript:void(0)">6</a> <a title="���" href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a> <a title="x" href="javascript:void(0)">x</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a title="ȷ��" href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				
	        				<script type="text/javascript">	
	        					<%
									if("1".equals(redStarKey))
									{
								%>
									var textContent1 = document.getElementById('redstar1').innerHTML;
									document.getElementById('redstar1').innerHTML=textContent1 + '<font color="red">*</font>';
									
									var textContent2 = document.getElementById('redstar2').innerHTML;
									document.getElementById('redstar2').innerHTML=textContent2 + '<font color="red">*</font>';
									
									var textContent3 = document.getElementById('redstar3').innerHTML;
									document.getElementById('redstar3').innerHTML=textContent3 + '<font color="red">*</font>';
								<%
									}
								%>	
								
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
								var type = 1;
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		
						    		if (!numBoardBtns[i].className) 
						    		{	
						    			numBoardBtns[i].className='';
						    		}
						    		
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
										
							  			//�ֻ�����������ɺ��Զ���ת���ٴ������ֻ�����
										if (pattern.test(trim(lastObj.value)) && type == 1
												&& document.getElementById('numBoardText2').value == "")
										{
											document.getElementById('numBoardText2').focus();
											changObj(document.getElementById("numBoardText2"), 2);
											return true;
										}
										
										//�ٴ������ֻ�������ɺ��Զ���ת����ֵ�����������
										if (pattern.test(trim(lastObj.value)) && type == 2
												&& document.getElementById('numBoardText3').value == "")
										{
											changObj(document.getElementById('numBoardText3'), 3);
											document.getElementById('numBoardText3').focus();
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
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if(t==2)
									{
										type = 2;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else
									{
										type = 3;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 11 && !isNaN(v)&& type == 1)
									{								
										lastObj.value += v;									
									}
									else if (lastObj.value.length < 11 && !isNaN(v)&& type == 2)
									{								
										lastObj.value += v;									
									}
									else if (lastObj.value.length < 18 && !isNaN(v)&& type == 3)
									{								
										lastObj.value += v;									
									}
									
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
								}
	              			</script>
	        				<!--С����end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
			<div class="popup_confirm" id="popup_confirm">
                 <div class="bg"></div>
                 <div class="tips_title">��ʾ��</div>
                 <div class="tips_body">
			     <p id="confirmMsg"></p>
			     <div class="blank60"></div>
			     </div>
				 <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a title="ȷ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();cardPayCommit();">ȷ��</a></span>
	                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span>
                 </div>
            </div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
