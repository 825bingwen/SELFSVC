<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	// �ն˻���Ϣ
	TerminalInfoPO terminalInfo1 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

    // ʡ����Ϣ
    String provinceSD = Constants.PROOPERORGID_SD;
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>		
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
</head>
<body scroll="no" onload="bodyLoad();">
<form name="actform" method="post">
	<input type="hidden" id="errormessage" name="errormessage" value="<s:property value='errormessage'/>" />

	<%@ include file="/titleinc.jsp"%>

	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<%@ include file="/jsp/valuecard/valueCardHeader.jsp" %>
		</div>
		
		<div class="b712">
			<div class="bg bg712" id="errMsg"></div>
			<div class="p40 pr0">
  					<div class=" mt10 pl20 clearfix" style="padding-left:10px;margin-top:35px;">
  					<div class="custom_money pl30 fl" style="width:226px;float:left;padding-left:2px;">
						<div class="pl40 fs20 fl lh48"><span class="red">*</span>�ֻ����룺</div>
						<input type="text" name="telnum" id="telnum" value="" maxlength="11" class="text_valueCard" onclick="changObj(this, 1);" onfocus="MoveLast(event);" />
					</div>
   					<div class="numboard numboard_big numboard_small2 fl" id="numBoard"style="width: 370px;float:right;padding-left:10px;">
   						<div class=" numbox clearfix"style="width:370px;float:right;clear:right;padding-left:12px;">
       						<div class="clearfix">
       							<a href="javascript:void(0)">1</a>
       							<a href="javascript:void(0)">2</a>
       							<a href="javascript:void(0)">3</a> 
       							<a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
     						</div>
       						<div class="clearfix"> 
       							<a href="javascript:void(0)">4</a>
       							<a href="javascript:void(0)">5</a>
       							<a href="javascript:void(0)">6</a>
								<a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
       						</div>
       						<div class="nleft"> 
       							<a href="javascript:void(0)">7</a>
       							<a href="javascript:void(0)">8</a>
       							<a href="javascript:void(0)">9</a> 
       							<a href="javascript:void(0)">x</a>
       							<a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
       						</div>
       						<div class="nright"> 
       							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;"></a> 
       						</div>
       						<div class="blank10"></div>
   						</div>
   					</div>
   				</div>
			</div>
		</div>
		<div class="i_tips ml20 fl" style="margin-top:-170px">
	    	<p class="tit_arrow mt10">
	   		<span class=" bg"></span>��ܰ��ʾ��
	           <p class="pl40" style="font-size:14px; position:relative; width:635px;">
	           	<s:if test="null != tipsList && tipsList.size > 0">
	           		<s:iterator value="tipsList">
	           			<%-- html��ǩת��--%>
	           			<s:property value="description" escape="false"/>
	           		</s:iterator>
	           	</s:if>
	           	<s:else>
	            	1���ֻ�����Ϊ������Ϣ�����������ȷ��д��<br />
	
					2����������˶�������ֻ�������Ϣ���мۿ�����ɹ����мۿ�������Զ��ŵ���ʽ���͵����ֻ������ϡ�<br />
	           	</s:else>
			</p>
	   </div>
	</div>
	
	<%@ include file="/backinc.jsp"%>
</form>
	<script type="text/javascript">
	
	// �ύ��־
	var submitFlag = 0;
	
	// �����ɿ�
	document.onkeyup = pwdKeyboardUp;
	
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//����
		if (key == 220 || key == 82) 
		{
			goback("<s:property value='curMenuId' />");
		}
	}
	
	// �������ҳ��ĸ�����ʾ
	document.getElementById("highLight1").className = "on";
	
	var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
	var type = 1;
	
	// �ֻ�����
	var numBoardText = document.getElementById('telnum');
	
	for (i = 0; i < numBoardBtns.length; i++)
	{
		if (!numBoardBtns[i].className) 
		{
			numBoardBtns[i].className = '';
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
		
		numBoardBtns[i].onmouseup = function()
		{					  				
			changValue(0, this.innerHTML);
					
			this.className = '';
					
			if (pangu_getStrLen(trim(numBoardText.value)) == 11 ) 
			{
				return false;
			}			      							   
		}
	}
	
	function changObj(o, t)
	{
		type = t;
		
		document.getElementById("errMsg").innerHTML = "";
		o.focus();
		
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
		else if (type == 1 && !isNaN(v) && numBoardText.value.length < 11)
		{			
			numBoardText.value += v;																		
		}
		
		var r = numBoardText.createTextRange(); 
		r.collapse(false); 
		r.select();
	}
	
	// �ж��ַ����ĳ���
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
	
	//ȷ�Ϲ����мۿ��û����ֻ�����
	function doSub()
	{
		var telNum = document.getElementById("telnum");
		
		// �ж��ֻ������Ƿ���������������ϵĻ�������ʾ�򣬷��ϵĻ�ҳ����ת��ѡ���мۿ�ҳ��
		if("" == telNum.value ||��pangu_getStrLen(telNum.value) < 11)
		{
			alertRedErrorMsg("������11λ���ֻ����룡");
		}
		else
		{
			document.actform.action = "${sessionScope.basePath}valueCard/chooseValueCard.action";
			document.actform.submit();
		}
	}
	
	function goback(curmenu) 
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.actform.action="${sessionScope.basePath }reception/receptionFunc_hub.action";
			document.actform.submit();				
		}			
	}
	
	// ҳ������ʱ�������֤��������ȡ�û����֤��Ϣ
	function bodyLoad()
	{
	    document.getElementById("telnum").focus();
	}
			
	</script>
</body>
</html>
