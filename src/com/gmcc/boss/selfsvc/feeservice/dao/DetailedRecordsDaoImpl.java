/*
 * 文件名：DetailedRecordsDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-12-16
 * 修改内容：新增，月详单查询
 */
package com.gmcc.boss.selfsvc.feeservice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.customize.sd.selfsvc.feeService.model.DetailPrintPwdPo;
import com.gmcc.boss.selfsvc.cache.PublicCache;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.feeservice.model.DetailedRecordsTimesPO;

/**
 * 
 * 月详单查询时，查询详单打印次数，打印结束后，更新详单打印记录
 * 
 * 
 * @author g00140516
 * @version 1.0，2010-12-16
 * @see
 * @since
 */
public class DetailedRecordsDaoImpl extends BaseDaoImpl
{
    public DetailedRecordsDaoImpl()
    {
        super();
    }
    
    /**
     * 查询清单打印次数
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @param listtype,帐单类型
     * @return
     * @see
     */
    public DetailedRecordsTimesPO getPrintTimes(String servNumber, String month,String listtype)
    {
        DetailedRecordsTimesPO obj = new DetailedRecordsTimesPO();
        obj.setServNumber(servNumber);
        obj.setMonth(month);
        obj.setListtype(listtype);
        // yKF28472 modify start 20111126 增加湖北打印全部详单次数限制 
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        {
        	return (DetailedRecordsTimesPO)sqlMapClientTemplate.queryForObject("feeservice.getPrintTimesHUB", obj);
        }
        else
        {
        	return (DetailedRecordsTimesPO)sqlMapClientTemplate.queryForObject("feeservice.getPrintTimes", obj);
        }
        // yKF28472 modify end 20111126 增加湖北打印全部详单次数限制 
        
    }
    
    /**
     * 新增详单打印记录
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @param times，打印次数
     * @param listtype,帐单类型
     * @see 
     */
    public void addPrintTimes(String servNumber, String month, int times, String listtype)
    {
        DetailedRecordsTimesPO obj = new DetailedRecordsTimesPO();
        obj.setServNumber(servNumber);
        obj.setMonth(month);
        obj.setTimes(times);
        obj.setListtype(listtype);
        // yKF28472 modify begion 20111126 增加湖北打印全部详单次数限制 
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        {
        	sqlMapClientTemplate.update("feeservice.addPrintTimesHUB", obj);
        }
        else
        {
        	sqlMapClientTemplate.update("feeservice.addPrintTimes", obj);
        }
        // yKF28472 end begion 20111126 增加湖北打印全部详单次数限制 
    }
    
    /**
     * 更新清单打印记录
     * 
     * @param servNumber，服务号码
     * @param month，月份
     * @param times，打印次数
     * @param listtype,帐单类型
     * @see
     */
    public void updatePrintTimes(String servNumber, String month, int times, String listtype)
    {
        DetailedRecordsTimesPO obj = new DetailedRecordsTimesPO();
        obj.setServNumber(servNumber);
        obj.setMonth(month);
        obj.setTimes(times);
        obj.setListtype(listtype);
        // yKF28472 modify begion 20111126 增加湖北打印全部详单次数限制 
        if (Constants.PROOPERORGID_HUB.equals(PublicCache.getInstance().getCachedData("ProvinceID")))
        {
        	sqlMapClientTemplate.update("feeservice.updatePrintTimesHUB", obj);
        }
        else
        {
        	sqlMapClientTemplate.update("feeservice.updatePrintTimes", obj);
        }

        
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
        DetailedRecordsTimesPO obj = new DetailedRecordsTimesPO();
        obj.setServNumber(servNumber);
        obj.setMonth(month);
        obj.setListtype(listtype);
        
        return (List<DetailedRecordsTimesPO>) sqlMapClientTemplate.queryForList("feeservice.getPrintTimes", obj);
    }
    
    /**
     * <验证用户输入的详单补打密码是否正确（山东）>
     * <功能详细描述>
     * @param termId 终端机编号
     * @param detailPwd 详单补打密码
     * @return true 正确，false 错误
     * @see [类、类#方法、类#成员]
     * @remark create by sWX219697 2014-6-3 15:19:36 OR_huawei_201405_877
     */
    public boolean authDetailPrintPwd(String termId,String detailPwd)
    {
    	DetailPrintPwdPo obj = new DetailPrintPwdPo();
    	
    	//补打密码
    	obj.setDetailPwd(detailPwd);
    	
    	//终端机ID
    	obj.setTermId(termId);
    	
    	int num = 0;
		num = (Integer)sqlMapClientTemplate.queryForObject("feeservice.authDetailPrintPwd",obj);
    	
		if(num > 0)
		{
			return true;
		}
		
    	return false;
    }
    
}
