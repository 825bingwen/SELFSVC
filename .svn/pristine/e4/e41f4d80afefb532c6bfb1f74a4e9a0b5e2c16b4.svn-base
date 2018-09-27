<%--
 @comment: 账单彩信寄送
 @remark: create m00227318 2012/10/15 OR_SD_201209_443 V200R003C12L10
--%>

<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.customize.sd.selfsvc.reception.action.MailBillSendAction"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	// 获取oid
	String oidBill = (String)request.getAttribute("oidBill");
	// 获取客户信息
    NserCustomerSimp customer = (NserCustomerSimp)session.getAttribute(Constants.USER_INFO);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>移动自助终端</title>
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

//切换邮件账单和彩信账单
var mltpFlag;
function doswitch(BillId)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		var btns = document.getElementById('BillTypeSele').getElementsByTagName('a');
		for( i = 0; i < btns.length; i++)
		{
			btns[i].className = 'btn_bill';
		}
		BillId.className = 'btn_billon';
	
		document.actform.target = "_self";
		//转向邮件账单页面
		if( BillId.id == "MailBill")
		{
			mltpFlag = "typeEmail";
		}
		//转向彩信账单页面
		else if( BillId.id == "MMSBill")
		{
			mltpFlag = "typeMms";
		}
		document.actform.action = "${sessionScope.basePath}mailBillSend/mailBillSend.action?mltpFlag=" + mltpFlag;
		document.actform.submit();
	}
}

//开通彩信账单寄送功能
function openMail(mltpFlag)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
    	document.actform.target = "_self";
    	document.actform.action = "${sessionScope.basePath}mailBillSend/openBillOrDetailMail.action?mltpFlag=" + mltpFlag;
		document.actform.submit();
	}
}

//取消彩信账单寄送功能
function cancleMail(oid,mltpFlag)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}mailBillSend/cancleBillOrDetailMail.action?oidBill=" + oid +"&mltpFlag=" + mltpFlag;
		document.actform.submit();
	}
}

openWindow = function(id)
{
    wiWindow = new OpenWindow(id,708,392);//打开弹出窗口
}
</script>
</head>
	<body scroll="no">
		<form name="actform" method="post">
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="b966">
					<div class="blank20"></div>
					<div class="relative p40">
						<div class="blank5"></div>
						
							<!-- modify begin m00227318 2012/10/10 V200R003C12L10 OR_SD_201209_443 -->						
							<div class="tab_num_bill" id="BillTypeSele"> 
							 	<a href="#" class="btn_bill" id="MailBill" onclick="doswitch(this);return false;">邮件账单定制</a> 
								<a href="#" class="btn_billon" id="MMSBill" onclick="doswitch(this);return false;">彩信账单定制</a>
								<!-- modify end m00227318 2012/10/10 V200R003C12L10 OR_SD_201209_443 -->
    							<div class="clear"></div>
  							</div>
  							
  							<div class="num_dis"> 
  								<table width="60%" class="tb_blue09" align="center">
  								
  									<!-- modify begin m00227318 2012/10/11 V200R003C12L10 OR_SD_201209_443 -->
  									<tr>
  										<th width="30%">话费信息类型</th>
  										<td>话费账单</td>
  									</tr>
  									<tr>
  										<th>寄送类型</th>
										<td>彩信</td>
  									</tr>
  									<%
  									if( null != oidBill && !"".equals(oidBill))
  									{
  									%>
  										<tr>
  											<th>状态</th>
											<td>已开通</td>
  										</tr>
  										<tr>
  											<th>操作</th>
											<td>
												<input type="button" class="bt2_liebiao white" value="取消" onmousedown="this.className='bt2on white'" 
									                    	onmouseup="this.className='bt2 white';openWindow('cancle_bill_confirm');"/>
											</td>
  										</tr>
  									<%
  									}
  									else
  									{
  									%>
  										<tr>
  											<th>状态</th>
											<td>未开通</td>
  										</tr>
  										<tr>
  											<th>操作</th>
											<td>
												<input type="button" class="bt2_liebiao white" value="开通" onmousedown="this.className='bt2on white'" 
									                    	onmouseup="this.className='bt2 white';openWindow('open_bill_confirm');"/>
											</td>
  										</tr>
  									<%
  									}
  									%>
  								</table>
    							<div class="clear"></div>
  							</div>
							
							<%
							if (null != busiDetailPage && !"".equals(busiDetailPage)) 
							{
							%>
								<div class="blank10 m36 busi_desp_border_bottom"></div>
		                        <div class=" blank10"></div>
		                        <div class="ml100">
		                       		<p class="tit_arrow "><span class=" bg"></span>业务介绍：</p>
		                            <p class="fs16 ml20 lh30"><%=busiDetailPage %></p>
		                        </div>
		                    <%
		                    }
		                    %>							

		                    
		                    <!-- add begin m00227318 -->							
            				<div class="popup_confirm" id="open_bill_confirm">
                 				<div class="bg"></div>
                  				<div class="tips_title">提示：</div>
                  				<div class="tips_body">
                     				<p>尊敬的客户，您确定<span class="yellow" >开通</span>彩信账单功能吗？</p>
                     				<div class="blank10"></div>
                     				<p class="mt30">确认开通请点击"确认"提交。</p>
                  				</div>
                  				<div class="btn_box tc mt20">
                     				<span class=" mr10 inline_block ">
                        				<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';openMail('typeMms');">确认</a>
                     				</span>
                     				<span class=" inline_block ">
                        				<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
                     				</span>
                  				</div>
            				</div>
            
            				<div class="popup_confirm" id="cancle_bill_confirm">
                  				<div class="bg"></div>
                  				<div class="tips_title">提示：</div>
                  				<div class="tips_body">
                     				<p>尊敬的客户，您已定制彩信账单功能，您确定<span class="yellow" >取消</span>吗？</p>
                     				<div class="blank10"></div>
                     				<p class="mt30">确认取消请点击"确认"提交。</p>
                 		 		</div>
                  				<div class="btn_box tc mt20">
                     				<span class=" mr10 inline_block ">
                        				<a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';cancleMail('<%=oidBill %>','typeMms');">确认</a>
                     				</span>
                     				<span class=" inline_block ">
                        				<a class="btn_bg_146" href="javascript:void(0)" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">取消</a>
                     				</span>
                 	 			</div>
            				</div>
            				<!-- add end m00227318 2012/10/13 -->
					</div>	
		    	</div>
		    </div>
				
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
