<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	// 清除缓存，防止页面后退安全隐患 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js?ver=${jsVersion}"></script>
		<style>
			.bookederror
			{
				width:598px; 
				height:150px; 
				color:red; 
				font-size:20px; 
				padding-top: 30px; 
				text-align:center; 
	            _background:none;
	        }
		</style>
	</head>
	
	<body scroll="no" onload="">
		<form name="actform" method="post" target="_self">
			
			<!-- 身份证信息 -->
			<!-- 姓名 -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<!-- 性别 -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<!-- 身份证号码 -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<!-- 证件地址 -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			<!-- 开始时间 -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
			<!-- 结束时间 -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
			<!-- 卡信息 -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
			<!-- 照片信息 -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
			
			<%-- 套餐信息 --%>
			<%-- 模板ID --%>
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
			<%-- 模板名称 --%>
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
			<%-- 产品ID --%>
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
			<%-- 产品名称 --%>
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
			<%-- 品牌 --%>
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
			<%-- 套餐月费 --%>
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
			<%-- 预存费用 --%>
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
			
			<!--空白卡序列号 -->
			<input type="hidden" id="blankno" name="blankno" value=""/>
			<!--手机号码 -->
			<s:hidden id="telnum" name="telnum"/>
			<s:hidden id="errormessage" name="errormessage"/>
						
			<!--------------simInfoPO信息 -------------->
            <input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
            <input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
            <input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
            <input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
			<input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value='simInfoPO.oldiccid'/>"/>
			
			<%-- 写卡所需信息 --%>
            <input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
			
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<%-- 选号规则  2：按前缀查询 3：按后缀查询 4：取随机号码，bookedTelnum：选择预约号码进行开户--%>
			<s:hidden name="selTelRule" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<!-- 提示框 -->
				<!-- 成功提示信息 -->
				<div class="popup_confirm" id="openWin_xq">
					<div class="bg"></div>
					<div class="tips_title">套餐详情</div>
					<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_xq">
						
				  	</div>
					<div class="btn_box tc mt20">
						<span class=" inline_block ">
							<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">关闭(按清除键)</a>
						</span>
					</div>
				</div>
				
				<%@ include file="/customer.jsp"%>
					
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>网上开户</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
	   					<a href="javascript:void(0)">3. 产品选择</a> 
	   					<a href="javascript:void(0)" class="on">4. 号码选择</a>
	   					<a href="javascript:void(0)">5. 设置服务密码</a>
	   					<a href="javascript:void(0)">6. 费用确认</a>
	   					<a href="javascript:void(0)">7. 开户缴费</a>
	   					<a href="javascript:void(0)">8. 取卡打印小票</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank30"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>已预约号码
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							
							<s:if test="bookedTelnumList != null && bookedTelnumList.size > 0">
								<table class="tb_blue" align="center">
									<tr>
										<th class="list_title">
											预定号码
										</th>
										<th class="list_title">
											证件类型
										</th>
										<th class="list_title">
											证件号码
										</th>
										<th class="list_title">
											到期时间
										</th>
										<th class="list_title">
											操作
										</th>
									</tr>
									<s:iterator value="bookedTelnumList" status="status" id="list">
										<tr>
											<td>
												${list.telnum }
											</td>
											<td>
												身份证
											</td>
											<td>
												${list.idCardNo }
											</td>
											<td>
												${list.deadline }
											</td>
											<td>
												<input type="button" name="ids" id="${list.telnum}" class="bt2_liebiao white" value="【  】" onmousedown="this.className='bt2on_1 white'" 
													onmouseup="this.className='bt2_liebiao white';" onclick="selectTelnum('${list.telnum}')"/>
											</td>
										</tr>
									</s:iterator>
								</table>
							</s:if>
							<s:else>
								<div class="bookederror">
									${errormessage}
								</div>
							</s:else>
						</div>
						<s:if test="bookedTelnumList != null && bookedTelnumList.size > 0">
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="cardInstall/inputTelnumByRule.action" />
							<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;right:260px;">确认(请按确认键)</a>
						</s:if>
						<s:else>
							<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value="curMenuId" />'); return false;" style="display:inline;right:260px;">返回</a>
						</s:else>
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">

//防止重复提交
var submitFlag = 0;

//82、220 返回		
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
		return;
	}
	//确认
	else if (key == 13 || key == 89 || key == 221)
	{
		doSub();
	}
}

<%-- 默认选中第一个号码 --%>
<s:if test="bookedTelnumList != null && bookedTelnumList.size > 0">
document.getElementsByName("ids")[0].onclick();
</s:if>

//选择号码
function selectTelnum(telnum)
{
    // 迭代所有选择框
    var objs = document.getElementsByName('ids');
    
    // 清空
    for(var i=0;i<objs.length;i++)
    {
        objs[i].value = '【  】';
    }
    
    // 选中
    if (document.getElementById(telnum).value == '【  】')
    {
        document.getElementById(telnum).value = '【√】';
        setValue("telnum",telnum);
    }
    //else if (document.getElementById(telnum).value == '【√】')
    //{
    //    document.getElementById(telnum).value = '【  】';
    //    setValue("telnum","");
    //}
    
}

// 后退
function goback(menuid)
{
     //防止重复操作
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
        document.actform.action = "${sessionScope.basePath }cardInstall/selectRule.action";
        document.actform.submit();
    }
}

// 提交           
function doSub() 
{
	//校验号码
    if (!isTelnumSelected())
    {
    	alertRedErrorMsg("请先选择手机号码！");
    	return;
    }
    
   	// 等待框
	openRecWaitLoading();

	// 发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
	var message = checkReadCardStatus();
	if (message != "")
	{
		setException(message);
		return;
	}
	
	// 读取空白卡序列号
	var blankCardSN = readBlankCardSN();
	
	if (blankCardSN.indexOf("1~") != -1)
	{
		setException(blankCardSN.split('~')[1]);
		return;
	}
	
	if(blankCardSN.length != 20)
	{
		setException("对不起，卡类型不正确，请联系营业厅管理员!");
		return;
	}
		
	// 获取空白卡序列号
	document.getElementById('blankno').value = blankCardSN;
	
	// 校验空白卡信息
    ret = chkBlankCardInfo();
    var resArray = ret.split('~~');
    if (resArray[0] == 0 || resArray[0] == "0")
    {
        setValue("iccid",resArray[1]);
        setValue("imsi",resArray[2]);
        setValue("issueData",resArray[3]);
        setValue("formNum",resArray[4]);
        setValue("oldiccid",resArray[5]);
    }
    else
    {
        setException(resArray[1]);
        return;
    }
        
    if (submitFlag == 0) 
    {
        submitFlag = 1; //提交标记
	
	    // 提交 设置密码
		setTimeout(
		    function(){
		        document.actform.action="${sessionScope.basePath }cardInstall/setPasswd.action";
		        document.actform.submit();                          
		    },
		500);
    }
}

//用户是否选中号码
function isTelnumSelected()
{
	//判断是否选中
    var objs = document.getElementsByName('ids');
    var flag = false;
    
    // 清空
    for(var i=0;i<objs.length;i++)
    {
        if(objs[i].value == '【√】')
        {
            flag = true;
            break;
        }
    }
    
    return flag;
}

// 校验空白卡信息
function chkBlankCardInfo()
{
    // 返回 0 1~~失败原因
    var ret = 1;// 0:成功 1:失败
    
    // URL
    var url = "${sessionScope.basePath}cardInstall/chkBlankCardInfo.action";
    
    // 参数
    var params = "blankno=" + document.getElementById('blankno').value + "&";
        params = params + "prodid=" + document.getElementById("mainProdId").value + "&";
        params = params + "telnum=" + document.getElementById("telnum").value ;
    
    // 调用
    var loader = new net.ContentLoaderSynchro(url, netload = function () {
            ret = this.req.responseText;
		    setValue("cardInfoStr",ret.substring(3));
    }, null, "POST", params, null);
    
    // 返回
    return ret;
}

// 出现异常
function setException(errorMsg) 
{			
	document.getElementById("errormessage").value = errorMsg;
	
	//异常处理，只要出现了异常就要记录日志  
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
	document.actform.submit();
}

// 下一页
function nextPage(linkURL)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.actform.action=linkURL;
        document.actform.submit();
    }
}
</script>
