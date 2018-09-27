<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="com.gmcc.boss.common.base.CEntityString"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<%@page import="com.gmcc.boss.selfsvc.util.CurrencyUtil"%>
<%@page import="com.gmcc.boss.selfsvc.baseService.spService.model.SpAvailPO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Vector dataVector = (Vector)request.getAttribute("spservice");
	
	request.setAttribute("spservice", dataVector);
	//CEntityString sTitle = (CEntityString)dataVector.get(0);
	//String[] titles = sTitle.EntityString.split(",");
	CRSet data = null;
	if (dataVector != null)
	{
		data = (CRSet)dataVector.get(1);
	}
	String curmenuid = (String)request.getAttribute(Constants.CUR_MENUID);
	
	List<SpAvailPO> availSPService = (List)request.getAttribute("availSPService");
	
%>
<html>
<head>
<title>����ҵ��ѡ��</title>
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

<%--modify begin cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>
function doSet(spBizName,dealType,domain,spId,spBizCode,bizType)
{
	document.getElementById("spBizName").value = spBizName;
	document.getElementById("dealType").value = dealType;
	document.getElementById("domain").value = domain;
	document.getElementById("spId").value = spId;
	document.getElementById("spBizCode").value = spBizCode;
	document.getElementById("bizType").value = bizType;	
	var operTypeName = document.getElementById("operTypeName");  
	operTypeName.innerHTML = spBizName;
	
}
function doSub()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//�ύ���
		document.actform.action="${sessionScope.basePath}baseService/cancelSP.action?operType=Order";
		document.actform.submit();
	}
}

//����ȷ�Ͽ�
openWindow = function(id)
{
	wiWindow = new OpenWindow("popup_confirm",708,392);//�򿪵�����������
}
<%--modify end cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>

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
		goback("<s:property value='#request.curMenuId' />");
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
</script>
</head>
<BODY scroll="no" onload="window.focus();">
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
				
		        <!--������-->
				<div class="box842W fl ml45 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative" >
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- �б����� -->
                                <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
								<p class="ptop180 tc p747w411h" id="inn" >
								<%
								if (Constants.PROOPERORGID_HUB.equals((String)PublicCache.getInstance().getCachedData(Constants.PROVINCE_ID)))
            					{
            					%>
            						 <table width="100%" class="tb_blue" >
					               		<tr>
											<th class="list_title" align="center" colspan="20">�ɶ���SPҵ��</th>
			                            </tr>
					                  <tr>
					                    <th scope="col">ҵ������</th>
					                    <th scope="col">�Ʒ�����</th>
					                    <th scope="col">����(Ԫ)</th>
					                    <th scope="col">ʧЧ����</th>
					                    <th scope="col">����</th>
					                  </tr>
					                  <% 
					                  	if(availSPService != null && availSPService.size() > 0)
					                  	{
					                  		for(int j =0;j < availSPService.size();j++)
					                  		{
					                  			SpAvailPO spAvail = availSPService.get(j);
										      	%>
										      		<tr>
									                    <td><%= spAvail.getOperatorName()%></td>
									                    <td><%= spAvail.getBillFlag()%></td>
									                    <td><%= spAvail.getFee()%></td>
									                    <td><%= spAvail.getExpireDate()%></td>
									                    <td>
								                    	<input type="button" class="bt2_liebiao white"
								                    	  value="����" onmousedown="this.className='bt2on white'" 
									                    	onmouseup="this.className='bt2 white'; doSet('<%=spAvail.getOperatorName() %>','1','<%=spAvail.getDomain() %>','<%=spAvail.getSpcode() %>','<%=spAvail.getOperatorCode() %>','<%=spAvail.getServType() %>');openWindow(popup_confirm);"/>
					                  				</tr>
										      	<%
										      }
									      }
					                  %>
					                </table>
					                <br/>
					                <table width="100%" class="tb_blue" >
					               		<tr>
											<th class="list_title" align="center" colspan="20">�ѿ�ͨSPҵ��</th>
			                            </tr>
					                  <tr>
					                    <th scope="col">�ṩ��</th>
					                    <th scope="col">ҵ������</th>
					                    <th scope="col">����(Ԫ)</th>
					                    <th scope="col">��ͨʱ��</th>
					                  </tr>
					                  <% 
					                  	if(dataVector != null && data.GetRowCount()!= 0)
										  {
										      for(int i =0 ;i < data.GetRowCount(); i++) 
										      {
										      	%>
										      		<tr>
									                    <td><%=data.GetValue(i, 2)%></td>
									                    <td><%=data.GetValue(i, 4)%></td>
									                    <td><%=CurrencyUtil.divide(data.GetValue(i, 7),"1000") %></td>
									                    <td><%=data.GetValue(i, 9) %></td>
					                  				</tr>
										      	<%
										      }
									      }
					                  %>
					                </table>
				                <% 
				                }
				                else
				                {
				                %>
									<%--modify begin cKF48754 2011/11/16 R003C11L11n01 OR_HUB_201111_82 --%>
					                <table width="100%" class="tb_blue" >
					               		<tr>
											<th class="list_title" align="center" colspan="20">�ѿ�ͨSPҵ��</th>
			                            </tr>
					                  <tr>
					                    <th scope="col">�ṩ��</th>
					                    <th scope="col">ҵ������</th>
					                    <th scope="col">����(Ԫ)</th>
					                    <th scope="col">��ͨʱ��</th>
					                  </tr>
					                  <% 
					                  	if(dataVector != null && data.GetRowCount()!= 0)
										  {
										      for(int i =0 ;i < data.GetRowCount(); i++) 
										      {
										      	%>
										      		<tr>
									                    <td><%=data.GetValue(i, 2)%></td>
									                    <td><%=data.GetValue(i, 4)%></td>
									                    <td><%=CurrencyUtil.divide(data.GetValue(i, 7),"1000") %></td>
									                    <td><%=data.GetValue(i, 9) %></td>
					                  				</tr>
										      	<%
										      }
									      }
					                  %>
					                </table>
					                <%--modify end cKF48754 2011/11/16 R003C11L11n01 OR_HUB_201111_82 --%>
					                <br/>
					                <table width="100%" class="tb_blue" >
					               		<tr>
											<th class="list_title" align="center" colspan="20">�ɶ���SPҵ��</th>
			                            </tr>
					                  <tr>
					                    <th scope="col">ҵ������</th>
					                    <th scope="col">�Ʒ�����</th>
					                    <th scope="col">����(Ԫ)</th>
					                    <th scope="col">ʧЧ����</th>
					                    <th scope="col">����</th>
					                  </tr>
					                  <% 
					                  	if(availSPService != null && availSPService.size() > 0)
					                  	{
					                  		for(int j =0;j < availSPService.size();j++)
					                  		{
					                  			SpAvailPO spAvail = availSPService.get(j);
										      	%>
										      		<tr>
									                    <td><%= spAvail.getOperatorName()%></td>
									                    <td><%= spAvail.getBillFlag()%></td>
									                    <td><%= spAvail.getFee()%></td>
									                    <td><%= spAvail.getExpireDate()%></td>
									                    <td>
								                    	<input type="button" class="bt2_liebiao white"
								                    	  value="����" onmousedown="this.className='bt2on white'" 
								                    	  	<%--modify begin cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>
									                    	onmouseup="this.className='bt2 white'; doSet('<%=spAvail.getOperatorName() %>','1','<%=spAvail.getDomain() %>','<%=spAvail.getSpcode() %>','<%=spAvail.getOperatorCode() %>','<%=spAvail.getServType() %>');openWindow(popup_confirm);"/>
									                    	<%--modify end cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>
					                  				</tr>
										      	<%
										      }
									      }
					                  %>
					                </table>
				                <% 
				                }
				                %>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="div75w350h">
								<div class="blank10px"></div>
								
								<%
								if(Constants.PROOPERORGID_HUB.equalsIgnoreCase(province))
								{
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom" style="left:766px;top:39px;">0%</div>
								<%
								}
								else
								{ 
								%>
									<div class="box66W tc f16 div66w36h" id="gunDom">0%</div>
								<%
								} 
								%>
							</div>
							<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<%--add begin cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>
				<div class="popup_confirm" id="popup_confirm">
                  <div class="bg"></div>
                  <div class="tips_title">��ʾ��</div>
                  <div class="tips_body">
				    <p>��ѡ�񶩹��� <i id="operTypeName"></i><i>ҵ��</i></p>
				    <div class="blank10"></div>
				    <p class="mt30">ȷ�϶�������"ȷ��"�ύ��</p>
				  </div>
                  <div class="btn_box tc mt20">
	                  <span class=" mr10 inline_block "><a title="ȷ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doSub();">ȷ��</a></span>
	                  <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span>
                  </div>
                </div>
                <%--add end cKF48754 2011/11/11 R003C11L11n01  ���ȷ����ʾ--%>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
		          
			</div>
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
