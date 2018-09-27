<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String pOrgName = termInfo.getOrgname();
	
	//�û���Ϣsession
	NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
	String servnumber = null;

	if (null != customer )
	{
		servnumber = customer.getServNumber();
	}
%>
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
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
	
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>		
		// �˳�
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
			return;			
		}
		if(key == 49)
		{
			//topGo('rec', 'reception/receptionFunc.action');
			goback('<s:property value='curMenuId' />');
			return;
		}
	<%
	}
	else
	{
	%>
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	<%
	}
	%>
	
}

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
		
		document.getElementById("curMenuId").value = menuid;
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
		document.forms[0].submit();
	}
}

	// add begin wWX217192 2014-07-09 OR_huawei_201406_338 ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_��ʵ���Ʋ���
	// ��ʵ���Ʋ����ͻ���ϢУ��ɹ����豸�Զ���ӡƾ��
	var pServNumber = "";
	
	var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
	var pPrintDate = getDateTime();  //��ӡ����
	
	// ��ʵ����ƾ����ӡ����
	if("<s:property value='curMenuId' />" == 'recNonRegister')
	{
		pServNumber = "<s:property value='telNumber' />";
		
		var accessValue = "";
		if("<s:property value='accessName' />" == "������֤")
		{
			accessValue = "������֤";
		}
		else if("<s:property value='accessName' />" == "��������")
		{
			accessValue = "��������";
		}
		else if("<s:property value='accessName' />" == "SIM��")
		{
			accessValue = "SIM���� " + "<s:property value='simCardNo' />";
		}
		else if("<s:property value='accessName' />" == "��ֵ��¼")
		{
			accessValue = "��ֵ��¼�� " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />Ԫ"
		}
		else if("<s:property value='accessName' />" == "ͨ����¼")
		{
			accessValue = "ͨ����¼�� " + "<s:property value='callNums' />";
		}
		
		var printcontext = "�𾴵��û������ã���������ֻ������ʵ���ƿͻ�������֤����֤��ʽΪ "+ accessValue + 
			"���뵱��ִ�ƾ����ǰ̨�����ʵ���ƿͻ�����������ƾ���Զ����ϣ����ǰ̨����������ͻ�Ͷ����Ҫ�ָ��ģ����ǽ�ע���˱�ҵ��򲹿���ԭ������" + 
			"ͬʱ�ú���30���ڲ��ܰ������ҵ��������������ѯӪҵԱ��  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"�Զ���ӡ��ʵ������֤����ƾ��");
		
		doPrintNoRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	
	// ʵ����5ѡ2ƾ����ӡ����
	if("<s:property value='curMenuId' />" == 'realNameReg')
	{
		pServNumber = "<s:property value='servnumber' />";
		
		var accessValue = "";
		if("<s:property value='selectMethod' />" == "selectRandomPwd")
		{
			accessValue = "������֤";
		}
		// modify begin wWX217192 2014-08-20 OR_huawei_201408_964 �����ն�����ʵ���Ǽ���֤�������ն�5ѡ2��֤��ʽ)�Ż�
		else
		{
			accessValue = "SIM���� " + "<s:property value='cardNum' />";
		}
		
		if("<s:property value='selectMethod2' />" == "selectServerPwd")
		{
			accessValue = accessValue + "�ͷ�������";
		}
		// modify end wWX217192 2014-08-20 OR_huawei_201408_964 �����ն�����ʵ���Ǽ���֤�������ն�5ѡ2��֤��ʽ)�Ż�
		else if("<s:property value='selectMethod2' />" == "selectRechargeRecord")
		{
			accessValue = accessValue + "�ͳ�ֵ��¼�� " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />Ԫ"
		}
		else if("<s:property value='selectMethod2' />" == "selectCallRecord")
		{
			accessValue = accessValue + "��ͨ����¼�� " + "<s:property value='calledNum' />";
		}
		
		var printcontext = "�𾴵��û������ã���������ֻ�����ʵ���ƵǼ���֤����֤��ʽΪ " + accessValue +
			"���뵱��ִ�ƾ����ǰ̨����ʵ���ƵǼǣ�����ƾ���Զ����ϣ����ǰ̨����������ͻ�Ͷ����Ҫ�ָ�ԭ���ϵģ����ǽ��ָ�ԭ���ϣ�" + 
			"ͬʱ�ú���30���ڲ��ܰ������ҵ��������������ѯӪҵԱ��  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"�Զ���ӡʵ������֤ƾ��");
		
		doPrintRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	// add end wWX217192 2014-07-09 OR_huawei_201406_338 ɽ��_Ӫҵ��ȫҵ�������Ż�-ҵ�����(�����ն�)_ʵ���Ʋ���
	
	// add begin wWX217192 2014-08-07 OR_SD_201408_166 �����ն�����ʵ���Ǽ���֤�������ն�5ѡ3��֤��ʽ)
	// ʵ����5ѡ3ƾ����ӡ����
	if("<s:property value='curMenuId' />" == 'realNameReg53')
	{
		pServNumber = "<s:property value='servnumber' />";

		var accessValue = "";
		if("<s:property value='selectMethod' />" == "selectRandomPwd")
		{
			accessValue = "������֤";
		}
		else if("<s:property value='selectMethod' />" == "selectServerPwd")
		{
			accessValue = "��������";
		}
		else if("<s:property value='selectMethod' />" == "selectSimNo")
		{
			accessValue = "SIM���� " + "<s:property value='cardNum' />"
		}
		
		accessValue = accessValue + "����ֵ��¼�� " + "<s:property value='chargeRecordPO.currMonChargeDate' />  " 
				+ "<s:property value = 'chargeRecordPO.currMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.lastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.lastMonChargeAmount' />Ԫ�� " + "<s:property value='chargeRecordPO.preLastMonChargeDate' />  "
				+ "<s:property value = 'chargeRecordPO.preLastMonChargeAmount' />Ԫ" + "��ͨ����¼�� " + "<s:property value='calledNum' />";
		
		var printcontext = "�𾴵��û������ã���������ֻ�����ʵ���ƵǼ���֤����֤��ʽΪ " + accessValue +
			"���뵱��ִ�ƾ����ǰ̨����ʵ���ƵǼǣ�����ƾ���Զ����ϣ����ǰ̨����������ͻ�Ͷ����Ҫ�ָ�ԭ���ϵģ����ǽ��ָ�ԭ���ϣ�" + 
			"ͬʱ�ú���30���ڲ��ܰ������ҵ��������������ѯӪҵԱ��  " + "  " +pOrgName + "   " + pPrintDate;
		
		writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.BUSITYPE_NOREALNAMEREG) %>", 
				"�Զ���ӡʵ������֤ƾ��");
		
		doPrintRealName_SD(pServNumber,pOrgName,pPrintDate,printcontext);
	}
	// add end wWX217192 2014-08-07 OR_SD_201408_166 �����ն�����ʵ���Ǽ���֤�������ն�5ѡ3��֤��ʽ)

//add begin sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_�����ն�Ӫ�������Ż�
//ɽ����������Ӫ��ƽ̨����������	
function feedbackByStatus()
{
	<%
	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(pageProvince))
	{
	%>	
		//�˵�id
		var offerMenu = "<s:property value='curMenuId' />";
		
		//�ֻ�����
		var servnumber = <%=servnumber%>;
		
		//����status=1����ʾҵ�񶩹��ɹ�
		var postStr ="offerMenu="+offerMenu+"&servnumber="+servnumber+"&status=1";  
		 
		var receptionUrl = "${sessionScope.basePath}ISSS/feedbackByStatus.action";
		
		//������Ӫ��ƽ̨����������
		var loader11 = new net.ContentLoader(receptionUrl, netload = function () 
		{
		}, null, "POST", postStr, null);
	
	<%
	}
	%>
}
//add end sWX219697 2014-9-18 14:43:11 OR_SD_201409_556_�����ն�Ӫ�������Ż�

</script>
</head>
	<body scroll="no" onload="feedbackByStatus()">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box service_transact_ok service_transact_fail m0auto">
					<dl class="clearfix reception_ok_pt60">
						<% 
			            if(Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
			            {
						%> 
							<br><br><br><br>
						<%
						}
						%>
						<dd class="tc" style="">
							<span class="transcact_ok">
							    <s:if test="successMessage == null">
								    ҵ�����ɹ�!
								</s:if>
								<s:else>
								    <s:property value="successMessage"/>
								</s:else>
							</span>
							<p class="btn_box clearfix"> 
								<% 
					            if(Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
					            {
								%>
									
									<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';topGo('rec', 'reception/receptionFunc_hub.action'); return false;">����ҵ��</a>
								<%
								}
								else
								{
								%>
									<%
									if ("1".equals(keyFlag))
									{
									%>
										<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';goback('<s:property value='curMenuId' />'); return false;">����ҵ��(��1��)</a>
									<%
									}
									else
									{
									%>
										<a href="#" class="see_more" onmousedown="this.className='see_more_hover'" onmouseup="this.className='see_more';topGo('rec', 'reception/receptionFunc.action'); return false;">����ҵ��</a>
									<%
									}
									%>
								<%
								}
								%> 
							</p>
						</dd>
					</dl>
					
					<div class="line"></div>
					<% 
		            if(!Constants.PROOPERORGID_HUB.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
		            {
					%>
					<!-- ����Ƽ�ҵ��ʼ -->
					  <div class="recommend_box">
					    <div class="recommend_title clearfix"><span>���ҵ���Ƽ���</span></div>
					    <ul class="recommend_list clearfix">
						    <%
						    if (titleTotalMenus != null && titleTotalMenus.size() > 0)
						    {
						    	MenuInfoPO menu = null;
						    	int j = 0; int k = 0;
						    	for (int i = 0; i < titleTotalMenus.size(); i++)
						    	{
						    		menu = (MenuInfoPO) titleTotalMenus.get(i);
						    		
						    		 if ("rec".equalsIgnoreCase(menu.getParentid()) 
						                        && (Constants.PROVINCE_REGION_999.equalsIgnoreCase(menu.getRegion())
						                                || terminalInfo.getRegion().equalsIgnoreCase(menu.getRegion()))&& k<4) 
						            {
						            	k++;
										%>
											<li>
												<a class="relative reception_recommanded_a" id="nav_<%=menu.getMenuid() %>" href="javascript:void(0);" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><img src='${sessionScope.basePath}<%=menu.getImgpath2() %>' alt='<%=menu.getMenuname() %>' /></a>
												<p><a href="javascript:void(0);" class="reception_recommanded_p_a" onclick="topGo('<%=menu.getMenuid() %>', '<%=menu.getGuiobj() %>'); return false;"><%=menu.getMenuname() %></a></p>
											</li>
										<%
						            }
						    	}
						    }
				      		%>
					    </ul>
					  </div>
					<!-- ����Ƽ�ҵ����� -->
					<%
					}
					%>
					
				</div>				
			</div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
