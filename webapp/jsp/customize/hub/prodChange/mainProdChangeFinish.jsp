<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String successBz = (String)request.getAttribute("successBz");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dummyKey.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}css/IE8.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = curmenu;
		/**
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
		**/
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
		document.actform.submit();
	}			
}

// ���������¼�
document.onkeydown = keyDown;
function keyDown(e)
{
	//8��32��66��77 ����
    //82��220 ����
    //13��89��221 ȷ��
    //80 ��ӡ
    //85 ��һҳ
    //68 ��һҳ
    
	//���ռ�����
	var key = GetKeyCode(e);
     
    //8:backspace 32:�ո� B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:����
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}

</script>
</head>
<body scroll="no" >
<form name="actform" method="post"">			
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<div class="b966 tc">
			<div class="blank30"></div>
			<div class=" p40">
				<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
				<div class="blank60"></div>
				<div class="blank60"></div>
				<span class="yellow fs30">				
					<ul><li>�����Ʒ����ɹ���&nbsp;&nbsp;&nbsp;&nbsp;</li></ul>
				</span>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>