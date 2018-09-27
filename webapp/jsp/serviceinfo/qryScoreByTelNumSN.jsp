<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String errorMsg = (String) request.getAttribute("errormessage");
	if (errorMsg == null)
	{
		errorMsg = "";
	}
	
	// add begin qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	errorMsg = CommonUtil.errorMessage(errorMsg);
	// add end qWX279398 2015-08-25 OR_HUB_201508_599 �������������ɷѻ���ȫ©��������
	
%>
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
			
			//�Ժ�������ж�
			var pattern = /^\d{8}$/;
			
			if ((key == 8 || key == 32 || key == 66 || key ==77) 
					&& pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//����ʱ��numBoardText2������ϣ��Զ���ת��numBoardText1
				document.getElementById("numBoardText1").focus();
				
				changObj(document.getElementById("numBoardText1"), 1);
				
				return true;
			}
			
			if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
					&& document.getElementById("numBoardText2").value == "")
			{
				//numBoardText1������ϣ��Զ���ת��numBoardText2
				document.getElementById("numBoardText2").focus();
				
				changObj(document.getElementById("numBoardText2"), 2);
				
				return true;
			}			
		}
		
		function MoveLast(e) 
		{
			var r = lastObj.createTextRange(); 
			r.collapse(false); 
			r.select();
		}
        function getMaxDay(year,month) { 
				if(month==4||month==6||month==9||month==11) 
					return "30"; 
				if(month==2) 
					if(year%4==0&&year%100!=0 || year%400==0) 
						return "29"; 
					else 
						return "28"; 
				return "31"; 
			} 
			
			function isNumber( s ){ 
				var regu = "^[0-9]+$"; 
					var re = new RegExp(regu); 
				if (s.search(re) != -1) { 
					return true; 
				} else { 
					return false; 
				} 
			} 
			
         function isDate(date,fmt) { 
					if (fmt==null)
						 fmt="yyyyMMdd"; 
					var yIndex = fmt.indexOf("yyyy"); 
					if(yIndex==-1) 
						return false; 
				var year = date.substring(yIndex,yIndex+4); 
				var mIndex = fmt.indexOf("MM"); 
				if(mIndex==-1)
					 return false; 
				var month = date.substring(mIndex,mIndex+2); 
				var dIndex = fmt.indexOf("dd"); 
				if(dIndex==-1)
					 return false; 
				var day = date.substring(dIndex,dIndex+2); 
				if(!isNumber(year)||year>"2100" || year< "1900") return false; 
				if(!isNumber(month)||month>"12" || month< "01") return false; 
				if(day>getMaxDay(year,month) || day< "01") return false; 
				return true; 
		} 

             
             
             
         //�жϿ�ʼ�����Ƿ���ڽ�������  
        function isStartEndDate(startDate,endDate){   
                 // alert(startDate+"==="+endDate);   
                if(startDate.length>0&&endDate.length>0){   
                    
                 	var s1=startDate.substring(0,4)+'/'+startDate.substring(4,6)+'/'+startDate.substring(6,8);
                 	var s2=endDate.substring(0,4)+'/'+endDate.substring(4,6)+'/'+endDate.substring(6,8);
                    var allStartDate = new Date(s1);   
                    
                    // modify begin wWX217192 2014-10-29 Bug 79079 �������նˡ�ɽ�������ַ��Ų�ѯ����ѯ��ʼ���ڴ��ڽ������ڲ�����ʾ���ڴ���
                    var allEndDate = new Date(s2);
                    // modify end wWX217192 2014-10-29 Bug 79079 �������նˡ�ɽ�������ַ��Ų�ѯ����ѯ��ʼ���ڴ��ڽ������ڲ�����ʾ���ڴ���
                    
                    if(allStartDate.getTime()>allEndDate.getTime())
                    {
                    	return false;   
                    }   
                }   
                 return true;   
             }  
               
       //��֤���ڸ�ʽ
 		function checkAskDate(StartDate,EndDate){   
               if(!isDate(StartDate.value,null)){   
               alertRedErrorMsg("��ʼ���ڸ�ʽ����ȷ!��ȷ��ʽΪ:20120501");
                     StartDate.focus();   
                     return false;   
                }else if(!isDate(EndDate.value,null)){ 
                	alertRedErrorMsg("�������ڸ�ʽ����ȷ!��ȷ��ʽΪ:20120531");  
                     EndDate.focus();   
                     return false;   
                 }else if(!isStartEndDate(StartDate.value,EndDate.value)){   
                    	alertRedErrorMsg("��ʼ���ڲ��ܴ��ڽ�������");  
                    	StartDate.focus();   
                     return false;   
                 }   
               return true;   
             }  


		function doSub()
		{
		var patterns = /^\d{8}$/;
			var startdate=document.getElementById("numBoardText1");
			var enddate=document.getElementById("numBoardText2");
			
			//�ж������Ƿ�Ϊ��
			if(startdate.value==null||""==startdate.value||!patterns.test(trim(startdate.value))){
				alertRedErrorMsg("��ʼ���ڸ�ʽ����ȷ");
				startdate.focus();   
				return ;  
			}
			if(enddate.value==null||""==enddate.value||!patterns.test(trim(enddate.value))){
				alertRedErrorMsg("�������ڸ�ʽ����ȷ");
				enddate.focus();   
				return ;  
			}
			
			//�ж������Ƿ�������ڸ�ʽ
			if(!checkAskDate(startdate,enddate)){
				return ;
			}
			
			//�ж��Ƿ�����������µ�
			if(parseInt(startdate.value)<parseInt("<s:property value="startDate"/>")){
				alertRedErrorMsg("ֻ�ܲ�ѯ����������£���ʼ���ڱ������"+"<s:property value="startDate"/>");
				startdate.focus();   
				return ;
			}
			if(parseInt(enddate.value)>parseInt("<s:property value="endDate"/>")){
				alertRedErrorMsg("ֻ�ܲ�ѯ����������£��������ڱ���С��"+"<s:property value="endDate"/>");
				enddate.focus();   
				return ;
			}
			
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openRecWaitLoading();
				
				document.actform.target = "_self";
				if("mx"=="<s:property value="queryType"/>")
				{
				    document.actform.action = "${sessionScope.basePath}scoreQry/qryScoreDetailHisForSd.action?startDate="+startdate.value+"&endDate="+enddate.value;
				}
				else if("dhls"=="<s:property value="queryType"/>"&&"<%=Constants.PROOPERORGID_SD%>"=="<s:property value="requestRegion"/>")
				{
				    document.actform.action = "${sessionScope.basePath}scoreQry/queryScoreChangeHisForsd.action?startDate="+startdate.value+"&endDate="+enddate.value;
				}
				else if("dhls"=="<s:property value="queryType"/>"&&"<%=Constants.PROOPERORGID_HUB%>"=="<s:property value="requestRegion"/>")
				{
				    document.actform.action = "${sessionScope.basePath}scoreQry/queryScorePrizeHisForhub.action?startDate="+startdate.value+"&endDate="+enddate.value;
				}
				
				// add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
				else if ("paymentScore"=="<s:property value="queryType"/>"&&"<%=Constants.PROOPERORGID_SD%>"=="<s:property value="requestRegion"/>")
				{
				    document.actform.action = "${sessionScope.basePath}scoreQry/qryScorePaymentSD.action?startDate="+startdate.value+"&endDate="+enddate.value;
				}
				// add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
				document.actform.submit();
			}	
		}
		
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
		</script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ><%=errorMsg %></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--����+�����+��ܰ��ʾ-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
          						<li class="on fs20 clearfix" id="phone_input_1" >
          							<i class="lh30">1.��������(����20120501)</i>
          							<span id="redstar1" class="pl20 fl lh75">��ʼ���ڣ�</span>
            						<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
            						<input type="text"  maxlength="8" id="numBoardText1" name="servnumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus="MoveLast(this);" value="" />
            						<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
          						</li>
          						<li class="fs20 clearfix" id="phone_input_2">
          							<i class="lh30">2.��������(����20120531)</i>
          							<span id="redstar2" class="pl20 fl lh75">�������ڣ�</span>
          							<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
            						<input type="text" maxlength="8" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus="MoveLast(this);" value="" />
            						<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
         						</li>         
        					</ul>
        					
        					<!--С����-->
	        				<div class="numboard numboard_big fl" id="numBoard">
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
									
									var textContent2 = document.getElementById('redstar2').innerHTML;
									document.getElementById('redstar2').innerHTML=textContent2 + '<font color="red">*</font>';
								<%
									}
								%>
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('numBoardText1');
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
							  			
							  			//�Ժ�������ж�
										var pattern = /^\d{8}$/;
										
							  			if (pattern.test(lastObj.value)
							  					&& document.getElementById("numBoardText2").value == "")
										{
											//numBoardText1������ϣ��Զ���ת��numBoardText2
											document.getElementById("numBoardText2").focus();
											
											changObj(document.getElementById("numBoardText2"), 2);
											
											return true;
										}		
							  			
									}					
								}
						
								function changObj(o, t)
								{
									document.getElementById("errorMsg").innerHTML = "";
									
									lastObj = o;
							
									if (t == 1)
									{
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 8 && !isNaN(v))
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
