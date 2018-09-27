<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.cache.PublicCache" %>

<%
String errorMessage = (String) request.getAttribute("errormessage");
String exception_message = (String) request.getAttribute("exception.message");
String backStep = (String) request.getAttribute("backStep");

String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);

// �޸��Զ��������õĵ�������ʾ��Ϣ
String autoChargeTips = (String) PublicCache.getInstance().getCachedData(Constants.SH_AUTOCHARGEHB_TIPS);

if(null == autoChargeTips || "" == autoChargeTips)
{
	autoChargeTips = "��ȷ��Ҫ�޸��Զ����ѵ�������";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
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
//��ֹҳ���ظ��ύ
var submitFlag = 0;

document.onkeydown = pwdKeyboardDown;
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardDown(e)
{	
	var key = GetKeyCode(e);
	
	//����
	if (key == 77) 
	{
		preventEvent(e);
	}
	
	if (!KeyIsNumber(key))
	{
		return false;//��仰��ؼ�
	}
}

function KeyIsNumber(KeyCode) 
{
	//ֻ��������0-9
	if (KeyCode >= 48 && KeyCode <= 57)
	{
		return true;
	}
	
	return false;
}

function pwdKeyboardUp(e)
{
	var key = GetKeyCode(e);
	
	<%
	if (Constants.PROOPERORGID_NX.equalsIgnoreCase(pageProvince))
	{
	%>
		//82:R �˳�
		if (key == 82 || key == 220)
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:����
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
	<%	
	} 
	%>
}
function doSub()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }bindBankCard/unsignHeBao.action";
		document.actform.submit();
	}		
}


</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<!-- ������ֵ -->
			<s:hidden id="trigamt" name="bankCardInfoPO.trigamt" value="1000" />
			<!-- �Զ����۽�� -->
			<s:hidden id="drawamt" name="bankCardInfoPO.drawamt" value="10" />
			<!-- ���б��� -->
			<s:hidden id="bankId" name="bankCardInfoPO.bankId" />
			<!-- ���֧��Э��� -->
			<s:hidden id="agrNo" name="bindBankCardPO.AGRNO" />
			<!-- ������ˮ�� -->
			<s:hidden id="tradeNo" name="bindBankCardPO.appFlowCode" />
			<!-- ��֤������ -->
			<s:hidden id="smsType" name="smsType" value="3" />
			<!-- �Զ������������� ��ѯ0,������1,ɾ����2,�޸ģ�3 -->
			<s:hidden id="oprType" name="oprType" value="3" />
			<!-- ֤���������� -->
			<input type="hidden" id="idCardType" name="bindBankCardPO.idCardType" value="00"/>
			<!-- ֤������ -->
			<input type="hidden" id="idCardNum" name="bindBankCardPO.idCardNum" value="<s:property value='bindBankCardPO.idCardNum' />"/>
			<!-- ���п����� -->
			<s:hidden name="bindBankCardPO.bankAbbr" id="bankAbbr" />
			<!-- ���� -->
			<input type="hidden" id="userFactName" name="bindBankCardPO.userFactName" value="<s:property value='bindBankCardPO.userFactName' />"/>
			<!-- ��Ƭ���� 0:��ǿ� 1:���ǿ� -->
			<input type="hidden" id="bankCardType" name="bindBankCardPO.bankCardType" value="<s:property value='bindBankCardPO.bankCardType' />"/>
			<!-- ��ȡ��¼ҳ����������п����� -->
			<input type="hidden" id="cardNo" name="cardNo" value="<s:property value='cardNo' />" />
			
			<%@ include file="/titleinc.jsp"%>
			<div class="main ycz" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="b966 hidden">
					<div class="blank30"></div>
					<div class=" p40">
						<p class="tit_info" align="left"><span class="bg"></span>�׳�ֵǩԼ����</p>

						<!--<div class="blank20"></div>-->
						<p class="fs18 mt20 clearfix">
							<span class="yellow fs22 fl lh48 pt10">�ۿ��˻���</span>
							<input type="text" id="numBoardText" class="text2 fl" readOnly="true" value="���п�β�� <s:property value='bindBankCardPO.bankCardNum' />" />
							<a href="javascript:void(0)" class="btn_104 ml20 fl mt5"
								onmousedown="this.className='btn_104_hover ml20 fl mt5'"
								onmouseup="this.className='btn_104 ml20 fl mt5';" onclick="unsign(); return false;" >ȡ����</a>
						</p>
						<p class="fs18 mt20">
							<span class="fs18 yellow_tips_1 pl62 ml50">�����ۿ��˻�������ȡ���������°�</span>
						</p>
						<div class="fs18 mt20">
							<span class="yellow fs22 fl lh48 mt40">�Զ����ѣ�</span>
							<s:if test="1 == payTypeFlag">
								<div class="bg_gray fl" style="height: 130px;">
									<div class="phone_num_list selectInput_list fl ml10">
										<div class="fs20 textSelect clearfix ">
											<div class="text1 resultSelect fl" id="trigamt0" onClick="showSelectTrigamt('trigamt','0')">
												<s:property value='balanceList[0].dictname' />
											</div>
											<div class="textSelect_con" style="right: 20px;" id="balanceDiv" style="display: none">
												<s:iterator value="balanceList">
													<span id="trigamt<s:property value='dictid' />" onClick="showSelectTrigamt('trigamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
												</s:iterator>
											</div>
											<div class="blank10"></div>
											<div class="text1 resultSelect fl" id="drawamt0" onClick="showSelectDrawamt('trigamt','0')">
												<s:property value='chargeList[0].dictname' />
											</div>
											<div class="textSelect_con" style="right: 20px; top:120px;" id="chargeDiv" style="display: none">
												<s:iterator value="chargeList">
													<span id="drawamt<s:property value='dictid' />" onClick="showSelectDrawamt('drawamt',<s:property value='dictid' />)"><s:property value="dictname" /></span>
												</s:iterator>
											</div>
										</div>
									</div>
								</div>
								<a href="javascript:void(0)" id="kt_tips"
									class="btn_104 ml10 fl mt40 fs14" style="font-size: 18px;"
									onmousedown="this.className='btn_104_hover ml10 fl mt40 fs14'"
									onmouseup="this.className='btn_104 ml10 fl mt40 fs14'; openWindow_Auto('popup_confirmAuto'); return false;">�޸�</a>
							</s:if>
							<s:else>
								<div style="margin-top:75px;"><span style="font-size: 18px;">�ѿ�ͨ</span></div>
							</s:else>
						</div>



					</div>
					
					<div class="i_tips ml20 fl">
						<p class="tit_arrow mt10">
							<span class=" bg"></span>��ܰ��ʾ��
							<br />
							<s:if test="HBManageTips != '' ">
								<p class="tit_arrow_hide pl40">
									<s:property value = 'HBManageTips' escape = 'false'/>
								</p>
							</s:if>
							<s:else>
								<p class="tit_arrow_hide pl40">
									1��Ԥ���ѿͻ����������趨��"�������"��ϵͳ�������õĻ��۽���Զ����Ѱ��˻��пۿΪ�ͻ����Ѳ�����֪ͨ�ͻ�
									<br />
									2���󸶷ѿͻ���ÿ�³�����ɺ�ϵͳ���ͻ��ĳ��˽���Զ����Ѱ��˻��пۿΪ�ͻ����ѣ�������֪ͨ�ͻ�
								</p>
							</s:else>
						</p>
					</div>
				</div>
	            
	            <!-- �����Զ���ֵ -->
	            <div class="openwin_tip div708w392h" id="popup_confirmAuto">
	                <div class="bg"></div>
	                <div class=" blank60"></div><div class=" blank60"></div>
	               
	                <div class="  blank10n"></div>
	                <p class="fs30 yellow pt30 tc pt30 pl20"><%=autoChargeTips %></p>
	                <div class=" blank10"></div>
	                <div class="tc">
	                <div class=" blank60"></div>
	                <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();" onclick="setAutoValue(); return false;">ȷ��</a>
	                <a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">ȡ��</a>
	                </div>
	            </div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
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
		
		// modify begin g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
		<%
		if (null != backStep && !"".equals(backStep.trim()))
		{
		%>
			history.go(parseInt("<%=backStep %>"));
		<%	
		}
		else
		{
		%>
			document.getElementById("curMenuId").value = menuid;
				
			//modify begin CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597
			
			// ��Ʒ���ٷ�����־  
			var quickPubFlag = "<s:property value='quickPubFlag' />";
			
			// ��Ʒ��ͨ��ͨ����Ʒ��ͨ�˵����룬���ߴ�����ҵ����룩
			if('1' == quickPubFlag)
			{
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath}" + "<%=menuURL %>";
				document.actform.submit();
			}
			else
			{						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
				document.forms[0].submit();
			}
			//modify end CKF76106 2012/10/12 R003C12L07n01 OR_HUB_201206_597  
		<%
		}
		%>
		// modify end g00140516 2012/05/26 R003C12L05n01 OR_huawei_201201_94 ����ʱ������һҳ��
	}
}

	// �޸��Զ����ѵĵ�������
	openWindow_Auto = function(id)
	{
		divFlag = id;
		wiWindow = new OpenWindow("popup_confirmAuto", 708, 392);// �򿪵������ڵ�����
	}
	
	// �رյ���divʱ�����divFlag��Ϣ
	function windowClose()
	{
		divFlag = "";
		wiWindow.close();
	}
	
	// �Ͱ��׳�ֵ��Լ
	function unsign()
	{
		document.actform.action = "${sessionScope.basePath }bindBankCard/sendHeBaoRandom.action";
		document.actform.submit();
	}
	
	//�л��Զ���ֵ����ʹ������
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
    	
	    	// ѡ���id
	        var M = m + n;
	        var a = document.getElementById(M).innerHTML;
	        document.getElementById(m+'0').innerHTML=a;
	        document.getElementById("trigamt").value = n;
	    }
	}
	
	//�л��Զ���ֵ����ͳ�ֵ���
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
    	
	    	// ѡ���id
	        var M = m + n;
	        var a = document.getElementById(M).innerHTML;
	        document.getElementById(m+'0').innerHTML=a;
	        document.getElementById("drawamt").value = n;
	    }
	}
	
	// �Ͱ��׳�ֵ�Զ���������
	function setAutoValue()
	{
		document.actform.action = "${sessionScope.basePath }bindBankCard/setHeBaoAutoValue.action";
		document.actform.submit();
	}
</script>


</html>
