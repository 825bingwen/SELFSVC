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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
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
			
			//返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />");
			}			
		}
		
		//提交
		function doSub(money)
		{
			if (submitFlag == 0)
			{	
				submitFlag = 1;
				
				document.getElementById("yingjiaoFee").value = money;
			
				document.actform.action = "${sessionScope.basePath }agentCharge/dutyInfo.action";
				document.actform.submit();
			}
		}
		
		//充值金额检验
		function checkMoney(money) 
		{
			//判断最低充值金额
			/**if (parseInt(money) < parseInt("<s:property value='minMoney'/>"))
			{	
				alertError("单笔充值金额不得低于<s:property value='minMoney'/>元！");
			 	return;
			}*/
			
			//地区
			var servRegion = document.actform.servRegion.value;
			
			//科目编码
			var subjectId = document.actform.subjectId.value;
			
			//代理商账户编码
			var agentAccount = document.actform.agentAccount.value;
			
			//代理商组织机构编码
			var orgId = document.actform.orgId.value;
			
			//菜单编号
			var curMenuId = document.getElementById("curMenuId").value;
			var postStr ="agentAccount="+agentAccount+"&yingjiaoFee="+money+"&subjectId="+subjectId
							+"&servRegion="+servRegion+"&curMenuId="+curMenuId+"&orgId="+orgId;   
			var url1 = "${sessionScope.basePath}agentCharge/checkBeforeCharge.action";
			
			//调用充值金额校验方法
			var loader1 = new net.ContentLoader(url1, netload = function () 
			{
				var resXml1 = this.req.responseText;
				var resArray = resXml1.split('~~');
				var checkMsg = resArray[1];
				
				//校验成功
				if (resArray[0] == 1 || resArray[0] == "1")
				{
					//提交
					doSub(money);
		        }
		        
				//校验失败
				else
				{
					alertError(resArray[1]);
					return false;
				}								
			}, null, "POST", postStr, null);
										
		}
		
		//转向用户输入金额页面
		function inputMoney()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.action = "${sessionScope.basePath }agentCharge/inputMoney.action";
				document.actform.submit();
			}			
		}
		
		//返回上一页
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
	<body scroll="no">
		<form name="actform" method="post">			
			<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='0'/>
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />"/>
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<input type="hidden" id="agentName" name="agentName" value="<s:property value='agentName' />"/>
			<input type="hidden" id="agentAccount" name="agentAccount" value="${agentAccount }"/>
			<input type="hidden" id="subjectId" name="subjectId" value="<s:property value='subjectId' />"/>
			<input type="hidden" id="orgId" name="orgId" value="<s:property value='orgId' />"/>	
			<input type="hidden" id="minMoney" name="minMoney" value="<s:property value='minMoney' />"/>
					
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>代理商交费流程</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 输入手机号码</a> 
    					<a href="javascript:void(0)" class="on">2. 选择金额</a> 
    					<a href="javascript:void(0)">3. 免责声明</a>
    					<a href="javascript:void(0)">4. 插入银联卡</a> 
    					<a href="javascript:void(0)">5. 输入密码</a>
    					<a href="javascript:void(0)">6. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60" style="height:30px"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>代理商名称：<span class="yellow">${agentName}</span></p>
    						<p class=" tit_info"><span class="bg"></span>资金账号：<span class="yellow">${agentAccount}</span></p>
    						<p class=" tit_info"><span class="bg"></span>交费方式：<span class="yellow">银联卡</span></p>
    						<div class="blank10"></div>
    						<div class="line"></div> 
    						<div class="blank10"></div>
    						<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择您要充值交费的金额：</span></p>
    						<div class="blank25"></div>        
        					<div class="blank25"></div>
        					<ul class="money_list clearfix">
        					<%--  从字典中取出代理商可选择的充值金额，若配置的金额不是5条，则显示默认--%>
        					<s:if test="null != #request.selectMoneyList && 5 == #request.selectMoneyList.size">
        					<s:iterator value="selectMoneyList" id="list">
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('${list.dictid }');return false;">${list.dictid }元</a></li>
          					</s:iterator>
          					</s:if>
          					<s:else>
          					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('200');return false;">200元</a></li>
          					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('500');return false;">500元</a></li>
          					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('1000');return false;">1000元</a></li>
          					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('3000');return false;">3000元</a></li>
          					<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="checkMoney('5000');return false;">5000元</a></li>
          					</s:else>
          						<li><a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="inputMoney();return false;">其他金额</a></li>
        					</ul>	
    					</div>
					</div>
				</div>

			</div>
			
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
