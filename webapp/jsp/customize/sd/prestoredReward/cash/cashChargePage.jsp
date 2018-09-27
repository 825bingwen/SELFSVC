<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.gmcc.boss.selfsvc.common.Constants"%>
<%
    // ������棬��ֹҳ����˰�ȫ���� 
    response.setHeader("Pragma", "no-cache"); 
    response.setHeader("Cache-Control", "no-store"); 
    response.setDateHeader("Expires", -1);
    
    // Ͷ�ҳ�ʱʱ��
    String timeout = (String)PublicCache.getInstance().getCachedData(Constants.SH_PAYMONEY_TIME);
    
    // �ն���Ϣ
    TerminalInfoPO termInfo = (TerminalInfoPO)session.getAttribute(Constants.TERMINAL_INFO);
    
    // �ֽ�ʶ��������
    String isCashEquip = termInfo.getTermspecial().substring(3, 4);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>�ƶ������ն�</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="${sessionScope.basePath }css/reset.css" type="text/css"rel="stylesheet" />
<link href="${sessionScope.basePath }css/style.css" type="text/css"rel="stylesheet" />
<script type="text/javascript"src="${sessionScope.basePath }js/public.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/script.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/dialyzer.js"></script>
<script type="text/javascript"src="${sessionScope.basePath }js/pluginscript/TerminalCashEquip.js"></script>
<script type="text/javascript" src="${sessionScope.basePath }js/pluginscript/TerminalManager.js"></script>
<script type="text/javascript">
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
    
    //ȷ��
    if (key == 13 || key == 89 || key == 221) 
    {
        doSub();
        return;
    }
    //����
    else if (key == 82 || key == 220) 
    {
        goback("<s:property value='curMenuId' />");
        return;
    }  
    
}
    
//Ͷ�ҵ�ʱ������λ��     
var payMoneyTime = "<%=timeout%>";

//ʣ��ʱ��
var leftTime = payMoneyTime;

//readCash��ʱ�����
var readCashToken;

//�ر�ʶ������0������Ҫ��1����Ҫ
var needClose = 0;

//�ύ��ǣ�0��ʾδȷ���ύ�ɷѣ�1��ʾ��ȷ���ύ�ɷ�
var submitFlag = 0;

function goback(menuid)
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />�˳����ѹ���");
    
    //��Ͷ��
    if (document.getElementById("tMoney").value != "" 
            && parseInt(document.getElementById("tMoney").value) > 0)
    {
        return;
    }
    
    if (submitFlag == 0)
    {
        submitFlag = 1;
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "����ѡ��ҳ��ҳ��");
        
        //����ʱ�������ʱ����ͬʱ�ر��ֽ�ʶ����          
        if (needClose == 1)
        {
            fCloseCashBill();
        }
        
        clearInterval(readCashToken);
        
        document.getElementById("curMenuId").value = menuid;
        
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }prestoredReward/qryActivitiesList.action";
        document.actform.submit();
    }
}
    
// �ύ
function doSub()
{
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />ȷ�Ͻ���");
    
    //δͶ��
    if (document.getElementById("tMoney").value == "" 
            || parseInt(document.getElementById("tMoney").value) <= 0)
    {
        return;
    }
    
    //��δ�ύ����
    if (submitFlag == 0)
    {
        // �ж�Ͷ�ҽ���������Ӧ�ɽ��
        var recFee = document.getElementById("recFee").value;
        var tMoney = document.getElementById("tMoney").value;
        if(tMoney - recFee < 0)
        {
            var alsoFee = recFee - tMoney;
            var tipText = "�𾴵Ŀͻ�������Ͷ�ҵĽ��㣬����Ͷ��"+alsoFee+"Ԫ("+alsoFee+"Ԫ=Ӧ�ɽ��-Ͷ�ҽ��)��";
            openWindow("openWin_tipsMsg",tipText);
            return;
        }
        
        submitFlag = 1;
        
        // ȷ�ϰ�ť���£��ò�����״̬
        document.getElementById('commitBusi').className = 'btn_box buy_enable_hover fl pl30 pt120';
        
        // ȡ����ť���£��ò�����״̬
        document.getElementById('cancelBusi').className = 'btn_box buy_enable_hover fl pl30 pt120';
        
        //�ر��ֽ�ʶ����
        if (needClose == 1)
        {
            fCloseCashBill();
        }
        
        //�����ʱ����
        clearInterval(readCashToken);
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "�ύ�������󣬺��룺" + document.getElementById("telNum").value + 
                "����" + document.getElementById("tMoney").value + 
                "����ˮ��" + document.getElementById("terminalSeq").value);
                
        //�����ȴ���
        openRecWaitLoading();
        
        //�ύ�ɷ�����
        document.actform.target = "_self";
        document.actform.action = "${sessionScope.basePath }prestoredReward/cashFinish.action";
        document.actform.submit();
    }
}

// �򿪵�����
function openWindow(id,tipMsg)
{
    document.getElementById('winText_tipsMsg').innerHTML = tipMsg;
    wiWindow = new OpenWindow(id,708,392);//�򿪵�����������    
}  

// �رյ���div
function windowClose()
{
    wiWindow.close();
}

// ȡ����������Ͷ���ʹ��СƱ��ӡ
function closeRec()
{
    setException("�û�ȡ��������ȡСƱ���������ʣ�����ѯ�ƶ�Ӫҵ����"); 
}

//�����쳣
function setException(errorMsg) 
{           
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='telNum' />���ѹ����г����쳣��" + errorMsg);
    
    document.getElementById("errormessage").value = errorMsg;
    
    // �����쳣�������ʱ����ͬʱ�ر��ֽ�ʶ����           
    if (needClose == 1)
    {
        fCloseCashBill();
    }
    
    // �����ʱ����
    clearInterval(readCashToken);
    
    // �쳣����ֻҪ�������쳣��Ҫ��¼��־  
    document.actform.target = "_self";
    document.actform.action = "${sessionScope.basePath }prestoredReward/cashError.action";
    document.actform.submit();
}       

// ��ȡ�ֻ�����
// ��ʼ���ֽ�ʶ����
// ��ȡͶ����ˮ
// ʹ��ҳ���˳���ť������
// ����ѭ����ȡͶ�ҽ��
function loadContent() 
{
    var telNum = "<s:property value='telNum' />";
    if (telNum == null || telNum == "")
    {            
        setException("�Բ����û���Ϣ��ȡʧ�ܣ��뷵�����²�����");
        return;
    }
          
    <%if (!"1".equals(isCashEquip)){%>
          setException("�Բ��𣬸��ն˻��ݲ�֧���ֽ��ֵ����ѡ��������ʽ��");
          return;
    <%}%>
    
    try 
    {
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "��ʼ���ֽ�ʶ������" + telNum);
                
        // ��ʼ���ֽ�ʶ����(�������� 0,20110509143345)
        var initData = initCashEquip_sd(telNum);
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                "�����" + initData);
        
        if (initData.substring(0, 1) != "0") 
        {
            setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
            return;                    
        }
        else
        {
            //�ֽ�ʶ������ʼ���ɹ������ٽ����û�Ͷ��ʱ����Ҫ�رա�
            needClose = 1;

            //��ȡͶ����ˮ
            document.getElementById("terminalSeq").value = initData.substring(2);
            
            // ��ʶ�ؼ�ʹ��
            closeStatus = 1;
            
            //��ʼ���ɹ�������Ҫ�ر�ʶ����������ҳ���˳���ť�޷�ִ�д˲��������Խ��á���ҳ�������˳�����ť
            document.getElementById("homeBtn").disabled = true;
            document.getElementById("quitBtn").disabled = true;
            
            startclock();
        }               
    } 
    catch(e) 
    {
        setException("�ֽ�ʶ������ʼ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
        return;
    }           
}

//ѭ����ȡͶ�ҽ��
function startclock() 
{
    var msg = preparecash();
    
    //ʶ����״̬���ʧ�ܣ�������Ͷ�ң���ʾ�쳣��Ϣ
    if (msg != "") 
    {
        setException(msg);
        return;
    }       
    
    try
    {
        // ��ȡͶ�ҽ��,ѭ������,ÿ��1��ִ��һ��
        readCashToken = setInterval("doCash()", 1000);
    }
    catch (e) 
    {
        setException("�Բ��𣬼�ʱ�������쳣���޷�ʹ���ֽ���г�ֵ����ʹ��������ʽ���ߵ�Ӫҵ�����г�ֵ��");
    }
}

//���ʶ����״̬
function preparecash() 
{
    var msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽֵ��";

    try 
    {
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "���ʶ����״̬"); 
                        
        //���ʶ����״̬ 0-���� 1-�쳣 2-Ǯ���� 3-Ǯ��� 4-��ڱ��� 5-Ǯ�䱻�� 6-�������� 9-�޴��豸
        var cashStatus = checkCashStatus();
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "�����" + cashStatus);    
        if (cashStatus == 0)
        {
            msg = "";
        }
        else if (cashStatus == 1) 
        {
            msg = "�Բ����ֽ�ʶ����״̬�쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        }
        else if (cashStatus == 2) 
        {
            msg = "�Բ���Ǯ���������޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        }
        else if (cashStatus == 3) 
        {
            msg = "�Բ���Ǯ��δ�����رգ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        } 
        else if (cashStatus == 4) 
        {
            msg = "�Բ���Ǯ���볮�ڱ��У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        }
        else if (cashStatus == 5) 
        {
            msg = "�Բ���Ǯ�䱻�У��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        } 
        else if (cashStatus == 6) 
        {
            msg = "�Բ����ֽ�ʶ��������δ֪�����޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        }
        else if (cashStatus == 9) 
        {
            msg = "�Բ����ֽ�ʶ���������ڣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
        }           
    }
    catch (e) 
    {
        msg = "�Բ����ֽ�ʶ���������쳣���޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��";
    }
    
    // ����
    return msg;
}

// ��ȡͶ�ҽ��
// ѭ�����ã�ÿ��1��ִ��һ��
function doCash() 
{
    if (leftTime <= 0)
    {
        return;
    }
    
    try 
    {   
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "��ȡͶ�ҽ��");  
                        
        // ��ȡͶ�ҽ�� 0 ��ʾû��Ͷ�ң����� ΪͶ����ֵ(���ܵ�ֵΪ��1,2,5,10,20,50,100)��
        var ret = getOnceCash();
        
        writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                        "�����" + ret);
        
        if (ret > 0) 
        {
            // ʱ�����¿�ʼ
            leftTime = "<%=timeout%>";
            
            // ��ʾʣ��ʱ��
            document.getElementById("tTime").value = leftTime;
            
            // Ͷ�Һ󣬲��ܷ���
            document.getElementById("backBtn").disabled = true;
            
            // ����Ͷ�ҽ�� 
            document.getElementById("tMoney").value = parseInt(document.getElementById("tMoney").value) + ret;
            
            // ȡ�����װ�Ť����ʾ
            document.getElementById('cancelBusi').style.display = "none";
            
            // Ͷ�ҽ�����0ʱȡ�����װ�Ť����ʾ,�ɷѰ�Ť��ʾ
            if (parseInt(document.getElementById("tMoney").value) > 0)
            {
                document.getElementById('commitBusi').style.display = "block";
                document.getElementById("promptMsg").innerHTML = "��Ͷ��ֽ�ң������ɷѰ�ť";
            }
            else
            {
                document.getElementById('bCommitBusi').style.display = "block";
            }
        }
        else
        {
            // Ͷ��ʱ��һ��timeout��
            leftTime = leftTime - 1;
            
            // ��ʾʣ��ʱ��
            document.getElementById("tTime").value = leftTime;
            
            //Ͷ��ʱ����������û�û�������ύ�ɷ����󣬴�ʱ����Ҫ�ύ�ɷ�����
            if (parseInt(document.getElementById("tTime").value) <= 0 && submitFlag == 0)
            {                   
                //Ͷ�ҽ�����0
                if (parseInt(document.getElementById("tMoney").value) > 0) 
                {
                    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                            "���׳�ʱ���û���Ͷ�ҡ��ύ��������");
                        
                    // �ύ�ɷ�
                    doSub();
                } 
                else 
                {
                    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
                            "���׳�ʱ���û�δͶ�ҡ����̽���");
                    
                    // ȡ������
                    cancelBusi();
                }
            }
        }               
    }
    catch (e) 
    {
        setException("�Բ��𣬻�ȡͶ�ҽ��ʧ�ܣ��޷�ʹ���ֽ���г�ֵ����ѡ��������ʽ��");
    }
}

// ȡ������
function cancelBusi()
{       
    writeDetailLog("<%=(String) PublicCache.getInstance().getCachedData(Constants.SH_CHARGELOG_DETAILFLAG) %>", 
            "<s:property value='servnumber' />ȡ�����ѽ���");
    
    if (needClose == 1)
    {
        // �ر��ֽ�ʶ����
        fCloseCashBill();
    }
    
    // �����ʱ����
    clearInterval(readCashToken);
    
    // ������ҳ
    setTimeout('window.location="${sessionScope.basePath}login/goHomePage.action"', 500)
}
</script>
    </head>
    <body scroll="no">
        <form name="actform" method="post"> 
        <!-- �ֻ����� -->
        <input type="hidden" id="telNum" name="telNum" value="<s:property value='telNum' />">

        <!-- ����� -->
        <input type="hidden" id="activityId" name="activityId" value='<s:property value="#request.activityId" />'/>
        
        <!-- ���α��� -->
        <input type="hidden" id="actLevelId" name="actLevelId" value='<s:property value="#request.actLevelId" />'/>

        <!-- ���ID -->
        <input type="hidden" id="groupId" name="groupId" value="<s:property value="#request.groupId" />"/>
        
        <!-- ������� -->
        <input type="hidden" id="groupName" name="groupName" value="<s:property value="#request.groupName" />"/>
        
        <!-- �������� -->
        <input type="hidden" id="actLevelName" name="actLevelName" value="<s:property value="#request.actLevelName" />"/>
        
        <!-- ������ -->
        <%-- modify by sWX219697 2015-7-20 prePayFee��ΪprepayFee--%>
        <input type="hidden" id="prepayFee" name="prepayFee" value='<s:property value="#request.prepayFee" />'/>
        
        <!-- ��Ʒ���봮 -->
        <input type="hidden" id="actreward" name="actreward" value='<s:property value="#request.actreward"/>'/>
        
        <!-- Ӧ����� -->
        <input type="hidden" id="recFee" name="recFee" value='<s:property value="#request.recFee"/>'/>
        
        <!-- �ն˻���ˮ -->
        <input type="hidden" id="terminalSeq" name="terminalSeq" value=""/>
            
        <!-- ������Ϣ -->
        <input type="hidden" id="errormessage" name="errormessage" value=""/>        
        
        <!-- �Ƿ�Ϊʵ�� 1:ʵ�0����ʵ�� -->        
        <input type="hidden" id="isGoods" name="isGoods" value='<s:property value="#request.isGoods"/>'/>
        
        <!-- ��־��ˮ -->
        <input type="hidden" id="chargeLogOID" name="chargeLogOID" value='<s:property value="chargeLogOID"/>'/>
        
        <!-- СƱ��Ʒ���� -->
        <input type="hidden" id="awardDesc" name="awardDesc" value='<s:property value="#request.awardDesc"/>'/>
        
            <%@ include file="/titleinc.jsp"%>

            <div class="main" id="main">
                <%@ include file="/customer.jsp"%>

                <div class="pl30">
                    <div class="b257 ">
                        <div class="bg bg257"></div>
                        <h2>Ԥ�������������̣�</h2>
                        <div class="blank10"></div>
                        <a title="ѡ������" href="#">1. ѡ��</a>
                        <a title="��������" href="#">2. ����Э��</a>  
                        <a title="ѡ��֧����ʽ" href="#">3. ѡ��֧����ʽ</a> 
                        <a title="֧��" href="#" class="on">4. Ͷ��ֽ��</a>
                        <a title="���" href="#">5. ���</a>
                    </div>

                    <div class="b712 fm_pay_money">
                        <div class="bg bg712"></div>
                        <div class="blank30"></div>
                        <div class="p40 pr0">
                            <div class="blank10"></div>
                            <div class="blank20"></div>
                            <div class=" pay_money_wrap2">
                                <p class="pay_all">
                                    <span class="pl120">��Ͷ�룺</span>
                                    <input type="text" id="tMoney" name="tMoney" value="0"readonly="readonly" />
                                    <span class="yellow">Ԫ</span>
                                </p>
                                <div class="pay_state clearfix">
                                    <span class="cash_arrow"></span>
                                    <p class="fl fs22">
                                        Ͷ��״̬��

                                        <span id="promptMsg" class="yellow">��Ͷ��ֽ��...</span>

                                        <br />
                                        <span class="pl119">Ͷ��ʱ����</span><span
                                            class="yellow"><%=timeout%></span>�룬��ǰʣ��
                                        <input type="text" id="tTime" name="tTime"
                                            value="<%=timeout%>" readonly="readonly" />
                                        ��
                                        <br />
                                        <span class="pl119">֧��</span><span
                                            class="yellow">5��10��20��50��100Ԫ</span>����ֽ��
                                    </p>
                                </div>
                            </div>
                            <div class="blank30"></div>
                            <div>
                                <img src="${sessionScope.basePath }images/rmb.gif"
                                    class="fl pl160" alt="��Ͷ��" />
                                <div style="display:none" class="btn_box buy_enable fl pl30 pt120" id="commitBusi">
                                    <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="doSub();return false;">�ɷ�</a>
                                </div>
                                <div style="display:block;" class="btn_box buy_enable fl pl30 pt120" id="cancelBusi">
                                    <a href="javascript:void(0);" onmousedown="this.className='hover'" onmouseup="this.className=''" onclick="cancelBusi();return false;">ȡ��</a>
                                </div>
                            </div>
                            <div class="popup_confirm" id="openWin_tipsMsg">
                                <div class="bg"></div>
                                <div class="tips_title">��ʾ��</div>
                                <div class="fs24 yellow pl55 pr30 pt40 line_height_12 h200" id="winText_tipsMsg">
                                </div>
                                <div class="tc">
                                    <a href="javascript:void(0)" class=" bt4" onmousedown="this.className='bt4on';" onmouseup="this.className='bt4';windowClose();">�����ɷ�</a> 
                                    <a class=" bt4 ml20" onmousedown="this.className='bt4on ml20'" onmouseup="this.className='bt4 ml20';closeRec();">ȡ������</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@ include file="/backinc.jsp"%>
        </form>
    </body>
    <script type="text/javascript">
    clearTimeout(timeOut);
    loadContent();
</script>
</html>
