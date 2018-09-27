<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
    
    String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);

 %>
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
var submitFlag = 0;

function goback(curmenu) 
{
    //已经选择了月份或者点击了返回，等待应答，不再执行任何操作
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        // add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
        if (document.getElementById("backWaitingFlag").value == "1")
        {
            openRecWaitLoading_NX("recWaitLoading");
        }
        // add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
        
        document.getElementById("curMenuId").value = curmenu;
        
        document.actform.target="_self";
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
    
    <%
    if (Constants.PROOPERORGID_NX.equalsIgnoreCase(provinceID))
    {
    %>
        //82:R 退出
        if (key == 82 || key == 220)
        {
            window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
            return ;
        }
    <%
    }
    else
    {
    %>
        //82:R 220:返回
        if (key == 82 || key == 220)
        {
            goback("<s:property value='curMenuId' />") ;
            return ;
        }
    <%  
    } 
    
    if ("1".equals(keyFlag))
    {
    %>
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
    <%
    }
    %>
}

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
</script>
    </head>
    <body scroll="no">
        <form name="actform" method="post">     
            <%@ include file="/titleinc.jsp"%>
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                <%
                    if (!"root".equalsIgnoreCase(parentMenuID) && "1".equals(keyFlag))
                    {
                %>
                    <a href="#" class="bt10_1 fr mr92" onmousedown="this .className='bt10_1on fr mr92'" onmouseup="this.className='bt10_1 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %>&nbsp;(按0键)</a>
                <%      
                    }
                    else if(!"root".equalsIgnoreCase(parentMenuID))
                    {
                %>
                    <a href="#" class="bt10 fr mr92" onmousedown="this .className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
                <%
                    }
                %>
                
                <!--滚动条-->
                <div class="box842W fl ml45IE6 relative">                   
                    <div class="bg"></div>
                    <div class="top"></div>
                    <div class="con relative" >
                        <div class="box747W fl">
                            
                            <div class="div747w444h" >
                                <!-- 列表内容 -->
                                <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
                                <div class="ptop180 tc box747w367h" id="inn">
                                <%if(provinceID.equalsIgnoreCase(Constants.PROOPERORGID_HUB)){ %>
                                
                                <table width="80%" class="tb_blue" align="center">
                                <tr>
                                <s:iterator value="servicetitle" status="title">
                                    
                                        <th width="25%" class="tc">
                                            <s:property />：
                                        </th>
                                   <s:if test="(#title.index==0)">
                                        <td width="25%" class="tc" colspan="3" >
                                            <span class="yellow" >
                                                <s:property value="score[#title.getIndex()]" />
                                            </span>
                                        </td>
                                    </s:if>
                                    <s:else>
                                    <td width="25%" class="tc"  >
                                            <span class="yellow" >
                                                <s:property value="score[#title.getIndex()]" />
                                            </span>
                                        </td>
                                    </s:else>
                                    
                                    <s:if test="(#title.index)%2==0">
                                        <s:if test="#title.last">
                                        </s:if>
                                        <s:else>
                                            </tr><tr>
                                        </s:else>
                                    </s:if>
                                </s:iterator>
                                </tr>
                            </table>                            
                                <%}else{ %>
                                
                                <%--add begin sWX219697  OR_SD_201404_777 积分居中展示--%>
                                <% String scorePlanSwitch = (String)PublicCache.getInstance().getCachedData(Constants.SCORE_PLAN_SWITCH);
                                if(provinceID.equalsIgnoreCase(Constants.PROOPERORGID_SD)&&"1".equals(scorePlanSwitch)){ %>
                                <p style="height:110px"></p>
                                <%} %>
                                <%--add end sWX219697  OR_SD_201404_777--%>
                                
                                <table width="80%" class="tb_blue" align="center" style="">
                                <%
                                    String scoreProFBSwitch = CommonUtil.getParamValue(Constants.SH_SCOREQRY_SWITCH);
                                    if (provinceID.equalsIgnoreCase(Constants.PROOPERORGID_SD) && "1".equals(scoreProFBSwitch))
                                    {
                                 %>
                                    <tr>
                                        <s:iterator value="servicetitle" status="title">
                                           <th width="50%"> <s:property/></th>
                                        </s:iterator>
                                    </tr>
                                    <s:if test="null == scoreSD || scoreSD.size() == 0">
                                        <tr>
                                           <td width="20%" class="tc">
                                               0
                                           </td>
                                           <td width="20%" class="tc">
                                               无
                                           </td>
                                        </tr>
                                    </s:if>
                                    <s:else>
	                                    <s:iterator value="scoreSD" status="status">
	                                        <tr>
		                                        <s:iterator value="scoreSD[#status.index]">
		                                        
			                                        <s:if test="key=='col_2'">
			                                            <td width="20%" class="tc">
			                                                <s:property value="value" />
			                                            </td>
		                                            </s:if>
	                                            </s:iterator>
		                                        <s:iterator value="scoreSD[#status.index]">
			                                        <s:if test="key=='col_3'">
			                                          <td width="20%" class="tc">
			                                              <s:if test="value==''">无</s:if>
	                                                      <s:else><s:property value="value" /></s:else>
	                                                  </td>
			                                        </s:if>
	                                            </s:iterator>
	                                        </tr>
	                                     </s:iterator>
	                                 </s:else>
                                     <tr><td colspan="2">可兑换积分：<s:property value="pointBalance"/></td></tr>
                                <%
                                    }
                                    else
                                    {
                                 %>
                                    <s:iterator value="servicetitle" status="title">
                                        <tr>
                                            <th width="60%" class="tc">
                                                <s:property />：
                                            </th>
                                            <td width="40%" class="tc">
                                                <span class="yellow">
                                                    <s:property value="score[#title.getIndex()]" />
                                                </span>
                                            </td>
                                        </tr>
                                    </s:iterator>   
                                <%
                                    }
                                 %>
                            </table>
                                <%} %>
                                    <p class="blank10"></p>
                                </div>  
                                        
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
                                
                            </div>
                            
                        </div>
                        <div class="box70W fr">
                            <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                            <div class="div75w350h">
                                <div class="blank10px"></div>
                                
                                <%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
                                	<div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
								<%
								}
								else
								{
								%>
                                	<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								}
								%>
                            </div>
                            <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                        </div>
                        
                        <div class="clear"></div>
                        
                    </div>
                    
                    <div class="btm"></div>
                </div>
                
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--滚动条结束-->
            </div>                
            
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
    <!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
    <script type="text/javascript">
        openSurveyDialog();
    </script>
    <!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
</html>
