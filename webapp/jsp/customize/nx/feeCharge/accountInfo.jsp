<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// ������棬��ֹҳ����˰�ȫ���� 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// add begin cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443
int nValueForPopWindow = 0;

String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
if (valueForPopWindow != null && !"".equals(valueForPopWindow))
{
	nValueForPopWindow = Integer.parseInt(valueForPopWindow);
}

String limitTip = (String) PublicCache.getInstance().getCachedData("SH_CASHCHARGE_LIMITTIP");
if (null == limitTip)
{
	limitTip = "";
}


// add end cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443
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
		
		// modify begin cKF76106 2012/09/25 OR_NX_201209_258
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}
			// add begin cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443
			<s:iterator value="usableTypes" id="type" status="st">	
				if (key == <s:property value="#st.index + 49" />)
				{
					doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />');
				}			
			</s:iterator>
			
			// �˳���82
			if(key == 82)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			}			
		}
		// modify end cKF76106 2012/09/25 OR_NX_201209_258
		
		function goback(menuid)
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
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />�˳���ֵ���ѹ���");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
				document.forms[0].submit();
			}
		}
		
		function doSub(menuid, url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />ѡ�񽻷ѷ�ʽ��" + menuid);
				
				<%--modify begin cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443 --%>
				if (url == "charge/cashCharge.action")
				{
					// �ֽ𽻷ѣ��жϴ�ʱ������Ƿ����
					var url1 = "${sessionScope.basePath}charge/checkTime.action";
					
					var loader = new net.ContentLoader(url1, netload = function () {
						var resXml1 = this.req.responseText;
						if (resXml1 == "1" || resXml1 == 1)
						{
							openRecWaitLoading_NX("recWaitLoading");
							
							document.actform.target = "_self";
							document.actform.action = "${sessionScope.basePath}" + url;
							document.actform.submit();
						}
						else
						{
							submitFlag = 0;
							
							alertError('<%=limitTip%>');
							
							return;
						}
					}, null, "POST", null, null);
				}
				else
				{
					openRecWaitLoading_NX("recWaitLoading");
					
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				<%--modify end cKF76106 2013/06/03 R003C13L05n01 OR_NX_201305_1443 --%>
			}			
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<s:if test="yingjiaoFeeFen == 0">
				<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value="0">
			</s:if>
			<s:else>
				<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>	
			</s:else>
			<input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
			<input type="hidden" id="servRegion" name="servRegion" value="<s:property value='servRegion' />">
			<input type="hidden" id="chargeFlag" name="chargeFlag" value="<s:property value='chargeFlag' />">
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��ֵ��������</h2>
						<%
						if ("feeChargeByCash".equals(curMenuId))
						{
						%>
							<div class="blank10"></div>
							<a href="javascript:void(0)">1. �����ֻ�����</a> 
							<a href="javascript:void(0)" class="on">2. ѡ��֧����ʽ</a>
	    					<p class="recharge_tips">�鿴���Ļ�����Ϣ�����ύ��ֵ���ѽ�����֧����</p>
	    					<a href="javascript:void(0)">3. ֧��</a> 
	    					<a href="javascript:void(0)">4. ���</a>
    					<%
    					}
    					else if ("feeChargeByCard".equals(curMenuId))
    					{
    					%>
							<div class="blank10"></div>
							<a href="javascript:void(0)">1. �����ֻ�����</a> 
							<a href="javascript:void(0)" class="on">2. ѡ��֧����ʽ</a>
	    					<a href="javascript:void(0)">3. ѡ����</a> 
	    					<a href="javascript:void(0)">4. ��������</a>
	    					<a href="javascript:void(0)">5. ����������</a> 
	    					<a href="javascript:void(0)">6. ��������</a>
	    					<a href="javascript:void(0)">7. �˶���Ϣ</a>
	    					<a href="javascript:void(0)">8. ���</a>
	    			    <%
    					}
    					%>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="servnumber" /></span></p>
    						<%--add begin g00140516 2011/12/08 R003C11L12n01 OR_NX_201112_250 --%>
    						<p class="fs22 fwb pl40 lh30">�û�������<span class="yellow fs22"><s:property value="subsname" /></span></p>
    						<%--add end g00140516 2011/12/08 R003C11L12n01 OR_NX_201112_250 --%>
    						<s:if test="yingjiaoFeeFen == 0">
								<p class="fs22 fwb pl40 lh30">������<span class="yellow fs22"><s:property value="balance" /></span> Ԫ</p>
							</s:if>
							<s:else>
								<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> Ԫ</p>
							</s:else>
							<div class="blank10"></div>
							<div class="line"></div>
      						<div class="blank10"></div>
      						<p class="fs22 tit_arrow"><span class="bg"></span>ѡ��֧����ʽ��</p>
      						<div class="blank20"></div>
        					<div class="blank5"></div>
        					<ul class="pay_way_list clearfix">
          						<s:iterator value="usableTypes" id="type" status="st">
        								<li>
											<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;">
	         								<img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' />
	         								<span class="chargeTypeDesp1">(�밴<s:property value="#st.index + 1" />��)</span>
	         								</a>
        								</li>
          						</s:iterator>
        					</ul>				
    					</div>
					</div>
				</div>
			</div>
			<%@ include file="/backinc.jsp"%>		
			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-04.wav" id="player04" align="center" autostart=true border="0" style="height:0px;width:0px;">
		</form>
	</body>
</html>
