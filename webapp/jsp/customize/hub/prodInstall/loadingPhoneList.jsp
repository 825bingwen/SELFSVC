<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
		<META HTTP-EQUIV="pragma" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"/>
		<META HTTP-EQUIV="Expires" CONTENT="0"/>
	    <meta http-equiv="Page-Exit" ; content="blendTrans(Duration=0.5)"/>
		<link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js">
       </script>
		<script type="text/javascript" src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
       </script>
	</head>
	<body onload="openWindow_wait();">
		<form name="dutyInfoForm" method="post" target="_self">
      <%@ include file="/titleinc.jsp"%>
          <div class="main" id="main">
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>
							ѡ�ſ�������
						</h2>
						<div class="blank10"></div>
						<a href="#">1. ����Э��ȷ��</a>
						<a href="#">2. ���֤��Ϣ��ȡ</a>
						<a href="#">3. ��Ʒѡ��</a>
						<a href="#" class="on">4. ����ѡ��</a>
						<a href="#">5. ����ȷ��</a>
						<a href="#">6. �����ɷ�</a>
						<a href="#">7. ȡ����ӡ��Ʊ</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank15"></div>
						<div class="p40">
							<div class="popupWin fs28 credit_pls_wait" id="pls_wait">
								<div class="bg"></div>
								<p class="mt40">
									<img src="${sessionScope.basePath }images/loading.gif"
										alt="��ѯ��..." />
								</p>
								<p class="tips_txt">
									���ڲ�ѯ�����Ժ�......
								</p>
								<div class="line"></div>
								<div class="popup_banner"></div>
							</div>
						</div>
						<div class=" clear"></div>
					</div>
				</div>
			</div>

			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
var isDone = 0;
openWindow_wait = function() 
{
	if (isDone == 0) 
	{
		isDone = 1;
		turnToMain();
	}
}

function turnToMain() 
{
	wiWindow = new OpenWindow("pls_wait", 804, 515);//�򿪵�����������
	window.location.href = "${sessionScope.basePath}hubprodinstall/ShowTelNumbers.action?curMenuId=<%=curMenuId%>";
}

removeAclick();
</script>

</html>