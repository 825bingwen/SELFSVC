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
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />");
		return;
	}
}

function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
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
		
		XmlHttpRequest = false;
		if (window.XMLHttpRequest)
		{ // Mozilla, Safari,...      
		    XmlHttpRequest = new XMLHttpRequest();      
		    if (XmlHttpRequest.overrideMimeType)      
		    {      
		        XmlHttpRequest.overrideMimeType('text/xml');      
		    }      
		}      
		else if (window.ActiveXObject)      
		{ // IE      
		    try             
		    {      
		        XmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");      
		    }      
		    catch (e)      
		    {      
		        try     
		        {      
		            XmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");      
		        }      
		        catch (e) {}      
		    }      
		}      
		     
		if (!XmlHttpRequest) 
		{      
		    alert('���ִ���,���ܽ���һ��XMLHTTPʵ��!');      
		    return false;      
		}    
		document.actform.transferType.value = type;
		var postStr ="transferType="+ type + "&recType=2";
		//��ѯ�����ͺ�ת��ͨ״̬
		XmlHttpRequest.onreadystatechange=callBack;
		XmlHttpRequest.open("POST","${sessionScope.basePath }service/callTransfer.action",true);
		XmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		XmlHttpRequest.send(postStr);
	}
}

//<!--�ص��ķ���-->   
function callBack()
{
    if(XmlHttpRequest.readyState==4)   
    {   
        if(XmlHttpRequest.status==200)
        {   
        	var state=XmlHttpRequest.responseText;
        	submitFlag = 0;
        	if(state == 1)
        	{
        		//δ��ͨ��ͨ
        		document.actform.recType.value = "1";
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }service/inputNumber.action";
				document.actform.submit();
        	}
        	if(state == 0)
        	{
        		//�ѿ�ͨ��ȡ��
        		document.actform.recType.value = "0";
	       		openWindow('popup_confirm');
        	}
        }
    }
}

//�ύҵ��
function submitReception()
{      		
	//��ֹ�ظ��ύ
	if (submitFlag == 0) 
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }service/callTransfer.action";
		document.actform.submit();
	}
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">		
			<input type="hidden" name="transferType" value="" />
			<input type="hidden" name="recType" value="" />
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
										<td width="600" style="font-size:17px;">
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
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('24');">��������ת</a></span>
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('25');">��æ��ת</a></span>
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('26');">��Ӧ���ת</a></span>
	    				<span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';selectType('27');">�ػ���ת</a></span>
    				</div>
    			</div>
    			
                  <!--box end--> 
                  <div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ȷ��Ҫ�� <i>ȡ�� �����ͺ���ת����</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20"><span class=" mr10 inline_block "><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';submitReception();">ȷ��</a></span><span class=" inline_block "><a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span></div>
                </div>
				<script type="text/javascript">
				openWindow = function(id)
				{
					wiWindow = new OpenWindow("popup_confirm",708,392);//�򿪵�����������
					//openWindow('popup_confirm')
				}
				</script>
				
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
