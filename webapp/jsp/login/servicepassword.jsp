<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ page import="com.huawei.boss.common.security.RSAUtil"%>

<%
    RSAUtil.generateKeyPair(session, 1024);
    String errorMsg = (String)request.getAttribute("errormessage");
    if (errorMsg == null)
    {
        errorMsg = "";
    }
    
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	    
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
    // ɽ����ѡ��ʽ��¼��ʽ�л���־   1:����ʽ  0������ʽ
    String availableFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_AVAILABLECODE_FLAG);
    String currServnumber = (String)request.getAttribute("servnumber");
    if (null == currServnumber)
    {
        currServnumber = "";
    }
    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
    
    //add begin CKF76106 2012/09/27 R003C12L07n01 OR_HUB_201206_597
    
    // ��Ʒ����
    String typeID = "";
    
    if(null != request.getParameter("typeID"))
    {
    	typeID = (String)request.getParameter("typeID");
    }
    
    // ��Ʒ���ٷ�����ʶ
    String quickPubFlag = "";
    
    if(null != request.getParameter("quickPubFlag"))
    {
    	quickPubFlag = (String)request.getParameter("quickPubFlag");
    }
    
    //add end CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597
    
    //add begin m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
    String authCodeType = (String) request.getParameter("authCodeType");
    
    // ��ѡ��֤��ʽ
    String resultAvaiCode = (String)request.getParameter("resultAvaiCode");
    
    // �û�����֤���ֻ������Զ�����Ҳ����޸�
    NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
    //add end m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
    
    // add begin cKF76106 2013/04/16  R003C13L04n01 OR_HUB_201303_548
    String cdrQryTelnum = (String)session.getAttribute("CDRQRY_TELNUM");
    // add end cKF76106 2013/04/16  R003C13L04n01 OR_HUB_201303_548
    
    //�޸�bug����Ӫ��ҵ����ת����������һҳ��ʾ�հ�ҳ
    //add by sWX219697 2015-2-13 17:17:45 �޸�bug86167
    String isssGoBackFlag = (String)session.getAttribute("isssGoBackFlag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"  src="${sessionScope.basePath }js/BigInt.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath }js/Barrett.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath }js/RSA.js"></script>	
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		
		// modify begin by qWX279398 DTS2015111900633
		// ��ѡ����������ת���������ҳ���־ 
		window.parent.currFlag = "servicePWD";
		// modify end by qWX279398 DTS2015111900633
		
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
				else if (key == 8 || key == 32 || key == 66)
				{
					var etarget = getEventTarget(e);
					if (etarget.type == "text" || etarget.type == "password") 
					{
						etarget.value = backString(etarget.value);
					}
					if (etarget.name == 'password' && etarget.value == '' )
					{
						<%
						if (null == custInformation)
						{
						%>
							MoveLast(document.getElementById('servnumber'));
						<%
						}
						%>
					}
				}
			
				<%
				if (null == custInformation)
				{
				%>
				var tel = document.forms[0].servnumber.value;
				var password = document.forms[0].password.value;		
		
				if ((key == 8 || key == 32 || key == 66)
				 		&& pangu_getStrLen(trim(password)) == 0 && pangu_getStrLen(trim(tel)) == 11) 
				{
					document.forms[0].servnumber.focus();
					
					changObj(document.forms[0].servnumber, 1);
					
					return true;
				}
				
	 			if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(password)) == 0) 
	 			{
	 				document.forms[0].password.focus();
	 				
	 				changObj(document.forms[0].password, 2);
	 				
	 				return true;
	 			}
	 			<%
				}
				%>
	 			
				return true;
			<%
			}
			else
			{
			%>	
				//����
				if (key == 82 || key == 220) 
				{
					goback("");
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
						<%
						if (null == custInformation)
						{
						%>
							MoveLast(document.getElementById('servnumber'));
						<%
						}
						%>
					}
				}
			
				<%
				if (null == custInformation)
				{
				%>
				var tel = document.forms[0].servnumber.value;
				var password = document.forms[0].password.value;		
		
				if ((key == 8 || key == 32 || key == 66 || key ==77)
				 		&& pangu_getStrLen(trim(password)) == 0 && pangu_getStrLen(trim(tel)) == 11)
				{
					document.forms[0].servnumber.focus();
					
					changObj(document.forms[0].servnumber, 1);
					
					return true;
				}
				
	 			if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(password)) == 0) 
	 			{
	 				document.forms[0].password.focus();
	 				
	 				changObj(document.forms[0].password, 2);
	 				
	 				return true;
	 			}
	 			<%
				}
				%>
	 			
				return true;
			<%
			}
			%>
		}		
		
		function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
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

		// ���ķ�ʵ���ƵǼ�ȷ�Ϻ��ύ
		function submitReception()
		{
			if (submitFlag == 0)
			{
				if ("<%=Constants.PROOPERORGID_HUB%>" == "<%=((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))%>")
				{
					encryptPassword("password","<%=(String)session.getAttribute(RSAUtil.PUBLIC_STRING)%>","<%=(String)session.getAttribute(RSAUtil.MODULUS_STRING)%>");
				}
				submitFlag = 1;
				
				openWindow_wait('pls_wait');
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/userLoginWithPwd.action";
				document.actform.submit();
			}
		}		

		function doSub()
		{
			//modify begin zWX176560 2013/08/15 OR_NX_201308_11
			if("<%=Constants.PROOPERORGID_NX%>" == "<%=((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))%>")
			{
				//�Ժ�������ж�
				var telNumber = document.getElementById("servnumber").value;
				if (telNumber == "")
				{
					changObj(document.getElementById('servnumber'), 1);
				
					if ("1" == "<%=popupFlag %>")
					{
						alertRedErrorMsg("��������ȷ���ֻ�����");
					}
					else
					{
						document.getElementById("errorMsg").innerHTML = "��������ȷ���ֻ�����";
					}
					
					return;
				}
			}
			else
			{
				<%--add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
				if ("<%=Constants.PROOPERORGID_HUB%>" == "<%=((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))%>")
				{
					//�Ժ�������ж�
					var pattern = /^\d{11}$/;
				
					var telNumber = document.getElementById("servnumber").value;
					if (telNumber == "" || !pattern.test(telNumber))
					{
						changObj(document.getElementById('servnumber'), 1);
				
						if ("1" == "<%=popupFlag %>")
						{
							alertRedErrorMsg("��������ȷ���ֻ�����");
						}
						else
						{
							document.getElementById("errorMsg").innerHTML = "��������ȷ���ֻ�����";
						}
					
						return;
					}
					
					var randomCode = document.getElementById("randomCodeImage").value;
					if (randomCode.value == "" || pangu_getStrLen(trim(randomCode)) != 4)
					{
						changObj(document.getElementById('randomCodeImage'), 3);

						alertRedErrorMsg("����ȷ������֤��");

						return;
					}

					//��֤ͼƬ��֤��
					var ran = randomCodeSecurity();
			
					if (ran == "0")
					{
						alertRedErrorMsg("ͼƬ��֤���������");
						changeImage();
						return;
					}	
							
				}
				else
				{
				<%--add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
					//�Ժ�������ж�
					var pattern = /^\d{11}$/;
				
					var telNumber = document.getElementById("servnumber").value;
					if (telNumber == "" || !pattern.test(telNumber))
					{
						changObj(document.getElementById('servnumber'), 1);
				
						if ("1" == "<%=popupFlag %>")
						{
							alertRedErrorMsg("��������ȷ���ֻ�����");
						}
						else
						{
							document.getElementById("errorMsg").innerHTML = "��������ȷ���ֻ�����";
						}
					
						return;
					}
				<%--add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
				}
				<%--add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
			}
			
			<%--add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
			//У��ͼƬ��֤��
			function randomCodeSecurity()
			{
				var url = "${sessionScope.basePath}login/randomCodeSecurity.action";
				var params = "randomCodeImage=" + getValue("randomCodeImage");
				var resXml;
				var loader = new net.ContentLoaderSynchro(url, netload = function()
				{
					resXml = this.req.responseText;
				}, null, "POST", params, "application/x-www-form-urlencoded");
			
				return resXml;
			}
			<%--add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
			//modify end zWX176560 2013/08/15 OR_NX_201308_11
			
			//�����û�������ֻ����Ƿ��ں�������(����)
			<%
				if(Constants.PROOPERORGID_NX.equals(pageProvince))
				{
			%>
					if(checkBlackList(telNumber))
					{
						changObj(document.getElementById('servnumber'), 1);
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
			<%
				}
			%>
			var password = document.getElementById("password").value;
			if (password.value == "" || pangu_getStrLen(trim(password)) != 6)
			{
				changObj(document.getElementById('password'), 2);
				
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("����ȷ��������");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "����ȷ�������룡";
				}
				
				return;
			}
			
			// add begin by hWX5316476 2014-2-18  OR_NX_201402_306 ���������ն��Ż�����_�������������
			// ��¼ʱ���Ƿ�����������֤
			<%
			if(Constants.PROOPERORGID_NX.equals(pageProvince))
			{
			%>
				var curMenuId = '<%=request.getParameter("curMenuId")%>';
				var ret = weakPwdCheckLogin(document.getElementById("servnumber").value,password);
				if(ret.split('^')[0] == '2')
				{
					if("recChangePwd" == curMenuId )
					{
						alertRedErrorMsg("�𾴵��û����ã����ķ���������ڼ򵥣��������޸ģ�ֻ�������ã�");
					}
					else
					{
						openWeakPwdConfirm(ret.split('^')[1]);
					}
					return;
				}
				else if(ret.split('^')[0] == '0')
				{
					alertRedErrorMsg(ret.split('^')[1]);
					return;
				}
				toRealNameCheck();
			<%
			}
			else
			{
			%>
				submitReception();
			<%
			}
			%>

		}
		
		// ��¼ʱ��У���Ƿ�����������֤
		function weakPwdCheckLogin(telNumber,password)
		{
			var url="${sessionScop.basePath}login/weakPwdCheckLogin.action";
			var param="servnumber="+telNumber+"&password="+password;
			var data = "";
			var contentLoader=new net.ContentLoaderSynchro(url,function(){
				data=this.req.responseText;
			},null,"POST",param,"application/x-www-form-urlencoded");
			return data;
			
		}
		
		//����������ȷ�Ͽ�
		openWeakPwdConfirm = function(content)
		{
			document.getElementById('weakPwdPromptId').innerHTML = content;
			wiWindow = new OpenWindow("weakPwdCheck_confirm",708,392);//�򿪵�����������
		}
		
		// add end  by hWX5316476  OR_NX_201402_306 ���������ն��Ż�����_�������������
		
		// ��ʵ������֤
		function toRealNameCheck()
		{
			// �Ƿ�ʵ������Ϣ�Ǽ�
			var ret = realNameCheck(document.getElementById("servnumber").value);
			if(ret.split('^')[0] == '0')
			{
				openRealNameConfirm(ret.split('^')[1]);
				return;
			}
			submitReception();
		}
		
		// ��ʵ������Ϣ��ʾ
		function realNameCheck(telNumber)
		{
			var url="${sessionScop.basePath}login/realNameCheck.action";
			var param="servnumber="+telNumber;
			var data = "";
			var contentLoader=new net.ContentLoaderSynchro(url,function(){
				data=this.req.responseText;
			},null,"POST",param,"application/x-www-form-urlencoded");
			return data;
		}
		
		//����ȷ�Ͽ�
		openRealNameConfirm = function(content)
		{
			document.getElementById('realnamePromptId').innerHTML = content;
			wiWindow = new OpenWindow("realNameCheck_confirm",708,392);//�򿪵�����������
		}
		
		// ת�����������֤ҳ��
		function goRamdomPage()
		{   
			//�Ժ�������ж�
			var pattern = /^\d{11}$/;
			
			var telNumber = document.getElementById("servnumber").value;
			if (telNumber == "" || !pattern.test(telNumber))
			{
				changObj(document.getElementById('servnumber'), 1);
			
				if ("1" == "<%=popupFlag %>")
				{
					alertRedErrorMsg("��������ȷ���ֻ�����");
				}
				else
				{
					document.getElementById("errorMsg").innerHTML = "��������ȷ���ֻ�����";
				}
				
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;		
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/randomcode_cq.action";
				document.actform.submit();
			}
		}
		
		//������һҳ
		function goback(menuid)
		{
			// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			if (document.getElementById("backWaitingFlag").value == "1")
			{
				openWindow_wait('pls_wait');
			}
			// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
			
			//modify begin sWX219697 2015-2-13 17:08:32 bug 86167
			<% 
			if(StringUtils.isBlank(isssGoBackFlag))
			{
			%>
				window.history.back();
			<%
			}
			else
			{
				session.removeAttribute("isssGoBackFlag");
			%>
				window.location.href = "${sessionScope.basePath }login/goHomePage.action?timeoutFlag=0";
			<%
			}
			%>
			//modify end sWX219697 2015-2-13 17:08:32 bug 86167
		}
		
		<%--
		* �û�����֤�����۽�������û�δ��֤�����۽��ֻ����������
		* @remark create g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600
		--%>
		function focusText()
		{
			<%
			if (null == custInformation && null == cdrQryTelnum)
			{
			%>
				document.getElementById('servnumber').focus();
			<%
			}
			else
			{
			%>
				document.getElementById('password').focus();
			<%
			}
			%>
		}
		
	    <%--
		* ת�򵽶�����֤����֤ҳ�档
		* @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
		--%>
		function goRandomPwdPage()
		{  
			if (submitFlag == 0)
			{
				submitFlag = 1;	
					
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goRandomPwdPage.action";
				document.actform.submit();
			}
			
		}
		
		<%--
		* ת��������֤ҳ�档
		* @remark create m00227318 2013/02/07 R003C13L01n01 OR_NX_201302_600
		--%>
		function goIDPage()
		{
		    if (submitFlag == 0)
		    {
		        submitFlag = 1;
		        
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}login/goIDPage.action";
				document.actform.submit();
		    }
		}
		
		// �������ڴ���DIV(����)
		openRecWaitLoading_NX = function(id){
		<%
			if(Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
			{
		%>
				wiWindow = new OpenWindow("recWaitLoading", 804, 515);
		<%
			}
		%>
		}
		
		<%--add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
		function changeImage()
		{
			document.getElementById('refreshRandomCodeImage').src="${sessionScope.basePath}login/randomCodeImage.action?pageId=" + Math.random();
		}
		<%--add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� --%>
		
		</script>
	</head>
	<body scroll="no" onload="focusText();">
		<form name="actform" method="post">
			<!--add begin CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597-->
			<input type="hidden" id="typeID" name="typeID" value="<%=typeID %>"/>
			<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="<%=quickPubFlag %>"/>
			<!--add end CKF76106 2012/09/27  R003C12L07n01 OR_HUB_201206_597-->
			
			<%--add begin g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>
			<input type="hidden" id="authCodeType" name="authCodeType" value="<%=authCodeType %>"/>
			<input type="hidden" id="resultAvaiCode" name="resultAvaiCode" value="<%=resultAvaiCode %>"/>
			<%--add end g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>
			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<div class="blank20"></div>
				<span class="yellow fs16 ml10" >
					&nbsp;&nbsp;
				</span>
			
				<%--modify begin g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>
				<div class="blank50">
				<%
				    // modify begin yKF28472
				    if (Constants.PROOPERORGID_CQ.equalsIgnoreCase(pageProvince))
				    {
				%>
				<br>
				<a href="#" class="bt10 fr mr43"
					onmousedown="this.className='bt10on fr mr43'"
					onmouseup="this.className='bt10 fr mr43';goRamdomPage(); return false;"
					style="display: inline">���������֤</a>
				<%
				    }
				    
				    // modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
				    // ������ͷ�ɽ����ѡ��ʽ   �������Ͳ�������������ʹ��ԭ����ʽ,ʹ��ԭ�а�ťչʾҳ��
				    else if (!((Constants.PROOPERORGID_CQ.equalsIgnoreCase(pageProvince)) || (Constants.PROOPERORGID_SD.equals(pageProvince)&& null != resultAvaiCode)) 
				    			|| (!Constants.PROOPERORGID_CQ.equalsIgnoreCase(pageProvince) && (!"1".equals(availableFlag))))
				    // modify end by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ
				    
				    {
				    	// ���л�������֤��֤ҳ��
				    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
						{
				%>
					<a id="idModeLink" href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goIDPage(); return false;" style="display:inline">����֤��֤��¼</a>    
				<%
						}
				
						// ���ж�����֤����֤ҳ��
						if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
						{
				%>		
					<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goRandomPwdPage(); return false;" style="display:inline">������֤���¼</a>    
				<%
						}
				    }
				%>
				</div>
				<%--modify end g00140516 2013/02/21 R003C13L02n01 OR_NX_201302_600 --%>

				<div class="b966">
					<div class="blank30" id="errorMsg"></div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<%
								if (null == custInformation && null == cdrQryTelnum)
								{
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� 
									if (Constants.PROOPERORGID_HUB.equals(pageProvince))
									{
									%>
										<li class="on fs20 clearfix" id="phone_input_1">
											<i class="lh30">1.�����ֻ�����</i>
											<span id="redstar1" class="pl20 fl lh75">�ֻ����룺</span>
											<input type="text" id="servnumber" name="servnumber"
												maxlength="11" class="text1 fl relative" style="margin-left:20px;"
												onclick="changObj(this, 1);MoveLast(this);" value="<%=currServnumber %>" />
										</li>
										<li class="fs20 clearfix" id="phone_input_2">
											<i class="lh30">2. ������֤</i>
											<span id="redstar2" class="pl20 fl lh75">������֤��</span>
											<input type="password" name="password" id="password"
												maxlength="6" class="text1 fl relative" style="margin-left:20px;"
												onclick="changObj(this, 2);MoveLast(this);" />
										</li>
										<li class="fs20 clearfix" id="phone_input_3">
											<i class="lh30">3. ͼƬ��֤����֤</i>
											<span id="redstar3" class="pl20 fl lh75">��֤�룺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
											<input type="text" name="randomCodeImage" id="randomCodeImage"
												maxlength="6" class="text3 fl relative"
												onclick="changObj(this, 3);MoveLast(this);" />
											<img border="1" 
												src="${sessionScope.basePath}login/randomCodeImage.action" width="60"
												height="45" title="������»�ȡ��֤��" id="refreshRandomCodeImage"
												onclick="changeImage();" style="cursor: pointer;" />
										</li>
									<%	
									}
									else
									{
									//add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� 
									%>	
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.�����ֻ�����</i>
									<span id="redstar1" class="pl20 fl lh75">�ֻ����룺</span>
									<input type="text" id="servnumber" name="servnumber"
										maxlength="11" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 1);MoveLast(this);" value="<%=currServnumber %>" />
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								<% 
								// ɽ����ѡ��ʽ��¼��Ϊ����ʽ��־
								if (Constants.PROOPERORGID_SD.equals(pageProvince) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">��֤��ʽ��</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">���������¼</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">����������¼</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goIDPage(); return false;" style="display:inline">����֤��֤��¼</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								
								<li class="fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. ������֤</i>
									<span id="redstar2" class="pl20 fl lh75">������֤��</span>
									<input type="password" name="password" id="password"
										maxlength="6" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 2);MoveLast(this);" />
								</li>
								<%
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸�
									}
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� 
								}
								else if(null != cdrQryTelnum)
								{
								%>
								<li class="fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.�����ֻ�����</i>
									<span id="redstar1" class="pl20 fl lh75">�ֻ����룺</span>
									<%-- �ֻ������Զ���䣬�Ҳ����޸� --%>
									<input type="text" id="servnumber" name="servnumber" value="<%=cdrQryTelnum %>"
									style="margin-left:20px;"	class="text1 fl relative" readonly="readonly" />
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								<% 
								// ɽ����ѡ��ʽ��¼��Ϊ����ʽ��־
								if (Constants.PROOPERORGID_SD.equals(pageProvince) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">��֤��ʽ��</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">���������¼</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">����������¼</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goIDPage(); return false;" style="display:inline">����֤��֤��¼</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								
								<li class="on fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. ������֤</i>
									<span id="redstar2" class="pl20 fl lh75">������֤��</span>
									<input type="password" name="password" id="password"
										maxlength="6" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 2);MoveLast(this);" />
								</li>									
								<%
								}
								else
								{
								%>
								<li class="fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.�����ֻ�����</i>
									<span id="redstar1" class="pl20 fl lh75">�ֻ����룺</span>
									<%-- �ֻ������Զ���䣬�Ҳ����޸� --%>
									<input type="text" id="servnumber" name="servnumber" value="<%=custInformation.getServNumber() %>"
									style="margin-left:20px;"	class="text1 fl relative" readonly="readonly" />
								</li>
								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								<% 
								// ɽ����ѡ��ʽ��¼��Ϊ����ʽ��־
								if (Constants.PROOPERORGID_SD.equals(pageProvince) && "optional".equals(authCodeType) && null != resultAvaiCode && ("1".equals(availableFlag)))
								{
								%>
								<li class="fs20 clearfix" id="phone_input_3" style="height:100%">
									<span id="redstar1" class="pl20 fl lh75">��֤��ʽ��</span>
									<span>
									<% 
							    	if ("optional".equals(authCodeType) && resultAvaiCode.charAt(0) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112on fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112on fr mr92';" style="display:inline">���������¼</a>
									<% 
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(1) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goRandomPwdPage(); return false;" style="display:inline">����������¼</a>
									<%
									}
									if ("optional".equals(authCodeType) && resultAvaiCode.charAt(2) == '1')
									{
									%>
									<a id="idModeLink" href="#" class="bt112 fr mr92" onmousedown="this.className='bt112on fr mr92'" onmouseup="this.className='bt112 fr mr92';goIDPage(); return false;" style="display:inline">����֤��֤��¼</a> 
									<%
									}
									%>
									</span>
								</li>
								<% 
								}
								%>								
								<%-- modify begin by qWX279398 2015-10-29 OR_SD_201510_540_ɽ��_�����նˡ��û���½��Ȩ�����Ӷ�������뷽ʽ --%>
								
								<li class="on fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. ������֤</i>
									<span id="redstar2" class="pl20 fl lh75">������֤��</span>
									<input type="password" name="password" id="password"
										maxlength="6" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 2);MoveLast(this);" />
								</li>									
								<%
								}
								%>
								

								<%
								    // modify begin yKF28472
								    if (Constants.PROOPERORGID_CQ.equalsIgnoreCase(pageProvince))
								    {
								%>
								<div class="blank30"></div>
								<div class="blank30"></div>
								<div class="blank30"></div>
								<p class="tit_arrow ">
									<span class=" bg"></span>��ܰ��ʾ��
								</p>
								<p class="p20">
									1. ��Ҳ��ͨ���ն˵Ľ����������룻
								</p>
								<p class="p20">
									2. �ṩ����Ͷ�������������֤��ʽ��
								</p>
								<%
								    }
								    else if ("1".equals(keyFlag))
								    {
								%>
								<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>��ܰ��ʾ��</p>
         							<p class="p10">1. ��Ϣ������Ϻ��밴"ȷ��"���ύ��</p>
         							<p class="p10">2. �����޸ģ��밴"���"����</p>
         						</li>
								<%
								    }
								%>

							</ul>



							<!--С����-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class=" numbox">
									<div class="blank10"></div>
									<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a
										href="javascript:void(0)">3</a>
									<a href="javascript:void(0)" class="func1" name="functionkey"
										id="numBoardBack" onmousedown="this.className='func1on'"
										onmouseup="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a
										href="javascript:void(0)">6</a>
									<a href="javascript:void(0)" class="func2" name="functionkey"
										id="numBoardClear" onmousedown="this.className='func2on'"
										onmouseup="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a
											href="javascript:void(0)">9</a>
										<a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a
											href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a href="javascript:void(0)" onclick="doSub();return false;"
											class="func3" name="functionkey" id="numBoardEnter"
											onmousedown="this.className='func3on'"
											onmouseup="this.className='func3'">1</a>
									</div>
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
									<%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ��������Ժ�......") %>
								</p>
								<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
								
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
								<% 
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸�
									if (Constants.PROOPERORGID_HUB.equals(pageProvince)) 
									{
								%>
									var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
									var lastObj = document.getElementById('servnumber');
									var lastpw = document.getElementById('password');
									var type = 1;
									
									<%
									if (null != custInformation)
									{
									%>
										lastObj = document.getElementById('password');
										type = 0;
									<%
									}
									%>
									
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
							  			
							  				// servnumber��������Զ���ת��password
							  				if (pangu_getStrLen(lastObj.value) == 11 
							  						&& pangu_getStrLen(trim(document.forms[0].password.value)) == 0) 
							 				{
							 					document.forms[0].password.focus();
							 				
							 					changObj(document.forms[0].password, 2);
							 				
							 					return true;
							 				}
							 				
							 				// password��������Զ���ת��randomCodeImage
							 				if (pangu_getStrLen(lastpw.value) == 6 
							  						&& pangu_getStrLen(trim(document.forms[0].randomCodeImage.value)) == 0) 
							 				{
							 					document.forms[0].randomCodeImage.focus();
							 				
							 					changObj(document.forms[0].randomCodeImage, 3);
							 				
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
										else if (t == 2)
										{
											type = 0;
											document.getElementById('phone_input_1').className = "fs20 clearfix";
											document.getElementById('phone_input_2').className = "on fs20 clearfix";
											document.getElementById('phone_input_3').className = "fs20 clearfix";
										}
										else
										{
											type = 2;
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
											lastObj.value = "";
										}
										else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
										{	
											lastObj.value += v;
										}
										else if(lastObj.value.length < 6 && !isNaN(v) && type == 0)
										{
											lastObj.value += v;
										}
										else if (lastObj.value.length < 4 && !isNaN(v) && type == 2)
										{
											lastObj.value += v;
										}
										var r = lastObj.createTextRange(); 
										r.collapse(false); 
										r.select();
									}
									
								<%	
									}
									else
									{
									//add end lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸�
								%>
									var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
									var lastObj = document.getElementById('servnumber');
									var type = 1;
									<%
									if (null != custInformation)
									{
									%>
										lastObj = document.getElementById('password');
										type = 0;
									<%
									}
									%>
									
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
							  			
							  				// servnumber��������Զ���ת��password
							  				if (pangu_getStrLen(lastObj.value) == 11 
							  						&& pangu_getStrLen(trim(document.forms[0].password.value)) == 0) 
							 				{
							 					document.forms[0].password.focus();
							 				
							 					changObj(document.forms[0].password, 2);
							 				
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
										}
										else
										{
											type = 0;
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
											lastObj.value = "";
										}
										else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
										{	
											lastObj.value += v;
										}
										else if(lastObj.value.length < 6 && !isNaN(v) && type == 0)
										{
											lastObj.value += v;
										}
										var r = lastObj.createTextRange(); 
										r.collapse(false); 
										r.select();
									}
								<%
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸�
									}
									//add begin lWX431760 2017-01-05 OR_HUB_201609_640_�����ն��û���¼��֤��ʽ�޸� 
								%>	
	                			
								
								
								
										
	              			</script>
							<!--С����end-->
							
							<!-- ��ɫ������ʾ��Ϣ -->
							<div class="popup_confirm" id="openWin_ErrorMsg">
								<div class="bg"></div>
								<div class="tips_title">��ʾ��</div>
								<div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg"></div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">�ر�</a>
									</span>
								</div>
							</div>
							
							<div class="popup_confirm" id="openWin_successMsg">
								<div class="bg"></div>
								<div class="tips_title">��ʾ��</div>
								<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_successMsg"></div>
								<div class="btn_box tc mt20">
									<span class=" inline_block ">
										<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȷ��</a>
									</span>
								</div>
							</div>
							
							<div class="popup_confirm" id="weakPwdCheck_confirm">
			                  <div class="bg"></div>
			                  <div class="tips_title">��ʾ��</div>
			                  <div class="tips_body">
			                  	<div class="blank30"></div>
							    <p id="weakPwdPromptId"></p>
							    <div class="blank30"></div>
							  </div>
			                  <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();toRealNameCheck();">ȷ��</a></span>
			                  </div>
			                </div>
							
							<div class="popup_confirm" id="realNameCheck_confirm">
			                  <div class="bg"></div>
			                  <div class="tips_title">��ʾ��</div>
			                  <div class="tips_body">
			                  	<div class="blank30"></div>
							    <p id="realnamePromptId"></p>
							    <div class="blank30"></div>
							  </div>
			                  <div class="btn_box tc mt20">
				                  <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">ȷ��</a></span>
				                  <span class=" inline_block "><a class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span>
			                  </div>
			                </div>
							<script type="text/javascript">								
								alertRedErrorMsg = function(content)
								{
									document.getElementById('winText_ErrorMsg').innerHTML = content;
									wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
								};
							</script>
						</div>
						<div class="blank10"></div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<!--�������ڴ���div-->
	<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
		<div class="bg"></div>
	    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
	    <%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
	   	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ��������Ժ�......") %></p>      
	   	<%-- modify end hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>               
	</div>
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
		
		//ҳ����ת֮ǰ���session��isssGoBackFlag
		//add by sWX219697 2015-2-13 17:26:48 �޸�bug��86167
		window.onbeforeunload = function()
		{
		    <%
			if(StringUtils.isNotBlank(isssGoBackFlag))
			{
				session.removeAttribute("isssGoBackFlag");
			}
			%>
		}
	</script>
</html>