<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content=""/>
		<meta http-equiv="description" content=""/>
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />		
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
			var submitFlag = 0;
			
			document.onkeydown = pwdKeyboardDown;
			
			//�������̰����¼�
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
			
			document.onkeyup = pwdKeyboardUp;
			
			//���������ͷ��¼�
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
					goback("<s:property value = 'curMenuId'/>");
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
				}			
			}
			
			function MoveLast(e) 
			{
				var etarget = getEventTarget(e);
				var r = etarget.createTextRange();
				r.moveStart("character", etarget.value.length);
				r.collapse(true);
				r.select();
			}
	
			// �ύ
			function doSub() 
			{
				//��ֹ�ظ�����
				if (submitFlag == 0) 
				{
					submitFlag = 1;
					
					openRecWaitLoading();
			  		document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}cardInstall/telnumResult.action";
					document.actform.submit();
				}
			}
	
			// ����
			function goback(curmenu) 
			{
				//��ֹ�ظ�����
				if (submitFlag == 0)
				{
					submitFlag = 1;
			
					document.getElementById("curMenuId").value = curmenu;
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }cardInstall/selectRule.action";
					document.actform.submit();
				}
			}
			
			function focusObj()
			{
				<s:if test="#request.selTelRule == 2">
					document.getElementById('telPrefix').focus();
				</s:if>
				<s:elseif test="#request.selTelRule == 3">
					document.getElementById('telSuffix').focus();
				</s:elseif>
			}
		</script>
	</head>
	<body onload="focusObj();" scroll="no">
		<form name="actform" method="post" action="">
			<%-- ���֤��Ϣ --%>
			<%-- ���� --%>
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<%-- �Ա� --%>
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<%-- ���֤���� --%>
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<%-- ֤����ַ --%>
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			<%-- ��ʼʱ�� --%>
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
			<%-- ����ʱ�� --%>
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
			<%-- ����Ϣ --%>
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
			<%-- ��Ƭ��Ϣ --%>
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
			
			<%-- �ײ���Ϣ --%>
			<%-- ģ��ID --%>
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
			<%-- ģ������ --%>
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
			<%-- ��ƷID --%>
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
			<%-- ��Ʒ���� --%>
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
			<%-- Ʒ�� --%>
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
			<%-- �ײ��·� --%>
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
			<%-- Ԥ����� --%>
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
			
			
			<%-- ѡ����Ϣ --%>
			<%-- ���� --%>
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<%-- ��֯����ID --%>
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<%-- �������� --%>
			<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
			<%-- ѡ�Ź��� --%>
			<input type="hidden" id="selTelRule" name="selTelRule" value="<s:property value='selTelRule'/>"/>
			
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
	
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)">3. ��Ʒѡ��</a> 
	   					<a href="javascript:void(0)" class="on">4. ����ѡ��</a>
	   					<a href="javascript:void(0)">5. ���÷�������</a>
	   					<a href="javascript:void(0)">6. ����ȷ��</a>
	   					<a href="javascript:void(0)">7. �����ɷ�</a>
	   					<a href="javascript:void(0)">8. ȡ����ӡСƱ</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40 pr0">
						<s:if test="#request.selTelRule == 2">
							<p class=" tit_info"><span class="bg"></span>���������ǰ׺(�7λ����)��</p>
						</s:if>
						<s:elseif test="#request.selTelRule == 3">
							<p class=" tit_info"><span class="bg"></span>����������׺(�4λ����)��</p>
						</s:elseif>
						
						<div class="blank10"></div>
						
						<div class="custom_money pl30">
							<s:if test="#request.selTelRule == 2">
								<span class="pl40 fs20 fl lh48">ǰ׺��</span>
          						<input type="text" id="telPrefix" name="telPrefix" maxlength="7" class="text1 fl" onclick="MoveLast(event);"/>
							</s:if>
							<s:elseif test="#request.selTelRule == 3">
								<span class="pl40 fs20 fl lh48">��׺��</span>
          						<input type="text" id="telSuffix" name="telSuffix" maxlength="4" class="text1 fl" onclick="MoveLast(event);"/>
							</s:elseif>							
       					</div>
       					
       					<div class="keyboard_wrap mt10 pl20 clearfix">
        					<div class="fl btn_back_box pt200">
        						<p class="fs16 pl10">&nbsp;&nbsp;</p>        							
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
            							<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)" name="functionkey">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)" name="functionkey">#</a> 
            						</div>
            						<div class="nright"> 
            							<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
            						</div>
            						<div class="blank10"></div>
          						</div>
        					</div>
        					<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								
								var numBoardText;
								<s:if test="#request.selTelRule == 2">
									numBoardText = document.getElementById('telPrefix');
								</s:if>
								<s:elseif test="#request.selTelRule == 3">
									numBoardText = document.getElementById('telSuffix');
								</s:elseif>
								
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
										numBoardText.value = "";
									}
									else
									{								
										<s:if test="#request.selTelRule == 2">
											if ((v == 0 && numBoardText.value.length == 0) || numBoardText.value.length >= 7)
							  				{
							  					
							  				}
							  				else
							  				{
							  					numBoardText.value += v;
							  				}
										</s:if>
										<s:elseif test="#request.selTelRule == 3">
											if (numBoardText.value.length >= 4)
							  				{
							  					
							  				}
							  				else
							  				{
							  					numBoardText.value += v;
							  				}
										</s:elseif>																			
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
			<%@ include file="/backinc.jsp"%>
		</form>
		<script type="text/javascript">
			// �����ʽ
			if ("4" == "<s:property value='selTelRule'/>")
			{	
				// �ύ
				doSub();
			}
		</script>
	</body>
</html>