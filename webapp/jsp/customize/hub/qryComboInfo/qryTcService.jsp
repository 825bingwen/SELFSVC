<%@page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
// add begin wangyunlong 2013-09-12 R003C13LG0801  OR_HUB_201303_624 
    String month = (String)request.getAttribute("month");
    String month1 = (String)request.getAttribute("month1");
    String month2 = (String)request.getAttribute("month2");
    String month3 = (String)request.getAttribute("month3");
    String month4 = (String)request.getAttribute("month4");
    String month5 = (String)request.getAttribute("month5");
// add end wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624 
%>
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
<script type="text/javascript">
var submitFlag = 0;

function goback(curmenu) 
{
    //�Ѿ�ѡ�����·ݻ��ߵ���˷��أ��ȴ�Ӧ�𣬲���ִ���κβ���
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        document.getElementById("curMenuId").value = curmenu;
        
        document.actform.target="_self";
        document.actform.action="${sessionScope.basePath }login/backForward.action";
        document.actform.submit();
    }           
}

// ��������¼�
document.onkeydown = keyDown;
function keyDown(e)
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
<%--
* ��ת����Ӧ��Action
* @remark create wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624 
--%>

function btnClick(btn,btClass,month){
    document.getElementById("month").value = month;
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath}comboInfo/qryComboInfo.action";
    document.actform.submit();
}

</script>
    </head>
    <body scroll="no">
        <form name="actform" method="post">         
            <%--modify begin wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624--%>
                    <input type="hidden" name="month" value="">
            <%--modify end wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624--%>
            <%@ include file="/titleinc.jsp"%>
            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>
                <a href="#" class="bt10 fr mr92" onmousedown="this.className='bt10on fr mr92'" onmouseup="this.className='bt10 fr mr92';topGo('<%=parentMenuID %>', '<%=parentMenuURL %>'); return false;" style="display:inline">����<%=parentMenuName %></a>
                <!--������-->
                <%-- modify begin jWX216858 2015-2-3 OR_HUB_201501_167 ���������ն˲˵��㼶�Ż����� --%>
                <s:if test="'1' == cmoboInfoFlag">
                    <%--modify begin wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624--%>
                    <div class="tc pt30 mb30">
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month %>')"><%=month %></a>
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month1 %>')"><%=month1 %></a>
                
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month2 %>')"><%=month2 %></a>
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month3 %>')"><%=month3 %></a>
                       
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month4 %>')"><%=month4 %></a>
                        <a href="#" class="bt2" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2'" onclick="btnClick(this,'bt222','<%=month5 %>')"><%=month5 %></a>
                    </div>
                    <%--modify end wangyunlong 2013-09-12 V200R003C13LG0801 OR_HUB_201303_624--%>
                </s:if>
                <%-- modify end jWX216858 2015-2-3 OR_HUB_201501_167 ���������ն˲˵��㼶�Ż����� --%>
                <div class="box842W fl ml45 relative">
                    <div class="bg"></div>
                    <div class="top"></div>
                    <div class="con relative" >
                        <div class="box747W fl">                    
                            <div class="div747w444h" >
                                <!-- �б����� -->
                                <p class="tit_info" align="left"><span class="bg"></span><%=menuName %></p>
                                <p class="ptop180 tc p747w411h" id="inn" >
                                    <table class="tb_blue" width="100%">
                                        <tr>
                                            <!-- ������ -->
                                            <s:iterator value="servicetitle" status="title">
                                                <th class="list_title" align="center" id="title<s:property value="#title.getIndex()"/>">
                                                    <s:property/>
                                                </th>
                                            </s:iterator>
                                        </tr>
                                
                                        <!-- �б��� -->
                                        <s:iterator value="result" status="result">
                                            <tr>
                                                <s:iterator value="result.get(#result.index)">
                                                    <td align="center"><s:property/></td>
                                                </s:iterator>
                                            </tr>
                                        </s:iterator>
                                        <tr>
                                            <td colspan="100"> 
                                                <strong>&nbsp;&nbsp;&nbsp;&nbsp;�ϼ�������</strong><s:property value="result.size"/>�� 
                                            </td>
                                        </tr>
                                    </table>
                                </p>                                
                                <!-- �б����� -->
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
                <script type="text/javascript">myScroll = new virtualScroll("inn","gunDom");</script>
                <!--����������-->
            </div>
            <%@ include file="/backinc.jsp"%>       
        </form>
    </body>
</html>
