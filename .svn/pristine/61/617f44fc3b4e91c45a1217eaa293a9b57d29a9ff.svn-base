package com.customize.hub.selfsvc.broadBandPay.service;

import java.util.List;

import com.customize.hub.selfsvc.broadBandPay.dao.BroadBandPayDaoImpl;
import com.customize.hub.selfsvc.broadBandPay.model.WBandVO;
import com.customize.hub.selfsvc.charge.model.CashFeeErrorInfoVO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;

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
public class BroadBandPayServiceImpl implements BroadBandPayService
{
    
    private BroadBandPayDaoImpl broadBandPayDaoImpl;
    
    /**
     * 记录缴费日志
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.addChargeLog(cardChargeLogVO);
    }

    /**
     * 银联扣款成功之后，更新日志
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * 获取缴费日志OID
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public String getChargeLogOID()
    {
        return broadBandPayDaoImpl.getChargeLogOID();
    }
    
    /**
     * 更新充值缴费记录
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        broadBandPayDaoImpl.updateChargeLog(cardChargeLogVO);
    }

    /**
     * 插入重复缴费错误日志
     * <功能详细描述>
     * @param cardChargeLogVO
     * @see [类、类#方法、类#成员]
     */
    public void insertFeeErrorLog(CashFeeErrorInfoVO cashFeeErrorInfo)
    {
        broadBandPayDaoImpl.insertFeeErrorLog(cashFeeErrorInfo);
        
    }
    
    /**
     * 查询宽带产品列表
     * <功能详细描述>
     * @param mainProdId
     * @see [类、类#方法、类#成员]
     */
    public List<WBandVO> qryWBankList(String mainProdId)
    {
        return (List<WBandVO>)broadBandPayDaoImpl.qryWBankList(mainProdId);
    }

    public BroadBandPayDaoImpl getBroadBandPayDaoImpl()
    {
        return broadBandPayDaoImpl;
    }

    public void setBroadBandPayDaoImpl(BroadBandPayDaoImpl broadBandPayDaoImpl)
    {
        this.broadBandPayDaoImpl = broadBandPayDaoImpl;
    }
    
    
}
