<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%
	TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
	String orgName = "";
	if (termInfo != null)
	{
		orgName = termInfo.getOrgname();
	}
	
	String month = (String)request.getAttribute("month");
	
	String menuid = (String)request.getAttribute("curMenuId");
	
	String qryType = (String)request.getAttribute("qryType");
	
	String custType = (String)request.getAttribute("custType");
	String unitelnumCon = (String)request.getAttribute("unitenumc");
   
    String billMonth = (null == month ? "" : month);
    
    String billMenuid = (null == month ? "" : menuid);
    
    String billQryType = (null == qryType ? "" : qryType);
    
    String billCustType =(null == custType ? "" : custType); 
    String billunitelnumCon =(null == unitelnumCon ? "" : unitelnumCon); 
    session.setAttribute("BILL_MONTH",billMonth);
    session.setAttribute("BILL_MENUID",billMenuid);
    session.setAttribute("BILL_QRYTYPE",billQryType);
    session.setAttribute("BILL_CUSTTYPE",billCustType);
    session.setAttribute("HIS_BILL_UNITELNUMCON",billunitelnumCon);
    String custInfo = (String)request.getAttribute("custInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<style type="text/css">
		.btn_120_832{width:120px;height:82px;display:block;background:url(${sessionScope.basePath }images/btn_120_82_1.png) no-repeat;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="${sessionScope.basePath }images/btn_120_82_1.png");_background:none}
		.btn_120_832_1{text-align:center;display:block;margin-top:25px;font-size:14pt;}
		</style>
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
		
		function printInfo()
		{
			//�Ѿ�����˷��أ������ٽ��д�ӡ
			if (submitFlag == 1)
			{
				return;
			}
			wiWindow.close();
			
			var DPsListPrinter1 = window.parent.document.getElementById("prtpluginid");
			var Ret3;
			try{
				Ret3 = DPsListPrinter1.PrintPicture(1);
			}
			catch(e)
			{
				alertError("���ڴ�ӡ���ؼ�δ��װ�������˵���ӡʧ�ܣ����������Ĳ��㣬����ԭ�¡�");
				return;
			}
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
     		Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
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
     		
		   	
		   	//�°��˵���ѯ
		   	var startPrint = document.getElementById('startPrint').value;
		   	if(5 != startPrint)
		   	{
		   		alert("ҳ���˵���Ϣδȫ����ѯ���������Ժ�......");
		   		return;
		   	}
		   	var tab1 = document.getElementById('curUserTab');
		   	var tab2 = document.getElementById('balanceInfo');
		   	var tab3 = document.frames('ifr_bd').document.getElementById('curBdTab');
		   	var tab4 = document.frames('ifr_bss').document.getElementById('curBssTab1');
		   	var tab5 = document.frames('ifr_bss').document.getElementById('curBssTab2');
		   	var tab6 = document.frames('ifr_asv').document.getElementById('curBaSvTab');
		   	var tab7 = document.frames('ifr_comm').document.getElementById('curBcommTab');
		   	var tab8 = document.frames('ifr_comm').document.getElementById('curBcommTab2');
		   	var isUnite = '<%=billCustType%>';
		   	
		   	
		    var custArray = createCustArray(tab1, tab2);
		    var bdArray = createBdArray(tab3,isUnite);
		   	var bssArray = createSsArray(tab4,tab5,tab6);
		   	//var commArray = createCommArray(tab7,tab8);
		   	var commArray=document.frames('ifr_comm').getPrintArray();
		    var printerArray = new Array();
		    printerArray = printerArray.concat(custArray);
		    printerArray = printerArray.concat(bdArray);
		    printerArray = printerArray.concat(bssArray);
		    printerArray = printerArray.concat(commArray);
			
			
			for(var i=0; i<printerArray.length; i++)
			{
				var rowInfo = printerArray[i];
				
				Ret4 = DPsListPrinter1.PrintLine(rowInfo);
			}
			//�°��˵���ѯ
			
			Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------"); 
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
  	      	Ret4 = DPsListPrinter1.PrintLine("  �ͻ��������ߣ�10086");
  	      	Ret4 = DPsListPrinter1.PrintLine(" ---------------------------------------------");
  	      	Ret4 = DPsListPrinter1.PrintLine("  ��ӡ�ص㣺<%=orgName %>");
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
			function goMonth(monthStr) 
		{	
			//����Ѿ�ѡ�����굥���ͻ��ߵ���˷��أ�����ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.actform.month.value = monthStr;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}hubfeeservice/queryMonthBill.action";
				document.actform.submit();
			}	
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
		<input type="hidden" name="month" value="<s:property value='month' />">			
			<input type="hidden" id="startPrint" value='0' />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����ʵ��굥��ѯ</a>
          
          <%
					String detailErrorMsg = (String) PublicCache.getInstance().getCachedData("SH_DETAIL_STYLE");
					if (detailErrorMsg != null && "1".equals(detailErrorMsg))
					{
					%>
			        <div id="btns" class="fl" >
			        <span class="fs18 ml50">���ٲ鿴��</span>
				        <s:iterator value="months" id="currMonth" status="st">
							<s:if test="#currMonth==month">
								<a href="javascript:void(0)" class="bt222on fs16"><s:property value="#currMonth.substring(0,4)+'��'+#currMonth.substring(4,6)+'��'" /></a>
							</s:if>
							<s:else>
							    <a href="javascript:void(0);goMonth('<s:property value="currMonth"/>')" class="bt2 fs16" ><s:property value="#currMonth.substring(0,4)+'��'+#currMonth.substring(4,6)+'��'" /></a>
	           				</s:else>  
						</s:iterator>
					</div>
					<%
					}
					 %>
          <div class="clear"></div>
		        <!--������-->
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- �б����� -->
								<p class="tit_info" align="left"><span class="bg"></span><%=month+menuName %></p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
								
								<!-- �ͻ���Ϣ 1 -->
								
					 <table id="custTab" width="100%" border="0" cellpadding="0" cellspacing="0">
						  <tr>
						  	<td width="55%">
							    <table id="curUserTab" width="100%">
										<tr>
											<td width="100%" align="left">
												<p class="margin-top:0px;" align="left">
												<img src="${sessionScope.basePath }images/loading2.gif" />
												<font size="1">���ڲ�ѯ�ͻ���Ϣ�����Ժ�......</font>
												</p>
											</td>
										</tr>			
										<script type="text/javascript">
										var tab  = document.getElementById('curUserTab');
										tab.deleteRow();
										var custStr = '<%=custInfo%>';
										var rowArray = custStr.split('%');
										var length = rowArray.length;
										var titleArray = new Array('��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����',
														  	 	     '�ֻ����룺','��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����',
														  	 	     'Ʒ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ƣ�',
														  	 	     '�Ʒ����ڣ�','','');
										for(var i=0; i<titleArray.length; i++)
										{
											var row = tab.insertRow();
											row.height=20;
											var cell = row.insertCell();
											cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
											if(i < length)
											{
												var content = titleArray[i] + rowArray[i].toString();
											}
											else
											{
												var content = titleArray[i];
											}
											cell.innerHTML = "<font size=2>" + content + "</font>";
											cell.align = "left";
										}
										</script>																		
								</table>
							</td>
							<td width="45%">
							    <table id="balanceInfo" width="100%">
										<tr>
											<td width="100%" align="left">
												<p class="margin-top:0px;" align="left">
												<img src="${sessionScope.basePath }images/loading2.gif" />
												<font size="1">���ڲ�ѯ�ͻ���ĩ�����Ϣ�����Ժ�......</font>
												</p>
											</td>
										</tr>			
										<script type="text/javascript">
										var url = "${sessionScope.basePath }hubfeeservice/curBillBalance.action";
										var loader1 = new net.ContentLoader(url, netload = function () 
										{
											var resXml = this.req.responseXML;
											
											if (resXml)
											{
													var root = resXml.documentElement;
													
													if('error' == root.tagName)
													{
														alert("��ѯ�ͻ���ĩ�����Ϣ����!");
														return;
													}
												    
												    displayBillBalance(resXml);								
											}
										    else
											{	
												alert("��ѯ�ͻ���ĩ�����Ϣ����!");
												return;
											}								
										}, null, "POST", "", null);
										
										function displayBillBalance(xmlData)
										{
											var tab  = document.getElementById('balanceInfo');
											tab.deleteRow();
											
											var titleArray = new Array('��ǰ���',
																	 '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����˻���',
														  	 	     '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�����˻���',
														  	 	     '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ר���˻���',
														  	 	     '���˻�����','�������ѣ�','��ǰǷ�ѣ�');
											
											var root = xmlData.documentElement;
											var allNodes = root.childNodes;
		  	 								var length = allNodes.length;
		  	 								
		  	 								var row = tab.insertRow();
											row.height=20;
											var cell = row.insertCell();
											cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
											var content = titleArray[0];
											cell.innerHTML = "<font size=2>" + content + "</font>";
											cell.align = "left";
		  	 									
		  	 								for(var i=0; i<length; i++)
											{
													var nodeValue = allNodes[i].text;
													if('EMPTY' == nodeValue)
													{
														continue;
													}
													var row = tab.insertRow();
													row.height=20;
													var cell = row.insertCell();
													cell.className = "border_RightBottom"+(i%2 == 0?"2":"");
													var content = titleArray[i+1] + allNodes[i].text;
													if(i == 3)
													{
														content = titleArray[i+1] + allNodes[i + 2].text;
													}
													else if(i == 5)
													{
														content = titleArray[i+1] + allNodes[i - 2].text;
													}
													cell.innerHTML = "<font size=2>" + content + "</font>";
													cell.align = "left";
											}
											
											var startPnt = document.getElementById("startPrint").value;
				
											document.getElementById("startPrint").value = parseInt(startPnt,10) + 1;
										}
										</script>																		
								</table>
							</td>
						 </tr>
					</table>
								
								
							    <table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">������Ϣ</font></p>
											</td>
									</tr>
								</table>
							    <!-- ������Ϣ 2 3 4 -->
							    <table id="consumeTab" width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<!-- ������Ϣ -->
											<td width="100%">
												<iframe id="ifr_bd" width="100%" 
													onload="this.height=(window.frames['ifr_bd'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toCurBillDetail.action">
												</iframe>
											</td>
											<!-- ͼ����Ϣ 
											<td width="45%">
												<iframe id="ifr_bti" width="100%"
													onload="this.height=(window.frames['ifr_bti'].document.body.scrollHeight);"
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toCurBillTrendImg.action">
												</iframe>
												<iframe id="ifr_bsi" width="100%"
													onload="this.height=(window.frames['ifr_bsi'].document.body.scrollHeight);" 
													marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toCurBillStructImg.action">
												</iframe>
											</td>
											-->
										</tr>	 
								</table>
								
								<!-- M ֵ 5
								<table id="mvalueTab" width="100%">
										<tr>
											<td>
												<iframe id="ifr_bm" width="100%"
													onload="this.height=(window.frames['ifr_bm'].document.body.scrollHeight);"
												  	marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 		src="${sessionScope.basePath}hubfeeservice/toCurBillMvalue.action">
												</iframe>
											</td>
										</tr>	 
								</table>
								-->
								
								<table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">һ��������ϸ</font></p>
											</td>
									</tr>
								</table>
								<!-- ҵ�������Ϣ 6 7 -->
								<table id="svTab" width="100%">
									<tr>
										<td>
											<table width="100%">
												<tr height='20'>
														<td width="100%" align="left">
															<p><font color='black' size="1">1���й��ƶ�����ҵ�����</font></p>
														</td>
												</tr>
											</table>
											<iframe id='ifr_bss' width="100%"
												onload="this.height=(window.frames['ifr_bss'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toCurBillSelfSv.action">
											</iframe>
										</td>
									</tr>
									<tr>
										<td>
											<table width="100%">
												<tr height='20'>
														<td width="100%" align="left">
															<p><font color='black' size="1">2�����շ�ҵ�����</font></p>
														</td>
												</tr>
											</table>
											<iframe id='ifr_asv' width="100%" 
												onload="this.height=(window.frames['ifr_asv'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toCurBillAgentSv.action">
											</iframe>
										</td>
									</tr> 
								</table>
								
								<table width="100%">
									<tr>
											<td width="100%" align="left">
												<p><font color='yellow' size="2">����ͨ����ʹ����Ϣ��ϸ</font></p>
											</td>
									</tr>
								</table>	
								<!-- ͨ�ŷ�����Ϣ 8 -->
								<table id="commTab" width="100%">
									<tr>
										<td>
											<iframe id='ifr_comm' width="100%"
												onload="this.height=(window.frames['ifr_comm'].document.body.scrollHeight);"
												marginheight="0" marginwidth="0" frameborder="0" scrolling="no" 
												 	src="${sessionScope.basePath}hubfeeservice/toCurBillCommDetail.action">
											</iframe>
										</td>
									</tr>	 
								</table>
								<!-- �б����� -->
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px"   >0%</div>
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
					<p class="blank10"></p>
					<a  class="btn_120_83" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>

				</div>
				
				<div class="box120W fl ml10" >
					<p class="blank10"></p>
					<a class="btn_120_832" href="javascript:void(0)" onclick="colQuerybill('message');" >
					
					<span class="btn_120_832_1" >
					
				       ���Ž���</span></a>

				</div>
				<div class="box120W fl ml10" >
					<p class="blank10"></p>
					<a class="btn_120_832" href="javascript:void(0)" onclick="colQuerybill('colMessage');"><span class="btn_120_832_1" >
					
					���Ž���</span></a></a>

				</div>
				
				
				
			
                <div class=" clear"></div>
                
                <!--������-->
                
                <div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">��ȷ��Ҫ��ӡ��ǰ�ʵ���</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();openWindowloading('openWinLoading')">ȷ��</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
                    </div>
                </div>
                
                <div class="openwin_tip" id="openWinLoading" style="width:708px; height:192px;">
                    <div class="bg loading">
                      <div class="blank60"></div>
                      <div class="relative">
                        <img src="${sessionScope.basePath}images/loading2.gif" class="fl ml100"  />
                        
                        <p class="tc fs28 fl lh2 ml20 yellow">�ʵ����ڴ�ӡ�����Ժ�...</p>
                      </div>
                    </div>
                </div>
                 <!--�˵�Эͬ��ѯ��ʾ-->
                <div class="openwin_tip" id="colBillQueryWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20" id="colBillQuery"></p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">ȷ��</a>
                    
                    </div>
                </div>
                
                <script type="text/javascript">
                
				openWindow = function(id){
					wiWindow = new OpenWindow("openWin1",708,392);//�򿪵�����������
					
				}
				
				openWindowloading = function(id){
					wiWindow = new OpenWindow("openWinLoading",708,192);//�򿪵�����������
					printInfo();
					//gotoPrintSuccess();
					
				}
				
				openWindowSuccess = function(id){
					wiWindow = new OpenWindow("openWinSuccess",708,392);//�򿪵�����������
				}
				
				function btnClick(btn,btClass){
					  var btns=document.getElementById('btns').getElementsByTagName('a');
					  for(i=0;i<btns.length;i++){
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
	<script type="text/javascript">
			
		function goback(curmenu) 
		{
			//�Ѿ�����˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target="_self";
				document.actform.action="${sessionScope.basePath }<%=menuURL %>";
				document.actform.submit();
				
			}	
		}
		
		
function createSimple(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	for(var i=0; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			content += cells[j].innerText;
		}
		
		if('' == content)
		{
			break;
		}
		
		array[i] = content;
	}
	return array;
}

function createCustArray(custTab, balanceTab)
{
	var custArray = new Array();
	
	custArray[0] = '�й��ƶ�ͨ�ſͻ��˵�';
	custArray[1] = '====================';
	custArray = custArray.concat(createSimple(custTab));
	custArray = custArray.concat(createSimple(balanceTab));
	
	return custArray;
}

function createUniteBd(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	for(var i=0; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		
		for(var j=0; j<cLen; j++)
		{
			if(j == 2)
			{
				break;
			}
			content += cells[j].innerText;
			
			if(j != cLen -1)
			{
				content += '   ';	
			}
		}
		array[i] = content;
	}
	return array;
}

function createNoUniteBd(tab,flag)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	//��һ�в���ӡ
	for(var i=1; i<len; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			if('unite' == flag)
			{
				if(j == 1)
				{
					continue;
				}
			}
			content += cells[j].innerText;
			if(j != cLen -1)
			{
				content += '   ';	
			}
		}
		array[i-1] = content;
	}
	return array;
}

function createBdArray(tab3,isUnite)
{
	var bdArray = new Array();
	
	if('1' == isUnite)
	{
		bdArray[0] = '*�˻��������Ϣ*'
		bdArray[1] = '====================';
		bdArray[2] = '�ϻ�������Ϣ[���/Ԫ]';
		bdArray = bdArray.concat(createUniteBd(tab3));
		var array = new Array('*�˻��������Ϣ*','====================','���˷�����Ϣ[���/Ԫ]');
		bdArray = bdArray.concat(array);
		bdArray = bdArray.concat(createNoUniteBd(tab3,'unite'));
	}
	else
	{
		var array = new Array('*�˻��������Ϣ*','====================','������Ϣ[���/Ԫ]');
		bdArray = bdArray.concat(array);
		bdArray = bdArray.concat(createNoUniteBd(tab3,'111'));
	}
	
	return bdArray;
}

function getBssTotalFee(tab)
{
	var bssFee = 0.00;
	var rows = tab.rows;
	var len = rows.length;
	var lastRow = rows[len-1];
	var cells = lastRow.cells;
	var cLen = cells.length;
	var lastCell = cells[cLen-1];
	bssFee = lastCell.innerText;
	return bssFee;
}

function createBssArray(tab)
{
	var array = new Array();
	var rows = tab.rows;
	var len = rows.length;
	var blankStr = '          ';
	
	//��β����ӡ
	for(var i=1; i<len-1; i++)
	{
		var content = '';
		var cells = rows[i].cells;
		var cLen = cells.length;
		for(var j=0; j<cLen; j++)
		{
			if(j == 0)
			{
				var title = cells[0].innerText;
				if(title.substring(0,10) != blankStr && '' != title)
				{
					content += '>>';
				}
			}
			if('' != cells[j].innerText)
			{
				content += cells[j].innerText;
				content += '   ';
			}
		}
		if('' == content)
		{
			break;
		}
		array[i-1] = content;
	}
	
	return array; 
}

function createAsvArray(tab)
{
	var array = new Array();
	array[0] = '>>���շ���ҵ�����';
	
	var rows = tab.rows;
	var len = rows.length;
	for(var i=1; i<len; i++)
	{
		var content = '';
		if(i != len -1)
		{
			var cells = rows[i].cells;
			var cLen = cells.length;
			content += cells[1].innerText;
			content += '    ';
			content += cells[cLen-1].innerText;
		}
		else
		{
			var cells = rows[i].cells;
			var cLen = cells.length;
			content += cells[0].innerText;
			content += '    ';
			content += cells[cLen-1].innerText;
		}
		
		array[i] = content;		
	}
	return array;
}

function createSsArray(tab4,tab5,tab6)
{
	var bssArray = new Array();
	
	bssArray[0] = '������ϸ[���/Ԫ]';
	bssArray[1] = '====================';
	
	bssArray = bssArray.concat(createBssArray(tab4));
	bssArray = bssArray.concat(createBssArray(tab5));
	var bssFee = '���úϼ�  ' + getBssTotalFee(tab5);
	var array = new Array(bssFee);
	bssArray = bssArray.concat(array);
	bssArray = bssArray.concat(createAsvArray(tab6));
	
	return bssArray;
} 


function createCommArray(tab1,tab2)
{
	var commArray = new Array();
	
	commArray[0] = '*ͨ����ʹ����Ϣ��ϸ*';
	
	commArray[1] = '====================';
	
	commArray = commArray.concat(createPkgAttr(tab1,'comm1'));
	
	commArray = commArray.concat(createPkgAttr(tab2,'111'));
	
	return commArray;
}

function createPkgAttr(tab,flag)
{
	var array = new Array();
	
	if(flag == 'comm1')
	{
		array[0] = '>>�ײͰ���ͨ����';
	}
	else
	{
		array[0] = '>>ʵ��ͨ��ʹ����';
	}
	
	var rows = tab.rows;
	var len = rows.length;
	
	//��һ�в���ӡ
	for(var i=1; i<len; i++)
	{
		var cells = rows[i].cells;
		var cLen = cells.length;
		
		/*
		var subArray = new Array();
		for(var j=0; j<cLen; j++)
		{
			if(j == 0)
			{
				subArray[0] = cells[0].innerText;
			}
			else
			{
				var strArray = cells[j].innerText.split('��');
				var strLen = strArray.length - 1;
				for(var k=0; k<strLen; k++)
				{
					strArray[k] = '      ' + (k+1) + '��' + strArray[k];
				}
				
				subArray = subArray.concat(strArray.slice(0,strLen));
			}
		}
		
		*/
		var text="";
		for(var j=0; j<cLen; j++)
		{
			text=text+" "+cells[j].innerText;
			
		}
		array.push(text);
		
	}
	return array;
}
function colQuerybill(type){
	var resuInfo="";
	var url="";
	if(type=="139Email"){
	 url= "${sessionScope.basePath }hubfeeservice/billColQuery139Email.action?isCurr=yes";
	 resuInfo="139��������˵���Ϣ�����쳣��";
	 }
	else if(type=="message") {url= "${sessionScope.basePath }hubfeeservice/billColQueryMessage.action?isCurr=yes";
	resuInfo="���Ž����˵���Ϣ�����쳣��";}
	else {
		
		resuInfo="���Ž����˵���Ϣ�����쳣��";
		url= "${sessionScope.basePath }hubfeeservice/billColQueryColorMessage.action?isCurr=yes";
		}
										var loader1 = new net.ContentLoader(url, netload = function () 
										{
											var resXml = this.req.responseXML;
											
											if (resXml)
											{
													var root = resXml.documentElement;
													
													
														
														document.getElementById("colBillQuery").innerHTML=root.text;
														wiWindow = new OpenWindow("colBillQueryWin1",708,392);
														return;
													
												    
												   				
											}
										    else
											{	
												
													document.getElementById("colBillQuery").innerHTML=resuInfo;
												    wiWindow = new OpenWindow("colBillQueryWin1",708,392);
												return;
											}								
										}, null, "POST", "", null);
	
}	
	</script>
</html>
