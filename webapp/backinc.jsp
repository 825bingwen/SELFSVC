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
			<div class="img"></div><span>��ҳ</span>
		</a>
		<a id="backBtn" href="javascript:void(0);" class="pre" onmousedown="this.className='pre1'" onmouseup="this.className='pre1';" onclick="goback('<%=curMenuId %>');return false;">
			<div class="img"></div><span>��һҳ</span></a>
		
		<!-- Ϊ������ӵ���ʱ���� modify begin wWX217192 2015-02-28 OR_HUB_201502_161_�����������ն˽�����������ʱ���ѹ��ܵ����� -->
		<a style="margin-left: 10px; margin-top: 18px; height:40px; float: left; font-size: 18px; width:640px;" id="countdownDiv">
		</a>
		<!-- Ϊ������ӵ���ʱ���� modify end wWX217192 2015-02-28 OR_HUB_201502_161_�����������ն˽�����������ʱ���ѹ��ܵ����� -->
		
		<a id="quitBtn" href="${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1" class="quit" onmousedown="this.className='quit1'" onmouseup="this.className='quit'">
			<div class="img"></div><span>�˳�</span></a>
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
* ������ҳʱ����ʾ�����ڴ������Ժ� 
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
// ������ҳ
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