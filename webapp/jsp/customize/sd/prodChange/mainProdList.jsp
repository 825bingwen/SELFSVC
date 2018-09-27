<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

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
    <body scroll="no" onload="window.focus();">
        <form name="actform" method="post">
            <s:hidden id="newProdId" name="newProdId"></s:hidden>
            <s:hidden id="newProdName" name="newProdName"></s:hidden>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>套餐资费变更流程</h2>
                        <div class="blank10"></div>
                        <a href="#">1. 输入手机号码</a>
                        <a href="#" class="on">2. 可转换主体产品</a>
                        <a href="#">3. 主体产品模板</a>
                        <a href="#">4. 变更确认</a>
                        <a href="#">5. 完成</a>
                    </div>
                    
                    <div class="box710W fl ml10 relative" style="margin-top: 10px;">
	                    <div class="bg"></div>
	                    <div class="top"></div>
	                    <div class="con relative" >
		                    <div style="height: 444px; float:left; padding: 0px 0px 5px 0px; width: 610px; overflow: hidden;">
		                        <p class="ptop180 tc" id="inn"  style="height: 450px; width: 610px; overflow: hidden;">
		                        
		                        <%-- 用户信息 --%>
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
		                        <%-- 套餐信息 --%>
		                        <table width="100%" class="tb_blue03">
                                  <tr>
                                    <th colspan="3"><span class="yellow">套餐列表</span></th>
                                  </tr>
                                  <tr>
                                    <th>套餐名称</th>
                                    <th>套餐介绍</th>
                                    <th>操作</th>
                                  </tr>
		                          <s:iterator value="prodChangeList" id="prodChangePO" status="st">
                                    <tr>
                                        <td><s:property value="#prodChangePO.newProdName"/></td>
                                        <td><s:property value="#prodChangePO.newProdDescr"/></td>
                                        <td>
                                            <input type="button" class="bt2_liebiao white" onclick="selectProdSub('<s:property value="#prodChangePO.newProdId"/>','<s:property value="#prodChangePO.newProdName"/>');" value="选择"/>    
                                        </td>
                                    </tr>
		                          </s:iterator>
		                          <tr></tr>
                                </table>
		                    </div>
		                    
		                    <%-- 滚动条 --%>
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
                </div>
            </div>
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
<script type="text/javascript">
var submitFlag = 0;
// 退出按钮，回主页面
function goback(menuid)
{
	// 防止重复提交
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath}login/backForward.action";
        document.actform.submit();
    }
}

// 选择要变更的主体产品
function selectProdSub(newProdId, newProdName)
{
	// 防止重复提交
	if(submitFlag == 0)
	{
		submitFlag = 1;
	    document.getElementById('newProdId').value = newProdId;
	    setValue("newProdName",newProdName);
	    openRecWaitLoading();
	    document.actform.target = "_self";
	    document.actform.action = '${sessionScope.basePath}prodChange/mainProdTemplateList.action';
	    document.actform.submit();
	}
}
</script> 
