<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>移动自助终端广告播放</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="<%=path%>/js/script.js" language="JavaScript" type="text/javascript"></script>
<style type="text/css">
body {
margin: 0 0 0 0;
float: none;
}
</style>
</head>
<script type="text/javascript">

// 本地多媒体文件存放位置
var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>'; 

// 广告播放列表文件名
var advWplFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>'; 

// 广告播放列表
var advPLFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVPLAY_LIST) %>';

// SWF类型
var swfType = '<%=Constants.FILE_TYPE_SWF%>';

// WMV播放
function wmvplay(){ 
	var player=document.getElementById("ActiveMovie1");   
	player.FullScreenMode = true ;
}

// 播放序号 播放SWF做循环时用
var playNO = 0;

// 页面加载时执行
function bodyLoad(){
    try{
		play();
	}catch(e){
		alert("请确认是否加入信任站点!");
	}
    //如果没有SWF文件则用avi播放,如果只要有一个文件是SWF,则用false播放
    if(!hasSwfFile){
        wmvplay();
    }else{
		cycleBegin();
    }

}

// 循环播放SWF文件
function cycleBegin(){
    if(playNO < playList.length){
        playswf();
    	setTimeout("cycleBegin()",playList[playNO][1]*1000);
    	playNO ++;
    }else{
		playNO = 0;
		playswf();
		setTimeout("cycleBegin()",playList[playNO][1]*1000);
		playNO ++;
    }
}

//是否含有swf类型文件
var hasSwfFile = false;

//播放列表
var playList = new Array();

//播放视频
function play(){
	if (IsIE() == 1) {
		var loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		loclXmlDoc.async = false;
		loclXmlDoc.load(localURL + "/" + advPLFileName);// 加载文件
		var state = loclXmlDoc.readyState;
		if (state == 4) {
			var oNodes = loclXmlDoc.selectNodes("//root/media");
			var localSize = oNodes.length;
			for(var i=0;i<localSize;i++){
			    if(getXmlData(oNodes[i], "resFormat") == swfType){
			        hasSwfFile = true;
			    }
			    //playList[i] = [getXmlData(oNodes[i], "downLoadPath"),getXmlData(oNodes[i], "resPlayTime")];
			    playList[i] = [getXmlData(oNodes[i], "resName"),getXmlData(oNodes[i], "resPlayTime")];
			}
		}
	}else{
	    //非IE浏览器
	}
}

function playswf(){
	//var filePath = "<%=path%>" + "/" + playList[playNO][0];
	var filePath = "<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/" + playList[playNO][0];
	document.getElementById("playDiv").innerHTML="<embed src='" + filePath + "' width='100%' height='100%' wmode='transparent' />";
}

function getXmlData(xml, itemName) {
	var items = xml.getElementsByTagName(itemName);
	var itemData = "";
	if (items.length > 0) {
		itemData = items[0].childNodes[0].nodeValue;
	}
	return itemData;
}
  </script>
	<body style="overflow-x:hidden;overflow-y:hidden"
		onload="window.focus();bodyLoad();">
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
				
<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) + "/" + (String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>

				<param name="FileName" value="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) + "/"
                    + (String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>">
			</object>
		</div>

	</body>
</html>
