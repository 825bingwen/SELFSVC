<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>����ԤԼ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
<script	type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
</head>
<BODY scroll="no" onload="window.focus();">
	<form name="actform" method="post">
		<input type="hidden" name="currArea" id="currArea" value="" />
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div style="float: left;">
				<a href="#" class="bt4 fr mr92" onmousedown="this.className='bt4on fr mr92'" onmouseup="this.className='bt4 fr mr92';topGo('recBroadBandAppoint', 'broadBandAppoint/qryAppointMsg.action'); return false;" style="display:inline">��������</a>
				</div>
				<br/>
				<!--������-->
				<div class="box842W fl relative ml90">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">					
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- �б����� -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
									<table class="tb_blue" width="100%">
			                       		  <tr>
											<th class="list_title" align="center" colspan="20">��ǰ�ն˿�ѡ���ַ�б�(�ɲ�ѡ)</th>
				                          </tr>
						                  <tr>
						                    <th scope="col">��ַ</th>
						                    <th scope="col">ѡ��</th>
						                  </tr>
			                    
				                    	<!-- �б��� -->
			                        	 <s:iterator value="areaList" id="areaStr" status="st">
						                  	<tr>
							                    <td width="70%">${areaStr.dictname}</td>
							                    <td width="30%">
							                    <input type="button" name="ids" id="${areaStr.dictid}" class="bt2_liebiao white" value="��  ��" onmousedown="this.className='bt2on_1 white'" 
													onmouseup="this.className='bt2_liebiao white';selectProd('${areaStr.dictid}','${areaStr.dictname}');"/>
												<br/>
			                  				</tr>
		                  			  	</s:iterator>
		                       		</table>
								</p>			
								<!-- �б����� -->
							</div>							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
                
                <div style="float: left;margin-top: 10px">
				<a href="#" class="bt2 fr mr30" style="margin-right: 90px" onmousedown="this.className='bt2on fr mr30'" onmouseup="this.className='bt2 fr mr30';doSub('<%=curMenuId %>');return false;" style="display:inline">��һ��</a>
				</div>
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
<script>
var submitFlag = 0;

//��ʼ���Ժſ�ȫ�����
init();

//���õ�ǰ��ַ
function doSet(areaName)
{
	var operTypeName = document.getElementById("currSelectArea");  
	operTypeName.innerHTML = areaName;
	
	var currArea = document.getElementById("currArea");  
	currArea.value = areaName;
}

//�Ժſ�ȫ�����
function init(){
	// ��������ѡ���
	var objs = document.getElementsByName('ids');
	
	// ���
	for(var i=0;i<objs.length;i++)
	{
		objs[i].value = '��  ��';
	}	
}

// ���ѡ���ȡ��
function selectProd(id,areaName)
{
	var currArea = document.getElementById("currArea"); 
	
	// ѡ��
	if (document.getElementById(id).value == '��  ��')
	{
		init();
		document.getElementById(id).value = '���̡�';
		currArea.value = areaName;
		return;
	}
	
	if (document.getElementById(id).value == '���̡�')
	{
		document.getElementById(id).value = '��  ��'
		currArea.value = "";
		return;
	}
}


//�����һ��
function doSub(menuid)
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//�ύ��� 
		document.getElementById("curMenuId").value = menuid;
		document.actform.action="${sessionScope.basePath}broadBandAppoint/customerInfo.action";
		document.actform.submit();
	}
}

//����
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}
</script>