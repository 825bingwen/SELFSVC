<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
<script>
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
        goback("<s:property value='#request.curMenuId' />");
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
        document.forms[0].action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
        document.forms[0].submit();
    }
}

// 选择支付方式
function selectPayType()
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        //弹出等待框
        openRecWaitLoading();
        
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }prestoredReward/selectPayType.action";
        document.forms[0].submit();
    }
}
</script>
</head>
    <body scroll="no">
        <form name="actform" method="post">
        
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
                        <a title="受理协议" href="#" class="on">2. 受理协议</a>  
                        <a title="选择支付方式" href="#">3. 选择支付方式</a> 
                        <a title="投入纸币" href="#">4. 支付</a>
                        <a title="完成" href="#">5. 完成</a>
                    </div>
                </div>
                
                <!--滚动条-->
                <div class="box710W fl ml10 relative" style="margin-top: 10px;">
                    <div class="bg"></div>
                    <div class="top"></div>
                    <div class="con relative" >
                    
                        <div class="box615W fl">
                            <div class="top"></div>
                            <div class="con" style="height:501px; padding:0px; overflow:hidden;">

                                <!-- 列表内容 -->
                                <div class="ptop180 tc" id="inn" style="height:468px;  overflow:hidden;" >
                                    <table class="fs16" style="text-align: left;" >
                                        <tr>
                                            <td class="fs22">
                                                活动详情
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><s:property value="activityDesc" /></td>
                                        </tr>
                                        <tr>
                                            <td style="text-align: right;">
                                            <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';selectPayType(); return false;" style="display:inline">同意协议并办理</a>
                                            </td>
                                        </tr>
                                        <tr><td>尊敬的客户：
                                    <br />
                                    &nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团山东有限公司的客户。在您申办业务前，请认真阅读以下条款：
                                    <br />
                                    &nbsp;&nbsp;&nbsp;&nbsp;本终端业务受理系统的所有权和运作权，以及所受理具体业务的经营权归中国移动通信集团山东有限公司所有，您必须完全同意所有服务条款，才可以通过本终端办理各类业务。
                                    移动电话号码和服务密码是您重要的个人资料，可以作为办理业务的凭证。凡使用本终端凭服务密码办理的一切业务，均视为您本人亲自办理，并由您本人负责。请您务必注意服务密码的保密。
                                    您必须提供准确的资料，我公司将根据您提供的资料进行核对，如有不符，系统将不予受理业务。
                                    目前本终端受理以下免办理手续费用的项目，包括业务办理，缴纳话费，打印清单，补打发票等业务。
                                    您在申请业务后，如需要查询，请您浏览自助查询终端的相关栏目或者拨打10086查询。
                                    <br />
                                    &nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意协议并办理]继续受理业务。如果您不同意以上条款，请按[退出]，本系统将自动退至主界面。</td></tr>
                                            
                                    </table></div>
                                <!-- 列表内容 -->
                            </div>
                            <div class="btm"></div>
                        </div>
                        <div class="box70W fr">
                            <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                            <div class="boxPage" style="width:75px; height:407px; ">
                                <div class="blank10px"></div>
                                <div class="box66W tc f16 lh30" id="gunDom" style="width:66px; height:36px; position:absolute; cursor:move;  top:52px; line-height:36px" >0%</div>
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
                <!--滚动条结束-->
                
                <div class=" clear"></div>
            </div>
            
            <%@ include file="/backinc.jsp"%>
        
        </form>
    </body>
</html>
