<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    </head>
    <body scroll="no">
        <form id="actform" name="actform" method="post" >
        
            <%-- �ֻ����� --%>
            <s:hidden id="servnumber" name="servnumber"></s:hidden>
            
            <%-- Ӫ������� --%>
            <s:hidden id="actId" name="actId"></s:hidden>
            
            <%-- �ظ����� --%>
            <s:hidden id="moContent" name="moContent"></s:hidden>
            
            <%-- ҵ���Ƽ�Ψһ��ˮ�� --%>
            <s:hidden id="commendOID" name="commendOID"></s:hidden>
            
            <%-- ��ֵ���ѱ�ʶ --%>
            <s:hidden id="feeChargeFlag" name="feeChargeFlag"></s:hidden>
            
            <%-- �û��������������ʶ --%>
            <s:hidden id="feedBackDefFlag" name="feedBackDefFlag" value="1"></s:hidden>
            
            <%-- �Ƽ����� --%>
			<s:hidden id="recmdType" name="recmdType"></s:hidden>
            
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">
            
                <%@ include file="/customer.jsp"%>  
                          
                <!--������-->
                <div class="box842W fl ml45 relative" style="margin-left:90px; display:inline;">
                    <div class="bg"></div>
                    <div class="top"></div>
                    <div class="con relative" >
                        <div class="box747W fl">
                            <div class="div747w444h" >
                                <!-- �б����� -->
                                <p class="ptop180 tc p747w411h" id="inn" >
                                    <table width="100%" class="tb_blue" id="actTable">
                                        <tr>
                                            <th class="list_title" align="center" colspan="20">�����</th>
                                        </tr>
	                                    <tr>
	                                        <th style="width: 70%" scope="col">�Ƽ�����</th>
	                                        <th scope="col">�Ƿ����</th>
	                                    </tr>
                                        <s:iterator value="feedBackDefList" id="feedBackDef">
	                                       	<tr>
	                                            <td><s:property value='#feedBackDef.recmdName' /></td>
	                                            <td><a href="javascript:void(0);" class="bt6 fr relative fl ml50" 
                                            		onmousedown="this.className='bt6on fl relative ml50'" 
                                            		onmouseup="this.className='bt6 fl relative ml50';showConfirmInfo('popup_confirm','<s:property value="#feedBackDef.recmdName"/>','<s:property value="#feedBackDef.moContent"/>');" >����</a>
	                                            </td>
	                                       	</tr>
                                        </s:iterator>
                                	</table>
                                <br/>
                            </div>
                        </div>    
                        <div class="box70W fr">
                            <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                            <div class="div75w350h">
                                <div class="blank10px"></div>
                                <div class="box66W tc f16 div66w36h" id="gunDom" style=" width:66px; height:40px; position:absolute; cursor:move; left:766px; top:39px; line-height:36px">0%</div>
                            </div>
                            <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="btm"></div>
                </div>
                <div class="popup_confirm" id="popup_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">��ʾ��</div>
                       <div class="tips_body">
                        <p>��ѡ�񶩹��� <i id="recmdName"></i></p>
                        <div class="blank10"></div>
                        <p class="mt30">ȷ�϶�������"ȷ��"�ύ��</p>
                      </div>
                      <div class="btn_box tc mt20">
                          <span class=" mr10 inline_block "><a title="ȷ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';doFeedBackDef();">ȷ��</a></span>
                          <span class=" inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';wiWindow.close();">ȡ��</a></span>
                      </div>
                </div>
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
            </div>
            <%@ include file="/backinc.jsp"%>        
        </form>
    </body>
</html>
<script type="text/javascript">
    
    // ��ֹҳ���ظ��ύ
    var submitFlag = 0;
    
    // ������һҳ
    function goback(curmenu) 
    {    
        if (submitFlag == 0)
        {
            submitFlag = 1;
            
            document.getElementById("curMenuId").value = curmenu;
            document.getElementById("actform").action 
            	= "${sessionScope.basePath}recommendProduct/getRecommendProductList.action";
            document.getElementById("actform").submit();
        }
    }
    
    document.onkeydown = pwdKeyboardDown;
    document.onkeyup = pwdKeyboardUp;
    
    // �������̰���
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
    
    // ֻ������������
    function KeyIsNumber(KeyCode) 
    {
        //ֻ��������0-9
        if (KeyCode >= 48 && KeyCode <= 57)
        {
            return true;
        }
        
        return false;
    }
    
    // ������һҳ
    function pwdKeyboardUp(e)
    {
        var key = GetKeyCode(e);
        if (key == 82 || key == 220)
        {
            goback("<s:property value='curMenuId' />");
            return;
        }
    }
    
    // �û����������
    function doFeedBackDef()
    {
        if (submitFlag == 0)
        {
        	submitFlag = 1;
        	
        	// ��ҵ�����ȴ�����
        	openRecWaitLoading('recWaitLoading');
        	
            var feeChargeFlag = document.getElementById("feeChargeFlag").value;
            
            if ("1" == feeChargeFlag)
            {
            	<s:if test="%{customerSimp != null}">
            		document.actform.action = "${sessionScope.basePath}/recommendProduct/doFeedBackDef.action";
                </s:if>
                <s:else>
                	document.actform.action = "${sessionScope.basePath}/recommendProduct/checkPassword.action";
                </s:else>
            }
            else
            {
                document.actform.action = "${sessionScope.basePath}/recommendProduct/doFeedBackDef.action";
            }
            
            document.actform.submit();
        }
    }
    
  	// ������ʾ��Ϣ
    function showConfirmInfo(id, recmdName, moContent)
    {
   		document.getElementById("moContent").value = moContent;
   		document.getElementById("recmdName").innerHTML = recmdName;
   		wiWindow = new OpenWindow("popup_confirm",708,392);
    }	
</script>