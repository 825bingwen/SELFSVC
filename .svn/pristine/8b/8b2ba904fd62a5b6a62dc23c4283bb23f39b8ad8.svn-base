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
	
	window.parent.netload(printDataObject);
}
</script>
</head>
<body>
</body>
</html>
