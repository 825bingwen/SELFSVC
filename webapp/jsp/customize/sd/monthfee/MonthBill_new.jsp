<%@ page contentType="text/html; charset=GBK"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.math.BigDecimal,java.text.*,java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@page import="java.util.Vector"%>

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
	
	// ���úϼ�
	String parseBillfixed_hj = (String)map.get("parseBillfixed_hj");
	
	// �˻���Ҫ��Ϣ
	Map<String,String> acctbalanceMap = (Map<String,String>)map.get("acctbalance");
	
	// ������Ϣ
	Map<String,String> scoreinfoMap = (Map<String,String>)map.get("scoreinfo");
	
	// �ʷ��Ƽ� 
	String recommend = (String)map.get("recommend");
	
	// ���ֱ�ע
	String scoreremark = (String)map.get("scoreremark");
	if(CommonUtil.isEmpty(scoreremark))
	{
		scoreremark = "";
	}
	
	// ��л�ﱸע
	String acknowledgement = (String)map.get("acknowledgement");
	if(CommonUtil.isEmpty(acknowledgement))
	{
		acknowledgement = "";
	}
	
	// ����ҵ����Ϣ(˫��)
	List<FeedetailPO> feedetailList = (List<FeedetailPO>)map.get("feedetails");

	// ����ҵ����Ϣ(����)
	List<FeedetailPO> feedetailList_ = (List<FeedetailPO>)map.get("feedetails_");
		
	// ���շ�ҵ��
	List<Map> spList = (List<Map>)map.get("spbill");
	
	// �ײ���Ϣ
	List<PkgPO> pkgList = (List<PkgPO>)map.get("pkg");
	
	// ������Ϣ
	List<PkgPO> obj_total = (List<PkgPO>)map.get("total");
	
	// �˱�������ϸ
	List<Map> inoutdetailList = (List<Map>)map.get("inoutdetail");
	
	// Э�����Ϣ
    List<Map> agreementinfoList = (List<Map>)map.get("agreementinfo");
    
	// ���Ϳ���Ϣ
    List<Map> presentinfoList = (List<Map>)map.get("presentinfo");
    
    // ���˴�����Ϣ
    List<Map> payedbyotherList = (List<Map>)map.get("payedbyother");
    
    // �����˸���Ϣ
	List<Map> payedforotherList = (List<Map>)map.get("payedforother");
	
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
	
	String startdate = (String)request.getAttribute("startdate");
	String enddate = (String)request.getAttribute("enddate");
	String startdateStr = startdate.substring(0,4) + "��" + startdate.substring(4,6) + "��" + startdate.substring(6,8) + "��";
	String enddateStr = enddate.substring(0,4) + "��" + enddate.substring(4,6) + "��" + enddate.substring(6,8) + "��";
	
	String curdate = (String)request.getAttribute("curdate");

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
		document.onkeydown = keyDown;
		
		function keyDown(e)
		{
			//8��32��66��77 ����
		    //82��220 ����
		    //13��89��221 ȷ��
		    //80 ��ӡ
		    //85 ��һҳ
		    //68 ��һҳ
		    
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
			
			//80:��ӡ
			if (key == 80)
			{
				openWindow('openWin1');
			}
		}
		
		function goback(curmenu) 
		{
			//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.target = "_self";
				document.actform.action = "<%=basePath %>monthFee/currMonthFee.action";
				document.actform.submit();
			}		
		}
		
		function printInfo()
		{
			//�Ѿ�����˷��أ������ٽ��д�ӡ
			if (submitFlag == 1)
			{
				return;
			}
			wiWindow.close();
			
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
		   	Ret4 = DPsListPrinter1.PrintLine("    �й��ƶ�ͨ��    �ͻ��˵�");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		Ret4 = DPsListPrinter1.PrintLine("    �ֻ�����: <%=customerSimp.getServNumber() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    �û�����: <%=customerSimp.getCustomerName() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    ��ǰƷ��: <%=request.getAttribute("brandnm") + " " + request.getAttribute("productnm") %>");
     		// add by lKF60882 2016-10-10 OR_SD_201604_913_ɽ��_�����ڸ��˵���ѯ���ܽ����������Ǽ�չʾ������
     		if("" != "<%=request.getAttribute("subsCreditName") %>")
     		{
	     	Ret4 = DPsListPrinter1.PrintLine("    �ͻ��Ǽ�: <%=request.getAttribute("subsCreditName") %>");
     		}
     		Ret4 = DPsListPrinter1.PrintLine("    ��    ��: <%=startdateStr %>-<%=enddateStr %>");     		
     		Ret4 = DPsListPrinter1.PrintLine("    ��ѯʱ��: <%=curdate%>");     		
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		
     		// ��ӡ�˵���Ϣ��ʼ
     		
     		// Change by hWX5316476 OR_SD_201308_1167 begin
     		// ����ĩ���
     		<%
     		if (acctbalanceMap != null && scoreinfoMap != null && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ������ĩ��           ");
	     		<%
     			if (acctbalanceMap.get("thisval") != null &&  !"0.00".equals(acctbalanceMap.get("thisval")))
     			{
     			%>
	     		Ret4 = DPsListPrinter1.PrintLine("       �ֽ����:  <%=acctbalanceMap.get("thisval") %>");
	     		<%
	     		}
	     		%>
	     		<%
	     		if (acctbalanceMap != null && acctbalanceMap.get("contractval") != null && !"0.00".equals(acctbalanceMap.get("contractval")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       Э������:  <%=acctbalanceMap.get("contractval") %>");
	     		<%
	     		}
	     		%>
	     		
	     		<%
	     		if (acctbalanceMap != null && acctbalanceMap.get("presentval") != null && !"0.00".equals(acctbalanceMap.get("presentval")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       �������:  <%=acctbalanceMap.get("presentval") %>");
	     		<%
	     		}
	     		%>
	     		
	     		<%
	     		if (scoreinfoMap != null && scoreinfoMap.get("thisavail") != null && !"0".equals(scoreinfoMap.get("thisavail")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       ���û���:  <%=scoreinfoMap.get("thisavail") %>");
	     		<%
	     		}
	     		%>
	     		<%
	     		if(parseBillfixed_hj != null ){
	     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ���·��úϼ�:  <%=parseBillfixed_hj %>");
	     		<%
	     		}
	     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
     		<%
     		}
     		%>
     		// Change by hWX5316476 OR_SD_201308_1167 end
     		
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
			
			// Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin
			// �˻���Ϣ
			<%
			if (acctbalanceMap != null && acctbalanceMap.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ���˻���Ϣ��           ");
				Ret4 = DPsListPrinter1.PrintLine("    ����ĩ����:<%=acctbalanceMap.get("lastval") != null ? acctbalanceMap.get("lastval") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    �����³�ֵ:<%=acctbalanceMap.get("income") != null ? acctbalanceMap.get("income") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ��ʹ��Э���:<%=acctbalanceMap.get("contractused") != null ? acctbalanceMap.get("contractused") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ���˷�:<%=acctbalanceMap.get("backfee") != null ? acctbalanceMap.get("backfee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ������ת��:<%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    �����ºϼƿ���:<%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %>");
				
				Ret4 = DPsListPrinter1.PrintLine("    ���ºϼƿ���:<%=acctbalanceMap.get("totalcanuse")  != null ? acctbalanceMap.get("totalcanuse") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    �����·��úϼ�:<%=acctbalanceMap.get("totalfee") !=null ? acctbalanceMap.get("totalfee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    �������˸���:<%=acctbalanceMap.get("payotherfee") != null ? acctbalanceMap.get("payotherfee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    ���ϻ��������:<%=acctbalanceMap.get("othersubsfee") != null ? acctbalanceMap.get("othersubsfee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ��ΥԼ��:<%=acctbalanceMap.get("latefee") != null ? acctbalanceMap.get("latefee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ������ת��:<%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    ����ĩ���:<%=acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : ""%>");
				
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			// Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end

			// ������Ϣ
			<%
			if (scoreinfoMap != null)
			{
				if (
					!("0".equals(scoreinfoMap.get("thisavail"))
					&& "0".equals(scoreinfoMap.get("lastavail"))
					&& "0".equals(scoreinfoMap.get("consume"))
					&& "0".equals(scoreinfoMap.get("award"))
					&& "0".equals(scoreinfoMap.get("transin"))
					&& "0".equals(scoreinfoMap.get("exchange"))
					&& "0".equals(scoreinfoMap.get("transout"))
					&& "0".equals(scoreinfoMap.get("clear")))
					)
				{
			%>
					Ret4 = DPsListPrinter1.PrintLine("    ��������Ϣ��           ");
					Ret4 = DPsListPrinter1.PrintLine("    ���û���:<%=scoreinfoMap.get("thisavail") %>");;
					Ret4 = DPsListPrinter1.PrintLine("    �����ڿ���:<%=scoreinfoMap.get("lastavail") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��������������:<%=scoreinfoMap.get("consume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڽ�������:<%=scoreinfoMap.get("award") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڿ���ת��:<%=scoreinfoMap.get("transin") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڶһ�:<%=scoreinfoMap.get("exchange") %>");
					Ret4 = DPsListPrinter1.PrintLine("    �����ڿ���ת��:<%=scoreinfoMap.get("transout") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����������:<%=scoreinfoMap.get("clear") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
				}
			}
			%>
			
												
			// ����ҵ��
			<%
			if (feedetailList_ != null && feedetailList_.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ���й��ƶ�����ҵ����á�");
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
			if (spList != null && spList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    �����շ�ҵ��");
				<%
				for (Map obj : spList)
				{
				%>
				Ret4 = DPsListPrinter1.PrintLine("    �������:<%=obj.get("spcode") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ҵ������:<%=obj.get("spname") %>");
				Ret4 = DPsListPrinter1.PrintLine("    �����ṩ��:<%=obj.get("servcode") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ʹ�÷�ʽ:<%=obj.get("usetype") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ��������:<%=obj.get("feetype") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ���:<%=obj.get("fee") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			// ͨ����ʹ����ϸ_�ײͰ���ͨ����
			<%
			if (pkgList != null && pkgList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ���ײͰ���ͨ������");
			    <%
				for(PkgPO pkgPO : pkgList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    >><%=pkgPO.getPkgname() %>:");
				<% 
					List<PrivPO> privlist = pkgPO.getPrivlist();
					for(PrivPO privPO : privlist)
					{
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=privPO.getFreetype() %>:<%=privPO.getTotal() %><%=privPO.getAttrtypename() %>");
				<%
					}
				%>
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			// ͨ����ʹ����ϸ_ʵ��ʹ��ͨ����
			<%-- modify begin sWX219697 2015-7-15 14:18:14 --%>
			<%
			if (obj_total != null && obj_total.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ���ײ���ʵ��ʹ��ͨ������");	
				Ret4 = DPsListPrinter1.PrintLine("    >>ͨ��������");	
			    <%
				for(PkgPO pkgPO : obj_total)
				{
					List<PrivPO> privlist = pkgPO.getPrivlist();
					for(PrivPO privPO : privlist)
					{
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=privPO.getFreetype() %>:<%=privPO.getUsed() %><%=privPO.getAttrtypename() %>");
				<%
					}
				%>
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			<%-- �ײ���ʵ��ʹ��ͨ������ϸ --%>
			<%
			if (pkgList != null && pkgList.size() > 0)
			{
			%>
			    <%
				for(PkgPO pkgPO : pkgList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    >><%=pkgPO.getPkgname() %>:");
				<% 
					List<PrivPO> privlist = pkgPO.getPrivlist();
					for(PrivPO privPO : privlist)
					{
				%>
						Ret4 = DPsListPrinter1.PrintLine("    <%=privPO.getFreetype() %>:<%=privPO.getUsed()%><%=privPO.getAttrtypename() %>");
				<%
					}
				%>
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			<%--modify end sWX219697 2015-7-15 14:18:14 --%>
			
			//  Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin 
			// �˱�������ϸ
			<%
			if (inoutdetailList != null && inoutdetailList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    ���˻�������ϸ��");		
				<%
				for (Map inoutdetailMap : inoutdetailList)
				{
				%>	
				Ret4 = DPsListPrinter1.PrintLine("    ʱ��:<%=inoutdetailMap.get("date") != null ? inoutdetailMap.get("date") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ��ʽ:<%=inoutdetailMap.get("model") != null ? inoutdetailMap.get("model") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ���:<%=inoutdetailMap.get("type") != null ? inoutdetailMap.get("type") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ���:<%=inoutdetailMap.get("fee") != null ?  inoutdetailMap.get("fee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    ��ע:<%=inoutdetailMap.get("desc") != null ?  inoutdetailMap.get("desc") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
            // Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end 
			<%
			if (agreementinfoList != null && agreementinfoList.size() > 0)
			{
			%>
				// Э�����Ϣ
				Ret4 = DPsListPrinter1.PrintLine("    ��Э�����Ϣ��");
				<%
				for (Map obj : agreementinfoList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    Э�������:<%=obj.get("name") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ʹ�ú���:<%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����ĩ���:<%=obj.get("lastmonthleft") %>");
					Ret4 = DPsListPrinter1.PrintLine("    +��������:<%=obj.get("curmonthpay") %>");
					Ret4 = DPsListPrinter1.PrintLine("    -���¿۳�:<%=obj.get("curmonthdeduct") %>");
					Ret4 = DPsListPrinter1.PrintLine("    =��ĩ���:<%=obj.get("monthclosing") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��Ч��:<%=obj.get("efftime") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ÿ��������Ѷ��:<%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����ʵ��ʹ��:<%=obj.get("curmonthused") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��ע:<%=obj.get("remark") %>");	
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			<%
			if (presentinfoList != null && presentinfoList.size() > 0)
			{
			%>
				// ���Ϳ���Ϣ
				Ret4 = DPsListPrinter1.PrintLine("    ��������Ϣ��");
				<%
				for (Map obj : presentinfoList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    Э�������:<%=obj.get("name") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ʹ�ú���:<%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����ĩ���:<%=obj.get("lastmonthleft") %>");
					Ret4 = DPsListPrinter1.PrintLine("    +��������:<%=obj.get("curmonthpay") %>");
					Ret4 = DPsListPrinter1.PrintLine("    -���¿۳�:<%=obj.get("curmonthdeduct") %>");
					Ret4 = DPsListPrinter1.PrintLine("    =��ĩ���:<%=obj.get("monthclosing") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��Ч��:<%=obj.get("efftime") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ÿ��������Ѷ��:<%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume")  %>");
					Ret4 = DPsListPrinter1.PrintLine("    ����ʵ��ʹ��:<%=obj.get("curmonthused") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ��ע: <%=obj.get("remark") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			

			// ���˴�����Ϣ
			<%
			if (payedbyotherList != null && payedbyotherList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    �����˴�����Ϣ��");
				<%
				for (Map payedbyother : payedbyotherList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    ��������:<%=payedbyother.get("paytelnum") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ���ѷ�Χ:<%=payedbyother.get("paytype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ���´���:<%=payedbyother.get("fee") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			// �����˸���Ϣ
			<%
			if (payedforotherList != null && payedforotherList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    �������˸�����Ϣ��");
				Ret4 = DPsListPrinter1.PrintLine("    ��������");
				Ret4 = DPsListPrinter1.PrintLine("    ���ѷ�Χ");
				Ret4 = DPsListPrinter1.PrintLine("    ���´���");
				<%
				for (Map payedforother : payedforotherList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    <%=payedforother.get("payedtelnum") %>");
					Ret4 = DPsListPrinter1.PrintLine("    <%=payedforother.get("paytype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    <%=payedforother.get("fee") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
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
		
		function billDetail(billcycle,month)
		{
			//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.getElementById("month").value = month;
				document.getElementById("billcycle").value = billcycle;
				
				document.actform.target = "_self";
				document.actform.action = "<%=basePath %>monthFee/monthFee.action";
				document.actform.submit();
			}
		}
		
		// -------------------------------------------------�������˵�������(�û����俪ͨ���δ֪)----------------------------------

		// �������˵�������(�û����俪ͨ���δ֪)
		function sendmail(needCheckMail)
		{
			var postStr = "";
			postStr = "needCheckMail=" + needCheckMail;
			postStr = postStr + "&telnum=<%=customerSimp.getServNumber() %>";
			postStr = postStr + "&custname=" + encodeURIComponent(encodeURIComponent(document.getElementById('custname').value));
			postStr = postStr + "&brandnm=" + encodeURIComponent(encodeURIComponent(document.getElementById('brandnm').value));
			postStr = postStr + "&productnm=" + encodeURIComponent(encodeURIComponent(document.getElementById('productnm').value));
			postStr = postStr + "&subsid=" + document.getElementById('subsid').value;
			postStr = postStr + "&cycle=" + document.getElementById('cycle').value;
			postStr = postStr + "&startdate=" + document.getElementById('startdate').value;
			postStr = postStr + "&enddate=" + document.getElementById('enddate').value;
			postStr = postStr + "&acctid=" + document.getElementById('acctid').value;
			postStr = postStr + "&unionacct=" + document.getElementById('unionacct').value;
			postStr = postStr + "&realbz=history";// ��ʷ�˵�
		//	postStr = postStr + "&curMenuId="+document.getElementById("curMenuId").value;//��ǰ�˵�id�� add by sWX219697 2014-04-30 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ�
			
			var url = "<%=basePath %>monthFee/sendmail_new.action?"+postStr;
			var loader = new net.ContentLoader(url, callBack, null, "POST", "", "application/x-www-form-urlencoded");     
		}   
  
		//<!--�ص��ķ���-->   
		function callBack()
		{
			var msgWelcome = this.req.responseText;
		    if(msgWelcome == 1)
        	{
        		successedSendWindow('successedSend');
        	}
        	if(msgWelcome == 2)
        	{
	       		openMailConfirmWindow('openMailConfirm');
        	}
		}
		
		
		
		var bill ;
		var mon ;
		
		// Ϊ�û���ͨ���139����
		function provideMailService(billcycle,month)
		{
		bill = billcycle;
		mon = month;
		//����ajax���� xmlHttpRequest            
		XmlHttpRequest = false;      
		//������Ҫ����һ��XMLHttpRequest����,�������з���������,��cf ��ͬ���������������ͬ      
		if (window.XMLHttpRequest)      
		{ // Mozilla, Safari,...      
		    XmlHttpRequest = new XMLHttpRequest();      
		    if (XmlHttpRequest.overrideMimeType)      
		    {      
		        XmlHttpRequest.overrideMimeType('text/xml');      
		    }      
		}      
		else if (window.ActiveXObject)      
		{ // IE      
		    try             
		    {      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		    }      
		    catch (e)      
		    {      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		    }      
		}      
		     
		if (!XmlHttpRequest) 
		{      
		    alert('���ִ���,���ܽ���һ��XMLHTTPʵ��!');      
		    return false;      
		}    
		
		var curMenuId = document.getElementById("curMenuId").value;
		var postStr ="curMenuId="+curMenuId+"&telnum=<%=customerSimp.getServNumber() %>";   
		
		XmlHttpRequest.onreadystatechange=PromptCallBack;//���ûص���js����  ����˵�������� ��������Ӧ�� ����ִ�е�js����   callBack�Ǻ�����   
		XmlHttpRequest.open("POST","<%=basePath %>monthFee/provideMailService.action",true);//��һ���������������ͣ�GET/POST�� �ڶ������������·�� �����дһ��servlet��ַ    
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
		XmlHttpRequest.send(postStr);//��ʼ�����������xmlHttpRequest   
		}   
  
		//<!--�ص��ķ���-->   
		function PromptCallBack()
		{
		       
		    if(XmlHttpRequest.readyState==4)   
		    {   
		        if(XmlHttpRequest.status==200)//���������ж� ȷ��ajax�����ѱ��ɹ���Ӧ   
		        {   
		        	var msgWelcome=XmlHttpRequest.responseText;
		        	if(msgWelcome == 0)
		        	{
		        		failToOpenMail('failToOpenMail');
		        	}
		        	if(msgWelcome == 1)
		        	{
			       		openMailPromptWindow('openMailPrompt');
			       		
			       		// ��ͨ����������Ҫ�����˵���Ϣ������
			       		sendmail(0,bill,mon);
		        	}
		        }
		    }
		}
		
		// �ʼ����ͳɹ���ʾ��
		failToOpenMail = function(id)
		{
			wiWindow = new OpenWindow("failToOpenMail",708,392);
		}
		
		// �ʼ����ͳɹ���ʾ��
		successedSendWindow = function(id)
		{
			wiWindow = new OpenWindow("successedSend",708,392);
		}
		
		// ȷ���û��Ƿ�ͨ139����
		openMailConfirmWindow = function(id)
		{
			wiWindow = new OpenWindow("openMailConfirm",708,392);
		}
		
		// ��ʾ�û��ѿ�ͨ139���䲢�Ժ����˵���Ϣ
		openMailPromptWindow = function(id)
		{
			wiWindow = new OpenWindow("openMailPrompt",708,392);
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
		<!-- �ͻ��Ǽ� -->
		<input type="hidden" id="subsCreditName" name="subsCreditName" value="<s:property value="#request.subsCreditName" />"/>
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
		
			<input type="hidden" name="month" value="">
			<input type="hidden" name="billcycle" value=""> 
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����˵��굥��ѯ</a>


          <div class="clear"></div>
		        <!--������-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- �б����� -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName%></p>
								<div class="ptop180 tc p747w411h" id="inn" >
								
									<!-- �˵����� -->
									
										<!-- �ͻ���Ϣ -->
										<br>
										<table class="tb_blue06" width="100%">
											<tr>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tc">�й��ƶ�ͨ��&nbsp;&nbsp;�ͻ��˵�</th>
															</tr>
															<tr>
																<td class="noBorder" align="center"><Strong>�ͻ�����</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>�ֻ�����</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber() %></td>
															</tr>
															<tr>
																<td align="center"><Strong>��ǰƷ��</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
															</tr>
															<%--add by lKF60882 2016-10-10 OR_SD_201604_913_ɽ��_�����ڸ��˵���ѯ���ܽ����������Ǽ�չʾ������--%>
															<s:if test='%{!"".equals(#request.subsCreditName)}'>
																<tr>
																	<td align="center"><Strong>�ͻ��Ǽ�</Strong></td>
																	<td class="tl"><s:property value="#request.subsCreditName" /></td>
																</tr>
															</s:if>
															<tr>
																<td align="center"><Strong>�Ʒ�����</Strong></td>
																<td class="tl"><%=startdateStr %>-<%=enddateStr %></td>
															</tr>
															<tr>
																<td align="center"><Strong>��ѯʱ��</Strong></td>
																<td class="tl"><s:property value="curdate" /></td>	
															<tr>
														</table>
												</td>
												<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin --%>
												<%
									     		if (null != acctbalanceMap && null != scoreinfoMap && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
									     		{
									     		%>
												<td class="noLRBorder">
												&nbsp;
												</td>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tl"><Strong>����ĩ���</Strong></th>
															</tr>
															<% 
															if (acctbalanceMap.get("thisval") != null && !"0.00".equals(acctbalanceMap.get("thisval")))
															{
															%>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;�ֽ����</td>
																<td class="tr">
																	<%=acctbalanceMap.get("thisval") %>
																</td>
															</tr>
															<%
															}
															 %>
															<% 
															if (acctbalanceMap.get("contractval") != null && !"0.00".equals(acctbalanceMap.get("contractval")))
															{
															%>
																<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;Э������</td>
																<td class="tr">
																		<%=acctbalanceMap.get("contractval") %>
																	</td>
																</tr>
															<% 
															}
															%>
															
															<% 
															if (acctbalanceMap.get("presentval") != null && !"0.00".equals(acctbalanceMap.get("presentval")))
															{
															%>
																<tr>
																	<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;�������</td>
																<td class="tr">
																		<%=acctbalanceMap.get("presentval") %>
																	</td>
																</tr>
															<% 
															}
															%>
															
															<% 
															if (scoreinfoMap.get("thisavail") != null && !"0".equals(scoreinfoMap.get("thisavail")))
															{
															%>
																<tr>
																	<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;���û���</td>
																<td class="tr">
																		<%=scoreinfoMap.get("thisavail") %>
																	</td>
																</tr>
															<% 
															}
															%>
															<% 
															if (parseBillfixed_hj != null && !"0.00".equals(scoreinfoMap.get("thisavail")))
															{
															%>
															<tr>
																<td class="tl bill_fee_sum"><Strong>���·��úϼ�</Strong></td>
																<td class="tr">
																	<%=parseBillfixed_hj %>
																</td>
															<tr>
															<%} %>
														</table>
												</td>
												<%} %>
												<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end --%>
											</tr>
										</table>
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
														<th class="tl">������Ŀ</th>
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
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }monthFee/billfixedBarChart_new.action"></iframe>
												<%
												}
												%>
											 	<br><br>
												<!-- ���·��ýṹͼ -->
												<%
												if (map.get("billfixed") != null)
												{
												%>
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }monthFee/billfixedPieChart_new.action"></iframe>
												<%
												}
												%>
																					
											 </td>
										</tr>
									</table>
									<br>
									
									<!-- �ʷ��Ƽ� -->
									<!-- 
									<table class="tb_blue02" width="100%">
										<tr>
											<th colspan="20"><Strong>�ʷ��Ƽ�</Strong></td>
										</tr>
										<tr>
											<td style="text-align: left;">
												<%=recommend == null ?"������" : recommend%>
											</td>
										</tr>
									</table>
									<br>
									 -->
									
									<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin --%>			
									<!-- �˻���Ϣ -->
									<%
									if (acctbalanceMap != null && acctbalanceMap.size() > 0)
									{
									%>
									<table class="tb_blue06" width="100%">
										<tr>
											<th colspan="20"><Strong>�˻���Ϣ</Strong></th>
										</tr>
										<tr>
											<th class="noLRBorder">����ĩ����</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">���³�ֵ</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">ʹ��Э���</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">�˷�</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">����ת��</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder" colspan="3">���ºϼƿ���</th>
										</tr>
										<tr>
											<td class="noLRBorder"><%= acctbalanceMap.get("lastval") != null ? acctbalanceMap.get("lastval") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("income") != null ? acctbalanceMap.get("income") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("contractused") != null ? acctbalanceMap.get("contractused") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("backfee") != null ? acctbalanceMap.get("backfee") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder" colspan="3"><%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %></td>
										</tr>
										<tr>
											<th class="noLRBorder">���ºϼƿ���</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">���·��úϼ�</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">�����˸���</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">�ϻ��������</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">ΥԼ��</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">����ת��</th>
											<th class="noLRBorder">��</th>
											<th class="noLRBorder">��ĩ���</th>
										</tr>
										<tr>
											<td class="noLRBorder"><%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("substotalfee") != null ? acctbalanceMap.get("substotalfee") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("payotherfee") != null ? acctbalanceMap.get("payotherfee") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("othersubsfee") != null ? acctbalanceMap.get("othersubsfee") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("latefee") != null ? acctbalanceMap.get("latefee") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %></td>
											<td class="noLRBorder">��</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : "" %></td>
										</tr>
									</table>
									<br/>
									<%} %>
                                    <%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end --%>
									<!-- ������Ϣ -->
									<% 
									int bz = 0;
									if (scoreinfoMap != null)
									{
										if (
											!("0".equals(scoreinfoMap.get("thisavail"))
											&& "0".equals(scoreinfoMap.get("lastavail"))
											&& "0".equals(scoreinfoMap.get("consume"))
											&& "0".equals(scoreinfoMap.get("award"))
											&& "0".equals(scoreinfoMap.get("transin"))
											&& "0".equals(scoreinfoMap.get("exchange"))
											&& "0".equals(scoreinfoMap.get("transout"))
											&& "0".equals(scoreinfoMap.get("clear")))
											)
										{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20"><Strong>������Ϣ</Strong></td>
											</tr>
											<tr>
												<td class="noLRBorder">���û���</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">���ڿ���</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">������������</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">���ڽ�������</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">���ڿ���ת��</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">���ڶһ�</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">���ڿ���ת��</td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder">��������</td>
											</tr>
											<tr>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("thisavail") != null ? scoreinfoMap.get("thisavail") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("lastavail") != null ? scoreinfoMap.get("lastavail") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("consume") != null ? scoreinfoMap.get("lastavail") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("award") != null ? scoreinfoMap.get("award") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("transin") != null ? scoreinfoMap.get("transin") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("exchange") != null ? scoreinfoMap.get("exchange") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("transout") != null ? scoreinfoMap.get("transout") : "" %></td>
												<td class="noLRBorder">��</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("clear") != null ? scoreinfoMap.get("clear") : "" %></td>
											</tr>
										</table>
										<br>
										
										<!-- ��ע -->
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20"><Strong>��ע</Strong></td>
											</tr>
											<tr>
												<td class="tl">
													<%=scoreremark%>
													<br>
													<%=acknowledgement%>
												</td>
											</tr>
										</table>
										<br>
										<% 
										}
										else
										{
											bz = 1;
										%>
											<!-- ��ע -->
									<table class="tb_blue05" width="100%">
												<tr>
													<th colspan="20"><Strong>��ע</Strong></td>
												</tr>
												<tr>
											<td class="tl">
														<%=acknowledgement%>
													</td>
												</tr>
											</table>
											<br>
										<%										
										}
									}
									else
									{
										if (bz == 0)
										{
									%>
										<!-- ��ע -->
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20"><Strong>��ע</Strong></td>
											</tr>
											<tr>
												<td class="tl">
													<%=acknowledgement%>
												</td>
											</tr>
										</table>
										<br>
									
									<% 
										}
									}
									%>
									
									
									
									
									<!-- ����ҵ�� -->
									
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20" class="tl"><Strong>�й��ƶ�����ҵ�����</Strong></th>
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
											<td class="tr"><%=feedetailPO.getValue1() %></td>
												
												<%if ("0".equals(feedetailPO.getBz2())){%>
											<td class="tl"><Strong><%=feedetailPO.getName2() %></Strong></td>
												<%}else{%>
											<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;<%=feedetailPO.getName2() %></td>
												<%}%>
											<td class="tr"><%=feedetailPO.getValue2() %></td>
												
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
									if (spList != null && spList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>���շ�ҵ��</Strong></th>
											</tr>
											<tr>
												<th class="tc">�������</th>
												<th class="tc">ҵ������</th>
												<th class="tc">�����ṩ��</th>
												<th class="tc">ʹ�÷�ʽ</th>
												<th class="tc">��������</th>
												<th class="tc">���</th>
												
											</tr>
											<% 
											for (Map obj : spList)
											{
											%>
											<tr>
												<td class="tc"><%=obj.get("spcode") %></td>
												<td class="tc"><%=obj.get("spname") %></td>
												<td class="tc"><%=obj.get("servcode") %></td>
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
									<% 
									if (pkgList != null && pkgList.size() > 0 && obj_total != null && obj_total.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="2" class="tl"><Strong>�ײ���ͨ����ʹ����ϸ</Strong></th>
											</tr>
											<tr>
												<th colspan="2"><Strong>�ײͰ���ͨ����</Strong></th>
											</tr>
											<%
											if (pkgList != null && pkgList.size() > 0)
											{
												for(PkgPO obj : pkgList)
												{
											%>
													<tr>
														<td class="tc"><%=obj.getPkgname() %></td>
														<td class="tl"><%=obj.getPkgdesc() %></td>
													</tr>
											<% 
												}
											}
											else
											{
											%>
													<tr><td colspan="2">������</td></tr>
											<% 
											}
											%>
											
											<tr>
												<th colspan="2"><Strong>�ײ���ʵ��ʹ��ͨ����</Strong></th>
											</tr>
											<%
											if (obj_total != null && obj_total.size() > 0)
											{
												for (PkgPO obj : obj_total)
												{
											%>
													<tr>
														<td class="tc">ͨ������</td>
														<td class="tl"><%=obj.getPrivs() %></td>
													</tr>
											<% 
												}
											}
											%>
											
											<%
											if (pkgList != null && pkgList.size() > 0)
											{
												for(PkgPO obj : pkgList)
												{
											%>
													<tr>
														<td class="tc"><%=obj.getPkgname() %></td>
														<td class="tl"><%=obj.getPrivs() %></td>
													</tr>
											<% 
												}
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
									<% 
									}
									%>
									<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin --%>
									<!-- �˱�������ϸ -->
									<% 
									if (inoutdetailList != null && inoutdetailList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>�˻�������ϸ</Strong></th>
											</tr>
											<tr>
												<th class="tc">ʱ��</th>
												<th class="tc">��ʽ</th>
												<th class="tc">���</th>
												<th class="tc">���</th>
												<th class="tc">��ע</th>
											</tr>
											<% 
												for (Map inoutdetailMap : inoutdetailList)
												{
											%>
													<tr>
														<td class="tc"><%=inoutdetailMap.get("date") != null ? inoutdetailMap.get("date") : "" %></td>
														<td class="tc"><%=inoutdetailMap.get("model") != null ? inoutdetailMap.get("model") : "" %></td>
														<td class="tc"><%=inoutdetailMap.get("type")  != null ? inoutdetailMap.get("type") : ""%></td>
														<td class="tc"><%=inoutdetailMap.get("fee")  != null ? inoutdetailMap.get("fee") : ""%></td>
														<td class="tc"><%=inoutdetailMap.get("desc") != null ? inoutdetailMap.get("desc") : ""%></td>
													</tr>
											<%
												}
											%>
										</table>
										<br/>
									<% 
									}
									%>
									<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end --%>
									<!-- Э�����Ϣ -->
									<% 
									if (agreementinfoList != null && agreementinfoList.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>Э�����Ϣ</Strong></th>
											</tr>
											<tr>
												<th class="tc leftBorder rightBorder">Э�������</th>
												<th class="tc leftBorder rightBorder">ʹ�ú���</th>
												<th class="tc noLRBorder">����ĩ���</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">��������</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">���¿۳�</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">��ĩ���</th>
												<th class="tc leftBorder rightBorder">��Ч��</th>
												<th class="tc leftBorder rightBorder">ÿ��������Ѷ��</th>
												<th class="tc leftBorder rightBorder">����ʵ��ʹ��</th>
												<th class="tc leftBorder rightBorder">��ע</th>
											</tr>
											<%
												for (Map obj : agreementinfoList)
												{
											%>
													<tr>
														<td class="tc leftBorder rightBorder"><%=obj.get("name") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %></td>
														<td class="tc noLRBorder"><%=obj.get("lastmonthleft") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthpay") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthdeduct") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("monthclosing") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("efftime") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("curmonthused") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("remark") %></td>
													</tr>
											<%
												}
											%>
										</table>
										<br>
									<% 
									}
									%>
									
									
									<!-- ���Ϳ���Ϣ -->
									<% 
									if (presentinfoList != null && presentinfoList.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>���Ϳ���Ϣ</Strong></th>
											</tr>
											<tr>
												<th class="tc leftBorder rightBorder">��������</th>
												<th class="tc leftBorder rightBorder">ʹ�ú���</th>
												<th class="tc noLRBorder">����ĩ���</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">��������</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">���¿۳�</th>
												<th class="tc noLRBorder">��</th>
												<th class="tc noLRBorder">��ĩ���</th>
												<th class="tc leftBorder rightBorder">��Ч��</th>
												<th class="tc leftBorder rightBorder">ÿ��������Ѷ��</th>
												<th class="tc leftBorder rightBorder">����ʵ��ʹ��</th>
												<th class="tc leftBorder rightBorder">��ע</th>
											</tr>
											<% 
												for (Map obj : presentinfoList)
												{
											%>
													<tr>
														<td class="tc leftBorder rightBorder"><%=obj.get("name") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %></td>
														<td class="tc noLRBorder"><%=obj.get("lastmonthleft") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthpay") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthdeduct") %></td>
														<td class="tc noLRBorder">��</td>
														<td class="tc noLRBorder"><%=obj.get("monthclosing") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("efftime") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("curmonthused") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("remark") %></td>
														<!--
														<td style="text-align: center;"><%=obj.get("curmonthreturn") %></td>
														-->
													</tr>
											<%
												}
											%>
										</table>
										<br>
									<% 
									}
									%>
									
									<!-- ���˴�����Ϣ -->
									<% 
									if (payedbyotherList != null && payedbyotherList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>���˴�����Ϣ</Strong></th>
											</tr>
											<tr>
												<th class="tc">��������</th>
												<th class="tc">���ѷ�Χ</th>
												<th class="tc">���´���</th>
											</tr>
											<%
											
												for (Map payedbyother : payedbyotherList)
												{
											%>
													<tr>
													<td class="tc"><%=payedbyother.get("paytelnum") %></td>
													<td class="tc"><%=payedbyother.get("paytype") %></td>
													<td class="tc"><%=payedbyother.get("fee") %></td>
													</tr>
											<%
												}
											%>
										</table>
										<br>
									<% 
									}
									%>
									
									<!-- �����˸���Ϣ -->
									<% 
									if (payedforotherList != null && payedforotherList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>�����˸�����Ϣ</Strong></th>
											</tr>
											<tr>
												<th class="tc">��������</th>
												<th class="tc">���ѷ�Χ</th>
												<th class="tc">���´���</th>
											</tr>
											
											<%
											for (Map payedforother : payedforotherList)
											{
											%>
											<tr>
												<td class="tc"><%=payedforother.get("payedtelnum") %></td>
												<td class="tc"><%=payedforother.get("paytype") %></td>
												<td class="tc"><%=payedforother.get("fee") %></td>
											</tr>
											<%
										    }
											%>
										</table>
										<br>
									<% 
									}
									%>
									
								<!-- �б����� -->
							</div>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
                <div class="box120W fl ml10">
                <%--modify begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
					<p class="blank10"></p>
					<a  class="btn_sendmail" href="javascript:void(0)" onclick="openSendmailWindow('sendmail')" ></a>
					
					<p class="blank10"></p>
					<a  class="btn_120_83_sd" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>
					
					<p class="blank10"></p>
					<!-- 
					<a  class="btn_120_48" href="javascript:void(0)" onclick="openDirectory('directory')" ></a>
					 -->
				 <%--modify end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>	
				</div>
                <div class=" clear"></div>
                
                <!--������-->
                <!-- �˵�������ʾ�� -->
                <div class="openwin_tip div708w392h" id="sendmail">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">��ȷ��Ҫ���͵�ǰ�˵��������ֻ������𣿣�</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();sendmail('1','<s:property value="#request.billcycle"/>','<%="" %>');">ȷ��</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
                    </div>
                </div>
                
                <!-- ��ʾ�û���ͨ������� -->
                <div class="openwin_tip div708w392h" id="openMailConfirm">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">����δ��ͨ139����</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">���ȷ����Ϊ����ͨ139������Ѱ棨��ѣ�</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();provideMailService('<s:property value="#request.billcycle"/>','<%="" %>');">ȷ��</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
                    </div>
                </div>
                
                <!-- �û���ͨ139����ʧ����ʾ��Ϣ -->
                <div class="openwin_tip div708w392h" id="failToOpenMail">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">139���俪ͨʧ��!</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20"></p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    </div>
                </div>
                
                <!-- �˵����ͳɹ���ʾ��Ϣ -->
                <div class="openwin_tip div708w392h" id="successedSend">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">�����˵���Ϣ�ѷ���������139����</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">���¼mail.10086.cn��ʹ���ֻ�����</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn��ѯ</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    </div>
                </div>
                
                <!-- ��ʾ�û��ѿ�ͨ139���䲢�Ժ����˵���Ϣ -->
                <div class="openwin_tip div708w392h" id="openMailPrompt">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">�����˵���Ϣ�Ժ󽫷���������139����</p>
                    
                 	<%-- modify begin sWX219697 2014-5-14 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ� --%> 
		        	<%
		        		String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH); 
		          		if("1".equals(sendRecords))
		          		{
		          	%>
                    	<p class="fs30 yellow pt30 tc pt30 pl20">Ϊ����������Ϣ��ȫ�������Ʊ�������139����</p>
                    	<p class="fs30 yellow pt30 tc pt30 pl20">���룬��������ɷ��͡�MM����10658139</p>
                    <%
                        }
                        else
				        {
				    %>
				    <p class="fs30 yellow pt30 tc pt30 pl20">���¼mail.10086.cn��ʹ���ֻ�����</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn��ѯ</p>
				    <%
				        }
				    %>
				  <%-- modify end sWX219697 2014-5-14 OR_SD_201404_105_ɽ��_��������139�����굥Ͷ����Ŀ�ĺ� --%>
				 
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    </div>
                </div>
                
                <!-- ��ӡ�˵���ʾ�� -->
                <div class="openwin_tip div708w392h" id="openWin1">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">��ȷ��Ҫ��ӡ��ǰ�˵���</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">ȷ��</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
                    </div>
                </div>
                
                <div class="openwin_tip div708w192h" id="openWinLoading">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100" alt='��ӡ��...' />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">�˵����ڴ�ӡ�����Ժ�...</p>
                      </div>
                    </div>
                </div>
                <%--add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
                <!--�嵥�Ķ�ָ�� -->
                <div class="openwin_tip openwin_big div804w515h" id="directory">
                    <div class="bg loading tc"></div>
                    <div class="blank60"></div>
                    <div class="fl ring_info">
						<p class="fs16 yellow pt30 tc pt30 pl20"><%=additionalInfo == null ? "&nbsp;" : additionalInfo%></p>
					</div>
                    <div class="blank10"></div>
                    <div class="tc">
                    <div class="blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    </div>
                </div>
                <%--add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
                
                <script type="text/javascript">
                
				openSendmailWindow = function(id)
				{
					wiWindow = new OpenWindow("sendmail",708,392);//�򿪵�����������
				}
				
				openWindow = function(id)
				{
					wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������
				}
				
				openWindowloading = function(id)
				{
					wiWindow = new OpenWindow("openWinLoading",708,192);//�򿪵�����������
					printInfo();
					//gotoPrintSuccess();
					
				}
				
				openWindowSuccess = function(id)
				{
					wiWindow = new OpenWindow("openWinSuccess",708,392);//�򿪵�����������
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
				<%--add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
				openDirectory = function(id){
					wiWindow = new OpenWindow("directory",708,392);//���������ʼ������ʾ��Ϣ����
				}
				<%--add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
				</script>
                <!--����������-->
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>