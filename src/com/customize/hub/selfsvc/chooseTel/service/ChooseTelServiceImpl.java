/*
 * 文件名：ResDataServiceImpl.java
 * 版权：CopyRight 2000-2015 Huawei Technologies Co., Ltd.All Righs Reserved.
 * 描述：自助终端资源数据 ServiceImpl
 * 修改人：z00107005
 * 修改时间：2008-1-17
 * 修改内容：新增
 * 修改人：g00140516
 * 修改时间：2010-11-30
 * 修改内容：修改，以适应自助终端的需要
 */
package com.customize.hub.selfsvc.chooseTel.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customize.hub.selfsvc.chooseTel.model.ServerNumPO;
import com.customize.hub.selfsvc.chooseTel.dao.ChooseTelDaoImpl;
import com.customize.hub.selfsvc.chooseTel.model.BlacklistPO;
import com.customize.hub.selfsvc.chooseTel.model.ChooseTelLogPO;
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
public class ChooseTelServiceImpl implements ChooseTelService
{
    private ChooseTelDaoImpl chooseTelDao;
    
    /**
     * 取得选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<ChooseTelNumPO> getChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        return chooseTelDao.getChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * 更新选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void updateChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        chooseTelDao.updateChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * 增加选号次数
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public void insertChooseTelNum(ChooseTelNumPO chooseTelNumPO)
    {
        chooseTelDao.insertChooseTelNum(chooseTelNumPO);
    }
    
    /**
     * 取得黑名单
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<BlacklistPO> getBlacklist(BlacklistPO blacklistPO)
    {
        return chooseTelDao.getBlacklist(blacklistPO);
    }
    
    /**
     * 查询吉祥号码规则
     * 
     * @return
     * @see 
     */
    public List<DictItemPO> qryLuckyNumRules()
    {
        return chooseTelDao.qryLuckyNumRules();
    }
    
    /**
     * 查询情侣号码
     * 
     * @param po
     * @return
     * @see 
     */
    public List<LoverNumPO> qryLoverNum(LoverNumPO po)
    {
        return chooseTelDao.qryLoverNum(po);
    }
    
    /**
     * 查询吉祥号码费用信息
     * 
     * @return
     * @see 
     */
    public Map<String, ServerNumPO> qryLuckyNumInfo()
    {
        Map<String, ServerNumPO> info = new HashMap<String, ServerNumPO>();
        
        List<ServerNumPO> list = chooseTelDao.qryLuckyNumInfo();
        
        if (null != list && list.size() > 0)
        {
            ServerNumPO po = null;
            
            for (int i = 0; i < list.size(); i++)
            {
                po = list.get(i);
                
                info.put(po.getTelnum(), po);
            }
        }
        
        return info;
    }
    
    /**
     * 更新情侣号码信息
     * 
     * @param po
     * @see 
     */
    public void updateLoverNumInfo(LoverNumPO po)
    {
        chooseTelDao.updateLoverNumInfo(po);
    }

    public ChooseTelDaoImpl getChooseTelDao()
    {
        return chooseTelDao;
    }

    public void setChooseTelDao(ChooseTelDaoImpl chooseTelDao)
    {
        this.chooseTelDao = chooseTelDao;
    }

    /**
     * 选号预约日志记录
     * @param chooseTelLogPO
     * @see [类、类#方法、类#成员]
     * @remark create by lWX431760 2017/01/09 OR_HUB_201611_276_【BOSS常规需求】自助终端号码预约功能优化的需求
     */
	@Override
	public void createTelLog(ChooseTelLogPO chooseTelLogPO) {
		chooseTelDao.createTelLog(chooseTelLogPO);
	}
}
