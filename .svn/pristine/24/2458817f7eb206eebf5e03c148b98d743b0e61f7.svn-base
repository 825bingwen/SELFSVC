package com.gmcc.boss.selfsvc.managerOperation.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;

/**
 * 维护人员管理 包括现金稽核
 * 
 * @author xKF29026
 * 
 */
public class ManagerOperationDaoImpl extends BaseDaoImpl
{
    public static final Log logger = LogFactory.getLog(ManagerOperationDaoImpl.class);
        
    /**
     * 校验稽核监督人员密码
     */
    public String checkAuditPassword(ManagerOperationPO managerOperationPO)
    {
    	ManagerOperationPO checkStatus = (ManagerOperationPO) sqlMapClientTemplate.queryForObject("managerOperation.checkAuditPassword", managerOperationPO);
    	if(checkStatus != null)
    	{
    		return "1";
    	}
    	else
    	{
    		return "2";
    	}
    }
    
    /** 
     * 查询终端机稽核人密码 
     * 
     * @param managerOperationPO
     * @return String
     * @see [类、类#方法、类#成员]
     * @remark: create zKF69263 2015/04/10 OR_SD_201502_169_SD_自助终端实现现金稽核功能
     */
    public String qryTermAuditPass(ManagerOperationPO managerOperationPO)
    {
        return (String) sqlMapClientTemplate.queryForObject("managerOperation.qryTermAuditPass", managerOperationPO);
    }
    
    /**
     * 获取系统计算总金额
     */
    public String getSysMoney(String termid)
    {
        ManagerOperationPO managerOperationPO = new ManagerOperationPO();
        managerOperationPO.setTermid(termid);
        String auditEndTime = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditEndTime", termid);
        if (auditEndTime == null)
        {
            auditEndTime = "2010-01-01 00:00:00";
        }
        else
        {
            auditEndTime = auditEndTime.substring(0, 19);
        }
        managerOperationPO.setAuditEndTime(auditEndTime);
        String totalFeeValus = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditTotalFee",
                managerOperationPO);
        if (totalFeeValus == null || totalFeeValus .equals("0"))
        {
            return "000";
        }
        return totalFeeValus;
    }
    
    /**
     * 插入现金稽核日志
     */
    public String insertAuditCashLog(ManagerOperationPO managerOperationPO)
    {
        String auditEndTime = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditEndTime",
                managerOperationPO.getTermid());
        if (auditEndTime == null)
        {
            auditEndTime = "2010-01-01 00:00:00";
        }
        else
        {
            auditEndTime = auditEndTime.substring(0, 19);
        }
        managerOperationPO.setAuditEndTime(auditEndTime);
        String totalFeeValus = (String)sqlMapClientTemplate.queryForObject("managerOperation.selectAuditTotalFee",
                managerOperationPO);
        if (totalFeeValus == null)
        {
            managerOperationPO.setSysMoney("0");
        }
        else
        {
            managerOperationPO.setSysMoney(totalFeeValus);
        }
        try
        {
            sqlMapClientTemplate.insert("managerOperation.insertAuditCashLog", managerOperationPO);
            return "1";
        }
        catch (Exception e)
        {
            logger.error("记录现金稽核日志异常：", e);
            
            return "0";
        }
    }
    
    /**
     * 获取上次稽核日志
     * 
     * @param termid
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312 
     */
    public ManagerOperationPO qryLastAuditLog(String termid)
    {
        List<ManagerOperationPO> auditLogs = (List<ManagerOperationPO>) sqlMapClientTemplate.queryForList("managerOperation.selectLastAuditLog", termid);
        if (null == auditLogs || auditLogs.size() == 0)
        {
            return null;
        }
        
        return auditLogs.get(0);
    }
    
    /**
     * 根据稽核时间段查询系统统计金额
     * 
     * @param log
     * @return
     * @see
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312 
     */
    public String getSysMoney(ManagerOperationPO log)
    {
        return (String) sqlMapClientTemplate.queryForObject("managerOperation.selectSystemTotalFee", log);
    }
    
    /**
     * 查询稽核时间段内各种面额的纸币张数
     * 
     * @param log
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public List<CashDetailPO> qryCashDetailInfo(ManagerOperationPO log)
    {
        return (List<CashDetailPO>) sqlMapClientTemplate.queryForList("managerOperation.getCashDetailInfo", log);
    }
    
    /**
     * 记录宁夏现金稽核日志
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String insertNXAuditLog(ManagerOperationPO managerOperationPO)
    {
        String result = "1";
        
        String auditStartTime = managerOperationPO.getAuditStartTime();
        if ("".equals(auditStartTime))
        {
            try
            {
                sqlMapClientTemplate.insert("managerOperation.insertNXFirstAuditLog", managerOperationPO);
            }
            catch (Exception e)
            {
                logger.error("记录宁夏现金稽核日志异常：", e);
                
                result = "0";
            }
        }
        else
        {
            try
            {
                sqlMapClientTemplate.insert("managerOperation.insertNXAuditLog", managerOperationPO);
            }
            catch (Exception e)
            {
                logger.error("记录宁夏现金稽核日志异常：", e);
                
                result = "0";
            }
        }
        
        return result;
    }
    
    /**
     * 查询稽核时间段内的前6条缴费记录或后6条缴费记录
     * 
     * @param sqlID
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public List<CardChargeLogVO> qrySixChargeRecords(String sqlID, ManagerOperationPO managerOperationPO)
    {
        List<CardChargeLogVO> records = null;
        
        try
        {
            records = (List<CardChargeLogVO>) sqlMapClientTemplate.queryForList("managerOperation." + sqlID, managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("根据" + sqlID + "查询稽核时间段内的前6条缴费记录或后6条缴费记录异常：", e);
        }
        
        return records;
    }
    
    /**
     * 根据指定的sqlID查询符合条件的缴费记录的条数
     * 
     * @param sqlID
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public String qryChargeRecordsNum(String sqlID, ManagerOperationPO managerOperationPO)
    {
        String result = "0";
        
        try
        {
            result = (String) sqlMapClientTemplate.queryForObject("managerOperation." + sqlID, managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("根据" + sqlID + "查询符合条件的记录异常：", e);
        }
        
        return result;
    }
    
    /**
     * 查询缴费失败记录
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create  cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
     */
    public List<CardChargeLogVO> qryFailedRecords(ManagerOperationPO managerOperationPO)
    {
        List<CardChargeLogVO> records = null;
        
        try
        {
            records = (List<CardChargeLogVO>) sqlMapClientTemplate.queryForList("managerOperation.selectFailedRecords" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("查询现金缴费失败记录异常：", e);
        }
        
        return records;
    }
    
    /**
     * 查询未打印的记录_宁夏
     * 
     * @param managerOperationPO
     * @return
     * @see 
     */
    public List<ManagerOperationPO> qryUnPrintRecords(ManagerOperationPO managerOperationPO)
    {
        List<ManagerOperationPO> records = null;
        
        try
        {
            records = (List<ManagerOperationPO>) sqlMapClientTemplate.queryForList("managerOperation.qryUnPrintRecords" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("查询未打印的结账单记录失败：", e);
        }
        
        return records;
    }
    
    /**
     * 稽核前记录日志_宁夏
     * 
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertLogByAuditBefore(ManagerOperationPO managerOperationPO)
    {
        try
        {
            sqlMapClientTemplate.insert("managerOperation.insertLogByAuditBefore" , managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("稽核前记录日志失败：", e);
        }
    }
    
    /**
     * 更新宁夏现金稽核日志
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateNXAuditLog(ManagerOperationPO managerOperationPO)
    {
        String result = "1";

        try
        {
            sqlMapClientTemplate.insert("managerOperation.updateNXAuditLog", managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("记录宁夏现金稽核日志异常：", e);
            
            result = "0";
        }
        
        return result;
    }
    
    /**
     * 更新宁夏现金稽核日志
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updatePringFlag(ManagerOperationPO managerOperationPO)
    {
        String result = "1";

        try
        {
            sqlMapClientTemplate.insert("managerOperation.updatePringFlag", managerOperationPO);
        }
        catch (Exception e)
        {
            logger.error("记录宁夏现金稽核日志异常：", e);
            
            result = "0";
        }
        return result;
    }
    
}
