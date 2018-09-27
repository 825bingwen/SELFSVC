<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
String path = request.getContextPath();
// FTP地址(终端机日志上传的FTP地址,用户名与密码终端厂商自已配置)
String logftpaddr = (String) PublicCache.getInstance().getCachedData(Constants.SH_LOGFTPADDR);
if (logftpaddr == null)
{
	logftpaddr = "";
}

// 日志上传间隔分钟数
String cashInterval = (String) PublicCache.getInstance().getCachedData("SH_CASHINTERVAL");
if (cashInterval == null || "".equals(cashInterval.trim()))
{
	cashInterval = "60";
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>获取终端IP</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<object id="mgrpluginid" classid="CLSID:90B097CF-4A8F-47B8-B7C2-166153FB4155"></object>
<script type="text/javascript">

function init()
{
    try 
    {
  		var ret = document.getElementById("mgrpluginid").InitManageConf("", "<%=logftpaddr %>", "<%=cashInterval %>");
  		if (ret != 0) 
  		{
  		  	 window.location = "<%=path%>/errorIP.jsp?errorType=001";
  		  	 return;
  		}
		var ip = document.getElementById("mgrpluginid").GetLocalIpaddress();
	    if(ip == "" || ip == undefined)
	    {
	  		window.location = "<%=path%>/errorIP.jsp?errorType=002";
	  		return;
	    }
	    
	    //add begin cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
		var mac = document.getElementById("mgrpluginid").GetLocalMac();
	    if(mac == "" || mac == undefined)
	    {
	  		window.location = "<%=path%>/errorIP.jsp?errorType=005";
	  		return;
	    }
	    
	    window.location = "<%=path%>/frame.jsp?termIP=" + ip + "&termMac=" + mac; 
		//add end cKF48754 2011/11/01 R003C11L11n01 OR_huawei_201111_149
	    
	  	return;
    	
  	}
  	catch (e)
  	{
  		window.location = "<%=path%>/errorIP.jsp?errorType=003";
  		return;
  	}
}  	
</script>
  </head>
  
  <body onload="init();">
  </body>
</html>
