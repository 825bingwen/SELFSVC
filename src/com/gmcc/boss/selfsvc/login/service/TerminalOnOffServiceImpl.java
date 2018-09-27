package com.gmcc.boss.selfsvc.login.service;

import com.gmcc.boss.selfsvc.login.dao.TerminalOnOffDaoImpl;
import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;

/**
 * 对用户开关机信息进行记录
 * 
 * @author lWX5316086
 * @version
 * @see
 * @since
 * @remark modify begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
 */
public class TerminalOnOffServiceImpl implements TerminalOnOffService
{
    
    private TerminalOnOffDaoImpl terminalOnOffDaoImpl = null;
    
    /**
     * 设备开启操作
     */
    @Override
    public void termOnOff(TerminalOnOffPO terminalOnOffPO)
    {
        //心跳ID
        String heartId = terminalOnOffDaoImpl.qryTermHeart(terminalOnOffPO.getTermId());
        
        //开关机状态ID
        String onOffId = terminalOnOffDaoImpl.qryTermOnOff(terminalOnOffPO.getTermId());
        
        //开关机历史ID
        String hisId = terminalOnOffDaoImpl.qryTermOnOffHis(terminalOnOffPO);
        
        //无开关机状态记录，新增数据
        if (onOffId == null)
        {
            terminalOnOffDaoImpl.insertTermOnOff(terminalOnOffPO);
        }
        //有开关机状态记录，更新数据
        else
        {
            terminalOnOffPO.setId(onOffId);
            terminalOnOffDaoImpl.updateTermOnOff(terminalOnOffPO);
        }
        
        //无开关机历史记录，新增数据
        if (hisId == null)
        {
            terminalOnOffDaoImpl.insertTermOnOffHis(terminalOnOffPO);
        }
        //有开关机历史记录，更新数据并插入数据
        else
        {
            terminalOnOffPO.setId(hisId);
            terminalOnOffDaoImpl.updateTermOnOffHis(terminalOnOffPO);
            terminalOnOffDaoImpl.insertTermOnOffHis(terminalOnOffPO);
        }
        
        //无心跳记录，新增数据
        if (heartId == null)
        {
            terminalOnOffDaoImpl.insertTermHeart(terminalOnOffPO.getTermId());
        }
        //有心跳记录，更新数据
        else
        {
            terminalOnOffDaoImpl.updateTermHeart(terminalOnOffPO.getTermId());
        }
        
    }
    
    /**
     * 更新设备开关机心跳表
     * 
     * @param termId
     */
    public void updateTermHeart(String termId)
    {
        terminalOnOffDaoImpl.updateTermHeart(termId);
    }
    
    /** 根据termid获取region
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
     */
    public int getRegionByTermId (String termId)
    {
        int region = terminalOnOffDaoImpl.qryRegionByTermId(termId);
        return region;
    }
    
    public TerminalOnOffDaoImpl getTerminalOnOffDaoImpl()
    {
        return terminalOnOffDaoImpl;
    }
    
    public void setTerminalOnOffDaoImpl(TerminalOnOffDaoImpl terminalOnOffDaoImpl)
    {
        this.terminalOnOffDaoImpl = terminalOnOffDaoImpl;
    }
    
}
