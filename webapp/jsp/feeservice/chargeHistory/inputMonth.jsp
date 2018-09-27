<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
%>
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
			
			// ���ģ����
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
			// ���ģ��˳�
			else if (key == 82 || key == 220) 
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
				return;
			}
			// ���ģ����
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
				if (etarget.name == 'endDate' && etarget.value == '' )
				{
					moveLast(document.getElementById('startDate'));
				}
			}
			
			//�Ժ�������ж�
			var pattern = /^\d{8}$/;
			
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
				
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}

		function doSub()
		{
			//�Ժ�������ж�
			var pattern = /^\d{8}$/;
			
			var startDate = document.getElementById("numBoardText1").value;
			var endDate = document.getElementById("numBoardText2").value;
			if (startDate == "" || !pattern.test(startDate))
			{
				changObj(document.getElementById('numBoardText1'), 1);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("��������ȷ�Ŀ�ʼ����");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "��������ȷ�Ŀ�ʼ����";
				<%
				}
				%>
				
				return;
			}
			
			if (endDate == "" || !pattern.test(endDate))
			{
				changObj(document.getElementById('numBoardText2'), 1);
				
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("��������ȷ�Ľ�������");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "��������ȷ�Ľ�������";
				<%
				}
				%>
				
				return;
			}
			
			if (endDate < startDate)
			{
				changObj(document.getElementById('numBoardText2'), 2);
                
				<%
				if ("1".equals(popupFlag))
				{
				%>
					alertRedErrorMsg("��ʼ����ҪС�ڽ�������");
				<%
				}
				else
				{
				%>
					document.getElementById("errorMsg").innerHTML = "��ʼ����ҪС�ڽ�������";
				<%
				}
				%>
				
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openRecWaitLoading_NX();
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }feeservice/qryChargeHistoryRecords.action";
				document.actform.submit();
			}	
		}
		
		function moveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}	
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="blank50">
				<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) 
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">�����˵��굥��ѯ</a>
                <%
                	}
                	else
                	{
                %>
                <%-- ���ı�Ӧ��ʹ�ð���0�������ʵ��굥��ѯ�����Ǵ�ҳ��������ҳ�棬0�ǿ���������������֡����Դ˴���֧��ʹ�ð���0�������ʵ��굥��ѯ --%>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">����<%=parentMenuName %>&nbsp;</a>
                <%
                	}
                %>
				</div>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.�����뿪ʼ����(��ʽ��20110101)</i>
          							<span id="redstar1" class="pl20 fl lh75">��ʼ���ڣ�</span>
            						<input type="text" maxlength="8" id="numBoardText1" name="startDate" class="text1 fl relative" onclick="moveLast(this);changObj(this, 1);" onfocus="moveLast(this);" />
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2.�������������(��ʽ��20110101)</i>
          							<span id="redstar2" class="pl20 fl lh75">�������ڣ�</span>
            						<input type="text" maxlength="8" id="numBoardText2" name="endDate" class="text1 fl relative" onclick="moveLast(this);changObj(this, 2);" onfocus="moveLast(this);" />
         						</li>
         						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>��ܰ��ʾ��</p>
         							<p class="p10">1. ����������Ϻ��밴"ȷ��"����</p>
         							<p class="p10">2. �����޸ģ��밴"���"����</p>
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
						    		
					     			if (numBoardBtns[i].name == 'functionkey')
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
										var pattern = /^\d{8}$/;
										
										if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
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
									lastObj.focus();
									lastObj.select();
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);

									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 8 && !isNaN(v))
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
	</body>
</html>
