package com.customize.nx.selfsvc.prodChange.model;

/**
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  qWX279398
 * @version  [�汾��, 2015-7-20]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 * @remark modify begin qWX279398 2015��7��20��16:42:21 �Ӻ���ͬ������copy��findbugs�޸�
 */
public class ProdLogVO
{
    /**
     * ��Ʒ����SN
     */
    private String prodSn;
    
    /**
     * SH_PRIV_LOG��ˮ��
     */
    private String prodLogOID;
    
    /**
     *�û��������
     */
    private String servnumber;
    
    /**
     * ����
     */
    private String region;
    
    /**
     * �û�ת����Ʒ
     */
    private String toProdId;
    
    /**
     * ��ǰ�û���Ʒ
     */
    private String fromProdId;
    
    /**
     * ��Ʒת������
     */
    private String prodType;
    
    /**
     * �û���������
     */
    private String opType;
    
    /**
     * ҵ������ʱ��
     */
    private String recDate;
    
    /**
     * ��Ʒת��ID
     */
    private String changeId;
    
    /**
     * ҵ������״̬
     */
    private String recStauts;
    
    //add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193
    /**
     * ����״̬����
     */
    private String recstatusdesc;
    
    /**
     * ��ϸ�ñ�����
     */ 
    
    private String chgdesp;
    //add begin yKF70747 2012/04/12 R003C12L04n01 OR_HUB_201202_1193

    public String getProdSn()
    {
        return prodSn;
    }

    public void setProdSn(String prodSn)
    {
        this.prodSn = prodSn;
    }

    public String getProdLogOID()
    {
        return prodLogOID;
    }

    public void setProdLogOID(String prodLogOID)
    {
        this.prodLogOID = prodLogOID;
    }

    public String getServnumber()
    {
        return servnumber;
    }

    public void setServnumber(String servnumber)
    {
        this.servnumber = servnumber;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }

    public String getToProdId()
    {
        return toProdId;
    }

    public void setToProdId(String toProdId)
    {
        this.toProdId = toProdId;
    }

    public String getFromProdId()
    {
        return fromProdId;
    }

    public void setFromProdId(String fromProdId)
    {
        this.fromProdId = fromProdId;
    }

    public String getProdType()
    {
        return prodType;
    }

    public void setProdType(String prodType)
    {
        this.prodType = prodType;
    }

    public String getOpType()
    {
        return opType;
    }

    public void setOpType(String opType)
    {
        this.opType = opType;
    }

    public String getRecDate()
    {
        return recDate;
    }

    public void setRecDate(String recDate)
    {
        this.recDate = recDate;
    }

    public String getChangeId()
    {
        return changeId;
    }

    public void setChangeId(String changeId)
    {
        this.changeId = changeId;
    }

    public String getRecStauts()
    {
        return recStauts;
    }

    public void setRecStauts(String recStauts)
    {
        this.recStauts = recStauts;
    }

    public String getRecstatusdesc()
    {
        return recstatusdesc;
    }

    public void setRecstatusdesc(String recstatusdesc)
    {
        this.recstatusdesc = recstatusdesc;
    }

    public String getChgdesp()
    {
        return chgdesp;
    }

    public void setChgdesp(String chgdesp)
    {
        this.chgdesp = chgdesp;
    }
    
}
