<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//add begin by cwx456134 2017-05-11 OR_SD_201703_234_ɽ��_���ӷ�Ʊ�Ż�����
//��ȡ���ӷ�Ʊ���Բ�����trueΪ����
TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
//add end by cwx456134 2017-05-11 OR_SD_201703_234_ɽ��_���ӷ�Ʊ�Ż�����
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">

		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		
		// �ύ�ı�־λ
		var submitFlag = 0;
		
		// ���ò�ѯ�ӿ�
		function selectType(startdate, enddate, cycle, acctid)
		{
			// ��Ʊ��ӡ���豸
			var prtObj;
			var isElectronInvoice = "<%=isElectronInvoice %>";
	        if ("true" != isElectronInvoice)
	        {
				try {
					
					prtObj = window.parent.document.getElementById("invprtpluginid");
					
				    // �򿪷�Ʊ��ӡ������
					var openCom = prtObj.OpenCom();
					if (openCom == 1) 
					{
						alertRedErrorMsg("����:��Ʊ��ӡ�����ڹ���!");
						return;
					} 
					else 
					{
						if (openCom == 61) 
						{
							alertRedErrorMsg("����:��Ʊ��ӡ������,�޷���ʼ��!");
							return;
						} 
						else if (openCom == 65) 
						{
							alertRedErrorMsg("����:��Ʊ��ӡ��ȱֽ!");
							return;
						} 
						else if (openCom != 0) 
						{
							alertRedErrorMsg("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
							return;
						}
					}
					
					// ��ʼ����Ʊ��ӡ��
					var initInvoicePrt = prtObj.InitVoicePrint();
					if (initInvoicePrt == 61) 
					{
						alertRedErrorMsg("����:��Ʊ��ӡ������,�޷���ʼ��!");
						return;
					} 
					else if (initInvoicePrt == 65) 
					{
						alertRedErrorMsg("����:��Ʊ��ӡ��ȱֽ!");
						return;
					}
					else if (openCom != 0) 
					{
						alertRedErrorMsg("����:���豸�쳣,�޷���ʼ����Ʊ��ӡ��!");
						return;
					}
					
					// ��鷢Ʊ��ӡ��ȱֽ
					var v = prtObj.CheckPaper();
					if (v != 0 )
					{
						alertRedErrorMsg("����:��Ʊ��ӡ��ȱֽ�����!");
					    return;
					}
				}
				catch (e) 
				{
					alertRedErrorMsg("����:��Ʊ��ӡ��������,�޷���ӡ��Ʊ!");
					return;
				}
	        }		
			//��ֹ�ظ��ύ
			if (submitFlag == 0) 
			{
				submitFlag = 1;	
				document.getElementById("cycle").value = cycle;
				document.getElementById("startdate").value = startdate;
				document.getElementById("enddate").value = enddate;
				document.getElementById("acctid").value = acctid;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }charge/printInvoice.action";
				document.actform.submit();
			}  
		}
		
	</script>
	</head>

	<body scroll="no">
		<form action="" name="actform" method="post">

			<!-- �ͻ��绰-->
			<input type="hidden" id="servnumber" name="servnumber"
				value="<s:property value='servnumber' />">
			<!-- �·� -->
			<input type="hidden" id="month" name="month"
				value="<s:property value='#request.month' />" />

			<!-- ������Ϣ -->

			<!-- ���� -->
			<input type="hidden" id="cycle" name="invoicePrint.billCycle"
				value="" />
			<!-- ��ʼʱ�� -->
			<input type="hidden" id="startdate" name="invoicePrint.startdate"
				value="" />
			<!-- ����ʱ�� -->
			<input type="hidden" id="enddate" name="invoicePrint.enddate"
				value="" />
			<!-- ���˺� -->
			<input type="hidden" id="acctid" name="invoicePrint.acctId" value="" />
			
			<%-- add begin jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż� --%>
			<%-- ��Ʊ��ӡ���� --%>
			<input type="hidden" id="invoiceType" name="invoiceType" value="Last"/>
			
			<!-- ��ӡ�½ᷢƱ��־��1����ӡ�½ᷢƱ -->
            <s:hidden id="monthInvoiceFlag" name="monthInvoiceFlag"/>
            
            <!-- �������ɱ�־��1���������� -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- �ɷ��ܽ�� -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- ƾ����ӡ��ǣ�1���Ѵ�ӡ -->
            <s:hidden name="printPayProFlag" id="printPayProFlag"/>
            
            <!-- �û���Ϣ�ַ��� -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- ��ӡȫ����Ʊ��־��1:����ӡ 2���Ѵ�ӡ -->
            <s:hidden name="printAllInvFlag" id="printAllInvFlag"/>
            
            <!-- �Ѵ�ӡ�ֻ������ַ��� -->
            <s:hidden id="printInvTelnum" name="printInvTelnum"/>
            
            <%--���˽ɷѱ�ʶ  "morePhones" ���˽ɷ�         ""���˽ɷ�  --%>
			<input type="hidden" name="morePhonesFlag" id="morePhonesFlag" value="<s:property value='morePhonesFlag' />"/>
            <%-- add end jWX216858 2015-4-16 OR_SD_201501_1063 �����ն�֧�����ɹ����Ż� --%>
    
			<%@include file="/titleinc.jsp"%>

			<div class="main" id="main">

				<%@ include file="/customer.jsp"%>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							��ֵ�������̣�
						</h2>
						<div class="blank10"></div>
						<a href="#">1. �����ֻ�����</a>
						<a href="#">2. ѡ��֧����ʽ</a>
						<a href="#">3. ѡ����</a>
						<a href="#">4. ֧��</a>
						<a href="#" class="on">5. ���</a>
					</div>

					<div class="box710W fl ml10 relative" style="margin-top: 10px;">
						<div class="bg"></div>
						<div class="top"></div>
						<div class="con relative">
							<div
								style="height: 444px; float: left; padding: 0px 0px 5px 0px; width: 610px; overflow: hidden;" id="inn">
								<table width="100%" class="tb_blue">
									<tr class="tr_color">
										<th width="20%" class='list_title'>
											�� ��
										</th>
										<th width="30%" class='list_title'>
											���ڿ�ʼʱ��
										</th>
										<th width="30%" class='list_title'>
											���ڽ���ʱ��
										</th>
										<th width="20%" class='list_title'>
											�� ��
										</th>
									</tr>
									<s:iterator value="cycleList" id="list" status="st">
										<tr class="tr_color">
											<td class="tc">
												<s:property value='#list.cycle' />
											</td>
											<td class="tc">
												<s:property value='#list.startdate' />
											</td>
											<td class="tc">
												<s:property value='#list.enddate' />
											</td>
											<td>
												<span class=" mr10 inline_block "> <a
													class="btn_bg_146" href="javascript:void(0);"
													onclick="selectType('<s:property value='#list.startdate' />', '<s:property value='#list.enddate' />', '<s:property value='#list.cycle' />', '<s:property value='#list.acctid' />' );return false;"
													onmousedown="" onmouseup="">��ӡ</a> </span>
											</td>
										</tr>
									</s:iterator>
								</table>
							</div>
		                    <%-- ������ --%>
	                       <div class="box70W fr">
	                            <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
	                            <div class="boxPage" style="width:75px; height:400px; ">
	                                <div class="blank10px"></div>
	                                <div class="box66W tc f16 lh30" id="gunDom" style="width:66px; height:36px; position:absolute; cursor:move;  left: 633px; top:52px; line-height:36px" >0%</div>
	                            </div>
	                            <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
	                        </div>
	                        <div class="clear"></div>
	                    </div>
	                    <div class="btm"></div>
	                </div>
	                <script type="text/javascript">
	                  myScroll = new virtualScroll("inn","gunDom");
	                </script>
                </div>
            </div>
			<%@include file="/backinc.jsp"%>
		</form>

	</body>
</html>
<script type="text/javascript">
// ���ؽɷ����ҳ��
function goback(menuid)
{
    // �������ɲſ��Է���
    if ("1" == '<s:property value="morePhoneFlag"/>')
    {
	    if (submitFlag == 0)
	    {
	        document.getElementById("curMenuId").value = menuid;
	        
	        document.forms[0].target = "_self";
	        
	        document.forms[0].action = "${sessionScope.basePath }charge/finish.action";
	        document.forms[0].submit();
	    }
    }   
}
</script>
