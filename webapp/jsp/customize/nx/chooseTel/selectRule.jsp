<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="">
		<meta http-equiv="description" content="">
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		//82��220 ����		
		document.onkeydown = pwdKeyboardDown;
		
		function pwdKeyboardDown(e)
		{	
			var key = GetKeyCode(e);
			
			//����
			if (key == 77) 
			{
				preventEvent(e);
			}
			
			if (!KeyIsNumber(key))
			{
				return false;//��仰��ؼ�
			}
		}
		
		function KeyIsNumber(KeyCode) 
		{
    		//ֻ��������0-9
    		if (KeyCode >= 48 && KeyCode <= 57)
    		{
    			return true;
    		}
    		
    		return false;
		}	
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);
			
			//����
			if (key == 82 || key == 220) 
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
				return;		
			}
			<%
			if("1".equals(keyFlag))
			{
			%>
				if(key == 49)
				{
					doSub(4);
				}
				else if(key == 50)
				{
					doSub(2);
				}	
				else if(key == 51)
				{
					doSub(3);
				}
			<%
			}
			%>				
		}

		// �ύ
		function doSub(sele_rule) 
		{			
			//��ֹ�ظ�����
			if (submitFlag == 0) 
			{
				submitFlag = 1;
				
				openRecWaitLoading_NX("recWaitLoading");
				
				document.actform.sele_rule.value = sele_rule;
				
		  		document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}chooseTel/inputTelNumByRule.action";
				document.actform.submit();
			}
		}
		
		// ����
		function goback(curmenu) 
		{
			//��ֹ�ظ�����
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
				document.getElementById("curMenuId").value = curmenu;
				
				document.actform.target = "_self";
				//modify begin l00190940 2011-12-5 ������һҳ����
				//document.actform.action = "${sessionScope.basePath }chooseTel/selectRegion.action";
				document.actform.action = "${sessionScope.basePath }login/backForward.action";
				//modify end l00190940 2011-12-5 ������һҳ����
				document.actform.submit();
			}
		}
		</script>
	</head>
	<body onload="window.focus();" scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
			<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
			<input type="hidden" id="regionname" name="regionname" value="<s:property value='regionname'/>" />
			<input type="hidden" id="sele_rule" name="sele_rule" value=""/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2><%=menuName %>����</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0);" class="on">
							1.ѡ���ѯ��ʽ
						</a>
						<a href="javascript:void(0);">
							2.�����ѯ����
						</a>
						<a href="javascript:void(0);">
							3.ѡ�����
						</a>
						<a href="javascript:void(0);">
							4.���������Ϣ
						</a>
						<a href="javascript:void(0);">
							5.���
						</a>
					</div>
				</div>
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank60"></div>
					<div class="p40">
						<p class=" tit_info"><span class="bg"></span>���У�<s:property value='regionname'/></p>
						<div class="blank10"></div>
						<div class="line"></div>
						<div class="blank10"></div>
						<p class="tit_arrow fs22"><span class="bg"></span>��ѡ������ѯ��ʽ��</p>
						<div class="blank20"></div>
        				<div class="blank5"></div>
						
						<ul class="money_list_clearfix">
							<%
							if("1".equals(keyFlag))
							{
							%>
								<li class="tel_selectrule tc">
									<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(4); return false;">
										���(��1��)
									</a>
								</li>
								<li class="pt10"></li>	
								<li class="tel_selectrule tc">
									<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
										ǰ��λ(��2��)
									</a>
								</li>
								<li class="pt10"></li>							
								<li class="tel_selectrule tc">
									<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(3); return false;">
										����λ(��3��)
									</a>
								</li>
							<%
							}
							else
							{
							%>
								<li class="tel_selectrule tc">
									<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(4); return false;">
										���
									</a>
								</li>
								<li class="pt10"></li>	
								<li class="tel_selectrule tc">
									<a class="tc" href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(2); return false;">
										ǰ��λ
									</a>
								</li>
								<li class="pt10"></li>							
								<li class="tel_selectrule tc">
									<a href="#" class="tc" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="doSub(3); return false;">
										����λ
									</a>
								</li>
							<%
							}
							%>
						</ul>
					</div>	
				</div>	
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
