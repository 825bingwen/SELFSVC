<%--create by sWX219697 2014-6-10 OR_huawei_201404_1118 ɽ��_[�����ն�]_֧�Ŵ����̿��г�ֵ���� --%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG); %>   

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
		
		document.onkeydown = pwdKeyboardDown;
		
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
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//ȷ��
			if (key == 13 || key == 89 || key == 221) 
			{
				checkMoney();
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
		
		//�ύ
		function doSub()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
		        document.actform.action = "${sessionScope.basePath}agentCharge/dutyInfo.action";
		        document.actform.submit();
			}
		}
		
		//��ֵ���У��	
		function checkMoney() 
		{
			if (document.getElementById("yingjiaoFee").value == '')
				//|| parseInt(document.getElementById("yingjiaoFee").value) < parseInt("<s:property value='minMoney'/>"))
			{	
				alertError("�������ֵ���");
				document.getElementById("yingjiaoFee").value = '';
			 	return;
			}
			
			//����
			var servRegion = document.actform.servRegion.value;
			
			//��Ŀ����
			var subjectId = document.actform.subjectId.value;
			
			//��ֵ��� Ԫ
			var yingjiaoFee = document.getElementById("yingjiaoFee").value;
			
			//�������˻�����
			var agentAccount = document.actform.agentAccount.value;
			
			//��������֯��������
			var orgId = document.actform.orgId.value;
			
			//�˵����
			var curMenuId = document.getElementById("curMenuId").value;
			var postStr ="agentAccount="+agentAccount+"&yingjiaoFee="+yingjiaoFee+"&subjectId="+subjectId
							+"&servRegion="+servRegion+"&curMenuId="+curMenuId+"&orgId="+orgId;   
			var url1 = "${sessionScope.basePath}agentCharge/checkBeforeCharge.action";
			
			//���ó�ֵ���У�鷽��
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
						
				var resXml1 = this.req.responseText;
				var resArray = resXml1.split('~~');
				var checkMsg = resArray[1];
				
				//У��ɹ�
				if (resArray[0] == 1 || resArray[0] == "1")
				{
					//�ύ
					doSub();
		        }
		        
				//У��ʧ��
				else
				{
					document.getElementById("yingjiaoFee").value = '';
					alertError(resArray[1]);
					return false;
				}								
			}, null, "POST", postStr, null);
			 
			
		}

		//������һҳ��ť
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].action = "${sessionScope.basePath }agentCharge/agentChargePage.action";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('yingjiaoFee').focus();">
		<form name="actform" method="post">			
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />"/>
			<input type="hidden" id="agentName" name="agentName" value="<s:property value='agentName' />"/>
			<input type="hidden" id="agentAccount" name="agentAccount" value="${agentAccount }"/>
			<input type="hidden" id="subjectId" name="subjectId" value="<s:property value='subjectId' />"/>
			<input type="hidden" id="orgId" name="orgId" value="<s:property value='orgId' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>�����̽�������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
    					<a href="javascript:void(0)" class="on">2. ������</a> 
    					<a href="javascript:void(0)">3. ��������</a>
    					<a href="javascript:void(0)">4. ����������</a> 
    					<a href="javascript:void(0)">5. ��������</a>
    					<a href="javascript:void(0)">6. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60" style="height:30px"></div>
    					<div class="p40 pr0">
    						<p class=" tit_info"><span class="bg"></span>���������ƣ�<span class="yellow">${agentName}</span></p>
    						<p class=" tit_info"><span class="bg"></span>�ʽ��˺ţ�<span class="yellow">${agentAccount}</span></p>
    						<p class=" tit_info"><span class="bg"></span>���ѷ�ʽ��<span class="yellow">������</span></p>
    						<div class="blank10"></div>
    						<div class="line"></div> 
    						<div class="blank10"></div>
    						<div class="custom_money pl30"><span class="pl40 fs20 fl lh48">�������</span>
          						<input type="text" maxlength="10" id="yingjiaoFee" name="yingjiaoFee" class="text1 fl" onfocus="MoveLast(event);" value='' />
        					</div>
        					<div class="keyboard_wrap mt10 pl20 clearfix">
        						<div class="fl btn_back_box pt200">
        							<p class="pl10 fs16">&nbsp;&nbsp;</p>        							
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
            								<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="checkMoney();return false;">1</a> 
            							</div>
            							<div class="blank10"></div>
          							</div>
        						</div>
        						<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('yingjiaoFee');
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
										if ((v == 0 && numBoardText.value.length == 0)
												|| numBoardText.value.length >= 10)
												//|| (parseInt(numBoardText.value + v) > 10000))
						  				{
						  					
						  				}
						  				else
						  				{
						  					numBoardText.value += v;
						  				}																			
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
