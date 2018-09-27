package com.customize.sd.selfsvc.serviceinfo.service;

import java.util.List;

import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;


/**
 * <绑定银行卡信息Service>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface BindBankCardService
{
    /**
     * 查询流水号
     * 
     * @return
     */
    public String qryOrderId();
    
    /**
     * <根据银行卡类型查找银行展示信息>
     * <功能详细描述>
     * @param bankCardType 0：借记卡，1：信用卡
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-7 11:32:07 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    public List<BankCardInfoPO> getBankInfoList(String bankCardType);
}
