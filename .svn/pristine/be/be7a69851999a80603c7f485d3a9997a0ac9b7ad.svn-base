<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1);

// 现金交费操作是否在终端机上记录详细日志。1，记；0，不记。
String chargeLogDetail = (String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
	</head>
	<body scroll="no" onload="document.getElementById('payAmount').focus();">
		<form name="actform" method="post">
			<%-- 手机号码 --%>
			<s:hidden id="servnumber" name="chargeLogVO.servnumber" ></s:hidden>
			
			<%-- 省份编码 --%>
			<s:hidden id="telProvinceCode" name="chargeLogVO.provinceCode"></s:hidden>
			
			<%-- 手机号码归属地市--%>
			<s:hidden id="servRegion" name="chargeLogVO.servRegion"></s:hidden>
			
			<%-- 支付方式 1:银联卡 4：现金--%>
			<s:hidden id="payType" name="chargeLogVO.payType" value="1"></s:hidden>
							
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>充值交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
						<a href="javascript:void(0)">2. 选择支付方式</a>
    					<a href="javascript:void(0)" class="on">3. 选择金额</a>
    					<a href="javascript:void(0)">4. 免责声明</a>
    					<a href="javascript:void(0)">5. 插入银联卡</a> 
    					<a href="javascript:void(0)">6. 输入密码</a>
    					<a href="javascript:void(0)">7. 核对信息</a>
    					<a href="javascript:void(0)">8. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40 pr0">
    						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="chargeLogVO.servnumber" /></span></p>
    						<div class="line w625"></div>
    						<p class="tit_arrow fs22"><span class="bg"></span>请输入您要充值交费的金额：<span class="yellow">（<s:property value="minMoney"/>元-<s:property value="maxMoney"/>元 ) </span>：</p>
    						<div class="blank10"></div>
    						<div class="custom_money pl30"><span id="redstar1" class="pl40 fs20 fl lh48">请输入金额：</span>
          						<input type="text" maxlength="4" id="payAmount" name="payAmount" class="text1 fl" onclick="MoveLast(event);"  value="" />
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
            								<a href="javascript:void(0)" class="func3" name="functionkey" id="numBoardEnter" onmousedown="this.className='func3on'" onmouseup="this.className='func3'" onclick="selectPayAmount();return false;">1</a> 
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
								<%
									}
								%>
                				var numBoardBtns = document.getElementById('numBoard').getElementsByTagName('div')[0].getElementsByTagName('a');
								var numBoardText = document.getElementById('payAmount');
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
												|| (parseInt(numBoardText.value + v) > '<s:property value="maxMoney"/>'))
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
		<script type="text/javascript">
		//防止页面重复提交
		var submitFlag = 0;
		
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//更正
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//这句话最关键
			}
			
			var inputFee = document.getElementById('payAmount').value;
			
			// 密码键盘输入金额，第一位不能是0
			if(key == 48 && ''== inputFee)
			{
				return false;
			}
			// 密码键盘输入金额，不能大于3000
			if(parseInt(parseInt(inputFee)*10 + key - 48) > '<s:property value="maxMoney"/>')
			{
				return false;
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{   
			//只允许输入0-9
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
			
			//确认
			if (key == 13 || key == 89 || key == 221) 
			{
				selectPayAmount();
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
		
		// 选择交费金额
		function selectPayAmount()
		{
			//对金额进行判断
			var pattern = /^\d{1,4}$/; 
			
			if (!pattern.test(document.getElementById("payAmount").value) 
					|| parseInt(document.getElementById("payAmount").value) <= 0)
			{			 	
			 	return;
			}
			
			if(parseInt(document.getElementById("payAmount").value) < '<s:property value="minMoney"/>')
        	{
	            alertRedErrorMsg("对不起，每次充值交费的最低金额限度为<s:property value='minMoney'/>元，请重新输入交费金额！");
	            document.getElementById('payAmount').focus();
	            return;
        	}
			 
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openRecWaitLoading_NX("recWaitLoading");
				
				writeDetailLog("<%=chargeLogDetail %>", "交费金额：" + document.getElementById("payAmount").value);
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }nonlocalCharge/dutyInfo.action";
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				writeDetailLog("<%=chargeLogDetail %>", "<s:property value='chargeLogVO.servnumber' />退出跨省异地充值交费功能");
				
				document.getElementById("curMenuId").value = menuid;
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }nonlocalCharge/selectOtherFee.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>
