package com.customize.sd.selfsvc.packageService.model;

/**
 * 
 * �ػ�ҵ���PO
 * @author  hWX5316476
 * @version  [�汾��, Dec 22, 2014]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PrivServPackPO
{
    /**
     * NCODE
     */
    private String ncode;
    
    /**
     * �ػ�ҵ�������
     */
    private String privName;
    
    /**
     * ����
     */
    private String region;
    
    /**
     * �Żݼۣ�Ԫ��
     */
    private String privFee;
    
    /**
     * ��Ч���ͣ���0Ĭ�� 2���� 3������ 4ָ��ʱ����Ч��
     */
    private String effType;
    
    /**
     * ��Чʱ��
     */
    private String effDate;
    
    /**
     * ʧЧʱ��
     */
    private String endDate;
    
    /**
     * Ʒ��
     */
    private String brand;
    
    /**
     * �Ż�����
     */
    private String privDesc;
    
    public String getNcode()
    {
        return ncode;
    }
    
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
    public String getPrivName()
    {
        return privName;
    }
    
    public void setPrivName(String privName)
    {
        this.privName = privName;
    }
    
    public String getRegion()
    {
        return region;
    }
    
    public void setRegion(String region)
    {
        this.region = region;
    }
    
    public String getPrivFee()
    {
        return privFee;
    }
    
    public void setPrivFee(String privFee)
    {
        this.privFee = privFee;
    }
    
    public String getEffDate()
    {
        return effDate;
    }
    
    public void setEffDate(String effDate)
    {
        this.effDate = effDate;
    }
    
    public String getEndDate()
    {
        return endDate;
    }
    
    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public String getPrivDesc()
    {
        return privDesc;
    }
    
    public void setPrivDesc(String privDesc)
    {
        this.privDesc = privDesc;
    }
    
    public String getEffType()
    {
        return effType;
    }
    
    public void setEffType(String effType)
    {
        this.effType = effType;
    }
    
}
