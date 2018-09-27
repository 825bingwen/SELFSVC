<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>

<%
    // ������棬��ֹҳ����˰�ȫ���� 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    String pOrgName = termInfo.getOrgname();
    String pTerminalInfo = termInfo.getTermname();
    
    String termSpecial = termInfo.getTermspecial();
    
    String canPrintInvoice = termSpecial.substring(1, 2);
            
    // �Ƿ���Ҫ�������
    String needRandomPwd = (String)request.getAttribute("needRandomPwd");
    
    //�½ᷢƱ��ť����
    String SH_YGZ_SWITCH = (String) PublicCache.getInstance().getCachedData("SH_YGZ_SWITCH");
    
    //Ԥ�淢Ʊ��ʾ��Ϣ
    String printConfirm = (String) PublicCache.getInstance().getCachedData("SH__PRINT_INVOICE_MSG");
    if (printConfirm == null || "".equals(printConfirm))
    {
        printConfirm = "�𾴵��û����ã���Ӫ�����������˰���Ź涨���Ͻ��ظ���Ʊ���鿪��Ʊ���������Ҫȫ����½ᷢƱ����������Ҫ��ӡԤ�淢Ʊ��";
    }
    
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
	// ʡ����Ϣ
    String provinceInfo = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String provinceSD = Constants.PROOPERORGID_SD;
    
    // �ɷ���ɺ��½��Ȩ���ӡ��Ʊ����        1:����    0:�ر�
    String printInvoiceNew = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRINT_INVOICENEW);
    
    // �ɷ���ɺ��½��Ȩ���ӡ��Ʊ���ش�
    String printInvoiceOpen = Constants.PRINT_INVOICE_OPEN;
    // modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>�ƶ������ն�</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
        <script type="text/javascript">
        var submitFlag = 0;
        
        var payType = "<s:property value='payType' />";
        
        var limitTime = 30;//ȡ��ʱ��30��
    
        var invoiceType = "";
        
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
            
            //����
            if (key == 82 || key == 220) 
            {
                goback("<s:property value='curMenuId' />");
            }           
        }
        
        </script>
    </head>
    <body onload="window.focus();doFinish();" scroll="no">
        <form name="payMoneyForm" method="post">
            <input type="hidden" id="invoiceType" name="invoiceType" value=""/>
            
            <s:hidden name="servnumber" id="servnumber"/>
            
            <!-- ��ӡ�½ᷢƱ��־��1����ӡ�½ᷢƱ -->
            <s:hidden id="monthInvoiceFlag" name="monthInvoiceFlag"/>
            
            <!-- �������ɱ�־��1���������� -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- �ɷ��ܽ�� -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- ƾ����ӡ��ǣ�1���Ѵ�ӡ -->
            <s:hidden name="printPayProFlag" id="printPayProFlag"/>
            
            <!-- �½ᷢƱ��ӡ���� -->
            <s:hidden name="invoicePrint.billCycle" id="month" />
            
            <!-- ��ӡȫ����Ʊ��־��1:����ӡ-->
            <s:hidden name="printAllInvFlag" id="printAllInvFlag"/>
            
            <!-- �û���Ϣ�ַ��� -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- �Ѵ�ӡ�ֻ������ַ��� -->
            <s:hidden id="printInvTelnum" name="printInvTelnum"/>
            
            <s:hidden name="recoid" id="recoid" />
            
            <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
			<!--Ԥ�桢�½��ӡ��ʶ -->
			<input type="hidden" name="printFlag" id="printFlag" value="<s:property value='printFlag' />"/>
			
			<!--��ӡȫ����Ʊ��ʶ -->
			<input type="hidden" name="printAllFlag" id="printAllFlag" value="<s:property value='printAllFlag' />"/>
			
			<!-- ���˽ɷѱ�ʶ -->
			<input type="hidden" name="morePhonesFlag" id="morePhonesFlag" value="<s:property value='morePhonesFlag' />"/>
			<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>��ֵ�������̣�</h2>
                        <div class="blank10"></div>
                        <a href="#">1. �����ֻ�����</a> 
                        <a href="#">2. ѡ��֧����ʽ</a> 
                        <a href="javascript:void(0)">3. ��������</a>
                        <a href="javascript:void(0)">4. ����������</a>
                        <a href="javascript:void(0)">5. ��������</a>
                        <a href="javascript:void(0)" class="on">6. ���</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank30"></div>
                        <div class="p40 pr0">
                            <div class="recharge_result tc">
                              <p class="title yellow ">���ĳ�ֵ��������ɡ�</p>
                              <p class="desc pt5">�뱣������Ľ���ƾ�������ٴ�ȷ����ȡ��������������</p>          
                            </div>
                            <div class="blank20"></div>
                            <div class="line w625"></div>
                            <div class="blank10"></div>
                            <div class="tit_info fl"><span class="bg"></span></div> 
                            <table width="540px "height="40px" style="line-height:40px;" class="telnum_list2 fl fs20" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <th class="bdb1px" width="15%">�ɷѽ��</th>
                                    <th class="bdb1px" width="28%">�ֻ�����</th>
                                    <th class="bdb1px">���</th>
                                    <th class="bdb1px">��ӡ</th>
                                </tr>            
                            </table>
                            <div id="sucessTable" class="tabdiv">
                                <table width="540px" class="telnum_list2 fs20"  cellpadding="0" cellspacing="0" border="0" id="tablelen">
                                    <s:iterator value="morePhones" id="morePhone" status="st">
                                        <tr>
                                            <s:if test="0 == #morePhone.chargeStatus">
                                                <td class="yellow" width="15%">�ɹ�</td>
                                            </s:if>
                                            <s:else>
                                                <td style="color:red" width="15%">ʧ��</td>
                                            </s:else>
                                            <td class="yellow" width="28%"><s:property value="#morePhone.servnumber"/></td>
                                            <td class="yellow"><s:property value="#morePhone.tMoney"/>Ԫ</td>
                                            <td>
                                              <s:if test="0 == #morePhone.chargeStatus">
                                                 <%
	                                                if ("1".equals(canPrintInvoice))
	                                                {       
	                                             %>
	                                                <s:if test="printInvTelnum.indexOf(#morePhone.servnumber) > 0">
		                                                <a class="print" id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">��ӡ��Ʊ</a>
	                                                </s:if>
	                                                <s:else>
	                                                    <a id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">��ӡ��Ʊ</a>
	                                                </s:else>
	                                             <%}
	                                               else
	                                               {
		                                         %>
	                                                <a class="print" id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">��ӡ��Ʊ</a>
	                                              <%}%>
                                              </s:if>
                                              <s:else>
                                                  <a href="javascript:void(0);" id="print_<s:property value='#st.index + 1'/>" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">��ӡ��Ʊ</a>
                                              </s:else>
                                                  <%
                                                    if ("1".equals(SH_YGZ_SWITCH))
                                                    {
                                                  %>
                                                    <a href="javascript:void(0);" onclick="printMonthInvoice('<s:property value="#morePhone.servnumber"/>');return false;">��ӡ�½�</a>
                                                 <%} 
                                                   else
                                                   {
                                                 %>
                                                    <a href="javascript:void(0);" class="print" disabled="true" onclick="printMonthInvoice('<s:property value="#morePhone.servnumber"/>');return false;">��ӡ�½�</a>
                                                  <%} %>
                                            </td>            
                                        </tr>
                                    </s:iterator>
                                </table>  
                            </div>
                            <div class="box70W scrollbtn" id="scrollbar">
                                <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                                <div class="boxPage" style="width:75px; height:80px; ">
                                    <div class="blank10px"></div>
                                    <div class="box66W tc f16 lh30" id="gunDom" style=" position:absolute; width:66px; height:36px; top:52px; left:3px; cursor:move; line-height:36px" >0%</div>
                                </div>
                                <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                            </div>
                            <table width="540px" class="telnum_list2 fl fs20 ml40 yellow" style="_margin-left:20px;" cellpadding="0" cellspacing="0" border="0">
                                <tr class="trbg" align="left">
                                    <td width="15%">�ϼƣ�</td>
                                    <td width="28%"></td>
                                    <td><s:property value="totalFee"/>Ԫ</td>
                                    <td></td>            
                                </tr>
                            </table>  
                            <div class="multi_sucessbtn">
                                <div class="btn_box clearfix" style="width:580px;padding-left:30px;">
                                	
                                	<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
                                	<% 
                                	if (!printInvoiceNew.equals(printInvoiceOpen))
									{
									%>
                                    <s:if test="1 == printAllInvFlag">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">��ӡȫ����Ʊ</a>
                                    </s:if>
                                    <s:elseif test="'' != printInvTelnum">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">��ӡȫ����Ʊ</a>
                                    </s:elseif>
                                    <s:else>
                                        <a href="" id="printAllInv" onclick="printAllInvoice(); return false;">��ӡȫ����Ʊ</a>
                                    </s:else>
                                    <%
                                    }
                                    else
                                    {
                                    %>
                                    <s:if test="'' != printInvTelnum">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">��ӡȫ����Ʊ</a>
                                    </s:if>
                                    <s:else>
                                        <a href="" id="printAllInv" onclick="printAllInvoice(); return false;">��ӡȫ����Ʊ</a>
                                    </s:else>
                                    <%
                                    }
                                    %>
                                    <%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
                                    
                                    <a href="" onclick="goHomePage();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">��������</a>
                                    <a href="" onclick="continueCharge();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">������ֵ����</a>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
                <div class="popup_confirm" id="popup_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">��ʾ��</div>
                    <div class="tips_body">
                        <p>������ӡ��Ʊ��Ϊ��ȷ������Ϣ�İ�ȫ��ϵͳ���·�����У���뵽�����ֻ����뽫�յ��Ķ���У���������¸�ҳ���У������ڡ�</p>
                        <div class="blank10"></div>
                        <p class="mt30">��ȷ���Ƿ������</p>
                    </div>
                    <div class="btn_box tc mt20">
                        <span class=" mr10 inline_block ">
                            <a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="sendRandomPwd();return false;">ȷ��</a>
                        </span>
                        <span class=" inline_block ">
                            <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();submitFlag=0;">ȡ��</a>
                        </span>
                    </div>
                </div>
                
                <%-- ��Ʊ��ӡ��ʾ��Ϣ add by sWX219697 2014-7-22 17:54:15 OR_huawei_201407_1042_�����նˣ�ɽ������ֵ��Ʊ��ӡ�Ż�--%>
                <div class="popup_confirm" id="print_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">��ʾ��</div>
                    <div class="tips_body">
                        <p><%=printConfirm%></p>
                        <div class="blank10"></div>
                        <p class="mt30">��ȷ���Ƿ������</p>
                    </div>
                    <div class="btn_box tc mt20">
                        <span class=" mr10 inline_block ">
                            <a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">����ӡ</a>
                        </span>
                        <span class=" inline_block ">
                            <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="printInvoice('Last','0');return false;">������ӡԤ�淢Ʊ</a>
                        </span>
                    </div>
                </div>
                                
                <script type="text/javascript">
                myScroll = new virtualScroll("sucessTable","gunDom");
                openWindow = function(id)
                {
                    wiWindow = new OpenWindow(id, 708, 392);//�򿪵�����������
                }               
                </script>
            </div>
            
            <%@ include file="/backinc.jsp"%>           
        </form>
    </body>
</html>
<script type="text/javascript">
function goback(menuid)
{
    if (submitFlag == 0)
    {
        document.getElementById("curMenuId").value = menuid;
        
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
        document.forms[0].submit();
    }
}       

function sendRandomPwd()
{
    document.getElementById("invoiceType").value = invoiceType;
            
    document.payMoneyForm.target="_self";
    document.payMoneyForm.action="${sessionScope.basePath }charge/validateByRandomPwd.action";
    document.payMoneyForm.submit();
}

// ��ȡ������ȡ��״̬
function takeOutBankCardStatus() 
{
    limitTime = limitTime - 1;
    
    if (limitTime <= 0)
    {
        //��ʱ�������ʱ����ͬʱ�̿�
        clearInterval(waitingToken);
        
        //�̿�
        CaptureBankCard();
        
        return;
    }
    
    try 
    {
        //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
        var ret = OpenComByCard();
    
        ret = getCardPosition();
        
        //������
        if (ret == "1" || ret == 1) 
        {   
            //�Ѿ�ȡ�߿��������ʱ����
            clearInterval(waitingToken);                                    
        }           
    }
    catch (e){}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
}

function startClock()
{
    try 
    {
        waitingToken = setInterval("takeOutBankCardStatus()", 1000);
    }
    catch (e) {}//���Ѿ��˳��������ڼ��ȡ��״̬ʱ�����쳣��Ҳ�����κδ�����
}

function doFinish()
{
	<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
	// �����֤��ʱ��
	window.parent.currLeftTime = "";
	window.parent.currNumber = "";
	<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
	
    // ƾ��û�д�ӡ����ȥ��ӡƾ��
    if ("1" != document.getElementById("printPayProFlag").value)
    {
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	            "���ѳɹ�");
	    
	    //������ 0���ڶ������ڣ�-1 ��ȡʧ�ܣ�1���������޿���2 �ֿ�״̬(�˿���δȡ��)
	    var ret = getCardPosition();
	    
	    //�¿��ɹ���������ʱ���񣬿��û��Ƿ���30����ȡ�������������û�У��̿�
	    if (ret == "2")
	    {
	        startClock();
	    }
	    
	    // ƾ�����Ϊ�Ѵ�ӡ
	    document.getElementById("printPayProFlag").value = "1";
	    
	    // �ֻ�����
	    var pServNumber = "";
	    
	    // �ն˽�����ˮ
	    var pTerminalSeq = "";
	    
	    // ������ˮ
	    var pDealNum = "";
	    
	    // �ͻ�����
	    var pCustName = "";
	    
	    // ����ҵ��ɷѽ��
	    var tMoney = "";
	    
	    var pStatus = "";
	    <s:iterator value="morePhones" id="morePhone">
		     pServNumber = pServNumber + "<s:property value='#morePhone.servnumber' />" + ",";
		     pTerminalSeq = pTerminalSeq + "<s:property value='#morePhone.chargeLogOID' />" + ",";
		     pDealNum = pDealNum + "<s:property value='#morePhone.dealnum'/>" + ",";
		     pCustName = pCustName + "<s:property value='#morePhone.custName'/>" + ",";
		     tMoney = tMoney + "<s:property value='#morePhone.tMoney'/>" + ",";
		     pStatus = pStatus + "<s:property value='#morePhone.chargeStatus'/>" + ",";
	    </s:iterator>
	    var pOrgName = "<%=pOrgName%>";  //��ӡ�ص�
	    var pPrintDate = getDateTime();  //��ӡ����
	    var pTerminalInfo = "<%=pTerminalInfo%>"; //�ն���Ϣ
	    var pDealTime = "<s:property value='pDealTime' />";   //����ʱ��
	    var totalFee = "<s:property value='totalFee' />";     //���׽��
	    
	    var printcontext = "<s:property value='printcontext' />";// ����СƱ
	    
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	            "�Զ���ӡƾ��");
	    
	    doPrintMorePhoneSD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,totalFee,
	                pTerminalSeq, pCustName, pStatus, printcontext);
    }
    
}
//������ֵ�ɷ�
function continueCharge()
{   
    document.payMoneyForm.target = "_self";
    document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
    document.payMoneyForm.submit();
}

// ��ӡ�½ᷢƱ
function printMonthInvoice(telnum)
{   
    // ��ӡ�½ᷢƱ��־��1����ӡ�½ᷢƱ
    document.getElementById("monthInvoiceFlag").value = "1";
    document.getElementById("servnumber").value = telnum;
    document.getElementById("month").value = <%=CommonUtil.getLastMonth("yyyyMM", 1)%>;
    document.payMoneyForm.target = "_self";
    
    <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
	// ��Ʊ��ӡ��½��Ȩ 
	if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
	{
		// �½��ʶ
		document.getElementById("printFlag").value = "YJ";
		document.getElementById("printAllFlag").value = "";
		document.payMoneyForm.action = "${sessionScope.basePath}charge/goServicePwdPage.action";
	}
	else
	{
    	document.payMoneyForm.action = "${sessionScope.basePath}charge/qryBillCycle.action";
    }
    <%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�--%>
    
    document.payMoneyForm.submit();
}

//��ӡ��ǰ��Ʊ���ж��ŷ�Ʊ��ӡ���һ��
function printInvoice(invoicetype,flag,telnum,oid)
{
    // ���˽ɷѱ�־
	document.getElementById("morePhonesFlag").value = "morePhones";
	document.getElementById("printFlag").value = "";
    if (flag == "1")
    {
    	document.getElementById("printAllFlag").value = "";
        document.getElementById("monthInvoiceFlag").value = "0";
        document.getElementById("servnumber").value = telnum;
        document.getElementById("recoid").value = oid;
        openWindow('print_confirm');
        return;
    }
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            telnum + "ѡ���ӡ��Ʊ");
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        invoiceType = invoicetype;
        
        //��ӡ��Ʊʱ��Ҫ�������
        if ("1" == "<%=needRandomPwd %>")
        {
            openWindow('popup_confirm');
        }
        else
        {
            document.getElementById("invoiceType").value = invoicetype;
            document.forms[0].target = "_self";
            
            // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
			// ��Ʊ��ӡ��½��Ȩ
			if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
			{
				document.forms[0].action = "${sessionScope.basePath}charge/goServicePwdPage.action";
			}
			else
			{
            	document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
            }
            // modify end by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
            
            document.forms[0].submit();
        }
    }               
}

// ��ӡȫ����Ʊ
function printAllInvoice()
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        // ����ӡȫ����Ʊ��־Ϊ��ӡ
        document.getElementById("printAllInvFlag").value = "1";
        document.getElementById("invoiceType").value = "Last";
        document.forms[0].target = "_self";
        
        // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_ɽ��_�����ն˽ɷ�ʱֱ�Ӵ�ӡ��Ʊ���Ż�
		// ��Ʊ��ӡ��½��Ȩ��
		if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
		{
			submitFlag = 0;
			
		    // ȫ����ӡ��ʶ
			document.getElementById("printAllFlag").value = "all";
			document.getElementById("monthInvoiceFlag").value = "0";
			
			// �½��ʶ
			document.getElementById("printFlag").value = "";
			printInvoice('Last','0');
			return false;
		}
		else
		{
        	document.forms[0].action = "${sessionScope.basePath }charge/printAllInvoice.action";
        	document.forms[0].submit();
        }
    }  
}
</script>

