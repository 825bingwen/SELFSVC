<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
      					<p class="tit_info"><span class="bg"></span>��������Ĵ����<span class="yellow">ҵ�����������˿�����ע��ȡ����</span></p>
      					<p class="tit_info"><span>����ʱ����</span><span class="yellow">30</span>�룬��ǰʣ��<input type="text"  id="tTime" name="tTime" value="30" readonly="readonly" />��</p>
      					<div class="blank10"></div>   
     						<div class="blank20"></div>
      					<div class="blank10"></div>
     						<div class="gif_animation">
     							<img src="${sessionScope.basePath }images/gif1.gif" alt="��忨" />
     						</div>        
    					</div>
  				</div>
		</div>
	</div>
	
	<%@ include file="/backinc.jsp"%>
		
	<script type="text/javascript">
		
		// �����ɿ�
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
		}
	</script>
