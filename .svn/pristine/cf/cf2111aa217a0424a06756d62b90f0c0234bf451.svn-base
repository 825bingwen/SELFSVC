package com.customize.hub.selfsvc.broadBandPay.dao;

import java.util.List;

import com.customize.hub.selfsvc.broadBandPay.model.WBandVO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 
 * 宽带交费
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Sep 13, 2012]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BroadBandPayDaoImpl extends BaseDaoImpl
{
    /**
     * 缴费记录日志
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("broadBandPay.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.update("broadBandPay.updateCardChargeLog", cardChargeLogVO);
    }
    
    /**
     * 更新充值缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.update("broadBandPay.updateChargeLog", cardChargeLogVO);
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
     * 插入重复缴费错误日志
     * <功能详细描述>
     * @param cashFeeErrorInfo
     * @see [类、类#方法、类#成员]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        sqlMapClientTemplate.insert("broadBandPay.insertFeeErrorLog", cashFeeErrorInfo);
    }
    
    /**
     * 根据主体产品查询宽带产品列表
     * <功能详细描述>
     * @param mainProdId
     * @see [类、类#方法、类#成员]
     */
    public List<WBandVO> qryWBankList(String mainProdId)
    {
        return sqlMapClientTemplate.queryForList("broadBandPay.qryWBankList",mainProdId);
    }
}
