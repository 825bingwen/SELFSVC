<%@ page language="java" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
String path = request.getContextPath();
String termIP = (String)request.getParameter("termIP");

if (termIP == null)
{
	termIP = "";
}

String termMAC = (String) request.getParameter("termMac");
if (termMAC == null)
{
	termMAC = "";
}
// 图片播放间隔时间 秒
String waitCycleTime = (String)PublicCache.getInstance().getCachedData("SH_IMAGE_CYCLE_TIME");
if (waitCycleTime == null || "".equals(waitCycleTime.trim()))
{
	// 默认10秒钟切换一次图片
	waitCycleTime = "10";
}                    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>移动自助终端图片屏保播放页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="${sessionScope.basePath}/js/script.js?ver=${jsVersion}" language="JavaScript" type="text/javascript"></script>
<script src="${sessionScope.basePath}/js/media/SelfMedia.js?ver=${jsVersion}" language="JavaScript" type="text/javascript"></script>
<style type="text/css">
body {
margin: 0,0,0,0;
float: none;
}
</style>
</head>
<script type="text/javascript">

// 屏保取消
function screenCancel()
{
	window.open("<%=path %>/login/index.action?lockTerm=lockTerm&termIP=<%=termIP%>&termMac=<%=termMAC %>&timeoutFlag=1","_self");
}

// 图片播放间隔时间
var waitTime = <%=waitCycleTime %>;

// 文件下载后保存到的本地路径
var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';

// 本地屏保播放列表(scfile.xml)
var scPLFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCPLAY_LIST) %>';

// 页面加载时启动
function bodyLoad()
{
    try
    {
		// 获取播放列表
		play();
	}
	catch(e)
	{
		alert("请确认是否加入信任站点!");
	}
	
	// 开始播放
    playStartOth();
}

//播放列表
var imageList = new Array();

// 图片播放列表索引
var m = 0;
//是否存在屏保文件 
var hasScFileExist = false;

//获取播放列表
function play(){
	if (IsIE() == 1) 
	{
		var loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		loclXmlDoc.async = false;
		loclXmlDoc.load(localURL + "/" + scPLFileName);// 解析scfile.xml
		var state = loclXmlDoc.readyState;
		if (state == 4) 
		{
			var oNodes = loclXmlDoc.selectNodes("//root/media");
			var localSize = oNodes.length;
			for(var i=0;i<localSize;i++)
			{
				// 获取配置文件中的image列表
			    if(getXmlData(oNodes[i], "resFormat") == 'jpg' || getXmlData(oNodes[i], "resFormat") == 'jpeg')
			    {
			    	// 获取图片名称
			    	imageList[m] = getXmlData(oNodes[i], "resName");
			    	m++;
			        if(scFileExist(localURL+"/"+getXmlData(oNodes[i], "resName")))
			        {
			            hasScFileExist = true;
			        }
			    }
			}
		}
	}
	else
	{
	    //非IE浏览器
	}
}

// 遍历索引
var j = 0;

// 无效文件个数
var failedImageCnt = 0;
// 播放jpg
function playStartOth()
{
	// 播放列表为空
	if(imageList.length == 0||!hasScFileExist)
	{
		screenCancel();
	}
	
	// 播放
	doChangeImage();
	
	// 循环播放jpg
	setInterval("doChangeImage()",waitTime*1000);
}

// 展示jpg
function doChangeImage()
{
	// modify begin by l00263786 2015-06-01 OR_SD_201504_102 自助终端屏保实现全省统一配置
	// 图片播放到最后一张，返回第一张
	if(j == imageList.length)
	{
		j=0;
	}
	// 图片的绝对路径
	var scFileAbsolutePath;
	// 通过循环获取一张存在的图片，直到超出播放列表范围 
	while(j<imageList.length)
	{
		if(failedImageCnt == imageList.length)
		{
		   	screenCancel();
		}
		// 获取屏保文件绝对路径 
		scFileAbsolutePath = localURL+ "/"+imageList[j];
		// 屏保文件索引，下次进入后获取下一张屏保文件
		j++;
		// 如果文件存在 ，设置其为屏保
		if(scFileExist(scFileAbsolutePath))
		{
            document.getElementById("imageNode").src = scFileAbsolutePath;
            // 屏保设置成功，退出循环
			break;
		}
		else
		{
			failedImageCnt++;
		}
	}
	// modify end by l00263786 2015-06-01 OR_SD_201504_102 自助终端屏保实现全省统一配置
}

// 根据节点名称取节点数据
function getXmlData(xml, itemName) 
{
	var items = xml.getElementsByTagName(itemName);
	var itemData = "";
	if (items.length > 0) 
	{
		itemData = items[0].childNodes[0].nodeValue;
	}
	return itemData;
}
</script>
	<body onload="window.focus();bodyLoad();" onkeydown="screenCancel()" onmousedown="screenCancel()">
		<center>
			<div id="playDiv" style="width: 100%;height: 100%;" >
				<img src="" id="imageNode"/>
			</div>
		</center>
	</body>
</html>
