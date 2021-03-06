/*
 * 文件名：DetailedRecordsServiceImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-16
 * 修改内容：新增，月详单查询
 */
package com.gmcc.boss.selfsvc.feeservice.service;

import java.util.List;

import com.gmcc.boss.selfsvc.feeservice.dao.DetailedRecordsDaoImpl;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;

/**
 * 月详单查询，查询详单打印次数，打印结束后，更新详单打印记录
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-16
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
     * 查询详单打印次数
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @return
     * @see
     */
    public DetailedRecordsTimesPO getPrintTimes(String servNumber, String month, String listtype)
    {
        return detailedRecordsDaoImpl.getPrintTimes(servNumber, month, listtype);
    }
    
    /**
     * 新增详单打印记录
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @param times，打印次数
     * @see 
     */
    public void addPrintTimes(String servNumber, String month, int times, String listtype)
    {
    	detailedRecordsDaoImpl.addPrintTimes(servNumber, month, times, listtype);
    }
    
    /**
     * 更新详单打印记录
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @param times，打印次数
     * @see
     */
    public void updatePrintTimes(String servNumber, String month, int times, String listtype)
    {
        detailedRecordsDaoImpl.updatePrintTimes(servNumber, month, times, listtype);
    }
    
    /**
     * 查询详单打印记录
     * @param servNumber 手机号码
     * @param month 月份
     * @param listtype 详单类型
     * @return 符合条件的详单打印记录，按打印时间降序排列
	 * @remark create g00140516 2013/02/03 R003C13L01n01 每年的12个月，每月详单均只能打印一次
     */
    public List<DetailedRecordsTimesPO> getPrintRecords(String servNumber, String month,String listtype)
    {
    	return detailedRecordsDaoImpl.getPrintRecords(servNumber, month, listtype);
    }
    
    /**
     * 验证详单补打密码
     * @param termId 终端机ID
     * @param detailPwd 详单补打密码
     * @return true 正确
     * @remark create by sWX219697 2014-6-3 OR_huawei_201405_877
     */
    public boolean authDetailPrintPwd(String termId, String detailPwd)
    {
    	return detailedRecordsDaoImpl.authDetailPrintPwd(termId, detailPwd);
    }
    
}
