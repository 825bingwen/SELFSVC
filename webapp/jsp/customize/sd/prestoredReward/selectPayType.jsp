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
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
    //��ֹҳ���ظ��ύ
    var submitFlag = 0;
    
    //82��220 ����
    
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
        
    function goback(menuid)
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                    "<s:property value='telNum' />�˳����ѹ���");
            
            document.getElementById("curMenuId").value = menuid;
                    
            document.forms[0].target = "_self";
            document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
            document.forms[0].submit();
        }
    }
    
    //�ύ���ѷ�ʽ��flagΪ0ʱ����ʾ��ؽɷ���Ϣ��Ϊ1ʱ����Ҫ��ʾ��ֱ���ύ
    function doSub(menuid, url,flag)
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                    "<s:property value='telNum' />ѡ�񽻷ѷ�ʽ��" + menuid);
            
            if (url == "prestoredReward/cardCharge.action")
            {
                // ���������ѣ��жϴ�ʱ������Ƿ����
                var url1 = "${sessionScope.basePath}prestoredReward/checkTime.action";
                
                var loader = new net.ContentLoader(url1, netload = function () {
                    var resXml1 = this.req.responseText;
                    
                    if (resXml1 == "1" || resXml1 == 1)
                    {
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
                document.actform.target = "_self";
                document.actform.action = "${sessionScope.basePath}" + url;
                document.actform.submit();
            }
        }           
    }
    
</script>
</head>
    <body scroll="no">
        <form name="actform" method="post">
        
        <!-- �ֻ��� -->
        <input type="hidden" id="telNum" name="telNum" value="<s:property value='#request.telNum' />"/>
        
        <!-- ����� -->
        <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
        
        <!-- ���α��� -->
        <input type="hidden" id="actLevelId" name="actLevelId" value='<s:property value="#request.actLevelId" />'/>

        <!-- ���ID -->
        <input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
        
        <!-- ������� -->
        <input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
        
        <!-- �������� -->
        <input type="hidden" id="actLevelName" name="actLevelName" value="<s:property value="#request.actLevelName" />"/>
        
        <!-- ������ -->
        <%-- modify by sWX219697 2015-7-20 prePayFee��ΪprepayFee--%>
        <input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
        
        <!-- ��Ʒ���봮 -->
        <input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward"/>'/>
           
        <!-- Ӧ������ -->
        <input type="hidden" id="recFee" name="recFee" value='<s:property value="#request.recFee"/>'/> 
        
        <!-- �Ƿ�Ϊʵ�� 1:ʵ�0����ʵ�� -->        
        <input type="hidden" id="isGoods" name="isGoods" value='<s:property value="#request.isGoods"/>'/>
        
        <!-- СƱ��Ʒ���� -->
        <input type="hidden" id="awardDesc" name="awardDesc" value='<s:property value="#request.awardDesc"/>'/>
        
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>Ԥ�������������̣�</h2>
                        <div class="blank10"></div>
                        <a title="ѡ������" href="#">1. ѡ��</a>
                        <a title="��������" href="#">2. ����Э��</a>  
                        <a title="ѡ��֧����ʽ" href="#" class="on">3. ѡ��֧����ʽ</a> 
                        <a title="֧��" href="#">4. ֧��</a>
                        <a title="���" href="#">5. ���</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40">
                            <p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="telNum" /></span></p>
                            <p class="fs22 fwb pl40 lh30">Ӧ�����ã�<span class="yellow fs22"><s:property value="recFee" /></span>Ԫ</p>
                            <div class="blank10"></div>
                            <div class="line"></div>
                            <div class="blank10"></div>
                            <p class="tit_arrow fs22"><span class="bg"></span>ѡ��֧����ʽ��</p>
                            <div class="blank20"></div>
                            <div class="blank5"></div>
                            <ul class="pay_way_list clearfix">
                                <s:iterator value="payTypeFunc" id="type" status="st">
                                    <s:if test="#st.index == 0">                                        
                                        <li class="mr70">                                           
                                    </s:if>
                                    <s:else>
                                        <li>                                            
                                    </s:else>
                                        <a title='<s:property value="#type.menuname" />' href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>                     
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
