<%--
 @User: 马雪洁/m00227318
 @De: 日期(2012/08/29)
 @comment: 个性化内容宣传页面，在月详单查询页面点击超链接后，转向此页面。页面提供个性化内容宣传，页面内容可配置
 @remark: create m00227318 2012/08/29 V200R003C12L08n01 OR_NX_201207_1179
--%>
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
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<%-- add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125 --%>
<script type="text/javascript">
		var submitFlag = 0;
		
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
			
			// 返回首页重新计时
			resetGoHome();
		     
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
		
		function goback(curmenu) 
		{
			//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading"); 
				//add end m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
				
				history.go(-1);
			}		
		}
	</script>
<%-- add begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125 --%>
</head>
<body scroll="no">
    <form name="actform" method="post">	
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
						<%@ include file="/customer.jsp"%>				
						<!--滚动条-->
						<%-- modify begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125 --%>
	        			<a href="#" class="bt8 fr mr92" onmousedown="this.className='bt8on fr mr92'" onmouseup="this.className='bt8 fr mr92';openRecWaitLoading_NX('recWaitLoading');history.go(-1); return false;" style="display:inline">返回</a>
	        			<%-- modify begin m00227318 2012/10/18 V200R003C12L10 OR_huawei_201210_125 --%>
	        		
	        			<div class="box842W fl ml45 relative">
	        			    <div class="bg"></div>
							      <div class="top"></div>
									  <div class="con relative" >
								        <div class="box747W fl">
									          <div class="div747w444h">
										            <div class=" p747w411h mb10 relative" id="inn" style="font-size:18px; line-height:1.5em; width:680px;">
											            <p style="padding-left: 50px; padding-top: 50px; ">
											            	<s:property value="textUrl" escape="false" />
											            </p>											           			
										            </div>
									          </div>
								        </div>
								        
								        <div class="box70W fr">
									          <input type="button" class="btnUp" onclick="myScroll.moveUp(300);resetGoHome();" />
									          <div class="div75w350h" onclick="resetGoHome();">
										            <div class="blank10px"></div>
										            <div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
									          </div>
									          <input type="button" class="btnDown" onclick="myScroll.moveDown(300);resetGoHome();"/>
								        </div>
								        
								       <div class="clear"></div>
							     </div>
							     <div class="btm"></div>
	        		</div>
	        		<script type="text/javascript">
	        				myScroll = new virtualScroll("inn", "gunDom");
	        		</script>		
	    </div>
	    <%@ include file="/backinc.jsp"%>	
    </form>
</body>
</html>
