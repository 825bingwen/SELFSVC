<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String successBz = (String)request.getAttribute("successBz");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}css/IE8.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/ajax_0.1.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
<script type="text/javascript">
var submitFlag = 0;
var checkBz = 0;//���֤�Ƿ��ѱ�����ʹ�� 0:δ���� 1:������
var checkText = '';//���֤����ʱ����ʾ��Ϣ

// �����ȴ�ʱ��
var limitTime = 30;
		
//�������ʣ��ʱ��ľ��
var timeToken;
		
//�ȴ������ľ��
var waitingToken;

// ��ʼ�����֤�������߳�������־��1����������0��δ���������Ϊ1ʱ���û�����ȡ������������ùر����֤�Ķ����ӿ�
var initCardReader = 0;

function doSub()
{	
	var idCard = trim(document.actform.idCard.value);
	if (idCard == "")
	{
		alertRedErrorMsg("���֤���벻��Ϊ�գ�");
		return ;
	}

	var ren = checkIDNo(idCard);

	if (ren != "1")
	{
		alertRedErrorMsg(ren);
		return;
	}

	/**
	checkIdCard();
	
	if (checkBz == 1)
	{
		document.getElementById('winText').innerHTML = checkText;
		wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������
		return;
	}
	**/
	
	//�����ظ��ύ
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }chooseTel/idCardFinish.action";
		document.actform.submit();

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
      
document.onkeyup = pwdKeyboardUp;
      	      
function pwdKeyboardUp(e)
{
	var key	= GetKeyCode(e);
	
	//ȷ��
	if (key == 13 || key == 89 || key == 221)
	{
	   	doSub();	
	   	return ;
	}	  	
	//����
	else if (key == 82 || key == 220)
	{
	   	goback("<s:property value='curMenuId' />");
	   	return ;
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
	 
function goback(curmenu) 
{
       //��ֹ�ظ��ύ
	if (submitFlag == 0) 
	{
		if(closeStatus == 3)
		{
			//  �ر����֤������
			try{
				window.parent.document.getElementById("idcardpluginid").CloseCardReader();
			}catch(e){}
		}
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
		document.actform.submit();
	}
}

//���Ķ������ 
var xmlHttp; 

//ajax����ִ�з������˴�Ϊ�ύ��servlet�����,���ش��ı��� 
function checkIdCard(){ 
	//�������������XMLHttpRequest���Ķ��� 
	if(window.XMLHttpRequset){ 
	  xmlHttp = new XMLHttpRequest(); 
	}else if(window.ActiveXObject){ 
	  xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	}
	var idCard = document.getElementById('idCard').value;
	var URL = "${sessionScope.basePath }chooseTel/checkIdCard.action?idCard="+idCard; 
	xmlHttp.open("post",URL,false); 
	xmlHttp.onreadystatechange=callback; 
	xmlHttp.send(null); 
} 

//�ص����� 
function callback(){
	if(xmlHttp.readyState == 4){
		if(xmlHttp.status == 200){
			var v = xmlHttp.responseText; 
			checkText = v;
			checkBz = 0;
			if ('' != v)
			{
				checkBz = 1;
			}
		} 
	} 
} 

// ҳ������ʱ�������֤��������ȡ�û����֤��Ϣ
function bodyLoad()
{
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
			
			// �����֤��Ƕ�뵽ҳ���������
			document.getElementById("idCard").value = idCardNo;
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
	
	document.getElementById("idCard").value = limitTime;
	
	//����ʱ�����
	if (parseInt(document.getElementById("idCard").value) <= 0 && submitFlag == 0)
	{
       	setErrorInfoRegion("��������ʧ�ܣ����ֹ������������֤����Ϣ��");
       	document.getElementById("idCard").value = "";
       	document.getElementById("idCard").focus();
       	
       	//���ùر����֤�Ķ����ӿ�
		//������
		CloseCardReader();
       	
       	//���Ե�������˵�
		closeStatus = 0;
	}
}

// ��¼������Ϣ
function setErrorInfoRegion(errMsg)
{
	document.getElementById("errorMsg").innerHTML = errMsg;
}

<%--add begin lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
//�ֶ��������֤����
function manualIdCard()
{
	document.getElementById("phone_input_1").style.display="block";
}

//�������������֤����
function automaticIdCard()
{
	document.getElementById("phone_input_1").style.display="block";
	bodyLoad();
}
<%--add end lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="telnum" id="telnum" value="<s:property value='telnum' />"/>
			<input type="hidden" name="payfee" id="payfee" value="<s:property value='payfee' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">				
				<%@ include file="/customer.jsp"%>
				<a title="����ѡ�ſ���" href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">����ѡ�ſ���</a>
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<%--modify begin lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
								<a title="�ֶ��������֤��" href="javascript:void(0)" class=" bt3" id="manual" name="manual" onclick="manualIdCard();">�ֶ��������֤��</a><br/>
								<a title="��������ȡ���֤��" href="javascript:void(0)" class=" bt3" id="automatic" name="automatic" onclick="automaticIdCard();">��������ȡ���֤��</a>
          						<li class="on fs20 clearfix" id="phone_input_1" style="display:none;">
          							<i class="lh30">1.�������֤����</i>
          							<span class="pl20 fl lh75">���֤���룺</span>
            						<input type="text" maxlength="18" id="idCard" name="idCard" class="text1 fl relative fs22" onfocus="MoveLast(this)" />
          						</li>
          						<%--modify end lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
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
	            						<a title="7" href="javascript:void(0)">7</a><a title="8" href="javascript:void(0)">8</a><a title="9" href="javascript:void(0)">9</a> <a title="X" href="javascript:void(0)">X</a><a title="0" href="javascript:void(0)">0</a><a title="#" href="javascript:void(0)">#</a> 
	            					</div>
	            					<div class="nright"> 
	            						<a title="ȷ��" href="javascript:void(0)" onclick="doSub();return false;" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'">1</a> 
	            					</div>
	            					<div class="blank10"></div>
	          					</div>
	        				</div>
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('idCard');
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
									else if (lastObj.value.length < 18 && v != "#")
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
				
				<!--������-->
				<div class="openwin_tip" id="openWin1">
				  	<div class="bg"></div>
				  	<div class=" blank60"></div>
				  	<div class=" blank60"></div>
				  	<div class="  blank10n"></div>
				  	<p class="fs28 lh2 pl142">
						<span class="yellow">&nbsp;&nbsp;ԤԼ���ƣ�</span> <br />
						<span class="ml20" id="winText"></span>
				 	</p>
				  	<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
					    <a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a> 
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<%--add begin lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
	<script type="text/javascript">
		if (window.parent. is2GIDFlag == "0")
		{
			document.getElementById("manual").style.display = "none";
			document.getElementById("automatic").style.display="none";
			document.getElementById("phone_input_1").style.display="block";
		}
		
	</script>
	<%--add end lWX431760 2017-02-24 OR_HUB_201611_276_��BOSS�������������ն˺���ԤԼ�����Ż������� --%>
</html>
