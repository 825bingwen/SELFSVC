package com.customize.hub.selfsvc.charge.service;

import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.terminfo.model.TerminalInfoPO;

public interface FeeChargeHubService
{
    // �������ɷ���־
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record);
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ظ��ɷѴ�����־
     * 
     * @param cashFeeErrorInfo
     * @see [�ࡢ��#��������#��Ա]
     */
    void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo);
    /**
     * �����û��Ľ������ͣ������ѽ���ѯ���ʺ��û��ĳ齱�
     * ��������ϸ������
     * @param [type] [��������]
     * @param [���ѽ��] [mcount]
     * @return �齱�Id
     * @exception/throws [Υ������] [Υ��˵��]
     * @see [�ࡢ��#��������#��Ա]
     * @depreced
     * @remark create yKF73963 ��2012-11-09�� OR_HUB_201209_706  ��������-�齱ƽ̨-�齱�ӿڸ��� 
     */
     public String getActId(String type,String mcount);
     
     /**
      * <У���ظ��ɷ�>
      * <������ϸ����>
      * @param termInfo
      * @param servnumber
      * @param tMoney ���ѽ���
      * @param terminalSeq Ӳ�����ɵ���ˮ��
      * @return true ������false�������⣬�ظ��ɷ�
      * @see [�ࡢ��#��������#��Ա]
     * @remark create by sWX219697 2015-4-16 17:29:35 OR_NX_201501_1030_����_���ڿ�������ҵ��֧��ϵͳ�����֪ͨ
      */
     public boolean checkCashFee(TerminalInfoPO termInfo, String servnumber, String tMoney, String terminalSeq);
}
