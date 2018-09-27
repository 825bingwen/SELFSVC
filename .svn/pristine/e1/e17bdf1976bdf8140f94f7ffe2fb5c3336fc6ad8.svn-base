<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%
// 清除缓存，防止页面后退安全隐患 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Cache-Control", "no-store"); 
response.setDateHeader("Expires", -1); 

// 银联卡缴费读卡等待时间(秒)
String limitTime = (String) PublicCache.getInstance().getCachedData(Constants.SH_READCARD_TIME); 

String recFeeFen = CommonUtil.yuanToFen((String) request.getAttribute("recFee"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>读卡页面</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_sd.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/CardInstallManager_sd.js"></script>
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
		return false;
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
		return;
	}
}
// 读卡等待时间
var limitTime = <%=limitTime %>;

//等待读卡的句柄
var waitingToken;

// 提交标志
var submitFlag = 0;

// 等待读取标志，1，成功；0，失败。如果为1时，用户主动取消缴费操作，需调用取消刷卡接口
var readingCard = 0;

//出现异常
function setException(errorMsg) 
{           
    if (submitFlag == 0)
    {
        // 提交标志置为1
        submitFlag = 1;
        
        //清除定时任务
        clearInterval(waitingToken);
        
        //显示错误信息
        document.getElementById("errormessage").value = errorMsg;
        
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
        document.readCardForm.action = "${sessionScope.basePath }cardInstall/installFeeError.action";
        document.readCardForm.submit();
    }           
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

//-----------------------------------------循环读卡------------------------------------------------------------------------
// 获得读卡标志
function getReadCardStatus() 
{
    //读卡时间结束
    if (limitTime <= 0 && submitFlag == 0)
    {               
        setException("读卡超时，无法进行缴费操作");
        return;
    }

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
        setException("读卡器读卡失败，无法使用储蓄卡进行充值，请选用其它方式。");
    }
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
        strin = strin + formatStr('<%=recFeeFen %>','left','0',12);// 交易金额(12)
        strin = strin + formatStr('','right',' ',55);// POS流水号(6)+收银机号(10)+收银员号(10)+银行检索参考号(15)+授权号分期付款期数(6)+ 原交易日期(8)
        strin = strin + 'H';// 卡片类型(1)
        strin = strin + formatStr('','right',' ',240);// 自定义信息(76)+第二磁道(37)+第三磁道(104)+原交易码(2)+原终端编号(15)+原授权号(6)
        //附加信息，101：个人交费，102：代理商交费,103：预存有礼缴费，104：开户缴费
        strin = strin + formatStr("104"+document.getElementById("chargeId").value,'right',' ',100);// 移动缴费附加信息100
        
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
            resultstr = resultstr + "000000013000";// 交易金额(12)
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
            var url1 = "${sessionScope.basePath}cardInstall/updateCardChargeLog.action";
    
            var params1 = "chargeId=" + document.getElementById("chargeId").value;//日志编号
            params1 = params1 + "&unionpayuser=" + trim(resultstr.substring(resultstr.length-139,resultstr.length-124));// 商户编码
            params1 = params1 + "&unionpaycode=" + trim(resultstr.substring(resultstr.length-154,resultstr.length-139));// POS机编号
            params1 = params1 + "&busitype=" + encodeURI(encodeURI("开户缴费"));//交易类型
            params1 = params1 + "&cardnumber=" + trim(resultstr.substring(resultstr.length-191,resultstr.length-172));// 卡号
            params1 = params1 + "&posnum=" + trim(resultstr.substring(resultstr.length-209,resultstr.length-203));// POS流水号
            params1 = params1 + "&batchnum=" + trim(resultstr.substring(resultstr.length-197,resultstr.length-191));// 批次号
            params1 = params1 + "&authorizationcode=" + trim(resultstr.substring(resultstr.length-203,resultstr.length-197));// 授权码
            params1 = params1 + "&businessreferencenum=" + trim(resultstr.substring(resultstr.length-166,resultstr.length-154));// 交易参考号
            params1 = params1 + "&unionpaytime=" + trim(resultstr.substring(resultstr.length-14,resultstr.length));// 扣款时间
            params1 = params1 + "&vouchernum=";// 凭证号
            params1 = params1 + "&unionpayfee=" + trim(resultstr.substring(resultstr.length-124,resultstr.length-112));// 扣款金额
            params1 = params1 + "&terminalSeq=" + document.getElementById("terminalSeq").value;//交易流水号，终端流水号
            params1 = params1 + "&status=01";//交易状态 10表示银联扣款成功
            params1 = params1 + "&description=" + encodeURI(encodeURI("开户扣款成功"));
                    
            var loader1 = new net.ContentLoader(url1, netload = function () {
                var resXml1 = this.req.responseText;                    
                //更新日志成功
                if (resXml1 == "1" || resXml1 == 1)
                {
                    // 开户提交
                    goSuccess();                                
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

// 银联卡扣款成功，开户提交
function goSuccess() 
{
    // 应当校验一下是否为0
    if (document.getElementById("tMoney").value == "" 
            || parseInt(document.getElementById("tMoney").value) <= 0)
    {
        return;
    }
    
    // 缴费状态  0 成功
    document.getElementById("payStatus").value = "0";
    
    // 空白卡号
    var blankno = '<s:property value="blankno"/>';
    
    var cardInfoStr = document.getElementById("cardInfoStr").value;
    var blankno = document.getElementById("blankno").value;
        
    // 提交缴费请求前先写卡
    var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
    
    // 写卡失败 进行二次写卡
    if(writeData.indexOf("11~") != -1)
    {
        // 再次写卡
        againWriteCardProcess();
        return;
    }
    
    // 写卡过程异常，跳转异常页面
    if(writeData.indexOf("1~") != -1)
    {
        // 写卡失败 1
        document.getElementById("writeCardStatus").value = "1";
    
        writeCardException(writeData.split("~")[1]);
        return;
    }
    
    // 写卡成功
    document.getElementById("writeCardStatus").value = "0";
    
    // 开户状态 默认数据2 0 成功  1 失败
    document.getElementById("installStatus").value = "2";
        
    //提交缴费请求
    document.readCardForm.target = "_self";
    document.readCardForm.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
    document.readCardForm.submit();          
}

// 再次写卡流程
function againWriteCardProcess()
{
    /**
    * 写卡异常处理流程：
    *1. 设用厂商回收卡接口把费卡走到回收箱  
    *2. 走新卡到读卡位
    *3. 调用卡商的读空卡序列号磁出序列号
    *4. 调用BOSS接口校验卡资源是否可用
    *5. 转到费用明细确认页面进行写卡，直接写卡次数达到。
    */
    // 1.设用厂商回收卡接口把费卡走到回收箱 在写卡失败的时候已经移动到回收箱了
    // 2.发卡、写卡器，检查检查卡箱是否有卡，接口ReadCardStatus()需终端机厂商提供
    var message = checkReadCardStatus();
    if (message != "")
    {
        writeCardException(message);
        return;
    }
    
    // 读取空白卡序列号
    var blankCardSN = readBlankCardSN();
    
    if (blankCardSN.indexOf("1~") != -1)
    {
        writeCardException(blankCardSN.split('~')[1]);
        return;
    }
    
    if(blankCardSN.length != 20)
    {
        writeCardException("对不起，卡类型不正确，请联系营业厅管理员!");
        return;
    }
            
    // 获取空白卡序列号
    document.getElementById('blankno').value = blankCardSN;
        
    // 校验空白卡信息    
    ret = chkBlankCardInfo();
    var resArray = ret.split('~~');
    if (resArray[0] == 0 || resArray[0] == "0")
    {
        setValue("iccid",resArray[1]);
        setValue("imsi",resArray[2]);
        setValue("issueData",resArray[3]);
        setValue("formNum",resArray[4]);
    }
    else
    {
        writeCardException(resArray[1]);
        return;
    }
    
    var blankno = document.getElementById("blankno").value;
    
    var cardInfoStr = document.getElementById("cardInfoStr").value;
    
    // 提交缴费请求前先写卡
    var writeData = writeCard(cardInfoStr,blankno,"${sessionScope.basePath}","<s:property value='telnum' />");
    
    // 二次写卡失败 跳转异常页面
    if(writeData.indexOf("1~") != -1)
    {
        // 写卡失败 1
        document.getElementById("writeCardStatus").value = "1";
        writeCardException(writeData.split("~")[1]);
        return;
    }
    // 写卡成功
    document.getElementById("writeCardStatus").value = "0";
    
    // 开户状态 默认数据2 0 成功  1 失败
    document.getElementById("installStatus").value = "2";
    
    //提交缴费请求
    document.readCardForm.target = "_self";
    document.readCardForm.action = "${sessionScope.basePath }cardInstall/installCardCommit.action";
    document.readCardForm.submit();
}

// 校验空白卡信息
function chkBlankCardInfo()
{
    // 返回 0 1~~失败原因
    var ret = 1;// 0:成功 1:失败
    
    // URL
    var url = "${sessionScope.basePath}cardInstall/chkBlankCardInfo.action";
    
    // 参数
    var params = "blankno=" + document.getElementById('blankno').value + "&";
        params = params + "prodid=" + document.getElementById("mainProdId").value + "&";
        params = params + "telnum=" + document.getElementById("telnum").value ;
        
    
    // 调用
    var loader = new net.ContentLoaderSynchro(url, netload = function () {
            ret = this.req.responseText;
            setValue("cardInfoStr",ret.substring(3));
    }, null, "POST", params, null);
    
    // 返回
    return ret;
}

// 出现写卡异常
function writeCardException(errorMsg)
{
    document.getElementById("errormessage").value = errorMsg;
        
    //清除定时任务
    clearInterval(waitingToken);
        
    // 写卡异常记录异常日志（增加缴费日志与更新开户日志）
    document.readCardForm.target = "_self";
    document.readCardForm.action = "${sessionScope.basePath }cardInstall/makeErrLog.action";
    document.readCardForm.submit();
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

//页面加载时执行
function bodyLoad() 
{
    // 接口调用返回信息
    var ret;
    
    // 打开提示框
    alertJd("正在加载,请稍候...");
    
    // 标识控件使用
    closeStatus = 1;

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

	<!--------------simInfoPO信息 -------------->
    <input type="hidden" id="imsi" name="simInfoPO.imsi" value="<s:property value="simInfoPO.imsi" />" />
    <input type="hidden" id="iccid" name="simInfoPO.iccid" value="<s:property value="simInfoPO.iccid" />" />
    <input type="hidden" id="issueData" name="simInfoPO.issueData" value="<s:property value="simInfoPO.issueData" />" />
    <input type="hidden" id="formNum" name="simInfoPO.formNum" value="<s:property value="simInfoPO.formNum" />" />
	<input type="hidden" id="oldiccid" name="simInfoPO.oldiccid" value="<s:property value='simInfoPO.oldiccid'/>"/>
	
	<!-- 身份证信息 -->
	<!-- 姓名 -->
	<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
	<!-- 性别 -->
	<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
	<!-- 身份证号码 -->
	<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
	<!-- 证件地址 -->
	<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
	<!-- 开始时间 -->
	<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
	<!-- 结束时间 -->
	<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
	<!-- 卡信息 -->
	<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
	<!-- 照片信息 -->
	<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
	
	<!-- 套餐信息 -->
	<!-- 模板ID -->
	<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
	<!-- 模板名称 -->
	<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
	<!-- 产品ID -->
	<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
	<!-- 产品名称 -->
	<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
	<!-- 品牌 -->
	<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
	<!-- 套餐月费 -->
	<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
	<!-- 预存费用 -->
	<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
	
	<!-- 选号信息 -->
	<!-- 地市 -->
	<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
	<!-- 组织机构ID -->
	<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
	<!-- 地市名称 -->
	<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
	<!-- 选号规则 -->
	<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
	<!-- 前缀 -->
	<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
	<!-- 后缀 -->
	<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
	<!--空白卡序列号 -->
	<input type="hidden" id="blankno" name="blankno" value="<s:property value='blankno'/>"/>
	<!--手机号码 -->
	<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum'/>" />
	<!--IMSI -->
	<input type="hidden" id="imsi" name="imsi" value="<s:property value='imsi'/>" />

	<!-- 缴费信息 -->
	<!-- 费用合计 -->
	<input type="hidden" id="recFee" name="recFee" value="<s:property value='recFee'/>" />
	<%-- 开户日志 --%>
	<%-- 流水号 --%>
	<input type="hidden" id="installId" name="installId" value='<s:property value="installId" />'/>
	<%-- 缴费流水号 --%>
	<input type="hidden" id="chargeId" name="chargeId" value='<s:property value="chargeId" />'/>
	<%-- 缴费方式，1：银联卡；4：现金 --%>
	<input type="hidden" id="payType" name="payType" value='<s:property value="payType" />'/>
    <%-- 默认2：初始状态 0：写卡成功 1：写卡失败 --%> 
    <input type="hidden" id="writeCardStatus" name="writeCardStatus" value='<s:property value="writeCardStatus" />'/>
    <%-- 默认2：初始状态 0：缴费成功 1：缴费失败 --%> 
    <input type="hidden" id="payStatus" name="payStatus" value='<s:property value="payStatus" />'/>
    <%-- 默认2：初始状态 0：开户成功 1：开户失败 --%>
    <input type="hidden" id="installStatus" name="installStatus" value='<s:property value="installStatus" />'/>
    <%-- 是否打印小票  1-不打印，0-打印 --%>
	<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
	<%-- 银联打印信息 --%>
    <input type="hidden" id="printcontext" name="printcontext" value=""/>
    <%-- 服务密码 --%>
    <input type="hidden" id="passwd" name="passwd" value="<s:property value='passwd'/>"/>
	<%-- 写卡所需信息 --%>
    <input type="hidden" id="cardInfoStr" name="cardInfoStr" value="<s:property value='cardInfoStr'/>"/>
	<input type="hidden" id="errormessage" name="errormessage" value=''/>
	<input type="hidden" id="unionRetCode" name="unionRetCode" value=''>
	<input type="hidden" id="terminalSeq" name="terminalSeq" value=''/>
	<input type="hidden" id="tMoney" name="tMoney" value="" />
	<%-- 是否将卡移动到回收箱 1：回收 0：不回收 --%>
    <input type="hidden" id="callBackCard" name="callBackCard" value="0"/>
	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<div class="pl30">
			<div class="b257">
				<div class="bg bg257"></div>
				<h2>在线开户</h2>
				<div class="blank10"></div>
				<a href="javascript:void(0)">1. 入网协议确认</a> 
				<a href="javascript:void(0)">2. 读取身份证信息</a>
				<a href="javascript:void(0)">3. 产品选择</a> 
				<a href="javascript:void(0)">4. 号码选择</a>
				<a href="javascript:void(0)">5. 设置服务密码</a>
				<a href="javascript:void(0)">6. 费用确认</a>
				<a href="javascript:void(0)" class="on">7. 开户缴费</a>
				<a href="javascript:void(0)">8. 取卡打印小票</a>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank60"></div>
				<div class="p40 pr0">
				    <p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
                    <p class="fs22 fwb pl40 lh30">交费金额：<span class="yellow fs22"><s:property value="recFee" /></span> 元</p>
				    
 					<p class="tit_info"><span class="bg"></span>请插入您的储蓄卡，<span class="yellow">业务办理结束后将退卡，请注意取卡。</span></p>
 					<p class="tit_info"><span>读卡时长共</span><span class="yellow">30</span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="30" readonly="readonly" />秒</p>
 					<div class="blank10"></div>   
					<div class="blank20"></div>
 					<div class="blank10"></div>
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
	closeStatus = 1;
    bodyLoad();        
</script>
</html>
