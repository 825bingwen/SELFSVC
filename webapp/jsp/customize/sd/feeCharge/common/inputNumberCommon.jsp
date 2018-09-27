<%@page contentType="text/html; charset=GBK"%>

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
	    <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js?ver=${jsVersion }"></script>
	</head>
	<body scroll="no" onload="document.getElementById('numBoardText1').focus();doLoad();">
		<form name="actform" method="post">		
		    <%-- 是否打印小票  1-不打印，0-打印 --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="1" />
            
            <%-- 错误信息 --%>
            <input type="hidden" id="errormessage" name="errormessage" value='' />
            
            <%-- 校验现金设备标识 --%>
            <input type="hidden" id="checkCashFlag" name="checkCashFlag" value='' />
            
            <%-- 校验银联卡设备标识 --%>
            <input type="hidden" id="checkCardFlag" name="checkCardFlag" value='' />    
            	
			<%@ include file="/titleinc.jsp"%>
	
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank30" id="errorMsg" ></div>
					
					<div class=" p40">						
						<p class="fs22 mb30"></p>
						
						<!--键盘+输入框+温馨提示-->
						<div class="keyboard_wrap clearfix">
							<ul class="phone_num_list fl">
		        						<li class="on fs20 clearfix" id="phone_input_1" >
		        							<i class="lh30">1.输入手机号码</i>
		        							<span class="pl20 fl lh75">手机号码：</span>
		          						<input type="text" maxlength="11" id="numBoardText1" name="servNumber" class="text1 fl relative" onclick="changObj(this, 1);MoveLast(this);" onfocus=""/>
		        						</li>
		        						<li class="fs20 clearfix" id="phone_input_2">
		        							<i class="lh30">2. 再次输入手机号码</i>
		        							<span class="pl20 fl lh75">再次输入：</span>
		          						<input type="text" maxlength="11" id="numBoardText2" class="text1 fl relative" onclick="changObj(this, 2);MoveLast(this);" onfocus=""/>
		       						</li>         
		      					</ul>
		      					
		      					<!--小键盘-->
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
		       				<!--小键盘end-->
						</div>						
						<div class="blank10"></div>
					</div>					
				</div>
			</div>
		
			<%@ include file="/backinc.jsp"%>		
		</form> 
		<div class="popup_confirm" id="chkHardware_confirm">
            <div class="bg"></div>
            <div class="tips_title">
                提示：
            </div>
            <div class="tips_body">
                <p id="chkHardwareMsg" style="font-size: 19px; color: red;"></p>
            </div>
            <div class="btn_box tc mt20">
                <span class=" mr10 inline_block "><a href="#"
                    class="btn_bg_146" onmousedown="this.className='key_down'"
                    onmouseup="this.className='btn_bg_146';wiWindow.close();document.getElementById('numBoardText1').focus();">继续充值</a>
                </span>
                <span class=" inline_block "><a class="btn_bg_146" href="#"
                    onmousedown="this.className='key_down'"
                    onmouseup="this.className='btn_bg_146';wiWindow.close();goback('feeCharge');">返回</a>
                </span>
            </div>
        </div>                
	</body>
</html>
<script type="text/javascript">
//防止页面重复提交
var submitFlag = 0;

function doSub()
{	
	//对号码进行判断
	var pattern = /^\d{11}$/;
	
	var telNumber = document.getElementById("numBoardText1").value;
	if (telNumber == "" || !pattern.test(telNumber))
	{
		changObj(document.getElementById('numBoardText1'), 1);
		
		alertRedErrorMsg("请输入正确的手机号码");
		
		return;
	}
	
	var confirmTelNumber = document.getElementById("numBoardText2").value;
	if (confirmTelNumber != telNumber)
	{
		changObj(document.getElementById('numBoardText2'), 2);
		
		alertRedErrorMsg("两次输入的手机号码必须相同");
		
		return;
	}
	
	if (submitFlag == 0)
	{
	    openRecWaitLoading();
	    submitFlag = 1;
	    document.actform.action = url;
	    document.actform.submit();
	}	
}

// 硬件检查
function doLoad()
{
    // 提示信息
    var showMessage = "";
    
    // 校验现金识币器 =====start========
    var chkCashMsg = "";
    
    // 校验是否支持现金缴费标志,0不支持,1:支持
    if (window.parent.isCashEquip == 1)
    {
        chkCashMsg = initCashPayEquip();
    }
    else
    {
        chkCashMsg = "该终端机不支持现金识币器";
    }
    
    if (chkCashMsg != "")
    {
        chkCashMsg = "由于本终端的现金识币器故障，暂时无法使用现金进行缴费！";
        setValue("checkCashFlag", "casherror");
        showMessage = showMessage + chkCashMsg;
    }
    // 校验现金识币器 =====end========
    
    // 校验银联读卡器 =====start========
    var chkCardMsg = "";

    // 校验是否支持银行卡缴费标志,0不支持,1:支持
    if (window.parent.isUnionPay == 1)
    {
        chkCardMsg = initUnionCardPayEquip();
    }
    else
    {
        chkCardMsg = "该终端机不支持银联读卡器";
    }
    
    if (chkCardMsg != "")
    {
        chkCardMsg = "由于本终端的银联读卡器故障，暂时无法使用银联卡进行缴费！";
        setValue("checkCardFlag", "carderror");
        showMessage = showMessage + chkCardMsg;
    }
    // 校验银联读卡器 =====end========
        
    if (chkCashMsg != "" && chkCardMsg != "")
    {
        goToError("尊敬的客户，由于本终端的现金识币器和银联读卡器故障，暂时无法进行缴费！");
        return;
    }
    
    // 校验加密键盘 =====start========
    var chkKeyBoardMsg = "";
        
    // 校验是否支持加密键盘标志,0不支持,1:支持
    if (window.parent.isKeyBoard == 1)
    {
        chkKeyBoardMsg = initKeyBoard();
    }
    else
    {
        chkKeyBoardMsg = "该终端机不支持密码键盘";
    }
    
    if (chkKeyBoardMsg != "")
    {
        chkKeyBoardMsg = "由于本终端的密码键盘故障，暂时无法使用银联卡进行缴费！";
        setValue("checkCardFlag", "carderror");
        showMessage = showMessage + chkKeyBoardMsg;
    }
    // 校验加密键盘 =====end========
        
    if (chkCashMsg != "" && chkKeyBoardMsg != "")
    {
        goToError("尊敬的客户，由于本终端的现金识币器和密码键盘故障，暂时无法进行缴费！");
        return;
    }
    
    // 校验票据打印机 =====start========
    var chkPrintMsg = "";
    
    // 校验是否支持打印票据标志,0不支持,1:支持
    if (window.parent.isPrintFlag == 1)
    {
        chkPrintMsg = initListPrinter();
    }
    else
    {
        chkPrintMsg = "该终端机不支持小票打印机";
    }
    
    if (chkPrintMsg != "")
    {
        chkPrintMsg = "由于本终端的小票打印机故障或缺纸，暂时无法打印小票！";
        showMessage = showMessage + chkPrintMsg;
    }
    // 校验票据打印机 =====end========
    
    // 校验硬件是否正常并提示异常信息
    if (showMessage != "")
    {
        openConfirmMessage("尊敬的客户，" + showMessage);
    }
}

// 转到错误页面
function goToError(errorMsg) 
{           
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        //弹出等待框
        openRecWaitLoading();
        
        // 记录错误信息
        setValue("errormessage", errorMsg);

        //异常处理，只要出现了异常就要记录日志  
        document.actform.action = "${sessionScope.basePath }charge/goToError.action";
        document.actform.submit();
    }
}

// 弹出确认框
function openConfirmMessage(content)
{
    document.getElementById('chkHardwareMsg').innerHTML = content;
    wiWindow = new OpenWindow("chkHardware_confirm",708,392);
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}	

<%-- 小键盘begin--%>
var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
var lastObj = document.getElementById('numBoardText1');
for (i = 0; i < numBoardBtns.length; i++)
{
    if (!numBoardBtns[i].className) 
    {
        numBoardBtns[i].className='';
    }
    // firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
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
            
            //对号码进行判断
        var pattern = /^\d{11}$/;
        
            if (pattern.test(trim(lastObj.value))
                    && document.getElementById("numBoardText2").value == "")
        {
            //numBoardText1输入完毕，自动跳转到numBoardText2
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
    else if (lastObj.value.length < 11 && !isNaN(v))
    {                               
        lastObj.value += v;                                 
    }
    
    var r = lastObj.createTextRange(); 
    r.collapse(false); 
    r.select();
}
<%-- 小键盘begin--%>

alertRedErrorMsg = function(content)
{
    document.getElementById('winText_ErrorMsg').innerHTML = content;
    wiWindow = new OpenWindow("openWin_ErrorMsg", 708, 392);
};

//8、32、66、77 更正
//82、220 返回
//13、89、221 确认
//80 打印
//85 上一页
//68 下一页

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
    else if (key == 82 || key == 220) 
    {
        goback("<s:property value='curMenuId' />");
        return;
    }
    //更正
    else if (key == 8 || key == 32 || key == 66 || key ==77)
    {
        var etarget = getEventTarget(e);
        if (etarget.type == "text" || etarget.type == "password") 
        {
            etarget.value = backString(etarget.value);
        }
    }
    
    //对号码进行判断
    var pattern = /^\d{11}$/;
    
    if ((key == 8 || key == 32 || key == 66 || key ==77) 
            && pattern.test(trim(document.getElementById("numBoardText1").value)) 
            && document.getElementById("numBoardText2").value == "")
    {
        //更正时，numBoardText2更正完毕，自动跳转到numBoardText1
        document.getElementById("numBoardText1").focus();
        
        changObj(document.getElementById("numBoardText1"), 1);
        
        return true;
    }
    
    if (pattern.test(trim(document.getElementById("numBoardText1").value)) 
            && document.getElementById("numBoardText2").value == "")
    {
        //numBoardText1输入完毕，自动跳转到numBoardText2
        document.getElementById("numBoardText2").focus();
        
        changObj(document.getElementById("numBoardText2"), 2);
        
        return true;
    }           
}
</script>
