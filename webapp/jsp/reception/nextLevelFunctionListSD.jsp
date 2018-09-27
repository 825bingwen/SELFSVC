<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="${sessionScope.basePath}js/pluginscript/musterPrinter.js"></script>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
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
    
    //82:R 220:返回
    if (key == 82 || key == 220)
    {
        goback("<s:property value='curMenuId' />") ;
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
                <div class="service_brand w966 m0auto">
                    <s:if test="pageStyle == 1">
                    <div class="service_index_list">
                    	<p class="hot_service"></p>
                        <ul class="clearfix" >
                            <s:if test="menus != null && menus.size() > 0">
                                <s:iterator value="menus" id="menu" status="st">
                                    <li style="height:145px;">
                                        <a class="relative" id="nav_" href="javascript:void(0);" onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
	                                        <img src='${sessionScope.basePath}<s:property value='#menu.imgpath2' />' alt='<s:property value='#menu.menuname' />' />
	                                    </a>
                                        <p>
	                                        <a href="javascript:void(0);" onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
	                                            <s:property value='#menu.menuname' />
	                                        </a>
                                    	</p>
                                    </li>
                                </s:iterator>
                            </s:if>                                 
                        </ul>
                    </div>
                    </s:if>
                    <s:else>
                        <div class="service_list">
                        <ul class="clearfix">
                            <s:if test="menus != null && menus.size() > 0">
                                <s:iterator value="menus" id="menu" status="st">
                                    <li>
                                        <a class="awrap" href="javascript:void(0)" onclick="topGo('<s:property value="#menu.menuid" />', '<s:property value="#menu.guiobj" />'); return false;">
                                        <h2><s:property value="#menu.menuname" /></h2>
                                        <h3><s:property value="#menu.tiptext" /></h3>
                                        </a>
                                    </li>
                                </s:iterator>
                            </s:if>                                 
                        </ul>
                    </div>
                    </a>
                    </s:else>
                </div>          
            </div>
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
</html>
