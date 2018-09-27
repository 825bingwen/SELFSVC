<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
			<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
				<META HTTP-EQUIV="Expires" CONTENT="0">
					<link href="${sessionScope.basePath }css/style.css" type="text/css"
						rel="stylesheet" />
					<link href="${sessionScope.basePath }css/reset.css" type="text/css"
						rel="stylesheet" />
					<style type="text/css">
					.tab_966_brand1 {width:640px;height:520px;background:url(../images/bg_openwin_tip2.png.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader ( enabled=true, sizingMethod=scale,src ="../images/bg_openwin_tip2.png.png");_background:none;}

.page-left1 {
	float: left;
	width: 39%;
}
</style>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/public.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/script.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/dialyzer.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
					<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) {
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
<%--	if (submitFlag == 0) {--%>
<%--		submitFlag = 1;--%>
<%----%>
<%--		document.getElementById("curMenuId").value = curmenu;--%>
<%----%>
<%--		document.actform.target = "_self";--%>
<%--		document.actform.action = "${sessionScope.basePath }hubprodinstall/chooseTheProduct.action";--%>
<%--		document.actform.submit();--%>
<%----%>
<%--	}--%>
}
// 处理键盘事件
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//更正
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
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
document.onkeydown = keyDown;
function keyDown(e) {
	//接收键盘码
	var key = GetKeyCode(e);

	//8:backspace 32:空格 B:66 M:77
	if (key == 8 || key == 32 || key == 66 || key == 77) {
		return false;
	}

	//82:R 220:返回
	if (key == 82 || key == 220) {
		goback("<s:property value='curMenuId' />");
		return;
	}
}

// 选择号码
function selectTelNum(telnum, payfee) {
	
	if (telnum == '') {
		//document.getElementById('chooseNum').style.visibility = 'hidden';
		document.getElementById('telnum').value = '';
		document.getElementById('payfee').value = '';
		return;
	}
	var displayFee = (Number(payfee) / 100).toFixed(2);
	//document.getElementById('chooseNum').style.visibility = 'hidden';
	document.getElementById('telnum').value = telnum;
	document.getElementById('payfee').value = payfee;
	//document.getElementById('chooseNum').innerHTML = '';
	//document.getElementById('chooseNum').style.visibility = 'visible';
	//document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">'
	//		+ telnum + '</p><p class="fs14 tc">预存费：' + displayFee + ' 元</p>';
	var param = "&telnum="+telnum;
	 var url = "${sessionScope.basePath }hubprodinstall/queryLowFee.action";
			var params = "telnum=" + telnum;
			var loader = new net.ContentLoaderSynchro(url, netload = function () {
							var resXml = this.req.responseText;
							var displayLowFee =	resXml;
							document.getElementById('winText').innerHTML = '<span class="yellow">您选择预订：</span><br />'
			+'<span class="ml20">号码：</span><span class="">'
			+ telnum
			+ '<br /><span class="ml20">预存费：</span><span class="">'
			+ displayFee + '</span>元<br />'
			+'<span class="ml20">最低消费：</span><span class="">'
			+ displayLowFee + '</span>元';
			openWindow('openWin1');
							}, null, "POST", params, "application/x-www-form-urlencoded"); 
	
}

// 下一页
function nextPage(linkURL) {
	// 执行查询
	if (submitFlag == 0) {
		submitFlag = 1;

		document.actform.target = "_self";
		document.actform.action = linkURL;
		document.actform.submit();
	}
}

// 自选号码搜索页面
function phoneSearch() {
	// 执行搜索
	if (submitFlag == 0) {
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }chooseTel/phoneSearchByFinalNbr.action?bz=1";
		document.actform.submit();
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" name="telnum" id="telnum" value="">
				<input type="hidden" name="telnumbeVO.fee" id="payfee" value="">
				<!--modify begin by wWX191797 at20140412 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造 -->
				<input type="hidden" name="lowFee" id="lowFee" value="">
				<input type="hidden" name="installFee" id="installFee" value="">
				<input type="hidden" name="sallBrand" id="sallBrand" value="">
				<input type="hidden" name="haveFee" id="haveFee" value="">
				<!--modify begin by wWX191797 at20140412 for OR_HUB_201311_1069_湖北_自助终端-自助选号功能中优质号码开户信息展示的配合改造 -->
					<%@ include file="/titleinc.jsp"%>
					<div class="main" id="main">
						<div class="pl30">
							<div class="b257 ">
								<div class="bg bg257"></div>
								<h2>
									选号开户流程
								</h2>
								<div class="blank10"></div>
								<a href="#">1. 入网协议确认</a>
								<a href="#">2. 身份证信息记取</a>
								<a href="#">3. 产品选择</a>
								<a href="#" class="on">4. 号码选择</a>
								<a href="#">5. 费用确认</a>
								<a href="#">6. 开户缴费</a>
								<a href="#">7. 取卡打印发票</a>
							</div>

							<div class="b712">
								<div class="bg bg712"></div>
								<div class="blank15"></div>
								<div class="p40">

									<!--滚动条-->
									<div class="box842W fl ml20 relative tab_966 tab_966_brand1"
										style="display: inline; margin-left: 0;">
										<div class=" blank15"></div>
										<p class=" fs18 fl ml30">
											点击即可选定号码，您每次可以预订
											<span class="yellow">1</span>个号码。
										</p>
										<div class=" blank15"></div>
										<div class=" blank5"></div>
										<div class=" clear"></div>

										<div class="num_dis">
											<s:iterator value="telnums" id="telnumb">
												<a title=""
													onclick="selectTelNum('<s:property value="#telnumb.telnum"/>', '<s:property value="#telnumb.fee"/>')"
													href="javascript:void(0)"><span></span>
													<p>
														<s:property value="#telnumb.telnum" />
													</p> </a>
											</s:iterator>
											<div class="clear"></div>
										</div>

										<div class="num_foot fs18 pl18">
											<pg:paginator recordsCount="${totalsize }" pageSize="${pagesize }" page="${page }" linkUrl="hubprodinstall/ShowTelNumbers.action" />
										</div>
										
									</div>
									<!--滚动条结束-->
									<div class=" clear"></div>
									<!--弹出窗-->

									<div class="openwin_tip" id="openWin1">
										<div class="bg"></div>
										<div class=" blank60"></div>
										<div class=" blank60"></div>
										<div class="  blank10n"></div>
										<p class="fs28 lh2 pl142" id="winText" name="winText">
											<span class="yellow">您选择预订：</span>
											<br />
											号码：13948603946
											<span class="ml20">预存费：</span><span class="">100.00</span>元
										</p>
										<div class="tc">
											<div class=" clear "></div>
											<div class=" blank30 "></div>
											<a title="确认" href="javascript:void(0)" class=" bt4"
												onmousedown="this.className='bt4on'"
												onmouseup="this.className='bt4';wiWindow.close();openWindowloading();">确认</a>
											<a title="" href="javascript:void(0)" class=" bt4 ml20"
												onmousedown="this.className='bt4on ml20'"
												onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
										</div>
									</div>

									<div class="openwin_tip openwin_big div804w515h"
										id="openWinLoading">
										<div class="bg loading tc">
											<div class="blank60"></div>
											<div class="blank60"></div>
											<img src="${sessionScope.basePath }images/loading.gif"
												class="" alt="处理中..." />
											<div class="blank30"></div>
											<p class="fs24   lh2">
												正在执行预定信息处理，请稍候...
											</p>
											<p class=" fs18  lh2 yellow"></p>
										</div>
									</div>
									<script type="text/javascript">
                openWindow = function(id) {
	          // 检查是否已选中
	         			 if (document.getElementById('telnum').value == '') {
		     															 return;
	           																}

	        				 wiWindow = new OpenWindow("openWin1", 708, 392);//打开弹出窗口例子					
                							}

                openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",804,515);//打开弹出窗口例子
					gotoPrintSuccess();					
								}				
				
				function gotoPrintSuccess(){
					setTimeout(
						function(){
							

								document.actform.target="_self";
								document.actform.action="${sessionScope.basePath }hubprodinstall/chooseTelNum.action";
								document.actform.submit();
								},
					       0);
				}
				</script>
									<!--弹出窗结束-->
								</div>
							</div>
						</div>
					</div>

					<%@ include file="/backinc.jsp"%>
		</form>

	</body>
<script type="text/javascript">
removeAclick();
</script>
</html>
