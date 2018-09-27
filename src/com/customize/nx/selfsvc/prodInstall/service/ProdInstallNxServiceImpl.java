package com.customize.nx.selfsvc.prodInstall.service;

import java.util.List;
import java.util.Map;

import com.customize.nx.selfsvc.prodInstall.dao.ProdInstallNxDaoImpl;
import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;

/**
 * ���߿���
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  user
 * @version  [�汾��, Jul 29, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_NX_201303_280  ���������ն��Ż�����֮���߿���
 */
public class ProdInstallNxServiceImpl implements ProdInstallNxService
{
    private ProdInstallNxDaoImpl prodInstallNxDaoImpl;

    /**
     * ��ѯ��Ʒģ��
     * <������ϸ����>
     * @param tpltTempletPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<TpltTempletPO> qryTpltTempletList(TpltTempletPO tpltTempletPO)
    {
        return prodInstallNxDaoImpl.qryTpltTempletList(tpltTempletPO);
    }

    /**
     * ע��DAO
     * <������ϸ����>
     * @param prodInstallNxDaoImpl
     * @see [�ࡢ��#��������#��Ա]
     */
    public void setProdInstallNxDaoImpl(ProdInstallNxDaoImpl prodInstallNxDaoImpl)
    {
        this.prodInstallNxDaoImpl = prodInstallNxDaoImpl;
    }

    
    /**
     * ����д����¼
     * <������ϸ����>
     * @param LogInstallPO
     * @return ʧ��0��
     * @see [�ࡢ��#��������#��Ա]
     */
    public String addLogInstall(LogInstallPO logInstallPO)
    {
    	return prodInstallNxDaoImpl.addLogInstall(logInstallPO);
    }
    
    /**
     * ��¼�û�������־֮ǰ�ȼ�¼�û���Ͷ�����
     * 
     * @param params
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(Map<String, String> params)
    {
        if (null == params)
        {
            return;
        }
        
        // �û�Ͷ�ҵ������Ϣ����50;100����Ͷ�����Σ���һ��50���ڶ���100
        String cashDetail = params.get("cashDetail");
        if (null == cashDetail || "".equals(cashDetail.trim()))
        {
            return;
        }
        
        String termID = params.get("termID");
        String servNumber = params.get("telnum");
        String terminalSeq = params.get("terminalSeq");
        
        String[] cashes = cashDetail.split(";");
        
        CashDetailLogPO log = null;
        for (int i = 0; i < cashes.length; i++)
        {
            log = new CashDetailLogPO();
            log.setTermID(termID);
            log.setServNum(servNumber);
            log.setFormNum(terminalSeq);
            log.setCashFee(cashes[i]);
            
            prodInstallNxDaoImpl.insertCashDetailInfo(log);
        }       
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return prodInstallNxDaoImpl.getChargeLogOID();
    }
    
    /**
     * �ɷ���־
     * 
     * @param cardChargeLogVO �������ɷ���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * ��ȡ������־Id
     * 
     * @return
     * @see
     */
    public String getInstallId()
    {
        return prodInstallNxDaoImpl.getInstallId();
    }
    
    /**
     * ���¿�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(LogInstallPO logInstallPO)
    {
    	prodInstallNxDaoImpl.updateInstallLog(logInstallPO);
    }
    
    /**
     * �����������ɷ���־״̬
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLogStatus(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.updateCardChargeLogStatus(cardChargeLogVO);
    }
    
    /**
     * ���¿�����־��ע��Ϣ
     * 
     * @param logInstallPO
     * @see
     */
    public void updateInstallLogNotes(LogInstallPO logInstallPO)
    {
    	prodInstallNxDaoImpl.updateInstallLogNotes(logInstallPO);
    }
}
