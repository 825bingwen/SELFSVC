
package com.gmcc.boss.selfsvc.charge.dao;

import java.util.List;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * <充值日志记录>
 * <功能详细描述>
 * 
 * @author  sWX219697
 * @version  [版本号, Mar 19, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChargeDaoImpl extends BaseDaoImpl
{
    
    /**
     * 更新日志记录
     * 
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeStatus(CardChargeLogVO cardChargeLogVO)
    {
        this.sqlMapClientTemplate.update("charge.updateChargeStatus", cardChargeLogVO);
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String) this.sqlMapClientTemplate.queryForObject("charge.getChargeLogOID");
    }
    
    /**
     * <添加交费日志>
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO chargeLog)
    {
        this.sqlMapClientTemplate.update("charge.insertNonlocalChargeLog", chargeLog);
    }
    
    /**
     * <银联扣款后更新交费状态>
     * <功能详细描述>
     * @param chargeLog
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO chargeLog)
    {
    	this.sqlMapClientTemplate.update("charge.updateCardChargeLogAfterPay", chargeLog);
    }
    
    /**
     * <生成交易流水序列>
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public String getNonlocalChargeID()
    {
    	return (String)this.sqlMapClientTemplate.queryForObject("charge.getNonlocalChargeID");
    }
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * @param log
     * @see 
     * @remark create by hWX5316476 2015-3-27 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    public void insertCashDetailInfo(CashDetailLogPO log)
    {
        sqlMapClientTemplate.insert("charge.insertCashDetailLog", log);
    }
    
    /**
     * 查询手机号归属地市
     * <功能详细描述>
     * @param chargeLog
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by hWX5316476 2015-3-31 OR_NX_201501_1030_宁夏_关于跨区服务业务支撑系统改造的通知
     */
    @SuppressWarnings("unchecked")
    public List<String> qryServRegion(CardChargeLogVO chargeLog)
    {
        return (List<String>) sqlMapClientTemplate.queryForList("charge.qryServRegion", chargeLog);
    }
}
