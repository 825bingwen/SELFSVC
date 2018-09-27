<%@page contentType="text/html; charset=GBK"%>

<div class="footer" id="footer">

	<%
	// modify begin qWX279398 2015-12-25 DTS2015122502620 
	String getPageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	// modify end qWX279398 2015-12-25 DTS2015122502620 
	
	if(Constants.PROOPERORGID_HUB.equals(proID))
	{
	%>
		<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'" onclick="goHomePage();return false;">
			<div class="img"></div><span>首页</span>
		</a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';" onclick="goback('<%=curMenuId %>');return false;">
			<div class="img"></div><span>上一页</span></a>
		
		<!-- 为湖北添加倒计时功能 modify begin wWX217192 2015-02-28 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求 -->
		<a style="margin-left: 10px; margin-top: 18px; height:40px; float: left; font-size: 18px; width:640px;" id="countdownDiv">
		</a>
		<!-- 为湖北添加倒计时功能 modify end wWX217192 2015-02-28 OR_HUB_201502_161_关于在自助终端界面新增倒计时提醒功能的需求 -->
		
		<a id="quitBtn" href="${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'">
			<div class="img"></div><span>退出</span></a>
	<%
	}
	else
	{
	%>
		<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>	
		<a id="homeBtn" href="javascript:void(0);" class="home" onmousedown="this.className='home1'" onmouseup="this.className='home'" onclick="goHomePage();return false;"></a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';" onclick="goback('<%=curMenuId %>');return false;"></a>
		
		<%--modify begin qWX279398 2015-12-25 DTS2015122502620  --%>
		<a id="quitBtn" href="javascript:void(0);" onclick="goQuitBtn();return false;" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'"></a>
		<%--modify end qWX279398 2015-12-25 DTS2015122502620  --%>
		<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
	<%
	}
	%>
</div>
<script type="text/javascript">
<%--
* 返回首页时，显示“正在处理，请稍候” 
*
* @remark create g00140516 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125
--%>
function goHomePage()
{
	obj = document.getElementById("backWaitingFlag");
	
	if (obj != null && obj != "undefined" && document.getElementById("backWaitingFlag").value == "1")
	{
		openRecWaitLoading_NX("recWaitLoading");
	}
	
	window.location.href = "${sessionScope.basePath }login/goHomePage.action?timeoutFlag=0";
}

// modify begin qWX279398 2015-12-25 DTS2015122502620 
// 返回首页
function goQuitBtn()
{	
	if ('<%=getPageProvince %>' == '<%=Constants.PROOPERORGID_SD %>')
	{
		window.parent.currFlag = "";
		window.parent.currLeftTime = "";
		window.parent.currNumber = "";
	}
	window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
}
// modify end qWX279398 2015-12-25 DTS2015122502620 
</script>