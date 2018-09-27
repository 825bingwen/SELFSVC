/*
 * 文 件 名:  CardBusiDaoImpl.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  办卡业务Dao实现
 * 修 改 人:  zKF69263
 * 修改时间:  2014-10-24
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.customize.hub.selfsvc.cardbusi.dao;

import java.util.List;

import com.customize.hub.selfsvc.cardbusi.model.CardBusiLogPO;
import com.customize.hub.selfsvc.cardbusi.model.ProdTempletPO;
import com.gmcc.boss.selfsvc.charge.model.CardChargeLogVO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 办卡业务Dao实现
 * 
 * @author  zKF69263
 * @version  [版本号, 2014-10-24]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CardBusiDaoImpl extends BaseDaoImpl
{
    /**
     * 查询开户产品模板
     * 
     * @param prodTempletPO
     * @return List<ProdTempletPO>
     * @see [类、类#方法、类#成员]
     */
    public List<ProdTempletPO> qryProdTempletList(ProdTempletPO prodTempletPO)
    {
        return sqlMapClientTemplate.queryForList("cardbusi.qryProdTempletList", prodTempletPO);
    }
    
    /** 
     * 查询终端支持空白卡写卡支持的卡类型
     * 
     * @param termId 终端机编号
     * @return DictItemPO
     * @see [类、类#方法、类#成员]
     */
    public DictItemPO qryTermBlankCardType(String termId)
    {
        return (DictItemPO)sqlMapClientTemplate.queryForObject("cardbusi.qryTermBlankCardType", termId);
    }
    
    /**
     * 添加写卡记录
     * <功能详细描述>
     * @param CardBusiLogPO
     * @return 失败0；
     * @see [类、类#方法、类#成员]
     */
    public String addLogInstall(CardBusiLogPO cardBusiLogPO)
    {
        return (String)sqlMapClientTemplate.insert("cardbusi.addLogInstall", cardBusiLogPO);
    }
    
    /**
     * 获取开户日志Id
     * 
     * @return
     * @see
     */
    public String getInstallId()
    {
        return (String)sqlMapClientTemplate.queryForObject("cardbusi.getInstallId");
    }
    
    /**
     * 更新开户日志
     * 
     * @param cardChargeLogVO
     * @see
     */
    public void updateInstallLog(CardBusiLogPO logInstallPO)
    {
        sqlMapClientTemplate.update("cardbusi.updateInstallLog", logInstallPO);
    }
    
    /**
     * 更新缴费日志状态
     * @param CardChargeLogVO
     * 
     */
    public void updateCardChargeLog(CardChargeLogVO cardChargeLogVO)
    {
        sqlMapClientTemplate.update("cardbusi.updateCardChargeLog", cardChargeLogVO);
    }
    
    /**
     * 查找本月补卡次数
     * 
     * @return
     * @see
     */
    public int getReissueCardNum(CardBusiLogPO cardBusiLogPO)
    {
        return (Integer)sqlMapClientTemplate.queryForObject("cardbusi.getReissueCardNum",cardBusiLogPO);
    }
}
