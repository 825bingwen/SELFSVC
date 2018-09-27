<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>移动自助终端</title>
		<meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript"  src="${sessionScope.basePath}js/public.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
    </head>
    <body scroll="no" onload="window.focus();loadContent();">
        <form id="actform" name="actform" method="post">
        	<%-- 新的主体产品id--%>
            <input name="newProdId" id="newProdId" type="hidden" value="<s:property value='newProdId'/>"/>
            <%-- 新的主体产品名称--%>
            <input name="newProdName" id="newProdName" type="hidden" value="<s:property value='newProdName'/>"/>
            <%-- 新的主体产品模板--%>
            <input name="templateId" id="templateId" type="hidden" value="<s:property value='templateId'/>"/>
            <%-- 新的主体产品模板--%>
            <input name="templateName" id="templateName" type="hidden" value="<s:property value='templateName'/>"/>
            
            <%@ include file="/titleinc.jsp"%>

            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>

                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>套餐资费变更流程</h2>
                        <div class="blank10"></div>
                        <a href="#">1. 输入手机号码</a>
                        <a href="#">2. 可转换主体产品</a>
                        <a href="#">3. 主体产品模板</a>
                        <a href="#" class="on">4. 变更确认</a>
                        <a href="#">5. 完成</a>
                    </div>
                    <!--滚动条-->
                    <div class="box710W fl ml10 relative" style="margin-top: 10px;">
                        <div class="bg"></div>
                        <div class="top"></div>
                        <div class="con relative" >
                            <div style="height: 444px; float:left; padding: 0px 0px 5px 0px; width: 610px; overflow: hidden;">
                                <p class="ptop180 tc" id="inn"  style="height: 450px; width: 610px; overflow: hidden;">
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="4"><span class="yellow">用户信息</span></th>
                                    </tr>
                                    <tr>
                                        <th>手机号</th>
                                        <th>姓名</th>
                                        <th>品牌</th>
                                    </tr>
                                    <tr>
                                        <td><s:property value="customer.servNumber" /></td>
                                        <td><s:property value="customer.customerName" /></td>
                                        <td><s:property value="customer.brandName" /></td>
                                    </tr>
                                </table>
                                <br/>
                                <%-- 开通的业务 --%>
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="2"><span class="yellow">开通的业务</span></th>
                                    </tr>
                                    <tr>
	                                    <th>套餐名称</th>
	                                    <th>优惠名称</th>
                                    </tr>
                                    <s:iterator value="openProdList">
	                                    <tr>
	                                        <td><s:property value="prodname"/></td>
	                                        <td><s:property value="privname"/></td>
	                                    </tr>
                                    </s:iterator>
                                </table>
                                <br/>
                                <%-- 取消的业务 --%>
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="2"><span class="yellow">取消的业务</span></th>
                                    </tr>
                                    <tr>
                                        <th>套餐名称</th>
                                        <th>优惠名称</th>
                                    </tr>
                                    <s:iterator value="cancelProdList">
                                        <tr>
	                                        <td><s:property value="prodname"/></td>
                                            <td><s:property value="privname"/></td>
                                        </tr>
                                    </s:iterator>
                                </table>
                                <br/>
                                <%-- 保留的业务 --%>
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="2"><span class="yellow">保留的业务</span></th>
                                    </tr>
                                    <tr>
                                        <th>套餐名称</th>
                                        <th>优惠名称</th>
                                    </tr>
                                    <s:iterator value="reserveProdList">
                                        <tr>
                                            <td><s:property value="prodname"/></td>
                                            <td><s:property value="privname"/></td>
                                        </tr>
                                    </s:iterator>
                                </table>
	                             <input type="button" class="bt2_liebiao white" value="上一步" onclick="history.back()" >   
	                                &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
	                             <input type="button" class="bt2_liebiao white"  value="确认变更"  onclick="mainProdChangeSubmit();" >  
                             </div>
                            <div class="box70W fr">
                                <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                                <div class="boxPage" style="width:75px; height:400px; ">
                                    <div class="blank10px"></div>
                                    <div class="box66W tc f16 lh30" id="gunDom" style="width:66px; height:36px; position:absolute; cursor:move;  left: 633px; top:52px; line-height:36px" >0%</div>
                                </div>
                                <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="btm"></div>
                    </div>
                    <script type="text/javascript">
                      myScroll = new virtualScroll("inn","gunDom");
                    </script>
                    <!--滚动条结束-->
                </div>
             </div>
             <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
<script type="text/javascript">
// add begin jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求
// 首先加载
function loadContent()
{
	var retMOPrivTips = '<s:property value="retMOPrivTips"/>';
	if (null != retMOPrivTips && "" != retMOPrivTips)
	{
	   alertSuccessMsg(retMOPrivTips);
	}
}
// add end jWX216858 2015-6-16 OR_SD_201505_294 关于对MO包月客户变更业务时增加提醒的需求

var submitFlag = 0;

// 退出按钮，会主页面
function goback(menuid)
{
	if (submitFlag == 0)
	{
	    submitFlag = 1;
	    
	    document.getElementById("curMenuId").value = menuid;
	    document.forms[0].target = "_self";
	    document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
	    document.forms[0].submit();
	}
}
    
// 确认变更按钮          
function mainProdChangeSubmit()
{   
	openRecWaitLoading();
	document.forms[0].target = "_self";
    document.actform.action = '${sessionScope.basePath}prodChange/mainProdChangeSubmit.action';
    document.getElementById("actform").submit();
}
</script> 

