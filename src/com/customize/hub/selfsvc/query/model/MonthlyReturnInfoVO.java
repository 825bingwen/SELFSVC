/*
 * 文 件 名:  MonthlyReturnInfoVO.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HW
 * 修改时间:  2011-9-14
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.query.model;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author HW
 * @version [版本号, 2011-9-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonthlyReturnInfoVO
{
    
    // 返还账期
    private String billCycle;
    
    // 返还预存金额
    private String storeFlowamt;
    
    // 预存余额
    private String storeBalance;
    
    // 返还赠送金额
    private String giftFlowamt;
    
    // 赠送余额
    private String giftBalance;
    
    /**
     * @return 返回 billCycle
     */
    public String getBillCycle()
    {
        return billCycle;
    }
    
    /**
     * @param 对billCycle进行赋值
     */
    public void setBillCycle(String billCycle)
    {
        this.billCycle = billCycle;
    }
    
    /**
     * @return 返回 storeFlowamt
     */
    public String getStoreFlowamt()
    {
        return storeFlowamt;
    }
    
    /**
     * @param 对storeFlowamt进行赋值
     */
    public void setStoreFlowamt(String storeFlowamt)
    {
        this.storeFlowamt = storeFlowamt;
    }
    
    /**
     * @return 返回 storeBalance
     */
    public String getStoreBalance()
    {
        return storeBalance;
    }
    
    /**
     * @param 对storeBalance进行赋值
     */
    public void setStoreBalance(String storeBalance)
    {
        this.storeBalance = storeBalance;
    }
    
    /**
     * @return 返回 giftFlowamt
     */
    public String getGiftFlowamt()
    {
        return giftFlowamt;
    }
    
    /**
     * @param 对giftFlowamt进行赋值
     */
    public void setGiftFlowamt(String giftFlowamt)
    {
        this.giftFlowamt = giftFlowamt;
    }
    
    /**
     * @return 返回 giftBalance
     */
    public String getGiftBalance()
    {
        return giftBalance;
    }
    
    /**
     * @param 对giftBalance进行赋值
     */
    public void setGiftBalance(String giftBalance)
    {
        this.giftBalance = giftBalance;
    }
    
}
