<%--
 @User: zWX176560
 @De: 2013/8/28
 @comment: 显示积分值
 @remark: create zWX176560 时间 2013/8/28 OR_NX_201308_595_宁夏_新开发积分查询接口 版本号(R003C11L09n01)
--%>
<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String provinceID = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);	
String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
<META HTTP-EQUIV="Expires" CONTENT="0"/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">

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
    
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(provinceID))
	{
	%>
		//82:R 退出
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:返回
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		if (key == 48)
		{
			openRecWaitLoading_NX("recWaitLoading");
			history.go(-1);
		}
		// 上翻
		else if (key == 85)
		{
			myScroll.moveUp(30);
			return;
		}
		// 下翻
		else if (key == 68)
		{
			myScroll.moveDown(30);
			return;
		}	
	<%
	}
	%>
}

function gowait()
{
	openRecWaitLoading_NX("recWaitLoading");
}
</script>
   </head>
	<body scroll="no">
		<form name="actform" method="post">		
			<%@ include file="/titleinc.jsp"%>
		 	<div class="main" id="main">
		  		<%@ include file="/customer.jsp"%>
				<%
					if (!"root".equalsIgnoreCase(parentMenuID) && "1".equals(keyFlag))
					{
				%>
					<a href="#" class="bt10_1 fr mr92" onmousedown="this .className='bt10_1on fr mr92'" onmouseup="this.className='bt10_1 fr mr92';gowait();history.go(-1); return false;" style="display:inline">返回<s:property value=""/>&nbsp;(按0键)</a>
				<%		
					}
				%>
		        <!--滚动条-->
				<div class="box842W fl ml45IE6 relative">					
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							
							<div class="div747w444h" >
								<!-- 列表内容 -->
		                        <p class="tit_info" align="left"><span class="bg"></span>积分查询</p>
								<div class="ptop180 tc box747w367h" id="inn" >
								
								<table width="80%" class="tb_blue" align="center">
								
									<tr>
										<th width="60%" class="tc">
											<s:property value="userScorePO.scoreType"/>：
										</th>
										<td width="40%" class="tc">
											<span class="yellow">
												<s:property value="userScorePO.scoreValue" />
											</span>
										</td>
									</tr>

							</table>			
								
									<p class="blank10"></p>
								</div>	
	    						
							</div>
							
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
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
	<!-- add begin m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
	<script type="text/javascript">
	    openSurveyDialog();
	</script>
	<!-- add end m00227318 2012/09/11 eCommerce R003C12L08_EHUB OR_HUB_201205_1032  -->
</html>
