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
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript"
						src="${sessionScope.basePath }jsp/customize/hub/js/romoveAclick_Hub.js">
</script>
		<style type="text/css">
			.tb_blue_08,.tb_blue_08 td ,.tb_blue_08 th,.tb_num{ border:1px solid #1a9ae3; border-collapse:collapse; font-size:18px; color:#fff; height:30px;}
			.tb_blue_08 th{ background:#056e99; }	
		</style>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
		var submitFlag = 0;
		
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
			}			
		}
		
		// ��һҳ
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
		
		function confirmFee()
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				var url = "${sessionScope.basePath }hubprodinstall/installChargeType.action";
				document.forms[0].action = url;
				document.forms[0].target = "_self";
				document.forms[0].submit();
			}
		}
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
		<input type="hidden" id="telnum" name="telnum" value="<s:property value="telnum" />"/>
		<input type="hidden" id="brand" name="brand" value="<s:property value="brand" />"/>
		<input type="hidden" id="mainprodname" name="mainprodname" value='<s:property value="mainprodname" />'/>
		<input type="hidden" id="prodtempletid" name="prodtempletid" value="<s:property value="prodtempletid" />"/>
		<input type="hidden" id="receptionFee" name="receptionFee" value="<s:property value="receptionFee" />"/>
		<!-- ÿҳ���� -->
		<input type="hidden" name="pagesize" id="pagesize" value='<s:property value="#request.pagesize" />'/>
		<input type="hidden" name="totalsize" id="totalsize" value='<s:property value="#request.totalsize" />'/>
		<input type="hidden" name="pagenum" id="pagenum" value='<s:property value="#request.pagenum" />'/>
		<%@ include file="/titleinc.jsp"%>
			<div class="main" id="main">			
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>�����������̣�</h2>
      					<div class="blank10"></div>
      					<a title="����Э��ȷ��" href="#">1. ����Э��ȷ��</a>
      					<a title="���֤��Ϣ��֤" href="#">2. ���֤��Ϣ��ȡ</a>
      					<a title="��Ʒѡ��" href="#">3. ��Ʒѡ��</a>  
      					<a title="����ѡ��" href="#">4. ����ѡ��</a> 
      					<a title="����ȷ��" href="#" class="on">5. ����ȷ��</a>
      					<a title="�����ɷ�" href="#">6. �����ɷ�</a>
      					<a title="ȡ����ӡ��Ʊ" href="#">7. ȡ����ӡ��Ʊ</a>
					</div>
				</div>
				
				<div class="b712">
					<div class="bg bg712"></div>
					<div class="blank30"></div>
					<div class="p40">
						<p class=" tit_info">
							<span class="bg"></span>������ϸ�б�<span class="yellow"></span>
						</p>
						<div class="blank10"></div>
					
					<table id="infoTab" class="tb_blue_08" width="100%">
								<tr>
									<td width="25%" class="tc">����</td>
									<td width="25%" class="tc"><span class="yellow"><s:property value="#request.telnum" /></span></td>
									<td width="25%" class="tc">Ʒ��</td>
									<td width="25%" class="tc">
										<span class="yellow">
											<s:if test="#request.brand == 'BrandGotone'">
													ȫ��ͨ
											</s:if>
											<s:elseif test="#request.brand == 'BrandMzone'">
													���еش�
											</s:elseif>
											<s:else>
													������
											</s:else>
										</span>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tc">��Ʒ</td>
									<td width="75%" class="tc" colspan='3'><span class="yellow"><s:property value="#request.mainprodname" /></span></td>
								</tr>
					</table>
					<br/>
					<table id="feeTab" width="100%" class="tb_blue_08" >
	                  <tr>
	                    <th scope="col">��������</th>
	                    <th scope="col">���(Ԫ)</th>
	                    <th scope="col">����</th>
	                    <th scope="col">��������</th>
	                  </tr>
	                  <s:iterator value="itemlist" id="item" status="st">
		                  <tr>
			                    <td><s:property value="#item.itemname" /></td>
			                    <td>
			                    <s:property value="#item.itemfee" /></td>
			                    <td><s:property value="#item.itemnum" /></td>
			                    <td>
				                    <s:if test="#item.itemtype == 'Ԥ���'">
											Ԥ���
									</s:if>
									<s:elseif test="#item.itemtype != null && #item.itemtype != ''">
											�����շ�
									</s:elseif>
			                    </td>
		                  </tr>
	                  </s:iterator>
	                </table>
	                <br/>
	                <table id="feeTab" width="100%" class="tb_blue_08" >
	                  <tr>
	                    <th scope="col">װ��Ԥ��(Ԫ)</th>
	                    <th scope="col">�������(Ԫ)</th>
	                    <th scope="col">����Ʒ��</th>
	                    <th scope="col">�����ʷ�</th>
	                  </tr>
	                  <tr>
	                    <th scope="col"><s:property value="#request.installFee" /></th>
	                    <th scope="col"><s:property value="#request.lowFee"/></th>
	                    <th scope="col"><s:property value="#request.sallBrand"/></th>
	                    <th scope="col"><s:property value="#request.haveFee"/></th>
	                  </tr>
	                  </table>
					</div>	
					<!-- ��ҳ -->
					<pg:paginator recordsCount="${totalsize }" pageSize="${pagesize }" page="${page }" linkUrl="hubprodinstall/ckTelSimCard.action" />
					
					<br/>
					<div class="btn_box tc">
						    <span class=" mr10 inline_block "><a id='reckonSpan' href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';" onclick="confirmFee()">����ȷ��</a></span>
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
