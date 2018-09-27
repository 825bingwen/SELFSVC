<%@ page contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ChildProdPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.AddAttrPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.SubsChildProdPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.SpeakProdPO"%>


<%
NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);

// ��ǰ��ƷPO
ProdConfigPO prodConfigPO = (ProdConfigPO)session.getAttribute(Constants.PROD_INFO);

// �Ӳ�Ʒ
ChildProdPO childProdPO = prodConfigPO.getChildProdPO();

// �Ӳ�Ʒ�б�
List<SubsChildProdPO> subsChildProdPOList = childProdPO.getSubsChildProdPOList();

// �Ѷ����Ĳ�Ʒ
List<SpeakProdPO> speakProdPOList = (List<SpeakProdPO>)request.getAttribute("speakProdPOList");

// ��Ч��ʽ
String effectType = "";

// ��ͨ��Ч��ʽ
String effectRec = "";

// �˶���Ч��ʽ
String effectDel = "";

// �޸���Ч��ʽ
String effectMod = "";

if(null != prodConfigPO.getEffectType())
{
	effectType = prodConfigPO.getEffectType();
}

if(null != prodConfigPO.getEffectRec())
{
	effectRec = prodConfigPO.getEffectRec();
}

if(null != prodConfigPO.getEffectDel())
{
	effectDel = prodConfigPO.getEffectDel();
}

if(null != prodConfigPO.getEffectMod())
{
	effectMod = prodConfigPO.getEffectMod();
}

Map<String, String> tipsMap = (Map<String, String>) request.getAttribute("tipsMap");
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�Ӳ�Ʒҳ��</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/cookie.js"></script>
<script>
var submitFlag = 0;
    	
function doSub(prodID,region,brand)
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//�ύ���
		document.actform.target = "_self";
		<%
		if(null != custInformation)
		{
		%>
			document.actform.action = "${sessionScope.basePath}quickPublish/prodRec.action";
		<%
		}
		else
		{
		%>
			document.actform.action = "${sessionScope.basePath}quickPublish/authPassword.action";
		<%
		}
		%>
		document.actform.submit();	
	}
}

//����ȷ�Ͽ�
openWindow = function(id)
{
	wiWindow = new OpenWindow("popup_confirm",708,392);//�򿪵�����������
}

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
		goback();
		return;
	}
}

// ѡ���Ӳ�Ʒ
function selectSubProd(id)
{
	// id pkgid~prodid~privid
	var ids = id.split('~');
	
	// ��Ʒ������
	var pkgid = ids.length >= 2 ? ids[0] : '';
	
	// ��Ʒ����
	var prodid = ids.length >= 2 ? ids[1] : '';
	
	// �Żݱ���
	var privid = ids.length >= 3 ? ids[2] : '';
	
	// ��������ѡ���
	var objs = document.getElementsByName('ids');
	
	if (document.getElementById(id).value == '��  ����Ʒ')
	{
		document.getElementById(id).value = '���̡���Ʒ';
		for (var i=0;i<objs.length;i++)
		{
			if (objs[i].id.indexOf(pkgid+'~'+prodid+'~') != -1 && objs[i].id != id && document.getElementById(objs[i].id+'_td').innerHTML == '��ѡ')
			{
				objs[i].value = '���̡��Ż�';
			}
		}
	}
	else if (document.getElementById(id).value == '���̡���Ʒ')
	{
		if (document.getElementById(id+'_td').innerHTML == '��ѡ')
		{
			alertRedErrorMsg("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ò�Ʒ�Ǳ�ѡ��Ʒ������ɾ����");
			return;
		}
		document.getElementById(id).value = '��  ����Ʒ';
		for (var i=0;i<objs.length;i++)
		{
			if (objs[i].id.indexOf(pkgid+'~'+prodid+'~') != -1 && objs[i].id != id)
			{
				objs[i].value = '��  ���Ż�';
			}
		}
		
	}
	else if (document.getElementById(id).value == '��  ���Ż�')
	{
		document.getElementById(id).value = '���̡��Ż�';
		document.getElementById(pkgid+'~'+prodid+'~').value = '���̡���Ʒ';
		for (var i=0;i<objs.length;i++)
		{
			if (objs[i].id.indexOf(pkgid+'~'+prodid+'~') != -1 && objs[i].value == '��  ���Ż�' && document.getElementById(objs[i].id+'_td').innerHTML == '��ѡ')
			{
				objs[i].value = '���̡��Ż�';
			}
		}
	}
	else if (document.getElementById(id).value == '���̡��Ż�')
	{
		if (document.getElementById(id+'_td').innerHTML == '��ѡ')
		{
			alertRedErrorMsg("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���Ż��Ǳ�ѡ�Żݣ�����ɾ����");
			return;
		}
		document.getElementById(id).value = '��  ���Ż�';
	}
	
}

// ���� subprods = ������~��Ʒ����~�Żݱ���^������~��Ʒ����~�Żݱ���
function setChildProdIds()
{
	// ���ѡ�е��Ӳ�Ʒ�б� 
	document.getElementById('childProdIds').value = '';
	
	// ���hidden
	var objs = document.getElementsByName("ids");
	var strByids = '';
	for(var i=0;i<objs.length;i++)
	{
		if (objs[i].value == '���̡���Ʒ' || objs[i].value == '���̡��Ż�')
		{
			if (strByids == '')
			{
				strByids = objs[i].id;
			}
			else
			{
				strByids = strByids + '^' + objs[i].id;
			}
		}
	}
	
	// ѡ�е��Ӳ�Ʒ
	document.getElementById('childProdIds').value = strByids;
	
}

// �Ӳ�Ʒ����ҳ��
// id ������~��Ʒ����~�Żݱ���
function subProdAddAttr(id)
{
	if ("PCOpDel" == document.getElementById('opertype').value)
	{
		return;
	}
	
	// ѡ�е��Ӳ�Ʒ
	setChildProdIds();
	
	// ��ѯ�Ӳ�Ʒ����ʱ�õ����Ӳ�ƷID
	document.getElementById('subProdId').value = id;
	
	openQryWaitLoading('qryWaitLoading');
	
	// �ύ
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }quickPublish/subProdAddAttr.action";
	document.actform.submit();
}

// ����
function doCommit()
{
	var minprod = <%=childProdPO.getMinprod() %>;// ��ѡ��Ʒ��С��
	var maxprod = <%=childProdPO.getMaxprod() %>;// ��ѡ��Ʒ�����
	
	// ���hidden
	var objs = document.getElementsByName("ids");
	
	// �趨ѡ�е��Ӳ�Ʒ
	setChildProdIds();
	
	// �մ�
	if (document.getElementById('childProdIds').value == '')
	{
		alertRedErrorMsg("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ѡ���Ӳ�Ʒ��");
		return;
	}
	
	// У���Ƿ��޸Ĺ�
	if (document.getElementById('opertype').value == 'PCOpMod')
	{
		var ids_old = document.getElementById('childProdIds_old').value.split('^');
		var ids_new = document.getElementById('childProdIds').value.split('^');
		var bz = 0;
		if (ids_old.length == ids_new.length)
		{
			// ids_old | ids_new 
			for(var i=0;i<ids_old.length;i++)
			{
				var bz_ = 0;
				for(var j=0;j<ids_new.length;j++)
				{
					if (ids_old[i] == ids_new[j])
					{
						bz_ = 1;
						break;
					}
				}
				if (bz_ == 0)
				{
					bz = 1;
					break;
				}
			}
			
			// ids_new | ids_old
			for(var i=0;i<ids_new.length;i++)
			{
				var bz_ = 0;
				for(var j=0;j<ids_old.length;j++)
				{
					if (ids_old[i] == ids_new[j])
					{
						bz_ = 1;
						break;
					}
				}
				if (bz_ == 0)
				{
					bz = 1;
					break;
				}
			} 
		}
		else
		{
			bz = 1;
		}
		
		// �Ӳ�Ʒ�ޱ䶯
		if (bz == 0)
		{
			var childProdAddAttrs_new = document.getElementById('childProdAddAttrs').value;
			var childProdAddAttrs_old = document.getElementById('childProdAddAttrs_old').value;
			for (var i=0;i<ids_new.length;i++)
			{
				var key = ids_new[i];
				var attr_old = getAddAttrs(childProdAddAttrs_old,key);
				var attr_new = getAddAttrs(childProdAddAttrs_new,key);
				if (attr_old != attr_new)
				{
					bz = 1;
					break;
				}
			}
			
			if (bz == 0)
			{
				// У�鸽������
				alertRedErrorMsg("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����ޱ���������ύ��");
				return;
			}
		}
	}
    
	
	// ҳ��У��
	// ���ò�Ʒ����  
	// pkg001~subproductcode003~::addarrtid_003=13645319981=#addarrtid_004=13645319982=#addarrtid_005==#addarrtid_006=0531=]
	// pkg001~subproductcode003~::addarrtid_003=13645319981=#addarrtid_004=13645319982=#addarrtid_005==#addarrtid_006=0531=]
	var checkBz = '';
	var childProdIds = document.getElementById('childProdIds').value;
	var opertype = document.getElementById('opertype').value;
	var url = "${sessionScope.basePath}quickPublish/checkBySubProd.action";
	var params = "childProdIds=" + childProdIds + "&opertype=" + opertype;
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
					var resXml = this.req.responseText;
						if ('' != resXml)
						{
							checkBz = resXml;
						}
					}, null, "POST", params, "application/x-www-form-urlencoded"); 
					
	if (checkBz != '')
	{
		alertRedErrorMsg(checkBz);
		return;
	}
	
	if (submitFlag == 0) 
	{
		submitFlag = 1;// �ύ���
		openRecWaitLoading('recWaitLoading');
		document.actform.target = "_self";
		
		// �������� PCOpRec:��ͨ PCOpMod:�޸�
		var opertype = document.getElementById('opertype').value;
		
		if (opertype == 'PCOpRec')
		{
			if ('<%=effectRec %>' == '' || '<%=effectRec %>'.length == 1 && '<%=effectRec %>' != '5')
			{
				document.getElementById('effectType').value = '<%=effectRec %>';
				document.actform.action = "${sessionScope.basePath}quickPublish/prodPackegRec.action";
			}
			else
			{
				document.actform.action = "${sessionScope.basePath}quickPublish/goEffectType.action";
			}
		}
		
		if (opertype == 'PCOpMod')
		{
			if ('<%=effectMod %>' == '' || '<%=effectMod %>'.length == 1 && '<%=effectRec %>' != '5')
			{
				document.getElementById('effectType').value = '<%=effectMod %>';
				document.actform.action = "${sessionScope.basePath}quickPublish/prodPackegRec.action";
			}
			else
			{
				document.actform.action = "${sessionScope.basePath}quickPublish/goEffectType.action";
			}
		}
		
		document.actform.submit();	
	}
}

// id=childProdName1#id=childProdName2
function getValue(str,key)
{
	var objs = str.split('#');
	for(var i=0;objs.length;i++)
	{
		if (objs[i] == '')
		{
			return '';
		}
		var objects = objs[i].split('=');
		if (key == objects[0])
		{
			return objects[1];
		}
	}
	return '';
}

// str(pkgid~prodid~privid::attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2]pkgid~prodid~privid::attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2)
// key(pkgid~prodid~privid)
// ���ݲ�Ʒ�����ѯ��Ʒ�µ����и�������
function getAddAttrs(str,key)
{
	if (str == '')
	{
		return '';
	}
	if (str.indexOf(key+'::') == -1)
	{
		return '';
	} 
	str = str.substr(0,str.length-1);
	var objs = str.split(']');
	for(var i=0;objs.length;i++)
	{
		var objects = objs[i].split('::');
		if (key == objects[0])
		{
			return objects[1];
		}
	}
	
	return '';
}

// ��ʼ��ҳ����Ϣ
function initLoad()
{
	<%
	// ��ճ�ʼ����Ϣ
	String initBz = (String)request.getAttribute("initBz");
	if ("1".equals(initBz))
	{
	%>
		// ����Ӳ�Ʒ�б���Ϣ  pkgid~prodid~privid^pkgid~prodid~privid
		document.getElementById('childProdIds').value = '';
		
		// ��ո��������б���Ϣ 
		// pkgid~prodid~privid::attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2;
		// pkgid~prodid~privid::attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2;
		document.getElementById('childProdAddAttrs').value = '';
		
		// ��ʼ��cookie���Ӳ�Ʒ����
		getSubProdAttrsByAjax();
	<%
	}
	%>
	
	// ��ͨʱʹ��_����Żݱ���
	if ('PCOpRec' == document.getElementById('opertype').value && '1' == '<%=initBz %>')
	{
		// �����ѡ
		// ��������ѡ���
		var objs = document.getElementsByName('ids');
		for (var i=0;i<objs.length;i++)
		{
			if (document.getElementById(objs[i].id+'_td').innerHTML == '��ѡ')
			{
				if (objs[i].value.indexOf('�Ż�') != -1)
				{
					objs[i].value = '���̡��Ż�';
					
					// ѡ����һ���Ӳ�Ʒ
					var ids = objs[i].id.split('~');
					var pkgid = ids.length >=2 ? ids[0] : '';
					var prodid = ids.length >=2 ? ids[1] : '';
					var privid = ids.length >=3 ? ids[2] : '';
					document.getElementById(pkgid+'~'+prodid+'~').value = '���̡���Ʒ';
				}
				else if (objs[i].value.indexOf('��Ʒ') != -1)
				{
					objs[i].value = '���̡���Ʒ';
				}
			}
		}
	}
	
	if (document.getElementById('childProdIds').value == '')
	{
		return;
	}
    
    // ȡ������ѡ��
	var objs = document.getElementsByName("ids");
	for(var i=0;i<objs.length;i++)
	{
		if (objs[i].value == '��  ����Ʒ' || objs[i].value == '���̡���Ʒ')
		{
			objs[i].value = '��  ����Ʒ';
		}
		else if (objs[i].value == '��  ���Ż�' || objs[i].value == '���̡��Ż�')
		{
			objs[i].value = '��  ���Ż�';
		}
	}
	
	// ȡcookie����ѡ�еĲ�ѡ��
	var subprods = document.getElementById('childProdIds').value.split('^');
	for(var i=0;i<subprods.length;i++)
	{
		if (document.getElementById(subprods[i]).value == '��  ����Ʒ')
		{
			document.getElementById(subprods[i]).value = '���̡���Ʒ';
		}
		else if (document.getElementById(subprods[i]).value == '��  ���Ż�')
		{
			document.getElementById(subprods[i]).value = '���̡��Ż�';
		}
	}
	
}

// ���ò�Ʒ����  
// pkg001~subproductcode003~::addarrtid_003=13645319981=#addarrtid_004=13645319982=#addarrtid_005==#addarrtid_006=0531=]
// pkg001~subproductcode003~::addarrtid_003=13645319981=#addarrtid_004=13645319982=#addarrtid_005==#addarrtid_006=0531=]
function getSubProdAttrsByAjax()
{		
	var opertype = document.getElementById('opertype').value;
	var url = "${sessionScope.basePath}quickPublish/getSubProdAttrsByAjax.action";
	var params = "opertype=" + opertype;
	var loader = new net.ContentLoaderSynchro(url, callBack, null, "POST", params, "application/x-www-form-urlencoded");
}   

// �ص��ķ���
function callBack()
{
	// pkg001~subproductcode003~::addarrtid_003=13645319981=#addarrtid_004=13645319982=#addarrtid_005==#addarrtid_006=0531=]
    var returnStr = this.req.responseText;
    if (returnStr != '')
    {
    	var obj = returnStr.split('@@'); 
    	
    	// �趨Ĭ��ֵ
    	document.getElementById('childProdAddAttrs').value = obj[0];
    	document.getElementById('childProdIds').value = obj[1];
    	
    	// �ȶ��Ƿ񱻸��Ĺ�
    	document.getElementById('childProdAddAttrs_old').value = obj[0];
    	document.getElementById('childProdIds_old').value = obj[1];
    }
}

</script>
</head>
<BODY scroll="no" onload="window.focus();initLoad();">
	<form name="actform" method="post">
		<!-- 1:��� ���������� -->
		<input type="hidden" id="initBz" name="initBz" value="<s:property value='initBz'/>"/>
		<input type="hidden" id="effectType" name="effectType" value=""/>
		<!-- ѡ�е��Ӳ�Ʒ -->
		<input type="hidden" id="childProdIds" name="childProdIds" value="<s:property value='childProdIds'/>"/>
		<!-- ѡ�е��Ӳ�Ʒ���� -->
		<input type="hidden" id="childProdAddAttrs" name="childProdAddAttrs" value="<s:property value='childProdAddAttrs'/>"/>
		<!-- �������� PCOpRec:��ͨ PCOpMod:�޸� PCOpDel:�ر� -->
		<input type="hidden" id="opertype" name="opertype" value="<s:property value='opertype'/>"/>
		
		<!-- �Ӳ�ƷID -->
		<input type="hidden" id="subProdId" name="subProdId" value=""/>
		
		<!-- ԭ�������� -->
		<input type="hidden" id="childProdIds_old" name="childProdIds_old" value="<s:property value='childProdIds_old'/>"/>
		<!-- ԭ�Ӳ�Ʒ -->
		<input type="hidden" id="childProdAddAttrs_old" name="childProdAddAttrs_old" value="<s:property value='childProdAddAttrs_old'/>"/>
		<input type="hidden" id="quickPubFlag" name="quickPubFlag" value="1"/>
		<input type="hidden" id="searchType" name="searchType" value="<s:property value='searchType'/>" />
		<input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
		<input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
		
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
		        <!--������-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- �б����� -->
								<p class="tit_info" align="left"><span class="bg"></span>�Ӳ�Ʒ�б�</p>
								<p class="ptop180 tc p747w411h" id="inn" >
								<br>
								<table class="tb_blue" width="100%">
									
									<%
									for (SubsChildProdPO po : subsChildProdPOList)
									{
									%>
		            					<tr>
			                                <td nowrap="nowrap" style="text-align: left;width: 15%">
			                                	<%
			                                	
			                                	// id = ��Ʒ������~��Ʒ����~�Żݱ���
			                                	String id = po.getPkgid() + "~" + po.getProdid() + "~" + po.getPrivid();
			                                	String key = "";
			                                	
			                                	// �Ż�
			                                	if (!"".equals(po.getPrivid()))
			                                	{
			                                		out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			                                		key = po.getPrivid() + "_Privilege";
			                                	}
			                                	else
			                                	{
			                                		key = po.getProdid() + "_Product";
			                                	}
			                                	
			                                	// ѡ�����ͣ���ѡ�ͱ�ѡ�� 0:��ѡ 1:��ѡ
			                                	if ("1".equals(po.getIsmandatory()))
			                                	{
			                                	%>
			                                		<input type="button" name="ids" id="<%=id %>" class="bt2_liebiao white" value="<%="".equals(po.getPrivid())?"���̡���Ʒ":"���̡��Ż�" %>" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';selectSubProd('<%=id %>');"/>
			                                	<%
			                                	}
			                                	else if ("0".equals(po.getIsmandatory()))
			                                	{
			                                		if (speakProdPOList == null || speakProdPOList.size() == 0)
			                                		{
			                                	%>
			                                			<input type="button" name="ids" id="<%=id %>" class="bt2_liebiao white" value="<%="".equals(po.getPrivid())?"��  ����Ʒ":"��  ���Ż�" %>" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';selectSubProd('<%=id %>');"/>
			                                	<%	
			                                		}
			                                		else
			                                		{
			                                			int bz = 0;
			                                			System.out.println("��Ʒ������ ��Ʒ���� �Żݱ��� = " + po.getPkgid() +" "+po.getProdid()+" "+po.getPrivid());
					                                	for (SpeakProdPO speakProdPO : speakProdPOList)
														{
															// ��Ʒ������ ��Ʒ���� �Żݱ���
															if (po.getPkgid().equals(speakProdPO.getPkgid())
																&& po.getProdid().equals(speakProdPO.getProdid())
																&& po.getPrivid().equals(speakProdPO.getPrivid())
																)
															{
												%>
																<input type="button" name="ids" id="<%=id %>" class="bt2_liebiao white" value="<%="".equals(po.getPrivid())?"���̡���Ʒ":"���̡��Ż�" %>" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_1 white';selectSubProd('<%=id %>');"/>
												<%		
																	bz = 1;
																	break;
															}
														}
														if (bz == 0)
														{
												%>
												        		<input type="button" name="ids" id="<%=id %>" class="bt2_liebiao white" value="<%="".equals(po.getPrivid())?"��  ����Ʒ":"��  ���Ż�" %>" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_1 white';selectSubProd('<%=id %>');"/>
												<%
														}
													}
												}
			                                	%>
			                                	 
			                                	
			                                </td>
			                                <td style="text-align: left;"><b><%=po.getProdname() %></b></td>
			                                <td style="text-align: center;display: none;" id='<%=id %>_td'><%="0".equals(po.getIsmandatory())?"��ѡ":"��ѡ" %></td>
			                                <%
			                                if (tipsMap != null && tipsMap.size() > 0)
			                                {
			                                	if (tipsMap.containsKey(key))
			                                	{
			                               	%>
			                               	<td style="text-align: left;">                    
			                                	<%=tipsMap.get(key) %>		
			                               	</td>
			                               	<%
			                                	}
			                                	else
			                                	{
			                                %>
			                                	<td style="text-align: left;"> 
			                                		&nbsp;
			                                	</td>
			                                <%
			                                	}
			                                }
			                                %>		                                
			                                <td style="width: 15%">
			                                <%
			                                if ("1".equals(po.getHasattr()))
			                                {
											%>
												<input type="button" class="bt2_liebiao white" value="��������" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';subProdAddAttr('<%=id %>');"/>
											<%
											}
											
											%>
											
											</td>
		                                </tr> 
	            					<%
	            					}
	            					%>
	                                <tr>
					                    <td colspan="4" style="text-align: center;">
			        						<input type="button" class="bt2_liebiao white" value="����" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';doCommit();"/>
			        						<input type="button" class="bt2_liebiao white" value="����" onmousedown="this.className='bt2on_1 white'" onmouseup="this.className='bt2_liebiao white';goback();"/>
				                    	</td>
				                    </tr>
                                </table>
				                <br/>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
								<%
								}
								else
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								}
								%>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				      <p>��ѡ����� <i><%="" %></i><i>ҵ��</i></p>
				      <div class="blank10"></div>
				      <p class="mt30">ȷ�϶�������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a title="ȷ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub('<s:property value="prodDetailPO.prodID"/>','<s:property value="prodDetailPO.region"/>','<s:property value="prodDetailPO.brand"/>');">ȷ��</a></span>
	                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span>
                  </div>
                </div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
		          
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
<script type="text/javascript">
	// ���ز�Ʒ�б�
function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		if ('2' == '<s:property value="searchType" />' || '3' == '<s:property value="searchType" />')
		{
			document.getElementById("curMenuId").value = "rec";
				
			document.forms[0].target = "_self";
			document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
			document.forms[0].submit();
		}
		else
		{
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
			document.actform.submit();
		}
	}	
}
</script>
</html>
