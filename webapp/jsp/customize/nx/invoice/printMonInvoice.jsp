<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%             
    // ������棬��ֹҳ����˰�ȫ���� 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title></title>
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
    </head>
    
    <body onload="window.focus();doFinish();" scroll="no">
        <form id="payMoneyForm" name="payMoneyForm" method="post">
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main" align="center">
                
                <%@ include file="/customer.jsp"%>
                
                <div class="div_blank_170w538h"></div>
                <div class="pl30" >                     
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40 pr0">
                            <div class="blank20"></div>
                            <div class="line w625"></div>
                            <div class="blank30"></div>
                            <div class="recharge_result print tc">
                                <p id="resultMsg1" class="title yellow pt30">��Ʊ���ڴ�ӡ</p>
                                <p id="resultMsg2" class="desc pt20">���Ժ����������</p>
                                <div class="btn_box4 clearfix">
                                    <a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continuePrint();return false;">������ӡ</a>                              
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%@ include file="/backinc.jsp"%>           
        </form>
    </body>
</html>
<script type="text/javascript">

    // ��ֹҳ���ظ��ύ
    var submitFlag = 0;
    
    document.onkeydown = pwdKeyboardDown;
    
    // ��������
    function pwdKeyboardDown(e)
    {   
        var key = GetKeyCode(e);
        
        // ����
        if (key == 77) 
        {
            preventEvent(e);
        }
        
        if (!KeyIsNumber(key))
        {
            return false;//��仰��ؼ�
        }
    }
    
    // ��������У��
    function KeyIsNumber(KeyCode) 
    {
        // ֻ��������0-9
        if (KeyCode >= 48 && KeyCode <= 57)
        {
            return true;
        }
        
        return false;
    }   
    
    document.onkeyup = pwdKeyboardUp;
    
    // ����̧��
    function pwdKeyboardUp(e) 
    {
        var key = GetKeyCode(e);
        
        // ����
        if (key == 82 || key == 220)
        {
            goback("<s:property value='curMenuId' />");
        }       
    }
    
    // ȷ��
    function doFinish() 
    {
    	printMonInvoiceNX("<s:property value='monInvoicePO.printTime'/>",
                        "<s:property value='monInvoicePO.customerName'/>",
                        "<s:property value='monInvoicePO.fundType'/>",
                        "<s:property value='regionName'/>",
                        "<s:property value='monInvoicePO.fee'/>",
                        "<s:property value='monInvoicePO.telnum'/>",
                        "<s:property value='monInvoicePO.contractNum'/>",
                        "<s:property value='monInvoicePO.formNum'/>",
                        "<s:property value='monInvoicePO.invoiceFee'/>",
                        "<s:property value='operid'/>",
                        "<s:property value='monInvoicePO.commServFee'/>",
                        "<s:property value='monInvoicePO.commServFeeName'/>",
                        "<s:property value='monInvoicePO.sellDiscount'/>");
                        
        document.getElementById("resultMsg1").innerHTML = "���ķ�Ʊ�Ѵ�ӡ�ɹ���";
        document.getElementById("resultMsg2").innerHTML = "�뱣������ķ�Ʊ��";
    }
    
    // ���ز�ѯδ��Ʊҳ��
    function goback(menuid)
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
        
            document.getElementById("curMenuId").value = menuid;
            document.getElementById("payMoneyForm").action = "${sessionScope.basePath}monInvoice/qryBillCycle.action";
            document.getElementById("payMoneyForm").submit();
        }
    }
    
    // ������ӡ
    function continuePrint()
    {   
        document.getElementById("payMoneyForm").action = "${sessionScope.basePath}monInvoice/qryCurrentMonth.action";
        document.getElementById("payMoneyForm").submit();
    }
</script>