<%--
 @User: zWX176560
 @De: 2013/9/17
 @comment: ��ʾ�û���ʵ��Ϣ
 @remark: create zWX176560 ʱ�� 2013/9/17 OR_SD_201309_66��������������ǩԼ���ѣ����ܰ󶨣���֧�����󣨷����������� �汾��(R003C11L08n01)
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	// �ն˻���Ϣ
	TerminalInfoPO terminalInfo2 = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    
	// �ؼ�֧�ֱ�־
	String termSpecial = terminalInfo2.getTermspecial();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Expires" CONTENT="0"/>
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/IdCardBook.js"></script>
	</head>
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post">		
			<!-- ���� -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- ֤������ -->
			<input type="hidden" id="id_type" name="bindBankCardPO.id_type" value="<s:property value='bindBankCardPO.id_type' />"/>
			<!-- ֤���������� -->
			<input type="hidden" id="idCardTypeText" name="bindBankCardPO.idCardTypeText" value="<s:property value='bindBankCardPO.idCardTypeText' />"/>
			<!-- ���п����� -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="0"/>
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />"/>
			<%@ include file="/titleinc.jsp"%>
		 	<div class="main ycz" id="main">
		  		<%@ include file="/customer.jsp"%>
		        	<div class="b966 hidden" id="ycz">
					<div id="b_authen3_1" class="b_authen3_1"></div>
					<div class="blank30"></div>
						<div class=" pl62">
							<p class="tit_info" align="left"><span class="bg"></span>�׳�ֵǩԼ</p>
							<div class="blank20 ml45" id="errorMsg"></div>
							<!--<div class="blank20"></div>-->
							<!--����+�����+��ܰ��ʾ-->
							<div class="keyboard_wrap authentication clearfix">
								<ul class="phone_num_list fl">
									<li class="sms_bg_1 fs20 mt15 clearfix " id="phone_input_1"><span class="fl lh75">���֤���룺</span>
										<input type="text" name="certid" id="certid" maxlength="18" class="text1 fl fs20" onfocus="MoveLast(event);" />
										<a href="javascript:void(0)" id="manuInput" class="btn_104 mt10" onmousedown="this.className='btn_104_hover fl mt10';" onmouseup="this.className='btn_104 fl mt10'; " onclick="inputCertid();return false;">�ֶ�����</a>
										<!--С����-->
										<div class="numboard numboard_big fl" id="numBoard" style="display:none">
											<div class=" numbox">
												<div class="blank10"></div>
												<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a> <a href="javascript:void(0)" class="func1" name="functionkey" id="numBoardBack" onmousedown="this.className='func1on'" onmouseup="this.className='func1';changValue(-1);"></a>
												<div class="clear"></div>
												<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a> <a href="javascript:void(0)" class="func2" name="functionkey" id="numBoardClear" onmousedown="this.className='func2on'" onmouseup="this.className='func2';changValue(-2);"></a>
												<div class="clear"></div>
												<div class="nleft"> <a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a> <a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a href="javascript:void(0)">#</a> </div>
												<div class="nright"> <a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'; inputCertidFinish();">1</a> </div>
												<div class="blank10"></div>
											</div>
										</div>
										<script type="text/javascript">
										var numBoardBtns=document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
										var numBoardText=document.getElementById('certid');
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
										
										// �����������������䵽�������
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
											else if (numBoardText.value.length < 18)
											{			
												numBoardText.value += v;																		
											}
											
											var r = numBoardText.createTextRange(); 
											r.collapse(false); 
											r.select();
										}
										 
										</script> 
										<!--С����end--> 
									</li>
									<li class="clearfix pt10 hidden" id="ycz_sd_text"><p class="fl pl62 fs18 lh30 yellow ml70">���ֶ��������֤����</p></li>
									
									<li class="clearfix pt20 hidden">
										<span class="fl lh75 fs18 width128 tr">�����ͣ�</span>
							  			<input type="button" id="debitCard" class="btn_on white" value="[��]��ǿ�" onmousedown="this.className='btn_off white'" onmouseup="this.className='btn_off white';" onclick="selectCard(0); return false;"/>
									    <input type="button" id="creditCard" class="btn_off white" value="[  ]���ÿ�" onmousedown="this.className='btn_off white'" onmouseup="this.className='btn_off white';" onclick="selectCard(1); return false;"/>
									</li>
									
									<li class="clearfix hidden  mt20">
										<span class="fl lh75 fs18 width128 tr"></span>
										<a href="javascript:void(0)" id="sd_btn" class=" btn_104" onmousedown="this.className='btn_104_hover'" onmouseup="this.className='btn_104';" onclick=" doSub();return false;">ǩԼ</a>
									</li>
									
									<li class="i_tips">
								  		<p class="tit_arrow"><span class="bg"></span>��ܰ��ʾ��
									       <s:if test="userInfoTips != ''">
									       		<p class="tit_arrow_hide">
									       			<s:property value = 'userInfoTips' escape = 'false'/>
									       		</p>
									       	</s:if>
									       	<s:else>
										       	<p class="tit_arrow_hide">
										       		1."�׳�ֵ��ԭ���ſ��֧����ҵ��Ŀǰ���ޱ�ʡ�ͻ�ʹ�ã��ݲ�����ʡ�ƶ��ͻ��ṩ�˷���<br />
													<%-- 2.Ŀǰ���ÿ�ǩԼ�Ĳ���Ƚ�ǿ���Ϊ��㡢�ײ�������֧�ֵ����и��࣬����Ƽ�������ʹ�����ÿ����ֻ��Ž���ǩԼ��<br /> --%>
													2."�׳�ֵ"ҵ��Ŀǰ�ݲ�֧���˷ѡ�<br />
													3.���ɻ��ѳɹ���,�ͻ�����3������ƾ������������֤���ֻ����������Ӫҵ������Ʊ�����͵Ļ��Ѳ������ṩ��Ʊ��������ҵ�������ɷ���Ŀ����ɷ���ҵ��λ��ȡ��Ʊ��
												</p>
											</s:else>
								        </p>
									</li>
							      	
								</ul>
							</div>
						</div>
						<div class="blank10"></div>
					</div>
				</div>
		    </div>                
			<%@ include file="/backinc.jsp"%>		
		</form>
		<script type="text/javascript">
			
			// �Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
			var is2GIDFlag = <%=termSpecial.charAt(8)%>;	
			
			// �����ȴ�ʱ��
			var limitTime = 30;
			
			// �������ʣ��ʱ��ľ��
			var timeToken;
			
			// �ȴ������ľ��
			var waitingToken;
			
			// ��ʼ�����֤�������߳�������־��1����������0��δ���������Ϊ1ʱ���û�����ȡ������������ùر����֤�Ķ����ӿ�
			var initCardReader = 0;
			
			// ��ֹҳ���ظ��ύ
			var submitFlag = 0;
			
			// �ύ
			function doSub() 
			{
				var certid = document.actform.certid.value;
				
				// У�����֤����
				if (certid == "")   
				{
					alertRedErrorMsg("������֤���š�");
					return false;
				}
				
				// У���������֤����Ǽǵ�֤�������Ƿ����
				if (document.actform.idCardNum.value != certid)
				{
					alertRedErrorMsg("��֤������Ǽǵ�֤�����벻����");
				 	return false;
				}
				
				if (submitFlag == 0)
				{
					submitFlag = 1;
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath }bindBankCard/writeBankCardInfo.action";
					document.actform.submit();
				}			
			}
			
			// ѡ�����п�����
			function selectCard(type)
			{	
				if (type == 0)// ���п�
				{
					document.getElementById('debitCard').value = '[��]��ǿ�';
					document.getElementById('creditCard').value = '[  ]���ÿ�';
					document.getElementById('debitCard').className = 'btn_on white';
					document.getElementById('creditCard').className = 'btn_off white';
					document.getElementById('bankCardType').value = "0";
				}
				else if (type == 1)// ���ÿ�
				{
					document.getElementById('debitCard').value = '[  ]��ǿ�';
					document.getElementById('creditCard').value = '[��]���ÿ�';
					document.getElementById('creditCard').className = 'btn_on white';
					document.getElementById('debitCard').className = 'btn_off white';
					document.getElementById('bankCardType').value = "1";
				}
			}
			
			// ������һҳ
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					document.getElementById("curMenuId").value = menuid;
							
					document.forms[0].target = "_self";
					document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
					document.forms[0].submit();
				}
			}
			
			// ������ƶ�������������
			function MoveLast(e) 
			{
				var etarget = getEventTarget(e);
				var r = etarget.createTextRange();
				r.moveStart("character", etarget.value.length);
				r.collapse(true);
				r.select();
			}
		
			// �ֹ�����֤������
			function inputCertid() 
			{
				document.getElementById("manuInput").style.display = 'none';
				document.getElementById("numBoard").style.display = '';
				
				document.getElementById("ycz").className = 'b966 hidden';
				document.getElementById("b_authen3_1").style.display = 'block';
			}
			
			// �ֹ�����֤��������ɺ󣬵��ȷ�ϰ�ť
			function inputCertidFinish() 
			{
				document.getElementById("manuInput").style.display = '';
				document.getElementById("numBoard").style.display = 'none';
				
				document.getElementById("ycz").className = 'b966 hidden';
				document.getElementById("b_authen3_1").style.display = 'none';
			}
			
			// ҳ������ʱ�������֤��������ȡ�û����֤��Ϣ
			function bodyLoad()
			{
			    document.getElementById("certid").focus();
			    
			    // ���֤���Ų�Ϊ��ʱ���򲻵������֤������
				if (document.getElementById("certid").value != "")
				{
					return;
				}
			    
			    // �ж����֤������Ϊ"01"���֤ʱ���������֤�������豸
			    if (document.getElementById("id_type").value == "01")
			    {
			    	// �ж������ն��Ƿ�֧�����֤������
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
						document.getElementById("certid").focus();
						
						//������ҳ���˳���ť����Ӧ��ʽ
			            document.getElementById("homeBtn").disabled = false;
			            document.getElementById("quitBtn").disabled = false;
			            return;
					}
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
						document.getElementById("certid").focus();
					}
					
					// ��������˵���Ҫ�ر����֤����
					closeStatus = 3;
				}
				catch (excep) 
				{		
					setErrorInfoRegion("��ʼ�����֤�������쳣�����ֹ������������֤����Ϣ��");
					document.getElementById("certid").focus();
				}
			}
			
			//����ʱ���������
			function startclock() 
			{			
				try 
				{
					setErrorInfoRegion("�������֤��������ˢһ���������֤��ϵͳ�Ὣ�������֤�����뵽ҳ�档");
					
					// ��ȡ���֤��������
					waitingToken = setInterval("getReadCardStatus()", 1000);
					
					// ��ʱ��
					timeToken = setInterval("calLeftTime()", 1000);
				}
				catch (e) 
				{
					setErrorInfoRegion("��������ʧ�ܣ����ֹ������������֤����Ϣ��");
					document.getElementById("certid").focus();
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
						
						//������ҳ���˳���ť����Ӧ��ʽ
			            document.getElementById("homeBtn").disabled = false;
			            document.getElementById("quitBtn").disabled = false;
					} 
					else if (ret == "-1")
					{
						setErrorInfoRegion("��ȡ���֤��������ʧ�ܣ����ֹ������������֤����Ϣ��");
						document.getElementById("certid").focus();
					}
					else if (ret == "2")
					{	
						setErrorInfoRegion("֤���޷�ʶ�����ֹ������������֤����Ϣ��");
						document.getElementById("certid").focus();
					}						
				}
				catch (e) 
				{		
					setErrorInfoRegion("���֤����������ʧ�ܣ����ֹ������������֤����Ϣ��");
					document.getElementById("certid").focus();
					
					//������ҳ���˳���ť����Ӧ��ʽ
		            document.getElementById("homeBtn").disabled = false;
		            document.getElementById("quitBtn").disabled = false;
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
				
				setErrorInfoRegion("�������֤��������ˢһ���������֤��ϵͳ�Ὣ�������֤�����뵽ҳ�档ʣ��" + limitTime + "��");
				
				//����ʱ�����
				if (parseInt(limitTime) <= 0 && submitFlag == 0)
				{
		        	setErrorInfoRegion("��������ʧ�ܣ����ֹ������������֤����Ϣ��");
				}
				
				document.getElementById("certid").focus();
			        	
		    	//���ùر����֤�Ķ����ӿ�
				//������
				CloseCardReader();
				
				//���Ե�������˵�
				closeStatus = 0;
			}
			
			//���ô�����Ϣ
			function setErrorInfoRegion(errMsg)
			{
				document.getElementById("errorMsg").innerHTML = errMsg;		    
			}
		</script>
	</body>
</html>
