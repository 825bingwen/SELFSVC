<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%@ taglib uri="/WEB-INF/paginator.tld" prefix="pg"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		
		<script language="javascript">
			//��ֹ�ظ��ύ
			var submitFlag = 0;
			
			//82��220 ����		
			document.onkeydown = pwdKeyboardDown;
			
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
			
			document.onkeyup = pwdKeyboardUp;
			
			function pwdKeyboardUp(e) 
			{
				var key = GetKeyCode(e);
				
				//����
				if (key == 82 || key == 220) 
				{
					goback("<s:property value='curMenuId' />");
					return;
				}
				//ȷ��
				else if (key == 13 || key == 89 || key == 221)
				{
					doSub();
				}			
				// ����� M
				else if(key == 77)
				{
					wiWindow.close();
				}
			}
			
		</script>
	</head>
	
	<body scroll="no" onload="bodyLoad();">
		<form name="actform" method="post" target="_self">
			
			<!-- ���֤��Ϣ -->
			<!-- ���� -->
			<input type="hidden" id="idCardName" name="idCardPO.idCardName" value='<s:property value="idCardPO.idCardName" />' />
			<!-- �Ա� -->
			<input type="hidden" id="idCardSex" name="idCardPO.idCardSex" value='<s:property value="idCardPO.idCardSex" />' />
			<!-- ���֤���� -->
			<input type="hidden" id="idCardNo" name="idCardPO.idCardNo" value='<s:property value="idCardPO.idCardNo" />' />
			<!-- ֤����ַ -->
			<input type="hidden" id="idCardAddr" name="idCardPO.idCardAddr" value='<s:property value="idCardPO.idCardAddr" />' />
			<!-- ��ʼʱ�� -->
			<input type="hidden" id="idCardStartTime" name="idCardPO.idCardStartTime" value='<s:property value="idCardPO.idCardStartTime" />' />
			<!-- ����ʱ�� -->
			<input type="hidden" id="idCardEndTime" name="idCardPO.idCardEndTime" value='<s:property value="idCardPO.idCardEndTime" />' />
			<!-- ����Ϣ -->
			<input type="hidden" id="idCardContent" name="idCardPO.idCardContent" value='<s:property value="idCardPO.idCardContent" />' />
			<!-- ��Ƭ��Ϣ -->
			<input type="hidden" id="idCardPhoto" name="idCardPO.idCardPhoto" value='<s:property value="idCardPO.idCardPhoto" />' />
			
			<!-- �ײ���Ϣ -->
			<!-- ģ��ID -->
			<input type="hidden" id="templetId" name="tpltTempletPO.templetId" value='' />
			<!-- ģ������ -->
			<input type="hidden" id="templetName" name="tpltTempletPO.templetName" value='' />
			<!-- ��ƷID -->
			<input type="hidden" id="mainProdId" name="tpltTempletPO.mainProdId" value='' />
			<!-- ��Ʒ���� -->
			<input type="hidden" id="mainProdName" name="tpltTempletPO.mainProdName" value='' />
			<!-- Ʒ�� -->
			<input type="hidden" id="brand" name="tpltTempletPO.brand" value='' />
			<!-- �ײ��·� -->
			<input type="hidden" id="monthFee" name="tpltTempletPO.monthFee" value='' />
			<!-- Ԥ����� -->
			<input type="hidden" id="minFee" name="tpltTempletPO.minFee" value='' />
			
			<%-- �Ƿ��ӡСƱ  1-����ӡ��0-��ӡ --%>
			<input type="hidden" id="canNotPrint" name="canNotPrint" value="<s:property value = 'canNotPrint' />" />
			<%-- ֧����ʽ��ʶ 11 ���豸������ 10 �ֽ����  01 �������� --%>
			<input type="hidden" id="payTypeFlag" name="payTypeFlag" value="<s:property value = 'payTypeFlag' />"/>
			
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<!-- ��ʾ�� -->
				<!-- �ɹ���ʾ��Ϣ -->
				<div class="popup_confirm" id="openWin_xq">
					<div class="bg"></div>
					<div class="tips_title">�ײ�����</div>
					<div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_xq">
						
				  	</div>
					<div class="btn_box tc mt20">
						<span class=" inline_block ">
							<a class="btn_bg_146" href="javascript:void(0);" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">�ر�(�������)</a>
						</span>
					</div>
				</div>
				
				<%@ include file="/customer.jsp"%>
					
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>���߿���</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. ����Э��ȷ��</a> 
						<a href="javascript:void(0)">2. ��ȡ���֤��Ϣ</a>
	   					<a href="javascript:void(0)" class="on">3. ��Ʒѡ��</a> 
	   					<a href="javascript:void(0)">4. ����ѡ��</a>
	   					<a href="javascript:void(0)">5. ���÷�������</a>
	   					<a href="javascript:void(0)">6. ����ȷ��</a>
	   					<a href="javascript:void(0)">7. �����ɷ�</a>
	   					<a href="javascript:void(0)">8. ȡ����ӡСƱ</a>
					</div>
					
					<div class="b712">
						<div class="bg bg712"></div>
						<div class="blank30"></div>
						<div class="p40">
							<p class=" tit_info">
								<span class="bg"></span>��Ʒ�б�
								<span class="yellow"></span>
							</p>
							<div class="blank15"></div>
							
							<table width="100%" class="tb_blue">
								<tr>
									<th class="list_title" align="center" >
										ѡ��
									</th>
									<th class="list_title" align="center" >
										�ײ�����
									</th>
									<th class="list_title" align="center">
										�ײ��·�(Ԫ)
									</th>
									<th class="list_title" align="center">
										Ʒ��
									</th>
								</tr>
								<s:if test="tpltTempletList != null && tpltTempletList.size > 0">
									<s:iterator value="tpltTempletList" status="result" id="tpltTemplet">
										<tr align="center">
											<td>
												<input type="button" name="ids" id="<s:property value="#tpltTemplet.templetId" />" class="bt2_liebiao white" value="��  ��" onmousedown="this.className='bt2on_1 white'" 
													onmouseup="this.className='bt2_liebiao white';selectProd('<s:property value="#tpltTemplet.templetId" />','<s:property value="#tpltTemplet.description" />','<s:property value="#tpltTemplet.templetName" />','<s:property value="#tpltTemplet.mainProdId" />','<s:property value="#tpltTemplet.mainProdName" />','<s:property value="#tpltTemplet.brand" />','<s:property value="#tpltTemplet.monthFee" />','<s:property value="#tpltTemplet.minFee" />');"/>
												<br/>
											</td>
											<td>
												<s:if test="%{#tpltTemplet.templetName.length()>16}">
                                                    <s:property value='#tpltTemplet.templetName.substring(0,16)' />...
                                                </s:if>
                                                <s:else>
                                                    <s:property value='#tpltTemplet.templetName' />
                                                </s:else>
											</td>
											<td>
												<s:property value="#tpltTemplet.monthFee" /><br />
											</td>
											<td>
												<s:if test="#tpltTemplet.brand == 'BrandMzone'">
													���еش�
												</s:if>
												<s:elseif test="#tpltTemplet.brand =='BrandSzx'">
													������
												</s:elseif>
												<s:elseif test="#tpltTemplet.brand =='BrandGotone'">
													ȫ��ͨ
												</s:elseif>	
												<s:else>
											 		<s:property value='#tpltTemplet.brand' />
											 	</s:else>
											</td>
										</tr>
									</s:iterator>
								</s:if>
							</table>
						</div>
						<pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="cardInstall/selectProd.action" />
						<a href="#" class="bt10 fr mr43" onmousedown="this.className='bt10on fr mr43'" onmouseup="this.className='bt10 fr mr43';doSub(); return false;" style="display:inline;right:260px;">ȷ��(�밴ȷ�ϼ�)</a>
					</div>
				</div>
			</div>
			
			<%@ include file="/backinc.jsp"%>
		</form>
	</body>
	<script language="javascript">
	function bodyLoad()
	{
		document.getElementsByName("ids")[0].onmouseup();
	}
	</script>
</html>
<script type="text/javascript">
// ����
function goback(menuid)
{
     //��ֹ�ظ�����
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }cardInstall/agreement.action";
        document.actform.submit();
    }
}

// �ύ           
function doSub() 
{
    if (submitFlag == 0) 
    {
        submitFlag = 1; //�ύ���
        
        //�ж��Ƿ�ѡ��
        var objs = document.getElementsByName('ids');
        var flag = 0;
        
        // ���
        for(var i=0;i<objs.length;i++)
        {
            //alert(objs[i].value);
            if(objs[i].value == '���̡�')
            {
                flag = 1;
                break;
            }
        }
        
        if (flag == 1)
        {
            openRecWaitLoading();
            
            document.actform.target = "_self";
            document.actform.action = "${sessionScope.basePath }cardInstall/selectRule.action";
            document.actform.submit();
        }
        else
        {   
            submitFlag = 0;
            // ��ʾ
            showDetailInfo("��ѡ����Ӧ��Ʒ��");
        }
    }
}

//�����쳣
function setException(errorMsg) 
{           
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        openRecWaitLoading();
        
        document.getElementById("errormessage").value = errorMsg;

        //�쳣����ֻҪ�������쳣��Ҫ��¼��־  
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }cardInstall/installError.action";
        document.actform.submit();
    }           
}   

// �Ƿ���ʾ����
var isShowDetail = false;

// ѡ���Ʒ
function selectProd(id,content,templetName,mainProdId,mainProdName,brand,monthFee,minFee)
{
    // ��������ѡ���
    var objs = document.getElementsByName('ids');
    
    // ���
    for(var i=0;i<objs.length;i++)
    {
        objs[i].value = '��  ��';
    }
    
    // ѡ��
    if (document.getElementById(id).value == '��  ��')
    {
        document.getElementById(id).value = '���̡�';
    }
    else if (document.getElementById(id).value == '���̡�')
    {
        document.getElementById(id).value = '��  ��'
    }
    
    // �����Ʒģ������
    // ģ��ID
    document.getElementById("templetId").value = id;
    // ģ������
    document.getElementById("templetName").value = templetName;
    // ��ƷID
    document.getElementById("mainProdId").value = mainProdId;
    // ��Ʒ����
    document.getElementById("mainProdName").value = mainProdName;
    // Ʒ��
    document.getElementById("brand").value = brand;
    // �ײ��·�
    document.getElementById("monthFee").value = monthFee;
    // Ԥ�����
    document.getElementById("minFee").value = minFee;
    
    // �Ƿ���ʾ����
    if (isShowDetail)
    {
        // ��ʾ��Ϣ
        showDetailInfo(content);
    }
    
    isShowDetail = true;
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

// չʾ��Ʒ��ϸ��Ϣ
function showDetailInfo(content)
{  
    document.getElementById('winText_xq').innerHTML="<span class='yellow'>"+content+"</span>";
    
    wiWindow = new OpenWindow("openWin_xq", 400, 200);
}
</script>
