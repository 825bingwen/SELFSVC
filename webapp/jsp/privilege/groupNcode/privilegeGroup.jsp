<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="Cache-Control" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<link href="${sessionScope.basePath }css/reset.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css?ver=${jsVersion}" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/scriptNew.js?ver=${jsVersion}"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js?ver=${jsVersion}"></script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%-- ��ID --%>
			<s:hidden id="groupid" name="groupid"></s:hidden>
			
			<%-- �û������ײ� --%>
			<s:hidden id="currNcode" name="custNcodeInfo.currNcode"></s:hidden>
			<s:hidden id="currNcodeName" name="custNcodeInfo.currNcodeName"></s:hidden>
			
			<%-- �û������ײ� --%>
			<s:hidden id="nextNcode" name="custNcodeInfo.nextNcode"></s:hidden>
			<s:hidden id="nextNcodeName" name="custNcodeInfo.nextNcodeName"></s:hidden>
			
			<%-- ��������--%>
			<s:hidden id="operType" name="operType" value=""></s:hidden>
			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="b966">
					<div class="">
						<div style="height: 70px; padding-left: 70px; padding-top: 40px; padding-bottom: 10px;">
							<div class="duanxin_bg">
								<p>
									<span>�����ײͣ�</span><span class="duanxin_sp" id="currName">
								    	<s:if test="custNcodeInfo.currNcodeName != null && custNcodeInfo.currNcodeName != ''">
								    		<s:property value="custNcodeInfo.currNcodeName"/>
								    	</s:if>
									    <s:elseif test="custNcodeInfo.currNcode != null && custNcodeInfo.currNcode != ''" >
									    	<s:property value="custNcodeInfo.currNcode"/>
									    </s:elseif>
									    <s:else>
									    	δ��ͨ
									    </s:else>
									</span>
								</p>
							</div>
							<div class="duanxin_bg2">
								<p>
									<span>�����ײͣ�</span>
									<span class="duanxin_sp" id="nextName">
								    	<s:if test="custNcodeInfo.nextNcodeName != null && custNcodeInfo.nextNcodeName != ''" >
								    		<s:property value="custNcodeInfo.nextNcodeName"/>
									    </s:if>
								    	<s:elseif test="custNcodeInfo.nextNcode != null && custNcodeInfo.nextNcode != ''" >
								    		<s:property value="custNcodeInfo.nextNcode"/>
									    </s:elseif>
									    <s:else>
									    	δ��ͨ
									    </s:else>
									</span>
								</p>
							</div>
						</div>
						<div class="duanxin_bg3" id="serviceList">
							<p class="hot_service" style="margin: 0; padding: 10px;">
								��ѡҵ��
							</p>
							<div class="bg"></div>
							<div class="top"></div>
							<div class="con">
								<div class="box747W fl" style="position:relative;">
									<!-- ҵ������ -->
									<div id="inns" class="duanxin_dqt">
										<div class="duanxin_dqt1"></div>
										<s:iterator value="custNcodeInfo.groupNcodeList" id="groupNcodeInfo" status="st">
											<div class="duanxin_bg5">
												<p>
													<s:property value="#groupNcodeInfo.ncodeName" />
												</p>
											</div>
											<s:if test="custNcodeInfo.nextNcode == #groupNcodeInfo.ncode" >
												<a href="javascript:void(0)" class="b_bt2on11"  
													onclick="choosePrivlige('<s:property value='#st.index' />','<s:property value="#groupNcodeInfo.ncode" />','<s:property value="#groupNcodeInfo.ncodeName" />','<s:property value="#groupNcodeInfo.fee" />'); return false;">
													<span class="duanxin_sp2">ѡ��</span> 
												</a>
				    						</s:if>
				    						<s:else>
												<a href="javascript:void(0)" class="b_bt21" 
													onclick="choosePrivlige('<s:property value='#st.index' />','<s:property value="#groupNcodeInfo.ncode" />','<s:property value="#groupNcodeInfo.ncodeName" />','<s:property value="#groupNcodeInfo.fee" />'); return false;">
													<span class="duanxin_sp2">ѡ��</span> 
												</a>
											</s:else>
										</s:iterator>
									</div>
									<!-- �б����� -->
								</div>
								<div class=" fl ml20 relative">
									<div class="box70W fr">
										<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
										<div class="boxPage" style="width: 75px; height: 60px;">
											<div class="box66W tc f16 lh30" id="gunDom"
												style="width: 66px; height: 36px; position: absolute; cursor: move; top: 52px; line-height: 36px">
												0%
											</div>
										</div>
										<input type="button" class="btnDown" onclick="myScroll.moveDown(30)" />
									</div>
								</div>
								<div class="clear"></div>
							</div>
							<div class="btm"></div>
						</div>
						<script type="text/javascript">
							myScroll = new virtualScroll("inns","gunDom");	
                		</script>
					</div>
					<div class="duanxin_gn">
						<div class="duanxin_cz">
							<p>
								��������:
							</p>
						</div>
						<div class="num_qued">
							 <a href="javascript:void(0);selectOperType('ADD');" id="ADD" style="position:relative;">
								 <span class="duanxin_czp">��ͨ</span></a>
							</a>
							<a href="javascript:void(0);selectOperType('DEL');" id="DEL" style="position:relative;">
								 <span class="duanxin_czp">ȡ��</span></a>
							</a>
						</div>

						<div class="duanxin_ljsx">��Ч��ʽ��
							<span class="duanxin_gnsp">
								<s:if test="custNcodeInfo.currNcode != null && custNcodeInfo.currNcode != ''" >
			  						������Ч
			  						<s:hidden name="effectType" id="effectType" value="3"></s:hidden>
				  				</s:if>
				  				<s:else>
				  					������Ч
				  					<s:hidden name="effectType" id="effectType" value="1"></s:hidden>
				  				</s:else>
							</span>
						</div>
						<div class="duanxin_gn1" style="position:absolute;">
							<span>���ܷѣ�</span>
							<span class="duanxin_gnsp" id="nextNcodeFee"></span>
						</div>
					</div>
					<div class="btn_box tc " style="margin-top:20px;">
				    	<span class=" mr10 inline_block ">
 							<a href="javascript:void(0);" class="btn_bg_146" onmousedown="this.className='key_down'" 
 							onmouseup="this.className='btn_bg_146';" onclick="confirmPrivilege(); return false;">��������</a>
  						</span>
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
			
			//82:R 220:����
			if (key == 82 || key == 220)
			{
				goback("<s:property value='curMenuId' />") ;
		  		return ;
			}
			// ȷ�ϼ�
			if(key == 89)
			{
				confirmPrivilege();
			}
			// �����
			else if(key == 77)
			{
				topGo('rec', 'reception/receptionFunc.action');
			}
		}
		
		<%-- ��ʼ��������������ײͣ�Ĭ��ѡ�������ײ�ȡ������������Ĭ��ѡ���һ����ͨ --%>
		<s:if test="custNcodeInfo.nextNcodeName != null && custNcodeInfo.nextNcodeName != ''" >
			selectOperType('DEL');
			getObj("nextNcodeFee").innerHTML = "�� " + "<s:property value='custNcodeInfo.nextNcodeFee'/>" + "Ԫ" ;
		</s:if>
		<s:else>
			var chooseBtnList = getObj("inns").getElementsByTagName("a");
			if(chooseBtnList.length > 0)
			{
				chooseBtnList[0].onclick();
			}
		</s:else>
		
		<%-- ѡ��ͬ��ҵ�� --%>
		function choosePrivlige(j,ncode, ncodeName, fee)
		{
			<%-- ����Ҫ�����ҵ����Ϣ --%>
			setValue("nextNcode",ncode);
			setValue("nextNcodeName",ncodeName);
			getObj("nextNcodeFee").innerHTML = "�� " + fee + "Ԫ" ;
			
			<%-- ����Ҫ�����ҵ����Ϣ��ʽ --%>
			var chooseBtnList = document.getElementById("inns").getElementsByTagName("a");
			for (i = 0; i < chooseBtnList.length; i++)
			{    
				chooseBtnList[i].className = "b_bt21";
			}
			chooseBtnList[j].className = "b_bt2on11";
			
			<%-- ѡ���ҵ�����û������ײ���ͬ��Ĭ�ϲ�������ȡ��������ͨ --%>
			if(ncode == '<s:property value="custNcodeInfo.nextNcode"/>')
			{
				selectOperType('DEL');
			}
			else
			{
				selectOperType('ADD');
			}
			
		}
		
		<%-- ѡ��������� --%>
		function selectOperType(operType)
		{
			<%-- ���ò������� --%>
			setValue("operType",operType);
			
			<%-- ���ò������Ͱ�ť --%>
			getObj("ADD").className='';
			getObj("DEL").className='';
			getObj(operType).className='on';
		}
		
		<%-- ȷ�ϰ��� --%>
		function confirmPrivilege()
		{
			if (getValue("nextNcode") == "")
			{
				alertRedErrorMsg("��ѡ��Ҫ������ײ�");
				return;
			}
			
			if (getValue("operType") == "")
			{
				alertRedErrorMsg("��ѡ�������ʽ");
				return;
			}
			
			if (submitFlag == 0)
			{
				submitFlag = 1;
				openRecWaitLoading();
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }privilege/confirmPrivInfo.action";
				document.forms[0].submit();
			}
		}
		
		<%-- ������һҳ --%>
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				openRecWaitLoading();
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
		}
		</script>
	</body>
</html>