<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<%@ include file="/titleinc.jsp"%>

<div class="main" id="main">
	<%@ include file="/customer.jsp"%>
	<div class="pl30">
		<!-- 此处若被其他功能引用时，请用菜单区分此处需要引入的头文件 -->
		<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
		
		 <div class="b712 fm_pay_money">
		 	<div class="bg bg712"></div>
   					<div class="blank30"></div>
   					<div class="p40 pr0">
   						<div class="blank10"></div>
     					<div class="blank20"></div>
     					<div class=" pay_money_wrap2">
     					 	<p class="pay_all">
     					 		<span style="padding-left:120px;">已投入：</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">元</span>
     					 	</p>
     					 	<div class="pay_state clearfix">
     					 		<span class="cash_arrow"></span>
           						<p class="fl fs22">
           							投币状态： 
           							<s:if test="yingjiaoFee == '0'">
									<span id="promptMsg" class="yellow">投币结束后，请点击缴费按钮</span>
								</s:if>
								<s:else>
									<span id="promptMsg" class="yellow">请投入纸币...</span>
								</s:else>			
           							<br />
           							<span style="padding-left:119px;">投币时长共</span><span class="yellow"><%=timeout %></span>秒，当前剩余<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />秒
           							<br/>
           							<span style="padding-left:119px;">支持</span><span class="yellow">5、10、20、50、100元</span>面额的纸币
           						</p>
         					</div>
     					</div>
     					<div class="blank30"></div>
     					<div>
     					 	<img src="${sessionScope.basePath }images/rmb.gif" style="float:left; padding-left:160px;" alt="请投币" />
     					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box cancle fl" id="cancelBusi">
     					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">取消本次交易</a>
     					 	</div>
     					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box charge_unable fl" id="bCommitBusi">
     					 		<a href="#" onclick="return false;"></a>
     					 	</div>
     					 	<div style="padding-top:120px; padding-left:30px; display:none" class="btn_box buy_enable fl" id="commitBusi">
     					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="openWindow_wait('pls_wait');return false;">缴费</a>
     					 	</div>
     					</div>
   					</div>
		 </div>
	</div>
	
	<!--弹出框 正在处理 请稍候-->
	<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
		<div class="bg"></div>
                	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="处理中..." /></p>
               	<p class="tips_txt">正在处理，请稍候......</p>
              	<div class="line"></div>
               	<div class="popup_banner"></div>                
             </div>

	<script type="text/javascript">
	    var isDone = 0;
		openWindow_wait = function(id){
		
			// 判断当前的缴费金额是否大于等于有价卡面值总和
			if(document.getElementById("tMoney").value < <s:property value='totalFee' />)
			{
				alertRedErrorMsg("您的投币金额不足！");
				return;
			}
			
			if(isDone == 0)
			{
				isDone = 1;
				document.getElementById('commitBusi').disabled = true;
				doSub();
			}
		}				
	</script>
	<!--弹出窗结束-->
</div>

<%@ include file="/backinc.jsp"%>
<script type="text/javascript">
	
	// 键盘松开
	document.onkeyup = pwdKeyboardUp;
	
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//确认
		if (key == 13 || key == 89 || key == 221) 
		{
			doSub();
			return;
		}
		//返回
		else if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
			return;
		}		
	}
	
</script>
</html>
