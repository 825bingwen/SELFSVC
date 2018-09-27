<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.login.model.NserCustomerSimp"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdConfigPO"%>
<%@page import="com.gmcc.boss.selfsvc.quickpublish.model.ProdTypePO"%>
<%
List prodList = (List)request.getAttribute("prodList");
NserCustomerSimp custInformation = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
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

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;
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

function pwdKeyboardUp(e)
{
    var key = GetKeyCode(e);
    if (key == 82 || key == 220)
    {
        goback("<s:property value='curMenuId' />");
        return;
    }
}

// 下一页
function nextPage(linkURL)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.actform.target="_self";
        document.actform.action=linkURL;
        document.actform.submit();
    }
}

// 产品详情查询
function prodDetail(prodID,region,brand)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("prodID").value = prodID;
        document.getElementById("region").value = region;
        document.getElementById("brand").value = brand;
        
        document.actform.target="_self";
        document.actform.action = "${sessionScope.basePath}quickpublish/prodDetail.action";
        document.actform.submit();  
    }
}
// 点击业务类型按钮
function btnClick(btn,btClass)
{
    var btns=document.getElementById('btns').getElementsByTagName('a');
    for(i=0;i<btns.length;i++) 
    {
        btns[i].className=btClass;
    }
    if(btn.id == 'recButton')
    {
        document.getElementById("buttonType").value = "0";  
    }
    else if(btn.id == 'openButton')
    {
        document.getElementById("buttonType").value = "1";      
    }
    else if(btn.id == 'noOpenButton')
    {
        document.getElementById("buttonType").value = "2";      
    }
    btn.className=btClass+'on';
}

// 初始化业务类型按钮状态,0:推荐业务按钮选中 1：已开通业务按钮选中 2：未开通业务按钮选中                     
function initButton()
{
    if('recNetNew' != '<s:property value="curMenuId"/>')
    {
	    var btns=document.getElementById('btns').getElementsByTagName('a');
	    
	    for(i=0;i<btns.length;i++)
	    {
	        btns[i].className='bt222';
	    }
	    <s:if test="buttonType == 0">
	        document.getElementById('recButton').className='bt222on';
	    </s:if>
	    <s:if test="buttonType == 1">
	        document.getElementById('openButton').className='bt222on';
	    </s:if>
	    <s:if test="buttonType == 2">
	        document.getElementById('noOpenButton').className='bt222on';
	    </s:if> 
    }
}   

</script>
</head>
    <body scroll="no" onload="initButton();">
        <form name="actform" method="post">         
            <input type="hidden" id="prodID" name="prodID" value=""/>
            <input type="hidden" id="region" name="region" value=""/>
            <input type="hidden" id="brand" name="brand" value=""/>
            <input type="hidden" id="typeID" name="typeID" value="<s:property value='typeID'/>"/>
            <input type="hidden" id="buttonType" name="buttonType" value="<s:property value='buttonType'/>"/>
            
            <%@ include file="/titleinc.jsp"%>
            <div class="main" id="main" style="height:520px;">
                <%@ include file="/customer.jsp"%>
                <div id="btns" class="fl" style="margin-left:20px;">
                <%-- modify begin jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求 --%>
                <s:if test="'recNetNew' != curMenuId">
                <a href="javascript:void(0);" id="recButton" name="recButton" class="bt2 fs16" onclick="btnClick(this,'bt222');getProdList('');return false;">推荐业务</a>
                <%
                    if(null != custInformation)
                    {
                %>
                    <a href="javascript:void(0);" id="openButton" name="openButton" class="bt2 fs16" onclick="btnClick(this,'bt222');getProdList('');return false;">已开通业务</a>
                    <a href="javascript:void(0);" id="noOpenButton" name="noOpenButton" class="bt2 fs16" onclick="btnClick(this,'bt222');getProdList('');return false;">未开通业务</a>
                    
                <%
                    }
                %>
                </s:if>
                <%-- modify end jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求 --%>
                </div>
                <div class="service_brand w966 m0auto">
                    <div class="service_list h505">
                        <s:if test="prodList != null && prodList.size() > 0">
                            <ul class="clearfix">
                                <s:iterator value="prodList" id="prod">
                                    <!-- 产品大类展示 -->
                                    <s:if test="#prod.isTypeID == 1">
                                        <li>
                                            <a class="awrap" href="javascript:void(0);"
                                                onclick="getProdList('<s:property value='#prod.prodTypePO.typeID' />'); return false;">
                                                <h2>
                                                    <s:if test="%{#prod.prodTypePO.typeName.length()>10}">
                                                        <s:property value='#prod.prodTypePO.typeName.substring(0,10)' />...
                                                    </s:if>
                                                    <s:else>
                                                        <s:property value='#prod.prodTypePO.typeName' />
                                                    </s:else>
                                                </h2>
                                                <h3>
                                                    ·
                                                    <s:property value='#prod.prodTypePO.typeName' />
                                                </h3> 
                                            </a>
                                        </li>
                                    </s:if>
                                    <!-- 产品展示 -->
                                    <s:else>
                                        <li>
                                            <a class="awrap" href="javascript:void(0);"
                                                onclick="prodDetail('<s:property value='#prod.ncode' />', '<s:property value='#prod.areaCode' />', '<s:property value='#prod.brand' />'); return false;">
                                                <h2>
                                                    <s:if test="%{#prod.prodName.length()>10}">
                                                        <s:property value='#prod.prodName.substring(0,10)' />...
                                                    </s:if>
                                                    <s:else>
                                                        <s:property value='#prod.prodName' />
                                                    </s:else>
                                                </h2>
                                                <h3>
                                                    <s:property value='#prod.prodName' />
                                                </h3> 
                                            </a>
                                        </li>
                                    </s:else>
                                </s:iterator>
                            </ul>
                        </s:if>
                        <s:else>
                            <div class="showbutton02" align="center">
                                <span class="yellow"><font size="5px;">
                                    未查询到符合条件的产品  </font>
                                </span>
                            </div>
                        </s:else>
                    </div>
                </div>
            </div>
            <pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
    <script type="text/javascript">
    // 获取产品列表
    function getProdList(typeID)
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            document.getElementById("typeID").value = typeID;
            document.actform.target="_self";
            document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
            document.actform.submit();
        }
    }
    
    
    function goback(curmenu) 
    {
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            var typeID = document.getElementById("typeID").value;
            
            <%-- modify begin jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求 --%>
            if ("recNetNew" == document.getElementById("curMenuId").value)
            {
                goHomePage();
                return;
            }
            <%-- modify end jWX216858 2015-2-3 OR_HUB_201501_167 关于自助终端菜单层级优化需求 --%>
            if(""!= typeID)
            {
                document.getElementById("typeID").value ="";
                document.actform.target="_self";
                document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
                document.actform.submit();
            }
            else
            {
                document.getElementById("curMenuId").value = curmenu;
                document.actform.target="_self";
                document.actform.action="${sessionScope.basePath }login/backForward.action";
                document.actform.submit();
            }
        }           
    }
    </script>
</html>
