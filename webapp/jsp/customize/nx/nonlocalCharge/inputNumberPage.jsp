<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

//������ʾ������־
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);

// �ֽ𽻷Ѳ����Ƿ����ն˻��ϼ�¼��ϸ��־��1���ǣ�0�����ǡ�
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
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
          							<span id="redstar1" class="pl20 fl lh75">�ֻ����룺</span>
            						<input type="text" maxlength="11" id="numBoardText1" name="chargeLogVO.servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(event);" onfocus="MoveLast(event);"/>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2. �ٴ������ֻ�����</i>
          							<span id="redstar2" class="pl20 fl lh75">�ٴ����룺</span>
            						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(event);" onfocus="MoveLast(event);"/>
         						</li>
								
         						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>��ܰ��ʾ��</p>
         							<p class="p10">1. �ֻ�����������Ϻ��밴"ȷ��"�����н��ѣ�</p>
         							<p class="p10">2. �����޸ģ��밴"���"����</p>
         						</li> 
								
        					</ul>
        					
        					<!--С����-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class=" numbox">
	            					<div class="blank10"></div>
	            					<a href="javascript:play();">1</a><a href="javascript:play()">2</a><a href="javascript:play()">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
	            					<div class="clear"></div>
	            					<a href="javascript:play()">4</a><a href="javascript:play()">5</a><a href="javascript:play()">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
	            					<div class="clear"></div>
	            					<div class="nleft"> 
	            						<a href="javascript:play()">7</a><a href="javascript:play()">8</a><a href="javascript:play()">9</a> <a href="javascript:void(0)">x</a><a href="javascript:play()">0</a><a href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
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
								<%
									}
								%>
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
						
									numBoardBtns[i].onmousedown = function()
									{
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
	              			</script>
	        				<!--С����end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
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
			else if (key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}
			//�˳���82
			else if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}
			//�����77
			else if(key == 77)
			{
				changValue(-2);
				return;
			}	
			//����
			else if (key == 8 || key == 32 || key == 66 )
			{
				var etarget = getEventTarget(e);
				if (etarget.type == "text" || etarget.type == "password") 
				{
					etarget.value = backString(etarget.value);
				}
			}
			
			//�Ժ�������ж�
			var pattern = /^\d{11}$/;
			
			if ((key == 8 || key == 32 || key == 66) 
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
				
				// ������ʾ
				document.getElementById("player03").play();
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}
			
		// ȷ���ύ
		function doSub()
		{
			// �Ժ�������ж�
			var telNumber = document.getElementById("numBoardText1").value;
			if (telNumber == "")
			{
				changObj(document.getElementById('numBoardText1'), 1);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("��������ȷ���ֻ�����");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "��������ȷ���ֻ�����";
				<%
				}
				%>
				return;
			}
			
			var confirmTelNumber = document.getElementById("numBoardText2").value;
			if (confirmTelNumber != telNumber)
			{
				changObj(document.getElementById('numBoardText2'), 2);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("����������ֻ����������ͬ");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "����������ֻ����������ͬ";
				<%
				}
				%>
				
				return;
			}
			
			// �����û�������ֻ����Ƿ��ں�������(����)
			if(checkBlackList(telNumber))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("�Բ����������������ն˰���ҵ��");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "�Բ����������������ն˰���ҵ��";
				}
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=chargeLogDetail %>",telNumber + "ѡ����س�ֵ����");
						
				openRecWaitLoading_NX();
				document.actform.target = "_self";
				
				// �ͻ�Ӧ�ɷ����ܶ��ѯ
				document.actform.action = "${sessionScope.basePath}nonlocalCharge/qryPayAmount.action";
				document.actform.submit();
			}	
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
				
				writeDetailLog("<%=chargeLogDetail %>",document.getElementById("numBoardText1").value + "�˳���س�ֵ���ѹ���");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}	
		
		function play()
		{
			if (document.getElementById('numBoardText1').value.length == 11
			 	&& document.getElementById('numBoardText2').value.length == 0)
			{
				// ������ʾ
				document.getElementById("player03").play();
			}
		}
		</script>
	</body>
</html>