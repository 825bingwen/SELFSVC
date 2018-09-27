<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
//取消绑定时的提示信息
String cancelTip = (String) PublicCache.getInstance().getCachedData("SH_EASYPAY_CANCEL");

if(null == cancelTip || "".equals(cancelTip))
{
	cancelTip = "若您已开通自动交费功能，则取消银行卡绑定后自动交费功能会自动关闭。请确认是否要取消绑定";
}

//设置自动交费、设置副号码的成功提示信息
String successMsg = (String)request.getAttribute("successMsg");

if (null == successMsg)
{
	successMsg = "";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript">
		
		//防止页面重复提交
		var submitFlag = 0;
		
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
		
			//82:R 220:返回
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />");
				return;
			}
		}
		
		/**
		 * 取消银行卡绑定
		 */
		function cancelBind()
		{
			if (submitFlag == 0)
			{
				//点击后 取消绑定按钮置为不可用
				document.getElementById("cancelBtn").disabled = true;
				
				//弹出等待框
				openRecWaitLoading();
				
				submitFlag = 1;
				document.actform.action = "${sessionScope.basePath }bindBankCard/cancelBind.action";
				document.actform.submit();
			}		
		}
		
		/**
		 * 删除选中的副号码
		 */
		function deleteViceNum(confirm)
		{
			var fhm_li=document.getElementById("fhm").getElementsByTagName("li");
			
			//遍历li节点，取出选中的号码
			for(var i=0;i<fhm_li.length;i++)
			{
				//已选中的号码
				if(fhm_li[i].className =='fl fhm_li_on')
				{
					var viceNumber = fhm_li[i].childNodes[0].childNodes[1].firstChild.nodeValue;
					
					//设置选中的副号码串，格式：13963478598~13963478598~13963478598
					if(getValue("viceNumber") == "")
					{
						setValue("viceNumber", viceNumber);
					}
					else
					{
						setValue("viceNumber", getValue("viceNumber") + "~" + viceNumber);
					}
					
				}
			}
			
			//没选中号码时提示信息
			if(getValue("viceNumber") == "")
			{
				alertRedErrorMsg("请点击选中要删除的副号码，可以选择多个要删除的副号码");
				return;
			}
			
			
			//删除确认框
			if(confirm == "1")
			{
				//删除确认框
				openEasyPayConfirm("delete_confirm");
				
				//防止重复设值
				setValue("viceNumber","");
				return;
			}
			
			//删除副号码提交
			if (submitFlag == 0)
			{
				//弹出等待框
				openRecWaitLoading();
				
				//删除，操作类型为2
				setValue("operType","2");
				
				submitFlag = 1;
				document.actform.action = "${sessionScope.basePath }bindBankCard/viceNumberMng.action";
				document.actform.submit();
			}
			
		}
		
		/**
		 * 自动交费设置确认
		 */
		function autoFeeMngConfirm()
		{
			var oldTrigamt = getValue("oldTrigamt");
			var oldDrawamt = getValue("oldDrawamt");
			var trigamt = getValue("trigamt");
			var drawamt = getValue("drawamt");
			
			//若预付费用户，且已开通自动交费
			if(oldTrigamt == trigamt && oldDrawamt == drawamt)
			{
				alertRedErrorMsg("自动交费的触发余额和划扣金额没有变化，无需修改");
				return;
			}
			
			if (oldTrigamt != trigamt && oldDrawamt == drawamt)
			{
				getObj("autoFeeMng_confirm_msg").innerHTML = "您确定要将自动交费功能的触发余额从<span class='yellow'>"+
					oldTrigamt/100+"元</span>修改为<span class='yellow'>"+trigamt/100+"元</span>吗";
			}
			
			if(oldTrigamt == trigamt && oldDrawamt != drawamt)
			{
				getObj("autoFeeMng_confirm_msg").innerHTML = "您确定要将自动交费功能的划扣金额从<span class='yellow'>"+
					oldDrawamt/100+"元</span>修改为<span class='yellow'>"+drawamt/100+"元</span>吗";
			}
			
			if(oldTrigamt != trigamt && oldDrawamt != drawamt)
			{
				getObj("autoFeeMng_confirm_msg").innerHTML = "您确定要将自动交费功能的触发余额从<span class='yellow'>"+
				oldTrigamt/100+"元</span>修改为<span class='yellow'>"+
				trigamt/100+"元</span>，将划扣金额从<span class='yellow'>"+
				oldDrawamt/100+"元</span>修改为<span class='yellow'>"+drawamt/100+"元</span>吗";
			}
			
			//修改确认框
			openEasyPayConfirm("autoFeeMng_confirm");
		}
		
		/**
		 * 自动交费设置提交
		 */
		function autoFeeMngSub()
		{
			if (submitFlag == 0)
			{
				//弹出等待框
				openRecWaitLoading();
			
				document.getElementById("autoFeeBtn").disabled = true;
			
				submitFlag = 1;
				document.actform.action = "${sessionScope.basePath }bindBankCard/setAutoFee.action";
				document.actform.submit();
			}
		}
		
		//切换自动充值的最低触发余额
		function showSelectTrigamt(m,n)
		{
			document.getElementById('chargeDiv').style.display="none";
			if(n==0)
			{
				if(document.getElementById('balanceDiv').style.display=='')
			    {
			    	document.getElementById('balanceDiv').style.display="none";
			    }
			    else
			    {
			    	document.getElementById('balanceDiv').style.display="";
			    }
		    }
		    else
		    {
		    	document.getElementById('balanceDiv').style.display="none";
	    	
		    	// 选择的id
		        var M = m + n;
		        var a = document.getElementById(M).innerHTML;
		        document.getElementById(m+'0').innerHTML=a;
		        setValue("trigamt",n);
		    }
		}
		
		//切换自动充值的最低充值金额
		function showSelectDrawamt(m,n)
		{
			document.getElementById('balanceDiv').style.display="none";
			
			if(n==0)
			{
				if(document.getElementById('chargeDiv').style.display=='')
			    {
			    	document.getElementById('chargeDiv').style.display="none";
			    }
			    else
			    {
			    	document.getElementById('chargeDiv').style.display="";
			    }
		    }
		    else
		    {
		    	document.getElementById('chargeDiv').style.display="none";
	    	
		    	// 选择的id
		        var M = m + n;
		        var a = document.getElementById(M).innerHTML;
		        document.getElementById(m+'0').innerHTML=a;
		        setValue("drawamt",n);
		    }
		}
		
		/**
		 * 添加副号码
		 */
		function addViceNumber()
		{
			if (submitFlag == 0)
			{
				//弹出等待框
				openRecWaitLoading();
			
				//document.getElementById("addBtn").disabled = true;
			
				submitFlag = 1;
				document.actform.action = "${sessionScope.basePath }bindBankCard/inputViceNum.action";
				document.actform.submit();
			}
		}
	    
	    //易充值确认提示框
	    openEasyPayConfirm = function(id)
	    {
	   	    wiWindow = new OpenWindow(id,708,392);
	    }
	</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			
			<%--副号码管理操作类型 新增(操作类型：1)、删除(操作类型：2)--%>
			<s:hidden id="operType" name="bankCardInfoPO.operType"/>
			
			<%--用户付费类型--%>
			<s:hidden name="bankCardInfoPO.payType" value="%{bankCardInfoPO.payType}"/>
			
			<%--副号码列表--%>
			<s:hidden name="bankCardInfoPO.viceNumber" id="viceNumber" value=""/>
			
			<%-- 用户已绑定的副号码 --%>
			<s:hidden name="oldViceNumber" value="%{oldViceNumber}"/>
			
			<%-- 用户是否开通已自动交费 --%>
			<s:hidden name="bankCardInfoPO.autoStatus" value="%{bankCardInfoPO.autoStatus}"/>
			
			<%-- 用户已绑定的银行卡后四位 --%>
			<s:hidden name="bankCardInfoPO.bankCardNum" value="%{bankCardInfoPO.bankCardNum}"/>
			
			<%--预付费用户自动充值设置--%>
			<s:if test="1 == bankCardInfoPO.payType">
				<s:if test="0 == bankCardInfoPO.autoStatus">
					<!-- 本次修改的自动交费金额 -->
					<s:hidden name="bankCardInfoPO.trigamt" value="%{bankCardInfoPO.trigamt}" id="trigamt"/>
					<s:hidden name="bankCardInfoPO.drawamt" value="%{bankCardInfoPO.drawamt}" id="drawamt"/>
					<s:hidden value="%{bankCardInfoPO.trigamt}" id="oldTrigamt"/>
					<s:hidden value="%{bankCardInfoPO.drawamt}" id="oldDrawamt"/>
				</s:if>
				<s:else>
					<s:hidden name="bankCardInfoPO.trigamt" value="%{balanceList[0].dictid}" id="trigamt"/>
					<s:hidden name="bankCardInfoPO.drawamt" value="%{chargeList[0].dictid}" id="drawamt"/>
				</s:else>
			</s:if>
			
			<%--用户已绑定的副号码列表--%>
			<%@ include file="/titleinc.jsp"%>
			<div class="main ycz" id="main">
				<%@ include file="/customer.jsp"%>
			  <div class="b966 hidden">
			    <div class="blank30"></div>
			    <div class="w500 fl">
			    <div class=" pl30 bor_r">
			    <p class=" tit_info">
					<span class="bg"></span>易充值签约
				</p>
			      <!--<div class="blank20"></div>-->
			      
			    <p class="fs18 mt20 phone_num_list">
			      	<span class="yellow fs22 fl lh48 pt10">扣款账户：</span>
			      	<input type="text" id="numBoardText" class="text1 fl" value="银行卡尾号 <s:property value='bankCardInfoPO.bankCardNum'/>"/>
			        <a href="javascript:void(0)" id="cancelBtn" class="btn_104 ml10 fl mt5" onclick="openEasyPayConfirm('cancelBind_confirm');return false;" style="margin-top:7px;">取消绑定</a>
				</p>
				<p class="fs18 mt20">
					<span class="fs18 yellow_tips_1 pl62 ml50">更换扣款账户，需先取消，再重新绑定</span>
				</p>
				
				<%--后付费用户自动充值设置--%>
				<s:if test="0 == bankCardInfoPO.payType">
					<p class="fs18 mt20">
					  <p class="fs22 mt10">
					  	<span class="yellow">自动充值：</span>
					  	
						<%--已开通自动充值--%>
						<s:if test="0 == bankCardInfoPO.autoStatus">
							<a class="btn_104" style="background:none;_filter: _background:none;">已开通</a>
						</s:if>
						
						<%--未开通自动充值--%>
						<s:else>
							<a href="javascript:void(0)" class="btn_104" id="autoFeeBtn" onclick="autoFeeMngSub();return false;" onmousedown="this.className='btn_104_hover'" onmouseup="this.className='btn_104'">开通</a>
						</s:else>
					  </p>
					</p>
			    </s:if>
			    
			    <%--预付费用户自动充值设置--%>
			    <s:else>
			    	<div class="fs18 mt20 phone_num_list" >
				      	<span class="yellow fs22 fl lh48 mt40">自动交费：</span>
				        <div class="bg_gray fl" style="height: 130px;background: ;">
				        	<div class="phone_num_list selectInput_list fl">
								<div class="fs20 textSelect clearfix ">
								
									<%--用户触发金额--%>
									<div class="text1 resultSelect fl" id="trigamt0" onClick="showSelectTrigamt('trigamt','0')">
										<%--用户已开通自动交费--%>
										<s:if test="0 == bankCardInfoPO.autoStatus">
											<s:iterator value="balanceList" id="bl">
												<s:if test="#bl.dictid == bankCardInfoPO.trigamt">
													<s:property value='dictname' />
												</s:if>
											</s:iterator>
										</s:if>
										<%--用户未开通自动交费--%>
										<s:else>
											<s:property value='balanceList[0].dictname' />
										</s:else>
									</div>
									<div class="textSelect_con" style="right: 20px;" id="balanceDiv" style="display: none">
										<%-- 触发金额字典项--%>
										<s:iterator value="balanceList">
											<span id="trigamt<s:property value='dictid' />" onClick="showSelectTrigamt('trigamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
										</s:iterator>
									</div>
									<div class="blank5"></div>
									
									<%--用户划扣金额--%>
									<div class="text1 resultSelect fl" id="drawamt0" onClick="showSelectDrawamt('trigamt','0')">
										<%-- 用户已开通自动交费功能--%>
										<s:if test="0 == bankCardInfoPO.autoStatus">
											<s:iterator value="chargeList" id="cl">
												<s:if test="#cl.dictid == bankCardInfoPO.drawamt">
													<s:property value='dictname' />
												</s:if>
											</s:iterator>
										</s:if>
										<%-- 用户未开通自动交费功能--%>
										<s:else>
											<s:property value='chargeList[0].dictname' />
										</s:else>
									</div>
									<div class="textSelect_con" style="right: 20px; top:120px;" id="chargeDiv" style="display: none">
										<%--划扣金额字典项--%>
										<s:iterator value="chargeList">
											<span id="drawamt<s:property value='dictid' />" onClick="showSelectDrawamt('drawamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
										</s:iterator>
									</div>
				        		</div>
				        	</div>
				        </div>
				        
				        <%--已开通自动充值，按钮为修改--%>
						<s:if test="0 == bankCardInfoPO.autoStatus">
							<a href="javascript:void(0)" id="autoFeeBtn" onclick="autoFeeMngConfirm();return false;" class="btn_104 fl mt40" onmousedown="this.className='btn_104_hover fl mt40'" onmouseup="this.className='btn_104 fl mt40'">修改</a>
						</s:if>
						<%--未开通自动充值，按钮为开通--%>
						<s:else>
							<a href="javascript:void(0)" id="autoFeeBtn" onclick="autoFeeMngSub();return false;" class="btn_104 fl mt40" onmousedown="this.className='btn_104_hover fl mt40'" onmouseup="this.className='btn_104 fl mt40'">开通</a>
						</s:else>
					</div>
			    </s:else>
			    </div>
			    </div>
			    
			    <div class="w500_r fl">
			    	<p class="fs22 mt30 bor_b pb_10">
			    	
			    	<%-- 用户未绑定副号码时，隐藏删除按钮 --%>
			    	<s:if test="viceNumList == null || viceNumList.size == 0">
			    		<a href="javascript:void(0)" onclick="addViceNumber();return false;" class="btn_104" onmousedown="this.className='btn_104_hover'" onmouseup="this.className='btn_104'">添加副号</a><span class="yellow_tips_1 ml10">最多添加10个副号</span></p>
			    	</s:if>
			    	
			    	<%-- 用户已绑定10个副号码时，隐藏添加按钮 --%>
			    	<s:if test="viceNumList != null && viceNumList.size == 10">
			    		<a href="javascript:void(0)" onclick="deleteViceNum('1');return false;" id="del_fh" class="btn_104 ml10" onmousedown="this.className='btn_104_hover ml10'" onmouseup="this.className='btn_104 ml10'">删除副号</a><span class="yellow_tips_1 ml10">最多添加10个副号</span></p>
			    	</s:if>
			    	
			    	<%-- 用户已绑定1-9个副号码时，添加删除按钮都显示 --%>
			    	<s:if test="viceNumList != null && viceNumList.size > 0 && 10 > viceNumList.size">
			    		<a href="javascript:void(0)" onclick="addViceNumber();return false;" class="btn_104" onmousedown="this.className='btn_104_hover'" onmouseup="this.className='btn_104'">添加副号</a><a href="javascript:void(0)" onclick="deleteViceNum('1');return false;" id="del_fh" class="btn_104 ml10" onmousedown="this.className='btn_104_hover ml10'" onmouseup="this.className='btn_104 ml10'">删除副号</a><span class="yellow_tips_1 ml10">最多添加10个副号</span></p>
			    	</s:if>
			    	
			        <ul class="fl pt10 bor_t pos_rel" id="fhm">
			        	<div id="fhmDom" style="height:200px;overflow: hidden;">
			        	
			        		<%-- 用户未添加副号码 --%>
				        	<s:if test="viceNumList == null || viceNumList.size == 0">
				        		<span class="yellow_tips_1 ml10">您还未添加副号码</span>
				        	</s:if>
				        	<s:else>
					        	<%-- 遍历副号码 --%>
					        	<s:iterator value="viceNumList" status="st">
						        	<li class="fl">
						            	<div class="fs22 fl">
						            	    <%--判断号码索引，1-9加空格，与副号码10对齐--%>
							            	<s:if test="#st.index > 8">
							            		副号<s:property value="#st.index+1" />：
							            	</s:if>
							            	<s:else>
							            		副号<s:property value="#st.index+1" />&nbsp;&nbsp;：
							            	</s:else>
							            	<span class="yellow"><s:property value="viceNumber"/></span>
						            	</div>
						            </li>
					        	</s:iterator>
				        	</s:else>
			        	</div>
			        	<%--副号码滚动条--%>
			        	<div class="box70W pos_abs" style="z-index: 9999; right: -2px; top:0px;">
							<input type="button" class="btnUp" onclick="myScrollDialog.moveUp(30)" />
							<div class="div75w350h" style="height:105px;">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="viceMoveDom" style="top: 52px; left: 2px; height: 30px">
									0%
								</div>
							</div>
							<input type="button" class="btnDown" onclick="myScrollDialog.moveDown(30)" />
						</div>
			        </ul>
			       
			    </div>
			    
			    <div class="i_tips ml20 fl" style="margin-top:-10px">
			    	<p class="tit_arrow mt10">
			    		<span class=" bg"></span>温馨提示：<br />
			            <p class="tit_arrow_hide pl40">
			            	<s:if test="null != easyPayTipList && easyPayTipList.size > 0">
			            		<s:iterator value="easyPayTipList">
			            			<%-- html标签转义--%>
			            			<s:property value="description" escape="false"/>
			            		</s:iterator>
			            	</s:if>
			            	<s:else>
				            	1、预付费客户余额到达或低于设定的"触发余额"后，系统参照设置的划扣金额自动从已绑定账户中扣款，为客户交费并短信通知客户<br />
				
								2、后付费客户，每月出账完成后，系统按客户的出账金额自动从已绑定账户中扣款，为客户交费，并短信通知客户<br />
				
								3、可设置本省本地或异地移动号码为副号，不能设置外省号码为副号<br />
				
								4、主号码可发送"金额数字"+"#"+"副号" 至 10086515为副号交费（副号不可直接发送短信交费），系统将在主号码已绑定账户中扣款，为副号完成交费
			            	</s:else>
						</p>
			   </div>
			    
			  </div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
		
		<%--解除绑定提示框--%>
		<div class="popup_confirm" id="cancelBind_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p><%=cancelTip%></p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="javascript:void(0);"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="cancelBind();return false;">取消绑定</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();">关闭</a>
				</span>
			</div>
		</div>
		
		<%--删除副号码提示框--%>
		<div class="popup_confirm" id="delete_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p>请确认是否要删除选中的副号码</p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="javascript:void(0);"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="deleteViceNum('0');return false;">确定</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
				</span>
			</div>
		</div>
		
		<%--修改自动交费设置提示框--%>
		<div class="popup_confirm" id="autoFeeMng_confirm">
			<div class="bg"></div>
			<div class="tips_title">
				提示：
			</div>
			<div class="tips_body">
				<div class="blank30"></div>
				<p id="autoFeeMng_confirm_msg">请确认是否修改自动交费的触发余额和</p>
				<div class="blank30"></div>
			</div>
			<div class="btn_box tc mt20">
				<span class=" mr10 inline_block "><a href="javascript:void(0);"
					class="btn_bg_146" onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();" onclick="autoFeeMngSub();return false;">确定</a>
				</span>
				<span class=" inline_block "><a class="btn_bg_146" href="#"
					onmousedown="this.className='key_down'"
					onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
				</span>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	
	myScrollDialog = new virtualScroll("fhmDom", "viceMoveDom");

	function goback(menuid)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
	
			document.getElementById("curMenuId").value = menuid;
			document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
			document.forms[0].submit();
		}
	}
	
	var fhm_li=document.getElementById("fhm").getElementsByTagName("li");
	for(var i=0;i<fhm_li.length;i++)
	{
		fhm_li[i].onclick=function()
		{
			var span=document.createElement("span");
			span.innerHTML="[√]";
			span.style.fontSize="30px";
			if(this.className == 'fl')
			{
					this.className+=' fhm_li_on';
					this.appendChild(span);
			}
			else if(this.className != 'fl')
			{
					this.className=this.className.replace(" fhm_li_on", "");
					var op=this.getElementsByTagName("span")[1];
         			op.parentNode.removeChild(op);
			}
		}	
	};
	
	//预付费用户
	if("<s:property value='bankCardInfoPO.payType'/>" == "1")
	{
		//防止在字典项查不到触发余额数据时，下拉框空白
	   	if (getObj("trigamt0").innerHTML == "")
	   	{
	   		getObj("trigamt0").innerHTML = "<s:property value='balanceList[0].dictname' />";
	   	}
	   	
	   	//防止在字典项查不到划扣金额数据时，下拉框空白
	   	if (getObj("drawamt0").innerHTML == "")
	   	{
	   		getObj("drawamt0").innerHTML = "<s:property value='chargeList[0].dictname' />";
	   	}
	}
   	
   	//修改自动缴费，操作副号码成功后的提示信息
  	if ("" != "<%=successMsg %>")
	{		
		alertSuccessMsg("<%=successMsg %>");
	}
</script>


</html>
