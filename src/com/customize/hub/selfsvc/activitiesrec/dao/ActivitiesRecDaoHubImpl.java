package com.customize.hub.selfsvc.activitiesrec.dao;

import java.util.List;

import com.customize.hub.selfsvc.activitiesrec.model.ActivityVO;
import com.customize.hub.selfsvc.invoice.model.InvoicePrintPO;
import com.customize.hub.selfsvc.privAccept.model.PrivLogVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 促销活动受理
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Jan 31, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ActivitiesRecDaoHubImpl extends BaseDaoImpl
{
    /**
     * 查询促销活动组(并过滤用户已存在档次)
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getActivitieGroups(ActivityVO activityVO)
    {
       return sqlMapClientTemplate.queryForList("activities.getActivitieGroups", activityVO);
    }
    
    /**
     * 根据组查询档次列表
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getDangciByGroupId(ActivityVO activityVO)
    {
       return sqlMapClientTemplate.queryForList("activities.getDangciByGroupId", activityVO);
    }
    
    /**
     * 根据活动编码和档次编码查询档次
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<ActivityVO> getDangciById(ActivityVO activityVO)
    {
       return sqlMapClientTemplate.queryForList("activities.getDangciById", activityVO);
    }
    
    /**
     * 获取缴费日志OID
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * 缴费记录日志sh_charge_log(投币之后、业务受理之前执行记录)
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 更新缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("chargeHub.updateChargeLog", cardChargeLogVO);
    }
    
    /**
     * 更新缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("chargeHub.updateCardChargeLog", cardChargeLogVO);
    }
    
    /**
     * 增加受理记录
     * <功能详细描述>
     * @param privLogVO
     * @see [类、类#方法、类#成员]
     */
    public void createPrivLog(PrivLogVO privLogVO)
    {
        sqlMapClientTemplate.insert("activities.insertPrivLog",privLogVO);
    }
    
    /**
     * 根据ID查询chargeLog对象
     * <功能详细描述>
     * @param activityVO
     * @see [类、类#方法、类#成员]
     */
    public List<CardChargeLogVO> getChargeLogById(CardChargeLogVO cardChargeLogVO)
    {
       return sqlMapClientTemplate.queryForList("activities.getChargeLogById", cardChargeLogVO);
    }
    
    /**
     * 记录打印发票日志
     * <功能详细描述>
     * @param record
     * @see [类、类#方法、类#成员]
     */
    public void insertInvoiceLog(InvoicePrintPO record)
    {
        sqlMapClientTemplate.insert("invoiceHub.insertInvoiceLog", record);
    }
    
}
