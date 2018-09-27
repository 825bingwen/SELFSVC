<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>广告更新页面</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="<%=path%>/js/script.js" language="JavaScript" type="text/javascript"></script>
<script src="<%=path%>/js/media/SelfMedia.js" language="JavaScript" type="text/javascript"></script>
<script language="JavaScript" src="<%=path%>/js/net.js"></script>
<style type="text/css">
body {
margin: 0 0 0 0;
float: none;
}
</style>
</head>
<script type="text/javascript">

// 服务器根目录
var rootPath = '<%=path%>';

// 本地多媒体资源文件路径

var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';

// 广告播放列表文件名
var advWplFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>';

// 广告播放列表
var advPLFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVPLAY_LIST) %>';

// js下载文件
function downMediaFile(strRemoteURL,strLocalURL) 
{ 
	try{ 
	<%
		// 为防止被骗杀毒软件杀掉
	    out.println("var xmlHTTP;");
	    out.println("if(window.ActiveXObject){");
	    out.println("xmlHTTP=new ActiveXObject( \"Microsoft.XMLHTTP\");");
	    out.println("}else if(window.XMLHttpRequest){");
	    out.println("xmlHTTP=new XMLHttpRequest();");
	    out.println("}");
	    out.println("xmlHTTP.open( \"Get\",strRemoteURL,false); ");
	    out.println("xmlHTTP.send();"); 
	    out.println("var adodbStream=new ActiveXObject( \"ADODB.Stream\");"); 
	    out.println("adodbStream.Type=1;");
	    out.println("adodbStream.Open();"); 
	    out.println("adodbStream.write(xmlHTTP.responseBody);"); 
	    out.println("adodbStream.SaveToFile(strLocalURL,2);"); 
	    out.println("adodbStream.Close();"); 
	    out.println("adodbStream=null;"); 
	    out.println("xmlHTTP=null;"); 
	%>
	}catch(e){ 
		//TODO 下载出错是不是考虑重新下载
		//downMediaFile(strRemoteURL,strLocalURL);
	}
}
// 页面加载时执行
function bodyLoad(){
	getMediaList("media/getMediaAdvList.action",advPLFileName,'<%=Constants.ADV_TYPE%>',advWplFileName);
	document.frames('broadcastADV').location.reload();
	startclock();
}

// 30分钟更新一次广告视频
function startclock() {	//设置时间计算周期
	try {
		se = setInterval("update()", 1000*60*30);
	}catch (e) {

	}
}
//false表示还未更新
var isUpdate = false;

function update(){
    if(!isUpdate){
        if(getNowHour() == 2){
            isUpdate = true;
            getMediaList("media/getMediaAdvList.action",advPLFileName,'<%=Constants.ADV_TYPE %>',advWplFileName);
            document.frames('broadcastADV').location.reload();
        }
    }
    if(getNowHour() == 3){
    	isUpdate = false;
    }
}
</script>
	<body style="overflow-x:hidden;overflow-y:hidden" onload="bodyLoad();">
		<iframe id="broadcastADV" name="broadcastADV" frameborder="0" scrolling="no" width="100%"
			height="100%" src="<%=path%>/jsp/SelfMedia/SelfAdv/selfAdv.jsp"></iframe>
	</body>
</html>
