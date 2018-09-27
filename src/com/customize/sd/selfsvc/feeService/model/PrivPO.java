package com.customize.sd.selfsvc.feeService.model;

import com.gmcc.boss.selfsvc.util.CurrencyUtil;

/**
 * 
 * <һ�仰���ܼ���>
 * <������ϸ����>
 * 
 * @author  yKF28472
 * @version  [�汾��, Feb 16, 2012]
 * @see  [�����/����]
 * @since  [��Ʒ/ģ��汾]
 */
public class PrivPO
{
    private String rateid;// �ʷ�����ID
    private String freetype;// ͨѶ���ͣ�ALL,GSM,SMS,GPRS,WLAN,MMS,ISMG,MMR
    private String total;// ���͵�����
    private String used;// �Ѿ�ʹ�õ���
    private String attrtype;// ��λ 1 ���� 2 ʱ��(��) 3 ����(Ԫ) 4 ����(KB) 5 ����(M) 6 ʱ��(Сʱ)
    private String attrtypename;
    private String feename;// �ʷ���������
    private String attrtypeunit;// ��λ����
    private String surplusamount;// ʣ����
    private String privset;// �ײ���Ϣ��ѯ�е�����
    public String getRateid()
    {
        return rateid;
    }
    public void setRateid(String rateid)
    {
        this.rateid = rateid;
    }
    public String getFreetype()
    {
        return freetype;
    }
    public void setFreetype(String freetype)
    {
        this.freetype = freetype;
    }
    public String getTotal()
    {
        return total;
    }
    public void setTotal(String total)
    {
        this.total = total;
    }
    public String getUsed()
    {
        return used;
    }
    public void setUsed(String used)
    {
        this.used = used;
    }
    public String getAttrtype()
    {
        return attrtype;
    }
    public void setAttrtype(String attrtype)
    {
        this.attrtype = attrtype;
    }
    public String getFeename()
    {
        return feename;
    }
    public void setFeename(String feename)
    {
        this.feename = feename;
    }
    public String getAttrtypename()
    {
        // ��λ 1 ���� 2 ʱ��(��) 3 ����(Ԫ) 4 ����(KB) 5 ����(M) 6 ʱ��(Сʱ)
        if ("1".equals(attrtype))
        {
            attrtypename = "����";
        }
        else if ("2".equals(attrtype))
        {
            attrtypename = "ʱ��(��)";
        }
        else if ("3".equals(attrtype))
        {
            attrtypename = "����(Ԫ)";
        }
        else if ("4".equals(attrtype))
        {
            attrtypename = "����(KB)";
        }
        else if ("5".equals(attrtype))
        {
            attrtypename = "����(M)";
        }
        else if ("6".equals(attrtype))
        {
            attrtypename = "ʱ��(Сʱ)";
        }
        else
        {
            attrtypename = attrtype;
        }
        return attrtypename;
    }
    public void setAttrtypename(String attrtypename)
    {
        this.attrtypename = attrtypename;
    }
	public String getAttrtypeunit() 
	{
        return attrtypeunit;
	}
	public void setAttrtypeunit(String attrtypeunit) 
	{
		this.attrtypeunit = attrtypeunit;
	}
	public String getSurplusamount() 
	{
		return surplusamount;
	}
	public void setSurplusamount(String surplusamount)
	{
		this.surplusamount = surplusamount;
	}
	public String getPrivset() {
		return privset;
	}
	public void setPrivset(String privset) {
		this.privset = privset;
	}
	
}
