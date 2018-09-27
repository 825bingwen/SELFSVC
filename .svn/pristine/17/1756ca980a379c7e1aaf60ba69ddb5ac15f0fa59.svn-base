<%--
	create by sWX219697 2014-8-5 17:04:11 OR_SD_201408_20_山东_ISSS平台对接自助终端的需求
	营销业务提示页面，用于用户充值后的业务展示，实现js分页功能
	modify by sWX219697 2014-9-17 11:25:36 OR_SD_201409_556_自助终端营销功能优化
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/json2.js"></script>
	<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
	<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<% 
	String dialogMsg = CommonUtil.getParamValue(Constants.SH_ISSS_DIALOG_MSG);
	if (dialogMsg == null || "".equals(dialogMsg))
	{
		dialogMsg = "您好！以下推荐业务是否需要办理？";
	}
	
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造
	// 根据菜单id获取特惠业务包菜单名称，若名称为空，则表明此菜单没有上线
	String privServPack = CommonUtil.getMenuName("privServPack");
	
	// 营销推荐活动超时时间，超时自动关闭(默认10000，单位毫秒)
	String closeService = CommonUtil.getParamValue(Constants.SH_CLOSESERVICE_OUTTIME, "10000");
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_山东_ISSS自助终端UCD改造
%>

<style type="text/css">
.openwin_big1 .bg2 {
	width: 889px;
	height: 528px;
	left: 60px;
	top: 0px;
	background: url(${sessionScope.basePath}images/bg_openwin_big2.png);
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true,sizingMethod=scale,src="${sessionScope.basePath }images/bg_openwin_big2.png");
	_background: none
}
</style>

<!-- 
<div class="openwin_big1 " id="webServiceDialog"
	style="HEIGHT: 528px; top: 160px; padding-left: 7px; position:relative;">
	<div class="bg2" style="HEIGHT: 528px; left: 10px; z-index:0;"></div>
	<div style="position:absolute; z-index:5; left:0px; top:25px;"> 
		<div class="blank30 tc fs24 pt15 pos_rel" style="width:819px; text-align:center;">
			<%=dialogMsg%>
		</div>
	
		<div class="blank30">
			<p class="fs18 lh2 yellow pl45">
			</p>
		</div>
	
		<div class="blank60 height260 fs16 1h2 pl45"
			id="" style="overflow: hidden;">
			
		</div>
		<div class="blank30">
			<p class="fs18 lh2 yellow pl45">
	
			</p>
		</div>
		<div class="btn_box tc">
			<span class=" inline_block "><a title="" class="btn_bg_146"
				href="javascript:void(0);" onmousedown="this.className='key_down';"
				onmouseup="this.className='btn_bg_146';"
				onclick="wiWindowWeb.close();">关闭</a> </span>
		</div>
	</div>
	<div class="box70W" style=" position:absolute; z-index:6; right:30px; top:30px;">
		<input type="button" class="btnUp" onclick="myScrollDialog.moveUp(30)"  />
		<div class="div75w350h">
			<div class="blank10px"></div>
			<div class="box66W tc f16 div66w36h" id="moveDom" style="top:52px; left:2px; height:30px" >0%</div>
		</div>
		<input type="button" class="btnDown" onclick="myScrollDialog.moveDown(30)"/>
	</div>
	<div style="clear:both;"></div>
</div>
 -->
<div class="openwin_790" id="openWin1" style="">
    <div class="bg"></div>                    
    <div class="blank30"></div>
    <p class="fs26 ml80">尊敬的用户，我们为您推荐</p>
    
    <%--modify begin qWX279398 2016-01-06 OR_SD_201512_312_调整自助终端弹出营销推荐窗口 --%>
    <%-- <input type="button" class="bt4 suggest" onmousedown="this.className='bt4on suggest'" onmouseup="this.className='bt4 suggest';" onclick="wiWindowWeb.close();printInvoice('Last','1');" value="打印发票" />--%>
    <%--modify end qWX279398 2016-01-06 OR_SD_201512_312_调整自助终端弹出营销推荐窗口 --%>
    
    <input type="button" class="bt4 close" onmousedown="this.className='bt4on close'" onmouseup="this.className='bt4 close';" onclick="wiWindowWeb.close();" value="关闭" />
    <div class="blank30"></div>
    <div class="ml25" id="webServiceTable">
	</div>
</div>

<%-- 山东移动自助终端业务展示--%>
<script type="text/javascript">
openwebServiceDialog = function(id)
{
	openWindowFlag = 0;
 	wiWindowWeb = new OpenWindow("openWin1", 790,392);	    
}

//加载弹出框
// modify by jWX216858 2015-5-7 OR_SD_201504_452_山东_ISSS自助终端UCD改造
function serviceDialog()
{
	//参数
	var postStr ="servnumber=<s:property value='servnumber' />";  
	 
	var receptionUrl = "${sessionScope.basePath}ISSS/qryOfferInfoList.action";
	
	// 获取推荐列表
	var loader1List = new net.ContentLoader(receptionUrl, netload = function () 
	{
		var text = this.req.responseText;
		
		try
		{
			// js对象  obj-数组
			var obj = JSON.parse(text);
			
			// 拼装展示内容
			var content = "";	
			
			for(var i=0;i<obj.length;i++)
			{
			    if (i < 3)
			    {
				    content +="<div class='recommended fl'><div class='blank10'></div>";
	                content +="<p class='fs20 yellow p10 lh28'>";   
					var offerInfo = obj[i];
					
					// 业务
					var offName = offerInfo.offerName;
					
					// 描述
					var offDesc = offerInfo.desc;
					
					// 菜单id
					var offerMenuid = offerInfo.menuId;
					
					// url
					var offerUrl = offerInfo.url;
					
					var offerId = offerInfo.offerId;
					
					var treatmentid = offerInfo.treatment_id;
					var offerCode = offerInfo.offerCode;
					
					if ("1" == offerId)
					{
					    content += offName + "</p><div class='blank10'></div><p class='fs18 p10 recommended_text'>";
                        content += offDesc + "</p><div class='blank10'></div></div>";
					}
					else
					{
						content += offName + "</p><div class='blank10'></div><p class='fs18 p10 recommended_text'>";
						content += offDesc + "</p><div class='blank10'></div><a class='bt4' onmousedown='this.className=\"bt4on\"' onmouseup='this.className=\"bt4\"'";
						content +="onclick=\"go('"+offerMenuid+"','"+offerUrl+"','"+offerId+"','"+treatmentid+"','"+offerCode+"');\">";
						content += "去看看</a></div>";
					}
			    }
			}
			// 如果没有可推荐的业务，则不弹出提示框
			if(obj.length > 0)
			{
			    // 推荐活动不够三个时，显示更多业务
			    if (1 == obj.length || 2 == obj.length)
			    {
			        content += "<div class='recommended fl'><div class='blank10'></div>";
                    content += "<p class='fs20 yellow p10 lh28'>更多业务</p>";
                    content += "<div class='blank10'></div>";
                    content += "<img src='${sessionScope.basePath}images/more_bussiness.png' style='top:60px; width:140px;height:140px'/>";
                    content += "<a class='bt4' onmousedown=\"this.className='bt4on'\" onmouseup=\"this.className='bt4'; topGo('rec', 'reception/receptionFunc.action'); return false;\">去看看</a></div>";
			    }
			    
			    // 显示最新优惠，（走特惠业务包）
			    if (1 == obj.length && null != '<%=privServPack %>' && "" != '<%=privServPack %>')
			    {
			        content += "<div class='recommended fl'><div class='blank10'></div>";
                    content += "<p class='fs20 yellow p10 lh28'>最新优惠</p>";
                    content += "<div class='blank10'></div>";
                    content += "<img src='${sessionScope.basePath}images/more_activity.png' style='top:60px;width:140px;height:140px'/>";
                    content += "<a class='bt4' onmousedown=\"this.className='bt4on'\" onmouseup=\"this.className='bt4'; topGo('privServPack', 'privServPack/qryPrivServPack.action'); return false;\">去看看</a></div>";
			    }
			    
				document.getElementById("webServiceTable").innerHTML = content;
				openwebServiceDialog("openWin1");
				
				// modify begin qWX279398 OR_SD_201508_958_山东_自助终端营销推荐和发票打印展示优化
				if (typeof wiWindow!='undefined')
				{
					wiWindow.close();
				}
				// modify end qWX279398 OR_SD_201508_958_山东_自助终端营销推荐和发票打印展示优化
				
				
				// 10秒后自动关闭营销推荐活动弹出窗 
			    setTimeout("wiWindowWeb.close()",'<%=closeService %>');
			}
			// 清空提示
		    document.getElementById("offerListWaitTip").innerHTML = "";
		}
		catch(ee)
		{
			// 异常
		}
		
	}, null, "POST", postStr, null);
	
}

//跳转
function go(menuid, url, offerId, treatment_id, offerCode) 
{
	//offerid为1时，即按钮为考虑时，不需要记录session，直接调用反馈接口
	//add begin sWX219697 2015-2-12 08:53:33 OR_SD_201502_198_ISSS自助终端、便利店支持无产品营销活动推荐
	if("1" == offerId)
	{
		//关闭推荐框
		wiWindowWeb.close();
		
		//考虑一下
		consider(offerId, treatment_id);
		return;
	}
	
	//add by SWX219697 2015-2-13 17:37:30 修改bug 86167
	<%
	session.setAttribute("isssGoBackFlag","isssGoBackFlag");
	%>
	//add end SWX219697 2015-2-13 17:37:30 修改bug 86167
	
	//add end sWX219697 2015-2-12 08:53:38 OR_SD_201502_198_ISSS自助终端、便利店支持无产品营销活动推荐
	
	//参数
	var postStr ="offerMenu="+menuid+"&offerId="+offerId+"&treatment_id="+treatment_id
		+"&servnumber=<s:property value='servnumber' />"+"&offerCode="+offerCode;  
	 
	var receptionUrl = "${sessionScope.basePath}ISSS/setISSSSession.action";
	
	//先将相关的业务信息存session，便于订购后反馈，然后跳转至对应的菜单
	var loader11 = new net.ContentLoader(receptionUrl, netload = function () 
	{
	    //跳转至对应业务页面
		document.getElementById("curMenuId").value = menuid;
		
		document.forms[0].action = "${sessionScope.basePath}" + url;
		document.forms[0].submit();	
								
	}, null, "POST", postStr, null);


}

/**
 * 考虑一下
 * add by sWX219697 2015-2-12 08:54:28 OR_SD_201502_198_ISSS自助终端、便利店支持无产品营销活动推荐
 */
function consider(offerId, treatment_id)
{
	//参数
	var postStr ="&offerId="+offerId+"&treatment_id="+treatment_id
		+"&servnumber=<s:property value='servnumber' />";  
	 
	var considerUrl = "${sessionScope.basePath}ISSS/consider.action";
	
	//先将相关的业务信息存session，便于订购后反馈，然后跳转至对应的菜单
	var loader = new net.ContentLoader(considerUrl, netload = function () 
	{
	}, null, "POST", postStr, null);
}
</script>