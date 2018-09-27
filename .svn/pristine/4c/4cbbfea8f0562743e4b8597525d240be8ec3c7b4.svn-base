package com.customize.hub.selfsvc.common.dao;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

public class FeeDaoImpl extends BaseDaoImpl 
{
	/**
     * 银联卡缴费记录日志
     * 
     * @param cardChargeLogVO 银行卡信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("charge.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 交费成功后，更新交费日志信息
     * 〈功能详细描述〉
     * @param [cardChargeLogVO] [交费类型]
     * @see [类、类#方法、类#成员]
     * @depreced
     * @remark add by wWX217192 2015-05-25 OR_HUB_201503_1068_关于配合集团《关于下发电子化有价卡业务省级系统改造方案的通知》的改造需求自助终端改造
     */
    public void updateChargeLogForResult(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.insert("chargeHub.updateChargeLogForCash", cardChargeLogVO);
    }
    
    /**
     * 更新充值缴费记录
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.insert("chargeHub.updateChargeLog", cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.insert("chargeHub.updateCardChargeLog", cardChargeLogVO);
    }
}
