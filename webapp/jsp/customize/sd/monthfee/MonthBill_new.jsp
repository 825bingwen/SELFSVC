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
	
	// 客户信息
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	// 终端信息
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	// 账单信息
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	
	// 费用信息
	List<Object> obj_billfixed = (List<Object>)map.get("billfixed");
	
	// 费用合计
	String parseBillfixed_hj = (String)map.get("parseBillfixed_hj");
	
	// 账户概要信息
	Map<String,String> acctbalanceMap = (Map<String,String>)map.get("acctbalance");
	
	// 积分信息
	Map<String,String> scoreinfoMap = (Map<String,String>)map.get("scoreinfo");
	
	// 资费推荐 
	String recommend = (String)map.get("recommend");
	
	// 积分备注
	String scoreremark = (String)map.get("scoreremark");
	if(CommonUtil.isEmpty(scoreremark))
	{
		scoreremark = "";
	}
	
	// 感谢语备注
	String acknowledgement = (String)map.get("acknowledgement");
	if(CommonUtil.isEmpty(acknowledgement))
	{
		acknowledgement = "";
	}
	
	// 自有业务信息(双排)
	List<FeedetailPO> feedetailList = (List<FeedetailPO>)map.get("feedetails");

	// 自有业务信息(单排)
	List<FeedetailPO> feedetailList_ = (List<FeedetailPO>)map.get("feedetails_");
		
	// 代收费业务
	List<Map> spList = (List<Map>)map.get("spbill");
	
	// 套餐信息
	List<PkgPO> pkgList = (List<PkgPO>)map.get("pkg");
	
	// 汇总信息
	List<PkgPO> obj_total = (List<PkgPO>)map.get("total");
	
	// 账本入账明细
	List<Map> inoutdetailList = (List<Map>)map.get("inoutdetail");
	
	// 协议款信息
    List<Map> agreementinfoList = (List<Map>)map.get("agreementinfo");
    
	// 赠送款信息
    List<Map> presentinfoList = (List<Map>)map.get("presentinfo");
    
    // 他人代付信息
    List<Map> payedbyotherList = (List<Map>)map.get("payedbyother");
    
    // 代他人付信息
	List<Map> payedforotherList = (List<Map>)map.get("payedforother");
	
	// 菜单
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
	String startdateStr = startdate.substring(0,4) + "年" + startdate.substring(4,6) + "月" + startdate.substring(6,8) + "日";
	String enddateStr = enddate.substring(0,4) + "年" + enddate.substring(4,6) + "月" + enddate.substring(6,8) + "日";
	
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
			//8、32、66、77 更正
		    //82、220 返回
		    //13、89、221 确认
		    //80 打印
		    //85 上一页
		    //68 下一页
		    
			//接收键盘码
			var key = GetKeyCode(e);
		     
		    //8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />") ;
		   		return ;
			}
			
			//80:打印
			if (key == 80)
			{
				openWindow('openWin1');
			}
		}
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
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
			//已经点击了返回，不能再进行打印
			if (submitFlag == 1)
			{
				return;
			}
			wiWindow.close();
			
			var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
			var Ret3 = DPsListPrinter1.PrintPicture(1);
		   	if (Ret3 == 1)
		   	{
		      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret3 == 41)
		   	{
		      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	
		   	var Ret4;
		   	Ret4 = DPsListPrinter1.PrintLine("    中国移动通信    客户账单");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		Ret4 = DPsListPrinter1.PrintLine("    手机号码: <%=customerSimp.getServNumber() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    用户名称: <%=customerSimp.getCustomerName() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    当前品牌: <%=request.getAttribute("brandnm") + " " + request.getAttribute("productnm") %>");
     		// add by lKF60882 2016-10-10 OR_SD_201604_913_山东_关于在各账单查询功能界面中增加星级展示的需求
     		if("" != "<%=request.getAttribute("subsCreditName") %>")
     		{
	     	Ret4 = DPsListPrinter1.PrintLine("    客户星级: <%=request.getAttribute("subsCreditName") %>");
     		}
     		Ret4 = DPsListPrinter1.PrintLine("    账    期: <%=startdateStr %>-<%=enddateStr %>");     		
     		Ret4 = DPsListPrinter1.PrintLine("    查询时间: <%=curdate%>");     		
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		
     		// 打印账单信息开始
     		
     		// Change by hWX5316476 OR_SD_201308_1167 begin
     		// 本期末余额
     		<%
     		if (acctbalanceMap != null && scoreinfoMap != null && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    【本期末余额】           ");
	     		<%
     			if (acctbalanceMap.get("thisval") != null &&  !"0.00".equals(acctbalanceMap.get("thisval")))
     			{
     			%>
	     		Ret4 = DPsListPrinter1.PrintLine("       现金余额:  <%=acctbalanceMap.get("thisval") %>");
	     		<%
	     		}
	     		%>
	     		<%
	     		if (acctbalanceMap != null && acctbalanceMap.get("contractval") != null && !"0.00".equals(acctbalanceMap.get("contractval")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       协议款余额:  <%=acctbalanceMap.get("contractval") %>");
	     		<%
	     		}
	     		%>
	     		
	     		<%
	     		if (acctbalanceMap != null && acctbalanceMap.get("presentval") != null && !"0.00".equals(acctbalanceMap.get("presentval")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       赠款余额:  <%=acctbalanceMap.get("presentval") %>");
	     		<%
	     		}
	     		%>
	     		
	     		<%
	     		if (scoreinfoMap != null && scoreinfoMap.get("thisavail") != null && !"0".equals(scoreinfoMap.get("thisavail")))
				{
	     		%>
	     			Ret4 = DPsListPrinter1.PrintLine("       可用积分:  <%=scoreinfoMap.get("thisavail") %>");
	     		<%
	     		}
	     		%>
	     		<%
	     		if(parseBillfixed_hj != null ){
	     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    本月费用合计:  <%=parseBillfixed_hj %>");
	     		<%
	     		}
	     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
     		<%
     		}
     		%>
     		// Change by hWX5316476 OR_SD_201308_1167 end
     		
     		// 费用信息 
     		<%
     		if (obj_billfixed != null && obj_billfixed.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    【费用信息】           ");
	     		Ret4 = DPsListPrinter1.PrintLine("    【费用项目】【金额/元】");
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
			// 账户信息
			<%
			if (acctbalanceMap != null && acctbalanceMap.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【账户信息】           ");
				Ret4 = DPsListPrinter1.PrintLine("    上月末结余:<%=acctbalanceMap.get("lastval") != null ? acctbalanceMap.get("lastval") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ＋本月充值:<%=acctbalanceMap.get("income") != null ? acctbalanceMap.get("income") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ＋使用协议款:<%=acctbalanceMap.get("contractused") != null ? acctbalanceMap.get("contractused") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ＋退费:<%=acctbalanceMap.get("backfee") != null ? acctbalanceMap.get("backfee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ＋过户转入:<%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    ＝本月合计可用:<%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %>");
				
				Ret4 = DPsListPrinter1.PrintLine("    本月合计可用:<%=acctbalanceMap.get("totalcanuse")  != null ? acctbalanceMap.get("totalcanuse") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    －本月费用合计:<%=acctbalanceMap.get("totalfee") !=null ? acctbalanceMap.get("totalfee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    －代他人付费:<%=acctbalanceMap.get("payotherfee") != null ? acctbalanceMap.get("payotherfee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    －合户号码费用:<%=acctbalanceMap.get("othersubsfee") != null ? acctbalanceMap.get("othersubsfee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    －违约金:<%=acctbalanceMap.get("latefee") != null ? acctbalanceMap.get("latefee") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    －过户转出:<%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    ＝月末余额:<%=acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : ""%>");
				
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			// Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end

			// 积分信息
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
					Ret4 = DPsListPrinter1.PrintLine("    【积分信息】           ");
					Ret4 = DPsListPrinter1.PrintLine("    可用积分:<%=scoreinfoMap.get("thisavail") %>");;
					Ret4 = DPsListPrinter1.PrintLine("    ＝上期可用:<%=scoreinfoMap.get("lastavail") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期新增积分:<%=scoreinfoMap.get("consume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期奖励积分:<%=scoreinfoMap.get("award") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期跨区转入:<%=scoreinfoMap.get("transin") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期兑换:<%=scoreinfoMap.get("exchange") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期跨区转出:<%=scoreinfoMap.get("transout") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期作废:<%=scoreinfoMap.get("clear") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
				}
			}
			%>
			
												
			// 自有业务
			<%
			if (feedetailList_ != null && feedetailList_.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【中国移动自有业务费用】");
				Ret4 = DPsListPrinter1.PrintLine("    【费用项目】【金额/元】");
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
			
			
			// 代收费业务
			<%
			if (spList != null && spList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【代收费业务】");
				<%
				for (Map obj : spList)
				{
				%>
				Ret4 = DPsListPrinter1.PrintLine("    服务号码:<%=obj.get("spcode") %>");
				Ret4 = DPsListPrinter1.PrintLine("    业务名称:<%=obj.get("spname") %>");
				Ret4 = DPsListPrinter1.PrintLine("    服务提供商:<%=obj.get("servcode") %>");
				Ret4 = DPsListPrinter1.PrintLine("    使用方式:<%=obj.get("usetype") %>");
				Ret4 = DPsListPrinter1.PrintLine("    费用类型:<%=obj.get("feetype") %>");
				Ret4 = DPsListPrinter1.PrintLine("    金额:<%=obj.get("fee") %>");
				Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			// 通信量使用明细_套餐包含通信量
			<%
			if (pkgList != null && pkgList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【套餐包含通信量】");
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
			
			// 通信量使用明细_实际使用通信量
			<%-- modify begin sWX219697 2015-7-15 14:18:14 --%>
			<%
			if (obj_total != null && obj_total.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【套餐内实际使用通信量】");	
				Ret4 = DPsListPrinter1.PrintLine("    >>通信总量：");	
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
			
			<%-- 套餐内实际使用通信量明细 --%>
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
			// 账本入账明细
			<%
			if (inoutdetailList != null && inoutdetailList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【账户入账明细】");		
				<%
				for (Map inoutdetailMap : inoutdetailList)
				{
				%>	
				Ret4 = DPsListPrinter1.PrintLine("    时间:<%=inoutdetailMap.get("date") != null ? inoutdetailMap.get("date") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    方式:<%=inoutdetailMap.get("model") != null ? inoutdetailMap.get("model") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    类别:<%=inoutdetailMap.get("type") != null ? inoutdetailMap.get("type") : "" %>");
				Ret4 = DPsListPrinter1.PrintLine("    金额:<%=inoutdetailMap.get("fee") != null ?  inoutdetailMap.get("fee") : ""%>");
				Ret4 = DPsListPrinter1.PrintLine("    备注:<%=inoutdetailMap.get("desc") != null ?  inoutdetailMap.get("desc") : ""%>");
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
				// 协议款信息
				Ret4 = DPsListPrinter1.PrintLine("    【协议款信息】");
				<%
				for (Map obj : agreementinfoList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    协议款名称:<%=obj.get("name") %>");
					Ret4 = DPsListPrinter1.PrintLine("    使用号码:<%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %>");
					Ret4 = DPsListPrinter1.PrintLine("    上月末余额:<%=obj.get("lastmonthleft") %>");
					Ret4 = DPsListPrinter1.PrintLine("    +当月续费:<%=obj.get("curmonthpay") %>");
					Ret4 = DPsListPrinter1.PrintLine("    -当月扣除:<%=obj.get("curmonthdeduct") %>");
					Ret4 = DPsListPrinter1.PrintLine("    =月末余额:<%=obj.get("monthclosing") %>");
					Ret4 = DPsListPrinter1.PrintLine("    有效期:<%=obj.get("efftime") %>");
					Ret4 = DPsListPrinter1.PrintLine("    每月最低消费额度:<%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    当月实际使用:<%=obj.get("curmonthused") %>");
					Ret4 = DPsListPrinter1.PrintLine("    备注:<%=obj.get("remark") %>");	
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
				// 赠送款信息
				Ret4 = DPsListPrinter1.PrintLine("    【赠款信息】");
				<%
				for (Map obj : presentinfoList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    协议款名称:<%=obj.get("name") %>");
					Ret4 = DPsListPrinter1.PrintLine("    使用号码:<%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %>");
					Ret4 = DPsListPrinter1.PrintLine("    上月末余额:<%=obj.get("lastmonthleft") %>");
					Ret4 = DPsListPrinter1.PrintLine("    +当月续费:<%=obj.get("curmonthpay") %>");
					Ret4 = DPsListPrinter1.PrintLine("    -当月扣除:<%=obj.get("curmonthdeduct") %>");
					Ret4 = DPsListPrinter1.PrintLine("    =月末余额:<%=obj.get("monthclosing") %>");
					Ret4 = DPsListPrinter1.PrintLine("    有效期:<%=obj.get("efftime") %>");
					Ret4 = DPsListPrinter1.PrintLine("    每月最低消费额度:<%=obj.get("lowestconsume") == null ? "" : obj.get("lowestconsume")  %>");
					Ret4 = DPsListPrinter1.PrintLine("    当月实际使用:<%=obj.get("curmonthused") %>");
					Ret4 = DPsListPrinter1.PrintLine("    备注: <%=obj.get("remark") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			

			// 他人代付信息
			<%
			if (payedbyotherList != null && payedbyotherList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【他人代付信息】");
				<%
				for (Map payedbyother : payedbyotherList)
				{
				%>
					Ret4 = DPsListPrinter1.PrintLine("    代付号码:<%=payedbyother.get("paytelnum") %>");
					Ret4 = DPsListPrinter1.PrintLine("    付费范围:<%=payedbyother.get("paytype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    本月代付:<%=payedbyother.get("fee") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
				<%
				}
				%>
			<%
			}
			%>
			
			// 代他人付信息
			<%
			if (payedforotherList != null && payedforotherList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【代他人付费信息】");
				Ret4 = DPsListPrinter1.PrintLine("    代付号码");
				Ret4 = DPsListPrinter1.PrintLine("    付费范围");
				Ret4 = DPsListPrinter1.PrintLine("    本月代付");
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
		      	alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	else if (Ret4 == 41)
		   	{
		      	alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
		      	return;
		   	}
		   	
		   	Ret4 = DPsListPrinter1.PrintLine("  声明：本账单信息仅供核对之用，不做任何凭证。");
  	      	Ret4 = DPsListPrinter1.PrintLine("  以上内容，如有不详之处，请向营业员咨询。");
  	      	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  	      	Ret4 = DPsListPrinter1.PrintLine("  打印地点：<%=termInfo.getOrgname() %>");
  	      	Ret4 = DPsListPrinter1.PrintLine("  打印时间: " + getCurrentTime());
  	      	
  	      	//切纸
  			var Ret5 = DPsListPrinter1.SetCutPaper();
  			if (Ret5 == 1)
  			{
      			alertError("由于打印机缺纸或未知故障，您的账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
  			else if (Ret5 == 41)
  			{
     			alertError("由于打印机设备底层驱动程序未安装，您的账单打印失败，给您带来的不便，敬请原谅。");
      			return;
  			}
     		
		}
		
		function billDetail(billcycle,month)
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
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
		
		// -------------------------------------------------发送月账单到邮箱(用户邮箱开通与否未知)----------------------------------

		// 发送月账单到邮箱(用户邮箱开通与否未知)
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
			postStr = postStr + "&realbz=history";// 历史账单
		//	postStr = postStr + "&curMenuId="+document.getElementById("curMenuId").value;//当前菜单id， add by sWX219697 2014-04-30 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函
			
			var url = "<%=basePath %>monthFee/sendmail_new.action?"+postStr;
			var loader = new net.ContentLoader(url, callBack, null, "POST", "", "application/x-www-form-urlencoded");     
		}   
  
		//<!--回调的方法-->   
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
		
		// 为用户开通免费139邮箱
		function provideMailService(billcycle,month)
		{
		bill = billcycle;
		mon = month;
		//创建ajax核心 xmlHttpRequest            
		XmlHttpRequest = false;      
		//下面需要建立一个XMLHttpRequest对象,用它进行服务器请求,针cf 不同浏览器建立方法不同      
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
		    alert('出现错误,不能建立一个XMLHTTP实例!');      
		    return false;      
		}    
		
		var curMenuId = document.getElementById("curMenuId").value;
		var postStr ="curMenuId="+curMenuId+"&telnum=<%=customerSimp.getServNumber() %>";   
		
		XmlHttpRequest.onreadystatechange=PromptCallBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
		XmlHttpRequest.open("POST","<%=basePath %>monthFee/provideMailService.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
		XmlHttpRequest.send(postStr);//开始向服务器发送xmlHttpRequest   
		}   
  
		//<!--回调的方法-->   
		function PromptCallBack()
		{
		       
		    if(XmlHttpRequest.readyState==4)   
		    {   
		        if(XmlHttpRequest.status==200)//以上两个判断 确定ajax请求已被成功相应   
		        {   
		        	var msgWelcome=XmlHttpRequest.responseText;
		        	if(msgWelcome == 0)
		        	{
		        		failToOpenMail('failToOpenMail');
		        	}
		        	if(msgWelcome == 1)
		        	{
			       		openMailPromptWindow('openMailPrompt');
			       		
			       		// 开通免费邮箱后需要发送账单信息到邮箱
			       		sendmail(0,bill,mon);
		        	}
		        }
		    }
		}
		
		// 邮件发送成功提示框
		failToOpenMail = function(id)
		{
			wiWindow = new OpenWindow("failToOpenMail",708,392);
		}
		
		// 邮件发送成功提示框
		successedSendWindow = function(id)
		{
			wiWindow = new OpenWindow("successedSend",708,392);
		}
		
		// 确认用户是否开通139邮箱
		openMailConfirmWindow = function(id)
		{
			wiWindow = new OpenWindow("openMailConfirm",708,392);
		}
		
		// 提示用户已开通139邮箱并稍后发送账单信息
		openMailPromptWindow = function(id)
		{
			wiWindow = new OpenWindow("openMailPrompt",708,392);
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
		
		<!-- 客户信息 -->
		
		<!-- 客户名称 -->
		<input type="hidden" id="custname" name="custname" value="<s:property value="#request.custname" />"/>
		<!-- 品牌 -->
		<input type="hidden" id="brandnm" name="brandnm" value="<s:property value="#request.brandnm" />"/>
		<!-- 客户星级 -->
		<input type="hidden" id="subsCreditName" name="subsCreditName" value="<s:property value="#request.subsCreditName" />"/>
		<!-- 主体产品 -->
		<input type="hidden" id="productnm" name="productnm" value="<s:property value="#request.productnm" />"/>
		<!-- 用户ID -->
		<input type="hidden" id="subsid" name="subsid" value="<s:property value="#request.subsid" />"/>
		
	    <!-- 账期信息 -->
	    
		<!-- 账期 -->
		<input type="hidden" id="cycle" name="cycle" value="<s:property value="#request.cycle" />"/>
		<!-- 开始时间 -->
		<input type="hidden" id="startdate" name="startdate" value="<s:property value="#request.startdate" />"/>
		<!-- 结束时间 -->
		<input type="hidden" id="enddate" name="enddate" value="<s:property value="#request.enddate" />"/>
		<!-- 主账号 -->
		<input type="hidden" id="acctid" name="acctid" value="<s:property value="#request.acctid" />"/>		
		<!-- 是否合户用户 -->
		<input type="hidden" id="unionacct" name="unionacct" value="<s:property value="#request.unionacct" />"/>		
		
			<input type="hidden" name="month" value="">
			<input type="hidden" name="billcycle" value=""> 
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回账单详单查询</a>


          <div class="clear"></div>
		        <!--滚动条-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName%></p>
								<div class="ptop180 tc p747w411h" id="inn" >
								
									<!-- 账单内容 -->
									
										<!-- 客户信息 -->
										<br>
										<table class="tb_blue06" width="100%">
											<tr>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tc">中国移动通信&nbsp;&nbsp;客户账单</th>
															</tr>
															<tr>
																<td class="noBorder" align="center"><Strong>客户名称</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>手机号码</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber() %></td>
															</tr>
															<tr>
																<td align="center"><Strong>当前品牌</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
															</tr>
															<%--add by lKF60882 2016-10-10 OR_SD_201604_913_山东_关于在各账单查询功能界面中增加星级展示的需求--%>
															<s:if test='%{!"".equals(#request.subsCreditName)}'>
																<tr>
																	<td align="center"><Strong>客户星级</Strong></td>
																	<td class="tl"><s:property value="#request.subsCreditName" /></td>
																</tr>
															</s:if>
															<tr>
																<td align="center"><Strong>计费周期</Strong></td>
																<td class="tl"><%=startdateStr %>-<%=enddateStr %></td>
															</tr>
															<tr>
																<td align="center"><Strong>查询时间</Strong></td>
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
																<th colspan="2" class="tl"><Strong>本期末余额</Strong></th>
															</tr>
															<% 
															if (acctbalanceMap.get("thisval") != null && !"0.00".equals(acctbalanceMap.get("thisval")))
															{
															%>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;现金余额</td>
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
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;协议款余额</td>
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
																	<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;赠款余额</td>
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
																	<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;可用积分</td>
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
																<td class="tl bill_fee_sum"><Strong>本月费用合计</Strong></td>
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
														 
												<!-- 费用信息 -->
												<table class="tb_blue04" width="100%">
													<tr>
														<th colspan="100%" class="tc" ><Strong>费用信息</Strong></th>
													</tr>
													<tr>
														<th class="tl">费用项目</th>
														<th class="tl">金额/元</th>
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
														<tr><td colspan="2">无数据</td></tr>
													<%
													} 
													%>
												</table>
												<br>
											 
											 </td>
											 <td class="noLRBorder">
											 
											    <!-- 消费趋势图 -->
											 	<%
											 	if (map.get("billhalfyeartrend") != null && ((List<BillPO>)map.get("billhalfyeartrend")).size() > 0)
											 	{
											 	%>
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }monthFee/billfixedBarChart_new.action"></iframe>
												<%
												}
												%>
											 	<br><br>
												<!-- 当月费用结构图 -->
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
									
									<!-- 资费推荐 -->
									<!-- 
									<table class="tb_blue02" width="100%">
										<tr>
											<th colspan="20"><Strong>资费推荐</Strong></td>
										</tr>
										<tr>
											<td style="text-align: left;">
												<%=recommend == null ?"无数据" : recommend%>
											</td>
										</tr>
									</table>
									<br>
									 -->
									
									<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin --%>			
									<!-- 账户信息 -->
									<%
									if (acctbalanceMap != null && acctbalanceMap.size() > 0)
									{
									%>
									<table class="tb_blue06" width="100%">
										<tr>
											<th colspan="20"><Strong>账户信息</Strong></th>
										</tr>
										<tr>
											<th class="noLRBorder">上月末结余</th>
											<th class="noLRBorder">＋</th>
											<th class="noLRBorder">本月充值</th>
											<th class="noLRBorder">＋</th>
											<th class="noLRBorder">使用协议款</th>
											<th class="noLRBorder">＋</th>
											<th class="noLRBorder">退费</th>
											<th class="noLRBorder">＋</th>
											<th class="noLRBorder">过户转入</th>
											<th class="noLRBorder">＝</th>
											<th class="noLRBorder" colspan="3">本月合计可用</th>
										</tr>
										<tr>
											<td class="noLRBorder"><%= acctbalanceMap.get("lastval") != null ? acctbalanceMap.get("lastval") : "" %></td>
											<td class="noLRBorder">＋</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("income") != null ? acctbalanceMap.get("income") : "" %></td>
											<td class="noLRBorder">＋</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("contractused") != null ? acctbalanceMap.get("contractused") : "" %></td>
											<td class="noLRBorder">＋</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("backfee") != null ? acctbalanceMap.get("backfee") : "" %></td>
											<td class="noLRBorder">＋</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %></td>
											<td class="noLRBorder">＝</td>
											<td class="noLRBorder" colspan="3"><%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %></td>
										</tr>
										<tr>
											<th class="noLRBorder">本月合计可用</th>
											<th class="noLRBorder">－</th>
											<th class="noLRBorder">本月费用合计</th>
											<th class="noLRBorder">－</th>
											<th class="noLRBorder">代他人付费</th>
											<th class="noLRBorder">－</th>
											<th class="noLRBorder">合户号码费用</th>
											<th class="noLRBorder">－</th>
											<th class="noLRBorder">违约金</th>
											<th class="noLRBorder">－</th>
											<th class="noLRBorder">过户转出</th>
											<th class="noLRBorder">＝</th>
											<th class="noLRBorder">月末余额</th>
										</tr>
										<tr>
											<td class="noLRBorder"><%=acctbalanceMap.get("totalcanuse") != null ? acctbalanceMap.get("totalcanuse") : "" %></td>
											<td class="noLRBorder">－</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("substotalfee") != null ? acctbalanceMap.get("substotalfee") : "" %></td>
											<td class="noLRBorder">－</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("payotherfee") != null ? acctbalanceMap.get("payotherfee") : "" %></td>
											<td class="noLRBorder">－</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("othersubsfee") != null ? acctbalanceMap.get("othersubsfee") : "" %></td>
											<td class="noLRBorder">－</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("latefee") != null ? acctbalanceMap.get("latefee") : "" %></td>
											<td class="noLRBorder">－</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("transferin") != null ? acctbalanceMap.get("transferin") : "" %></td>
											<td class="noLRBorder">＝</td>
											<td class="noLRBorder"><%=acctbalanceMap.get("thisval") != null ? acctbalanceMap.get("thisval") : "" %></td>
										</tr>
									</table>
									<br/>
									<%} %>
                                    <%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 end --%>
									<!-- 积分信息 -->
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
												<th colspan="20"><Strong>积分信息</Strong></td>
											</tr>
											<tr>
												<td class="noLRBorder">可用积分</td>
												<td class="noLRBorder">＝</td>
												<td class="noLRBorder">上期可用</td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder">本期新增积分</td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder">本期奖励积分</td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder">本期跨区转入</td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder">本期兑换</td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder">本期跨区转出</td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder">本期作废</td>
											</tr>
											<tr>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("thisavail") != null ? scoreinfoMap.get("thisavail") : "" %></td>
												<td class="noLRBorder">＝</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("lastavail") != null ? scoreinfoMap.get("lastavail") : "" %></td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("consume") != null ? scoreinfoMap.get("lastavail") : "" %></td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("award") != null ? scoreinfoMap.get("award") : "" %></td>
												<td class="noLRBorder">＋</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("transin") != null ? scoreinfoMap.get("transin") : "" %></td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("exchange") != null ? scoreinfoMap.get("exchange") : "" %></td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("transout") != null ? scoreinfoMap.get("transout") : "" %></td>
												<td class="noLRBorder">－</td>
												<td class="noLRBorder"><%=scoreinfoMap != null && scoreinfoMap.get("clear") != null ? scoreinfoMap.get("clear") : "" %></td>
											</tr>
										</table>
										<br>
										
										<!-- 备注 -->
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20"><Strong>备注</Strong></td>
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
											<!-- 备注 -->
									<table class="tb_blue05" width="100%">
												<tr>
													<th colspan="20"><Strong>备注</Strong></td>
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
										<!-- 备注 -->
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20"><Strong>备注</Strong></td>
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
									
									
									
									
									<!-- 自有业务 -->
									
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20" class="tl"><Strong>中国移动自有业务费用</Strong></th>
										</tr>
										<tr>
											<th class="tc">费用名称</th>
											<th class="tc">金额/元</th>
											<th class="tc">费用名称</th>
											<th class="tc">金额/元</th>
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
											<tr><td colspan="4">无数据</td></tr>
										<% 
										}
										%>
									</table>
									<br>
									
									
									<!-- 代收费业务 -->
									<% 
									if (spList != null && spList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>代收费业务</Strong></th>
											</tr>
											<tr>
												<th class="tc">服务号码</th>
												<th class="tc">业务名称</th>
												<th class="tc">服务提供商</th>
												<th class="tc">使用方式</th>
												<th class="tc">费用类型</th>
												<th class="tc">金额</th>
												
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
									
									<!-- 通信量使用明细 -->
									<% 
									if (pkgList != null && pkgList.size() > 0 && obj_total != null && obj_total.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="2" class="tl"><Strong>套餐内通信量使用明细</Strong></th>
											</tr>
											<tr>
												<th colspan="2"><Strong>套餐包含通信量</Strong></th>
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
													<tr><td colspan="2">无数据</td></tr>
											<% 
											}
											%>
											
											<tr>
												<th colspan="2"><Strong>套餐内实际使用通信量</Strong></th>
											</tr>
											<%
											if (obj_total != null && obj_total.size() > 0)
											{
												for (PkgPO obj : obj_total)
												{
											%>
													<tr>
														<td class="tc">通信总量</td>
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
													<tr><td colspan="2">无数据</td></tr>
											<% 
											}
											%>
										</table>
										<br>
									<% 
									}
									%>
									<%-- Change by  hWX5316476 2013/10/17 OR_SD_201308_1167 begin --%>
									<!-- 账本入账明细 -->
									<% 
									if (inoutdetailList != null && inoutdetailList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>账户入账明细</Strong></th>
											</tr>
											<tr>
												<th class="tc">时间</th>
												<th class="tc">方式</th>
												<th class="tc">类别</th>
												<th class="tc">金额</th>
												<th class="tc">备注</th>
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
									<!-- 协议款信息 -->
									<% 
									if (agreementinfoList != null && agreementinfoList.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>协议款信息</Strong></th>
											</tr>
											<tr>
												<th class="tc leftBorder rightBorder">协议款名称</th>
												<th class="tc leftBorder rightBorder">使用号码</th>
												<th class="tc noLRBorder">上月末余额</th>
												<th class="tc noLRBorder">＋</th>
												<th class="tc noLRBorder">当月续费</th>
												<th class="tc noLRBorder">－</th>
												<th class="tc noLRBorder">当月扣除</th>
												<th class="tc noLRBorder">＝</th>
												<th class="tc noLRBorder">月末余额</th>
												<th class="tc leftBorder rightBorder">有效期</th>
												<th class="tc leftBorder rightBorder">每月最低消费额度</th>
												<th class="tc leftBorder rightBorder">当月实际使用</th>
												<th class="tc leftBorder rightBorder">备注</th>
											</tr>
											<%
												for (Map obj : agreementinfoList)
												{
											%>
													<tr>
														<td class="tc leftBorder rightBorder"><%=obj.get("name") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %></td>
														<td class="tc noLRBorder"><%=obj.get("lastmonthleft") %></td>
														<td class="tc noLRBorder">＋</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthpay") %></td>
														<td class="tc noLRBorder">－</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthdeduct") %></td>
														<td class="tc noLRBorder">＝</td>
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
									
									
									<!-- 赠送款信息 -->
									<% 
									if (presentinfoList != null && presentinfoList.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>赠送款信息</Strong></th>
											</tr>
											<tr>
												<th class="tc leftBorder rightBorder">赠款名称</th>
												<th class="tc leftBorder rightBorder">使用号码</th>
												<th class="tc noLRBorder">上月末余额</th>
												<th class="tc noLRBorder">＋</th>
												<th class="tc noLRBorder">当月续费</th>
												<th class="tc noLRBorder">－</th>
												<th class="tc noLRBorder">当月扣除</th>
												<th class="tc noLRBorder">＝</th>
												<th class="tc noLRBorder">月末余额</th>
												<th class="tc leftBorder rightBorder">有效期</th>
												<th class="tc leftBorder rightBorder">每月最低消费额度</th>
												<th class="tc leftBorder rightBorder">当月实际使用</th>
												<th class="tc leftBorder rightBorder">备注</th>
											</tr>
											<% 
												for (Map obj : presentinfoList)
												{
											%>
													<tr>
														<td class="tc leftBorder rightBorder"><%=obj.get("name") %></td>
														<td class="tc leftBorder rightBorder"><%=obj.get("usedtel") == null ? "" : obj.get("usedtel") %></td>
														<td class="tc noLRBorder"><%=obj.get("lastmonthleft") %></td>
														<td class="tc noLRBorder">＋</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthpay") %></td>
														<td class="tc noLRBorder">－</td>
														<td class="tc noLRBorder"><%=obj.get("curmonthdeduct") %></td>
														<td class="tc noLRBorder">＝</td>
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
									
									<!-- 他人代付信息 -->
									<% 
									if (payedbyotherList != null && payedbyotherList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>他人代付信息</Strong></th>
											</tr>
											<tr>
												<th class="tc">代付号码</th>
												<th class="tc">付费范围</th>
												<th class="tc">本月代付</th>
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
									
									<!-- 代他人付信息 -->
									<% 
									if (payedforotherList != null && payedforotherList.size() > 0)
									{
									%>
										<table class="tb_blue05" width="100%">
											<tr>
												<th colspan="20" class="tl"><Strong>代他人付费信息</Strong></th>
											</tr>
											<tr>
												<th class="tc">代付号码</th>
												<th class="tc">付费范围</th>
												<th class="tc">本月代付</th>
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
									
								<!-- 列表内容 -->
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
                <!--滚动条结束-->
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
                
                <!--弹出窗-->
                <!-- 账单发送提示框 -->
                <div class="openwin_tip div708w392h" id="sendmail">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确认要发送当前账单到您的手机邮箱吗？！</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();sendmail('1','<s:property value="#request.billcycle"/>','<%="" %>');">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <!-- 提示用户开通免费邮箱 -->
                <div class="openwin_tip div708w392h" id="openMailConfirm">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您尚未开通139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">点击确定将为您开通139邮箱免费版（免费）</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();provideMailService('<s:property value="#request.billcycle"/>','<%="" %>');">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <!-- 用户开通139邮箱失败提示信息 -->
                <div class="openwin_tip div708w392h" id="failToOpenMail">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">139邮箱开通失败!</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20"></p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <!-- 账单寄送成功提示信息 -->
                <div class="openwin_tip div708w392h" id="successedSend">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的账单信息已发送至您的139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">请登录mail.10086.cn或使用手机访问</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn查询</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <!-- 提示用户已开通139邮箱并稍后发送账单信息 -->
                <div class="openwin_tip div708w392h" id="openMailPrompt">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的账单信息稍后将发送至您的139邮箱</p>
                    
                 	<%-- modify begin sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 --%> 
		        	<%
		        		String sendRecords = (String) PublicCache.getInstance().getCachedData(Constants.SEND_RECORDS_MAIL_SWITCH); 
		          		if("1".equals(sendRecords))
		          		{
		          	%>
                    	<p class="fs30 yellow pt30 tc pt30 pl20">为保障您的信息安全，请妥善保存您的139邮箱</p>
                    	<p class="fs30 yellow pt30 tc pt30 pl20">密码，重置密码可发送“MM”至10658139</p>
                    <%
                        }
                        else
				        {
				    %>
				    <p class="fs30 yellow pt30 tc pt30 pl20">请登录mail.10086.cn或使用手机访问</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn查询</p>
				    <%
				        }
				    %>
				  <%-- modify end sWX219697 2014-5-14 OR_SD_201404_105_山东_关于启动139邮箱详单投递项目的函 --%>
				 
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <!-- 打印账单提示框 -->
                <div class="openwin_tip div708w392h" id="openWin1">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确定要打印当前账单吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <div class="openwin_tip div708w192h" id="openWinLoading">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100" alt='打印中...' />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">账单正在打印，请稍候...</p>
                      </div>
                    </div>
                </div>
                <%--add begin cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
                <!--清单阅读指南 -->
                <div class="openwin_tip openwin_big div804w515h" id="directory">
                    <div class="bg loading tc"></div>
                    <div class="blank60"></div>
                    <div class="fl ring_info">
						<p class="fs16 yellow pt30 tc pt30 pl20"><%=additionalInfo == null ? "&nbsp;" : additionalInfo%></p>
					</div>
                    <div class="blank10"></div>
                    <div class="tc">
                    <div class="blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                <%--add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
                
                <script type="text/javascript">
                
				openSendmailWindow = function(id)
				{
					wiWindow = new OpenWindow("sendmail",708,392);//打开弹出窗口例子
				}
				
				openWindow = function(id)
				{
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
				}
				
				openWindowloading = function(id)
				{
					wiWindow = new OpenWindow("openWinLoading",708,192);//打开弹出窗口例子
					printInfo();
					//gotoPrintSuccess();
					
				}
				
				openWindowSuccess = function(id)
				{
					wiWindow = new OpenWindow("openWinSuccess",708,392);//打开弹出窗口例子
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
					wiWindow = new OpenWindow("directory",708,392);//弹出发送邮件后的提示信息窗口
				}
				<%--add end cKF48754 2011/11/14 R003C11L11n01 OR_SD_201108_652--%>
				</script>
                <!--弹出窗结束-->
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
