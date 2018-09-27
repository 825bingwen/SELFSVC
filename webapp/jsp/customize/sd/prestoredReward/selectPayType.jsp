<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    // 清除缓存，防止页面后退安全隐患 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
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
    //防止页面重复提交
    var submitFlag = 0;
    
    //82、220 返回
    
    document.onkeydown = pwdKeyboardDown;
    
    function pwdKeyboardDown(e)
    {   
        var key = GetKeyCode(e);
        
        //更正
        if (key == 77) 
        {
            preventEvent(e);
        }
        
        if (!KeyIsNumber(key))
        {
            return false;//这句话最关键
        }
    }
    
    function KeyIsNumber(KeyCode) 
    {
        //只允许输入0-9
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
        
        //返回
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
                    "<s:property value='telNum' />退出交费功能");
            
            document.getElementById("curMenuId").value = menuid;
                    
            document.forms[0].target = "_self";
            document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
            document.forms[0].submit();
        }
    }
    
    //提交交费方式，flag为0时需提示异地缴费信息，为1时不需要提示，直接提交
    function doSub(menuid, url,flag)
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                    "<s:property value='telNum' />选择交费方式：" + menuid);
            
            if (url == "prestoredReward/cardCharge.action")
            {
                // 银联卡交费，判断此时间段内是否可用
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
        
        <!-- 手机号 -->
        <input type="hidden" id="telNum" name="telNum" value="<s:property value='#request.telNum' />"/>
        
        <!-- 活动编码 -->
        <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
        
        <!-- 档次编码 -->
        <input type="hidden" id="actLevelId" name="actLevelId" value='<s:property value="#request.actLevelId" />'/>

        <!-- 活动组ID -->
        <input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
        
        <!-- 活动组名称 -->
        <input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
        
        <!-- 档次名称 -->
        <input type="hidden" id="actLevelName" name="actLevelName" value="<s:property value="#request.actLevelName" />"/>
        
        <!-- 受理金额 -->
        <%-- modify by sWX219697 2015-7-20 prePayFee改为prepayFee--%>
        <input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
        
        <!-- 奖品编码串 -->
        <input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward"/>'/>
           
        <!-- 应交费用 -->
        <input type="hidden" id="recFee" name="recFee" value='<s:property value="#request.recFee"/>'/> 
        
        <!-- 是否为实物 1:实物，0：非实物 -->        
        <input type="hidden" id="isGoods" name="isGoods" value='<s:property value="#request.isGoods"/>'/>
        
        <!-- 小票奖品描述 -->
        <input type="hidden" id="awardDesc" name="awardDesc" value='<s:property value="#request.awardDesc"/>'/>
        
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>预存有礼受理流程：</h2>
                        <div class="blank10"></div>
                        <a title="选择活动档次" href="#">1. 选择活动</a>
                        <a title="档次描述" href="#">2. 受理协议</a>  
                        <a title="选择支付方式" href="#" class="on">3. 选择支付方式</a> 
                        <a title="支付" href="#">4. 支付</a>
                        <a title="完成" href="#">5. 完成</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40">
                            <p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telNum" /></span></p>
                            <p class="fs22 fwb pl40 lh30">应交费用：<span class="yellow fs22"><s:property value="recFee" /></span>元</p>
                            <div class="blank10"></div>
                            <div class="line"></div>
                            <div class="blank10"></div>
                            <p class="tit_arrow fs22"><span class="bg"></span>选择支付方式：</p>
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
