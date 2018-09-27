<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<%@page import="java.math.BigDecimal,java.text.*,java.util.*"%>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@page import="java.util.Vector"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
	NserCustomerSimp customerSimp = (NserCustomerSimp) session.getAttribute(Constants.USER_INFO);
	CRSet brief = (CRSet) request.getAttribute("brief");
	CRSet billfixed = (CRSet) request.getAttribute("billfixed");
	CRSet billflex = (CRSet) session.getAttribute("billflex");
	CRSet acct = (CRSet) request.getAttribute("acct");
	CRSet score = (CRSet) request.getAttribute("score");
	CRSet sp = (CRSet) request.getAttribute("sp");
	
	String servNumber = "";
	if (customerSimp != null)
	{
		servNumber = customerSimp.getServNumber();
	}
	
	String subsName = "";
	if (customerSimp != null)
	{
		subsName = customerSimp.getCustomerName();
	}
	
	String month = request.getParameter("month");
	String startDate = CommonUtil.getBillValueByKey(billfixed,"startdate");
	String endDate = CommonUtil.getBillValueByKey(billfixed, "enddate");
	
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	
	String orgName = "";
	if (termInfo != null)
	{
		orgName = termInfo.getOrgname();
	}
	
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
%>
<%!String toStrong(CRSet crset, int i) 
{
		String rtn = "";
		String src = crset.GetValue(i, 2);
		if (src.indexOf(" ") == 0) 
		{
			    rtn = "<td style=\"text-align: left\" height='20'>"
					    + src.replaceAll(" ", "&nbsp;") + "</td>";
			    
			    rtn += "<td style=\"text-align: left\" height='20'>"
					    +(src.substring(0,src.lastIndexOf(" "))).replaceAll(" ","&nbsp;")+ crset.GetValue(i, 3).replaceAll(" ", "")
					    + "</td>";

		} 
		else 
		{
			rtn = "<td style=\"text-align: left\" height='20'><strong>" + src.trim()
					+ "&nbsp;</strong></td>";
			rtn += "<td style=\"text-align: left\" height='20'><strong>"
					+ crset.GetValue(i, 3).trim()
					+ "&nbsp;</strong></td>";
		}
		return rtn;
	}
	
	String fomartStr(String src, String unit) 
	{
		if (src == null || "".equals(src.trim())) 
		{
			return "";
		}

		try 
		{
			if (new Double(src).doubleValue() > 0) 
			{
				return src + unit;
			} else 
			{
				return "";
			}
		} 
		catch (NumberFormatException ex) 
		{
			return "";
		}
	}
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
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("  ");
     		Ret4 = DPsListPrinter1.PrintLine("      手机号码: <%=servNumber %>");
     		Ret4 = DPsListPrinter1.PrintLine("      用户名称: <%=subsName %>");
     		Ret4 = DPsListPrinter1.PrintLine("      账单月份: <%=month %>");     		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
     		
     		// 打印账单信息开始
     		//账单条目：20；费用(元)：10；开始日期：12
     		Ret4 = DPsListPrinter1.PrintLine("               费用信息           ");
     		Ret4 = DPsListPrinter1.PrintLine("  费用项目                 金 额/元  ");
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
     		
     		<%
     		if(billflex != null)
     		{
     			for(int i=0;i<billflex.GetRowCount();i++)
     			{
     				String billTitle = billflex.GetValue(i,2);
     				String billContent = billflex.GetValue(i,3);
     				if (billTitle.indexOf(" ") == 0)
     				{
     					billContent = billTitle.substring(0,billTitle.lastIndexOf(" ")).replaceAll(" "," ") + CommonUtil.fillLeftBlank(billContent,10);
     				}
     				else 
     				{
     					billContent = billflex.GetValue(i,3);
     				}
     		%>
     		Ret4 = DPsListPrinter1.PrintLine("<%="  "+CommonUtil.fillRightBlank(billTitle,24) + billContent %>");
     		<%
     			}
     		}
     			
     		%> 
     		// 打印账单信息结束		
     		// 打印账户信息开始
     		Ret4 = DPsListPrinter1.PrintLine("                       账户信息           ");
     		Ret4 = DPsListPrinter1.PrintLine("       账户项目                          金额/元  ");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月初结余",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"lastval"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("退费",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"backfee"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("过户转出余额",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"transferout"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("过户转入余额",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"transferin"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("协议款分月返还",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"contractused"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月累计已交费",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"income"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("费用合计",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"totalfee"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("违约金",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"latefee"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月末结余",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"thisval"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("赠送费剩余额",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"presentval"), "元"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("协议款剩余额",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(acct,"contractval"), "元"),10)%>");
     		// 打印账户信息结束
     		
     		// 打印积分信息开始
     		Ret4 = DPsListPrinter1.PrintLine("                       积分信息           ");
     		Ret4 = DPsListPrinter1.PrintLine("       积分项目                             积分值   ");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("上月末剩余可兑换积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"lastval"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月新增积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"income"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("其中：消费积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"consume"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("其中：在网时间奖励积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"innet"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("其中：其他奖励积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"otheraward"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月已兑换积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"thisused"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("本月末剩余可兑换积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"thisval"), "分"),10)%>");
     		Ret4 = DPsListPrinter1.PrintLine("       <%=CommonUtil.fillRightBlank("年底将清零积分",30) + CommonUtil.fillLeftBlank(fomartStr(CommonUtil.getBillValueByKey(score,"resetval"), "分"),10)%>");
     		// 打印积分信息结束
     		
     		// 打印代收信息费明细开始
     		Ret4 = DPsListPrinter1.PrintLine("                     代收信息费明细           ");
     		Ret4 = DPsListPrinter1.PrintLine("  服务商sp   服务代码   订购业务名称   费用/元   ");
     		<%
			int spCount=0;
		   	if (sp != null && sp.GetRowCount() > 0) 
		   	{
					String[] strSP;
					BigDecimal fee = new BigDecimal("0");
					int spCnt = sp.GetRowCount();
					for (int i = 0; i < spCnt-1; i++) 
					{
						if (i < 3) 
						{
							strSP = sp.GetValue(i, 2).split("  ");
		%>
		Ret4 = DPsListPrinter1.PrintLine("  <%=strSP[0]%>  <%=strSP[1]%>  <%=strSP[2]%>  <%=CommonUtil.fillLeftBlank(sp.GetValue(i, 3),10)%>");
   		<%
	   		spCount++;	
	   					}
	   				}
   		%>
		Ret4 = DPsListPrinter1.PrintLine("  <%=CommonUtil.fillRightBlank("合计",30) + CommonUtil.fillLeftBlank(sp.GetValue(spCnt-1, 3),10)%> ");
   		<%
	   		}
	   		%>
     		// 打印代收信息费明细结束
     		
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
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
  	      	Ret4 = DPsListPrinter1.PrintLine("  客户服务热线：10086");
  	      	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  	      	Ret4 = DPsListPrinter1.PrintLine("  打印地点：<%=orgName %>");
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
		
		var XmlHttpRequest;   
		// 发送月账单到邮箱(用户邮箱开通与否未知)
		function sendmail(needCheckMail,billcycle,month)
		{
		//alert(billcycle);alert(month);
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
		var postStr ="needCheckMail="+ needCheckMail + "&curMenuId=" + curMenuId+"&billcycle="+billcycle+"&month="+
			month+"&telnum=<%=servNumber%>&custName="+encodeURIComponent(encodeURIComponent("<%=customerSimp.getCustomerName()%>"))+
			"&brandID=<%=customerSimp.getBrandID()%>";
		   
		XmlHttpRequest.onreadystatechange=callBack;//设置回调的js函数  就是说发送请求 服务器响应后 回来执行的js函数   callBack是函数名   
		XmlHttpRequest.open("POST","<%=basePath %>monthFee/sendmail.action",true);//第一个参数是请求类型（GET/POST） 第二个请求服务器路径 你可以写一个servlet地址    
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");      
		XmlHttpRequest.send(postStr);//开始向服务器发送xmlHttpRequest   
		}   
  
		//<!--回调的方法-->   
		function callBack()
		{
		       
		    if(XmlHttpRequest.readyState==4)   
		    {   
		        if(XmlHttpRequest.status==200)//以上两个判断 确定ajax请求已被成功相应   
		        {   
		        	var msgWelcome=XmlHttpRequest.responseText;
		        	
		        	if(msgWelcome == 1)
		        	{
		        		successedSendWindow('successedSend');
		        	}
		        	if(msgWelcome == 2)
		        	{
			       		openMailConfirmWindow('openMailConfirm');
		        	}
		        }
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
		var postStr ="curMenuId="+curMenuId+"&telnum=<%=servNumber%>";   
		
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
			<input type="hidden" name="month" value="">
			<input type="hidden" name="billcycle" value="">
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
			
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回帐单详单查询</a>
			<s:if test="#request.twobill != null">
					<div class="b966 tc">
			  			<div class=" p40">
							<div class="blank30"></div>
							<p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span>当期月份有多个账期，请选择您要查看的账期</p>
							<div class="blank30"></div>
							<a href="#" class="bt2 fs16" onclick="billDetail('<s:property value="#request.firstCycle"/>','<%=month %>')"><s:property value="#request.firstCycle"/></a>
							<a href="#" class="bt2 fs16" onclick="billDetail('<s:property value="#request.secondCycle"/>','<%=month %>')"><s:property value="#request.secondCycle"/></a>
						</div>
					</div>			
			</s:if>
			<s:else>

          <div class="clear"></div>
		        <!--滚动条-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
								
									<!-- 帐单内容 -->
									<!-- 客户信息 -->
									<%
										if (brief != null) 
										{
											%>
											<table class="tb_blue" width="100%">
												<tr>
													<th colspan="100%" style="text-align: center">中国移动通信客户帐单</th>
												</tr>
												<tr>
													<td align="center"><Strong>客户姓名</Strong></td>
													<td align="center"><Strong>手机号码</Strong></td>
													<td align="center"><Strong>计费周期</Strong></td>
												</tr>
												<tr>
													<td align="center"><%=customerSimp.getCustomerName() %></td>
													<td align="center"><%=customerSimp.getServNumber() %></td>
													<td align="center"><%=month %>(<%=startDate%>-<%=endDate%>)</td>
												</tr>
											</table>
											<br>
										<!-- 客户信息 -->
										<!-- 费用信息开始 -->
											<%
										}
										
										if (billflex != null) 
										{
											%>
											<table class="tb_blue" width="100%">
												<tr>
													<th colspan="100%" style="text-align: center">费用信息</th>
												</tr>
												<tr>
													<td style="text-align: center">费用项目</td>
													<td style="text-align: center">金 额/元</td>
													<td style="text-align: center">费用项目</td>
													<td style="text-align: center">金 额/元</td>
												</tr>
												<%int count = billflex.GetRowCount();
													int num = count/2;
													if(count/2 == 1)
													{
														num += 1;
													}
													for (int i = 0; i < num; i++) 
													{
														out.println("<tr>");
														out.println(toStrong(billflex, i));
														if (i + num < count - 1) 
														{
															out.println(toStrong(billflex, i + num));
														} 
														else 
														{
															out.println("<td style=\"text-align: left\" height='20'>&nbsp;</td>");
															out.println("<td style=\"text-align: left\" height='20'>&nbsp;</td>");
														}
														out.println("</tr>");
													}
													%>
													<tr> 
														<td style="text-align: left" colspan="4">&nbsp;&nbsp;
														 <strong>费用合计：<%=billflex.GetValue(billflex.GetRowCount() - 1,3)%>&nbsp;元</strong>
														</td>
													</tr>
											</table>
											<br />
											<!-- 费用信息结束 -->
											<!-- 账户信息开始 -->
											<%
										}
										if (acct != null) 
										{
											%>
												<table class="tb_blue" width="100%">
													<tr>
														<th colspan="2" align="center" height="20">帐户信息</th>
														<td width="1" rowspan="14" >&nbsp;</td>
														<th colspan="2" align="center">积分信息</th>
													</tr>
													<tr> 
													    <td width="35%" align="center" height="20" >帐户项目</td>
													    <td width="15%" align="center">金额/元 </td>
													    <td width="35%" align="center">积分项目</td>
													    <td width="15%" align="center">积分值</td>
												    </tr>
												    <tr>
														<td style="text-align: left" height="20" >本月初结余</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"lastval"), "元")%></td>
														<td style="text-align: left">上月末剩余可兑换积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"lastval"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >退费</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"backfee"), "元")%></td>
														<td style="text-align: left">本月新增积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"income"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >过户转出余额</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"transferout"), "元")%></td>
														<td style="text-align: left">其中：消费积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"consume"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >过户转入余额</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"transferin"), "元")%></td>
														<td style="text-align: left">其中：在网时间奖励积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"innet"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >协议款分月返还</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"contractused"), "元")%></td>
														<td style="text-align: left">其中：其他奖励积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"otheraward"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >本月累计已交费</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct, "income"), "元")%></td>
														<td style="text-align: left">&nbsp;</td>
												        <td align="center">&nbsp;</td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >费用合计</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"totalfee"), "元")%></td>
														<td style="text-align: left">本月已兑换积分</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"thisused"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >违约金</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"latefee"), "元")%></td>
														<td style="text-align: left"><strong>本月末剩余可兑换积分</strong></td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(score,"thisval"), "分")%></td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" ><strong>本月末结余</strong></td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"thisval"), "元")%></td>
														<td width="199">&nbsp;</td>
														<td width="82">&nbsp;</td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >赠送费剩余额</td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"presentval"), "元")%></td>
														<td width="199">&nbsp;</td>
														<td width="82">&nbsp;</td>
													</tr>
													<tr>
														<td style="text-align: left" height="20" >协议款剩余额 </td>
												        <td align="center"><%=fomartStr(CommonUtil.getBillValueByKey(acct,"contractval"), "元")%></td>
														<td width="199">&nbsp;</td>
														<td width="82">&nbsp;</td>
													</tr>
													<tr>
														<td style="text-align: left" colspan="2" height="20" ><strong>本月末帐户余额：<%=CommonUtil.getBillValueByKey(acct, "notice")%></strong></td>
														<td style="text-align: left"><strong>年底将清零积分</strong></td>
														<td align="center"><strong><%=fomartStr(CommonUtil.getBillValueByKey(score,"resetval"), "分")%></strong></td>
													</tr>
												</table>
												<br />
												<!-- 账户信息结束 -->
												<!-- 代收信息费明细开始 -->
											<%
										}
										
										%>
											<table class="tb_blue" width="100%">
										    	<tr>
													<th colspan="4" align="center">代收信息费明细</th>
												</tr>
												<tr>
												    <td width="15%" align="center" height="20" >服务商(SP)</td>
												    <td width="15%" align="center">服务代码</td>
												    <td width="20%" align="center">订购业务名称</td>
												    <td width="15%" align="center">费用/元</td>
												</tr>
												<%
												if (sp != null && sp.GetRowCount() > 0) 
												{
													String[] strSP;
													BigDecimal fee = new BigDecimal("0");
													int spCnt = sp.GetRowCount();
													for (int i = 0; i < spCnt-1; i++) 
													{
														if (i < 3) 
														{
															strSP = sp.GetValue(i, 2).split("  ");
									
															%>
															<tr>
															    <td align="center" height="20" ><%=strSP[0]%></td>
															    <td align="center"><%=strSP[1]%></td>
															    <td align="center"><%=strSP[2]%></td>
															    <td align="center"><%=sp.GetValue(i, 3)%></td>
															</tr>
															<%
														} 
														else 
														{
															fee = fee.add(new BigDecimal(sp.GetValue(i, 3)));
														}
													}
									
													if (fee.doubleValue() > 0) 
													{
									
														%>
														<tr>
														    <td align="center" height="20" >其它</td>
														    <td>&nbsp;</td>
														    <td>&nbsp;</td>
														    <td align="center"><%=fee.toString()%></td>
														</tr>
														<%
													}
									
													%>
													<tr>
													    <td align="center" height="20" >合计</td>
													    <td>&nbsp;</td>
													    <td>&nbsp;</td>
													    <td align="center"><%=sp.GetValue(spCnt - 1, 3)%></td>
													</tr>
													<%
													} 
												%>
											</table>
											<br />
											<!-- 代收信息费明细结束 -->
										<%
										
										%>
											<!-- 消费分析图开始 -->
											<table class="tb_blue" width="100%">
												<tr>
													<th align="center">消费分析图</th>
												</tr>
												<tr>
													<td width="25%" rowspan="6">
														<iframe marginheight="0" marginwidth="0" frameborder="0" scrolling="no" src="<%=basePath %>monthFee/billPieChart.action">
														</iframe>
													</td>
												</tr>
											</table>
											<!-- 消费分析图结束 -->
										<%
									%>
											
								<!-- 列表内容 -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
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
                	<!--  
					<p class="blank10"></p>
					<a  class="btn_sendmail" href="javascript:void(0)" onclick="openSendmailWindow('sendmail')" ></a>
					-->
					<p class="blank10"></p>
					<a  class="btn_120_83" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>
					<!--
					<p class="blank10"></p>
					<a  class="btn_120_48" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>
					-->
				</div>
                <div class=" clear"></div>
                
                <!--弹出窗-->
                <!-- 账单发送提示框 -->
                <div class="openwin_tip" id="sendmail" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确认要发送当前账单到您的手机邮箱吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();sendmail('1','<s:property value="#request.billcycle"/>','<%=month %>');">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <!-- 提示用户开通免费邮箱 -->
                <div class="openwin_tip" id="openMailConfirm" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您尚未开通139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">点击确定将为您开通139邮箱免费版（免费）</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();provideMailService('<s:property value="#request.billcycle"/>','<%=month %>');">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <!-- 用户开通139邮箱失败提示信息 -->
                <div class="openwin_tip" id="failToOpenMail" style="width:708px; height:392px;">
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
                <div class="openwin_tip" id="successedSend" style="width:708px; height:392px;">
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
                <div class="openwin_tip" id="openMailPrompt" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的账单信息稍后将发送至您的139邮箱</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">请登录mail.10086.cn或使用手机访问</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">wapmail.10086.cn查询</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <!-- 打印账单提示框 -->
                <div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确定要打印当前帐单吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <div class="openwin_tip" id="openWinLoading" style="width:708px; height:192px;">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">帐单正在打印，请稍候...</p>
                      </div>
                    </div>
                </div>
                
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
				</script>
                <!--弹出窗结束-->
			</s:else>
				
				
				
			</div>	
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
