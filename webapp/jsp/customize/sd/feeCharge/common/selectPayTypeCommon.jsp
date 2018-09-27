<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
</head>

		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<%@ include file="/jsp/valuecard/valueCardHeader.jsp" %>
				
				<div class="b712">
					<div class="bg bg712"></div>
   					<div class="blank60"></div>
   					<div class="p40">
   						<p class=" tit_info"><span class="bg"></span>手机号码：<span class="yellow"><s:property value="telnum" /></span></p>
						<p class="fs22 fwb pl40 lh30">应收金额：<span class="yellow fs22"><s:property value="totalFee" /></span> 元</p>
						<div class="blank10"></div>
						<div class="line"></div>
     						<div class="blank10"></div>
     						<p class="fs22 tit_arrow"><span class="bg"></span>选择支付方式：</p>
     						<div class="blank20"></div>
       					<div class="blank5"></div>
       					<ul class="pay_way_list clearfix">
       						<s:iterator value="usableTypes" id="type" status="st">
       							<s:if test="#st.index == 0">          								
          							<li class="mr70">          									
         						</s:if>
         						<s:else>
         							<li>          									
         						</s:else>
       							<a href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
       							</li>
          					</s:iterator>
       					</ul>				
   					</div>
				</div>
			</div>
		</div>
		<%@ include file="/backinc.jsp"%>
	<script type="text/javascript">
		// 键盘松开
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//返回
			if (key == 220 || key == 82) 
			{
				goback("<s:property value='curMenuId' />");
			}
		}
	</script>
</html>
