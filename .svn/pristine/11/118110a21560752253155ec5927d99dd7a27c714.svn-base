<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<%@ include file="/titleinc.jsp"%>
	
	<div class="main" id="main">
		<%@ include file="/customer.jsp"%>
		
		<div class="pl30">
			<%@include file="/jsp/valuecard/valueCardHeader.jsp" %>
			
			<div class="b712">
				<div class="bg bg712"></div>
  					<div class="blank15"></div>
  					<div class="p40">
  						<p class=" tit_info1">
  							尊敬的客户：<br/>
	      				&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您成为我们中国移动通信集团湖北有限公司的客户。在您申办业务前，请认真阅读以下条款：<br/>
	      				&nbsp;&nbsp;&nbsp;&nbsp;本终端业务受理系统的所有权和运作权，以及所受理具体业务的经营权归中国移动通信集团湖北有限公司所有，您必须完全同意所有服务条款，才可以通过本终端办理各类业务。
	      				移动电话号码和服务密码是您重要的个人资料，可以作为办理业务的凭证。凡使用本终端凭服务密码办理的一切业务，均视为您本人亲自办理，并由您本人负责。请您务必注意服务密码的保密。
	      				您必须提供准确的资料，我公司将根据您提供的资料进行核对，如有不符，系统将不予受理业务。
	      				目前本终端受理以下免办理手续费用的项目，包括业务办理，缴纳话费，打印清单，补打发票等业务。
	      				您在申请业务后，如需要查询，请您浏览自助查询终端的相关栏目或者拨打10086查询。<br/>
	      				&nbsp;&nbsp;&nbsp;&nbsp;如果您完全接受以上的所有条款，请按[同意]继续受理业务。如果您不同意以上条款，请按[首页]或[退出]，本系统将自动退至主界面。
  						</p>
  						<div class=" tr"> 
  							<a class=" btagree" href="javascript:void(0);" onMouseDown="this.className='btagreeon'" onMouseUp="this.className='btagree';" onclick="doSub();return false;">同意</a> 
  						</div>
  					</div>
  				</div>
		</div>
	</div>
	
	<%@ include file="/backinc.jsp"%>
	
	<script language="javascript">
		
		// 键盘松开
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
	</script>

