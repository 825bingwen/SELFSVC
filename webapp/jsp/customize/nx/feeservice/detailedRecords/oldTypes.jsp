<%--
 @User: ��Ⱥ/g00140516
 @De: 2012/02/09
 @comment: NG3.5���굥����֮�굥��ѯ
 @remark create g00140516 2012/03/14 R003C12L03n01 OR_NX_201203_128
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>NG2.3�����ն�ϵͳ--�ƶ����Ѳ�ѯ--���굥��ѯ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
		//��ѯ�굥
		function go(btn, btClass, cdrType) 
		{	
			document.getElementById("cdrType").value = cdrType;
			
			//����Ѿ�ѡ�����굥���ͻ��ߵ���˷��أ�����ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openQryWaitLoading('qryWaitLoading');

				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/qryDetailedRecordsOld.action";
				document.actform.submit();				
			}		
		}
		
		function goback(curmenu) 
		{
			//�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/selectMonth.action";
				document.actform.submit();
			}			
		}
		
		document.onkeydown = keyDown;
		function keyDown(e)
		{
			//8��32��66��77 ����
		    //82��220 ����
		    //13��89��221 ȷ��
		    //80 ��ӡ
		    //85 ��һҳ
		    //68 ��һҳ
		    
			//���ռ�����
			var key = GetKeyCode(e);
		     
		    //8:backspace 32:�ո� B:66 M:77
		    if (key == 8 || key == 32 || key == 66 || key == 77)
		    {
		    	return false;
		    }
		    
		    //82:R 220:����
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />") ;
		   		return ;
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="cycle" value="<s:property value='cycle' />" />
			<input type="hidden" name="startDate" value="<s:property value='startDate' />" />
			<input type="hidden" name="endDate" value="<s:property value='endDate' />" />
			<input type="hidden" id="cdrType" name="cdrType" value="" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';goback('<s:property value='curMenuId' />'); return false;" style="display:inline">�����ϼ�</a>
				<div class="b966 tc">
				    <div class=" p40">
				    	<div class="blank30"></div>
						<p class="fs22 fwb  tit_info rel" align="left"><span class="bg"></span>�굥����ѡ��</p>
						<div class="blank30"></div>
						<div id="btns" class="fl" >							
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '1');">
				    				ͨ���굥
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '3');">
				    				���������굥
				    			</a>
							</span>
							<span class="relative mr10 inline_block ">
								<a class="btn_bg_146" onmouseup="this.className='btn_bg_146_hover';go(this, 'bt222', '4');">
				    				GPRS������ѯ
				    			</a>
							</span>							
						</div>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>