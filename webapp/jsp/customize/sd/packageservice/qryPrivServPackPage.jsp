<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

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
	// ����˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
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
    
	//82:R 220:����
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
  		return ;
	}
}

// ��һҳ
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading();	
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}
}

// ��������ҳ
function goDetail(ncode,region)
{
	if (submitFlag == 0)
	{
		openRecWaitLoading();
		document.getElementById("ncode").value = ncode;
		document.getElementById("region").value = region;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }privServPack/qryPrivServPackDetail.action";
		document.actform.submit();
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
		  	<%@ include file="/titleinc.jsp"%>
		  	<input type="hidden" name="ncode" id="ncode" value=""></input>
		  	<input type="hidden" name="region" id="region" value=""></input>
		  	<div class="main" id="main">
		  		<%@ include file="/customer.jsp"%>
				<div class="blank20"></div>
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- �б����� -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn" >
			                 		<s:if test="privServPackList != null && privServPackList.size > 0">
								    	<table width="95%" class="tb_blue" align="right">
								    		<tr>
				                      			<th width="30%">�ػ�ҵ��</th>
				                      			<th width="20%">���ܷ�(Ԫ)</th>
				                      			<th width="20%">��Чʱ��</th>
				                      			<th width="20%">ʧЧʱ��</th>
				                      			<th width="10%">����</th>
							     			</tr>
								        	<s:iterator value="privServPackList" id="privServPack">
								            	<tr>
								                    <td align="center">
								                    	<s:property value="#privServPack.privName"/>
								                    </td>
								                    <td align="center">
								                    	<s:property value='#privServPack.privFee'/>
								                    </td>
								                    <td align="center">
								                    	<s:property value="#privServPack.effDate"/>
								                    </td>
								                    <td align="center">
								                    	<s:property value="#privServPack.endDate"/>
								                    </td>
								                    <td>
								                    	<input type='button' class='bt2_liebiao white' value='�鿴' 
								                    	onmousedown="this.className='bt2_liebiao_on white'" 
								                    	onmouseup="this.className='bt2_liebiao white';goDetail('<s:property value="#privServPack.ncode"/>','<s:property value="#privServPack.region"/>');" />
								                    </td>
								            	</tr>
								        	</s:iterator>
								        </table>
							    	</s:if>
				                  	<p class="blank10"></p>
								</div>								
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h" >
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <%--����������--%>
                <%-- ��ҳ--%>
                <div>
                	<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="privServPack/qryPrivServPack.action" />
                </div>
        	</div>                
			<%-- ����ҳ --%>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
