<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>

<%@ include file="/titleinc.jsp"%>

<div class="main" id="main">
	<%@ include file="/customer.jsp"%>
	<div class="pl30">
		<!-- �˴�����������������ʱ�����ò˵����ִ˴���Ҫ�����ͷ�ļ� -->
		<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
		
		 <div class="b712 fm_pay_money">
		 	<div class="bg bg712"></div>
   					<div class="blank30"></div>
   					<div class="p40 pr0">
   						<div class="blank10"></div>
     					<div class="blank20"></div>
     					<div class=" pay_money_wrap2">
     					 	<p class="pay_all">
     					 		<span style="padding-left:120px;">��Ͷ�룺</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">Ԫ</span>
     					 	</p>
     					 	<div class="pay_state clearfix">
     					 		<span class="cash_arrow"></span>
           						<p class="fl fs22">
           							Ͷ��״̬�� 
           							<s:if test="yingjiaoFee == '0'">
									<span id="promptMsg" class="yellow">Ͷ�ҽ����������ɷѰ�ť</span>
								</s:if>
								<s:else>
									<span id="promptMsg" class="yellow">��Ͷ��ֽ��...</span>
								</s:else>			
           							<br />
           							<span style="padding-left:119px;">Ͷ��ʱ����</span><span class="yellow"><%=timeout %></span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />��
           							<br/>
           							<span style="padding-left:119px;">֧��</span><span class="yellow">5��10��20��50��100Ԫ</span>����ֽ��
           						</p>
         					</div>
     					</div>
     					<div class="blank30"></div>
     					<div>
     					 	<img src="${sessionScope.basePath }images/rmb.gif" style="float:left; padding-left:160px;" alt="��Ͷ��" />
     					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box cancle fl" id="cancelBusi">
     					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">ȡ�����ν���</a>
     					 	</div>
     					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box charge_unable fl" id="bCommitBusi">
     					 		<a href="#" onclick="return false;"></a>
     					 	</div>
     					 	<div style="padding-top:120px; padding-left:30px; display:none" class="btn_box buy_enable fl" id="commitBusi">
     					 		<a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="openWindow_wait('pls_wait');return false;">�ɷ�</a>
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
	    var isDone = 0;
		openWindow_wait = function(id){
		
			// �жϵ�ǰ�Ľɷѽ���Ƿ���ڵ����мۿ���ֵ�ܺ�
			if(document.getElementById("tMoney").value < <s:property value='totalFee' />)
			{
				alertRedErrorMsg("����Ͷ�ҽ��㣡");
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
	<!--����������-->
</div>

<%@ include file="/backinc.jsp"%>
<script type="text/javascript">
	
	// �����ɿ�
	document.onkeyup = pwdKeyboardUp;
	
	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//ȷ��
		if (key == 13 || key == 89 || key == 221) 
		{
			doSub();
			return;
		}
		//����
		else if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
			return;
		}		
	}
	
</script>
</html>
