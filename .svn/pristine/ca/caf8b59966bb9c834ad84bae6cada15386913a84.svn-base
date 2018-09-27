<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO" %>
<%@page import="java.util.List" %>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>
<%@page import="com.gmcc.boss.selfsvc.resdata.model.MenuInfoPO" %>
<%@page import="com.gmcc.boss.selfsvc.util.CommonUtil" %>

<%
    // 清除缓存，防止页面后退安全隐患 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
    String pOrgName = termInfo.getOrgname();
    String pTerminalInfo = termInfo.getTermname();
    
    String termSpecial = termInfo.getTermspecial();
    
    String canPrintInvoice = termSpecial.substring(1, 2);
            
    // 是否需要随机密码
    String needRandomPwd = (String)request.getAttribute("needRandomPwd");
    
    //月结发票按钮开关
    String SH_YGZ_SWITCH = (String) PublicCache.getInstance().getCachedData("SH_YGZ_SWITCH");
    
    //预存发票提示信息
    String printConfirm = (String) PublicCache.getInstance().getCachedData("SH__PRINT_INVOICE_MSG");
    if (printConfirm == null || "".equals(printConfirm))
    {
        printConfirm = "尊敬的用户您好，“营改增”后根据税务部门规定，严禁重复开票和虚开发票，如果您需要全额的月结发票，则请您不要打印预存发票。";
    }
    
    // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
	// 省份信息
    String provinceInfo = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String provinceSD = Constants.PROOPERORGID_SD;
    
    // 缴费完成后登陆鉴权后打印发票开关        1:开启    0:关闭
    String printInvoiceNew = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRINT_INVOICENEW);
    
    // 缴费完成后登陆鉴权后打印发票开关打开
    String printInvoiceOpen = Constants.PRINT_INVOICE_OPEN;
    // modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>移动自助终端</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/TerminalManager.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayReadCardManager_sd.js"></script>
        <script type="text/javascript">
        var submitFlag = 0;
        
        var payType = "<s:property value='payType' />";
        
        var limitTime = 30;//取卡时间30秒
    
        var invoiceType = "";
        
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
        
        </script>
    </head>
    <body onload="window.focus();doFinish();" scroll="no">
        <form name="payMoneyForm" method="post">
            <input type="hidden" id="invoiceType" name="invoiceType" value=""/>
            
            <s:hidden name="servnumber" id="servnumber"/>
            
            <!-- 打印月结发票标志，1：打印月结发票 -->
            <s:hidden id="monthInvoiceFlag" name="monthInvoiceFlag"/>
            
            <!-- 话费连缴标志，1：话费连缴 -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- 缴费总金额 -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- 凭条打印标记，1：已打印 -->
            <s:hidden name="printPayProFlag" id="printPayProFlag"/>
            
            <!-- 月结发票打印账期 -->
            <s:hidden name="invoicePrint.billCycle" id="month" />
            
            <!-- 打印全部发票标志，1:正打印-->
            <s:hidden name="printAllInvFlag" id="printAllInvFlag"/>
            
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- 已打印手机号码字符串 -->
            <s:hidden id="printInvTelnum" name="printInvTelnum"/>
            
            <s:hidden name="recoid" id="recoid" />
            
            <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
			<!--预存、月结打印标识 -->
			<input type="hidden" name="printFlag" id="printFlag" value="<s:property value='printFlag' />"/>
			
			<!--打印全部发票标识 -->
			<input type="hidden" name="printAllFlag" id="printAllFlag" value="<s:property value='printAllFlag' />"/>
			
			<!-- 多人缴费标识 -->
			<input type="hidden" name="morePhonesFlag" id="morePhonesFlag" value="<s:property value='morePhonesFlag' />"/>
			<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>充值交费流程：</h2>
                        <div class="blank10"></div>
                        <a href="#">1. 输入手机号码</a> 
                        <a href="#">2. 选择支付方式</a> 
                        <a href="javascript:void(0)">3. 免责声明</a>
                        <a href="javascript:void(0)">4. 插入银联卡</a>
                        <a href="javascript:void(0)">5. 输入密码</a>
                        <a href="javascript:void(0)" class="on">6. 完成</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank30"></div>
                        <div class="p40 pr0">
                            <div class="recharge_result tc">
                              <p class="title yellow ">您的充值交费已完成。</p>
                              <p class="desc pt5">请保存好您的交易凭条，并再次确认已取走您的银联卡。</p>          
                            </div>
                            <div class="blank20"></div>
                            <div class="line w625"></div>
                            <div class="blank10"></div>
                            <div class="tit_info fl"><span class="bg"></span></div> 
                            <table width="540px "height="40px" style="line-height:40px;" class="telnum_list2 fl fs20" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <th class="bdb1px" width="15%">缴费结果</th>
                                    <th class="bdb1px" width="28%">手机号码</th>
                                    <th class="bdb1px">金额</th>
                                    <th class="bdb1px">打印</th>
                                </tr>            
                            </table>
                            <div id="sucessTable" class="tabdiv">
                                <table width="540px" class="telnum_list2 fs20"  cellpadding="0" cellspacing="0" border="0" id="tablelen">
                                    <s:iterator value="morePhones" id="morePhone" status="st">
                                        <tr>
                                            <s:if test="0 == #morePhone.chargeStatus">
                                                <td class="yellow" width="15%">成功</td>
                                            </s:if>
                                            <s:else>
                                                <td style="color:red" width="15%">失败</td>
                                            </s:else>
                                            <td class="yellow" width="28%"><s:property value="#morePhone.servnumber"/></td>
                                            <td class="yellow"><s:property value="#morePhone.tMoney"/>元</td>
                                            <td>
                                              <s:if test="0 == #morePhone.chargeStatus">
                                                 <%
	                                                if ("1".equals(canPrintInvoice))
	                                                {       
	                                             %>
	                                                <s:if test="printInvTelnum.indexOf(#morePhone.servnumber) > 0">
		                                                <a class="print" id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">打印发票</a>
	                                                </s:if>
	                                                <s:else>
	                                                    <a id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">打印发票</a>
	                                                </s:else>
	                                             <%}
	                                               else
	                                               {
		                                         %>
	                                                <a class="print" id="print_<s:property value='#st.index + 1'/>" href="javascript:void(0);" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">打印发票</a>
	                                              <%}%>
                                              </s:if>
                                              <s:else>
                                                  <a href="javascript:void(0);" id="print_<s:property value='#st.index + 1'/>" disabled="true" onclick="printInvoice('Last','1','<s:property value="#morePhone.servnumber"/>','<s:property value="#morePhone.recoid"/>');return false;">打印发票</a>
                                              </s:else>
                                                  <%
                                                    if ("1".equals(SH_YGZ_SWITCH))
                                                    {
                                                  %>
                                                    <a href="javascript:void(0);" onclick="printMonthInvoice('<s:property value="#morePhone.servnumber"/>');return false;">打印月结</a>
                                                 <%} 
                                                   else
                                                   {
                                                 %>
                                                    <a href="javascript:void(0);" class="print" disabled="true" onclick="printMonthInvoice('<s:property value="#morePhone.servnumber"/>');return false;">打印月结</a>
                                                  <%} %>
                                            </td>            
                                        </tr>
                                    </s:iterator>
                                </table>  
                            </div>
                            <div class="box70W scrollbtn" id="scrollbar">
                                <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                                <div class="boxPage" style="width:75px; height:80px; ">
                                    <div class="blank10px"></div>
                                    <div class="box66W tc f16 lh30" id="gunDom" style=" position:absolute; width:66px; height:36px; top:52px; left:3px; cursor:move; line-height:36px" >0%</div>
                                </div>
                                <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                            </div>
                            <table width="540px" class="telnum_list2 fl fs20 ml40 yellow" style="_margin-left:20px;" cellpadding="0" cellspacing="0" border="0">
                                <tr class="trbg" align="left">
                                    <td width="15%">合计：</td>
                                    <td width="28%"></td>
                                    <td><s:property value="totalFee"/>元</td>
                                    <td></td>            
                                </tr>
                            </table>  
                            <div class="multi_sucessbtn">
                                <div class="btn_box clearfix" style="width:580px;padding-left:30px;">
                                	
                                	<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
                                	<% 
                                	if (!printInvoiceNew.equals(printInvoiceOpen))
									{
									%>
                                    <s:if test="1 == printAllInvFlag">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">打印全部发票</a>
                                    </s:if>
                                    <s:elseif test="'' != printInvTelnum">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">打印全部发票</a>
                                    </s:elseif>
                                    <s:else>
                                        <a href="" id="printAllInv" onclick="printAllInvoice(); return false;">打印全部发票</a>
                                    </s:else>
                                    <%
                                    }
                                    else
                                    {
                                    %>
                                    <s:if test="'' != printInvTelnum">
                                        <a href="" disabled="true" class="printAll" onclick="printAllInvoice(); return false;">打印全部发票</a>
                                    </s:if>
                                    <s:else>
                                        <a href="" id="printAllInv" onclick="printAllInvoice(); return false;">打印全部发票</a>
                                    </s:else>
                                    <%
                                    }
                                    %>
                                    <%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
                                    
                                    <a href="" onclick="goHomePage();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">结束办理</a>
                                    <a href="" onclick="continueCharge();return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">继续充值交费</a>
                                </div>
                            </div>
                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
                <div class="popup_confirm" id="popup_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">提示：</div>
                    <div class="tips_body">
                        <p>您将打印发票，为了确保您信息的安全，系统将下发短信校验码到您的手机，请将收到的短信校验码输入下个页面的校验码框内。</p>
                        <div class="blank10"></div>
                        <p class="mt30">请确认是否继续？</p>
                    </div>
                    <div class="btn_box tc mt20">
                        <span class=" mr10 inline_block ">
                            <a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="sendRandomPwd();return false;">确认</a>
                        </span>
                        <span class=" inline_block ">
                            <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();submitFlag=0;">取消</a>
                        </span>
                    </div>
                </div>
                
                <%-- 发票打印提示信息 add by sWX219697 2014-7-22 17:54:15 OR_huawei_201407_1042_自助终端（山东）充值发票打印优化--%>
                <div class="popup_confirm" id="print_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">提示：</div>
                    <div class="tips_body">
                        <p><%=printConfirm%></p>
                        <div class="blank10"></div>
                        <p class="mt30">请确认是否继续？</p>
                    </div>
                    <div class="btn_box tc mt20">
                        <span class=" mr10 inline_block ">
                            <a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">不打印</a>
                        </span>
                        <span class=" inline_block ">
                            <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="printInvoice('Last','0');return false;">继续打印预存发票</a>
                        </span>
                    </div>
                </div>
                                
                <script type="text/javascript">
                myScroll = new virtualScroll("sucessTable","gunDom");
                openWindow = function(id)
                {
                    wiWindow = new OpenWindow(id, 708, 392);//打开弹出窗口例子
                }               
                </script>
            </div>
            
            <%@ include file="/backinc.jsp"%>           
        </form>
    </body>
</html>
<script type="text/javascript">
function goback(menuid)
{
    if (submitFlag == 0)
    {
        document.getElementById("curMenuId").value = menuid;
        
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
        document.forms[0].submit();
    }
}       

function sendRandomPwd()
{
    document.getElementById("invoiceType").value = invoiceType;
            
    document.payMoneyForm.target="_self";
    document.payMoneyForm.action="${sessionScope.basePath }charge/validateByRandomPwd.action";
    document.payMoneyForm.submit();
}

// 读取读卡器取卡状态
function takeOutBankCardStatus() 
{
    limitTime = limitTime - 1;
    
    if (limitTime <= 0)
    {
        //超时，清除定时任务，同时吞卡
        clearInterval(waitingToken);
        
        //吞卡
        CaptureBankCard();
        
        return;
    }
    
    try 
    {
        //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
        var ret = OpenComByCard();
    
        ret = getCardPosition();
        
        //测试用
        if (ret == "1" || ret == 1) 
        {   
            //已经取走卡，清除定时任务
            clearInterval(waitingToken);                                    
        }           
    }
    catch (e){}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

function startClock()
{
    try 
    {
        waitingToken = setInterval("takeOutBankCardStatus()", 1000);
    }
    catch (e) {}//卡已经退出，即便在检查取卡状态时发生异常，也不做任何处理了
}

function doFinish()
{
	<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
	// 清空验证码时间
	window.parent.currLeftTime = "";
	window.parent.currNumber = "";
	<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
	
    // 凭条没有打印过，去打印凭条
    if ("1" != document.getElementById("printPayProFlag").value)
    {
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	            "交费成功");
	    
	    //生产用 0卡在读卡器内；-1 获取失败；1读卡器内无卡；2 持卡状态(退卡后未取卡)
	    var ret = getCardPosition();
	    
	    //吐卡成功，启动定时任务，看用户是否在30秒内取走银联卡，如果没有，吞卡
	    if (ret == "2")
	    {
	        startClock();
	    }
	    
	    // 凭条标记为已打印
	    document.getElementById("printPayProFlag").value = "1";
	    
	    // 手机号码
	    var pServNumber = "";
	    
	    // 终端交易流水
	    var pTerminalSeq = "";
	    
	    // 交易流水
	    var pDealNum = "";
	    
	    // 客户名称
	    var pCustName = "";
	    
	    // 单笔业务缴费金额
	    var tMoney = "";
	    
	    var pStatus = "";
	    <s:iterator value="morePhones" id="morePhone">
		     pServNumber = pServNumber + "<s:property value='#morePhone.servnumber' />" + ",";
		     pTerminalSeq = pTerminalSeq + "<s:property value='#morePhone.chargeLogOID' />" + ",";
		     pDealNum = pDealNum + "<s:property value='#morePhone.dealnum'/>" + ",";
		     pCustName = pCustName + "<s:property value='#morePhone.custName'/>" + ",";
		     tMoney = tMoney + "<s:property value='#morePhone.tMoney'/>" + ",";
		     pStatus = pStatus + "<s:property value='#morePhone.chargeStatus'/>" + ",";
	    </s:iterator>
	    var pOrgName = "<%=pOrgName%>";  //打印地点
	    var pPrintDate = getDateTime();  //打印日期
	    var pTerminalInfo = "<%=pTerminalInfo%>"; //终端信息
	    var pDealTime = "<s:property value='pDealTime' />";   //交易时间
	    var totalFee = "<s:property value='totalFee' />";     //交易金额
	    
	    var printcontext = "<s:property value='printcontext' />";// 银联小票
	    
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	            "自动打印凭条");
	    
	    doPrintMorePhoneSD(pServNumber, pOrgName, pPrintDate, pTerminalInfo, pDealNum, pDealTime, tMoney,totalFee,
	                pTerminalSeq, pCustName, pStatus, printcontext);
    }
    
}
//继续充值缴费
function continueCharge()
{   
    document.payMoneyForm.target = "_self";
    document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
    document.payMoneyForm.submit();
}

// 打印月结发票
function printMonthInvoice(telnum)
{   
    // 打印月结发票标志，1：打印月结发票
    document.getElementById("monthInvoiceFlag").value = "1";
    document.getElementById("servnumber").value = telnum;
    document.getElementById("month").value = <%=CommonUtil.getLastMonth("yyyyMM", 1)%>;
    document.payMoneyForm.target = "_self";
    
    <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
	// 发票打印登陆鉴权 
	if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
	{
		// 月结标识
		document.getElementById("printFlag").value = "YJ";
		document.getElementById("printAllFlag").value = "";
		document.payMoneyForm.action = "${sessionScope.basePath}charge/goServicePwdPage.action";
	}
	else
	{
    	document.payMoneyForm.action = "${sessionScope.basePath}charge/qryBillCycle.action";
    }
    <%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
    
    document.payMoneyForm.submit();
}

//打印当前发票，有多张发票打印最后一张
function printInvoice(invoicetype,flag,telnum,oid)
{
    // 多人缴费标志
	document.getElementById("morePhonesFlag").value = "morePhones";
	document.getElementById("printFlag").value = "";
    if (flag == "1")
    {
    	document.getElementById("printAllFlag").value = "";
        document.getElementById("monthInvoiceFlag").value = "0";
        document.getElementById("servnumber").value = telnum;
        document.getElementById("recoid").value = oid;
        openWindow('print_confirm');
        return;
    }
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            telnum + "选择打印发票");
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        invoiceType = invoicetype;
        
        //打印发票时需要随机密码
        if ("1" == "<%=needRandomPwd %>")
        {
            openWindow('popup_confirm');
        }
        else
        {
            document.getElementById("invoiceType").value = invoicetype;
            document.forms[0].target = "_self";
            
            // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
			// 发票打印登陆鉴权
			if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
			{
				document.forms[0].action = "${sessionScope.basePath}charge/goServicePwdPage.action";
			}
			else
			{
            	document.forms[0].action = "${sessionScope.basePath }charge/printInvoiceWithoutPwd.action";
            }
            // modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
            
            document.forms[0].submit();
        }
    }               
}

// 打印全部发票
function printAllInvoice()
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        // 将打印全部发票标志为打印
        document.getElementById("printAllInvFlag").value = "1";
        document.getElementById("invoiceType").value = "Last";
        document.forms[0].target = "_self";
        
        // modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
		// 发票打印登陆鉴权打开
		if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
		{
			submitFlag = 0;
			
		    // 全部打印标识
			document.getElementById("printAllFlag").value = "all";
			document.getElementById("monthInvoiceFlag").value = "0";
			
			// 月结标识
			document.getElementById("printFlag").value = "";
			printInvoice('Last','0');
			return false;
		}
		else
		{
        	document.forms[0].action = "${sessionScope.basePath }charge/printAllInvoice.action";
        	document.forms[0].submit();
        }
    }  
}
</script>

