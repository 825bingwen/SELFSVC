<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
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
//��ֹҳ���ظ��ύ
var submitFlag = 0;

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
		
		document.getElementById("searchType").value="3";
				
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
		document.forms[0].submit();
	}
}
function searchFunc(searchType)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
	
		document.getElementById("searchType").value=searchType;
		
		document.forms[0].target = "_self";
		document.forms[0].action = "${sessionScope.basePath }reception/receptionFunc_hub.action";
		document.forms[0].submit();
	}
}

// ��һҳ
function nextPage(linkURL)
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		
		document.actform.target="_self";
		document.actform.action=linkURL;
		document.actform.submit();
	}
}

	function goProd(ncode, region, brand)
	{
		if (submitFlag == 0)
		{
			submitFlag = 1;
			
			document.getElementById("curMenuId").value = "recProductOn";
			document.getElementById("searchType").value = "3";
			
			document.actform.target="_self";
			document.actform.action = "${sessionScope.basePath}quickpublish/prodDetail.action?prodID=" + ncode + "&region=" + region + "&brand=" + brand;
			document.actform.submit();	
		}
	}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" id="searchType" name="searchType" />
			<input type="hidden" id="searchLetter" name="searchLetter"
				value="<s:property value='searchLetter' />" />

			<%@ include file="/titleinc.jsp"%>

			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>

				<div class="service_brand w966 m0auto">
					<span class="tips mt0">������"<s:property value='searchLetter' />"�Ľ�����£�������鿴���ҵ��</span>

					<div class="brand_list">
						<a href="javascript:void(0);" class="btn_bg_205"
							onmousedown="this.className='btn_bg_205_hover'"
							onmouseup="this.className='btn_bg_205'"
							onclick="searchFunc('3'); return false;"> <span>��������</span> </a>
					</div>

					<div class="service_list " style="height: 360px;">
						<s:if
							test="null != menuMinChildNode && menuMinChildNode.size() > 0">
							<ul class="clearfix">
								<s:iterator value="menuMinChildNode" id="menu">
									<!-- sh_prod_config���еĲ�Ʒ�����⴦�� -->
									<s:if test="#menu.isProd == 1">
										<li>
											<a class="awrap" href="javascript:void(0)"
												onclick="goProd('<s:property value='#menu.prodConfigPO.ncode' />', '<s:property value='#menu.prodConfigPO.areaCode' />', '<s:property value='#menu.prodConfigPO.brand' />'); return false;">
												<h2>
													<s:property value='#menu.prodConfigPO.prodName' />
												</h2>
												<h3>
													&nbsp;
													<s:property value='#menu.prodConfigPO.prodName' />
												</h3>
											</a>
										</li>
									</s:if>
									<s:else>
										<li>
											<a class="awrap" href="javascript:void(0)"
												onclick="topGo('<s:property value='#menu.menuid' />', '<s:property value='#menu.guiobj' />'); return false;">
												<h2>
													<s:property value='#menu.menuname' />
												</h2> <s:if test="#menu.tiptext == null || #menu.tiptext == ''">
													<h3>
														&nbsp;Ϊ���ṩ
														<s:property value='#menu.menuname' />
														ҵ��ͨ����
													</h3>
												</s:if> <s:else>
													<h3>
														&nbsp;
														<s:property value='#menu.menuname' />
													</h3>
												</s:else> </a>
										</li>
									</s:else>
								</s:iterator>
							</ul>
						</s:if>
						<s:else>
							<div class="showbutton02" align="center">
								<span class="yellow"><font size="10px;">
										�Բ���û����������Ҫ����Ϣ�� </font>
								</span>
							</div>
						</s:else>
					</div>

					<pg:paginator recordsCount="${recordCount }"
						pageSize="${pageSize }" page="${page }"
						linkUrl="reception/searchLetter_hub.action" />

					<div
						class="service_quick_search m0auto mt_18 clearfix showbutton01">
						<p style="margin-left:90px;_margin-left:48px;" class="brand ml50">
							<a class="clearfix" href="javascript:void(0);"
								onclick="searchFunc('1'); return false;"> <span
								class="cfff200">������鿴</span> <span>ѡ��ҵ����࣬���ٲ�<br />�������õ�ҵ��</span>
							</a>
						</p>

						<p class="brand pbleft02">
							<a class="clearfix" href="javascript:void(0);"
								onclick="searchFunc('2'); return false;"> <span
								class="cfff200">��Ʒ�Ʋ鿴</span> <span>ѡ������Ʒ�ƣ����ٲ�<br />����ʹ�õ�ҵ��</span>
							</a>
						</p>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
</html>
