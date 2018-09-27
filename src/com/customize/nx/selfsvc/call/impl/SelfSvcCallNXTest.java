package com.customize.nx.selfsvc.call.impl;

import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.customize.nx.selfsvc.call.SelfSvcCallNX;
import com.gmcc.boss.common.base.ReturnWrap;
import com.gmcc.boss.selfsvc.call.IntMsgUtil;
import com.gmcc.boss.selfsvc.common.SSReturnCode;

public class SelfSvcCallNXTest implements SelfSvcCallNX
{
    private IntMsgUtil intMsgUtil;
    
    public IntMsgUtil getIntMsgUtil()
    {
        return intMsgUtil;
    }

    public void setIntMsgUtil(IntMsgUtil intMsgUtil)
    {
        this.intMsgUtil = intMsgUtil;
    }

    /**
     * �˻�����ѯ
     */
    public ReturnWrap queryBalance(Map<String, String> paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
            		"<process_code>cli_qry_balance_nx</process_code><verify_code></verify_code>" +
            		"<resp_time>20110630194643</resp_time><sequence><req_seq>5</req_seq>" +
            		"<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>" +
            		"<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]>" +
            		"</retmsg></retinfo></message_head><message_body>" +
            		"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            		"<lasttimebalance>31924.06</lasttimebalance><shouldpay>0.00</shouldpay>" +
            		"<newbalance>31924.06</newbalance></message_content>]]></message_body>" +
            		"</message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * ���˵���ѯ
     */
    public ReturnWrap qryMonthBill(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recQryArrear</menuid> "
                    + "<process_code>cli_qry_arrear</process_code><verify_code></verify_code><resp_time>20110513002353</resp_time> "
                    + "<sequence><req_seq>50</req_seq><operation_seq></operation_seq></sequence><retinfo> "
                    + "<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg> "
                    + "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?> "
                    + "<message_content><lastbal>1276.48</lastbal><curbal>1276.48</curbal><totalpayed>17.72</totalpayed>" 
                    + "<billitem><type>#����</type><itemname>�»�����|010000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��ֵҵ���|020000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����˸���|060000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ͨ�ŷ�|030000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���շ�|050000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���շ�|040000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����|010010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ײ������|010020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�л����·�|010030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ڰ��·�|010040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����һ���¹��ܷ�|010050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ڣ������۰�̨�����·�|010060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���Ű���|010070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���еش����Ű��·�|010080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���еش�����ͨ�����·�|010090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�������·�|010100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD��e��G3�����ʼǱ�������|010110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>GPRS����|010120000</itemname><fee>10.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ǳ������¹��ܷ�|010130000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����������|010140000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>400���ܷ�|010150000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��e��G3�ֻ������ײͷ�|010160000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ײ���ʹ�÷�(�շ�̯�ۼ�)|010170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��ݮ��BlackBerry���������书�ܷ�|010180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>BlackBerry ���ſͻ���չҵ���ܷ�|010190000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>BlackBerry ���ſͻ���չҵ��ͨ�ŷ�|010200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>12580��Ϣ��|020010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>12580�ƶ������|020020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ADC���ܷ�|020030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ��Խ�|020040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ADCͨ�ŷ�|020060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>GPRS����DDN��|020070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>IPֱͨ����|020090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TDGPRS����DDN��|020100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD�ֻ�����GPRS��|020110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD��E��GPRS��|020120000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>USSD��|020130000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��e��G3�ֻ�������|020150000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>WAP��|020170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>MobileMarket��Ϣ��|020210000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ٱ�����Ϣ��|020220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���幦�ܷ�|020230000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����ײͷ�|020250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������Ϣ��|020260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�˵����·�|020270000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�˿ڷ�|020290000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����POP���ܷ�|020320000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����QQ���ܷ�|020330000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���Ż�Ա�¹��ܷ�|020340000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�������佻�ѹ��ܷ�|020350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ͨѶ¼�¹��ܷ�|020360000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����λ��ҵ���¹��ܷ�|020370000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������д����ͨ�ŷ�|020380000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������д����ͨ�ŷ�|020390000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���������¹��ܷ�|020400000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ʼ��۰�̨���ŷ�|020410000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ʼ��۰�̨����Ϣ��|020420000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ѿ��|020460000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����E����|020470000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���Ų����¹��ܷ�|020500000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ռ������|020580000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�������ѷ�|020590000</itemname><fee>2.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����ֻ������£�|020620000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����ֻ���ͳ��|020630000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ũ����Ϣƽ̨��Ϣ��|020640000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ũ��ͨ����ͨ�ŷ�|020650000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����(MAS)��|020670000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>G+������Ϸ�����ܷ�|020680000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��������|020690000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������վ(MAS)��|020700000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ���Ƶ����15���ܷ�|020710000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>��ҵ�����(ADC)|020720000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ�����(MAS)��|020740000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ǿ����ʾ�¹��ܷ�|020770000</itemname><fee>10.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>ǿ�������¹��ܷ�|020780000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ȫ��ͨ���ʳ�;�ײ������|020790000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ͨ��|020800000</itemname><fee>0.00</fee></billitem><billitem><type>#����</type><itemname>�������ͨ��|020840000</itemname><fee>0.00</fee></billitem><billitem><type>#����</type><itemname>�ֻ���Ƶ���·�|020850000</itemname><fee>0.00</fee></billitem><billitem><type>#����</type><itemname>�ֻ���Ƶ�㲥��|020860000</itemname><fee>0.00</fee></billitem><billitem><type>#����</type><itemname>�ֻ�����GPRS��|020870000</itemname><fee>0.00</fee></billitem><billitem><type>#����</type><itemname>M2M��ҵ��������Ӧ�ù��ܷ�|020880000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ�����(ADC)ͨ�ŷ�|020890000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ���Ϸƽ̨��Ϣ��[GP]|020910000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ�֤ȯ�����|020920000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ�����������Ϣ��|020940000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���������|020950000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>MAS���Ʒ��ֵ���ܷ�|020960000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��E��GPRS��|020970000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�������־��ֲ����|020980000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���߾�������|020990000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>WLANԤ������|021000000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��ҵ��վ��ADC��ͨ�ŷ�|021010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>УѶͨ(ADC)ͨ�ŷ�|021040000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>УѶͨ(ADC)���ܷ�|050330000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�»�����ͳ��|021060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>����������Ϣ��|021070000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>��ҵ����·�|021080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��Ϣ��|021100000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�Ų��ܼ��¹���ʹ�÷�|021110000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�ƶ�CRM(ADC)��|021140000</itemname><fee>0.00</fee></billitem>"
                    + "  <billitem><type>#����</type><itemname>�ƶ�CRM(MAS)��|021160000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�ƶ�OA(ADC)��|021180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�OA(MAS)��|021200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�����(ADC)��|021220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�����(MAS)��|021240000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�������(ADC)��|021260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�������(MAS)��|021280000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�ƶ�ɳ����|021310000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ��ܻ���|021320000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>������־��|021350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����и���|021370000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������ʾ�¹��ܷ�|021380000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ��Ķ����ܷ�|021390000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�ֻ��������ӷ�|021420000</itemname><fee>6.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��˹�����ӹ��ܷ�|021430000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>һ��ͨ�����|021440000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>12582ũ��ͨ��Ϣ��|021460000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>M2M�ײͷ�|021470000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>M2Mר��APN�˿ڷ�|021480000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>����ҵ��Ӫ�������|021490000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>GPRSͨ�ŷ�|030010000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>IP��;��;��|030020000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>IP��;�л���|030030000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>IP���γ�;��|030060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>TD��e��G3�����ʼǱ�������|030120000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�۰�̨��;��;��|030130000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�����л���|030140000</itemname><fee>37.60</fee></billitem>"
                    + "  <billitem><type>#����</type><itemname>���ŷ�|030150000</itemname><fee>0.00</fee></billitem>"
                    + "    <billitem><type>#����</type><itemname>����Ϣͨ�ŷ�|030160000</itemname><fee>3.80</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�۰�̨��;�л���|030170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�۰�̨���γ�;��|030180000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�۰�̨���η�|030190000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�۰�̨IP��;��|030200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�۰�̨��;��|030210000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�۰�̨���η�|030220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ʳ�;��;��|030230000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ʳ�;�л���|030250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�������γ�;��|030260000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�������η�|030290000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ڣ������۰�̨����;��|030300000</itemname><fee>142.76</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>���ڣ������۰�̨������IP��;��|030320000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>���ڣ������۰�̨�����γ�;��|030370000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>���ڣ������۰�̨�����γ�;|030380000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>���ڣ������۰�̨�����η�|030390000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�������η�|030480000</itemname><fee>1.80</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�������γ�;��|030490000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TDIPͨ����|033010000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>TD��ͨ��;��|033020000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD��ͨ�л���|033030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD��ͨ���η�|033040000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>TD��Ƶ��;��|033050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>TD��Ƶ�л���|033060000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>TD��Ƶ���η�|033070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������|040010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ͨ���ŷ�|050010000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������|050020000</itemname><fee>0.30</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ͨ���ŷ�|050030000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�й���ɫ�������|050040000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������Ϣ��|050050000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�м��˾��|050060000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��ͯ�����|050070000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���Ż�ͨ��Ϣ��|050080000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��Ů�����|050090000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�����쳵��|050100000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>ũ��ͨ��|050110000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ����ʵ�(ADC)��|050140000</itemname><fee>0.00</fee></billitem>"
                    + " <billitem><type>#����</type><itemname>�ֻ���־���·�|050160000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ۻ��������|050170000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��Ϣ̨��Ϣ�ѷ�|050190000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ҽ��ͨ���ŷ�|050200000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>ҽ��ͨ���ŷ�|050220000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ƶ�����վ��|050240000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���ֺ������|050250000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ͼƬ������Ϣ��|050260000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>������־��|050280000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�й���ʮ���ܻ���ž��|050300000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�ֻ�����(ADC)���ܷ�|050310000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>��ҵ��վ��ADC�����ܷ�|050320000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ũ��ͨ���·�|050340000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>����ũ��ͨ�����|050350000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>�й���Ṥ��Э����ž��|050360000</itemname><fee>0.00</fee></billitem>"
                    + "<billitem><type>#����</type><itemname>���շ�|060010000</itemname><fee>0.00</fee></billitem>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }

    }
    
    /**
     * ���û���������������
     * 
     * @param map
     * @return
     */
    public ReturnWrap sendSMS(Map map)
    {
        ReturnWrap rtw = new ReturnWrap();
        rtw.setStatus(SSReturnCode.SUCCESS);
        
        return rtw;
    }
    
    /**
     * �ɷѲ�ѯ�ӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap qryfeeChargeAccount(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_busi_chargefee_hub</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921001956</resp_time><sequence><req_seq>33</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<subsname>��Ⱥ</subsname><chargeflag>00</chargeflag><shouldpay>0.00</shouldpay>"
                    + "</message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * �ɷѽӿ�
     * 
     * @param map
     * @return
     */
    public ReturnWrap chargeCommit(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
            		"<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
            		"<process_code>cli_busi_chargefee_nx</process_code><verify_code></verify_code>" +
            		"<resp_time>20110630194643</resp_time><sequence><req_seq>4</req_seq><operation_seq>" +
            		"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
            		"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
            		"<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            		"<formnum>at95120110630195051</formnum><dealNum>95188004080808909</dealNum>" +
            		"<subsname>#����</subsname><chargeamount>100</chargeamount><unitname>��������</unitname>" +
            		"<cycle></cycle><totalamount></totalamount><suminfo></suminfo><invoicecontent>" +
            		"��������1|10||��������2|10\n��������13|10||��������4|10\n��������5|10||��������6|10\n" +
            		"��������7|10||��������8|10\n��������9|10||��������10|10\n��������11|10||��������12|10\n" +
            		"��������13|10||��������14|10\n��������15|10||��������16|10\n" +
            		"</invoicecontent></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * �����ײ���Ϣ��ѯ
     * @param map
     * @return
     */
    public ReturnWrap qryPackageInfo(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            "<menuid>qrySerPri</menuid><process_code>cli_qry_taocan_nx</process_code><verify_code></verify_code>" +
            "<resp_time>20110715145931</resp_time><sequence><req_seq>27</req_seq><operation_seq></operation_seq>" +
            "</sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
            "</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
            "<rownum>6</rownum><privitem><name>(200����)����ʱ��</name><sumnum>12000</sumnum><usednum>60</usednum>" +
            "<leftnum>11940</leftnum></privitem><privitem><name>(300����)����ʱ��</name><sumnum>18000</sumnum><usednum>0</usednum>" +
            "<leftnum>18000</leftnum></privitem><privitem><name>100��400</name><sumnum>400</sumnum><usednum>16.8</usednum>" +
            "<leftnum>383.2</leftnum></privitem><privitem><name>����400����ͨ��(��;ͨ��)</name><sumnum>24000</sumnum>" +
            "<usednum>840</usednum><leftnum>23160</leftnum></privitem><privitem><name>GPRS����8M</name><sumnum>8388608</sumnum>" +
            "<usednum>0</usednum><leftnum>8388608</leftnum></privitem><privitem><name>GPRS5Ԫ��30M</name><sumnum>31457280</sumnum>" +
            "<usednum>1229824</usednum><leftnum>30227456</leftnum></privitem>" +
            "</message_content>]]></message_body>" +
            "</message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
        
    }
    
    /**
     * �����ֻ������ѯ�ͻ���Ϣ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap getCustinfo(Map map)
    {
            String response = "";
            
            String cycle = (String)map.get("cycle");
            if ("201206".equals(cycle))
            {
                response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBillItem</menuid><process_code>getcustinfo</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    
                    // �ͻ�����
                    + "<custname>��ǿ</custname>"
                    
                    // Ʒ��
                    + "<brandnm>������</brandnm>"
                    
                    // �����Ʒ
                    + "<productnm>�����Ʒ</productnm>"
                    
                    // �û�ID
                    + "<subsid>10000000000001</subsid>"
                    
                    // ������Ϣ 
                    
                    // ����+��ʼʱ��+����ʱ��+���˺�+�Ƿ�ϻ��û�
                    + "<cyclelist><cycle>20120601</cycle><startdate>20120501</startdate><enddate>20120630</enddate><acctid>10000001</acctid><unionacct>1</unionacct></cyclelist>"
//                    + "<cyclelist><cycle>20120516</cycle><startdate>20120515</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>0</unionacct></cyclelist>"
                    
                    + "</message_content>]]></message_body></message_response>";
            }
            else
            {
                response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryBillItem</menuid><process_code>getcustinfo</process_code><verify_code></verify_code>"
                    + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    
                    // �ͻ�����
                    + "<custname>��ǿ</custname>"
                    
                    // Ʒ��
                    + "<brandnm>������</brandnm>"
                    
                    // �����Ʒ
                    + "<productnm>�����Ʒ</productnm>"
                    
                    // �û�ID
                    + "<subsid>10000000000001</subsid>"
                    
                    // ������Ϣ 
                    
                    // ����+��ʼʱ��+����ʱ��+���˺�+�Ƿ�ϻ��û�
                    + "<cyclelist><cycle>20120501</cycle><startdate>20120501</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>1</unionacct></cyclelist>"
 //                   + "<cyclelist><cycle>20120516</cycle><startdate>20120515</startdate><enddate>20120531</enddate><acctid>10000001</acctid><unionacct>0</unionacct></cyclelist>"
                    
                    + "</message_content>]]></message_body></message_response>";
            }
            
                        
                ReturnWrap rtw = null;
                try
                {
                    rtw = intMsgUtil.parseResponse(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rtw;
    }
    
    /**
     * �����ֻ������ѯ�ͻ���Ϣ
     * 
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryMonthBill_new(Map map)
    {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                + "<menuid>qryBillItem</menuid><process_code>getcustinfo</process_code><verify_code></verify_code>"
                + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
                + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                
                +"<custbill>"
                +" <billinfo>"
                
                // ������Ϣ
                +"     <billfixed>"
                +"         <fee><name>�ײͼ��̶�����</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <feegroup>"
                +"             <name>�ײ������</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>����ͨ�ŷ�</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"                 <fee><name>������</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"                 <fee><name>�̲��ŷ�</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"             </subfee>"
                +"         </feegroup>"
                +"         <fee><name>������ֵҵ���</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>           "
                +"         <fee><name>�Żݼ���</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <fee><name>���Ŵ�����</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>"
                +"         <fee><name>���˴�����</name><value>10.00</value><sortorder/><isshow>1</isshow></fee>         "
                +"     </billfixed>"
                
                // ����ҵ��
                +"     <feedetails>"
                +"         <feegroup>"
                +"             <name>�ײͺ͹̶���</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>ȫ��ͨ128�ײ�</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>��������ײ�</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>���������ײͰ�</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>wlan</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>�ײ���������</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>���ڳ�;</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>�۰ĳ�;</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value><sortorder/></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>�ײ�������</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>wlanʱ��</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>����ҵ��</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>ʡ��wlan����</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>�ײ���˲��ŷ�</name>"
                +"             <sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>���ڶ���</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>����</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>������ֵҵ���</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>��ͨ����</name><value>10.00</value><sortorder/></fee>"
                +"                 <fee><name>��������</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>�����˸���</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>telnum</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"         <feegroup>"
                +"             <name>����</name><sortorder/>"
                +"             <subfee>"
                +"                 <fee><name>222222</name><value>10.00</value><sortorder/></fee>"
                +"             </subfee>"
                +"             <fee><name>�ϼ�</name><value>10.00</value></fee>"
                +"         </feegroup>"
                +"     </feedetails>"

                +" </billinfo>"
                
                // �ʱ���Ϣ
                +" <acctbalance>"
                       //<!--�����˻����-->
                +"     <leftmoney1>10.00</leftmoney1>"
                       //<!--Э������-->
                +"     <leftmoney2>10.00</leftmoney2>     "
                       //<!--��������-->
                +"     <consume>10.00</consume>"
                       //<!--����ĩǷ��-->
                +"     <debtfee>10.00</debtfee>"
                +"     <acctlist>"
                +"         <acct>"
                               //<!--0 ���� 1 Э���-->
                +"             <accttype>0</accttype>"
                               //<!--����ĩ���� -->
                +"             <lastval>10.00</lastval>"
                               //<!--���³�ֵ -->
                +"             <income>10.00</income>"
                               //<!-- ���ڷ���  -->"
                +"             <returnfee>10.00</returnfee>"
                               //<!-- �˷� -->"
                +"             <backfee>5.00</backfee>"
                               //<!-- ���ڿ���  -->"
                +"             <canuse>10.00</canuse>"
                               //<!-- ����֧�� -->"
                +"             <outfee>10.00</outfee>"
                               //<!-- ����֧�� -->"
                +"             <otherfee>10.00</otherfee>"
                               //<!-- ����ĩ���  -->"
                +"             <thisval>10.00</thisval >"
                +"         </acct>"
                +"     </acctlist>"
                +" </acctbalance> "
                
                // �ʷ��Ƽ�
                +" <recommend>�Ƽ�</recommend>"
                
                // <!-- ��ѯ���ڵ���ǰ�������·����м����·��ؼ����� -->
                +" <billhalfyeartrend>"
                +"     <bill><month>201201</month><money>100.00</money></bill>"
                +"     <bill><month>201202</month><money>200.00</money></bill>"
                +"     <bill><month>201203</month><money>300.00</money></bill>"
                +"     <bill><month>201204</month><money>400.00</money></bill>"
                +"     <bill><month>201205</month><money>500.00</money></bill>"
                +"     <bill><month>201206</month><money>600.00</money></bill>"
                +" </billhalfyeartrend>"
                
                // ������Ϣ
                +" <scoreinfo>"
                +"     <score>"
                //         <!--���ڿ���-->
                +"         <lastavail>10.00</lastavail>"
                //         <!--��������-->
                +"         <consume>10.00</consume>"
                //         <!--���ڽ���-->
                +"         <award>10.00</award>"
                //         <!--���ڿ���ת��-->
                +"         <transin>10.00</transin>"
                //         <!--���ڶһ���-->
                +"         <exchange>10.00</exchange>"
                //         <!--���ڿ���ת��-->
                +"         <transout>10.00</transout>"
                //         <!--��������-->
                +"         <clear>10.00</clear>"
                //         <!--���û���-->
                +"         <thisavail>10.00</thisavail>"
                //         <!--����������-->
                +"         <totalin>10.00</totalin>"
                //         <!--����������-->
                +"         <totalout>10.00</totalout>"
                +"     </score>"
                +" </scoreinfo>"
                
                // ͨѶ����ϸ
                +" <pkginfo>"
                +"     <pkg>"
                +"         <!--�ײ�ID-->"
                +"         <pkgid>11111</pkgid>"
                +"         <!--�ײ�����-->"
                +"         <pkgname>�ײ�����</pkgname>"
                +"         <!--�ײ�����-->"
                +"         <pkgdesc>�ײ�����</pkgdesc>"
                +"         <!--�ַ��б�-->"
                +"         <privlist>              "
                +"             <priv>"
                                   //<!--�ʷ�����ID-->
                +"                 <rateid>1000014</rateid>"
                                   //<!--ͨѶ���ͣ�ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR-->
                +"                 <freetype>GSM</freetype>"
                                   //<!--���͵�����-->
                +"                 <total>22</total>"
                                   //<!--�Ѿ�ʹ�õ���-->
                +"                 <used>15</used>"
                                   //<!--��λ 1 ���� 2 ʱ��(��) 3 ����(Ԫ) 4 ����(KB) 5 ����(M) 6 ʱ��(Сʱ) -->
                +"                 <attrtype>1</attrtype>"
                                   //<!--�ʷ���������-->
                +"                 <feename>111111</feename>"
                +"             </priv>"
                +"         </privlist>"
                +"     </pkg>"
                +" </pkginfo>"
                
                // <!--����ҵ���-->
                +" <spbill>"
                +"     <sp>"
                //         <!--ҵ��˿�-->
                +"         <spcode>ҵ��˿�</spcode>"
                //         <!--��ҵ����-->
                +"         <spname>��ҵ����</spname>"
                //         <!--ҵ������-->
                +"         <servcode>ҵ������</servcode>"
                //         <!--ʹ�÷�ʽ-->
                +"         <usetype>ʹ�÷�ʽ</usetype>"
                //         <!--��������-->
                +"         <feetype>��������</feetype>"
                //         <!--����-->
                +"         <fee>10.00</fee>"
                +"     </sp>"
                +" </spbill>"
                
                // <!--��������ϸ-->
                +" <inoutdetail>"
                
                +"     <inout>"
                //         <!--�ֽ���Э��� 0:�ֽ� 1��Э��� 2:ר���˻���� 3��Э���-->"
                +"         <feetype>0</feetype>"
                //         <!--ʱ��-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--��ʽ-->"
                +"         <model>��ʽ</model>"
                //         <!--���-->"
                +"         <type>���</type>"
                //         <!--���-->"
                +"         <fee>10.00</fee>"
                //         <!--��ע-->"
                +"         <desc>��ע...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--�ֽ���Э��� 0:�ֽ� 1��Э��� 2:ר���˻���� 3��Э���-->"
                +"         <feetype>1</feetype>"
                //         <!--ʱ��-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--��ʽ-->"
                +"         <model>��ʽ</model>"
                //         <!--���-->"
                +"         <type>���</type>"
                //         <!--���-->"
                +"         <fee>10.00</fee>"
                //         <!--��ע-->"
                +"         <desc>��ע...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--�ֽ���Э��� 0:�ֽ� 1��Э��� 2:ר���˻���� 3��Э���-->"
                +"         <feetype>2</feetype>"
                //         <!--ʱ��-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--��ʽ-->"
                +"         <model>��ʽ</model>"
                //         <!--���-->"
                +"         <type>���</type>"
                //         <!--���-->"
                +"         <fee>10.00</fee>"
                //         <!--��ע-->"
                +"         <desc>��ע...</desc>"
                +"     </inout>"
                
                +"     <inout>"
                //         <!--�ֽ���Э��� 0:�ֽ� 1��Э��� 2:ר���˻���� 3��Э���-->"
                +"         <feetype>3</feetype>"
                //         <!--ʱ��-->"
                +"         <date>2012-01-01 01:01:01</date>"
                //         <!--��ʽ-->"
                +"         <model>��ʽ</model>"
                //         <!--���-->"
                +"         <type>���</type>"
                //         <!--���-->"
                +"         <fee>10.00</fee>"
                //         <!--��ע-->"
                +"         <desc>��ע...</desc>"
                +"     </inout>"
                
                +" </inoutdetail>"
                
                +"</custbill>"
                
                + "</message_content>]]></message_body></message_response>";
                        
                ReturnWrap rtw = null;
                try
                {
                    rtw = intMsgUtil.parseResponse_MonthFee_NX(response);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                return rtw;
    }

    /**
     * ��ѯ�û�����֧����ʽ
     * 
     * @param paramMap
     * @return
     * @see 
     */
    public ReturnWrap qryChargeType(Map<String, String> paramMap)
    {
        try
        {
            String menuID = (String)paramMap.get("menuID");
            String touchOID = (String)paramMap.get("touchOID");
            String operID = (String)paramMap.get("operID");
            String termID = (String)paramMap.get("termID");
            
            String telnum = (String)paramMap.get("telnum");// �ֻ�����
            String payType = (String) paramMap.get("paytype");
            
            Document doc = DocumentHelper.createDocument();
            Element eBody = doc.addElement("message_content");
            
            // �ֻ�����
            Element eReq_telnum = eBody.addElement("telnum");
            eReq_telnum.addText(telnum);
            
            // ֧����ʽ
            Element eReq_paytype = eBody.addElement("paytype");
            eReq_paytype.addText(payType);
            
            Document docXML = intMsgUtil.createMsg(doc, "cli_qry_chargetype_nx", menuID, touchOID, "1", telnum, operID, termID);
            
            return intMsgUtil.invoke(docXML);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            return rtw;
        }
    }
    
    /**
     * �ɷѽӿ�
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/02/04 R003C13L01n01 OR_huawei_201302_122 
     */
    public ReturnWrap queryInvoice(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>" +
                    "<message_head version=\"1.0\"><menuid>feeCharge</menuid>" +
                    "<process_code>cli_busi_chargefee_nx</process_code><verify_code></verify_code>" +
                    "<resp_time>20110630194643</resp_time><sequence><req_seq>4</req_seq><operation_seq>" +
                    "</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>0</retcode><retmsg>" +
                    "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>" +
                    "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>" +
                    "<formnum>at95120110630195051</formnum><dealNum>95188004080808909</dealNum>" +
                    "<subsname>#����</subsname><chargeamount>100</chargeamount><unitname>��������</unitname>" +
                    "<cycle></cycle><totalamount></totalamount><suminfo></suminfo><invoicecontent>" +
                    "��������1|10||��������2|10\n��������13|10||��������4|10\n��������5|10||��������6|10\n" +
                    "��������7|10||��������8|10\n��������9|10||��������10|10\n��������11|10||��������12|10\n" +
                    "��������13|10||��������14|10\n��������15|10||��������16|10\n" +
                    "</invoicecontent></message_content>]]></message_body></message_response>";
            
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * ���ýӿڻ�ȡ��Ʒ���ȷ����Ϣ
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductRecInfo(Map map)
    {
        try
        {
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recMainProdChange</menuid>"
                    + "<process_code>cli_busi_MainIntProductRec</process_code><verify_code></verify_code><unicontact></unicontact>"
                    + "<resp_time>20120425100133</resp_time><sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
                    + "<retcode>0</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content>"
                    + "<prodlist><type>A</type><prodid>Christ_bag1</prodid><prodname>Christ��Ʒ��1</prodname><begindate>2012-04-25 10:06:53</begindate>"
                    + "<enddate>2012-04-25 10:06:53</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate>"
                    + "</privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>C06</prodid><prodname>��������</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>H01</prodid><prodname>������ʾ</prodname><begindate>2002-07-02 17:33:40</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>SELFRING</prodid><prodname>����</prodname><begindate>2006-02-23 11:17:50</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>Z07</prodid><prodname>�ƶ�ȫʱͨ</prodname><begindate>2006-08-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>M03</prodid><prodname>�������ο�ͨ</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname>���μ����</pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_roamid</prodid><prodname>���μ����</prodname><begindate>2001-03-23 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pp.gt.csl</prodid><prodname>�ں�V��</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>pkg_cslbx.634</prodid><prodname>���������ײͱ�ѡ��</prodname><begindate>2007-09-01 00:00:00</begindate><enddate>"
                    + "</enddate><pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>O</type><prodid>gl.base.999251.634</prodid><prodname>ȫʱͨЭ������3Ԫ</prodname><begindate>2007-09-01 00:00:00</begindate><enddate></enddate>"
                    + "<pkgname></pkgname><privid>gl.base.999251.634</privid><privname>ȫʱͨЭ������3Ԫ</privname><privbegindate>2007-09-01 00:00:00</privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "<prodlist><type>D</type><prodid>Christmasappend</prodid><prodname>Christmas��ֵ1</prodname><begindate>2012-04-12 15:46:20</begindate><enddate>2012-04-26 00:00:00</enddate>"
                    + "<pkgname></pkgname><privid></privid><privname></privname><privbegindate></privbegindate><privenddate></privenddate><reason></reason></prodlist>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * ���ýӿ�ִ�������Ʒת��
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap mainProductChangeSubmit(Map map)
    {
        try
        {
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>recMainProdChange</menuid><process_code>cli_busi_ChangeProductSubmit</process_code><verify_code></verify_code><unicontact></unicontact><resp_time>20120425100313</resp_time><sequence><req_seq>5</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[������ҵ������������ԭ��:��ʾ����Ʒ[����]���������[��Ʒ���]ҵ��!......]]></retmsg></retinfo></message_head></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * ���ýӿڻ�ȡ��ת����Ʒ
     * 
     * @param map
     * @return
     * @remark create cKF76106 2013/06/15 R003C13L06n01 OR_NX_201303_281
     */
    public ReturnWrap qryChangeMainProduct(Map map)
    {
        try
        {
            
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">" +
            		"<menuid>recMainProdChange</menuid><process_code>cli_qry_changeMainProduct</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content><prodlist><prodid>name5</prodid></prodlist><prodlist><prodid>name6</prodid></prodlist><prodlist><prodid>name7</prodid></prodlist><prodlist><prodid>_pp.mz.cyk</prodid></prodlist><prodlist><prodid>pp.mz.2007</prodid></prodlist><prodlist><prodid>Christmasmain</prodid></prodlist><prodlist><prodid>zzwmainprod</prodid></prodlist><prodlist><prodid>pp.gt.st</prodid></prodlist><prodlist><prodid>pp.mz.cyk</prodid></prodlist><prodlist><prodid>123_test</prodid></prodlist><prodlist><prodid>pp.mz.lj</prodid></prodlist><prodlist><prodid>_pp.eo.pr</prodid></prodlist><prodlist><prodid>gaoprod</prodid></prodlist><prodlist><prodid>pp.gt.88rl.new.999</prodid></prodlist><prodlist><prodid>pp.gt.ct</prodid></prodlist><prodlist><prodid>pp.gt.68h.634</prodid></prodlist><prodlist><prodid>pp.gt.yg.634</prodid></prodlist><prodlist><prodid>pp.eo.qsk.634</prodid></prodlist><prodlist><prodid>pp.eo.sx.634</prodid></prodlist><prodlist><prodid>pp.gt.68y.634</prodid></prodlist><prodlist><prodid>pp.gt.68</prodid></prodlist><prodlist><prodid>pp.gt.88tcy.634</prodid></prodlist><prodlist><prodid>pp.gt.ygp.634</prodid></prodlist><prodlist><prodid>pp.eo.ch.634</prodid></prodlist><prodlist><prodid>pp.eo.es</prodid></prodlist><prodlist><prodid>pp.eo.szxctk1</prodid></prodlist><prodlist><prodid>pp.eo.ffct.634</prodid></prodlist><prodlist><prodid>pp.eo.07szxctk.634</prodid></prodlist><prodlist><prodid>pp.eo.fk</prodid></prodlist><prodlist><prodid>pp.eo.xyh.634</prodid></prodlist><prodlist><prodid>pp.eo.zfgj.634</prodid></prodlist><prodlist><prodid>pp.eo.ty</prodid></prodlist><prodlist><prodid>pp.eo.fr</prodid></prodlist><prodlist><prodid>pp.gt.lc</prodid></prodlist><prodlist><prodid>pp.gt.stp</prodid></prodlist><prodlist><prodid>pp.eo.pr</prodid></prodlist><prodlist><prodid>pp.gt.ct1p</prodid></prodlist><prodlist><prodid>pp.gt.lcp</prodid></prodlist><prodlist><prodid>pp.gt.frp</prodid></prodlist><prodlist><prodid>pp.gt.cslp</prodid></prodlist><prodlist><prodid>pp.gt.ct2p</prodid></prodlist></message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    
    /**
     * �������Ͳ�ѯ����ֵ
     * 
     * @param map
     * @return
     * @remark create zWX176560 2013/08/22 R003C13L09n01 OR_NX_201308_595
     */
    public ReturnWrap qryUserScoreInfoByType(Map paramMap)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid>qryUserScoreInfoByType</menuid><process_code>cli_qry_userscoreInfo</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
                    + "<message_content><crset><COL_0>ConsumeScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>InnetScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>BrandScore</COL_0><COL_1>1111</COL_1></crset><crset><COL_0>OtherAward</COL_0><COL_1>1111</COL_1></crset></message_content>]]></message_body></message_response>";
            
          //String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
          //        + "<menuid>qryUserScoreInfoByType</menuid><process_code>cli_qry_userscoreInfo</process_code><verify_code></verify_code><resp_time>20130619150956</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[No Information!]]></retmsg></retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
          //        + "<message_content></message_content>]]></message_body></message_response>";
            
          // String response = "<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN"><html><head><title>500 Internal Server Error</title></head><body><h1>Internal Server Error</h1><p>The server encountered an internal error or misconfiguration and was unable to complete your request.</p><p>Please contact the server administrator,you@example.com and inform them of the time the error occurred,and anything you might have done that may have"
          // + "caused the error.</p><p>More information about this error may be available in the server error log.</p><hr><address>Apache/2.0.63 (Unix) mod_fastcgi/2.4.6 Server at 192.168.11.145 Port 9700</address></body></html>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }
    @Override
    public ReturnWrap addChargeType(Map<String, String> paramMap)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReturnWrap cancelChargeType(Map<String, String> paramMap)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * �հ׿���Դ��ѡ
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap blankCardTmpPick(Map map)
    {
        try
        {
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
                    + "<menuid></menuid><process_code>cli_blankcardtmppick_nx</process_code><verify_code></verify_code>"
                    + "<resp_time>20140311153704</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq>"
                    + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
                    + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
                    + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
                    + "<siminfo>89860050150353805206,460006345032906,B074F86AA65E4F83C1AE0D2DA4A8B79A,+8613800634500,1234,6767,97827907,89550562,</siminfo>"
                    + "</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
        }
    }

    /**
     * У��հ׿���Դ�Ƿ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkBlankNo(Map map)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
				+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_chkblankno_nx"
				+"</process_code><verify_code></verify_code><resp_time>20140306193816</resp_time>"
				+"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head></message_response>";
			
			return intMsgUtil.parseResponse(response);
		} 
    	catch (Exception e)
    	{
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    	
    }
    
    /**
     * ����ʱ֤������У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkCertSubs(Map map)
    {
    	try {
    		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
    			+"<menuid>openAccount</menuid><process_code>cli_chkcertsubs_nx</process_code><verify_code></verify_code>"
    			+"<resp_time>20140305180720</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence>"
    			+"<retinfo><rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
    			+"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
    			+"<ifValid>1</ifValid></message_content>]]></message_body></message_response>";
    		
    		return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    }

    /**
     * �ſ�У��
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap chkTelSimcard(Map map)
    {
    	try {
			String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
				+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_chktelsimcard_nx"
				+"</process_code><verify_code></verify_code><resp_time>20140307115011</resp_time>"
				+"<sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo>"
				+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
				+"</retmsg></retinfo></message_head></message_response>";
			return intMsgUtil.parseResponse(response);
		} catch (Exception e) {
			ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");
            
            return rtw;
		}
    }

    /**
     * ��ѯ�ֻ������б�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap qryNumberByProdid(Map map)
    {
        try {
//            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
//                    + "<message_head version=\"1.0\"><menuid></menuid><process_code>cli_qry_numbynet</process_code>"
//                    + "<verify_code></verify_code><resp_time>20110621111358</resp_time><sequence><req_seq>28</req_seq>"
//                    + "<operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode>"
//                    + "<retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head>"
//                    + "<message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
//                    + "<tellist><telnum>18709610009</telnum><productinfo></productinfo><fee>1000</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709611604</telnum><productinfo></productinfo><fee>2000</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709614964</telnum><productinfo></productinfo><fee>3000</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709617084</telnum><productinfo></productinfo><fee>400</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709614472</telnum><productinfo></productinfo><fee>50</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610013</telnum><productinfo></productinfo><fee>60</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709612745</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610034</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610001</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610018</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709614134</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610022</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709617424</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610039</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610006</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709614870</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709612647</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610754</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610016</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709617947</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709618430</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709618174</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709612074</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610020</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610037</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610004</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709614740</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709610025</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709617154</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "<tellist><telnum>18709613914</telnum><productinfo></productinfo><fee>0</fee><org_id>NX.01.S01</org_id><region></region><col>hellow ɽ�����ϲ���12</col></tellist>"
//                    + "</message_content>]]></message_body></message_response>";
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\"><menuid>"
        		+"openAccount</menuid><process_code>cli_qry_numbynet</process_code><verify_code></verify_code><resp_time>20140311185636"
        		+"</resp_time><sequence><req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype><retcode>"
        		+"100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
        		+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
        		+"<tellist><telnum>13706387658</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386386</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706389126</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386006</telnum><productinfo>δ֪</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388159</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388259</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385226</telnum><productinfo>δ֪</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706388136</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386786</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385129</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706387444</telnum><productinfo>δ֪</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386477</telnum><productinfo>δ֪</productinfo><fee>128</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385469</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706387176</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706386565</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP.01.02.04</org_id><region>634</region><tellevel>����������</tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706384958</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP.01.02.01</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"<tellist><telnum>13706385768</telnum><productinfo>δ֪</productinfo><fee>0</fee><org_id>SD.LP</org_id><region>634</region><tellevel></tellevel><low_consum_fee>10</low_consum_fee><low_consum_pre>100</low_consum_pre><low_inservice_time>120</low_inservice_time></tellist>"
        		+"</message_content>]]></message_body></message_response>";
            return intMsgUtil.parseResponse(response);
        } catch (Exception e) {
            ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
        }
    }

    /**
     * �������
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap reckonRecFee(Map map)
    {
    	try{
    	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
    		+"<message_head version=\"1.0\"><menuid>openAccount</menuid><process_code>cli_reckonrecfee_nx"
    		+"</process_code><verify_code></verify_code><resp_time>20140311155844</resp_time><sequence>"
    		+"<req_seq>1</req_seq><operation_seq></operation_seq></sequence><retinfo><rettype>0</rettype>"
    		+"<retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]></retmsg>"
    		+"</retinfo></message_head><message_body><![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>"
    		+"<message_content><feelist><feename>�������û�����Ԥ����1</feename><realfee>800</realfee><num>"
    		+"1</num><feeitem>Prepay</feeitem><discountfee>0</discountfee><receivablefee>800</receivablefee>"
    		+"</feelist></message_content>]]></message_body></message_response>";
    	
    	return intMsgUtil.parseResponse(response);
    	}catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }

    /**
     * ������Դ��ѡ�ӿ�
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap telNumTmpPick(Map map)
    {
    	try{
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response>"
        		+"<message_head version=\"1.0\"><menuid></menuid><process_code>cli_telnumtmppick_nx"
        		+"</process_code><verify_code></verify_code><resp_time>20140306161150</resp_time>"
        		+"<sequence><req_seq>2</req_seq><operation_seq></operation_seq></sequence><retinfo>"
        		+"<rettype>0</rettype><retcode>100</retcode><retmsg><![CDATA[Processing the request succeeded!]]>"
        		+"</retmsg></retinfo></message_head></message_response>";
        	
        	return intMsgUtil.parseResponse(response);
    	}
    	catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }

    /**
     * ����
     * <������ϸ����>
     * @param map
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public ReturnWrap terminalInstall(Map map)
    {
    	try{
        	String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
        		+"<menuid>cashChargeByOpen</menuid><process_code>cli_terminalinstall_nx</process_code><verify_code>"
        		+"</verify_code><resp_time>20140312130130</resp_time><sequence><req_seq>2</req_seq><operation_seq>"
        		+"</operation_seq></sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
        		+"<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
        		+"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content><installformnum>" +
        		"634140408898309356</installformnum><oid>88009898309338</oid></message_content>]]></message_body></message_response>";
        	
        	return intMsgUtil.parseResponse(response);
    	}
    	catch(Exception ex)
    	{
    		ReturnWrap rtw = new ReturnWrap();
            rtw.setStatus(0);
            rtw.setReturnMsg("");

            return rtw;
    	}
    }
    
	@Override
	public ReturnWrap getMonInvoiceData(Map<String, String> paramMap) {
		try
        {
    		// ��ȷ�ķ��ر���
            String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_response>" +
            		"<message_head version=\"1.0\">" +
            		"<menuid>recMonthInvoice</menuid>" +
            		"<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
            		"<verify_code></verify_code>" +
            		"<resp_time>20140520191951</resp_time>" +
            		"<sequence>" +
            		"<req_seq>2</req_seq>" +
            		"<operation_seq></operation_seq>" +
            		"</sequence>" +
            		"<retinfo>" +
            		"<rettype>0</rettype>" +
            		"<retcode>0</retcode>" +
            		"<retmsg><![CDATA[Processing the request succeeded!]]></retmsg>" +
            		"</retinfo>" +
            		"</message_head>" +
            		"<message_body>" +
            		"<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
            		"<message_content>" +
            		"<invoicelist><name>paynumno</name><value>9522003091182</value></invoicelist>" +
            		"<invoicelist><name>username</name><value>�Ĺ�</value></invoicelist>" +
            		"<invoicelist><name>telnumber</name><value>13469625267</value></invoicelist>" +
            		"<invoicelist><name>formnum</name><value>88009855697681</value></invoicelist>" +
            		"<invoicelist><name>callfeeCreateTime</name><value>2014.04---2014.04</value></invoicelist>" +
            		"<invoicelist><name>totalmoneydx</name><value>��ʰԲ��</value></invoicelist>" +
            		"<invoicelist><name>totalmoney</name><value>��50.00</value></invoicelist>" +
            		"<invoicelist><name>note</name><value></value></invoicelist>" +
            		"<invoicelist><name>WorkStation</name><value>ʯ��ɽ����</value></invoicelist>" +
            		"<invoicelist><name>time</name><value>2014     06      23</value></invoicelist>" +
            		"<invoicelist><name>printtime</name><value>2014.06.23</value></invoicelist>" +
            		"<invoicelist><name>OperatorName</name><value>ϵͳ����Ա</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1Name</name><value>ͨ�ŷ����:</value></invoicelist>" +
            		"<invoicelist><name>ContentItem1</name><value>50.00</value></invoicelist>" +
            		"<invoicelist><name>thisinvamt</name><value>���η�Ʊ���: 88.12Ԫ</value></invoicelist>" +
            		"</message_content>]]>" +
            		"</message_body></message_response>";
    		
    		// δ��ȫ���˵��쳣���ر���
    		/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    				"<message_response>" +
    				"<message_head version=\"1.0\">" +
    				"<menuid>recMonthInvoice</menuid>" +
    				"<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
    				"<verify_code></verify_code>" +
    				"<resp_time>20140520152439</resp_time>" +
    				"<sequence><req_seq>4</req_seq><operation_seq></operation_seq></sequence>" +
    				"<retinfo>" +
    				"<rettype>0</rettype>" +
    				"<retcode>966391</retcode>" +
    				"<retmsg><![CDATA[�ʺţ�[6348888934481]�����ڣ�[20140101]���������˵�û����ȫ���ˣ����ܴ�ӡ�����ڷ�Ʊ��]]></retmsg>" +
    				"</retinfo>" +
    				"</message_head></message_response>";*/
    		
    		// �Ѵ�ӡ���ķ�Ʊ�쳣���ر���
    		/*String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?>" +
    				"<message_response>" +
    				"<message_head version=\"1.0\">" +
    				"<menuid>recMonthInvoice</menuid>" +
    				"<process_code>cli_qry_monthinvoiceinfo_sd</process_code>" +
    				"<verify_code></verify_code>" +
    				"<resp_time>20140520154904</resp_time>" +
    				"<sequence>" +
    				"<req_seq>4</req_seq>" +
    				"<operation_seq></operation_seq>" +
    				"</sequence>" +
    				"<retinfo>" +
    				"<rettype>0</rettype>" +
    				"<retcode>966515</retcode>" +
    				"<retmsg><![CDATA[��ӡ�����Ѿ��ﵽ����ӡ�������븴λ���ٴδ�ӡ��]]></retmsg>" +
    				"</retinfo>" +
    				"</message_head></message_response>";*/
    		
            return intMsgUtil.parseResponse(response);
        }
        catch (Exception e)
        {
            return new ReturnWrap();
        }
	}

	/**
     * ��ѯ���ڣ����ģ�
     * 
     * @param map
     * @return
     * @see
     * @remark create jWX216858 2014/06/17 OR_NX_201406_553_����_[Ӫ����]�����ն��ṩ��ֵ˰�½ᷢƱ��ӡ
     */
	@Override
	public ReturnWrap qryBillCycle(Map<String, String> paramMap)
	{
		String response = "<?xml version=\"1.0\" encoding=\"GBK\" ?><message_response><message_head version=\"1.0\">"
            + "<menuid>recMonthInvoice</menuid><process_code>cli_qry_billCycleCustInfo</process_code><verify_code></verify_code>"
            + "<resp_time>20100921004740</resp_time><sequence><req_seq>89</req_seq><operation_seq></operation_seq>"
            + "</sequence><retinfo><rettype>0</rettype><retcode>100</retcode><retmsg>"
            + "<![CDATA[Processing the request succeeded!]]></retmsg></retinfo></message_head><message_body>"
            + "<![CDATA[<?xml version=\"1.0\" encoding=\"GBK\" ?><message_content>"
            
            // �ͻ�����
            + "<custname>��ǿ</custname>"
            
            // Ʒ��
            + "<brandnm>������</brandnm>"
            
            // �����Ʒ
            + "<productnm>�����Ʒ</productnm>"
            
            // ��������
            + "<regionname>ɽ��</regionname>"
            
            // �û�ID
            + "<userid>10000000000001</userid>"
            
            // �û��ȼ�
            + "<creditlevel>3</creditlevel>"
            
            // ������Ϣ 
            
            // ����+��ʼʱ��+����ʱ��+���˺�+�Ƿ�ϻ��û�
            + "<cyclelist><cycle>20120601</cycle><startdate>2012-06-01</startdate><enddate>2012-06-30</enddate><acctid>10000001</acctid><unionacct>1</unionacct><unionacct>2</unionacct></cyclelist>"
            
            + "</message_content>]]></message_body></message_response>";
            
            ReturnWrap rtw = null;
            try
            {
                rtw = intMsgUtil.parseResponse(response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
            return rtw;
	}
}
