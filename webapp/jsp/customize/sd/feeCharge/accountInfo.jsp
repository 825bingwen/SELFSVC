<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
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
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
		//82��220 ����
		
		document.onkeydown = pwdKeyboardDown;
		
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
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}			
		}
			
		function selectPayType() 
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/selectFeeChargeType.action";
				document.actform.submit();
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />�˳���ֵ���ѹ���");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		
		//�ύ���ѷ�ʽ��flagΪ0ʱ����ʾ��ؽɷ���Ϣ��Ϊ1ʱ����Ҫ��ʾ��ֱ���ύ
		function doSub(menuid, url,flag)
		{
			
			//��Ϊʡ�ڿ������ѣ�������ʾ�� regionFlag 0:��������
			<%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>
			if ('<s:property value="regionFlag"/>' == "0" && flag == "0")
			{
				document.getElementById("typeUrl").value = url;
				openRegionConfirm("chargeConfirm");
				return;
			}
			
			//�û����������ֵ����ת֮ǰѡ��ĳ�ֵ��ʽ
			if (flag == "1")
			{
				url = document.getElementById("typeUrl").value;
			}
			<%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />ѡ�񽻷ѷ�ʽ��" + menuid);
				
				<%--modify begin g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
				if (url == "charge/cardCharge.action")
				{
					// ���������ѣ��жϴ�ʱ������Ƿ����
					var url1 = "${sessionScope.basePath}charge/checkTime.action";
					
					var loader = new net.ContentLoader(url1, netload = function () {
						var resXml1 = this.req.responseText;
						
						if (resXml1 == "1" || resXml1 == 1)
						{
							document.actform.target = "_self";
							document.actform.action = "${sessionScope.basePath}" + url;
							document.actform.submit();
						}
						else
						{
							submitFlag = 0;
							
							alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
							
							return;
						}
					}, null, "POST", null, null);
				}
				else
				{
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				<%--modify end g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
			}			
		}
		
	   //����������ʾ��	
	   <%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>	
	   openRegionConfirm = function(id)
	   {
	   		wiWindow = new OpenWindow(id,708,392);
	   }
	   <%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>
	   
		<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_ɽ��_�����ն˽ɷѺ�ġ��쳣����ʾ��Ϣ�õ��ɷ�ǰ�ͽ�����ʾ--%>	
		// ҳ�����
		function doLoad()
		{
			//modify by sWX219697 2015-8-6 19:46:20 �޸�ҳ�汨ȱ�ٶ����js����
			try
			{
				// �ֽ�ʶ�������ϣ�����ʾ�ֽ�ɷ�
				if ("<s:property value="#parameters.checkCashFlag"/>" == "casherror")
				{
					if(document.getElementById("cashcharge"))
					{
						document.getElementById("cashcharge").style.display = "none";
					}
				}
				
				// ������������������̹��ϣ�����ʾ�������ɷ�
				if ("<s:property value="#parameters.checkCardFlag"/>" == "carderror")
				{
					if(document.getElementById("cardcharge"))
					{
						document.getElementById("cardcharge").style.display = "none";
					}
					
				}
			}
			catch(e){}

		}
		<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_ɽ��_�����ն˽ɷѺ�ġ��쳣����ʾ��Ϣ�õ��ɷ�ǰ�ͽ�����ʾ--%>	
		</script>
	</head>
	<body scroll="no" onload="doLoad();">
		<form name="actform" method="post">
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />"/>
			<input type="hidden" id="acceptType" name="acceptType" value="<s:property value='acceptType' />"/>
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />"/>
			<input type="hidden" id="custName" name="custName" value="<s:property value='custName' />"/>
			<!-- ��תurl -->
			<%-- add begin sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>
			<input type="hidden" id="typeUrl" name="typeUrl" value=""/>
			<%-- add end sWX219697 2014-7-16 OR_huawei_201406_1125_ɽ��_֧�ſ����ɷ�--%>	
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��ֵ��������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)" class="on">2. ѡ��֧����ʽ</a>
    					<p class="recharge_tips">�鿴���Ļ�����Ϣ�����ύ��ֵ���ѽ�����֧����</p>
    					<a href="javascript:void(0)">3. ѡ����</a> 
    					<a href="javascript:void(0)">4. ֧��</a> 
    					<a href="javascript:void(0)">5. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="servnumber" /></span></p>
    						<s:if test="yingjiaoFee == null">
    							<p class="fs22 fwb pl40 lh30">������<span class="yellow fs22"><s:property value="balance" /></span> Ԫ</p>
    						</s:if>
    						<s:else>    						
    							<p class="fs22 fwb pl40 lh30">Ӧ�����ѣ�<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> Ԫ</p>
    						</s:else>
    						<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank10"></div>
      						<p class="tit_arrow fs22"><span class="bg"></span>ѡ��֧����ʽ��</p>
							<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
          						<%-- add begin zKF69263 2015-06-03 OR_SD_201504_1108_ɽ��_�����ն˽ɷѺ�ġ��쳣����ʾ��Ϣ�õ��ɷ�ǰ�ͽ�����ʾ--%>	
          						<s:iterator value="usableTypes" id="type" status="st">
          							<s:if test="#st.index == 0">          								
          								<li class="mr70">          									
          							</s:if>
          							<s:else>
          								<li>          									
          							</s:else>
          							<s:if test="!canPayWithCash && #type.menuid == 'cashcharge'">
          								<a href="javascript:void(0);" id="<s:property value="%{#type.menuid}"/>"><img src='${sessionScope.basePath}<s:property value="#type.imgpath2" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:if>
          							<s:else>
          								<a href="javascript:void(0);" id="<s:property value="%{#type.menuid}"/>" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />','0'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
          							</s:else>          									
          							</li>
          						</s:iterator>
          						<%-- add end zKF69263 2015-06-03 OR_SD_201504_1108_ɽ��_�����ն˽ɷѺ�ġ��쳣����ʾ��Ϣ�õ��ɷ�ǰ�ͽ�����ʾ--%>	
        					</ul>								
    					</div>
					</div>
				</div>
			</div>
		    <%-- ����������ʾ�� --%>
            <%-- add begin sWX219697 2014-7-16 11:27:00 OR_huawei_201406_1125_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_֧�ſ����ɷ�--%>
             <div class="popup_confirm" id="chargeConfirm">
             	<div class="bg"></div>
             	<div class="tips_title">��ʾ��</div>
             	<div class="tips_body">
				<p>���ֻ����벻�����ڱ�����
				<s:if test="regionName != null && regionName != ''">
                	��������${regionName}������
                </s:if>
				</p>
				<div class="blank10"></div>
				<p class="mt30">��ȷ���Ƿ�������ѣ�</p>
		        </div>
             	<div class="btn_box tc mt20">
             		<span class=" mr10 inline_block ">
             			<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="doSub('<s:property value="#type.menuid" />','','1'); return false;">����</a>
             		</span>
             		<span class=" inline_block ">
             			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="goback('<s:property value="curMenuId" />'); return false;">����</a>
             		</span>
             	</div>
             </div>
			<%-- add end sWX219697 2014-7-16 11:27:07 OR_huawei_201406_1125_ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_֧�ſ����ɷ�--%>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
