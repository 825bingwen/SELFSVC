<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<%@ include file="/titleinc.jsp"%>
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		<div class="pl30">
			<%@include file="${sessionScope.basePath }jsp/valuecard/valueCardHeader.jsp" %>
			
			 <div class="b712">
			 	<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40 pr0">
    						<div class="blank10"></div>     
      					<div class="blank20"></div>
      					<div class="blank40"></div>
    						<div class="recharge_result tc">
     							<div class="repeatcasherror">
    							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ǳ���Ǹ�������νɷѿ�������
    							���������ն�(�ʵ��굥��ѯ->������ʷ��ѯ)�������ƶ���վ���СƱƾ֤��Ӫҵǰ̨��ѯ��ʵ��
    							�ɴ˸����������㣬�����½⣡
    						    </div>
    						    <a href="javascript:void(0);" class="bt10 fr mr201" onmousedown="this.className='bt10on fr mr201'" onmouseup="this.className='bt10 fr mr201';" style="display:inline;" onclick="qryFeeHistory();return false;">�ɷ���ʷ��ѯ</a>
    						</div>
    					</div>
			 </div>
		</div>
	</div>
	
	<%@ include file="/backinc.jsp"%>		
