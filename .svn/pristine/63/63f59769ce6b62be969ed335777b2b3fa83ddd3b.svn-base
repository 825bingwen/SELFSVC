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
<script>
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
    document.forms[0].target = "_self";
    document.forms[0].action = "${sessionScope.basePath }recommend4G/getPhoneList.action";
    document.forms[0].submit();
}

</script>

<style type="text/css">
.btn_back_104 {
    width:104px;
    height:56px;
    left:862px;
    _left:882px;
    background:url(../images/can/back_btn.png) no-repeat;
    _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/can/back_btn.png");
    _background:none;
}
.fav_title {
    margin-top:30px;
    margin-left:32px;
}
.tab_966 .con {
    padding-top:35px;_padding-top:27px;
}
.tab_966 .bg {
    background: url("../images/can/fav_bg-1.png") repeat scroll 0 0 transparent!important; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/can/fav_bg.png");   _background:none;    height: 526px;    width: 966px;
}
.box747W p{ font-size:18px; line-height:24px; padding:0 20px; margin-bottom:40px;}
.box747W .con{height:444px; padding:0px; width:747px; overflow:hidden; background:url(../images/bg_747_b.png) repeat-y; _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="");
    _background:none;
}
.tab_966 .bg2{width:966px; height:562px; background:url(../images/fav_bg-1.png) repeat scroll 0 0 transparent!important;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/fav_bg-1.png");_background:none}
</style>
</head>
    <body scroll="no">
        <form name="actform" method="post">
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">            
                
                <!--滚动条-->
				<p class="fav_title fs18" style="margin-top:10px;">4G手机推荐详情页</p>
				<div class=" fl ml20 relative tab_966 fav_detail" style="margin-top:0px; height:580px;">
				  <div class="bg2"></div>
				  <div class="con relative" style="margin-top:3px; padding-left:0px;">
				    <div class="box747W fl">
				      <div class="con" id="inn1" style="height:500px; padding:0px; width:740px; margin:0px auto; overflow:hidden;">
				        <s:iterator value="descAdobes" id="descAdobe">
				            <img src="<%=imgPath %>/<s:property/>" class="details"/>
				        </s:iterator>
				      </div>
				    </div>
				    <div class="box70W fr">
				      <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
				      <div class="boxPage" style="width:75px; height:395px; ">
				        <div class="blank10px"></div>
				        <div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:30px; position:absolute; cursor:move; left:827px; top:85px; line-height:30px" >0%</div>
				      </div>
				      <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
				    </div>
				    <div class="clear"></div>
				  </div>
				  <div class="btm"></div>
				</div>
				<script type="text/javascript">
				                 
                    myScroll = new virtualScroll("inn1","gunDom");
                    myScroll.parentTop = myScroll.parentTop + 33;
                 
                </script>
				<!--滚动条结束-->
				<div class=" clear"></div>
            </div>
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
