/*
 * 文 件 名:  ConnUtil.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <与浪潮建立连接>
 * 修 改 人:  zWX176560
 * 修改时间:  Sep 14, 2013
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <新增>
 */
package com.customize.sd.selfsvc.serviceinfo.dao;
import java.util.List;

import com.customize.sd.selfsvc.serviceinfo.model.BankCardInfoPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * <银行卡无密绑定>
 * <功能详细描述>
 * 
 * @author  zWX176560
 * @version  2013/09/14 R003C13L08n01 OR_SD_201309_66
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BindBankCardDaoImpl extends BaseDaoImpl
{
    /**
     * 查询流水号
     * 
     * @return
     */
    public String qryOrderId()
    {
        return (String) this.sqlMapClientTemplate.queryForObject("bindBankCard.qryOrderId");
    }
    
    /**
     * <查找银行展示信息>
     * <功能详细描述>
     * @param bankCardType 0：借记卡，1：信用卡
     * @return
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-10-7 11:38:30 OR_SD_201408_1190_山东_关于自助终端上易充值业务优化的需求
     */
    @SuppressWarnings("unchecked")
	public List<BankCardInfoPO> getBankInfoList(String bankCardType)
    {
        return sqlMapClientTemplate.queryForList("bindBankCard.getBankInfoByType", bankCardType);
    }
}
