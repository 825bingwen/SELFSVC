package com.customize.sd.selfsvc.po;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * 
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  lWX431760
 * @version  [�汾��, 2017-7-19]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
@edu.umd.cs.findbugs.annotations.SuppressWarnings( {"NM_CONFUSING", "EC_UNRELATED_TYPES"})
public class NcodePO extends BasePO
{
    /**
     * ע������
     */
    private static final long serialVersionUID = 8543408428674495922L;

    private String ncode;
    
    private String objType;
    
    private String optype;
    
    private String pkgId;
    
    private String prodId;
    
    private String privId;
    
    private String region;

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getObjType()
    {
        return objType;
    }

    public void setObjType(String objType)
    {
        this.objType = objType;
    }    

    public String getOptype()
    {
        return optype;
    }

    public void setOptype(String optype)
    {
        this.optype = optype;
    }

    public String getPkgId()
    {
        return pkgId;
    }

    public void setPkgId(String pkgId)
    {
        this.pkgId = pkgId;
    }

    public String getProdId()
    {
        return prodId;
    }

    public void setProdId(String prodId)
    {
        this.prodId = prodId;
    }

    public String getPrivId()
    {
        return privId;
    }

    public void setPrivId(String privId)
    {
        this.privId = privId;
    }

    public String getRegion()
    {
        return region;
    }

    public void setRegion(String region)
    {
        this.region = region;
    }
    
    
}
