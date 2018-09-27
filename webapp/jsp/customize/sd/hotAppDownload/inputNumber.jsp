<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="document.getElementById('servNumber').focus();">
		<form name="actform" method="post">
			<!-- app应用id，唯一标识 -->
            <s:hidden name="appInfoPO.appId" id="appId"></s:hidden>
            
            <!-- 应用名称 -->
            <s:hidden name="appInfoPO.appName"></s:hidden>
            
            <!-- app应用下载地址 -->
            <s:hidden name="appInfoPO.shortAddress"></s:hidden>
            
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<div class="blank40"></div>
				<div class="b966">
					<div class="blank30" id="errorMsg"><s:property value="errormessage"/></div>

					<div class=" p40">
						<p class="fs22 mb30"></p>

						<!--输入框-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
								<li class="on fs20 clearfix" id="phone_input_1">
                                    <i class="lh30">1.输入手机号码</i>
                                    <span id="redstar1" class="pl20 fl lh75">手机号码：<font color="red">*</font></span>
                                    <input type="text" id="servNumber" name="servNumber"
                                        maxlength="11" class="text1 fl relative"
                                        onclick="changObj(this, 1);MoveLast(this);" />
                                </li>
                                <li class="fs20 clearfix" id="phone_input_2">
                                    <i class="lh30">2. 密码验证</i>
                                    <span id="redstar2" class="pl20 fl lh75">密码验证：<font color="red">*</font></span>
                                    <input type="password" name="password" id="password"
                                        maxlength="6" class="text1 fl relative"
                                        onclick="changObj(this, 2);MoveLast(this);" />
                                </li>							
							</ul>

							<!--小键盘-->
							<div class="numboard numboard_big fl" id="numBoard">
								<div class="numbox">
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
							<!-- 红色错误提示信息 -->
                            <div class="popup_confirm" id="openWin_ErrorMsg">
                                <div class="bg"></div>
                                <div class="tips_title">提示：</div>
                                <div class="fs24 red pl55 pr30 pt40 line_height_12 h200" id="winText_ErrorMsg"></div>
                                <div class="btn_box tc mt20">
                                    <span class=" inline_block ">
                                        <a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">关闭</a>
                                    </span>
                                </div>
                            </div>
                            <script type="text/javascript">                             
                                alertRedErrorMsg = function(content)
                                {
                                    document.getElementById('winText_ErrorMsg').innerHTML = content;
                                    wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
                                };
                            </script>
							<script type="text/javascript">	
	                			var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var lastObj = document.getElementById('servNumber');
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
							  			
							  			// servNumber输入完毕自动跳转到password
							  			if (pangu_getStrLen(lastObj.value) == 11 
							  					&& pangu_getStrLen(trim(document.forms[0].password.value)) == 0) 
							 			{
							 				document.forms[0].password.focus();
							 				
							 				changObj(document.forms[0].password, 2);
							 				
							 				return true;
							 			}
									}					
								}
						
						        function MoveLast(lastObj)
						        {
						            var r = lastObj.createTextRange(); 
						            r.collapse(false); 
						            r.select();
						        }
						        
								function changObj(o, t)
								{
									lastObj = o;
							
									if (t == 1)
									{
										type = 1;
										document.getElementById('phone_input_1').className = "on fs20 clearfix";
										document.getElementById('phone_input_2').className = "fs20 clearfix";
									}
									else
									{
										type = 0;
										document.getElementById('phone_input_1').className = "fs20 clearfix";
										document.getElementById('phone_input_2').className = "on fs20 clearfix";
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
									else if (lastObj.value.length < 11 && !isNaN(v) && type == 1)
									{	
										lastObj.value += v;
									}
									else if(lastObj.value.length < 6 && !isNaN(v) && type == 0)
									{
										lastObj.value += v;
									}
									var r = lastObj.createTextRange(); 
									r.collapse(false); 
									r.select();
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
	              			</script>
							<!--小键盘end-->
							
						</div>
						<div class="blank10"></div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<!--弹出正在处理div-->
	<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
		<div class="bg"></div>
	    <p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
	   	<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"正在处理，请稍候......") %></p>      
	</div>
	<script type="text/javascript">
	    openRecWaitLoading = function(id){
		    wiWindow = new OpenWindow("recWaitLoading", 804, 515);
	    }
	</script>
</html>
<script type="text/javascript">
var submitFlag = 0;

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
    var key = GetKeyCode(e);

    //确认
    if (key == 13 || key == 89 || key == 221) 
    {
        doSub();
        return;
    }
    
    //返回
    if (key == 82 || key == 220) 
    {
        goback("<s:property value='curMenuId' />");
        return;
    }
    
    // 更正
    else if (key == 8 || key == 32 || key == 66 || key == 77)
    {
        var etarget = getEventTarget(e);
        if (etarget.type == "text" || etarget.type == "password") 
        {
            etarget.value = backString(etarget.value);
        }
        if (etarget.name == 'password' && etarget.value == '' )
        {
            MoveLast(document.getElementById('servNumber'));
        }
    }
    
    var tel = document.forms[0].servNumber.value;
    var password = document.forms[0].password.value;        

    if ((key == 8 || key == 32 || key == 66 || key ==77)
            && pangu_getStrLen(trim(password)) == 0 && pangu_getStrLen(trim(tel)) == 11)
    {
        document.forms[0].servNumber.focus();
        
        changObj(document.forms[0].servNumber, 1);
        
        return true;
    }
    
    if (pangu_getStrLen(trim(tel)) == 11 && pangu_getStrLen(trim(password)) == 0) 
    {
        document.forms[0].password.focus();
        
        changObj(document.forms[0].password, 2);
         
        return true;
    }
}       

function doSub()
{
    //对号码进行判断
    var pattern = /^\d{11}$/;
    
    var telNumber = document.getElementById("servNumber").value;
    if (telNumber == "" || !pattern.test(telNumber))
    {
        changObj(document.getElementById('servNumber'), 1);
    
        alertRedErrorMsg("请输入正确的手机号码");
        
        return;
    }
    var password = document.getElementById("password").value;
    if (password.value == "" || pangu_getStrLen(trim(password)) != 6)
    {
        changObj(document.getElementById('password'), 2);
        
        alertRedErrorMsg("请正确输入密码");
        return;
    }
  
    if (submitFlag == 0) 
    {
        openRecWaitLoading();
        submitFlag = 1; //提交标记
        
        document.actform.action = "${sessionScope.basePath }hotAppDownload/customerLogin.action";
        document.actform.submit();
    }
}

function goback()
{
    if (submitFlag == 0) 
    {
        openRecWaitLoading();
        submitFlag = 1; //提交标记
        
        document.actform.action = "${sessionScope.basePath }hotAppDownload/showDetail.action";
        document.actform.submit();
    }
} 

</script>
