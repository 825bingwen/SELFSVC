package com.customize.hub.selfsvc.broadBandPay.model;

/**
 * 
 * �����Ʒ����
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Sep 13, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class WBandVO
{
    private String mainProdId;// �����Ʒ����
    private String ncode;// �����ƷNCODE������CRM����һ��
    private String prodName;// �����Ʒ����
    private String detailDesc;// �����Ʒ������Ϣ
    private String fee;// �����Ʒ������ã���λ����
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getMainProdId()
    {
        return mainProdId;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setMainProdId(String mainProdId)
    {
        this.mainProdId = mainProdId;
    }
    public String getNcode()
    {
        return ncode;
    }
    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    public String getProdName()
    {
        return prodName;
    }
    public void setProdName(String prodName)
    {
        this.prodName = prodName;
    }
    public String getDetailDesc()
    {
        return detailDesc;
    }
    public void setDetailDesc(String detailDesc)
    {
        this.detailDesc = detailDesc;
    }
    public String getFee()
    {
        return fee;
    }
    public void setFee(String fee)
    {
        this.fee = fee;
    }
    
    
}
