<%--
	create by sWX219697 2014-8-5 17:04:11 OR_SD_201408_20_ɽ��_ISSSƽ̨�Խ������ն˵�����
	Ӫ��ҵ����ʾҳ�棬�����û���ֵ���ҵ��չʾ��ʵ��js��ҳ����
	modify by sWX219697 2014-9-17 11:25:36 OR_SD_201409_556_�����ն�Ӫ�������Ż�
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/json2.js"></script>
	<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
	<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<% 
	String dialogMsg = CommonUtil.getParamValue(Constants.SH_ISSS_DIALOG_MSG);
	if (dialogMsg == null || "".equals(dialogMsg))
	{
		dialogMsg = "���ã������Ƽ�ҵ���Ƿ���Ҫ����";
	}
	
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
	// ���ݲ˵�id��ȡ�ػ�ҵ����˵����ƣ�������Ϊ�գ�������˲˵�û������
	String privServPack = CommonUtil.getMenuName("privServPack");
	
	// Ӫ���Ƽ����ʱʱ�䣬��ʱ�Զ��ر�(Ĭ��10000����λ����)
	String closeService = CommonUtil.getParamValue(Constants.SH_CLOSESERVICE_OUTTIME, "10000");
	// add begin jWX216858 2015-5-11 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
%>

<style type="text/css">
.openwin_big1 .bg2 {
	width: 889px;
	height: 528px;
	left: 60px;
	top: 0px;
	background: url(${sessionScope.basePath}images/bg_openwin_big2.png);
	_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true,sizingMethod=scale,src="${sessionScope.basePath }images/bg_openwin_big2.png");
	_background: none
}
</style>

<!-- 
<div class="openwin_big1 " id="webServiceDialog"
	style="HEIGHT: 528px; top: 160px; padding-left: 7px; position:relative;">
	<div class="bg2" style="HEIGHT: 528px; left: 10px; z-index:0;"></div>
	<div style="position:absolute; z-index:5; left:0px; top:25px;"> 
		<div class="blank30 tc fs24 pt15 pos_rel" style="width:819px; text-align:center;">
			<%=dialogMsg%>
		</div>
	
		<div class="blank30">
			<p class="fs18 lh2 yellow pl45">
			</p>
		</div>
	
		<div class="blank60 height260 fs16 1h2 pl45"
			id="" style="overflow: hidden;">
			
		</div>
		<div class="blank30">
			<p class="fs18 lh2 yellow pl45">
	
			</p>
		</div>
		<div class="btn_box tc">
			<span class=" inline_block "><a title="" class="btn_bg_146"
				href="javascript:void(0);" onmousedown="this.className='key_down';"
				onmouseup="this.className='btn_bg_146';"
				onclick="wiWindowWeb.close();">�ر�</a> </span>
		</div>
	</div>
	<div class="box70W" style=" position:absolute; z-index:6; right:30px; top:30px;">
		<input type="button" class="btnUp" onclick="myScrollDialog.moveUp(30)"  />
		<div class="div75w350h">
			<div class="blank10px"></div>
			<div class="box66W tc f16 div66w36h" id="moveDom" style="top:52px; left:2px; height:30px" >0%</div>
		</div>
		<input type="button" class="btnDown" onclick="myScrollDialog.moveDown(30)"/>
	</div>
	<div style="clear:both;"></div>
</div>
 -->
<div class="openwin_790" id="openWin1" style="">
    <div class="bg"></div>                    
    <div class="blank30"></div>
    <p class="fs26 ml80">�𾴵��û�������Ϊ���Ƽ�</p>
    
    <%--modify begin qWX279398 2016-01-06 OR_SD_201512_312_���������ն˵���Ӫ���Ƽ����� --%>
    <%-- <input type="button" class="bt4 suggest" onmousedown="this.className='bt4on suggest'" onmouseup="this.className='bt4 suggest';" onclick="wiWindowWeb.close();printInvoice('Last','1');" value="��ӡ��Ʊ" />--%>
    <%--modify end qWX279398 2016-01-06 OR_SD_201512_312_���������ն˵���Ӫ���Ƽ����� --%>
    
    <input type="button" class="bt4 close" onmousedown="this.className='bt4on close'" onmouseup="this.className='bt4 close';" onclick="wiWindowWeb.close();" value="�ر�" />
    <div class="blank30"></div>
    <div class="ml25" id="webServiceTable">
	</div>
</div>

<%-- ɽ���ƶ������ն�ҵ��չʾ--%>
<script type="text/javascript">
openwebServiceDialog = function(id)
{
	openWindowFlag = 0;
 	wiWindowWeb = new OpenWindow("openWin1", 790,392);	    
}

//���ص�����
// modify by jWX216858 2015-5-7 OR_SD_201504_452_ɽ��_ISSS�����ն�UCD����
function serviceDialog()
{
	//����
	var postStr ="servnumber=<s:property value='servnumber' />";  
	 
	var receptionUrl = "${sessionScope.basePath}ISSS/qryOfferInfoList.action";
	
	// ��ȡ�Ƽ��б�
	var loader1List = new net.ContentLoader(receptionUrl, netload = function () 
	{
		var text = this.req.responseText;
		
		try
		{
			// js����  obj-����
			var obj = JSON.parse(text);
			
			// ƴװչʾ����
			var content = "";	
			
			for(var i=0;i<obj.length;i++)
			{
			    if (i < 3)
			    {
				    content +="<div class='recommended fl'><div class='blank10'></div>";
	                content +="<p class='fs20 yellow p10 lh28'>";   
					var offerInfo = obj[i];
					
					// ҵ��
					var offName = offerInfo.offerName;
					
					// ����
					var offDesc = offerInfo.desc;
					
					// �˵�id
					var offerMenuid = offerInfo.menuId;
					
					// url
					var offerUrl = offerInfo.url;
					
					var offerId = offerInfo.offerId;
					
					var treatmentid = offerInfo.treatment_id;
					var offerCode = offerInfo.offerCode;
					
					if ("1" == offerId)
					{
					    content += offName + "</p><div class='blank10'></div><p class='fs18 p10 recommended_text'>";
                        content += offDesc + "</p><div class='blank10'></div></div>";
					}
					else
					{
						content += offName + "</p><div class='blank10'></div><p class='fs18 p10 recommended_text'>";
						content += offDesc + "</p><div class='blank10'></div><a class='bt4' onmousedown='this.className=\"bt4on\"' onmouseup='this.className=\"bt4\"'";
						content +="onclick=\"go('"+offerMenuid+"','"+offerUrl+"','"+offerId+"','"+treatmentid+"','"+offerCode+"');\">";
						content += "ȥ����</a></div>";
					}
			    }
			}
			// ���û�п��Ƽ���ҵ���򲻵�����ʾ��
			if(obj.length > 0)
			{
			    // �Ƽ����������ʱ����ʾ����ҵ��
			    if (1 == obj.length || 2 == obj.length)
			    {
			        content += "<div class='recommended fl'><div class='blank10'></div>";
                    content += "<p class='fs20 yellow p10 lh28'>����ҵ��</p>";
                    content += "<div class='blank10'></div>";
                    content += "<img src='${sessionScope.basePath}images/more_bussiness.png' style='top:60px; width:140px;height:140px'/>";
                    content += "<a class='bt4' onmousedown=\"this.className='bt4on'\" onmouseup=\"this.className='bt4'; topGo('rec', 'reception/receptionFunc.action'); return false;\">ȥ����</a></div>";
			    }
			    
			    // ��ʾ�����Żݣ������ػ�ҵ�����
			    if (1 == obj.length && null != '<%=privServPack %>' && "" != '<%=privServPack %>')
			    {
			        content += "<div class='recommended fl'><div class='blank10'></div>";
                    content += "<p class='fs20 yellow p10 lh28'>�����Ż�</p>";
                    content += "<div class='blank10'></div>";
                    content += "<img src='${sessionScope.basePath}images/more_activity.png' style='top:60px;width:140px;height:140px'/>";
                    content += "<a class='bt4' onmousedown=\"this.className='bt4on'\" onmouseup=\"this.className='bt4'; topGo('privServPack', 'privServPack/qryPrivServPack.action'); return false;\">ȥ����</a></div>";
			    }
			    
				document.getElementById("webServiceTable").innerHTML = content;
				openwebServiceDialog("openWin1");
				
				// modify begin qWX279398 OR_SD_201508_958_ɽ��_�����ն�Ӫ���Ƽ��ͷ�Ʊ��ӡչʾ�Ż�
				if (typeof wiWindow!='undefined')
				{
					wiWindow.close();
				}
				// modify end qWX279398 OR_SD_201508_958_ɽ��_�����ն�Ӫ���Ƽ��ͷ�Ʊ��ӡչʾ�Ż�
				
				
				// 10����Զ��ر�Ӫ���Ƽ�������� 
			    setTimeout("wiWindowWeb.close()",'<%=closeService %>');
			}
			// �����ʾ
		    document.getElementById("offerListWaitTip").innerHTML = "";
		}
		catch(ee)
		{
			// �쳣
		}
		
	}, null, "POST", postStr, null);
	
}

//��ת
function go(menuid, url, offerId, treatment_id, offerCode) 
{
	//offeridΪ1ʱ������ťΪ����ʱ������Ҫ��¼session��ֱ�ӵ��÷����ӿ�
	//add begin sWX219697 2015-2-12 08:53:33 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
	if("1" == offerId)
	{
		//�ر��Ƽ���
		wiWindowWeb.close();
		
		//����һ��
		consider(offerId, treatment_id);
		return;
	}
	
	//add by SWX219697 2015-2-13 17:37:30 �޸�bug 86167
	<%
	session.setAttribute("isssGoBackFlag","isssGoBackFlag");
	%>
	//add end SWX219697 2015-2-13 17:37:30 �޸�bug 86167
	
	//add end sWX219697 2015-2-12 08:53:38 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
	
	//����
	var postStr ="offerMenu="+menuid+"&offerId="+offerId+"&treatment_id="+treatment_id
		+"&servnumber=<s:property value='servnumber' />"+"&offerCode="+offerCode;  
	 
	var receptionUrl = "${sessionScope.basePath}ISSS/setISSSSession.action";
	
	//�Ƚ���ص�ҵ����Ϣ��session�����ڶ���������Ȼ����ת����Ӧ�Ĳ˵�
	var loader11 = new net.ContentLoader(receptionUrl, netload = function () 
	{
	    //��ת����Ӧҵ��ҳ��
		document.getElementById("curMenuId").value = menuid;
		
		document.forms[0].action = "${sessionScope.basePath}" + url;
		document.forms[0].submit();	
								
	}, null, "POST", postStr, null);


}

/**
 * ����һ��
 * add by sWX219697 2015-2-12 08:54:28 OR_SD_201502_198_ISSS�����նˡ�������֧���޲�ƷӪ����Ƽ�
 */
function consider(offerId, treatment_id)
{
	//����
	var postStr ="&offerId="+offerId+"&treatment_id="+treatment_id
		+"&servnumber=<s:property value='servnumber' />";  
	 
	var considerUrl = "${sessionScope.basePath}ISSS/consider.action";
	
	//�Ƚ���ص�ҵ����Ϣ��session�����ڶ���������Ȼ����ת����Ӧ�Ĳ˵�
	var loader = new net.ContentLoader(considerUrl, netload = function () 
	{
	}, null, "POST", postStr, null);
}
</script>