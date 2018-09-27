<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%	
	// 是否支持金属键盘操作。1，支持
	String keyFlag = (String) PublicCache.getInstance().getCachedData(Constants.SH_OPERATION_KEYFLAG);
	
	// SIM卡商RPS系统URL
	String rpsUrl = (String) PublicCache.getInstance().getCachedData("SH_BLANKCARDSERV_URL");
%>
<html>
<head>
<title>移动自助终端</title>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content=""/>
<meta http-equiv="description" content=""/>
<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/MoveCardManager.js"></script>
<script type="text/javascript">
var submitFlag = 0;

// 弹出div
var divFlag = "";

//82、220 返回		
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
		return false;
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
		goback("<s:property value = 'curMenuId'/>");
	}
	<%	
	if ("1".equals(keyFlag))
	{
	%>
		if(key ==  48)
		{
			openWindow('openWin1');
		}
		<s:iterator value="serverNumList" id="po" status="st">
			if(key ==  <s:property value="#st.index + 49" />)
			{
				<s:if test=" ''!= #po.telnum && null!= #po.telnum">
					selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />');
				</s:if>
			}
		</s:iterator>
		
		<s:if test="pageCount > 1">
		// 上翻
		if (key == 85)
		{
			var page = parseInt('<s:property value="page" />')-1;
			
			if(page>0)
			{
				nextPage("${sessionScope.basePath }chooseTel/telNumResult.action?page="+page);
			}

		}
		// 下翻
		else if (key == 68)
		{
			<s:if test="page < pageCount">
				var page = parseInt('<s:property value="page" />')+1;
				nextPage("${sessionScope.basePath }chooseTel/telNumResult.action?page="+page);
			</s:if>
		}
	</s:if>

		// 按确认键
		if(key ==  89 && divFlag != '')
		{
			windowClose();
			gotoPrintSuccess();
		}
		// 按清除键
		if(key ==  77 && divFlag != '')
		{
			windowClose();
		}	
	<%
	}
	%>
}

function goback(curmenu) 
{
	//已经选择了月份或者点击了返回，等待应答，不再执行任何操作
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		// 等待提示
		if (document.getElementById("backWaitingFlag").value == "1")
		{
			openRecWaitLoading_NX("recWaitLoading");
		}

		document.getElementById("curMenuId").value = curmenu;
		document.actform.target="_self";
		document.actform.action="${sessionScope.basePath }prodInstall/selectRule.action";
		document.actform.submit();				
	}			
}

// 选择号码
function selectTelNum(telnum, payfee, orgid)
{
	<s:iterator value="serverNumList" id="po" status="st">
		<%	
		if ("1".equals(keyFlag))
		{
		%> 	
			<s:if test="'' != #po.telnum && null != #po.telnum">
				document.getElementById('telNum_<s:property value="telnum" />').innerHTML = '<s:property value="telnum" />(按<s:property value='#st.index + 1' />键)';
			</s:if>
			<s:else>
				document.getElementById('telNum_<s:property value="telnum" />').innerHTML = '<s:property value="telnum" />';
			</s:else>
		<%
		}
		else
		{
		%>
			document.getElementById('telNum_<s:property value="telnum" />').innerHTML = '<s:property value="telnum" />';
		<%
		}
		%>
	</s:iterator>
	
	// 设置选中的号码为粗体			
	document.getElementById('telNum_'+telnum).innerHTML = '<B>'+telnum+'</B>';
	
	if (telnum == '')
	{
		document.getElementById('chooseNum').style.visibility='hidden';
		document.getElementById('telnum').value = '';
		return;
	}
	
	// 显示受理费用
	var displayFee;
	if (/^\d+$/.test(payfee))
	{
		displayFee = (Number(payfee)/100).toFixed(2) + "元"
	}
	else
	{
		displayFee = payfee;
	}
	
	
	document.getElementById('telnum').value = telnum;
	document.getElementById("orgid").value = orgid;
	
	document.getElementById('chooseNum').style.visibility='hidden';
	document.getElementById('chooseNum').innerHTML = '<p class="fs16 tc">' + telnum + '</p><p class="fs14 tc">选号费：' + displayFee + ' </p>';
	document.getElementById('chooseNum').style.visibility='visible';			
	
	document.getElementById('winText').innerHTML = '<span class="yellow">您选择开户：</span><br />' + telnum + '<span class="ml20">选号费：</span><span class="">' + displayFee + '</span>';
}

// 下一页
function nextPage(linkURL)
{			
	// 执行查询
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading_NX("recWaitLoading");
		
  		document.actform.target = "_self";
		document.actform.action = linkURL;
		document.actform.submit();
	}
}

// 关闭弹出div时，清空divFlag
function windowClose()
{
	divFlag = "";
	
	wiWindow.close();
}

// 号码资源占选
function telnumTmpPick()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}prodInstall/telnumTmpPick.action";
	
	// 参数
	var params = "telnum=" + document.getElementById('telnum').value;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

// 校验空白卡资源是否可用
function chkBlankNo()
{
	// 返回 0 1~~失败原因
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}prodInstall/chkBlankNo.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "orgid=" + document.getElementById("orgid").value ;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

// 空白卡资源暂选
function blankCardTmpPick()
{
	// 返回 1 0~~imsi~~iccid~~smspNumber
	var ret = 1;// 0:成功 1:失败
	
	// URL
	var url = "${sessionScope.basePath}prodInstall/blankCardTmpPick.action";
	
	// 参数
	var params = "blankno=" + document.getElementById('blankno').value + "&";
	    params = params + "telnum=" + document.getElementById("telnum").value;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

// 号卡校验
function chkTelSimcard()
{
	// 返回 1 0~~imsi~~iccid~~smspNumber  0:成功 1:失败
	var ret = 1;
	
	// URL
	var url = "${sessionScope.basePath}prodInstall/chkTelSimcard.action";
	
	// 参数
	var params = "telnum=" + document.getElementById('telnum').value + "&";
	    params = params + "iccid=" + document.getElementById("iccid").value + "&";
	    params = params + "prodid="+document.getElementById("prodid").value + "&";
	    params = params + "orgid="+document.getElementById("orgid").value;
	
	// 调用
	var loader = new net.ContentLoaderSynchro(url, netload = function () {
			ret = this.req.responseText;
	}, null, "POST", params, null);
	
	// 返回
	return ret;
}

//出现异常
function setException(errorMsg) 
{			
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		openRecWaitLoading_NX("recWaitLoading");
		
		document.getElementById("errormessage").value = errorMsg;

		//异常处理，只要出现了异常就要记录日志  
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }prodInstall/installError.action";
		document.actform.submit();
	}			
}	
</script>
</head>
<body onload="window.focus();" scroll="no">
	<form id="actform" name="actform" method="post">
		
		<input type="hidden" id="errormessage" name="errormessage" value="" />
		<!-- 身份证信息 -->
		<!-- 姓名 -->
		<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
		<!-- 性别 -->
		<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
		<!-- 身份证号码 -->
		<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
		<!-- 证件地址 -->
		<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
		<!-- 开始时间 -->
		<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
		<!-- 结束时间 -->
		<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
		<!-- 卡信息 -->
		<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
		<!-- 照片信息 -->
		<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
		
		<!-- 套餐信息 -->
		<!-- 模板ID -->
		<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='<s:property value="tpltTempletPO.templetId" />' />
		<!-- 模板名称 -->
		<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='<s:property value="tpltTempletPO.templetName" />' />
		<!-- 产品ID -->
		<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='<s:property value="tpltTempletPO.mainProdId" />' />
		<!-- 产品名称 -->
		<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='<s:property value="tpltTempletPO.mainProdName" />' />
		<!-- 品牌 -->
		<input type="hidden" id="brand" name="tpltTempletPO.brand" value='<s:property value="tpltTempletPO.brand" />' />
		<!-- 套餐月费 -->
		<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='<s:property value="tpltTempletPO.monthFee" />' />
		<!-- 预存费用 -->
		<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='<s:property value="tpltTempletPO.minFee" />' />
		
		<!-- 选号信息 -->
		<!-- 地市 -->
		<input type="hidden" id="region" name="region" value="<s:property value='region'/>" />
		<!-- 组织机构ID -->
		<input type="hidden" id="orgid" name="orgid" value="<s:property value='orgid'/>" />
		<!-- 地市名称 -->
		<input type="hidden" id="regionname" name="regionName" value="<s:property value='regionName'/>" />
		<!-- 选号规则 -->
		<input type="hidden" id="sele_rule" name="sele_rule" value="<s:property value='sele_rule'/>"/>
		<!-- 前缀 -->
		<input type="hidden" id="tel_prefix" name="tel_prefix" value="<s:property value='tel_prefix'/>"/>
		<!-- 后缀 -->
		<input type="hidden" id="tel_suffix" name="tel_suffix" value="<s:property value='tel_suffix'/>"/>
		<!--空白卡序列号 -->
		<input type="hidden" id="blankno" name="blankno" value=""/>
		<!--手机号码 -->
		<input type="hidden" id="telnum" name="telnum" value="" />
		<!--IMSI -->
		<input type="hidden" id="imsi" name="imsi" value="" />
		<!--ICCID -->
		<input type="hidden" id="iccid" name="iccid" value="" />
		<!--短消息中心号码 -->
		<input type="hidden" id="smsp" name="smsp" value="" />
		<!-- 产品ID -->
		<input type="hidden" id="prodid" name="prodid" value="<s:property value='prodid'/>" />
		<%-- 是否打印小票  1-不打印，0-打印 --%>
		<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
		
		<%@ include file="/titleinc.jsp"%>
		
		<div class="main" id="main">			
			<%@ include file="/customer.jsp"%>
			
			<div class="pl30">
				<div class="b257 ">
					<div class="bg bg257"></div>
					<h2><%=menuName %>流程</h2>
					<div class="blank10"></div>
					<a href="javascript:void(0)">1. 入网协议确认</a> 
					<a href="javascript:void(0)">2. 读取身份证信息</a>
   					<a href="javascript:void(0)">3. 产品选择</a>
   					<a href="javascript:void(0)" class="on">4. 号码选择</a>
   					<a href="javascript:void(0)">5. 设置服务密码</a>
   					<a href="javascript:void(0)">6. 费用确认</a>
   					<a href="javascript:void(0)">7. 开户缴费</a>
   					<a href="javascript:void(0)">8. 取卡打印发票</a>
				</div>
			</div>
			
			<div class="b712">
				<div class="bg bg712"></div>
				<div class="blank10"></div>
				<div class="p40">
					<div class="blank10"></div>
					<p class="tit_info_fs20">
						<span class="bg"></span>尊敬的用户，请点击选定号码，每次只能预定<span class="yellow">1</span>个号码。
					</p>
					<div class="blank10"></div>
					<div class="num_dis_fs16">
						<s:iterator value="serverNumList" id="po" status="st">
						<%	
						if ("1".equals(keyFlag))
						{
						%> 	
							<s:if test="'' != #po.telnum && null != #po.telnum">
								<a id="telNum_<s:property value="telnum" />" href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />');" ><s:property value="telnum" />&nbsp;(按<s:property value='#st.index + 1' />键)</a>
							</s:if>
							<s:else>
								<a id="telNum_<s:property value="telnum" />" href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />');" ><s:property value="telnum" /></a>
							</s:else>
						<%
						}
						else
						{
						%>
							<a id="telNum_<s:property value="telnum" />" href="javascript:void(0);" onclick="selectTelNum('<s:property value="telnum" />', '<s:property value="fee" />', '<s:property value="orgid" />');" ><s:property value="telnum" /></a>
						<%
						}
						%>
						</s:iterator>
						<div class="clear"></div>
 						</div>
 						
 						<s:if test="pageFlag == 'true'">
 							<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="prodInstall/telnumResult.action" />
 						</s:if>
 						
 						<div class="fs18" style="padding-top:10px;margin-left:160px;">  
    					<div class="fright2"> 
    						<span class="fl pt15" >已选定号码：</span> 
							<a href="javascript:void(0)" class="bt_choosenum fl" style=" visibility:hidden" id="chooseNum">
								<p class="fs16 tc" id="telnumText"></p>
								<p class="fs14 tc" id="payfeeText"></p>
        					</a>
        					<%	
							if ("1".equals(keyFlag))
							{
							%>
								<a href="javascript:void(0)" class="bt6 fr relative fl ml20"  onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';openWindow('openWin1')">预订&nbsp;(按0键)</a> 
							<%
							}
							else
							{
							%> 
								<a href="javascript:void(0)" class="bt6 fr relative fl ml20"  onmousedown="this.className='bt6on fl relative ml20'" onmouseup="this.className='bt6 fl relative ml20';openWindow('openWin1')">预订</a> 
							<%
							}
							%>
        				</div>
					</div>
				</div>	
				
				<div class=" clear"></div>
				
				<!--弹出窗-->
				<div class="openwin_tip div708w392h" id="openWin1">
				  	<div class="bg"></div>
					<div class=" blank60"></div>
					<div class="  blank10n"></div>
					<p class="fs28 lh2 pl142" id="winText" name="winText">
					</p>
  					<div class="tc">
					    <div class=" clear "></div>
					    <div class=" blank30 "></div>
					    <%	
						if ("1".equals(keyFlag))
						{
						%>
							<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';windowClose();gotoPrintSuccess();">确认(按确认键)</a> 
    						<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';windowClose()">取消(按清除键)</a> 
						<%
						}
						else
						{
						%> 
							<a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';windowClose();gotoPrintSuccess();">确认</a> 
    						<a href="javascript:void(0)" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';windowClose()">取消</a> 
						<%
						}
						%>	
    				</div>
				</div>
				
				<div class="openwin_tip openwin_big div804w515h" id="openWinLoading">
  					<div class="bg loading tc">
					    <div class="blank60"></div>
					    <div class="blank60"></div>
					    <img src="${sessionScope.basePath }images/loading.gif"  class="" alt="处理中..."/>
					    <div class="blank30"></div>
					    <p class="fs24   lh2">正在执行预定信息处理，请稍候...</p>
					    <p class=" fs18  lh2 yellow"></p>
  					</div>
				</div>
				
				<script type="text/javascript">
				openWindow = function(id)
				{
					// 检查是否已选中
					if (document.getElementById('telnum').value == '')
					{
						return;
					}
					
					divFlag = "openWin1";
					wiWindow = new OpenWindow("openWin1",708,392);				
				}

				// 流程
				
				function gotoPrintSuccess()
				{
					// 关闭提示
					windowClose();
					
					// 等待框
					openRecWaitLoading();
					
					// 号码资源占选
					var ret = telnumTmpPick();
					if (ret != 0)
					{
						setException("号码资源占选失败！");
						return;
					}
					
					// 建立与RPS系统的连接
					var blankCardServUrl = "<%=rpsUrl %>";
				    var connectRPS = InitWebService(blankCardServUrl);	    
			        if(connectRPS != 0)
			        {
			        	var error = GetLastError();
			            var re=/用户已登陆/i;
			            
			            // 查找字符串。
			        	var r = error.search(re);
			        	
			        	//上次未正常注销
						if(r != -1)
						{
							setException("此鉴权卡上次未正常注销RPS系统，请稍候再试！"+error);
						}
						setException("鉴权失败，失败信息："+error);
			         }
			         else
			         {
			             //鉴权成功！
			         }
			         
			         // 写卡前判断写卡器是否已经插入卡
			         ret = IsCardExist();
			         if (ret != 0)
			         {
				         // 将空白卡走到读卡位置
				         ret = MoveCardToWrite();
				         if (ret != 0)
				         {
				         	setException("将空白卡走到读卡位置失败！");
				         }
			         }
			         
			         // 写卡前判断写卡器是否已经插入卡
			         ret = IsCardExist();
			         if (ret != 0)
			         {
			         	setException("写卡器内未插入空白卡！");
			         }
			         
			         // 获取空白卡序列号
			         document.getElementById('blankno').value = GetICCSerial();
			         
					// 校验卡资源是否可用
					ret = chkBlankNo();
					if (ret != 0)
					{
						setException("校验卡资源是否可用失败！");
						return;
					}
					
					// 空白卡资源暂选
					ret = blankCardTmpPick();
					if (ret == 1)
					{
						setException("空白卡资源暂选失败！");
						return;
					}
					else if (ret == 2)
					{
						setException("调用空白卡资源暂选接口失败！");
						return;
					}
					else
					{
						var imsi = ret.split('~~')[1];
						var iccid = ret.split('~~')[2];
						var smsp = ret.split('~~')[3];
						document.getElementById('imsi').value = imsi;
						document.getElementById('iccid').value = iccid;
						document.getElementById('smsp').value = smsp;
					}
					
					// 号卡校验
					ret = chkTelSimcard();
					if (ret != 0)
					{
						var errMsg = ret.split('~~')[1];
						setException(errMsg);
						return;
					}
					
					// 提交 设置密码
					setTimeout(
						function(){
							document.actform.target="_self";
							document.actform.action="${sessionScope.basePath }prodInstall/setPasswd.action";
							document.actform.submit();							
						},
					500);
				}
				</script>
				<!--弹出窗结束-->	
			</div>	
		</div>	
		
		<%@ include file="/backinc.jsp"%>
	</form>
</body>
</html>
