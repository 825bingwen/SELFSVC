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
	
	// 客户信息
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	
	// 终端信息
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	// 账单信息
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	
	// 费用信息
	List<Object> obj_billfixed = (List<Object>)map.get("billfixed");
	
	// 自有业务信息(双排)
	List<FeedetailPO> feedetailList = (List<FeedetailPO>)map.get("feedetails");
	
	// 自有业务信息(单排)
	List<FeedetailPO> feedetailList_ = (List<FeedetailPO>)map.get("feedetails_");
	
	// 账户概要信息
	Map acctbalanceMap = (Map)map.get("acctbalance");
	
	// 账户概要列表
	List<Map> acctbalanceList = (List<Map>)acctbalanceMap.get("acctlist");
	
	// 积分信息
	Map<String,String> scoreinfoMap = (Map<String,String>)map.get("scoreinfo");
	
	// 资费推荐 
	String recommend = (String)map.get("recommend");
	
	// 套餐信息
	List<PkgPO> pkgList = (List<PkgPO>)map.get("pkg");
	
	// 代收费业务
	List<Map> spbillList = (List<Map>)map.get("spbill");
	
	// 账本入账明细
	List<Map> inoutdetailList = (List<Map>)map.get("inoutdetail");
	
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

    //add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
	//文字链接
	String textHref = (String) PublicCache.getInstance().getCachedData(Constants.MONTHBILL_TEXT_HREF);
	//add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
	
	// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	int nValueForPopWindow = 0;
	
	String valueForPopWindow = (String) PublicCache.getInstance().getCachedData("SH_CLOSE_POPWINDOW_VALUE");
	if (valueForPopWindow != null && !"".equals(valueForPopWindow))
	{
		nValueForPopWindow = Integer.parseInt(valueForPopWindow);
	}
	// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
	
	String startdate = (String)request.getAttribute("startdate");
	String enddate = (String)request.getAttribute("enddate");
	
	String startdate_format = startdate.substring(0,4) + "年" + startdate.substring(4,6) + "月" + startdate.substring(6,8) + "日";
	String enddate_format = enddate.substring(0,4) + "年" + enddate.substring(4,6) + "月" + enddate.substring(6,8) + "日";
	
	// 当前余额
	String leftmoney0 = acctbalanceMap != null && acctbalanceMap.get("leftmoney0")!=null?(String)acctbalanceMap.get("leftmoney0"):"";
	
	// 话费账户可用余额
	String leftmoney1 = acctbalanceMap != null && acctbalanceMap.get("leftmoney1")!=null?(String)acctbalanceMap.get("leftmoney1"):"";
	
	// 协议款余额
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
	
	String currentDate = CommonUtil.dateToString(new Date(), "yyyy年MM月dd日");
	String currentDate1 = CommonUtil.dateToString(new Date(), "yyyyMMdd");
	
	// add begin hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
	// 账单信息通信量明细资费政策名称代替套餐名称标识（0：关闭 1：开启）
	String feenameswitch = (String)PublicCache.getInstance().getCachedData(Constants.SH_BILLFEENAMESWITCH);
	// add end hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
		     		
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
			// 返回首页重新计时
			resetGoHome();
			
			//接收键盘码
			var key = GetKeyCode(e);
		    //8:backspace 32:空格 B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 退出
			if (key == 82 || key == 220)
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
		   		return ;
			}
			// 0键
			else if (key == 48)
			{
				//topGo('qryBill', 'feeservice/feeServiceFunc.action');
				goback('<s:property value='curMenuId' />');
				return ;
			}
			// 1键
			else if (key == 49 || key == 80)
			{
				<%if("1".equals((String) PublicCache.getInstance().getCachedData("SH_PRINT_MONTHLIMT")))
				{
				%>
					var currentMonth = getCurrentTime1().substring(0,6);
					if('<s:property value="#request.month" />' == currentMonth)
					{
						alertError("当月账单只能查询不能打印，敬请谅解！");
						return;
					}
				<%
				}
				%>
				openWindowloading('openWinLoading');
				return ;
			}
			// 上翻
			else if (key == 85)
			{
				myScroll.moveUp(300);
				return;
			}
			// 下翻
			else if (key == 68)
			{
				myScroll.moveDown(300);
				return;
			}
			// add begin g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
			else if (<%=nValueForPopWindow %> != 0 && <%=nValueForPopWindow %> == key)
			{
				try
				{
					wiWindow.close();
				}
				catch (ex){}
				
				return;
			}
			// add end g00140516 2013/02/03 R003C13L01n01 弹出提示框使用金属键盘按键关闭
		}
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
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
			//已经点击了返回，不能再进行打印
			if (printFlag == 1)
			{
				alertError("已进行打印，不能重复打印！");
				return;
			}
			
			printFlag = 1;
			
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
		   	Ret4 = DPsListPrinter1.PrintLine("    中国移动通信客户化账单");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		Ret4 = DPsListPrinter1.PrintLine("    手机号码: <%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%>");
     		Ret4 = DPsListPrinter1.PrintLine("    用户名称: <%=customerSimp.getCustomerName() %>");
     		Ret4 = DPsListPrinter1.PrintLine("    品    牌: <%=request.getAttribute("brandnm") + " " + request.getAttribute("productnm") %>");
     		//Ret4 = DPsListPrinter1.PrintLine("    账    期: <s:property value="#request.startdate" />-<s:property value="#request.enddate" />");     		
     		Ret4 = DPsListPrinter1.PrintLine("    账    期: <s:property value="#request.startdate" />-<%=currentDate1 %>");
     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------------------");
     		
     		// 打印账单信息开始
     		// 本期末余额 add OR_NX_201301_222
     		<%
     		if (acctbalanceMap != null && scoreinfoMap != null && acctbalanceMap.size() > 0 && scoreinfoMap.size() > 0)
     		{
     		%>
	     		Ret4 = DPsListPrinter1.PrintLine("    【本期末余额】           ");
	     		Ret4 = DPsListPrinter1.PrintLine("       当前余额:  <%=leftmoney0 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("       话费账户可用余额:  <%=leftmoney1 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("       协议款余额:  <%=leftmoney2 %>");
	     		Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
     		<%
     		}
     		%>
     		//  add OR_NX_201301_222
     		
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
			
			// 积分信息
			<%
			if (scoreinfoMap != null && scoreinfoMap.size() > 0)
			{
			%>
					Ret4 = DPsListPrinter1.PrintLine("    【积分信息】           ");
					Ret4 = DPsListPrinter1.PrintLine("    可用积分:<%=scoreinfoMap.get("thisavail") %>");;
					Ret4 = DPsListPrinter1.PrintLine("    ＝上期可用:<%=scoreinfoMap.get("lastavail") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期新增:<%=scoreinfoMap.get("consume") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期奖励:<%=scoreinfoMap.get("award") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ＋本期转入:<%=scoreinfoMap.get("transin") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期兑换:<%=scoreinfoMap.get("exchange") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期转出:<%=scoreinfoMap.get("transout") %>");
					Ret4 = DPsListPrinter1.PrintLine("    －本期作废:<%=scoreinfoMap.get("clear") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
			}
			%>
			
			// 自有业务
			<%
			if (feedetailList_ != null && feedetailList_.size() > 0)
			{
			%>
				//Ret4 = DPsListPrinter1.PrintLine("    【中国移动自有业务费用】");
				Ret4 = DPsListPrinter1.PrintLine("    【中国移动业务费用】");
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
			if (spbillList != null && spbillList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【代收费业务】");
			<%
				for (Map obj : spbillList)
				{
			%>
					Ret4 = DPsListPrinter1.PrintLine("    业务端口:<%=obj.get("spcode") %>");
					Ret4 = DPsListPrinter1.PrintLine("    业务名称:<%=obj.get("servcode") %>");
					Ret4 = DPsListPrinter1.PrintLine("    企业名称:<%=obj.get("spname") %>");
					Ret4 = DPsListPrinter1.PrintLine("    使用方式:<%=obj.get("usetype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    费用类型:<%=obj.get("feetype") %>");
					Ret4 = DPsListPrinter1.PrintLine("    金额:<%=obj.get("fee") %>");
					Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
				}
			}
			%>
			
			// 通信量明细
			
			<%
			if (pkgList != null && pkgList.size() > 0)
			{
			%>
				Ret4 = DPsListPrinter1.PrintLine("    【通信量明细】");
			<%
				// modify begin hWX5316476 2014-8-18 BUG_NX_201408_154_宁夏_关于宁夏自助终端<账单查询>菜单套餐剩余分钟数展现无计数单位的Bug
				// modify begin hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
				if("1".equals(feenameswitch))
				{
					for(PkgPO pkgPO : pkgList)
					{
						List<PrivPO> privList =  pkgPO.getPrivlist();
						for(PrivPO privPO : privList)
						{
			%>
							Ret4 = DPsListPrinter1.PrintLine("    套餐优惠名称:<%=privPO.getFeename() %>");
							Ret4 = DPsListPrinter1.PrintLine("    项目:<%=privPO.getFreetype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    赠送总量:<%=privPO.getTotal() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    已使用量:<%=privPO.getUsed() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    剩余量:<%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %>");
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
							Ret4 = DPsListPrinter1.PrintLine("    套餐优惠名称:<%=pkgPO.getPkgname() %>");
							Ret4 = DPsListPrinter1.PrintLine("    项目:<%=privPO.getFreetype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    赠送总量:<%=privPO.getTotal() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    已使用量:<%=privPO.getUsed() %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    剩余量:<%=CurrencyUtil.minusTo0(privPO.getTotal(), privPO.getUsed()) %><%=privPO.getAttrtype() %>");
							Ret4 = DPsListPrinter1.PrintLine("    ---------------------------------");
			<%
						}
					}
				}
				// modify end hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
				// modify end hWX5316476 2014-8-18 BUG_NX_201408_154_宁夏_关于宁夏自助终端<账单查询>菜单套餐剩余分钟数展现无计数单位的Bug
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
		
		<%--
         * 超链接函数
         * 转向monthBillUrl.action
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
		
		
		<!-- 客户信息 -->
		
		<!-- 客户名称 -->
		<input type="hidden" id="custname" name="custname" value="<s:property value="#request.custname" />"/>
		<!-- 品牌 -->
		<input type="hidden" id="brandnm" name="brandnm" value="<s:property value="#request.brandnm" />"/>
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
		
			<input type="hidden" name="month" value="<s:property value="#request.month" />">
			<input type="hidden" name="billcycle" value=""> 
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
        		<a href="#" class="bt10_1 fr mr43" onmousedown="this.className='bt10_1on fr mr43'" onmouseup="this.className='bt10_1 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">返回上级&nbsp;(按0键)</a>


          <div class="clear"></div>
		        <!--滚动条-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h">
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn" >
								
									<!-- 账单内容 -->
										
										<!-- 客户信息  delete OR_NX_201301_222
										<br>
										<table class="tb_blue05" width="100%">
											<tr>
																<th colspan="2" class="tc">中国移动通信客户账单</th>
											</tr>
											<tr>
												<td class="noBorder" align="center"><Strong>客户姓名</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
											</tr>
											<tr>
												<td align="center"><Strong>手机号码</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%></td>
											</tr>
											<tr>
												<td align="center"><Strong>品    牌</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
											</tr>
											<tr>
												<td align="center"><Strong>计费周期</Strong></td>
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
																<th colspan="2" class="tc">中国移动通信客户账单</th>
															</tr>
															<tr>
																<td class="noBorder" align="center"><Strong>客户姓名</Strong></td>
																<td class="tl"><s:property value="#request.custname" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>手机号码</Strong></td>
																<td class="tl"><%=customerSimp.getServNumber().substring(0,3) + "****" + customerSimp.getServNumber().substring(7,11)%></td>
															</tr>
															<tr>
																<td align="center"><Strong>品    牌</Strong></td>
																<td class="tl"><s:property value="#request.brandnm" />&nbsp;&nbsp;<s:property value="#request.productnm" /></td>
															</tr>
															<tr>
																<td align="center"><Strong>计费周期</Strong></td>
																<td class="tl"><%=startdate_format %>至<%=currentDate %></td>
															</tr>
														</table>
												</td>
												<td class="noLRBorder">
												&nbsp;
												</td>
												<td class="noLRBorder">
														<table class="tb_blue05" width="100%">
															<tr>
																<th colspan="2" class="tl"><Strong>本期末余额</Strong></th>
															</tr>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;当前余额</td>
																<td class="tr">
																	<%=leftmoney0 %>
																</td>
															</tr>
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;话费账户可用余额</td>
																<td class="tr">
																	<%=leftmoney1 %>
																</td>
															</tr>
															
															<tr>
																<td class="tl">&nbsp;&nbsp;&nbsp;&nbsp;协议款余额</td>
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
														 
												<!-- 费用信息 -->
												<table class="tb_blue04" width="100%">
													<tr>
														<th colspan="100%" class="tc" ><Strong>费用信息</Strong></th>
													</tr>
													<tr>
														<th class="tl">费用信息</th>
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
												<iframe height="300" width="460" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="${sessionScope.basePath }nxfeeservice/billfixedBarChart_new.action"></iframe>
												<%
												}
												%>
											 
											 	<br><br>
											 	
												<!-- 当月费用结构图 -->
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
									
									<!-- 资费推荐 -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20"><Strong>资费推荐</Strong></th>
										</tr>
										<tr>
											<td class="tl">
												<%=recommend == null ?"无数据" : recommend%>
											</td>
										</tr>
									</table>
									<br>
									
									<!-- 积分信息 -->
									<% 
									if (scoreinfoMap != null && scoreinfoMap.size() > 0)
									{
									%>
										<table class="tb_blue06" width="100%">
											<tr>
												<th colspan="20"><Strong>积分信息</Strong></th>
											</tr>
											<tr>
												<th>可用积分</th>
												<th>=</th>
												<th>上期可用</th>
												<th>+</th>
												<th>本期新增</th>
												<th>+</th>
												<th>本期奖励</th>
												<th>+</th>
												<th>本期转入</th>
												<th>-</th>
												<th>本期兑换</th>
												<th>-</th>
												<th>本期转出</th>
												<th>-</th>
												<th>本期作废</th>
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
									
									<!-- 备注 -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th>备注</th>
										</tr>
										<tr>
											<td colspan="20" class="tl">备注：（ 1 ）本期新增积分仅当您结清本月费用后才生效（ 2 ）积分商城 http://jf.10086.cn 感谢您使用中国移动客户账单，如有任何疑问，请查阅附录账户、费用、通信量明细信息，或登录 www.10086.cn 的账务中心，我们将竭诚为您服务！
											    <%--add begin m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179 --%>
											    <%-- 超链接文字，文字内容可配置，点击后转到个性化内容宣传页面 --%>
											    <br>
											    <a href="#" title="欢迎访问个性化宣传页面！" onclick="goURL()" style="color:yellow; font-size:18px;" onmouseover="this.style.color = 'red';" onmouseout= "this.style.color = 'yellow';" >
											    <%=textHref %></a>
											    <%--add end m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179 --%>
											</td>
										</tr>
									</table>
									<br>
									
									<!-- 附录 -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th><div class="fs18" style="display: inline;">附录</div>：费用、通信量、账户明细</th>
										</tr>
									</table>
									<br>
									
									<!-- 自有业务 -->
									<table class="tb_blue05" width="100%">
										<tr>
											<!-- 
											<th colspan="20" class="tl"><Strong>中国移动自有业务</Strong></th>
											 -->
											 <th colspan="20" class="tl"><Strong>中国移动业务费用</Strong></th>
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
											<tr><td colspan="4">无数据</td></tr>
										<% 
										}
										%>
									</table>
									<br>
									
									
									<!-- 代收费业务 -->
									<% 
									if (spbillList != null && spbillList.size() > 0)
									{
									%>
									<table class="tb_blue05" width="100%">
											<tr>
											<th colspan="20" class="tl"><Strong>代收费业务</Strong></th>
											</tr>
											<tr>
											<th class="tc">业务端口</th>
											<th class="tc">业务名称</th>
											<th class="tc">企业名称</th>
											<th class="tc">使用方式</th>
											<th class="tc">费用类型</th>
											<th class="tc">金额</th>
												
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
									
									<!-- 通信量使用明细 -->
									<table class="tb_blue05" width="100%">
										<tr>
											<th colspan="20" class="tl"><Strong>通信量明细</Strong></th>
										</tr>
										<tr>
											<th class="tc">套餐优惠名称</th>
											<th class="tc">项目</th>
											<th class="tc">赠送总量</th>
											<th class="tc">已使用量</th>
											<th class="tc">剩余量</th>
										</tr>
										<%
										if (pkgList != null && pkgList.size() > 0)
										{
											// modify begin hWX5316476 2014-8-18 BUG_NX_201408_154_宁夏_关于宁夏自助终端<账单查询>菜单套餐剩余分钟数展现无计数单位的Bug
											// modify begin hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
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
											// modify end hWX5316476 2014-03-26 OR_NX_201403_1590_宁夏_[自助终端需求]账单查询改造需求
											// modify end hWX5316476 2014-8-18 BUG_NX_201408_154_宁夏_关于宁夏自助终端<账单查询>菜单套餐剩余分钟数展现无计数单位的Bug
										}
										else
										{
										%>
												<tr><td colspan="3">无数据</td></tr>
										<% 
										}
										%>
										
										
									</table>
									<br>
								</div>
								<!-- 列表内容 -->
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
                <!--滚动条结束-->
                <div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_120_82_1" href="javascript:void(0)" onclick="openWindow('openWin1')" >打印<br/>(按1键)</a>
				</div>
                <div class=" clear"></div>
                
               
                
                <!-- 打印账单提示框 -->
                <div class="openwin_tip div708w392h" id="openWin1">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确定要打印当前账单吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading');">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <div class="openwin_tip div708w192h" id="openWinLoading">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  alt="打印中..."  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">账单正在打印，请稍候...</p>
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
							alertError("当月账单只能查询不能打印，敬请谅解！");
							return;
						}
					<%
					}
					%>
					wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子
				}
				
				openWindowloading = function(id)
				{
					//wiWindow = new OpenWindow("openWinLoading",708,192);//打开弹出窗口例子
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
                <!--弹出窗结束-->
				
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
