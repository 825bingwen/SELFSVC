<%--
 @User: m00227318
 @De: 2012/09/20
 @comment: ��ʾ�Զ���Ҫ�һ��Ļ�����
 @remark: create m00227318 2012/09/14 eCommerce V200R003C12L09  OR_huawei_201209_33
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="${sessionScope.basePath }js/net.js"></script>
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
			selectScore();
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

	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
				
			document.getElementById("curMenuId").value = menuid;
						
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }scoreExECoup/getPrefRewardList.action";
			document.forms[0].submit();
		}
	}
	
	<!-- ������ -->
openWindow = function(id){                       
        wiWindow = new OpenWindow(id,708,392);//�򿪵�����������                                        
        }
<!-- ���������� -->
	
</script>
</head>
	<body scroll="no" onload="document.getElementById('exScore').focus();">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<input type="hidden" id="changeType" name="changeType" value='<s:property value="#request.changeType" />'/>
			
			<!-- ����� -->
		    <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
			<!-- ���α��� -->
			<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
			<!-- ��Ʒ���봮 -->
		    <input type="hidden" id="prefRewardId" name="prefRewardId" value='<s:property value="#request.prefRewardId" />'/>
		    <!-- Ҫ�һ��Ļ����� -->
		    <input type="hidden" id="exchangeScore" name="exchangeScore" value=""/>
		    
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div style="padding-left:100px; padding-top:10px">				
				    <div class="b712" style="padding-left:100px; padding-top:10px">
						<div class="bg bg712" style="margin-left:120px; margin-top:10px"></div>
    					<div class="blank20"></div>
    					<div class="p40 pr0" style="padding-left:70px">
    						<p class="tit_info"><span class="bg"></span>��ѡ����Ҫ�һ��Ļ�������<span class="yellow"><s:if test="score_value!=0">�����ٶһ�����Ϊ��<s:property value="score_value"/> )</s:if> </span></p>
    						<p class="tit_info"><span >����ǰ���û���ֵ��<%=customerInfor.getSubscore()%></span></p>
    						<div class="line w625"></div>
    						<div class="blank10"></div>
    						<div class="custom_money"><span class="pl40 fs20 fl lh48">������һ�����ֵ��</span>
          						<input type="text" id="exScore" name="exScore" class="text1 fl" onfocus="MoveLast(event);" value="" />
        					</div>
        					<div class="keyboard_wrap mt5 pl55 clearfix">
        						<div class="fl btn_back_box" style="padding-top:200px;">
        							<p style="padding-left:10px; font-size:16px;">&nbsp;&nbsp;</p>        							
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
            								<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="selectScore();return false;">1</a> 
            							</div>
            							<div class="blank10"></div>
          							</div>
        						</div>
        						<script type="text/javascript">
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('exScore');
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
										if (v == 0 && numBoardText.value.length == 0)
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
		    <!-- ��������ȷ��ѡ����Ϣ -->	
			<div class = "openwin_tip" id="openWin_conf" style="width:708px; height:392px;">
			    <div class="bg"></div>
				<div class=" blank30"></div>
				<p class="fs24 lh2" style="padding-left:70px;" id="winText" name="winText">
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
	    			<a title="ȷ���ύ���ֶһ�����ȯ" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();commitPrefRwd();">ȷ��</a> 
	    			<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close();changValue(-2);">ȡ��</a> 
	    		</div>
			</div>
			
			<!-- ��������������Ϣ -->
			<div class="openwin_tip" id="openWin_err">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="yellow"><font color="red" size="5">��������</font></span>
					<br/>
					<span><font color="red" size="5">������ִ��ڵ�ǰ���û��֣����������룡</font>
					</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="���ֶһ�����ȯ����" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();changValue(-2);">ȷ��</a> 
	    		</div>
			</div>
			
			<!-- ��������С����С�һ�ֵ��Ϣ -->
			<div class="openwin_tip" id="openWin_err_s">
			    <div class="bg"></div>
				<div class=" blank60"></div>
			    <div class="  blank10n"></div>
				<p class="fs28 lh2" style="padding-left:50px; padding-right:50px" id="winText2" name="winText2">
					<span class="transcact_ok"><font color="red" size="5">��������</font> </span>
					<br/>
					<span><font color="red" size="5">�������ٶһ��Ļ���ֵΪ��<s:property value="score_value"/></font>
					</span>
					<br/>
					<span></span>
				</p>
	  			<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
                    <a title="���ֶһ�����ȯ����" href="javascript:void(0)" class=" bt4 tc " onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();changValue(-2);">ȷ��</a> 
	    		</div>
			</div>
			
		    <%@ include file="/backinc.jsp"%>	
		</form>
    </body>
    <script type="text/javascript">
    //���ȷ��
	function selectScore()
	{
	    var commitScore = document.getElementById("exScore").value;
	    //�Խ������ж�
		//if (parseInt(commitScore) <= 0)
		//{			 	
			// return;
		//}
		
		//�ж��Զ���Ľ���Ƿ�С��ָ������С�һ����
		if (<s:property value="score_value"/> > 0&&commitScore < <s:property value="score_value"/> )
		{
		   openWindow("openWin_err_s");
        }
		else if ((<%=customerInfor.getSubscore()%> < commitScore) )
		{
		   //������������ֲ���100�ı������ߴ��ڵ�ǰ���û���
		   openWindow("openWin_err");
        }
        //�û�ȷ���ύ��Ϣ
        else
        {
            var msg = '<br/>��ȷ��ʹ��<span class="yellow">' + commitScore + '</span>���ֶһ�����ȯ��<br/>���"ȷ��"�ύ����ȡ�������ء�';

            document.getElementById('winText').innerHTML = msg;
            document.getElementById('exchangeScore').value = commitScore;
            openWindow("openWin_conf");
        }	
	}	
	
	//�ύҵ��
    function commitPrefRwd()
    {
        //�Ѿ�ѡ���˻��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	    if (submitFlag == 0)
	    {
	        submitFlag = 1;
	        	        
	        openRecWaitLoading();
	        
            setTimeout(
		    	function(){
            		document.actform.target = "_self";
            		document.actform.action = "${sessionScope.basePath }scoreExECoup/commitPrefRewardList.action";
            		document.actform.submit();
            		}, 500);
        }
    }
    </script>
</html>
				    