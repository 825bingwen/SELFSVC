package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * �ӿڷ��ص��Ѷ����Ĳ�Ʒ����
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Jul 31, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class SpeakProdPO
{
    // �Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���룻�������ء���
    private String prodid;
    
    // ��Ʒ����
    private String prodname;
    
    // �������Դ�
    private String attrparam;
    
    // ������Դ��
    private String serviceres;
    
    // ����ʱ�䣬��ʽ��yyyymmddhh24miss
    private String recdate;
    
    // ��ʼʱ�䣬��ʽ��yyyymmddhh24miss
    private String  startdate;
    
    // ����ʱ�䣬��ʽ��yyyymmddhh24miss
    private String enddate;
    
    // ״̬ 0��ԤԼ 1������ 2����ͣ 3�����˶�
    private String status;
    
    // ������ˮ
    private String formnum;
    
    // �ײʹ���
    private String pkgtype;
    
    // ��Ʒ����
    private String proddesc;
    
    // ���ͷ�
    private String doneenum;
    
    // ���͹�ϵ����
    private String doneerelaid;
    
    // �ײʹ�������
    private String pkgname;
    
    // ȡ��ʱ�䣬��ʽ��yyyymmddhh24miss
    private String canceldate;
    
    // �Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ز�Ʒ���������ģ����룻�������ء���
    private String pkgid;
    
    // ���ֶ����ڱ�ʶ�ǲ�Ʒ������NCODE����Ϊ0��ʶΪNCODE��ôCOL_0�����ֵ��ΪNCODE����Ϊ1��COL_1��������ݾ��ǲ�Ʒ��
    private String prodtype;
    
    // �Żݱ���
    private String privid;
    
    // �Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ�����ء�������������ncode
    private String ncode;

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

    public String getAttrparam()
    {
        return attrparam;
    }

    public void setAttrparam(String attrparam)
    {
        this.attrparam = attrparam;
    }

    public String getServiceres()
    {
        return serviceres;
    }

    public void setServiceres(String serviceres)
    {
        this.serviceres = serviceres;
    }

    public String getRecdate()
    {
        return recdate;
    }

    public void setRecdate(String recdate)
    {
        this.recdate = recdate;
    }

    public String getStartdate()
    {
        return startdate;
    }

    public void setStartdate(String startdate)
    {
        this.startdate = startdate;
    }

    public String getEnddate()
    {
        return enddate;
    }

    public void setEnddate(String enddate)
    {
        this.enddate = enddate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getFormnum()
    {
        return formnum;
    }

    public void setFormnum(String formnum)
    {
        this.formnum = formnum;
    }

    public String getPkgtype()
    {
        return pkgtype;
    }

    public void setPkgtype(String pkgtype)
    {
        this.pkgtype = pkgtype;
    }

    public String getProddesc()
    {
        return proddesc;
    }

    public void setProddesc(String proddesc)
    {
        this.proddesc = proddesc;
    }

    public String getDoneenum()
    {
        return doneenum;
    }

    public void setDoneenum(String doneenum)
    {
        this.doneenum = doneenum;
    }

    public String getDoneerelaid()
    {
        return doneerelaid;
    }

    public void setDoneerelaid(String doneerelaid)
    {
        this.doneerelaid = doneerelaid;
    }

    public String getPkgname()
    {
        return pkgname;
    }

    public void setPkgname(String pkgname)
    {
        this.pkgname = pkgname;
    }

    public String getCanceldate()
    {
        return canceldate;
    }

    public void setCanceldate(String canceldate)
    {
        this.canceldate = canceldate;
    }

    public String getPkgid()
    {
        return pkgid;
    }

    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }

    public String getProdtype()
    {
        return prodtype;
    }

    public void setProdtype(String prodtype)
    {
        this.prodtype = prodtype;
    }

    public String getPrivid()
    {
        return privid;
    }

    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }
    
}
