<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants" %>

<%
	// �ն���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
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
	
	Vector vector = (Vector)request.getAttribute("query");
	String error = (String)request.getAttribute("error");
	if (error != null && error.length() > 0)
	{
	    if (error.indexOf("information") >= 0)
	    {
	        error = "�޼�¼���뷵�ؼ�����ѯ";
	    }
	}
	
	//modify swx219697 2015-2-6 09:03:06 ɽ����������û��³���δ�۷�ʱ��ʾ�հ���Ϣ
	if(vector != null && null == vector.get(0) && null == vector.get(1) && null == vector.get(2))
	{
		error = "000";
	}
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�³��۷Ѳ�ѯ</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script language="JavaScript" type="text/javascript">
var submitFlag = 0;

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
		
		document.getElementById("curMenuId").value = '<%=menuInfo.getParentid() %>';
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }feeservice/feeServiceFunc.action";
		document.actform.submit();
		
	}			
}

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
	
	<%
	if ("1".equals(keyFlag))
	{
	%>
		// 0��
		if (key == 48)
		{
			//topGo('qryBill', 'feeservice/feeServiceFunc.action');
			goback("<s:property value='curMenuId' />");
			return;
		}
		// �Ϸ�
		else if (key == 85)
		{
			myScroll.moveUp(30);
			return;
		}
		// �·�
		else if (key == 68)
		{
			myScroll.moveDown(30);
			return;
		}		
	<%
	}
	%>
}
</script>
</head>
<body scroll="no">
	<form id="actform" name="actform" method="post">
		<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
		 		<%@ include file="/customer.jsp"%>
		 		
		 		<%
                	if (Constants.PROOPERORGID_SD.equalsIgnoreCase(province)) 
                	{
                %>
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">����<%=parentMenuName %>&nbsp;</a>
				<%
                	}
                	else if ("1".equals(keyFlag))
                	{
                %>
                <a href="#" class="bt10_1 fr mr92" onmousedown="this.className='bt10_1on fr mr92'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">����<%=parentMenuName %>&nbsp;(��0��)</a>
                <%
                	}
                	else
                	{
                %>	 		
                <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">����<%=parentMenuName %>&nbsp;</a>
                <%
                	}
                %>
				
        		<!--������-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">					
							<div class="div747w444h">
								<!-- �б����� -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc p747w411h" id="inn">
									
<%
						            if (error == null)
						            {
						%>
						<%
						                if (null != vector && vector.size() > 0)
						                {
						                    List listBaseFee = (ArrayList)vector.get(0);//�ײ���Ϣ��
						                    List listFuncFee = (ArrayList)vector.get(1);//���ܷ�
						                    List listSumPackage = (ArrayList)vector.get(2);//���·�
						                    if (null != listBaseFee && listBaseFee.size() > 0)
						                    {
						%>
						<table class="tb_blue" width="100%">
							<tr class="tr_color">
								<th colspan="100%" class="tc">�ײ���Ϣ��</th>
							</tr>
							<tr class="tr_color">
								<th width='60%' align='center' class='list_title'>
									��������
									<!-- �������� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									��Ԫ��
									<!-- ��Ԫ�� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									ʱ��
									<!-- ʱ�� -->
								</th>
							</tr>
							<%
							List baseFee = null;
							for (int row = 0; row < listBaseFee.size(); row++)
							{
								baseFee = (List) listBaseFee.get(row);
							%>
							<tr class="tr_font">
								<td width='60%' align='center'>
									<%=baseFee.get(0)%>
								</td>
								<td width='20%' align='center'>
									<%=baseFee.get(1)%>
								</td>
								<td width='20%' align='center'>
									<%=baseFee.get(2)%>
								</td>
							</tr>								
							<%
							}
							 %>
						</table>
						<%
						}
						%>
						<%
		                    if (null != listFuncFee && listFuncFee.size() > 0)
		                    {
						%>
						<br>
						<table class="tb_blue" width="100%">
							<tr class="tr_color">
								<th colspan="100%" class="tc">���ܷ�</th>
							</tr>
							<tr class="tr_color">
								<th width='60%' align='center' class='list_title'>
									��������
									<!-- �������� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									��Ԫ��
									<!-- ��Ԫ�� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									ʱ��
									<!-- ʱ�� -->
								</th>
							</tr>
							<%
								List funcFee = null;
								for (int row=0; row < listFuncFee.size(); row++)
								{
									funcFee = (List) listFuncFee.get(row);
							%>
							<tr class="tr_font">
								<td width='60%' align='center'>
									<%=funcFee.get(0)%>
								</td>
								<td width='20%' align='center'>
									<%=funcFee.get(1)%>
								</td>
								<td width='20%' align='center'>
									<%=funcFee.get(2)%>
								</td>
							</tr>
							<%
								}
							 %>
							
						</table>
						<%
						}
						%>
						<%
	                    if (null != listSumPackage && listSumPackage.size() > 0)
	                    {
						%>
						<br>
						<table class="tb_blue" width="100%">
							<tr class="tr_color">
								<th colspan="100%" class="tc">���·�</th>
							</tr>
							<tr class="tr_color">
								<th width='60%' align='center' class='list_title'>
									��������
									<!-- �������� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									��Ԫ��
									<!-- ��Ԫ�� -->
								</th>
								<th width='20%' align='center' class='list_title'>
									ʱ��
									<!-- ʱ�� -->
								</th>
							</tr>
							<%
							List packageFee = null;
							for (int row = 0; row < listSumPackage.size(); row++)
							{
								packageFee = (List) listSumPackage.get(row);
							%>
							<tr class="tr_font">
								<td width='60%' align='center'>
									<%=packageFee.get(0)%>
								</td>
								<td width='20%' align='center'>
									<%=packageFee.get(1)%>
								</td>
								<td width='20%' align='center'>
									<%=packageFee.get(2)%>
								</td>
							</tr>
							<%
							}
							 %>								
						</table>
						<%
						}
						%>
						<%
				           }
				            }
				            
				            //���ܷѣ����·ѣ��ײͷѾ�Ϊ0ʱ��ҳ����ʾ�����ݡ�
				            else if("000".equals(error) && Constants.PROOPERORGID_SD.equalsIgnoreCase(province))
				            {
						%>
							<table class="tb_blue" width="100%">
								<tr class="tr_color">
									<th colspan="100%" class="tc">�ײ���Ϣ��</th>
								</tr>
								<tr class="tr_color">
									<th width='60%' align='center' class='list_title'>
										��������
										<!-- �������� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										��Ԫ��
										<!-- ��Ԫ�� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										ʱ��
										<!-- ʱ�� -->
									</th>
								</tr>
								<tr class="tr_font">
									<td colspan="3" align="left" class="list_title">
										������
									</td>
								</tr>	
							</table>
							<br>
							<table class="tb_blue" width="100%">
								<tr class="tr_color">
									<th colspan="100%" class="tc">���ܷ�</th>
								</tr>
								<tr class="tr_color">
									<th width='60%' align='center' class='list_title'>
										��������
										<!-- �������� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										��Ԫ��
										<!-- ��Ԫ�� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										ʱ��
										<!-- ʱ�� -->
									</th>
								</tr>
								<tr class="tr_font">
									<td colspan="3" align="left" class="list_title">
										������
									</td>
								</tr>
							</table>
							<br>
							<table class="tb_blue" width="100%">
								<tr class="tr_color">
									<th colspan="100%" class="tc">���·�</th>
								</tr>
								<tr class="tr_color">
									<th width='60%' align='center' class='list_title'>
										��������
										<!-- �������� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										��Ԫ��
										<!-- ��Ԫ�� -->
									</th>
									<th width='20%' align='center' class='list_title'>
										ʱ��
										<!-- ʱ�� -->
									</th>
								</tr>
								<tr class="tr_font">
									<td colspan="3" align="left" class="list_title">
										������
									</td>
								</tr>
							</table>
						
						<%
						}
						else
						{
						%>
							<table class="tb_blue" width="100%">
								<tr>
									<td colspan="2" align="left" class="list_title">
										<%=error%>
									</td>
								</tr>
							</table>
						<%
						}
						%>
								</p>								
								<!-- �б����� -->
							</div>
							</div>								
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								
								<%
                				if (Constants.PROOPERORGID_HUB.equalsIgnoreCase(province)) 
                				{
                				%>
									<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
								<%
								}
								else
								{ 
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								}
								%>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
		    </div>
			<%@ include file="/backinc.jsp"%>	
	</form>
</body>
</html>
