<%--
 @User: �뿭/fwx439896
 @De: ����(2017/11/13)
 @comment: ���������ն˿��ԤԼ�˵�������д�ͻ���Ϣҳ��
 @remark: create fwx439896 2017-11-13 OR_HUB_201708_259_��BOSS�������󡿿��ҵ��ԤԼӪ����Ŀ������ӥ�ɣ�_�������˵����   V200R005C20LG2301
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>���ԤԼ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
					<script type="text/javascript">

		var submitFlag = 0;
		
		
		
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
				return false;
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
				goback("");
				return;
			}
			//����
			else if (key == 8 || key == 32 || key == 66 || key ==77)
			{
				var etarget = getEventTarget(e);
				if (etarget.type == "text" || etarget.type == "installDate") 
				{
					etarget.value = backString(etarget.value);
				}
				if (etarget.name == 'installDate' && etarget.value == '' )
				{
					MoveLast(document.getElementById('band'));
				}
			}
			
			var tel = document.forms[0].band.value;
			var installDate = document.forms[0].installDate.value;
			
			var randomCode = document.forms[0].cardIdNum.value;	
	
			if ((key == 8 || key == 32 || key == 66 || key ==77)
			 		&& pangu_getStrLen(trim(installDate)) == 0 && pangu_getStrLen(trim(tel)) == 11) 
			{
				document.forms[0].band.focus();
				
				changObj(document.forms[0].band, 1);
				
				return true;
			}
			
 			if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(installDate)) == 0) 
 			{
 				document.forms[0].installDate.focus();
 				
 				changObj(document.forms[0].installDate, 2);
 				
 				return true;
 			}
 			
 		
			return true;
		}		
		
		function MoveLast(lastObj)
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
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
		/**
		*�ύ
		*/
		function doSub()
		{
		    //��ȡ��ǰ��������� �����֤��
			var installDate = document.getElementById("installDate").value;
			var cardIdNum = document.getElementById("cardIdNum").value;
	
			//�����ڸ�ʽ����У��
			var p=/^[0-9]{4}(0[1-9]|1[0-2])[0-3]{1}[0-9]{1}$/;
			if (installDate==""||!p.test(installDate))
			{
				alertRedErrorMsg("����ȷ���ڸ�ʽ");
				return;
			}
		
		  //getSysDate() ��ȡ��ǰϵͳʱ��   
		  //���  ��ǰʱ��  ��  ����ʱ��  ֮��   ����  ture  ��֮false
          if(getSysDate()>installDate)
          { 
        	
			  alertRedErrorMsg("�����뵱ǰʱ��֮������");

			  return;
          }    

        //У�����֤��Ϣ    
		if (cardIdNum != "" && !(/(^\d{17}([0-9]|x|X)$)/.test(cardIdNum)))  
		{ 
   		    alertRedErrorMsg("������18λ��������֤��");
			return ; 
		}

        //�ύ
		if (submitFlag == 0)
		{
			submitFlag = 1;
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}broadBandAppoint/selectBandProd.action";
			document.actform.submit();
		}
	}
		
	
		//������һҳ
		function goback(menuId)
		{
			window.history.back();
		}
		
		/***
		 * ��ȡϵͳʱ�� ��ʽ��yyyyMMdd 
		 */
		function getSysDate() {
			
			var sysDate = new Date();
			var year = sysDate.getFullYear();
			var month = sysDate.getMonth() + 1;
			if (month < 10) {
				month = "0" + month;
			}
			var day = sysDate.getDate();
			if (day < 10) {
				day = "0" + day;
			}
			return year.toString()+ month.toString() + day.toString();;
		}


		
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
		<s:hidden name="currArea" id="cur1"></s:hidden>
		<input type="hidden" name="currAreaa" value="#request.currAreaa"/>
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main" style="margin-top:-10px;">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
					
					<div class=" p40">
					<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
						<p class="fs22 mb30"></p>
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
									<i class="lh30">1.���������������λ��M��</i>
									<span class="pl20 fl lh75">�������</span>
									<input type="text" id="band" name="bandNum"
										maxlength="11" class="text1 fl relative" style="margin-left:20px;"
										onclick="changObj(this, 1);MoveLast(this);"/>
								</li>
								<li class="fs20 clearfix" id="phone_input_2">
									<i class="lh30">2. ԤԼ��װʱ�䣨��ʽ��YYYYMMdd��
									</i>
									<span class="pl20 fl lh75">ԤԼʱ�䣺&nbsp;&nbsp;</span>
									<input type="text" name="installDate" id="installDate"
										maxlength="8" class="text1 fl relative"
										onclick="changObj(this, 2);MoveLast(this);"  />
								</li>
								<li class="fs20 clearfix" id="phone_input_3">
									<i class="lh30">3. �������֤��
									</i>
									<span  id="redstar2"  class="pl20 fl lh75">���֤��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
									<input type="text" name="cardIdNum" id="cardIdNum"
										maxlength="18" class="text1 fl relative" style="font-size:22px; " 
										onclick="changObj(this, 3);MoveLast(this);"/>
								</li><%--
								<li class="fs20 clearfix">
								--%><div class="blank10"></div>
								<p class="tit_arrow">
									<span class="bg"></span>��ܰ��ʾ��
								</p>
								<p class="p20">
									ԤԼ��װʱ��Ϊ����
								</p>
								<p class="p20">
									�����������֤��ѡ��
								</p>
								<p class="p20">
									��� ȷ��  ������һ��
								</p>
								</li>
							</ul>
							
							<!--С����-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class=" numbox">
									<div class="blank10"></div>
									<a href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a
										href="javascript:void(0)">3</a>
									<a href="javascript:void(0)" class="func1" name="functionkey"
										id="numBoardBack" onmousedown="this.className='func1on'"
										onmouseup="this.className='func1';changValue(-1);"></a>
									<div class="clear"></div>
									<a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a
										href="javascript:void(0)">6</a>
									<a href="javascript:void(0)" class="func2" name="functionkey"
										id="numBoardClear" onmousedown="this.className='func2on'"
										onmouseup="this.className='func2';changValue(-2);"></a>
									<div class="clear"></div>
									<div class="nleft">
										<a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a
											href="javascript:void(0)">9</a>
										<a href="javascript:void(0)">x</a><a href="javascript:void(0)">0</a><a
											href="javascript:void(0)">#</a>
									</div>
									<div class="nright">
										<a href="javascript:void(0)" onclick="doSub();return false;"
											class="func3" name="functionkey" id="numBoardEnter"
											onmousedown="this.className='func3on'"
											onmouseup="this.className='func3'">1</a>
									</div>
									<div class="blank10"></div>
								</div>
							</div>

							<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('band');
								var lastpw = document.getElementById('installDate');
								var type = 1;
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
							  			
							  			// band��������Զ���ת��installDate
							  			if (pangu_getStrLen(lastObj.value) == 11 
							  					&& pangu_getStrLen(trim(document.forms[0].installDate.value)) == 0) 
							 			{
							 				document.forms[0].installDate.focus();
							 				
							 				changObj(document.forms[0].installDate, 2);
							 				
							 				return true;
							 			}
							 			
							 		
							 			// installDate��������Զ���ת��cardIdNum
							 			if (pangu_getStrLen(lastpw.value) == 8
							 					&& pangu_getStrLen(trim(document.forms[0].cardIdNum.value)) == 0)
							 			{
							 				document.forms[0].cardIdNum.focus();
							 				
							 				changObj(document.forms[0].cardIdNum, 3);
							 				
							 				return true;
							 			}
							 		
							   
									}					
								}
								
						
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix";
									}
									else if (t == 2)
									{
										type = 0;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
										document.getElementById('phone_input_3').className = "fs20 clearfix"
									}
									else 
									{
										type = 2;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
										document.getElementById('phone_input_3').className = "on fs20 clearfix"
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
										lastObj.value = "";
									}
									// isNaN()�жϲ���������,��Ϊ�����򷵻�false���������򷵻�true
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
									{	
										lastObj.value += v;
									}
									else if(lastObj.value.length < 8 && !isNaN(v) && type == 0)
									{
										
										lastObj.value += v;
									}
									else if (lastObj.value.length < 18 && !isNaN(v) && type == 2)
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
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	
</html>
