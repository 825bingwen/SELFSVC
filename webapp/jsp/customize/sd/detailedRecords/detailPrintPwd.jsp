<%--
create by sWX219697 2014-6-4 10:48:31 OR_huawei_201405_877
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
		
    String errormessage = (String)request.getAttribute("errormessage");
    if (errormessage == null)
    {
    	errormessage = "";
    }
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
    
    String popupFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_ERRORMSG_POPUPFLAG);
    
    String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String selectReason = (String)request.getAttribute("selectReason");
    if (selectReason == null)
    {
    	selectReason = "";
    }

%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>NG2.3�����ն�ϵͳ����������֤</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script type="text/javascript">

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
			etarget.value = backString(etarget.value);
		
			var code = document.actform.detailPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.detailPwd.focus();
				return true;
			}
		}	
	<%
	}
	else
	{
	%>	
		// ����
		if (key == 82 || key == 220)
		{
			goback("<%=currMenuID %>");
			return;
		}		
		//����
		else if (key == 8 || key == 32 || key == 66 || key ==77)
		{
			var etarget = getEventTarget(e);
			etarget.value = backString(etarget.value);
		
			var code = document.actform.detailPwd.value;
			if (pangu_getStrLen(trim(code)) == 6)
			{
				document.actform.detailPwd.focus();
				return true;
			}
		}
	<%
	}
	%>
}
		
document.onkeyup = pwdKeyboardUp;
		
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
	var detailPwd = document.actform.detailPwd.value;
	
	if (trim(detailPwd) == "" || isNaN(detailPwd) || pangu_getStrLen(trim(detailPwd)) != 6) 
	{
		document.actform.detailPwd.focus();
		document.actform.detailPwd.select();
		document.getElementById("errorMsg").innerHTML = "��������ȷ�Ĳ�������";
		
		return;
	}
	
	//�Ѿ������ȷ�ϻ򷵻أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		//�첽��֤��������
		authPwd();
    }
}

//�첽��֤����
function authPwd()
{
    //����ajax���� xmlHttpRequest            
    XmlHttpRequest = false;      
    //������Ҫ����һ��XMLHttpRequest����,�������з���������,��cf ��ͬ���������������ͬ      
	if (window.XMLHttpRequest)      
	{ 
	    // Mozilla, Safari,...      
		XmlHttpRequest = new XMLHttpRequest();      
		if (XmlHttpRequest.overrideMimeType)      
		{      
		    XmlHttpRequest.overrideMimeType('text/xml');      
		}      
	}      
	else if (window.ActiveXObject)      
	{ // IE      
        try             
		{      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		}      
		catch (e)      
		{      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		}      
	}      
		     
	if (!XmlHttpRequest) 
    {      
        // alert('���ִ���,���ܽ���һ��XMLHTTPʵ��!');      
		return false;      
	}    
		
	var detailPwd = document.actform.detailPwd.value;
	var postStr ="detailPwd="+detailPwd;   
		
	//���ûص���js����  ����˵�������� ��������Ӧ�� ����ִ�е�js����   callBack�Ǻ�����  
	XmlHttpRequest.onreadystatechange=CallBack;
	
	//��һ���������������ͣ�GET/POST�� �ڶ������������·�� �����дһ��servlet��ַ 
	XmlHttpRequest.open("POST","<%=basePath %>cdrquery/authDetailPrintPwd.action",true);    
	XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");  
	
	//��ʼ�����������xmlHttpRequest    
	XmlHttpRequest.send(postStr);   
}

//�ص�����
function CallBack()
{
	if((XmlHttpRequest.readyState==4)&&(XmlHttpRequest.status==200))   
	{   
		var msgWelcome=XmlHttpRequest.responseText;
		
		//����Ĳ������벻��ȷ
		if(msgWelcome == 0)
		{
			document.getElementById("errorMsg").innerHTML = "�����������";
			document.actform.detailPwd.value="";
			document.actform.detailPwd.focus();
			submitFlag = 0;//�ı�״̬λ��ʹ��һҳ��ť����
		    return;
		}
		
		//��д��ȷ����ӡ�굥������ת
		if(msgWelcome == 1)
		{
		    //��ӡ�굥
			//printInfo();
			
			//��ת���굥չʾҳ��
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}cdrquery/qryDetailedRecords.action";
			document.actform.submit();
		}
	}
}

//��ӡ�굥
function printInfo()
{
	//�����굥���ͻ�ȡ��Ӧ���굥����
	var cdrName = {'FIXFEE':'�ײͼ��̶����굥','GSM':'ͨ���굥','SMS':'��/�����굥',
		'GPRSWLAN':'�����굥','ISMG':'��ֵҵ��۷Ѽ�¼ ','INFOFEE':'���շ�ҵ��۷Ѽ�¼'};
	var cdrType = "<s:property value='cdrType' />";
    var printTime = getCurrentTime();

    //��ӡ
  	doPrint_SDNew('1', "<s:property value='cdrType' />", '${sessionScope.CustomerSimpInfo.servNumber }', 
  			'<s:property value="startDate" />', '<s:property value="endDate" />', '${sessionScope.TERMINALINFO.orgname }', 
  			printTime, '${sessionScope.basePath}', '<s:property value="curMenuId" />', cdrName[cdrType], 
  			'<s:property value="chQueryDate" escape="false"/>', '<s:property value="chStartDate" escape="false"/>',
  			'<s:property value="chEndDate" escape="false"/>', '<s:property value="custName" escape="false"/>',
  			"<s:property value='iscycle' />", "<s:property value='cycle' />", "<s:property value='month' />");
  			
}

//��¼�嵥��ӡ����
function InsertPrintInfo()
{
    var url = "${sessionScope.basePath }cdrquery/updatePrintTimes.action";
			
	var loader = new net.ContentLoader(url, null, null, "POST", "cdrType=<s:property value='cdrType' />", null);    		
}

//��һҳ��ť
function goback(curmenu) 
{
	//�Ѿ������ȷ�ϻ򷵻أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target = "_self";
		document.actform.action="${sessionScope.basePath}cdrquery/qryDetailedRecords.action";
		document.actform.submit();
	}		
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
</script>
	</head>
	<body scroll="no" onload="document.getElementById('detailPwd').focus();">
		<form id="actform" name="actform" method="post">
		<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" name="cdrType" value="<s:property value='cdrType' />" />
			<input type="hidden" name="iscycle" value="<s:property value='iscycle' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="month" value="<s:property value='month' />" />
			<input type="hidden" name="chQueryDate" value="<s:property value="chQueryDate" escape="false"/>" />
			<input type="hidden" name="chQueryDate" value="<s:property value="chQueryDate" escape="false"/>" />
			<input type="hidden" name="chStartDate" value="<s:property value="chStartDate" escape="false"/>" />
			<input type="hidden" name="chEndDate" value="<s:property value="chEndDate" escape="false"/>" />
			
			<input type="hidden" id="selectReason" name="selectReason" value="<%=selectReason %>" />	
			
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966 hidden">
					<div class="blank30" id="errorMsg"></div>
					
    				<div class=" pl62">
    					<p class="fs18" style="visibility:hidden;">Ϊȷ�����ĸ�����Ϣ��ȫ��������������롣</p>
    					<div class="blank30"></div>
      					<p class="fs18 mt10"><span style="color:#c71d02; font-size: 18px;" id="needRandomPwd"></span></p>
      					
      					<!--����+�����+��ܰ��ʾ-->
      					<div class="keyboard_wrap authentication authentication_2n ">
      						<ul class="phone_num_list fl">
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          						</li>
          						
          						<li class="clearfix pt10 hidden" style="height:auto;">
          							<p class=" fs18 fl lh10 yellow">����ϵӪҵ������Ա���벹������</p>          							
          						</li>
          						<li class="fs20 mt10 clearfix hidden" style="height:auto;" id="phone_input_1">
          							<div class="blank10"></div>
          							<span id="redstar1" style="font-size:18px;">�굥�������룺</span>
          							<br />
            						<input type="password" id="detailPwd" name="detailPwd" maxlength="6" class="text1 fl relative" onclick="MoveLast(event);"  value="" />
          						</li>
          						<%
          						if ("1".equals(keyFlag))
          						{
          						%>
          						<li class="fs18 mt30 line_height_12">
         							<p class="tit_arrow "><span class=" bg"></span>��ܰ��ʾ��</p>
         							<p class="p10">1. ����������Ϻ��밴"ȷ��"���ύ��</p>
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
								var numBoardText = document.getElementById('detailPwd');
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
