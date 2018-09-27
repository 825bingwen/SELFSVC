package com.customize.hub.selfsvc.activitiesrec.service;

import java.util.List;

import com.customize.hub.selfsvc.activitiesrec.dao.ActivitiesRecDaoHubImpl;
import com.customize.hub.selfsvc.activitiesrec.model.ActivityVO;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

public class ActivitiesRecServiceHubImpl implements ActivitiesRecHubService
{
    
    private ActivitiesRecDaoHubImpl activitiesRecDaoHubImpl;
    
    /**
     * ��ѯ�������
     * <������ϸ����>
     * @param activityVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ActivityVO> getActivitieGroups(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getActivitieGroups(activityVO);
    }
    
    /**
     * �������ѯ�����б�
     * <������ϸ����>
     * @param activityVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ActivityVO> getDangciByGroupId(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getDangciByGroupId(activityVO);
    }
    
    /**
     * ���ݻ����͵��α����ѯ����
     * <������ϸ����>
     * @param activityVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<ActivityVO> getDangciById(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getDangciById(activityVO);
    }
    
    /**
     * ��ȡ�ɷ���־OID
     * <������ϸ����>
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public String getChargeLogOID()
    {
        return activitiesRecDaoHubImpl.getChargeLogOID();
    }
    
    /**
     * �ɷѼ�¼��־sh_charge_log(Ͷ��֮��ҵ������֮ǰִ�м�¼)
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * ���½ɷѼ�¼
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.updateChargeLog(cardChargeLogVO);
    }
    
    /**
     * ���½ɷѼ�¼
     * <������ϸ����>
     * @param cardChargeLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * ���������¼
     * <������ϸ����>
     * @param privLogVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public void createPrivLog(PrivLogVO privLogVO)
    {
        activitiesRecDaoHubImpl.createPrivLog(privLogVO);
    }
    
    /**
     * ����ID��ѯchargeLog����
     * <������ϸ����>
     * @param activityVO
     * @see [�ࡢ��#��������#��Ա]
     */
    public List<CardChargeLogVO> getChargeLogById(CardChargeLogVO cardChargeLogVO)
    {
        return activitiesRecDaoHubImpl.getChargeLogById(cardChargeLogVO);
    }
    
    /**
     * ��¼��ӡ��Ʊ��־
     * <������ϸ����>
     * @param record
     * @see [�ࡢ��#��������#��Ա]
     */
    public void insertInvoiceLog(InvoicePrintPO record)
    { 
        activitiesRecDaoHubImpl.insertInvoiceLog(record);
    }

    public ActivitiesRecDaoHubImpl getActivitiesRecDaoHubImpl()
    {
        return activitiesRecDaoHubImpl;
    }

    public void setActivitiesRecDaoHubImpl(ActivitiesRecDaoHubImpl activitiesRecDaoHubImpl)
    {
        this.activitiesRecDaoHubImpl = activitiesRecDaoHubImpl;
    }
    
    
}
