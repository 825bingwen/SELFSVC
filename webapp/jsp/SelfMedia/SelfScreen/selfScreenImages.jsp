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
// ͼƬ���ż��ʱ�� ��
String waitCycleTime = (String)PublicCache.getInstance().getCachedData("SH_IMAGE_CYCLE_TIME");
if (waitCycleTime == null || "".equals(waitCycleTime.trim()))
{
	// Ĭ��10�����л�һ��ͼƬ
	waitCycleTime = "10";
}                    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>�ƶ������ն�ͼƬ��������ҳ��</title>
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

// ����ȡ��
function screenCancel()
{
	window.open("<%=path %>/login/index.action?lockTerm=lockTerm&termIP=<%=termIP%>&termMac=<%=termMAC %>&timeoutFlag=1","_self");
}

// ͼƬ���ż��ʱ��
var waitTime = <%=waitCycleTime %>;

// �ļ����غ󱣴浽�ı���·��
var localURL = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';

// �������������б�(scfile.xml)
var scPLFileName = '<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCPLAY_LIST) %>';

// ҳ�����ʱ����
function bodyLoad()
{
    try
    {
		// ��ȡ�����б�
		play();
	}
	catch(e)
	{
		alert("��ȷ���Ƿ��������վ��!");
	}
	
	// ��ʼ����
    playStartOth();
}

//�����б�
var imageList = new Array();

// ͼƬ�����б�����
var m = 0;
//�Ƿ���������ļ� 
var hasScFileExist = false;

//��ȡ�����б�
function play(){
	if (IsIE() == 1) 
	{
		var loclXmlDoc = new ActiveXObject("Msxml2.DOMDocument");
		loclXmlDoc.async = false;
		loclXmlDoc.load(localURL + "/" + scPLFileName);// ����scfile.xml
		var state = loclXmlDoc.readyState;
		if (state == 4) 
		{
			var oNodes = loclXmlDoc.selectNodes("//root/media");
			var localSize = oNodes.length;
			for(var i=0;i<localSize;i++)
			{
				// ��ȡ�����ļ��е�image�б�
			    if(getXmlData(oNodes[i], "resFormat") == 'jpg' || getXmlData(oNodes[i], "resFormat") == 'jpeg')
			    {
			    	// ��ȡͼƬ����
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
	    //��IE�����
	}
}

// ��������
var j = 0;

// ��Ч�ļ�����
var failedImageCnt = 0;
// ����jpg
function playStartOth()
{
	// �����б�Ϊ��
	if(imageList.length == 0||!hasScFileExist)
	{
		screenCancel();
	}
	
	// ����
	doChangeImage();
	
	// ѭ������jpg
	setInterval("doChangeImage()",waitTime*1000);
}

// չʾjpg
function doChangeImage()
{
	// modify begin by l00263786 2015-06-01 OR_SD_201504_102 �����ն�����ʵ��ȫʡͳһ����
	// ͼƬ���ŵ����һ�ţ����ص�һ��
	if(j == imageList.length)
	{
		j=0;
	}
	// ͼƬ�ľ���·��
	var scFileAbsolutePath;
	// ͨ��ѭ����ȡһ�Ŵ��ڵ�ͼƬ��ֱ�����������б�Χ 
	while(j<imageList.length)
	{
		if(failedImageCnt == imageList.length)
		{
		   	screenCancel();
		}
		// ��ȡ�����ļ�����·�� 
		scFileAbsolutePath = localURL+ "/"+imageList[j];
		// �����ļ��������´ν�����ȡ��һ�������ļ�
		j++;
		// ����ļ����� ��������Ϊ����
		if(scFileExist(scFileAbsolutePath))
		{
            document.getElementById("imageNode").src = scFileAbsolutePath;
            // �������óɹ����˳�ѭ��
			break;
		}
		else
		{
			failedImageCnt++;
		}
	}
	// modify end by l00263786 2015-06-01 OR_SD_201504_102 �����ն�����ʵ��ȫʡͳһ����
}

// ���ݽڵ�����ȡ�ڵ�����
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
