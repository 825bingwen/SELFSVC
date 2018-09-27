package com.customize.nx.selfsvc.prodInstall.service;

import java.util.List;
import java.util.Map;

import com.customize.nx.selfsvc.prodInstall.dao.ProdInstallNxDaoImpl;
import com.customize.nx.selfsvc.prodInstall.model.LogInstallPO;
import com.customize.nx.selfsvc.prodInstall.model.TpltTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.charge.model.CashDetailLogPO;

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
public class ProdInstallNxServiceImpl implements ProdInstallNxService
{
    private ProdInstallNxDaoImpl prodInstallNxDaoImpl;

    /**
     * 查询产品模板
     * <功能详细描述>
     * @param tpltTempletPO
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TpltTempletPO> qryTpltTempletList(TpltTempletPO tpltTempletPO)
    {
        return prodInstallNxDaoImpl.qryTpltTempletList(tpltTempletPO);
    }

    /**
     * 注入DAO
     * <功能详细描述>
     * @param prodInstallNxDaoImpl
     * @see [类、类#方法、类#成员]
     */
    public void setProdInstallNxDaoImpl(ProdInstallNxDaoImpl prodInstallNxDaoImpl)
    {
        this.prodInstallNxDaoImpl = prodInstallNxDaoImpl;
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
    	return prodInstallNxDaoImpl.addLogInstall(logInstallPO);
    }
    
    /**
     * 记录用户交费日志之前先记录用户的投币情况
     * 
     * @param params
     * @see 
     * @remark create g00140516 2012/03/09 R003C12L03n01 OR_NX_201201_312
     */
    public void insertCashDetailInfo(Map<String, String> params)
    {
        if (null == params)
        {
            return;
        }
        
        // 用户投币的面额信息，如50;100，既投币两次，第一次50，第二次100
        String cashDetail = params.get("cashDetail");
        if (null == cashDetail || "".equals(cashDetail.trim()))
        {
            return;
        }
        
        String termID = params.get("termID");
        String servNumber = params.get("telnum");
        String terminalSeq = params.get("terminalSeq");
        
        String[] cashes = cashDetail.split(";");
        
        CashDetailLogPO log = null;
        for (int i = 0; i < cashes.length; i++)
        {
            log = new CashDetailLogPO();
            log.setTermID(termID);
            log.setServNum(servNumber);
            log.setFormNum(terminalSeq);
            log.setCashFee(cashes[i]);
            
            prodInstallNxDaoImpl.insertCashDetailInfo(log);
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
        return prodInstallNxDaoImpl.getChargeLogOID();
    }
    
    /**
     * 缴费日志
     * 
     * @param cardChargeLogVO 银联卡缴费信息
     * @return
     */
    public void addChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.addChargeLog(cardChargeLogVO);
    }
    
    /**
     * 银联扣款成功之后，更新日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.updateCardChargeLog(cardChargeLogVO);
    }
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId()
    {
        return prodInstallNxDaoImpl.getInstallId();
    }
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(LogInstallPO logInstallPO)
    {
    	prodInstallNxDaoImpl.updateInstallLog(logInstallPO);
    }
    
    /**
     * 更新银联卡缴费日志状态
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLogStatus(CardChargeLogVO cardChargeLogVO)
    {
    	prodInstallNxDaoImpl.updateCardChargeLogStatus(cardChargeLogVO);
    }
    
    /**
     * 更新开户日志备注信息
     * 
     * @param logInstallPO
     * @see
     */
    public void updateInstallLogNotes(LogInstallPO logInstallPO)
    {
    	prodInstallNxDaoImpl.updateInstallLogNotes(logInstallPO);
    }
}
