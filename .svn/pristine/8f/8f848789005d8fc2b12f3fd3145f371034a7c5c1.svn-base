package com.customize.nx.selfsvc.prodInstall.service;

import java.util.List;
import java.util.Map;

import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;


/**
 * 
 * 在线开户
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Jul 29, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public interface ProdInstallNxService
{
    /**
     * 查询产品模板
     * <功能详细描述>
     * @param tpltTempletPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TpltTempletPO> qryTpltTempletList(TpltTempletPO tpltTempletPO);
    
    /**
     * 保存写卡记录
     * <功能详细描述>
     * @param LogInstallPO
     * @return 失败0；
     * @see [类、类#方法、类#成员]
     */
    public String addLogInstall(LogInstallPO logInstallPO);
    
    /**
     * 记录用户开户日志之前先记录用户的投币情况
     * 
     * @param params
     * @see 
     * @remark create yWX163692 2013/10/26 
     */
    public void insertCashDetailInfo(Map<String, String> params);
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID();
    
    /**
     * 缴费日志
     * 
     * @param cardChargeLogVO 银联卡缴费信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId();
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(LogInstallPO logInstallPO);
    
    /**
     * 更新银联卡缴费日志状态
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLogStatus(CardChargeLogVO cardChargeLogVO);
    
    /**
     * 更新开户日志备注信息
     * 
     * @param logInstallPO
     * @see
     */
    public void updateInstallLogNotes(LogInstallPO logInstallPO);
}
