<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.hub.selfsvc.chooseTel.model.NetNbrPO" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	// ȫ������
	List<NetNbrPO> netNbrTmpList = (List<NetNbrPO>)request.getAttribute("netNbrTmpList");
	
	// ��ѯ�����б���
	List<NetNbrPO> netNbrList = (List<NetNbrPO>)request.getAttribute("netNbrList");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
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
// ��������¼�
document.onkeydown = keyDown;
function keyDown(e)
{
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

// ѡ�����
function selectNetNbr(netNbr)
{
	if (netNbr == '')
	{
		document.getElementById('netNbr').value == '';
		return;
	}
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('netNbr').value = netNbr;
	document.getElementById('chooseNum').innerHTML = '';
	if ('' != netNbr)
	{
		document.getElementById('chooseNum').style.visibility='visible';
		document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">�ŶΣ�'+netNbr+'</p>';
	}
}

// ��һҳ
function nextPage(linkURL)
{
	// ִ�в�ѯ
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}		
}

// ת������������ҳ��
function finalNbrSearch()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/phoneSearchByFinalNbr.action?bz=1";
		document.actform.submit();
	}		
}

// ת�����Ŷ�����ҳ��
function netValueSearch()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }chooseTel/phoneSearchByNetValue.action?bz=1";
		document.actform.submit();
	}		
}

// �����Ž�������
function doNetNbrSearch()
{
	if (document.getElementById('netNbr').value == '')
	{
		return;
	}
	document.actform.target="_self";
	document.actform.action="${sessionScope.basePath }chooseTel/netNbrSearchResult.action?bz=1";
	document.actform.submit();	
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
			<input type="hidden" name="netNbr" id="netNbr" value="">
			<input type="hidden" name="finalNbr" id="finalNbr" value="">
				
          <%@ include file="/customer.jsp"%>
          <a title="����������" href="#" class="bt5 num_intro x_66" onmousedown="this.className='bt5on num_intro x_66'" onmouseup="this.className='bt5 num_intro x_66';netValueSearch()">����������</a>
          <a title="��β������" href="#" class="bt5 num_backtonum" onmousedown="this.className='bt5on num_backtonum'" onmouseup="this.className='bt5 num_backtonum';finalNbrSearch()">��β������</a>
          <div class="num_brand fs24 lh2 p20">
           ����������
          </div>
       
		<div class="box842W fl ml20 relative tab_966  tab_966_result " style="display:inline">
                    
                    <div class="bg2"></div>
                    <div class="blank50"></div><div class="  blank50"></div>
                    <div class="num_dis" >
                      <p class="fs18">��<span class="yellow"><%=netNbrTmpList.size() %>��</span>���������������ż��ɷ���ѡ����������</p>
                      <div class="blank10"></div>
                      <%
					  for(NetNbrPO po:netNbrList)
					  {
					  %>
                      <a title="" onclick="selectNetNbr('<%=po.getNetNbr() %>')" href="javascript:void(0)"><span></span><%=po.getNetNbr() %></a>
                      <%
                      }
                      %>
                    </div>
                    
                    
                    <div class="  blank10"></div>
                    
                    <div class="num_foot fs18 pt0 pl23"> 
                      <s:if test="pageFlag == 'true'">
    						<pg:paginator recordsCount="${recordCount }" pageSize="${pageviewnum }" page="${page }" linkUrl="chooseTel/phoneSearchByNetNbr.action" alignType="left" />
    				</s:if>
    				<s:else>
    						<div class='page-left'></div>
    				</s:else>
                      <div class="fright1">
                        <span class="fl pt15">��ѡ���ŶΣ�</span>
                        <a title="�Ŷ�" href="javascript:void(0)" class="bt_choosenum2 fl" style=" visibility:hidden" id="chooseNum">
                         		<p class="fs16 tc" id="telnumText">�ŶΣ�138</p>
                        </a>
                        <a title="����" href="javascript:void(0)" class="bt2 fr relative fl"  onmousedown="this.className='bt2on fl relative'" onmouseup="this.className='bt2 fl relative';" onclick="doNetNbrSearch();return false;">����</a>
                      </div>
                    </div>
					
				</div>
                
				
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
