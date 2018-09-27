<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@page import="com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO"%>
<%

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

CardChargeLogVO chargeLogVO = (CardChargeLogVO) request.getAttribute("chargeLogVO");

String fenMoney = "";
if(Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
{
	fenMoney = CommonUtil.yuanToFen(chargeLogVO.getYingjiaoFee());
}
else
{
	fenMoney = CommonUtil.yuanToFen((String)request.getAttribute("totalFee"));
}

%>
<%@ include file="/titleinc.jsp"%>

<div class="main" id="main">

    <%-- 是否打印小票  1-不打印，0-打印 --%>
    <input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value='canNotPrint'/>" />
    <%@ include file="/customer.jsp"%>
    
    <div class="pl30">
    
    	<!-- modify begin wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
    	<s:if test="'cardcharge_valueCard' == curMenuId">
        	<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
        </s:if>
        <s:else>
        	<%@ include file="/jsp/customize/sd/feeCharge/common/chargeHeader.jsp"%>
        </s:else>
        <!-- modify end wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
        
        <div class="b712">
            <div class="bg bg712"></div>
            <div class="blank60"></div>
            <div class="p40 pr0">
                <p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="servNumber" /></span></p>
                <p class="fs22 fwb pl40 lh30">交费金额：
                <span class="yellow fs22">
                	
                	<!-- modify begin wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
                	<s:if test="'cardcharge_valueCard' == curMenuId">
                		<s:property value='totalFee' />
                	</s:if>
                	<s:else>
                		<s:property value="chargeLogVO.yingjiaoFee" />
                	</s:else>
                	<!-- modify end wWX217192 2015-06-18 OR_SD_201505_1022_山东_山东电子充值卡改造需求_自助终端改造 -->
                	
                </span> 元</p>
                
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

<script type="text/javascript">
document.getElementById("highLight5").className = "on";

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
    document.readCardForm.action = errorUrl;
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
        //附加信息，106:省外异地缴费
        strin = strin + formatStr(chargeType + document.getElementById("chargeLogOID").value,'right',' ',100);// 移动缴费附加信息100
        
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
            //resultstr = '000001';// 返回码(6)
            //resultstr = resultstr + formatStr('error', 'right', ' ', 40);// 返回码含义(40)
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
            document.getElementById("tMoney").value = trim(resultstr.substring(resultstr.length-124, resultstr.length-112));
            document.getElementById("printcontext").value = trim(resultstr.substring(resultstr.length-209));
                
            //更新日志
            var url1 = updateUrl;
    		
    		var params1 = "";
    		
    		if("cardcharge_valueCard" == "<s:property value='curMenuId' />")
    		{
				params1 = "cardPayLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value;//日志编号
	            params1 = params1 + "&cardPayLogVO.servnumber=" + document.getElementById("servnumber").value;
	            params1 = params1 + "&cardPayLogVO.unionpayuser=" + trim(resultstr.substring(resultstr.length-139,resultstr.length-124));// 商户编码
	            params1 = params1 + "&cardPayLogVO.unionpaycode=" + trim(resultstr.substring(resultstr.length-154,resultstr.length-139));// POS机编号
	            params1 = params1 + "&cardPayLogVO.busiType=" + encodeURI(encodeURI("消费"));//交易类型
	            params1 = params1 + "&cardPayLogVO.cardnumber=" + trim(resultstr.substring(resultstr.length-191,resultstr.length-172));// 卡号
	            params1 = params1 + "&cardPayLogVO.posNum=" + trim(resultstr.substring(resultstr.length-209,resultstr.length-203));// POS流水号
	            params1 = params1 + "&cardPayLogVO.batchnum=" + trim(resultstr.substring(resultstr.length-197,resultstr.length-191));// 批次号
	            params1 = params1 + "&cardPayLogVO.authorizationcode=" + trim(resultstr.substring(resultstr.length-203,resultstr.length-197));// 授权码
	            params1 = params1 + "&cardPayLogVO.businessreferencenum=" + trim(resultstr.substring(resultstr.length-166,resultstr.length-154));// 交易参考号
	            params1 = params1 + "&cardPayLogVO.unionpaytime=" + trim(resultstr.substring(resultstr.length-14,resultstr.length));// 扣款时间
	            params1 = params1 + "&cardPayLogVO.vouchernum=";// 凭证号
	            params1 = params1 + "&cardPayLogVO.unionpayfee=" + trim(resultstr.substring(resultstr.length-124,resultstr.length-112));// 扣款金额
	            params1 = params1 + "&cardPayLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;//交易流水号，终端流水号
	            params1 = params1 + "&cardPayLogVO.status=01";//交易状态 01表示银联扣款成功
	            params1 = params1 + "&cardPayLogVO.description=" + encodeURI(encodeURI("交易成功"));						
    		}
    		else
    		{
	            params1 = "chargeLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value;//日志编号
	            params1 = params1 + "&chargeLogVO.servnumber=" + document.getElementById("servnumber").value;
	            params1 = params1 + "&chargeLogVO.unionpayuser=" + trim(resultstr.substring(resultstr.length-139,resultstr.length-124));// 商户编码
	            params1 = params1 + "&chargeLogVO.unionpaycode=" + trim(resultstr.substring(resultstr.length-154,resultstr.length-139));// POS机编号
	            params1 = params1 + "&chargeLogVO.busiType=" + encodeURI(encodeURI("消费"));//交易类型
	            params1 = params1 + "&chargeLogVO.cardnumber=" + trim(resultstr.substring(resultstr.length-191,resultstr.length-172));// 卡号
	            params1 = params1 + "&chargeLogVO.posNum=" + trim(resultstr.substring(resultstr.length-209,resultstr.length-203));// POS流水号
	            params1 = params1 + "&chargeLogVO.batchnum=" + trim(resultstr.substring(resultstr.length-197,resultstr.length-191));// 批次号
	            params1 = params1 + "&chargeLogVO.authorizationcode=" + trim(resultstr.substring(resultstr.length-203,resultstr.length-197));// 授权码
	            params1 = params1 + "&chargeLogVO.businessreferencenum=" + trim(resultstr.substring(resultstr.length-166,resultstr.length-154));// 交易参考号
	            params1 = params1 + "&chargeLogVO.unionpaytime=" + trim(resultstr.substring(resultstr.length-14,resultstr.length));// 扣款时间
	            params1 = params1 + "&chargeLogVO.vouchernum=";// 凭证号
	            params1 = params1 + "&chargeLogVO.unionpayfee=" + trim(resultstr.substring(resultstr.length-124,resultstr.length-112));// 扣款金额
	            params1 = params1 + "&chargeLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;//交易流水号，终端流水号
	            params1 = params1 + "&chargeLogVO.status=01";//交易状态 01表示银联扣款成功
	            params1 = params1 + "&chargeLogVO.description=" + encodeURI(encodeURI("交易成功"));
	        }
                    
            var loader1 = new net.ContentLoader(url1, netload = function () {
                var resXml1 = this.req.responseText;
                        
                //更新日志成功
                if (resXml1 == "0" || resXml1 == 0)
                {
                    //提交缴费请求
                    document.readCardForm.action = commitUrl;
                    document.readCardForm.submit();                                     
                }
                //更新日志失败
                else
                {                             
                    setException("银联扣款成功，更新缴费日志失败。请取走您的银联卡并联系营业人员");
                    
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
            document.getElementById("highLight6").className = "on";
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