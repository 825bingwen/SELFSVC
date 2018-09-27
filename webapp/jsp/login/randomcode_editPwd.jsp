<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	String servnumber = (String)request.getAttribute("servnumber");
	
    String errormessage = (String)request.getAttribute("errormessage");
    if (errormessage == null)
    {
    	errormessage = "";
    }
    
    // add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	errormessage = CommonUtil.errorMessage(errormessage);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NG2.3�����ն�ϵͳ���������֤</title>
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
<!--
var submitFlag = 0;
		
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
		
document.onkeydown = pwdKeyboardDown;		
document.onkeyup = pwdKeyboardUp;

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

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	//ȷ��
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
	}
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R �˳�
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
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
			etarget.value = backString(etarget.value);
			
			var code = document.actform.randomPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.randomPwd.focus();
				return true;
			}
		}	
	<%
	}
	else
	{
	%>
		//82:R 220:����
		if (key == 82 || key == 220)
		{
			goback("root");
		}
		//����
		else if (key == 8 || key == 32 || key == 66 || key ==77)
		{
			var etarget = getEventTarget(e);
			etarget.value = backString(etarget.value);
			
			var code = document.actform.randomPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.randomPwd.focus();
				return true;
			}
		}
	<%	
	} 
	%>
}
		
// �������򣬹�궨λ���ı����ݺ�
function MoveLast(e) 
{
	var etarget = getEventTarget(e);
	var r = etarget.createTextRange();
	r.moveStart("character", etarget.value.length);
	r.collapse(true);
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

//ȷ��
function doSub() 
{
	var randomPwd = document.actform.randomPwd.value;
	
	if (trim(randomPwd) == "" || isNaN(randomPwd) || pangu_getStrLen(trim(randomPwd)) != 6) 
	{
		document.actform.randomPwd.focus();
		document.actform.randomPwd.select();
		document.getElementById("errorMsg").innerHTML = "��������ȷ�Ķ�������";
		
		return;
	}
	
	//�Ѿ������ȷ�ϻ򷵻أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/editPasswordByRandomPwd.action";
		document.actform.submit();
       }
}

function goback(curmenu) 
{
	//�Ѿ������ȷ�ϻ򷵻أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action = "<%=basePath %>login/backForward.action";
		document.actform.submit();
	}		
}
		
		//����
function load()
{  
	document.getElementById('randomPwd').focus();
}
//-->
</script>
	</head>
	<body scroll="no" onload="document.getElementById('randomPwd').focus();">
		<form id="actform" name="actform" method="post">
			
			<%@ include file="/titleinc.jsp"%>
			<input type="hidden" name="servnumber" id="servnumber" value="<%=servnumber %>"/>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 hidden">
					<div class="blank30" id="errorMsg" ><%=errormessage %></div>
					
    				<div class=" pl62">
    					<p class="fs18" style="visibility:hidden;">Ϊȷ�����ĸ�����Ϣ��ȫ��������������롣</p>
    					<div class="blank30"></div>
      					<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
      					
      					<!--����+�����+��ܰ��ʾ-->
      					<div class="keyboard_wrap authentication authentication_2n clearfix">
      						<ul class="phone_num_list fl">
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          						</li>
          						
								<li class="clearfix pt10 hidden" style="height:auto;">
         							<p class=" fs18 fl lh30 yellow" >
         						<%
								if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
								{
								%>
									������ķ�������Ϊ��ʼ���롣<br>Ϊȷ����ȫ����ͨ��������֤���޸ķ������롣<br><br>
								<%
								}
								%>
									���������ѷ��͵��ֻ�����ע����գ�</p>
         						</li>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          							<div class="blank30"></div>
          							<span id="redstar1" style="font-size:18px;">�������룺</span>
          							<br />
            						<input type="text" id="randomPwd" name="randomPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(event);" value="" />
          						</li>
          						<%
          						if ("1".equals(keyFlag))
          						{
          						%>
          						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>��ܰ��ʾ��</p>
         							<p class="p10">1. ��������������Ϻ��밴"ȷ��"���ύ��</p>
         							<p class="p10">2. �����޸ģ��밴"���"����</p>
         						</li>
          						<%	
          						}
          						%>        
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
        					
        					<script type="text/javascript">
        						<%
									if("1".equals(redStarKey))
									{
								%>
									var textContent1 = document.getElementById('redstar1').innerHTML;
									document.getElementById('redstar1').innerHTML=textContent1 + '<font color="red">*</font>';
								<%
									}
								%>
								
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
