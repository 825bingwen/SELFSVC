package com.gmcc.boss.selfsvc.managerOperation.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.Constants;
import com.gmcc.boss.selfsvc.managerOperation.dao.ManagerOperationDaoImpl;
import com.gmcc.boss.selfsvc.managerOperation.model.CashDetailPO;
import com.gmcc.boss.selfsvc.managerOperation.model.ManagerOperationPO;
import com.gmcc.boss.selfsvc.util.CommonUtil;

/**
 * 维护人员管理 包括现金稽核
 * 
 * @author xKF29026
 *
 */
public class ManagerOperationServiceImpl implements ManagerOperationService
{
    private ManagerOperationDaoImpl managerOperationDaoImpl;
    
    /**
     * 校验稽核人员密码
     */
    public String checkAuditPassword(ManagerOperationPO managerOperationPO)
    {
        // add begin zKF69263 2015-4-2 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        // 现金稽核开关
        String cashAuditSwitch = CommonUtil.getParamValue(Constants.SH_CASHAUDIT_SWITCH);
        
        // 为1表示现金稽核开关开启
        if ("1".equals(cashAuditSwitch))
        {
            // 查询终端机稽核人密码 
            String termAuditPass = managerOperationDaoImpl.qryTermAuditPass(managerOperationPO);
            
            // 如果终端机没有设置稽核人密码，设置为公共密码
            if (StringUtils.isEmpty(termAuditPass))
            {
                // 取得公共密码并进行MD5加密
                termAuditPass = CommonUtil.MD5Encode(CommonUtil.getParamValue(Constants.SH_CASHAUDIT_PASSWORD));
            }
            
            // 验证稽核人密码是否正确，正确返回1，错误返回2
            if (StringUtils.isNotEmpty(termAuditPass) && termAuditPass.equals(managerOperationPO.getAuditPsw()))
            {
                return "1";
            }
            
            return "2";
        }
        // add end zKF69263 2015-4-2 OR_SD_201502_169_山东_自助终端实现现金稽核功能
        
        return managerOperationDaoImpl.checkAuditPassword(managerOperationPO);
    }
    
    /**
     * 获取系统计算总金额
     */
    public String getSysMoney(String termid)
    {
        return managerOperationDaoImpl.getSysMoney(termid);
    }
    
    /**
     * 插入现金稽核日志
     */
    public String insertAuditCashLog(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.insertAuditCashLog(managerOperationPO);
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
        String strMoney = managerOperationDaoImpl.getSysMoney(log);
        
        if (null == strMoney || "".equals(strMoney.trim()))
        {
            strMoney = "0";
        }
        
        return strMoney;
    }
    
    /**
     * 查询稽核时间段内各种面额的纸币张数
     * 
     * @param log
     * @param fillStr
     * @return
     * @see 
     * @remark: create g00140516 2012/03/11 R003C12L03n01 OR_NX_201201_312
     */
    public List<CashDetailPO> qryCashDetailInfo(ManagerOperationPO log, String fillStr)
    {
        List<CashDetailPO> cashes = managerOperationDaoImpl.qryCashDetailInfo(log);
        if (null != cashes && cashes.size() > 0)
        {
            CashDetailPO cash = null;
            for (int i = 0; i < cashes.size(); i++)
            {
                cash = cashes.get(i);
                
                // 经过测试，此处需使用两个&nbsp;，只用一个的话，无法右对齐
                cash.setCashFee(CommonUtil.fillLeftBlank(cash.getCashFee(), 3, fillStr));
                cash.setCashNum(CommonUtil.fillLeftBlank(cash.getCashNum(), 4, fillStr));
            }
        }
        
        return cashes;
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
        return managerOperationDaoImpl.qryLastAuditLog(termid);
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
        return managerOperationDaoImpl.insertNXAuditLog(managerOperationPO);
    }
    
    /**
     * 查询稽核时间段内的前6条缴费记录或后6条缴费记录
     * 
     * @param flag first，前6条；否则，后6条
     * @param managerOperationPO
     * @return
     * @see 
     * @remark: create g00140516 2012/03/12 R003C12L03n01 OR_NX_201201_312
     */
    public List<CardChargeLogVO> qrySixChargeRecords(String flag, ManagerOperationPO managerOperationPO)
    {
        if ("first".equalsIgnoreCase(flag))
        {
            return managerOperationDaoImpl.qrySixChargeRecords("selectFirstSixChargeRecords", managerOperationPO);
        }
                
        return managerOperationDaoImpl.qrySixChargeRecords("selectLastSixChargeRecords", managerOperationPO);
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
        return managerOperationDaoImpl.qryChargeRecordsNum(sqlID, managerOperationPO);
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
        return managerOperationDaoImpl.qryFailedRecords(managerOperationPO);
    }
    
    /**
     * 查询未打印的记录
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ManagerOperationPO> qryUnPrintRecords(ManagerOperationPO managerOperationPO)
    {
        return managerOperationDaoImpl.qryUnPrintRecords(managerOperationPO);
    }
    
    /**
     * 稽核前记录数据
     * <功能详细描述>
     * @param managerOperationPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertLogByAuditBefore(ManagerOperationPO managerOperationPO)
    {
        managerOperationDaoImpl.insertLogByAuditBefore(managerOperationPO);
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
        return managerOperationDaoImpl.updateNXAuditLog(managerOperationPO);
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
        return managerOperationDaoImpl.updatePringFlag(managerOperationPO);
    }
    
    public ManagerOperationDaoImpl getManagerOperationDaoImpl()
    {
        return managerOperationDaoImpl;
    }
    
    public void setManagerOperationDaoImpl(ManagerOperationDaoImpl managerOperationDaoImpl)
    {
        this.managerOperationDaoImpl = managerOperationDaoImpl;
    }
}
