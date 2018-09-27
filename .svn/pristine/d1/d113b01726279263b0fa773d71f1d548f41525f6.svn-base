<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">  
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
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
			
			//80:打印
			if (key == 80)
			{
				openWindow('openWin1');
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
			<!-- 用户ID -->
			<input type="hidden" id="subsid" name="subsid" value="<s:property value="subsid" />"/>
			<%-- 月份 --%>
			<input type="hidden" name="month" value="<s:property value='month' />" />
			<%-- 账期 --%>
			<input type="hidden" id="cycle" name="cycle" value="<s:property value='cycle' />"/>
			<%-- 开始时间 --%>
			<input type="hidden" id="startdate" name="startdate" value="<s:property value='startdate' />"/>
			<%-- 结束时间 --%>
			<input type="hidden" id="enddate" name="enddate" value="<s:property value='enddate' />"/>
			<%-- 账户ID --%>
			<input type="hidden" id="acctid" name="acctid" value="<s:property value='acctid' />"/>
			<%-- 是否为合户用户 --%>
			<input type="hidden" id="unionacct" name="unionacct" value="<s:property value='unionacct' />"/>

			<%-- 手机号 --%>
			<input type="hidden" id="telnum" name="telnum" value="<s:property value='telnum' />"/>
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">返回账单详单查询</a>
				
				<%-- 当月以及前五个月月份信息 --%>
				<div id="btns" class="fl" >
			        <span class="fs18 ml50">快速查看：</span>
			        <s:iterator value="months" id="currMonth" status="st">
						<s:if test="#currMonth==month">
							<a href="javascript:void(0)" class="bt222on fs16"><s:property value="#currMonth.substring(0,4)+'年'+#currMonth.substring(4,6)+'月'" /></a>
						</s:if>
						<s:else>
						    <a href="javascript:void(0);goMonth('<s:property value="currMonth"/>')" class="bt2 fs16" ><s:property value="#currMonth.substring(0,4)+'年'+#currMonth.substring(4,6)+'月'" /></a>
           				</s:else>  
					</s:iterator>
				</div>
				<div class="clear"></div>
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						
						<%-- 包月量信息内容 --%>
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="tit_info" align="left">&nbsp;</p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
								<table class="tb_blue" width="90%">
								 	<tr>
								 		<th colspan="2" style="text-align: left">账期<s:property value="cycle" />包月量信息</th>
								 	</tr>
								 	<tr>
								 		<td>
											通信总量
										</td>
										<td style="text-align: left">
											<s:iterator value="obj_total" id="pkgpo" status="st">
												<s:iterator value="#pkgpo.privlist" id="list" status="st">
													<s:property value="#list.freetype" />&nbsp;&nbsp;
														包月量:&nbsp;<s:property value="#list.total" />&nbsp;&nbsp;
														使用量:&nbsp;<s:property value="#list.used" />;
														<br/>
												</s:iterator>
							        		</s:iterator>
										</td>
								 	</tr>
								 	
									<s:iterator value="obj_pkg" id="pkgpo" status="st">
										<tr>
											<td>
												<s:property value="#pkgpo.pkgname" />
											</td>
											<td style="text-align: left;">
												<s:iterator value="#pkgpo.privlist" id="list" status="st">
												<s:property value="#list.freetype" />&nbsp;&nbsp;
													包月量:&nbsp;<s:property value="#list.total" />&nbsp;&nbsp;
													使用量:&nbsp;<s:property value="#list.used" />;
													<br/>
												</s:iterator>
											</td>
										</tr>
					        		</s:iterator>
								</table>
							</div>
						</div>
						
						<%-- 滚动条 --%>
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
                
                <%-- 发送短信到手机 --%>
                <div class="box120W fl ml10">
					<p class="blank10"></p>
					<a  class="btn_sendmsg" href="javascript:void(0)" onclick="openWindow('openWin1')" ></a>
				</div>
                <div class=" clear"></div>
                
                <%-- 弹出框 --%>
                <div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
                    <div class="bg"></div>
                    <div class=" blank60"></div><div class=" blank60"></div>
                   
                    <div class="  blank10n"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您确定要将当前包月量信息发送短信到手机吗？</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();sendMonthlyMsg();">确定</a>
                    <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
                    </div>
                </div>
                
                <%-- 短信发送成功弹出框 --%>
                <div class="openwin_tip div708w392h" id="successedSend">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的包月量信息短信发送成功！</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <%-- 短信发送失败弹出框 --%>
                <div class="openwin_tip div708w392h" id="errorSend">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">对不起，您的包月量信息短信发送失败！</p>
                    <p class="fs30 yellow pt30 tc pt30 pl20">请稍后再试！给您带来的不便，敬请原谅！</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
                
                <%-- 短信重复发送弹出提示框 --%>
                <div class="openwin_tip div708w392h" id="reaptSend">
                    <div class="bg"></div>
                    <div class=" blank40"></div><div class=" blank40"></div>
                    <p class="fs30 yellow pt30 tc pt30 pl20">您的包月量信息短信已经发送！</p>
                     <p class="fs30 yellow pt30 tc pt30 pl20">如仍需重复发送，请重新查询！</p>
                    <div class=" blank10"></div>
                    <div class="tc">
                    <div class=" blank60"></div>
                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确定</a>
                    </div>
                </div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">
var isReaptSend = 0;
// 退出按钮，回主页面
function goback(menuid)
{
	// 防止重复提交
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath}login/backForward.action";
        document.actform.submit();
    }
}

// 查询对应月份的账期数据
function goMonth(monthStr) 
{	
	//如果已经选择了账期或者点击了月份，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.month.value = monthStr;
		openRecWaitLoading();
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}monthlyAmount/qryMonthCycle.action";
		document.actform.submit();
	}	
}

// 发短信确认弹出框
openWindow = function(id)
{
	wiWindow = new OpenWindow("openWin1",708,392);
}

// 短信发送成功提示框
successedSendWindow = function(id)
{
	wiWindow = new OpenWindow("successedSend",708,392);
}	

// 短信发送成功提示框
errorSendWindow = function(id)
{
	wiWindow = new OpenWindow("errorSend",708,392);
}

// 短信发送成功提示框
ReaptSendWindow = function(id)
{
	wiWindow = new OpenWindow("reaptSend",708,392);
}

// 发送短信
function sendMonthlyMsg()
{
	if(isReaptSend == 0)
	{
		var postStr = "";
		
		// 短信信息内容
		var shortMsg = "尊敬的用户，您好！您的账期"+document.getElementById('cycle').value+"包月量信息如下：  通信总量:";
		
		<s:iterator value="obj_total" id="pkgpo" status="st">
			<s:iterator value="#pkgpo.privlist" id="list" status="st">
				shortMsg = shortMsg + "<s:property value="#list.freetype" />(" +
							"包月量:" + "<s:property value="#list.total" />,"+
							"使用量:" + "<s:property value="#list.used" />)  ";
			</s:iterator>
	    </s:iterator>
	    shortMsg = shortMsg + ";";
	    <s:iterator value="obj_pkg" id="pkgpo" status="st">
			shortMsg = shortMsg + "<s:property value="#pkgpo.pkgname" />:";
			<s:iterator value="#pkgpo.privlist" id="list" status="st">
				shortMsg = shortMsg + "<s:property value="#list.freetype" />(" + 
						"包月量:<s:property value="#list.total" />," +
						"使用量:<s:property value="#list.used" />)  ";
			</s:iterator>
	    </s:iterator>
	    shortMsg = shortMsg + ";";
		
		// 请求参数
		postStr = "telnum="+document.getElementById('telnum').value;
		postStr = postStr + "&shortMsg="+shortMsg;
		
		// 入参中包含中文，将中文转换为十六进制防止乱码	
		var param = encodeURI(encodeURI(postStr));
		var url = "${sessionScope.basePath}monthlyAmount/sendMonthlyMsg.action";
		var loader = new net.ContentLoader(url, callBack, null, "POST", param, "application/x-www-form-urlencoded");
	}
	else
	{
		// 重复发送提示
		ReaptSendWindow('reaptSend');
	}
}

// 回调的方法   
function callBack()
{
	// 返回符号
	var msgWelcome = this.req.responseText;

	// 短信发送成功
    if(msgWelcome == 'true')
    {
    	isReaptSend = 1;
    	successedSendWindow('successedSend');
    }
    // 短信发送失败
    else
    {
    	errorSendWindow('errorSend');
    }
}

</script>
