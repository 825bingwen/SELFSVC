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
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
		 		<%@ include file="/customer.jsp"%>
		 		
				<a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
        		<!--滚动条-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">					
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<!-- 列表内容 -->
                        		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
									<table class="tb_blue" width="100%">
		                       			<tr>
		                           			<!-- 标题行 -->
		                           			<s:iterator value="servicetitle" status="title">
			                               		<th class="list_title" align="center" id="title<s:property value="#title.getIndex()"/>">
			                                   		<s:property/>
			                               		</th>
			                           		</s:iterator>
				                    	</tr>
			                    
				                    	<!-- 列表行 -->
			                        	<s:iterator value="result" status="result">
			                            	<tr>
			                                	<s:iterator value="result.get(#result.index)">
			                                    	<td align="center"><s:property/></td>
			                                	</s:iterator>
			                            	</tr>
			                        	</s:iterator>
			                      		<tr>
				                      		<td colspan="100"> 
				                      			<strong>&nbsp;&nbsp;&nbsp;&nbsp;合计条数：</strong><s:property value="result.size"/>条 
					                      	</td>
				                      	</tr>
		                       		</table>
								</div>								
								<!-- 列表内容 -->
							</div>							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
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
