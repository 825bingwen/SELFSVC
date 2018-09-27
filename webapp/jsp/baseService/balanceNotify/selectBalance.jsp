<%@page contentType="text/html; charset=GBK"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<jsp:directive.page import="java.util.Vector" />
<jsp:directive.page import="com.gmcc.boss.selfsvc.common.model.DictItemVO" />
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	        + path + "/";
	Vector balanceVector = (Vector)request.getAttribute("balanceVector");
	
	String curmenuid = (String)request.getAttribute(Constants.CUR_MENUID);
	String pageStr = request.getParameter("pagecount");
	int pagecount = pageStr == null ? 0 : Integer.parseInt(pageStr);
	int pageNum = 9;
	String operType=(String)request.getAttribute("operType");
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
var balanceValue;
var operType;
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
	var key=GetKeyCode(e);
	<%
  if(balanceVector != null && balanceVector.size()!= 0)
  {
      int curkey = 49;
      int size = balanceVector.size();
      for(int i =0 ;i + pagecount * pageNum < size&& i < pageNum; i++,curkey++) 
      {
          DictItemVO dictitemVO = (DictItemVO) balanceVector.get(i + pagecount * pageNum);
         
		%>
			if(key==<%=curkey%>) 
			{
			  changeBalance("<%=dictitemVO.getDictid()%>");
			}
		<% 
      }
  }
	%>
		if(key == 82 || key == 220) 
		{
		  goback("<s:property value='curMenuId' />");
		}
	<% 
	if(balanceVector != null && balanceVector.size()!= 0 && pagecount>=1) 
	{
		%>
			if(key==85) 
			{
			  goPremenu();
			}
		<%
	}
	if(balanceVector != null && balanceVector.size()!= 0 && balanceVector.size()-(pagecount+1)*pageNum>0) 
	{
		%>
			if(key==68) 
			{
			  goNextmenu();
			}
		<% 
	}
  %>
}

function changeBalance(balance,operTypes)
{
	document.getElementById("balanceValue").value = balance;
	document.getElementById("balanceValue").innerHTML = balance;
	document.getElementById("balanceType").value = operTypes;
	
	openWindow("openWin1");
}

function goPremenu() 
{
    var page = parseInt(document.getElementById("pagecount").value) - 1;
  
    // add begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	openRecWaitLoading_NX("recWaitLoading");
	// add end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
		
    document.actform.target="_self";
    document.actform.action="${sessionScope.basePath}baseService/chgBalanceUrgePage.action?pagecount="+page;
    document.actform.submit();
}
function goNextmenu()
{
    var page = parseInt(document.getElementById("pagecount").value) + 1;
  
    // add begin m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	openRecWaitLoading_NX("recWaitLoading");
	// add end m00227318 2012/10/19 eCommerce V200R003C12L10 OR_huawei_201210_125
	
    document.actform.target="_self";
    document.actform.action="${sessionScope.basePath}baseService/chgBalanceUrgePage.action?pagecount="+page;
    document.actform.submit();
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
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">			
				<%@ include file="/customer.jsp"%>
				
					  <div class="service_brand w966 m0auto">
					    <div class="service_index_list">
					    <p class="hot_service"></p>
					    	
					    	<div class="p40">
      
								<div class="blank10"></div>
								<p class="tit_arrow fs22" ><span class="bg"></span><span class="fs22">请选择余额提醒值：</span></p>
								<div class="blank25"></div>
								<div class="blank25"></div>
        						
			    				<%
							            if (balanceVector != null && balanceVector.size() != 0)
							            {
							                int count = 0;
							                int size = balanceVector.size();
											%>
												<input type="hidden" id="pagecount" name="pagecount"
													value="<%=pagecount%>">
												<div class="btn_box tc">
											<%
								                for (; count + pagecount * pageNum < size && count < pageNum; count++)
								                {
								                    DictItemVO dictitemVO = (DictItemVO)balanceVector.get(count + pagecount * pageNum);
													%>
													
										    			<span class=" inline_block mr70" ><a href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';changeBalance('<%=dictitemVO.getDictid()%>','<%=operType %>');" title="<%=dictitemVO.getDictname()%>"><%=dictitemVO.getDictname()%></a></span>
										    			
													<%
												}
												
												%>
													</div>
												<%
									    }
									%>
			    				
        					</div>
        					<div class=" clear"></div>
					
					<!--弹出窗-->
					<div class="openwin_tip" id="openWin1" style="width:708px; height:392px;">
					  	<div class="bg"></div>
						<div class=" blank60"></div>
						<div class="  blank10n"></div>
						<s:hidden id="balanceType" name="balanceType"></s:hidden>
						<p class="fs28 lh2" style="padding-left:142px;" id="winText" name="winText">
							<span >您选择：</span>
							<span class="yellow">变更余额提醒值为 </span>
							<span id="balanceValue" class="yellow"></span>
							<span>元</span>
							<br/>
							<span>请点击确认，进行提交！</span>
						</p>
	  					<div class="tc">
						    <div class=" clear "></div>
						    <div class=" blank30 "></div>
	    					<a title="确认变更余额提醒值" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();gotoPrintSuccess();">确认</a> 
	    					<a title="" href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a> 
	    				</div>
					</div>
					
					<script type="text/javascript">
					openWindow = function(id){
						wiWindow = new OpenWindow(id,708,392);//打开弹出窗口例子					
					}
					
					function gotoPrintSuccess()
					{
						openRecWaitLoading();
						
						var balance = document.getElementById("balanceValue").value;
						var operType = document.getElementById("balanceType").value;
						setTimeout(
							function(){
								wiWindow.close();
								
								document.actform.target = "_self";
								document.actform.action = "${sessionScope.basePath}baseService/chgBalanceUrgeDef.action?balanceAwake=" + balance+"&operType="+operType;
								document.actform.submit();						
							},
						500);
					}
					</script>
					<!--弹出窗结束-->	
					    </div>
					    
					    
					  </div>
				
			</div>
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
