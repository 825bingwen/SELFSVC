<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//add begin by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
//获取电子发票特性参数，true为开启
TerminalInfoPO termInfo = (TerminalInfoPO)request.getSession().getAttribute(Constants.TERMINAL_INFO);
String isElectronInvoice = CommonUtil.getDictNameById(termInfo.getRegion(), "SH_ELECTRON_INVOICE");
//add end by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
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
		
		// 提交的标志位
		var submitFlag = 0;
		
		document.onkeydown = pwdKeyboardDown;
		
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
		
		document.onkeyup = pwdKeyboardUp;
		
		function pwdKeyboardUp(e) 
		{
			var key = GetKeyCode(e);

			//返回
			if (key == 82 || key == 220) 
			{
				goback("<s:property value='curMenuId' />");
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }monthInvoicePrint/qryCurrentMonth.action";
				document.forms[0].submit();
			}
		}
		
		// 调用查询接口
		function selectType(startdate, enddate, cycle, acctid)
		{
			// 发票打印机设备
			var prtObj;

			//modify begin by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
			//如果开启电子发票，不进行打印机校验
			var isElectronInvoice = "<%=isElectronInvoice %>";
			if ("true" != isElectronInvoice)
			{
				try {
				
					prtObj = window.parent.document.getElementById("invprtpluginid");
					
				    // 打开发票打印机串口
					var openCom = prtObj.OpenCom();
					if (openCom == 1) 
					{
						alertRedErrorMsg("警告:发票打印机串口故障!");
						return;
					} 
					else 
					{
						if (openCom == 61) 
						{
							alertRedErrorMsg("错误:发票打印机故障,无法初始化!");
							return;
						} 
						else if (openCom == 65) 
						{
							alertRedErrorMsg("警告:发票打印机缺纸!");
							return;
						} 
						else if (openCom != 0) 
						{
							alertRedErrorMsg("错误:打开设备异常,无法初始化发票打印机!");
							return;
						}
					}
					
					// 初始化发票打印机
					var initInvoicePrt = prtObj.InitVoicePrint();
					if (initInvoicePrt == 61) 
					{
						alertRedErrorMsg("错误:发票打印机故障,无法初始化!");
						return;
					} 
					else if (initInvoicePrt == 65) 
					{
						alertRedErrorMsg("警告:发票打印机缺纸!");
						return;
					}
					else if (openCom != 0) 
					{
						alertRedErrorMsg("错误:打开设备异常,无法初始化发票打印机!");
						return;
					}
					
					// 检查发票打印机缺纸
					var v = prtObj.CheckPaper();
					if (v != 0 )
					{
						alertRedErrorMsg("警告:发票打印机缺纸或故障!");
					    return;
					}
				}
				catch (e) 
				{
					alertRedErrorMsg("警告:发票打印机初故障,无法打印发票!");
					return;
				}
			}
			//modify end by cwx456134 2017-05-11 OR_SD_201703_234_山东_电子发票优化需求
			//防止重复提交
			if (submitFlag == 0) 
			{
				submitFlag = 1;	
				document.getElementById("cycle").value = cycle;
				document.getElementById("startdate").value = startdate;
				document.getElementById("enddate").value = enddate;
				document.getElementById("acctid").value = acctid;
				
				document.actform.target = "_self";
				document.actform.action = "${sessionScope.basePath }invoice/printInvoice.action";
				document.actform.submit();
			}  	 
		}
		
	</script>
  </head>
  
  <body scroll="no">
      <form action="" name="actform" method="post">
      
          <!-- 月份 -->
		  <input type="hidden" id="month" name="month" value="<s:property value='#request.month' />"/>
	
	      <!-- 账期信息 -->
	    
		  <!-- 账期 -->
		  <input type="hidden" id="cycle" name="invoicePrint.billCycle" value=""/>
		  <!-- 开始时间 -->
		  <input type="hidden" id="startdate" name="invoicePrint.startdate" value=""/>
		  <!-- 结束时间 -->
		  <input type="hidden" id="enddate" name="invoicePrint.enddate" value=""/>
		  <!-- 主账号 -->
		  <input type="hidden" id="acctid" name="invoicePrint.acctId" value=""/>		
		
          <%@include file="/titleinc.jsp" %>
          
          <div class="main" id="main">
            
            <%@ include file="/customer.jsp" %>
            
			<div class="box842W fl ml45IE6 relative">
			<div class="bg"></div>
			<div class="top"></div>
			<div class="con relative" >
				<div class="box747W fl">					
					<div class="div747w444h" >
			
	        			<p class="fs18 p20 ml50">请选择查询账期：</p>
	          
	        			<div class="blank15"></div>
	        
				        <div class="ptop180 tc p747w411h" id="inn">
				        	<table width="80%" class="tb_blue" align="center">
				        	    <tr class="tr_color">
				        	    	<th width="20%"  class='list_title'>账  期</th>
				        	    	<th width="30%" class='list_title'>账期开始时间</th>
				        	    	<th width="30%" class='list_title'>账期结束时间</th>
				        	    	<th width="20%" class='list_title'>操  作</th>
				        	    </tr>
				        		<s:iterator value="cycleList" id="list" status="st">
				        			<tr class="tr_color">
					        			<td class="tc"><s:property value='#list.cycle' /></td>
					        			<td class="tc"><s:property value='#list.startdate' /></td>
					        			<td class="tc"><s:property value='#list.enddate' /></td>
					        			<td>
					        			    <span class=" mr10 inline_block ">
					        			    	<a class="btn_bg_146" href="javascript:void(0);" onclick="selectType('<s:property value='#list.startdate' />', '<s:property value='#list.enddate' />', '<s:property value='#list.cycle' />', '<s:property value='#list.acctid' />' );return false;" onmousedown="" onmouseup="">打印</a>
               								</span>
					        			</td>
				        			</tr>
				        		</s:iterator>
				        	</table>
				        </div>		         			
					</div>
         			</div>
          		<div class="box70W fr">
						<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
						<div class="div75w350h">
							<div class="blank10px"></div>
							<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
						</div>
						<input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="btm"></div>
			</div>
			<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
	        <!--滚动条结束-->
         		</div>
        	 </div>
          </div>
          <%@include file="/backinc.jsp" %>
      </form>
    
  </body>
</html>
