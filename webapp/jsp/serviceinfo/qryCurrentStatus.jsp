<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import = "com.gmcc.boss.selfsvc.login.model.NserCustomerSimp" %>
<%
String currProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

//add begin xkf29026 2011/10/19 ��ǰ״̬��ѯ���û����ֲ���ʾȫ����ֻ��ʾ�գ����������*���档����ɽ��
String custName = ((NserCustomerSimp)request.getAttribute("customer")).getCustomerName();

if(Constants.PROOPERORGID_SD.equalsIgnoreCase(currProvince))
{
    if(custName != null && custName.length() > 0)
    {
        //modify begin l00190940 2011/10/25 R003C11L10n01 ��ǰ״̬��ѯ��ʱ����ʾ�û�ȫ�����̶���ʾ����**�����ֵ����һ���֣����û������Σ���ʾ**�� ����ɽ��
        String custNameString = custName.substring(custName.length()-1);
        custName = "**" + custNameString;
        //modify end l00190940 2011/10/25 R003C11L10n01 ��ǰ״̬��ѯ��ʱ����ʾ�û�ȫ�����̶���ʾ����**�����ֵ����һ���֣����û������Σ���ʾ**�� ����ɽ��
    }
}
//add end xkf29026 2011/10/19 ��ǰ״̬��ѯ���û����ֲ���ʾȫ����ֻ��ʾ�գ����������*���档����ɽ��

//add begin yWX163692 2013/11/21  OR_NX_201310_1186  ���Ŀͻ�������Ϣģ����չʾ
if(Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
{
	custName = CommonUtil.getVagueName(custName);
}
//add end yWX163692 2013/11/21  OR_NX_201310_1186  ���Ŀͻ�������Ϣģ����չʾ

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
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
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.getElementById("curMenuId").value = curmenu;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }login/backForward.action";
		document.actform.submit();		
	}			
}

// ��������¼�
document.onkeyup = pwdKeyboardUp;
		
function pwdKeyboardUp(e)
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
    
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R �˳�
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:����
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		if (key == 48)
		{
			//topGo('qryService', 'serviceinfo/serviceInfoFunc.action'); 
			goback("<s:property value='curMenuId' />") ;
		}
	<%
	}
	%>
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
		  	<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
			  	<%@ include file="/customer.jsp"%>
				
				<div class="blank50">
				<%
				if ("1".equals(keyFlag))
				{
				%>
					<a href="#" class="bt10_1 fr mr92" onmousedown="this.className='bt10_1on fr mr92'" onmouseup="this.className='bt10_1 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">����<%=parentMenuName %>&nbsp;(��0��)</a>
				<%
				}
				else
				{
				%>
					<a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">����<%=parentMenuName %></a>
				<%
				}
				%>
				</div>
				<div class="b966">
	          		<div class=" p40">
	          			<div class="blank30"></div>
	            		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
	            		<div class="blank30"></div>
	            		<div class="relative p40">	            
	            			<div class="blank15"></div>
	            			<div class="p20">
				                <table width="100%" class="tb_blue" >
				                  <tr>
				                    <th scope="col">�ֻ�����</th>
				                    <th scope="col">�ͻ�����</th>
				                    <th scope="col">��������</th>
				                    <%
				                    if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
				                    {
				                    %>
				                    <th scope="col">Ʒ������</th>
				                    <%
				                    }
				                     %>				                    
				                    <th scope="col">��ǰ״̬</th>
				                  </tr>
				                  <tr>
				                    <td><s:property value="customer.servNumber"/></td>
				                    <td><%=custName %></td>
				                    <td><s:property value="customer.regionName"/></td>
				                    <%
				                    if (!Constants.PROOPERORGID_NX.equalsIgnoreCase(currProvince))
				                    {
				                    %>
				                    <td><s:property value="customer.brandName"/></td>
				                    <%
				                    }
				                    %>				                    
				                    <td><s:property value="currentStatus"/></td>
				                  </tr>
				                </table>	
	             			</div>	             
						</div>
	            	</div>
	          	</div>
		  	</div>
		  	<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
	<script type="text/javascript">
	    openSurveyDialog();
	</script>
	<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032 -->
</html>
