<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%@page import="com.customize.hub.selfsvc.prodInstall.model.SimVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移动自助终端</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
			<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
				<META HTTP-EQUIV="Expires" CONTENT="0">
					<link href="${sessionScope.basePath }css/reset.css" type="text/css"
						rel="stylesheet" />
					<link href="${sessionScope.basePath }css/style.css" type="text/css"
						rel="stylesheet" />
				<style type="text/css">		
.openwin_big1{ width:708px; height:450px; display:none; z-index:9999}

.openwin_big1  .bg1{ width:708px; height:450px; background:url(../images/bg_openwin_big.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_openwin_big.png");_background:none}	
.openwin_big1  .bgtitle{ width:700px; height:45px;  background:url(../images/bg_880.png);_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="../images/bg_880.png");_background:none}							
				</style>		
					<script type="text/javascript"
						src="${sessionScope.basePath }js/public.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/script.js">
</script>
					<script type="text/javascript"
						src="${sessionScope.basePath }js/dialyzer.js">
</script>
<script type="text/javascript"
							src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
					<script language="javascript">
//防止重复提交
var submitFlag = 0;

//82、220 返回		
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//更正
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
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
					return;
				}
				//确认
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
			}
			
			function goback(menuid)
			{
<%--				if (submitFlag == 0)--%>
<%--				{--%>
<%--					submitFlag = 1;--%>
<%--					--%>
<%--					document.getElementById("curMenuId").value = menuid;--%>
<%--				--%>
<%--					window.history.back();--%>
<%--				}--%>
			}
						
			function doSub() 
			{
				
				if (submitFlag == 0) 
				{
					submitFlag = 1;	//提交标记
					
				
						//判断是否已经读卡
				var iccid='<s:property value="#session.attr.SimCardInfo.iccid"/>';
				if(iccid&&iccid.substring(0,5)=='89860'){
			
					//alert("你已经选择了产品，不能再选择！");
					doSubExp("${sessionScope.basePath }hubprodinstall/chooseTheProduct.action");
					return ;
				}
				//判断是否选择产品
			if(document.getElementById('shTpltTempletVO.brand').value){}else{
				//alert("你还没有选择产品，请选择产品！");
				submitFlag = 0;	//提交标记
				return ;
			}
				//判断读卡是否正确
				var ret=getSimCardICCID();
				//alert(ret);
				//测试用
				//var ret='898600C7170942207213';
				
				if(ret!=-1&&ret.substring(0,5)=='89860'){
					document.getElementById('simICCID').value=ret;
					document.dutyInfoForm.target = "_self";
					document.dutyInfoForm.action = "${sessionScope.basePath }hubprodinstall/chooseTheProduct.action";
					document.dutyInfoForm.submit();
					
				}else{
					goToExcPage("SIM卡信息异常！");
					
				}
					}
				}
			// 分页
		function nextPage(linkURL)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				document.forms[0].target = "_self";
				document.forms[0].action = linkURL;
				document.forms[0].submit();
			}
		}
function showDetailInfo(content)
{  
	document.getElementById('winText_common1').innerHTML = content;
	
	wiWindow = new OpenWindow("openWin_common", 708, 392);
}
//读取SIM卡的ICCID
function getSimCardICCID()
{
	//return '0';
	var ret;
	try
	{
		//调用系统正在处理提示
			openWindowloading();
		//读卡过程
		var stat=window.parent.document.getElementById("simcardpluginid").ReadCardStatus();
		if(stat==-1){
			goToExcPage("检查读卡器状态失败！");
			return -1;
		}else{
			var wmsg=checkCardReaderStatus(stat);
			//提示用户
			//openWindow(wmsg);
	   }
			
			//stat=window.parent.document.getElementById("simcardpluginid").MoveCardToWrite();
			//if(stat==-1)
			//{
					 //goToExcPage("移卡失败！");
					 //return -1;
			//}
		  
		  stat=window.parent.document.getElementById("simcardpluginid").IsCardExist(); 
		  //alert("IsCardExist~" + stat);
		  
			stat=window.parent.document.getElementById("simcardpluginid").GetICCSerial();
			
			if(stat == -1)
			{
				 goToExcPage("读取系列号失败！");
				 return -1;
		  }
		  
		
			ret = window.parent.document.getElementById("simcardpluginid").GetSIMICCID();
			if(ret == -1)
			{
				 goToExcPage("读取SIMICCID失败！");
				 return -1;
		    }
		   

			return ret;
	}
	catch(e)
	{
		goToExcPage("读取SIM卡信息过程出现异常！");
		return '-1';
	}
	
}
//异常
function goToExcPage(errormessage)
{
submitFlag = 0;
document.getElementById("errormessage").value=errormessage;
doSubExp("${sessionScope.basePath}hubprodinstall/handleError.action");
}
//系统正在处理提示
 function  openWindowloading (){
	wiWindow = new OpenWindow("openWinLoading",804,515);//打开弹出窗口例子
								
				}
//检查读卡器状态
function checkCardReaderStatus(i){
	    var msg="";
		var arr = i.split('~');
		if(arr[0] == 0)
		{
		     msg = "通道卡片位置："
		    if(arr[1] == 0)
			{
			    msg = msg + "通道无卡";
			}
			else if(arr[1] == 1)
			{
			    msg = msg + "读磁卡位置有卡";
			}
			else if(arr[1] == 2)
			{
			    msg = msg + "IC卡位置有卡";
			}
			else if(arr[1] == 3)
			{
			    msg = msg + "前端夹卡位置有卡";
			}
			else if(arr[1] == 4)
			{
			    msg = msg + "前端不夹卡位置有卡";
			}
			 msg = msg + "；卡箱卡片状态：";
		    if(arr[2] == 0)
			{
			    msg = msg + "卡箱无卡。";
			}
			else if(arr[2] == 1)
			{
			    msg = msg + "卡箱卡片不足,提醒需要加卡。";
			}
			else if(arr[2] == 2)
			{
			    msg = msg + "卡箱卡片足够。";
			}
		   
		}
		return msg;
}
  function doSubExp(url)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
			
				
				
				document.dutyInfoForm.target = "_self";
				document.dutyInfoForm.action = url;
				document.dutyInfoForm.submit();	
			}			
		}
  //提示
   function openWindow(msg){
	  document.getElementById("winText").innerHTML="<span class='yellow'>"+msg+"</span>";
	  wiWindow = new OpenWindow("openWin1",708,392);//打开弹出窗口例子					
				}
		</script>
	</head>

	<body scroll="no" onload="">
		<form name="dutyInfoForm" method="post" target="_self">
		<input type="hidden" name="errormessage" id="errormessage"/>
		<input type="hidden" id="simICCID" name="simVO.iccid"/>
		<input type="hidden" id="shTpltTempletVO.brand" name="shTpltTempletVO.brand"/>
		<input type="hidden" id="shTpltTempletVO.templetid" name="shTpltTempletVO.templetid"/>
         <%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
			<div class="openwin_big1" id="openWin_common">
  	<div class="bg1"></div>
	<div class=" blank5"></div>
 
   <p class="fs24 lh2 pl20" id="winText_common" name="winText_common">
   <div class="bgtitle" style="font-size:22px;margin-left:3px;" aligh="center" >
	<div style="margin:20px;">套餐详情信息</div>
	</div>
	
	<div  style="font-size:20px;">
	<div id="winText_common1" style="text-indent:2em;padding-right:15px;font-size:18px;" class="tit_info1"></div>
	</div>
	</p>
	<div class="tc">
    <div class=" clear "></div>
    <div class=" blank30 "></div>
		<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">确定</a> 
	</div>
   </div>
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							选号开户流程
						</h2>
						<div class="blank10"></div>
						<a href="#">1. 入网协议确认</a>
						<a href="#">2. 身份证信息记取</a>
						<a href="#" class="on">3. 产品选择</a>
						<a href="#">4. 号码选择</a>
						<a href="#">5. 费用确认</a>
						<a href="#">6. 开户缴费</a>
						<a href="#">7. 取卡打印发票</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank30"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>产品列表
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							<div>
							<table width="100%" class="tb_blue">
								<tr>

<%--									<th class="list_title" align="center">--%>
<%--										选择--%>
<%--									</th>--%>
									<th class="list_title" align="center" >
										模板名称
									</th>
									<th class="list_title" align="center">
										套餐月费
									</th>
									<th class="list_title" align="center">
										品牌
									</th>
									<th class="list_title" align="center">
										套餐详情
									</th>


								</tr>
								<s:if test="products != null && products.size > 0">
									<s:iterator value="products" status="result" id="pro">
									
										<tr align="center">
										
<%--											<td width="5%">--%>
<%--											<s:if test="#pro.templetid!=null&&#pro.templetid.length()>0">--%>
<%--												<input type="radio" name="shTpltTempletVO.templetid"--%>
<%--													value="<s:property value='#pro.templetid'/>" onclick="document.getElementById('shTpltTempletVO.brand').value='<s:property value="#pro.brand" />';doSub();"/>--%>
<%--													</s:if>--%>
<%--											</td>--%>
											<td width="40%" onclick="document.getElementById('shTpltTempletVO.templetid').value='<s:property value="#pro.templetid" />';document.getElementById('shTpltTempletVO.brand').value='<s:property value="#pro.brand" />';doSub();" style="color:yellow;">
												<s:property value="#pro.templetname" />
											</td>
											<td width="20%">
												<s:property value="#pro.monthfee" />
											</td>
											<td width="10%">
											
												<s:property value="#pro.brand" />
											</td>
											<td width="30%">
											<s:if test="#pro.description.length()>15">
											<s:property value="#pro.description.substring(0,15)+'...'" /><a href="javascript:void(0);" style="color:yellow;" onclick="showDetailInfo('<s:property value="#pro.description"/>');">
											
											详情
											</a>
											</s:if>
											<s:elseif test="#pro.description!=null&&#pro.description.length()>0">
												<s:property value="#pro.description+'...'" /><a href="javascript:void(0);" style="color:yellow;" onclick="showDetailInfo('<s:property value="#pro.description"/>');">
											详情
											</a>
											</s:elseif>
											</td>
                                          </tr>
									</s:iterator>
								</s:if>

							</table>


						</div>
						
					
					</div>

				</div>
					<!-- 分页 -->
					<pg:paginator recordsCount="${totalsize }" pageSize="${pagesize }" page="${page }" linkUrl="hubprodinstall/cardInfoConfirm.action" />
					
					<div class="openwin_tip openwin_big div804w515h" id="openWinLoading">
					<div class="bg loading tc">
						<div class="blank60"></div>
						<div class="blank60"></div>
						<img src="${sessionScope.basePath }images/loading.gif" class=""
							alt="处理中..." />
						<div class="blank30"></div>
						<p class="fs24   lh2">
							系统正在读取SIM卡信息，请稍候...
						</p>
						<p class=" fs18  lh2 yellow"></p>
					</div>
				</div>
				<div class="openwin_tip" id="openWin1">
				  	<div class="bg"></div>
					<div class=" blank60"></div>
					<div class=" blank60"></div>
					<div class="  blank10n"></div>
					<p class="fs28 lh2 pl142" id="winText" name="winText">
						
					</p>
  					<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
    					<a title="确认" href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();">确认</a> 
    					
    				</div>
				   </div>
			</div>
		</div>
			

			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
	<script type="text/javascript">
removeAclick();
</script>
</html>























