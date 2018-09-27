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
		document.actform.action="${sessionScope.basePath }login/backForward.action";
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

function queryOther(menuid,url) 
	{
	
		document.getElementById("curMenuId").value = menuid;
			
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath}" + url;
		
		
		document.forms[0].submit();
	}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
		  	<%@ include file="/titleinc.jsp"%>
		  
		  	<div class="main" id="main">
		  		<%@ include file="/customer.jsp"%>
				
				<a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
				<a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';queryOther('<s:property value="curMenuId" />', '/billSend/backInfoDateInput.action'); return false;" style="display:inline">查询其他时间段</a>
        		<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc p747w411h" id="inn" >
			                 		<table width="100%" class="tb_blue">
			                 		<tr>
			                 		
			                 			<th class="list_title" align="center" width="10%">序号</th>
			                 			<th class="list_title" align="center" width="30%">活动返还起止时间</th>
			                 			<th class="list_title" align="center" width="14%">返还时间</th>
			                 			<th class="list_title" align="center" style="padding:0;" colspan="2" width="16%">
			                 			<table >
			                 			<tr>
			                 			<td colspan="2" style="border-width:0;">返还金额
			                 			</td>
			                 			</tr>
			                 			<tr>
			                 			<td style="border-left-width:0;border-right-width:1;border-bottom-width:0;border-top-width:1;border-style:solid;"> 话费
			                 			</td>
			                 		      <td style="border-left-width:0;border-right-width:0;border-bottom-width:0;border-top-width:1;border-top-style:solid;">
			                 		         专款
			                 			</td>
			                 			</tr>
			                 			</table>
			                 			
			                 			</th>
			                 			<th class="list_title" align="center" width="10%">剩余返还余额</th>
			                 			<th class="list_title" align="center" width="20%">备注</th>
			                 			
			                 			
			                 		</tr>
			                 		<s:if test="resList != null && resList.size > 0">
								        	<s:iterator value="resList" status="result">
								            	<tr>
								            	<td align="center"><s:property value="#result.index+1"/></td>
								                	<s:iterator value="resList.get(#result.index)" status="res" id="back">
								                	
								                	  <s:if test="#res.index==0||#res.index==3">
								                	  </s:if>
								                	  <s:else>
								                	   <s:if test="#res.index==4">
								                	   <s:if test="#back.indexOf('cash')!=-1">
								                	   <td align="center" width="8%"><s:property value="#back.substring(4,#back.length())"/></td>
								                	   <td align="center" width="8%">&nbsp;</td>
								                	   </s:if>
								                	   <s:else>
								                	   <td align="center" width="8%">&nbsp;</td>
								                	   <td align="center" width="8%"><s:property value="#back"/></td>
								                	   
								                	   </s:else>
								                	   </s:if>
								                	   <s:else>
								                	
								                    	<td align="center"><s:property value="#back"/></td>
								                    	</s:else>
								                    	</s:else>
								                	</s:iterator>
								            	</tr>
								        	</s:iterator>
								    	</s:if>
			                 		
			                 		

								    	
				                     	<tr>
				                        	<td colspan="100"> <strong>&nbsp;&nbsp;&nbsp;&nbsp;合计条数：</strong><s:property value="resList.size"/>条</td>
				                     	</tr>
				                  	</table>                  	
								</p>								
								<!-- 列表内容 -->
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h" >
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
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
