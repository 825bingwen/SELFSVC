<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%

// Ͷ�ҳ�ʱʱ��
String timeout = (String) PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);

// �ն���Ϣ
TerminalInfoPO termInfo = (TerminalInfoPO) session.getAttribute(Constants.TERMINAL_INFO);

// �ֽ�ʶ��������
String isCashEquip = termInfo.getTermspecial().substring(3, 4);

// �û���Ϣ
NserCustomerSimp customerSimp = (NserCustomerSimp)request.getSession().getAttribute(Constants.USER_INFO);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript">
document.onkeydown = pwdKeyboardDown;
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
	
document.onkeyup = pwdKeyboardUp;

function pwdKeyboardUp(e) 
{
	var key = GetKeyCode(e);
	
	//ȷ��
	if (key == 13 || key == 89 || key == 221) 
	{
		doSub();
		return;
	}
	//����
	else if (key == 82 || key == 220) 
	{
		goback("<s:property value='curMenuId' />");
		return;
	}		
}
	
//Ͷ�ҵ�ʱ������λ��		
var payMoneyTime = "<%=timeout %>";

//ʣ��ʱ��
var leftTime = payMoneyTime;

//readCash��ʱ�����
var readCashToken;

//�ر�ʶ������0������Ҫ��1����Ҫ
var needClose = 0;

//�ύ��ǣ�0��ʾδȷ���ύ�ɷѣ�1��ʾ��ȷ���ύ�ɷ�
var submitFlag = 0;

function goback(menuid)
{
	//��Ͷ��
	if (document.getElementById("tMoney").value != "" 
			&& parseInt(document.getElementById("tMoney").value) > 0)
	{
		return;
	}
	
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//����ʱ�������ʱ����ͬʱ�ر��ֽ�ʶ����			
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		clearInterval(readCashToken);
		
		document.getElementById("curMenuId").value = menuid;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }login/backForward.action";
		document.actform.submit();
	}
}
	
// �ύ
function doSub()
{
	//δͶ��
	if (document.getElementById("tMoney").value == "" 
			|| parseInt(document.getElementById("tMoney").value) <= 0)
	{
		return;
	}
	
	//��δ�ύ����
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		//�޸Ľɷ��ظ��ύ
		wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
	
		//�ر��ֽ�ʶ����
		if (needClose == 1)
		{
			fCloseCashBill();
		}
		
		//�����ʱ����
        clearInterval(readCashToken);
        
        //�û�Ͷ����
        document.getElementById('totalFee').value = document.getElementById('tMoney').value * 100;

		//�ύ�ɷ�����
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }activitiesRec/finish.action";
		document.actform.submit();
	}
}
	
//�����쳣
function setException(errorMsg) 
{			
	document.getElementById("errormessage").value = errorMsg;
	
	// �����쳣�������ʱ����ͬʱ�ر��ֽ�ʶ����			
	if (needClose == 1)
	{
		fCloseCashBill();
	}
	
	// �����ʱ����
	clearInterval(readCashToken);
	
	// �쳣������ֻҪ�������쳣��Ҫ��¼��־  
	document.actform.target = "_self";
	document.actform.action = "${sessionScope.basePath }activitiesRec/goCashError.action";
	document.actform.submit();
}		

// ��ȡ�ֻ�����
// ��ʼ���ֽ�ʶ����
// ��ȡͶ����ˮ
// ʹ��ҳ���˳���ť������
// ����ѭ����ȡͶ�ҽ��
function loadContent() 
{
	
	var serverNumber = <%=customerSimp.getServNumber() %>;
     
	<%if (!"1".equals(isCashEquip)){%>
		  setException("�Բ��𣬸��ն˻��ݲ�֧���ֽ��ֵ����ѡ��������ʽ��");
		  return;
	<%}%>
    
    try 
    {
    	// ��ʼ���ֽ�ʶ����(�������� 0,20110509143345)
		var initData = initCashEquip(serverNumber);
	   	
	   	// ��ʶ�ؼ�ʹ��
	   	closeStatus = 2;
	   	
        if (initData.substring(0, 1) != "0") 
        {
        	setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
            return;                    
        }
        else
        {
        	//�ֽ�ʶ������ʼ���ɹ������ٽ����û�Ͷ��ʱ����Ҫ�رա�
        	needClose = 1;
        	
        	//��ȡͶ����ˮ
        	document.getElementById("terminalSeq").value = initData.substring(2);
        	
        	//��ʼ���ɹ�������Ҫ�ر�ʶ����������ҳ���˳���ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
        	document.getElementById("homeBtn").disabled = true;
        	document.getElementById("quitBtn").disabled = true;
        	
        	startclock();
        }               
    } 
    catch(e) 
    {
        setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
        return;
    }           
}

//ѭ����ȡͶ�ҽ��
function startclock() 
{
	var msg = preparecash();
	
	//ʶ����״̬���ʧ�ܣ�������Ͷ�ң���ʾ�쳣��Ϣ
	if (msg != "") 
	{
		setException(msg);
		return;
	}		
	
	try
	{
		// ��ȡͶ�ҽ��,ѭ������,ÿ��1��ִ��һ��
		readCashToken = setInterval("doCash()", 1000);
	}
	catch (e) 
	{
		setException("�Բ��𣬼�ʱ�������쳣���޷�ʹ���ֽ���г�ֵ����ʹ��������ʽ���ߵ�Ӫҵ�����г�ֵ��");
	}
}

//���ʶ����״̬
function preparecash() 
{
	var msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽֵ��";

	try 
	{
		//���ʶ����״̬ 0-���� 1-�쳣 2-Ǯ���� 3-Ǯ��� 4-��ڱ��� 5-Ǯ�䱻�� 6-�������� 9-�޴��豸
		var cashStatus = checkCashStatus();

		if (cashStatus == 0)
		{
			msg = "";
		}
		else if (cashStatus == 1) 
		{
			msg = "�Բ����ֽ�ʶ����״̬�쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		}
		else if (cashStatus == 2) 
		{
			msg = "�Բ���Ǯ���������޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		}
		else if (cashStatus == 3) 
		{
			msg = "�Բ���Ǯ��δ�����رգ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		} 
		else if (cashStatus == 4) 
		{
			msg = "�Բ���Ǯ���볮�ڱ��У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		}
		else if (cashStatus == 5) 
		{
			msg = "�Բ���Ǯ�䱻�У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		} 
		else if (cashStatus == 6) 
		{
			msg = "�Բ����ֽ�ʶ��������δ֪�����޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		}
		else if (cashStatus == 9) 
		{
			msg = "�Բ����ֽ�ʶ���������ڣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
		}			
	}
	catch (e) 
	{
		msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
	}
	
	// ����
	return msg;
}

// ��ȡͶ�ҽ��
// ѭ�����ã�ÿ��1��ִ��һ��
function doCash() 
{
	if (leftTime <= 0)
	{
		return;
	}
	
	try 
	{	
		// ��ȡͶ�ҽ�� 0 ��ʾû��Ͷ�ң����� ΪͶ����ֵ(���ܵ�ֵΪ��1,2,5,10,20,50,100)��
		var ret = getOnceCash();

		if (ret > 0) 
		{
			// ��ʶ�ؼ�ʹ��
	   		closeStatus = 1;
			
			// ʱ�����¿�ʼ
			leftTime = "<%=timeout %>";
			
			// ��ʾʣ��ʱ��
			document.getElementById("tTime").value = leftTime;
			
			// Ͷ�Һ󣬲��ܷ���
            document.getElementById("backBtn").disabled = true;
			
			// ����Ͷ�ҽ�� 
			document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
			
			// ȡ�����װ�Ť����ʾ
			document.getElementById('cancelBusi').style.display = "none";
			
			// Ͷ�ҽ�����0ʱȡ�����װ�Ť����ʾ,�ɷѰ�Ť��ʾ
			if (parseInt(document.getElementById("tMoney").value) > 0)
			{
				document.getElementById('bCommitBusi').style.display = "none";
				document.getElementById('commitBusi').style.display = "block";
				document.getElementById("promptMsg").innerHTML = "��Ͷ��ֽ�ң������ɷѰ�ť";
			}
			else
			{
				document.getElementById('bCommitBusi').style.display = "block";
			}
		}
		else
		{
			// Ͷ��ʱ��һ��timeout��
			leftTime = leftTime - 1;
			
			// ��ʾʣ��ʱ��
			document.getElementById("tTime").value = leftTime;
			
			//Ͷ��ʱ����������û�û�������ύ�ɷ����󣬴�ʱ����Ҫ�ύ�ɷ�����
			if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
			{           	 	
		        //Ͷ�ҽ�����0
		       	if (parseInt(document.getElementById("tMoney").value) > 0) 
				{
					// �ύ�ɷ�
					doSub();
				} 
				else 
				{
					// ȡ������
					cancelBusi();
					//setException("Ͷ��ʱ�������Ͷ�ҽ��Ϊ0���޷����нɷѲ���");
				}
			}
		}				
	}
	catch (e) 
	{
		setException("�Բ��𣬻�ȡͶ�ҽ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
	}
}

// ȡ������
function cancelBusi()
{		
	if (needClose == 1)
	{
		// �ر��ֽ�ʶ����
		fCloseCashBill();
	}
	
	// �����ʱ����
	clearInterval(readCashToken);
	
	// ������ҳ
	setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
}
</script>
</head>
<body scroll="no">
	<form name="actform" method="post">
		
		<!-- Ӫ���Ƽ���ʶ -->
		<input type="hidden" id="recommendActivityFlag" name="recommendActivityFlag" value='<s:property value="#request.recommendActivityFlag" />'/>
		
		<input type="hidden" id="payType" name="payType" value="<%=Constants.PAY_BYCASH %>">
		
		<!-- ������� -->
		<input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
		
		<!-- �������� -->
		<input type="hidden" id="dangciName" name="dangciName" value="<s:property value="#request.dangciName" />"/>
		
		<!-- ����� -->
		<input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
		<!-- ���α��� -->
		<input type="hidden" id="dangciId" name="dangciId" value='<s:property value="#request.dangciId" />'/>
		<!-- ��Ʒ���봮 -->
		<input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward" />'/>
		<!-- �ֻ�imeiid�� -->
		<input type="hidden" id="imeiid" name="imeiid" value='<s:property value="#request.imeiid" />'/>
		<!-- ��Ʒ�ܼ� -->
		<input type="hidden" id="rewardAccount" name="rewardAccount" value='<s:property value="#request.rewardAccount" />'/>
		<!-- ��Ʒ���� -->
		<input type="hidden" id="quantity" name="quantity" value='<s:property value="#request.quantity" />'/>
		
		<%-- modify by sWX219697 2015-7-20 totalfee��ΪtotalFee,findbugs�޸�--%>
		<!-- �û�Ͷ��ķ��ý�� -->
		<input type="hidden" id="totalFee" name="totalFee" value='<s:property value="#request.totalFee" />'/>
		<!-- ������� -->
		<input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
		
		<input type="hidden" id="yingjiaoFee" name="yingjiaoFee" value='<s:property value="yingjiaoFee" />'>
		<input type="hidden" id="terminalSeq" name="terminalSeq" value=''>
		<input type="hidden" id="errorType" name="errorType" value=''>
		<input type="hidden" id="errormessage" name="errormessage" value=''>
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">
			<%@ include file="/customer.jsp"%>
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
      					<h2>��������̣�</h2>
      					<div class="blank10"></div>
      					<a title="ѡ������" href="#">1. ѡ������</a>
      					<a title="����Э��" href="#">2. ����Э��</a>  
      					<a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
      					<a title="Ͷ��ֽ��" href="#" class="on">4. Ͷ��ֽ��</a>
      					<p class="recharge_tips">֧��10��50��100Ԫ����ֽ�ҡ�</p>
          				<a title="���" href="#" >5. ���</a>   					
				</div>
				
				 <div class="b712 fm_pay_money">
				 	<div class="bg bg712"></div>
     					<div class="blank30"></div>
     					<div class="p40 pr0">
     						<div class="blank10"></div>
       					<div class="blank20"></div>
       					<div class=" pay_money_wrap2">
       					 	<p class="pay_all">
       					 		<span style="padding-left:80px;">��Ͷ�룺</span><input type="text" id="tMoney" name="tMoney" value="0" readonly="readonly" /><span class="yellow">Ԫ</span>&nbsp;&nbsp;
       					 		<span class="yellow">Ӧ�ɣ�<s:property value="#request.prepayFee" />Ԫ</span>
       					 		
       					 	</p>
       					 	<div class="pay_state clearfix">
       					 		<span class="cash_arrow"></span>
             						<p class="fl fs22">
             							Ͷ��״̬�� 
             							<s:if test="yingjiaoFee == '0'">
										<span id="promptMsg" class="yellow">Ͷ�ҽ����������ɷѰ�ť</span>
									</s:if>
									<s:else>
										<span id="promptMsg" class="yellow">��Ͷ��ֽ��...</span>
									</s:else>			
             							<br />
             							<span style="padding-left:119px;">Ͷ��ʱ����</span><span class="yellow"><%=timeout %></span>�룬��ǰʣ��<input type="text" id="tTime" name="tTime" value="<%=timeout %>" readonly="readonly" />��
             							<br/>
             							<span style="padding-left:119px;">֧��</span><span class="yellow">5��10��20��50��100Ԫ</span>����ֽ��
             						</p>
           					</div>
       					</div>
       					<div class="blank30"></div>
       					<div>
       					 	<img src="${sessionScope.basePath }images/rmb.gif" style="float:left; padding-left:160px;" alt="��Ͷ��" />
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box cancle fl" id="cancelBusi">
       					 		<a title="ȡ�����ν���" href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">ȡ�����ν���</a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px;" class="btn_box charge_unable fl" id="bCommitBusi">
       					 		<a title="" href="#" onclick="return false;"></a>
       					 	</div>
       					 	<div style="padding-top:120px; padding-left:30px; display:none" class="btn_box buy_enable fl" id="commitBusi">
       					 		<a title="����" href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="openWindow_wait('pls_wait');return false;">�ɷ�</a>
       					 	</div>
       					</div>
     					</div>
				 </div>
			</div>
			
			<!--������ ���ڴ��� ���Ժ�-->
			<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
				<div class="bg"></div>
                  	<p class="mt40"><img src="${sessionScope.basePath }images/loading.gif" alt="������..." /></p>
                 	<p class="tips_txt">���ڴ��������Ժ�......</p>
                	<div class="line"></div>
                 	<div class="popup_banner"></div>                
               </div>

			<script type="text/javascript">
			    //�޸Ľɷ��ظ��ύ���� change by LiFeng
			    var isDone = 0;
				openWindow_wait = function(id){
					if(isDone == 0)
					{
						isDone = 1;
						document.getElementById('commitBusi').disabled = true;
						doSub();
					}
				    //setTimeout("doSub()", 500);
				}				
			</script>
			<!--����������-->
		</div>
		
		<%@ include file="/backinc.jsp"%>		
	</form>
</body>
<script type="text/javascript">
	clearTimeout(timeOut);
	loadContent();
</script>
</html>