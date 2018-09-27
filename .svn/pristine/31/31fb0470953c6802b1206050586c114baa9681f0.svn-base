<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
<META HTTP-EQUIV="Expires" CONTENT="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">		
		  	<%@ include file="/titleinc.jsp"%>
		  	<div class="main" id="main">
		  		<input type="hidden" name="ncode" id="ncode" value="<s:property value='privServPackPO.ncode'/>"></input>
		  		<input type="hidden" name="effDate" id="effDate" value="<s:property value='privServPackPO.effDate'/>"></input>
		  		<input type="hidden" name="endDate" id="endDate" value="<s:property value='privServPackPO.endDate'/>"></input>
		  		<%@ include file="/customer.jsp"%>
				<div class="blank20"></div>
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<div class="blank30"></div>
								<!-- 列表内容 -->
								<div class="ptop180 tc p747w411h" id="inn" >
									<table style="width: 80%;" class="tb_blue" align="center">
										<tr>
		                                	<th style="width: 30%;">业务名称</th>
			                                <td style="width: 70%;">
			                                 	<s:property value="privServPackPO.privName"/>	
			                                </td>
		                                </tr>
		                               	<tr>
		                               		<th>
												业务介绍
											</th>
			                                <td>
			                                	<s:property value="privServPackPO.privDesc"/>
			                                 </td>
		                                </tr>
	                                </table>
	                                <div>
	                                	<br />
	                                	<input type="button" id="packRecBtn" class="bt2_liebiao white" value="办理" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';openWindow();"/>
	                                	<input type="button" id="goLastBtn" class="bt2_liebiao white" value="返回" onmousedown="this.className='bt2on white'" onmouseup="this.className='bt2_liebiao white';gobackPage();"/>
									</div>
								</div>								
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
				<div class="popup_confirm" id="popup_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">提示：</div>
                    <div class="tips_body">
				        <p><i id="tipContent">您选择办理： </i><i><s:property value="privServPackPO.privName"/></i></p>
				        <div class="blank10"></div>
				        <p class="mt30">确认操作请点击"确认"提交。</p>
				    </div>
                    <div class="btn_box tc mt20">
	                    <span class="mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">确认</a></span>
	                    <span class="inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消</a></span>
                    </div>
            	</div>
        	</div>                
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
	gobackPage();		
}

// 弹出提示标志 0：未弹出 1：弹出
var tipflag = 0;

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
    
    if (key == 13 || key == 89 || key == 221)
    {
    	if(tipflag == 1)
    	{
    		doSub();
    	}
    	else
    	{
    		openWindow();
    	}
    }
    
    //8:backspace 32:空格 B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
	//82:R 220:返回
	if (key == 82 || key == 220)
	{
		gobackPage() ;
  		return ;
	}
}

// 返回上一页
function gobackPage()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		openRecWaitLoading();
		document.getElementById("packRecBtn").disabled = 'disabled';
		document.getElementById("goLastBtn").disabled = 'disabled';
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }privServPack/qryPrivServPack.action";
		document.actform.submit();
	}
}

// 办理
function doSub()
{
	if (submitFlag == 0) 
	{
		windowClose();
		submitFlag = 1;
		openRecWaitLoading();
		document.getElementById("packRecBtn").disabled = 'disabled';
		document.getElementById("goLastBtn").disabled = 'disabled';
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}privServPack/privServPackRec.action";
		document.actform.submit();	
	}
}

//弹出确认框
openWindow = function()
{
	wiWindow = new OpenWindow("popup_confirm",708,392);
	tipflag = 1;
}

// 关闭弹出div时，提示设置为未弹出
function windowClose()
{
	tipflag = 0;
	wiWindow.close();
}
</script>
</html>
