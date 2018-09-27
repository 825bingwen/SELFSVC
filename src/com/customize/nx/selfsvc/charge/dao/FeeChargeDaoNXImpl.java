package com.customize.nx.selfsvc.charge.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.charge.model.InvoicePrintRecord;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeChargeDaoNXImpl extends BaseDaoImpl
{
    // ��־
    private static Log logger = LogFactory.getLog(FeeChargeDaoNXImpl.class);
    
    /**
     * �������ɷѼ�¼��־
     * 
     * @param cardChargeLogVO ���п���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateCardChargeLogNX", cardChargeLogVO);
    }
    
    /**
     * ���³�ֵ�ɷѼ�¼
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.updateChargeLog", cardChargeLogVO);
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * 
     * @param record
     * @see
     */
    public void insertInvoiceLog(InvoicePrintRecord record)
    {
        sqlMapClientTemplate.insert("charge.insertInvoiceLog", record);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * ��¼�û�������־֮ǰ�ȼ�¼�û���Ͷ�����
     * 
     * @param log
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(CashDetailLogPO log)
    {
        try
        {
            sqlMapClientTemplate.insert("charge.insertCashDetailLog", log);
        }
        catch (Exception e)
        {
            logger.error("��¼�û�Ͷ������쳣��", e);
        }
    }
}
