<%--
 @User: ����/zKF69263
 @De: 2015/03/30
 @comment: �����ն�ʵ���ֽ���˹���
 @remark: create zKF69263 2015/03/30 R003C15L03n01 OR_SD_201502_169
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>���볮���е��ֽ���</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no" onload="doLoad();">
		<!--������ʾ��-->
	    <div class="openwin_tip openwin_tip" id="openWin1" style="">
		    <div class="bg"></div>
		    <div class=" blank60"></div>
		    <div class=" blank40"></div>
		    <div class="  blank10n"></div>
		    <p class="tc fs26">������Ľ���ϵͳͳ�ƵĽ���ͬ����ȷ��������</p>
		    <p class="tc fs26 pt30">��������ȷ��������������ȡ�������������</p>
		    <div class="tc">
		    <div class=" blank40"></div>
		    <a  class=" bt4"  class=" bt4" onmousedown="this.className='bt4on';qdbuttonClick()" onmouseup="this.className='bt4'" >ȷ��</a>
		    <a  class=" bt4 ml20"  onmousedown="this.className='bt4on ml20';" onmouseup="this.className='bt4 ml20';wiWindow.close();">ȡ��</a>
		    </div>
		</div>
		<form name="actform" method="post">
			
			<%-- ���˽���ʱ�� --%>
			<input type="hidden" name="auditEndTime" value="<s:property value='auditEndTime' />">
			<%-- ���˽���ʱ������ --%>
			<input type="hidden" name="auditEndTimeFen" value="<s:property value='auditEndTimeFen' />">
			<%-- �ϴλ��˿�ʼʱ������ --%>
			<input type="hidden" name="lastAuditStartTimeFen" value="<s:property value='lastAuditStartTimeFen' />">
			<%-- �ϴλ��˽���ʱ�� --%>
			<input type="hidden" name="lastAuditEndTime" value="<s:property value='lastAuditEndTime' />">
			<%-- �ϴλ��˽���ʱ������ --%>
			<input type="hidden" name="lastAuditEndTimeFen" value="<s:property value='lastAuditEndTimeFen' />">
			<%-- ϵͳͳ�ƽ�� --%>
			<input type="hidden" name="sysMoney" value="<s:property value='sysMoney' />">
			<%-- ����id --%>
			<input type="hidden" name="auditId" value="<s:property value='auditId' />">
			<%-- �˵���ӡ��ʶ --%>
			<input type="hidden" name="updateFlag" value="<s:property value='updateFlag' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					<div class=" p20">						
						<p class="fs22 mb30"></p>
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="audit_info fl">
          						<!-- ֮ǰδ���й��ֽ���ˣ�����ʾ�ϴλ���ʱ��� -->
          						<s:if test="lastAuditStartTimeFen == '' && lastAuditEndTimeFen == ''">          							
          						</s:if>
          						<!-- ��һ�ν����ֽ����ʱ����û�п�ʼʱ��� -->
          						<s:elseif test="lastAuditStartTimeFen == ''">
          							<li class="fs18 clearfix">
	          							�ϴλ���ʱ��Σ���ʼ-<s:property value="lastAuditEndTimeFen" />
	          						</li>
          						</s:elseif>
          						<!-- ���״ν����ֽ���� -->
          						<s:else>
          							<li class="fs18 clearfix">
	          							�ϴλ���ʱ��Σ�<s:property value="lastAuditStartTimeFen" />-<s:property value="lastAuditEndTimeFen" />
	          						</li>
          						</s:else>
          						
          						<!-- �״ν����ֽ����ʱ����û�п�ʼʱ��� -->
          						<s:if test="lastAuditEndTimeFen == ''">
	          						<li class="fs18 clearfix">
	          							���λ���ʱ��Σ���ʼ-<s:property value="auditEndTimeFen" />
	          						</li>
          						</s:if>
          						<!-- ���״ν����ֽ����ʱ���ϴλ��˽���ʱ����Ǳ��λ��˿�ʼʱ�� -->
          						<s:else>          							       						
          							<li class="fs18 clearfix">
          								���λ���ʱ��Σ�<s:property value="lastAuditEndTimeFen" />-<s:property value="auditEndTimeFen" />
          							</li>
          						</s:else> 
          						
          						<li class="fs18 clearfix">
          							&nbsp;&nbsp;&nbsp;&nbsp;ϵͳͳ�ƽ�<s:property value="sysMoney" />Ԫ
          						</li>
        					</ul>
        					<!--С����-->
	        				<div class="numboard numboard_big fl" id="numBoard">
	          					<div class="custom_money pt10">
	          						<span id="redstar1" class="pl20 fs18 fl lh48">����ʵ�ʽ�</span>
	          						<s:if test="1 == updateFlag">
	          							<input type="text" id="realMoney" name="realMoney" value="<s:property value='realMoney' />" class="text1 fl relative" onfocus="MoveLast(event);" readonly="true" />
	          						</s:if>
	          						<s:else>
	          							<input type="text"  id="realMoney" name="realMoney" class="text1 fl relative" onclick="changObj(this, 2);" onfocus="MoveLast(event);" maxlength='6' />
	          						</s:else>
        						</div>
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
								<%
									}
								%>
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[1].getElementsByTagName('a');
								var lastObj = document.getElementById('realMoney');
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
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									lastObj = o;
									
								}					
						
								function changValue(t, v)
								{
									<s:if test="1 == updateFlag">
										return;
									</s:if>
									
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (lastObj.value.length < 6 && !isNaN(v))
									{								
										lastObj.value += v;									
									}
									if ("undefind" != lastObj.id)
									{
										document.getElementById(lastObj.id).focus();
									}
								}
	              			</script>
	        				<!--С����end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
		</form>
	</body>
</html>
<script type="text/javascript">
		
	var submitFlag = 0;
	document.onkeydown = pwdKeyboardDown;
	
	// ���̰����¼�
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
	
	// У��-ֻ��������0-9����
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
	
	// ����̧���¼�
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
		return true;
	}	
	
	// �ƶ������
	function MoveLast(e) 
	{
		var etarget = getEventTarget(e);
		var r = etarget.createTextRange();
		r.moveStart("character", etarget.value.length);
		r.collapse(true);
		r.select();
	}
	
	var checkSubmitFlag = false;
	
	// ȷ���ύ
	function doSub()
	{
	    if (checkSubmitFlag)
	    {
	        document.getElementById('errorMsg').innerHTML = '�����ظ��ύ��';
	        return ;
	    }
	    
		var realMoney = document.getElementById("realMoney").value;
		if (realMoney == "")
		{
			changObj(document.getElementById('realMoney'), 2);
			document.getElementById('errorMsg').innerHTML = '������ӳ�����ȡ�õ��ֽ�����';
			return ;
		}
		
		if (realMoney != parseInt("<s:property value='sysMoney' />"))
		{
		    wiWindow = new OpenWindow("openWin1",508,192);
		    return; 
		}
		
		checkSubmitFlag = true;
		
		// �����ȴ�ͼ��
		openRecWaitLoading("recWaitLoading");
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"ȷ�ϻ��ˣ�����ʵ�ʽ����ϵͳͳ�ƽ��һ�¡�");
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}managerOperation/insertSDAuditLog.action";
		document.actform.submit();	
	}
	
	// ȷ���ύ
	function qdbuttonClick()
	{
	    if (checkSubmitFlag)
	    {
	        return ;
	    }
	    
	    checkSubmitFlag = true;
	    
		wiWindow.close();
		
		// �����ȴ�ͼ��
		openRecWaitLoading("recWaitLoading");
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"ȷ�ϻ��ˣ�����ʵ�ʽ����ϵͳͳ�ƽ�һ�¡�");
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}managerOperation/insertSDAuditLog.action";
		document.actform.submit();
	}
	
	// ����
	function goback()
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath }managerOperation/selectOperationType.action";
			document.actform.submit();
		}
	}
	
	// ��ʼ����
	function doLoad()
	{	
		// �����˵�������
   		closeStatus = 1;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
				"�����ֽ����");
				
		document.getElementById('realMoney').focus();
	}
</script>