<%--
 @User: ��Ⱥ/g00140516
 @De: 2012/09/05
 @comment: �������л���ҵ��
 @remark create g00140516 2012/09/05 R003C12L07n01 OR_NX_201206_794
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%	
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
    if (key == 8 || key == 32 || key == 66 )
    {
    	return false;
    }
    
    //82:�˳�
	if (key == 82 || key == 220)
	{
		window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		return;
	}
	
	<%
	if("1".equals(keyFlag))
	{
	%>
		if(key == 48)
		{
			topGo('rec', 'reception/receptionFunc.action');
		}
		if(key == 77)
		{
			cancelBindInfo();
		}
	<%
	}
	%>				
}

function cancelBindInfo()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }bankhuakou/cancelBindInfo.action";
		document.actform.submit();		
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
		  	<input type="hidden" id="pan" name="pan" value="<s:property value='pan' />" />
		  	
		  	<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
			  	<%@ include file="/customer.jsp"%>
			  	<div class="blank50">
			  	<%
				if("1".equals(keyFlag))
				{
				%>
					<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">����<%=parentMenuName %>(��0��)</a>
				<%
				}
				else
				{
				%>
					<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">����<%=parentMenuName %></a>
				<%
				}
				%>
				</div>
	          	<div class="b966">
	          		<div class=" p40">
	          			<div class="blank30"></div>
	            		<div class="blank30"></div>
	            		<div class="relative p40">	            
	            			<div class="blank15"></div>
	            			<div class="p20">
				                <table width="100%" class="tb_blue" >
				                  <tr>
				                    <th scope="col">����</th>
				                    <th scope="col">֧����ʽ</th>
				                    <th scope="col">��������</th>
				                    <th scope="col">���۽��</th>                    
				                    <th scope="col">��ͨʱ��</th>
				                  </tr>
				                  <tr>
				                    <td><s:property value="pan" /></td>
				                    <td>���л���</td>
				                    <td><s:property value="drawType" /></td>
				                   	<td><s:property value="drawAmt" /></td>				                    
				                    <td><s:property value="createTime" /></td>
				                  </tr>
				                </table>	
	             			</div>
	             			
	             			<div class="btn_box tc" style="margin-top: 150px;">
					    		<span class=" mr10 inline_block ">
					    		<%
								if("1".equals(keyFlag))
								{
								%>
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';;" onclick="cancelBindInfo();">ȡ��(�������)</a>
								<%
								}
								else
								{
								%>
									<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';;" onclick="cancelBindInfo();">ȡ��</a>
								<%
								}
								%>
					    		</span>
				    		</div>             
						</div>
	            	</div>
	          	</div>
		  	</div>
		  	<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
