package com.gmcc.boss.selfsvc.login.service;

import com.gmcc.boss.selfsvc.login.model.TerminalOnOffPO;

/**
 * 对用户开关机信息进行记录
 * @author lWX5316086
 * @version 
 * @see 
 * @since
 */
public interface TerminalOnOffService
{
    
    /**
     * 开机操作
     * 
     * @param terminalOnOffPO
     */
    public void termOnOff(TerminalOnOffPO terminalOnOffPO);
    
    /**
     * 更新开关机心跳表
     * 
     * @param termId
     */
    public void updateTermHeart(String termId);
    
    /** 根据termid获取region
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     * @remark add begin qWX279398 2015-12-24 OR_SD_201511_596 开关机历史表新增region
     */
    public int getRegionByTermId (String termId);

}
