<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    // ������棬��ֹҳ����˰�ȫ���� 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <META HTTP-EQUIV="pragma" CONTENT="no-cache">
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js?ver=${jsVersion }"></script>
        
    </head>
    <body scroll="no" onload="doLoad();">
        <form name="actform" method="post">
            <!-- �ֻ����� -->
            <s:hidden id="servNumber" name="servNumber"></s:hidden>
            
            <!-- �ֻ����� -->
            <s:hidden id="servnumber" name="chargeLogVO.servnumber"></s:hidden>
            
            <!-- ʡ�ݱ��� -->
            <s:hidden id="provinceCode" name="chargeLogVO.provinceCode"></s:hidden>
            
            <!-- �ͻ����� -->
            <s:hidden id="custName" name="chargeLogVO.custName"></s:hidden>
            
            <!-- ֧����ʽ 1:��������4���ֽ� -->
            <s:hidden id="payType" name="chargeLogVO.payType"></s:hidden>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <%@ include file="/jsp/customize/sd/feeCharge/common/chargeHeader.jsp"%>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40">
                            <p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="servNumber" /></span></p>
                            <s:if test="chargeLogVO.yingjiaoFee == 0.00">
                                <p class="fs22 fwb pl40 lh30">������<span class="yellow fs22"><s:property value="chargeLogVO.balance" /></span> Ԫ</p>
                            </s:if>
                            <s:else>                            
                                <p class="fs22 fwb pl40 lh30">Ӧ�����ѣ�<span class="yellow fs22"><s:property value="chargeLogVO.yingjiaoFee" /></span> Ԫ</p>
                            </s:else>
                            <div class="blank10"></div>
                            <div class="line"></div>
                            <div class="blank10"></div>
                            <p class="tit_arrow fs22"><span class="bg"></span>ѡ��֧����ʽ��</p>
                            <div class="blank20"></div>
                            <div class="blank5"></div>
                            <ul class="pay_way_list clearfix">
                                <s:iterator value="chargeLogVO.usableTypes" id="type" status="st">
                                    <s:if test="#st.index == 0">                                        
                                        <li class="mr70">                                           
                                    </s:if>
                                    <s:else>
                                        <li>                                            
                                    </s:else>
                                    <a href="javascript:void(0);" id="<s:property value="%{#type.menuid}"/>" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />','0'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>                                      
                                    </li>
                                </s:iterator>
                            </ul>                               
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
</html>
<script type="text/javascript">
document.getElementById("highLight2").className = "on";

//��ֹҳ���ظ��ύ
var submitFlag = 0;

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

// ������һҳ
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "<s:property value='servNumber' />�˳���ֵ���ѹ���");
        
        document.getElementById("curMenuId").value = menuid;
                
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }nonlocalChargeSD/inputNumber.action";
        document.forms[0].submit();
    }
}

// ҳ�����
function doLoad()
{
    if ("<s:property value='#parameters.checkCashFlag'/>" == "casherror")
    {
        document.getElementById("nonlocalCashCharge").style.display = "none";
    }
    
    if ("<s:property value='#parameters.checkCardFlag'/>" == "carderror")
    {
        document.getElementById("nonlocalCardCharge").style.display = "none";
    }
}

// �ύ
function doSub(menuid, url)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "<s:property value='servNumber' />ѡ�񽻷ѷ�ʽ��" + menuid);
        
        if (url == "nonlocalChargeSD/cardCharge.action")
        {
            // ���������ѣ��жϴ�ʱ������Ƿ����
            var url1 = "${sessionScope.basePath}charge/checkTime.action";
            
            var loader = new net.ContentLoader(url1, netload = function () {
                var resXml1 = this.req.responseText;
                
                if (resXml1 == "1" || resXml1 == 1)
                {
                    // ����֧����ʽ
                    document.getElementById("payType").value = '1';
                    document.actform.target = "_self";
                    document.actform.action = "${sessionScope.basePath}" + url;
                    document.actform.submit();
                }
                else
                {
                    submitFlag = 0;
                    
                    alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
                    
                    return;
                }
            }, null, "POST", null, null);
        }
        else
        {
            // ����֧����ʽ
            document.getElementById("payType").value = '4';
            document.actform.target = "_self";
            document.actform.action = "${sessionScope.basePath}" + url;
            document.actform.submit();
        }
    }           
}

</script>
