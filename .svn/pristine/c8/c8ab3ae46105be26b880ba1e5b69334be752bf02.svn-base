<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.util.CurrencyUtil"%>
<%@page import="com.gmcc.boss.selfsvc.baseService.familymem.model.FamilyMemPO"%>
<%@page import="com.gmcc.boss.common.cbo.global.cbo.common.CRSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String curmenuid = (String) request
			.getAttribute(Constants.CUR_MENUID);
%>
<html>
	<head>
		<title>家庭网添加成员</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/style.css" type="text/css"
			rel="stylesheet" />			
		<link href="${sessionScope.basePath }css/reset.css" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script>
var submitFlag = 0;

// 弹出Div
var divFlag = "";

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
function addFamilyMem()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }baseService/initAddFamilyMem.action";
		document.forms[0].submit();
	}
}

// 打开弹出窗口
openWindow = function(id)
{
	divFlag = id;
	wiWindow = new OpenWindow(id, 708, 392);// 打开弹出窗口的配置
}

// 关闭弹出div时，清空divFlag信息
function windowClose()
{
	divFlag = "";
	wiWindow.close();
}

// 删除家庭网
function deleteFamilyMem()
{
	// 防止重复提交
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }baseService/deleteFamilyMem.action";
		document.actform.submit();
	}
}

// 是否显示提示
var isShowTips = false;

function selectMember(telNum,isHot,shortNum)
{
	// 迭代所有选择框
	var objs = document.getElementsByName('ids');
	
	// 选中
	if (document.getElementById(telNum).value == '【  】')
	{
		// 清空
		for(var i=0;i<objs.length;i++)
		{
			objs[i].value = '【  】';
		}
		document.getElementById(telNum).value = '【√】';
		if(1 == isHot)
		{
			alertRedErrorMsg("对不起，家庭网主号不能删除，您可以点击家庭网销户按钮来注销家庭网业务！");
			document.getElementById(telNum).value = '【  】';
			return;
		}
	}
	else if (document.getElementById(telNum).value == '【√】')
	{
		// 清空
		for(var i=0;i<objs.length;i++)
		{
			objs[i].value = '【  】';
		}
		return;
	}
	
	// 要删除成员的手机号
	setValue("memTelnum",telNum);
	
	isShowTips = true;
}

function showDelTips()
{
	if (submitFlag == 0) 
	{
		//判断是否选中
		var objs = document.getElementsByName('ids');
		var flag = 0;
		
		// 清空
		for(var i=0;i<objs.length;i++)
		{
			if(objs[i].value == '【√】')
			{
				flag = 1;
				break;
			}
		}
		
		if (flag == 1)
		{
			// 是否显示详情
			if (isShowTips)
			{
				document.getElementById('tipContent').innerHTML="您确定要删除家庭网成员"+getValue("memTelnum")+"吗？";
				openWindow('popup_confirm_tips');
			}
		}
		else
		{	
			// 提示
			alertRedErrorMsg("请选择要删除的家庭网成员！");
		}
	}
}

function doDelMem()
{
	if (submitFlag == 0) 
	{
		submitFlag = 1;	//提交标记
		openRecWaitLoading();
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath }baseService/delMemByTelNum.action";
		document.actform.submit();
	}
}

</script>
	</head>
	<BODY scroll="no" onload="window.focus();">
		<form name="actform" method="post">
			<input type="hidden" name="shortNum" value="<s:property value='shortNum'/>"/>
			<input type="hidden" name="memTelnum" value="" />
			<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				<!--滚动条-->
				<div class="box842W fl ml45IE6 relative">
					<div class="bg"></div>
					<div class="top"></div>
					<div class="con relative">
						<div class="box747W fl">
							<div class="div747w444h" >
								<!-- 列表内容 -->
								<p class="tit_info" align="left">
									<span class="bg"></span><%=menuName%></p>
								<p class="ptop180 tc p747w411h" id="inn" >

								<table width="100%" class="tb_blue">
									<tr>
										<th scope="col" >
											选择
										</th>
										<th scope="col">
											成员电话
										</th>
										<th scope="col">
											成员名称
										</th>
										<th scope="col">
											短号
										</th>
										<th scope="col">
											加入家庭时间
										</th>
										<th scope="col">
											是否主号
										</th>
										
									</tr>
									<s:iterator value="familyMemList" id="familyMemPO">
										<tr>
											<td>
												<input type="button" name="ids" id="<s:property value="#familyMemPO.telNum" />" class="bt2_liebiao white" value="【  】" onmousedown="this.className='bt2on_1 white'" 
													onmouseup="this.className='bt2_liebiao white';selectMember('<s:property value="#familyMemPO.telNum" />','<s:property value="#familyMemPO.isHost" />','<s:property value="#familyMemPO.shortNum" />');"/>
												<br/>
											</td>
											<td>
												<s:property value="#familyMemPO.telNum"/>
											</td>
											<td>
												<s:property value="#familyMemPO.name"/>
											</td>
											<td>
												<s:property value="#familyMemPO.shortNum"/>
											</td>
											<td>
												<s:property value="#familyMemPO.addDate"/>
											</td>
											<td>
												<s:if test="%{1 == #familyMemPO.isHost}">
													是
												</s:if>
												<s:else>
													否
												</s:else>
											</td>
										</tr>
									</s:iterator>
								</table>
							</div>
						</div>
						<div class="box70W fr">
							<input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
							<div class="boxPage div75w350h">
								<div class="blank10px"></div>
								<div class="box66W tc f16 div66w36h" id="gunDom" >0%</div>
							</div>
							<input type="button" class="btnDown"
								onclick="myScroll.moveDown(30)" />
						</div>
						<div class="clear"></div>
					</div>
					<div class="btm"></div>
				</div>
				<script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
				<!--滚动条结束-->
				<p align="center">
					<a href="#" class="bt7" onmousedown="this.className='bt7on'"
						onmouseup="this.className='bt7'; addFamilyMem();">添加家庭成员</a>
						
					<%--add begin sWX219697 2015-2-4 10:25:23 OR_SD_201412_777 家庭网成员删除--%>
					<a href="#" class="bt7" onmousedown="this.className='bt7on'"
						onmouseup="this.className='bt7';showDelTips(); ">删除家庭成员</a>
					<%--add end sWX219697 2015-2-4 10:25:23 OR_SD_201412_777 家庭网成员删除--%>
						
					<a href="#" class="bt7" onmousedown="this.className='bt7on'"
						onmouseup="this.className='bt7'; openWindow('popup_confirm');">家庭网销户</a>
					
				</p>
			
				<!-- 打印账单提示框 -->
	            <div class="openwin_tip div708w392h" id="popup_confirm">
	                <div class="bg"></div>
	                <div class=" blank60"></div><div class=" blank60"></div>
	               
	                <div class="  blank10n"></div>
	                <p class="fs30 yellow pt30 tc pt30 pl20">您确定要注销此家庭网吗？</p>
	                <div class=" blank10"></div>
	                <div class="tc">
	                <div class=" blank60"></div>
	                <a href="#" class=" bt4" onmousedown="this.className='bt4on'" onmouseup="this.className='bt4';wiWindow.close();deleteFamilyMem();">确定</a>
	                <a href="#" class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';wiWindow.close()">取消</a>
	                </div>
	            </div>
				<div class="popup_confirm" id="popup_confirm_tips">
                    <div class="bg"></div>
                    <div class="tips_title">提示：</div>
                    <div class="tips_body">
				        <p><i id="tipContent"></i></p>
				        <div class="blank10"></div>
				        <p class="mt30">确认操作请点击"确认"提交。</p>
				    </div>
                    <div class="btn_box tc mt20">
	                    <span class="mr10 inline_block "><a title="确认" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();doDelMem();">确认</a></span>
	                    <span class="inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">取消</a></span>
                    </div>
            	</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	
		<script type="text/javascript">
		
		//家庭网成员删除提示信息
		if("" != "<s:property value='successMessage'/>")
		{
			alertSuccessMsg("<s:property value="successMessage"/>");
		}
	</script>
</html>
