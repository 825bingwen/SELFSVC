/*
 * �ļ�����MenuInfoPO.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-11-30
 * �޸����ݣ�����
 */
package com.gmcc.boss.selfsvc.resdata.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * ��Դ����
 * 
 * @author g00140516
 * 
 */
public class ResDataPO extends BasePO
{
    private static final long serialVersionUID = 1L;
    
    private String restype; // ��Դ�������� productid,menutype,region
    
    private String reskey; // ��Դ����key
    
    private String resval;// ��Դ����ֵ
    
    public String getReskey()
    {
        return reskey;
    }
    
    public String getRestype()
    {
        return restype;
    }
    
    public String getResval()
    {
        return resval;
    }
    
    public void setReskey(String reskey)
    {
        this.reskey = reskey;
    }
    
    public void setRestype(String restype)
    {
        this.restype = restype;
    }
    
    public void setResval(String resval)
    {
        this.resval = resval;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[" + this.getClass() + "[");
        sb.append(", restype:" + this.getRestype());
        sb.append(", reskey:" + this.getReskey());
        sb.append(", resval:" + this.getResval());
        sb.append("]]");
        return super.toString();
    }
    
}
