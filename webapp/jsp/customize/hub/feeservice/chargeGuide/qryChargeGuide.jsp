<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	Vector answerItems = (Vector) request.getAttribute("answer");
	int row = 0;
	int num = 0;
	if(answerItems.size()>0){
		if(answerItems.size()%2 ==0){
			row = answerItems.size()/2;
		}else{
			row = answerItems.size()/2+1;
		}
	}

%>
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
//选择答案
function btnClick(answerCode){

		document.getElementById("answerCode").value = answerCode;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}hubfeeservice/qryChargeGuide.action";
		document.actform.submit();
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">	
			<input type="hidden" id="streamNo" name="streamNo" value="20011957982339">
			<input type="hidden" id="questionCode" name="questionCode" value="<s:property value='questionCode' />">
			<input type="hidden" id="answerCode" name="answerCode" value="">
			<input type="hidden" id="questionFlag" name="questionFlag" value="1">
		  	<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
			  	<%@ include file="/customer.jsp"%>
			  	<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">返回<%=parentMenuName %></a>
	          	<div class="b966">
	          		<div class=" p40">
	          			<div class="blank30"></div>
	            		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
	            		<div class="blank30"></div>
	            		<div class="relative p40">	            
	            			<div class="blank15"></div>
	            			<div class="p20">
				                <table width="100%" class="tb_blue" >
				                  <tr>
				                    <th scope="col" align="left" colspan="2">请选择问题以下问题</th>
				                  </tr>
				                  <tr>
				                    <td align="left" colspan="2"><s:property value="question"/></td> 
				                  </tr>
				                  <%
					        		for(int j=0;j<row;j++){
										String[] answerItemO = null;
										String[] answerItemT = null;
										if(answerItems.size()%2!=0&&num == answerItems.size()/2){
											answerItemO = (String[])answerItems.get(num+j);
									%>
										<tr>
											<td>
											   <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43'" onclick="btnClick('<%=answerItemO[0] %>')"  style="display:inline">
												<%=answerItemO[0].charAt(answerItemO[0].length()-1) %>.&nbsp;&nbsp;<%=answerItemO[1] %></a>
											</td>
											<td>
											</td>
										</tr>
									<%
										}else{
											answerItemO = (String[])answerItems.get(num+j);
											answerItemT = (String[])answerItems.get(num+j+1);
									%>
										<tr>
											<td>
											   <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43'" onclick="btnClick('<%=answerItemO[0] %>')" style="display:inline">	   
												<%=answerItemO[0].charAt(answerItemO[0].length()-1) %>.&nbsp;&nbsp;<%=answerItemO[1] %></a>
											</td>
											<td>
											   <a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43'" onclick="btnClick('<%=answerItemT[0] %>')" style="display:inline">
												<%=answerItemT[0].charAt(answerItemT[0].length()-1) %>.&nbsp;&nbsp;<%=answerItemT[1] %></a>
											</td>
										 </tr>
									<%
										}
										num ++;
									}
								  %>
				                </table>	
	             			</div>	             
						</div>
	            	</div>
	          	</div>
		  	</div>
		  	<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
