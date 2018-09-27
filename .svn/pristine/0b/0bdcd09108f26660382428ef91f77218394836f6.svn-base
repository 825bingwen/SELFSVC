<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);
String isCardEquip = termInfo.getTermspecial().substring(4, 5);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>		
		<script language="javascript">
			//防止重复提交
			var submitFlag = 0;
			
			//82、220 返回		
			document.onkeydown = pwdKeyboardDown;
		
			function pwdKeyboardDown(e)
			{	
				var key = GetKeyCode(e);
				
				//更正
				if (key == 77) 
				{
					preventEvent(e);
				}
				
				if (!KeyIsNumber(key))
				{
					return false;//这句话最关键
				}
			}
		
			function KeyIsNumber(KeyCode) 
			{
	    		//只允许输入0-9
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
				
				//返回
				if (key == 82 || key == 220) 
				{
					goback("<s:property value='curMenuId' />");
					return;
				}
				//确认
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
			}
			
			function goback(menuid)
			{
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("curMenuId").value = menuid;
				
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }activitiesRec/queryPresentsList.action";
					document.dutyInfoForm.submit();
				}
			}
						
			function doSub() 
			{
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }activitiesRec/selectType.action";
					document.dutyInfoForm.submit();
				}
			}
			
			//出现异常
			function setException(errorMsg) 
			{			
				if (submitFlag == 0)
				{
					submitFlag = 1;
					
					document.getElementById("errormessage").value = errorMsg;

					//异常处理，只要出现了异常就要记录日志  
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }charge/goCardError.action";
					document.dutyInfoForm.submit();
				}			
			}	
			
			
		</script>
	</head>

	<body scroll="no" onload="">
		<form name="dutyInfoForm" method="post" target="_self">
		
		<!-- 营销推荐标识 -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<!-- 档次名称 -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- 活动组名称 -->
		<input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
		
		<!-- 活动编码 -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- 档次编码 -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- 奖品编码串 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- 手机imeiid号 -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- 赠品总价 -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- 赠品数量 -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		<!-- 受理金额 -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
      					<h2>活动受理流程：</h2>
      					<div class="blank10"></div>
      					<a title="选择活动档次" href="#">1. 选择活动档次</a>
      					<a title="受理协议" href="#" class="on">2. 受理协议</a>  
      					<a title="选择支付方式" href="#">3. 选择支付方式</a> 
      					<a title="投入纸币" href="#">4. 投入纸币</a>
          				<a title="完成" href="#" >5. 完成</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank15"></div>
    					<div class="p40">
    						<p class=" tit_info1">
    							尊敬的客户：
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团湖北有限公司的客户。在您申办业务前，请认真阅读以下条款：
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;本终端业务受理系统的所有权和运作权，以及所受理具体业务的经营权归中国移动通信集团湖北有限公司所有，您必须完全同意所有服务条款，才可以通过本终端办理各类业务。
									移动电话号码和服务密码是您重要的个人资料，可以作为办理业务的凭证。凡使用本终端凭服务密码办理的一切业务，均视为您本人亲自办理，并由您本人负责。请您务必注意服务密码的保密。
									您必须提供准确的资料，我公司将根据您提供的资料进行核对，如有不符，系统将不予受理业务。
									目前本终端受理以下免办理手续费用的项目，包括业务办理，缴纳话费，打印清单，补打发票等业务。
									您在申请业务后，如需要查询，请您浏览自助查询终端的相关栏目或者拨打10086查询。
									<br />
									&nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意]继续受理业务。如果您不同意以上条款，请按[退出]，本系统将自动退至主界面。
    						</p>
    						<div class=" tr"> 
    							<a title="同意" class=" btagree" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">同意</a> 
    						</div>
    					</div>
    				</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
