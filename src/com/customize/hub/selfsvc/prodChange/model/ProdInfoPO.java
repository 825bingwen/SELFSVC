/*
 * �� �� ��:  ProdInfoPO.java
 * ��    Ȩ:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * ��    ��:  <����>
 * �� �� ��: yKF70747
 * �޸�ʱ��:  Apr 17, 2012
 * ���ٵ���:  <���ٵ���>
 * �޸ĵ���:  <�޸ĵ���>
 * �޸�����:  <�޸�����>
 */
package com.customize.hub.selfsvc.prodChange.model;

/**
 * ��ȷ�ϵ��ײ���ϢPO
 * 
 * @author  yKF70747
 * @version  [�汾��, Apr 17, 2012]
 * @see 
 * @since 
 */
public class ProdInfoPO
{
    

    /**
     * ����
     */
    private String type;
    
    /**
     * ��Ʒ����
     */
    private String prodid;
    
    /**
     * ��Ʒ����
     */
    private String prodname;
    
    /**
     * ��Ʒ��ʼʱ��
     */
    private String begindate;
    
    /**
     * ��Ʒ����ʱ��
     */
    private String enddate;
    
    /**
     * ��Ʒ������
     */
    private String pkgname;
    
    /**
     * �Żݱ���
     */
    private String privid;
    
    /**
     * �Ż�����
     */
    private String privname;
    
    /**
     * �Żݿ�ʼʱ��
     */
    private String privbegindate;
    
    /**
     * �Żݽ���ʱ��
     */
    private String privenddate;
    
    /**
     * ȡ��ԭ��
     */
    private String reason;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getProdid()
    {
        return prodid;
    }

    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    public String getProdname()
    {
        return prodname;
    }

    public void setProdname(String prodname)
    {
        this.prodname = prodname;
    }

    public String getBegindate()
    {
        return begindate;
    }

    public void setBegindate(String begindate)
    {
        this.begindate = begindate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivid()
    {
        return privid;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getPrivname()
    {
        return privname;
    }

    public void setPrivname(String privname)
    {
        this.privname = privname;
    }

    public String getPrivbegindate()
    {
        return privbegindate;
    }

    public void setPrivbegindate(String privbegindate)
    {
        this.privbegindate = privbegindate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public String getPrivenddate()
    {
        return privenddate;
    }
    @edu.umd.cs.findbugs.annotations.SuppressWarnings({"NM_CONFUSING","EC_UNRELATED_TYPES"})
    public void setPrivenddate(String privenddate)
    {
        this.privenddate = privenddate;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }
    
   
}
