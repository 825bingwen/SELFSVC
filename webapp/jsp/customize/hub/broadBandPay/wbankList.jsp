<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.broadBandPay.model.WBandVO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

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
//��ֹҳ���ظ��ύ
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	//����
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//��仰��ؼ�
	}
}

function KeyIsNumber(KeyCode) 
{
	//ֻ��������0-9
	if (KeyCode >= 48 && KeyCode <= 57)
	{
		return true;
	}
	return false;
}

function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

function qryPayType(ncode,prodName,detailDesc,fee)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// �����ƷNCODE
		document.getElementById('ncode').value = ncode;
		
		// �����Ʒ����
		document.getElementById('prodName').value = prodName;
		
		// �����Ʒ����
		document.getElementById('detailDesc').value = detailDesc;
		
		// ������
		document.getElementById('fee').value = fee;

		// �ύ
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }broadBandPay/qryPayType.action";
		document.forms[0].submit();
	}
}

// ��һҳ
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = linkURL;
		document.forms[0].submit();
	}
}

</script>
</head>
	<body scroll="no">
			<form id="actform" name="actform" method="post">
			
			<!-- �����ƷNCODE -->
			<input type="hidden" id="ncode" name="ncode" value=""/>
			
			<!-- �����Ʒ���� -->
			<input type="hidden" id="prodName" name="prodName" value=""/>
			
			<!-- �����Ʒ���� -->
			<input type="hidden" id="detailDesc" name="detailDesc" value=""/>
			
			<!-- ������ -->
			<input type="hidden" id="fee" name="fee" value=""/>
			
			<!-- �ֻ�������region -->
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			
			<!-- ��ǰҳ -->
			<input type="hidden" name="currentPage" id="currentPage" value='<s:property value="#request.currentPage" />'/>
			<!-- ������ -->
			<input type="hidden" name="countNum" id="countNum" value='<s:property value="#request.countNum" />'/>
			<!-- ��ҳ�� -->
			<input type="hidden" name="countPageNum" id="countPageNum" value='<s:property value="#request.countPageNum"/>'/>
			<!-- ÿҳ���� -->
			<input type="hidden" name="pageNum" id="pageNum" value='<s:property value="#request.pageNum" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>����ɷ����̣�</h2>
      					<div class="blank10"></div>
      					<a title="�������˺�" href="#">1. �������˺�</a>
      					<a title="ѡ������Ʒ" href="#" class="on">2. ѡ������Ʒ</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
      					<a title="Ͷ��ֽ��" href="#">4. Ͷ��ֽ��</a>
          				<a title="���" href="#">5. ���</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
					<div class="blank10"></div>
					<table width="100%" class="tb_blue" >
	                  <tr>
	                    <th scope="col">��Ʒ����</th>
	                    <th scope="col">������Ϣ</th>
	                    <th scope="col">�������(Ԫ)</th>
	                    <th scope="col">����</th>
	                  </tr>
	                  <s:iterator value="wbankList" id="list" status="st">
	                  <tr>
		                    <td><s:property value="#list.prodName" /></td>
		                    <td><s:property value="#list.detailDesc" /></td>
		                    <td><s:property value="#list.fee" /></td>
		                    <td>
	                    		<input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="ȷ��" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';qryPayType('<s:property value="#list.ncode" />','<s:property value="#list.prodName" />','<s:property value="#list.detailDesc" />','<s:property value="#list.fee" />')"/>
	                    	</td>
	                    	            
	                  </tr>
	                  </s:iterator>
	                </table>
  					</div>	
					<!-- ��ҳ -->
					<pg:paginator recordsCount="${recordCount }" pageSize="${pageNum }" page="${page }" linkUrl="activitiesRec/queryActivities.action" />
					
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
			
		</form>
	</body>
</html>

