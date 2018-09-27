<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.math.BigDecimal"%>


<%
	// add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
	String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	// 积分全部兑换计算方式
	String ward = (String) PublicCache.getInstance().getCachedData(Constants.SH_RTSCOREEXEALL_RULES);
	double subsReward = Double.valueOf(ward);
	
	// 获取积分
	int reward = Integer.parseInt((String)request.getAttribute("subsScore"));
	reward = reward/10;
	
	BigDecimal bd1 = BigDecimal.valueOf(reward);
	BigDecimal bd2 = BigDecimal.valueOf(subsReward);
	
	BigDecimal bd3 = bd1.multiply(bd2);
	// add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			
			<%-- 批次编码 --%>
			<s:hidden name="rewardPO.activityId" id="activityId"/>
			
			<%-- 档次编码 --%>
			<s:hidden name="rewardPO.actLevelId" id="actLevelId"/>
			
			<%-- 奖品串编码 --%>
			<s:hidden name="rewardPO.actreward" id="actreward"/>
			
			<%--奖品数量 --%>
			<s:hidden name="rewardPO.quantity" id="quantity"/>
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!--滚动条-->
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">

							<div class="div747w444h">
								<p class=" tit_info">
									<span class="bg"></span>当前可兑换积分：<span class="yellow">
									${subsScore}</span>
									
									<%--add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
									&nbsp;&nbsp;&nbsp;
									<s:if test="null != rewardPO">
										<span class="bg"></span>可兑换<%=bd3 %>元电子券
										<input type="button" class="bt2_liebiao"
															style="color: #FFFFFF;" value="全部兑换"
															onmousedown="this.className='bt2on'"
															onmouseup="this.className='bt2_liebiao'"   
															onclick="scoreExchange('1','${rewardPO.actScore}','${rewardPO.rewardName}','${rewardPO.activityId}','${rewardPO.actLevelId}','${rewardPO.actreward}','<%=reward %>')"/>
									</s:if>
									<%--add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
									
								</p>
								
							<div class="ptop180 tc box747w367h" id="inn" style="height:420px;">
								<p class="blank10"></p>
								<p class="tit_arrow_hide pl40" style="height:150px;text-align:left;">
					            	<s:if test="null != exchangeTipList && exchangeTipList.size > 0">
					            		<s:iterator value="exchangeTipList">
					            			<%-- html标签转义--%>
					            			<s:property value="description" escape="false"/>
					            		</s:iterator>
					            	</s:if>
					            	<s:else>
					            		将您手机的积分兑换成可消费的电子现金券，不同的积分可兑换成不同金额的电子券，在您消费时出抵现金使用。电子券特点如下：<br />
					            		
						            	1、兑换便捷，操作简单，消费时使用一条动态短信就可轻松实现购物；<br />
						
										2、安全性高，不易丢失，不易破损；<br />
						
										3、使用灵活，无使用次数限制。比如您有100元可消费电子券，可以分多次进行消费，不用一次消费完；<br />
						
										4、首次使用时电子券时需先免费开通中国移动和包业务，开通方式编辑短信KT至10658888；
					            	</s:else>
								</p>
								
									<table width="100%" class="tb_blue" align="center">
										<tr>
											<th scope="col">
												活动名称
											</th>
											<th scope="col">
												档次说明
											</th>
											<th scope="col">
												奖品名称
											</th>
											<th scope="col">
												交费金额(元)
											</th>
											<th scope="col">
												操作
											</th>
										</tr>
										<s:if test="null != exECashList && exECashList.size > 0">
										<s:iterator value="exECashList" id="list" status="st">
											<tr>

												<td class="tc">
													${list.activityDesc}
												</td>
												<td class="tc">
													${list.actLevelName}
												</td>
												<td class="tc">
													${list.rewardName}
												</td>
												
												<%-- modify by sWX219697 2015-7-20 prePayFee改为prepayFee--%>
												<td class="tc">
													${list.prepayFee}
												</td>
												<td>
												
													<%--add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
													<input type="button" class="bt2_liebiao"
														style="color: #FFFFFF;" value="兑换"
														onmousedown="this.className='bt2on'"
														onmouseup="this.className='bt2_liebiao'"   
														onclick="scoreExchange('1','${list.actScore}','${list.rewardName}','${list.activityId}','${list.actLevelId}','${list.actreward}','')"/>
													<%--add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
														
												</td>
											</tr>
										</s:iterator>
										</s:if>
										<s:else>
											<tr><td colspan="5"><s:property value="exCashTip"/></td></tr>
										</s:else>
									</table>
									<p class="blank10"></p>
								</div>

							</div>

						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>

								<div class="box66W tc f16 div66w36h" id="gunDom">
									0%
								</div>
							</div>
							<input type="button" class="btnDown"
								onclick="myScroll.moveDown(30)" />
						</div>

						<div class="clear"></div>

					</div>

					<div class="btm"></div>
				</div>
				
				<div class="popup_confirm" id="exchange_confirm">
					<div class="bg"></div>
					<div class="tips_title">
						提示：
					</div>
					<div class="tips_body">
						<div class="blank30"></div>
						<p id="exchange_tip"></p>
						<div class="blank30"></div>
					</div>
					<div class="btn_box tc mt20">
						<span class=" mr10 inline_block "><a href="javascript:void(0);"
							class="btn_bg_146" onmousedown="this.className='key_down'"
							onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="scoreExchange('0');return false;">确定</a>
						</span>
						<span class=" inline_block "><a class="btn_bg_146" href="#"
							onmousedown="this.className='key_down'"
							onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
						</span>
					</div>
				</div>
				
				<%-- add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化 --%>	
				<!-- 列表内容 -->
                <%
                    String scoreSwitch = PublicCache.getInstance().getCachedData("SH_SCORELIST_SWITCH") == null ? "0" : (String)PublicCache.getInstance().getCachedData("SH_SCORELIST_SWITCH");
                    if ("1".equals(scoreSwitch))
                    {
                %>
                    <div class="btn_box tc">
                        <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';;" onclick="jfmxcx('<%=provinceID%>')">积分明细查询</a></span>
                        <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="jfdhlscx('<%=provinceID%>')">积分兑换历史查询</a></span>
                        <%-- add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求 --%>
                        <%
                            String scoreProFBSwitch = CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH);
                            if (provinceID.equalsIgnoreCase(Constants.PROOPERORGID_SD) && "1".equals(scoreProFBSwitch))
                            {
                         %>
                         <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="scorePaymentQry('<%=provinceID%>')">积分发放查询</a></span>
                         <%
                            }
                          %>
                         <%-- add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求 --%>
                    </div>
                <%
                    }
                %>
				<%-- add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化 --%>
				
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
				<!--滚动条结束-->
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
			<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
    //已经选择了月份或者点击了返回，等待应答，不再执行任何操作
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = curmenu;
        
        document.actform.action="${sessionScope.basePath }login/backForward.action";
        document.actform.submit();
    }           
}

document.onkeyup = pwdKeyboardUp;
        
function pwdKeyboardUp(e)
{
    //8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
    //接收键盘码
    var key = GetKeyCode(e);
     
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
        return false;
    }

        //82:R 220:返回
        if (key == 82 || key == 220)
        {
            goback("<s:property value='curMenuId' />") ;
            return ;
        }

        if (key == 48)
        {
            //topGo('qryService', 'serviceinfo/serviceInfoFunc.action');
            goback("<s:property value='curMenuId' />") ; 
        }
        // 上翻
        else if (key == 85)
        {
            myScroll.moveUp(30);
            return;
        }
        // 下翻
        else if (key == 68)
        {
            myScroll.moveDown(30);
            return;
        }   
}

/**
 * 积分兑换电子券
 * confirm:0:提交，1：弹出确认框
 * actScore：积分
 * actNo:批次
 * actLevel：档次
 * actReward：奖品编码
 * quantity： 奖品数量
 */
function scoreExchange(confirm,actScore,rewardName,actNo,actLevel,actReward,quantity)
{
	//弹出提示框
	if("1" == confirm)
	{
		<%-- 积分兑换接口入参 --%>
		setValue("activityId",actNo);
		setValue("actLevelId",actLevel);
		setValue("actreward",actReward);
		
		<%--add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
		setValue("quantity",quantity);
		<%--add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化--%>
		
		getObj("exchange_tip").innerHTML = '您确定要使用<span class="yellow">'+actScore+
			'</span>积分兑换<span class="yellow">'+rewardName+'</span>吗？';
		openExchangeConfirm("exchange_confirm");
		
		return;
	}
	
	if(submitFlag == "0")
	{	
		submitFlag = "1";
		openRecWaitLoading();
		document.actform.action = "${sessionScope.basePath }scoreExchange/exchangeCommit.action";
		document.actform.submit();
	}
}

//兑换确认提示框
openExchangeConfirm = function(id)
{
    wiWindow = new OpenWindow(id,708,392);
}

<%-- add begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化 --%>
function jfmxcx(region)
{
    //modify begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
    if (submitFlag == 0)
    {
        submitFlag = 1;
    
        document.actform.target="_self";
        document.actform.action="${sessionScope.basePath }scoreQry/forStartAndEnd.action?queryType=mx&requestRegion="+region;
        document.actform.submit();
    }
    //modify end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125         
}

function jfdhlscx(region)
{
    //modify begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
    if (submitFlag == 0)
    {
        submitFlag = 1;
    
        document.actform.target="_self";
        document.actform.action="${sessionScope.basePath }scoreQry/forStartAndEnd.action?queryType=dhls&requestRegion="+region;
        document.actform.submit();
    }
    //modify end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
}

// add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
function scorePaymentQry(region)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
    
        document.actform.target="_self";
        document.actform.action="${sessionScope.basePath }scoreQry/forStartAndEnd.action?queryType=paymentScore&requestRegion="+region;
        document.actform.submit();
    }
}
// add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求
<%-- add end by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化 --%>

</script>
</html>
