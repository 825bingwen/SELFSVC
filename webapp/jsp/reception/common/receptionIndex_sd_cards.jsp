<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
	// modify begin g00140516 2012/10/12 R003C12L10n01 OR_HUB_201206_597
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc.action";
		document.forms[0].submit();
	}
	// modify end g00140516 2012/10/12 R003C12L10n01 OR_HUB_201206_597
}

// 搜索方式切换
function searchFunc(searchType){
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("searchType").value=searchType;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/initFunctionList_sd.action";
		document.forms[0].submit();
	}
}

// 按品牌搜索
function search_Cards(Cards){
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("searchCards").value=Cards;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/searchCards_sd.action";
		document.forms[0].submit();
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="searchCards" name="searchCards" />
			<input type="hidden" id="searchType" name="searchType" />
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<h1><span></span>业务办理</h1>
  				<div class="box service_home w806 m0auto">
    				<div class="service_types">
      					<ul class="clearfix">
        					<li class=" service_brand_1">
        						<a href="javascript:void(0);" onclick="search_Cards('BrandGotone'); return false;">
        							<div class="awrap">
          								<h2>全球通</h2>
          								<h3><span class="arrow_3dot"></span>提供办理以下业务：</h3>
          								<p><s:property value='BrandGotone_sd' /></p>
            						</div>
         	 					</a>
         	 				</li>
         	 				
        					<li class="service_brand_2">
        						<a href="javascript:void(0);" onclick="search_Cards('BrandMzone'); return false;">
        							<div class="awrap">
          								<h2>动感地带</h2>
          								<h3><span class="arrow_3dot"></span>提供办理以下业务：</h3>
          								<p><s:property value='BrandMzone_sd' /></p>
            						</div>
          						</a>
          					</li>
          					
        					<li class=" service_brand_3 last">
        						<a href="javascript:void(0)" onclick="search_Cards('BrandSzx'); return false;">
        							<div class="awrap">
          								<h2>神州行</h2>
          								<h3><span class="arrow_3dot"></span>提供办理以下业务：</h3>
          								<p><s:property value='BrandSzx_sd' /></p>
            						</div>
          						</a>
          					</li>
      					</ul>
    				</div>
    				
    				<div class="service_quick_search mt40 clearfix">    					
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
