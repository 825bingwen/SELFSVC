<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>移动自助终端</title>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <META HTTP-EQUIV="pragma" CONTENT="no-cache">
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <link href="${sessionScope.basePath }css/reset.css" type="text/css"
            rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css" type="text/css"
            rel="stylesheet" />
        <script type="text/javascript"
            src="${sessionScope.basePath }js/public.js"></script>
        <script type="text/javascript"
            src="${sessionScope.basePath }js/script.js"></script>
        <script type="text/javascript"
            src="${sessionScope.basePath }js/dialyzer.js"></script>
        <script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
    //已经选择了月份或者点击了返回，等待应答，不再执行任何操作
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = curmenu;
        
        document.actform.target="_self";
        
        // modify begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
        document.actform.action="${sessionScope.basePath }login/backForward.action";
        // document.actform.action="${sessionScope.basePath }scoreQry/qryScore.action";
        // modify begin by qWX279398 OR_SD_201507_533_山东_自助终端积分兑换功能优化
        
        document.actform.submit();
    }           
}

// 处理键盘事件
document.onkeydown = keyDown;
function keyDown(e)
{
    //8、32、66、77 更正
    //82、220 返回
    //13、89、221 确认
    //80 打印
    //85 上一页
    //68 下一页
    
    //接收键盘码
    var key = GetKeyCode(e);

    //8：backspace
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
        return false;
    }
    
    //R：返回
    if (key == 82 || key == 220)
    {
        goback("<s:property value='curMenuId' />") 
        return ;
    }
}
</script>
    </head>
    <body scroll="no">
        <form name="actform" method="post">
            <%@ include file="/titleinc.jsp"%>
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                <%
                    if (!"root".equalsIgnoreCase(parentMenuID))
                    {
                %>
                <a href="#" class="bt10 fr mr92" onmousedown="this .className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
                <%      
                    }
                %>
                <!--滚动条-->
                <div class="box842W fl ml45 relative">
                    <div class="bg"></div>
                    <div class="top"></div>
                    <div class="con relative">
                        <div class="box747W fl">

                            <div
                                style="height: 444px; padding: 0px 0px 5px 0px; width: 747px; overflow: hidden;">
                                <!-- 列表内容 -->
                                <p class="tit_info" align="left">
                                    <span class="bg"></span><%=menuName%></p>
                                <p class="ptop180 tc" id="inn"
                                    style="height: 367px; width: 747px; overflow: hidden;">
                                <table width="100%" class="tb_blue" align="center">
                                 
                                    <tr>
                                        <s:iterator value="servicetitle" status="title">
                                            <th width="20%" class="tc">
                                            <strong>    <s:property /></strong>
                                            </th>
                                        </s:iterator>
                                        
                                        <s:if test="retMessage!=null">
                                        <td width="100%">
                                        <s:property value="retMessage"/>
                                        </td>
                                        </s:if>
                                    </tr>
                                    
                                    <!-- 积分兑换明细查询 -->
                                    <s:iterator value="scoreDetail" status="status">
                                        <tr>
                                        <s:iterator value="scoreDetail[#status.index]">
                                        
                                        <s:if test="key=='col_4'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreDetail[#status.index]">
                                        <s:if test="key=='col_9'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreDetail[#status.index]">
                                        <s:if test="key=='col_8'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreDetail[#status.index]">
                                        <s:if test="key=='col_5'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreDetail[#status.index]">
                                        <s:if test="key=='col_3'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        </tr>
                                    </s:iterator>
                                    
                                    <!-- 山东积分兑换历史查询 -->
                                    <s:iterator value="scoreChangesd" status="status">
                                        <tr>
                                        <s:iterator value="scoreChangesd[#status.index]">
                                        
                                        <s:if test="key=='col_2'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreChangesd[#status.index]">
                                        <s:if test="key=='col_5'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreChangesd[#status.index]">
                                        <s:if test="key=='col_10'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        </tr>
                                    </s:iterator>
                                    <!-- 湖北兑换历史查询 -->
                                    <s:iterator value="scoreChangehub" status="status">
                                        <tr>
                                        <s:iterator value="scoreChangehub[#status.index]">
                                        
                                        <s:if test="key=='col_1'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreChangehub[#status.index]">
                                        <s:if test="key=='col_2'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreChangehub[#status.index]">
                                        <s:if test="key=='col_3'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        <s:iterator value="scoreChangehub[#status.index]">
                                        <s:if test="key=='col_4'">
                                            <td width="20%" class="tc">
                                                <s:property value="value" />
                                        </td>
                                        </s:if>
                                        </s:iterator>
                                        </tr>
                                    </s:iterator>
                                    
                                    <!-- add begin jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求 -->
                                    <!-- 积分发放查询 -->
                                    <s:iterator value="scorePayment" status="status">
                                       <tr>
                                            <s:iterator value="scorePayment[#status.index]">
                                                <s:if test="key=='col_1'">
                                                    <td width="20%" class="tc">
                                                      <s:property value='value' />
                                                    </td>
                                                </s:if>
                                            </s:iterator>
                                            <s:iterator value="scorePayment[#status.index]">
                                                <s:if test="key=='col_3'">
                                                    <td width="20%" class="tc">
                                                        <s:property value="value" />
                                                    </td>
                                                </s:if>
                                            </s:iterator>
                                            <s:iterator value="scorePayment[#status.index]">
                                                <s:if test="key=='col_4'">
                                                    <td width="20%" class="tc">
                                                      <s:if test="value==''">无</s:if>
                                                      <s:else><s:property value="value" /></s:else>
                                                    </td>
                                                </s:if>
                                            </s:iterator>
                                            <s:iterator value="scorePayment[#status.index]">
                                                <s:if test="key=='col_5'">
                                                    <td width="20%" class="tc">
                                                        <s:property value="value" />
                                                    </td>
                                                </s:if>
                                            </s:iterator>
                                        </tr>
                                    </s:iterator>
                                    <!-- add begin jWX216858 2014-10-20 V200R003C10LG1001 OR_SD_201407_498 山东移动促销回馈积分查询方案支撑需求 -->
                                </table>
                                </p>
                            </div>

                        </div>
                        <div class="box70W fr">
                            <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                            <div class="boxPage" style="width: 75px; height: 350px;">
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
									<div class="box66W tc f16 lh30" id="gunDom"
                                    style="width: 66px; height: 36px; position: absolute; cursor: move; left: 765px; top: 52px; line-height: 36px">
                                    0%
                                </div>
								<%
								}
								%>
                                
                            </div>
                            <input type="button" class="btnDown"
                                onclick="myScroll.moveDown(30)" />
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
</html>
