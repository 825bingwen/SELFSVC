<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
					<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) {
<%--	if (submitFlag == 0) {--%>
<%--		submitFlag = 1;--%>
<%----%>
<%--		document.getElementById("curMenuId").value = curmenu;--%>
<%----%>
<%--		document.actform.target = "_self";--%>
<%--		document.actform.action = "${sessionScope.basePath }hubprodinstall/getCardInfo.action";--%>
<%--		document.actform.submit();--%>
<%----%>
<%--	}--%>
}

// ��������¼�
document.onkeydown = pwdKeyboardDown;

function pwdKeyboardDown(e) {
	var key = GetKeyCode(e);

	//����
	if (key == 77) {
		preventEvent(e);
	}

	if (!KeyIsNumber(key)) {
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
document.onkeyup = pwdKeyboardUp;
function pwdKeyboardUp(e)
{
	//8��32��66��77 ����
    //82��220 ����
    //13��89��221 ȷ��
    //80 ��ӡ
    //85 ��һҳ
    //68 ��һҳ
    
	//���ռ�����
	var key = GetKeyCode(e);
     
    //8:backspace 32:�ո� B:66 M:77
    if (key == 8 || key == 32 || key == 66 || key == 77)
    {
    	return false;
    }
    
    //82:R 220:����
	if (key == 82 || key == 220)
	{
		goback("<s:property value='curMenuId' />") ;
   		return ;
	}
}

function doSub(){

	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.actform.target = "_self";
		
		document.actform.action = "${sessionScope.basePath }hubprodinstall/cardInfoConfirm.action";
		document.actform.submit();	
	}			
}

function doCancle()
{
	if (submitFlag == 0)
	{
		submitFlag = 1;
		document.getElementById("errormessage").value="���֤��Ϣ��ȡ�������飡";
		document.actform.target = "_self";
		document.actform.action = "${sessionScope.basePath}hubprodinstall/handleError.action";
		document.actform.submit();	
	}			
}
</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<input type="hidden" name="errormessage" id="errormessage" />
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
						<a href="#" class="on">2. ���֤��Ϣ��ȡ</a>
						<a href="#">3. ��Ʒѡ��</a>
						<a href="#">4. ����ѡ��</a>
						<a href="#">5. ����ȷ��</a>
						<a href="#">6. �����ɷ�</a>
						<a href="#">7. ȡ����ӡ��Ʊ</a>
					</div>

					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank40"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>���֤��Ϣȷ��
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							<table width="100%" class="tb_blue" align="center">
								<tr>
									<th width="40%" class="tc">
										����
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardName" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										�Ա�
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardSex" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										���֤��
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardNo" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤����ַ
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardAddr" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤����ʼʱ��
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardStartTime" /> </span>
									</td>
								</tr>
								<tr>
									<th width="40%" class="tc">
										֤������ʱ��
									</th>
									<td width="60%" class="tc">
										<span class="yellow fs20"> <s:property
												value="idCardVO.idCardEndTime" /> </span>
									</td>
								</tr>
							</table>
							<div class="blank20"></div>
							<div class="btn_box tc">
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doSub();">ȷ��</a>
								</span>
								<span class=" mr10 inline_block "><a href="#"
									class="btn_bg_146" onmousedown="this.className='key_down'"
									onmouseup="this.className='btn_bg_146';doCancle();">ȡ��</a>
								</span>
							</div>
						</div>
					</div>
					<div class=" clear"></div>

				</div>
			</div>
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script type="text/javascript">
removeAclick();
</script>
</html>
