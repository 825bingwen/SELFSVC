<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";

String imgPath = basePath + Constants.PHONEIMG_FILE_RELATIVELY_PATH;
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
<script type="text/javascript" src="${sessionScope.basePath }js/picScroll.js"></script>
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
        goback("<s:property value='#request.curMenuId' />");
        return;
    }
}

function goback()
{
    goHomePage();
}

// 展示手机详情
function phoneDetail(descAdobe)
{
    document.getElementById("descAdobe").value = descAdobe;
    
    document.forms[0].target = "_self";
    document.forms[0].action = "${sessionScope.basePath }recommend4G/phoneDetail.action";
    document.forms[0].submit();
}

</script>
</head>
    <body scroll="no">
        <form name="actform" method="post">
            <s:hidden name="descAdobe"></s:hidden>
            <s:hidden name="recommendProdPath"></s:hidden>
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">            
                <%@ include file="/customer.jsp"%>
                <h1 style="visibility: visible"><span></span>4G手机推荐，点击手机可查看详情</h1>
		        <div class="blank30"></div>
		        <div class="rollBox">
		           <div class="LeftBotton" id="LeftBotton"></div>
		           <div class="Cont" id="gdq">
		              <div class="ScrCont">
			              <s:iterator value="phoneInfoList" id="phone">
	                         <dl class="pic">
	                         <a href="#" onclick="phoneDetail('<s:property value="#phone.descAdobe"/>');return false;">
	                            <img width="112px;" height="216px;" src="<%=imgPath %>/<s:property value='#phone.phoneAdobe'/>"/>
	                            <div class="info">
	                                <p style="text-align:center"><s:property value="#phone.phoneName"/></p>
	                                <p style="text-align:center">
	                                   <s:if test="%{#phone.message.length()>28}">
                                           <s:property value='#phone.message.substring(0,28)' />...
                                       </s:if>
                                       <s:else>
                                           <s:property value='#phone.message' />
                                       </s:else>
	                                </p>
	                                <font>￥<s:property value="#phone.phonePrice"/></font>
	                            </div>
	                         </a>
	                         </dl>
	                     </s:iterator>
		              </div>
		           </div>
		           <div class="RightBotton" id="RightBotton"></div>
		        </div>
		        <SCRIPT>
		          Effect.HtmlMove("gdq","div/dl","scrollLeft",5,"RightBotton","LeftBotton",7000,1);
		        </SCRIPT>
            </div>
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
