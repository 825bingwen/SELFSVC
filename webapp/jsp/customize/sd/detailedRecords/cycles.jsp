<%--
 @User: ��Ⱥ/g00140516
 @De: 2012/02/09
 @comment: NG3.5���굥����֮�굥��ѯ
 @remark: create g00140516 2012/02/09 R003C12L01n01 OR_huawei_201112_953
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
		
		//ѡ������
		function selectCycle(cycle) 
		{	
			document.getElementById("cycle").value = cycle;
			
			<s:iterator value="cycles" id="currCycle">
				if (cycle == "<s:property value="#currCycle.cycle" />")
				{
					document.getElementById("startDate").value = "<s:property value="#currCycle.startDate" />";
					document.getElementById("endDate").value = "<s:property value="#currCycle.endDate" />";
				}				
			</s:iterator>
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
					
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}cdrquery/selectType.action";
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
			<input type="hidden" id="cycle" name="cycle" value="" />
			<input type="hidden" id="startDate" name="startDate" value="" />
			<input type="hidden" id="endDate" name="endDate" value="" />
			<input type="hidden" name="custName" value="<s:property value='custName' />" />
			<input type="hidden" name="iscycle" value="<s:property value='iscycle' />" />
			<input type="hidden" name="month" value="<s:property value='month' />">
						
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!-- modify begin cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'feeservice/feeServiceFunc.action'); return false;" style="display:inline">�����˵��굥��ѯ</a>
				<!-- modify end cKF76106 2012/10/18 R003C12L10n01 OR_huawei_201210_39 -->
				<div class="blank30"></div>
		          
		        <p class="fs18 p20 ml50">��ѡ���ѯ���ڣ�</p>
		          
		        <div class="blank15"></div>
		        
		        <div class="pl160">
		        	<ul>
		        		<s:iterator value="cycles" id="currCycle">
		        			<li class="cycle">
		        				<a href="#" onmousedown="this.className='hover tc'" onmouseup="this.className='tc'" onclick="selectCycle(<s:property value='#currCycle.cycle' />)">
		        					<s:property value="#currCycle.startDate" /> - <s:property value="#currCycle.endDate" />
		        				</a>
		        			</li>
		        		</s:iterator>
		        	</ul>
		        </div>		         			
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
