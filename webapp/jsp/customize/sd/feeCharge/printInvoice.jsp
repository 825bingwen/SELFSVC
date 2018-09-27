<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%             
    // 清除缓存，防止页面后退安全隐患 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    String invoiceData = (String) request.getAttribute("invoiceXML");
    String invoiceType = (String) request.getAttribute("invoiceType");
    String printType = (String) request.getAttribute("printType");
    
	//add begin by cwx456134 2017-04-20 OR_SD_201703_234_山东_电子发票优化需求
	String isElectronInvoice = (String) request.getAttribute("isElectronInvoice");
	String servnumber = (String) request.getAttribute("servnumber");
	//add end by cwx456134 2017-04-20 OR_SD_201703_234_山东_电子发票优化需求
    
    //modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    // 缴费完成后登陆鉴权后打印发票开关        1:开启    0:关闭
    String printInvoiceNew = (String) PublicCache.getInstance().getCachedData(Constants.SH_PRINT_INVOICENEW);
    
    // 缴费完成后登陆鉴权后打印发票开关打开
    String printInvoiceOpen = Constants.PRINT_INVOICE_OPEN;
    //modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title></title>
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/SelfPayPrinter.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
        <script type="text/javascript">
        //<!--
        //防止页面重复提交
        var submitFlag = 0;
        
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
        
        //-->
        </script>
    </head>
    <body onload="window.focus();doFinish();" scroll="no">
        <form name="payMoneyForm" method="post">
            <input type="hidden" id="servnumber" name="servnumber" value="<s:property value='servnumber' />">
            
            <%-- add begin jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
            <!-- 话费连缴标志，1：话费连缴 -->
            <s:hidden id="morePhoneFlag" name="morePhoneFlag" />
            
            <!-- 打印月结发票标志，1：打印月结发票 -->
            <s:hidden id="monthInvoiceFlag" name="monthInvoiceFlag"/>
            
            <!-- 用户信息字符串 -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
            
            <!-- 缴费总金额 -->
            <s:hidden name="totalFee" id="totalFee" />
            
            <!-- 凭条打印标记，1：已打印 -->
            <s:hidden name="printPayProFlag" id="printPayProFlag"/>
            
            <!-- 月结发票打印账期 -->
            <s:hidden name="invoicePrint.billCycle" id="month" />
            
            <!-- 打印全部发票标志，1:正打印 -->
            <s:hidden name="printAllInvFlag" id="printAllInvFlag"/>
            
            <!-- 主动营销是否已推荐，1：已推荐 -->
            <s:hidden name="serviceDialogFlag" id="serviceDialogFlag"/>
            
            <!-- 已打印手机号码字符串 -->
            <s:hidden id="printInvTelnum" name="printInvTelnum"/>
            <%-- add end jWX216858 2015-4-16 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
            
            <%-- add begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
            <%--多人缴费已打印预存发票号码个数 --%>
			<input type="hidden" name="printInvTelnumLen" id="printInvTelnumLen" value="<s:property value='printInvTelnumLen' />"/>
			
			<%--多人缴费号码总个数 --%>
			<input type="hidden" name="morePhonesLen" id="morePhonesLen" value="<s:property value='morePhonesLen' />"/>
			
			<%-- 多人缴费打印全部打印标识 --%>
			<input type="hidden" name="printAllFlag" id="printAllFlag" value="<s:property value='printAllFlag' />"/>
			
			<%--多人缴费标识  "morePhones" 多人缴费         ""单人缴费  --%>
			<input type="hidden" name="morePhonesFlag" id="morePhonesFlag" value="<s:property value='morePhonesFlag' />"/>
            <%-- add end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
            
            <%@ include file="/titleinc.jsp"%>
            
            <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
            <div class="popup_confirm" id="print_confirm1">
                    <div class="bg"></div>
                    <div class="tips_title">提示：</div>
                    <div class="tips_body">
                        <p></p>
                        <div class="blank10"></div>
                        <p class="mt30">请确认是否继续打印其他号码的发票?</p>
                    </div>
                    <div class="btn_box tc mt20">
                        <span class=" mr10 inline_block ">
                            <a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">不打印</a>
                        </span>
                        <span class=" inline_block ">
                            <a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="continuePrintAll()">继续打印发票</a>
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
            	<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>充值交费流程：</h2>
                        <div class="blank10"></div>
                        <a href="#">1. 输入手机号码</a> 
                        <a href="#">2. 选择支付方式</a> 
                        <a href="#">3. 选择金额</a> 
                        <a href="#">4. 支付</a>
                        <a href="#" class="on">5. 完成</a>
                    </div>
                    
                    <div class="b712">
                        <div class="bg bg712"></div>
                        <div class="blank60"></div>
                        <div class="p40 pr0">
                        
                        <%-- add begin by cwx456134 2017-04-20 OR_SD_201703_234_山东_电子发票优化需求 --%>
      					<div id="emailList" style="display: none;">
    						<table width="540px" class="telnum_list fl fs20" cellpadding="0" cellspacing="0" border="0">
					            <tr>
					                <th class="bdb1px" width="33%">手机号码</th>
					                <th class="bdb1px" width="48%">电子邮箱</th>
					                <th class="bdb1px" width="19%">发送结果</th>
					            </tr>  
					        </table>
					        <div id="sucessTable0" class="tabdiv0" style="height: 180px;margin-left: 0px;">
					            <table width="540px"  class="telnum_list fl fs20 "   cellpadding="0" cellspacing="0" border="0">
						            <s:iterator value="#request.messages" id="message">
						                <tr>
						                    <td class="yellow" width="33%"><s:property value="#message.telNum"/></td>
		                                    <td class="yellow" width="48%"><s:property value="#message.email"/></td>
		                                    <td class="yellow" width="19%"><s:property value="#message.flag"/></td>   
						                </tr>
						            </s:iterator>
						        </table>
						    </div>
					        <div class="box70W scrollbtn" id="scrollbar">
					            <input type="button" class="btnUp" onclick="myScroll.moveUp(40)" />
					            <div class="boxPage" style="width:75px; height:80px; ">
					                <div class="blank10px"></div>
					                <div class="box66W tc f16 lh30 disn" id="gunDom" style=" position:absolute; width:66px; height:20px; top:52px; left:3px; cursor:move; line-height:36px" >0%</div>
					            </div>
					            <input type="button" class="btnDown" onclick="myScroll.moveDown(40)"/>
					        </div>
						 </div>
                        <%-- add begin by cwx456134 2017-04-20 OR_SD_201703_234_山东_电子发票优化需求 --%>
                        
                            <%-- modify begin jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
                            <s:if test="1 != printAllInvFlag">
	                            <p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='servnumber' /></span></p>
	                            <%-- modify begin kWX211786 20141/6/23  自助缴费增加月结发票打印 
	                            自主缴费发票打印 显示缴费金额 月结发票打印 显示账期--%>
	                            <s:if test="tMoney != null && tMoney != ''">
	                                <p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
	                            </s:if>
	                            <%-- modify begin kWX211786 20141/6/23  自助缴费增加月结发票打印--%>
	                            <div class="blank20"></div>
	                            <div class="line w625"></div>
                            </s:if>
                            <%-- modify end jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
                            <div class="blank30"></div>
                            <div class="recharge_result print tc">
                                <p id="resultMsg1" class="title yellow pt30">发票正在打印</p>
                                <p id="resultMsg2" class="desc pt20">请稍侯...</p>
                                <div class="btn_box4 clearfix">
                                    <%-- modify begin jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
                                    <s:if test="1 == morePhoneFlag">
                                       <a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continuePrint();return false;">继续打印发票</a>
                                    </s:if> 
                                    <s:else>
                                       <a href="javascript:void(0)" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="continueCharge();return false;">继续充值交费</a>
                                    </s:else> 
                                    <%-- modify end jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>                             
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <%@ include file="/backinc.jsp"%>           
        </form>
    </body>
</html>

<script type="text/javascript">
//确认
function doFinish() 
{
	<%-- add begin by cwx456134 2017-04-19 OR_SD_201703_234_山东_电子发票优化需求 --%>
	<%-- 获取开关，是否启动电子发票，如启动，走此处逻辑 ；电子发票接口不返回发票信息--%>
	var isElectronInvoice = "<%=isElectronInvoice %>";
	if (isElectronInvoice == "true")
	{
		var servnumber = "<%=servnumber %>";
		if(servnumber!=null && servnumber !="")
		{
			var innerHtml = "<span style='font-size:23px;line-height:35px;'>当前开具发票为电子发票，电子发票开具后将推送到您的邮箱："+servnumber+"@139.com，请注意查收。</span>";
			document.getElementById("resultMsg1").innerHTML = innerHtml;
			document.getElementById("resultMsg2").innerHTML = "";
		}
		else //("1" == "<s:property value='printAllInvFlag'/>")
	    {
			document.getElementById("emailList").style.display="block";
			document.getElementById("resultMsg1").style.display="none";
			document.getElementById("resultMsg2").style.display="none";
	    }
		
		return;
	}
	<%-- add end by cwx456134 2017-04-19 OR_SD_201703_234_山东_电子发票优化需求 --%>
	
	<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
	// 清空验证码时间
	window.parent.currLeftTime = "";
	window.parent.currNumber = "";
	<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
	
    // 打印全部发票
    if ("1" == "<s:property value='printAllInvFlag'/>")
    {
        printLastInvoice();
        
        <%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
		// 发票打印登陆鉴权
		if ('<%=printInvoiceOpen%>' == '<%=printInvoiceNew %>')
		{
			// 没有打印到最后一个号码 且 为打印全部发票
			if ("<s:property value='printInvTelnumLen+1' />" != "<s:property value='morePhonesLen' />" 
				&& "<s:property value='printAllFlag' />" == "all")
			{
				openWindow('print_confirm1');
			}
		}
		<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
        
        return;
    }
    if ("Last" == "<%=invoiceType %>")
    {
        printLastInvoice();
    }       
}

function printAllInvoice()
{
	var telnum = "<s:property value='telnums'/>";
	
	var telnums = telnum.split(",");
	// 循环打印预存发票
	
	<s:iterator value="#request.invoice" id="inv" status="status">
	    var invoiceXml = getDocument('<s:property escape="false"/>');
	    if (invoiceXml == null)
	    {
	        return;
	    }
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	                "发票打印开始");
	    
	    document.getElementById("servnumber").value = telnums['<s:property  value="#status.index"/>'];
	    
	    //打印发票
	    var allInvoices = invoiceXml.getElementsByTagName("entry");
	    for (i = 0; i < allInvoices.length; i++)
	    {
	        printInvoice(allInvoices[i]);
	    }
	    
	    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
	                "发票打印结束");
	</s:iterator>
	document.getElementById("resultMsg1").innerHTML = "您的发票已打印成功！";
	document.getElementById("resultMsg2").innerHTML = "请保存好您的发票。";
}


// 打印发票
function printLastInvoice()
{   
    var invoiceXml = getDocument('<%=invoiceData%>');
    if (invoiceXml == null)
    {
        return;
    }
    
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "发票打印开始");
    
    //打印发票
    var allInvoices = invoiceXml.getElementsByTagName("entry");
    for (i = 0; i < allInvoices.length; i++)
    {
        printInvoice(allInvoices[i]);
    }
    
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "发票打印结束");
    document.getElementById("resultMsg1").innerHTML = "您的发票已打印成功！";
    document.getElementById("resultMsg2").innerHTML = "请保存好您的发票。";
}

/**
  * 取得缓存相关发票xml数据的documentElement对象
  * id xml对象的ID属性
  */
function getDocument(invoiceData) 
{
    try
    {
        var xmlDoc; 
        if (IsIE() == 1)
        {
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = "false";
            
            xmlDoc.loadXML(invoiceData);
            return xmlDoc;
        }
        else
        {
            var parser = new DOMParser();
            xmlDoc = parser.parseFromString(invoiceData, "text/xml");
            return xmlDoc;
        }
    } 
    catch(e) 
    {
        pubErrShow("浏览器不能识别，无法进行发票打印!");
        
        return null;
    }
}

function printInvoice(invoice) 
{
    // 打印山东发票
    if ("1" == "<%=printType %>")
    {
        printInvoiceSD1(invoice);
    }
    else
    {
        printInvoiceSD(invoice);
    }
    
    var feeTime = getXmlInvoiceData(invoice, "feeTime");
    var servnumber = document.getElementById("servnumber").value;
    <%-- modify begin zKF66389 2015-9-24 OR_SD_201508_459 【一线体验】关于对自助终端--发票打印日志记录异常的整改需求 --%>
    //var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?=" + servnumber + "&cycle=" + feeTime;
    var url = "${sessionScope.basePath }charge/insertInvoiceLog.action?servnumber=" + servnumber + "&cycle=" + feeTime;
    <%-- modify end zKF66389 2015-9-24 OR_SD_201508_459 【一线体验】关于对自助终端--发票打印日志记录异常的整改需求 --%>
    var loader = new net.ContentLoader(url, null, null, "POST", "", null);
}

function getXmlInvoiceData(invoice, itemname) 
{
    var items = invoice.getElementsByTagName(itemname);
    var itemdata = "";
    if (items.length > 0) {
        itemdata = items[0].childNodes[0].nodeValue;
    }
    return itemdata;
}

function goback(menuid)
{
    if (submitFlag == 0)
    {
        document.getElementById("curMenuId").value = menuid;
        
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }charge/feeCharge.action";
        
        <%-- modify begin jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
        // 当前页面为话费连缴打印发票完成页面，返回缴费完成页面
        if ("1" == '<s:property value="morePhoneFlag"/>')
        {
            document.forms[0].action = "${sessionScope.basePath }charge/finish.action";
        }   
        // 当前页面为话费连缴打印月结发票完成页面，返回查询账期页面
        if ("1" == '<s:property value="morePhoneFlag"/>' && "1" == '<s:property value="monthInvoiceFlag"/>')
        {
            document.getElementById("month").value = <%=CommonUtil.getLastMonth("yyyyMM", 1)%>;
            document.forms[0].action = "${sessionScope.basePath }charge/qryBillCycle.action";
        }
            
        <%-- modify end jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>         
        document.forms[0].submit();
    }
}

//继续充值缴费
function continueCharge()
{   
    document.payMoneyForm.target = "_self";
    document.payMoneyForm.action = "${sessionScope.basePath }<%=menuURL %>";
    document.payMoneyForm.submit();
}

<%-- add begin jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>
// 继续打印发票
function continuePrint()
{
    document.forms[0].target = "_self";
    document.forms[0].action = "${sessionScope.basePath }charge/finish.action";
    document.forms[0].submit();
}
<%-- add end jWX216858 2015-4-13 OR_SD_201501_1063 自助终端支撑连缴功能优化 --%>

<%-- modify begin by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>
// 继续打印全部发票
function continuePrintAll()
{
	document.forms[0].target = "_self";
	document.forms[0].action = "${sessionScope.basePath}charge/goServicePwdPage.action";
	document.forms[0].submit();
}
<%-- modify end by qWX279398 2015-11-25  OR_SD_201511_30_山东_自助终端缴费时直接打印发票的优化--%>

</script>
<script type="text/javascript" >
   myScroll = new virtualScroll("sucessTable0","gunDom");       
</script>
