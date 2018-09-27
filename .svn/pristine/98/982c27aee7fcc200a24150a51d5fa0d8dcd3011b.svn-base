<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>移动自助终端</title>
        <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
        <META HTTP-EQUIV="pragma" CONTENT="no-cache">
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
        <META HTTP-EQUIV="Expires" CONTENT="0">
        <link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath }css/style.css?ver=${jsVersion }" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion }"></script>
        <script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion }"></script>        
    </head>

    <body scroll="no" onload="">
        <form name="actForm" method="post" target="_self">
            <!-- app应用id，唯一标识 -->
            <s:hidden name="appInfoPO.appId" id="appId"></s:hidden>
            
            <!-- 应用名称 -->
            <s:hidden name="appInfoPO.appName"></s:hidden>
            
            <!-- app应用下载地址 -->
            <s:hidden name="appInfoPO.shortAddress"></s:hidden>
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
	                <div class="b966" style="margin-top:25px; relative">
				    <div class="app_bg relative"> 
				        <div  class="app_service4">
				            <div class="app_bt2"><img class="fl" src="${sessionScope.basePath }hotAppDownload/showAppImg.action?picPath=<s:property value='appInfoPO.appIcon'/>"/></div>
				            <div class="app_service3">
				                <p class="app_service2"><s:property value="appInfoPO.appName"/></p>
				                <a href="javascript:void(0)" id="downloadBtn" onclick="downloadClient();return false;" class="app_bt3"><span class="app_sp3" style="padding-right:20px;_padding-right:5px;">下载客户端</span></a>
				            </div>
			                <div class="app_bt4">
			                    <img src="${sessionScope.basePath }hotAppDownload/showAppImg.action?picPath=<s:property value='appInfoPO.appTWOCode'/>"/></div>
			                <div class="app_bt5"><p>手机<br/>扫描<br/>下载</p></div>
			            </div>
				        <div class="app_box765W fl">
				                <!-- AAp简介内容 -->
				            <div id="inns" class="app_con_1" >
				                <div class="app_box_2">
                                    <s:property value="appInfoPO.appDesc" escape="false"/>
				                </div> 
				             </div> 
				                <!-- AAp简介内容 -->
				        </div>
				        
				        <div class=" fl ml20 relative">
				            <div class="box70W fr">
				                <input type="button" class="btnUp" onclick="myScroll.moveUp(60)" />
				                <div class="app_boxPage" style="height:200px;">
				                    <div class="box66W tc f16 lh30 fl ml20" id="gunDom" style=" width:69px; height:30px; position:absolute; cursor:move; left:4px; _left:3px; top:52px; line-height:30px" >0%</div>
				                </div>
				                <input type="button" class="btnDown" onclick="myScroll.moveDown(60)"/>
				            </div>
				        </div>
				        <div class="clear"></div>
		                <script type="text/javascript">
		                                
		                      function btnClick(btn){
			                      var inns=document.getElementById('inns').getElementsByTagName('a');
			                      
			                      for(i=0;i<inns.length;i++){
			                          inns[i].className='b_bt21';
			                          }
			                      btn.className='b_bt2on11';
		                      };    
		                      myScroll = new virtualScroll("inns","gunDom");
		                </script>
				    </div>
				 </div>
            </div>
            
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
    <script type="text/javascript">

//防止重复提交
var submitFlag = 0;

document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
    var key = GetKeyCode(e);
    
    //返回
    if (key == 82 || key == 220) 
    {
        goback("<s:property value='curMenuId' />");
        return;
    }
    //确认
    else if (key == 13 || key == 89 || key == 221)
    {
        doSub();
    }           
}

// 返回上一页
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
    
        document.actForm.action = "${sessionScope.basePath }hotAppDownload/hotAppShow.action";
        document.actForm.submit();
    }
}

// 下载客户端
function downloadClient()
{
    if (null != "<s:property value='customerSimp'/>" && "" != "<s:property value='customerSimp'/>")
    {
        synSendAddress();
    }
    else
    {
        if (submitFlag == 0)
	    {
	        openRecWaitLoading();
	        submitFlag = 1;
	        document.actForm.action = "${sessionScope.basePath }hotAppDownload/inputNumber.action";
	        document.actForm.submit();
	    }
    }
}

// 异步发送app下载地址
function synSendAddress()
{
	var appName = '<s:property value="appInfoPO.appName"/>';
	var url="${sessionScop.basePath}hotAppDownload/sendAdress.action";
    var param='servNumber=<s:property value="customerSimp.servNumber"/>' + '&appInfoPO.shortAddress=<s:property value="appInfoPO.shortAddress"/>' + '&appInfoPO.appName=' + encodeURI(encodeURI(appName));
    var data = "";
    var contentLoader=new net.ContentLoaderSynchro(url,function(){
        data=this.req.responseText;
        if (1 == data || "1" == data)
        {
	        document.getElementById("downloadBtn").disabled = true;
	        alertSuccessMsg('<s:property value="appInfoPO.appName"/>' + "的下载链接将通过短信发送到您的手机，请注意查收！");
        }
        else
        {
            alertRedErrorMsg('尊敬的用户，下载链接发送失败。您可以再次点击"下载客户端"按钮，或者使用手机扫描屏幕右侧二维码下载此app应用！');
        }
    },null,"POST",param,"application/x-www-form-urlencoded");
}
</script>
</html>