/*
 * �ļ�����DetailedRecordsServiceImpl.java
 * ��Ȩ��CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * ������
 * �޸��ˣ�g00140516
 * �޸�ʱ�䣺2010-12-16
 * �޸����ݣ����������굥��ѯ
 */
package com.gmcc.boss.selfsvc.feeservice.service;

import java.util.List;

import com.gmcc.boss.selfsvc.feeservice.dao.DetailedRecordsDaoImpl;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;

/**
 * ���굥��ѯ����ѯ�굥��ӡ��������ӡ�����󣬸����굥��ӡ��¼
 * 
 * 
 * @author g00140516
 * @version 1.0��2010-12-16
 * @see
 * @since
 */
public class DetailedRecordsServiceImpl implements DetailedRecordsService
{
    private DetailedRecordsDaoImpl detailedRecordsDaoImpl = null;
    
    public DetailedRecordsDaoImpl getDetailedRecordsDaoImpl()
    {
        return detailedRecordsDaoImpl;
    }
    
    public void setDetailedRecordsDaoImpl(DetailedRecordsDaoImpl detailedRecordsDaoImpl)
    {
        this.detailedRecordsDaoImpl = detailedRecordsDaoImpl;
    }
    
    /**
     * ��ѯ�굥��ӡ����
     * 
     * @param servNumber���������
     * @param month���·�
     * @return
     * @see
     */
    public DetailedRecordsTimesPO getPrintTimes(String servNumber, String month, String listtype)
    {
        return detailedRecordsDaoImpl.getPrintTimes(servNumber, month, listtype);
    }
    
    /**
     * �����굥��ӡ��¼
     * 
     * @param servNumber���������
     * @param month���·�
     * @param times����ӡ����
     * @see 
     */
    public void addPrintTimes(String servNumber, String month, int times, String listtype)
    {
    	detailedRecordsDaoImpl.addPrintTimes(servNumber, month, times, listtype);
    }
    
    /**
     * �����굥��ӡ��¼
     * 
     * @param servNumber���������
     * @param month���·�
     * @param times����ӡ����
     * @see
     */
    public void updatePrintTimes(String servNumber, String month, int times, String listtype)
    {
        detailedRecordsDaoImpl.updatePrintTimes(servNumber, month, times, listtype);
    }
    
    /**
     * ��ѯ�굥��ӡ��¼
     * @param servNumber �ֻ�����
     * @param month �·�
     * @param listtype �굥����
     * @return �����������굥��ӡ��¼������ӡʱ�併������
	 * @remark create g00140516 2013/02/03 R003C13L01n01 ÿ���12���£�ÿ���굥��ֻ�ܴ�ӡһ��
     */
    public List<DetailedRecordsTimesPO> getPrintRecords(String servNumber, String month,String listtype)
    {
    	return detailedRecordsDaoImpl.getPrintRecords(servNumber, month, listtype);
    }
    
    /**
     * ��֤�굥��������
     * @param termId �ն˻�ID
     * @param detailPwd �굥��������
     * @return true ��ȷ
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877
     */
    public boolean authDetailPrintPwd(String termId, String detailPwd)
    {
    	return detailedRecordsDaoImpl.authDetailPrintPwd(termId, detailPwd);
    }
    
}