<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>现金稽核成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dummyKey.js"></script>
<script type="text/javascript">

var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) 
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
		goback();
   		return ;
	}
}	

function goback()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;			
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }managerOperation/selectOperationType.action";
		document.actform.submit();
	}
}	
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">			
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<h1><span></span>业务办理</h1>
					<div class="service_brand w966 m0auto">
						<div class="service_index_list">
						<p class="hot_service"></p>
								<div class="p40">
					      			<div class="blank10"></div>
									<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">自助终端提醒您：</span></p>
									<div class="blank25"></div>
									<div class="blank25"></div>
									<div class="btn_box tc">
									<p class="fs22w  pl20"><span class="yellow fs30">操作成功!</span></p>
          							<div class=" blank60"></div>
          							<p class="fs22w  pl20"><span class="yellow fs30"><s:property value="#request.successMessage"/>！</span></p>
								</div>
							</div>
				  	 	</div>
			 		</div>
			</div>		    
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
</html>
