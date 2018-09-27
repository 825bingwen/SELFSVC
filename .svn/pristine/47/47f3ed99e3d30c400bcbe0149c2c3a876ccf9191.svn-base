<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script type="text/javascript"
			src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript"
			src="${sessionScope.basePath }js/dialyzer.js"></script>

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

// 上一页
function goback(menuid)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("curMenuId").value = menuid;
		
		document.getElementById("searchType").value="2";
		
		document.forms[0].action = "${sessionScope.basePath }reception/initFunctionList_sd.action";
		document.forms[0].submit();
	}
}

// 搜索方式切换
function searchFunc(searchType){
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("searchType").value=searchType;
		
		// document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/initFunctionList_sd.action";
		document.forms[0].submit();
	}
}

// 按分类搜索
function search_Cards(Cards){
	if (submitFlag == 0)
	{
		submitFlag = 1;
				
		document.getElementById("searchCards").value=Cards;
		
		// document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/searchCards_sd.action";
		document.forms[0].submit();
	}
}

	// 下一页
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
	
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="searchCards" name="searchCards"
				value="<s:property value='searchCards'/>" />
			<input type="hidden" id="searchType" name="searchType" />

			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="service_brand w966 m0auto">
					<div class="brand_list mt_26" style="margin-top: 0px;">
						<s:if test="'BrandGotone'!= searchCards">
							<a href="javascript:void(0);" class="btn_bg_205"
								onmousedown="this.className='btn_bg_205_hover'"
								onmouseup="this.className='btn_bg_205'"
								onclick="search_Cards('BrandGotone'); return false;"> <span>全球通业务</span>
							</a>
						</s:if>
						<s:if test="'BrandMzone'!=searchCards">
							<a href="javascript:void(0);" class="btn_bg_205"
								onmousedown="this.className='btn_bg_205_hover'"
								onmouseup="this.className='btn_bg_205'"
								onclick="search_Cards('BrandMzone'); return false;"> <span>动感地带</span>
							</a>
						</s:if>
						<s:if test="'BrandSzx'!=searchCards">
							<a href="javascript:void(0);" class="btn_bg_205"
								onmousedown="this.className='btn_bg_205_hover'"
								onmouseup="this.className='btn_bg_205'"
								onclick="search_Cards('BrandSzx'); return false;"> <span>神州行业务</span>
							</a>
						</s:if>
						<a href="javascript:void(0);" class="btn_bg_205"
							onmousedown="this.className='btn_bg_205_hover'"
							onmouseup="this.className='btn_bg_205'"
							onclick="searchFunc('2'); return false;"> <span>返回品牌列表>></span>
						</a>
					</div>

					<div class="service_list" style="height: 360px;">
						<s:if
							test="menuMinChildNode != null && menuMinChildNode.size() > 0">
							<ul class="clearfix">
								<s:iterator value="menuMinChildNode" id="menu">
									<li>
										<a class="awrap" href="javascript:void(0);"
											onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
											<h2>
												<s:property value='#menu.menuname' />
											</h2>
											<h3>
												·
												<s:property value='#menu.tiptext' />
											</h3> </a>
									</li>
								</s:iterator>
							</ul>
						</s:if>
						<s:else>
							<div class="showbutton02" align="center">
								<span class="yellow"><font size="10px;">
										对不起，没有搜索到你要的信息！ </font>
								</span>
							</div>
						</s:else>
					</div>

					<pg:paginator recordsCount="${recordCount }"
						pageSize="${pageSize }" page="${page }"
						linkUrl="reception/searchCards_sd.action" />

					<div class="service_quick_search mt10 m0auto clearfix" style="margin-top: -5px;">
						<p class="brand pbleft01" style="margin-left: 90px;">
							<a class="clearfix" href="javascript:void(0);"
								onclick="searchFunc('1'); return false;"> <span
								class="cfff200">按分类查看</span> <span>选择业务分类，快速查<br />找您适用的业务</span>
							</a>
						</p>

						<p class="brand pbleft02">
							<a class="clearfix" href="javascript:void(0);"
								onclick="searchFunc('3'); return false;"> <span
								class="cfff200">首字母搜索</span> <span>输入业务首字的拼音首<br />字母，快速查找业务</span>
							</a>
						</p>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
