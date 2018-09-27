package com.customize.nx.selfsvc.prodInstall.service;

import java.util.List;
import java.util.Map;

import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;


/**
 * 
 * ���߿���
 * <������ϸ����>
 * 
 * @author  user
 * @version  [�汾��, Jul 29, 2013]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]OR_NX_201303_280  ���������ն��Ż�����֮���߿���
 */
public interface ProdInstallNxService
{
    /**
     * ��ѯ��Ʒģ��
     * <������ϸ����>
     * @param tpltTempletPO
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<TpltTempletPO> qryTpltTempletList(TpltTempletPO tpltTempletPO);
    
    /**
     * ����д����¼
     * <������ϸ����>
     * @param LogInstallPO
     * @return ʧ��0��
     * @see [�ࡢ��#��������#��Ա]
     */
    public String addLogInstall(LogInstallPO logInstallPO);
    
    /**
     * ��¼�û�������־֮ǰ�ȼ�¼�û���Ͷ�����
     * 
     * @param params
     * @see 
     * @remark create yWX163692 2013/10/26 
     */
    public void insertCashDetailInfo(Map<String, String> params);
    
    /**
     * ��ȡ�ɷ���־OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
    
    /**
     * �ɷ���־
     * 
     * @param cardChargeLogVO �������ɷ���Ϣ
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * �����ۿ�ɹ�֮�󣬸�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ��ȡ������־Id
     * 
     * @return
     * @see
     */
    public String getInstallId();
    
    /**
     * ���¿�����־
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(LogInstallPO logInstallPO);
    
    /**
     * �����������ɷ���־״̬
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLogStatus(CardChargeLogVO cardChargeLogVO);
    
    /**
     * ���¿�����־��ע��Ϣ
     * 
     * @param logInstallPO
     * @see
     */
    public void updateInstallLogNotes(LogInstallPO logInstallPO);
}
