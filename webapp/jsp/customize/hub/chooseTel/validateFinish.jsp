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
	// modify begin g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
	<%
	if ("0".equals(successBz))
	{
	%>
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = curmenu;
			
			document.actform.target="_self";
			document.actform.action="${sessionScope.basePath }chooseTel/selectRegion.action?bz=1";
			document.actform.submit();
		}
	<%
	}
	else
	{
	%>
		history.go(-1);
	<%
	}
	%>
	// modify end g00140516 2012/05/31 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��	
}

// ��������¼�
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

// ��ӡƾ֤
function printInfo()
{
	//�Ѿ�����˷��أ������ٽ��д�ӡ
	if (submitFlag == 1)
	{
		return;
	}
	var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
	var Ret3;
	try{
		Ret3 = DPsListPrinter1.PrintPicture(1);
	}
	catch(e)
	{
		alertError("���ڴ�ӡ���ؼ�δ��װ�����ĺ���ԤԼƾ֤��ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		return;
	}
   	if (Ret3 == 1)
   	{
      	alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ����ĺ���ԤԼƾ֤��ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
   	else if (Ret3 == 41)
   	{
      	alertError("���ڴ�ӡ���豸�ײ���������δ��װ�����ĺ���ԤԼƾ֤��ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
   	
   	var Ret4;
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ��ӭʹ���й��ƶ�");
	Ret4 = DPsListPrinter1.PrintLine("      ����ѡ�ſ�������");
	Ret4 = DPsListPrinter1.PrintLine("      �绰���룺");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='telnum' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ��֤�룺");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='coid' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ʧЧʱ�䣺");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='ctime' />");
	Ret4 = DPsListPrinter1.PrintLine("      ");
    Ret4 = DPsListPrinter1.PrintLine("      ��Ԥ���<s:property value='payfeeY' />Ԫ");
	Ret4 = DPsListPrinter1.PrintLine("      ");
	Ret4 = DPsListPrinter1.PrintLine("      ѡ��Ӫҵ����");
	Ret4 = DPsListPrinter1.PrintLine("      <s:property value='locus' escape="false"/>");
    Ret4 = DPsListPrinter1.PrintLine("      ������ʾ��");
    <%         
         /*************��ӡ���������ʾ***************/
         int n = 12;//һ������ַ�����         
         int L=((String)request.getAttribute("cremind")).length();    //�ַ�������ΪL,
			   int num=(L /n);        // nΪÿ��ȡ���ַ�������numΪ�ж������������Ӵ�
				
			if (L==0)
			{
		%>
				Ret4 = DPsListPrinter1.PrintLine("      ");
		<%
		  			
			}else	if (L<=n)
			{
				 String s=(String)request.getAttribute("cremind");
	  %>
				 Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
		<%
		  			
			}else {
			int m=0;
			for(int i=0;i<num;i++){  	
				String s=((String)request.getAttribute("cremind")).substring(m,m+n);
	  %>
				Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
		<%
				m=m+n;
				}
				
		  if (m<L){
		  		String s=((String)request.getAttribute("cremind")).substring(m,L);
		%>
		  		Ret4 = DPsListPrinter1.PrintLine("      <%=s%>");
	  <%
		  	}	
		  }   
    %>       
	
	if (Ret4 == 1)
   	{
      	alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
   	else if (Ret4 == 41)
   	{
      	alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
   	
	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
   	if (Ret4 == 1)
   	{
      	alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
   	else if (Ret4 == 41)
   	{
      	alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      	return;
   	}
  	
   	Ret4 = DPsListPrinter1.PrintLine("  �������ݣ����в���֮��������ӪҵԱ��ѯ��");
   	Ret4 = DPsListPrinter1.PrintLine("  �ͻ��������ߣ�10086");
   	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
   	Ret4 = DPsListPrinter1.PrintLine("  ��ӡ�ص㣺<s:property value='locus' escape="false"/>");
   	Ret4 = DPsListPrinter1.PrintLine("  ��ӡʱ��: " + getCurrentTime());
   	
   	//��ֽ
	var Ret5 = DPsListPrinter1.SetCutPaper();
	if (Ret5 == 1)
	{
		alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		return;
	}
	else if (Ret5 == 41)
	{
		alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		return;
	}
}

// ҳ�����ʱִ��
function finish()
{
	if ('<s:property value="successBz" />' == '0')
	{
		printInfo();
	}
}

<!-- Add by LiFeng ֱ�Ӵ�ӡ -->
function doFinish()
{
    finish();
}
</script>
</head>
<body scroll="no" onload="doFinish();">
<form name="actform" method="post"">			
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<a title="����ѡ�ſ���" href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">����ѡ�ſ���</a>
		<div class="b966 tc">
			<div class="blank30"></div>
			<div class=" p40">
				<p class="tit_info" align="left"><span class="bg"></span>����ѡ��</p>
				<div class="blank60"></div>
				<div class="blank60"></div>
				<span class="yellow fs30">
					<%
					if ("0".equals(successBz))
					{
					%>
					<ul><li>�ֻ��ţ�<s:property value="telnum"/>&nbsp;�ѳɹ�ԤԼ,�����Ʊ��ܴ�ӡƾ��!&nbsp;&nbsp;&nbsp;&nbsp;</li></ul>
					<br />
					<ul><li>����48Сʱ�ڵ�Ӫҵ������!&nbsp;&nbsp;&nbsp;&nbsp;</li></ul>
					<!-- Change by LiFeng ֱ�Ӵ�ӡ
					<br>
					<a href="#" class="bt2 fs16" onclick="finish()">��ӡƾ֤</a>
					 -->
					<%
					}
					else
					{
					%>
					<ul><li><s:property value="alertMsg"/></li></ul>
					<%
					}
					%>
				</span>
			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
