<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
String recType = (String) request.getAttribute("recType");

// �ն���Ϣ
TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
List totalMenus = (List) PublicCache.getInstance().getCachedData(termInfo.getTermgrpid());
    
    String currMenuID = (String) request.getAttribute(Constants.CUR_MENUID);
	if (currMenuID == null || "".equals(currMenuID.trim()))
	{
		currMenuID = request.getParameter(Constants.CUR_MENUID);
		if (currMenuID == null || "".equals(currMenuID.trim()))
		{
			currMenuID = "root";
		}
	}
	
	MenuInfoPO menuInfo = null;
	if (totalMenus != null && totalMenus.size() > 0)
	{
		for (int i = 0; i < totalMenus.size(); i++)
		{
			menuInfo = (MenuInfoPO) totalMenus.get(i);
			if (currMenuID.equals(menuInfo.getMenuid()))
			{
				break;
			}
		}
	}
	
	String pageProvince = (String) PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID);
	
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// ����div
var divFlag = "";

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
		if ((key == 82 || key == 220) && divFlag == '')
		{
			window.location.href = "${sessionScope.basePath }login/index.action?lockTerm=lockTerm&timeoutFlag=1";
   			return ;
		}
	<%
	}
	else
	{
	%>
		//82:R 220:����
		if (key == 82 || key == 220)
		{
			goback("<s:property value='curMenuId' />") ;
   			return ;
		}
	<%	
	} 
	
	if ("1".equals(keyFlag))
	{
	%>
		// 1��
		if(key == 49 && divFlag == '')
		{
			openWindowwtjhz('wtjhz');
		}
		// 2��
		else if(key == 50 && divFlag == '')
		{
			openWindow('popup_confirm');
		}
		// ȷ�ϼ�
		else if(key == 89 && divFlag == 'wtjhz')  
		{
			selectType('24');
		}
		else if(key == 89 && divFlag == 'popup_confirm')  
		{
			submitReception();
		}
		// �����
		else if(key == 77 &&��divFlag != '')
		{
			windowClose();
		}
	<%
	}
	%>
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
function selectType(type)
{	 
	//��ֹ�ظ��ύ
	if (submitFlag == 0) 
	{
		submitFlag = 1;	
		        
		//��ͨ
		document.actform.transferType.value = type;
		
		//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		//add begin m00227318 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }reception/inputNumber.action";
		document.actform.submit();
	}  	 
}

//�ύҵ��
function submitReception()
{      		
	//��ֹ�ظ��ύ
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		document.actform.transferType.value = 28;
		
		//add begin m00227319 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		openRecWaitLoading_NX("recWaitLoading");
		//add begin m00227319 2012/10/19 V200R003C12L10 OR_huawei_201210_125
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }reception/callTransfer.action";
		document.actform.submit();
	}
}
// �رյ���divʱ�����divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="transferType" value="" />
			<input type="hidden" name="recType" value="<%=recType %>" />	
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<div class="box ring_tone m0auto">
					<ul class="curr_meal clearfix">
						<li><span>��ѡ�����</span><span class="charge_name"><%=menuName %>ҵ��</span></li>
					
					</ul>
    				<%--modify begin g00140516 2011/10/29 R003C11L10n01 �޸�ҳ����ʽ --%>
    				<ul class="ring_detail">
	                    <li class="clearfix li1">
	       					<p class="fl cap">ҵ����ܣ�</p>
							<div class="fl ring_info">
								<table width="600" border="0">
									<tr>
										<td width="600" class="fs16">
											<%
												if (menuInfo != null && menuInfo.getBusidetail() != null && !"".equals(menuInfo.getBusidetail().trim()))
												{
													%>
														<%= menuInfo.getBusidetail() %>
													<% 
												}
											%>
										</td>
									</tr>
								</table>
							 </div>
	      				</li>
	      				<li class="clearfix li2">
							      <p class="fl cap">��ܰ��ʾ��</p>
							      <div class="fl ring_info">
							          <p><%=additionalInfo == null ? "&nbsp;" : additionalInfo %></p>
							      </div>
						      </li>
					<%--modify end g00140516 2011/10/29 R003C11L10n01 �޸�ҳ����ʽ --%>
                    </ul>
    				<div class="btn_box tc">
    					
	    				<%
						if ("1".equals(keyFlag))
						{
						%>
								<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindowwtjhz('wtjhz');">��������ת(��1��)</a></span>
						<%
						}
						else
						{
						%>
								 <span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindowwtjhz('wtjhz');">��������ת</a></span>
						<%
						}
						%>
						
	    				<%
        				if (!Constants.PROOPERORGID_NX.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
        				{
	    				%>
		    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindowymzh('ymzh');">��æ��ת</a></span>
		    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindowwyd('wyd');">��Ӧ���ת</a></span>
		    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindowbkj('bkj');">���ɼ���ת</a></span>
	    				<%
	    				}
	    				%>
	    				
	    				<%
						if ("1".equals(keyFlag))
						{
						%>
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindow('popup_confirm');">ȡ��(��2��)</a></span>
						<%
						}
						else
						{
						%>
	    					<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openWindow('popup_confirm');">ȡ��</a></span>
						<%
						}
						%>
    				</div>
    			</div>
    			
                  <!--box end--> 
                  <div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>ȡ�� ����ת��ҵ����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
				    <%
					if ("1".equals(keyFlag))
					{
					%>
				         <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">ȷ��(��ȷ�ϼ�)</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">ȡ��(�������)</a></span></div>
					<%
					}
					else
					{
					%>
				         <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">ȷ��</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span></div>
					<%
					}
					%>
                </div>
				<script type="text/javascript">
				openWindow = function(id)
				{
					divFlag = "popup_confirm";
					wiWindow = new OpenWindow("popup_confirm",708,392);//�򿪵�����������
				}
				</script>
				
				<!-- ��������ת -->
                  <div class="popup_confirm" id="wtjhz">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>��������תҵ����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20">
                  	    <%
						if ("1".equals(keyFlag))
						{
						%>
						<span class=" mr10 inline_block ">
                  			<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('24');">ȷ��(��ȷ�ϼ�)</a>
                  		</span>
                  		<span class=" inline_block ">
                  			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">ȡ��(�������)</a>
                  		</span>						
                  		<%
						}
						else
						{
						%>
	    				<span class=" mr10 inline_block ">
                  			<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('24');">ȷ��</a>
                  		</span>
                  		<span class=" inline_block ">
                  			<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a>
                  		</span>	
						<%
						}
						%>
                  	
                  </div>
                </div>
				<script type="text/javascript">
				openWindowwtjhz = function(id)
				{
					divFlag = "wtjhz";
					wiWindow = new OpenWindow("wtjhz",708,392);//�򿪵�����������
				}
				</script>
				
				<!-- ��æת��-->
                  <div class="popup_confirm" id="ymzh">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>��æ��תҵ����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('25');">ȷ��</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span></div>
                </div>
				<script type="text/javascript">
				openWindowymzh = function(id)
				{
					wiWindow = new OpenWindow("ymzh",708,392);//�򿪵�����������
				}
				</script>
				
				<!-- ��Ӧ��ת-->
                  <div class="popup_confirm" id="wyd">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>��Ӧ���תҵ����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('26');">ȷ��</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span></div>
                </div>
				<script type="text/javascript">
				openWindowwyd = function(id)
				{
					wiWindow = new OpenWindow("wyd",708,392);//�򿪵�����������
				}
				</script>
				
				<!-- ���ɼ�-->
                  <div class="popup_confirm" id="bkj">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>���ɼ���תҵ����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('27');">ȷ��</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span></div>
                </div>
				<script type="text/javascript">
				openWindowbkj = function(id)
				{
					wiWindow = new OpenWindow("bkj",708,392);//�򿪵�����������
				}
				</script>
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
