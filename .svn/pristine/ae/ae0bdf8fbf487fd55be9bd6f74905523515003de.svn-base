package com.customize.nx.selfsvc.prodInstall.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;

/**
 * 在线开户
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  user
 * @version  [版本号, Jul 29, 2013]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]OR_NX_201303_280  宁夏自助终端优化需求之在线开户
 */
public class ProdInstallNxDaoImpl extends BaseDaoImpl
{
	// 日志
    private static Log logger = LogFactory.getLog(ProdInstallNxDaoImpl.class);
    /**
     * 查询产品模板 
     * <功能详细描述>
     * @param tpltTempletPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TpltTempletPO> qryTpltTempletList(TpltTempletPO tpltTempletPO)
    {
        return sqlMapClientTemplate.queryForList("install.qryTpltTempletList", tpltTempletPO);
    }
    
    /**
     * 保存写卡记录
     * <功能详细描述>
     * @param LogInstallPO
     * @return 失败0；
     * @see [类、类#方法、类#成员]
     */
    public String addLogInstall(LogInstallPO logInstallPO)
    {
    	return (String)sqlMapClientTemplate.insert("install.addLogInstall", logInstallPO);
    }

    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * 
     * @param log
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(CashDetailLogPO log)
    {
        try
        {
            sqlMapClientTemplate.insert("install.insertCashDetailLog", log);
        }
        catch (Exception e)
        {
            logger.error("记录用户投币情况异常：", e);
        }
    }
    
    /**
     * 获取缴费日志OID
     * 
     * @return
     * @see
     */
    public String getChargeLogOID()
    {
        return (String)sqlMapClientTemplate.queryForObject("install.getChargeLogOID");
    }
    
    /**
     * 缴费记录日志
     * 
     * @param cardChargeLogVO 银行卡信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.insert("install.insertChargeLog", cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.update("install.updateCardChargeLogNX", cardChargeLogVO);
    }
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId()
    {
        return (String)sqlMapClientTemplate.queryForObject("install.getInstallId");
    }
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(LogInstallPO logInstallPO)
    {
        sqlMapClientTemplate.update("install.updateInstallLog", logInstallPO);
    }
    
    /**
     * 更新银联卡缴费日志状态
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLogStatus(CardChargeLogVO cardChargeLogVO)
    {
    	sqlMapClientTemplate.update("install.updateCardChargeLogStatus", cardChargeLogVO);
    }
    
    /**
     * 更新开户日志备注信息
     * 
     * @param logInstallPO
     * @see
     */
    public void updateInstallLogNotes(LogInstallPO logInstallPO)
    {
    	sqlMapClientTemplate.update("install.updateInstallLogNotes", logInstallPO);
    }
}
