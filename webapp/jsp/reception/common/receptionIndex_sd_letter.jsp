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

// 按首字母搜索
function searchFristLetter(Letter){
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.getElementById("searchLetter").value=Letter;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/searchLetter_sd.action";
		document.forms[0].submit();
	}
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="searchType" name="searchType" />
			<input type="hidden" id="searchLetter" name="searchLetter" />
		
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="box service_search m0auto">
    				<h2 class="">请点击要搜索的业务首字的拼音首字母：<br />（如：搜索“手机阅读”可点击“s”后，在列表中进行选择）</h2>
    				
    				<ul class="keyboard_list clearfix">
      					<li class="clearfix">
							<a href="javascript:void(0);" onclick="searchFristLetter('Q'); return false;">Q</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('W'); return false;">W</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('E'); return false;">E</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('R'); return false;">R</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('T'); return false;">T</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('Y'); return false;">Y</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('U'); return false;">U</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('I'); return false;">I</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('O'); return false;">O</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('P'); return false;">P</a>
						</li>
						<li class="clearfix pl31">
							<a href="javascript:void(0);" onclick="searchFristLetter('A'); return false;">A</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('S'); return false;">S</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('D'); return false;">D</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('F'); return false;">F</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('G'); return false;">G</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('H'); return false;">H</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('J'); return false;">J</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('K'); return false;">K</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('L'); return false;">L</a>
						</li>
						<li class="clearfix pl62">
							<a href="javascript:void(0);" onclick="searchFristLetter('Z'); return false;">Z</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('X'); return false;">X</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('C'); return false;">C</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('V'); return false;">V</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('B'); return false;">B</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('N'); return false;">N</a>
							<a href="javascript:void(0);" onclick="searchFristLetter('M'); return false;">M</a>
						</li>	
    				</ul>
  				</div>
  				
				<div class="service_quick_search m0auto mt_18 clearfix">
      				<p class="brand pbleft01" style="margin-left: 90px;">
						<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('1'); return false;">
							<span class="cfff200">按分类查看</span> <span>选择业务分类，快速查<br />找您适用的业务</span>
						</a>
					</p>
      				<p class="brand pbleft02">
						<a class="clearfix" href="javascript:void(0);" onclick="searchFunc('2'); return false;">
							<span class="cfff200">按品牌查看</span> <span>选择您的品牌，快速查<br />找您使用的业务</span>
						</a>
					</p>
    			</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
