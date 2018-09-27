<%@ page contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">  
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		var submitFlag = 0;
		
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
			
			//80:��ӡ
			if (key == 80)
			{
				openWindow('openWin1');
			}
		}
		</script>
	</head>
	<body scroll="no" onload="window.focus();">	
		<form name="actform" method="post">
			<!-- �û�ID -->
			<input type="hidden" id="subsid" name="subsid" value="<s:property value="subsid" />"/>
			<%-- �·� --%>
			<input type="hidden" name="month" value="<s:property value='month' />" />
			<%-- ���� --%>
			<input type="hidden" id="cycle" name="cycle" value=""/>
			<%-- ��ʼʱ�� --%>
			<input type="hidden" id="startdate" name="startdate" value=""/>
			<%-- ����ʱ�� --%>
			<input type="hidden" id="enddate" name="enddate" value=""/>
			<%-- �˻�ID --%>
			<input type="hidden" id="acctid" name="acctid" value=""/>
			<%-- �Ƿ�Ϊ�ϻ��û� --%>
			<input type="hidden" id="unionacct" name="unionacct" value=""/>
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';topGo('qryBill', 'serviceinfo/serviceInfoFunc.action'); return false;" style="display:inline">�����˵��굥��ѯ</a>
				
				<%-- �����Լ�ǰ������·ݵ�����Ϣ --%>
				<div id="btns" class="fl" >
			        <span class="fs18 ml50">���ٲ鿴��</span>
			        <s:iterator value="months" id="currMonth" status="st">
						<s:if test="#currMonth==month">
							<a href="javascript:void(0)" class="bt222on fs16"><s:property value="#currMonth.substring(0,4)+'��'+#currMonth.substring(4,6)+'��'" /></a>
						</s:if>
						<s:else>
						    <a href="javascript:void(0);goMonth('<s:property value="currMonth"/>')" class="bt2 fs16" ><s:property value="#currMonth.substring(0,4)+'��'+#currMonth.substring(4,6)+'��'" /></a>
           				</s:else>  
					</s:iterator>
				</div>
				
				<div class="clear"></div>
				<div class="box842W fl ml20 relative">
                    <div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						
						<%-- ������Ϣ --%>
						<div class="box747W fl">
							<div style="height:444px; padding:0px 0px 5px 0px; width:747px; overflow:hidden;">
								<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="tit_info" align="left">&nbsp;</p>
								<p class="ptop180 tc" id="inn" style="height:411px;  width:747px; overflow:hidden;" >
								<table class="tb_blue" width="85%">
								 	<tr>
								 		<th  scope="col" >����</th>
								 		<th  scope="col" >��ʼʱ��-����ʱ��</th>
								 		<th  scope="col" style="width: 10%">����</th>
								 	</tr>
								 		
									<s:iterator value="cycleList" id="list" status="st">
										<tr>
											<td>
												<s:property value="#list.cycle" />
											</td>
											<td>
												<s:property value="#list.startdate" />-<s:property value="#list.enddate" />
											</td>
											<td style="width: 10%">
						        				<a href="javascript:void(0)" class="bt222 fs16" onclick="view('<s:property value="#list.cycle" />', '<s:property value="#list.startdate" />','<s:property value="#list.enddate" />','<s:property value="#list.acctid" />','<s:property value="#list.unionacct" />')" onmousedown="this.className='bt222on fs16'" onmouseup="this.className='bt222 fs16';">
						        					��ѯ
						        				</a>
											</td>
					        			<tr>
					        		</s:iterator>
								</table>
							</div>
						</div>
						
						<%-- ������ --%>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage" style="width:75px; height:350px; ">
								<div class="blank10px"></div>
								<div class="box66W tc f16 lh30" id="gunDom" style=" width:66px; height:36px; position:absolute; cursor:move; left:765px; top:52px; line-height:36px" >0%</div>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <div class=" clear"></div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
<script type="text/javascript">
// �˳���ť������ҳ��
function goback(menuid)
{
	// ��ֹ�ظ��ύ
    if (submitFlag == 0)
    {
        submitFlag = 1;
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath}login/backForward.action";
        document.actform.submit();
    }
}

// ��ѯ��Ӧ�·�������Ϣ
function goMonth(monthStr) 
{	
	// ����Ѿ�ѡ�����·ݻ��ߵ���˷��أ�����ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.month.value = monthStr;
		
		<%-- add begin hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>
		openRecWaitLoading();
		<%-- add end hWX5316476 2015-6-24 OR_SD_201506_330  �����նˡ��굥��ѯ����ҳ�����ӡ�����Ŭ����ѯ�����Ժ󡭡��ĵȴ�����--%>

		document.actform.action = "${sessionScope.basePath}monthlyAmount/qryMonthCycle.action";
		document.actform.submit();
	}	
}

// �鿴��Ӧ���ڰ�������Ϣ		
function view(cycle,startdate,enddate,acctid,unionacct)
{
	// ����Ѿ�ѡ�������ڻ��ߵ���˷��أ�����ִ���κβ���
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("cycle").value = cycle;
		document.getElementById("startdate").value = startdate;
		document.getElementById("enddate").value = enddate;
		document.getElementById("acctid").value = acctid;
		document.getElementById("unionacct").value = unionacct;
		openRecWaitLoading();
		document.actform.action = "${sessionScope.basePath}monthlyAmount/qryMonthlyAmount.action";
		document.actform.submit();
	}
}	
</script>
