<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null) {
		errorMsg = "";
	}
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	
	
	String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
	
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>��������������������</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script language="JavaScript" type="text/javascript">

//��ֹҳ���ظ��ύ
var submitFlag = 0;

//��������ύ
function doSub()
{
	//�ж���������Ƿ�Ϊ��
	var randomPwd = trim(document.actform.randomPwd.value);
	if(randomPwd == "" || pangu_getStrLen(randomPwd) != 6)
	{
		document.actform.randomPwd.focus();
		return ;
	}
	
	if(type == 1)
	{
		changObj(document.getElementById('newPasswd'), 2);
		document.actform.newPasswd.focus() ;
		return;
	}
	
	//�ж��������Ƿ�Ϊ��
	var password = trim(document.actform.newPasswd.value);
	if(password == "" || pangu_getStrLen(password) != 6)
	{
	    document.actform.newPasswd.vlaue = "" ;
		document.actform.newPasswd.focus();
		return ;
	}
	
	else if (type == 2)
	{
		changObj(document.getElementById('confirmPwd'), 3);
		document.actform.confirmPwd.focus() ;
		return;
	}
	
	//�ж�ȷ�������Ƿ�Ϊ��
	var confirmPwd = trim(document.actform.confirmPwd.value);
	if(confirmPwd == "" || pangu_getStrLen(confirmPwd) != 6)
	{
		document.actform.confirmPwd.focus();
		return ;
	}
	
	//�ж�ȷ��������������Ƿ�һ��
	if (password != confirmPwd)
	{
	    if ("1" == "<%=popupFlag %>")
		{
			alertRedErrorMsg("�������ȷ�����벻һ�£����������룡");
		}
		else
		{
			document.getElementById("errorMsg").innerHTML = "�������ȷ�����벻һ�£����������룡";
		}
	    
		document.actform.confirmPwd.value = "" ;
		document.actform.confirmPwd.focus() ;
		return ;
	}
	// add begin cKF76106 20130207 NG3.3&3.5 �淶���϶Ȳ�����Ŀ�����������ⱨ��-��ʾ����������벻��Ϊ������
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		if(riskPassword(password))
		{
			if ("1" == "<%=popupFlag %>")
			{
				alertRedErrorMsg("������������밲ȫ�Եͣ����������ã�");
			}
			else
			{
				document.getElementById("errorMsg").innerHTML = "������������밲ȫ�Եͣ����������ã�";
			}
			
			document.actform.confirmPwd.value = "" ;
			document.actform.newPasswd.value = "" ;
			changObj(document.getElementById("newPasswd"), 2);
			document.getElementById('newPasswd').focus();
			return ;
		}
	<%
	}
	%>
	// add end cKF76106 20130207 NG3.3&3.5 �淶���϶Ȳ�����Ŀ�����������ⱨ��-��ʾ����������벻��Ϊ������
	else
	{
		// add begin hWX5316476 2014-2-20 OR_NX_201402_306 ���������ն��Ż�����_������������� 
		<%
		if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
		{
		%>
			document.actform.action = "${sessionScope.basePath}baseService/resetPasswordNewNX.action";
		<%
		}
		%>
		// add end hWX5316476 2014-2-20 OR_NX_201402_306 ���������ն��Ż�����_������������� 
		
	    openRecWaitLoading_NX('recWaitLoading');
	    document.actform.submit();
	}
}

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardDown(e)
{
    var key=GetKeyCode(e);
	if(key == 77) 
	{
	   preventEvent(e);
	}  
	if(!KeyIsNumber(key))
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

function pwdKeyboardUp(e)
{
	var key=GetKeyCode(e);
	//ȷ��
	if (key == 13 || key == 89 || key == 221)
	{
		doSub();	
		return ;
	}
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>		
		// �˳�
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			return;			
		}
		// ���
		else if (key == 77)
		{
			changValue(-2);
			return;
		}
		//����
		if(key == 8 || key == 32 || key == 66) 
		{
			var etarget = getEventTarget(e);
			if(etarget.type == "text" || etarget.type == "password") 
			{
				etarget.value=backString(etarget.value);
			}
			if (etarget.name == 'confirmPwd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('newPasswd'));
				changObj(document.getElementById("newPasswd"), 2);
			}
			if (etarget.name == 'newPasswd' && etarget.value == '' )
			{
				MoveLast(document.getElementById('randomPwd'));
				changObj(document.getElementById("randomPwd"), 1);
			}
		}
		
		//��������Ϊ6λ����
		var pattern = /^\d{6}$/;
		
		//����ʱ��ȷ������Ϊ��ת��������
		if ((key == 8 || key == 32 || key == 66 )
			&& pattern.test(trim(document.getElementById("newPasswd").value))
			&& document.getElementById("confirmPwd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//����ʱ��������Ϊ��ʱת���������
		if ((key == 8 || key == 32 || key == 66 )
			&& pattern.test(trim(document.getElementById("randomPwd").value))
			&& document.getElementById("newPasswd").value == "")
		{
			document.getElementById('randomPwd').focus();
			changObj(document.getElementById("randomPwd"), 1);
			return true;
		}
		
		//�������������ɺ��Զ���ת�������������
		if(pattern.test(trim(document.getElementById("randomPwd").value)) && document.getElementById("newPasswd").value == "")
		{
			document.getElementById('newPasswd').focus();
			changObj(document.getElementById("newPasswd"), 2);
			return true;
		}
		
		//������������ɺ��Զ���ת��ȷ�����������
		if(pattern.test(trim(document.getElementById("newPasswd").value)) && document.getElementById("confirmPwd").value == "")
		{
			changObj(document.getElementById('confirmPwd'), 3);
			document.getElementById('confirmPwd').focus();
			return true;
		}	
	<%
	}
	else
	{
	%>
	//����
	if ( key == 82 || key == 220 )
	{
	    goback("<s:property value='curMenuId' />");
	    return ;
	}
	//����
	if(key == 8 || key == 32 || key == 66 || key ==77) 
	{
		var etarget = getEventTarget(e);
		if(etarget.type == "text" || etarget.type == "password") 
		{
			etarget.value=backString(etarget.value);
		}
		if (etarget.name == 'confirmPwd' && etarget.value == '' )
		{
			MoveLast(document.getElementById('newPasswd'));
			changObj(document.getElementById("newPasswd"), 2);
		}
		if (etarget.name == 'newPasswd' && etarget.value == '' )
		{
			MoveLast(document.getElementById('randomPwd'));
			changObj(document.getElementById("randomPwd"), 1);
		}
	}
	
	//��������Ϊ6λ����
	var pattern = /^\d{6}$/;
	
	//����ʱ��ȷ������Ϊ��ת��������
	if ((key == 8 || key == 32 || key == 66 || key ==77)
		&& pattern.test(trim(document.getElementById("newPasswd").value))
		&& document.getElementById("confirmPwd").value == "")
	{
		document.getElementById('newPasswd').focus();
		changObj(document.getElementById("newPasswd"), 2);
		return true;
	}
	
	//����ʱ��������Ϊ��ʱת���������
	if ((key == 8 || key == 32 || key == 66 || key ==77)
		&& pattern.test(trim(document.getElementById("randomPwd").value))
		&& document.getElementById("newPasswd").value == "")
	{
		document.getElementById('randomPwd').focus();
		changObj(document.getElementById("randomPwd"), 1);
		return true;
	}
	
	//�������������ɺ��Զ���ת�������������
	if(pattern.test(trim(document.getElementById("randomPwd").value)) && document.getElementById("newPasswd").value == "")
	{
		document.getElementById('newPasswd').focus();
		changObj(document.getElementById("newPasswd"), 2);
		return true;
	}
	
	//������������ɺ��Զ���ת��ȷ�����������
	if(pattern.test(trim(document.getElementById("newPasswd").value)) && document.getElementById("confirmPwd").value == "")
	{
		changObj(document.getElementById('confirmPwd'), 3);
		document.getElementById('confirmPwd').focus();
		return true;
	}
	<%
	}
	%>
	
}
      
function trim(str) 
{
   while (str.charAt(str.length - 1)==" ") 
   {
     str = str.substring(0, str.length - 1);
   }
   while (str.charAt(0)==" ") 
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
           count = count + 2;                
       else                                  
           count = count + 1;                
   }                                         
   return count;                             
}
	 
function MoveLast(lastObj)
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
				
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
	}
}

/*
 * ��������Ƿ��Σ����.
 */
function riskPassword(value)
{
  var reg = /^(123456|654321|111111|222222|333333|444444|555555|666666|777777|888888|999999|000000)$/;
  return reg.test(value);    
}
</script>
	</head>

	<body scroll="no" onload="document.actform.randomPwd.focus();">
		<form name="actform" method="post"
			action="${sessionScope.basePath }baseService/resetPassword.action">
			<!-- add begin cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746 -->
			<input type="hidden" id="initPwdLoginFlag" name="initPwdLoginFlag" value="<s:property value='initPwdLoginFlag'/>"/>
			<!-- add end cKF76106 2012/12/14 V200R003C12L12 OR_NX_201211_746 -->
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">


				<%@ include file="/customer.jsp"%>

				<div class="b966">
					<div class="blank30" id="errorMsg"></div>
					<div class=" p40">
						<p class="fs22 mb30"></p>
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.�����������</i>
									<span id="redstar1" class="pl20 fl lh75">������룺</span>
									<input type="password" id="randomPwd" name="randomPwd"
										maxlength="6" class="text1 fl relative"
										onclick="MoveLast(this);changObj(this, 1);" onfocus="MoveLast(this);" />
								</li>
								<li class="fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. ����������</i>
									<span id="redstar2" class="pl20 fl lh75">&nbsp;&nbsp;&nbsp;�����룺</span>
									<input type="password" id="newPasswd" name="newPasswd"
										maxlength="6" class="text1 fl relative"
										onclick="MoveLast(this);changObj(this, 2);" onfocus="MoveLast(this);" />
								</li>
								<li class="fs20 clearfix" id="phone_input_3">
									<i class="lh30">3. ����ȷ������</i>
									<span id="redstar3" class="pl20 fl lh75">ȷ�����룺</span>
									<input type="password" id="confirmPwd" name="confirmPwd"
										maxlength="6" class="text1 fl relative"
										onclick="MoveLast(this);changObj(this, 3);" onfocus="MoveLast(this);" />
								</li>
							</ul>

							<!--С����-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class=" numbox">
									<div class="blank10"></div>
									<a title="1" href="javascript:void(0)">1</a><a title="2" href="javascript:void(0)">2</a><a
										 title="3" href="javascript:void(0)">3</a>
									<a title="�˸�" href="javascript:void(0)" class="func1" name="functionkey"
										id="numBoardBack" onmousedown="this.className='func1on'"
										onmouseup="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a title="4" href="javascript:void(0)">4</a><a title="5" href="javascript:void(0)">5</a><a
										 title="6" href="javascript:void(0)">6</a>
									<a title="���" href="javascript:void(0)" class="func2" name="functionkey"
										id="numBoardClear" onmousedown="this.className='func2on'"
										onmouseup="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a
											 title="9" href="javascript:void(0)">9</a>
										<a title="x" href="javascript:void(0)">x</a><a title="0" href="javascript:void(0)">0</a><a
											title="#" href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a title="ȷ��" href="javascript:void(0)"
											onclick="doSub();return false;"
											class="func3" name="functionkey" id="numBoardEnter"
											onmousedown="this.className='func3on'"
											onmouseup="this.className='func3'">1</a>
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
								var lastObj = document.getElementById('randomPwd');
								var type = 1;
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
						 
									numBoardBtns[i].onmousedown = function()
									{
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function()
									{
										changValue(0, this.innerHTML);
										
							  			this.className = '';	
							  			
							  			//��������Ϊ6λ����
										var pattern = /^\d{6}$/;
										
							  			//������������ɺ��Զ���ת�������������
										if (pattern.test(trim(lastObj.value)) && type == 1
												&& document.getElementById('newPasswd').value == "")
										{
											document.getElementById('newPasswd').focus();
											changObj(document.getElementById("newPasswd"), 2);
											return true;
										}
										
										//������������ɺ��Զ���ת��ȷ�����������
										if (pattern.test(trim(lastObj.value)) && type == 2
												&& document.getElementById('confirmPwd').value == "")
										{
											changObj(document.getElementById('confirmPwd'), 3);
											document.getElementById('confirmPwd').focus();
											return true;
										}  
							   
									}					
								}
						
								function changObj(o, t)
								{
									lastObj = o;
									
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if(t == 2)
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
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 1)
									{								
										lastObj.value += v;								
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 2)
									{								
										lastObj.value += v;								
									}
									else if (lastObj.value.length < 6 && !isNaN(v) && type == 3)
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
			</div>
		</form>
	</body>
	<script type="text/javascript">
		if ("" != "<%=errorMsg %>")
		{			
			if ("1" == "<%=popupFlag %>")
			{
				alertRedErrorMsg("<%=errorMsg %>");
			}
			else
			{
				document.getElementById("errorMsg").innerHTML = "<%=errorMsg %>";
			}
		}
	</script>
</html>