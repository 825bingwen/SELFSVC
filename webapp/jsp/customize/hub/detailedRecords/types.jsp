<%--
 @User: ��Ⱥ/g00140516
 @De: 2012/02/09
 @comment: NG3.5���굥����֮�굥��ѯ
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_HUB_201201_981
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
			
			var url = "";
			
			if ('GPRSWLAN' != cdrType)
			{
				url = "${sessionScope.basePath}cdrquery/selectFeeType.action";				
			}
			else
			{
				url = "${sessionScope.basePath}cdrquery/selectGprsWlanType.action";
			}			
			
			//����Ѿ�ѡ�����굥���ͻ��ߵ���˷��أ�����ִ���κβ���
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target = "_self";
				document.actform.action = url;
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
			<input type="hidden" name="month" value="<s:property value='month' />">
			<input type="hidden" id="cdrType" name="cdrType" value="" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����ʵ��굥��ѯ</a>
				
				<div class="b966 tc">
				    <div class=" p40">
				    	<div class="blank30"></div>
						<p class="fs22 fwb  tit_info rel" align="left">
							<span class="bg"></span>��ѡ���굥����
						</p>
						<div class="blank30"></div>
						<div id="btns" class="fl ml40" >
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'FIXFEE');">
					    			�ײ���̶���
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'GSM');">
					    			ͨ���굥
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'SMS');">
					    			��/�����굥
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'GPRSWLAN');">
					    			�����굥
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'ISMG');">
					    			��ֵҵ��۷Ѽ�¼
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'INFOFEE');">
					    			���շ�ҵ���¼
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'OTHERFEE');">
					    			�����۷Ѽ�¼
					    		</a>
							</span>
							<span class="relative mr20 inline_block mb30">
								<a class="btn_bg_175" onmouseup="this.className='btn_bg_175_hover';go(this, 'bt222', 'DISCOUNT');">
					    			�˵������¼
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