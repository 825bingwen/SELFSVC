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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		
		<script language="javascript">
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
				// 清除键 M
				else if(key == 77)
				{
					wiWindow.close();
				}
			}
			
		</script>
	</head>
	
	<body scroll="no" onload="bodyLoad();">
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
			
			<!-- 套餐信息 -->
			<!-- 模板ID -->
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='' />
			<!-- 模板名称 -->
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='' />
			<!-- 产品ID -->
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='' />
			<!-- 产品名称 -->
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='' />
			<!-- 品牌 -->
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='' />
			<!-- 套餐月费 -->
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='' />
			<!-- 预存费用 -->
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='' />
			
			<%-- 是否打印小票  1-不打印，0-打印 --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<%-- 支付方式标识 11 两设备都可用 10 现金可用  01 银联可用 --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
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
						<h2>在线开户</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. 入网协议确认</a> 
						<a href="javascript:void(0)">2. 读取身份证信息</a>
	   					<a href="javascript:void(0)" class="on">3. 产品选择</a> 
	   					<a href="javascript:void(0)">4. 号码选择</a>
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
								<span class="bg"></span>产品列表
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							
							<table width="100%" class="tb_blue">
								<tr>
									<th class="list_title" align="center" >
										选择
									</th>
									<th class="list_title" align="center" >
										套餐名称
									</th>
									<th class="list_title" align="center">
										套餐月费(元)
									</th>
									<th class="list_title" align="center">
										品牌
									</th>
								</tr>
								<s:if test="tpltTempletList != null && tpltTempletList.size > 0">
									<s:iterator value="tpltTempletList" status="result" id="tpltTemplet">
										<tr align="center">
											<td>
												<input type="button" name="ids" id="<s:property value="#tpltTemplet.templetId" />" class="bt2_liebiao white" value="【  】" onmousedown="this.className='bt2on_1 white'" 
													onmouseup="this.className='bt2_liebiao white';selectProd('<s:property value="#tpltTemplet.templetId" />','<s:property value="#tpltTemplet.description" />','<s:property value="#tpltTemplet.templetName" />','<s:property value="#tpltTemplet.mainProdId" />','<s:property value="#tpltTemplet.mainProdName" />','<s:property value="#tpltTemplet.brand" />','<s:property value="#tpltTemplet.monthFee" />','<s:property value="#tpltTemplet.minFee" />');"/>
												<br/>
											</td>
											<td>
												<s:if test="%{#tpltTemplet.templetName.length()>16}">
                                                    <s:property value='#tpltTemplet.templetName.substring(0,16)' />...
                                                </s:if>
                                                <s:else>
                                                    <s:property value='#tpltTemplet.templetName' />
                                                </s:else>
											</td>
											<td>
												<s:property value="#tpltTemplet.monthFee" /><br />
											</td>
											<td>
												<s:if test="#tpltTemplet.brand == 'BrandMzone'">
													动感地带
												</s:if>
												<s:elseif test="#tpltTemplet.brand =='BrandSzx'">
													神州行
												</s:elseif>
												<s:elseif test="#tpltTemplet.brand =='BrandGotone'">
													全球通
												</s:elseif>	
												<s:else>
											 		<s:property value='#tpltTemplet.brand' />
											 	</s:else>
											</td>
										</tr>
									</s:iterator>
								</s:if>
							</table>
						</div>
						<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="cardInstall/selectProd.action" />
						<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;right:260px;">确认(请按确认键)</a>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script language="javascript">
	function bodyLoad()
	{
		document.getElementsByName("ids")[0].onmouseup();
	}
	</script>
</html>
<script type="text/javascript">
// 后退
function goback(menuid)
{
     //防止重复操作
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }cardInstall/agreement.action";
        document.actform.submit();
    }
}

// 提交           
function doSub() 
{
    if (submitFlag == 0) 
    {
        submitFlag = 1; //提交标记
        
        //判断是否选中
        var objs = document.getElementsByName('ids');
        var flag = 0;
        
        // 清空
        for(var i=0;i<objs.length;i++)
        {
            //alert(objs[i].value);
            if(objs[i].value == '【√】')
            {
                flag = 1;
                break;
            }
        }
        
        if (flag == 1)
        {
            openRecWaitLoading();
            
            document.actform.target = "_self";
            document.actform.action = "${sessionScope.basePath }cardInstall/selectRule.action";
            document.actform.submit();
        }
        else
        {   
            submitFlag = 0;
            // 提示
            showDetailInfo("请选择相应产品！");
        }
    }
}

//出现异常
function setException(errorMsg) 
{           
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        openRecWaitLoading();
        
        document.getElementById("errormessage").value = errorMsg;

        //异常处理，只要出现了异常就要记录日志  
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
        document.actform.submit();
    }           
}   

// 是否显示详情
var isShowDetail = false;

// 选择产品
function selectProd(id,content,templetName,mainProdId,mainProdName,brand,monthFee,minFee)
{
    // 迭代所有选择框
    var objs = document.getElementsByName('ids');
    
    // 清空
    for(var i=0;i<objs.length;i++)
    {
        objs[i].value = '【  】';
    }
    
    // 选中
    if (document.getElementById(id).value == '【  】')
    {
        document.getElementById(id).value = '【√】';
    }
    else if (document.getElementById(id).value == '【√】')
    {
        document.getElementById(id).value = '【  】'
    }
    
    // 保存产品模板属性
    // 模板ID
    document.getElementById("templetId").value = id;
    // 模板名称
    document.getElementById("templetName").value = templetName;
    // 产品ID
    document.getElementById("mainProdId").value = mainProdId;
    // 产品名称
    document.getElementById("mainProdName").value = mainProdName;
    // 品牌
    document.getElementById("brand").value = brand;
    // 套餐月费
    document.getElementById("monthFee").value = monthFee;
    // 预存费用
    document.getElementById("minFee").value = minFee;
    
    // 是否显示详情
    if (isShowDetail)
    {
        // 提示信息
        showDetailInfo(content);
    }
    
    isShowDetail = true;
}

// 下一页
function nextPage(linkURL)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.actform.target="_self";
        document.actform.action=linkURL;
        document.actform.submit();
    }
}

// 展示产品详细信息
function showDetailInfo(content)
{  
    document.getElementById('winText_xq').innerHTML="<span class='yellow'>"+content+"</span>";
    
    wiWindow = new OpenWindow("openWin_xq", 400, 200);
}
</script>
