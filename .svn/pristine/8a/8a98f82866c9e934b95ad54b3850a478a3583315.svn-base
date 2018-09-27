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
	<body scroll="no" onload="document.getElementById('yingjiaoFee').focus();">
		<form name="actform" method="post">			
			<!-- 手机号码 -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- 手机号码 -->
            <s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
            
            <!-- 省份编码 -->
            <s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
            
            <!-- 客户名称 -->
            <s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
            
            <!-- 支付方式 1:银联卡，4：现金 -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
            
            <!-- 错误信息 -->
            <s:hidden id="errormessage" name="errormessage"></s:hidden>
            
            <%-- 是否打印小票  1-不打印，0-打印 --%>
            <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<%@ include file="/jsp/customize/sd/feeCharge/common/chargeHeader.jsp"%>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40 pr0">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servNumber" /></span></p>
    						<div class="line w625"></div>
    						<p class="tit_arrow fs22"><span class="bg"></span>请选择您要充值交费的金额：<span class="yellow">（<s:property value="minMoney"/>元-<s:property value="maxMoney"/>元 )</span>：</p>
    						<div class="blank10"></div>
    						<div class="custom_money pl30"><span class="pl40 fs20 fl lh48">请输入金额：</span>
          						<input type="text" maxlength="4" id="yingjiaoFee" name="chargeLogVO.yingjiaoFee" class="text1 fl" onfocus="MoveLast(event);" value="" />
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
            								<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="doSub();return false;">1</a> 
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
					    			//// firfox下获取name属性值用getAttribute("name"),而不能直接用obj.name
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
										if ((v == 0 && numBoardText.value.length == 0) || numBoardText.value.length >= 4
												|| (parseInt(numBoardText.value + v) > parseInt("<s:property value='maxMoney'/>")))
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

<script type="text/javascript">
document.getElementById("highLight3").className = "on";

//防止页面重复提交
var submitFlag = 0;

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
    var key = GetKeyCode(e);
    
    //确认
    if (key == 13 || key == 89 || key == 221) 
    {
        inputMoney();
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
}

// 输入金额后提交
function doSub() 
{
    //对金额进行判断
    var pattern = /^\d{1,4}$/; 
    
    if (!pattern.test(document.getElementById("yingjiaoFee").value) 
            || parseInt(document.getElementById("yingjiaoFee").value) <= 0)
    {               
        return;
    }
    
    if(parseInt(document.getElementById("yingjiaoFee").value) < '<s:property value="minMoney"/>')
    {
        alertRedErrorMsg("每次充值缴费的最低金额为<s:property value='minMoney'/>元！");
        document.getElementById('yingjiaoFee').focus();
        return;
    }
     
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.actform.action = "${sessionScope.basePath }nonlocalChargeSD/dutyInfo.action";
        document.actform.submit();
    }           
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
                
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/qryfeeChargeAccount.action";
        document.forms[0].submit();
    }
}
</script>
