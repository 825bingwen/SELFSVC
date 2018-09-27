<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.math.BigDecimal"%>


<%
	// add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
	String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	// ����ȫ���һ����㷽ʽ
	String ward = (String) PublicCache.getInstance().getCachedData(Constants.SH_RTSCOREEXEALL_RULES);
	double subsReward = Double.valueOf(ward);
	
	// ��ȡ����
	int reward = Integer.parseInt((String)request.getAttribute("subsScore"));
	reward = reward/10;
	
	BigDecimal bd1 = BigDecimal.valueOf(reward);
	BigDecimal bd2 = BigDecimal.valueOf(subsReward);
	
	BigDecimal bd3 = bd1.multiply(bd2);
	// add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
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
			
			<%-- ���α��� --%>
			<s:hidden name="rewardPO.activityId" id="activityId"/>
			
			<%-- ���α��� --%>
			<s:hidden name="rewardPO.actLevelId" id="actLevelId"/>
			
			<%-- ��Ʒ������ --%>
			<s:hidden name="rewardPO.actreward" id="actreward"/>
			
			<%--��Ʒ���� --%>
			<s:hidden name="rewardPO.quantity" id="quantity"/>
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!--������-->
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">

							<div class="div747w444h">
								<p class=" tit_info">
									<span class="bg"></span>��ǰ�ɶһ����֣�<span class="yellow">
									${subsScore}</span>
									
									<%--add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
									&nbsp;&nbsp;&nbsp;
									<s:if test="null != rewardPO">
										<span class="bg"></span>�ɶһ�<%=bd3 %>Ԫ����ȯ
										<input type="button" class="bt2_liebiao"
															style="color: #FFFFFF;" value="ȫ���һ�"
															onmousedown="this.className='bt2on'"
															onmouseup="this.className='bt2_liebiao'"   
															onclick="scoreExchange('1','${rewardPO.actScore}','${rewardPO.rewardName}','${rewardPO.activityId}','${rewardPO.actLevelId}','${rewardPO.actreward}','<%=reward %>')"/>
									</s:if>
									<%--add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
									
								</p>
								
							<div class="ptop180 tc box747w367h" id="inn" style="height:420px;">
								<p class="blank10"></p>
								<p class="tit_arrow_hide pl40" style="height:150px;text-align:left;">
					            	<s:if test="null != exchangeTipList && exchangeTipList.size > 0">
					            		<s:iterator value="exchangeTipList">
					            			<%-- html��ǩת��--%>
					            			<s:property value="description" escape="false"/>
					            		</s:iterator>
					            	</s:if>
					            	<s:else>
					            		�����ֻ��Ļ��ֶһ��ɿ����ѵĵ����ֽ�ȯ����ͬ�Ļ��ֿɶһ��ɲ�ͬ���ĵ���ȯ����������ʱ�����ֽ�ʹ�á�����ȯ�ص����£�<br />
					            		
						            	1���һ���ݣ������򵥣�����ʱʹ��һ����̬���žͿ�����ʵ�ֹ��<br />
						
										2����ȫ�Ըߣ����׶�ʧ����������<br />
						
										3��ʹ������ʹ�ô������ơ���������100Ԫ�����ѵ���ȯ�����Էֶ�ν������ѣ�����һ�������ꣻ<br />
						
										4���״�ʹ��ʱ����ȯʱ������ѿ�ͨ�й��ƶ��Ͱ�ҵ�񣬿�ͨ��ʽ�༭����KT��10658888��
					            	</s:else>
								</p>
								
									<table width="100%" class="tb_blue" align="center">
										<tr>
											<th scope="col">
												�����
											</th>
											<th scope="col">
												����˵��
											</th>
											<th scope="col">
												��Ʒ����
											</th>
											<th scope="col">
												���ѽ��(Ԫ)
											</th>
											<th scope="col">
												����
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
												
												<%-- modify by sWX219697 2015-7-20 prePayFee��ΪprepayFee--%>
												<td class="tc">
													${list.prepayFee}
												</td>
												<td>
												
													<%--add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
													<input type="button" class="bt2_liebiao"
														style="color: #FFFFFF;" value="�һ�"
														onmousedown="this.className='bt2on'"
														onmouseup="this.className='bt2_liebiao'"   
														onclick="scoreExchange('1','${list.actScore}','${list.rewardName}','${list.activityId}','${list.actLevelId}','${list.actreward}','')"/>
													<%--add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
														
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
						��ʾ��
					</div>
					<div class="tips_body">
						<div class="blank30"></div>
						<p id="exchange_tip"></p>
						<div class="blank30"></div>
					</div>
					<div class="btn_box tc mt20">
						<span class=" mr10 inline_block "><a href="javascript:void(0);"
							class="btn_bg_146" onmousedown="this.className='key_down'"
							onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="scoreExchange('0');return false;">ȷ��</a>
						</span>
						<span class=" inline_block "><a class="btn_bg_146" href="#"
							onmousedown="this.className='key_down'"
							onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
						</span>
					</div>
				</div>
				
				<%-- add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż� --%>	
				<!-- �б����� -->
                <%
                    String scoreSwitch = PublicCache.getInstance().getCachedData("SH_SCORELIST_SWITCH") == null ? "0" : (String)PublicCache.getInstance().getCachedData("SH_SCORELIST_SWITCH");
                    if ("1".equals(scoreSwitch))
                    {
                %>
                    <div class="btn_box tc">
                        <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';;" onclick="jfmxcx('<%=provinceID%>')">������ϸ��ѯ</a></span>
                        <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="jfdhlscx('<%=provinceID%>')">���ֶһ���ʷ��ѯ</a></span>
                        <%-- add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������ --%>
                        <%
                            String scoreProFBSwitch = CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH);
                            if (provinceID.equalsIgnoreCase(Constants.PROOPERORGID_SD) && "1".equals(scoreProFBSwitch))
                            {
                         %>
                         <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="scorePaymentQry('<%=provinceID%>')">���ַ��Ų�ѯ</a></span>
                         <%
                            }
                          %>
                         <%-- add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������ --%>
                    </div>
                <%
                    }
                %>
				<%-- add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż� --%>
				
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
				<!--����������-->
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
			<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
    //�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
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
    //8��32��66��77 ����
    //82��220 ����
    //13��89��221 ȷ��
    //80 ��ӡ
    //85 ��һҳ
    //68 ��һҳ
    
    //���ռ�����
    var key = GetKeyCode(e);
     
    //8:backspace 32:�ո� B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
        return false;
    }

        //82:R 220:����
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
        // �Ϸ�
        else if (key == 85)
        {
            myScroll.moveUp(30);
            return;
        }
        // �·�
        else if (key == 68)
        {
            myScroll.moveDown(30);
            return;
        }   
}

/**
 * ���ֶһ�����ȯ
 * confirm:0:�ύ��1������ȷ�Ͽ�
 * actScore������
 * actNo:����
 * actLevel������
 * actReward����Ʒ����
 * quantity�� ��Ʒ����
 */
function scoreExchange(confirm,actScore,rewardName,actNo,actLevel,actReward,quantity)
{
	//������ʾ��
	if("1" == confirm)
	{
		<%-- ���ֶһ��ӿ���� --%>
		setValue("activityId",actNo);
		setValue("actLevelId",actLevel);
		setValue("actreward",actReward);
		
		<%--add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
		setValue("quantity",quantity);
		<%--add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż�--%>
		
		getObj("exchange_tip").innerHTML = '��ȷ��Ҫʹ��<span class="yellow">'+actScore+
			'</span>���ֶһ�<span class="yellow">'+rewardName+'</span>��';
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

//�һ�ȷ����ʾ��
openExchangeConfirm = function(id)
{
    wiWindow = new OpenWindow(id,708,392);
}

<%-- add begin by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż� --%>
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

// add begin jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
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
// add end jWX216858 2014-10-08 V200R003C10LG1001 OR_SD_201407_498 ɽ���ƶ������������ֲ�ѯ����֧������
<%-- add end by qWX279398 OR_SD_201507_533_ɽ��_�����ն˻��ֶһ������Ż� --%>

</script>
</html>
