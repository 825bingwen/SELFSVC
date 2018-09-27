<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/newAdd.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js?ver=${jsVersion}"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
<style>
	.yellow{color:#ffd543;}
	.lh1_7{line-height:1.7em;}
	.clear{clear:both;}
</style>

</head>
<body scroll="no">
	<form name="actform" method="post">  
	
		<s:hidden name="telProdPo.prodId" />
	    
	    <%--��ѯ��ʽ��VOICECALL������ͨ���ײͣ�GPRSWLAN�����������ײ�--%>
		<s:hidden name="telProdPo.qryType" id="qryType"/>
		
		<%--�Ҳ�div����--%>
		<input type="hidden" name="rightDivHtml" id="rightDivHtml" value="<s:property value='rightDivHtml' escape="false" />">
		
		<%--����������--%>
		<input type="hidden" name="openDiv" id="openDiv" value="">
		
		<%--�ܷ���--%>
		<input type="hidden" name="totalHidden" id="totalHidden" value="<s:property value='totalHidden'/>">	
        
        <%--����ͨ���ײ�id--%>
        <s:hidden  name="telProdPo.voicePrivId" id="voicePrivId"/>
        
        <%--���������ײ�id--%>
        <s:hidden  name="telProdPo.netPrivId" id="netPrivId"/>
        
        <%--����ͨ���ײͷ���--%>
        <s:hidden id="voicePrice" name="telProdPo.voicePrice"/>	
        
        <%--���������ײͷ���--%>
        <s:hidden id="netPrice" name="telProdPo.netPrice"/>	
		
		<%@ include file="/titleinc.jsp"%>			
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="selectPackage_mainContent">
		
				<!-- ������� ��ʼ -->
				<div class="selectPackage_leftContent">
		
					<div class="titleNav">
						<s:if test="telProdPo.qryType == 'VOICECALL' || telProdPo.qryType == null">
							<a href="javascript:void(0)" class="btn1_selected"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn1" onclick="qryTelProdList('VOICECALL');return false;"></a>
						</s:else>
						<div class="icon_plus" style="margin-left:75px;"></div>
						
						<s:if test="telProdPo.qryType == 'GPRSWLAN'">
							<a href="javascript:void(0)" class="btn2_selected" style="margin-left:75px;"></a>
						</s:if>
						<s:else>
							<a href="javascript:void(0)" class="btn2" style="margin-left:75px;"  onclick="qryTelProdList('GPRSWLAN');return false;"></a>
						</s:else>
						
						<div class="icon_equal" style="margin-left:75px;"></div>
					</div>
					<div class="clear"></div>
					
					<!-- btn1��Ӧ������ ��ʼ -->
					<div class="packageListBody" id="listDiv">
						<s:if test="voiceProdList.size > 0">
							<div class="packageList_title">
								<s:iterator value="voiceProdList" id="prod" status="status">
									<s:if test = "#status.index == 0">
										<s:property value="#prod.voiceDesc" escape="false"/>
									</s:if>
								</s:iterator>
							</div>
 						</s:if>
						
						<s:if test="voiceProdList.size > 0">
							<div class="packageList_content">
								<div class="topBg"></div>
								<div class="middleBg">
									<s:iterator value="voiceProdList" id="prod" >
	 									<div class="datalist">
											<div class="info">
												<a  href="javascript:void(0)"
													onclick="openDetail(
													'<s:property value="#prod.voicePrivId" escape="false"/>',
													'<s:property value="#prod.voiceName" escape="false"/>',
													'<s:property value="#prod.voiceFeeStan" escape="false"/>',
													'<s:property value="#prod.voicePrice" escape="false"/>',
													'<s:property value="#prod.voiceSuitPer" escape="false"/>',
													'<s:property value="#prod.voiceBusDesc" escape="false"/>',
													'1'
													)">
													<s:property value="#prod.voiceName" escape="false"/>
												</a>
											</div>
											<a href="javascript:void(0)" class="addMyPackage"  
												value="<s:property value="#prod.voicePrivId"/>,<s:property value="#prod.voiceName" escape="false"/>,"
												name="href3" id="<s:property value="#prod.voiceName" escape="false"/>" 
												onclick="addClick('<s:property value="#prod.voicePrivId" escape="false"/>','listDiv',this,'1','<s:property value="#prod.voicePrice"/>'
												)" >
												�����ҵ��ײ�
											</a>
										</div>
		 							</s:iterator>
		 							<div class="clear"></div>
	 							</div>
								<div class="bottomBg"></div>
							</div>
 						</s:if>
						
						<s:if test="netProdList.size > 0">
                            <div class="packageList_title">
                                <s:iterator value="netProdList" id="prod" status="status">
                                    <s:if test = "#status.index == 0">
                                        <s:property value="#prod.netDesc" escape="false"/>
                                    </s:if>
                                </s:iterator>
                            </div>
                        </s:if>
                        
                        <s:if test="netProdList.size > 0">
                            <div class="packageList_content">
                                <div class="topBg"></div>
                                <div class="middleBg">
                                    <s:iterator value="netProdList" id="prod" >
                                        <div class="datalist">
                                            <div class="info">
                                                <a  href="javascript:void(0)"
                                                    onclick="openDetail(
                                                    '<s:property value="#prod.netPrivId" escape="false"/>',
                                                    '<s:property value="#prod.netName" escape="false"/>',
                                                    '<s:property value="#prod.netFeeStan" escape="false"/>',
                                                    '<s:property value="#prod.netPrice" escape="false"/>',
                                                    '<s:property value="#prod.netSuitPer" escape="false"/>',
                                                    '<s:property value="#prod.netBusDesc" escape="false"/>',
                                                    '2'
                                                    )">
                                                    <s:property value="#prod.netName" escape="false"/>
                                                </a>
                                            </div>
                                            <a href="javascript:void(0)" class="addMyPackage"  
                                                value="<s:property value="#prod.netPrivId"/>,<s:property value="#prod.netName" escape="false"/>"
                                                name="href4" id="<s:property value="#prod.netName" escape="false"/>" 
                                                onclick="addClick('<s:property value="#prod.netPrivId" escape="false"/>','listDiv',this,'2','<s:property value="#prod.netPrice"/>'
                                                )" >
                                                            �����ҵ��ײ�
                                            </a>
                                        </div>
                                    </s:iterator>
                                    <div class="clear"></div>
                                </div>
                                <div class="bottomBg"></div>
                            </div>
                        </s:if>
						<div class="clear"></div>
						
						<!-- ��ҳ ��ʼ -->
						<div class="sortPages">
							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="telProdDiy/qryProdListByProdId.action" />
						</div>
						<div class="clear"></div>
						<!-- ��ҳ ���� -->
					</div>
					<!-- btn1��Ӧ������ ���� -->
					<div class="clear"></div>
				</div>
				<!-- ������� ���� -->
		
				<!-- �Ҳ����� ��ʼ -->
				<div class="selectPackage_rightContent" style="position:relative;">
					<div class="rightTitle"></div>
					
					<!-- ������ ��ʼ -->
					<div class="scrollBody">
					
						<div class="packageList" id="inn">
							<div class="packageList_body" id="rightDiv">
								<div class="listTitle">����ͨ������ѡ��</div>
								<div id="yySelect"><div class="listContent" ></div></div>
								
								<div class="listTitle">������������ѡ��</div>
								<div id="gprsSelect"><div class="listContent" ></div></div>
							</div>
						</div>
						
						<div class="scrollBar">
							<input type="button" class="btnTop" onclick="myScroll.moveUp(30)" />
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
							<div class="iconScroll" id="gunDom">0%</div>
						</div>
							
					</div>
					<!-- ������ ���� -->
					<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom","1");</script>
					
					<div class="rightContent_text" style="position:absolute; bottom:-120px; right:10px;">
						�ҵ��ײ��ܼ�<span class="orange_FFD543" id="totleFee"><s:property value="totalHidden"/></span>Ԫ<br />
						����������Ч<br /><br />
						<input type="button" class="btn_deal" onclick="recSubmit();return false;" />
					</div>
				</div>
				<!-- �Ҳ����� ���� -->
				
			</div>
			
			<!--������ BEGIN-->
			<div class="detail_tip" id="openWin1">
				<div class="bg"></div>
				<div class=" blank60"></div>
				<p class="fs28 lh1_7 pl50 pr50" id="winText"
					name="winText" style="font-size:24px;">
				</p>
				<div class="tc">
					<div class=" clear "></div>
					<div class=" blank30 "></div>
					<a  href="javascript:void(0)" class=" bt4"
						onmousedown="this.className='bt4on';"
						onmouseup="this.className='bt4';wiWindow.close();">�ر�����</a>
					<a id="addbutton" href="javascript:void(0)" class=" bt4 ml20"
					    onmousedown="this.className='bt4on ml20';"
						onmouseup="this.className='bt4 ml20';divAdd();wiWindow.close();">�����ҵ��ײ�</a>
				</div>
			</div>
			<!-- ������ END -->
			<script type="text/javascript">
			openWindow = function(id){
				wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������					
			}
			</script>
			
		</div>
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<script type="text/javascript">
var submitFlag = 0;

// ��һҳ
function goback(curmenu) 
{
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

// �ײͲ�ѯ
function qryTelProdList(qryType)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// ҳ���Ҳ�����
		var rightDivHtml = getObj("rightDiv").innerHTML;
		setValue("rightDivHtml",rightDivHtml);
		setValue("qryType",qryType);
		
		document.actform.action="${sessionScope.basePath }telProdDiy/qryProdListByProdId.action";
		document.actform.submit();
	}
}

// ��һҳ
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// ҳ���Ҳ�����
		var rightDivHtml = getObj("rightDiv").innerHTML;
		setValue("rightDivHtml",rightDivHtml);

		document.actform.action=linkURL;
		document.actform.submit();
	}
}

// ��������ҵ��ײ�
function addClick(id,qryType,btn,goods_type,price)
{   
    <%--1������ͨ���ײͣ�2�����������ײ�--%>
    if ('1' == goods_type)
    {
        // ������������ͨ���ײ�id(voicePrivId)��ֵ
        setValue("voicePrivId",id);
    }
    else if ('2' == goods_type)
    {
        // �����������������ײ�id(netPrivId)��ֵ
        setValue("netPrivId",id);
    }
    
    // ��ȡidΪ"listDiv"div�µ�����a��ǩ
	var btns = document.getElementById(qryType).getElementsByTagName('a');
	
	// ��ǰ��������Ϊȡ������ʱ���ĳ�"�����ҵ��ײ�"
	if(btn.innerHTML == "<SPAN class=orange_FFD543>ȡ������</SPAN>")
	{
		btn.innerHTML = "�����ҵ��ײ�";
		
		// �����ȡ�����롱���Ҳ�����
		if('1' == goods_type)
		{
		    document.getElementById("voicePrice").value = "0";
			document.getElementById("yySelect").innerHTML= "<div class='listContent'></div>";
		}
		else if('2' == goods_type)
		{
		    document.getElementById("netPrice").value = "0";
			document.getElementById("gprsSelect").innerHTML= "<div class='listContent'></div>";
		}
	}
	
	// ��ǰ��������Ϊ"�����ҵ��ײ�"ʱ
	else
	{ 
	    // ����a��ǩ������Ϊ"�����ҵ��ײ�"
		for(i=0; i<btns.length; i++)
		{		
			if(btns[i].className== "addMyPackage" )
			{
				if('1' == goods_type)
				{
					btns[i].innerHTML="�����ҵ��ײ�";
				}
				else if('2' == goods_type && btns[i].name == "href4")
				{
					btns[i].innerHTML="�����ҵ��ײ�";
				}
			}
		}
		// ��ǰ����������Ϊ"ȡ������"
		btn.innerHTML="<span class=\"orange_FFD543\">ȡ������</span>";
		
		// ����������ҵ��ײ͡����Ҳ����ʾ��Ӧ����
		<%--1������ͨ���ײͣ�2�����������ײ�--%>
		if('1' == goods_type)
		{
		    document.getElementById("voicePrice").value = price;
			document.getElementById("yySelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
		}
		else if('2' == goods_type)
		{
		    document.getElementById("netPrice").value = price;
			document.getElementById("gprsSelect").innerHTML= "<div class='listContent' id='" + btn.value + "'>" + btn.id + "</div>";
		}
	}
	
	// ��ȡ�Ҳ�div�µ�����div��ǩ
	var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
	var inputStr = "";
	for(var i=0; i<inputs.length; i++)
	{
		if(inputs[i].className == "listContent" && ''!= inputs[i].innerHTML)
		{
			if('' == inputStr)
			{
				inputStr = inputs[i].innerHTML;
			}
			else
			{
				inputStr = inputStr + "," + inputs[i].innerHTML;
			}
		}
	}
    // �����ܷ���
	if('' == inputStr)
	{	
		document.getElementById("totalHidden").value = "0.00" ;
		document.getElementById("totleFee").innerHTML = "0.00" ;
	} 
	else
	{   
	    // ����ͨ���ײͷ���
        var voiceFee = getValue("voicePrice");
        
        if(voiceFee == "")
        {
        	voiceFee = "0";
        }
        
        // ���������ײͷ���
        var netFee = getValue("netPrice");
        
        if(netFee == "")
        {
        	netFee = "0";
        }
        
        // �ܷ���
		var totleFee = parseInt(voiceFee) + parseInt(netFee);
		
		document.getElementById("totleFee").innerHTML = totleFee +".00" ;
		document.getElementById("totalHidden").value = totleFee +".00" ;
	}
}

// �鿴����(�ײ�id���ײ����ƣ��ʷѱ�׼���۸��ײ�����)
function openDetail(id,name,feestandard,price,suitPer,busDesc,goods_type)
{
	document.getElementById('winText').innerHTML = "";
    document.getElementById('winText').innerHTML = 
      '<span class="yellow" style="text-align: center; width: 620px; font-size:28px; font-weight:bold; display: block;">' + name +'</span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;" >�ʺ���Ⱥ</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + suitPer + '</span><span class="clear"></span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">ҵ��˵��</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + busDesc + '</span><span class="clear"></span>'
    + '<span class="yellow" style="width:130px; display:block; font-weight:bold; float:left;">�ʷ�˵��</span>'
    + '<span style="float:left; display:block; width:420px; text-align:left;">' + feestandard + '</span><span class="clear"></span>';
    
    document.getElementById('openDiv').value = 'listDiv' + ","+ name + "," + price + "," + goods_type + ',' +id;
    
    document.getElementById("addbutton").innerHTML="�����ҵ��ײ�";
    
    var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
    
    for(var i=0; i<inputs.length; i++)
    {
        if(inputs[i].className == "listContent")
        {
            var good = inputs[i].id.split(',')[0];
            if(good == id)
            {
                document.getElementById("addbutton").innerHTML = "ȡ������";
                break;
            }
        }
    }	
	openWindow('openWin1');
}

// ����div�������ҵ��ײ�
function divAdd()
{
	var addArray = document.getElementById('openDiv').value.split(",");
	var objid = addArray[1];
	addClick(addArray[4],addArray[0],document.getElementById(objid),addArray[3],addArray[2]);
	wiWindow.close();
}

// һ������
function recSubmit()
{	
	var yySelect = document.getElementById("yySelect").getElementsByTagName('div');
	var gprsSelect = document.getElementById("gprsSelect").getElementsByTagName('div');
	if(''== yySelect[0].id && ''== gprsSelect[0].id)
	{
		alertRedErrorMsg("�𾴵��û�������ͨ���ײͺ����������ײͶ��Ǳ�ѡ�ģ���ѡ��");
		return;
	}
	
	if(''== yySelect[0].id)
	{
		alertRedErrorMsg("�𾴵��û�������ͨ���ײͺ����������ײͶ��Ǳ�ѡ�ģ���ѡ������ͨ���ײͣ�");
		return;
	}
	
	if(''== gprsSelect[0].id)
	{
		alertRedErrorMsg("�𾴵��û�������ͨ���ײͺ����������ײͶ��Ǳ�ѡ�ģ���ѡ�����������ײͣ�");
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading('recWaitLoading');
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }telProdDiy/recSubmit.action";
		document.actform.submit();
	}
	
}


//�����ҵ��ײ�
var leftHtml = document.getElementById('rightDivHtml').value;
if('' != leftHtml)
{
	document.getElementById('rightDiv').innerHTML = document.getElementById('rightDivHtml').value;
	
	var inputs = document.getElementById("rightDiv").getElementsByTagName('div');
	var inputStr = "";
	for(var i=0; i<inputs.length; i++)
	{
		if(inputs[i].className == "listContent")
		{
			inputStr = inputStr + "#" + inputs[i].id;
		}
	}
	
	var inputArray = inputStr.split('#');
	var btns = document.getElementById('listDiv').getElementsByTagName('a');
	
	for(var i=0;i< inputArray.length;i++)
	{  			
		for(var j=0;j<btns.length;j++ )
		{
			if(btns[j].className== "addMyPackage" && btns[j].value == inputArray[i])
			{	
				btns[j].innerHTML = "<span class=\"orange_FFD543\">ȡ������</span>";
				break;
			}
		}
	}
}
</script>
</html>
