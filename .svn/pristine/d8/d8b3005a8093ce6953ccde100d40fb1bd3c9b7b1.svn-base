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
            <!-- 更多应用标志 -->
            <s:hidden name="moreFlag" id="moreFlag"></s:hidden>
            
            <!-- app应用id，唯一标识 -->
            <s:hidden name="appInfoPO.appId" id="appId"></s:hidden>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                <div class="b966" style="margin-top:20px;">
                
                	<%--modify begin qWX279398 2015-09-22 OR_SD_201509_152_山东_自助终端功能优化 --%>
                    <p class="hot_service"> </p>
       				<%--modify end qWX279398 2015-09-22 OR_SD_201509_152_山东_自助终端功能优化 --%>
       				
                    <div class="app_bg"> 
                        
                        <div class="app_box765W fl relative">
                                <!-- AAp下载内容 -->
                            <div id="inns"  class="app_con" id="appList">
                            	<s:iterator value="appInfoList" id="subAppInfoList" status="st">
	                                <div class="app_box_1">
	                                    <p class="app_service"><s:property value="#subAppInfoList[0].appTypeName"/></p>
	                                    <div class="line2"></div>
	                                    <s:iterator value="subAppInfoList" status="status" id="appInfo">
	                                        <s:if test="0 == moreFlag && 4 > #status.index">
	                                            <a onclick="showDetail('<s:property value="#appInfo.appId"/>');return false;"><img src="${sessionScope.basePath }hotAppDownload/showAppImg.action?picPath=<s:property value='#appInfo.appIcon'/>" /><span><s:property value="#appInfo.appName"/></span></a>
	                                        </s:if>
	                                        <s:elseif test="1 == moreFlag">
	                                            <a href="javascript:void(0)" onclick="showDetail('<s:property value="#appInfo.appId"/>');return false;"><img src="${sessionScope.basePath }hotAppDownload/showAppImg.action?picPath=<s:property value='#appInfo.appIcon'/>" /><span><s:property value="#appInfo.appName"/></span></a>
	                                        </s:elseif>
	                                    </s:iterator>
	                                </div>
                                </s:iterator>
                                <s:if test="0 == moreFlag">
                                    <div class="app_box_1">
                                        <p class="app_service">更多应用</p>
                                        <div class="line2"></div>
                                         <a href="javascript:void(0)" onclick="showMoreApp();return false;"><img src="${sessionScope.basePath }/images/app_gengduo2.png" /><span >更多应用</span></a>
                                    </div> 
                                </s:if>
                            </div> 
                        </div> 
                        <div class="fl ml20 relative">
                            <div class="box70W fr">
                                <input type="button" class="btnUp" onclick="myScroll.moveUp(40)" />
                                <div class="app_boxPage">
                                    <div class="box66W tc f16 lh30 fl ml20" id="gunDom" style="width:75px; _width:70px; left:4px; _left:2px; height:30px; position:absolute; cursor:move; ; top:52px; line-height:30px" >0%</div>
                                </div>
                                <input type="button" class="btnDown" onclick="myScroll.moveDown(40)"/>
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
</html>
<script language="javascript">

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
        openRecWaitLoading();
        document.getElementById("curMenuId").value = menuid;
        
        if ("1" == '<s:property value="moreFlag"/>' || 1 == '<s:property value="moreFlag"/>')
        {
        	document.getElementById("moreFlag").value = '0';
	        document.actForm.action = "${sessionScope.basePath }hotAppDownload/hotAppShow.action";
        }
        else
        {
        	document.actForm.action = "${sessionScope.basePath }login/goHomePage.action?timeoutFlag=0";
        }
        document.actForm.submit();
    }
}
      
// 提交      
function showDetail(appId) 
{
    if (submitFlag == 0) 
    {
        openRecWaitLoading();
        submitFlag = 1; //提交标记
        
        document.getElementById("appId").value = appId;
        document.actForm.action = "${sessionScope.basePath }hotAppDownload/showDetail.action";
        document.actForm.submit();
    }
}

//显示更多应用
function showMoreApp()
{
    openRecWaitLoading();
    document.getElementById("moreFlag").value = '1';
    document.actForm.action = "${sessionScope.basePath }hotAppDownload/hotAppShow.action";
    document.actForm.submit();
}
</script>
