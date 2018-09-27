<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
    // 清除缓存，防止页面后退安全隐患 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    // 投币超时时间
    String timeout = (String)PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);
    
    // 终端信息
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // 现金识币器特性
    String isCashEquip = termInfo.getTermspecial().substring(3, 4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css"rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css"rel="stylesheet" />
<script type="text/javascript"src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
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
    
    //确认
    if (key == 13 || key == 89 || key == 221) 
    {
        doSub();
        return;
    }
    //返回
    else if (key == 82 || key == 220) 
    {
        goback("<s:property value='curMenuId' />");
        return;
    }  
    
}
    
//投币的时长，单位秒     
var payMoneyTime = "<%=timeout%>";

//剩余时间
var leftTime = payMoneyTime;

//readCash定时器句柄
var readCashToken;

//关闭识币器。0：不需要；1：需要
var needClose = 0;

//提交标记，0表示未确认提交缴费，1表示已确认提交缴费
var submitFlag = 0;

function goback(menuid)
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />退出交费功能");
    
    //已投币
    if (document.getElementById("tMoney").value != "" 
            && parseInt(document.getElementById("tMoney").value) > 0)
    {
        return;
    }
    
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "返回选择活动页面页面");
        
        //返回时，清除定时任务。同时关闭现金识币器          
        if (needClose == 1)
        {
            fCloseCashBill();
        }
        
        clearInterval(readCashToken);
        
        document.getElementById("curMenuId").value = menuid;
        
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
        document.actform.submit();
    }
}
    
// 提交
function doSub()
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />确认交费");
    
    //未投币
    if (document.getElementById("tMoney").value == "" 
            || parseInt(document.getElementById("tMoney").value) <= 0)
    {
        return;
    }
    
    //尚未提交请求
    if (submitFlag == 0)
    {
        // 判断投币金额，不能少于应缴金额
        var recFee = document.getElementById("recFee").value;
        var tMoney = document.getElementById("tMoney").value;
        if(tMoney - recFee < 0)
        {
            var alsoFee = recFee - tMoney;
            var tipText = "尊敬的客户，您所投币的金额不足，请再投入"+alsoFee+"元("+alsoFee+"元=应缴金额-投币金额)。";
            openWindow("openWin_tipsMsg",tipText);
            return;
        }
        
        submitFlag = 1;
        
        // 确认按钮按下，置不可用状态
        document.getElementById('commitBusi').className = 'btn_box buy_enable_hover fl pl30 pt120';
        
        // 取消按钮按下，置不可用状态
        document.getElementById('cancelBusi').className = 'btn_box buy_enable_hover fl pl30 pt120';
        
        //关闭现金识币器
        if (needClose == 1)
        {
            fCloseCashBill();
        }
        
        //清除定时任务
        clearInterval(readCashToken);
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "提交交费请求，号码：" + document.getElementById("telNum").value + 
                "；金额：" + document.getElementById("tMoney").value + 
                "；流水：" + document.getElementById("terminalSeq").value);
                
        //弹出等待框
        openRecWaitLoading();
        
        //提交缴费请求
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }prestoredReward/cashFinish.action";
        document.actform.submit();
    }
}

// 打开弹出窗
function openWindow(id,tipMsg)
{
    document.getElementById('winText_tipsMsg').innerHTML = tipMsg;
    wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子    
}  

// 关闭弹出div
function windowClose()
{
    wiWindow.close();
}

// 取消办理，将所投金额使用小票打印
function closeRec()
{
    setException("用户取消办理，请取小票！如有疑问，请咨询移动营业厅。"); 
}

//出现异常
function setException(errorMsg) 
{           
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />交费过程中出现异常：" + errorMsg);
    
    document.getElementById("errormessage").value = errorMsg;
    
    // 出现异常后，清除定时任务。同时关闭现金识币器           
    if (needClose == 1)
    {
        fCloseCashBill();
    }
    
    // 清除定时任务
    clearInterval(readCashToken);
    
    // 异常处理，只要出现了异常就要记录日志  
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath }prestoredReward/cashError.action";
    document.actform.submit();
}       

// 获取手机号码
// 初始化现金识币器
// 获取投币流水
// 使首页和退出按钮不可用
// 启动循环获取投币金额
function loadContent() 
{
    var telNum = "<s:property value='telNum' />";
    if (telNum == null || telNum == "")
    {            
        setException("对不起，用户信息获取失败，请返回重新操作。");
        return;
    }
          
    <%if (!"1".equals(isCashEquip)){%>
          setException("对不起，该终端机暂不支持现金充值，请选用其它方式。");
          return;
    <%}%>
    
    try 
    {
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "初始化现金识币器：" + telNum);
                
        // 初始化现金识币器(正常返回 0,20110509143345)
        var initData = initCashEquip_sd(telNum);
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "结果：" + initData);
        
        if (initData.substring(0, 1) != "0") 
        {
            setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
            return;                    
        }
        else
        {
            //现金识币器初始化成功。不再接收用户投币时，需要关闭。
            needClose = 1;

            //获取投币流水
            document.getElementById("terminalSeq").value = initData.substring(2);
            
            // 标识控件使用
            closeStatus = 1;
            
            //初始化成功，就需要关闭识币器。而首页、退出按钮无法执行此操作，所以禁用”首页“、“退出”按钮
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
            
            startclock();
        }               
    } 
    catch(e) 
    {
        setException("现金识币器初始化失败，无法使用现金进行充值，请选用其它方式。");
        return;
    }           
}

//循环获取投币金额
function startclock() 
{
    var msg = preparecash();
    
    //识币器状态检测失败，不允许投币，显示异常信息
    if (msg != "") 
    {
        setException(msg);
        return;
    }       
    
    try
    {
        // 获取投币金额,循环调用,每隔1秒执行一次
        readCashToken = setInterval("doCash()", 1000);
    }
    catch (e) 
    {
        setException("对不起，计时器发生异常，无法使用现金进行充值，请使用其它方式或者到营业厅进行充值。");
    }
}

//检测识币器状态
function preparecash() 
{
    var msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式值。";

    try 
    {
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "检测识币器状态"); 
                        
        //检测识币器状态 0-正常 1-异常 2-钱箱满 3-钱箱打开 4-入口被夹 5-钱箱被夹 6-其它故障 9-无此设备
        var cashStatus = checkCashStatus();
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "结果：" + cashStatus);    
        if (cashStatus == 0)
        {
            msg = "";
        }
        else if (cashStatus == 1) 
        {
            msg = "对不起，现金识币器状态异常，无法使用现金进行充值，请选用其它方式。";
        }
        else if (cashStatus == 2) 
        {
            msg = "对不起，钱箱已满，无法使用现金进行充值，请选用其它方式。";
        }
        else if (cashStatus == 3) 
        {
            msg = "对不起，钱箱未正常关闭，无法使用现金进行充值，请选用其它方式。";
        } 
        else if (cashStatus == 4) 
        {
            msg = "对不起，钱箱入钞口被夹，无法使用现金进行充值，请选用其它方式。";
        }
        else if (cashStatus == 5) 
        {
            msg = "对不起，钱箱被夹，无法使用现金进行充值，请选用其它方式。";
        } 
        else if (cashStatus == 6) 
        {
            msg = "对不起，现金识币器发生未知错误，无法使用现金进行充值，请选用其它方式。";
        }
        else if (cashStatus == 9) 
        {
            msg = "对不起，现金识币器不存在，无法使用现金进行充值，请选用其它方式。";
        }           
    }
    catch (e) 
    {
        msg = "对不起，现金识币器出现异常，无法使用现金进行充值，请选用其它方式。";
    }
    
    // 返回
    return msg;
}

// 获取投币金额
// 循环调用，每隔1秒执行一次
function doCash() 
{
    if (leftTime <= 0)
    {
        return;
    }
    
    try 
    {   
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "获取投币金额");  
                        
        // 获取投币金额 0 表示没有投币，否则 为投币面值(可能的值为：1,2,5,10,20,50,100)。
        var ret = getOnceCash();
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "结果：" + ret);
        
        if (ret > 0) 
        {
            // 时间重新开始
            leftTime = "<%=timeout%>";
            
            // 显示剩余时间
            document.getElementById("tTime").value = leftTime;
            
            // 投币后，不能返回
            document.getElementById("backBtn").disabled = true;
            
            // 计算投币金额 
            document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
            
            // 取消交易按扭不显示
            document.getElementById('cancelBusi').style.display = "none";
            
            // 投币金额大于0时取消交易按扭不显示,缴费按扭显示
            if (parseInt(document.getElementById("tMoney").value) > 0)
            {
                document.getElementById('commitBusi').style.display = "block";
                document.getElementById("promptMsg").innerHTML = "已投入纸币，请点击缴费按钮";
            }
            else
            {
                document.getElementById('bCommitBusi').style.display = "block";
            }
        }
        else
        {
            // 投币时长一共timeout秒
            leftTime = leftTime - 1;
            
            // 显示剩余时间
            document.getElementById("tTime").value = leftTime;
            
            //投币时间结束，而用户没有主动提交缴费请求，此时，需要提交缴费请求
            if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
            {                   
                //投币金额大于0
                if (parseInt(document.getElementById("tMoney").value) > 0) 
                {
                    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                            "交易超时，用户已投币。提交交费请求");
                        
                    // 提交缴费
                    doSub();
                } 
                else 
                {
                    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                            "交易超时，用户未投币。流程结束");
                    
                    // 取消交易
                    cancelBusi();
                }
            }
        }               
    }
    catch (e) 
    {
        setException("对不起，获取投币金额失败，无法使用现金进行充值，请选用其它方式。");
    }
}

// 取消交易
function cancelBusi()
{       
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='servnumber' />取消交费交易");
    
    if (needClose == 1)
    {
        // 关闭现金识币器
        fCloseCashBill();
    }
    
    // 清除定时任务
    clearInterval(readCashToken);
    
    // 返回首页
    setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
}
</script>
    </head>
    <body scroll="no">
        <form name="actform" method="post"> 
        <!-- 手机号码 -->
        <input type="hidden" id="telNum" name="telNum" value="<s:property value='telNum' />">

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
        
        <!-- 应交金额 -->
        <input type="hidden" id="recFee" name="recFee" value='<s:property value="#request.recFee"/>'/>
        
        <!-- 终端机流水 -->
        <input type="hidden" id="terminalSeq" name="terminalSeq" value=""/>
            
        <!-- 错误信息 -->
        <input type="hidden" id="errormessage" name="errormessage" value=""/>        
        
        <!-- 是否为实物 1:实物，0：非实物 -->        
        <input type="hidden" id="isGoods" name="isGoods" value='<s:property value="#request.isGoods"/>'/>
        
        <!-- 日志流水 -->
        <input type="hidden" id="chargeLogOID" name="chargeLogOID" value='<s:property value="chargeLogOID"/>'/>
        
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
                        <a title="选择支付方式" href="#">3. 选择支付方式</a> 
                        <a title="支付" href="#" class="on">4. 投入纸币</a>
                        <a title="完成" href="#">5. 完成</a>
                    </div>

                    <div class="b712 fm_pay_money">
                        <div class="bg bg712"></div>
                        <div class="blank30"></div>
                        <div class="p40 pr0">
                            <div class="blank10"></div>
                            <div class="blank20"></div>
                            <div class=" pay_money_wrap2">
                                <p class="pay_all">
                                    <span class="pl120">已投入：</span>
                                    <input type="text" id="tMoney" name="tMoney" value="0"readonly="readonly" />
                                    <span class="yellow">元</span>
                                </p>
                                <div class="pay_state clearfix">
                                    <span class="cash_arrow"></span>
                                    <p class="fl fs22">
                                        投币状态：

                                        <span id="promptMsg" class="yellow">请投入纸币...</span>

                                        <br />
                                        <span class="pl119">投币时长共</span><span
                                            class="yellow"><%=timeout%></span>秒，当前剩余
                                        <input type="text" id="tTime" name="tTime"
                                            value="<%=timeout%>" readonly="readonly" />
                                        秒
                                        <br />
                                        <span class="pl119">支持</span><span
                                            class="yellow">5、10、20、50、100元</span>面额的纸币
                                    </p>
                                </div>
                            </div>
                            <div class="blank30"></div>
                            <div>
                                <img src="${sessionScope.basePath }images/rmb.gif"
                                    class="fl pl160" alt="请投币" />
                                <div style="display:none" class="btn_box buy_enable fl pl30 pt120" id="commitBusi">
                                    <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">缴费</a>
                                </div>
                                <div style="display:block;" class="btn_box buy_enable fl pl30 pt120" id="cancelBusi">
                                    <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">取消</a>
                                </div>
                            </div>
                            <div class="popup_confirm" id="openWin_tipsMsg">
                                <div class="bg"></div>
                                <div class="tips_title">提示：</div>
                                <div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_tipsMsg">
                                </div>
                                <div class="tc">
                                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';windowClose();">继续缴费</a> 
                                    <a class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';closeRec();">取消办理</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
    <script type="text/javascript">
    clearTimeout(timeOut);
    loadContent();
</script>
</html>
