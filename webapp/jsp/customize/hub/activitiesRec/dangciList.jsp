<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script>
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
		goback("<s:property value='#request.curMenuId' />");
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
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryActivities.action";
		document.forms[0].submit();
	}
}

function queryDangCiDesc(dangciId,dangciName,activityId,activityName,prepayFee)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// �����
		document.getElementById("activityId").value = activityId;
		
		// �����
		document.getElementById("activityName").value = activityName;
		
		// ���α���
		document.getElementById("dangciId").value = dangciId;
		
		// ��������
		document.getElementById("dangciName").value = dangciName;
		
		// ������
		document.getElementById("prepayFee").value = prepayFee;

		// �ύ
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }activitiesRec/queryDangCiDesc.action";
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
	<form name="actform" method="post">
	
		<!-- Ӫ���Ƽ���ʶ -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- ����� -->
		<input type="hidden" id="activityId" name="activityId" value=""/>
		
		<!-- ����� -->
		<input type="hidden" id="activityName" name="activityName" value=""/>
		
		<!-- ���α��� -->
		<input type="hidden" id="dangciId" name="dangciId" value=""/>
		
		<!-- �������� -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- ������� -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- ������ -->
		<input type="hidden" id="prepayFee" name="prepayFee" value=""/>
		
		<!-- ���ID -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />">
		
		<!-- ��ǰҳ -->
		<input type="hidden" name="currentPage_danci" id="currentPage_danci" value='<s:property value="#request.currentPage_danci" />'/>
		<!-- ������ -->
		<input type="hidden" name="countNum_danci" id="countNum_danci" value='<s:property value="#request.countNum_danci" />'/>
		<!-- ��ҳ�� -->
		<input type="hidden" name="countPageNum_danci" id="countPageNum_danci" value='<s:property value="#request.countPageNum_danci"/>'/>
		<!-- ÿҳ���� -->
		<input type="hidden" name="pageNum_danci" id="pageNum_danci" value='<s:property value="#request.pageNum_danci" />'/>
		
		<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��������̣�</h2>
      					<div class="blank10"></div>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="ѡ������" href="#" class="on">2. ѡ������</a>
      					<a title="����Э��" href="#">3. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">4. ѡ��֧����ʽ</a> 
      					<a title="Ͷ��ֽ��" href="#">5. Ͷ��ֽ��</a>
          				<a title="���" href="#">6. ���</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>�����б�<span class="yellow"></span>
						</p>
						<div class="blank10"></div>
					<table width="100%" class="tb_blue" >
	                  <tr>
	                    <th scope="col">��������</th>
	                    <th scope="col">��Ʒ��ֵ(Ԫ)</th>
	                    <th scope="col">������(Ԫ)</th>
	                    <th scope="col">����</th>
	                  </tr>
	                  <s:iterator value="activityList" id="list" status="st">
	                  <tr>
		                    <td><s:property value="#list.dangciName" /></td>
		                    <td><s:property value="#list.presentValue" /></td>
		                    <td><s:property value="#list.prepayFee" /></td>
		                    <td>
	                    		<input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="ȷ��" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';queryDangCiDesc('<s:property value="#list.dangciId" />','<s:property value="#list.dangciName" />', '<s:property value="#list.activityId" />','<s:property value="#list.activityName" />','<s:property value="#list.prepayFee" />')"/>
	                    	</td>
	                    	            
	                  </tr>
	                  </s:iterator>
	                </table>
  						
  						
					</div>	
					
					<!-- ��ҳ -->
					<pg:paginator recordsCount="${recordCount }" pageSize="${pageNum_danci }" page="${page }" linkUrl="activitiesRec/queryDangCiList.action" />
						
				</div>	
			</div>	
			
			<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
