/*
 * 文件名：ResDataDaoImpl.java
 * 版权：CopyRight 2010-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：新增
 */
package com.customize.hub.selfsvc.chooseTel.dao;

import java.util.List;

import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.model.BlacklistPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
import com.gmcc.boss.selfsvc.common.BaseDaoImpl;
import com.gmcc.boss.selfsvc.resdata.model.DictItemPO;

/**
 * 预约选号_湖北
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yKF28472
 * @version  [版本号, Apr 19, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChooseTelDaoImpl extends BaseDaoImpl
{
    public ChooseTelDaoImpl()
    {
        super();
    }
    
    /**
     * 取得选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ChooseTelNumPO> getChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.getChooseTelNum",chooseTelNumPO);
    }
    
    /**
     * 更新选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        sqlMapClientTemplate.update("chooseTel.updateChooseTelNum", chooseTelNumPO);
    }
    
    /**
     * 增加选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        sqlMapClientTemplate.insert("chooseTel.insertChooseTelNum", chooseTelNumPO);
    }
    
    /**
     * 取得黑名单
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<BlacklistPO> getBlacklist(BlacklistPO blacklistPO)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.getBlacklist",blacklistPO);
    }

    /**
     * 查询吉祥号码规则
     * 
     * @return
     * @see 
     */
    public List<DictItemPO> qryLuckyNumRules()
    {
        return sqlMapClientTemplate.queryForList("chooseTel.luckyNumRules");
    }
    
    /**
     * 查询情侣号码
     * 
     * @return
     * @see 
     */
    public List<LoverNumPO> qryLoverNum(LoverNumPO po)
    {
        return sqlMapClientTemplate.queryForList("chooseTel.loverNums", po);
    }
    
    /**
     * 查询吉祥号码费用信息
     */
    public List<ServerNumPO> qryLuckyNumInfo()
    {
        return sqlMapClientTemplate.queryForList("chooseTel.luckyNumInfo");
    }
    
    /**
     * 更新情侣号码的信息，包括状态和失效时间
     * 
     * @param po
     * @see 
     */
    public void updateLoverNumInfo(LoverNumPO po)
    {
        sqlMapClientTemplate.update("chooseTel.updateLoverNumInfo", po);
    }
    
    /**
     * 记录选号预约日志
     * @param chooseTelLogPO
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017/01/09 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求
     */
    public void createTelLog(ChooseTelLogPO chooseTelLogPO)
    {
    	sqlMapClientTemplate.insert("chooseTel.insertTelLog", chooseTelLogPO);
    }
}
