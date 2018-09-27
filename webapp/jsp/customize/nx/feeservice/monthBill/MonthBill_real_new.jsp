<%@ page contentType="text/html; charset=GBK"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@page import="com.gmcc.boss.selfsvc.util.CurrencyUtil"%>

<%@page import="com.customize.sd.selfsvc.feeService.model.FeePO"%>
<%@page import="com.customize.sd.selfsvc.feeService.model.FeeGroupPO"%>
<%@page import="com.customize.sd.selfsvc.feeService.model.FeedetailPO"%>
<%@page import="com.customize.sd.selfsvc.feeService.model.PkgPO"%>
<%@page import="com.customize.sd.selfsvc.feeService.model.PrivPO"%>
<%@page import="com.customize.sd.selfsvc.feeService.model.BillPO"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	// �ͻ���Ϣ
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	// �ն���Ϣ
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	// �˵���Ϣ
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	
	// ������Ϣ
	List<Object> obj_billfixed = (List<Object>)map.get("billfixed");
	
	// ����ҵ����Ϣ(˫��)
	List<FeedetailPO> feedetailList = (List<FeedetailPO>)map.get("feedetails");
	
	// ����ҵ����Ϣ(����)
	List<FeedetailPO> feedetailList_ = (List<FeedetailPO>)map.get("feedetails_");
	
	// �˻���Ҫ��Ϣ
	Map acctbalanceMap = (Map)map.get("acctbalance");
	
	// �˻���Ҫ�б�
	List<Map> acctbalanceList = (List<Map>)acctbalanceMap.get("acctlist");
	
	// ������Ϣ
	Map<String,String> scoreinfoMap = (Map<String,String>)map.get("scoreinfo");
	
	// �ʷ��Ƽ� 
	String recommend = (String)map.get("recommend");
	
	// �ײ���Ϣ
	List<PkgPO> pkgList = (List<PkgPO>)map.get("pkg");
	
	// ���շ�ҵ��
	List<Map> spbillList = (List<Map>)map.get("spbill");
	
	// �˱�������ϸ
	List<Map> inoutdetailList = (List<Map>)map.get("inoutdetail");
	
	// �˵�
	List totalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
	String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
	
	MenuInfoPO menuInfo = null;
	if (totalMenus != null && totalMenus.size() > 0)
	{
		for (int i = 0; i < totalMenus.size(); i++)
		{
			menuInfo = (MenuInfoPO) totalMenus.get(i);
			if (currMenuID.equals(menuInfo.getMenuid()))
			{
				break;
			}
		}
	}

    //add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
	//��������
	String textHref = (String) PublicCache.getInstance().getCachedData(Constants.MONTHBILL_TEXT_HREF);
	//add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
	
	// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
	
	String startdate = (String)request.getAttribute("startdate");
	String enddate = (String)request.getAttribute("enddate");
	
	String startdate_format = startdate.substring(0,4) + "��" + startdate.substring(4,6) + "��" + startdate.substring(6,8) + "��";
	String enddate_format = enddate.substring(0,4) + "��" + enddate.substring(4,6) + "��" + enddate.substring(6,8) + "��";
	
	// ��ǰ���
	String leftmoney0 = acctbalanceMap != null && acctbalanceMap.get("leftmoney0")!=null?(String)acctbalanceMap.get("leftmoney0"):"";
	
	// �����˻��������
	String leftmoney1 = acctbalanceMap != null && acctbalanceMap.get("leftmoney1")!=null?(String)acctbalanceMap.get("leftmoney1"):"";
	
	// Э������
	String leftmoney2 = acctbalanceMap != null && acctbalanceMap.get("leftmoney2")!=null?(String)acctbalanceMap.get("leftmoney2"):"";
	
	if (!"".equals(leftmoney0))
	{
        double dMoney = Double.parseDouble(leftmoney0) / 100;
        java.text.DecimalFormat nfMoney = new java.text.DecimalFormat("0.00");
        leftmoney0 = nfMoney.format(dMoney);
	}
	
	if (!"".equals(leftmoney1))
	{
        double dMoney = Double.parseDouble(leftmoney1) / 100;
        java.text.DecimalFormat nfMoney = new java.text.DecimalFormat("0.00");
        leftmoney1 = nfMoney.format(dMoney);
	}
	
	if (!"".equals(leftmoney0))
	{
        double dMoney = Double.parseDouble(leftmoney2) / 100;
        java.text.DecimalFormat nfMoney = new java.text.DecimalFormat("0.00");
        leftmoney2 = nfMoney.format(dMoney);
	}
	
	String currentDate = CommonUtil.dateToString(new Date(), "yyyy��MM��dd��");
	String currentDate1 = CommonUtil.dateToString(new Date(), "yyyyMMdd");
	
	// add begin hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
	// �˵���Ϣͨ������ϸ�ʷ��������ƴ����ײ����Ʊ�ʶ��0���ر� 1��������
	String feenameswitch = (String)PublicCache.getInstance().getCachedData(Constants.SH_BILLFEENAMESWITCH);
	// add end hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
		     		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e)
		{
			// ������ҳ���¼�ʱ
			resetGoHome();
			
			//���ռ�����
			var key = GetKeyCode(e);
		    //8:backspace 32:�ո� B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R �˳�
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return ;
			}
			// 0��
			else if (key == 48)
			{
				//topGo('qryBill', 'feeservice/feeServiceFunc.action');
				goback('<s:property value='curMenuId' />');
				return ;
			}
			// 1��
			else if (key == 49 || key == 80)
			{
				<%if("1".equals((String) PublicCache.getInstance().getCachedData("SH_PRINT_MONTHLIMT")))
				{
				%>
					var currentMonth = getCurrentTime1().substring(0,6);
					if('<s:property value="#request.month" />' == currentMonth)
					{
						alertError("�����˵�ֻ�ܲ�ѯ���ܴ�ӡ�������½⣡");
						return;
					}
				<%
				}
				%>
				openWindowloading('openWinLoading');
				return ;
			}
			// �Ϸ�
			else if (key == 85)
			{
				myScroll.moveUp(300);
				return;
			}
			// �·�
			else if (key == 68)
			{
				myScroll.moveDown(300);
				return;
			}
			// add begin g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 ������ʾ��ʹ�ý������̰����ر�
		}
		
		function goback(curmenu) 
		{
			//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action = "<%=basePath %>nxfeeservice/monthBill.action";
				document.actform.submit();
			}		
		}
		
		var printFlag = 0;
		function printInfo()
		{
			//�Ѿ�����˷��أ������ٽ��д�ӡ
			if (printFlag == 1)
			{
				alertError("�ѽ��д�ӡ�������ظ���ӡ��");
				return;
			}
			
			printFlag = 1;
			
			var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
			var Ret3 = DPsListPrinter1.PrintPicture(1);
		   	if (Ret3 == 1)
		   	{
		      	alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		      	return;
		   	}
		   	else if (Ret3 == 41)
		   	{
		      	alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		      	return;
		   	}
		   	
		   	var Ret4;
		   	Ret4 = DPsListPrinter1.PrintLine("    �й��ƶ�ͨ�ſͻ����˵�");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		Ret4 = DPsListPrinter1.PrintLine("    �ֻ�����: <%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%>");
     		Ret4 = DPsListPrinter1.PrintLine("    �û�����: <%=customerSimp.getCustomerName() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    Ʒ    ��: <%=request.getAttribute("brandnm") + " " + request.getAttribute("productnm") %>");
     		//Ret4 = DPsListPrinter1.PrintLine("    ��    ��: <s:property value="#request.startdate" />-<s:property value="#request.enddate" />");     		
     		Ret4 = DPsListPrinter1.PrintLine("    ��    ��: <s:property value="#request.startdate" />-<%=currentDate1 %>");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		
     		// ��ӡ�˵���Ϣ��ʼ
     		// ����ĩ��� add OR_NX_201301_222
     		<%
     		if (acctbalanceMap != null && scoreinfoMap != null && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ������ĩ��           ");
	     		Ret4 = DPsListPrinter1.PrintLine("       ��ǰ���:  <%=leftmoney0 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("       �����˻��������:  <%=leftmoney1 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("       Э������:  <%=leftmoney2 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
     		<%
     		}
     		%>
     		//  add OR_NX_201301_222
     		
     		// ������Ϣ 
     		<%
     		if (obj_billfixed != null && obj_billfixed.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ��������Ϣ��           ");
	     		Ret4 = DPsListPrinter1.PrintLine("    ��������Ŀ�������/Ԫ��");
				<%
				for(Object obj:obj_billfixed)
				{
					if (obj instanceof FeePO)
					{
						FeePO feePO = (FeePO)obj;
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=feePO.getName() %>:<%=feePO.getValue() %>");
				<%
					}
					if (obj instanceof FeeGroupPO)
					{
						FeeGroupPO feeGroupPO = (FeeGroupPO)obj;
						List<FeePO> subfee = feeGroupPO.getSubfee();
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=feeGroupPO.getName() %>");
				<%
						 for (FeePO feePO : subfee)
						 {
				%>
							Ret4 = DPsListPrinter1.PrintLine("        <%=feePO.getName() %>:<%=feePO.getValue() %>");
				<% 
						 }
					}
				}
				%>
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			
			// ������Ϣ
			<%
			if (scoreinfoMap != null && scoreinfoMap.size() > 0)
			{
			%>
					Ret4 = DPsListPrinter1.PrintLine("    ��������Ϣ��           ");
					Ret4 = DPsListPrinter1.PrintLine("    ���û���:<%=scoreinfoMap.get("thisavail") %>");;
					Ret4 = DPsListPrinter1.PrintLine("    �����ڿ���:<%=scoreinfoMap.get("lastavail") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����������:<%=scoreinfoMap.get("consume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڽ���:<%=scoreinfoMap.get("award") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ������ת��:<%=scoreinfoMap.get("transin") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڶһ�:<%=scoreinfoMap.get("exchange") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ������ת��:<%=scoreinfoMap.get("transout") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����������:<%=scoreinfoMap.get("clear") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			
			// ����ҵ��
			<%
			if (feedetailList_ != null && feedetailList_.size() > 0)
			{
			%>
				//Ret4 = DPsListPrinter1.PrintLine("    ���й��ƶ�����ҵ����á�");
				Ret4 = DPsListPrinter1.PrintLine("    ���й��ƶ�ҵ����á�");
				Ret4 = DPsListPrinter1.PrintLine("    ��������Ŀ�������/Ԫ��");
				<%
				for (FeedetailPO feedetailPO : feedetailList_)
				{
					if ("1".equals(feedetailPO.getBz()))
					{
				%>
						Ret4 = DPsListPrinter1.PrintLine("        <%=feedetailPO.getName() %>:<%=feedetailPO.getValue() %>");
				<%
					}
					else
					{
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=feedetailPO.getName() %>:<%=feedetailPO.getValue() %>");
				<%
					}
				}
				%>
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			
			// ���շ�ҵ��
			<% 
			if (spbillList != null && spbillList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    �����շ�ҵ��");
			<%
				for (Map obj : spbillList)
				{
			%>
					Ret4 = DPsListPrinter1.PrintLine("    ҵ��˿�:<%=obj.get("spcode") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ҵ������:<%=obj.get("servcode") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��ҵ����:<%=obj.get("spname") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ʹ�÷�ʽ:<%=obj.get("usetype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��������:<%=obj.get("feetype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ���:<%=obj.get("fee") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
				}
			}
			%>
			
			// ͨ������ϸ
			
			<%
			if (pkgList != null && pkgList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ��ͨ������ϸ��");
			<%
				// modify begin hWX5316476 2014-8-18 BUG_NX_201408_154_����_�������������ն�<�˵���ѯ>�˵��ײ�ʣ�������չ���޼�����λ��Bug
				// modify begin hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
				if("1".equals(feenameswitch))
				{
					for(PkgPO pkgPO : pkgList)
					{
						List<PrivPO> privList =  pkgPO.getPrivlist();
						for(PrivPO privPO : privList)
						{
			%>
							Ret4 = DPsListPrinter1.PrintLine("    �ײ��Ż�����:<%=privPO.getFeename() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��Ŀ:<%=privPO.getFreetype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��������:<%=privPO.getTotal() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��ʹ����:<%=privPO.getUsed() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ʣ����:<%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
						}
					}
				}
				else
				{
					for(PkgPO pkgPO : pkgList)
					{
						List<PrivPO> privList =  pkgPO.getPrivlist();
						for(PrivPO privPO : privList)
						{
			%>
							Ret4 = DPsListPrinter1.PrintLine("    �ײ��Ż�����:<%=pkgPO.getPkgname() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��Ŀ:<%=privPO.getFreetype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��������:<%=privPO.getTotal() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ��ʹ����:<%=privPO.getUsed() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ʣ����:<%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
						}
					}
				}
				// modify end hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
				// modify end hWX5316476 2014-8-18 BUG_NX_201408_154_����_�������������ն�<�˵���ѯ>�˵��ײ�ʣ�������չ���޼�����λ��Bug
			}
			%>
			
     		if (Ret4 == 1)
		   	{
		      	alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
		      	return;
		   	}
		   	
		   	Ret4 = DPsListPrinter1.PrintLine("  ���������˵���Ϣ�����˶�֮�ã������κ�ƾ֤��");
  	      	Ret4 = DPsListPrinter1.PrintLine("  �������ݣ����в���֮��������ӪҵԱ��ѯ��");
  	      	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  	      	Ret4 = DPsListPrinter1.PrintLine("  ��ӡ�ص㣺<%=termInfo.getOrgname() %>");
  	      	Ret4 = DPsListPrinter1.PrintLine("  ��ӡʱ��: " + getCurrentTime());
  	      	
  	      	//��ֽ
  			var Ret5 = DPsListPrinter1.SetCutPaper();
  			if (Ret5 == 1)
  			{
      			alertError("���ڴ�ӡ��ȱֽ��δ֪���ϣ������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      			return;
  			}
  			else if (Ret5 == 41)
  			{
     			alertError("���ڴ�ӡ���豸�ײ���������δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
      			return;
  			}
		}
		
		<%--
         * �����Ӻ���
         * ת��monthBillUrl.action
         * @remark create m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
        --%>
		function goURL()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }nxfeeservice/monthBillUrl.action";
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
		
		
		<!-- �ͻ���Ϣ -->
		
		<!-- �ͻ����� -->
		<input type="hidden" id="custname" name="custname" value="<s:property value="#request.custname" />"/>
		<!-- Ʒ�� -->
		<input type="hidden" id="brandnm" name="brandnm" value="<s:property value="#request.brandnm" />"/>
		<!-- �����Ʒ -->
		<input type="hidden" id="productnm" name="productnm" value="<s:property value="#request.productnm" />"/>
		<!-- �û�ID -->
		<input type="hidden" id="subsid" name="subsid" value="<s:property value="#request.subsid" />"/>
		
	    <!-- ������Ϣ -->
	    
		<!-- ���� -->
		<input type="hidden" id="cycle" name="cycle" value="<s:property value="#request.cycle" />"/>
		<!-- ��ʼʱ�� -->
		<input type="hidden" id="startdate" name="startdate" value="<s:property value="#request.startdate" />"/>
		<!-- ����ʱ�� -->
		<input type="hidden" id="enddate" name="enddate" value="<s:property value="#request.enddate" />"/>
		<!-- ���˺� -->
		<input type="hidden" id="acctid" name="acctid" value="<s:property value="#request.acctid" />"/>		
		<!-- �Ƿ�ϻ��û� -->
		<input type="hidden" id="unionacct" name="unionacct" value="<s:property value="#request.unionacct" />"/>		
		
			<input type="hidden" name="month" value="<s:property value="#request.month" />">
			<input type="hidden" name="billcycle" value=""> 
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
        		<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">�����ϼ�&nbsp;(��0��)</a>


          <div class="clear"></div>
		        <!--������-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- �б����� -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn" >
								
									<!-- �˵����� -->
										
										<!-- �ͻ���Ϣ  delete OR_NX_201301_222
										<br>
										<table class="tb_blue05" width="100%">
											<tr>
																<th colspan="2" class="tc">�й��ƶ�ͨ�ſͻ��˵�</th>
											</tr>
											<tr>
												<td class="noBorder" align="center"><Strong>�ͻ�����</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
											</tr>
											<tr>
												<td align="center"><Strong>�ֻ�����</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%></td>
											</tr>
											<tr>
												<td align="center"><Strong>Ʒ    ��</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
											</tr>
											<tr>
												<td align="center"><Strong>�Ʒ�����</Strong></td>
												<td class="tl"><%=startdate_format %>-<%=enddate_format %></td>
											</tr>
										</table>
										 -->
										<!-- add OR_NX_201301_222 -->
										<table class="tb_blue06" width="100%">
											<tr>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tc">�й��ƶ�ͨ�ſͻ��˵�</th>
															</tr>
															<tr>
																<td class="noBorder" align="center"><Strong>�ͻ�����</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>�ֻ�����</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%></td>
															</tr>
															<tr>
																<td align="center"><Strong>Ʒ    ��</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>�Ʒ�����</Strong></td>
																<td class="tl"><%=startdate_format %>��<%=currentDate %></td>
															</tr>
														</table>
												</td>
												<td class="noLRBorder">
												&nbsp;
												</td>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tl"><Strong>����ĩ���</Strong></th>
															</tr>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;��ǰ���</td>
																<td class="tr">
																	<%=leftmoney0 %>
																</td>
															</tr>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;�����˻��������</td>
																<td class="tr">
																	<%=leftmoney1 %>
																</td>
															</tr>
															
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;Э������</td>
																<td class="tr">
																	<%=leftmoney2 %>
																</td>
															<tr>
															
														</table>
												</td>
											</tr>
										</table>
										<!-- OR_NX_201301_222 -->
									<br>
									
									<table  class="tb_blue05" width="100%">
										<tr>
											 <td width="45%" class="noLRBorder">
														 
												<!-- ������Ϣ -->
												<table class="tb_blue04" width="100%">
													<tr>
														<th colspan="100%" class="tc" ><Strong>������Ϣ</Strong></th>
													</tr>
													<tr>
														<th class="tl">������Ϣ</th>
														<th class="tl">���/Ԫ</th>
													</tr>
													<% 
													if (obj_billfixed != null)
													{
													%>
														<%
														for(Object obj:obj_billfixed)
														{
														
															if (obj instanceof FeePO)
															{
																FeePO feePO = (FeePO)obj;
														%>
														<tr>
															<td class="tl"><Strong><%=feePO.getName() %></Strong></td>
															<td class="tr"><%=feePO.getValue() %></td>
														</tr>
														<%
															}
															
															if (obj instanceof FeeGroupPO)
															{
																FeeGroupPO feeGroupPO = (FeeGroupPO)obj;
																List<FeePO> subfee = feeGroupPO.getSubfee();
														%>
														<tr>
															<td class="tl"><Strong><%=feeGroupPO.getName() %></Strong></td>
															<td class="tl">&nbsp;</td>
														</tr>
														<%
																 for (FeePO feePO : subfee)
																 {
														%>
																	<tr>
																	<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;<%=feePO.getName() %></td>
																	<td class="tr"><%=feePO.getValue() %></td>
																	</tr>
														<% 
																 }
														%>
														
														<%	
															}
														}
														%>
													<% 
													}
													else
													{
													%>
														<tr><td colspan="2">������</td></tr>
													<%
													} 
													%>
												</table>
												<br>
											 
											 </td>
											 <td class="noLRBorder">
											 
											    <!-- ��������ͼ -->
											 	<%
											 	if (map.get("billhalfyeartrend") != null && ((List<BillPO>)map.get("billhalfyeartrend")).size() > 0)
											 	{
											 	%>
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }nxfeeservice/billfixedBarChart_new.action"></iframe>
												<%
												}
												%>
											 
											 	<br><br>
											 	
												<!-- ���·��ýṹͼ -->
												<%
												if (map.get("billfixed") != null)
												{
												%>
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }nxfeeservice/billfixedPieChart_new.action"></iframe>
												<%
												}
												%>
															
											 </td>
										</tr>
									</table>
									<br>
									
									<!-- �ʷ��Ƽ� -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20"><Strong>�ʷ��Ƽ�</Strong></th>
										</tr>
										<tr>
											<td class="tl">
												<%=recommend == null ?"������" : recommend%>
											</td>
										</tr>
									</table>
									<br>
									
									<!-- ������Ϣ -->
									<% 
									if (scoreinfoMap != null && scoreinfoMap.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20"><Strong>������Ϣ</Strong></th>
											</tr>
											<tr>
												<th>���û���</th>
												<th>=</th>
												<th>���ڿ���</th>
												<th>+</th>
												<th>��������</th>
												<th>+</th>
												<th>���ڽ���</th>
												<th>+</th>
												<th>����ת��</th>
												<th>-</th>
												<th>���ڶһ�</th>
												<th>-</th>
												<th>����ת��</th>
												<th>-</th>
												<th>��������</th>
											</tr>
											<tr>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("thisavail") != null ? scoreinfoMap.get("thisavail") : "" %></td>
												<td>=</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("lastavail") != null ? scoreinfoMap.get("lastavail") : "" %></td>
												<td>+</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("consume") != null ? scoreinfoMap.get("consume") : "" %></td>
												<td>+</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("award") != null ? scoreinfoMap.get("award") : "" %></td>
												<td>+</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("transin") != null ? scoreinfoMap.get("transin") : "" %></td>
												<td>-</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("exchange") != null ? scoreinfoMap.get("exchange") : "" %></td>
												<td>-</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("transout") != null ? scoreinfoMap.get("transout") : "" %></td>
												<td>-</td>
												<td><%=scoreinfoMap != null && scoreinfoMap.get("clear") != null ? scoreinfoMap.get("clear") : "" %></td>
											</tr>
										</table>
										<br>
									<% 
									}
									%>
									
									<!-- ��ע -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th>��ע</th>
										</tr>
										<tr>
											<td colspan="20" class="tl">��ע���� 1 �������������ֽ��������屾�·��ú����Ч�� 2 �������̳� http://jf.10086.cn ��л��ʹ���й��ƶ��ͻ��˵��������κ����ʣ�����ĸ�¼�˻������á�ͨ������ϸ��Ϣ�����¼ www.10086.cn ���������ģ����ǽ��߳�Ϊ������
											    <%--add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179 --%>
											    <%-- ���������֣��������ݿ����ã������ת�����Ի���������ҳ�� --%>
											    <br>
											    <a href="#" title="��ӭ���ʸ��Ի�����ҳ�棡" onclick="goURL()" style="color:yellow; font-size:18px;" onmouseover="this.style.color = 'red';" onmouseout= "this.style.color = 'yellow';" >
											    <%=textHref %></a>
											    <%--add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179 --%>
											</td>
										</tr>
									</table>
									<br>
									
									<!-- ��¼ -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th><div class="fs18" style="display: inline;">��¼</div>�����á�ͨ�������˻���ϸ</th>
										</tr>
									</table>
									<br>
									
									<!-- ����ҵ�� -->
									<table class="tb_blue05" width="100%">
										<tr>
											<!-- 
											<th colspan="20" class="tl"><Strong>�й��ƶ�����ҵ��</Strong></th>
											 -->
											 <th colspan="20" class="tl"><Strong>�й��ƶ�ҵ�����</Strong></th>
										</tr>
										<tr>
											<th class="tc">��������</th>
											<th class="tc">���/Ԫ</th>
											<th class="tc">��������</th>
											<th class="tc">���/Ԫ</th>
										</tr>
										<% 
										if (feedetailList != null && feedetailList.size() > 0)
										{
										%>
											<%
											for (FeedetailPO feedetailPO : feedetailList)
											{
											%>
											<tr>
												<%if ("0".equals(feedetailPO.getBz1())){%>
												<td class="tl"><Strong><%=feedetailPO.getName1() %></Strong></td>
												<%}else{%>
												<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;<%=feedetailPO.getName1() %></td>
												<%}%>
												<td class="tc"><%=feedetailPO.getValue1() %></td>
												
												<%if ("0".equals(feedetailPO.getBz2())){%>
												<td class="tl"><Strong><%=feedetailPO.getName2() %></Strong></td>
												<%}else{%>
												<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;<%=feedetailPO.getName2() %></td>
												<%}%>
												<td class="tc"><%=feedetailPO.getValue2() %></td>
											</tr>
											<%
											}
											%>
										<% 
										}
										else
										{
										%>
											<tr><td colspan="4">������</td></tr>
										<% 
										}
										%>
									</table>
									<br>
									
									
									<!-- ���շ�ҵ�� -->
									<% 
									if (spbillList != null && spbillList.size() > 0)
									{
									%>
									<table class="tb_blue05" width="100%">
											<tr>
											<th colspan="20" class="tl"><Strong>���շ�ҵ��</Strong></th>
											</tr>
											<tr>
											<th class="tc">ҵ��˿�</th>
											<th class="tc">ҵ������</th>
											<th class="tc">��ҵ����</th>
											<th class="tc">ʹ�÷�ʽ</th>
											<th class="tc">��������</th>
											<th class="tc">���</th>
												
											</tr>
											<% 
											for (Map obj : spbillList)
											{
											%>
												<tr>
													<td class="tc"><%=obj.get("spcode") %></td>
													<td class="tc"><%=obj.get("servcode") %></td>
													<td class="tc"><%=obj.get("spname") %></td>
													<td class="tc"><%=obj.get("usetype") %></td>
													<td class="tc"><%=obj.get("feetype") %></td>
													<td class="tc"><%=obj.get("fee") %></td>
												</tr>
											<%
											}
											%>
										</table>
										<br>
									<% 
									}
									%>
									
									<!-- ͨ����ʹ����ϸ -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20" class="tl"><Strong>ͨ������ϸ</Strong></th>
										</tr>
										<tr>
											<th class="tc">�ײ��Ż�����</th>
											<th class="tc">��Ŀ</th>
											<th class="tc">��������</th>
											<th class="tc">��ʹ����</th>
											<th class="tc">ʣ����</th>
										</tr>
										<%
										if (pkgList != null && pkgList.size() > 0)
										{
											// modify begin hWX5316476 2014-8-18 BUG_NX_201408_154_����_�������������ն�<�˵���ѯ>�˵��ײ�ʣ�������չ���޼�����λ��Bug
											// modify begin hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
											if("1".equals(feenameswitch))
											{
												for(PkgPO pkgPO : pkgList)
												{
													List<PrivPO> privList =  pkgPO.getPrivlist();
													for(PrivPO privPO : privList)
													{
										%>
														<tr>
															<td class="tc"><%=privPO.getFeename() %></td>
															<td class="tc"><%=privPO.getFreetype() %></td>
															<td class="tc"><%=privPO.getTotal() %><%=privPO.getAttrtype() %></td>
															<td class="tc"><%=privPO.getUsed() %><%=privPO.getAttrtype() %></td>
															<td class="tc"><%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %></td>
														</tr>
										<% 
													}
												}
											}
											else
											{
												for(PkgPO pkgPO : pkgList)
												{
													List<PrivPO> privList =  pkgPO.getPrivlist();
													for(PrivPO privPO : privList)
													{
										%>
														<tr>
															<td class="tc"><%=pkgPO.getPkgname() %></td>
															<td class="tc"><%=privPO.getFreetype() %></td>
															<td class="tc"><%=privPO.getTotal() %><%=privPO.getAttrtype() %></td>
															<td class="tc"><%=privPO.getUsed() %><%=privPO.getAttrtype() %></td>
															<td class="tc"><%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %></td>
														</tr>
										<% 
													}
												}
											}
											// modify end hWX5316476 2014-03-26 OR_NX_201403_1590_����_[�����ն�����]�˵���ѯ��������
											// modify end hWX5316476 2014-8-18 BUG_NX_201408_154_����_�������������ն�<�˵���ѯ>�˵��ײ�ʣ�������չ���޼�����λ��Bug
										}
										else
										{
										%>
												<tr><td colspan="3">������</td></tr>
										<% 
										}
										%>
										
										
									</table>
									<br>
								</div>
								<!-- �б����� -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(300);resetGoHome();" />
							<div class="div75w350h" onclick="resetGoHome();">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(300);resetGoHome();"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
                <div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_82_1" href="javascript:void(0)" onclick="openWindow('openWin1')" >��ӡ<br/>(��1��)</a>
				</div>
                <div class=" clear"></div>
                
               
                
                <!-- ��ӡ�˵���ʾ�� -->
                <div class="openwin_tip div708w392h" id="openWin1">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">��ȷ��Ҫ��ӡ��ǰ�˵���</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading');">ȷ��</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
                    </div>
                </div>
                
                <div class="openwin_tip div708w192h" id="openWinLoading">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  alt="��ӡ��..."  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">�˵����ڴ�ӡ�����Ժ�...</p>
                      </div>
                    </div>
                </div>
                
                
                <script type="text/javascript">
                
				openWindow = function(id)
				{
					<%if("1".equals((String) PublicCache.getInstance().getCachedData("SH_PRINT_MONTHLIMT")))
					{
					%>
						var currentMonth = getCurrentTime1().substring(0,6);
						if('<s:property value="#request.month" />' == currentMonth)
						{
							alertError("�����˵�ֻ�ܲ�ѯ���ܴ�ӡ�������½⣡");
							return;
						}
					<%
					}
					%>
					wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������
				}
				
				openWindowloading = function(id)
				{
					//wiWindow = new OpenWindow("openWinLoading",708,192);//�򿪵�����������
					printInfo();
				}
				
				function btnClick(btn,btClass)
				{
					  var btns=document.getElementById('btns').getElementsByTagName('a');
					  for(i=0;i<btns.length;i++)
					  {
						  btns[i].className=btClass;
					  }
					  btn.className=btClass+'on';
				}
				
				</script>
                <!--����������-->
				
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
