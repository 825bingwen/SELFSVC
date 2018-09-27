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
     * 查询促销活动组
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getActivitieGroups(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getActivitieGroups(activityVO);
    }
    
    /**
     * 根据组查询档次列表
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getDangciByGroupId(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getDangciByGroupId(activityVO);
    }
    
    /**
     * 根据活动编码和档次编码查询档次
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getDangciById(ActivityVO activityVO)
    {
        return activitiesRecDaoHubImpl.getDangciById(activityVO);
    }
    
    /**
     * 获取缴费日志OID
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getChargeLogOID()
    {
        return activitiesRecDaoHubImpl.getChargeLogOID();
    }
    
    /**
     * 缴费记录日志sh_charge_log(投币之后、业务受理之前执行记录)
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * 更新缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.updateChargeLog(cardChargeLogVO);
    }
    
    /**
     * 更新缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        activitiesRecDaoHubImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * 增加受理记录
     * <功能详细描述>
     * @param privLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createPrivLog(PrivLogVO privLogVO)
    {
        activitiesRecDaoHubImpl.createPrivLog(privLogVO);
    }
    
    /**
     * 根据ID查询chargeLog对象
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<CardChargeLogVO> getChargeLogById(CardChargeLogVO cardChargeLogVO)
    {
        return activitiesRecDaoHubImpl.getChargeLogById(cardChargeLogVO);
    }
    
    /**
     * 记录打印发票日志
     * <功能详细描述>
     * @param record
     * @see [类、类#方法、类#成员]
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
