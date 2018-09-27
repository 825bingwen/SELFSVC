package com.customize.hub.selfsvc.chooseTel.service;

import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.model.BlacklistPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelNumPO;
import com.customize.hub.selfsvc.chooseTel.model.LoverNumPO;
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
public interface ChooseTelService
{
    /**
     * 取得选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ChooseTelNumPO> getChooseTelNum(ChooseTelNumPO chooseTelNumPO);
    
    /**
     * 更新选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateChooseTelNum(ChooseTelNumPO chooseTelNumPO);
    
    /**
     * 增加选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertChooseTelNum(ChooseTelNumPO chooseTelNumPO);
    
    /**
     * 取得黑名单
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<BlacklistPO> getBlacklist(BlacklistPO blacklistPO);
    
    /**
     * 查询吉祥号码规则
     * 
     * @return
     * @see 
     */
    public List<DictItemPO> qryLuckyNumRules();
    
    /**
     * 查询情侣号码
     * 
     * @param po
     * @return
     * @see 
     */
    public List<LoverNumPO> qryLoverNum(LoverNumPO po);
    
    /**
     * 查询吉祥号码费用信息
     * 
     * @return
     * @see 
     */
    public Map<String, ServerNumPO> qryLuckyNumInfo();
    
    /**
     * 更新情侣号码信息
     * 
     * @param po
     * @see 
     */
    public void updateLoverNumInfo(LoverNumPO po);
    
    /**
     * 选号预约记录日志
     * @param chooseTelLogPO
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017/01/09 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求
     */
    public void createTelLog(ChooseTelLogPO chooseTelLogPO);
}
