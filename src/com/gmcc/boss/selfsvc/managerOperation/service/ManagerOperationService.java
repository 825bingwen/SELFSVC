package com.gmcc.boss.selfsvc.managerOperation.service;

import java.util.List;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;

/**
 * 维护人员管理 包括现金稽核
 * 
 * @author xKF29026
 *
 */
public interface ManagerOperationService
{
    // 校验稽核人员密码
    public String checkAuditPassword(ManagerOperationPO managerOperationPO);
    
    // 获取系统计算总金额
    public String getSysMoney(String termid);
    
    // 插入现金稽核日志
    public String insertAuditCashLog(ManagerOperationPO managerOperationPO);
    
    /**
     * 根据稽核时间段查询系统统计金额
     * 
     * @param log
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String getSysMoney(ManagerOperationPO log);
        
    /**
     * 获取上次稽核日志
     * 
     * @param termid
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public ManagerOperationPO qryLastAuditLog(String termid);
    
    /**
     * 查询稽核时间段内各种面额的纸币张数
     * 
     * @param log
     * @param fillStr
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public List<CashDetailPO> qryCashDetailInfo(ManagerOperationPO log, String fillStr);
    
    /**
     * 记录宁夏现金稽核日志
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public String insertNXAuditLog(ManagerOperationPO managerOperationPO);
    
    /**
     * 查询稽核时间段内的前6条缴费记录和后6条缴费记录
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public List<CardChargeLogVO> qrySixChargeRecords(String flag, ManagerOperationPO managerOperationPO);
    
    /**
     * 根据指定的sqlID查询符合条件的缴费记录的条数
     * 
     * @param sqlID
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public String qryChargeRecordsNum(String sqlID, ManagerOperationPO managerOperationPO);
    
    /**
     * 查询缴费失败记录
     * 
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create  cKF76106 2012/08/07 R003C12L07n01 OR_NX_201207_780
     */
    public List<CardChargeLogVO> qryFailedRecords(ManagerOperationPO managerOperationPO);
    
    /**
     * 查询未打印的记录
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ManagerOperationPO> qryUnPrintRecords(ManagerOperationPO managerOperationPO);
    
    /**
     * 稽核前记录数据
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertLogByAuditBefore(ManagerOperationPO managerOperationPO);
    
    /**
     * 更新宁夏现金稽核日志
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updateNXAuditLog(ManagerOperationPO managerOperationPO);    
    
    /**
     * 更新宁夏现金稽核日志
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String updatePringFlag(ManagerOperationPO managerOperationPO);

}
