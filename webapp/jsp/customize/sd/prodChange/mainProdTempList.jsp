<%@ page language="java" contentType="text/html; charset=GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
        <form name="actform" method="post">
        	<%-- ת���������Ʒid --%>
        	<input name="oldProdId" id="oldProdId" type="hidden" value="<s:property value='oldProdId'/>"/>
        	<%-- �µ������Ʒid--%>
            <input name="newProdId" id="newProdId" type="hidden" value="<s:property value='newProdId'/>"/>
            <%-- �µ������Ʒ����--%>
            <input name="newProdName" id="newProdName" type="hidden" value="<s:property value='newProdName'/>"/>
            <%-- �µ������Ʒģ��id--%>
            <input name="templateId" id="templateId" type="hidden"/>
            <%-- �µ������Ʒģ�����ƣ���ԭ���ײ͵ĵ������ƣ� --%>
            <input name="templateName" id="templateName" type="hidden"/>
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
                        <a href="#" class="on">3. �����Ʒģ��</a>
                        <a href="#">4. ���ȷ��</a>
                        <a href="#">5. ���</a>
                    </div>
                    <!--������-->
                    <div class="box710W fl ml10 relative" style="margin-top: 10px;">
                        <div class="bg"></div>
                        <div class="top"></div>
                        <div class="con relative" >
                            <div style="height: 444px; float:left; padding: 0px 0px 5px 0px; width: 610px; overflow: hidden;">
                                <p class="ptop180 tc" id="inn"  style="height: 450px; width: 610px; overflow: hidden;">
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="4"><span class="yellow">�û���Ϣ</span></th>
                                    </tr>
                                    <tr>
                                        <th>�ֻ���</th>
                                        <th>����</th>
                                        <th>Ʒ��</th>
                                    </tr>
                                    <tr>
                                        <td><s:property value="customer.servNumber" /></td>
                                        <td><s:property value="customer.customerName" /></td>
                                        <td><s:property value="customer.brandName" /></td>
                                    </tr>
                                </table>
                                <br/>
                                <table width="100%" class="tb_blue03">
                                    <tr>
                                        <th colspan="3"><span class="yellow">�ײ�ģ���б�</span></th>
                                    </tr>
                                    <tr>
                                        <th>ģ������</th>
                                        <th>ģ������</th>
                                        <th>����</th>
                                    </tr>
                                    <s:iterator value="prodTemplateList" >
                                        <tr>
                                            <td><s:property value="templateName"/></td>
                                            <td><s:property value="templateDescr"/></td>
                                            <td>
                                                <input type="button" class="bt2_liebiao white" value="���" 
                                                onclick="selectTempSub('<s:property value='prodid'/>','<s:property value='prodname'/>','<s:property value='templateId'/>','<s:property value="templateName"/>');" />  
                                            </td>
                                        </tr>
                                    </s:iterator>
                                </table>
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
                <div class="popup_confirm" id="popup_confirm">
                    <div class="bg"></div>
                    <div class="tips_title">��ʾ��</div>
                    <div class="tips_body">
				        <p><i id="tipContent"></i></p>
				        <div class="blank10"></div>
				        <p class="mt30">ȷ�ϲ�������"ȷ��"�ύ��</p>
				    </div>
                    <div class="btn_box tc mt20">
	                    <span class="mr10 inline_block "><a title="ȷ��" href="#" class="btn_bg_146" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();changeTpltInProd();">ȷ��</a></span>
	                    <span class="inline_block "><a title="" class="btn_bg_146" href="#" onmousedown="this.className='key_down'" onmouseup="this.className='btn_bg_146';windowClose();">ȡ��</a></span>
                    </div>
            	</div>
            </div>
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>
<script type="text/javascript">
// ������ʾ��־ 0��δ���� 1������
var tipflag = 0;

// ��������¼�
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
    
    if (key == 13 || key == 89 || key == 221)
    {
    	if(tipflag == 1)
    	{
    		windowClose();
    		changeTpltInProd();
    	}
    }
    
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


var submitFlag = 0;
// �˳���ť������ҳ��
function goback(menuid)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = menuid;
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }login/backForward.action";
        document.actform.submit();
    }
}
// ѡ��ģ��
function selectTempSub(newProdId,newProdName,templateId,templateName)
{
	document.getElementById("templateId").value = templateId;
    document.getElementById("newProdId").value = newProdId;
    document.getElementById("newProdName").value = newProdName;
    setValue("templateName",templateName);
	    
	var oldProdId = "<s:property value='oldProdId'/>";
	
	if(oldProdId == newProdId)
	{
		openWindow("��ѡ�����"+templateName);
		return;
	}
	
	if (submitFlag == 0)
    {  
    	submitFlag = 1;
	    openRecWaitLoading();
	    document.actform.target = "_self";
	    document.actform.action = "${sessionScope.basePath}prodChange/prodChangeValidateInfo.action";
	    document.actform.submit();
	}
}

function changeTpltInProd()
{
	if (submitFlag == 0)
    {  
    	submitFlag = 1;
	    openRecWaitLoading();
	    document.actform.target = "_self";
	    document.actform.action = "${sessionScope.basePath}prodChange/chgLevelInGroup.action";
	    document.actform.submit();
	}
}

//����ȷ�Ͽ�
openWindow = function(alsoTips)
{
	document.getElementById("tipContent").innerHTML = alsoTips;
	wiWindow = new OpenWindow("popup_confirm",708,392);
	tipflag = 1;
}

// �رյ���divʱ����ʾ����Ϊδ����
function windowClose()
{
	tipflag = 0;
	wiWindow.close();
}
</script> 
