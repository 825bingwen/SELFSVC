package com.gmcc.boss.selfsvc.quickpublish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * �����Ʒ�����ύ����
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Jul 20, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class MultiProdCommitPO
{
    // �ֻ�����
    private String telnum;
    
    // ��ʶ�ýӿ��Ƿ�ֻ����У�飬�����а���
    private String ischeck;
    
    // ���ͺ��룬��������ҵ�񡣴�����
    private String doneenum;
    
    // ��ѱ�ʶ��Ԥ���ֶΣ��ݲ�ʹ�á�������
    private String iscalcfee;
    
    // ���ŷ��ͱ�ʶ
    private String issendsms;
    
    // ������
    private String opersource;
    
    // ��Ʒ�ύ����
    private List<ProdCommitPO> prodCommitList = new ArrayList<ProdCommitPO>();

    public String getTelnum()
    {
        return telnum;
    }

    public void setTelnum(String telnum)
    {
        this.telnum = telnum;
    }

    public String getIscheck()
    {
        return ischeck;
    }

    public void setIscheck(String ischeck)
    {
        this.ischeck = ischeck;
    }

    public String getDoneenum()
    {
        return doneenum;
    }

    public void setDoneenum(String doneenum)
    {
        this.doneenum = doneenum;
    }

    public String getIscalcfee()
    {
        return iscalcfee;
    }

    public void setIscalcfee(String iscalcfee)
    {
        this.iscalcfee = iscalcfee;
    }

    public String getIssendsms()
    {
        return issendsms;
    }

    public void setIssendsms(String issendsms)
    {
        this.issendsms = issendsms;
    }

    public String getOpersource()
    {
        return opersource;
    }

    public void setOpersource(String opersource)
    {
        this.opersource = opersource;
    }

    public List<ProdCommitPO> getProdCommitList()
    {
        return prodCommitList;
    }

    public void setProdCommitList(List<ProdCommitPO> prodCommitList)
    {
        this.prodCommitList = prodCommitList;
    }
    
    
}
