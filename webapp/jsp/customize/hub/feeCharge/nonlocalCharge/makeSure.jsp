<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page import="com.gmcc.boss.selfsvc.util.CommonUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String yuanMoney = (String) request.getAttribute("yingjiaoFee");

String fenMoney = CommonUtil.yuanToFen(yuanMoney);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>���п��ɷ�ȷ��</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/SelfPayReadCardManager_hub.js"></script>
	</head>
	<body scroll="no" onload="doLoad();startclock();">
		<form name="actForm" method="post">		
			<input type="hidden" id="payType" name="cardChargeLogVO.payType" value="<%=Constants.PAY_BYCARD %>">
			<s:hidden id="servnumber" name="cardChargeLogVO.servnumber"/>
			<s:hidden id="provinceCode" name="cardChargeLogVO.provinceCode"/>
			<s:hidden id="yingjiaoFee" name="yingjiaoFee"/>
			<s:hidden id="tMoney" name="tMoney"/>
			<s:hidden id="needReturnCard" name="needReturnCard" value='1'/>
			<s:hidden id="cardnumber" name="cardChargeLogVO.cardnumber"/>
			<s:hidden id="terminalSeq" name="cardChargeLogVO.terminalSeq"/>
			<s:hidden id="errorType" name="errorType"/>
			<s:hidden id="errormessage" name="errormessage"/>
			<s:hidden id="chargeLogOID" name="cardChargeLogVO.chargeLogOID"/>
			<s:hidden id="printcontext" name="printcontext" value=""/>
			<s:hidden id="posResCode" name="cardChargeLogVO.posResCode"/>
			<s:hidden id="acceptType" name="cardChargeLogVO.acceptType"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��ֵ��������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)">2. ѡ��֧����ʽ</a>
    					<a href="javascript:void(0)">3. ѡ����</a> 
    					<a href="javascript:void(0)">4. ��������</a>
    					<a href="javascript:void(0)">5. ����������</a> 
    					<a href="javascript:void(0)">6. ��������</a>
    					<a href="javascript:void(0)" class="on">7. �˶���Ϣ</a>
    					<p class="recharge_tips">�˶Խ��Ѻ���</p>
    					<a href="javascript:void(0)">8. ���</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
    					<div class="blank60"></div>
    					<div class="p40">
    						<p class=" tit_info"><span class="bg"></span>�ֻ����룺<span class="yellow"><s:property value="cardChargeLogVO.servnumber" /></span></p>
    						<p class="fs22 fwb pl40 lh30">���ѽ�<span class="yellow fs22"><s:property value="yingjiaoFee" /></span> Ԫ</p>
							<p class="tit_info"><span>�˶���Ϣʱ����</span><span class="yellow">60</span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="60" readonly="readonly" />��</p>
							<div class="blank25"></div>
							<div class="line"></div>
      						<div class="blank60"></div>
      						
      						<div class="recharge_result tc">
      							<div class="btn_box2 clearfix">
      								<a href="javascript:void(0);" style="margin-left:80px;" onclick="openWindow_wait('pls_wait');return false;" onmousedown="this.className='hover'" onmouseup="this.className=''">����</a>
      							</div>
      						</div>				
    					</div>
					</div>
				</div>
				
				<!--������ ���ڴ��� ���Ժ�-->
				<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
					<div class="bg"></div>
                   	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
                  	<p class="tips_txt">���ڴ������Ժ�......</p>
                 	<div class="line"></div>
                  	<div class="popup_banner"></div>                
                </div>

				<script type="text/javascript">
					openWindow_wait = function(id){
						//�����ʱ����
						clearInterval(timeToken);
						
						wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
					    setTimeout("doSub()", 500);
					}				
				</script>
				<!--����������-->
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
	clearTimeout(timeOut);
	
	// ��ʶ�ؼ�ʹ��
	closeStatus = 1;
	
	// �ύ��־ 0:δ�ύ 1:���ύ
	var submitFlag = 0;
	
	// �˶�ʱ��
	var limitTime = 60;
	
	//����ʣ��ʱ��ľ��
	var timeToken;	
	
	//82��220 ����
	document.onkeydown = pwdKeyboardDown;
	
	//���̰���
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
	
	//ֻ������������
	function KeyIsNumber(KeyCode) 
	{
	  		//ֻ��������0-9
	  		if (KeyCode >= 48 && KeyCode <= 57)
	  		{
	  			return true;
	  		}
	  		
	  		return false;
	}	
	
	//82��220 ����		
	document.onkeyup = pwdKeyboardUp;

	function pwdKeyboardUp(e) 
	{
		var key = GetKeyCode(e);
		
		//����
		if (key == 82 || key == 220) 
		{
			goback("<s:property value='curMenuId' />");
			return;
		}
		else if (key == 13 || key == 89 || key == 221)
		{
			openWindow_wait('pls_wait');
		}		
	}

	function goback(menuid)
	{
		setException("�ɷѲ�����ȡ������ע��ȡ��������������");
	}
	
	/********************��POS�ɷ�����*******************************/
	/**
	1�������ɷ��ն�������ҵ��ǰ�÷������п��ۿ�����
	2������ҵ��ǰ��������POSP�������п��ۿ�ס�
	3������POSP������ҵ��ǰ�÷������п��ۿ�����
	4������ҵ��ǰ�ý����п��ۿ���ת���������ɷ��նˡ�
	5�������ɷ��ն��������ɷ��ն��ۺϹ���ƽ̨�����ƶ���������
	6�������ɷ��ն��ۺϹ���ƽ̨��BOSS�����ƶ����������նˡ�
	7��BOSS�������ɷ��ն��ۺϹ���ƽ̨���ؼ��˽����
	8�������ɷ��ն��ۺϹ���ƽ̨�����˽�����ظ������ն�
	**/
	/**************************************************************/
	
	/************************************************
					��POS����
	************************************************/
	var print_posNum = "";// ���ٺ�
	var print_batchNum = "";// ���κ�
	
	//�����쳣
	function setException(errorMsg) 
	{
		submitFlag = 1;	//�ύ���
		
		// �����ʱ����
		clearInterval(timeToken);
		
		// ��ʾ������Ϣ
		document.getElementById("errormessage").value = errorMsg;
			
		// �쳣����ֻҪ�������쳣��Ҫ��¼��־  
		document.actForm.action = "${sessionScope.basePath }nonlocalChargeHUB/goCardError.action";
		document.actForm.submit();
		
	}
	
	// ��BOSS����(������)
	function payToBoss() 
	{
		if (document.getElementById("tMoney").value == "" 
				|| parseInt(document.getElementById("tMoney").value) <= 0)
		{
			return;
		}
		
		//�ύ�ɷ�����
		document.actForm.action = "${sessionScope.basePath }nonlocalChargeHUB/chargeCommit.action";
		document.actForm.submit();			
	}
	
	//�������ۿ�
	function fPosPay()
	{
		try
		{
			document.getElementById("errorType").value = "add";
			
			//�����á�һ��֮�ڸ��ٺŲ�������ͬ��һ��ǩ��֮�ڵ����κ�����ͬ��
			var result = GetPosBatchNum();
			
			var dataArray = result.split("$");
			
			//��ȡ���ٺš����κ�ʧ��
			if (dataArray[0] != "0")
			{
				setException("��ȡ���ٺź����κ�ʧ�ܣ��޷�ʹ�����������г�ֵ����ȡ��������������");
				
				return;
			}
			
			//��ȡ���ٺš����κųɹ�
			print_posNum = dataArray[1];
			print_batchNum = dataArray[2];
			
			document.getElementById("terminalSeq").value = print_batchNum + print_posNum;
			
			//�ۿ�֮ǰ��¼��־
			var url = "${sessionScope.basePath }nonlocalChargeHUB/addChargeLog.action";
			
			var params = "cardChargeLogVO.servnumber=" + <s:property value='cardChargeLogVO.servnumber' /> + "&tMoney=" + <s:property value='yingjiaoFee' />;
			params = params + "&cardChargeLogVO.cardnumber=" + document.getElementById("cardnumber").value + "&cardChargeLogVO.terminalSeq=" + document.getElementById("terminalSeq").value;
			params = params	+ "&cardChargeLogVO.status=00&cardChargeLogVO.description=" + encodeURI(encodeURI('�ۿ�֮ǰ��¼��־')) + "&cardChargeLogVO.acceptType=" + document.getElementById("acceptType").value;
			params = params	+ "&cardChargeLogVO.posNum=" + print_posNum + "&cardChargeLogVO.batchNumBeforeKoukuan=" + print_batchNum;
			params = params + "&cardChargeLogVO.provinceCode=" + getValue("provinceCode");
		
			var loader = new net.ContentLoader(url, netload = function () {
					var resXml = this.req.responseText;
					var dataArray = resXml.split("~~");
					//��¼��־�ɹ�
					if (dataArray[0] == "1") 
					{
						//���ν��Ѷ�Ӧ����־�Ѿ���ӵ����У�֮��Ĳ���ֻ�Ǹ��´�����¼��������������
						document.getElementById("errorType").value = "update";
						var oid = dataArray[1].replace(/[\r\n]/g, "");
						document.getElementById("chargeLogOID").value = oid;
						
						var posResCode = '';
						
						//�ύ�ۿ�����
						try
						{
							var ret ;
							
							// �ر��������
							ret = CloseCom()
							if (ret != "0" || ret != 0)
							{
								setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
								return;
							}
							
							// �����ۿ�
							// posnum ���ٺ�;batchnum ���κţ� bankcardID���п��ţ�money �ɷѽ��(��)
							var payReturnStr = Pay(print_posNum,print_batchNum,'<s:property value="cardChargeLogVO.cardnumber" />',"<%=fenMoney %>");
	
							// �򿪼��̴��ڡ���������ģʽ
							ret = OpenCom();
							if (ret != "0" || ret != 0)
							{
								window.parent.showFrmErr("����:�򿪼��̴���ʧ�ܣ�");
							}
							ret = SetWorkMode(0);
							if (ret != "0" || ret != 0)
							{
								window.parent.showFrmErr("����:���ü���ģʽʧ�ܣ�");
							}
							
							posResCode = payReturnStr.substring(0,2);
							document.getElementById('posResCode').value = posResCode;
							if (payReturnStr.substring(0,2) != "00")
							{
								setException("�������ɷ�ʧ�ܣ���ȡ��������������ԭ��"+payReturnStr);
								return;
							}
							
							// �ۿ�����ȡֵ
							var resultstr = payReturnStr.substring(0,34);
							var printcontext = payReturnStr.substring(34,payReturnStr.length);
							
							document.getElementById('printcontext').value = printcontext;
							
							// �ۿ�ɹ� ����34 �ɹ�00
							if (resultstr.substring(0,2) == "00" && resultstr.length == 34)
							{
							    
							    // �򿪼��̴��ڡ���������ģʽ
								OpenCom();
								SetWorkMode(0);
								
								var printcontexts = printcontext.split('~');							
								document.getElementById("tMoney").value = printcontexts[9];
								
								//������־
								var url1 = "${sessionScope.basePath }nonlocalChargeHUB/updateCardChargeLog.action";
				
								var params1 = "cardChargeLogVO.chargeLogOID=" + document.getElementById("chargeLogOID").value + "&cardChargeLogVO.unionpayuser=" + printcontexts[0];
								params1 = params1 + "&cardChargeLogVO.unionpaycode=" + printcontexts[1] + "&cardChargeLogVO.busiType=" + encodeURI(encodeURI(printcontexts[2]));
								params1 = params1 + "&cardChargeLogVO.batchnum=" + printcontexts[4] + "&cardChargeLogVO.authorizationcode=" + printcontexts[5];
								params1 = params1 + "&cardChargeLogVO.businessreferencenum=" + printcontexts[6] + "&cardChargeLogVO.unionpaytime=" + printcontexts[7];
								params1 = params1 + "&cardChargeLogVO.vouchernum=" + printcontexts[8] + "&cardChargeLogVO.unionpayfee=" + printcontexts[9];
								params1 = params1 + "&cardChargeLogVO.status=01&cardChargeLogVO.description=" + encodeURI(encodeURI('�ۿ�ɹ�')) + "&cardChargeLogVO.posResCode=" + document.getElementById('posResCode').value;
								
								var loader1 = new net.ContentLoader(url1, netload = function () {
									var resXml1 = this.req.responseText;
									
									//������־�ɹ�
									if (resXml1 == "1" || resXml1 == 1)
									{
										//����
										payToBoss();									
									}
									//������־ʧ��
									else
									{	
										setException("�ɷ�ʧ�ܣ���ȡ��������������СƱ��ƾСƱ����Ӫҵ�������˿�������ллʹ��!");
										return;
									}								
								}, null, "POST", params1, null);
							}
							//�ۿ�ʧ��
							else
							{
								setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
								return;
							}
								
						}
						catch (e)
						{
							
							if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
							{
								setException("�������ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
							}
							else
							{
								setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
							}
						}							
					}
					//��¼��־ʧ��
					else 
					{						
						setException("��־��¼ʧ�ܣ����ܽ����������ɷѲ�������ȡ��������������");
					}					
			}, null, "POST", params, null);	
		}
		catch (e)
		{
			if (document.getElementById("tMoney").value != "" && parseInt(document.getElementById("tMoney").value) > 0)
			{
				setException("�������ۿ�ɹ������ǽ���ʧ�ܡ���ȡ��������������");
			}
			else
			{
				setException("�������ۿ�ʧ�ܣ����ܼ������г�ֵ��������ȡ��������������");
			}
		}			
	}
	
	//�ύ����
	function doSub() 
	{		
		if (submitFlag == 0) 
		{
			submitFlag = 1;
			
			fPosPay();				
		}
	}
	
	//����ʣ��ʱ��
	function waitForSubmit() 
	{
		if (limitTime <= 0)
		{
			return;
		}
		
		//������ʱ��һ��limitTime��
		limitTime = limitTime - 1;
					
		document.getElementById("tTime").value = limitTime;
					
		//������ʱ�����
		if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
		{           	
			//�����쳣��������־
			document.getElementById("errorType").value = "add";
			
			setException("�˶���Ϣ��ʱ����ȡ��������������");
		}
	}
	
	// ������ʱ��
	function startclock() 
	{
		try 
		{
			timeToken = setInterval("waitForSubmit()", 1000);
		}
		catch (e) 
		{
			//�����쳣��������־
			document.getElementById("errorType").value = "add";
			
			setException("�˶���Ϣʧ�ܣ���ȡ��������������");
		}
	}
	
	function doLoad()
	{
		//���á���ҳ�������˳�����ť
	       document.getElementById("homeBtn").disabled = true;
	       document.getElementById("quitBtn").disabled = true;
	}
	</script>
</html>
