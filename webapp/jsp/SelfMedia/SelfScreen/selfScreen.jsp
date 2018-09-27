<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
String path = request.getContextPath();
String termIP = (String)request.getParameter("termIP");
//add begin g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149
if (termIP == null)
{
	termIP = "";
}

String termMAC = (String) request.getParameter("termMac");
if (termMAC == null)
{
	termMAC = "";
}
//add end g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>移动自助终端屏保播放页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="${sessionScope.basePath}/js/script.js" language="JavaScript" type="text/javascript"></script>
<style type="text/css">
body {
margin: 0,0,0,0;
float: none;
}
</style>
</head>
<script src="${sessionScope.basePath}/js/media/SelfMedia.js?ver=${jsVersion}" language="JavaScript" type="text/javascript"></script>
<script type="text/javascript">

// 屏保取消
function screenCancel(){
	<%--modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
	window.open("<%=path %>/login/index.action?lockTerm=lockTerm&termIP=<%=termIP%>&termMac=<%=termMAC %>&timeoutFlag=1","_self");
	<%--modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920 --%>
}

// 服务器根路径
var rootPath = '<%=path%>';

// 文件下载后保存到的本地路径
var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';

// 本地屏保播放文件列表(sc.wpl)
var scWplFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCWPL_LIST) %>';

// 本地屏保播放列表(scfile.xml)
var scPLFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCPLAY_LIST) %>';

// SWF类型
var swfType = '<%=Constants.FILE_TYPE_SWF %>';

// 播放编号
var playNO = 0;

// 页面加载时启动
function bodyLoad(){
    try{
		play();
	}catch(e){
		alert("请确认是否加入信任站点!");
	}
    if(!hasScFileExist)
    {
    	screenCancel();
    }
    //如果没有SWF文件则用avi播放,如果只要有一个文件是SWF,则用false播放
    if(!hasSwfFile){
        playStartOth();//播放WMV\AVI
    }else{
		cycleBegin();//播放SWF
    }
    

}

// 循环播放SWF
function cycleBegin(){
    if(playNO < playList.length){
        playStartSwf();
    	setTimeout("cycleBegin()",playList[playNO][1]*1000);
    	playNO ++;
    }else{
		playNO = 0;
		playStartSwf();
		setTimeout("cycleBegin()",playList[playNO][1]*1000);
		playNO ++;
    }
}

//是否含有swf类型文件
var hasSwfFile = false;
//屏保文件是否存在 
var hasScFileExist = false;
//播放列表
var playList = new Array();

//播放(这个方法主要设置播放列表而非真正播放屏保)
function play(){
	if (IsIE() == 1) {
		var loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		loclXmlDoc.async = false;
		loclXmlDoc.load(localURL + "/" + scPLFileName);// 解析scfile.xml
		var state = loclXmlDoc.readyState;
		if (state == 4) {
			var oNodes = loclXmlDoc.selectNodes("//root/media");
			var localSize = oNodes.length;
			for(var i=0;i<localSize;i++){
			    if(getXmlData(oNodes[i], "resFormat") == swfType){
			        hasSwfFile = true;
			    }
			    //playList[i] = [getXmlData(oNodes[i], "downLoadPath"),getXmlData(oNodes[i], "resPlayTime")]
			    playList[i] = [getXmlData(oNodes[i], "resName"),getXmlData(oNodes[i], "resPlayTime")];
			    if(scFileExist(localURL+"/"+getXmlData(oNodes[i], "resName")))
			    {
			    	hasScFileExist = true;
			    }
			}
		}
	}else{
	    //非IE浏览器
	}
}

// 播放单个SWF
function playStartSwf(){
	//var filePath = rootPath + "/" + playList[playNO][0];
	var filePath = "<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/" + playList[playNO][0];
	var content = "<embed src='" + filePath + "' width='100%' height='100%' wmode='transparent' />";
	document.getElementById("playDiv").innerHTML = content;
}

// 循环播放WMV
function playStartOth(){
	// 全屏播放
	var player=document.getElementById("ActiveMovie1");
	player.FullScreenMode = true ;
}

// 根据节点名称取节点数据
function getXmlData(xml, itemName) {
	var items = xml.getElementsByTagName(itemName);
	var itemData = "";
	if (items.length > 0) {
		itemData = items[0].childNodes[0].nodeValue;
	}
	return itemData;
}
</script>
	<body style="overflow-x:hidden;overflow-y:hidden" onload="window.focus();bodyLoad();" onkeydown="screenCancel()" onmousedown="screenCancel()">
		<div id="playDiv" style="width: 100%;height: 100%;">
		<object classid="CLSID:05589FA1-C356-11CE-BF01-00AA0055595A"
				id="ActiveMovie1" border="0" width="100%" height="100%">
				<param name="ShowDisplay" value="0">
				<param name="ShowControls" value="0">
				<param name="AutoStart" value="1">
				<param name="AutoRewind" value="0">
				<param name="PlayCount" value="0">
				<param name="Appearance" value="0">
				<param name="BorderStyle" value="0">
				<param name="MovieWindowHeight" value="0">
				<param name="MovieWindowWidth" value="0">
				<param name="FileName" value="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) + "/"
                    + (String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCWPL_LIST)%>">
			</object>
		</div>
	</body>
</html>
