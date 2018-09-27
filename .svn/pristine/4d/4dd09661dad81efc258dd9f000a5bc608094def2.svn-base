<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
			<div class="pl30">
				<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank60"></div>
   					<div class="p40 pr0">
   						<p class="tit_info "><span class="bg"></span>手机号码：<span class="yellow"><s:property value='cardPayLogVO.servnumber' /></span></p>
   						<p class="tit_desc pl40 ">交费金额：<span class="yellow"><s:property value='tMoney' />元</span> </p>
   						
   						<div class="blank20"></div>
     					<div class="line w625"></div>
    						<div class="blank30"></div>
    						<div class="recharge_result tc">
    							<p class="title yellow pt30">您购买的电子有价卡已出票成功！</p>
       						<p class="desc pt20">请保存好您的的交易凭条。</p>
       						<%--<s:if test='hasMultiInvoices == "true"'>
       							<div class="btn_box3 clearfix">
       						</s:if>
       						<s:else>
       							<div class="btn_box2 clearfix">
       						</s:else>
       						  <input type="hidden" id="actId" vlaue="%{actId}" name="actId"/> --%>
       						</div>
    						</div>
   					</div>
				</div>
			</div>
			
			<div class="popup_confirm" id="popup_confirm">
               	<div class="bg"></div>
               	<div class="tips_title">提示：</div>
               	<div class="tips_body">
  				<p>您将打印发票，为了确保您信息的安全，系统将下发短信校验码到您的手机，请将收到的短信校验码输入下个页面的校验码框内。</p>
  				<div class="blank10"></div>
  				<p class="mt30">请确认是否继续？</p>
				</div>
               	<div class="btn_box tc mt20">
               		<span class=" mr10 inline_block ">
               			<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="sendRandomPwd();return false;">确认</a>
               		</span>
               		<span class=" inline_block ">
               			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();submitFlag=0;">取消</a>
               		</span>
               	</div>
             </div>
		<script type="text/javascript">
		openWindow = function(id)
		{
			wiWindow = new OpenWindow("popup_confirm", 708, 392);//打开弹出窗口例子
		}				
		</script>
	</div>
	
	<%@ include file="/backinc.jsp"%>
