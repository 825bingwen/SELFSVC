<%--
 @User: zWX176560
 @De: 2013/9/17
 @comment: ��ʾ�û���ʵ��Ϣ
 @remark: create zWX176560 ʱ�� 2013/9/17 OR_SD_201309_66��������������ǩԼ���ѣ����ܰ󶨣���֧�����󣨷����������� �汾��(R003C11L08n01)
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		//8��32��66��77 ����
		//82��220 ����
		//13��89��221 ȷ��
		//80 ��ӡ
		//85 ��һҳ
		//68 ��һҳ
		
		/*
		 *��ȥ���������ߵĿո�
		 */
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
			}
			
			// �����ÿ����Ž����ж�
			var pattern1 = /^\d{16}$/;
										
			//�����ڽ����ж�
			var pattern2 = /^\d{2}$/;
			
			//��cvn2�����ж�
			var pattern3 = /^\d{3}$/;
			
			if (pattern1.test(trim(document.getElementById("bankCardNum").value))
					&& document.getElementById("expire1").value == "")
			{
				//�����꿨�ţ��Զ���ת����Ч��
				document.getElementById("expire1").focus();
				
				changObj(document.getElementById("expire1"), 3);
				
				return true;
			}
			
			if (pattern2.test(trim(document.getElementById("expire1").value)) 
					&& document.getElementById("expire2").value == "")
			{
				//��������ϣ��Զ���ת����
				document.getElementById("expire2").focus();
				
				changObj(document.getElementById("expire2"), 3);
				
				return true;
			}			
			
			if (pattern2.test(trim(document.getElementById("expire2").value)) 
					&& document.getElementById("cvn2").value == "")
			{
				//��������ϣ��Զ���ת��cvn2
				document.getElementById("cvn2").focus();
				
				changObj(document.getElementById("cvn2"), 4);
				
				return true;
			}				
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern2.test(trim(document.getElementById("expire2").value)) 
					&& document.getElementById("cvn2").value == "")
			{
				//����ʱ��cvn2������ϣ��Զ���ת����
				document.getElementById("year").focus();
				
				changObj(document.getElementById("year"), 3);
				
				return true;
			}
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}

		function doSub()
		{
			//�Կ��Ž����ж�
			var pattern1 = /^\d{16}$/;
			var creditCardNum = document.getElementById("bankCardNum").value;
			if (creditCardNum == "" || !pattern1.test(creditCardNum))
			{
				changObj(document.getElementById("bankCardNum"), 2);
				alertRedErrorMsg("��������ȷ�����ÿ�����");
				return;
			}
			
			//���·ݽ����ж�
			var pattern2 = /^\d{2}$/;
			
			var month = document.getElementById("expire1").value;
			if (month == "" || !pattern2.test(month) || month > 12 )
			{
				changObj(document.getElementById('expire1'), 3);
			
				alertRedErrorMsg("��������ȷ���·�");
				return;
			}
			
			//����ݽ����ж�
			
			var year = document.getElementById("expire2").value;
			if (year == "" || !pattern2.test(year))
			{
				changObj(document.getElementById('expire2'), 3);
			
				alertRedErrorMsg("��������ȷ�����");
				return;
			}
			
			//��cvn2�����ж�
			var pattern3 = /^\d{3}$/;
			
			var cvn2 = document.getElementById("cvn2").value;
			if (cvn2 == "" || !pattern3.test(cvn2))
			{
				changObj(document.getElementById('cvn2'), 4);
			
				alertRedErrorMsg("��������ȷ��cvn2");
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;

				openWindow_wait('pls_wait');
				
				// ���úͰ��׳�ֵ��������
				document.getElementById("commitFlag").value = "1";
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/getBindBankType.action";
				document.forms[0].submit();
			}	
		}
		
		// ��һҳ
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }bindBankCard/checkLoginUserIsFactUser.action";
				document.forms[0].submit();
			}
		}	
		</script>
	</head>
	<body scroll="no" onload="selectBank();" >
		<form name="actform" method="post">
		
			<s:hidden id="commitFlag" name="heBaoCommitFlag"/>
			<!-- ��Ƭ���� 0:��ǿ� 1:���ǿ� -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="<s:property value='bindBankCardPO.bankCardType' />" />
			<!-- ���� -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />" />
			<!-- ֤������ -->
			<input type="hidden" id="id_type" name="bindBankCardPO.id_type" value="<s:property value='bindBankCardPO.id_type' />" />
			<!-- ֤���������� -->
			<input type="hidden" id="idCardTypeText" name="bindBankCardPO.idCardTypeText" value="<s:property value='bindBankCardPO.idCardTypeText' />" />
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />" />
			<!-- ���п����� -->
			<s:hidden name="bindBankCardPO.bankAbbr" id="bankAbbr" />
			<!-- ������֤������ 1ǩԼ 3��Լ -->
			<s:hidden name="smsType" name="smsType" value="1"/>
			<!-- ֤�����ͣ��Ͱ��׳�ֵʹ�ã� -->
			<input type="hidden" id="idCardType" name="bindBankCardPO.idCardType" value="00"/>

			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="b966" id="b966" style="margin-top:-10px;">
					<div class="pt20 pl31">
						<!-- �б����� -->
						<p class="tit_info" align="left">
							<span class="bg"></span>�׳�ֵǩԼ-���ÿ�
						</p>
					</div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix" style="position:relative;margin-top:-10px;">
							<ul class="phone_num_list fl" id="phone_num_list" style="">
								<li class="on fs20  clearfix pt30" id="phone_input_1">
									<span class=" pl62 fl lh48">���У�</span>
									<input type="text" id="numBoardText1" readOnly="true" class="text3_1 fl"
										onclick="changObj(this,1);selectBank()" value="����ѡ������" />
									<div id="yh_icon" class="nh_1">
										<div class="yh_show_title">
											�迪ͨ����֧������
										</div>
										<a href="javascript:void(0)" class="btn_104 ml140 fl mt5" style="margin-top:-13px;"
											onmouseup="changObj(this,1); selectBank()" >����</a>
									</div>

								</li>
								<li class="fs20 clearfix pt30" id="phone_input_2">
									<span class="pl20 fl lh75">���ÿ��ţ�</span>
									<input type="text" style="font-size:22px;" maxlength="16" id="bankCardNum" name="bindBankCardPO.bankCardNum" onclick="changObj(this, 2);MoveLast(this);" onfocus="MoveLast(this);" class="text1 fl relative"   />
								</li>
								<li class="fs20 clearfix pt30" id="phone_input_3">
									<span class=" pl40 fl lh75">��Ч�ڣ�</span>
									<input type="text"  maxlength="2" id="expire1" name="expire1" onclick="changObj(this, 3);MoveLast(this);" onfocus="MoveLast(this);" class="text11 fl relative"   />
									<span class="fl lh75 mr5">��</span>
									<input type="text"  maxlength="2" id="expire2" name="expire2" onclick="changObj(this, 3);MoveLast(this);" onfocus="MoveLast(this);" class="text11 fl relative"   />
									<span class="fl lh75">��</span>
								</li>
								<li class="fs20 clearfix pt30" id="phone_input_4">
									<span class="pl20 fl lh75 pl40">CVN2��</span>
									<input type="text" maxlength="3" id="cvn2" name="bindBankCardPO.cvn2" onclick="changObj(this, 4);MoveLast(this);" onfocus="MoveLast(this);" class="text1 fl relative"   />
								</li>



							</ul>
							<!--�����б�-->
							<div class="numboard numboard_big fr" id="yh_board" style="display: none;">
								<div class="numbox">
									<div class="blank10"></div>
									<div class="yh" id="yhDiv">
										<div class="yh_box" >
											<s:iterator value="cardInfoList">
					                			<a href="javascript:void(0);" style="line-height:0px;" onclick=" bankShow('<s:property value='imgPath' />','<s:property value='bankId' />' );">
					                				<img src="../images/bankPic/<s:property value='imgPath' />" />
					                			</a>
					                		</s:iterator>
										</div>
									</div>
									<div class="box70W" style=" position:absolute; z-index:6; right:0px; top:40px; height:340px;">
										<input type="button" class="btnUp" onclick="myScrollDialog.moveUp(30)"  />
										<div class="div75w220h">
											<div class="blank10px"></div>
											<div class="box66W tc f16 div66w36h" id="moveDom" style="top:52px; left:2px; height:30px" >0%</div>
										</div>
										<input type="button" class="btnDown" onclick="myScrollDialog.moveDown(30)"/>
									</div>
									<div class="blank10"></div>
								</div>
							</div>

							<!--�����б�end-->

							<!--С����-->
	        				<div class="numboard numboard_big fl" id="numBoard" style="display:none">
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
	        				
	        				<%--add begin l00190940 2011/12/12 OR_SD_201111_370 Ϊ����������֤������ڴ���ҳ�� --%>
							<!--������ ���ڴ��� ���Ժ�-->
							<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
								<div class="bg"></div>
								<p class="mt40">
									<img src="${sessionScope.basePath }images/loading.gif" alt="������..." />
								</p>
								
								<%-- modify begin hWX5316476 2015-6-27 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
								<p class="tips_txt">
									<%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ������Ժ�......") %>
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
							<%--add end l00190940 2011/12/12 OR_SD_201111_370 Ϊ����������֤������ڴ���ҳ�� --%>	
	        				
	        				<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('bankCardNum');
								for (i = 0; i < numBoardBtns.length; i++)
								{
						    		if (!numBoardBtns[i].className) 
						    		{
						    			numBoardBtns[i].className='';
						    		}
						    		//alert (numBoardBtns[i].getAttribute("name")+"|"+numBoardBtns[i].id+"|"+numBoardBtns[i].className);
						    		// firfox�»�ȡname����ֵ��getAttribute("name"),������ֱ����obj.name
					     			if (numBoardBtns[i].getAttribute("name") == 'functionkey')
					     			{
					     				continue;  
					     			}
						
									numBoardBtns[i].onmousedown = function(){
							 			this.className = 'on';
									}
									
									numBoardBtns[i].onmouseup = function(){
										changValue(0, this.innerHTML);
										
							  			this.className = '';
							  			
							  			var pattern1 = /^\d{16}$/;
										
										//�����ڽ����ж�
										var pattern2 = /^\d{2}$/;
										
										//��cvn2�����ж�
										var pattern3 = /^\d{3}$/;
										
										if (pattern1.test(trim(document.getElementById("bankCardNum").value))
												&& document.getElementById("expire1").value == "")
										{
											//�����꿨�ţ��Զ���ת����Ч��
											document.getElementById("expire1").focus();
											
											changObj(document.getElementById("expire1"), 3);
											
											return true;
										}
										
										if (pattern2.test(trim(document.getElementById("expire1").value)) 
												&& document.getElementById("expire2").value == "")
										{
											//��������ϣ��Զ���ת����
											document.getElementById("expire2").focus();
											
											changObj(document.getElementById("expire2"), 3);
											
											return true;
										}			
										
										if (pattern2.test(trim(document.getElementById("expire2").value)) 
												&& document.getElementById("cvn2").value == "")
										{
											//��������ϣ��Զ���ת��cvn2
											document.getElementById("cvn2").focus();
											
											changObj(document.getElementById("cvn2"), 4);
											
											return true;
										}	
													
									}					
								}
						
								function changObj(o, t)
								{
									lastObj = o;
							
									if(t==1)
									{
										document.getElementById('phone_input_1').className = "on fs20  clearfix";
										document.getElementById('phone_input_2').className = "fs20  clearfix";
										document.getElementById('phone_input_3').className = "fs20  clearfix";
										document.getElementById('phone_input_4').className = "fs20  clearfix";
									}
									else if(t==2)
									{
										document.getElementById('phone_input_1').className = "fs20  clearfix";
										document.getElementById('phone_input_2').className = "on fs20  clearfix";
										document.getElementById('phone_input_3').className = "fs20  clearfix";
										document.getElementById('phone_input_4').className = "fs20  clearfix";
									}
									else if(t == 3)
									{
										document.getElementById('phone_input_1').className = "fs20  clearfix";
										document.getElementById('phone_input_2').className = "fs20  clearfix";
										document.getElementById('phone_input_3').className = "on fs20  clearfix";
										document.getElementById('phone_input_4').className = "fs20  clearfix";
									}
									else
									{
										document.getElementById('phone_input_1').className = "fs20  clearfix";
										document.getElementById('phone_input_2').className = "fs20  clearfix";
										document.getElementById('phone_input_3').className = "fs20  clearfix";
										document.getElementById('phone_input_4').className = "on fs20  clearfix";
									}
								}					
						
								function changValue(t, v)
								{
									if (t == -1)
									{
										lastObj.value = lastObj.value.slice(0, -1);
									}
									else if(t == -2)
									{
										lastObj.value = ""
									}
									else if (!isNaN(v) && lastObj.id == 'bankCardNum' && lastObj.value.length < 16)
									{								
										lastObj.value += v;									
									}
									else if (lastObj.value.length < 2 && !isNaN(v) && lastObj.id == 'expire1')
									{
										lastObj.value += v;
									}
									else if (lastObj.value.length < 2 && !isNaN(v) && lastObj.id == 'expire2')
									{
										lastObj.value += v;
									}
									else if (lastObj.value.length < 3 && !isNaN(v) && lastObj.id == 'cvn2')
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
						<!--keyboard_wrap end-->
						<div class="blank10"></div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
		
		<script type="text/javascript">
			
			// �ֱ��û��Ƿ�ѡ��������Ϣ
			var clickFlag = 0;
			
			var list=document.getElementById("phone_num_list");
			var lis=list.childNodes;
			
			for(var i=0;i<lis.length;i++)
			{
				lis.item(i).style.position="absolute";
				lis.item(i).style.height="103px";
				lis.item(0).style.top="0px";
				lis.item(1).style.top="100px";
				lis.item(2).style.top="200px";
				lis.item(3).style.top="300px";
			}
			document.getElementById("yh_board").style.marginTop="0px";
		
			// ѡ�����к�չʾ����ͼƬ
			function bankShow(imgPath, bankId)
			{	
				document.getElementById("bankAbbr").value = bankId;
				document.getElementById("numBoardText1").style.background = '';
				document.getElementById("numBoardText1").style.display = 'none';
				document.getElementById("yh_icon").style.background = "url(../images/bankPic/" + imgPath + ") no-repeat center #fff ;";
				document.getElementById("yh_icon").style.display = "block";
				document.getElementById("numBoard").style.display = "block";
				document.getElementById("yh_board").style.display = "none";
				if(lastObj!=document.getElementById('bankCardNum'))
				{
					changObj(document.getElementById('bankCardNum'),2);
				}
				clickFlag = 1;
				document.getElementById("numBoardText1").disabled = "true";
				lis.item(0).className="fs20 clearfix pt30 ";
				lis.item(1).className+=" on";
			}
			
			// ѡ��������Ϣ
			function selectBank()
			{
				document.getElementById("numBoard").style.display = "none";
				
				if(clickFlag == 1)
				{
					document.getElementById("yh_icon").style.display = "block";
					clickFlag = 0;
				}
				
				document.getElementById("yh_board").style.display="block";
				var b966 = document.getElementById("b966");
				b966.className += " mt10";
				
				var list = document.getElementById("phone_num_list");
				var lis = list.childNodes;
				for(var i=0;i<lis.length;i++){
					lis.item(i).style.paddingTop="30px";
				}
				
				myScrollDialog = new virtualScroll("yhDiv", "moveDom");
			}
			
			function turn_3()
			{	
				document.getElementById("numBoardText3").focus();
				lis.item(1).className="fs20 clearfix pt30 ";
				lis.item(2).className+=" on";
				document.getElementById("rt1").style.display="none";
				document.getElementById("rt2").style.display="block";
			};
			
			function turn_4()
			{		
				document.getElementById("numBoardText4").focus();
				document.getElementById("rt2").style.display="none";
				document.getElementById("rt3").style.display="block";
			};
			function turn_5()
			{	
				document.getElementById("numBoardText5").focus();
				document.getElementById("numBoardText5").value="";
				document.getElementById("numBoardText5").style.fontSize="28px";
				lis.item(2).className="fs20 clearfix pt30 ";
				lis.item(3).className+=" on";
			};
	
	 </script>
	</body>
</html>