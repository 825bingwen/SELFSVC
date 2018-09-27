<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content=""/>
		<meta http-equiv="description" content=""/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
		<style>
			.bt_choosenum{ width:148px; height:55px; line-height:14px; padding:13px 0 0 11px;text-align:left;display:inline-block;*zoom:1; _display:inline; background:url(${sessionScope.basePath }images/bt_choosenum2.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/bt_choosenum2.png");_background:none }
		</style>
	</head>
	<body scroll="no">
		<form id="actform" name="actform" method="post">
			
			<input type="hidden" id="errormessage" name="errormessage" value="" />
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
			
			<!-- 套餐信息 -->
			<!-- 模板ID -->
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
			<!-- 模板名称 -->
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
			<!-- 产品ID -->
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
			<!-- 产品名称 -->
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
			<!-- 品牌 -->
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
			<!-- 套餐月费 -->
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
			<!-- 预存费用 -->
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
			
			<!-- 选号信息 -->
			<!-- 地市 -->
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<!-- 组织机构ID -->
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<!-- 地市名称 -->
			<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
			<!-- 选号规则 -->
			<input type="hidden" id="selTelRule" name="selTelRule" value="<s:property value='selTelRule'/>"/>
			<!-- 前缀 -->
			<input type="hidden" id="telPrefix" name="telPrefix" value="<s:property value='telPrefix'/>"/>
			<!-- 后缀 -->
			<input type="hidden" id="telSuffix" name="telSuffix" value="<s:property value='telSuffix'/>"/>
			<!--空白卡序列号 -->
			<input type="hidden" id="blankno" name="blankno" value=""/>
			<!--手机号码 -->
			<input type="hidden" id="telnum" name="telnum" value="" />
						
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
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>在线开户</h2>
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
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank10"></div>
					<div class="p40">
						<div class="blank10"></div>
						<p class="tit_info_fs20">
							<span class="bg"></span>尊敬的用户，请点击选定号码，每次只能预定<span class="yellow">1</span>个号码。
						</p>
						<div class="blank10"></div>
						<div class="num_dis_fs16">
							<s:iterator value="serverNumList" id="po" status="st">
								<a id="telNum_<s:property value="telnum" />" href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />', '<s:property value="lowConsumFee" />');" ><s:property value="telnum" /></a>
							</s:iterator>
							<div class="clear"></div>
	 						</div>
	 						
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="cardInstall/telnumResult.action" />
	 						
	 						<div class="fs18" style="padding-top:10px;margin-left:160px;">  
	    					<div class="fright2"> 
	    						<span class="fl pt15">已选定号码：</span> 
								<a href="javascript:void(0)" class="bt_choosenum fl" style="visibility:hidden" id="chooseNum">
									<p class="fs16 tc" id="telnumText"></p>
									<p class="fs14 tc" id="payfeeText"></p>
	        					</a>
								<a href="javascript:void(0)" class="bt6 fr relative fl ml20" onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';openWindow('openWin1')">预订</a> 
	        				</div>
						</div>
					</div>	
					
					<div class=" clear"></div>
					
					<!--弹出窗-->
					<div class="openwin_tip div708w392h" id="openWin1">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<p class="fs28 lh2 pl142" id="winText" name="winText">
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
						    <div class=" blank10 "></div>
							<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';windowClose();" 
								onclick="gotoPrintSuccess();return false;">确认</a> 
    						<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';windowClose()">取消(按清除键)</a> 
	    				</div>
					</div>
					
					<div class="openwin_tip openwin_big div804w515h" id="openWinLoading">
	  					<div class="bg loading tc">
						    <div class="blank60"></div>
						    <div class="blank60"></div>
						    <img src="${sessionScope.basePath }images/loading.gif"  class="" alt="处理中..."/>
						    <div class="blank30"></div>
						    <p class="fs24   lh2">正在执行预定信息处理，请稍候...</p>
						    <p class=" fs18  lh2 yellow"></p>
	  					</div>
					</div>
					<!--弹出窗结束-->	
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">
var submitFlag = 0;

// 弹出div
var divFlag = "";

// 82、220 返回		
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
	
	//返回
	if (key == 82 || key == 220) 
	{
		goback("<s:property value = 'curMenuId'/>");
	}

	// 按确认键
	if((key == 13 || key == 89 || key == 221) && divFlag != '')
	{
		windowClose();
		gotoPrintSuccess();
	}
	
	// 按清除键
	if(key == 77 && divFlag != '')
	{
		windowClose();
	}
}

<s:if test="%{null == serverNumList || serverNumList.size() == 0}">
	// 没有查询到符合条件的号码时，提示信息
	alertSuccessMsg("没有查询到满足条件的可用号码");
</s:if>

function goback(curmenu) 
{
	// 选择了手机号点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }cardInstall/selectRule.action";
		document.actform.submit();				
	}			
}

// 选择号码
function selectTelNum(telnum, payfee, orgid, lowConsumFee)
{
	<s:iterator value="serverNumList" id="po" status="st">
		document.getElementById('telNum_<s:property value="telnum" />').innerHTML = '<s:property value="telnum" />';
	</s:iterator>
	
	// 设置选中的号码为粗体			
	document.getElementById('telNum_'+telnum).innerHTML = '<B>'+telnum+'</B>';
	
	if (telnum == '')
	{
		document.getElementById('chooseNum').style.visibility='hidden';
		document.getElementById('telnum').value = '';
		return;
	}
	
	document.getElementById('telnum').value = telnum;
	document.getElementById("orgid").value = orgid;
	
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">' + telnum + '</p><p class="fs14 tc">选号费：' + payfee + ' 元 </p><p class="fs14 tc">月最低消费：' + lowConsumFee + ' 元</p>';
	document.getElementById('chooseNum').style.visibility='visible';			
	
	document.getElementById('winText').innerHTML = '<span class="yellow">您选择开户：</span><br />号码：' + telnum + '<br />选号费：' + payfee + ' 元<br />月最低消费：' + lowConsumFee + ' 元';
}

// 下一页
function nextPage(linkURL)
{			
	// 执行查询
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading();
		
  		document.actform.target = "_self";
		document.actform.action = linkURL;
		document.actform.submit();
	}
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
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

// 弹出窗口
openWindow = function(id)
{
	// 检查是否已选中
	if (document.getElementById('telnum').value == '')
	{
	    alertRedErrorMsg("您好，请选择手机号码！");
		return;
	}
	
	divFlag = "openWin1";
	wiWindow = new OpenWindow("openWin1",708,392);				
}

// 流程
function gotoPrintSuccess()
{
	// 关闭提示
	windowClose();
	
	// 等待框
	openRecWaitLoading();
	
	// 号码资源占选
    var ret = telnumTmpPick();
    if (ret != 0)
    {
        setException("号码资源占选失败！");
        return;
    }
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

    // 提交 设置密码
	setTimeout(
	    function(){
	        document.actform.target="_self";
	        document.actform.action="${sessionScope.basePath }cardInstall/setPasswd.action";
	        document.actform.submit();                          
	    },
	500);
}

// 号码资源占选
function telnumTmpPick()
{
    // 返回 0 1~~失败原因
    var ret = 1;// 0:成功 1:失败
    
    // URL
    var url = "${sessionScope.basePath}cardInstall/telnumTmpPick.action";
    
    // 参数
    var params = "telnum=" + document.getElementById('telnum').value  ;
    
    // 调用
    var loader = new net.ContentLoaderSynchro(url, netload = function () {
            ret = this.req.responseText;
    }, null, "POST", params, null);
    
    // 返回
    return ret;
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

</script>
