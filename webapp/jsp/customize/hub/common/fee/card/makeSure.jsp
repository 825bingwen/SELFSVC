<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="cardPayLogVO.servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="totalFee" /></span> Ԫ</p>
							<p class="tit_info"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />��</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--������ ���ڴ��� ���Ժ�-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
                  	<p class="tips_txt">���ڴ������Ժ�......</p>
                 	<div class="line"></div>
                  	<div class="popup_banner"></div>                
                </div>

				<script type="text/javascript">
					openWindow_wait = function(id){
						//�����ʱ����
						clearInterval(timeToken);
						wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
					    setTimeout("doSub()", 500);
					}				
				</script>
				<!--����������-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
	<script language="javascript">
		document.onkeyup = pwdKeyboardUp;
	
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
				return;
			}
			else if (key == 13 || key == 89 || key == 221)
			{
				openWindow_wait('pls_wait');
			}		
		}
		
	</script>
</html>
