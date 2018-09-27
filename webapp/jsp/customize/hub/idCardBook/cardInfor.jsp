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

function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }idCardBook/idCardBookCommit.action";
		document.actform.submit();	
	}			
}

function doCancle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/backForward.action";
		document.actform.submit();	
	}			
}
</script>
</head>
<body scroll="no">
<form name="actform" method="post">
	<input type="hidden" id="idCardContent" name="idCardContent" value = '<s:property value="idCardContent" />' />

	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">		
		<%@ include file="/customer.jsp"%>
		<div class="b966">
			<div class="blank30"></div>
			<div class="relative p40">
				<p class="tit_info tl"><span class="bg"></span><%=menuName %></p>
				<div class="blank30"></div>
				<p class="p20">            				
					<table width="60%" class="tb_blue" align="center">	
						<tr>
							<th width="40%" class="tc">
								姓名
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardName" />  
								</span>
							</td>
						</tr>
						<tr>
							<th width="40%" class="tc">
								性别
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardSex" />
								</span>
							</td>
						</tr>
						<tr>
							<th width="40%" class="tc">
								身份证号
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardNo" />
								</span>
							</td>
						</tr>
						<tr>
							<th width="40%" class="tc">
								证件地址
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardAddr" />
								</span>
							</td>
						</tr>
						<tr>
							<th width="40%" class="tc">
								证件开始时间
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardStartTime" />
								</span>
							</td>
						</tr>
						<tr>
							<th width="40%" class="tc">
								证件结束时间
							</th>
							<td width="60%" class="tc">
								<span class="yellow fs20">
									<s:property value="idCardEndTime" />
								</span>
							</td>
						</tr>									
					</table>							
			</div>
				
				<div class="btn_box tc">
	    			<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">预约</a></span>
	    			<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doCancle();">取消</a></span>
    			</div>
		</div>
	</div>
	<%@ include file="/backinc.jsp"%>		
</form>
</body>
</html>
