<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	// ������棬��ֹҳ����˰�ȫ���� 
	response.setHeader("Pragma", "no-cache"); 
	response.setHeader("Cache-Control", "no-store"); 
	response.setDateHeader("Expires", -1);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>�ƶ������ն�</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<META HTTP-EQUIV="pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<link href="${sessionScope.basePath }css/style.css" type="text/css" rel="stylesheet" />
		<link href="${sessionScope.basePath }css/reset.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="${sessionScope.basePath }js/public.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/script.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/dialyzer.js"></script>
		<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
		<script type="text/javascript">
		//��ֹҳ���ظ��ύ
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
			}			
		}
		
		function goback(menuid)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />�˳���ֵ���ѹ���");
				
				document.getElementById("curMenuId").value = menuid;
						
				document.forms[0].target = "_self";
				document.forms[0].action = "${sessionScope.basePath }charge/morePhone.action";
				document.forms[0].submit();
			}
		}
		
		// ���˽ɷ�
        function morePhone()
        {
            document.forms[0].target = "_self";
            document.forms[0].action = "${sessionScope.basePath }charge/morePhone.action";
            document.forms[0].submit();
        }
		
		//�ύ���ѷ�ʽ��flagΪ0ʱ����ʾ��ؽɷ���Ϣ��Ϊ1ʱ����Ҫ��ʾ��ֱ���ύ
		function doSub(menuid, url,flag)
		{
			if (submitFlag == 0)
			{
				submitFlag = 1;
				
				writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
						"<s:property value='servnumber' />ѡ�񽻷ѷ�ʽ��" + menuid);
				
				<%--modify begin g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
				if (url == "charge/cardCharge.action")
				{
					// ���������ѣ��жϴ�ʱ������Ƿ����
					var url1 = "${sessionScope.basePath}charge/checkTime.action";
					
					var loader = new net.ContentLoader(url1, netload = function () {
						var resXml1 = this.req.responseText;
						
						if (resXml1 == "1" || resXml1 == 1)
						{
							document.actform.target = "_self";
							document.actform.action = "${sessionScope.basePath}" + url;
							document.actform.submit();
						}
						else
						{
							submitFlag = 0;
							
							alertRedErrorMsg("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGE_CARD_MSG) %>");
							
							return;
						}
					}, null, "POST", null, null);
				}
				else
				{
					document.actform.target = "_self";
					document.actform.action = "${sessionScope.basePath}" + url;
					document.actform.submit();
				}
				<%--modify end g00140516 2012/12/10 eCommerce V200R003C12L11 OR_SD_201211_692 --%>
			}			
		}
		
		</script>
	</head>
	<body scroll="no">
		<form name="actform" method="post">
			<s:hidden id="morePhoneFlag" name="morePhoneFlag" />
			<s:hidden name="totalFee" id="totalFee" />
			<s:hidden name="chargeType"/>
			<!-- �û���Ϣ�ַ��� -->
            <s:hidden id="morePhoneInfo" name="morePhoneInfo"/>
			<%@ include file="/titleinc.jsp"%>
			
			<div class="main" id="main">
				<%@ include file="/customer.jsp"%>
				
				<div class="pl30">
					<div class="b257 ">
						<div class="bg bg257"></div>
						<h2>��ֵ��������</h2>
						<div class="blank10"></div>
						<a href="javascript:void(0)">1. �����ֻ�����</a> 
						<a href="javascript:void(0)" class="on">2. ѡ��֧����ʽ</a>
    					<p class="recharge_tips">�鿴���Ļ�����Ϣ�����ύ��ֵ���ѽ�����֧����</p>
    					<a href="javascript:void(0)">3. ��������</a>
                        <a href="javascript:void(0)">4. ����������</a>
                        <a href="javascript:void(0)">5. ��������</a>
                        <a href="javascript:void(0)">6. ���</a>
					</div>
					
					<div class="b712" style="_margin-left:120px;_top:-535px;">
						<div class="bg bg712"></div>
    					<div class="blank20"></div>
    					<div class="p40">
    						<div class="tit_info fl"><span class="bg"></span></div> 
    						<table width="540px" class="telnum_list fl fs20" cellpadding="0" cellspacing="0" border="0">
					            <tr>
					                <th class="bdb1px" width="33%">�ֻ�����</th>
					                <th class="bdb1px" width="21%">��ֵ���</th>
					                <th class="bdb1px" width="45%">�˺�</th>
					            </tr>  
					        </table>
					        <div id="sucessTable0" class="tabdiv0">
					            <table width="540px" class="telnum_list fl fs20 "  cellpadding="0" cellspacing="0" border="0">
						            <s:iterator value="morePhones" id="morePhone">
						                <tr>
						                    <td class="yellow" width="33%"><s:property value="#morePhone.servnumber"/></td>
		                                    <td class="yellow" width="21%"><s:property value="#morePhone.tMoney"/>Ԫ</td>
		                                    <s:if test="null == #morePhone.yingjiaoFee || '' == #morePhone.yingjiaoFee">
		                                       <td width="45%">���<s:property value="#morePhone.balance"/>Ԫ</td>
		                                    </s:if>   
		                                    <s:else>
		                                       <td width="45%">Ӧ��<s:property value="#morePhone.yingjiaoFee"/>Ԫ</td>
		                                    </s:else>   
						                </tr>
						            </s:iterator>  
						        </table>
						    </div>
						    
					        <div class="box70W scrollbtn" id="scrollbar">
					            <input type="button" class="btnUp" onclick="myScroll.moveUp(40)" />
					            <div class="boxPage" style="width:75px; height:20px; ">
					                <div class="blank10px"></div>
					                <div class="box66W tc f16 lh30 disn" id="gunDom" style=" position:absolute; width:66px; height:20px; top:52px; left:3px; cursor:move; line-height:36px" >0%</div>
					            </div>
					            <input type="button" class="btnDown" onclick="myScroll.moveDown(40)"/>
					        </div>
					        <table width="540px" class="telnum_list fl fs20 ml40 yellow" style="_margin-left:20px;" cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td width="33%">�ϼƣ�</td>
                                    <td width="21%" class="yellow"><s:property value="totalFee"/>Ԫ</td>
                                    <td width="45%"></td>
                                </tr>        
                            </table>  
					        <div class="clear"></div>
					        <div class="blank5"></div>
					        <div><a class="btn_142 ml240 fl" onclick=" morePhone();return">�����޸�</a></div>
					        <div class="blank10"></div>
					        <div class="bdt1px"></div>
      						<p class="tit_arrow fs16 fwb"><span class="bg"></span>ѡ��֧����ʽ��</p>
        					<ul class="pay_way_list clearfix">
                                <s:iterator value="usableTypes" id="type" status="st">
                                    <s:if test="#type.menuid == 'cardcharge'">
                                        <li class="mr70">
                                            <a class="card_1" href="javascript:void(0);" onclick="doSub('<s:property value="#type.menuid" />', '<s:property value="#type.guiobj" />','0'); return false;"><img src='${sessionScope.basePath}<s:property value="#type.imgpath" />' alt='<s:property value="#type.menuname" />' /></a>
                                        </li>
                                    </s:if>
                                </s:iterator>
                            </ul>           					
    					</div>
					</div>
				</div>
			</div>
		    
			<%@ include file="/backinc.jsp"%>		
		</form>
	</body>
</html>
<script type="text/javascript" >
   myScroll = new virtualScroll("sucessTable0","gunDom");       
</script>
