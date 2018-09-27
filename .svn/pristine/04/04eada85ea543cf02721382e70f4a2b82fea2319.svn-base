<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
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
//防止页面重复提交
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

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

function pwdKeyboardUp(e)
{
    var key = GetKeyCode(e);
    if (key == 82 || key == 220)
    {
        goback("<s:property value='curMenuId' />");
        return;
    }
}

function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
                
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
        document.forms[0].submit();
    }
}

// 查询档次
function queryActLevelDesc(groupId,groupName,actLevelId,actLevelName,activityId,prepayFee,activityDesc,awardDesc)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        // 活动组编码
        document.getElementById('groupId').value = groupId;
        
        // 活动级名称
        document.getElementById('groupName').value = groupName;
        
        // 活动编码
        document.getElementById("activityId").value = activityId;
        
        // 档次编码
        document.getElementById("actLevelId").value = actLevelId;
        
        // 档次名称
        document.getElementById("actLevelName").value = actLevelName;
        
        // 受理金额
        document.getElementById("prepayFee").value = prepayFee;

        // 活动描述
        document.getElementById("activityDesc").value = activityDesc;
        
        // 小票奖品描述
        document.getElementById("awardDesc").value = awardDesc;
         
        // 提交
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }prestoredReward/queryActLevelDesc.action";
        document.forms[0].submit();
    }
}

// 下一页
function nextPage(linkURL)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.forms[0].target = "_self";
        document.forms[0].action = linkURL;
        document.forms[0].submit();
    }
}

</script>
</head>
    <body scroll="no">
        <form id="actform" name="actform" method="post">
            
            <!-- 活动组ID -->
            <input type="hidden" id="groupId" name="groupId" value=""/>
            
            <!-- 活动组名称 -->
            <input type="hidden" id="groupName" name="groupName" value=""/>
            
            <!-- 活动编码 -->
            <input type="hidden" id="activityId" name="activityId" value=""/>
            
            <!-- 档次编码 -->
            <input type="hidden" id="actLevelId" name="actLevelId" value=""/>
            
            <!-- 档次名称 -->
            <input type="hidden" id="actLevelName" name="actLevelName" value=""/>
            
            <!-- 受理金额 -->
        	<%-- modify by sWX219697 2015-7-20 prePayFee改为prepayFee--%>
            <input type="hidden" id="prepayFee" name="prepayFee" value=""/>
            
            <!-- 活动描述 -->
            <input type="hidden" id="activityDesc" name="activityDesc" value=""/>
            
            <!-- 小票奖品描述 -->
            <input type="hidden" id="awardDesc" name="awardDesc" value=""/>
                        
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">            
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>预存有礼受理流程：</h2>
                        <div class="blank10"></div>
                        <a title="选择活动档次" href="#" class="on">1. 选择活动</a>
                        <a title="受理协议" href="#">2. 受理协议</a>  
                        <a title="选择支付方式" href="#">3. 选择支付方式</a> 
                        <a title="投入纸币" href="#">4. 支付</a>
                        <a title="完成" href="#">5. 完成</a>
                    </div>
                </div>
                
                <div class="b712">
                    <div class="bg bg712"></div>
                    <div class="blank30"></div>
                    <div class="p40">
                    <div class="blank10"></div>
                        <table width="100%" class="tb_blue" >
                          <tr>
                            <th scope="col">活动</th>
                            <th scope="col">档次</th>
                            <th scope="col">赠品价值(元)</th>
                            <th scope="col">受理金额(元)</th>
                            <th scope="col">操作</th>
                          </tr>
                          <s:iterator value="prestoredRewardList" id="list" status="st">
                          <tr>
                                <td><s:property value="#list.groupName" /></td>
                                <td><s:property value="#list.actLevelName" /></td>
                                <td><s:property value="#list.presentValue" /></td>
                                <td><s:property value="#list.prepayFee" /></td>
                                <td>
                                    <input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="办理" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';queryActLevelDesc('<s:property value="#list.groupId" />','<s:property value="#list.groupName" />','<s:property value="#list.actLevelId" />','<s:property value="#list.actLevelName" />', '<s:property value="#list.activityId" />','<s:property value="#list.prepayFee" />','<s:property value="#list.activityDesc" />','<s:property value="#list.awardDesc"/>')"/>
                                </td>
                                            
                          </tr>
                          </s:iterator>
                        </table>
                    </div>  
                    
                    <!-- 分页 -->
                    <div class="sortPages">
                        <pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
                    </div>
                    <div class="clear"></div>
                </div>  
            </div>  
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>

