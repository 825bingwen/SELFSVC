<%--
 @User: 高群/g00140516
 @De: 2012/02/09
 @comment: NG3.5帐详单改造之详单查询
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_huawei_201112_953
--%>
<%@ page contentType="text/html; charset=GBK"%>

<%
    String printDataObject = (String) request.getAttribute("printData");  

    if (printDataObject == null)
    {
    	printDataObject = "var printDataObject = {}";
    }              			   
%>

<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<%
	out.print("<script type='text/javascript'>"+printDataObject+";</script>");
%>
<script type='text/javascript'>
window.onload = function() {
	if (typeof(printRecords) != "undefined" && printRecords != null)
	{
		printDataObject['records'] = printRecords;
	}
	
	window.parent.netload_SD(printDataObject);
}
</script>
</head>
<body>
</body>
</html>
