package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * ��Ʒ�����Ӳ�ƷPO,�ֶ�Ϊ�ӿ�Э�鷵�ز��� <������ϸ����>
 * 
 * @author cKF76106
 * @version [�汾��, Jul 9, 2012]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class SubsChildProdPO
{
    // ��Ʒ������
    private String pkgid;
    
    // ��ֵ��Ʒ����
    private String prodid;
    
    // �Żݱ���
    private String privid;
    
    // ��Ʒ����
    private String prodname;
    
    // ѡ������ 0����ѡ 1����ѡ
    private String ismandatory;
    
    // �Ƿ��и������� 0���� 1����
    private String hasattr;
    
    // �ӿ�ҵ������ PCIntRelaNormal����ͨҵ�� PCIntRelaRadio��ҵ���л�
    private String inftype;

    public String getPkgid()
    {
        return pkgid;
    }

    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }

    public String getProdid()
    {
        return prodid;
    }

    public void setProdid(String prodid)
    {
        this.prodid = prodid;
    }

    public String getPrivid()
    {
        return privid;
    }

    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getProdname()
    {
        return prodname;
    }

    public void setProdname(String prodname)
    {
        this.prodname = prodname;
    }

    public String getIsmandatory()
    {
        return ismandatory;
    }

    public void setIsmandatory(String ismandatory)
    {
        this.ismandatory = ismandatory;
    }

    public String getHasattr()
    {
        return hasattr;
    }

    public void setHasattr(String hasattr)
    {
        this.hasattr = hasattr;
    }

    public String getInftype()
    {
        return inftype;
    }

    public void setInftype(String inftype)
    {
        this.inftype = inftype;
    }

}
