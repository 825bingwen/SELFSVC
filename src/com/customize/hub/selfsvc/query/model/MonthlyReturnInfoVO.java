/*
 * �� �� ��:  MonthlyReturnInfoVO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��:  HW
 * �޸�ʱ��:  2011-9-14
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.query.model;

/**
 * <һ�仰���ܼ���> <������ϸ����>
 * 
 * @author HW
 * @version [�汾��, 2011-9-14]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class MonthlyReturnInfoVO
{
    
    // ��������
    private String billCycle;
    
    // ����Ԥ����
    private String storeFlowamt;
    
    // Ԥ�����
    private String storeBalance;
    
    // �������ͽ��
    private String giftFlowamt;
    
    // �������
    private String giftBalance;
    
    /**
     * @return ���� billCycle
     */
    public String getBillCycle()
    {
        return billCycle;
    }
    
    /**
     * @param ��billCycle���и�ֵ
     */
    public void setBillCycle(String billCycle)
    {
        this.billCycle = billCycle;
    }
    
    /**
     * @return ���� storeFlowamt
     */
    public String getStoreFlowamt()
    {
        return storeFlowamt;
    }
    
    /**
     * @param ��storeFlowamt���и�ֵ
     */
    public void setStoreFlowamt(String storeFlowamt)
    {
        this.storeFlowamt = storeFlowamt;
    }
    
    /**
     * @return ���� storeBalance
     */
    public String getStoreBalance()
    {
        return storeBalance;
    }
    
    /**
     * @param ��storeBalance���и�ֵ
     */
    public void setStoreBalance(String storeBalance)
    {
        this.storeBalance = storeBalance;
    }
    
    /**
     * @return ���� giftFlowamt
     */
    public String getGiftFlowamt()
    {
        return giftFlowamt;
    }
    
    /**
     * @param ��giftFlowamt���и�ֵ
     */
    public void setGiftFlowamt(String giftFlowamt)
    {
        this.giftFlowamt = giftFlowamt;
    }
    
    /**
     * @return ���� giftBalance
     */
    public String getGiftBalance()
    {
        return giftBalance;
    }
    
    /**
     * @param ��giftBalance���и�ֵ
     */
    public void setGiftBalance(String giftBalance)
    {
        this.giftBalance = giftBalance;
    }
    
}
