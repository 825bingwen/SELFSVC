<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

String yuanMoney = (String) request.getAttribute("totalFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>读卡页面</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
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
        }
        
        // 读卡等待时间
        var limitTime = <%=limitTime %>;
        
        //等待读卡的句柄
        var waitingToken;
        
        // 提交标志
        var submitFlag = 0;
        
        //出现异常
        function setException(errorMsg)
        {           
            //显示错误信息
            document.getElementById("errormessage").value = errorMsg;
                
            //清除定时任务
            clearInterval(waitingToken);
                
            try
            {
                // 关闭读卡设备
                CloseComByCard();
                
                // 打开密码键盘并设置明文模式
                OpenCom();
                SetWorkMode(0)
            }
            catch (e)
            {}
                
            //异常处理，只要出现了异常就要记录日志
            document.readCardForm.target = "_self";
            document.readCardForm.action = "${sessionScope.basePath}charge/goMorePhoneError.action";
            document.readCardForm.submit();         
        }
        
        // 中英文字符串长度
        function strlen(str)    
        {    
            var i;    
            var len;    
                
            len = 0;    
            for (i=0;i<str.length;i++)    
            {    
                if (str.charCodeAt(i)>255) len+=2; else len++;    
            }
            return len;    
        }
        
        function trim(str) 
        {
            while (str.charAt(str.length - 1) == " ") 
            {
                str = str.substring(0, str.length - 1);
            }
            
            while (str.charAt(0) == " ") 
            {
                str = str.substring(1, str.length);
            }
            
            return str;
        }
        
        //----------------------------------扣款及缴费--------------------------------------------------------------------------------
        //银联卡扣款
        function pay()
        {
            //清除定时任务
            clearInterval(waitingToken);
            
            // 关闭读卡设备
            try
            {
            
                //平台系统检测到用户已插入银联卡后，需调用此接口关闭读卡设备，释放串口。
                var ret = CloseComByCard();
                
                if (ret != 0 && ret != "0")
                {
                    setException("关闭读卡设备失败，无法使用银联卡进行充值，请联系营业人员取走您的银联卡。");
                            
                    return;
                }
            }
            catch(e)
            {
                setException("关闭读卡设备失败，无法使用银联卡进行充值，请联系营业人员取走您的银联卡。");
                            
                return;
            }
            
            // 发起银联卡扣款请求
            var resultstr = "";
            try
            {
                // 交易请求报文
                // 交易代码(2)+交易金额(12)+POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)
                // + 原交易日期(8)+卡片类型(1)+自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
                var strin = '01';// 交易代码(2)
                strin = strin + formatStr('<%=fenMoney %>','left','0',12);// 交易金额(12)
                strin = strin + formatStr('','right',' ',55);// POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)+ 原交易日期(8)
                strin = strin + 'H';// 卡片类型(1)
                strin = strin + formatStr('','right',' ',240);// 自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
                //附加信息，107:话费连缴
                strin = strin + formatStr("107"+document.getElementById("chargeLogOID").value,'right',' ',100);// 移动缴费附加信息100
                
                // 生产用  
                if (true) // true:生产 false:测试
                {   
                    resultstr = window.parent.document.getElementById("unionpluginid").CardTrans(strin);
                }
                // 测试用  
                else
                {
                    // 返回码(6)+  返回码含义(40)+POS流水号(6)+授权码(6)+批次号(6)+卡号(19)+有效期(4)+银行号(2)+银行检索参考号(12)+终端号(15)+商户号(15)
                    // 交易金额(12)+加密后的密码或定单号(16)+分期付款信息(74)+发卡行代码(8)+银联主机主易日期(8)+银联主机主易时间(6)
//                  resultstr = '000001';// 返回码(6)
//                  resultstr = resultstr + formatStr('error', 'right', ' ', 40);// 返回码含义(40)
                    resultstr = '000000';// 返回码(6)
                    resultstr = resultstr + formatStr('','right',' ',40);// 返回码含义(40)                   
                    resultstr = resultstr + "pos001";// POS流水号(6)
                    resultstr = resultstr + "sqm001";// 授权码(6)
                    resultstr = resultstr + "pch001";// 批次号(6)
                    resultstr = resultstr + "kahao12345678901234";// 卡号(19)
                    resultstr = resultstr + "0313";// 有效期(4)
                    resultstr = resultstr + "01";// 银行号(2)
                    resultstr = resultstr + "chankaohao99";// 银行检索参考号(12)
                    resultstr = resultstr + "zdh123456789012";// 终端号(15)
                    resultstr = resultstr + "shh123456789012";// 商户号(15)
                    resultstr = resultstr + "000000000100";// 交易金额(12)
                    resultstr = resultstr + "****************";//加密后的密码或定单号(16)
                    resultstr = resultstr + formatStr('','right',' ',74);// 分期付款信息(74)
                    resultstr = resultstr + formatStr('','right',' ',8);// 发卡行代码(8)
                    resultstr = resultstr + '20120101';// 银联主机主易日期(8)
                    resultstr = resultstr + '101010';// 银联主机主易时间(6)
                }
            }
            catch (e)
            {}
            
            try
            {
                // 打开密码键盘，设置明文模式
                OpenCom();
                SetWorkMode(0);
            }
            catch(e)
            {}
            
            try
            {
                // 调用出现问题，没有取到返回值
                if (resultstr == "")
                {
                    setException("银联扣款失败，无法充值，请联系营业人员取走您的银联卡。");
                            
                    return;
                }
                // 扣款成功 定长255 成功000000
                else if (resultstr.substring(0,6) == "000000" && strlen(resultstr) == 255)
                {
                    // 流水号_交易参考号
                    document.getElementById("terminalSeq").value = trim(resultstr.substring(resultstr.length-166, resultstr.length-154));
                    document.getElementById("printcontext").value = trim(resultstr.substring(resultstr.length-209));
                        
                    //更新日志
                    var url1 = "${sessionScope.basePath}charge/updateMoreChargeLog.action";
            
                    var params1 = "cardChargeVO.moreChargeOID=" + document.getElementById("chargeLogOID").value;//日志编号
                    params1 = params1 + "&cardChargeVO.unionpayuser=" + trim(resultstr.substring(resultstr.length-139,resultstr.length-124));// 商户编码
                    params1 = params1 + "&cardChargeVO.unionpaycode=" + trim(resultstr.substring(resultstr.length-154,resultstr.length-139));// POS机编号
                    params1 = params1 + "&cardChargeVO.busiType=" + encodeURI(encodeURI("消费"));//交易类型
                    params1 = params1 + "&cardChargeVO.cardnumber=" + trim(resultstr.substring(resultstr.length-191,resultstr.length-172));// 卡号
                    params1 = params1 + "&cardChargeVO.posNum=" + trim(resultstr.substring(resultstr.length-209,resultstr.length-203));// POS流水号
                    params1 = params1 + "&cardChargeVO.batchnum=" + trim(resultstr.substring(resultstr.length-197,resultstr.length-191));// 批次号
                    params1 = params1 + "&cardChargeVO.authorizationcode=" + trim(resultstr.substring(resultstr.length-203,resultstr.length-197));// 授权码
                    params1 = params1 + "&cardChargeVO.businessreferencenum=" + trim(resultstr.substring(resultstr.length-166,resultstr.length-154));// 交易参考号
                    params1 = params1 + "&cardChargeVO.unionpaytime=" + trim(resultstr.substring(resultstr.length-14,resultstr.length));// 扣款时间
                    params1 = params1 + "&cardChargeVO.vouchernum=";// 凭证号
                    params1 = params1 + "&cardChargeVO.unionpayfee=" + trim(resultstr.substring(resultstr.length-124,resultstr.length-112));// 扣款金额
                    params1 = params1 + "&cardChargeVO.terminalSeq=" + document.getElementById("terminalSeq").value;//交易流水号，终端流水号
                    params1 = params1 + "&cardChargeVO.status=01";//交易状态 01表示银联扣款成功
                    params1 = params1 + "&cardChargeVO.description=" + encodeURI(encodeURI("交易成功"));
                    params1 = params1 + "&morePhoneInfo=" + encodeURI(encodeURI(document.getElementById("morePhoneInfo").value));
                            
                    var loader1 = new net.ContentLoader(url1, netload = function () {
                        var resXml1 = this.req.responseText;
                                
                        //更新日志成功
                        if (resXml1 == "1" || resXml1 == 1)
                        {
                            //提交缴费请求
                            document.readCardForm.target = "_self";
                            document.readCardForm.action = "${sessionScope.basePath }charge/morePhoneCommit.action";
                            document.readCardForm.submit();                                     
                        }
                        //更新日志失败
                        else
                        {                                       
                            setException("银联扣款成功，但是交费失败。请取走您的银联卡。");
                            
                            return;
                        }                               
                    }, null, "POST", params1, null);
                }
                //扣款失败
                else
                {
                    document.getElementById("unionRetCode").value = resultstr.substring(0,6);
                    
                    setException(trim(resultstr.substring(6)) + "。请取走您的银联卡。");
                    
                    return;
                }
            }
            catch (e)
            {
                if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
                {
                    setException("银联扣款成功，但是交费失败。请取走您的银联卡。");
                }
                else
                {
                    setException("银联扣款失败，无法充值。请取走您的银联卡。");
                }
            }               
        }
        
        //-----------------------------------------循环读卡------------------------------------------------------------------------
        // 获得读卡标志
        function getReadCardStatus() 
        {
            try 
            {
                // 0 读卡成功；-1 读卡失败；1 尚未读取到卡信息
                var ret = getCardPosition();// 获取卡位置，以判断用户是否已插入银联卡

                // 读卡失败
                if (ret == "-1")
                {
                    setException("读卡失败，无法使用银联卡进行充值，请选用其它方式。");
                }
                // 卡在读卡器内
                else if (ret == "0" || ret == 0)
                {   
                    document.getElementById("flowDesc2").style.display = "block";
                    document.getElementById("flowDesc1").style.display = "none";
                    pay();
                }
                // 尚未读取到卡信息
                else
                {
                    // 读卡时长一共limitTime秒
                    limitTime = limitTime - 1;
            
                    // 设定界面显示
                    document.getElementById("tTime").value = limitTime;
                    
                    //读卡时间结束
                    if (limitTime <= 0)
                    {
                        setException("读卡超时，无法使用银联卡进行充值，请选用其它方式。");
                        return;
                    }
                }
            }
            catch (e) 
            {
                setException("读卡失败，无法使用银联卡进行充值，请选用其它方式。");
            }
        }
        
        //设置时间计算周期
        function startclock() 
        {   
            try 
            {
                waitingToken = setInterval("getReadCardStatus()", 1000);                
            }
            catch (e) 
            {
                setException("读卡失败，无法使用银联卡进行充值，请选用其它方式。");
            }
        }
        
        //----------------------------------页面初始化---------------------------------------------------------------------------------
        //页面加载时执行
        function bodyLoad() 
        {
            // 关闭密码键盘
            try
            {
                //调用银联扣款控件前关闭设备所用串口并创建监听程序
                var ret = CloseCom();
                if (ret != 0)
                {
                    setException("关闭密码键盘失败，无法使用银联卡进行充值，请选用其它方式。");
                
                    return;
                }
            }
            catch(e)
            {
                setException("关闭密码键盘失败，无法使用银联卡进行充值，请选用其它方式。");

                return;
            }
            
            // 打开读卡设备
            try 
            {
                var ret = OpenComByCard();
                if (ret != "0" && ret != 0) 
                {
                    setException("打开读卡设备失败，无法使用银联卡进行充值，请选用其它方式。");
                    return;
                }
                else
                {       
                    //初始化成功，就需要关闭读卡器。禁用所有按钮，包括页面上部和下部。
                    closeStatus = 1;
                    
                    document.getElementById("homeBtn").disabled = true;
                    document.getElementById("backBtn").disabled = true;
                    document.getElementById("quitBtn").disabled = true;
                            
                    startclock();
                }
            }
            catch (ex) 
            {
                setException("打开读卡设备失败，无法使用银联卡进行充值，请选用其它方式。");
                return;
            }
        }       
        </script>
    </head>
    <body scroll="no">
        <form name="readCardForm" method="post" target="_self">
            <!-- 错误信息 -->
            <input type="hidden" id="errormessage" name="errormessage" value=''>
            
            <!-- 缴费类型 -->
            <input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
            <input type="hidden" id="chargeLogOID" name="chargeLogOID" value="<s:property value='chargeLogOID' />">
            <input type="hidden" id="printcontext" name="printcontext" value="">
            <s:hidden name="chargeType"/>
            
            <!-- 银联卡扣款金额，单位：分 -->
            <s:hidden name="tMoney" id="tMoney"/>
            
            <!-- 话费连缴标志，1：话费连缴 -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- 银联返回错误码 -->
            <s:hidden name="unionRetCode" id="unionRetCode"/>
            
            <!-- 缴费总金额 -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 " id="flowDesc1">
                        <div class="bg bg257"></div>
                        <h2>充值交费流程</h2>
                        <div class="blank10"></div>
                        <a href="javascript:void(0)">1. 输入手机号码</a> 
                        <a href="javascript:void(0)">2. 选择支付方式</a>
                        <a href="javascript:void(0)">3. 免责声明</a>
                        <a href="javascript:void(0)" class="on">4. 插入银联卡</a>
                        <p class="recharge_tips">插入您的银联卡。业务办理结束后请<br />注意取回银联卡。</p>
                        <a href="javascript:void(0)">5. 输入密码</a>
                        <a href="javascript:void(0)">6. 完成</a>
                    </div>
                    
                    <div class="b257 " id="flowDesc2" style="display: none">
                        <div class="bg bg257"></div>
                        <h2>充值交费流程</h2>
                        <div class="blank10"></div>
                        <a href="javascript:void(0)">1. 输入手机号码</a> 
                        <a href="javascript:void(0)">2. 选择支付方式</a>
                        <a href="javascript:void(0)">3. 免责声明</a>
                        <a href="javascript:void(0)">4. 插入银联卡</a>
                        <a href="javascript:void(0)" class="on">5. 输入密码</a>
                        <a href="javascript:void(0)">6. 完成</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40 pr0">
                            <p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="totalFee" /></span> 元</p>
                            
                            <div class="blank60"></div>
                            <div class="line"></div>
                            <div class="blank25"></div>
                            <p class="tit_info">请插入您的银联卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
                            <p class="tit_info"><span>读卡时长共</span><span class="yellow"><%=limitTime %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=limitTime %>" readonly="readonly" />秒。</p>                          
                            <div class="gif_animation">
                                <img src="${sessionScope.basePath }images/gif1.gif" alt="请插卡" />
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
        bodyLoad();        
    </script>
</html>
