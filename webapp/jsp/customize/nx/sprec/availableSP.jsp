<%--
 @User: g00140516
 @De: 2012/07/06
 @comment: 宁夏梦网业务订购
 @remark: create g00140516 2012/07/06 R003C12L07n01 OR_NX_201205_649
--%>
<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>梦网业务订购</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script	type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script>
		var submitFlag = 0;
		
		// 弹出div
		var divFlag = "";

		document.onkeydown = pwdKeyboardDown;
		document.onkeyup = pwdKeyboardUp;
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
		
		function pwdKeyboardUp(e)
		{
			var key = GetKeyCode(e);
			//82:R 退出
			if ((key == 82 || key == 220) && divFlag == '')
			{
				window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
	   			return ;
			}
			
			<s:if test="availSPService != null && availSPService.size() > 0">
				<s:iterator value="availSPService" id="spAvail" status="st">
				if (key == <s:property value="#st.index + 49" />)
				{
					if(('1' == doSet('<s:property value="#spAvail.operatorName" escape="false"/>', '1', 
						'<s:property value="#spAvail.domain" escape="false"/>', '<s:property value="#spAvail.spcode" escape="false"/>',
					    '<s:property value="#spAvail.operatorCode" escape="false"/>','<s:property value="#spAvail.servType" escape="false"/>'))
					    && divFlag == '')
					{
						openWindow(popup_confirm);
					}
					return;
				}
			</s:iterator>
			</s:if>
			
			<s:if test="pageCount > 1">
				// 上翻
				if (key == 85 && divFlag == '')
				{
					var page = parseInt('<s:property value="page" />')-1;
					
					if(page>0)
					{
						nextPage("${sessionScope.basePath }nxSPRec/qryAvailableSP.action?page="+page);
					}
	
				}
				// 下翻
				else if (key == 68 && divFlag == '')
				{
					<s:if test="page < pageCount">
						var page = parseInt('<s:property value="page" />')+1;
						nextPage("${sessionScope.basePath }nxSPRec/qryAvailableSP.action?page="+page);
					</s:if>
				}
			</s:if>
			// 确认键
			if(key == 89 && divFlag != '')
			{
				doSub();
			}
			// 清除键
			else if(key == 77 && divFlag != '')
			{
				windowClose();
			}
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				if (document.getElementById("backWaitingFlag").value == "1")
				{
					openRecWaitLoading_NX("recWaitLoading");
				}
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.getElementById("curMenuId").value = menuid;
				
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		
		//弹出确认框
		openWindow = function(id)
		{
			divFlag = id;
			wiWindow = new OpenWindow("popup_confirm", 708, 392);//打开弹出窗口例子
		}
		
		function doSet(spBizName,dealType,domain,spId,spBizCode,bizType)
		{
			document.getElementById("spBizName").value = spBizName;
			document.getElementById("dealType").value = dealType;
			document.getElementById("domain").value = domain;
			document.getElementById("spId").value = spId;
			document.getElementById("spBizCode").value = spBizCode;
			document.getElementById("bizType").value = bizType;	
			
			document.getElementById("operTypeName").innerHTML = spBizName;	
			
			return "1";		
		}
		
		function doSub()
		{
			if (submitFlag == 0) 
			{
				submitFlag = 1;	//提交标记
				
				// add begin g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				openRecWaitLoading_NX("recWaitLoading");
				// add end g00140516 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
				
				document.actform.action="${sessionScope.basePath}nxSPRec/orderSP.action";
				document.actform.submit();
			}
		}
		
		// 下一页
		function nextPage(linkURL)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.actform.target="_self";
				document.actform.action=linkURL;
				document.actform.submit();
			}
		}
		
		// 关闭弹出div时，清空divFlag
		function windowClose()
		{
			divFlag = "";
			
			wiWindow.close();
		}		
		</script>
	</head>
	<body scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="spBizName" id="spBizName" value=""/>
			<input type="hidden" name="dealType" id="dealType" value=""/>
			<input type="hidden" name="domain" id="domain" value=""/>
			<input type="hidden" name="spId" id="spId" value=""/>
			<input type="hidden" name="spBizCode" id="spBizCode" value=""/>
			<input type="hidden" name="bizType" id="bizType" value=""/>
			
			<%@ include file="/titleinc.jsp"%>
				<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
					<div class="b966s">
	          		<div class="p40">
	          			<div class="blank30"></div>
	            		<p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
	            		<div class="blank10"></div>
	            		<div class="relative p40">	            
	            			<div class="p20">
				               <table width="100%" class="tb_blue" >
				                  	<tr>
					                    <th scope="col">业务名称</th>
					                    <th scope="col">计费类型</th>
					                    <th scope="col">费用(元)</th>
					                    <th scope="col">失效日期</th>
					                    <th scope="col">操作</th>
				                  	</tr>
				                  	<s:if test="availSPService != null && availSPService.size() > 0">
					                  	<s:iterator value="availSPService" id="spAvail" status="st">
					                  		<tr>
									           	<td><s:property value="#spAvail.operatorName"/></td>
									            <td><s:property value="#spAvail.billFlag"/></td>
									            <td><s:property value="#spAvail.fee"/></td>
									            <td><s:property value="#spAvail.expireDate"/></td>
									            <td>
								                	<input type="button" class="bt2_liebiao white" value="订购(按<s:property value='#st.index + 1' />键)" onmousedown="this.className='bt2on white'" 
									                    	onmouseup="this.className='bt2 white'; doSet('<s:property value="#spAvail.operatorName"/>', '1', '<s:property value="#spAvail.domain"/>', '<s:property value="#spAvail.spcode"/>', '<s:property value="#spAvail.operatorCode"/>','<s:property value="#spAvail.servType"/>');openWindow(popup_confirm);" />
									            </td>
					                  		</tr>
					                  	</s:iterator>
				                  	</s:if>
				                </table>
	             			</div>	             
						</div>
						
						
	            	</div>
	          	</div>
	          	<div class="blank10"></div>
	          	<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
	          	
		  	</div>
				
			<div class="popup_confirm" id="popup_confirm">
                 <div class="bg"></div>
                 <div class="tips_title">提示：</div>
                 <div class="tips_body">
			    <p>您选择订购： <i id="operTypeName"></i><i>业务</i></p>
			    <div class="blank10"></div>
			    <p class="mt30">确认订购请点击"确认"提交。</p>
			  </div>
                 <div class="btn_box tc mt20">
                  <span class=" mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">确认(按确认键)</a></span>
                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消(按清除键)</a></span>
                 </div>
               </div>
               
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
