<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%
TerminalInfoPO terminalInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// �ն�����
String termSpecial = terminalInfo.getTermspecial();

//add begin cKF48754 2011/11/10 R003C11L11n01 ����ɽ��ǰ̨�ֽ���˹���
//ʡ��
String province = (String) PublicCache.getInstance().getCachedData("ProvinceID");
//add end cKF48754 2011/11/10 R003C11L11n01 ����ɽ��ǰ̨�ֽ���˹���

String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

//add begin cKF76106 2011/11/17 R003C12L09n01 ����ȥ��ҳ�潥��Ч��
// ҳ�潥��Ч��ʱ��
String lateTime = "0.5";

if(Constants.PROOPERORGID_NX.equals(province))
{
	lateTime = "0.0";
}
//add end cKF76106 2011/11/17 R003C12L09n01 ����ȥ��ҳ�潥��Ч��

String lockedFlag = (String) PublicCache.getInstance().getCachedData("SH_LOCKED_FLAG");

// add begin hWX5316476 2014-11-20 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
// ͼƬ�������أ�0���ر�  1��������
String imageSwitch = (String) PublicCache.getInstance().getCachedData("SH_IMAGESCREEN_SWITCH");
// add end hWX5316476 2014-11-20 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>	
		<title>�ƶ������ն�</title>
		<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Expires" CONTENT="0"/>
		<meta http-equiv="Page-Exit"; content="blendTrans(Duration=<%=lateTime %>)"/> 
		<link href="${sessionScope.basePath}css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/newAdd.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath}js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/net.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/script.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/media/SelfMedia.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath}js/initControl.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js?ver=${jsVersion}"></script>
		<script type="text/javascript">
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
			
			<%--add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
			
			<%
			if ("1".equals(keyFlag))
			{
			%>
				<s:iterator value="centerMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
				
				<s:iterator value="topRightMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48 + centerMenus.size" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
				
				<s:iterator value="bottomRightMenus" id="menu" status="st">	
					if (key == <s:property value="#st.index + 48 + centerMenus.size + topRightMenus.size" />)
					{
						go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />');
					}
				</s:iterator>
			<%
			}
			%>
						   				
			<%--add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
		}
		
		//�Ƿ�֧�ִ�ӡƱ�ݱ�־,0��֧��,1:֧��
		var isPrintFlag = <%=termSpecial.charAt(0)%>;
		
		//�Ƿ�֧�ִ�ӡ��Ʊ��־,0��֧��,1:֧��
		var isInvoicePrint = <%=termSpecial.charAt(1)%>;
		
		//�Ƿ�֧�ּ��ܼ��̱�־,0��֧��,1:֧��
		var isKeyBoard = <%=termSpecial.charAt(2)%>;
		
		//�Ƿ�֧���ֽ�ɷѱ�־,0��֧��,1:֧��
		var isCashEquip = <%=termSpecial.charAt(3)%>;
		
		//�Ƿ�֧�����п��ɷѱ�־,0��֧��,1:֧��
		var isUnionPay = <%=termSpecial.charAt(4)%>;
		  
		//�Ƿ�֧�ֶ�ȡ�������֤��Ϣ��־,0��֧��,1:֧��
		var is2GIDFlag = <%=termSpecial.charAt(8)%>;
		
		//�Ƿ�֧�������ۿ�ӿ� 0��֧��,1:֧��
		var isCardPay = <%=termSpecial.charAt(9) %>
		
		// �ؼ����Ƶ�� 
		var frequency = <%=PublicCache.getInstance().getCachedData(Constants.SH_OCX_CHECK_FREQUENCY) %>;
		
		//��ʱ����ҳ����һ��ʱ����û���κβ���������ת������ҳ��
		var screenTime = 0;
		
		// js���������ļ�

		// ��������·��
		var rootPath = '<%=request.getContextPath() %>';
		
		// �ļ����غ󱣴浽�ı���·��
		var localURL = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>';
		
		// �������������ļ��б�(sc.wpl)
		var scWplFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCWPL_LIST) %>';
		
		// ���ع�沥���ļ��б�(adv.wpl)
		var advWplFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVWPL_LIST) %>';
		
		// �������������б�(scfile.xml)
		var scPLFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_SCPLAY_LIST) %>';
		
		// ���ع�沥���б�(advfile.xml)
		var advPLFileName = '<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_ADVPLAY_LIST) %>';
		
		// �Ƿ�������
		var hasScreen = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_IS_THERE_SCREENSAVERS) %>;
		
		// ��ҳ���������ĵȴ�ʱ��
		var goSCTime = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_GO_SCEEN_TIME) %>;
		
		// ϵͳҳ��ˢ��ʱ��
		var timeRefresh = <%=(String) PublicCache.getInstance().getCachedData(Constants.PAGE_TIMEOUT) %>;

		// �ؼ���ʼ��
		function initControl()
		{
			 if (frequency == 0){
			 
			    // Ʊ�ݴ�ӡ����ʼ��
			    indexInitListPrt();
			    
			    // ��Ʊ��ӡ����ʼ��
			    indexInitInvoicePrint();
		    }
		}

		function go(menuid, url) 
		{
			document.getElementById("curMenuId").value = menuid;
			
			//modify begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
			openRecWaitLoading_NX("recWaitLoading"); 
			//modify end m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125
			
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}" + url;
			document.actform.submit();
		}

		function dofocus()
		{
		    window.focus();
			
			//���г�ʼ������			
			initControl();
		}

		// ��̨���ݿ����������������ʱ��Ĭ��60��
		function screenAD(){
		    screenTime ++ ;
		    <%--modify begin g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149 --%>
		    <%
		   	 String termIP = (String)request.getSession().getAttribute("termIP");
		   	 String termMac = (String) request.getSession().getAttribute("termMac");
		    %>
		    
		    if(screenTime == goSCTime)
		    {
		    	// add begin by l00263786 20150616 OR_SD_201504_102 �����ն�����ʵ��ȫʡͳһ����
		    	// �ж��Ƿ����������Դ�����������ļ��Ͷ�Ӧ����Դ�ļ���
		        if(!scResExist(localURL,scWplFileName,scPLFileName))
		        {
		        	screenTime = 0;
		        	return;
		        }
		    	// add end by l00263786 20150616 OR_SD_201504_102 �����ն�����ʵ��ȫʡͳһ����
		    	<%
		    	// modify begin hWX5316476 2014-11-20 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
				// ͼƬ�������ſ��ؿ���
				if("1".equals(imageSwitch))
		    	{
		    	%>
		        	window.open("${sessionScope.basePath}jsp/SelfMedia/SelfScreen/selfScreenImages.jsp?termIP=<%=termIP %>&termMac=<%=termMac %>", "_self");
		    	<%
				}
				else
				{
				%>
		    		window.open("${sessionScope.basePath}jsp/SelfMedia/SelfScreen/selfScreen.jsp?termIP=<%=termIP %>&termMac=<%=termMac %>", "_self");
		    	<%
				}
				// modify end hWX5316476 2014-11-20 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
				%>
		    }
		    <%--modify end g00140516 2011/11/11 R003C11L11n01 OR_huawei_201111_149 --%>
		}

		// ȡ������
		function screenCancel(){
		    screenTime = 0;
		    
		    document.getElementById()
		}
		
		function f_setTimeout() 
		{
			<%
			if ("1".equals(lockedFlag))
			{
			%>
			top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?lockTerm=lockTerm&timeoutFlag=1");
			<%
			}
			else
			{
			%>
			// modify begin g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
	  		top.mainfrm.location.replace("${sessionScope.basePath }login/goHomePage.action?timeoutFlag=1");
	  		// modify end g00140516 2012/04/09 R003C12L04n01 OR_SD_201202_920
			<%
			}
			%>
		}

		function welcomePlay()
		{
			document.getElementById("player").play();
		}
		
		// ҳ���ʼ��
		function bodyLoad()
		{
			// add begin lWX5316086 ���� ����ͬһ����ͬһ������ε�¼
			<%
			if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
			{
			%>
				// ����ն˻���¼��Ϣ������
				deleteLoginCheckByTermId();
			<%
			}
			%>  
			// add end lWX5316086 ���� ����ͬһ����ͬһ������ε�¼
			
			// ������ʾ����
			<%
			if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
					&& "1".equals((String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
			{
				String interval = (String) PublicCache.getInstance().getCachedData("SH_WELCOME_INTERVAL");
			%>
				setInterval("welcomePlay()", <%=interval %> * 1000);
			<%
			}
			%>   
			if(hasScreen == 1)
			{
				// modify begin hWX5316476 2014-12-01 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
				<%
				if (Constants.PROOPERORGID_SD.equals(province))
				{
				%>
			        // ����Ӻ�̨��ȡ�����ļ��б�,�����ص�ָ��Ŀ¼
					var isHaveSetScreen = getMediaList("media/getMediaScList.action", scPLFileName, '<%=Constants.SC_TYPE%>', scWplFileName);
					// ��������ļ����سɹ����������
					if(isMediaScDownloaded(isHaveSetScreen))
					{
						setInterval('screenAD()', 1000);
					}
					
			    <%
				}
				else
				{
				%>
					setInterval('screenAD()', 1000);
					getMediaList("media/getMediaScList.action", scPLFileName, '<%=Constants.SC_TYPE%>', scWplFileName);
				<%
				}
				%>
				// modify end hWX5316476 2014-12-01 OR_SD_201411_622_ɽ��_���������ն˹���ƽ̨֧��������Ϣ����������
			}
			else
			{
				setTimeout("f_setTimeout()", timeRefresh);	
			}
			
		}
		
		// add begin lWX5316086 ���� ����ͬһ����ͬһ������ε�¼
		function deleteLoginCheckByTermId()
		{
			var url = "${sessionScope.basePath }login/deleteLoginCheckByTermId.action";
		
			var loader = new net.ContentLoader(url, netload = function () {
				var resXml = this.req.responseText;
						
			}, null, "POST", null, null);
			
		}
		// add end lWX5316086 ���� ����ͬһ����ͬһ������ε�¼

		
		// ���������ļ�������
		function downMediaFile(strRemoteURL,strLocalURL) 
		{
		    //��ֹ��ɱ�����ɱ��
			<%
			    out.println("var xmlHTTP;");
			    out.println("if(window.ActiveXObject){");
			    out.println("xmlHTTP=new ActiveXObject( \"Microsoft.XMLHTTP\");");
			    out.println("}else if(window.XMLHttpRequest){");
			    out.println("xmlHTTP=new XMLHttpRequest();");
			    out.println("}");
			    out.println("xmlHTTP.open( \"Get\",strRemoteURL,false); ");
			    out.println("xmlHTTP.send();"); 
			    out.println("var adodbStream=new ActiveXObject( \"ADODB.Stream\");"); 
			    out.println("adodbStream.Type=1;");
			    out.println("adodbStream.Open();"); 
			    out.println("adodbStream.write(xmlHTTP.responseBody);"); 
			    out.println("adodbStream.SaveToFile(strLocalURL,2);"); 
			    out.println("adodbStream.Close();"); 
			    out.println("adodbStream=null;"); 
			    out.println("xmlHTTP=null;"); 
			%>
		}
		
		function goProvideGoods()
		{
			document.actform.target="_self";
			document.actform.action = "${sessionScope.basePath}managerOperation/selectOperationType.action";
			document.actform.submit();
		}
		
		function inputMgtPwd()
		{
			document.actform.target = "_self";
			document.actform.action = "${sessionScope.basePath}rectelInfo/inputMgtPwd.action";
			document.actform.submit();	
		}	
		
		// �ֽ�ʶ����״̬��鶨ʱ�����
		var checkCashToken;	 
		
		// ����ֽ�ʶ����״̬�����Ϊ3-Ǯ��򿪣���ת���ֽ����ҳ�棨���ģ�
		function doCashAuditFwd()
		{
			var cashStatus = "";
			
			try
			{
				// ����ʶ����״̬���ӿڣ�ȡʶ����״̬
				cashStatus = checkCashStatus();
			}
			catch (e) 
			{
				clearInterval(checkCashToken);
				alert("�ֽ�ʶ���������쳣,���ʶ����״̬ʧ��");
				return;
			}
			
			if(cashStatus == 3)
			{
				// �����ʱ����
				clearInterval(checkCashToken);
				window.location.href = "${sessionScope.basePath}managerOperation/doCashAudit.action?from=index2";
			}
		}
		<%
			// modify begin zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
			// �ֽ���˿���
			String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
		
			if ((Constants.PROOPERORGID_NX.equals(province) || "1".equals(cashAuditSwitch)) 
				&& termSpecial.charAt(3) == '1'){
		%>
				// ʶ����״̬�����ʱ��
				var checkInterval = <%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CASHSTATUS_CHECKINTERVAL) %>;
				checkCashToken = setInterval("doCashAuditFwd()", checkInterval);				
		<%
			}
			// modify end zKF69263 2015-4-1 OR_SD_201502_169_ɽ��_�����ն�ʵ���ֽ���˹���
		%>
		</script>
	</head>
	<body scroll="no" class="margin0" onclick="screenCancel()" onload="dofocus();bodyLoad();">
		<form id="actform" name="actform" method="post">
			<input type="hidden" id="curMenuId" name="curMenuId" value=""/>
			
			<%
			if (Constants.PROOPERORGID_HUB.equals(province))
			{
			%>
				<div class="header" style="height:90px; margin-top:32px">
			<%
			}
			else
			{
			%>
				<div class="header">
			<%
			}
			%>
				<%
				if (Constants.PROOPERORGID_NX.equals(province))
				{
				%>
			    	<div class="nx_new_logo"></div>
			    <%
				}
				else if (Constants.PROOPERORGID_SD.equals(province))
				{
				%>
					<div class="logo"></div>
				<%
				}
				else if (Constants.PROOPERORGID_HUB.equals(province))
				{
				%>
					<div class="logo" style="margin:24px 38px 0px 53px; _margin:24px 38px 0px 26px;"></div>
				<%
				}
				%>
			    <div class="tit"></div>
			    <div class="time">
			    	<%--modify begin cKF48754 2011/11/10 R003C11L11n01 ����ɽ��ǰ̨�ֽ���˹��� --%>
			   	 	<%
					if (Constants.PROOPERORGID_SD.equals(province))
					{
					%>
					<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" alt="��ǰʱ��">
					<span id="_ShowTime"></span>
					<%
					}
					else if (Constants.PROOPERORGID_NX.equals(province))
					{
					%>
					<img src="${sessionScope.basePath}images/bell.gif" width="20" height="12" alt="��ǰʱ��" onclick="inputMgtPwd();">
					<span id="_ShowTime"></span>
					<%
					}
					else
					{	
					%>
			    	<span onclick="goProvideGoods();" style="width:40px; height:24px; background:#DBEEFA; float:left" title="�ֽ����"></span>
			    	<div id="_ShowTime" style="display:none;"></div>
			    	<%
					}
					%>
					<%--modify end cKF48754 2011/11/10 R003C11L11n01 ����ɽ��ǰ̨�ֽ���˹��� --%>
			    	
			    	<script type="text/javascript">startTime();</script>
			    </div>			    
			</div>
                  
			<div class="index_main">
				<s:if test="centerMenus != null && centerMenus.size > 0">
					<%
					if (Constants.PROOPERORGID_HUB.equals(province))
					{
					%>
						<div class="index_left">
				   		<s:iterator value="centerMenus" id="menu" status="st">
				   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   				<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='pic<s:property value="#st.index+1" /> bg' alt='<s:property value="#menu.menuname" />' />
				   			</a>
				   		</s:iterator>
				   		</div>
					<%
					}
					else
					{
					%>
						
						<div class="index_left">
							<%
							if (Constants.PROOPERORGID_NX.equals(province))
							{
								List centerMenus = (List)request.getAttribute("centerMenus");
								
							%>
								<%
								if(centerMenus.size()==2||centerMenus.size()==3||centerMenus.size()==4||centerMenus.size()==5) 
								{
								%>
									<div class="newAdd_main newAdd_menu<%=centerMenus.size() %>">
										<s:iterator value="centerMenus" id="menu" status="st">
											<!--
											<div onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" class="newAdd_navBody navBody<s:property value="#st.index" />"><span>���밴<s:property value="#st.index" />����</span></div>
											<img class="newAdd_navBody_img navBody<s:property value="#st.index" />" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
											-->
											<a onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;"  class="navBody body<s:property value="#st.index + 1" />" href="javascript:void(0)">
												<img style="position:absolute; top:0px; left:0px;" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
												<span>���밴<s:property value="#st.index" />����</span>
											</a>									 
										</s:iterator>
									</div>
								<%
								}
								else{
								 %>
								 	<div class="newAdd_main newAdd_menu8 %>">
										<s:iterator value="centerMenus" id="menu" status="st">
											<!--
											<div onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" class="newAdd_navBody navBody<s:property value="#st.index" />"><span>���밴<s:property value="#st.index" />����</span></div>
											<img class="newAdd_navBody_img navBody<s:property value="#st.index" />" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
											-->
											<a onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;"  class="navBody body<s:property value="#st.index + 1" />" href="javascript:void(0)">
												<img style="position:absolute; top:0px; left:0px;" src="${sessionScope.basePath}<s:property value="#menu.imgpath" />" alt="<s:property value="#menu.menuname" />" title="<s:property value="#menu.menuname" />" />
												<span>���밴<s:property value="#st.index" />����</span>
											</a>									 
										</s:iterator>
									</div>
								<%
								} 
								%>
					   		<%
					   		}
					   		else
					   		{
					   		%>
								<s:iterator value="centerMenus" id="menu" status="st">
						   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
						   				<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='pic<s:property value="#st.index" /> bg' alt='<s:property value="#menu.menuname" />' />
						   				<%--add begin g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
						   				<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span class="centerMenuTip<s:property value="#st.index" />">(�밴<s:property value='#st.index + 1' />��)</span>
						   				<%
						   				}
						   				%>
						   				<%--add end g00140516 2012/09/19 R003C12L09n01 OR_NX_201207_781 --%>
						   			</a>
						   		</s:iterator>
					   		<%
					   		}
					   		%>
					   	 </div>
					   	 <%
					   	 }
					   	 %>
						
				   	
			   	</s:if>
			   	
			   	<s:if test="rightMenus != null && rightMenus.size > 0">
			   		<div class="index_right">
			   			<s:iterator value="rightMenus" id="menu" status="st">
				   			<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
		   						<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='index_right bt<s:property value="#st.index+1" />' alt='<s:property value="#menu.menuname" />' />
		   						<%
				   				if ("1".equals(keyFlag))
				   				{
				   				%>
				   					<span style="font-size:16px; margin-left: 60px;" >(�밴<s:property value='#st.index + centerMenus.size + 1'/>��)</span>
				   				<%
				   				}
				   				%>
		   					</a>
	   					</s:iterator>
			   		</div>
			   	</s:if>
			   	
			   	<s:elseif test="(topRightMenus != null && topRightMenus.size > 0) || (bottomRightMenus != null && bottomRightMenus.size > 0)">
				   	<%
					if (Constants.PROOPERORGID_NX.equals(province))
					{
					%>
					<div class="index_right_nx">
					<%
					}
					else
					{
					%>
					<div class="index_right">
					<%
					}
					%>			   	
				   		<s:if test="topRightMenus != null && topRightMenus.size > 0">
				   			<s:iterator value="topRightMenus" id="menu" status="st">
				   				<s:if test="#st.index == 0">
				   					<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   						<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt11' alt='<s:property value="#menu.menuname" />' />
				   						<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span style="font-size:16px; margin-left: 60px;" >(�밴<s:property value='#st.index + centerMenus.size + 1'/>��)</span>
						   				<%
						   				}
						   				%>
				   					</a>
				   				</s:if>
					   			<s:else>
					   				<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
					   					<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt21' alt='<s:property value="#menu.menuname" />' />
					   					<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<span style="font-size:16px; margin-left: 60px;" >(�밴<s:property value='#st.index + centerMenus.size + 1'/>��)</span>
						   				<%
						   				}
						   				%>
					   				</a>
					   			</s:else>
					   		</s:iterator>
				   		</s:if>
				   		<s:if test="bottomRightMenus != null && bottomRightMenus.size > 0">
				   			<s:iterator value="bottomRightMenus" id="menu" status="st">
				   				<s:if test="#st.index == 0">
				   					<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
				   						<s:if test="topRightMenus == null || topRightMenus.size == 0">
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt3111' alt='<s:property value="#menu.menuname" />' />
				   						</s:if>
				   						<s:elseif test="topRightMenus.size == 1">
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt311' alt='<s:property value="#menu.menuname" />' />
				   						</s:elseif>
				   						<s:else>
				   							<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt31' alt='<s:property value="#menu.menuname" />' />
				   						</s:else>
				   						
				   						<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<%--modify begin g00140516 2013/02/16 R003C13L02n01 ��ʽ��ҳ����� --%>
						   					<span class="fs16 ml60">(�밴<s:property value='#st.index + centerMenus.size + topRightMenus.size'/>��)</span>
						   					<%--modify end g00140516 2013/02/16 R003C13L02n01 ��ʽ��ҳ����� --%>
						   				<%
						   				}
						   				%>
				   					</a>
				   				</s:if>
					   			<s:else>
					   				<a href="javascript:void(0);" onclick="go('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;" title='<s:property value="#menu.menuname" />'>
					   					<img src='${sessionScope.basePath}<s:property value="#menu.imgpath" />' class='bt41' alt='<s:property value="#menu.menuname" />' />
					   					
										<%
						   				if ("1".equals(keyFlag))
						   				{
						   				%>
						   					<%--modify begin g00140516 2013/02/16 R003C13L02n01 ��ʽ��ҳ����� --%>
						   					<span class="fs16 ml60">(�밴<s:property value='#st.index + centerMenus.size + topRightMenus.size'/>��)</span>
						   					<%--modify end g00140516 2013/02/16 R003C13L02n01 ��ʽ��ҳ����� --%>
						   				<%
						   				}
						   				%>
						   			</a>
					   			</s:else>
					   		</s:iterator>
				   		</s:if>					   		
				   	</div>
			   	</s:elseif>   
	  		</div>
  		</form>
  		<%
  		// �𾴵��û�����ӭ��ʹ���й��ƶ����������նˣ�������Ļ������Ҫ�ķ���лл��
		if (Constants.PROOPERORGID_NX.equals((String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID))
				&& "1".equals((String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_PHONICMSG)))
		{
		%>
  			<embed src="<%=(String)PublicCache.getInstance().getCachedData(Constants.SH_LOCAL_FILE_PATH) %>/Charge-01.wav" id="player" align="center" border="0" style="height:0px;width:0px;">
  		<%
  		}
  		%>
  		<%-- modify begin m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 --%>
  		<%-- modify begin hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
  		<!--�������ڴ���div-->
		<div class="popupWin fs28 credit_pls_wait" id="recWaitLoading">
			<div class="bg"></div>
    		<p class="mt120"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
   			<p class="tips_txt"><%=CommonUtil.getParamValue(Constants.REC_WAITLOADING_MSG,"���ڴ������Ժ�......") %></p>
   		</div>
   		<%-- modify end hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
   		
   		<script type="text/javascript">
   		// �������ڴ���DIV(����)
		openRecWaitLoading_NX = function(id)
		{
		<%
		if(Constants.PROOPERORGID_NX.equalsIgnoreCase(province))
		{
		%>
			wiWindow = new OpenWindow("recWaitLoading", 804, 515);
		<%
		}
		%>
		} 
    	</script>
    	<%-- modify end m00227318 2012/10/20 eCommerce V200R003C12L10 OR_huawei_201210_125 --%> 
	</body>
</html>
