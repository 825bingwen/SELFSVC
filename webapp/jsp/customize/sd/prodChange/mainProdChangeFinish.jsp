<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>�ƶ������ն�</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <link href="${sessionScope.basePath}css/reset.css" type="text/css" rel="stylesheet" />
        <link href="${sessionScope.basePath}css/style.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript"  src="${sessionScope.basePath}js/public.js"></script>
        <script type="text/javascript"  src="${sessionScope.basePath}js/script.js"></script>
        <script type="text/javascript" src="${sessionScope.basePath}js/dialyzer.js"></script>
    </head>
    <body scroll="no" onload="window.focus();">
        <form id="actform" name="actform" method="post">
            <%@ include file="/titleinc.jsp"%>

            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
				
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>�ײ��ʷѱ������</h2>
                        <div class="blank10"></div>
                        <a href="#">1. �����ֻ�����</a>
                        <a href="#">2. ��ת�������Ʒ</a>
                        <a href="#">3. �����Ʒģ��</a>
                        <a href="#">4. ���ȷ��</a>
                        <a href="#" class="on">5. ���</a>
                    </div>
                    <!--������-->
                    <div class="box710W fl ml10 relative" style="margin-top: 10px;">
                        <div class="bg"></div>
                        <div class="top"></div>
                        <div class="con relative" >
                            <div style="height: 444px; float:left; padding: 0px 0px 5px 0px; width: 610px; overflow: hidden;">
                                <p class="ptop180 tc" id="inn"  style="height: 450px; width: 610px; overflow: hidden;">
                                <table width="100%" class="tb_blue">
                                    <tr>
                                        <th colspan="4"><span class="yellow">ҵ�����ɹ�!</span></th>
                                    </tr>
                                    <tr>
                                        <th style="width:16%">�ֻ��ţ�</th><td style="width:34%"><s:property value="customer.servNumber" /></td>
                                        <th style="width:16%">����:</th><td style="width:34%"><s:property value="customer.customerName" /></td>
                                    </tr>
                                    <s:if test="chgSelfLevel == 1">
                                    	<th>���ײͣ�</th><td colspan="3"><s:property value='templateName'/></td>
                                    </s:if>
                                    <s:else>
	                                    <tr>
	                                        <th>���ײͣ�</th><td><s:property value='newProdName'/></td>
	                                        <th>��Чʱ�䣺</th><td colspan="3">������Ч</td>
	                                    </tr>
                                    </s:else>
                                </table>
                                <br/>
                                <a href="#" class="bt4" onmousedown="this.className='bt4on'" 
                                onmouseup="this.className='bt4';topGo('rec', 'reception/receptionFunc.action'); return false;" style="display:inline">��������ҵ��</a> 
                            	
                            </div>
                            <div class="box70W fr">
                                <input type="button" class="btnUp" onclick="myScroll.moveUp(30)" />
                                <div class="boxPage" style="width:75px; height:400px; ">
                                    <div class="blank10px"></div>
                                    <div class="box66W tc f16 lh30" id="gunDom" style="width:66px; height:36px; position:absolute; cursor:move;  left: 633px; top:52px; line-height:36px" >0%</div>
                                </div>
                                <input type="button" class="btnDown" onclick="myScroll.moveDown(30)"/>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="btm"></div>
                    </div>
                    <script type="text/javascript">
                      myScroll = new virtualScroll("inn","gunDom");
                    </script>
                    <!--����������-->
                   
                </div>
             </div>
             <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
<script type="text/javascript">
var submitFlag = 0;
// �˳���ť������ҳ��
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
        document.forms[0].submit();
    }
}
</script> 

