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
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%-- 组ID --%>
			<s:hidden id="groupid" name="groupid"></s:hidden>
			<s:hidden id="groupid" name="groupNcodePO.groupId"></s:hidden>
			
			<%-- 用户当月套餐 --%>
			<s:hidden id="currNcode" name="custNcodeInfo.currNcode"></s:hidden>
			<s:hidden id="currNcodeName" name="custNcodeInfo.currNcodeName"></s:hidden>
			
			<%-- 用户下月套餐 --%>
			<s:hidden id="nextNcode" name="custNcodeInfo.nextNcode"></s:hidden>
			<s:hidden id="nextNcodeName" name="custNcodeInfo.nextNcodeName"></s:hidden>
			
			<%-- 操作类型--%>
			<s:hidden id="operType" name="operType"></s:hidden>
			
			<%-- 生效方式--%>
			<s:hidden id="effectType" name="effectType"></s:hidden>
			<s:hidden id="ncode" name="groupNcodePO.ncode"></s:hidden>
			<s:hidden id="ncode" name="groupNcodePO.ncodeName"></s:hidden>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box ring_tone m0auto">
					<ul class="curr_meal clearfix">
						<li style="margin-left:30px;"><span>套餐名称：</span><span class="charge_name"><s:property value="groupNcodePO.ncodeName" />业务</span></li>
						<li style="margin-left:30px;">
							<span class="charge_name2">生效方式：</span>
							<span class="charge_name">
								<s:if test="1 == effectType">
		    						立即生效
		    					</s:if>
		    					<s:elseif test="3 == effectType">
		    						下月生效
		    					</s:elseif>
	    					</span>
						</li>
					</ul>
    				<ul class="ring_detail">
	                    <li class="clearfix li1">
	       					<p class="fl cap">业务介绍：</p>
							<div class="fl ring_info">
								<table width="600" border="0">
									<tr>
										<td width="600" >
											<s:property value="groupNcodePO.ncodeDesc" />
										</td>
									</tr>
								</table>
							 </div>
	      				</li>
						<li class="clearfix li2">
					       <p class="fl cap">温馨提示：</p>
					       <div class="fl ring_info">
					           <p><s:property value="groupNcodePO.tips" /></p>
					       </div>						      
						</li>						
                    </ul>
                    <div class="btn_box tc">
                    	<span class=" mr10 inline_block ">
                    		<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';commitPrivNcode();">
								确定
							</a>
						</span>
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';goback('<%=curMenuId %>');">返回</a></span>
    				</div>
    			</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
		<script type="text/javascript">
		var submitFlag = 0;
		
		document.onkeyup = pwdKeyboardUp;

		function pwdKeyboardUp(e)
		{
			var key = GetKeyCode(e);
			
			//82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />") ;
	   			return ;
			}
			// 确认键
			if(key == 89)
			{
				commitPrivNcode();
			}
			// 清除键
			else if(key == 77)
			{
				goback('');
			}

		}
		
		<%-- 返回上一页--%>
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading();
				document.forms[0].action = "${sessionScope.basePath }privilege/privilege.action?" + "<s:property value='groupid' />";
				document.forms[0].submit();
			}
		}
		
		<%-- 提交办理--%>
		function commitPrivNcode()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading();
				document.forms[0].action = "${sessionScope.basePath }privilege/commitPrivNcode.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>