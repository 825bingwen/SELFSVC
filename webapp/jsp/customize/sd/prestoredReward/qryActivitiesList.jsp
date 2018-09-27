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
                
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }login/backForward.action";
        document.forms[0].submit();
    }
}

// ��ѯ����
function queryActLevelDesc(groupId,groupName,actLevelId,actLevelName,activityId,prepayFee,activityDesc,awardDesc)
{
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        // ������
        document.getElementById('groupId').value = groupId;
        
        // �������
        document.getElementById('groupName').value = groupName;
        
        // �����
        document.getElementById("activityId").value = activityId;
        
        // ���α���
        document.getElementById("actLevelId").value = actLevelId;
        
        // ��������
        document.getElementById("actLevelName").value = actLevelName;
        
        // ������
        document.getElementById("prepayFee").value = prepayFee;

        // �����
        document.getElementById("activityDesc").value = activityDesc;
        
        // СƱ��Ʒ����
        document.getElementById("awardDesc").value = awardDesc;
         
        // �ύ
        document.forms[0].target = "_self";
        document.forms[0].action = "${sessionScope.basePath }prestoredReward/queryActLevelDesc.action";
        document.forms[0].submit();
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

</script>
</head>
    <body scroll="no">
        <form id="actform" name="actform" method="post">
            
            <!-- ���ID -->
            <input type="hidden" id="groupId" name="groupId" value=""/>
            
            <!-- ������� -->
            <input type="hidden" id="groupName" name="groupName" value=""/>
            
            <!-- ����� -->
            <input type="hidden" id="activityId" name="activityId" value=""/>
            
            <!-- ���α��� -->
            <input type="hidden" id="actLevelId" name="actLevelId" value=""/>
            
            <!-- �������� -->
            <input type="hidden" id="actLevelName" name="actLevelName" value=""/>
            
            <!-- ������ -->
        	<%-- modify by sWX219697 2015-7-20 prePayFee��ΪprepayFee--%>
            <input type="hidden" id="prepayFee" name="prepayFee" value=""/>
            
            <!-- ����� -->
            <input type="hidden" id="activityDesc" name="activityDesc" value=""/>
            
            <!-- СƱ��Ʒ���� -->
            <input type="hidden" id="awardDesc" name="awardDesc" value=""/>
                        
            <%@ include file="/titleinc.jsp"%>
            
            <div class="main" id="main">            
                <%@ include file="/customer.jsp"%>
                
                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>Ԥ�������������̣�</h2>
                        <div class="blank10"></div>
                        <a title="ѡ������" href="#" class="on">1. ѡ��</a>
                        <a title="����Э��" href="#">2. ����Э��</a>  
                        <a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
                        <a title="Ͷ��ֽ��" href="#">4. ֧��</a>
                        <a title="���" href="#">5. ���</a>
                    </div>
                </div>
                
                <div class="b712">
                    <div class="bg bg712"></div>
                    <div class="blank30"></div>
                    <div class="p40">
                    <div class="blank10"></div>
                        <table width="100%" class="tb_blue" >
                          <tr>
                            <th scope="col">�</th>
                            <th scope="col">����</th>
                            <th scope="col">��Ʒ��ֵ(Ԫ)</th>
                            <th scope="col">������(Ԫ)</th>
                            <th scope="col">����</th>
                          </tr>
                          <s:iterator value="prestoredRewardList" id="list" status="st">
                          <tr>
                                <td><s:property value="#list.groupName" /></td>
                                <td><s:property value="#list.actLevelName" /></td>
                                <td><s:property value="#list.presentValue" /></td>
                                <td><s:property value="#list.prepayFee" /></td>
                                <td>
                                    <input type="button" class="bt2_liebiao" style="color:#FFFFFF;" value="����" onmousedown="this.className='bt2on'" onmouseup="this.className='bt2';queryActLevelDesc('<s:property value="#list.groupId" />','<s:property value="#list.groupName" />','<s:property value="#list.actLevelId" />','<s:property value="#list.actLevelName" />', '<s:property value="#list.activityId" />','<s:property value="#list.prepayFee" />','<s:property value="#list.activityDesc" />','<s:property value="#list.awardDesc"/>')"/>
                                </td>
                                            
                          </tr>
                          </s:iterator>
                        </table>
                    </div>  
                    
                    <!-- ��ҳ -->
                    <div class="sortPages">
                        <pg:paginator recordsCount="${recordCount }" pageSize="${pageSize }" page="${page }" linkUrl="<%=menuURL %>" />
                    </div>
                    <div class="clear"></div>
                </div>  
            </div>  
            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
</html>

