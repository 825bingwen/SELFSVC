/*
 * �ļ�����DetailedRecordsTimesPO.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-16
 * �޸����ݣ��������굥��ӡ��¼
 */
package com.gmcc.boss.selfsvc.feeservice.model;

import com.gmcc.boss.selfsvc.common.BasePO;

/**
 * �굥��ӡ��¼
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-16
 * @see
 * @since
 */
public class DetailedRecordsTimesPO extends BasePO
{
    private static final long serialVersionUID = -9066580054390068166L;
    
    /**
     * �������
     */
    private String servNumber = "";
    
    /**
     * �·�
     */
    private String month = "";
    
    /**
     * �Ѵ�ӡ����
     */
    private int times = 0;
    
    /**
     * ��ӡ����
     */
    private String listtype = "";
    
    // add begin g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
    /**
     * ��ӡ����
     */
    private String printDate = "";
    // add end g00140516 2013/02/01 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
    
    public String getServNumber()
    {
        return servNumber;
    }
    
    public void setServNumber(String servNumber)
    {
        this.servNumber = servNumber;
    }
    
    public String getMonth()
    {
        return month;
    }
    
    public void setMonth(String month)
    {
        this.month = month;
    }
    
    public int getTimes()
    {
        return times;
    }
    
    public void setTimes(int times)
    {
        this.times = times;
    }

    public String getListtype()
    {
        return listtype;
    }

    public void setListtype(String listtype)
    {
        this.listtype = listtype;
    }

	public String getPrintDate()
	{
		return printDate;
	}

	public void setPrintDate(String printDate)
	{
		this.printDate = printDate;
	}


    
    
}
