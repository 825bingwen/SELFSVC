package com.gmcc.boss.selfsvc.quickpublish.model;

/**
 * 
 * ��Ʒ�����ύ����
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Jul 20, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class ProdCommitPO
{
    // �Բ�Ʒ���µ��Ӳ�Ʒ����ģ���µ��Ӳ�Ʒ������Ʒ���룻������NCODE
    private String ncode;
    
    // ��Ч��ʽ
    // 2������
    // 3������
    // 4������
    // 5��ָ����Чʱ��
    // Ĭ����������Ч������Χ���ݲ�Ʒ��ʵ���������
    private String effecttype;
    
    // ��������
    // PCOpRec:��ͨ
    // PCOpMod:�޸�
    // PCOpDel:�ر�
    // PCOpPau:��ͣ
    // PCOpRes:�ָ�
    private String opertype;
    
    // �������ԣ���ʽ��
    // attrid1=attrvalue1=optype1#attrid2=attrvalue2=optype2��
    // ���������Ա���=��������ֵ=�������ԵĲ������ͣ����в�������Ŀǰû�ã�ֱ��ʹ��
    // attrid1=attrvalue1= #attrid2=attrvalue2=#������
    private String attrparam;
    
    // ԭ��ֵ��Ʒ���룬Ŀǰû�á�ֱ�Ӵ�����
    private String oldprodid;
    
    // ������Դ
    private String serviceres;
    
    // �ӿڶ�Ӧ���ͣ��Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ�á�
    // PCIntRelaNormal����ͨҵ��
    // PCIntRelaRadio��ҵ���л�
    private String inftype;
    
    // �Ƿ�ģ�崦�� 1���� ���������� Ŀǰû�ã�����������
    private String templetflag;
    
    // ������Чʱ��
    private String startdate;
    
    // ����ʧЧʱ��
    private String enddate;
    
    // ��Ʒ�����롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
    private String pkgid;
    
    // �������ͣ�������Ʒ���Żݡ�����SP�������Ʒ��ģ�塢ncode
    private String objtype;
    
    // �Żݱ��롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
    private String privid;
    
    // ģ����롣�Բ�Ʒ�����Ӳ�Ʒ����ģ�����Ӳ�Ʒ�����ʱ��ʹ��
    private String templetid;

    public String getNcode()
    {
        return ncode;
    }

    public void setNcode(String ncode)
    {
        this.ncode = ncode;
    }

    public String getEffecttype()
    {
        return effecttype;
    }

    public void setEffecttype(String effecttype)
    {
        this.effecttype = effecttype;
    }

    public String getOpertype()
    {
        return opertype;
    }

    public void setOpertype(String opertype)
    {
        this.opertype = opertype;
    }

    public String getAttrparam()
    {
        return attrparam;
    }

    public void setAttrparam(String attrparam)
    {
        this.attrparam = attrparam;
    }

    public String getOldprodid()
    {
        return oldprodid;
    }

    public void setOldprodid(String oldprodid)
    {
        this.oldprodid = oldprodid;
    }

    public String getServiceres()
    {
        return serviceres;
    }

    public void setServiceres(String serviceres)
    {
        this.serviceres = serviceres;
    }

    public String getInftype()
    {
        return inftype;
    }

    public void setInftype(String inftype)
    {
        this.inftype = inftype;
    }

    public String getTempletflag()
    {
        return templetflag;
    }

    public void setTempletflag(String templetflag)
    {
        this.templetflag = templetflag;
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

    public String getPkgid()
    {
        return pkgid;
    }

    public void setPkgid(String pkgid)
    {
        this.pkgid = pkgid;
    }

    public String getObjtype()
    {
        return objtype;
    }

    public void setObjtype(String objtype)
    {
        this.objtype = objtype;
    }

    public String getPrivid()
    {
        return privid;
    }

    public void setPrivid(String privid)
    {
        this.privid = privid;
    }

    public String getTempletid()
    {
        return templetid;
    }

    public void setTempletid(String templetid)
    {
        this.templetid = templetid;
    }
    
    
}
