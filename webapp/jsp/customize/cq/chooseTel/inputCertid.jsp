<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// �ն˻���Ϣ
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

	// �ؼ�֧�ֱ�־
	String termSpecial = terminalInfo1.getTermspecial();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
			var contacttel = document.forms[0].contacttel.value;		
	
			if ((key == 8 || key == 32 || key == 66 || key ==77)
			 		&& pangu_getStrLen(trim(contacttel)) == 0 
			 		&& (pangu_getStrLen(trim(certid)) == 15 || pangu_getStrLen(trim(certid)) == 18)) 
			{
				document.forms[0].certid.focus();
				
				changObj(document.forms[0].certid, 1);
				
				return true;
			}
			
 			if (pangu_getStrLen(trim(certid)) == 18 && document.forms[0].contacttel.value == "") 
 			{
 				document.forms[0].contacttel.focus();
 				
 				changObj(document.forms[0].contacttel, 2);
 				
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
			var certid = trim(document.actform.certid.value);
			if (!(/(^\d{15}$)|(^\d{17}([0-9]|x)$)/.test(certid)))   
			{
				document.getElementById('certid').focus();
				document.getElementById('errorMsg').innerHTML = "��������ȷ��15λ��18λ���֤�š�";
				return false; 
			}
			
			var contacttel = trim(document.actform.contacttel.value);
			if (contacttel != "" && !(/^\d+$/.test(contacttel)))
			{
				document.getElementById('contacttel').focus();
				document.getElementById('errorMsg').innerHTML = "��������ȷ����ϵ�绰��";
				return false; 
			}				
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/makeSureTel.action";
				document.actform.submit();
			}		
		}
		
		function goback(curmenu) 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }chooseTel/telNumResult.action";
				document.actform.submit();				
			}			
		}
		
		// ҳ������ʱ�������֤��������ȡ�û����֤��Ϣ
		function bodyLoad()
		{
			if (is2GIDFlag != 1) 
			{
			    setErrorInfoRegion("���ն˲�֧�����֤�����������ֹ������������֤����Ϣ��");
				document.getElementById("certid").focus();
				return;
			}
			
			try 
			{				
				//������
				var ret = InitIdCardReader();// �������֤������Ϊ����״̬

				//������
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{
					setErrorInfoRegion("��ʼ�����֤�������쳣�������ֹ������������֤����Ϣ��");
                    return;
				}
				else
				{		
					//׼��ˢ����������
					initCardReader = 1;
					
					//�ȴ��������������ɹ��󣬾���Ҫ�رոý��̡�������ҳ�������˳�����ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
                	document.getElementById("homeBtn").disabled = true;
                	document.getElementById("quitBtn").disabled = true;
					
					//�ڵ��ó�ʼ�����֤�������󣬻�ȡ���֤��Ϣ֮ǰ����Ҫ���ô˽ӿڼ��һ�¶�����״̬
					getIdCardStatus();
					
					// ��ʼ��ʱ����ѭ�����ýӿ�
					startclock();
				}
			}
			catch (ex) 
			{
				setErrorInfoRegion("��ʼ�����֤�������쳣�����ֹ������������֤����Ϣ��");
                return;
			}
		}
		
		//��ȡ���֤������״̬
		function getIdCardStatus()
		{
			try
			{
				//������
				var ret = GetIdCardStatus();// ��ȡ���֤������״̬
				
				// ������
				//var ret = 0;
				
				if (ret != "0" && ret != 0) 
				{					
					setErrorInfoRegion("��ʼ�����֤�������쳣�����ֹ������������֤����Ϣ��");
				}
				
				// ��������˵���Ҫ�ر����֤����
				closeStatus = 3;
			}
			catch (excep) 
			{		
				setErrorInfoRegion("��ʼ�����֤�������쳣�����ֹ������������֤����Ϣ��");
			}
		}
		
		//����ʱ���������
		function startclock() 
		{			
			try 
			{
				setErrorInfoRegion("�������֤��������ˢһ���������֤��ϵͳ���Զ����������֤�����뵽ҳ������֤����Ϣ���С�");
				
				// ��ȡ���֤��������
				waitingToken = setInterval("getReadCardStatus()", 1000);
				
				// ��ʱ��
				timeToken = setInterval("calLeftTime()", 1000);
			}
			catch (e) 
			{
				setErrorInfoRegion("��������ʧ�ܣ����ֹ������������֤����Ϣ��");
			}
		}
		
		// ѭ�����û�ȡ���֤�������ݽӿ�
		function getReadCardStatus() 
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			try 
			{
				//������
				var ret = GetIdCardContent();// �򿪲��Ž����û����֤ ���꿨�󷵻�״̬ �������뵽��������Ҫ��needReturnCardֵΪ1��
				
				//������
				//var ret = "0����~��~����~����~סַ~37061218840530402X~ǩ������~��Ч����ʼ����~��Ч�ڽ�ֹ����~����סַ";

				if (ret.substring(0,1) == "0" || ret.substring(0,1) == 0) 
				{	
					//���ùر����֤�Ķ����ӿ�
					//������
					CloseCardReader();
					
					//���Ե�������˵�
					closeStatus = 0;
					
					//���֤��Ϣ��0����~�Ա�~����~����~סַ~������ݺ���~ǩ������~��Ч����ʼ����~��Ч�ڽ�ֹ����~����סַ
					var idCardInfor = ret.substring(1,ret.length).split('~');
					
					//������ݺ���
					var idCardNo = idCardInfor[5];
					
					//ȡ����ʱ��
					clearInterval(timeToken);
					
					document.getElementById("certid").value = idCardNo;
					
					// �����㶨λ�������ֻ������
					changObj(document.getElementById('contacttel'), 2);
					document.getElementById("contacttel").focus();
				} 
				else if (ret == "-1")
				{
					setErrorInfoRegion("��ȡ���֤��������ʧ�ܣ����ֹ������������֤����Ϣ��");
				}
				else if (ret == "2")
				{	
					setErrorInfoRegion("֤���޷�ʶ�����ֹ������������֤����Ϣ��");
				}						
			}
			catch (e) 
			{		
				setErrorInfoRegion("���֤����������ʧ�ܣ����ֹ������������֤����Ϣ��");
			}
		}
		
		//�������ʣ��ʱ��
		function calLeftTime()
		{
			if (limitTime <= 0)
			{
				return;
			}
			
			//����ʱ��һ��limitTime��
			limitTime = limitTime - 1;
			
			document.getElementById("certid").value = limitTime;
			
			//����ʱ�����
			if (parseInt(document.getElementById("certid").value) <= 0 && submitFlag == 0)
			{
	        	setErrorInfoRegion("��������ʧ�ܣ����ֹ������������֤����Ϣ��");
	        	document.getElementById("certid").value = "";
	        	document.getElementById("certid").focus();
	        	
	        	//���ùر����֤�Ķ����ӿ�
				//������
				CloseCardReader();
				
				//���Ե�������˵�
				closeStatus = 0;
			}
		}
		
		//���ô�����Ϣ
		function setErrorInfoRegion(errMsg)
		{
			document.getElementById("errorMsg").innerHTML = errMsg;		    
		}
		
		</script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />			
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
			<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
			<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
			
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />	
			<input type="hidden" id="org_id" name="org_id" value="<s:property value='org_id'/>" />
			
			<input type="hidden" id="certtype" name="certtype" value="IdCard"/>
			<input type="hidden" id="name" name="name" value=""/>
			
			<%@ include file="/titleinc.jsp"%>
		
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>����</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);">
							1.ѡ���ѯ��ʽ
						</a>
						<a href="javascript:void(0);">
							2.�����ѯ����
						</a>
						<a href="javascript:void(0);">
							3.ѡ�����
						</a>
						<a href="javascript:void(0);" class="on">
							4.���������Ϣ
						</a>
						<a href="javascript:void(0);">
							5.���
						</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712" id="errorMsg"></div>
					<div class="blank20"></div>
					<div class="p40 pr0">
						<p class="blank20" id="errorMsg"></p>
						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="telnum" /></span></p>
                       	<div class="line w625"></div>
						<div class="blank10"></div>
						<div class="custom_money pl30 fl">
							<span class="pl40 fs20 fl lh48">���֤�ţ�</span>
							<input type="text" name="certid" id="certid" value="" maxlength="18" style="font-size:24px;" class="text1 fl" onclick="changObj(this, 1);" onfocus="MoveLast(event);">											
       					</div>
       					<div class="blank10"></div>
       					<div class="custom_money pl30 fl">
							<span class="pl40 fs20 fl lh48">��ϵ�绰��</span>
							<input type="text" name="contacttel" id="contacttel" value="" maxlength="12" class="text1 fl" onclick="changObj(this, 2);" onfocus="MoveLast(event);">					
       					</div>
       					
       					<div class="keyboard_wrap mt10 pl20 clearfix">
        					<div class="fl btn_back_box" style="padding-top:200px;">
        						<p style="padding-left:10px; font-size:16px;">&nbsp;&nbsp;</p>        							
        					</div>
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
        					<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var type = 1;
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
						  				
						  				if (pangu_getStrLen(trim(numBoardText.value)) == 18 
						  						&& document.forms[0].contacttel.value == "") 
							 			{
							 				document.forms[0].contacttel.focus();
							 				
							 				changObj(document.forms[0].contacttel, 2);
							 			}			      							   
									}
								}
								
								function changObj(o, t)
								{
									type = t;
									
									document.getElementById("errorMsg").innerHTML = "";
									
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
									else if (type == 1 && numBoardText.value.length < 18)
									{			
										numBoardText.value += v;																		
									}
									else if (type == 2 && numBoardText.value.length < 12 && !isNaN(v))
									{
										numBoardText.value += v;
									}
									
									var r = numBoardText.createTextRange(); 
									r.collapse(false); 
									r.select();
								}		
              				</script>
        				</div>
					</div>
				</div>		
			</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
